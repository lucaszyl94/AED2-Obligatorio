/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.ort.obli;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Usuario implements Comparable<Usuario> {

    private int id;

    private String nombre;
    private String mail;
    private String password;
    private Punto[] direcciones;

    public Usuario(String mail, String nombre, String password) {

        this.nombre = nombre;
        this.mail = mail;
        this.password = password;
    }

    public Usuario(String mail) {

        this.mail = mail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Punto[] getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(Punto[] direcciones) {
        this.direcciones = direcciones;
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

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX
            = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    public boolean verificarMail() {
        return validate(this.getMail()) && this.getMail() != null && !this.getMail().trim().equals("");// && this.getMail().contains("@") &&this.getMail().contains(".com") ;
    }

    public boolean verificarPassword() {
        return this.getPassword().length() > 5 && this.getPassword() != null;
    }

    public boolean validar() {
        return this.verificarMail() && this.verificarNombre() && this.verificarPassword();
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
        Usuario other = (Usuario) obj;
        if (mail != other.mail) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(Usuario o) {
        return this.mail.compareTo(o.mail);

        // return this.nombreCompleto.compareTo(o.nombreCompleto);
    }

    public boolean agregarDireccion(double coordX, double coordY) {

        Punto p = existeDireccion(new Punto(coordX, coordY));
        if (p != null) {
            p.setDato(p.getDato() + 1);
            int pos = buscarPos(p);
            direcciones[pos] = p;
            return true;
        } else {
            Punto pp = new Punto(coordX, coordY, 1);
            int pos = posHueco(direcciones);
            direcciones[pos] = pp;
            return true;
        }

    }

    private int posHueco(Object[] o) {
        int pos = 0;
        while (o[pos] != null) {
            pos++;
        }
        return pos;
    }

    private int posConVertice() {
        int pos = 0;
        while (direcciones[pos] == null) {
            pos++;
        }
        return pos;
    }

    public Punto existeDireccion(Punto p) {
        for (Punto pp : direcciones) {
            if (p.coordX == pp.coordX && p.coordY == pp.coordY) {
                return pp;
            }
        }
        return null;
    }

    private int buscarPos(Punto destino) {
        for (int i = 0; i < direcciones.length; i++) {
            if (destino.equals(direcciones[i])) {
                return i;
            }
        }
        return -1;
    }
}
