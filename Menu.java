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
        System.out.println("");
        System.out.println("==============================================");
        System.out.println("Bem-vindo ao Produtos DB");
        System.out.println("");
        System.out.println("1. Área produtos");
        System.out.println("2. Área clientes");
        System.out.println("3. Área pedidos");
        System.out.println("==============================================");
        System.out.println("");
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

    public void showMenuPedidos() {
        System.out.println("Área pedidos");
        System.out.println("");
        System.out.println("1. Cadastrar pedido");
        System.out.println("2. Consultar pedidos");
        System.out.println("3. Alterar pedido");
        System.out.println("4. Deletar pedido");
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

        if (option == 3) {
            showMenuPedidos();
            int optionPedido = input.nextInt();
            input.nextLine();
            choosePedido(optionPedido);
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

    public void choosePedido(int option) {
        if (option == 1) {
            System.out.println("");
            System.out.print("Insira o ID do Cliente para o pedido: ");
            int idCliente = input.nextInt();
            input.nextLine();

            Pedido pedido = new Pedido(idCliente, "PROCESSANDO");

            boolean adicionando = true;

            while (adicionando == true) {
                System.out.println("\n--- Adicionar Item ao Carrinho ---");
                System.out.print("Insira o ID do Produto: ");
                int idProduto = input.nextInt();
                input.nextLine();

                Produto prod = dao.consultar(idProduto);

                if (prod == null) {
                    System.out.println("Produto não encontrado! Tente novamente.");
                } else {
                    System.out.print("Insira a quantidade desejada: ");
                    int quantidade = input.nextInt();

                    ItemPedido item = new ItemPedido(prod, quantidade);
                    pedido.adicionarItem(item);

                    System.out.println(quantidade + "x " + prod.getDescricao() + " adicionado(s) ao carrinho!");

                    System.out.print("\nDeseja adicionar outro produto? (1-Sim / 2-Não): ");
                    int resp = input.nextInt();
                    if (resp != 1) {
                        adicionando = false;
                    }
                }

                if (pedido.getItens().isEmpty()) {
                    System.out.println("Nenhum item adicionado ao pedido. Pedido não será salvo.");
                    return;
                }

                System.out.println("\nResumo do Pedido:");
                for (ItemPedido item : pedido.getItens()) {
                    System.out.printf("- %s | Qtd: %d | Un: R$ %.2f | Subtotal: R$ %.2f%n",
                            item.getProduto().getDescricao(),
                            item.getQuantidade(),
                            item.getPrecoUnitario(),
                            item.getSubtotal());
                }

                System.out.printf("VALOR TOTAL: R$ %.2f%n", pedido.getValorTotal());

                System.out.print("\nConfirmar e Finalizar Pedido? (1-Sim / 2-Não): ");
                int confirmar = input.nextInt();

                if (confirmar == 1) {
                    daoPedido.salvar(pedido);
                    System.out.println("Pedido gravado no Banco de Dados!");
                } else {
                    System.out.println("Pedido cancelado.");
                }

            }

        }

        if (option == 2) {
            List<Pedido> lista = daoPedido.consultar();

            System.out.println();
            System.out.printf("| %-5s | %-10s | %-20s | %-15s |%n", "ID", "ID Cliente", "Data Cadastro",
                    "Status Pedido");
            System.out.println("|-------|------------|----------------------|-----------------|");

            for (Pedido p : lista) {
                System.out.printf("| %-5d | %-10d | %-20s | %-15s |%n",
                        p.getId(),
                        p.getIdCliente(),
                        p.getDataCadastro(),
                        p.getStatusPedido());
            }

        }

        if (option == 3) {
            System.out.println("");
            System.out.println("Insira o id do pedido que você quer alterar: ");
            int id = input.nextInt();
            input.nextLine();

            Pedido p2 = daoPedido.consultar(id);

            if (p2 == null) {
                System.out.println("Pedido não encontrado!");
                return;
            } else {
                System.out.println("");
                System.out.println("Insira o novo status do pedido: (Atual: " + p2.getStatusPedido() + ")");
                String statusPedido = input.nextLine();
                p2.setStatusPedido(statusPedido);

                System.out.println("");
                System.out.println("Você deseja alterar os itens do pedido? (1-Sim / 2-Não): ");
                int resp = input.nextInt();
                input.nextLine();

                if (resp == 1) {
                    boolean alterandoItens = true;

                    while (alterandoItens == true) {
                        System.out.println("");
                        System.out.println("-Adicionar item (1)");
                        System.out.println("-Remover item (2)");
                        System.out.println("-Finalizar alterações (3)");
                        System.out.println("");
                        int escolha = input.nextInt();

                        if (escolha == 2) {
                            System.out.println("");
                            System.out.println("Insira o ID do Produto que você deseja remover do pedido: ");
                            int idProdutoRemover = input.nextInt();
                            input.nextLine();

                            List<ItemPedido> itensAtuais = p2.getItens();
                            ItemPedido itemParaRemover = null;

                            for (ItemPedido item : itensAtuais) {
                                if (item.getProduto().getId() == idProdutoRemover) {
                                    itemParaRemover = item;
                                    break;
                                }
                            }

                            if (itemParaRemover != null) {
                                p2.removerItem(itemParaRemover);
                                System.out.println("Produto removido do pedido!");
                            } else {
                                System.out.println("Produto não encontrado no pedido.");
                            }
                        }

                        if (escolha == 1) {
                            System.out.println("");
                            System.out.println("Insira o ID do Produto para adicionar ao pedido: ");
                            int idProduto = input.nextInt();
                            input.nextLine();

                            Produto prod = dao.consultar(idProduto);

                            if (prod == null) {
                                System.out.println("Produto não encontrado! Tente novamente.");
                            } else {
                                System.out.println("");
                                System.out.println("Insira a quantidade desejada: ");
                                int quantidade = input.nextInt();

                                ItemPedido item = new ItemPedido(prod, quantidade);
                                p2.adicionarItem(item);

                                System.out
                                        .println(quantidade + "x " + prod.getDescricao() + " adicionado(s) ao pedido!");
                            }
                        }

                        if (escolha == 3) {
                            alterandoItens = false;
                        }

                    }

                }

                daoPedido.alterar(p2);
                System.out.println("Alterações nos itens do pedido foram salvas!");
            }

        }

        if (option == 4) {
            System.out.println("");
            System.out.println("Insira o id do pedido que você quer deletar: ");
            int id = input.nextInt();
            daoPedido.deletar(id);

            System.out.println("");
            System.out.println("Pedido deletado!");
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