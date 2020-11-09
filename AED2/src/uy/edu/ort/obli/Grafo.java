package uy.edu.ort.obli;

import java.util.Arrays;

import uy.edu.ort.cola.ColaDinamica;
import uy.edu.ort.cola.ICola;

public class Grafo {

    private int tope;
    private int cant;
    private Punto[] vertices;
    private Movil[] moviles;
    private Delivery[] delivery;
    private Arista[][] matAdyD;
    private Arista[][] matAdyND;

    public Grafo(int tope){// boolean esDirigido) {
        this.tope = tope;
        this.vertices = new Punto[tope];
        this.matAdyD = new Arista[tope][tope];
        this.matAdyND = new Arista[tope][tope];
        this.delivery = new Delivery[tope];
        this.moviles = new Movil[tope];
        for (int i = 0; i < matAdyD.length; i++) {
            for (int j = 0; j < matAdyD.length; j++) { 
                matAdyD[i][j] = new Arista();
            }
        }

        for (int i = 0; i < matAdyND.length; i++) {
            for (int j = i; j < matAdyND.length; j++) {
                matAdyND[i][j] = matAdyND[j][i] = new Arista();
            }
        }
//if (esDirigido) {
//			for (int i = 0; i < matAdyD.length; i++) {
//				for (int j = 0; j < matAdyD.length; j++) {
//					matAdyD[i][j] = new Arista();
//				}
//			}
//		} else {
//			for (int i = 0; i < matAdyND.length; i++) {
//				for (int j = i; j < matAdyND.length; j++) {
//					matAdyND[j][i] = new Arista();
//				}
//			}
//		}
    }

    public boolean esLleno() {

        return cant == tope;
    }

    public boolean esVacio() {
        return cant == 0;
    }

    // Pre: !esLleno()
    public boolean agregarVertice(Punto<Object> dato, Object o) {
        //dato.setDato(o);
        if(!esLleno()){
           int pos = posHueco(vertices);
        vertices[pos] = dato;
        cant++; 
        return true;
        }
       return false;
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
        while (vertices[pos] == null) {
            pos++;
        }
        return pos;
    }

    public boolean existeVertice(Punto origen) {
        return buscarPos(origen) != -1;
    }

    // Pre: existeVertice(origen) && existeVertice(destino)
    public boolean existeArista(Punto origen, Punto destino, boolean esDirigido) {
        int posOrigen = buscarPos(origen);
        int posDestino = buscarPos(destino);
if(existeVertice(origen) && existeVertice(destino)){
        if(esDirigido){
     return matAdyD[posOrigen][posDestino].isExiste();
}else{
     return matAdyND[posOrigen][posDestino].isExiste();
}
      
    }
return false;
    }
    // Pre: existeVertice(origen) && existeVertice(destino)
    public void borrarArista(Punto origen, Punto destino) {
        int posOrigen = buscarPos(origen);
        int posDestino = buscarPos(destino);

        matAdyD[posOrigen][posDestino].setExiste(false);
        matAdyND[posOrigen][posDestino].setExiste(false);
    }

    // Pre: existeVertice(origen)
    public void borrarVertice(Punto origen) {
        int posOrigen = buscarPos(origen);
        cant--;
        vertices[posOrigen] = null;
        if(existeVertice(origen)){
           for (int i = 0; i < tope; i++) {
            matAdyD[i][posOrigen].setExiste(false);
            matAdyD[posOrigen][i].setExiste(false);
            matAdyND[i][posOrigen].setExiste(false);
            matAdyND[posOrigen][i].setExiste(false);
        }  
        }
       
    }

    // Pre: existeVertice(origen) && existeVertice(destino) && !existeArista(origen,
    // destino)
    public boolean agregarArista(Punto origen, Punto destino, int metros, int minutos) {
        int posOrigen = buscarPos(origen);
        int posDestino = buscarPos(destino);
if(existeVertice(origen) && existeVertice(destino) && !existeArista(origen,destino,true)){
           matAdyD[posOrigen][posDestino].setExiste(true);
        matAdyD[posOrigen][posDestino].setmetros(metros);
        matAdyD[posOrigen][posDestino].setminutos(minutos); 
          return true;
}else if(existeVertice(origen) && existeVertice(destino) && !existeArista(origen,destino,false)){
        matAdyND[posOrigen][posDestino].setExiste(true);
        matAdyND[posOrigen][posDestino].setmetros(metros);
        matAdyND[posOrigen][posDestino].setminutos(minutos); 
    

        return true;
}
 return false;   
    }

    private int buscarPos(Punto destino) {
        for (int i = 0; i < tope; i++) {
            if (destino.equals(vertices[i])) {
                return i;
            }
        }
        return -1;
    }

    // Pre: existeVertice(origen)
    public boolean dfs(Punto origen) {
        int posOrigen = buscarPos(origen);
        boolean[] vis = new boolean[tope];
        if(existeVertice(origen)){
            dfsRec(posOrigen, vis);
            return true;
        }
       return false;
    }

    private void dfsRec(int pos, boolean[] vis) {
        System.out.println(vertices[pos]);
        vis[pos] = true;
        for (int j = 0; j < tope; j++) {
            if (!vis[j] && matAdyND[pos][j].isExiste()) {
                dfsRec(j, vis);
            }
        }
    }

