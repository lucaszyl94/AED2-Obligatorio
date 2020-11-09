package uy.edu.ort.obli;

public class Arista {
	private boolean existe;
	private int minutos;
	private int metros;

	public Arista(int minutos, int metros) {
		this.existe = true;
		this.minutos = minutos;
		this.metros = metros;
	}

	public Arista() {
//		this.existe = false;
//		this.minutos = 0;
	}

	public boolean isExiste() {
		return existe;
	}

	public void setExiste(boolean existe) {
		this.existe = existe;
	}

	public int getminutos() {
		return minutos;
	}

	public void setminutos(int minutos) {
		this.minutos = minutos;
	}

	public int getmetros() {
		return metros;
	}

	public void setmetros(int metros) {
		this.metros = metros;
	}
	
	@Override
	public String toString() {
		return minutos+""+metros;
	}

}
