package Ejercicio1;

public class Main {

	public static void main(String[] args) {
		
		Thread hilo = null;
		ClassA classA=new ClassA();
		
		for(int i=0;i<5;i++) {
			ClassB classB=new ClassB(classA);
			hilo=new Thread(classB);
			hilo.start();
		}		
	}
}
