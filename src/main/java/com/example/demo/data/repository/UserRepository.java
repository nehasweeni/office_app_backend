package com.example.demo.data.repository;

import com.example.demo.data.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>{

    List<User> findAll();

    @Query(value = "SELECT * FROM user_reg WHERE id = :id ", nativeQuery = true)
    User getUserByID(@Param("id") Integer id);

    @Query(value = "SELECT * FROM user_reg LEFT JOIN booking ON user_reg.id=booking.userID " +
            "LEFT JOIN office ON booking.officeID=office.office_id", nativeQuery = true)
    List<User> getAllData();

}
