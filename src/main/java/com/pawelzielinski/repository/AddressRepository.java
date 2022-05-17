package com.pawelzielinski.repository;

import com.pawelzielinski.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
