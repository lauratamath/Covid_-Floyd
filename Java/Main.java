/**
*@author: Laura Tamath
*@since 16/05/2020
*@version /05/2020
*referencias https://www.cimat.mx/~cesteves/cursos/algoritmos/pdf/ASSP_FloydWarshall.pdf: 
**/

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main{
 	public static void main(String[] args) throws IOException {
        
        File archivo = new File ("guategrafo.txt");
        ArrayList<Departamentos> departamento = new ArrayList<>();
        ArrayList<String> departamentos = new ArrayList<>();
        Departamentos deps = new Departamentos();
        

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
        
        
        
        Scanner sc = new Scanner(System.in);
        int op = 0;
        int seleccion;
        
        Floyd af = new Floyd();
        long matriz[][];
        
        OUTER:
        while (true) {
            System.out.println("\nDebido al Covid-19, se mostrara una planificacion de la ruta mas corta entre hospitales");
            System.out.println("***************************************************************************************");
            System.out.println("1. Mostrar ruta mas corta entre departamentos.");
            System.out.println("2. Indicar el departamento que esta en el centro del grafo.");
            System.out.println("3. Modificar grafo.");
            System.out.println("4. Mostrar matriz de adyacencia.");
            System.out.println("5. Salir");
            seleccion = sc.nextInt();
            
            switch (seleccion) {
                case 1:
                    System.out.println("\t1. Ingrese el departamento de origen.");
                    String o = sc.nextLine();
                    o = sc.nextLine();
                    System.out.println("\t2. Ingrese el departamento de destino.");
                    String d = sc.nextLine();
                    
                    departamentos.clear();
                    departamentos = deps.crearLista(departamento);
                    matriz = af.crearMatriz(departamentos, departamento);
                    
                    if(af.vExistencia(o, d, departamentos)){
                        
                        System.out.println(af.aFloyd(matriz, departamentos, o, d));
                    }else{
                        System.out.println("Dichos departamentos no se encuentran en 'guategrafo'.");
                    }
                    break;
                case 2:
                    departamentos.clear();
                    departamentos = deps.crearLista(departamento);
                    matriz = af.crearMatriz(departamentos, departamento);
                    af.centroGrafo(matriz);
                    
                    System.out.println("El departamento central es: " + departamentos.get(af.centroGrafo(matriz)));
                    
                    break;
                case 3:
                    String seleccion2 = "";
                    System.out.println("\ta. Hay interrupcion de trafico entre un par de departamentos, debido a algun desastre natural.");
                    System.out.println("\tb. Establecer nueva conexion entre departamentos, para una mejor movilizacion.");
                    seleccion2 = sc.next();
                    
                    if("a".equals(seleccion2.toLowerCase())){
                        System.out.println("Ingrese el departamento de origen: ");
                        String ori = sc.next();
                        System.out.println("Ingrese el departamento de destino: ");
                        String dest = sc.next();
                        
                        boolean hubo = false;
                        for(Departamentos c: departamento){
                            if((c.getOrigen().equals(ori)) && (c.getDestino().equals(dest))){
                                departamento.remove(c);
                                hubo = true;
                                System.out.println("Se ha establecido la interrupcion correctamente.");
                                break;
                            }
                        }
                        if(hubo == false){
                            System.out.println("Estos departamentos no pertenecen al grafo.");
                        }
                    }else if("b".equals(seleccion2.toLowerCase())){
                        System.out.println("Ingrese el departamento de origen.");
                        String ori = sc.next();
                        System.out.println("Ingrese el departamento de destino.");
                        String dest = sc.next();
                        System.out.println("Ingrese la distancia entre " + ori + " y " + dest + ":");
                        int dist = sc.nextInt();
                        
                        departamento.add(new Departamentos(ori, dest, dist));
                        System.out.println("Se agregaron correctamente los departamentos y  distancia respectiva.");
                    }
                    
                    break;
                case 4:
                    departamentos.clear();
                    departamentos = deps.crearLista(departamento);
                    matriz = af.crearMatriz(departamentos, departamento);
                    System.out.println("La matriz de adyacencia es:");
                    for(String s: departamentos){
                        System.out.print(s + " ");
                    }
                    System.out.println("");
                    System.out.println(af.verMatriz(matriz));
                    break;
                case 5:
                    break OUTER;
                default:
                    break;
            }
            
            
            System.out.println("--------------------------------------------------------");
        }
    }
	
}
