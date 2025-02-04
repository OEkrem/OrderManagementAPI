package com.oekrem.SpringMVCBackEnd.Controller;

import com.oekrem.SpringMVCBackEnd.Models.Address;
import com.oekrem.SpringMVCBackEnd.Services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AddressController {

    private AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {this.addressService = addressService;}

    @GetMapping("/addresses")
    public List<Address> getAddresses(){
        return addressService.findAll();
    }

    @GetMapping("/addresses/{id}")
    public Address getAddressById(@PathVariable int id){
        return addressService.getAddressById(id);
    }

    @PostMapping("/address/add")
    public ResponseEntity<String> addAddress(@RequestBody Address address){
        System.out.println(address);
        if (address.getUser() == null || address.getUser().getId() == null) {
            return ResponseEntity.badRequest().body("User ID is required!");
        }

        addressService.addAddress(address);
        return ResponseEntity.ok("Address added successfully!");
    }

    @PostMapping("/address/update")
    public void updateAddress(@RequestBody Address address){
        addressService.updateAddress(address);
    }

    @PostMapping("/address/delete")
    public void deleteAddress(@RequestBody Address address){
        addressService.deleteAddress(address);
    }
}
