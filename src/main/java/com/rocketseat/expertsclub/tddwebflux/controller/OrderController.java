package com.rocketseat.expertsclub.tddwebflux.controller;


import com.rocketseat.expertsclub.tddwebflux.domain.Order;
import com.rocketseat.expertsclub.tddwebflux.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
@Slf4j
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Order> createOrder(@Valid @RequestBody Order order) {
        log.info("Criando uma nova ordem com as informações {}", order);
        return orderService.save(order);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Flux<Order> getAllOrders() {
        log.info("Listando todas as ordens ");
        return orderService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Order> findById(@PathVariable String id) {
        log.info("Buscando a ordem com o id {}", id);
        return orderService.findById(id);
    }


    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> update(@PathVariable String id, @Valid @RequestBody Order order) {
        log.info("Atualizando a ordem com o id {} as informações {}", id, order);
        return orderService.update(order.withId(id));
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> delete(@PathVariable String id) {
        log.info("Deletando a ordem com o id {}", id);
        return orderService.delete(id);
    }
}
