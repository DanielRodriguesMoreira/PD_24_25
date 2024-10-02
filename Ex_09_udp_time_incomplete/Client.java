package ex_09_udp_time_incomplete;

import java.io.IOException;
import java.net.*;
import java.util.Calendar;

public class Client {
    public static final int MAX_SIZE = 10000;
    public static final String TIME_REQUEST = "TIME";
    public static final int TIMEOUT = 10;

    public static void main(String[] args) {
        InetAddress serverAddr;
        int serverPort;
        DatagramPacket packet;
        Calendar response;

        if (args.length != 2) {
            System.out.println("Sintaxe: java Client serverAddress serverUdpPort");
            return;
        }

        try(DatagramSocket socket = new DatagramSocket()) {

            serverAddr = InetAddress.getByName(args[0]);
            serverPort = Integer.parseInt(args[1]);

            socket.setSoTimeout(TIMEOUT * 1000);

            // a)
            // [PT] Serializar o objecto do tipo String TIME_REQUEST para um array de bytes
            // [EN] Serialize the object of type String TIME_REQUEST into a byte array
            ...

            // b)
            // [PT] Construir o packet com o resultado da serialização
            // [EN] Build the packet with the result from the serialization
            packet = new DatagramPacket(..., ..., serverAddr, serverPort);

            socket.send(packet);

            packet = new DatagramPacket(new byte[MAX_SIZE], MAX_SIZE);

            socket.receive(packet);

            // c)
            // [PT] Deserializar os array de bytes recebidos para um objeto do tipo Time
            // [EN] Deserialize the received byte array into a Calendar object
            ...
            response = ...

            System.out.println("Resposta: " + response.toString());

        } catch (UnknownHostException e) {
            System.out.println("Destino desconhecido:\n\t" + e);
        } catch (NumberFormatException e) {
            System.out.println("O porto do servidor deve ser um inteiro positivo.");
        } catch (SocketTimeoutException e) {
            System.out.println("Nao foi recebida qualquer resposta:\n\t" + e);
        } catch (SocketException e) {
            System.out.println("Ocorreu um erro ao nivel do socket UDP:\n\t" + e);
        } catch (IOException e) {
            System.out.println("Ocorreu um erro no acesso ao socket:\n\t" + e);
        } catch(ClassNotFoundException e){
            System.out.println("O objecto recebido não é do tipo esperado:\n\t"+e);
        }
    }
}
