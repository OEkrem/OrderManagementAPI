package com.oekrem.SpringMVCBackEnd.security;

import com.oekrem.SpringMVCBackEnd.exceptions.UserExceptions.UserNotFoundException;
import com.oekrem.SpringMVCBackEnd.models.OrderDetail;
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
            case ORDER_DETAILS -> {
                User test = orderDetailRepository.getOwnerById( (Long) entityId).orElse(null);
                System.out.println("Order detail user: " + test.getEmail() + ", Authenticated email: " + email);
                yield test != null && test.getEmail().equals(email);
            }
            case ORDER -> orderRepository.getOwnerById( (Long) entityId)
                    .map(p-> p.equals(user))
                    .orElse(false);
            case USER -> {
                if(entityId instanceof Long) {
                    User test = userRepository.getUserById( (Long) entityId).orElse(null);
                    yield test != null && test.getId().equals(entityId);

                } else if (entityId instanceof String) {
                    User test = userRepository.getUserByEmail( (String) entityId).orElse(null);
                    yield test != null && test.getEmail().equals(entityId);
                }
                yield false;
            }
            default -> false;
        };
    }
}
