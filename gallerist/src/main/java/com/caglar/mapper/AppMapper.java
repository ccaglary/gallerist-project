package com.caglar.mapper;

import org.mapstruct.Mapper;

import com.caglar.dto.DtoAccount;
import com.caglar.dto.DtoAccountIU;
import com.caglar.dto.DtoAddress;
import com.caglar.dto.DtoAddressIU;
import com.caglar.dto.DtoCar;
import com.caglar.dto.DtoCarIU;
import com.caglar.dto.DtoCustomer;
import com.caglar.dto.DtoCustomerIU;
import com.caglar.dto.DtoGallerist;
import com.caglar.dto.DtoGalleristCar;
import com.caglar.dto.DtoGalleristCarIU;
import com.caglar.dto.DtoGalleristIU;
import com.caglar.dto.DtoSaledCar;
import com.caglar.dto.DtoSaledCarIU;
import com.caglar.dto.DtoUser;
import com.caglar.model.Account;
import com.caglar.model.Address;
import com.caglar.model.Car;
import com.caglar.model.Customer;
import com.caglar.model.Gallerist;
import com.caglar.model.GalleristCar;
import com.caglar.model.SaledCar;
import com.caglar.model.User;

@Mapper(componentModel = "spring")
public interface AppMapper {

    // Account
    DtoAccount toDto(Account account);
    Account toEntity(DtoAccount dto);
    Account toEntity(DtoAccountIU dto);

    // address
    DtoAddress toDto(Address address);
    Address toEntity(DtoAddress dto);
    Address toEntity(DtoAddressIU dto);

    // car
    DtoCar toDto(Car car);
    Car toEntity(DtoCar dto);
    Car toEntity(DtoCarIU dto);

    // Customer
    DtoCustomer toDto(Customer customer);
    Customer toEntity(DtoCustomer dto);
    Customer toEntity(DtoCustomerIU dto);

    // Galleristt
    DtoGallerist toDto(Gallerist gallerist);
    Gallerist toEntity(DtoGallerist dto);
    Gallerist toEntity(DtoGalleristIU dto);

    // GalleristCar
    DtoGalleristCar toDto(GalleristCar galleristCar);
    GalleristCar toEntity(DtoGalleristCar dto);
    GalleristCar toEntity(DtoGalleristCarIU dto);

    // saledCar
    DtoSaledCar toDto(SaledCar saledCar);
    SaledCar toEntity(DtoSaledCar dto);
    SaledCar toEntity(DtoSaledCarIU dto);

    // User
    DtoUser toDto(User user);
    User toEntity(DtoUser dto);
}
