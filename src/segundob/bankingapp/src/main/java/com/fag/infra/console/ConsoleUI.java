package com.fag.infra.console;

import java.util.Scanner;

import com.fag.domain.dto.UserAccountDTO;
import com.fag.domain.repositories.IUserInterface;

public class ConsoleUI implements IUserInterface {

    private Scanner inpuScanner = new Scanner(System.in);

    @Override
    public Integer showInitialScreenMenu() {
        System.out.println("Bem vindo ao SandrolaxBank!");
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
        
        return null;
    }

    @Override
    public UserAccountDTO getCreateUserData() {
        //receber dados de criação de conta
        return null;
    }

    @Override
    public void showErrorMsg(String msg) {
        //Apresentar mensagem de erro
    }
    
    @Override
    public void showExitMessage() {
        //Apresentar mensagem ao selecionar sair da aplicação
    }

}