package com.example.uzum_market.service;

import com.example.uzum_market.dto.OrderHistoryDTO;
import com.example.uzum_market.dto.PaymentHistoryDTO;
import com.example.uzum_market.enums.OrderStatus;
import com.example.uzum_market.exceptions.RestException;
import com.example.uzum_market.model.*;
import com.example.uzum_market.repository.AddressRepository;
import com.example.uzum_market.repository.HistoryItemRepository;
import com.example.uzum_market.repository.HistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HistoryService {
    private final HistoryRepository historyRepository;
    private final AddressRepository addressRepository;
    private final HistoryItemRepository historyItemRepository;

    public List<OrderHistoryDTO> showOrderHistory(OrderStatus orderStatus) {
        return null;
    }

    public List<PaymentHistoryDTO> showPaymentHistory() {
        return null;
    }

    public void addHistory(Promocode promocode, Integer addressId, List<OrderItem> orderItems, User user, Payment payment, Orders order) {

        Address address = addressRepository.findById(addressId).orElseThrow(() -> RestException.restThrow("Address not found", HttpStatus.NOT_FOUND));
        History history = History.builder()
                .createAt(System.currentTimeMillis())
                .status(OrderStatus.ORDER)
                .user(user)
                .payment(payment)
                .address(address)
                .promocode(promocode)
                .build();
        History history1 = historyRepository.save(history);

        for (OrderItem orderItem : orderItems) {

            HistoryItem historyItem = getHistoryItem(order, orderItem, history1);

            historyItemRepository.save(historyItem);
        }
    }

    private static HistoryItem getHistoryItem(Orders order, OrderItem orderItem, History history1) {
        return HistoryItem.builder()
                .history(history1)
                .count(orderItem.getCount())
                .price(orderItem.getCurPrice())
                .order(order)
                .attachmentId(orderItem.getPrice().getAttachments().get(0).getId())
                .seller(orderItem.getPrice().getProduct().getSeller())
                .colorName(orderItem.getColor().getName())
                .specificationsName(orderItem.getPrice().getSpecification().getName())
                .createdAt(System.currentTimeMillis())
                .specificationsType(orderItem.getPrice().getSpecification().getTitle())
                .orderStatus(OrderStatus.ORDER)
                .product(orderItem.getPrice().getProduct())
                .build();
    }
}
