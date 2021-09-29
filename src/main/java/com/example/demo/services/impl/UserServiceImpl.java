package com.example.demo.services.impl;

import com.example.demo.data.domain.User;
import com.example.demo.data.repository.UserRepository;
import com.example.demo.dto.UserDTO;
import com.example.demo.services.api.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<UserDTO> getAllData() {
        List<UserDTO> listDataDTO = new ArrayList<>();
        List<User> data = userRepository.findAll();

        data.forEach(data1 -> {
            UserDTO userDTO = convertToUserDTO(data1);
            System.out.println(data1.getBookingList().size());

            listDataDTO.add(userDTO);
        });
        System.out.println(data);
        return listDataDTO;

    }

    @Override
    public List<UserDTO> getBookingByID(Integer userID) {
        List<UserDTO> bookingsByIdDTOList = new ArrayList<>();
        List<User> users = userRepository.getAllData();

        users.forEach(booking -> {
            UserDTO userDTO = convertToUserDTO(booking);
            if (userDTO.getId() == userID) {
                bookingsByIdDTOList.add(userDTO);
            }
        });

        return bookingsByIdDTOList;
    }




    @Override
    public List<UserDTO> getAllUsers() {

        List<UserDTO> listUserDTO = new ArrayList<>();
        List<User> users = userRepository.findAll();

        users.forEach(user -> {
            UserDTO userDTO = convertToUserDTO(user);
            listUserDTO.add(userDTO);
        });

        return listUserDTO;
    }


    @Override
    public UserDTO addUser(UserDTO userDTO) {

        User user = new User();

        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());

        User saved = userRepository.save(user);

        UserDTO added_user = new UserDTO();
        added_user.setName(saved.getName());

        return added_user;
    }

    @Override
    public UserDTO getUserById(Integer id) {

        return getAllUsers()
                .stream()
                .filter(t -> id.equals(t.getId()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public UserDTO getByIdUsingNativeQuery(Integer id) {

        User user = userRepository.getUserByID(id);

        return convertToUserDTO(user);
    }


    @Override
    public void deleteUserById(Integer id) {

        userRepository.findById(id).ifPresent(userRepository::delete);
    }

    @Override
    public UserDTO updateUserById(Integer id, UserDTO userDTO) {

        User user = userRepository.findById(id).orElse(null);

        if (user != null) {
            user.setEmail(userDTO.getEmail());
            user.setPassword(userDTO.getPassword());
            user.setName(userDTO.getName());

            User saved = userRepository.save(user);

            UserDTO updated_user = new UserDTO();
            updated_user.setName(saved.getName());

            return updated_user;
        }

        return null;
    }


    UserDTO convertToUserDTO(User user) {


        UserDTO userDTO = new UserDTO();

        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());

        return userDTO;
    }



}
