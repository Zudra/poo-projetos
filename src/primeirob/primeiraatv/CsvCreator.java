package primeirob.primeiraatv;

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CsvCreator {
    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);
        Path caminho = Paths.get("src/primeirob/primeiraatv/arquivo.csv");
        int colunas;

        do {
            System.out.println("Digite o número de colunas: (min 3)");
            colunas = leitor.nextInt();
            leitor.nextLine();
        } while (colunas < 3);Path.of("arquivo.csv");

        String[] header = new String[colunas];
        for (int i = 0; i < colunas; i++) {
            System.out.println("Digite o nome da coluna " + (i + 1) + ":");
            header[i] = leitor.nextLine();
        }

        List<String[]> linhas = new ArrayList<>();
        String continuar;
        do {
            String[] linha = new String[colunas];
            for (int i = 0; i < colunas; i++) {
                System.out.println("Digite o valor da coluna " + header[i] + ":");
                linha[i] = leitor.nextLine();
            }
            linhas.add(linha);

            System.out.println("Deseja adicionar mais uma linha? (s/n)");
            continuar = leitor.nextLine();
        } while (continuar.equalsIgnoreCase("s"));

        boolean ArquivoExiste = !Files.exists(caminho);
        try(BufferedWriter writer = Files.newBufferedWriter(caminho, StandardOpenOption.CREATE, StandardOpenOption.APPEND)){
            if(ArquivoExiste){
                writer.write(String.join(";", header));
                writer.newLine();
            }

            for (String[] linha : linhas) {
                writer.write(String.join(";", linha));
                writer.newLine();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
