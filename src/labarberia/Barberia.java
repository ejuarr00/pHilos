package labarberia;

public class Barberia {

	private static Barberia  instance;
	int numeroSillasBarberia;
	Barbero []barberos;
	boolean []barberoTrabajando;
	public boolean libre=false;
	public int ocupadoNum= -666;

	private Barberia(){

	}

	public static Barberia getBarberia(){
		if (instance==null)
			instance=new Barberia();
		return instance;
	}

	public boolean[] getBarberoTrabajando() {
		return barberoTrabajando;
	}

	public void setBarberoTrabajando(boolean[] barberoTrabajando) {
		this.barberoTrabajando = barberoTrabajando;
	}

	public Barbero[] getBarberos() {
		return barberos;
	}

	public void setBarberos(Barbero[] barberos) {
		this.barberos=barberos;
		this.barberoTrabajando=new boolean[barberos.length];
	}

	public synchronized int getNumeroSillasBarberia() {
		return numeroSillasBarberia;
	}

	public synchronized void setNumeroSillas(int nextInt){
		this.numeroSillasBarberia=nextInt;
	}

	//busco en el vector de booleanos de barberos si alguno esta libre
	public synchronized int buscoBarberoLibre(){
		for (int i=0;i<barberoTrabajando.length;i++){
			if(!barberoTrabajando[i]){
				barberoTrabajando[i]=true;
				notifyAll();
				return i;
			}
		}
		return ocupadoNum;
	}

	//dejo en el vector de barberos el que corresponda a false por que no esta ocupado y la variable libre a true
	public synchronized void dejarLibreBarbero(int numeroBarberoLibre) {		
		barberoTrabajando[numeroBarberoLibre]=false;
		libre=true;
	}

	//barbero que esta libre se pone como ocupado 
	public synchronized boolean barberoEstaLibre() {
		if (libre){
			notify();
			libre=false;
			return true;
		}
		return false;
	}
}
