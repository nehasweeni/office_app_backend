package com.example.demo.data.repository;

import com.example.demo.data.domain.Office;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface OfficeRepository extends CrudRepository<Office, Integer> {
    List<Office> findAll();

    @Query(value = "SELECT * FROM office WHERE office_id = :office_id", nativeQuery = true)
    Office getOfficeByID(@Param("office_id") Integer office_id);


    @Query(value = "SELECT o.* FROM office o LEFT JOIN booking b ON o.office_id = b.officeID and b.booking_date = :chosenDate where b.officeID IS NULL ", nativeQuery = true)
    List<Office> getAvailableOffices(@Param("chosenDate")Date chosenDate);

    @Query(value = "SELECT b.* FROM office b LEFT JOIN photos p on b.office_id = p.office_id", nativeQuery = true)
    List<Office> viewOfficesAndPhotos();



}
