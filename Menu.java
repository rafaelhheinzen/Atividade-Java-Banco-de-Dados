import java.util.List;
import java.util.Scanner;

import produto.ICRUD;
import produto.Produto;
import produto.produtoDao;

import cliente.clienteDao;
import cliente.ICRUDcliente;
import cliente.Cliente;

import pedido.ItemPedido;
import pedido.Pedido;
import pedido.pedidoDao;
import pedido.ICRUDpedido;





public class Menu {
    Scanner input = new Scanner(System.in);

    ICRUD dao = new produtoDao();
    ICRUDcliente daoCliente = new clienteDao();
    ICRUDpedido daoPedido = new pedidoDao();

    public void showMenu() {
        System.out.println("Bem-vindo ao Produtos DB");
        System.out.println("");
        System.out.println("1. Área produtos");
        System.out.println("2. Área clientes");
    }

    public void showMenuProdutos() {
        System.out.println("Área produtos");
        System.out.println("");
        System.out.println("1. Cadastrar produto");
        System.out.println("2. Consultar produtos");
        System.out.println("3. Alterar produto");
        System.out.println("4. Deletar produto");
    }

    public void showMenuClientes() {
        System.out.println("Área clientes");
        System.out.println("");
        System.out.println("1. Cadastrar cliente");
        System.out.println("2. Consultar clientes");
        System.out.println("3. Alterar cliente");
        System.out.println("4. Deletar cliente");
    }

    public void chooseMenu(int option) {
        if (option == 1) {
            showMenuProdutos();
            int optionProduto = input.nextInt();
            input.nextLine();
            chooseProduto(optionProduto);
        }

        if (option == 2) {
            showMenuClientes();
            int optionCliente = input.nextInt();
            input.nextLine();
            chooseCliente(optionCliente);
        }
    }

    public void chooseProduto(int option) {
        if (option == 1) {
            System.out.println("");
            System.out.println("Insira a descrição do produto: ");
            String descricao = input.nextLine();

            System.out.println("");
            System.out.println("Insira o preço do produto: ");
            double preco = input.nextDouble();

            System.out.println("");
            System.out.println("Insira a quantidade de estoque do produto: ");
            int estoque = input.nextInt();

            Produto p1 = new Produto(descricao, preco, estoque);
            dao.salvar(p1);
        }

        if (option == 2) {
            List<Produto> lista = dao.consultar();

            // Números estranhos para a compactação do texto no resultado

            System.out.println();
            System.out.printf("| %-5s | %-20s | %-10s | %-10s |%n", "ID", "Descrição", "Preço", "Estoque");
            System.out.println("|-------|----------------------|------------|------------|");

            for (Produto p : lista) {
                System.out.printf("| %-5d | %-20s | %10.2f | %10d |%n",
                        p.getId(),
                        p.getDescricao(),
                        p.getPreco(),
                        p.getEstoque());
            }

        }

        if (option == 3) {
            System.out.println("");
            System.out.println("Insira o id do produto que você quer alterar: ");
            int id = input.nextInt();
            input.nextLine();

            System.out.println("");
            System.out.println("Insira a nova descrição do produto: ");
            String descricao = input.nextLine();

            System.out.println("");
            System.out.println("Insira o novo preço do produto: ");
            double preco = input.nextDouble();

            System.out.println("");
            System.out.println("Insira o novo estoque do produto: ");
            int estoque = input.nextInt();

            Produto p2 = new Produto(id, descricao, preco, estoque);
            dao.alterar(p2);

        }

        if (option == 4) {
            System.out.println("");
            System.out.println("Insira o id do produto que você quer deletar: ");
            int id = input.nextInt();
            dao.deletar(id);

            System.out.println("");
            System.out.println("Produto deletado!");

        }
    }

