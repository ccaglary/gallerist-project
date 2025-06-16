package com.caglar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.caglar.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
