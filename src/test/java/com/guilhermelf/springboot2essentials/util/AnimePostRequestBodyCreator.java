package com.guilhermelf.springboot2essentials.util;

import com.guilhermelf.springboot2essentials.domain.Anime;
import com.guilhermelf.springboot2essentials.requests.AnimePostRequestBody;

public class AnimePostRequestBodyCreator {
    public static AnimePostRequestBody createAnimePostRequestBody() {
        return AnimePostRequestBody.builder()
                .name(AnimeCreator.createAnimeToBeSaved().getName())
                .build();
    }
}
