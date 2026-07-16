import java.util.List;
import java.util.Scanner;

import produto.ICRUD;
import produto.Produto;
import produto.produtoDao;

public class Menu {
    Scanner input = new Scanner(System.in);

    ICRUD dao = new produtoDao();

    public void showMenu() {
        System.out.println("Bem-vindo ao Produtos DB");
        System.out.println("");
        System.out.println("1.Área produtos");
    }

    public void showMenuProdutos() {
        System.out.println("Área produtos");
        System.out.println("");
        System.out.println("1. Cadastrar produto");
        System.out.println("2. Consultar produtos");
        System.out.println("3. Alterar produto");
        System.out.println("4. Deletar produto");
    }

    public void chooseMenu(int option) {
        if (option == 1) {
            showMenuProdutos();
            int optionProduto = input.nextInt();
            input.nextLine();
            chooseProduto(optionProduto);
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

    public void repeat() {
        showMenu();

        int option = input.nextInt();
        input.nextLine();
        chooseMenu(option);

        repeat();
    }
}