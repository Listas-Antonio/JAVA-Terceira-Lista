package com.autobots.automanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.autobots.automanager.entity.Merchandise;

public interface MerchandiseRepository extends JpaRepository<Merchandise, Long>{}
