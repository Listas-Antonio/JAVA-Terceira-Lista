package com.autobots.automanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.autobots.automanager.entity.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> { }