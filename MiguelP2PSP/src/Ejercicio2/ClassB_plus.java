package Ejercicio2;

public class ClassB_plus implements Runnable {
	
	private ClassA classA;
	private ClassB[] lista;
	private boolean acceder=true;

	public ClassB_plus(ClassA classA) {
		
		this.classA=classA;
	}
	
	public ClassB_plus(ClassA classA,ClassB[] lista) {
		
		this.classA=classA;
		this.lista=lista;		
	}
	
	@Override
	public void run() {
		
		synchronized(this){	
			while(acceder) {
				try {
					if(classA.isFinished()) {
						wait();
					} else {
						acceder=false;
						notify();
					}
						
				} catch (InterruptedException e) {					
					e.printStackTrace();
				}
			}	
		}
		classA.EnterAndWait();	
		synchronized(lista){	
			lista.notifyAll();
		}
	}
}
