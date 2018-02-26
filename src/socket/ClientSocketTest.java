/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package socket;

import gui.Processamento;
import gui.gridPanel;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.JTextField;
import org.openide.util.Exceptions;

/**
 *
 * @author Daniel Macedo
 */
public class ClientSocketTest extends Thread {

    boolean sair = false;
    private String ip;
    private int serverPort;
    private JTextField txt_write;
    private Socket socket;
    private OutputStream saida;
    private InputStream entrada;
    private BufferedReader read;
    private boolean pleaseWait = true;
    private Processamento proc;

    public ClientSocketTest(String ip, int port, JTextField txt_write) {
        this.ip = ip;
        this.serverPort = port;
        this.txt_write = txt_write;
        try {
            this.socket = new Socket(this.ip, this.serverPort);
            entrada = socket.getInputStream();
            saida = socket.getOutputStream();
        } catch (UnknownHostException ex) {
            Exceptions.printStackTrace(ex);
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
    }

    public void startProcessamento(gridPanel field, gridPanel enemyField) {
        proc = new Processamento(field, enemyField);
    }

    public void write(String send) {
        PrintStream escrita = new PrintStream(saida);
//        proc.play(send);
        escrita.println(send);
//        System.out.println(this.pleaseWait);
    }

    public void pauseThread() {
        synchronized (this) {
            this.pleaseWait = true;
            this.notify();
        }
    }

    public void resumeThread() {

        synchronized (this) {
            this.pleaseWait = false;
            this.notify();
        }
    }

    public void run() {
        while (true) {
//            System.out.println("Cliente:1");
            synchronized (this) {
                String res = ".";
                String send = "";
                while (!res.equals(send)) {
                    pauseThread();
                    while (pleaseWait) {
                        try {
                            wait();
                        } catch (InterruptedException ex) {
                            Exceptions.printStackTrace(ex);
                        }
                    }
                    send = txt_write.getText();
                    res = proc.preWrite(send);
                    txt_write.setText(res);
                }
                txt_write.setEnabled(false);
                txt_write.setText("Aguardando...");
                write(send);
//                System.out.println("Cliente:3");
                res = "";
                try {
                    read = new BufferedReader(new InputStreamReader(entrada));
//                    System.out.println("Cliente:4");
                    System.out.println("Esperando confirmação...");
                    res = read.readLine();
                    System.out.println("resposta: " + res);
//                    System.out.println("Cliente:5");
                    txt_write.setText(res);
                    System.out.println("res: " + res);
//                    txt_write.setEnabled(true);
                    proc.play(send, res);
                } catch (IOException ex) {
                    Exceptions.printStackTrace(ex);
                }
                res = "";
                try {
                    read = new BufferedReader(new InputStreamReader(entrada));
//                    System.out.println("Cliente:4");
                    res = read.readLine();
//                    System.out.println("Cliente:5");
                    txt_write.setText(res);
                    System.out.println("res: " + res);
                    write(proc.confere(res));
                    txt_write.setEnabled(true);
//                    proc.play(send,res);
                } catch (IOException ex) {
                    Exceptions.printStackTrace(ex);
                }
//                if ()
//                System.out.println("Cliente:6");
                pauseThread();
            }
        }
    }
}
