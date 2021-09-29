package com.example.demo.services.impl;

import com.example.demo.data.domain.Booking;
import com.example.demo.data.domain.Office;
import com.example.demo.data.domain.Photos;
import com.example.demo.data.domain.User;
import com.example.demo.data.repository.BookingRepository;
import com.example.demo.data.repository.OfficeRepository;
import com.example.demo.data.repository.PhotosRepository;
import com.example.demo.data.repository.UserRepository;
import com.example.demo.dto.BookingDTO;
import com.example.demo.dto.OfficeDTO;
import com.example.demo.dto.PhotosDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.exception.OfficeNotFoundException;
import com.example.demo.services.api.OfficeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
@Component
@RequiredArgsConstructor
public class OfficeServiceImpl implements OfficeService {
    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final OfficeRepository officeRepository;
    private final PhotosRepository photosRepository;

    @Override
    public List<OfficeDTO> getAllOffices() {
        List<OfficeDTO> officeDTOList = new ArrayList<>();
        List<Office> offices = officeRepository.findAll();
        offices.forEach(office -> {
            OfficeDTO officeDTO = convertToOfficeDTO(office);
            officeDTOList.add(officeDTO);
        });

        return officeDTOList;

    }

    @Override
    public OfficeDTO addOffice(OfficeDTO officeDTO) {
        Office office = new Office();
        List<Photos> photos = new ArrayList<>();

        office.setOffice_id(officeDTO.getOffice_id());
        office.setOffice_name(officeDTO.getOffice_name());
        office.setOffice_address(officeDTO.getOffice_address());


        Office saved = officeRepository.save(office);

        OfficeDTO added_office = new OfficeDTO();
        added_office.setOffice_id(saved.getOffice_id());

        officeDTO.getPhotosDTOS().forEach(photosDTO -> {
            Photos photos1 = new Photos();
            photos1.setPhotos_id(photosDTO.getPhotos_id());
            photos1.setPhoto(photosDTO.getPhoto());
            photos1.setContent_type(photosDTO.getContent_type());
            Office office1 = officeRepository.getOfficeByID(saved.getOffice_id());
            photos1.setOffice2(office1);
            photos.add(photos1);
        });


        List<Photos> saved_photos = (List<Photos>) photosRepository.saveAll(photos);
        PhotosDTO added_photos = new PhotosDTO();
        saved_photos.forEach(added_photo -> {
            added_photos.setContent_type(added_photo.getContent_type());
            added_photos.setPhotos_id(added_photo.getPhotos_id());
            added_photos.setOffice_id(added_photo.getOffice2().getOffice_id());
//            added_office.setPhotosDTOS(added_photo.getPhoto());
        });


        System.out.println(added_photos);
        return added_office;

    }

    @Override
    public BookingDTO AddBookingsBasedOnOfficeAvailability(Date chosenDate, BookingDTO bookingDTO) throws Exception {
        List<OfficeDTO> officeDTOList = new ArrayList<>();

        List<Office> availableOffices = officeRepository.getAvailableOffices(chosenDate);
        availableOffices.forEach(office -> {
            OfficeDTO officeDTO = convertToOfficeDTO(office);
            officeDTOList.add(officeDTO);
        });

        System.out.println(officeDTOList);
        OfficeDTO chosenOffice = officeDTOList.stream().findAny().orElse(null);
        if (chosenOffice != null) {
            System.out.println("Name:" + chosenOffice.getOffice_name());
            Booking booking = new Booking();

            booking.setBooking_id(bookingDTO.getBooking_id());
            booking.setBooking_date(chosenDate);

            User user = userRepository.getUserByID(bookingDTO.getUserID());
            booking.setUser(user);

            Office office = officeRepository.getOfficeByID(chosenOffice.getOffice_id());
            booking.setOffice(office);

            Booking booking1 = bookingRepository.save(booking);
            BookingDTO bookingDTO1 = new BookingDTO();
            bookingDTO1.setBooking_id(booking1.getBooking_id());
            bookingDTO1.setBooking_date(booking1.getBooking_date());
            bookingDTO1.setUserID(booking1.getUser().getId());
            bookingDTO1.setOfficeID(booking1.getOffice().getOffice_id());

            return bookingDTO1;

        }else {
            throw  new OfficeNotFoundException("No office available");
        }

    }

    @Override
    public List<OfficeDTO> viewOfficesAndPhotos() {
        List<OfficeDTO> officeDTOList = new ArrayList<>();
        List<Office> viewOfficesAndPhotosList = officeRepository.viewOfficesAndPhotos();
        viewOfficesAndPhotosList.forEach(officeAndPhoto -> {
            OfficeDTO officeDTO = convertToOfficeDTO(officeAndPhoto);

            List<Photos> photosList = officeAndPhoto.getPhotos();
            List<PhotosDTO> photosDTOList = new ArrayList<>();
            photosList.forEach(photos -> {
                PhotosDTO photosDTO = convertToPhotosDTO(photos);
                photosDTOList.add(photosDTO);
//                officeDTO.setPhotosDTOS(convertToPhotosDTO(photos));
//                photosList.add(photos);
            });
            officeDTO.setPhotosDTOS(photosDTOList);
            officeDTOList.add(officeDTO);


        });

        return  officeDTOList;
    }


    OfficeDTO convertToOfficeDTO(Office office) {


        OfficeDTO officeDTO = new OfficeDTO();

        officeDTO.setOffice_id(office.getOffice_id());
        officeDTO.setOffice_name(office.getOffice_name());
        officeDTO.setOffice_address(office.getOffice_address());

        return officeDTO;
    }

    PhotosDTO convertToPhotosDTO(Photos photos) {


        PhotosDTO photosDTO = new PhotosDTO();

        photosDTO.setPhotos_id(photos.getPhotos_id());
        photosDTO.setOffice_id(photos.getOffice2().getOffice_id());
        photosDTO.setPhoto(photos.getPhoto());
        photosDTO.setContent_type(photos.getContent_type());

        return photosDTO;
    }


}
