package Ejercicio2;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ClassA {

	private int counter;
	private Set<Long> threadIds=new HashSet<Long>();
	
	public ClassA(int counter) {
		this.counter=counter;
	}
	
	public synchronized void EnterAndWait() {
		
		Long idHilo=Thread.currentThread().getId();
		System.out.println("El hilo que está ejecutando es: "+idHilo);
		threadIds.add(idHilo);	
		counter--;	
		System.out.println("El hilo que está acabando de ejecutar es: "+idHilo);		
	}
	
	public boolean isFinished() {
		
		boolean valor=false;
		
		if(counter==0) {
			valor=true;
		} else {
			valor=false;
		}
		
		return valor;
	}
	
	public void listarHilos() {
		
		String listar=null;
		
		for(Long id : threadIds) {
			listar=String.valueOf(id);
			System.out.println("Hilo "+listar);
		}
	}
	
	public void comprobacion(List<Thread> listaHilos) {
		
		List<Thread> listaThread=listaHilos;
		Long idHilo=null;
		
		for(Long id : threadIds) {
			Long idThread=id;			
			for(int j=0;j<listaThread.size();j++) {
				Thread hilo=listaThread.get(j);
				idHilo=hilo.getId();
				if(idThread==idHilo) {
					System.out.println("El hilo "+idHilo+" ha ejecutado el método EnterAndWait().");
				}
			}		
		}	
	}
}
