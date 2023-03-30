package io.github.byavess.rest.Controller;

import io.github.byavess.domain.entity.Cliente;
import io.github.byavess.domain.entity.Produto;
import io.github.byavess.domain.repository.Produtos;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.*; // pra não precisar colocar o @ResponseStatus(**HttpStatus**.

@RestController
@RequestMapping("/api/produto")
public class ProdutoController {
    private Produtos repository;

    public ProdutoController (Produtos repository){

        this.repository =repository;
    }

    @PostMapping
    @ResponseStatus(CREATED)// codigo de estatus apropriado quando cria um recurso no servidor
    public Produto save(@RequestBody Produto produto) {
        return repository.save(produto);

    }
    @PutMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void update(@PathVariable Integer id, @RequestBody Produto produto) {
        repository
                .findById(id)
                .map( p ->{
                    produto.setId(p.getId());
                    repository.save(produto);
                    return produto;
                }) .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "produto não encontrado"));
    }
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        repository.findById(id)
                .map(p -> {
                    repository.deleteById(id);//ou o (p.getId)
                    return Void.TYPE; //ou null
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "produto não encontrado") );
    }
    @GetMapping("{id}")
    public Produto getById(@PathVariable Integer id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não econtrado"));
    }

    @GetMapping
    public List<Produto> find(Produto filtro) {
        ExampleMatcher matcher = ExampleMatcher // permite fazer algumas configurações pra encontrar clientes
                .matching()
                .withIgnoreCase()
                .withStringMatcher(
                        ExampleMatcher.StringMatcher.CONTAINING//aqui faz o filtro e retorna
                );

        Example example = Example.of(filtro, matcher); // pega o filtro pega o que ta poupulada e cria o example
        return repository.findAll(example);
    }







}
