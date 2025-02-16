package com.application.mock.repository;

import com.application.mock.model.UserContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserContractRepository extends JpaRepository<UserContact, Long> {
}