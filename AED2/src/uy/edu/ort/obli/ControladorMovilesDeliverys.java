/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.ort.obli;

import java.util.ArrayList;


/**
 *
 * @author hp
 */
public class ControladorMovilesDeliverys {
    private Nodo<Movil> raizMovil;
    private Nodo<Delivery>raizDelivery;
  //  private ABB arbolMovil = new ABB(raizMovil);
   
    
    private void insertarRec(Movil dato, Nodo<Movil> nodo) {
      
        if (dato.getMatricula().compareTo(nodo.getDato().getMatricula())<0){
            if (nodo.getIzq() == null) {
                
                nodo.setIzq(new Nodo<Movil>(dato));
            } else {
                insertarRec(dato, nodo.getIzq());
            }
        } else if (dato.getMatricula().compareTo(nodo.getDato().getMatricula())>0) {
            if (nodo.getDer() == null) {
                
                nodo.setDer(new Nodo<Movil>(dato));
            } else {
                insertarRec(dato, nodo.getDer());
            }
        }
    }
    private void insertarRec(Delivery dato, Nodo<Delivery> nodo) {
      
        if (dato.getCi().compareTo(nodo.getDato().getCi())<0){
            if (nodo.getIzq() == null) {
                
                nodo.setIzq(new Nodo<Delivery>(dato));
            } else {
                insertarRec(dato, nodo.getIzq());
            }
        } else if (dato.getCi().compareTo(nodo.getDato().getCi())>0) {
            if (nodo.getDer() == null) {
                
                nodo.setDer(new Nodo<Delivery>(dato));
            } else {
                insertarRec(dato, nodo.getDer());
            }
        }
    }
   public int compare(Delivery u1, Delivery u2) {
             return u1.getCi().compareTo(u2.getCi());
            }
            public int compare(Movil u1, Movil u2) {
             return u1.getMatricula().compareTo(u2.getMatricula());
            }
       private Object existeRec(String key, Nodo nodo) {
        
        if (nodo == null) {
            return null;
        } else if (key.equals(nodo.getDato())) {
            return nodo.getDato();
        } else if (key.compareTo(nodo.getDato().toString()) < 0) {
            return existeRec(key, nodo.getIzq());
        } else {
            return existeRec(key, nodo.getDer());
        }
    }
    private ArrayList<Object> listarAscendienteRec(Nodo<Object> nodo, ArrayList<Object> lista) {
        
        if (nodo != null) {
            listarAscendienteRec(nodo.getIzq(), lista);
            lista.add(nodo.getDato());
            listarAscendienteRec(nodo.getDer(), lista);
        }
        return lista;
    }
    public boolean registrarMovil(String matricula,double x,double y){   
        Movil m = new Movil(matricula,x,y);
        
        if(raizMovil==null){
         Nodo<Movil> i= new Nodo<Movil>(m);
         raizMovil=i;
         if(m.validarMatricula()) return true;
        
        }
        if (existeRec(m.getMatricula(), raizMovil) != null) {
            return false;
        }
        
        if (m.validarMatricula() == true) {
            
            insertarRec(m, raizMovil);
            
            return true;
        }
        return false; 
    }
public boolean perteneceMovil(Nodo<Movil> nm){
    
    return false ;//arbolMovil.pertenece((Comparable) nm);
}
    public Movil deliveryMasCercano(double xi,double yi){
        return null;
    }
    public String caminoMinimoMovil(double xi,double yi,double xf,double yf){
return new String();
}
    public String caminoMinimoDelivery(double xi,double yi,double xf,double yf){
return new String();
}  
    public boolean registrarDelivery(String cedula,double x,double y){
         Delivery d = new Delivery(cedula,x,y);
        
        if(raizDelivery==null){
         Nodo<Delivery> i= new Nodo<Delivery>(d);
         raizDelivery=i;
         if(d.verificarCedula()) return true;
        
        }
        if (existeRec(d.getCi(), raizDelivery) != null) {
            return false;
        }
        
        if (d.verificarCedula() == true) {
            
            insertarRec(d, raizDelivery);
            
            return true;
        }
        return false;
    }
}

