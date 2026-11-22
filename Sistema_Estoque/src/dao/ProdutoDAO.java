package dao;

import model.Produto;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ProdutoDAO {

  private static final String CAMINHO_ARQUIVO = "data/produtos.csv";

  public void salvar(Produto produto) {
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(CAMINHO_ARQUIVO, true))) {
      bw.write(produto.toCSV());
      bw.newLine();
    } catch (IOException e) {
      System.out.println("Erro ao salvar no arquivo: " + e.getMessage());
    }
  }

}