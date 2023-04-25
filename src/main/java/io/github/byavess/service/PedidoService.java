package io.github.byavess.service;

import io.github.byavess.domain.entity.Pedido;
import io.github.byavess.domain.enums.StatusPedido;
import io.github.byavess.rest.dto.PedidoDTO;

import java.util.Optional;

public interface PedidoService {
    Pedido salvar (PedidoDTO dto);
    Optional<Pedido> obeterPedidoCompleto(Integer id);
    void atualizaStatus(Integer id, StatusPedido statusPedido);
}
