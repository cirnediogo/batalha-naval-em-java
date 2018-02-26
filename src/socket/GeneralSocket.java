package socket;

import gui.gridPanel;
import javax.swing.JTextField;

/**
 *
 * @author DiogoCirne
 */
enum SocketType {

    CLIENT, SERVER
}

public class GeneralSocket {

    ClientSocketTest clientSocket;
    ServidorSocketTest serverSocket;
    SocketType type;
    JTextField txt_write;

    public GeneralSocket() {
    }

    public GeneralSocket(JTextField txt_write) {
        this.txt_write = txt_write;
    }

    public void makeClientSocket(String ip, int port) {
        this.clientSocket = new ClientSocketTest(ip, port, this.txt_write);
        this.type = SocketType.CLIENT;
    }

    public void makeServerSocket(int port) {
        this.serverSocket = new ServidorSocketTest(port, this.txt_write);
        this.type = SocketType.SERVER;
    }
    
    public void makeProcessamento(gridPanel field, gridPanel enemyField) {
        if (this.type.equals(SocketType.SERVER)) {
            this.serverSocket.startProcessamento(field, enemyField);
        } else {
            this.clientSocket.startProcessamento(field, enemyField);
        }
    }
    
    public void start(boolean paused) {
        if (paused) {
            pauseThread();
        } else {
            resumeThread();
        }
        if (this.type.equals(SocketType.SERVER)) {
            this.serverSocket.start();
        } else {
            this.clientSocket.start();
        }
    }

    public void pauseThread() {
        if (this.type.equals(SocketType.SERVER)) {
            this.serverSocket.pauseThread();
        } else {
            this.clientSocket.pauseThread();
        }
    }

    public void resumeThread() {
        if (this.type.equals(SocketType.SERVER)) {
            this.serverSocket.resumeThread();
        } else {
//            System.out.println("resumeClinete");
            this.clientSocket.resumeThread();
        }
    }

//    public void write() {
//        if (this.type.equals(SocketType.SERVER)) {
//            this.serverSocket.write();
//        } else {
//            this.clientSocket.write();
//        }
//    }
}
