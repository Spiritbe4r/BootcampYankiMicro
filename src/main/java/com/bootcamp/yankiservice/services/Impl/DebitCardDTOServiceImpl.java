package com.bootcamp.yankiservice.services.Impl;

import com.bootcamp.yankiservice.documents.dto.DebitCardDTO;
import com.bootcamp.yankiservice.services.DebitCardDTOService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Service
public class DebitCardDTOServiceImpl implements DebitCardDTOService {

  @Autowired
  private WebClient.Builder client;

  @Value("${microservices-urls.api-debitcard}")
  private String apiDebitcard;

  private static final Logger log = LoggerFactory.getLogger(DebitCardDTOServiceImpl.class);

  @Override
  public Mono<DebitCardDTO> getDebitCard(String pan) {

    Map<String, Object> params = new HashMap<String, Object>();
    params.put("pan", pan);
    return client.baseUrl(apiDebitcard)
          .build()
          .get()
          .uri("/findByPan/{pan}", params)
          .accept(MediaType.APPLICATION_JSON)
          .exchangeToMono(clientResponse -> clientResponse.bodyToMono(DebitCardDTO.class))
          .doOnNext(c -> log.info("DebitCard Response: DebitCard={}" + c.getPan()));
  }

}
