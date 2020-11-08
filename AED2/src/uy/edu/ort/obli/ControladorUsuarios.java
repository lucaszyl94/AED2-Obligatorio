/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.ort.obli;

import java.util.Collections;
import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author hp
 */
public class ControladorUsuarios {
    
    private int id = 1;
 
    private Nodo<Usuario> raiz;
    private ABB arbol = new ABB(raiz);

    public ControladorUsuarios() {

    }
    
    public ControladorUsuarios(Nodo<Usuario> raiz ) {
        this.raiz = raiz;
        
    }
    
    private Usuario existeRec(String mail, Nodo<Usuario> nodo) {
        
        if (nodo == null) {
            return null;
        } else if (mail.equals(nodo.getDato().getMail())) {
            return nodo.getDato();
        } else if (mail.compareTo(nodo.getDato().getMail()) < 0) {
            return existeRec(mail, nodo.getIzq());
        } else {
            return existeRec(mail, nodo.getDer());
        }
    }
    
    public boolean login(String password, String mail) {
        return existeRec(mail, raiz).getPassword() == password;
    }
    
    private void insertarRec(Usuario dato, Nodo<Usuario> nodo) {
       
      
         // Chequeo si mi dato es menor al nodo
        if (dato.getId() < nodo.getDato().getId()) {
            // Chequeo si tengo algo a la izquierda. Si est� vac�o, debo insertar!
            if (nodo.getIzq() == null) {
                
                nodo.setIzq(new Nodo<Usuario>(dato));
            } else {
                // Si tengo una izquierda, llamo recursivamente!
                insertarRec(dato, nodo.getIzq());
            }
        } else if (dato.getId() > nodo.getDato().getId()) {
            // Chequeo si tengo algo a la derecha. Si est� vac�o, debo insertar!
            if (nodo.getDer() == null) {
                
                nodo.setDer(new Nodo<Usuario>(dato));
            } else {
                // Si tengo una derecha, llamo recursivamente!
                insertarRec(dato, nodo.getDer());
            }
        }
    }
    
    public boolean registrarUsuario(String pass, String nombre, String mail) {
        Usuario u = new Usuario(pass, nombre, mail);
        u.setId(id);
        if(raiz==null){
         Nodo<Usuario> i= new Nodo<Usuario>(u);
         raiz=i;
        }
        if (existeRec(u.getMail(), raiz) != null) {
            return false;
        }
        
        if (u.validar() == true) {
            
            insertarRec(u, raiz);
            id++;
            return true;
        }
        return false;
        
    }
    
    public ArrayList<Usuario> listarAscendiente() {
        ArrayList<Usuario> lista = new ArrayList<Usuario>();
        lista = listarAscendienteRec(raiz, lista);
        Collections.sort(lista, new Comparator<Usuario>() {
            
            @Override
            public int compare(Usuario u1, Usuario u2) {
                return u1.getMail().compareTo(u2.getMail());
            }
        });
        return lista;
        
    }
    
    private ArrayList<Usuario> listarAscendienteRec(Nodo<Usuario> nodo, ArrayList<Usuario> lista) {
        
        if (nodo != null) {
            listarAscendienteRec(nodo.getIzq(), lista);
            lista.add(nodo.getDato());
            //System.out.println("Nombre: "+nodo.getDato().getNombre()+" Mail: "+nodo.getDato().getMail());
            listarAscendienteRec(nodo.getDer(), lista);
        }
        return lista;
    }
    
    public Usuario buscarUsuario(String email) {
        
        return existeRec(email, raiz);
    }
    
    public boolean pertenece(Usuario u) {
        
       return arbol.pertenece((Comparable) u);
    }
    
}
