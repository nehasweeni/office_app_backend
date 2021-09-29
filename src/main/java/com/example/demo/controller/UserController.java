package com.example.demo.controller;
import com.example.demo.dto.UserDTO;
import com.example.demo.services.api.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * Gets lists of all users
     * @return listUserDTO
     */
    @GetMapping(value = "/all_users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllUsers() {
        try {
            return ResponseEntity.ok(userService.getAllUsers());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    /**
     *
     * @param userID Used to get other details about specific user
     * @return details about users
     */
    @GetMapping(value = "/findById/{userID}",  produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<?> getUserById(@PathVariable("userID") Integer userID) {
        try {
            return ResponseEntity.ok(userService.getUserById(userID));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    /**
     *
     * @param userID Used to get other details about specific user
     * @return user details
     */

    @GetMapping(value = "/findUserById/{userID}",  produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<?> getById(@PathVariable("userID") Integer userID) {
        try {
            return ResponseEntity.ok(userService.getByIdUsingNativeQuery(userID));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    /**
     * Gets lists of all users
     * @return listDataDTO
     */

    @GetMapping(value = "/getAllData",  produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<?> getAllData() {
        try {
            return ResponseEntity.ok(userService.getAllData());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    /**
     * Gets list of all bookings made by a specific user
     * @param userID Used to get all the specific bookings
     * @return bookingsByIdDTOList - bookings made by the user
     */
    @GetMapping(value = "/getBookingByID/{userID}",  produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<?> getBookingByID(@PathVariable("userID") Integer userID) {
        try {
            return ResponseEntity.ok(userService.getBookingByID(userID));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


    /**
     * Deletes user using the id
     * @param userID Used to delete specific user
     * @return "User deleted"
     */

    @DeleteMapping(value = "/deleteUser/{userID}")
    public ResponseEntity<?> deleteUserById(@PathVariable("userID") Integer userID) {
        try {
            userService.deleteUserById(userID);
            return new ResponseEntity<>("User deleted", HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    /**
     *
     * @param userDTO instance
     * @return added_user
     */

    @PostMapping(value = "/adduser", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addUser(@RequestBody UserDTO userDTO) {
        try {
            return ResponseEntity.ok(userService.addUser(userDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


    /**
     *
     * @param userDTO instance
     * @return updated_user
     */
    @PutMapping(value = "/updateUser", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<?> updateUser(@RequestBody UserDTO userDTO) {
        try {
            userService.updateUserById(1, userDTO);
            return new ResponseEntity<>("User updated", HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
