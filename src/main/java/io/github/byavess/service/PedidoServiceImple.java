package io.github.byavess.service;

import io.github.byavess.domain.repository.Pedidos;
import org.springframework.stereotype.Service;

@Service
class PedidoServiceImple implements PedidioService {

    private Pedidos repository;

    public PedidoServiceImple(Pedidos repository){
        this.repository = repository;
    }

}
