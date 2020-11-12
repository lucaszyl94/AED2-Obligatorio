package uy.edu.ort.obli;

import java.util.Arrays;

import uy.edu.ort.cola.ColaDinamica;
import uy.edu.ort.cola.ICola;
import uy.edu.ort.obli.Retorno.Resultado;

public class Grafo {

    private int tope;
    private int cant;
    public Punto[] vertices;
    //private Movil[] moviles;
    //private Delivery[] deliverys;
    private Arista[][] matAdyD;
    private Arista[][] matAdyND;

    public Grafo(int tope){// boolean esDirigido) {
        this.tope = tope;
        this.vertices = new Punto[tope];
        this.matAdyD = new Arista[tope][tope];
        this.matAdyND = new Arista[tope][tope];
        //this.deliverys = new Delivery[tope];
        //this.moviles = new Movil[tope];
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
    }

    public boolean esLleno() {

        return cant == tope;
    }

    public boolean esVacio() {
        return cant == 0;
    }

    public Punto[] getVertices() {
		return vertices;
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

    
    public int dijkstraMovilMasCercano(Punto origen) {
    	
        int posO = buscarPos(origen);

        // Armo los tres arreglos necesarios para realizar el algoritmo
        int[] dist = new int[tope];
        int[] ant = new int[tope];
        boolean[] vis = new boolean[tope];

        // inicializo los vectores
        for (int i = 0; i < tope; dist[i] = Integer.MAX_VALUE, ant[i] = -1, i++);

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
                if (!vis[j] && matAdyD[posMin][j].isExiste()) {
                    int sumaAcumulada = dist[posMin] + matAdyD[posMin][j].getmetros();
                    if (sumaAcumulada < dist[j]) {
                        dist[j] = sumaAcumulada;
                        ant[j] = posMin;
                    }
                }
            }
            
            int posMinMovil=-1, minMovil = Integer.MAX_VALUE;
        	for(int j =0;j<tope;j++) {
        		if(vertices[j] instanceof Movil && ((Movil)vertices[j]).isDisponible() == true && dist[j]<dist[posMinMovil]) {
        			posMinMovil = j;
        			minMovil = dist[j];
        		}
        	}
        	if(posMinMovil == -1) {
        		return -1;
        	}else {
        		Movil m = (Movil)vertices[posMinMovil];
        		m.setDisponible(false);
        		return minMovil;
        	} 
        }
        return -1; 
            
        }
        
        public Delivery dijkstraDeliveryMasCercano(Punto origen) {
        	
            int posO = buscarPos(origen);

            // Armo los tres arreglos necesarios para realizar el algoritmo
            int[] dist = new int[tope];
            int[] ant = new int[tope];
            boolean[] vis = new boolean[tope];

            // inicializo los vectores
            for (int i = 0; i < tope; dist[i] = Integer.MAX_VALUE, ant[i] = -1, i++);

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
                        int sumaAcumulada = dist[posMin] + 1;
                        if (sumaAcumulada < dist[j]) {
                            dist[j] = sumaAcumulada;
                            ant[j] = posMin;
                        }
                    }
                }
            }
        
    	int posMinDelivery=-1, minDelivery = Integer.MAX_VALUE;
    	for(int j =0;j<tope;j++) {
    		if(vertices[j] instanceof Delivery && ((Delivery)vertices[j]).isDisponible() == true && dist[j]<dist[posMinDelivery]) {
    			posMinDelivery = j;
    			minDelivery = dist[j];
    		}
    	}
    	if(posMinDelivery == -1) {
    		return null;
    	}else {
    		Delivery d = (Delivery)vertices[posMinDelivery];
    		d.setDato(minDelivery);
    		d.setDisponible(false);
    		return d;
    	} 
    }
    
    
    
        public Retorno dijkstraCaminoMovil(Punto origen, Punto destino) {
        	Retorno ret = new Retorno(Resultado.ERROR_1);

            int posO = buscarPos(origen);
            int posD = buscarPos(destino);

            // Armo los tres arreglos necesarios para realizar el algoritmo
            int[] dist = new int[tope];
            int[] ant = new int[tope];
            boolean[] vis = new boolean[tope];

            // inicializo los vectores
            for (int i = 0; i < tope; dist[i] = Integer.MAX_VALUE, ant[i] = -1, i++);

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
                if(posMin==-1)
                	break;
                vis[posMin] = true;

                // analizo a los adyacentes, actualizando su distancia en caso de ser menor a la
                // hasta ahora descubierta
                    
                for (int j = 0; j < tope; j++) {
                    if (!vis[j] && matAdyD[posMin][j].isExiste()) {
                        int sumaAcumulada = dist[posMin] + matAdyD[posMin][j].getmetros(); //para delivery dist[posMin] + 1
                        if (sumaAcumulada < dist[j]) {
                            dist[j] = sumaAcumulada;
                            ant[j] = posMin;
                        }
                    }
                }
            }
            if(!vis[posD]) {
            	ret = new Retorno(Resultado.ERROR_1);//no es conexo y no llego al destino;
            }else {
            	/*para ver el camino mas corto*/
            	String coordenadas = vertices[posD].toString();
            	while(ant[posD]!=-1) {
            		posD=ant[posD];
            		coordenadas=vertices[posD].toString() + "|" + coordenadas;
            	}
            	
            	ret = new Retorno(Resultado.OK);
            
            	ret.valorString = coordenadas;
            	
            	//para devolver metros
            	int posMinMovil=-1, minMovil = Integer.MAX_VALUE;
            	for(int j =0;j<tope;j++) {
            		if(vertices[j] instanceof Movil && ((Movil)vertices[j]).isDisponible() == true && dist[j]<dist[posMinMovil]) {
            			posMinMovil = j;
            			minMovil = dist[j];
            		}
            	}
            	if(posMinMovil == -1) {
            		return new Retorno(Resultado.ERROR_2);
            	}else {
            		ret.valorEntero = posMinMovil;
            	}
            }
            
			return ret;
        }
        
        
        public Retorno dijkstraCaminoDelivery(Punto origen, Punto destino) {
        	Retorno ret = new Retorno(Resultado.ERROR_1);

            int posO = buscarPos(origen);
            int posD = buscarPos(destino);

            // Armo los tres arreglos necesarios para realizar el algoritmo
            int[] dist = new int[tope];
            int[] ant = new int[tope];
            boolean[] vis = new boolean[tope];

            // inicializo los vectores
            for (int i = 0; i < tope; dist[i] = Integer.MAX_VALUE, ant[i] = -1, i++);

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
                if(posMin==-1)
                	break;
                vis[posMin] = true;

                // analizo a los adyacentes, actualizando su distancia en caso de ser menor a la
                // hasta ahora descubierta
                    
                for (int j = 0; j < tope; j++) {
                    if (!vis[j] && matAdyND[posMin][j].isExiste()) {
                        int sumaAcumulada = dist[posMin] + matAdyND[posMin][j].getminutos(); //para delivery dist[posMin] + 1
                        if (sumaAcumulada < dist[j]) {
                            dist[j] = sumaAcumulada;
                            ant[j] = posMin;
                        }
                    }
                }
            }
            if(!vis[posD]) {
            	ret = new Retorno(Resultado.ERROR_1);//no es conexo y no llego al destino;
            }else {
            	/*para ver el camino mas corto*/
            	String coordenadas = vertices[posD].toString();
            	while(ant[posD]!=-1) {
            		posD=ant[posD];
            		coordenadas=vertices[posD].toString() + "|" + coordenadas;
            	}
            	
            	ret = new Retorno(Resultado.OK);
            
            	ret.valorString = coordenadas;
            	
            	//para devolver metros
            	int posMinDelivery=-1, minDelivery = Integer.MAX_VALUE;
            	for(int j =0;j<tope;j++) {
            		if(vertices[j] instanceof Delivery && ((Delivery)vertices[j]).isDisponible() == true && dist[j]<dist[posMinDelivery]) {
            			posMinDelivery = j;
            			minDelivery = dist[j];
            		}
            	}
            	if(posMinDelivery == -1) {
            		return new Retorno(Resultado.ERROR_2);
            	}else {
            		ret.valorEntero = posMinDelivery;
            	}
            }
            
			return ret;
        }
            
    
    

    public boolean agregarDelivery(Delivery d) {
        if (!existeVertice(new Punto(d.coordX, d.coordY))) {
        int posH = posHueco(vertices);
        vertices[posH] = d;
        cant++;
        return true;
        }
       return false;
    }

    public boolean agregarMovil(Movil m) {
    	if (!existeVertice(new Punto(m.coordX, m.coordY))) {
            int posH = posHueco(vertices);
            vertices[posH] = m;
            cant++;
            return true;
            }
           return false;
    }

}
