package com.acccenture.banktrialprojectbed.repository;

import com.acccenture.banktrialprojectbed.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepo extends JpaRepository<Client, Integer> {
}
