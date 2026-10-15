package com.soa.validation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record OrderRequest(
        @NotBlank String customerName,
        @NotBlank String productName,
        @NotNull @Positive Integer quantity,
        @NotNull @Positive Double price
) {
}