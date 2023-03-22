package io.github.byavess;

import io.github.byavess.domain.entity.Cliente;
import io.github.byavess.domain.entity.Pedido;
import io.github.byavess.domain.repository.Clientes;
import io.github.byavess.domain.repository.Pedidos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class VendasApplication {

    @Bean
    public CommandLineRunner commandLineRunner( @Autowired Clientes clientes){
        return args -> {
//            Cliente c = new Cliente(null, "Arthur");
//            clientes.save(c);
        };
    }
        public static void main(String[] args) {
            SpringApplication.run(VendasApplication.class, args);
        }

}
