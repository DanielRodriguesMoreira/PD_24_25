package ex_15_incomplete;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class Master {

    static final int TIMEOUT = 10000; //10 seconds
    static final int TABLE_ENTRY_TIMEOUT = 60000; //60 seconds

    static final String GET_WORKERS_QUERY = "SELECT * FROM pi_workers;";

    private static int getWorkers(String sgbdAddress, String bdName, String user, String pass, List<Socket> workers) {

        String workerName;
        int workerPort;
        Socket socketToWorker;

        workers.clear();

        String dbUrl = "jdbc:mysql://" + sgbdAddress + "/" + bdName;

        // a)
        // [PT] Conectar com a base de dados usando as variáveils dbUrl, user e pass
        // [EN] Connect the database using the variables dbUrl and user and pass
        try (Connection conn = ...;
             Statement stmt = conn.createStatement()) {

            ResultSet rs = stmt.executeQuery("Select CURRENT_TIMESTAMP");
            Timestamp currentTimestampInServer = rs.next() ? rs.getTimestamp("current_timestamp") : null;

            rs = stmt.executeQuery(GET_WORKERS_QUERY);

            while (rs.next()) {
                try {
                    // b)
                    // [PT] Popular as variáveis com os valores obtidos na base de dados
                    // [EN] Populate the variables with the values from the database
                    workerName = ...
                    workerPort = ...
                    Timestamp timestamp = ...

                    System.out.println("> DB entry: [" + workerName + ":" + workerPort + "]");

                    long elapsedTime = currentTimestampInServer.getTime() - timestamp.getTime();
                    System.out.println("\t... Entry created/updated " + elapsedTime / 1000 + " seconds ago");

                    if (elapsedTime > TABLE_ENTRY_TIMEOUT) {
                        System.out.println("\t... Entry will be deleted!");
                        try (Statement stmt2 = conn.createStatement()) {
                            stmt2.executeUpdate("DELETE FROM pi_workers WHERE address = '" + workerName + "' AND port = " + workerPort + ";"); //Se usarmos stmt, rs e' encerrado, o que resulta numa excepcao no while
                        }
                        continue;
                    }

                    System.out.print("> Connecting to worker " + (workers.size() + 1));
                    System.out.println(" [" + workerName + ":" + workerPort + "]... ");

                    // c)
                    // [PT] Abre uma conexão TCP com o worker
                    // [EN] Open a TCP connection to the worker
                    socketToWorker = ...

                    // d)
                    // [PT] Define o timeout (TIMEOUT)
                    // [EN] Set a timeout (TIMEOUT)
                    socketToWorker...

                    // e)
                    // [PT] Adiciona o socket à lista de sockets dos workers
                    // [EN] Add the socket to the list of workers' sockets
                    ...

                    System.out.println("\t... connection established!");
                } catch (IOException e) {
                    System.out.println("\r\n> Cannot connect to host!\r\n\t " + e + "\r\n");
                }
            } //while

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return workers.size();
    }

    public static void main(String[] args) throws InterruptedException {
        long nIntervals;

        List<Socket> workers = new ArrayList<>();
        ObjectOutputStream output;
        ObjectInputStream input;

        int i, nWorkers;
        double workerResult;
        double pi = 0;

        Calendar t1, t2;

        System.out.println();

        if (args.length != 5) {
            System.out.println("> Syntax: java Master <number of intervals> <SGBD address> <BD name> <usename> <password>");
            return;
        }

        nIntervals = Long.parseLong(args[0]);

        t1 = GregorianCalendar.getInstance();
        nWorkers = getWorkers(args[1], args[2], args[3], args[4], workers);

        if (nWorkers <= 0) {
            return;
        }

        try {
            for (i = 0; i < nWorkers; i++) {
                // f)
                // [PT] Cria um ObjectOutputStream para transmitir objectos para o worker com index i
                // [EN] Create an ObjectOuputStream to transmit objects to worker at index i
                output = ...

                // g)
                // [PT] Envia o request serializado para o worker
                // [EN] Send the request serialized to the worker
                ...
            }

            System.out.println();

            for (i = 0; i < nWorkers; i++) {
                // h)
                // [PT] Cria um ObjectInputStream para receber objetos do worker com o index i
                // [EN] Create an ObjectInputStream to receive object from worker at index i
                input = ...

                // i)
                // [PT] Obtém o resultado do worker com o index i
                // [EN] Get the result from worker at index i
                workerResult = ...
                System.out.println("> Worker " + (i + 1) + ": " + workerResult);
                pi += workerResult;
            }

        } catch (IOException e) {
            System.err.println("> Erro ao aceder ao socket\r\n\t" + e);
        } catch (ClassNotFoundException e) {
            System.err.println("> Recebido objecto de tipo inesperado\r\n\t" + e);
        } finally {
            // j)
            // [PT] Fecha todos os sockets da lista de workers
            // [EN] Close all the sockets in workers list
            ...

            workers.clear();
        }

        t2 = GregorianCalendar.getInstance();

        System.out.println();
        System.out.println("> Valor aproximado do pi: " + pi + " (calculado em "
                + (t2.getTimeInMillis() - t1.getTimeInMillis()) + " msec.)");
    }
}
