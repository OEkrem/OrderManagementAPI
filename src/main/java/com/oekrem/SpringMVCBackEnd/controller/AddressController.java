package com.oekrem.SpringMVCBackEnd.controller;

import com.oekrem.SpringMVCBackEnd.dto.Request.CreateAddressRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.PatchAddressRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.UpdateAddressRequest;
import com.oekrem.SpringMVCBackEnd.dto.Response.AddressResponse;
import com.oekrem.SpringMVCBackEnd.services.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/addresses")
@RequiredArgsConstructor
@Tag(name = "Address Controller", description = "Manages Address operations")
public class AddressController {

    private final AddressService addressService;

    @Operation(summary = "Get all addresses", description = "lists all the addresses in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful", content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Page.class),
                            array = @ArraySchema(schema = @Schema(implementation = AddressResponse.class)))
            }),
            @ApiResponse(responseCode = "400", description = "Bad Request (Invalid query parameters)"),
            @ApiResponse(responseCode = "401", description = "Unauthorized, authentication required (JWT token required)"),
            //@ApiResponse(responseCode = "403", description = "Forbidden, user does not have access"),
    })
    @GetMapping
    public ResponseEntity<Page<AddressResponse>>getAddresses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) Long userId
    ){
        return ResponseEntity.ok(addressService.findAll(page, size, userId));
    }

    @Operation(summary = "Get Addresses By Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful", content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AddressResponse.class))
            }),
            @ApiResponse(responseCode = "400", description = "Bad Request (Invalid address id format)"),
            @ApiResponse(responseCode = "401", description = "Unauthorized, authentication required"),
            //@ApiResponse(responseCode = "403", description = "Forbidden, User does not have access"),
            @ApiResponse(responseCode = "404", description = "Address not found"),
    })
    @GetMapping("/{addressId}")
    //@PreAuthorize("hasRole('ADMIN') or @addressSecurity.isOwner(#addressId)")
    public ResponseEntity<AddressResponse> getAddressById(@PathVariable Long addressId){
        return ResponseEntity.ok(addressService.getAddressById(addressId));
    }

    @Operation(summary = "Add address")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created", content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AddressResponse.class))
            }),
            @ApiResponse(responseCode = "400", description = "Bad Request (Invalid or missing address details)"),
            @ApiResponse(responseCode = "401", description = "Unauthorized, authentication required"),
            //@ApiResponse(responseCode = "403", description = "Forbidden, user does not have permission to add an address"),
            @ApiResponse(responseCode = "409", description = "Conflict, Address could not be created")
    })
    @PostMapping
    public ResponseEntity<AddressResponse> addAddress(@RequestBody @Valid CreateAddressRequest addressRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(addressService.addAddress(addressRequest));
    }

    @Operation(summary = "Update address")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful", content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AddressResponse.class))
            }),
            @ApiResponse(responseCode = "400", description = "Bad Request (Invalid or missing address details)"),
            @ApiResponse(responseCode = "401", description = "Unauthorized, authentication required"),
            //@ApiResponse(responseCode = "403", description = "Forbidden, User does not have permission to update address"),
            @ApiResponse(responseCode = "404", description = "Address Not Found")
    })
    @PutMapping("/{addressId}")
    public ResponseEntity<AddressResponse> updateAddress(@PathVariable Long addressId, @RequestBody @Valid UpdateAddressRequest addressRequest){
        return ResponseEntity.ok(addressService.updateAddress(addressId, addressRequest));
    }

    @Operation(summary = "Patch address")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful", content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AddressResponse.class))
            }),
            @ApiResponse(responseCode = "400", description = "Bad Request (Invalid or missing address details)"),
            @ApiResponse(responseCode = "401", description = "Unauthorized, authentication required"),
            //@ApiResponse(responseCode = "403", description = "Forbidden, User does not have permission to patch address"),
            @ApiResponse(responseCode = "404", description = "Address Not Found")
    })
    @PatchMapping("/{addressId}")
    public ResponseEntity<AddressResponse> patchAddress(@PathVariable Long addressId, @RequestBody @Valid PatchAddressRequest patchAddressRequest){
        return ResponseEntity.ok(addressService.patchAddress(addressId ,patchAddressRequest));
    }

    @Operation(summary = "Delete address")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Address deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request (Invalid address id format)"),
            @ApiResponse(responseCode = "401", description = "Unauthorized, authentication required"),
            //@ApiResponse(responseCode = "403", description = "Forbidden, Only admins and address owners can delete"),
            @ApiResponse(responseCode = "404", description = "Address Not Found")
    })
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
