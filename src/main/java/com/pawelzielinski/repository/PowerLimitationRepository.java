package com.pawelzielinski.repository;

import com.pawelzielinski.model.Customer;
import com.pawelzielinski.model.PowerLimitation;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PowerLimitationRepository {

    PowerLimitation save(PowerLimitation powerLimitation);
    List<PowerLimitation> findAll();
    PowerLimitation getByZipCode(String zipCode);
    PowerLimitation getById(Integer id);

}
