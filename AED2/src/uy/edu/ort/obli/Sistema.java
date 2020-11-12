package uy.edu.ort.obli;

import java.awt.Desktop;
import java.util.*;
import java.util.Arrays;
import java.util.stream.*;
import java.net.URL;
import uy.edu.ort.obli.ABB;
import uy.edu.ort.obli.Retorno.Resultado;

public class Sistema implements ISistema {

    private Grafo Mapa;
    private ABB<Usuario> Usuarios;

    public Sistema() {
    }

    @Override
    public Retorno inicializarSistema(int maxPuntos) {
        this.Mapa = new Grafo(maxPuntos);
        this.Usuarios = new ABB<Usuario>();
        return new Retorno(Resultado.OK);
    }

    @Override
    public Retorno destruirSistema() {
        this.Mapa = null;
        this.Usuarios = null;
        return new Retorno(Resultado.OK);
    }

    @Override
    public Retorno registrarUsuario(String email, String nombre, String password) {
        Usuario u = new Usuario(email, nombre, password);
        if (!u.verificarMail()) {
            return new Retorno(Resultado.ERROR_1);
        }
        if (Usuarios.pertenece(u)) {
            return new Retorno(Resultado.ERROR_2);
        }
        Usuarios.insertar(u);
        return new Retorno(Resultado.OK);
    }

    @Override
    public Retorno buscarUsuario(String email) {
        Retorno ret = new Retorno(Retorno.Resultado.ERROR_1);
        Usuario u = new Usuario(email);

        if (u.verificarMail()) {
            Nodo<Usuario> us = Usuarios.buscar(u);
            ret.valorEntero = us.getCant();
            ret.valorString = us.getDato().getMail() + ";" + us.getDato().getNombre();
            ret.resultado = Resultado.OK;
        }
        return ret;
    }

    @Override
    public Retorno listarUsuarios() {
        Retorno ret = new Retorno(Retorno.Resultado.OK);
        ret.valorString = "";

        for (Usuario u : Usuarios.listarAsc()) {
            ret.valorString += "|" + u.getMail() + ";" + u.getNombre();
        }
        ret.valorString = ret.valorString.substring(1);
        return ret;
    }

    @Override
    public Retorno direccionesDeUsuario(String email) {
        Retorno ret = new Retorno(Resultado.ERROR_1);
        Usuario u = new Usuario(email);
        if (!u.verificarMail()) {
            return ret;
        }
        Nodo<Usuario> us = Usuarios.buscar(u);
        if (us == null) {
            return new Retorno(Resultado.ERROR_2);
        }
        ret = new Retorno(Resultado.OK);
        u = us.getDato();
        for (Punto p : u.getDirecciones()) {
            ret.valorString += p.toString();

        }
        return ret;
    }

    @Override
    public Retorno registrarEsquina(double coordX, double coordY) {
        Retorno ret = new Retorno(Retorno.Resultado.ERROR_1);
        if (Mapa.esLleno()) {
            return ret;
        }
        if (Mapa.existeVertice(new Punto(coordX, coordY))) {
            return new Retorno(Retorno.Resultado.ERROR_2);
        }
        Mapa.agregarVertice(new Punto(coordX, coordY), null);
        return new Retorno(Retorno.Resultado.OK);
    }

    @Override
    public Retorno registrarTramo(double coordXi, double coordYi, double coordXf, double coordYf, int metros, int tiempo) {
        Retorno ret = new Retorno(Resultado.ERROR_1);
        Punto origen = new Punto(coordXi, coordYi);
        Punto destino = new Punto(coordXf, coordYf);
        if (metros <= 0) {
            return ret;
        }
        if (tiempo <= 0) {
            return new Retorno(Resultado.ERROR_2);
        }
        if (!Mapa.existeVertice(origen) || !Mapa.existeVertice(destino)) {
            return new Retorno(Resultado.ERROR_3);
        }
        if (Mapa.existeArista(origen, destino, true) || Mapa.existeArista(origen, destino, false)) {
            return new Retorno(Resultado.ERROR_4);
        }
        if (Mapa.agregarArista(origen, destino, metros, tiempo)) {
            return new Retorno(Resultado.OK);
        }
        return ret;
    }

