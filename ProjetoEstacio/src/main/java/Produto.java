import java.sql.*;
import javax.swing.JOptionPane;

public class Produto {


    private int idproduto;
    private String nome;
    private int qtd;
    private Date data_entrega;
    private double preco;

    public Produto() {
    }

    public Produto(int idproduto, String nome, int qtd, Date data_entrega) {
        this.idproduto = idproduto;
        this.nome = nome;
        this.qtd = qtd;
        this.data_entrega = data_entrega;
        this.preco = preco;
    }

    public int getIdproduto() {
        return idproduto;
    }

    public void setIdproduto(int idproduto) {
        this.idproduto = idproduto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public Date getData_entrega() {
        return data_entrega;
    }

    public void setData_entrega(Date data_entrega) {
        this.data_entrega = data_entrega;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    //Métodos
    public void cadastrarProduto(Connection conexao) throws SQLException {
        try {
            this.setNome(JOptionPane.showInputDialog("Informe o nome do produto:"));
            this.setQtd(Integer.parseInt(JOptionPane.showInputDialog("Informe a quantidade:")));
            this.setData_entrega(Date.valueOf(JOptionPane.showInputDialog("Informe a data de aquisição do produto:")));
            this.setPreco(Double.parseDouble(JOptionPane.showInputDialog("Informe o valor unitário de compra do produto:")));
            String sql = "INSERT INTO produto (nome, qtd, data_entrega, preco) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, this.getNome());
            stmt.setInt(2, this.getQtd());
            stmt.setDate(3, this.getData_entrega());
            stmt.setDouble(4, this.getPreco());
            stmt.executeUpdate();

            //stmt.executeUpdate(sql);
            stmt.close();
            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto: " + e.getMessage());
        }


    }

    public void listarProduto(Connection conexao) throws SQLException {
        try {
            String sql = "SELECT * FROM produto";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery(sql);

            StringBuilder listaProdutos = new StringBuilder();
            listaProdutos.append("Lista de Produtos\n");
            while (rs.next()) {
                listaProdutos.append("ID: ").append(rs.getInt("idproduto")).append("\n");
                listaProdutos.append("Nome: ").append(rs.getString("nome")).append("\n");
                listaProdutos.append("Quantidade em estoque: ").append(rs.getInt("qtd")).append("\n");
                listaProdutos.append("Data de compra: ").append(rs.getDate("data_entrega")).append("\n");
                listaProdutos.append("preco: ").append(rs.getDouble("preco")).append("\n");

                System.out.println("ID: " + rs.getInt("idproduto") + ", Nome: " + rs.getString("nome") + ", Quantidade: " + rs.getInt("qtd") + ", Data: " + rs.getDate("data_entrega") + ", Preço: " + rs.getDouble("preco"));
            }


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Não foi possível exibir lista: " + e.getMessage());
        }

    }

    public void alterarProduto(Connection conexao) throws SQLException {

        String options[] = {"Nome", "Quantidade em estoque", "Data de compra", "Preço de compra unitário"};
          try {
              int p = Integer.parseInt(JOptionPane.showInputDialog("Informe o ID do produto a ser alterado:"));
              int op = JOptionPane.showOptionDialog(null, "O que deseja alterar? ", "Altualização de Produto", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
              switch (op) {
                  case 0:

                      String novoNome = JOptionPane.showInputDialog("Informe o novo nome:");
                      String sql = "UPDATE PRODUTO SET NOME = '" + novoNome + "' WHERE IDPRODUTO = " + p;
                      PreparedStatement stmt = conexao.prepareStatement(sql);
                      stmt.executeUpdate(sql);
                      JOptionPane.showMessageDialog(null, "Nome do produto alterado com sucesso");
                      break;
                  case 1:
                      int newQtd = Integer.parseInt(JOptionPane.showInputDialog("Informe a nova quantidade:"));
                      sql = "UPDATE PRODUTO SET QTD = '" + newQtd + "' WHERE IDPRODUTO = " + p;
                      stmt = conexao.prepareStatement(sql);
                      stmt.executeUpdate(sql);
                      JOptionPane.showMessageDialog(null, "Quantidade do produto alterada com sucesso");
                      break;
                  case 2:
                      Date newData = Date.valueOf(JOptionPane.showInputDialog("Informe a nova quantidade:"));
                      sql = "UPDATE PRODUTO SET DATA_ENTREGA = '" + newData + "' WHERE IDPRODUTO = " + p;
                      stmt = conexao.prepareStatement(sql);
                      stmt.executeUpdate(sql);
                      JOptionPane.showMessageDialog(null, "Data de aquisição do produto alterada com sucesso");
                      break;
                  case 3:
                      double newPreco = Double.parseDouble(JOptionPane.showInputDialog("Informe a nova quantidade:"));
                      sql = "UPDATE PRODUTO SET QTD = '" + newPreco + "' WHERE IDPRODUTO = " + p;
                      stmt = conexao.prepareStatement(sql);
                      stmt.executeUpdate(sql);
                      JOptionPane.showMessageDialog(null, "Preço do produto alterado com sucesso");
                      break;
                  default:
                      JOptionPane.showMessageDialog(null, "Opção Inválida");
              }
          }catch(SQLException e){
             JOptionPane.showMessageDialog( null, e.getMessage());
          }
    }

    public void deletarProduto(Connection conexao) throws SQLException{

        try{
            int p = Integer.parseInt(JOptionPane.showInputDialog("Informe o ID do produto a ser deletado:"));
            String sql = "DELETE FROM PRODUTO WHERE IDPRODUTO = " + p;
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Produto deletado com sucesso");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }


    }

}
