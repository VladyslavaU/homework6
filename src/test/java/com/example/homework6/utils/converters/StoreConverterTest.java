package com.example.homework6.utils.converters;

import com.example.homework6.model.Store;
import com.example.homework6.utils.dto.StoreCreateDto;
import com.example.homework6.utils.dto.StoreDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;

import static com.example.homework6.TestConstants.ADDRESS;
import static com.example.homework6.TestConstants.ID;
import static com.example.homework6.TestConstants.NAME;
import static com.example.homework6.TestConstants.NAME_TWO;
import static com.example.homework6.TestConstants.PHONE;
import static com.example.homework6.TestConstants.PHONE_TWO;
import static org.junit.jupiter.api.Assertions.assertEquals;


class StoreConverterTest {
    private StoreCreateDto storeCreateDto;
    private StoreDto storeDto;
    private Store store;

    @BeforeEach
    void setUp() {
        storeCreateDto = new StoreCreateDto()
                .setName(NAME)
                .setPhone(PHONE)
                .setAddress(ADDRESS);

        storeDto = new StoreDto()
                .setName(NAME_TWO)
                .setPhone(PHONE_TWO)
                .setAddress(ADDRESS);

        store = new Store()
                .setId(ID)
                .setName(NAME)
                .setPhone(PHONE)
                .setAddress(ADDRESS)
                .setProducts(new HashSet<>());
    }

    @Test
    void fromDtoCreate() {
        final Store actual = StoreConverter.fromDtoCreate(storeCreateDto);

        assertEquals(actual.getName(), storeCreateDto.getName());
        assertEquals(actual.getPhone(), storeCreateDto.getPhone());
        assertEquals(actual.getAddress(), storeCreateDto.getAddress());
    }

    @Test
    void fromDtoUpdate() {
        final Store actual = StoreConverter.fromDtoUpdate(storeDto, store);

        assertEquals(actual.getName(), storeDto.getName());
        assertEquals(actual.getPhone(), storeDto.getPhone());
        assertEquals(actual.getAddress(), storeDto.getAddress());
    }

    @Test
    void toDto() {
        final StoreDto actual = StoreConverter.toDto(store);

        assertEquals(actual.getName(), store.getName());
        assertEquals(actual.getPhone(), store.getPhone());
        assertEquals(actual.getAddress(), store.getAddress());
    }

    @Test
    void testToDto() {
        final List<StoreDto> storeDtos = StoreConverter.toDto(List.of(store));
        final StoreDto actual = storeDtos.get(0);

        assertEquals(storeDtos.size(), 1);
        assertEquals(actual.getName(), store.getName());
        assertEquals(actual.getPhone(), store.getPhone());
        assertEquals(actual.getAddress(), store.getAddress());
    }
}
