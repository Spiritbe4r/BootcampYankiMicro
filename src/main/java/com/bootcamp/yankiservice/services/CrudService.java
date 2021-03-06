package com.bootcamp.yankiservice.services;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CrudService<T, ID> {

  Mono<T> create(T o);

  Mono<T> update(T o);

  Mono<Void> delete(T o);

  Flux<T> findAll();

  Mono<T> findById(ID id);

}
