package com.guilhermelf.springboot2essentials.mapper;

import com.guilhermelf.springboot2essentials.domain.Anime;
import com.guilhermelf.springboot2essentials.requests.AnimePostRequestBody;
import com.guilhermelf.springboot2essentials.requests.AnimePutRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class AnimeMapper {
    public static final AnimeMapper INSTANCE = Mappers.getMapper(AnimeMapper.class);

    public abstract Anime toAnime(AnimePostRequestBody animePostRequestBody);

    public abstract Anime toAnime(AnimePutRequestBody animePutRequestBody);
}
