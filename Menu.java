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
        System.out.println("3. Vendas (Carrinho)");
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

    public void showMenuCarrinho() {
        System.out.println("");
        System.out.println("Área carrinho");
        System.out.println("");
        System.out.println("1. Adicionar produto");
        System.out.println("2. Remover produto");
        System.out.println("3. Ver carrinho");
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

        if (option == 3){
            showMenuCarrinho();
            int optionCarrinho = input.nextInt();
            input.nextLine();
            chooseCarrinho(optionCarrinho);
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

    public void chooseCarrinho(int option) {
        System.out.println("--- NOVO PEDIDO ---");
    System.out.println("Insira o ID do cliente que está comprando: ");
    int idCliente = input.nextInt();
    input.nextLine(); 

    Pedido carrinho = new Pedido(idCliente, "Aberto");

    boolean fechandoCarrinho = false;

    while (!fechandoCarrinho) {
        System.out.println("\n== GERENCIAR CARRINHO ==");
        System.out.println("1. Adicionar Produto");
        System.out.println("2. Finalizar e Salvar Pedido");
        System.out.println("3. Cancelar Pedido");
        int escolha = input.nextInt();
        input.nextLine();

        if (escolha == 1) {
            System.out.println("Insira o ID do produto: ");
            int idProduto = input.nextInt();
            
            Produto prod = dao.consultar(idProduto); 

            if (prod != null) {
                System.out.println("Produto encontrado: " + prod.getDescricao() + " | Preço: R$" + prod.getPreco());
                System.out.println("Insira a quantidade desejada: ");
                int qtd = input.nextInt();
                input.nextLine();

                if (qtd <= prod.getEstoque()) {
                    
                    ItemPedido item = new ItemPedido(prod, qtd);
                    carrinho.adicionarItem(item);
                    System.out.println("Produto adicionado ao carrinho!");
                } else {
                    System.out.println("Estoque insuficiente! Estoque disponível: " + prod.getEstoque());
                }
            } else {
                System.out.println("Produto não encontrado no sistema.");
            }

        } else if (escolha == 2) {
            if (carrinho.getItens().isEmpty()) {
                System.out.println("Não é possível finalizar um pedido sem itens!");
            } else {
            
                pedidoDao daoPedido = new pedidoDao();
                
                
                System.out.println("Valor total do pedido: R$ " + carrinho.getValorTotal());
                
                
                daoPedido.salvar(carrinho); 
                
                System.out.println("Pedido finalizado com sucesso!");
                fechandoCarrinho = true;
            }
        } else if (escolha == 3) {
            System.out.println("Pedido cancelado.");
            fechandoCarrinho = true;
        }
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