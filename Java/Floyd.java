/**
*@author: Laura Tamath
*@since 16/05/2020
*@version 18/05/2020
*referencia: Transversals of directed graph, PDF, compartido por Ing. Douglas: 
**/

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Floyd{

	 public String aFloyd(long[][] matriz, ArrayList<String> departamento, String origen, String destino){
        int vertices = matriz.length;
        long matrizAdy[][] = matriz;
        
        int restriccion1 = departamento.indexOf(origen);
        int restriccion2 = departamento.indexOf(destino);
        
        String caminos[][] = new String[vertices][vertices];
        String caminosAux[][] = new String[vertices][vertices];
        String caminoRecorrido = "";
        String cadena = "", cami = "";
        
        int i, j, k;
        float temp1, temp2, temp3, temp4, minimo;
        
        for(i = 0; i < vertices; i++){
            for(j = 0; j < vertices; j++){
                caminos[i][j] = "";
                caminosAux[i][j] = "";
            }
        }
        
        for (k = 0; k < vertices; k++) {
            for (i = 0; i < vertices; i++) {
                for (j = 0; j < vertices; j++) {
                    temp1 = matrizAdy[i][j];
                    temp2 = matrizAdy[i][k];
                    temp3 = matrizAdy[k][j];
                    temp4 = temp2 + temp3;
                    
                    minimo = Math.min(temp1, temp4);
                    if(temp1 != temp4){
                        if(minimo == temp4){
                            caminoRecorrido = "";
                            caminosAux[i][j] = k + "";
                            caminos[i][j] = cRecor(i, k, caminosAux, caminoRecorrido) + (k + 1);                            
                        }
                    }
                    matrizAdy[i][j] = (long) minimo;
                }
            }
        }
        
        ArrayList<Integer> camino = new ArrayList<>();
        
        //Ver la ruta para llegar a su destino
        for (i = 0; i < vertices; i++) {
            for (j = 0; j < vertices; j++) {
                if(matrizAdy[i][j] != 1000000000){
                    if(i != j){
                        if(caminos[i][j].equals("") && (j == restriccion2) && (i == restriccion1)){
                            cami += "De " + departamento.get(i) + " ---> " + departamento.get(j) + " debe irse por: " + departamento.get(i) + ", " + departamento.get(j) + "\n";
                        }else if(!caminos[i][j].equals("") && (j == restriccion2) && (i == restriccion1)){
                            String demas = caminos[i][j];
                            if(!demas.contains(",")){
                                camino.add(Integer.parseInt(demas));
                            }
                            while(demas.contains(",")){
                                String walk = demas.substring(0, demas.indexOf(","));
                                demas = demas.substring(demas.indexOf(",") + 2);
                                camino.add(Integer.parseInt(walk));
                                if(!demas.contains(",")){
                                    camino.add(Integer.parseInt(demas));
                                }
                            }
                            
                            String c = "";
                            for(Integer in: camino){
                                
                                c += departamento.get(in - 1) + ", ";
                            }
                            cami += "De " + departamento.get(i) + " ---> " + departamento.get(j) + " debe irse por: " + departamento.get(i) + ", " + c + departamento.get(j) + "\n";
                        }
                    }
                }
            }
        }
        return "\n" + cami;
    }
    
    
    public String cRecor(int i, int k, String[][] caminosAux, String caminoRecorrido){
        if(caminosAux[i][k].equals("")){
            return "";
        }else{
            caminoRecorrido += cRecor(i, Integer.parseInt(caminosAux[i][k]), caminosAux, caminoRecorrido) + (Integer.parseInt(caminosAux[i][k]) + 1) + ", ";
            return caminoRecorrido;
        }
    }
    
    public long[][] crearMatriz(ArrayList<String> departamento, ArrayList<Departamentos> cities){
        long matriz[][] = new long[departamento.size()][departamento.size()];
        
        for (int i = 0; i < departamento.size(); i++) {
            for (int j = 0; j < departamento.size(); j++) {
                if(i == j){
                    matriz[i][j] = 0;
                }else{
                    String origen = departamento.get(i);
                    String destino = departamento.get(j);
                    int distancia = 999999999;
                    for(Departamentos c: cities){
                        if(origen.equals(c.getOrigen()) && destino.equals(c.getDestino())){
                            distancia = c.getDistancia();
                        }
                    }
                    matriz[i][j] = distancia;
                }
            }
        }
        
        return matriz;
    }
    
    public boolean vExistencia(String origen, String destino, ArrayList<String> departamento){
        
        boolean existencia;
        
        existencia = departamento.contains(origen) && departamento.contains(destino);
        
        return existencia;
    }
    
    public String verMatriz(long[][] matriz){
        
        int fila = matriz.length;
        
        String cadena = "";
        
        for(int x = 0; x < fila; x++){
            for(int y = 0; y < fila; y++){
                if(matriz[x][y]==999999999){
                    cadena += -1 + "\t";
                }else{
                    cadena += matriz[x][y] + "\t";
                }
                
            }
            cadena += "\n";
        }
        if(cadena.equals("")){
            cadena = "No hay datos para mostrar.";
        }
        return cadena;
    }
    
    public int centroGrafo(long[][] matriz){
        ArrayList<Long> suma = new ArrayList<>();
        ArrayList<Long> maximo = new ArrayList<>();
        long max;
        int cont = 0;
        
        while(cont != matriz.length){
            max = 0;
            for (int i = 0; i < matriz.length; i++) {
                suma.add(matriz[i][cont]);
            }
            for(Long l: suma){
                if((l <= 9999999) && (l != 0)){
                    if(l > max){
                        max = l;
                    }
                }
            }
            maximo.add(max);
            suma.clear();
            cont++;
        }
        
        
        max = 0;
        cont = 0;
        for(Long l: maximo){
            if(l > max){
                max = l;
            }
        }
        int pos = maximo.indexOf(max);
        
        long min = max;
        
        for (int i = 0; i < matriz.length; i++) {
            if((matriz[i][pos]<=999999) && (matriz[i][pos]!=0)){
                if(matriz[i][pos] < min){
                    min = matriz[i][pos];
                }
            }
        }
        
        int resultado = 0;
        for (int i = 0; i < matriz.length; i++) {
            if(matriz[i][pos] == min){
                resultado = i;
                break;
            }
        }
        
        return resultado;
    }
    
    public ArrayList<Departamentos> crearListado() throws FileNotFoundException, IOException{
        ArrayList<Departamentos> departamento = new ArrayList<>();
        File archivo = new File ("guategrafo.txt"); //Los datos en este .txt son hipoteticos
        FileReader reader = new FileReader(archivo);
        BufferedReader buf = new BufferedReader(reader);
        String linea = "";
        Scanner scanner = new Scanner(reader);
        
        String depa1;
        String depa2;
        int distancia;
        int cont = 0;
        
        while (scanner.hasNextLine()) {
            linea = scanner.nextLine();
            
            depa1 = linea.substring(0, linea.indexOf(" "));
            linea = linea.substring(linea.indexOf(" ") + 1, linea.length());
            
            depa2 = linea.substring(0, linea.indexOf(" "));
            linea = linea.substring(linea.indexOf(" ") + 1, linea.length());
            
            distancia = Integer.parseInt(linea.substring(0, linea.length()));
            
            cont++;
            
            departamento.add(new Departamentos(depa1, depa2, distancia));
            
            reader.close();
            buf.close();
        }
        
        return departamento;
    }
}