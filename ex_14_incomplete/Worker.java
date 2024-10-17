package ex_14_incomplete;

import ex_15.models.RequestToWorker;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

//Esta classe tambem representa uma thread
public class Worker
{    
    protected Socket s;
    
    public Worker(Socket s)
    {
        /***/
    }
    
    public double getMyResult(int myId, int nWorkers, long nIntervals)
    {
        long i;
        double dX, xi, myResult;
        
        if(nIntervals < 1 || nWorkers < 1 || myId <1 || myId > nWorkers){
            return 0.0;
        }
        
        dX = 1.0/nIntervals;
        myResult = 0;
        
        for (i = myId-1 ; i < nIntervals; i += nWorkers) {             
            xi = dX*(i + 0.5);
            myResult += (4.0/(1.0 + xi*xi));               
        }
        
        myResult *= dX;
        
        return myResult;
    }
    
    @Override
    public void run()
    {
        int myId;
        int nWorkers;
        long nIntervals;
        double myResult;
        RequestToWorker req;
        
        //Cria um ObjectInputStream e um ObjectOutputStream associados ao socket s       
        try(ObjectInputStream in = /***/;
            ObjectOutputStream out = /***/){
                                        
            req = /***/ //Aguarda pela recepcao de um pedido enviado pelo master

            myId = /***/      
            nWorkers = /***/
            nIntervals = /***/               
            
            myResult = getMyResult(myId, nWorkers, nIntervals);
            
            //Envia myResult ao Master sob a forma de um Double serializado
            /***/
            
            System.out.format("<%s> %.10f\n", Thread.currentThread().getName(), myResult);
            
        }catch(ClassNotFoundException | IOException e){
                System.err.println("<" + Thread.currentThread().getName() + ">:\n\t" + e);
        }finally{
            try{
                if(s != null) s.close();
            }catch(IOException e){}            
        }
        
    }

    public static void main(String[] args) {
        
        Socket toMaster;
        int listeningPort;
        Thread t;
        int nCreatedThreads = 0;
        
        if(args.length != 1){
            System.out.println("Sintaxe: java Worker <listening port>");
            return;
        }
        
        listeningPort = /***/
        
        try(ServerSocket s = /***/){
                        
            while(true){
                toMaster = /***/ //Aceita um pedido de ligacao TCP de um master
                
                //Inicia uma thread destinada a tratar da comunicacao com o master
                nCreatedThreads++;
                t = new Thread(new Worker(/***/), "Thread_"+nCreatedThreads);
                /***/              
            }            
            
        }catch(IOException e){
            System.out.println("<Worker> Erro ao aceder ao socket:\n\t" + e);
        }
                
    }
}
