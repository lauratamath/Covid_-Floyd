/**
*@author: Laura Tamath
*@since 16/05/2020
*@version 16/05/2020
**/
import java.util.ArrayList;

public class Departamentos{
	private String origen;
	private String destino;
	private int distancia;

	public Departamentos(){
		//Constructor default
	}

	public Departamentos(String origen, String destino, int distancia){
		this.origen = origen;
		this.destino = destino;
		this.distancia = distancia;
	}

	public String getOrigen() {
        return origen;
    }

    public String getDestino() {
        return destino;
    }

    public int getDistancia() {
        return distancia;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    @Override
    public String toString() {
        return "Origen:" + origen + ", destino: " + destino + ", distancia: " + distancia;
    }
    
    public ArrayList crearLista(ArrayList<Departamentos> departamento){
        ArrayList<String> departamentos = new ArrayList<>();
        
        for(Departamentos c: departamento){
            if(!departamentos.contains(c.getOrigen())){
                departamentos.add(c.getOrigen());
            }
            if(!departamentos.contains(c.getDestino())){
                departamentos.add(c.getDestino());
            }
        }
        
        return departamentos;
    }
	
}