package Hilos_Barberia;

class Cliente extends Thread {
	private int nombre;

	public Cliente(int nombre) {
		this.nombre = nombre;
	}

	public Cliente() {
	}

	public int getNombre() {
		return nombre;
	}

	public void setNombre(int nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Cliente " + nombre;
	}

}