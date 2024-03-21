package com.clinic.services.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clinic.services.entity.ClinicServices;

public interface ServiceDao extends JpaRepository<ClinicServices, Integer> {

}
