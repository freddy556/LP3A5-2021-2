package javaapplication16;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author max_p
 */
public class Principal {
    
    static Semaphore mutex = new Semaphore(1);
    
    static Semaphore[] semaforos = new Semaphore[5];
    
    static Filosofo[] filosofos = new Filosofo[5];
    static int[] estado = new int[5];
    
    public static void main(String[] args){
        
        for(int i=0; i<estado.length; i++){
            estado[i] = 0;
        }
        
        
        filosofos[0] = new Filosofo("Sócrates", 0);
        filosofos[1] = new Filosofo("Platão", 1);
        filosofos[2] = new Filosofo("Aristóteles", 2);
        filosofos[3] = new Filosofo("Tales", 3);
        filosofos[4] = new Filosofo("Parmênides", 4);
        
        
        for(int i=0; i<filosofos.length; i++){
            System.out.println("hashi" + i + " - filosofo" + i + " - hashi" + (i + 1) % 5);
        }
        
        System.out.println("");
        for(int i=0; i<semaforos.length; i++){
            semaforos[i] = new Semaphore(0);
        }
        
        for(int i=0; i<filosofos.length; i++){
            filosofos[i].start();
        }
        
        try{
            Thread.sleep(10000);
            System.exit(0);
        } catch(InterruptedException ex){
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
