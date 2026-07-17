package pedido;

import produto.Produto;

public class ItemPedido {
    private int id;
    private Produto produto;
    private int quantidade;
    private double precoUnitario;

    // Construtor vazio
    public ItemPedido() {
    }

    
    public ItemPedido(Produto produto, int quantidade) {
        setProduto(produto);
        setQuantidade(quantidade);
        setPrecoUnitario(produto.getPreco());
    }

    
    public ItemPedido(int id, Produto produto, int quantidade, double precoUnitario) {
        setId(id);
        setProduto(produto);
        setQuantidade(quantidade);
        setPrecoUnitario(precoUnitario);
    }

    public double getSubtotal() {
        return this.quantidade * this.precoUnitario;
    }

    // Setters e Getters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }
}