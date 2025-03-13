package com.oekrem.SpringMVCBackEnd.security;

import com.oekrem.SpringMVCBackEnd.exceptions.UserExceptions.UserNotFoundException;
import com.oekrem.SpringMVCBackEnd.models.User;
import com.oekrem.SpringMVCBackEnd.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecurityService {

    private final AddressRepository addressRepository;
    private final UserRepository userRepository;
    private final PaymentRepository paymentRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final OrderRepository orderRepository;

    public boolean isOwner(Long entityId, EntityType entityType, String email) {
        User user = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        return switch (entityType){
            case ADDRESS -> addressRepository.getOwnerById(entityId)
                    .map(p -> p.equals(user))
                    .orElse(false);
            case PAYMENT -> paymentRepository.getOwnerById(entityId)
                    .map(p-> p.equals(user))
                    .orElse(false);
            case ORDER_DETAILS -> orderDetailRepository.getOwnerById(entityId)
                    .map(p-> p.equals(user))
                    .orElse(false);
            case ORDER -> orderRepository.getOwnerById(entityId)
                    .map(p-> p.equals(user))
                    .orElse(false);
            case USER -> userRepository.getUserById(entityId)
                    .map(p-> p.equals(user))
                    .orElse(false);
            default -> false;
        };
    }
}
