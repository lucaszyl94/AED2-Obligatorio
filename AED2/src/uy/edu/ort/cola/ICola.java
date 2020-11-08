package uy.edu.ort.cola;

public interface ICola<T> extends Iterable<T> {

	/**
	 * Inserta un elemento a la cola
	 * <br>Pre: La cola no puede estar llena
	 * @param dato
	 */
	public void enqueue(T dato);

	/**
	 * Borra un elemento de la cola
	 * <br>Pre: La cola no puede estar vacia
	 */
	public void dequeue();

	/**
	 * Borra un elemento de la cola, retornándolo
	 * <br>Pre: La cola no puede estar vacia
	 * @return tope
	 */
	public T frontAndDequeue();

	/**
	 * Retorna el inicio de la cola
	 * <br>Pre: La cola no puede estar vacia
	 * @return tope
	 */
	public T front();

	/**
	 * Retorna true sii la cola es vacía; false en caso contrario
	 * @return boolean
	 */
	public boolean esVacia();

	/**
	 * Retorna true sii la cola está llena; false en caso contrario
	 * @return boolean
	 */
	public boolean estaLlena();


	/**
	 * Retorna el largo de la cola
	 * @return int
	 */
	public int largo();

	/**
	 * Vacía la cola
	 */
	public void vaciar();

}
