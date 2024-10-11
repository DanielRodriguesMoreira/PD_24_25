package ex_10_time;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Calendar;

class ProcessClientThread implements Runnable {
    Socket clientSocket;

    public ProcessClientThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        String request;

        try (ObjectInputStream oin = new ObjectInputStream(clientSocket.getInputStream());
             ObjectOutputStream oout = new ObjectOutputStream(clientSocket.getOutputStream())) {

            // a)
            // [PT] Deserializar o objecto recebido
            // [EN] Deserialize the received object
            request = (String) oin.readObject();
            System.out.println("Recebido \"" + request + "\" de " + clientSocket.getInetAddress().getHostAddress() + ":" + clientSocket.getPort());

            // b)
            // [PT] Sair da thread se o pedido recebido for diferente do valor dado pela variável Server.TIME_REQUEST
            // [EN] Exit the thread if the request received is different from the value given by the Server.TIME_REQUEST variable
            if (!request.equalsIgnoreCase(Server.TIME_REQUEST)) {
                return;
            }

            // c)
            // [PT] Serializar o objecto do tipo Time
            // [EN] Serialize the object of type Time
            Calendar calendar = Calendar.getInstance();
            Time time = new Time(calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), calendar.get(Calendar.SECOND));
            oout.writeObject(time);
            oout.flush();
        } catch(IOException | ClassNotFoundException e) {
            System.out.println("Problema na comunicacao com o cliente " + clientSocket.getInetAddress().getHostAddress() + ":" + clientSocket.getPort() + "\n\t" + e);
        }
    }
}

public class Server {
    public static final String TIME_REQUEST = "TIME";

    public static void main(String[] args) {
        ServerSocket serverSocket;
        int listeningPort;

        if (args.length != 1) {
            System.out.println("Sintaxe: java Servidor listeningPort");
            return;
        }

        try {
            // d)
            // [PT] Popular as variáveis com os valores dos args
            // [EN] Populate variables with the arg values
            listeningPort = Integer.parseInt(args[0]);
            serverSocket = new ServerSocket(listeningPort);

            System.out.println("TCP Time Server iniciado...");

            while (true) {
                // e)
                // [PT] Escuta uma ligação a ser feita ao socket e aceita-a
                // [EN] Listens for a connection to be made to this socket and accepts it
                Socket clientSocket = serverSocket.accept();

                // f)
                // [PT] Criar e iniciar a thread que vai processar/atender cada cliente
                // [EN] Create and start the thread that will process/attend each client
                Runnable clientThread = new ProcessClientThread(clientSocket);
                Thread t = new Thread(clientThread);
                t.start();
            }
        } catch (NumberFormatException e) {
            System.out.println("O porto de escuta deve ser um inteiro positivo.");
        } catch (SocketException e) {
            System.out.println("Ocorreu um erro ao nivel do serverSocket TCP:\n\t" + e);
        } catch (IOException e) {
            System.out.println("Ocorreu um erro no acesso ao serverSocket:\n\t" + e);
        }
    }
}
