package io.github.byavess.service;

import io.github.byavess.domain.entity.Pedido;
import io.github.byavess.rest.dto.PedidoDTO;

public interface PedidoService {
    Pedido salvar (PedidoDTO dto);
}
