package Hilos_Barberia;

public class Main {

	public static void main(String[] args) {
		int tiempoInicial = (int) System.currentTimeMillis();
		int numSillas = 3;
		ColaClientes cola1 = new ColaClientes(1, numSillas);
		Barbero barbero = new Barbero(1, cola1, tiempoInicial);
		barbero.start();
		InsertarClientes insertarCli = new InsertarClientes(cola1, tiempoInicial);
		insertarCli.start();
		try {
			barbero.join();
			insertarCli.join();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}
}


