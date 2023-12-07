package com.example.uzum_market.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaginationDTO<T> {

    private List<T> content;

    private int number;

    private int size;

    private int totalPages;

    private long totalElements;

    private int numberOfElements;

    private PaginationDTO(List<T> content, int totalPages, int totalElements) {
        this.content = content;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
    }

    public static <E> PaginationDTO<E> makePagination(List<E> content, int totalPages, int totalElements) {
        return new PaginationDTO<>(content, totalPages, totalElements);
    }


}
