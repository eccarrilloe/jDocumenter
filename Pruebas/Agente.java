
public class Agente extends Comportamiento{
	
	private int comportamiento;
	private int gananciasNetas;
	private int ultimaGanancia;
	private int criterioAlto;
	
	public Agente(int tipoJuego, int criterioAlto){		
		comportamiento=tipoJuego;
		ultimaGanancia = 0;
		gananciasNetas = ultimaGanancia;
		this.criterioAlto = criterioAlto;
	}
	
	public void jugar(Entorno percepcion){
		comportamiento = evaluarEntorno(percepcion);
		if(comportamiento == 2){
			int decision = (int) Math.random()*2;
			if(decision==0){
				comportamiento = 0;
			}else{
				comportamiento = 1;
			}
		}
	}

	public int evaluarEntorno(Entorno percepcion){
		int resultado = 2;
		if(ultimaGanancia == 0){
			resultado = 0;
		}else{
			int criterio = (int) Math.random()*2;
			
			if(criterio == 0){
				if(percepcion.getRecursos()<criterioAlto){
					resultado = 1;
				}else{
					resultado = 0;
				}
			}
		}
		
		return resultado;
	}

	public int getComportamiento() {
		return comportamiento;
	}

	public void setComportamiento(int comportamiento) {
		this.comportamiento = comportamiento;
	}

	public int getGananciasNetas() {
		return gananciasNetas;
	}

	public void setGananciasNetas(int gananciasNetas) {
		this.gananciasNetas = gananciasNetas;
	}

	public int getUltimaGanancia() {
		return ultimaGanancia;
	}

	public void setUltimaGanancia(int ultimaGanancia) {
		this.ultimaGanancia = ultimaGanancia;
		gananciasNetas += this.ultimaGanancia;
	}

	@Override
	public String toString() {
		return "Agente [comportamiento=" + comportamiento + ", ultimaGanancia="
				+ ultimaGanancia + " total="+gananciasNetas+"]";
	}
	
}
