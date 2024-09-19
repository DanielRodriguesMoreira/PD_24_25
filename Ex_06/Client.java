package ex_06_incomplete;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.*;

public class Client {
    public static final int MAX_SIZE = 4000;
    public static final int TIMEOUT = 5; //segundos

    public static void main(String[] args) {
        File localDirectory;
        String fileName, localFilePath = null;
        InetAddress serverAddr;
        int serverPort;
        DatagramSocket socket = null;
        DatagramPacket packet;
        FileOutputStream localFileOutputStream = null;
        int contador = 0;

        // a)
        // [PT] Testar a sintaxe
        // [EN] Test the syntax
        if (...) {
            System.out.println("Sintaxe: java Client serverAddress serverUdpPort fileToGet localDirectory");
            return;
        }

        // b)
        // [PT] Popular as variáveis com os valores dos args
        // [EN] Populate variables with the arg values
        fileName = ...
        localDirectory = ...

        // c)
        // [PT] Verificar se a directoria existe
        // [EN] Check if the directory exists
        if (...) {
            System.out.println("A directoria " + localDirectory + " nao existe!");
            return;
        }

        // d)
        // [PT] Verificar se a directoria é mesmo uma directoria
        // [EN] Check if the directory is indeed a directory
        if (...) {
            System.out.println("O caminho " + localDirectory + " nao se refere a uma directoria!");
            return;
        }

        // e)
        // [PT] Verificar se temos acesso de escrita na directoria
        // [EN] Check if we have write access to the directory
        if (...) {
            System.out.println("Sem permissoes de escrita na directoria " + localDirectory);
            return;
        }

        try {

            try {
                localFilePath = localDirectory.getCanonicalPath() + File.separator + fileName;

                // f)
                // [PT] Criar instância de objeto que irá ser usado para escrever no ficheiro
                // [EN] Create object instance that will be used to write the file
                localFileOutputStream = ...

                System.out.println("Ficheiro " + localFilePath + " criado.");
            } catch (IOException e) {
                if (localFilePath == null) {
                    System.out.println("Ocorreu a excepcao {" + e + "} ao obter o caminho canonico para o ficheiro local!");
                } else {
                    System.out.println("Ocorreu a excepcao {" + e + "} ao tentar criar o ficheiro " + localFilePath + "!");
                }
                return;
            }

            try {
                // g)
                // [PT] Popular as variáveis com os valores do endereço IP e porto do servidor
                // [EN] Populate the variables with server address IP and port values
                serverAddr = ...
                serverPort = ...

                // h)
                // [PT] Criar o socket
                // [EN] Create the socket
                socket = ...

                socket.setSoTimeout(TIMEOUT * 1000);

                // i)
                // [PT] Criar o datagram packet com os dados necessários
                // [EN] Create the datagram packet with the necessary data
                packet = new DatagramPacket(...);

                // j)
                // [PT] Enviar o datagram packet
                // [EN] Send the datagram packet
                ...

                do {
                    packet = new DatagramPacket(new byte[MAX_SIZE], MAX_SIZE);

                    // k)
                    // [PT] Esperar por um datagram packet de resposta
                    // [EN] Wait for a response datagram packet
                    ...

                    if (packet.getPort() == serverPort && packet.getAddress().equals(serverAddr)) {
                        // l)
                        // [PT] Escrever os dados recebidos para dentro do ficheiro local
                        // [EN] Write the received data into the local file
                        localFileOutputStream.write(...);

                        contador++;
                    }

                } while (packet.getLength() > 0);

                System.out.println("Transferencia concluida (numero de blocos: " + contador + ")");

            } catch (UnknownHostException e) {
                System.out.println("Destino desconhecido:\n\t" + e);
            } catch (NumberFormatException e) {
                System.out.println("O porto do servidor deve ser um inteiro positivo:\n\t" + e);
            } catch (SocketTimeoutException e) {
                System.out.println("Nao foi recebida qualquer bloco adicional, podendo a transferencia estar incompleta:\n\t" + e);
            } catch (SocketException e) {
                System.out.println("Ocorreu um erro ao nivel do socket UDP:\n\t" + e);
            } catch (IOException e) {
                System.out.println("Ocorreu um erro no acesso ao socket ou ao ficheiro local " + localFilePath + ":\n\t" + e);
            }

        } finally {
            // m)
            // [PT] Libertar os recursos: socket e localFileOutputStream
            // [EN] Release resources: socket and localFileOutputStream
            ...
        }

    }
}
