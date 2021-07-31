import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    static ServerSocket serverSocket;
    static int N = 0;

    public static void main(String[] args) throws IOException {

        serverSocket = new ServerSocket(2048);
        while (true){
            Socket socket = serverSocket.accept();
            Client client = new Client(socket);
            client.start();
        }
    }

    static class Client extends Thread{
        Socket socket;
        DataInputStream dataInputStream;
        DataOutputStream dataOutputStream;

        Client(Socket sockeT) throws IOException {
            this.socket = sockeT;
            this.dataInputStream = new DataInputStream(socket.getInputStream());
            this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
        }

        @Override
        public void run() {
            System.out.println(++N);
        }
    }
}


