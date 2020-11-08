package uy.edu.ort.cola;

import java.util.Iterator;

public class ColaDinamica<T> implements ICola<T> {

	private NodoCola<T> inicio;
	private NodoCola<T> fin;
	private int largo;
	private int tope;

	public ColaDinamica() {
//		this.inicio = null;
//		this.fin = null;
//		this.largo = 0;		
		this.tope = -1;
	}

	public ColaDinamica(int tope) {
//		this.inicio = null;
//		this.fin = null;
//		this.largo = 0;		
		this.tope = tope;
	}

	@Override
	public void enqueue(T dato) {
		if (fin != null) {
			fin.setSig(new NodoCola<T>(dato));
			fin = fin.getSig();
		} else {
			inicio = fin = new NodoCola<T>(dato, inicio);
		}
		largo++;
	}

	@Override
	public void dequeue() {
		inicio = inicio.getSig();
		if(inicio == null)
			fin = null;
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
		return inicio.getDato();
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

			NodoCola<T> aux = inicio;

			@Override
			public boolean hasNext() {
				return aux != null;
			}

			@Override
			public T next() {
				T actual = aux.getDato();
				aux = aux.getSig();
				return actual;
			}
		};
	}

}
