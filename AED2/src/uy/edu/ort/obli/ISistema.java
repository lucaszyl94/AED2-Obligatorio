package uy.edu.ort.obli;

public interface ISistema {

    Retorno inicializarSistema(int maxPuntos);

    Retorno destruirSistema();

    Retorno registrarUsuario(String email, String nombre, String password);

    Retorno buscarUsuario(String email);

    Retorno listarUsuarios();

    Retorno direccionesDeUsuario(String email);

    Retorno registrarEsquina(double coordX, double coordY);

    Retorno registrarDelivery(String cedula, Double coordX, Double coordY);

    Retorno registrarMovil(String matricula, Double coordX, Double coordY);

    Retorno registrarTramo(double coordXi, double coordYi, double coordXf, double coordYf, int metros, int tiempo);

    Retorno movilMasCercano(Double coordXi, Double coordYi);

    Retorno deliveryMasCercano(Double coordXi, Double coordYi);

    Retorno caminoMinimoMovil(Double coordXi, Double coordYi, Double coordXf, Double coordYf, String mail);

    Retorno caminoMinimoDelivery(Double coordXi, Double coordYi, Double coordXf, Double coordYf);

    Retorno dibujarMapa();

}
