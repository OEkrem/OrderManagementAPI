package com.oekrem.SpringMVCBackEnd.Controller;

import com.oekrem.SpringMVCBackEnd.Dto.Response.AddressResponse;
import com.oekrem.SpringMVCBackEnd.Services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

    private AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {this.addressService = addressService;}

    @GetMapping
    public List<AddressResponse> getAddresses(){
        return addressService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressResponse> getAddressById(@PathVariable Long id){
        return ResponseEntity.ok(addressService.getAddressById(id));
    }

    // Add Update Delete işlemleri user üzerinden gerçekleştirilebiliyor zaten
    /*
    @PostMapping
    public ResponseEntity<AddAddressRequest> addAddress(@RequestBody AddAddressRequest address){
        addressService.addAddress(address);
        return ResponseEntity.ok(address);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Address> updateAddress(@PathVariable Long id, @RequestBody Address address){
        addressService.updateAddress(address);
        return ResponseEntity.ok(address);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long id){
        addressService.deleteAddress(id);
        return ResponseEntity.noContent().build();
    }
    */
}
