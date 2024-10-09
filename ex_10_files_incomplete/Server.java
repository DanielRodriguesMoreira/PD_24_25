package ex_10_files_incomplete;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;

class ProcessClientThread extends Thread {
    Socket clientSocket;
    File localDirectory;

    public ProcessClientThread(Socket clientSocket, File localDirectory) {
        this.clientSocket = clientSocket;
        this.localDirectory = localDirectory;
    }

    @Override
    public void run() {
        String requestedFileName, requestedCanonicalFilePath = null;
        byte[] fileChunk = new byte[Server.MAX_SIZE];
        int nbytes;

        try {
            clientSocket.setSoTimeout(Server.TIMEOUT);

            // a)
            // [PT] Deserializar o objecto recebido
            // [EN] Deserialize the received object
            ...
            System.out.println("Recebido pedido para \"" + requestedFileName + "\" de " + clientSocket.getInetAddress().getHostName() + ":" + clientSocket.getPort());

            requestedCanonicalFilePath = new File(localDirectory + File.separator + requestedFileName).getCanonicalPath();

            if (!requestedCanonicalFilePath.startsWith(localDirectory.getCanonicalPath() + File.separator)) {
                System.out.println("Nao e' permitido aceder ao ficheiro " + requestedCanonicalFilePath + "!");
                System.out.println("A directoria de base nao corresponde a " + localDirectory.getCanonicalPath() + "!");
                return;
            }

            try (FileInputStream requestedFileInputStream = new FileInputStream(requestedCanonicalFilePath)) {
                System.out.println("Ficheiro " + requestedCanonicalFilePath + " aberto para leitura.");

                OutputStream out = clientSocket.getOutputStream();

                do {
                    nbytes = requestedFileInputStream.read(fileChunk);
                    if (nbytes != -1) {
                        // b)
                        // [PT] Escrever o fileChunk
                        // [EN] Write the fileChunk
                        ...
                    }
                } while (nbytes > 0);

                System.out.println("Transferencia concluida");
            } finally {
                clientSocket.close();
            }

        } catch (SocketTimeoutException ex) {
            System.out.println("O cliente atual nao enviou qualquer nome de ficheiro (timeout)");
        } catch (FileNotFoundException e) {
            System.out.println("Ocorreu a excepcao {" + e + "} ao tentar abrir o ficheiro " + requestedCanonicalFilePath + "!");
        } catch (IOException ex) {
            System.out.println("Problem de I/O no atendimento ao cliente atual: " + ex);
        } catch(ClassNotFoundException e){
            System.out.println("O objecto recebido não é do tipo esperado:\n\t"+e);
        }
    }
}

public class Server {
    public static final int MAX_SIZE = 4000;
    public static final int TIMEOUT = 10000;

    public static void main(String[] args) {
        File localDirectory;
        int listeningPort;

        if (args.length != 2) {
            System.out.println("Sintaxe: java Servidor listeningPort localRootDirectory");
            return;
        }

        localDirectory = new File(args[1].trim());

        if (!localDirectory.exists()) {
            System.out.println("A directoria " + localDirectory + " nao existe!");
            return;
        }

        if (!localDirectory.isDirectory()) {
            System.out.println("O caminho " + localDirectory + " nao se refere a uma directoria!");
            return;
        }

        if (!localDirectory.canRead()) {
            System.out.println("Sem permissoes de leitura na directoria " + localDirectory + "!");
            return;
        }

        listeningPort = Integer.parseInt(args[0]);

        try (ServerSocket serverSocket = new ServerSocket(listeningPort)) {
            while (true) {
                // c)
                // [PT] Criar e iniciar a thread que vai processar/atender cada cliente
                // [EN] Create and start the thread that will process/attend each client
                ...
            }
        } catch (NumberFormatException e) {
            System.out.println("O porto de escuta deve ser um inteiro positivo:\n\t" + e);
        } catch (SocketException e) {
            System.out.println("Ocorreu uma excepcao ao nivel do socket UDP:\n\t" + e);
        } catch (IOException e) {
            System.out.println("Ocorreu a excepcao de E/S: \n\t" + e);
        }
    }
}
