package uy.edu.ort.cola;

import java.util.Iterator;

public class ColaEstatica<T> implements ICola<T> {

	public T[] arr;
	public int ppio;
	public int fin;
	public int tope;
	public int largo;

	public ColaEstatica(int tope) {
		this.arr = (T[]) new Object[tope];
//		this.ppio = 0;
//		this.fin = 0;
//		this.largo = 0;
		this.tope = tope;
	}

	@Override
	public void enqueue(T dato) {
		arr[fin] = dato;
		fin = (fin + 1) % tope;
		largo++;
	}

	@Override
	public void dequeue() {
		arr[ppio] = null;
		ppio = (ppio + 1) % tope;
		largo--;
	}

	@Override
	public T frontAndDequeue() {
		T ret = front();
		dequeue();
		return ret;
	}

	@Override
	public T front() {
		return arr[ppio];
	}

	@Override
	public boolean esVacia() {
		return largo == 0;
	}

	@Override
	public boolean estaLlena() {
		return largo == tope;
	}

	@Override
	public int largo() {
		return largo;
	}

	@Override
	public void vaciar() {
		while (!esVacia())
			dequeue();
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {

			int aux = ppio;
			int resto = largo;

			@Override
			public boolean hasNext() {
				return resto > 0;
			}

			@Override
			public T next() {
				T elem = arr[aux];
				aux = (aux + 1) % tope;
				resto--;
				return elem;
			}
		};
	}

}
