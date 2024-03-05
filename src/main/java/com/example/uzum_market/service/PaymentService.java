package com.example.uzum_market.service;

import com.example.uzum_market.enums.TransferStatus;
import com.example.uzum_market.model.*;
import com.example.uzum_market.repository.BalanceRepository;
import com.example.uzum_market.repository.PaymentRepository;
import com.example.uzum_market.repository.PaymentStoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final BalanceRepository balanceRepository;
    private final PaymentRepository paymentRepository;
    private final PaymentStoryRepository paymentStoryRepository;

    @Async
    public void withdrawMoney(Promocode promocode, List<OrderItem> orderItems, double totalPrice, User user, Integer paymentId) {
        Balance balance = Balance.builder().id(user.getBalance().getId()).amount(user.getBalance().getAmount() - totalPrice).isActive(true).build();
        savePaymentHistory(user,null,paymentId,totalPrice);
        balanceRepository.save(balance);
        orderItems.forEach(orderItem -> {
            User seller = orderItem.getPrice().getProduct().getSeller().getUser();
            double sellPrice = (orderItem.getCurPrice() - orderItem.getCurPrice()*promocode.getDiscount()/100)*orderItem.getCount();
            double amount = seller.getBalance().getAmount() +sellPrice ;
            Balance balance1 = Balance.builder().isActive(true).id(seller.getBalance().getId()).amount(amount).build();
            balanceRepository.save(balance1);
            savePaymentHistory(user, seller, paymentId, sellPrice);
        });
    }

    private void savePaymentHistory(User user, User seller, Integer paymentId, double amount) {
        PaymentStory paymentStory = PaymentStory.builder()
                .payment(paymentRepository.findById(paymentId).orElseThrow())
                .status(TransferStatus.SUCCESSFUL)
                .sender(user)
                .receiver(seller)
                .createAt(System.currentTimeMillis())
                .amount(amount)
                .build();
        paymentStoryRepository.save(paymentStory);
    }
}
