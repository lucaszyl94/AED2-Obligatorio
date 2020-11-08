package uy.edu.ort.obli;

import uy.edu.ort.obli.Retorno.Resultado;

public class Sistema implements ISistema {

    //private NodoUsuario  nodo;
    private ControladorUsuarios cu;
    private static Sistema instancia;
    private int maxPuntos;
    private ControladorMovilesDeliverys cmd;
private Grafo grafo;
    public Sistema() {
        // this.cu=new ControladorUsuarios();

    }

    public static Sistema getInstancia() {

        if (instancia == null) {
            instancia = new Sistema();
        }
        return instancia;
    }

    @Override
    public Retorno inicializarSistema(int maxPuntos) {
       
        if (maxPuntos > 0) {
             getInstancia();
             
        this.cmd = new ControladorMovilesDeliverys();
        this.cu = new ControladorUsuarios();
        this.grafo=new Grafo(maxPuntos);
            return new Retorno(Resultado.OK);
        } else {
            return new Retorno(Resultado.ERROR_1);
        }

    }

    @Override
    public Retorno destruirSistema() {
        this.cu = null;
        this.cmd = null;
        this.maxPuntos = 0;
        this.grafo=null;
        return new Retorno(Resultado.OK);
    }

    @Override
    public Retorno registrarUsuario(String email, String nombre, String password) {
        Usuario u = new Usuario(email, nombre, password);
        if (!u.verificarMail()) {
            return new Retorno(Resultado.ERROR_1);
        }
        if (cu.buscarUsuario(email) != null) {
            return new Retorno(Resultado.ERROR_2);
        }
        if (cu.registrarUsuario(password, nombre, email)) {
            return new Retorno(Resultado.OK);

        }
        return new Retorno(Resultado.ERROR_1);
    }

    @Override
    public Retorno buscarUsuario(String email) {
        Retorno ret = new Retorno(Retorno.Resultado.ERROR_1);
       boolean u = new Usuario(email,null,null).verificarMail();
        if(!u) return new Retorno(Resultado.ERROR_1);
        Usuario us= cu.buscarUsuario(email);
        if(us==null)return new Retorno(Resultado.ERROR_2);
         ret = new Retorno(Retorno.Resultado.OK);
        ret.valorString = (us.getMail()+";"+us.getNombre());
       // if (ret.valorString != null) {
           
       // }

        return ret;
    }

    @Override
    public Retorno listarUsuarios() {
        Retorno ret = new Retorno(Retorno.Resultado.OK);
        for (Usuario u : cu.listarAscendiente()) {
            ret.valorString += "\n" + u.getMail() + ";" + u.getNombre() + "\n";
        }

        return ret;
    }

    @Override
    public Retorno direccionesDeUsuario(String email) {
        return new Retorno(Resultado.NO_IMPLEMENTADA);
    }

    @Override
    public Retorno registrarEsquina(double coordX, double coordY) {
    	Retorno ret = new Retorno(Retorno.Resultado.ERROR_1);
    	return ret;
    //	if(instancia)
    }

    @Override
    public Retorno registrarTramo(double coordXi, double coordYi, double coordXf, double coordYf, int metros, int tiempo) {
        return new Retorno(Resultado.NO_IMPLEMENTADA);
    }

    @Override
    public Retorno registrarDelivery(String cedula, Double coordX, Double coordY) {
        Retorno ret = new Retorno(Retorno.Resultado.ERROR_1);
//
//        if (cmd.registrarDelivery(cedula, coordX, coordY)) {
//            ret = new Retorno(Retorno.Resultado.OK);
//            ret.valorString = "true";
//            return ret;
//
//        } else {
//            ret.valorString = "false";
//            return ret;
//        }
 if (grafo.agregarMovilODelivery(new Delivery(cedula, coordX, coordY))) {
            ret = new Retorno(Retorno.Resultado.OK);
            ret.valorString = "true";
            return ret;

        } else {
            ret.valorString = "false";
            return ret;
        }
    }

    @Override
    public Retorno registrarMovil(String matricula, Double coordX, Double coordY) {
        Retorno ret = new Retorno(Retorno.Resultado.ERROR_1);

        if (cmd.registrarMovil(matricula, coordX, coordY)) {
            ret.valorString = "true";
            return ret = new Retorno(Retorno.Resultado.OK);

        } else {
            ret.valorString = "false";
            return ret;
        }
    }

    @Override
    public Retorno movilMasCercano(Double coordXi, Double coordYi) {
        return new Retorno(Resultado.NO_IMPLEMENTADA);
    }

    @Override
    public Retorno deliveryMasCercano(Double coordXi, Double coordYi) {
        return new Retorno(Resultado.NO_IMPLEMENTADA);
    }

    @Override
    public Retorno caminoMinimoMovil(Double coordXi, Double coordYi, Double coordXf, Double coordYf) {
        return new Retorno(Resultado.NO_IMPLEMENTADA);
    }

    @Override
    public Retorno caminoMinimoDelivery(Double coordXi, Double coordYi, Double coordXf, Double coordYf) {
        return new Retorno(Resultado.NO_IMPLEMENTADA);
    }

    @Override
    public Retorno dibujarMapa() {
        return new Retorno(Resultado.NO_IMPLEMENTADA);
    }

}
