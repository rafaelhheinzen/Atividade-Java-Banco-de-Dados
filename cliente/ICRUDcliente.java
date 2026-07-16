package cliente;
import java.util.List;

public interface ICRUDcliente {
    void salvar(Cliente cliente);
    Cliente consultar(int id);
    void alterar(Cliente cliente);
    void deletar(int id);
    List<Cliente> consultar();
}