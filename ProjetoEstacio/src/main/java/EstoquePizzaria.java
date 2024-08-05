import javax.swing.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class EstoquePizzaria {
    public static void main(String[] args) {
        //Ajustando a data para torna-la compatível ao mysql
        try {
            String inputData = "2024-05-31";
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date data = dateFormat.parse(inputData);
            System.out.println("Data formatada: " + data);
        } catch (ParseException e) {
            System.out.println("Formato de data inválido. Por favor, insira a data no formato yyyy-MM-dd.");
        }
        //Configurando o Driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conexao = DriverManager.getConnection("jdbc:mysql://localhost/pjt_estacio", "root", "");
            Statement stmt = conexao.createStatement();
            Produto p = new Produto();
            //Vetor dos produtos a serem adicionados
            ArrayList<Produto> produtos = new ArrayList<>();
            String options[] = {"Cadastrar produtos", "Listar produtos", "Alterar dados de produtos", "Deletar produtos", "Sair"};
            ImageIcon icon = new ImageIcon("src/main/Extras/z.png");
            boolean sair = false;
            int x = 0, opcao;

            //Menu de operações
            do {
                opcao = JOptionPane.showOptionDialog(null, "Bem-vindo(a) ao estoque da ArtPizza! O que deseja fazer?", "Controle de Estoque", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, icon, options, options[0]);

                switch (opcao) {
                    case 0:
                        p.cadastrarProduto(conexao);
                        break;
                    case 1:
                       p.listarProduto(conexao);
                        break;
                    case 2:
                       p.listarProduto(conexao);
                       p.alterarProduto(conexao);
                       break;
                    case 3:
                        p.listarProduto(conexao);
                        p.deletarProduto(conexao);
                        break;
                    case 4:
                        sair = true;
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Opção Inválida");
                        break;
                }
            } while (opcao != 4);


        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Driver não localizado.\n" + e.getMessage());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao acessar Banco de Dados.");
        }
    }
}


