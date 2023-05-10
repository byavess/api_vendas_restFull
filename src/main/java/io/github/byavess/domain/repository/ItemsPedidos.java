package io.github.byavess.domain.repository;

import io.github.byavess.domain.entity.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ItemsPedidos extends JpaRepository<ItemPedido, Integer> {
}
