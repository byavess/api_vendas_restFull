package io.github.byavess.api.Controller;

import io.github.byavess.domain.entity.Cliente;
import io.github.byavess.domain.repository.Clientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller// passa a ser gerenciado pelo spring, pode receber requisições
public class ClienteController {


    private Clientes clientes;

    public ClienteController(Clientes clientes) {
        this.clientes = clientes;
    }

    @GetMapping("/api/clientes/{id}")
    @ResponseBody
    public ResponseEntity<Cliente> getClienteById(@PathVariable Integer id) { //parametro , mesmo tipo do mapeamento da entidade @Column
        Optional<Cliente> cliente = clientes.findById(id);//obtendo cliente por id
        if (cliente.isPresent()) {
            return ResponseEntity.ok(cliente.get()); //se existir
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/api/clientes")
    @ResponseBody
    public ResponseEntity save(@RequestBody Cliente cliente) {//request entra vem na requisisção e o response retorna
        Cliente clienteSalvo = clientes.save(cliente);
        return ResponseEntity.ok(clienteSalvo);
    }

    @DeleteMapping("/api/clientes/{id}")
    @ResponseBody
    public ResponseEntity delete(@PathVariable Integer id) { //parametro , mesmo tipo do mapeamento da entidade @Column
        Optional<Cliente> cliente = clientes.findById(id);
        if (cliente.isPresent()) {
            clientes.delete(cliente.get() );
            return ResponseEntity.noContent().build();// quando não for retornar nada noContente
        }
        return ResponseEntity.notFound().build();
    }

  @PutMapping("/api/clientes/{id}")
  @ResponseBody
    public ResponseEntity update(@PathVariable Integer id , @RequestBody  Cliente cliente){
       return clientes
               .findById(id) //validando id
               .map(clienteExistente -> {// se existe
                 cliente.setId(clienteExistente.getId()); // modifica id
      clientes.save(cliente);
      return ResponseEntity.noContent().build();
       }).orElseGet( () -> ResponseEntity.notFound().build());//retorna suplai que e uma interface que retorna qualuer coisa
  }

  @GetMapping("/api/clientes")
  public ResponseEntity pesquisaCliente(Cliente filtro) {
      ExampleMatcher matcher = ExampleMatcher // permite fazer algumas configurações pra encontrar clientes
              .matching()
              .withIgnoreCase()
              .withStringMatcher(
                      ExampleMatcher.StringMatcher.CONTAINING//aqui faz o filtro e retorna
              );

      Example example = Example.of(filtro, matcher); // pega o filtro pega o que ta poupulada e cria o example
      List<Cliente> lista = clientes.findAll(example);
      return ResponseEntity.ok(lista);
  }
}
