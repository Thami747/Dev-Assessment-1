/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thamidev.addressproject.utilities;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.thamidev.addressproject.model.Address;
import java.io.*;
import java.util.List;

/**
 *
 * @author thami
 */
public class AddressObjectMapper {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final String filePath = "src/main/resources/addresses.json";
    public static List<Address> getAddressesFromJson() throws IOException {
        InputStream inputStream = new FileInputStream(filePath);
        TypeReference<List<Address>> typeReference = new TypeReference<>() {};
        return objectMapper.readValue(inputStream, typeReference);
    }
}
