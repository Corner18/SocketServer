package ru.javalab.socketsapp.servers;

public class ChatServerStartMain {
    public static void main(String[] args) {
        MultiClientServer multiClientServer =
                new MultiClientServer();
        multiClientServer.start(7000);
    }
}
