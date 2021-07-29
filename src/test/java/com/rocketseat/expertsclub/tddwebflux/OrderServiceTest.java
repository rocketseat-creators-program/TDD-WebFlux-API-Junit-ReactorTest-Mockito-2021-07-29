package com.rocketseat.expertsclub.tddwebflux;


import com.rocketseat.expertsclub.tddwebflux.domain.Order;
import com.rocketseat.expertsclub.tddwebflux.repository.OrderRepository;
import com.rocketseat.expertsclub.tddwebflux.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(SpringExtension.class)
public class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;


    private final Order order = OrderBuilder.order();

    @BeforeEach
    public void setUp() {

        BDDMockito.when(orderRepository.save(OrderBuilder.order()))
                .thenReturn(Mono.just(order));

        BDDMockito.when(orderRepository.findAll())
                .thenReturn(Flux.just(order));

        BDDMockito.when(orderRepository.findById(ArgumentMatchers.anyString()))
                .thenReturn(Mono.just(order));


        BDDMockito.when(orderRepository.delete(ArgumentMatchers.any(Order.class)))
                .thenReturn(Mono.empty());


    }

    @Test
    @DisplayName("Deve criar uma ordem")
    public void shouldCreateAnOrderSuccessfully() {
        Order orderToBeSaved = OrderBuilder.order();

        StepVerifier.create(orderService.save(orderToBeSaved))
                .expectSubscription()
                .expectNext(order)
                .verifyComplete();
    }

    @Test
    @DisplayName("Deve listar todas as ordens")
    public void shouldReturnAllOrdersSuccessfully() {
        StepVerifier.create(orderService.findAll())
                .expectSubscription()
                .expectNext(order)
                .verifyComplete();
    }

    @Test
    @DisplayName("Deve buscar e retornar uma ordem pelo id ")
    public void shouldFindByIdAndReturnAnOrderSuccessfully() {
        StepVerifier.create(orderService.findById("ec-1"))
                .expectSubscription()
                .expectNext(order)
                .verifyComplete();
    }

    @Test
    @DisplayName("Deve retornar exception quando não encontrar uma ordem")
    public void ShouldReturnExceptionWhenOrderNotFound() {
        BDDMockito.when(orderRepository.findById(ArgumentMatchers.anyString()))
                .thenReturn(Mono.empty());

        StepVerifier.create(orderService.findById("ordem invalda-123"))
                .expectSubscription()
                .expectError(ResponseStatusException.class)
                .verify();
    }

    @Test
    @DisplayName("Deve deletar uma ordem com sucesso")
    public void shouldDeleteAnOrderSuccessfully() {
        StepVerifier.create(orderService.delete("ec-1"))
                .expectSubscription()
                .verifyComplete();
    }

    @Test
    @DisplayName("Deve retornar exception quando tenta deletar ordem e não encontra")
    public void shouldReturnExceptionWhenTryDeleteAndNotFound() {
        BDDMockito.when(orderRepository.findById(ArgumentMatchers.anyString()))
                .thenReturn(Mono.empty());

        StepVerifier.create(orderService.delete("ordem nao criada-123"))
                .expectSubscription()
                .expectError(ResponseStatusException.class)
                .verify();
    }

    @Test
    @DisplayName("Deve atualizar uma ordem")
    public void shouldUpdateOrderSuccessfully() {

        Order orderUpdated = OrderBuilder.order();

        StepVerifier.create(orderService.update(orderUpdated))
                .expectSubscription()
                .verifyComplete();
    }

    @Test
    @DisplayName("Deve retornar exception quando tenta excluir ordem e ela não existe")
    public void shouldReturnExceptionWhenTryReturnAndNotFound() {
        BDDMockito.when(orderRepository.findById(ArgumentMatchers.anyString()))
                .thenReturn(Mono.empty());

        StepVerifier.create(orderService.update(OrderBuilder.order()))
                .expectSubscription()
                .expectError(ResponseStatusException.class)
                .verify();
    }


}


