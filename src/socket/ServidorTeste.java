/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package socket;

import java.io.*;
import java.net.*;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Daniel Macedo
 */
public class ServidorTeste extends Thread {

    DataInputStream in;
    DataOutputStream out;
    Socket serverSocket;
    String data;

    public ServidorTeste(Socket serverSocket) {
        try {
            this.serverSocket = serverSocket;
            in = new DataInputStream(serverSocket.getInputStream());
            out = new DataOutputStream(serverSocket.getOutputStream());
            this.start();
        } catch (IOException e) {
            System.out.println("Connection:" + e.getMessage() + " Em outras palavras, ¨&%*¨%&¨$");
        }
    }

    public void run() {
        try {
            try {
                sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
            }

            data = in.readUTF();// read a line of data from the stream
            System.out.println("Received: " + data);
            out.writeUTF(data);
        } catch (EOFException e) {
            System.out.println("EOF:" + e.getMessage());
        } catch (IOException e) {
            System.out.println("readline:" + e.getMessage());
        } finally {
            if (data.equals("SAIR")) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    System.out.println("Servidor: Close falhou");
                }
            }
        }


    }

}
