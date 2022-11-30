package ru.ifmo.clientapp;


import ru.ifmo.lib.Commands;
import ru.ifmo.lib.Connection;
import ru.ifmo.lib.SimpleMessage;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private String ip;
    private int port;
    private Scanner scanner;


    public Client(String ip, int port) {
        this.ip = ip;
        this.port = port;
        scanner = new Scanner(System.in);
    }

    public void start(){
        System.out.println("Введите имя");
        String userName = scanner.nextLine();
        String text;
        while (true) {
            System.out.println("Введите команду. exit для выхода");
            text = scanner.nextLine();
            if ("exit".equalsIgnoreCase(text)) break;
            if (text.equalsIgnoreCase(Commands.HELP) || text.equalsIgnoreCase(Commands.COUNT)) {
                sendAndPrintMessage(SimpleMessage.getMessage(userName, text));
            } else {
                System.out.println("Такой команды не существует");
            }
        }
    }

    private void sendAndPrintMessage(SimpleMessage message){
        try (Connection connection = new Connection(new Socket(ip, port))){
            connection.sendMessage(message);

            SimpleMessage fromServer = connection.readMessage();
            System.out.println("от сервера: " + fromServer.getText());
        } catch (IOException e) {
            System.out.println("Ошибка отправки / получения сообщения");
        } catch (ClassNotFoundException e) {
            System.out.println("Ошибка чтения сообщения");
        } catch (Exception e) {
            System.out.println("Ошибка соединения");
        }
    }
}