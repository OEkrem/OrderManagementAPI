package com.oekrem.SpringMVCBackEnd.controller;

import com.oekrem.SpringMVCBackEnd.dto.Request.CreateAddressRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.PatchAddressRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.UpdateAddressRequest;
import com.oekrem.SpringMVCBackEnd.dto.Response.AddressResponse;
import com.oekrem.SpringMVCBackEnd.services.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/addresses")
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
    public ResponseEntity<AddressResponse> addAddress(@PathVariable Long id, @RequestBody CreateAddressRequest addressRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(addressService.addAddress(id, addressRequest));
    }

    // user_id ve address body'ye ihtiyacımız var
    @PutMapping("/users/{id}")
    public ResponseEntity<AddressResponse> updateAddress(@PathVariable Long id, @RequestBody UpdateAddressRequest addressRequest){
        return ResponseEntity.ok(addressService.updateAddress(id, addressRequest));
    }

    @PatchMapping("/users/{userId}")
    public ResponseEntity<AddressResponse> patchAddress(@PathVariable Long userId, @RequestBody PatchAddressRequest patchAddressRequest){
        return ResponseEntity.ok(addressService.patchAddress(userId ,patchAddressRequest));
    }

    // address id üzerinden siliniyor
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long id){
        addressService.deleteAddress(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /*
    @PatchMapping("/{id}")
public ResponseEntity<AddressResponse> patchAddress(
        @PathVariable Long id,
        @AuthenticationPrincipal UserPrincipal userPrincipal,
        @RequestBody PatchAddressRequest patchAddressRequest) {
    return ResponseEntity.ok(addressService.patchAddress(id, userPrincipal.getId(), patchAddressRequest));
}
bu şekilde autheentication ile de yapılabiliyor knk sonra ekleriz
     */

}
