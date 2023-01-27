package com.acccenture.banktrialprojectbed.repository;

import com.acccenture.banktrialprojectbed.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepo extends JpaRepository<Client, Integer> {
}
