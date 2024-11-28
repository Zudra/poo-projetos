package com.fag.infra.console;

import java.util.Scanner;

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

        return inpuScanner.nextInt();
    }

    @Override
    public Integer showHomeMenu(String userName) {
        System.out.println("Olá " + userName + "! O que deseja fazer hoje?");
        System.out.println("[1] Gerar QR Code PIX");
        System.out.println("[2] Consultar boleto");
        System.out.println("[3] Realizar pagamento boleto");

        return inpuScanner.nextInt();
    }

    @Override
    public com.fag.domain.dto.LoginDTO getLoginData() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite seu documento:");
        String document = scanner.nextLine();

        System.out.println("Digite sua senha:");
        String password = scanner.nextLine();

        LoginDTO loginData = new LoginDTO(document, password);

        scanner.close();
        return loginData;
    }

    @Override
    public UserAccountDTO getCreateUserData() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite seu documento:");
        String document = scanner.nextLine();

        System.out.println("Digite seu nome:");
        String name = scanner.nextLine();

        System.out.println("Digite seu email:");
        String email = scanner.nextLine();

        System.out.println("Digite sua conta:");
        String accountNumber = scanner.nextLine();

        UserAccountDTO userData = new UserAccountDTO(document, name, email, accountNumber);

        scanner.close();
        return userData;
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