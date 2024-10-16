package ex_14_incomplete;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Master {
    // [PT]
    /*
     Le linhas do ficheiro fileName.
     Em cada uma, a primeira palavra corresponde ao endereco de um worker e a segunda ao seu porto de escuta.
     Se existir algum problema com a formatacao da linha, ignora-a.
     Estabelece um ligacao TCP com o worker, colocando o respectivo socket em workers.
     Se existir alguma problema na ligacao ao worker, ignora-o.
    */
    // [EN]
    /*
     Read lines from the file fileName.
     In each line, the first word corresponds to the address of a worker and the second to its listening port.
     If there is any issue with the line's formatting, ignore it.
     Establish a TCP connection with the worker, placing the corresponding socket in workers.
     If there is any problem with the connection to the worker, ignore it."
    */
    private static int getWorkers(String fileName, List<Socket> workers) {
        String workerCoord;
        String workerName;
        int workerPort;

        workers.clear();

        // a)
        // [PT] Criar objeto que irá permitir obter informação sobre os workers a partir do ficheiro de texto com o nome dado pela variável fileName
        // [EN] Create an object that will allow obtaining information about the workers from the text file with the name given by the variable fileName
        try(BufferedReader inFile = ...) {

            // b)
            // [PT] Processa cada uma das linhas de texto do ficheiro
            // [EN] Process each line of text from the file
            while((workerCoord = ...) != null){
                workerCoord = workerCoord.trim();
                if(workerCoord.length() == 0){
                    continue;
                }                
                
                try{     

                    // c)
                    // [PT] Extrai as duas primeiras palavras da String workerCoord usando uma instancia de Scanner
                    // [EN] Extract the first two words from the String workerCoord using an instance of Scanner
                    ...
                    workerName = ...
                    workerPort = ...
                            
                } catch(Exception e) {
                    System.err.print("> Entrada incorrecta no ficheiro ");
                    System.err.println(fileName + ": \"" + workerCoord + "\"");
                    
                    // d)
                    // [PT] Salta as restantes instruções da iteração actual do ciclo while
                    // [EN] "Skip the remaining instructions of the current iteration of the while loop
                    ...
                }        
                
                System.out.print("> Estabelecendo ligacao com o worker " + (workers.size()+1));
                System.out.println(" [" + workerName + ":" + workerPort+"]... ");

                try {
                    // e)
                    // [PT] Estabelece uma ligacao TCP com o worker e acrescenta o socket à lista de workers
                    // [EN] "Establish a TCP connection with the worker and add the socket to the list of workers.
                    ...

                    System.out.println("... ligacao estabelecida!");                    
                } catch(UnknownHostException e) {
                    System.err.println();
                    System.err.println("> Destino " + workerName + " desconhecido!");
                    System.err.println(); System.err.println(e); System.err.println();
                } catch(IOException e) {
                    System.out.println("> Impossibilidade de estabelecer ligacao!");
                    System.err.println(); System.err.println(e); System.err.println(); 
                }
            } //while
        } catch(FileNotFoundException e) {
            System.err.println();
            System.err.println("Impossibilidade de abrir o ficheiro: " + fileName + "\n\t" + e);
        } catch(IOException e) {
            System.err.println(); System.err.println(e);
        }
        
        return workers.size();
    }
    
    public static void main(String[] args) throws InterruptedException {
        long nIntervals;
        List<Socket> workers  = new ArrayList<>();
        int i, nWorkers = 0;
        double workerResult;
        double pi = 0;
        Calendar t1, t2;
        
        System.out.println();

        if(args.length != 2) {
            System.out.println("Sintaxe: java Master <numero de intervalos> <ficheiro com os ip e portos TCP dos workers>");
            return;
        }

        // f)
        // [PT] Popula a variável com o valor obtido nos args
        // [EN] Set the variavel with the value from the args
        nIntervals = ...
        
        t1 = Calendar.getInstance();     

        // g)
        // [PT] Obtém o número de workers usando o método estático getWorkers da classe Worker
        // [EN] Get the number of workers using the static method getWorkers from the Worker class
        nWorkers = ...
        
        if(nWorkers <= 0) {
            return;
        }
        
        try {
            for(i=0; i<nWorkers; i++) {
                // h)
                // [PT] Cria um ObjectOutputStream associado ao socket relativo ao worker com indice i
                // [EN] Create an ObjectOutputStream associated with the socket for the worker at index i
                ObjectOutputStream output = ...

                // i)
                // [PT] Cria e envia o pedido (request to work)
                // [EN] Create and write the request (request to work)
                ...
            }

            System.out.println();
			
            for(i=0; i<nWorkers; i++) {
                // j)
                // [PT] Cria um ObjectInputStream associado ao socket relativo ao worker com indice i
                // [EN] Create an ObjectInputStream associated with the socket for the worker at index i
                ObjectInputStream input = ...
                
                // k)
                // [PT] Obtém o valor retornado sob a forma de um Double serializado
                // [EN] Get the returned value as a serializable Double
                workerResult = ...
                
                System.out.println("> Worker " + (i+1) + ": " + workerResult);
                pi += workerResult;
            }
            
        } catch(IOException e) {
            System.err.println("Erro ao aceder ao socket\n\t" + e);            
        } catch(ClassNotFoundException e) {
            System.err.println("Recebido objecto de tipo inesperado\n\t" + e);
        } finally {
            // l)
            // [PT] Fecha os sockets da lista de workers
            // [EN] Close the sockets from the workers list
            ...
        }

        t2 = Calendar.getInstance();

        System.out.println();
        System.out.println(">> Valor aproximado do pi: " + pi + " (calculado em " + 
                (t2.getTimeInMillis() - t1.getTimeInMillis()) + " msec.)");
    }
}
