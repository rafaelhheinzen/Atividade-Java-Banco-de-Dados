package pedido;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private int id;
    private int idCliente;
    private String dataCadastro; 
    private String statusPedido;
    private List<ItemPedido> itens = new ArrayList<>();

    

    public Pedido(){
    }

    public Pedido(int idCliente, String statusPedido){
        setIdCliente(idCliente);
        setStatusPedido(statusPedido);
    }

    public Pedido(int id, int idCliente, String dataCadastro, String statusPedido){
        setId(id);
        setIdCliente(idCliente);
        setDataCadastro(dataCadastro);
        setStatusPedido(statusPedido);
    }



    public void adicionarItem(ItemPedido item){
        this.itens.add(item);
    }

    public void removerItem(ItemPedido item){
        this.itens.remove(item);
    }


    public List<ItemPedido> getItens() {
        return itens;
    }

    public double getValorTotal() {
        double total = 0;
        for (ItemPedido item : this.itens) {
            total += item.getSubtotal();
        }
        return total;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public void setStatusPedido(String statusPedido) {
        this.statusPedido = statusPedido;
    }

    public int getIdCliente() {
        return this.idCliente;
    }

    public String getStatusPedido() {
        return this.statusPedido;
    }

    public int getId() {
        return this.id;
    }

    
    public void setDataCadastro(String dataCadastro){ this.dataCadastro = dataCadastro; }
    public String getDataCadastro(){ return this.dataCadastro; }

}