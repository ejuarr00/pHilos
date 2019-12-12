package labarberia;
import org.apache.commons.math3.distribution.ExponentialDistribution;

public class Barbero extends Thread {

	public static ExponentialDistribution distribucionExponencial;
	public static Barberia barberia;
	public char idBarbero;

	// barbero pasandole un numero me dice que letra es (es su id)
	public Barbero(int i) {
		char barbero = 0;
		switch(i){
		case 1:
			barbero='A';
			break;
		case 2:
			barbero='B';
			break;
		case 3:
			barbero='C';
			break;
		case 4:
			barbero='D';
			break;
		case 5:
			barbero='E';
			break;
		case 6:
			barbero='F';
			break;
		case 7:
			barbero='G';
			break;
		case 8:
			barbero='H';
			break;
		case 9:
			barbero='I';
			break;
		case 10:
			barbero='J';
			break;
		case 11:
			barbero='K';
			break;
		case 12:
			barbero='L';
			break;
		case 13:
			barbero='M';
			break;
		case 14:
			barbero='N';
			break;
		case 15:
			barbero='Ñ';
			break;
		case 16:
			barbero='O';
			break;
		case 17:
			barbero='P';
			break;
		case 18:
			barbero='Q';
			break;
		case 19:
			barbero='R';
			break;
		case 20:
			barbero='S';
			break;
		case 21:
			barbero='T';
			break;
		case 22:
			barbero='U';
			break;
		case 23:
			barbero='V';
			break;
		case 24:
			barbero='W';
			break;
		case 25:
			barbero='X';
			break;
		case 26:
			barbero='Y';
			break;
		case 27:
			barbero='Z';
			break;
		default:
			System.exit(0);
		}
		this.idBarbero=barbero;
	}
	
	public ExponentialDistribution getDistribucionExponencial() {
	return distribucionExponencial;
	}
	
	//metodo run en el que creo, pongo a dormir y destruyo barbero
	//@Override
	public void run(){
		//creo barbero y lo pongo a dormir
		System.out.println("El barbero "+idBarbero+" se ha creado.");
		System.out.println("El barbero "+idBarbero+" se pone a dormir.");

		synchronized (this) {
			
		}
		while(!Thread.interrupted()){

		}
		System.out.println("El barbero "+idBarbero +" ha sido destruido.");
		//a veces acaba bien y otra no por eso lo del System.exit(0)
		System.exit(0);
	}

	//tiempo que duerme el barbero sigue la distribucionExponencial
	static void tiempoDuermenBarbero() throws InterruptedException {
		long duermeBarbero;
		duermeBarbero=(long)distribucionExponencial.sample();
		//si es negativo lo paso a positivo
		if(duermeBarbero<0){
			duermeBarbero=duermeBarbero*-1;
		}
		Thread.sleep(duermeBarbero);
	}
}

