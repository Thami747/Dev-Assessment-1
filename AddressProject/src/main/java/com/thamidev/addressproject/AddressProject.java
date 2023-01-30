/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.thamidev.addressproject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thamidev.addressproject.model.Address;
import com.thamidev.addressproject.model.Type;
import com.thamidev.addressproject.utilities.AddressObjectMapper;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author thami
 */
public class AddressProject {

    static ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) {
        try {
            Address addressOne = new Address();
            addressOne.setId("1");
            addressOne.setType(new Type());
            addressOne.getType().setName("Postal Address");
            
            System.out.println("------------------------------PRETTY PRINT ADDRESS----------------------------------------------");
            prettyPrintAddress(addressOne);
            System.out.println("------------------------------PRETTY PRINT ALL ADDRESSES----------------------------------------------");
            prettyPrintAllAddress();
            System.out.println("------------------------------PRINT ADDRESS BY TYPE----------------------------------------------");
            printAddressByType(addressOne);
            System.out.println("------------------------------VALIDATE THE ADDRESS----------------------------------------------");
            isValidAddress(addressOne);

        } catch (IOException ex) {
            Logger.getLogger(AddressProject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static String prettyPrintAddress(Address address) throws IOException {
        List<Address> addressList = AddressObjectMapper.getAddressesFromJson();
        String prettyPrintMessage = "";
        String type = "";
        String lineDetails = "";
        String city = "";
        String provinceOrState = "";
        String postalCode = "";
        String country = "";
        for (Address newAddress : addressList) {
            if (newAddress.getId().equals(address.getId())) {
                if (newAddress.getType() != null) {
                    // pretty print type
                    type = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(newAddress.getType());
                }
                if (newAddress.getAddressLineDetail() != null) {
                    // pretty print line details
                    lineDetails = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(newAddress.getAddressLineDetail());
                }
                if (newAddress.getCityOrTown() != null) {
                    // pretty print city
                    city = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(newAddress.getCityOrTown());
                }
                if (newAddress.getProvinceOrState() != null) {
                    // pretty print province or state
                    provinceOrState = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(newAddress.getProvinceOrState());
                }
                if (newAddress.getPostalCode() != null) {
                    // pretty print postal code
                    postalCode = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(newAddress.getPostalCode());
                }
                if (newAddress.getCountry() != null) {
                    // pretty print country
                    country = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(newAddress.getCountry());
                }
                prettyPrintMessage = prettyPrintMessage.concat(type + ":").concat(lineDetails + " - ").concat(city + " - ").concat(provinceOrState + " - ").concat(postalCode + " - ").concat(country);
                break;
            }
        }
        System.out.println(prettyPrintMessage);
        return prettyPrintMessage;
    }

    public static void prettyPrintAllAddress() throws IOException {
        List<Address> addressList = AddressObjectMapper.getAddressesFromJson();
        String addressInfo;
        int count = 1;

        for (Address newAddress : addressList) {
            // pretty print type
            addressInfo = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(newAddress);
            System.out.println("Address " + count + "\n" + addressInfo);
            ++count;
        }
    }

    public static String printAddressByType(Address address) throws IOException {
        List<Address> addressList = AddressObjectMapper.getAddressesFromJson();
        String prettyPrintMessage = "";

        for (Address newAddress : addressList) {
            if (address.getType() != null) {
                if (newAddress.getType().getName().equals(address.getType().getName())) {
                    // pretty print type
                    prettyPrintMessage = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(newAddress);
                }
            }
        }
        System.out.println(prettyPrintMessage);
        return prettyPrintMessage;
    }
    
    public static String isValidAddress(Address address) throws IOException {
        List<Address> addressList = AddressObjectMapper.getAddressesFromJson();
        String responseMessage = "";
        for (Address newAddress : addressList) {
            if (newAddress.getId().equals(address.getId())) {
                if (newAddress.getPostalCode() != null && isNumeric(newAddress.getPostalCode())) {
                    responseMessage = "Successfully validated postal code.\n";
                } else {
                    responseMessage = "Postal code is null or is not numeric.";
                    break;
                }
               
                if (newAddress.getCountry() != null) {
                    responseMessage = responseMessage.concat("Successfully found country.\n");
                    
                    if (newAddress.getCountry().getCode().equalsIgnoreCase("ZA")) {
                        if (newAddress.getProvinceOrState() != null) {
                            responseMessage = responseMessage.concat("Successfully found the province: " + newAddress.getProvinceOrState().getName() + "\n");
                        } else {
                            responseMessage = "The country ZA requires a province or state but found null";
                            break;
                        }
                    }
                } else {
                    responseMessage = "Country is null.";
                    break;
                }
                
                if (newAddress.getAddressLineDetail() != null && !newAddress.getAddressLineDetail().getLine1().equals("")) {
                    responseMessage = responseMessage.concat("Successfully validated addressLine.");
                } else {
                    responseMessage = "No Addressline found for this address.";
                    break;
                }
            }
        }
        System.out.println(responseMessage);
        return responseMessage;
    }
    
    private static boolean isNumeric (String s) {
        if (s == null) {
            return false;
        }
        try {
            int number = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        }
        
        return true;
    }
}
