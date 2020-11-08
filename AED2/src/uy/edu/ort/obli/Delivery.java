/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.ort.obli;

/**
 *
 * @author hp
 */
public class Delivery extends Punto {
    private String ci;
    private boolean disponible;

    public Delivery( String ci,double x, double y) {
    	super(x,y);
        this.ci = ci;
        this.disponible=true;
    }


    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
    
    public boolean verificarCedula(){
        boolean ok = false;
        if (ci !=null){
            int digitos = ci.length();
            String numeros = "0123456789";
            boolean soloNumeros = true;
            ci = ci.toLowerCase();
            for (int x=0;x<ci.length()&&soloNumeros;x++){
                String d = ci.charAt(x)+"";
                if (!numeros.contains(d)){
                    soloNumeros = false;
                }
            }
           
            if (soloNumeros && digitos>=6&&digitos<=8){
                ok = true;
            }    
        }
        return ok;        
    
    }
    
    
    
}
