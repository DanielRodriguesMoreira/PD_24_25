package ex_10_files_incomplete;

import java.io.*;
import java.net.*;

public class Client {
    public static final int MAX_SIZE = 4000;
    public static final int TIMEOUT = 10000;

    public static void main(String[] args) {
        File localDirectory;
        String fileName, localFilePath = null;
        InetAddress serverAddr;
        int serverPort;
        InputStream in;
        byte[] fileChunk = new byte[MAX_SIZE];
        int nbytes;
        int contador = 0;

        if (args.length != 4) {
            System.out.println("Sintaxe: java Cliente serverAddress serverPort fileToGet localDirectory");
            return;
        }

        fileName = args[2].trim();
        localDirectory = new File(args[3].trim());

        if (!localDirectory.exists()) {
            System.out.println("A directoria " + localDirectory + " nao existe!");
            return;
        }
        if (!localDirectory.isDirectory()) {
            System.out.println("O caminho " + localDirectory + " nao se refere a uma directoria!");
            return;
        }
        if (!localDirectory.canWrite()) {
            System.out.println("Sem permissoes de escrita na directoria " + localDirectory);
            return;
        }

        try {
            localFilePath = localDirectory.getCanonicalPath() + File.separator + fileName;

            try (FileOutputStream localFileOutputStream = new FileOutputStream(localFilePath)) {
                System.out.println("Ficheiro " + localFilePath + " criado.");

                try {
                    serverAddr = InetAddress.getByName(args[0]);
                    serverPort = Integer.parseInt(args[1]);

                    try (Socket socketToServer = new Socket(serverAddr, serverPort)) {
                        socketToServer.setSoTimeout(TIMEOUT);

                        // a)
                        // [PT] Serializar o nome no ficheiro
                        // [EN] Serialize the filename
                        ...

                        in = socketToServer.getInputStream();
                        while ((nbytes = in.read(fileChunk)) > 0) {
                            System.out.println("Recebido o bloco n. " + ++contador + " com " + nbytes + " bytes.");
                            localFileOutputStream.write(fileChunk, 0, nbytes);
                        }

                        System.out.println("Transferencia concluida.");
                    } catch (NumberFormatException e) {
                        System.out.println("O porto do servidor deve ser um inteiro positivo:\n\t" + e);
                    } catch (SocketTimeoutException e) {
                        System.out.println("Não foi recebida qualquer bloco adicional, podendo a transferencia estar incompleta:\n\t" + e);
                    } catch (SocketException e) {
                        System.out.println("Ocorreu um erro ao nível do socket TCP:\n\t" + e);
                    } catch (IOException e) {
                        System.out.println("Ocorreu um erro no acesso ao socket ou ao ficheiro local " + localFilePath + ":\n\t" + e);
                    }
                } catch (UnknownHostException e) {
                    System.out.println("Destino desconhecido:\n\t" + e);
                }
            }
        } catch (IOException e) {
            if (localFilePath == null) {
                System.out.println("Ocorreu a excepcao {" + e + "} ao obter o caminho canonico para o ficheiro local!");
            } else {
                System.out.println("Ocorreu a excepcao {" + e + "} ao tentar criar o ficheiro " + localFilePath + "!");
            }
        }

    }
}
