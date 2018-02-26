package socket;

import gui.Processamento;
import gui.gridPanel;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JTextField;
import org.openide.util.Exceptions;

/**
 *
 * @author Daniel Macedo
 */
public class ServidorSocketTest extends Thread {

    private int serverPort;
    private ServerSocket server;
    private Socket socket;
    private InputStream entrada;
    private OutputStream saida;
    private BufferedReader read;
    private JTextField txt_write;
    private boolean pleaseWait = false;
    private Processamento proc;

    public ServidorSocketTest(int port, JTextField txt_write) {
        this.serverPort = port;
        this.txt_write = txt_write;
        try {
            server = new ServerSocket(this.serverPort);
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
    }

    public void startProcessamento(gridPanel field, gridPanel enemyField) {
        proc = new Processamento(field, enemyField);
//        System.out.println("criou processamento");
    }

    public void write(String send) {
        PrintStream escrita = new PrintStream(saida);
//        proc.play(send);
        escrita.println(send);
    }

    public void pauseThread() {
        synchronized (this) {
//        thread.pleaseWait = false;
            this.pleaseWait = true;
//            System.out.println("resu,eThreqd");
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
//            System.out.println("Servidor:1");
            synchronized (this) {
                String res = "";
                if (socket == null) {
                    while (pleaseWait) {
                        try {
                            wait();
                        } catch (InterruptedException ex) {
                            Exceptions.printStackTrace(ex);
                        }
                    }
                    try {
                        socket = server.accept();
                        entrada = socket.getInputStream();
                        saida = socket.getOutputStream();
                    } catch (IOException ex) {
                        Exceptions.printStackTrace(ex);
                    }
                } else {
//                    System.out.println("Servidor:2");
                    res = ".";
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
                        System.out.println("tentativa de enviar "+send);
                        res = proc.preWrite(send);
                        System.out.println("resultado: "+res);
                        txt_write.setText(res);
                    }
                    txt_write.setEnabled(false);
                    txt_write.setText("Aguardando...");
                    write(send);
                    res = "";
                    try {
                        read = new BufferedReader(new InputStreamReader(entrada));
                        //                    System.out.println("Cliente:4");
                        res = read.readLine();
                        //                    System.out.println("Cliente:5");
                        txt_write.setText(res);
//                        System.out.println("res: " + res);
                        //                    txt_write.setEnabled(true);
                        proc.play(send, res);
                    } catch (IOException ex) {
                        Exceptions.printStackTrace(ex);
                    }
//                    proc.play(send);
//                    System.out.println("Servidor:3");
                }
                res = "";
                try {
                    read = new BufferedReader(new InputStreamReader(entrada));
//                    System.out.println("Servidor:4");
                    res = read.readLine();
                    txt_write.setText(res);
//                    System.out.println("res: " + res);
                    write(proc.confere(res));
//                    System.out.println("Servidor:5");
                    txt_write.setEnabled(true);
                } catch (IOException ex) {
                    Exceptions.printStackTrace(ex);
                }
//                System.out.println("Servidor:6");
                pauseThread();
            }
        }
    }
}
