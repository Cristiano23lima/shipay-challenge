package com.shipay.challenge.client.adapter.in.http;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.shipay.challenge.client.adapter.in.http.dto.AddressDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.wiremock.spring.ConfigureWireMock;
import org.wiremock.spring.EnableWireMock;
import org.wiremock.spring.InjectWireMock;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableWireMock({
        @ConfigureWireMock(name = "brasilApi", baseUrlProperties = "api.cep.principal"),
        @ConfigureWireMock(name = "viaCep", baseUrlProperties = "api.cep.alternative")
})
class ClientControllerTestIT {
    @Autowired
    private TestRestTemplate restTemplate;

    @InjectWireMock("brasilApi")
    private WireMockServer brasilApiMock;

    @InjectWireMock("viaCep")
    private WireMockServer viaCepMock;

    @Test
    void getAddress_returnAddress_whenSuccessfully(){
        brasilApiMock.stubFor(WireMock.get("/api/cep/v2/62900000")
                .willReturn(WireMock.okJson("""
                        {
                            "cep": "62900000",
                            "state": "CE",
                            "city": "Russas",
                            "neighborhood": "Centro",
                            "street": "Rua Coronel Alexanzito"
                        }
            """))
        );

        brasilApiMock.stubFor(WireMock.get("/api/cnpj/v1/11222333000181")
                .willReturn(WireMock.okJson("""
                        {
                             "uf": "CE",
                             "cep": "62900000",
                             "qsa": [
                                 {
                                     "pais": null,
                                     "nome_socio": "ALEXANDRE DONIZETI RODRIGUES",
                                     "codigo_pais": null,
                                     "faixa_etaria": "Entre 51 a 60 anos",
                                     "cnpj_cpf_do_socio": "***668018**",
                                     "qualificacao_socio": "Diretor",
                                     "codigo_faixa_etaria": 6,
                                     "data_entrada_sociedade": "2019-07-26",
                                     "identificador_de_socio": 2,
                                     "cpf_representante_legal": "***000000**",
                                     "nome_representante_legal": "",
                                     "codigo_qualificacao_socio": 10,
                                     "qualificacao_representante_legal": "Não informada",
                                     "codigo_qualificacao_representante_legal": 0
                                 },
                                 {
                                     "pais": null,
                                     "nome_socio": "MAXIMILIAN STRAND BUENO DE MORAES",
                                     "codigo_pais": null,
                                     "faixa_etaria": "Entre 41 a 50 anos",
                                     "cnpj_cpf_do_socio": "***323938**",
                                     "qualificacao_socio": "Diretor",
                                     "codigo_faixa_etaria": 5,
                                     "data_entrada_sociedade": "2019-07-26",
                                     "identificador_de_socio": 2,
                                     "cpf_representante_legal": "***000000**",
                                     "nome_representante_legal": "",
                                     "codigo_qualificacao_socio": 10,
                                     "qualificacao_representante_legal": "Não informada",
                                     "codigo_qualificacao_representante_legal": 0
                                 },
                                 {
                                     "pais": null,
                                     "nome_socio": "ANDRE EDUARDO DANTAS",
                                     "codigo_pais": null,
                                     "faixa_etaria": "Entre 51 a 60 anos",
                                     "cnpj_cpf_do_socio": "***748808**",
                                     "qualificacao_socio": "Diretor",
                                     "codigo_faixa_etaria": 6,
                                     "data_entrada_sociedade": "2021-06-25",
                                     "identificador_de_socio": 2,
                                     "cpf_representante_legal": "***000000**",
                                     "nome_representante_legal": "",
                                     "codigo_qualificacao_socio": 10,
                                     "qualificacao_representante_legal": "Não informada",
                                     "codigo_qualificacao_representante_legal": 0
                                 }
                             ],
                             "cnpj": "00924432000199",
                             "pais": null,
                             "email": null,
                             "porte": "DEMAIS",
                             "bairro": "BAIRRO DO MOINHO",
                             "numero": "7001",
                             "ddd_fax": "",
                             "municipio": "Russas",
                             "logradouro": "Rua Coronel Alexanzito",
                             "cnae_fiscal": 9321200,
                             "codigo_pais": null,
                             "complemento": "FAZ.SERRA AZUL",
                             "codigo_porte": 5,
                             "razao_social": "HOPI HARI S.A. EM RECUPERACAO JUDICIAL",
                             "nome_fantasia": "HOPI HARI",
                             "capital_social": 285105000,
                             "ddd_telefone_1": "1161731953",
                             "ddd_telefone_2": "1194690565",
                             "opcao_pelo_mei": null,
                             "descricao_porte": "",
                             "codigo_municipio": 7237,
                             "cnaes_secundarios": [
                                 {
                                     "codigo": 7990200,
                                     "descricao": "Serviços de reservas e outros serviços de turismo não especificados anteriormente"
                                 }
                             ],
                             "natureza_juridica": "Sociedade Anônima Aberta",
                             "situacao_especial": "",
                             "opcao_pelo_simples": null,
                             "situacao_cadastral": 2,
                             "data_opcao_pelo_mei": null,
                             "data_exclusao_do_mei": null,
                             "cnae_fiscal_descricao": "Parques de diversão e parques temáticos",
                             "codigo_municipio_ibge": 3556701,
                             "data_inicio_atividade": "1995-11-16",
                             "data_situacao_especial": null,
                             "data_opcao_pelo_simples": null,
                             "data_situacao_cadastral": "2022-01-20",
                             "nome_cidade_no_exterior": "",
                             "codigo_natureza_juridica": 2046,
                             "data_exclusao_do_simples": null,
                             "motivo_situacao_cadastral": 0,
                             "ente_federativo_responsavel": "",
                             "identificador_matriz_filial": 1,
                             "qualificacao_do_responsavel": 10,
                             "descricao_situacao_cadastral": "ATIVA",
                             "descricao_tipo_de_logradouro": "ESTRADA",
                             "descricao_motivo_situacao_cadastral": "SEM MOTIVO",
                             "descricao_identificador_matriz_filial": "MATRIZ"
                         }
            """))
        );

        ResponseEntity<AddressDTO> response = restTemplate.getForEntity("/api/v1/client/address/62900000/11222333000181", AddressDTO.class);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals("CE", response.getBody().state());
        Assertions.assertEquals("Rua Coronel Alexanzito", response.getBody().street());
        Assertions.assertEquals("Russas", response.getBody().city());
    }

