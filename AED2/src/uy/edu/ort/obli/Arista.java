package uy.edu.ort.obli;

public class Arista {
	private boolean existe;
	private int costo;

	public Arista(int costo) {
		this.existe = true;
		this.costo = costo;
	}

	public Arista() {
//		this.existe = false;
//		this.costo = 0;
	}

	public boolean isExiste() {
		return existe;
	}

	public void setExiste(boolean existe) {
		this.existe = existe;
	}

	public int getCosto() {
		return costo;
	}

	public void setCosto(int costo) {
		this.costo = costo;
	}

	@Override
	public String toString() {
		return costo+"";
	}

}
