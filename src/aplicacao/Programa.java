package aplicacao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entidades.Produtos;

public class Programa {

	public static void main(String[] args) throws ParseException {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		// Arquivo inicial, contendo as informações dos produtos (nome, preço e quantidade)
		System.out.print("Digite o nome do arquivo (incluindo o formato): ");
		String arquivoInicial = sc.nextLine();
		String path = "C:\\Users\\eduar\\Documents\\PastaProduto\\";
		List<Produtos> lista = new ArrayList<>();
		
		// Realiza a leitura do arquivo inicial
        try (BufferedReader br = new BufferedReader(new FileReader(path + arquivoInicial))) {
            String linha = br.readLine();
            
            // Laço responsável por ler a linha até que ela seja nula
            while (linha != null) {
                String[] infoProdutos = linha.split(";");
                String nome = infoProdutos[0];
                double preco = Double.parseDouble(infoProdutos[1]);
                int quantidade = Integer.parseInt(infoProdutos[2]);
                
                // Instância e adição dos produtos à lista
                Produtos produtos = new Produtos(nome, preco, quantidade);
                lista.add(produtos);
              
                // Criação da pasta para o armazenamento o sumário
                boolean folder = new File(path + "out").mkdir();
                String precoTotal = String.valueOf(produtos.valorTotal());  
                
                // Realiza a escritura do sumário
                try (BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\eduar\\Documents\\PastaProduto\\out\\sumario.csv"))) {
                	for (Produtos linhas : lista) {
                		bw.write(linhas.getNome() + "," + String.format("%.2f", linhas.valorTotal()));
                		bw.newLine();
                	}
                }
                catch (IOException e) {
                	e.printStackTrace();
                }
                
                // Realiza a leitura da próxima linha
                linha = br.readLine();
            }
        }
        catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());
        }
	}
}