    public void chooseCliente(int option) {
        if (option == 1) {
            System.out.println("");
            System.out.println("Insira o nome do cliente: ");
            String nome = input.nextLine();

            System.out.println("");
            System.out.println("Insira o email do cliente: ");
            String email = input.nextLine();

            System.out.println("");
            System.out.println("Insira o cpf do cliente: ");
            String cpf = input.nextLine();
            input.nextLine();

            System.out.println("");
            System.out.println("Insira a rua do cliente: ");
            String rua = input.nextLine();

            System.out.println("");
            System.out.println("Insira o número do cliente: ");
            int numero = input.nextInt();
            input.nextLine();

            System.out.println("");
            System.out.println("Insira o bairro do cliente: ");
            String bairro = input.nextLine();

            System.out.println("");
            System.out.println("Insira a cidade do cliente: ");
            String cidade = input.nextLine();

            System.out.println("");
            System.out.println("Insira o CEP do cliente: ");
            int cep = input.nextInt();
            input.nextLine();

            System.out.println("");
            System.out.println("Insira o estado do cliente: ");
            String estado = input.nextLine();

            Cliente c1 = new Cliente(nome, email, cpf, rua, numero, bairro, cidade, cep, estado);
            daoCliente.salvar(c1);
        }

        if (option == 2) {
            List<Cliente> lista = daoCliente.consultar();

            System.out.println();

            System.out.printf("| %-6s | %-15s | %-20s | %-14s | %-20s | %-6s | %-15s | %-15s | %-10s | %-6s |%n",
                    "ID", "Nome", "E-mail", "CPF", "Rua", "Nº", "Bairro", "Cidade", "CEP", "Estado");


            System.out.println(
                    "|--------|-----------------|----------------------|----------------|----------------------|--------|-----------------|-----------------|------------|--------|");

            for (Cliente c : lista) {

                System.out.printf("| %-6s | %-15s | %-20s | %-14s | %-20s | %-6s | %-15s | %-15s | %-10s | %-6s |%n",
                        c.getId(), 
                        c.getNome(),
                        c.getEmail(),
                        c.getCpf(),
                        c.getRua(),
                        c.getNumero(),
                        c.getBairro(),
                        c.getCidade(),
                        c.getCep(),
                        c.getEstado());
            }
        }

        if (option == 3) {
            System.out.println("");
            System.out.println("Insira o id do usuário que você quer alterar: ");
            int id = input.nextInt();
            input.nextLine();

            System.out.println("");
            System.out.println("Insira o novo nome do cliente: ");
            String nome = input.nextLine();

            System.out.println("");
            System.out.println("Insira o novo email do cliente: ");
            String email = input.nextLine();

            System.out.println("");
            System.out.println("Insira o novo cpf do cliente: ");
            String cpf = input.nextLine();

            System.out.println("");
            System.out.println("Insira a nova rua do cliente: ");
            String rua = input.nextLine();

            System.out.println("");
            System.out.println("Insira o novo número do cliente: ");
            int numero = input.nextInt();
            input.nextLine();

            System.out.println("");
            System.out.println("Insira o novo bairro do cliente: ");
            String bairro = input.nextLine();

            System.out.println("");
            System.out.println("Insira a nova cidade do cliente: ");
            String cidade = input.nextLine();

            System.out.println("");
            System.out.println("Insira o novo CEP do cliente: ");
            int cep = input.nextInt();
            input.nextLine();

            System.out.println("");
            System.out.println("Insira o novo estado do cliente: ");
            String estado = input.nextLine();

            Cliente c2 = new Cliente(id, nome, email, cpf, rua, numero, bairro, cidade, cep, estado);
            daoCliente.alterar(c2);
        }

        if (option == 4) {
            System.out.println("");
            System.out.println("Insira o id do cliente que você quer deletar: ");
            int id = input.nextInt();
            daoCliente.deletar(id);

            System.out.println("");
            System.out.println("Cliente deletado!");
        }
    }

    public void repeat() {
        showMenu();

        int option = input.nextInt();
        input.nextLine();
        chooseMenu(option);

        repeat();
    }
}