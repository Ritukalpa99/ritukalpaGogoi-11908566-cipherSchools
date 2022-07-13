package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Guest;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Long>{

}
