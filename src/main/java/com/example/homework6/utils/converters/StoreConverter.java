package com.example.homework6.utils.converters;

import com.example.homework6.model.Store;
import com.example.homework6.utils.dto.StoreCreateDto;
import com.example.homework6.utils.dto.StoreDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class StoreConverter {
    public static Store fromDtoCreate(final StoreCreateDto storeDto) {
        return new Store()
                .setName(storeDto.getName())
                .setAddress(storeDto.getAddress())
                .setPhone(storeDto.getPhone());
    }

    public static Store fromDtoUpdate(final StoreDto storeDto, final Store toUpdate) {
        return toUpdate
                .setName(Optional.ofNullable(storeDto.getName()).orElse(toUpdate.getName()))
                .setAddress(Optional.ofNullable(storeDto.getAddress()).orElse(toUpdate.getAddress()))
                .setPhone(Optional.ofNullable(storeDto.getPhone()).orElse(toUpdate.getPhone()));
    }

    public static StoreDto toDto(final Store store) {
        return new StoreDto()
                .setId(store.getId())
                .setName(store.getName())
                .setAddress(store.getAddress())
                .setPhone(store.getPhone())
                .setProductDtos(store.getProducts() != null ? store.getProducts().stream().map(ProductConverter::toDto).collect(Collectors.toList()) : new ArrayList<>());
    }

    public static List<StoreDto> toDto(final List<Store> stores) {
        return stores.stream()
                .map(StoreConverter::toDto)
                .collect(Collectors.toList());
    }
}
