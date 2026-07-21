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
        String sqlPedido = "INSERT INTO tb_pedidos(cliente_id, status_pedido) VALUES (?, ?)";
        String sqlItem = "INSERT INTO tb_itens_pedido(pedido_id, produto_id, quantidade, preco_unitario) VALUES (?, ?, ?, ?)";

        Connection con = ConectaDB.getConnection();
        if (con == null) {
            return null;
        }

        try {
            con.setAutoCommit(false);

            PreparedStatement stmPedido = con.prepareStatement(sqlPedido, java.sql.Statement.RETURN_GENERATED_KEYS);
            stmPedido.setInt(1, pedido.getIdCliente());
            stmPedido.setString(2, pedido.getStatusPedido());
            stmPedido.executeUpdate();

            ResultSet rsKeys = stmPedido.getGeneratedKeys();
            int pedidoIdGenerado = 0;
            if (rsKeys.next()) {
                pedidoIdGenerado = rsKeys.getInt(1);
                pedido.setId(pedidoIdGenerado);
            }
            rsKeys.close();
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
                if (con != null) {
                    con.rollback();
                    con.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void alterar(Pedido pedido) {

        String sqlPedido = "UPDATE tb_pedidos SET status_pedido = ? WHERE id = ?";
        String sqlDeleteItens = "DELETE FROM tb_itens_pedido WHERE pedido_id = ?";
        String sqlInsertItem = "INSERT INTO tb_itens_pedido(pedido_id, produto_id, quantidade, preco_unitario) VALUES (?, ?, ?, ?)";

        Connection con = ConectaDB.getConnection();
        if (con == null)
            return;

        try {
            con.setAutoCommit(false);

            PreparedStatement stmPedido = con.prepareStatement(sqlPedido);
            stmPedido.setString(1, pedido.getStatusPedido());
            stmPedido.setInt(2, pedido.getId());

            int prod = stmPedido.executeUpdate();
            stmPedido.close();

            if (prod == 0) {
                System.out.println("Pedido ID " + pedido.getId() + " não foi encontrado no banco.");
                con.rollback();
                con.close();
                return;
            }

            PreparedStatement stmDelete = con.prepareStatement(sqlDeleteItens);
            stmDelete.setInt(1, pedido.getId());
            stmDelete.executeUpdate();
            stmDelete.close();

            if (pedido.getItens() != null && !pedido.getItens().isEmpty()) {
                PreparedStatement stmInsert = con.prepareStatement(sqlInsertItem);
                for (ItemPedido item : pedido.getItens()) {
                    stmInsert.setInt(1, pedido.getId());
                    stmInsert.setInt(2, item.getProduto().getId());
                    stmInsert.setInt(3, item.getQuantidade());
                    stmInsert.setDouble(4, item.getPrecoUnitario());
                    stmInsert.addBatch();
                }
                stmInsert.executeBatch();
                stmInsert.close();
            }

            con.commit();
            con.close();

            System.out.println("Alterando pedido: " + pedido.getId() + " e seus itens foram alterados.");

        } catch (SQLException e) {
            try {
                if (con != null) {
                    con.rollback();
                    con.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }

    }

    @Override
    public Pedido consultar(int id) {
        System.out.println("Consultando pedido com id: " + id);
        return new Pedido(id, 1, "202-01-01", "Em andamento");
    }

    @Override
    public void deletar(int id) {
        String sql = "delete from tb_pedidos where id = ?";
        Connection con = ConectaDB.getConnection();

        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, id);
            stm.execute();

            stm.close();
            con.close();

            System.out.println("Deletando pedido: " + id);
        } catch (SQLException e) {
            e.printStackTrace();

        }
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
