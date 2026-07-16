package cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class clienteDao implements ICRUDcliente {

    @Override
    public void salvar(Cliente cliente) {
        String sql = "insert into tb_clientes(nome, email, cpf, rua, numero, bairro, cidade, cep, estado)values(?,?,?,?,?,?,?,?,?)";
        
        Connection con = ConectaDB.getConnection();
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, cliente.getNome());
            stm.setString(2, cliente.getEmail());
            stm.setLong(3, cliente.getCpf());
            stm.setString(4, cliente.getRua());
            stm.setInt(5, cliente.getNumero());
            stm.setString(6, cliente.getBairro());
            stm.setString(7, cliente.getCidade());
            stm.setInt(8, cliente.getCep());
            stm.setString(9, cliente.getEstado());
            stm.execute();

            stm.close();
            con.close();

       } catch (SQLException e) {
            e.printStackTrace();

        }}



    @Override
    public void deletar(int id) {

        String sql = "delete from tb_clientes where id = ?";
        Connection con = ConectaDB.getConnection();

        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, id);
            stm.execute();

            stm.close();
            con.close();

            System.out.println("Deletando cliente: " + id);
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    @Override
    public void alterar(Cliente cliente) {

        String sql = "update tb_clientes set nome = ?, email = ?, cpf = ?, rua = ?, numero = ?, bairro = ?, cidade = ?, cep = ?, estado = ? where id = ?";
        Connection con = ConectaDB.getConnection();

        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, cliente.getNome());
            stm.setString(2, cliente.getEmail());
            stm.setLong(3, cliente.getCpf());
            stm.setString(4, cliente.getRua());
            stm.setInt(5, cliente.getNumero());
            stm.setString(6, cliente.getBairro());
            stm.setString(7, cliente.getCidade());
            stm.setInt(8, cliente.getCep());
            stm.setString(9, cliente.getEstado());
            stm.setInt(10, cliente.getId());
            stm.execute();

            stm.close();
            con.close();

            System.out.println("Alterando cliente: " + cliente.getNome());
        } catch (SQLException e) {
            e.printStackTrace();

        }

    }

    @Override
    public Cliente consultar(int id) {
        System.out.println("Consultando cliente com id: " + id);
        return new Cliente(id, "Nome do cliente: ", "email@example.com", 123456789011L, "Rua Exemplo", 123, "Bairro Exemplo", "Cidade Exemplo", 12345678, "Estado Exemplo");
    }

    @Override
    public List<Cliente> consultar() {
        System.out.println("Consultando todos os clientes");

        List<Cliente> clientes = new ArrayList<Cliente>();
        Connection con = ConectaDB.getConnection();
        String sql = "select * from tb_clientes";

        try {
            PreparedStatement stm = con.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Cliente c = new Cliente(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getLong(4), rs.getString(5), rs.getInt(6), rs.getString(7), rs.getString(8), rs.getInt(9), rs.getString(10));
                clientes.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clientes;
    }

}
