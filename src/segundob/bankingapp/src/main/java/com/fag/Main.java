package com.fag;

import java.util.Scanner;

import com.fag.infra.console.ConsoleUI;
import com.fag.infra.swing.SwingUI;
import com.fag.infra.testdb.UserTestDBRepository;
import com.fag.service.Application;
import com.fag.infra.celcoin.CelcoinBassRepository;

public class Main {

    public static void main(String[] args) {

        Scanner inpuScanner = new Scanner(System.in);

        Application app = new Application();

        ConsoleUI consoleUI = new ConsoleUI();
        SwingUI swingUI = new SwingUI();
        UserTestDBRepository userRepo = new UserTestDBRepository();
        CelcoinBassRepository celcoinBassRepo = new CelcoinBassRepository();


        System.out.println("Iniciando aplicação...");
        System.out.println("Qual interface deseja utilizar?");
        System.out.println("[1] Console");
        System.out.println("[2] Swing");
        System.out.println("[3] Sair");

        int option = inpuScanner.nextInt();

        switch (option) {
            case 1:
                app.execute(consoleUI, userRepo, celcoinBassRepo);
                break;
            case 2:
                app.execute(swingUI, userRepo, celcoinBassRepo);
                break;
            case 3:
                System.out.println("Saindo do programa...");
                inpuScanner.close();
                return;
            default:
                System.out.println("Opção inválida. Por favor, escolha novamente.");
                break;
        }

        inpuScanner.close();
    }

}