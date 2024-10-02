package ex_09_tcp_incomplete;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class Server {
    public static final String TIME_REQUEST = "TIME";

    public static void main(String[] args) {
        int listeningPort;
        String receivedMsg;

        if (args.length != 1) {
            System.out.println("Sintaxe: java Server listeningPort");
            return;
        }

        listeningPort = Integer.parseInt(args[0]);

        try (ServerSocket serverSocket = new ServerSocket(listeningPort)) {

            System.out.println("UDP Time Server iniciado...");

            while (true) {
                try (Socket clientSocket = serverSocket.accept()) {
                    // a)
                    // [PT] Deserializar o objecto do tipo String recebido no InputStream disponibilizado pelo socket
                    // [EN] Deserialize the object of type String received on the InputStream available on the socket
                    ...

                    System.out.println("Recebido \"" + receivedMsg + "\" de " + clientSocket.getInetAddress().getHostAddress() + ":" + clientSocket.getPort());

                    if (!receivedMsg.equalsIgnoreCase(TIME_REQUEST)) {
                        continue;
                    }

                    // b)
                    // [PT] Serializar o objecto do tipo Calendar para o OutputStream disponibilizado pelo socket
                    // [EN] Serialize the object of type Calendar to the OutputStream available on the socket
                    ...
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("O porto de escuta deve ser um inteiro positivo.");
        } catch (SocketException e) {
            System.out.println("Ocorreu um erro ao nivel do serverSocket TCP:\n\t" + e);
        } catch (IOException e) {
            System.out.println("Ocorreu um erro no acesso ao serverSocket:\n\t" + e);
        } catch(ClassNotFoundException e){
            System.out.println("O objecto recebido não é do tipo esperado:\n\t"+e);
        }
    }
}
