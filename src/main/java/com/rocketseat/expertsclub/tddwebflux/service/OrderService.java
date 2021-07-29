package com.rocketseat.expertsclub.tddwebflux.service;


import com.rocketseat.expertsclub.tddwebflux.domain.Order;
import com.rocketseat.expertsclub.tddwebflux.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {

    @Autowired
    private final OrderRepository orderRepository;

    public Flux<Order> findAll() {
        return orderRepository.findAll();

    }

    public Mono<Order> findById(String id) {
        return orderRepository.findById(id)
                .switchIfEmpty(OrderNotFoundException());
    }

    public Mono<Order> save(Order order) {
        return orderRepository.save(order);
    }

    public Mono<Void> update(Order order) {
        return findById(order.getId())
                .flatMap(orderRepository::save)
                .then();
    }

    public Mono<Void> delete(String id) {
        return findById(id)
                .flatMap(orderRepository::delete);
    }


    public <T> Mono<T> OrderNotFoundException() {
        return Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado"));
    }


}
