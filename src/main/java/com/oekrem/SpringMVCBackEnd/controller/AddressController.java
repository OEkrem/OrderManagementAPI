package com.oekrem.SpringMVCBackEnd.controller;

import com.oekrem.SpringMVCBackEnd.dto.Request.CreateAddressRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.PatchAddressRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.UpdateAddressRequest;
import com.oekrem.SpringMVCBackEnd.dto.Response.AddressResponse;
import com.oekrem.SpringMVCBackEnd.services.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/addresses")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @GetMapping
    public ResponseEntity<Page<AddressResponse>>getAddresses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) Long userId
    ){
        return ResponseEntity.ok(addressService.findAll(page, size, userId));
    }

    @GetMapping("/{addressId}")
    public ResponseEntity<AddressResponse> getAddressById(@PathVariable Long addressId){
        return ResponseEntity.ok(addressService.getAddressById(addressId));
    }

    @PostMapping
    public ResponseEntity<AddressResponse> addAddress(@RequestBody CreateAddressRequest addressRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(addressService.addAddress(addressRequest));
    }

    // user_id ve address body'ye ihtiyacımız var
    @PutMapping("/{addressId}")
    public ResponseEntity<AddressResponse> updateAddress(@PathVariable Long addressId, @RequestBody UpdateAddressRequest addressRequest){
        return ResponseEntity.ok(addressService.updateAddress(addressId, addressRequest));
    }

    @PatchMapping("/{addressId}")
    public ResponseEntity<AddressResponse> patchAddress(@PathVariable Long addressId, @RequestBody PatchAddressRequest patchAddressRequest){
        return ResponseEntity.ok(addressService.patchAddress(addressId ,patchAddressRequest));
    }

    // address id üzerinden siliniyor
    @DeleteMapping("/{addressId}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long addressId){
        addressService.deleteAddress(addressId);
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
