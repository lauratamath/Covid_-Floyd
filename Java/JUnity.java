/**
*@author: Laura Tamath
*@since 18/05/2020
*@version 18/05/2020
**/

import java.io.IOException;
import java.util.ArrayList;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class JUnity{

	@Test
    public void testCentroGrafo() throws IOException {
        Floyd instance = new Floyd();
        Departamentos depa = new Departamentos();
        ArrayList<Departamentos> city = instance.crearListado();
        ArrayList<String> departamento = depa.crearLista(deps);
        long[][] matriz = instance.crearMatriz(departamento, deps);
        
        
        //Ver cual es el centro
        int res = departamento.indexOf("Guatemala");
        int result = instance.centerGraph(matriz);
        assertEquals(res, result);
    }

	@Test
    public void testFloyd() throws IOException {
        Floyd instance = new Floyd();
        Departamentos depa = new Departamentos();
        ArrayList<Departamentos> deps = instance.crearListado();
        ArrayList<String> departamento = depa.crearLista(deps);
        long[][] matriz = instance.crearMatriz(departamento, deps);
        String origen = "Guatemala";
        String destino = "SantaRosa";
        
        String res = "\nDe Guatemala ---> Coban debe irse por: Guatemala, SantaRosa\n";
        String result = instance.aFloyd(matriz, departamento, origen, destino);
        assertEquals(res, result);
    }


}