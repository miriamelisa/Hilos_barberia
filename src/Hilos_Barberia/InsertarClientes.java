package Hilos_Barberia;

public class InsertarClientes extends Thread {
	private ColaClientes cola;
	private int tiempoInicial;

	public InsertarClientes(ColaClientes cola, int tiempoInicial) {
		super();
		this.cola = cola;
		this.tiempoInicial = tiempoInicial;
	}

	@Override
	public void run() {
		int id = 1;
		while (true) {
			try {
				Thread.sleep(1000);
				Cliente cli = new Cliente(id);
				id++;

				System.out.println("Llego " + cli.getNombre() + " clientes  a la cola.");
				cola.insertar(cli);

			} catch (Exception e) {
			}
		}
	}

	public ColaClientes getCola() {
		return cola;
	}

	public void setCola(ColaClientes cola) {
		this.cola = cola;
	}

	public int getTiempoInicial() {
		return tiempoInicial;
	}

	public void setTiempoInicial(int tiempoInicial) {
		this.tiempoInicial = tiempoInicial;
	}

}
