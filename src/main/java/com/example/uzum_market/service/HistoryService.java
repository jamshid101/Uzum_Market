package com.example.uzum_market.service;

import com.example.uzum_market.dto.OrderHistoryDTO;
import com.example.uzum_market.dto.PaymentHistoryDTO;
import com.example.uzum_market.enums.OrderStatus;
import com.example.uzum_market.repository.HistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HistoryService {
    private final HistoryRepository historyRepository;

    public List<OrderHistoryDTO> showOrderHistory(OrderStatus orderStatus) {
        return null;
    }

    public List<PaymentHistoryDTO> showPaymentHistory() {
        return null;
    }
}
