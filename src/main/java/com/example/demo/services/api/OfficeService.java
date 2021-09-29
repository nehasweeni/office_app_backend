package com.example.demo.services.api;

import com.example.demo.dto.BookingDTO;
import com.example.demo.dto.OfficeDTO;
import com.example.demo.dto.PhotosDTO;
import com.example.demo.exception.OfficeNotFoundException;

import javax.swing.event.ListDataEvent;
import java.sql.Date;
import java.util.List;

public interface OfficeService {
    /**
     * Gets list of all offices from office table
     * Using JPA findAll()
     * @return officeDTOList
     */
    List<OfficeDTO> getAllOffices();
    OfficeDTO addOffice(OfficeDTO officeDTO);

    /**
     * Checks for office availability on that specific date
     * Picks one of the available offices and saves the booking
     * @param chosenDate date chosen by User for the booking
     * @param bookingDTO instance
     * @return bookingDTO1
     */
    BookingDTO AddBookingsBasedOnOfficeAvailability(Date chosenDate, BookingDTO bookingDTO) throws Exception;
    List<OfficeDTO> viewOfficesAndPhotos();

}
