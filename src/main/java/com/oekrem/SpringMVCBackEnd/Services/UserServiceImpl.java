package com.oekrem.SpringMVCBackEnd.Services;

import com.oekrem.SpringMVCBackEnd.Business.UserManager;
import com.oekrem.SpringMVCBackEnd.DataAccess.AddressRepository;
import com.oekrem.SpringMVCBackEnd.DataAccess.UserRepository;
import com.oekrem.SpringMVCBackEnd.Models.Address;
import com.oekrem.SpringMVCBackEnd.Models.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private UserManager userManager;
    private UserRepository userRepository;
    private AddressRepository addressRepository;

    @Autowired
    public UserServiceImpl(UserManager userManager, UserRepository userRepository, AddressRepository addressRepository) {
        this.userManager = userManager;
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    @Transactional
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public void addUser(User user) {
        if(!user.getAddresses().isEmpty() || user.getAddresses() != null){
            Set<Address> addresses = new HashSet<>();
            for(Address address : user.getAddresses()){
                if(address.getId() != null)
                    addresses.add(addressRepository.getAddressById(address.getId().intValue()));
                if(address.getId() == null && address.getName() != null) {
                    addresses.add(address);
                    address.setUser(user);
                    addressRepository.addAddress(address);
                }
            }
            user.setAddresses(addresses);
        }
        userRepository.addUser(user);

    }

    @Override
    @Transactional
    public void updateUser(User user) {
        if(!user.getAddresses().isEmpty() || user.getAddresses() != null){
            Set<Address> addresses = new HashSet<>();
            for(Address address : user.getAddresses()){
                if(address.getId() != null)
                    addresses.add(addressRepository.getAddressById(address.getId().intValue()));
                if(address.getId() == null && address.getName() != null){
                    addresses.add(address);
                    address.setUser(user);
                    addressRepository.addAddress(address);
                }
            }
            user.setAddresses(addresses);
        }
        userRepository.updateUser(user);
    }

    @Override
    @Transactional
    public void deleteUser(User user) {
        userRepository.deleteUser(user);
    }

    @Override
    @Transactional
    public User getUserById(int id) {
        return userRepository.getUserById(id);
    }
}
