package com.oekrem.SpringMVCBackEnd.controller;

import com.oekrem.SpringMVCBackEnd.dto.Request.CreateAddressRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.UpdateAddressRequest;
import com.oekrem.SpringMVCBackEnd.dto.Response.AddressResponse;
import com.oekrem.SpringMVCBackEnd.services.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/addresses")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;


    @GetMapping
    public List<AddressResponse> getAddresses(){
        return addressService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressResponse> getAddressById(@PathVariable Long id){
        return ResponseEntity.ok(addressService.getAddressById(id));
    }


    @GetMapping("/users/{id}")
    public List<AddressResponse> getAddressesByUserId(@PathVariable Long id){
        return addressService.getAddressesByUserId(id);
    }

    @PostMapping("/users/{id}")
    public ResponseEntity<CreateAddressRequest> addAddress(@PathVariable Long id, @RequestBody CreateAddressRequest addressRequest){
        CreateAddressRequest address = addressService.addAddress(id, addressRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(address);
    }

    // user_id ve address body'ye ihtiyacımız var
    @PutMapping("/users/{id}")
    public ResponseEntity<UpdateAddressRequest> updateAddress(@PathVariable Long id, @RequestBody UpdateAddressRequest addressRequest){
        UpdateAddressRequest address= addressService.updateAddress(id, addressRequest);
        return ResponseEntity.ok(address);
    }

    // address id üzerinden siliniyor
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long id){
        addressService.deleteAddress(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
