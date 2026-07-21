package produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class produtoDao implements ICRUD {

    @Override
    public Produto salvar(Produto produto) {
        produto.setId(1);
        String sql = "insert into tb_produtos(descricao, preco, estoque)values(?,?,?)";
        Connection con = ConectaDB.getConnection();
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, produto.getDescricao());
            stm.setDouble(2, produto.getPreco());
            stm.setInt(3, produto.getEstoque());
            stm.execute();

            stm.close();
            con.close();

            return produto;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void deletar(int id) {

        String sql = "delete from tb_produtos where id = ?";
        Connection con = ConectaDB.getConnection();

        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, id);
            stm.execute();

            stm.close();
            con.close();

            System.out.println("Deletando produto: " + id);
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    @Override
    public void alterar(Produto produto) {

        String sql = "update tb_produtos set descricao = ?, preco = ?, estoque = ? where id = ?";
        Connection con = ConectaDB.getConnection();

        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, produto.getDescricao());
            stm.setDouble(2, produto.getPreco());
            stm.setInt(3, produto.getEstoque());
            stm.setInt(4, produto.getId());
            stm.execute();

            stm.close();
            con.close();

            System.out.println("Alterando produto: " + produto.getDescricao());
        } catch (SQLException e) {
            e.printStackTrace();

        }

    }

    @Override
    public Produto consultar(int id) {
        System.out.println("Consultando produto com id: " + id);

        Connection con = ConectaDB.getConnection();
        String sql = "select id, descricao, preco, estoque from tb_produtos where id = ?";

        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return new Produto(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getInt(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Produto> consultar() {
        System.out.println("Consultando todos os produtos");
        List<Produto> produtos = new ArrayList<Produto>();
        Connection con = ConectaDB.getConnection();
        String sql = "select * from tb_produtos";

        try {
            PreparedStatement stm = con.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Produto p = new Produto(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getInt(4));
                produtos.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return produtos;
    }

}
