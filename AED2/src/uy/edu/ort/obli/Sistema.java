package uy.edu.ort.obli;

import uy.edu.ort.obli.ABB;
import uy.edu.ort.obli.Retorno.Resultado;

public class Sistema implements ISistema {

    private static Sistema instancia;
    private Grafo Mapa;
    private ABB<Usuario> Usuarios;
//   private Grafo gMoviles;
//   private Grafo gDeliverys;
    public Sistema() {

    }

    public static Sistema getInstancia() {

        if (instancia == null) {
            instancia = new Sistema();
        }
        return instancia;
    }

    @Override
    public Retorno inicializarSistema(int maxPuntos) {
        this.Mapa = new Grafo(maxPuntos);//el mapa es un grafo dirigido? seria por tener orientacion de snetido de calles?
//        this.gDeliverys = new Grafo(maxPuntos,false);
//        this.gMoviles = new Grafo(maxPuntos,true);
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
       
        if(u.verificarMail()) {
        	Nodo<Usuario> us = Usuarios.buscar(u);
        	ret.valorEntero = us.getCant();
        	ret.valorString = us.getDato().getMail() + ";" + us.getDato().getNombre();
        	ret.resultado =  Resultado.OK;
        }
        return ret;
    }

    @Override
    public Retorno listarUsuarios() {
        Retorno ret = new Retorno(Retorno.Resultado.OK);
        for(Usuario u:Usuarios.listarAsc()){
             ret.valorString+= "\n"+u.getMail()+";"+u.getNombre()+"\n";  
        }

        
        return ret;
    }

    @Override
    public Retorno direccionesDeUsuario(String email) {
        Retorno ret= new Retorno(Resultado.ERROR_1);
          Usuario u = new Usuario(email);    
        if(!u.verificarMail()) return ret;
          Nodo<Usuario> us = Usuarios.buscar(u);
        if(us==null)return new Retorno   (Resultado.ERROR_2);  
   ret=new Retorno(Resultado.OK);
       u=us.getDato();
       for(Punto p:u.getDirecciones()){
           ret.valorString+=p.toString();
       
            }
      return ret;
    }

    @Override
    public Retorno registrarEsquina(double coordX, double coordY) {
    	Retorno ret = new Retorno(Retorno.Resultado.ERROR_1);
    	if(Mapa.esLleno()) return ret;
    	if(Mapa.existeVertice(new Punto(coordX,coordY))) return new Retorno(Retorno.Resultado.ERROR_2);
    	Mapa.agregarVertice(new Punto(coordX, coordY),null);
    	return new Retorno(Retorno.Resultado.OK);
    }

    @Override
    public Retorno registrarTramo(double coordXi, double coordYi, double coordXf, double coordYf, int metros, int tiempo) {
        Retorno ret = new Retorno(Resultado.ERROR_1);
        Punto origen = new Punto(coordXi,coordYi);
        Punto destino = new Punto(coordXf,coordYf);
        if(metros<=0) return ret;
        if(tiempo<=0) return new Retorno(Resultado.ERROR_2);
        if(!Mapa.existeVertice(origen) || !Mapa.existeVertice(destino)) return new Retorno(Resultado.ERROR_3);
        if(Mapa.existeArista(origen, destino,true)||Mapa.existeArista(origen, destino,false)) return new Retorno(Resultado.ERROR_4);
       if( Mapa.agregarArista(origen, destino, metros, tiempo)) return new Retorno(Resultado.OK); 
return ret;
    }

    @Override
    public Retorno registrarDelivery(String cedula, Double coordX, Double coordY) {
    	Retorno ret = new Retorno(Resultado.ERROR_1);
    	if(Mapa.esLleno()) return ret;
    	Punto ubicacion = new Punto(coordX,coordY);
    	if(Mapa.existeVertice(ubicacion)) new Retorno(Resultado.ERROR_2);
    	Delivery d = new Delivery(cedula,coordX,coordY);
    	if(Mapa.agregarDelivery(ubicacion, d))return new Retorno(Retorno.Resultado.OK);
    	//ret = new Retorno(Retorno.Resultado.OK);
        return ret;
    }

    @Override
    public Retorno registrarMovil(String matricula, Double coordX, Double coordY) {
    	Retorno ret = new Retorno(Resultado.ERROR_1);
    	if(Mapa.esLleno()) return ret;
    	Punto ubicacion = new Punto(coordX,coordY);
    	if(Mapa.existeVertice(ubicacion)) new Retorno(Resultado.ERROR_2);
    	Movil m = new Movil(matricula,coordX,coordY);
    	if(Mapa.agregarMovil(ubicacion, m))return new Retorno(Retorno.Resultado.OK);
    	//ret = new Retorno(Retorno.Resultado.OK);
        return ret;
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
