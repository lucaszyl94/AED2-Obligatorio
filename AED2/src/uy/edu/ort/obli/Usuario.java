/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.ort.obli;

public class Usuario {//<T extends Comparable<T>> uso clase usuario para insertar en dato?
       private int id;

  
    private String nombre;
    private String mail;
    private String password;


    public Usuario(String mail, String nombre, String password) {
       
        this.nombre = nombre;
        this.mail = mail;
        this.password = password;
    }

    public int getId() {
        return id;
    }
      public void setId(int id) {
        this.id = id;
    }

    public Usuario() {

    }

    public String getMail() {
        return mail;
    }

    public void setMail(String unMail) {

        mail = unMail;

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String unPassword) {

        password = unPassword;

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String unNombre) {
        nombre = unNombre;
    }

    public boolean verificarNombre() {
        return this.getNombre() != null && !this.getNombre().trim().equals("");
    }

    public boolean verificarMail() {
        return this.getMail() != null && !this.getMail().trim().equals("");// && this.getMail().contains("@") &&this.getMail().contains(".com") ;
    }

    public boolean verificarPassword() {
        return this.getPassword().length() > 5 && this.getPassword() != null;
    }
    public boolean validar(){
        return this.verificarMail()&&this.verificarNombre()&&  this.verificarPassword();
    }
}