    // Pre: existeVertice(origen)
    public boolean bfs(Punto origen) {
        int posOrigen = buscarPos(origen);
        boolean[] vis = new boolean[tope];
        if(existeVertice(origen)){
           bfsInterno(posOrigen, vis);
           return true;
        }
        return false;
    }

    private void bfsInterno(int posOrigen, boolean[] vis) {
        ICola<Integer> cola = new ColaDinamica<Integer>();
        cola.enqueue(posOrigen);
        vis[posOrigen] = true;
        while (!cola.esVacia()) {
            int pos = cola.frontAndDequeue();
            System.out.println(vertices[pos]);
            for (int j = 0; j < tope; j++) {
                if (!vis[j] && matAdyND[pos][j].isExiste()) {
                    cola.enqueue(j);
                    vis[j] = true;
                }
            }
        }
    }

    public void prim() {
        int pos = posConVertice();
        boolean[] vis = new boolean[tope];
        vis[pos] = true;
        // Realizo el procedimiento reiterativo |V|-1 veces
        for (int k = 0; k < cant - 1; k++) {
            int posO = -1, posD = -1, metros = Integer.MAX_VALUE;

            // Hago un todos contra todos para quedarme con la arista m�s corta
            // entre un visitado y un no visitado
            // Busco los visitados
            for (int i = 0; i < tope; i++) {
                if (vis[i]) {
                    // Busco los no visitados
                    for (int j = 0; j < tope; j++) {
                        if (!vis[j] && matAdyND[i][j].isExiste() && matAdyND[i][j].getmetros() < metros) {
                            posO = i;
                            posD = j;
                            metros = matAdyND[i][j].getmetros();
                        }
                    }
                }
            }
            vis[posD] = true;
            System.out.println(String.format("%s -> %s (costo: %s)", vertices[posO], vertices[posD], metros));
            // System.out.println(vertices[posO] + " -> " + vertices[posD] + " (costo: " +
            // costo + ")");
        }
    }

    public void dijkstra(Punto origen) {

        int posO = buscarPos(origen);

        // Armo los tres arreglos necesarios para realizar el algoritmo
        int[] dist = new int[tope];
        int[] ant = new int[tope];
        boolean[] vis = new boolean[tope];

        // inicializo los vectores
        for (int i = 0; i < tope; dist[i] = Integer.MAX_VALUE, ant[i] = -1, i++)
			;

        // asigno al destino como el primer nodo a ser recorrido
        dist[posO] = 0;

        // comienzo proceso reiterativo (V veces) para ir procesando a los v�rtices de a
        // uno
        for (int k = 0; k < cant; k++) {
            int posMin = -1, min = Integer.MAX_VALUE;

            // hallo al v�rtice no visitado de menor distancia al origen
            for (int i = 0; i < tope; i++) {
                if (!vis[i] && dist[i] < min) {
                    posMin = i;
                    min = dist[i];
                }
            }

            // visito al elemento a ser procesado
            vis[posMin] = true;

            // analizo a los adyacentes, actualizando su distancia en caso de ser menor a la
            // hasta ahora descubierta
            for (int j = 0; j < tope; j++) {
                if (!vis[j] && matAdyND[posMin][j].isExiste()) {
                    int sumaAcumulada = dist[posMin] + matAdyND[posMin][j].getmetros();
                    if (sumaAcumulada < dist[j]) {
                        dist[j] = sumaAcumulada;
                        ant[j] = posMin;
                    }
                }
            }
        }

        System.out.println(Arrays.toString(dist));
        System.out.println(Arrays.toString(vertices));
        System.out.println(Arrays.toString(ant));
    }

    public boolean agregarDelivery(Punto dato, Object o) {
        //dato.setDato(o);
        Delivery d = (Delivery) o;
        if (o.getClass() == Delivery.class && !existeVertice(new Punto(d.coordX, d.coordY))) {
 int posH = posHueco(delivery);
        delivery[posH] = (Delivery) o;
        int pos = posHueco(vertices);
        vertices[pos] = dato;
        cant++;
        return true;
        }
       return false;
    }

    public boolean agregarMovil(Punto dato, Object o) {
        //dato.setDato(o);
        Movil d = (Movil) o;
        if (o.getClass() == Movil.class && !existeVertice(new Punto(d.coordX, d.coordY))) {
   int posH = posHueco(moviles);
        moviles[posH] = (Movil) o;
        int pos = posHueco(vertices);
        vertices[pos] = dato;
        cant++;
        return true;
        }
return false;
     
    }

    public boolean agregarMovilODelivery(Object o) {
        if (o.getClass() == Movil.class) {
            Movil m = (Movil) o;
            Punto p = new Punto(m.coordX, m.coordY);
            if (!existeVertice(p)) {
                agregarMovil(p, m);
                return true;
            }

        } else if (o.getClass() == Delivery.class) {
            Delivery m = (Delivery) o;
            Punto p = new Punto(m.coordX, m.coordY);
            if (!existeVertice(p)) {
                agregarDelivery(p, m);
                return true;
            }
        }
        return false;
    }
}
