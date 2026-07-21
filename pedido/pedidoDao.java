package pedido;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class pedidoDao implements ICRUDpedido {

    @Override
    public Pedido salvar(Pedido pedido) {
        String sql = "insert into tb_pedidos(cliente_id, status_pedido) values(?,?)";
        String sqlItem = "INSERT INTO tb_itens_pedido(pedido_id, produto_id, quantidade, preco_unitario) VALUES (?, ?, ?, ?)";

        Connection con = ConectaDB.getConnection();
        try {
            PreparedStatement stmPedido = con.prepareStatement(sql);
            stmPedido.setInt(1, pedido.getIdCliente());
            stmPedido.setString(2, pedido.getStatusPedido());
            stmPedido.executeUpdate();


            ResultSet rsKeys = stmPedido.getGeneratedKeys();
            int pedidoIdGenerado = 0;
            if (rsKeys.next()) {
                pedidoIdGenerado = rsKeys.getInt(1);
                pedido.setId(pedidoIdGenerado);
            }
            stmPedido.close();
 

            PreparedStatement stmItem = con.prepareStatement(sqlItem);
            for (ItemPedido item : pedido.getItens()) {
                stmItem.setInt(1, pedido.getId());
                stmItem.setInt(2, item.getProduto().getId());
                stmItem.setInt(3, item.getQuantidade());
                stmItem.setDouble(4, item.getPrecoUnitario());
                stmItem.addBatch();
            }

            stmItem.executeBatch();
            stmItem.close();

            con.commit();
            con.close();

            System.out.println("Pedido " + pedidoIdGenerado + " e seus itens foram salvos com sucesso!");
            return pedido;
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void alterar(Pedido pedido) {

        String sql = "update tb_pedidos set cliente_id = ?, status_pedido = ? where id = ?";
        Connection con = ConectaDB.getConnection();

        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, pedido.getIdCliente());
            stm.setString(2, pedido.getStatusPedido());
            stm.setInt(3, pedido.getId());
            stm.execute();

            stm.close();
            con.close();

            System.out.println("Alterando pedido: " + pedido.getId());
        } catch (SQLException e) {
            e.printStackTrace();

        }

    }

    @Override
    public Pedido consultar(int id) {
        System.out.println("Consultando pedido com id: " + id);
        return new Pedido(id, 1, "2023-01-01", "Em andamento");
    }

    @Override
    public List<Pedido> consultar() {
        System.out.println("Consultando todos os pedidos");
        List<Pedido> pedidos = new ArrayList<Pedido>();
        Connection con = ConectaDB.getConnection();
        String sql = "select * from tb_pedidos";

        try {
            PreparedStatement stm = con.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Pedido p = new Pedido(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4));
                pedidos.add(p);
            }
            stm.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pedidos;
    }

}