    @Override
    public Retorno registrarDelivery(String cedula, Double coordX, Double coordY) {
        Retorno ret = new Retorno(Resultado.ERROR_1);
        if (Mapa.esLleno()) {
            return ret;
        }
        Punto ubicacion = new Punto(coordX, coordY);
        if (Mapa.existeVertice(ubicacion)) {
            return new Retorno(Resultado.ERROR_2);
        }
        Delivery d = new Delivery(cedula, coordX, coordY);
        if (Mapa.agregarDelivery(d)) {
            return new Retorno(Retorno.Resultado.OK);
        }
        return ret;
    }

    @Override
    public Retorno registrarMovil(String matricula, Double coordX, Double coordY) {
        Retorno ret = new Retorno(Resultado.ERROR_1);
        if (Mapa.esLleno()) {
            return ret;
        }
        Punto ubicacion = new Punto(coordX, coordY);
        if (Mapa.existeVertice(ubicacion)) {
            return new Retorno(Resultado.ERROR_2);
        }
        Movil m = new Movil(matricula, coordX, coordY);
        if (Mapa.agregarMovil(m)) {
            return new Retorno(Retorno.Resultado.OK);
        }
        return ret;
    }

    @Override
    public Retorno movilMasCercano(Double coordXi, Double coordYi) {
        Retorno ret = new Retorno(Resultado.OK);
        Punto origen = new Punto(coordXi, coordYi);
        if (!Mapa.existeVertice(origen)) {
            return new Retorno(Resultado.ERROR_1);
        }
        if (Arrays.stream(Mapa.vertices).filter(m -> m.getClass() == Movil.class).filter(x -> ((Movil) x).isDisponible()).toArray() == null) {
            return new Retorno(Resultado.ERROR_2);
        }
        Punto m = Mapa.dijkstraMovilMasCercano(origen);
        if(m==null) return new Retorno(Resultado.ERROR_2);
            ret = new Retorno(Resultado.OK);
            ret.valorEntero=m.getDato();
        
       return ret;
    }

    @Override
    public Retorno deliveryMasCercano(Double coordXi, Double coordYi) {
        Retorno ret = new Retorno(Resultado.OK);
        Punto origen = new Punto(coordXi, coordYi);
        if (!Mapa.existeVertice(origen)) {
            return new Retorno(Resultado.ERROR_1);
        }
        if (Arrays.stream(Mapa.vertices).filter(m -> m.getClass() == Delivery.class).filter(x -> ((Delivery) x).isDisponible()).toArray() == null) {
            return new Retorno(Resultado.ERROR_2);
        }
         Punto d = Mapa.dijkstraDeliveryMasCercano(origen);
        if(d==null) return new Retorno(Resultado.ERROR_2);
            ret = new Retorno(Resultado.OK);
            ret.valorEntero=d.getDato();
        ret.valorString= ""+d.coordX+";"+d.coordY+"";
       return ret;
    }

    @Override
    public Retorno caminoMinimoMovil(Double coordXi, Double coordYi, Double coordXf, Double coordYf, String mail) {
        Retorno ret = new Retorno(Resultado.OK);
        Punto origen = new Punto(coordXi, coordYi);
        Punto destino = new Punto(coordXf, coordYf);
        if (!Mapa.existeVertice(origen) || !Mapa.existeVertice(destino)) {
            return new Retorno(Resultado.ERROR_1);
        }
        if (Arrays.stream(Mapa.vertices).filter(m -> m.getClass() == Movil.class).filter(x -> ((Movil) x).isDisponible()).toArray() == null) {
            return new Retorno(Resultado.ERROR_2);
        }
        //Arrays.stream(Mapa.vertices).toArray() == null) return ret;
        //.filter(x -> x.getClass()==Movil.class) == null) return ret;
        //.filter(x->((Movil)x).isDisponible()).toArray() == null) return new Retorno(Resultado.ERROR_2);
        ret = Mapa.dijkstraCaminoMovil(origen, destino);
        Usuario u = new Usuario(mail);
        Nodo<Usuario> us = Usuarios.buscar(u);
        us.getDato().agregarDireccion(coordXf, coordYf);
        return ret;
    }

