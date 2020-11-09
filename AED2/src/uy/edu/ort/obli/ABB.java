package uy.edu.ort.obli;

public class ABB<T extends Comparable<T>> {
	
	private Nodo<T> raiz;

	public ABB() {

	}

	public ABB(Nodo<T> raiz) {
		this.raiz = raiz;
	}
	
	public Nodo<T> buscar(T dato) {
		int cant = 0;
		return buscarRec(dato, cant, raiz);
	}

	private Nodo<T> buscarRec(T dato, int cant, Nodo<T> nodo) {
		if (nodo == null) {
			return null;
		} else if (dato.equals(nodo.getDato())){
			nodo.setCant(cant);
			return nodo;
		} else if(dato.compareTo(nodo.getDato()) < 0) {
			cant++;
			return buscarRec(dato, cant, nodo.getIzq());
		} else {
			cant++;
			return buscarRec(dato, cant, nodo.getDer());
		}
	}
	
	public boolean pertenece(T dato) {
		return perteneceRec(dato, raiz);
	}

	private boolean perteneceRec(T dato, Nodo<T> nodo) {
		if (nodo == null) {
			return false;
		} else if (dato.equals(nodo.getDato())){
			return true;
		} else if(dato.compareTo(nodo.getDato()) < 0) {
			return perteneceRec(dato, nodo.getIzq());
		} else {
			return perteneceRec(dato, nodo.getDer());
		}
	}
	
	public void listarAscendiente() {
		listarAscendienteRec(raiz);
		System.out.println();
	}

	private void listarAscendienteRec(Nodo<T> nodo) {
		if(nodo != null) {
			listarAscendienteRec(nodo.getIzq());
			System.out.println(nodo.getDato());
			listarAscendienteRec(nodo.getDer());
		}
	}
	
	public void listarDescendiente() {
		listarDescendienteRec(raiz);
		System.out.println();
	}

	private void listarDescendienteRec(Nodo<T> nodo) {
		if(nodo != null) {
			listarDescendienteRec(nodo.getDer());
			System.out.println(nodo.getDato());
			listarDescendienteRec(nodo.getIzq());
		}
	}
	
	public void insertar (T dato) {
		if(raiz == null)
		{
			raiz = new Nodo<T>(dato);
		} else {
			insertarRec(dato, raiz);
		}
	}

	private void insertarRec(T dato, Nodo<T> nodo) {
		// Chequeo si mi dato es menor al nodo
		if(dato.compareTo(nodo.getDato()) < 0) {
			// Chequeo si tengo algo a la izquierda. Si est� vac�o, debo insertar!
			if(nodo.getIzq() == null) {
				nodo.setIzq(new Nodo<T>(dato));
			} else {
				// Si tengo una izquierda, llamo recursivamente!
				insertarRec(dato, nodo.getIzq());
			}
		} else if(dato.compareTo(nodo.getDato()) > 0) {
			// Chequeo si tengo algo a la derecha. Si est� vac�o, debo insertar!
			if(nodo.getDer() == null) {
				nodo.setDer(new Nodo<T>(dato));
			} else {
				// Si tengo una derecha, llamo recursivamente!
				insertarRec(dato, nodo.getDer());
			}
		}
	}
	
	public void borrarMin() {
		if(raiz.getIzq() == null)
			raiz = raiz.getDer();
		else
			borrarMinRec(raiz);
	}

	private void borrarMinRec(Nodo<T> nodo) {
		if(nodo.getIzq().getIzq()==null) {
			nodo.setIzq(nodo.getIzq().getDer());
		} else {
			borrarMinRec(nodo.getIzq());
		}
	}
	
}
