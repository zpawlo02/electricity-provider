package com.pawelzielinski.repository;

import com.pawelzielinski.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface AddressRepository extends JpaRepository<Address, Integer> {
}
