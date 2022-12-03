package com.autobots.automanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.autobots.automanager.entity.ServiceEntity;

public interface ServiceRepository extends JpaRepository<ServiceEntity, Long>{}
