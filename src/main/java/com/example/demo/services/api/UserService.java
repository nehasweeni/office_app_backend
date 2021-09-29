package com.example.demo.services.api;

import com.example.demo.dto.UserDTO;

import java.util.List;

public interface UserService {
    /**
     * Gets lists of all users
     * @return listDataDTO
     */
    List<UserDTO> getAllData();

    /**
     * Gets list of all bookings made by a specific user
     * @param userID Used to get all the specific bookings
     * @return bookingsByIdDTOList - bookings made by the user
     */
    List<UserDTO> getBookingByID(Integer userID);

    /**
     * Gets lists of all users
     * @return listUserDTO
     */
    List<UserDTO> getAllUsers ();

    /**
     * adds user to user_reg table
     * @param userDTO instance
     * @return added_user
     */
    UserDTO addUser(UserDTO userDTO);

    /**
     *
     * @param id Used to get other details about specific user
     * @return details about users
     */
    UserDTO getUserById(Integer id);

    /**
     *
     * @param id Used to get other details about specific user
     * @return convertToUserDTO(user)
     */
    UserDTO getByIdUsingNativeQuery(Integer id);

    /**
     * Deletes user using the id
     * @param id Used to delete specific user
     */
    void deleteUserById(Integer id);

    /**
     *
     * @param id Updates for that specific user
     * @param userDTO instance
     * @return updated_user
     */
    UserDTO updateUserById(Integer id, UserDTO userDTO);

}
