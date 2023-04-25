package io.github.byavess.exeption;

public class PedidoNaoEncontradoException extends RuntimeException {
    public PedidoNaoEncontradoException() {
        super("Pedido naão encontrado");
    }
}
