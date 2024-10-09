package ex_10_time_incomplete;

import java.io.IOException;
import java.net.*;

public class Client {
    public static final String TIME_REQUEST = "TIME";
    public static final int TIMEOUT = 1000;

    public static void main(String[] args) {
        InetAddress serverAddr;
        int serverPort;
        Time response;

        if (args.length != 2) {
            System.out.println("Sintaxe: java Client serverAddress serverPort");
            return;
        }

        try {
            // a)
            // [PT] Popular as variáveis com os valores dos args
            // [EN] Populate variables with the arg values
            serverAddr = ...
            serverPort = ...

            // b)
            // [PT] Criar socket
            // [EN] Create socket
            try (...) {
                // c)
                // [PT] Configurar timeout de recepção (usar a variável TIMEOUT)
                // [EN] Setup the reception timeout (use the variable TIMEOUT)
                ...

                // d)
                // [PT] Serializar o objecto do tipo String TIME_REQUEST para o OutputStream disponibilizado pelo socket
                // [EN] Serialize the object of type String TIME_REQUEST to the OutputStream available on the socket
                ...

                // e)
                // [PT] Deserializa o objecto do tipo Time recebido no InputStream disponibilizado pelo socket
                // [EN] Deserialize the object of type Time received on the InputStream available on the socket
                ...

                System.out.println("Response: " + response.toString());
            }
        } catch (UnknownHostException e) {
            System.out.println("Destino desconhecido:\n\t" + e);
        } catch (NumberFormatException e) {
            System.out.println("O porto do servidor deve ser um inteiro positivo.");
        } catch (SocketTimeoutException e) {
            System.out.println("Nao foi recebida qualquer resposta:\n\t" + e);
        } catch (SocketException e) {
            System.out.println("Ocorreu um erro ao nivel do socket TCP:\n\t" + e);
        } catch (IOException e) {
            System.out.println("Ocorreu um erro no acesso ao socket:\n\t" + e);
        } catch(ClassNotFoundException e){
            System.out.println("O objecto recebido não é do tipo esperado:\n\t"+e);
        }
    }
}