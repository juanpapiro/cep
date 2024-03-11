package com.api.cep.service.impl;

import com.api.cep.dto.AddressDto;
import com.api.cep.exception.CepNotFoundException;
import com.api.cep.service.CepService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@Log4j2
@Service
public class CepServiceImpl implements CepService {

    @Autowired
    @Qualifier("withssl")
    private RestTemplate restTemplate;

    @Value("${URL_FIND_ADDRESS_BY_CEP}")
    private String urlFindAddressByCep;

    @Override
    public AddressDto findAddress(String cep) {
        URI uri = UriComponentsBuilder.fromHttpUrl(urlFindAddressByCep).build(cep);
        log.info("Buscando endereço por cep: {}", uri.toString());
        try{
            AddressDto address = Optional.ofNullable(restTemplate
                    .getForEntity(uri.toString(), AddressDto.class))
                    .map(ResponseEntity::getBody)
                    .orElseThrow(() -> new RuntimeException("Falha ao consultar endereço por cep."));
            log.info("***** Endereço localizado com sucesso: {}", address);
            return address;
        } catch(HttpClientErrorException.NotFound e) {
            throw new CepNotFoundException("Cep não localizado!");
        }
    }
}
