package com.example.demo.services.impl;

import com.example.demo.data.domain.Booking;
import com.example.demo.data.domain.Office;
import com.example.demo.data.domain.User;
import com.example.demo.data.repository.BookingRepository;
import com.example.demo.data.repository.OfficeRepository;
import com.example.demo.data.repository.UserRepository;
import com.example.demo.dto.BookingDTO;
import com.example.demo.dto.OfficeDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.services.api.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Component
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final OfficeRepository officeRepository;

    @Override

    public List<BookingDTO> getAllBookings() {

        List<BookingDTO> bookingDTOList = new ArrayList<>();
        List<Booking> bookings = bookingRepository.findAllBookings();

        bookings.forEach(booking -> {
            BookingDTO bookingDTO = convertToBookingDTO(booking);
            bookingDTOList.add(bookingDTO);

        });
        System.out.println(bookingDTOList);
        return bookingDTOList;

    }

    @Override
    public List<BookingDTO> getAllBookingsUsingNativeQuery() {
        List<BookingDTO> bookingDTOList = new ArrayList<>();
        List<Booking> bookings = bookingRepository.getAllBookings();

        bookings.forEach(booking -> {
            BookingDTO bookingDTO = convertToBookingDTO(booking);
            bookingDTOList.add(bookingDTO);

        });
        System.out.println(bookingDTOList);
        return bookingDTOList;

    }

    @Override
    public List<BookingDTO> getAllBookingsByUserID(Integer userID) {
        List<BookingDTO> bookingDTOList = new ArrayList<>();
        List<Booking> bookings = bookingRepository.findAll();

        bookings.forEach(booking -> {
            BookingDTO bookingDTO = convertToBookingDTO(booking);
            if (bookingDTO.getUserID() == userID) {
                System.out.println(booking.getUser().getName());
                User user = booking.getUser();

                bookingDTO.setUserDTOS(convertToUserDTO(user));
                Office office = booking.getOffice();
                bookingDTO.setOfficeDTO(convertToOfficeDTO(office));

                bookingDTOList.add(bookingDTO);
            }
        });

        return bookingDTOList;

    }


    @Override
    public void deleteBookingByID(Integer userID, Integer booking_id) {

        bookingRepository.deleteBookingByUserAndBooking_id(userID, booking_id);

    }



    @Override
    public List<BookingDTO> getPreviousBookingOfUser(Integer userID) {
        List<BookingDTO> bookingDTOList = new ArrayList<>();
        List<Booking> previous_bookings = bookingRepository.getPreviousBookingsOfUser(userID);
        previous_bookings.forEach(previous_booking -> {
            BookingDTO bookingDTO = convertToBookingDTO(previous_booking);
            User user = previous_booking.getUser();
            bookingDTO.setUserDTOS(convertToUserDTO(user));
            Office office = previous_booking.getOffice();
            bookingDTO.setOfficeDTO(convertToOfficeDTO(office));
            bookingDTOList.add(bookingDTO);
        });

        return bookingDTOList;

    }

    @Override
    public List<BookingDTO> getCurrentBookingOfUser(Integer userID) {
        List<BookingDTO> bookingDTOList = new ArrayList<>();
        List<Booking> current_bookings = bookingRepository.getCurrentBookingsOfUser(userID);

        current_bookings.forEach(current_booking -> {

            BookingDTO bookingDTO = convertToBookingDTO(current_booking);
            Office office = current_booking.getOffice();
            bookingDTO.setOfficeDTO(convertToOfficeDTO(office));

            User user = current_booking.getUser();
            bookingDTO.setUserDTOS(convertToUserDTO(user));


            bookingDTOList.add(bookingDTO);
        });

        return bookingDTOList;
    }

    @Override
    public BookingDTO addBooking(BookingDTO bookingDTO) {
        Booking booking = new Booking();

        booking.setBooking_id(bookingDTO.getBooking_id());
        booking.setBooking_date(bookingDTO.getBooking_date());

        User user = userRepository.getUserByID(bookingDTO.getUserID());
        booking.setUser(user);

        Office office = officeRepository.getOfficeByID(bookingDTO.getOfficeID());
        booking.setOffice(office);

        Booking newBooking = bookingRepository.save(booking);

        BookingDTO new_Booking = new BookingDTO();
        new_Booking.setBooking_date(newBooking.getBooking_date());
        new_Booking.setBooking_id(newBooking.getBooking_id());
        new_Booking.setUserID(newBooking.getUser().getId());
        new_Booking.setOfficeID(newBooking.getOffice().getOffice_id());

        return new_Booking;

    }


    BookingDTO convertToBookingDTO(Booking booking) {


        BookingDTO bookingDTO = new BookingDTO();

        bookingDTO.setBooking_id(booking.getBooking_id());
        bookingDTO.setBooking_date(booking.getBooking_date());
        bookingDTO.setUserID(booking.getUser().getId());
        bookingDTO.setOfficeID(booking.getOffice().getOffice_id());

        return bookingDTO;

    }

    UserDTO convertToUserDTO(User user) {


        UserDTO userDTO = new UserDTO();

        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());

        return userDTO;
    }

    OfficeDTO convertToOfficeDTO(Office office) {


        OfficeDTO officeDTO = new OfficeDTO();

        officeDTO.setOffice_id(office.getOffice_id());
        officeDTO.setOffice_name(office.getOffice_name());
        officeDTO.setOffice_address(office.getOffice_address());

        return officeDTO;
    }
}
