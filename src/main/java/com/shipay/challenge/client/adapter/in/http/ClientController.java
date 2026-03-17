package com.shipay.challenge.client.adapter.in.http;

import com.shipay.challenge.client.adapter.in.http.dto.AddressDTO;
import com.shipay.challenge.client.domain.ports.AddressUsecase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/client")
public class ClientController {

    private static final Logger log = LogManager.getLogger(ClientController.class);
    private final AddressUsecase addressUsecase;

    public ClientController(final AddressUsecase addressUsecase){
        this.addressUsecase = addressUsecase;
    }

    @GetMapping("/address/{cep}/{cnpj}")
    public ResponseEntity<AddressDTO> getAddress(
            @PathVariable String cep,
            @PathVariable String cnpj
    ){
        log.info("received request for endpoint /address/{}/{}", cep, cnpj);
        AddressDTO response = Optional.of(this.addressUsecase.getAddress(cep, cnpj))
                .map(e -> {
                    return new AddressDTO(e.zipCode(), e.street(), e.neighborhood(), e.city(), e.state());
                }).orElseThrow();
        log.info("request finished for endpoint /address/{}/{}", cep, cnpj);
        return ResponseEntity.ok(response);
    }
}
