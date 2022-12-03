package com.autobots.automanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.autobots.automanager.entity.BarCodeCredential;


public interface BarCodeCredentialRepository extends JpaRepository<BarCodeCredential, Long> {
}