    @Override
    public Retorno caminoMinimoDelivery(Double coordXi, Double coordYi, Double coordXf, Double coordYf) {
        Retorno ret = new Retorno(Resultado.OK);
        Punto origen = new Punto(coordXi, coordYi);
        Punto destino = new Punto(coordXf, coordYf);
        if (!Mapa.existeVertice(origen) || !Mapa.existeVertice(destino)) {
            return new Retorno(Resultado.ERROR_1);
        }
        if (Arrays.stream(Mapa.vertices).filter(m -> m.getClass() == Delivery.class).filter(x -> ((Delivery) x).isDisponible()).toArray() == null) {
            return new Retorno(Resultado.ERROR_2);
        }
        ret = Mapa.dijkstraCaminoDelivery(origen, destino);
        return ret;
    }

    @Override
    public Retorno dibujarMapa() {
        Retorno ret = new Retorno(Resultado.OK);
        // String marcador= "markers=color:blue%7Clabel:1%7C"+"-34.90"+","+"-56.16";
        ret.valorString = "http://maps.googleapis.com/maps/api/staticmap?center=Montevideo,Uruguay&zoom=13&size=1200x600&maptype=roadmap&"; //"markers=color:blue%7Clabel:1%7C"+"coordX"+","+"coordy";
        for (Object d : Arrays.stream(Mapa.vertices).filter(d -> d.getClass() == Delivery.class).toArray()) {
            if (d != null) {

                ret.valorString += "markers=color:blue%7Clabel:D%7C" + String.valueOf(((Delivery) d).coordX) + "," + String.valueOf(((Delivery) d).coordY) + "&";
            }
        }
        for (Object m : Arrays.stream(Mapa.vertices).filter(m -> m.getClass() == Movil.class).toArray()) {
            if (m != null) {

                ret.valorString += "markers=color:blue%7Clabel:D%7C" + String.valueOf(((Movil) m).coordX) + "," + String.valueOf(((Movil) m).coordY) + "&";
            }
        }
        ret.valorString += "sensor=false&key=AIzaSyC2kHGtzaC3OOyc7Wi1LMBcEwM9btRZLqw";
        //  String mapa=marcadorGenerico;  //"http://maps.googleapis.com/maps/api/staticmap?center=Montevideo,Uruguay&zoom=13&size=1200x600&maptype=roadmap&markers=color:blue%7Clabel:1%7C-34.90,-56.16&markers=color:red%7Clabel:2%7C-34.91,-56.17&markers=color:green%7Clabel:3%7C-34.905,-56.19&sensor=false&key=AIzaSyC2kHGtzaC3OOyc7Wi1LMBcEwM9btRZLqw" ;

        //String url = "http://maps.googleapis.com/maps/api/staticmap?key=AIzaSyC2kHGtzaC3OOyc7Wi1LMBcEwM9btRZLqw&size=1200x600&maptype=roadmap&markers=color:blue%7Clabel:1%7C-34.90,-56.16&markers=color:red%7Clabel:2%7C-34.91,-56.17&markers=color:green%7Clabel:3%7C-34.905,-56.19";
        try {
            Desktop.getDesktop().browse(new URL(ret.valorString).toURI());
        } catch (Exception e) {
            e.printStackTrace();
        }
//return new Retorno(Resultado.OK);
        return ret;
    }

}
