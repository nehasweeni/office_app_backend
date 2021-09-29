package com.example.demo.data.repository;

import com.example.demo.data.domain.Photos;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PhotosRepository extends CrudRepository<Photos, Integer> {
    List<Photos> findAll();

}
