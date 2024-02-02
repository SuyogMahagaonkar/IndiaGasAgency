package com.psl.IGA.demo.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.psl.IGA.demo.project.model.Booking;
@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

}
