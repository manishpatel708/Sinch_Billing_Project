package com.sinch.challenge;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sinch.challenge.model.SmsPlan;

@Repository
public interface SmsPlanRepository extends JpaRepository<SmsPlan, Long> {

}
