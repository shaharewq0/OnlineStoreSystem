package Communication.socket.Echo;


import java.io.*;
import java.util.Scanner;

public class EchoClientMain {

    private static Scanner scanner;

    private static String host;
    private static int port;


    public static void main(String[] args) throws IOException {

        if (args.length == 0) {
            args = new String[]{"localhost", "8080"};
        }
        host = args[0];
        port = Integer.parseInt(args[1]);
        scanner = new Scanner(System.in);

        new Thread(surpriseCleanClient()).start();
        new Thread(surpriseCleanClient()).start();
        new Thread(surpriseCleanClient()).start();
    }


    static Runnable interactivecClient() {
        return () ->{

            String msg = "";
            String response;

            try {

                EchoClient client = new EchoClient(host, port);

                while (!Thread.currentThread().isInterrupted() && !msg.equals("bye")) {
                    System.out.println("enter message :");
                    msg = scanner.nextLine();
                    client.send(msg);

                    response = client.receive();
                    System.out.println(response);

                    client.close();

                }

            } catch (IOException e) {
                e.printStackTrace();
            }


            System.out.println("Done.");
        };
    }

    static Runnable surpriseClient() {
        return () ->{


            String response;

            try {

                EchoClient client = new EchoClient(host, port);

                client.send("surprise me!");

                response = client.receive();
                System.out.println(response);

                response = client.receive();
                System.out.println(response);

                client.close();

            } catch (IOException e) {
                e.printStackTrace();
            }


            System.out.println("Done.");
        };
    }


    static Runnable surpriseCleanClient() {
        return () ->{


            String response;

            try {

                EchoClient client = new EchoClient(host, port);

                client.send("hello!");
                response = client.receive();
                client.send("sup?");
                response = client.receive();

                int surprises = 3;
                for (int i = 1; i <=surprises ; i++) {
                    client.send("surprise me!");
                }
                for (int i = 1; i <=2*surprises ; i++) {
                    response = client.receive();
                }

                client.send("bye");
                response = client.receive();

                System.out.println("surpriseCleanClient Done.");
                client.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        };
    }

}
