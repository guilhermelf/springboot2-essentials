package com.guilhermelf.springboot2essentials.controller;

import com.guilhermelf.springboot2essentials.domain.Anime;
import com.guilhermelf.springboot2essentials.requests.AnimePostRequestBody;
import com.guilhermelf.springboot2essentials.requests.AnimePutRequestBody;
import com.guilhermelf.springboot2essentials.services.AnimeService;
import com.guilhermelf.springboot2essentials.util.DateUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "animes")
@Log4j2
@RequiredArgsConstructor
public class AnimeController {
    private final DateUtil dateUtil;
    private final AnimeService animeService;

    @GetMapping
    @Operation(summary = "List all animes paginated.", description = "The default size is 20, use the parameter size to change the default value",
    tags = {"anime"})
    public ResponseEntity<Page<Anime>> list(@ParameterObject Pageable pageable) {
        return ResponseEntity.ok(animeService.findAll(pageable));
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<Anime>> listAll() {
        return ResponseEntity.ok(animeService.listAllNonPageable());
    }


    @GetMapping(path = "/{id}")
    public ResponseEntity<Anime> findById(@PathVariable Long id) {
        return ResponseEntity.ok(animeService.findByIdOrThrowBadRequestException(id));
    }

    @GetMapping(path = "/by-id/{id}")
    public ResponseEntity<Anime> findByIdAuthenticationPrincipal(@PathVariable Long id,
                                                                 @AuthenticationPrincipal UserDetails userDetails) {
        log.info(userDetails);
        return ResponseEntity.ok(animeService.findByIdOrThrowBadRequestException(id));
    }

    @GetMapping(path = "/find")
    public ResponseEntity<List<Anime>> findByName(@RequestParam String name) {
        return ResponseEntity.ok(animeService.findByName(name));
    }

    @PostMapping
    public ResponseEntity<Anime> save(@RequestBody @Valid AnimePostRequestBody animePostRequestBody) {
        return new ResponseEntity<>(animeService.save(animePostRequestBody), HttpStatus.CREATED);
    }

    @DeleteMapping("/admin/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "When anime does not exist in the database")
    })
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        animeService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping()
    public ResponseEntity<Void> replace(@RequestBody AnimePutRequestBody animePutRequestBody) {
        animeService.replace(animePutRequestBody);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
