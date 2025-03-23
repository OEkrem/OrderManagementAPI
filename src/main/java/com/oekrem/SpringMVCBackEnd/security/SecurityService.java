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

    public boolean isOwner(Object entityId, EntityType entityType, String email) {
        User user = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        return switch (entityType){
            case ADDRESS -> addressRepository.getOwnerById((Long) entityId)
                    .map(p -> p.equals(user))
                    .orElse(false);
            case PAYMENT -> paymentRepository.getOwnerById( (Long) entityId)
                    .map(p-> p.equals(user))
                    .orElse(false);
            case ORDER_DETAILS -> orderDetailRepository.getOwnerById( (Long) entityId)
                    .map(p-> p.equals(user))
                    .orElse(false);
            case ORDER -> orderRepository.getOwnerById( (Long) entityId)
                    .map(p-> p.equals(user))
                    .orElse(false);
            case USER -> {
                if(entityId instanceof Long) {
                    yield userRepository.getUserById( (Long) entityId)
                            .map(p-> p.equals(user))
                            .orElse(false);
                } else if (entityId instanceof String) {
                    yield userRepository.getUserByEmail( (String) entityId)
                            .map(p-> p.equals(user))
                            .orElse(false);
                }
                yield false;
            }
            default -> false;
        };
    }
}
