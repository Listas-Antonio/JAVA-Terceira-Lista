package com.autobots.automanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.autobots.automanager.entity.UserPasswordCredential;

public interface UserPasswordCredentialRepository extends JpaRepository<UserPasswordCredential, Long> {
}
