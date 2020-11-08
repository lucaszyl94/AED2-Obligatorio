package uy.edu.ort.cola;

public class Main {

	public static void main(String[] args) {
		ICola<Integer> cola = new ColaEstatica<Integer>(10);
		
		cola.enqueue(6);
		cola.enqueue(5);
		cola.enqueue(4);
		cola.enqueue(3);
		cola.enqueue(2);
		cola.enqueue(1);
		
		for (Integer num : cola) {
			System.out.println(num);
		}
		
		System.out.println("---");
		System.out.println(cola.frontAndDequeue());
		System.out.println("---");
		
		for (Integer num : cola) {
			System.out.println(num);
		}
	}

}
