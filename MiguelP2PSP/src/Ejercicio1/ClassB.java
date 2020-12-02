package Ejercicio1;

public class ClassB implements Runnable {
	
	ClassA classA;

	public ClassB(ClassA classA) {
		
		this.classA=classA;
	}
	
	@Override
	public void run() {
		
		classA.EnterAndWait();
	}
}
