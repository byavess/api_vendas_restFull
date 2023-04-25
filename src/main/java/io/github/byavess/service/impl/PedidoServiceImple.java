package io.github.byavess.service.impl;

import io.github.byavess.domain.entity.Cliente;
import io.github.byavess.domain.entity.ItemPedido;
import io.github.byavess.domain.entity.Pedido;
import io.github.byavess.domain.entity.Produto;
import io.github.byavess.domain.enums.StatusPedido;
import io.github.byavess.domain.repository.Clientes;
import io.github.byavess.domain.repository.ItemsPedidos;
import io.github.byavess.domain.repository.Pedidos;
import io.github.byavess.domain.repository.Produtos;
import io.github.byavess.exeption.PedidoNaoEncontradoException;
import io.github.byavess.exeption.RegraNegocioException;
import io.github.byavess.rest.dto.ItemPedidoDTO;
import io.github.byavess.rest.dto.PedidoDTO;
import io.github.byavess.service.PedidoService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
// gera construtor com todos os argumentos obrigatorios e acrescenta o final nos paramentos da entidade
public class PedidoServiceImple implements PedidoService {

    private final Pedidos pedidosRepository;
    private final Clientes clientesRepository;
    private final Produtos produtosRepository;
    private final ItemsPedidos itemsPedidosRepository;

    @Override
    @Transactional//ou salva ou roollback //tudo ou nada
    public Pedido salvar(PedidoDTO dto) {
        Integer idCliente = dto.getCliente();
        Cliente cliente = clientesRepository.findById(idCliente).orElseThrow(() -> new RegraNegocioException("codigo de Cliente inválido"
        ));
        Pedido pedido = new Pedido();
        pedido.setTotal(dto.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);
        pedido.setStatus(StatusPedido.REALIZADO);


        List<ItemPedido> itemsPedido = converterItems(pedido, dto.getItems());
        pedidosRepository.save(pedido);
        itemsPedidosRepository.saveAll(itemsPedido);
        pedido.setItens(itemsPedido);

        return pedido;
    }

    @Override
    public Optional<Pedido> obeterPedidoCompleto(Integer id) {
        return pedidosRepository.findByIdFetchItens(id);
    }

    @Override
    @Transactional
    public void atualizaStatus(Integer id, StatusPedido statusPedido) {
        pedidosRepository
                .findById(id)
                .map ( pedido -> {
                    pedido.setStatus(statusPedido);
                    return pedidosRepository.save(pedido);
                }).orElseThrow( () -> new PedidoNaoEncontradoException() );
    }

    private List<ItemPedido> converterItems(Pedido pedido, List<ItemPedidoDTO> items) {
        if (items.isEmpty()) {
            throw new RegraNegocioException("Não e possivel realizar um pedido sem intems");
        }
        return items
                .stream()
                .map( dto -> {
                    Integer idProduto = dto.getProduto();
                    Produto produto = produtosRepository
                            .findById(idProduto)
                            .orElseThrow(
                                    () -> new RegraNegocioException(
                                            "Código de produto inválido: "+ idProduto
                                    ));

                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setQuantidade(dto.getQuantidade());
                    itemPedido.setPedido(pedido);
                    itemPedido.setProduto(produto);
                    return itemPedido;
                }).collect(Collectors.toList());
    }
}
