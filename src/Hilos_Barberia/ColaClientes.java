package Hilos_Barberia;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class ColaClientes {
	private ArrayList<Cliente> colaClientes;
	private int nombre;
	private int numSillas;
	private Semaphore semaphore;

	public ColaClientes(int nombre, int numSillas) {
		this.colaClientes = new ArrayList<>();
		this.nombre = nombre;
		this.numSillas = numSillas;
		this.semaphore = new Semaphore(1); // Inicializado con un permiso
	}

	public void insertar(Cliente cliente) {
		try {
			semaphore.acquire(); // Adquirir un permiso
			if (colaClientes.size() != numSillas) {
				int clientesEnCola = 1;
				clientesEnCola += colaClientes.size();
				System.out.println("Están dentro de la barbería: " + clientesEnCola + " clientes.");

				System.out.println("·····································");
				colaClientes.add(cliente);
				this.notify();
			} else {
				System.out.println(cliente.toString() + " se fue porque no habia sillas");
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		} finally {
			semaphore.release();
		}
	}

	public void eliminar() {
		try {
			semaphore.acquire();
			while (colaClientes.isEmpty()) {
				try {
					this.wait();
				} catch (Exception e) {
					System.out.println("Error al ejecutar el this.wait()");
				}
			}
			if (colaClientes.size() > 0) {
				Cliente clienteEliminado = colaClientes.remove(0);
				System.out.println("Cliente " + clienteEliminado.getNombre() + " salió de la barberia.");
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		} finally {
			semaphore.release();
		}
	}

	public ArrayList<Cliente> getColaClientes() {
		return colaClientes;
	}

	public void setColaClientes(ArrayList<Cliente> colaClientes) {
		this.colaClientes = colaClientes;
	}

	public int getNombre() {
		return nombre;
	}

	public void setNombre(int nombre) {
		this.nombre = nombre;
	}

	public int getNumSillas() {
		return numSillas;
	}

	public void setNumSillas(int numSillas) {
		this.numSillas = numSillas;
	}

	public Semaphore getSemaphore() {
		return semaphore;
	}

	public void setSemaphore(Semaphore semaphore) {
		this.semaphore = semaphore;
	}

}
