/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lp3a5;

import java.util.Scanner;
/**
 *
 * @author max_p
 */
class Contador extends Thread{
	 private volatile boolean contando = true;
	 private int contador = 1; 
	 
	 public void run() { 
		  
		 	while(contando) { 
		 		System.out.println(contador); 
		 		contador++; 
		 		try { 
		 			Thread.sleep(90);
		 		}catch(InterruptedException e) { 
		 			e.printStackTrace();
		 		}
		 	}
	  }
	  
	  public void PararContagem(){ 
		  contando = false;
	  } 
}
public class Aula3{

	public static void main(String[] args) {
		Contador c1 = new Contador(); 
		c1.start();
		Scanner PararCont = new Scanner(System.in);
		PararCont.nextLine(); 
		c1.PararContagem();
	}

}
