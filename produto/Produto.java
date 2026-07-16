package produto;
public class Produto {
    private int id;
    private String descricao;
    private double preco;

    public Produto(){

    }

    public Produto(String descricao, double preco){
        setDescricao(descricao);
        setPreco(preco);
    }

    public Produto(int id, String descricao, double preco){
        setId(id);
        setDescricao(descricao);
        setPreco(preco);
    }


    //Getters
    public int getId() {
        return this.id;
    }

    public String getDescricao(){
        return this.descricao;
    }

    public double getPreco(){
        return this.preco;
    }

    //Setters
    public void setId(int id){
        this.id = id;
    }

    public void setDescricao(String descricao){
        this.descricao = descricao;
    }

    public void setPreco(double preco){
        this.preco = preco;
    }
}