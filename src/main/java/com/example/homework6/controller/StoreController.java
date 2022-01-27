package com.example.homework6.controller;

import com.example.homework6.service.abstraction.StoreService;
import com.example.homework6.utils.converters.StoreConverter;
import com.example.homework6.utils.dto.StoreCreateDto;
import com.example.homework6.utils.dto.StoreDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/store")
@RequiredArgsConstructor
public class StoreController {
    private final StoreService storeService;

    @RequestMapping("/all")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StoreDto>> getAllStores() {
        return ResponseEntity.ok(StoreConverter.toDto(storeService.getStores()));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StoreDto> getStore(@RequestParam Long id) {
        return ResponseEntity.ok(StoreConverter.toDto(storeService.getStore(id)));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StoreDto> createStore(@Valid @RequestBody StoreCreateDto storeDto) {
        return ResponseEntity.ok(StoreConverter.toDto(storeService.createStore(storeDto)));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StoreDto> updateStore(@Valid @RequestParam Long id, @RequestBody StoreDto storeDto) {
        return ResponseEntity.ok(StoreConverter.toDto(storeService.updateStore(id, storeDto)));
    }

    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteStore(@RequestParam Long id) {
        storeService.deleteStore(id);
    }

    @RequestMapping("/products/add")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StoreDto> addProductToStore(@RequestParam Long id, @RequestParam String productName) {
        return ResponseEntity.ok(StoreConverter.toDto(storeService.addProductToStore(id, productName)));
    }

    @RequestMapping("/products/delete")
    @DeleteMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteProductFromStore(@RequestParam Long id, @RequestParam String productName) {
        storeService.deleteProductFromStore(id, productName);
    }
}
