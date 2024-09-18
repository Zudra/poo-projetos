package primeirob.terceiraatv;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

class Server {
    private ServerSocket server;
    private Socket socket;

    private BufferedReader reader;
    private PrintWriter writer;

    public static void main(String[] args) {
        System.out.println("Iniciando servidor do chat...");

        new Server();
    }

    public Server() {
        try {
            server = new ServerSocket(7777);
            System.out.println("Servidor está pronto para receber conexões✅");
            System.out.println("Aguardando...");
            socket = server.accept();

            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            writer = new PrintWriter(socket.getOutputStream());

            startReading();
            startWriting();
        } catch (Exception ex) {
            System.out.println("Erro ao iniciar servidor do chat..." + ex.getMessage());
        }
    }

    public void startReading() {
        Runnable r1 = () -> {
            System.out.println("Iniciando leitura...");

            try {
                while (true) {

                    String msg = reader.readLine();
                    if (msg.equals("EXIT")) {
                        System.out.println("Client terminou o chat!!");
                        socket.close();

                        break;
                    }

                    System.out.println("Client : " + msg);

                }
            } catch (Exception e) {
                System.out.print("----Conexão finalizada----");
            }
        };

        new Thread(r1).start();
    }

    public void startWriting() {
        Runnable r2 = () -> {
            System.out.println("Iniciando escrita...");

            try {
                while (!socket.isClosed()) {

                    BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));

                    String content = br1.readLine();
                    writer.println(content);
                    writer.flush();

                    if (content.equals("EXIT")) {
                        socket.close();
                        break;
                    }

                    System.out.print("----Última mensagem----\n");
                }
            } catch (Exception e) {
            }
        };

        new Thread(r2).start();
    }

}
