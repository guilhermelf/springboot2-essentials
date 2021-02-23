package com.guilhermelf.springboot2essentials.requests;

import lombok.Data;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class AnimePostRequestBody {
    @NotEmpty(message = "The name of the anime cannot be empty")
    private String name;
}