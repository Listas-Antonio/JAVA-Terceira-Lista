package com.autobots.automanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.autobots.automanager.entity.Credential;

public interface CredentialRepository extends JpaRepository<Credential, Long>{}
