package dao;

import model.Produto;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

  public List<Produto> buscarTodos() {
    List<Produto> produtos = new ArrayList<>();
    File arquivo = new File(CAMINHO_ARQUIVO);

    if (!arquivo.exists()) {
      return produtos;
    }

    try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
      String linha;
      boolean primeiraLinha = true;

      while ((linha = br.readLine()) != null) {
        if (primeiraLinha) {
          primeiraLinha = false;
          continue;
        }

        String[] dados = linha.split(";");

        if (dados.length == 4) {
          String nome = dados[0];
          double preco = Double.parseDouble(dados[1]);
          int quantidade = Integer.parseInt(dados[2]);
          String categoria = dados[3];

          Produto p = new Produto(nome, preco, quantidade, categoria);
          produtos.add(p);
        }
      }
    } catch (IOException e) {
      System.out.println("Erro ao ler o arquivo: " + e.getMessage());
    }

    return produtos;
  }

}