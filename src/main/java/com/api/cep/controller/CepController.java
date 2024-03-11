package com.api.cep.controller;

import com.api.cep.dto.AddressDto;
import com.api.cep.service.CepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/address")
public class CepController {

    @Autowired
    private CepService cepService;

    @GetMapping("/by-cep/{cep}")
    public ResponseEntity<AddressDto> findAddress(@PathVariable String cep) {
        return ResponseEntity.ok(cepService.findAddress(cep));
    }



}
