package com.rocketseat.expertsclub.tddwebflux.repository;


import com.rocketseat.expertsclub.tddwebflux.domain.Order;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends ReactiveMongoRepository<Order, String> {

}
