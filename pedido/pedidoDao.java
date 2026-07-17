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
        Connection con = ConectaDB.getConnection();
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, pedido.getIdCliente());
            stm.setString(2, pedido.getStatusPedido());
            stm.execute();

            stm.close();
            con.close();

            return pedido;
        } catch (SQLException e) {
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
