package primeirob.terceiraatv;

import java.io.*;
import java.net.Socket;

public class Cliente {

    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;

    public static void main(String[] args) {
        System.out.println("Iniciando cliente do chat...");
        new Cliente("localhost", 7777);
    }

    public Cliente(String endereco, int porta) {
        try {
            socket = new Socket(endereco, porta);
            System.out.println("Conectado ao servidor! ✅");

            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream());

            startReading();
            startWriting();
        } catch (Exception ex) {
            System.out.println("Erro ao conectar ao servidor: " + ex.getMessage());
        }
    }

    public void startReading() {
        Runnable r1 = () -> {
            System.out.println("Iniciando leitura...");
            try {
                while (true) {
                    String msg = reader.readLine();
                    System.out.println("Servidor: " + msg);
                }
            } catch (Exception e) {
                System.out.println("----Conexão finalizada----");
            }
        };
        new Thread(r1).start();
    }

    public void startWriting() {
        Runnable r2 = () -> {
            System.out.println("Iniciando escrita...");
            try {
                BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
                while (!socket.isClosed()) {
                    String content = br1.readLine();
                    writer.println(content);
                    writer.flush();

                    if (content.equals("EXIT")) {
                        socket.close();
                        break;
                    }
                }
            } catch (Exception e) {
            }
        };
        new Thread(r2).start();
    }
}