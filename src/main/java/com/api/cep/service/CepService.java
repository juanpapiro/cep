package com.api.cep.service;


import com.api.cep.dto.AddressDto;

public interface CepService {

    AddressDto findAddress(String cep);

}
