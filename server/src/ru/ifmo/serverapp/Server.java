package ru.ifmo.serverapp;

import ru.ifmo.lib.Commands;
import ru.ifmo.lib.Connection;
import ru.ifmo.lib.SimpleMessage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;

public class Server {
    private int port;
    private Connection connection;
    private HashSet<String> clients;

    public Server(int port) {
        this.port = port;
        clients = new HashSet<>();
    }

    public void start(){

        try (ServerSocket serverSocket = new ServerSocket(port)){
            System.out.println("Сервер запущен");
            while (true) {
                Socket newClient = serverSocket.accept();
                connection = new Connection(newClient);
                SimpleMessage message = connection.readMessage();
                clients.add(message.getSender());
                handleMessage(message);
            }
        } catch (IOException e) {
            System.out.println("Ошибка сервера");
        } catch (ClassNotFoundException e) {
            System.out.println("Ошибка чтения сообщения");
        }
    }

    private void  handleMessage(SimpleMessage message) throws IOException {
        System.out.println(message);
        if (message.getText().equals(Commands.HELP)) {
            connection.sendMessage(SimpleMessage.getMessage("сервер", getCommands()));
        } else if (message.getText().equals(Commands.COUNT)) {
            connection.sendMessage(SimpleMessage.getMessage("сервер", getClientsCount()));
        } else {
            connection.sendMessage(SimpleMessage.getMessage("сервер", "Команды не существует"));
        }
    }

    private String getClientsCount(){
        return "Количество клиентов " + clients.size();
    }

    private String getCommands(){
        return """
                help - список доступных команд,
                count - количество подключений
                """;
    }

}