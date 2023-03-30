package io.github.byavess.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO {//mapeia o objeto cfom propiedades simpes e recebe via requisisção
    private Integer cliente;
    private BigDecimal total;
    private List<ItemPedidoDTO> items;

}
