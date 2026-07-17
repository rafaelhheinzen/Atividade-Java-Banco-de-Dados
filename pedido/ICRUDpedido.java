package pedido;
import java.util.List;

public interface ICRUDpedido {
    Pedido salvar(Pedido pedido);
    Pedido consultar(int id);
    void alterar(Pedido pedido);
    List<Pedido> consultar();
}