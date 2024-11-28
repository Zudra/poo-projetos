package com.fag.infra.console;

import java.util.Map;
import java.util.Scanner;


import com.fag.domain.dto.BankslipDTO;
import com.fag.domain.dto.LoginDTO;
import com.fag.domain.dto.UserAccountDTO;
import com.fag.domain.repositories.IUserInterface;

public class ConsoleUI implements IUserInterface {

    private Scanner inpuScanner = new Scanner(System.in);

    @Override
    public Integer showInitialScreenMenu() {
        System.out.println("Bem vindo ao Zudra Bank!");
        System.out.println("[1] Realizar login");
        System.out.println("[2] Criar conta");
        System.out.println("[3] Sair");

        int option = inpuScanner.nextInt();

        inpuScanner.nextLine();

        return option;
    }

    @Override
    public Integer showHomeMenu(String userName) {
        System.out.println("Olá " + userName + "! O que deseja fazer hoje?");
        System.out.println("[1] Gerar QR Code PIX");
        System.out.println("[2] Consultar boleto");
        System.out.println("[3] Realizar pagamento boleto");

        int option = inpuScanner.nextInt();

        inpuScanner.nextLine();

        return option;
    }

    @Override
    public com.fag.domain.dto.LoginDTO getLoginData() {

        System.out.println("Digite seu documento:");
        String document = inpuScanner.nextLine();

        System.out.println("Digite sua senha:");
        String password = inpuScanner.nextLine();

        LoginDTO loginData = new LoginDTO(document, password);

        return loginData;
    }

    @Override
    public UserAccountDTO getCreateUserData() {


        System.out.println("Digite seu documento:");
        String document = inpuScanner.nextLine();

        System.out.println("Digite seu nome:");
        String name = inpuScanner.nextLine();

        System.out.println("Digite seu email:");
        String email = inpuScanner.nextLine();

        System.out.println("Digite sua senha:");
        String password = inpuScanner.nextLine();

        System.out.println("Digite sua conta:");
        String accountNumber = inpuScanner.nextLine();

        UserAccountDTO userData = new UserAccountDTO(document, name, email, password, accountNumber);

        return userData;
    }

    @Override
    public String getBarcode() {
        System.out.println("Insira o código de barras:");
        String barcode = inpuScanner.nextLine();

        return barcode;
    }

    @Override
    public BankslipDTO getPaymentBankslipInfo() {
        BankslipDTO bankslipDTO = new BankslipDTO();

        System.out.println("Insira o código de barras:");
        String barcode = inpuScanner.nextLine();

        System.out.println("Insira o identificador de pagamento:");
        String id = inpuScanner.nextLine();

        bankslipDTO.setBarcode(barcode);
        bankslipDTO.setTransactionId(id);

        return bankslipDTO;
    }

    @Override
    public void showBankslipData(String recipientFinal, String dueDate, String value) { 
        System.out.println("Dados do boleto: ");
        System.out.println("Beneficiário: " + recipientFinal);
        System.out.println("Vencimento: " + dueDate);
        System.out.println("Valor: " + value);
        return;
    }

    @Override
    public Double getPixData() {
        System.out.println("Insira valor do PIX:");
        Double amount = inpuScanner.nextDouble();

        return amount;
    }

    @Override
    public void showPixData(String qrCode) {
    System.out.println("Dados do PIX: ");
    }

    @Override
    public void showErrorMsg(String msg) {
        System.out.println(msg);
        return;
    }
    
    @Override
    public void showExitMessage() {
        System.out.println("Obrigado por utilizar o Zudra Bank!");
        return;
    }

}