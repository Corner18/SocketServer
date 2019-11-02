package ru.javalab.socketsapp.servers;

import ru.javalab.socketsapp.DAO.MessageDAO;
import ru.javalab.socketsapp.DAO.UserDAO;
import ru.javalab.socketsapp.entities.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MultiClientServer {
    private ServerSocket serverSocket;
    private List<ClientHandler> clients;

    public MultiClientServer() {
        clients = new ArrayList<ClientHandler>();
    }

    public void start(int port) {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        for (; ; ) /*бесконечный цикл*/ {
            try {
                ClientHandler handler =
                        new ClientHandler(serverSocket.accept());
                handler.start();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }
    }

    private class ClientHandler extends Thread {
        public User user;
        private Socket clientSocket;
        private BufferedReader reader;
        private UserDAO userDAO;
        private MessageDAO messageDAO;

        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
            clients.add(this);
            System.out.println("New client!");
        }

        @Override
        public void run() {
            System.out.println("in run");
            try {
                reader = new BufferedReader(
                        new InputStreamReader(
                                clientSocket.getInputStream()));
                String line;
                check();
                while ((line = reader.readLine()) != null) {
                    LocalDateTime dateTime = LocalDateTime.now();
                    System.out.println(line);
                    for (ClientHandler client : clients) {
                        PrintWriter writer = new PrintWriter(
                                client.clientSocket.getOutputStream(), true);
                        writer.println(line);
                        messageDAO.save(this.user.getId() , dateTime , line );


                    }
                }
                reader.close();
                clientSocket.close();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }

        private void check() {
            try {
                PrintWriter writer = new PrintWriter(this.clientSocket.getOutputStream(), true);
                login(writer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

            private void login(PrintWriter writer) throws IOException {
            writer.println("Авторизация");

            String login;
            String password;

            writer.println("Введите логин");
            login = this.reader.readLine();
            User user = userDAO.get(login);
            if (user.getId() != 0) {
                writer.println("Введите пароль");
                password = this.reader.readLine();
                if (user.getPassword().equals(password)) {
                    this.user = user;
                    clients.add(this);

                }

            }
        }


    }


}
