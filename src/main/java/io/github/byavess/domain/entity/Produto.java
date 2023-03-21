package io.github.byavess.domain.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "produto")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "descircao")
    private String descircao;
    @Column(name = "preco_unitario")
    private BigDecimal preco;

    @OneToMany(mappedBy = "produto")
    List<ItemPedido> itens;

    public Produto() {
    }

    public Produto(Integer id, String descircao, BigDecimal preco) {
        this.id = id;
        this.descircao = descircao;
        this.preco = preco;
    }

    public Integer getCliente_id() {
        return id;
    }

    public void setCliente_id(Integer cliente_id) {
        this.id = cliente_id;
    }

    public String getDescircao() {
        return descircao;
    }

    public void setDescircao(String descircao) {
        this.descircao = descircao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }
}