    @Test
    void getAddress_return404_whenAddressNotMatch(){
        brasilApiMock.stubFor(WireMock.get("/api/cep/v2/62900000")
                .willReturn(WireMock.okJson("""
                        {
                            "cep": "62900000",
                            "state": "CE",
                            "city": "Russas",
                            "neighborhood": "Centro",
                            "street": "Rua Coronel Alexanzito"
                        }
            """))
        );

        brasilApiMock.stubFor(WireMock.get("/api/cnpj/v1/11222333000181")
                .willReturn(WireMock.okJson("""
                        {
                             "uf": "SP",
                             "cep": "62900000",
                             "qsa": [
                                 {
                                     "pais": null,
                                     "nome_socio": "ALEXANDRE DONIZETI RODRIGUES",
                                     "codigo_pais": null,
                                     "faixa_etaria": "Entre 51 a 60 anos",
                                     "cnpj_cpf_do_socio": "***668018**",
                                     "qualificacao_socio": "Diretor",
                                     "codigo_faixa_etaria": 6,
                                     "data_entrada_sociedade": "2019-07-26",
                                     "identificador_de_socio": 2,
                                     "cpf_representante_legal": "***000000**",
                                     "nome_representante_legal": "",
                                     "codigo_qualificacao_socio": 10,
                                     "qualificacao_representante_legal": "Não informada",
                                     "codigo_qualificacao_representante_legal": 0
                                 },
                                 {
                                     "pais": null,
                                     "nome_socio": "MAXIMILIAN STRAND BUENO DE MORAES",
                                     "codigo_pais": null,
                                     "faixa_etaria": "Entre 41 a 50 anos",
                                     "cnpj_cpf_do_socio": "***323938**",
                                     "qualificacao_socio": "Diretor",
                                     "codigo_faixa_etaria": 5,
                                     "data_entrada_sociedade": "2019-07-26",
                                     "identificador_de_socio": 2,
                                     "cpf_representante_legal": "***000000**",
                                     "nome_representante_legal": "",
                                     "codigo_qualificacao_socio": 10,
                                     "qualificacao_representante_legal": "Não informada",
                                     "codigo_qualificacao_representante_legal": 0
                                 },
                                 {
                                     "pais": null,
                                     "nome_socio": "ANDRE EDUARDO DANTAS",
                                     "codigo_pais": null,
                                     "faixa_etaria": "Entre 51 a 60 anos",
                                     "cnpj_cpf_do_socio": "***748808**",
                                     "qualificacao_socio": "Diretor",
                                     "codigo_faixa_etaria": 6,
                                     "data_entrada_sociedade": "2021-06-25",
                                     "identificador_de_socio": 2,
                                     "cpf_representante_legal": "***000000**",
                                     "nome_representante_legal": "",
                                     "codigo_qualificacao_socio": 10,
                                     "qualificacao_representante_legal": "Não informada",
                                     "codigo_qualificacao_representante_legal": 0
                                 }
                             ],
                             "cnpj": "00924432000199",
                             "pais": null,
                             "email": null,
                             "porte": "DEMAIS",
                             "bairro": "BAIRRO DO MOINHO",
                             "numero": "7001",
                             "ddd_fax": "",
                             "municipio": "São Paulo",
                             "logradouro": "Rua Coronel Alexanzito",
                             "cnae_fiscal": 9321200,
                             "codigo_pais": null,
                             "complemento": "FAZ.SERRA AZUL",
                             "codigo_porte": 5,
                             "razao_social": "HOPI HARI S.A. EM RECUPERACAO JUDICIAL",
                             "nome_fantasia": "HOPI HARI",
                             "capital_social": 285105000,
                             "ddd_telefone_1": "1161731953",
                             "ddd_telefone_2": "1194690565",
                             "opcao_pelo_mei": null,
                             "descricao_porte": "",
                             "codigo_municipio": 7237,
                             "cnaes_secundarios": [
                                 {
                                     "codigo": 7990200,
                                     "descricao": "Serviços de reservas e outros serviços de turismo não especificados anteriormente"
                                 }
                             ],
                             "natureza_juridica": "Sociedade Anônima Aberta",
                             "situacao_especial": "",
                             "opcao_pelo_simples": null,
                             "situacao_cadastral": 2,
                             "data_opcao_pelo_mei": null,
                             "data_exclusao_do_mei": null,
                             "cnae_fiscal_descricao": "Parques de diversão e parques temáticos",
                             "codigo_municipio_ibge": 3556701,
                             "data_inicio_atividade": "1995-11-16",
                             "data_situacao_especial": null,
                             "data_opcao_pelo_simples": null,
                             "data_situacao_cadastral": "2022-01-20",
                             "nome_cidade_no_exterior": "",
                             "codigo_natureza_juridica": 2046,
                             "data_exclusao_do_simples": null,
                             "motivo_situacao_cadastral": 0,
                             "ente_federativo_responsavel": "",
                             "identificador_matriz_filial": 1,
                             "qualificacao_do_responsavel": 10,
                             "descricao_situacao_cadastral": "ATIVA",
                             "descricao_tipo_de_logradouro": "ESTRADA",
                             "descricao_motivo_situacao_cadastral": "SEM MOTIVO",
                             "descricao_identificador_matriz_filial": "MATRIZ"
                         }
            """))
        );

        ResponseEntity<AddressDTO> response = restTemplate.getForEntity("/api/v1/client/address/62900000/11222333000181", AddressDTO.class);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void getAddress_returnAddress_whenBrasilApiFailsAndViaCEPIsUP(){
        brasilApiMock.stubFor(WireMock.get("/api/cep/v2/62900000")
                .willReturn(WireMock.serverError()));

        viaCepMock.stubFor(WireMock.get("/ws/62900000/json")
                .willReturn(WireMock.okJson("""
                {
                    "cep": "62900000",
                    "logradouro": "Rua Coronel Alexanzito",
                    "bairro": "Centro",
                    "localidade": "Russas",
                    "uf": "CE"
                }
            """)));

        brasilApiMock.stubFor(WireMock.get("/api/cnpj/v1/11222333000181")
                .willReturn(WireMock.okJson("""
                        {
                             "uf": "CE",
                             "cep": "62900000",
                             "qsa": [
                                 {
                                     "pais": null,
                                     "nome_socio": "ALEXANDRE DONIZETI RODRIGUES",
                                     "codigo_pais": null,
                                     "faixa_etaria": "Entre 51 a 60 anos",
                                     "cnpj_cpf_do_socio": "***668018**",
                                     "qualificacao_socio": "Diretor",
                                     "codigo_faixa_etaria": 6,
                                     "data_entrada_sociedade": "2019-07-26",
                                     "identificador_de_socio": 2,
                                     "cpf_representante_legal": "***000000**",
                                     "nome_representante_legal": "",
                                     "codigo_qualificacao_socio": 10,
                                     "qualificacao_representante_legal": "Não informada",
                                     "codigo_qualificacao_representante_legal": 0
                                 },
                                 {
                                     "pais": null,
                                     "nome_socio": "MAXIMILIAN STRAND BUENO DE MORAES",
                                     "codigo_pais": null,
                                     "faixa_etaria": "Entre 41 a 50 anos",
                                     "cnpj_cpf_do_socio": "***323938**",
                                     "qualificacao_socio": "Diretor",
                                     "codigo_faixa_etaria": 5,
                                     "data_entrada_sociedade": "2019-07-26",
                                     "identificador_de_socio": 2,
                                     "cpf_representante_legal": "***000000**",
                                     "nome_representante_legal": "",
                                     "codigo_qualificacao_socio": 10,
                                     "qualificacao_representante_legal": "Não informada",
                                     "codigo_qualificacao_representante_legal": 0
                                 },
                                 {
                                     "pais": null,
                                     "nome_socio": "ANDRE EDUARDO DANTAS",
                                     "codigo_pais": null,
                                     "faixa_etaria": "Entre 51 a 60 anos",
                                     "cnpj_cpf_do_socio": "***748808**",
                                     "qualificacao_socio": "Diretor",
                                     "codigo_faixa_etaria": 6,
                                     "data_entrada_sociedade": "2021-06-25",
                                     "identificador_de_socio": 2,
                                     "cpf_representante_legal": "***000000**",
                                     "nome_representante_legal": "",
                                     "codigo_qualificacao_socio": 10,
                                     "qualificacao_representante_legal": "Não informada",
                                     "codigo_qualificacao_representante_legal": 0
                                 }
                             ],
                             "cnpj": "00924432000199",
                             "pais": null,
                             "email": null,
                             "porte": "DEMAIS",
                             "bairro": "BAIRRO DO MOINHO",
                             "numero": "7001",
                             "ddd_fax": "",
                             "municipio": "Russas",
                             "logradouro": "Rua Coronel Alexanzito",
                             "cnae_fiscal": 9321200,
                             "codigo_pais": null,
                             "complemento": "FAZ.SERRA AZUL",
                             "codigo_porte": 5,
                             "razao_social": "HOPI HARI S.A. EM RECUPERACAO JUDICIAL",
                             "nome_fantasia": "HOPI HARI",
                             "capital_social": 285105000,
                             "ddd_telefone_1": "1161731953",
                             "ddd_telefone_2": "1194690565",
                             "opcao_pelo_mei": null,
                             "descricao_porte": "",
                             "codigo_municipio": 7237,
                             "cnaes_secundarios": [
                                 {
                                     "codigo": 7990200,
                                     "descricao": "Serviços de reservas e outros serviços de turismo não especificados anteriormente"
                                 }
                             ],
                             "natureza_juridica": "Sociedade Anônima Aberta",
                             "situacao_especial": "",
                             "opcao_pelo_simples": null,
                             "situacao_cadastral": 2,
                             "data_opcao_pelo_mei": null,
                             "data_exclusao_do_mei": null,
                             "cnae_fiscal_descricao": "Parques de diversão e parques temáticos",
                             "codigo_municipio_ibge": 3556701,
                             "data_inicio_atividade": "1995-11-16",
                             "data_situacao_especial": null,
                             "data_opcao_pelo_simples": null,
                             "data_situacao_cadastral": "2022-01-20",
                             "nome_cidade_no_exterior": "",
                             "codigo_natureza_juridica": 2046,
                             "data_exclusao_do_simples": null,
                             "motivo_situacao_cadastral": 0,
                             "ente_federativo_responsavel": "",
                             "identificador_matriz_filial": 1,
                             "qualificacao_do_responsavel": 10,
                             "descricao_situacao_cadastral": "ATIVA",
                             "descricao_tipo_de_logradouro": "ESTRADA",
                             "descricao_motivo_situacao_cadastral": "SEM MOTIVO",
                             "descricao_identificador_matriz_filial": "MATRIZ"
                         }
            """))
        );

        ResponseEntity<AddressDTO> response = restTemplate.getForEntity("/api/v1/client/address/62900000/11222333000181", AddressDTO.class);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals("CE", response.getBody().state());
        Assertions.assertEquals("Rua Coronel Alexanzito", response.getBody().street());
        Assertions.assertEquals("Russas", response.getBody().city());
    }

    @Test
    void getAddress_returnBadRequest_whenCEPIsInvalid(){
        ResponseEntity<AddressDTO> response = restTemplate.getForEntity("/api/v1/client/address/62900A00/11222333000181", AddressDTO.class);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void getAddress_returnBadRequest_whenCNPJIsInvalid(){
        ResponseEntity<AddressDTO> response = restTemplate.getForEntity("/api/v1/client/address/62900000/1A222333000181", AddressDTO.class);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}
