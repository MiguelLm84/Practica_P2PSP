package Ejercicio2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {

	public static void main(String[] args) {
		
		Thread hilo=null;
		List<Thread> listaHilos=new ArrayList<Thread>();
		List<ClassB> listaClassB=new ArrayList<ClassB>();
		Thread[] hilosAlmacenados;
		ClassB[] lista;	
		Scanner sc=new Scanner(System.in);
		 
		System.out.println("Introducir un valor para el Counter: ");
		int numCounter=sc.nextInt();
		ClassA classA=new ClassA(numCounter);		
		
		System.out.println("Introducir número de hilos a crear: ");
		int numHilos=sc.nextInt();
		lista=new ClassB[numHilos];
		
		System.out.println("Introducir un monitor a utilizar o 'exit' para terminar.");
		System.out.println("1. Notify\n2. NotifyAll\n3. Exit");
		int opcion=sc.nextInt();
		
		do {
			switch(opcion) {
			
			case 1:
				
				for(int i=0;i<numHilos;i++) {
					ClassB classB=new ClassB(classA);
					listaClassB.add(classB);
				}
				for(int f=0;f<listaClassB.size();f++) {
					if(f==listaClassB.size()-1) {
						listaClassB.get(f);
						listaClassB.get(f).setNext(listaClassB.get(0));
					}else {
						listaClassB.get(f).setNext(listaClassB.get(f+1));
					}
				}
				for(ClassB cb : listaClassB) {
					hilo=new Thread(cb);
					hilo.start();
					listaHilos.add(hilo);	
				}
				synchronized(listaClassB.get(0)){
					listaClassB.get(0).notify();						
				}
//				synchronized(listaHilos){
//					while(classA.isFinished()) {
//						listaHilos.notifyAll();
//					}
//				}	
				
				try {
					hilo.join();
				} catch (InterruptedException e) {
					System.out.println("Error, el hilo se ha interrumpido.");
					e.getMessage();
				} catch (NullPointerException f) {
					System.out.println("Error, está a null.");
					f.getMessage();
				}
				
				if(classA.isFinished()) {
					System.out.println("\nIdentificadores de los hilos ejecutados: ");		
					classA.listarHilos();
					System.out.println(" ");
					classA.comprobacion(listaHilos);
				}
				break;
				
			case 2:
				
				hilosAlmacenados=new Thread[numHilos];
				
				for(int j=0;j<numHilos;j++) {					
					ClassB_plus classB_lista=new ClassB_plus(classA,lista);
					hilo=new Thread(classB_lista);
					hilosAlmacenados[j]=hilo;
					hilo.start();
					synchronized(hilosAlmacenados){
						hilosAlmacenados.notifyAll();
					}
					listaHilos.add(hilo);
					synchronized(hilosAlmacenados){
						while(classA.isFinished()) {
							hilosAlmacenados.notifyAll();
						}
					}	
				}
				try {
					hilo.join();
				} catch (InterruptedException e) {
					System.out.println("Error, el hilo se ha interrumpido.");
					e.getMessage();
				} catch (NullPointerException f) {
					System.out.println("Error, está a null.");
					f.getMessage();
				}
				if(classA.isFinished()) {
					System.out.println("\nIdentificadores de los hilos ejecutados: ");		
					classA.listarHilos();
					System.out.println(" ");
					classA.comprobacion(listaHilos);
				}
				break;
			
			case 3:
				System.out.println("La ejecución se ha terminado. Adios!!.");
				break;
				
			default:
				
				System.out.println("Error, el valor introducido no es correcto.");
				break;	
			}
		
		} while(opcion!=3);		
		sc.close();
	}
}
