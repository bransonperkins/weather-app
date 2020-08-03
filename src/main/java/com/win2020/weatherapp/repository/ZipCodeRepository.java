package com.win2020.weatherapp.repository;

import com.win2020.weatherapp.model.ZipCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ZipCodeRepository extends JpaRepository<ZipCode, Long> {
    public ZipCode findByZipCode(String zipCode);
}
