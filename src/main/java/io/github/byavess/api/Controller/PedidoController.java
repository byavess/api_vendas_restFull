package io.github.byavess.api.Controller;



import io.github.byavess.domain.entity.Pedido;
import io.github.byavess.domain.repository.Pedidos;

import io.github.byavess.service.PedidioService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {
    private PedidioService pedidioService;

    public PedidoController(PedidioService service){
        this.pedidioService = service;
    }









}
