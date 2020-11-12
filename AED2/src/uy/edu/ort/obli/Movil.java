/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.ort.obli;

import java.util.Objects;

/**
 *
 * @author hp
 */
public class  Movil extends Punto {
    private String matricula;
    private boolean disponible;

    public Movil(double coordX, double coordY) {
        super(coordX, coordY);
    }
    
//	public Movil(){
//	    
//	}
	
	public Movil(String matricula,double x,double y){
		super(x,y);
	    this.disponible=true;
	    this.matricula=matricula;
	}

   
	   
    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
    
    
    
    public boolean validarMatricula(){
        if(this.matricula!=null && this.matricula.trim().equals("")){
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + (int) (Double.doubleToLongBits(this.coordX) ^ (Double.doubleToLongBits(this.coordX) >>> 32));
        hash = 83 * hash + (int) (Double.doubleToLongBits(this.coordY) ^ (Double.doubleToLongBits(this.coordY) >>> 32));
        hash = 83 * hash + Objects.hashCode(this.matricula);
        hash = 83 * hash + (this.disponible ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Movil other = (Movil) obj;
        if (Double.doubleToLongBits(this.coordX) != Double.doubleToLongBits(other.coordX)) {
            return false;
        }
        if (Double.doubleToLongBits(this.coordY) != Double.doubleToLongBits(other.coordY)) {
            return false;
        }
        if (this.disponible != other.disponible) {
            return false;
        }
        if (!Objects.equals(this.matricula, other.matricula)) {
            return false;
        }
        return true;
    }
    
}
