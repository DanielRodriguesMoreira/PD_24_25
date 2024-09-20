package ex_06_incomplete;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Server {
    public static final int MAX_SIZE = 4000;

    public static void main(String[] args) {

        File localDirectory;
        String requestedFileName, requestedCanonicalFilePath = null;
        FileInputStream requestedFileInputStream = null;
        DatagramSocket socket = null;
        int listeningPort;
        DatagramPacket packet;
        byte[] fileChunk = new byte[MAX_SIZE];
        int nbytes;

        // a)
        // [PT] Testar a sintaxe
        // [EN] Test the syntax
        if (args.length != 2) {
            System.out.println("Sintaxe: java Servidor listeningPort localRootDirectory");
            return;
        }

        // b)
        // [PT] Popular as variáveis com os valores dos args
        // [EN] Populate variables with the arg values
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
        // [PT] Verificar se temos acesso de leitura na directoria
        // [EN] Check if we have read access to the directory
        if (...) {
            System.out.println("Sem permissoes de leitura na directoria " + localDirectory + "!");
            return;
        }

        try {
            listeningPort = Integer.parseInt(args[0]);

            // f)
            // [PT] Criar o socket
            // [EN] Create the socket
            socket = ...

            while (true) {
                // g)
                // [PT] Criar o datagram packet
                // [EN] Create the datagram packet
                packet = ...

                // h)
                // [PT] Esperar por um datagram packet
                // [EN] Wait for a datagram packet
                ...

                requestedFileName = new String(packet.getData(), 0, packet.getLength()).trim();

                System.out.println("Recebido pedido para \"" + requestedFileName + "\" de " + packet.getAddress().getHostAddress() + ":" + packet.getPort());

                requestedCanonicalFilePath = new File(localDirectory + File.separator + requestedFileName).getCanonicalPath();
                if (!requestedCanonicalFilePath.startsWith(localDirectory.getCanonicalPath() + File.separator)) {
                    System.out.println("Nao e' permitido aceder ao ficheiro " + requestedCanonicalFilePath + "!");
                    System.out.println("A directoria de base nao corresponde a " + localDirectory.getCanonicalPath() + "!");
                    continue;
                }

                // i)
                // [PT] Abrir ficheiro para leitura
                // [EN] Open file to read
                requestedFileInputStream = new ...
                System.out.println("Ficheiro " + requestedCanonicalFilePath + " aberto para leitura.");

                do {
                    // j)
                    // [PT] Ler um array de bytes (usar a variável fileChunk)
                    // [PT] NOTA: a variável nbytes será populada com o número de bytes lido
                    // [EN] Read a byte array (using the variable fileChunk)
                    // [EN] NOTE: the nbytes variable will be populated with the number of bytes read
                    nbytes = ...

                    if (nbytes == -1) {
                        nbytes = 0;
                    }

                    // k)
                    // [PT] Atualizar o datagram packet com os dados que queremos enviar
                    // [EN] Update the datagram packet with the data that we want to send
                    packet.setData(...);
                    packet.setLength(...);

                    // l)
                    // [PT] Enviar o datagram packet
                    // [EN] Send the datagram packet
                    ...

                } while (nbytes > 0);

                System.out.println("Transferencia concluida");

                requestedFileInputStream.close();
                requestedFileInputStream = null;
            }
        } catch (NumberFormatException e) {
            System.out.println("O porto de escuta deve ser um inteiro positivo:\n\t" + e);
        } catch (SocketException e) {
            System.out.println("Ocorreu uma excepcao ao nivel do socket UDP:\n\t" + e);
        } catch (FileNotFoundException e) {   //Subclasse de IOException
            System.out.println("Ocorreu a excepcao {" + e + "} ao tentar abrir o ficheiro " + requestedCanonicalFilePath + "!");
        } catch (IOException e) {
            System.out.println("Ocorreu a excepcao de E/S: \n\t" + e);
        } finally {
            // m)
            // [PT] Libertar os recursos: socket e localFileOutputStream
            // [EN] Release resources: socket and localFileOutputStream
            ...
        } //try

    } // main
}
