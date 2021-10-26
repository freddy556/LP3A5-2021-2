package javaapplication16;



/**
 *
 * @author max_p
 */
public class Filosofo extends Thread {
    int id;
    
    final int PENSANDO = 0;
    final int FAMINTO = 1;
    final int COMENDO = 2;
    
    public Filosofo(String nome, int id){
        super(nome);
        this.id = id;
    }
    
    public void ComFome(){
        Principal.estado[this.id] = 1;
        System.out.println("O Filósofo " + getName() + " está com fome!");
    }
    
    public void Come(){
        Principal.estado[this.id] = 2;
        System.out.println("O Filósofo " + getName() + " está comendo!");
        try{
            Thread.sleep(1000L);
        } catch(InterruptedException ex){
            System.out.println("ERRO" + ex.getMessage());
        }
    }
    
    public void Pensa(){
        Principal.estado[this.id] = 0;
        System.out.println("O Filósofo " + getName() + " está pensando!");
        try{
            Thread.sleep(1000L);
        } catch(InterruptedException ex){
            System.out.println("ERRO" + ex.getMessage());
        }
    }
    
    public void LargarHashi() throws InterruptedException{
        Principal.mutex.acquire();
        Pensa();
        
        Principal.filosofos[VizinhoEsquerda()].TentarObterHashi();
        Principal.filosofos[VizinhoDireita()].TentarObterHashi();
        Principal.mutex.release();
    }
    
    public void PegarHashi() throws InterruptedException{
        Principal.mutex.acquire();
        ComFome();
        
        TentarObterHashi();
        Principal.mutex.release();
        Principal.semaforos[this.id].acquire();
    }
    
    public void TentarObterHashi(){
        
        if(Principal.estado[this.id] == 1 
                && Principal.estado[VizinhoEsquerda()] != 2
                && Principal.estado[VizinhoDireita()]  !=2){
            Come();
            Principal.semaforos[this.id].release();
        } else {
            System.out.println(getName() + " não conseguiu comer!");
        }
        
    }
    
    @Override
    public void run(){
        try{
            Pensa();
            System.out.println("");
            do{
                PegarHashi();
                Thread.sleep(1000L);
                LargarHashi();
            } while(true);
        } catch(InterruptedException ex){
            System.out.println("ERRO" + ex.getMessage());
            return;
        }
    }
    
    public int VizinhoDireita(){
        return (this.id + 1) % 5;
    }
    
    public int VizinhoEsquerda(){
        if(this.id == 0){
            return 4;
        } else {
            return (this.id - 1) % 5;
        }
    }
    
    
}
