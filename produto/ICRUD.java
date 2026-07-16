package produto;
import java.util.List;

public interface ICRUD {
    Produto salvar(Produto pig);
    void deletar(int id);
    void alterar(Produto pig);
    Produto consultar(int id);
    List<Produto> consultar();
}



