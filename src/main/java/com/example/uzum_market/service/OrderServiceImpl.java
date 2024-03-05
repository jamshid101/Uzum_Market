package com.example.uzum_market.service;

import com.example.uzum_market.dto.*;
import com.example.uzum_market.enums.OrderStatus;
import com.example.uzum_market.enums.Status;
import com.example.uzum_market.exceptions.RestException;
import com.example.uzum_market.model.*;
import com.example.uzum_market.repository.*;
import com.example.uzum_market.utils.CommonUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final AddressRepository addressRepository;
    private final PromoCodeRepository promoCodeRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final PaymentRepository paymentRepository;
    private final HistoryService historyService;
    private final PaymentService paymentService;
    private final PriceRepository priceRepository;

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public void addOrder(AddOrderDTO addOrderDTO) {
        User user = CommonUtils.getCurrentUserFromContext();
        Address address = addressRepository.findById(addOrderDTO.getAddress()).orElseThrow(() -> RestException.restThrow("Address not found", HttpStatus.NOT_FOUND));
        Payment payment = paymentRepository.findById(addOrderDTO.getPayment()).orElseThrow(() -> RestException.restThrow("Payment type not found", HttpStatus.NOT_FOUND));
        Promocode promocode = promoCodeRepository.findByNameAndIsActive(addOrderDTO.getPromocode(), true).orElseThrow(() -> RestException.restThrow("Promocode not found", HttpStatus.NOT_FOUND));
        List<OrderItem> orderItems = orderItemRepository.findAllById(addOrderDTO.getAddOrderItemDTOS());
        Orders order = Orders.builder()
                .user(user)
                .address(address)
                .createdDate(System.currentTimeMillis())
                .payment(payment)
                .promocode(promocode)
                .orderItems(orderItems)
                .status(Status.BOOKED)
                .build();
        Orders orders = orderRepository.save(order);
        double totalPrice = checkCredentials(orderItems, user, promocode, orders);
        paymentService.withdrawMoney(promocode,orderItems, totalPrice, user, addOrderDTO.getPayment());
        historyService.addHistory(promocode,addOrderDTO.getAddress(), orderItems, user, payment, order);

    }



    @Override
    public ApiResult<Map<OrderStatus, List<OrderForSellerDTO>>> orderForSeller(MainCriteriaForOrder mainCriteriaForOrder) {
        Integer sellerId = CommonUtils.getCurrentUserFromContext().getId();
        List<OrderItem> allForSeller = orderItemRepository.findAllForSeller(sellerId);
        Map<OrderStatus, List<OrderForSellerDTO>> orderForSellerDTOS = getOrderForSellerDTOS(allForSeller);
        return ApiResult.successResponse(orderForSellerDTOS);
    }

    @Override
    public ApiResult<String> changeOrderStatus(ChangeStatusDTO changeStatusDTO) {
        Integer id = CommonUtils.getCurrentUserFromContext().getId();
        OrderItem orderItem = orderItemRepository.findById(changeStatusDTO.getOrderItemId()).orElseThrow(() -> RestException.restThrow("order not found", HttpStatus.NOT_FOUND));
        Integer sellerId = orderItem.getPrice().getProduct().getSeller().getId();
        if (!sellerId.equals(id)) throw RestException.restThrow("This product is not belong to you",HttpStatus.CONFLICT);
        orderItem.setOrderStatus(changeStatusDTO.getOrderStatus());
        orderItemRepository.save(orderItem);
        return ApiResult.successResponse(orderItem.getOrderStatus().name());
    }

    private static Map<OrderStatus, List<OrderForSellerDTO>> getOrderForSellerDTOS(List<OrderItem> allForSeller) {
        Map<OrderStatus, List<OrderForSellerDTO>> map = new EnumMap<>(OrderStatus.class);
        for (OrderStatus value : OrderStatus.values()) {
            map.put(value,new ArrayList<>());
        }
        for (OrderItem orderItem : allForSeller) {
            OrderForSellerDTO orderForSellerDTO = OrderForSellerDTO.builder()
                    .specificationName(orderItem.getPrice().getSpecification().getTitle())
                    .orderId(orderItem.getOrder().getId())
                    .orderStatus(orderItem.getOrderStatus())
                    .color(orderItem.getColor().getName())
                    .firstName(orderItem.getOrder().getUser().getName())
                    .phoneNumber(orderItem.getOrder().getUser().getPhoneNumber())
                    .promocode(orderItem.getOrder().getPromocode())
                    .specificationType(orderItem.getPrice().getSpecification().getName())
                    .address(orderItem.getOrder().getAddress())
                    .count(orderItem.getCount())
                    .price(orderItem.getCurPrice())
                    .date(orderItem.getOrder().getCreatedDate())
                    .build();
            map.get(orderItem.getOrderStatus()).add(orderForSellerDTO);
        }
        return map;
    }


    private double checkCredentials(List<OrderItem> orderItems, User user, Promocode promocode, Orders order) {
        double maxAmount = 0;

        boolean isActive = user.getBalance().getIsActive();
        if (!isActive)  throw RestException.restThrow(" Your balance blocked!!!");
        for (OrderItem orderItem : orderItems) {
            Price price = orderItem.getPrice();
            int count = price.getCount();
            if (orderItem.getCount() > count) {
                throw RestException.restThrow(orderItem.getPrice().getProduct().getNameUz() + " Not enough, available count = " + count);
            }
            price.setCount(price.getCount() - orderItem.getCount());
            priceRepository.save(price);
            maxAmount += orderItem.getCurPrice() * orderItem.getCount();
            orderItem.setOrderStatus(OrderStatus.ORDER);
            orderItem.setOrder(order);
            orderItemRepository.save(orderItem);
        }
        maxAmount = maxAmount - maxAmount * promocode.getDiscount() / 100;
        if (user.getBalance().getAmount() < maxAmount) {
            throw RestException.restThrow(" Your balance enough, you need " + (maxAmount - user.getBalance().getAmount()) + " sum");
        }
        return maxAmount;

    }
}
