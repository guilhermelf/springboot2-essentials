package com.guilhermelf.springboot2essentials.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnimePostRequestBody {
    @NotEmpty(message = "The name of the anime cannot be empty")
    @Schema(description = "This is the anime name", example = "Dragon Ball Super")
    private String name;
}