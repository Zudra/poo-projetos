package com.fag.infra.celcoin;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.StandardCharsets;

import com.fag.domain.dto.BankslipDTO;
import com.fag.domain.repositories.IBassRepository;
import com.fag.infra.utils.JsonUtils;

public class CelcoinBassRepository implements IBassRepository {
    public static String genToken() throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        URI uri = new URI("https://sandbox.openfinance.celcoin.dev/v5/token");
        String params = "client_id=" + URLEncoder.encode("41b44ab9a56440.teste.celcoinapi.v5", StandardCharsets.UTF_8)
                + "&grant_type=" + URLEncoder.encode("client_credentials", StandardCharsets.UTF_8)
                + "&client_secret=" + URLEncoder.encode(
                        "e9d15cde33024c1494de7480e69b7a18c09d7cd25a8446839b3be82a56a044a3", StandardCharsets.UTF_8);

        HttpRequest request = HttpRequest.newBuilder(uri)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(BodyPublishers.ofString(params))
                .build();

        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

        return JsonUtils.getField(response.body(), "access_token");
    }

    @Override
    public String consultarBoleto(String linhaDigitavel) {
        try {
            System.out.println("Consultando boleto " + linhaDigitavel + " na celcoin");
            System.out.println(genToken());

            HttpClient client = HttpClient.newHttpClient();
            URI uri = new URI("https://sandbox.openfinance.celcoin.dev/v5/transactions/billpayments/authorize");
            String params = "{\"barCode\":{\"type\":0,\"digitable\":\""+linhaDigitavel+"\"}}";

            HttpRequest request = HttpRequest.newBuilder(uri)
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + genToken())
                    .POST(BodyPublishers.ofString(params))
                    .build();

            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

            return response.body();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao consultar boleto" + e.getMessage());
        }
    }

    @Override
    public String pagarBoleto(String document, BankslipDTO dadosBoletoConsultado, Double originalValue, 
                                Double valueWithDiscount, Double valueWithAdditional) {
        try {
            System.out.println("Pagando boleto " + dadosBoletoConsultado.getBarcode() + " na celcoin");

            HttpClient client = HttpClient.newHttpClient();
            URI uri = new URI("https://sandbox.openfinance.celcoin.dev/v5/transactions/billpayments");
            String params = "{\"cpfCnpj\":"+document+",\"billData\":{\"originalValue\":\""+originalValue+"\",\"valueWithDiscount\":\""+valueWithDiscount+"\",\"valueWithAdditional\":\""+valueWithAdditional+"\"},\"barCode\":{\"type\":0,\"digitable\":\""+dadosBoletoConsultado.getBarcode()+"\"}}";

            HttpRequest request = HttpRequest.newBuilder(uri)
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + genToken())
                    .POST(BodyPublishers.ofString(params))
                    .build();

            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

            return response.body();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao pagar boleto" + e.getMessage());
        }
    }

    @Override
    public String gerarQrCode(String document, Double valorPix) {

        try {
            System.out.println("Gerando QR Code Pix " + valorPix + " na celcoin");

            HttpClient client = HttpClient.newHttpClient();
            URI uri = new URI("https://sandbox.openfinance.celcoin.dev/pix-indirect/v1/pix/v1/brcode/static");
            String params = "{\"key\":\""+document+"\",\"amount\":"+valorPix+"}";

            HttpRequest request = HttpRequest.newBuilder(uri)
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + genToken())
                    .POST(BodyPublishers.ofString(params))
                    .build();

            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

            return response.body();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao criar qr code pix" + e.getMessage());
        }
    }
}
