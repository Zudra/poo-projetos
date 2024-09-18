package primeirob.segundaatv;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

public class Conversor {

    private static final String[] MOEDAS = {"USD", "EUR", "JPY", "GBP", "BRL"};
    private static final double[] TAXAS_DE_CAMBIO = {1.0, 0.85, 110.0, 0.75, 5.4};
    private static final String[] SIMBOLOS_ROMANOS = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
    private static final int[] VALORES_ROMANOS = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            exibirMenu();
            int escolha = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer

            switch (escolha) {
                case 1:
                    converterInteiroParaRomano(scanner);
                    break;
                case 2:
                    converterMoeda(scanner);
                    break;
                case 3:
                    System.out.println("Saindo do programa...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida. Por favor, escolha novamente.");
            }
        }
    }

    private static void exibirMenu() {
        System.out.println("\n===== Sistema Conversor =====");
        System.out.println("1. Converter Inteiro para Romano");
        System.out.println("2. Converter Moeda");
        System.out.println("3. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void converterInteiroParaRomano(Scanner scanner) {
        System.out.print("Digite um número inteiro (1-3999): ");
        int numero = scanner.nextInt();

        if (numero < 1 || numero > 3999) {
            System.out.println("Número inválido. O número deve estar entre 1 e 3999.");
            return;
        }

        String numeroRomano = toRoman(numero);
        System.out.println("O número " + numero + " em números romanos é: " + numeroRomano);
    }

    private static String toRoman(int numero) {
        StringBuilder romano = new StringBuilder();
        int i = 0;

        while (numero > 0) {
            while (numero >= VALORES_ROMANOS[i]) {
                numero -= VALORES_ROMANOS[i];
                romano.append(SIMBOLOS_ROMANOS[i]);
            }
            i++;
        }

        return romano.toString();
    }

    private static void converterMoeda(Scanner scanner) {
        System.out.print("Digite a moeda de origem (ex: USD, EUR, JPY, GBP, BRL): ");
        String moedaOrigem = scanner.nextLine().toUpperCase();

        System.out.print("Digite a moeda de destino (ex: USD, EUR, JPY, GBP, BRL): ");
        String moedaDestino = scanner.nextLine().toUpperCase();

        System.out.print("Digite o valor a ser convertido: ");
        double valor = scanner.nextDouble();

        try {
            double valorConvertido = convert(valor, moedaOrigem, moedaDestino);
            NumberFormat formatoBRL = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
            System.out.println(valor + " " + moedaOrigem + " em " + moedaDestino + ": " + formatoBRL.format(valorConvertido));
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static double convert(double amount, String fromCurrency, String toCurrency) {
        int fromIndex = findCurrencyIndex(fromCurrency);
        int toIndex = findCurrencyIndex(toCurrency);

        if (fromIndex == -1 || toIndex == -1) {
            throw new IllegalArgumentException("Moeda inválida.");
        }

        double valorEmUSD = amount / TAXAS_DE_CAMBIO[fromIndex];
        return valorEmUSD * TAXAS_DE_CAMBIO[toIndex];
    }

    private static int findCurrencyIndex(String currency) {
        for (int i = 0; i < MOEDAS.length; i++) {
            if (MOEDAS[i].equals(currency)) {
                return i;
            }
        }
        return -1;
    }
}