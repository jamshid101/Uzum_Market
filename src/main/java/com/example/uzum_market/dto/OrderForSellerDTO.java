package com.example.uzum_market.dto;

import com.example.uzum_market.enums.OrderStatus;
import com.example.uzum_market.model.Address;
import com.example.uzum_market.model.Promocode;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderForSellerDTO {
       private @NotNull Integer orderId;

       private Double price;

       private Address address;

       private Long date;

       private String firstName;

       private String lastName;

       private String phoneNumber;

       private Promocode promocode;

       private OrderStatus orderStatus;

       private Integer count;

       private String color;

       private String specificationType;

       private String specificationName;
}

