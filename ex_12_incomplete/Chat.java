package ex_12_incomplete;

import java.io.*;
import java.net.*;

public class Chat extends Thread {
    public static final String LIST = "LIST";
    public static String EXIT = "EXIT";
    public static int MAX_SIZE = 1000;

    protected String username;
    protected MulticastSocket socket;
    protected boolean running;

    public Chat(String username, MulticastSocket socket) {
        this.username = username;
        this.socket = socket;
        running = true;
    }

    public void terminate() {
        running = false;
    }

    @Override
    public void run() {
        Object received;
        DatagramPacket pkt;
        Msg msg;

        if (socket == null || !running) {
            return;
        }

        try {
            while (running) {
                pkt = new DatagramPacket(new byte[MAX_SIZE], MAX_SIZE);
                socket.receive(pkt);

                // a)
                // [PT] Obter e ler o objecto recebido no packet
                // [EN] Get and read the object received on the packet
                try (...) {
                    received = ...

                    // b)
                    // [PT] Verificar se objecto recebido é do tipo Msg
                    // [EN] Check if type of received object is Msg
                    if (...) {
                        msg = (Msg) received;

                        // c)
                        // [PT] Verificar se a mensagem recebida é "LIST"
                        // [EN] Check if received message is "LIST"
                        if (...) {
                            // d)
                            // [PT] Serializar o objeto username e colocá-lo no packet
                            // [EN] Serialize the username object and set it in the package
                            try (...) {
                                ...
                                pkt.setData(...);
                                pkt.setLength(...);
                            }

                            // e)
                            // [PT] Enviar packet
                            // [EN] Send packet
                            ...

                            continue;
                        }

                        System.out.println();
                        System.out.print("(" + pkt.getAddress().getHostAddress() + ":" + pkt.getPort() + ") ");
                        System.out.println(msg.getNickname() + ": " + msg.getMsg());

                    // f)
                    // [PT] Verificar se objecto recebido é do tipo String
                    // [EN] Check if type of received object is String
                    } else if (received instanceof String) {
                        System.out.println(received);
                    }

                    System.out.println();
                    System.out.print("> ");

                } catch (ClassNotFoundException e) {
                    System.out.println();
                    System.out.println("Mensagem recebida de tipo inesperado! " + e);
                } catch (IOException e) {
                    System.out.println();
                    System.out.println("Impossibilidade de aceder ao conteudo da mensagem recebida! " + e);
                } catch (Exception e) {
                    System.out.println();
                    System.out.println("Excepcao: " + e);
                }
            }
        } catch (IOException e) {
            if (running) {
                System.out.println(e);
            }

            // g)
            // [PT] Fechar socket
            // [EN] Close socket
            if (!socket.isClosed()) {
                socket.close();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        InetAddress group;
        int port;
        DatagramPacket dgram;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String message;
        NetworkInterface nif;
        Chat chat = null;

        if (args.length != 4) {
            System.out.println("Sintaxe: java Chat <nickname> <groupo multicast> <porto> <NIC multicast>");
            return;
        }

        group = InetAddress.getByName(args[1]);
        port = Integer.parseInt(args[2]);

        try {
            nif = NetworkInterface.getByInetAddress(InetAddress.getByName(args[1])); //e.g., 127.0.0.1, 192.168.10.1, ...
        } catch (SocketException | NullPointerException | UnknownHostException | SecurityException ex) {
            nif = NetworkInterface.getByName(args[3]); //e.g., lo, eth0, wlan0, en0, ...
        }

        // h)
        // [PT] Criar multicast socket
        // [EN] Create multicast socket
        try(...) {
            // i)
            // [PT] Juntar a grupo de multicast usando as variáveis group, port e nif
            // [EN] Join a multicast group using the variables group, port and nif
            ...

            // j)
            // [PT] Criar e iniciar thread
            // [EN] Create and start thread
            ...

            System.out.print("> ");

            while (true) {
                // k)
                // [PT] Ler uma linha introduzida na consola (usar a variável in)
                // [EN] Read a line inputted on the console (use the variable in)
                message = ...

                if (message.equalsIgnoreCase(EXIT)) {
                    break;
                }

                // l)
                // [PT] Criar um objeto da classe Msg (com o username passado por argumento e a message) e escrever no datagrampacket
                // [EN] Create an object of type Msg (with the username obtained from the args and the message) and write it on the datagrampacket
                ...
                try (...) {
                    ...
                    dgram = new DatagramPacket(...);
                }

                // m)
                // [PT] Enviar o datagrama
                // [EN] Send the datagram
                ...
            }
        } finally {
            // n)
            // [PT] Terminar a thread
            // [EN] Terminate the thread
            ...
        }
    }
}
