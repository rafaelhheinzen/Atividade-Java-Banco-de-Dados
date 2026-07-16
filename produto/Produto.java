package produto;

public class Produto {
    private int id;
    private String descricao;
    private double preco;
    private int estoque;

    public Produto() {

    }

    public Produto(String descricao, double preco, int estoque) {
        setDescricao(descricao);
        setPreco(preco);
        setEstoque(estoque);
    }

    public Produto(int id, String descricao, double preco, int estoque) {
        setId(id);
        setDescricao(descricao);
        setPreco(preco);
        setEstoque(estoque);
    }

    // Getters
    public int getId() {
        return this.id;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public double getPreco() {
        return this.preco;
    }

    public int getEstoque() {
        return this.estoque;
    }

    
    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }
}