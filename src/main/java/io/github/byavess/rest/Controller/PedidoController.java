package io.github.byavess.rest.Controller;



import io.github.byavess.domain.entity.Pedido;
import io.github.byavess.rest.dto.PedidoDTO;
import io.github.byavess.service.PedidoService;

import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Integer save( @RequestBody PedidoDTO dto ){
        Pedido pedido = service.salvar(dto);
        return pedido.getId();
    }

}
