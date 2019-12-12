package labarberia;

import org.apache.commons.math3.distribution.NormalDistribution;

public class Cliente extends Thread{

	public static NormalDistribution distribucionNormal;
	public static Barberia barberia;
	public int numeroCliente;

	public Cliente(int idCliente){
		this.numeroCliente=idCliente;
	}
	public NormalDistribution getDistribucionNormal() {
	return distribucionNormal;
	}
	
	//metodo run en el que creo y destruyo cloente
	//@Override
	public void run(){
		System.out.println("El cliente "+numeroCliente+" se ha creado.");

		try{
			while(true){
				accionesCliente();
			}
		}catch (InterruptedException e){
			System.out.println("El cliente "+numeroCliente+" ha sido destruido.");
		}
	}
	//lo que hace el client 
	private synchronized void accionesCliente() throws InterruptedException{
		//espera entre cada vez que entra  a la baberberia que si no entraba continuadamente
		tiempoDuermeCliente();
		System.out.println("El cliente "+numeroCliente+" llega a la barbería.");
		int numeroBarberoLibre=barberia.buscoBarberoLibre();
		//si hay barbero libre atiendo
		if(numeroBarberoLibre!=barberia.ocupadoNum){
			AtenderCliente(numeroBarberoLibre);
		//si no hay barbero libre hace:
		}else{
			//si hay sitios en la barberia se sienta
			if(barberia.getNumeroSillasBarberia()>0){
				System.out.println("El cliente "+numeroCliente+" se sienta en una silla de espera.");
				barberia.setNumeroSillas(barberia.getNumeroSillasBarberia()-1);
				esperarTurno();
			}else{
				System.out.println("El cliente "+numeroCliente+" se marcha sin ser atendido.");
			}
		}
	}
	
	//metodo para atender al cliente
	private synchronized void AtenderCliente(int numeroBarberoLibre) throws InterruptedException {
		//System.out.println(numeroBarberoLibre+ "numero barbero libreEEEEEEEEEEEEEEEEEE");
		//atiendo al cliente
		System.out.println("El barbero "+BarberoLetra(numeroBarberoLibre+1)+" atiende al cliente "+numeroCliente+".");
		//tiempo que tarda en cortar el pelo al cliente
		Barbero.tiempoDuermenBarbero();
		//acaba de atender al cliente y el barbero se pone a dormir
		System.out.println("El barbero "+BarberoLetra(numeroBarberoLibre+1)+" ha cortado el pelo al cliente "+numeroCliente+".");
		System.out.println("El barbero "+BarberoLetra(numeroBarberoLibre+1)+" se pone a dormir.");
		barberia.dejarLibreBarbero(numeroBarberoLibre);
	}
	
	//para saber que barbero atiende a cliente
		public char BarberoLetra(int i) {
			char barbero = 'A';
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
			return barbero;
		}
		
	//cuando se sienta espera
	private synchronized void esperarTurno() throws InterruptedException{
		while (!barberia.libre){
			try{
				wait();
			} catch (InterruptedException e){	
			}
			int auxBarberoLib=barberia.buscoBarberoLibre();
			if (auxBarberoLib!=barberia.ocupadoNum){
				barberia.setNumeroSillas(barberia.getNumeroSillasBarberia()+1);
				AtenderCliente(auxBarberoLib);
			}else{
				wait();
			}
		}
	}
	
	//tiempo que duerme cliente sigue distribucionNormal
	private void tiempoDuermeCliente() throws InterruptedException{
		long duermecliente;
		duermecliente=(long)distribucionNormal.sample();
		if(duermecliente<0){
			duermecliente=duermecliente*-1;
		}
		Thread.sleep(duermecliente);	
	}
}
