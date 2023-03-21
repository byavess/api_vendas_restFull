package io.github.byavess.api.Controller;

import io.github.byavess.domain.entity.Cliente;
import io.github.byavess.domain.repository.Clientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller// passa a ser gerenciado pelo spring, pode receber requisições
public class ClienteController {


    private Clientes clientes;

    public ClienteController(Clientes clientes) {
        this.clientes = clientes;
    }

    @GetMapping("/api/clientes/{id}")
    @ResponseBody//resposta
    public ResponseEntity<Cliente> getClienteById(@PathVariable Integer id) { //parametro , mesmo tipo do mapeamento da entidade @Column
        Optional<Cliente> cliente = clientes.findById(id);
        if (cliente.isPresent()) {
            return ResponseEntity.ok(cliente.get());
        }
        return ResponseEntity.notFound().build();
    }

}
