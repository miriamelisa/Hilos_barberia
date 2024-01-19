package Hilos_Barberia;


class Barbero extends Thread {
	private int nombre;
    private ColaClientes cola;
    private int tiempoInicial;
    private boolean atendiendo;
    private long tiempoLimite = System.currentTimeMillis(); // Esto establecerá el tiempo límite correctamente
;

    public Barbero(int nombre, ColaClientes cola, int tiempoInicial) {
        super();
        this.nombre = nombre;
        this.cola = cola;
        this.tiempoInicial = tiempoInicial;
        this.atendiendo = true;
    }


	public int getNombre() {
		return nombre;
	}

	public void setNombre(int nombre) {
		this.nombre = nombre;
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

	@Override
    public void run() {
		long tiempoInicio = System.currentTimeMillis();
	    tiempoLimite = tiempoInicio + 2000; // Establecer el tiempo límite en 1 minuto (60000 milisegundos)

	    while (!Thread.interrupted() && atendiendo) {
	        try {
	            Thread.sleep(1000);

	            if (!cola.getColaClientes().isEmpty()) {
	                Cliente clienteAtendido = cola.getColaClientes().get(0);
	                System.out.println("El barbero " + this.nombre + " comienza a atender al " +
	                        clienteAtendido.toString());
	                Thread.sleep(1000);
	                System.out.println("El barbero " + this.nombre + " terminó de atender a  " +
	                        clienteAtendido.toString());
	                cola.eliminar();
	            } else {
	                System.out.println("El barbero " + this.nombre + " esta dormido mientras espera a los clientes.");
	            }

	            
	            
	        } catch (InterruptedException e) {
	            System.out.println("Interrupción del hilo del barbero.");
	            Thread.currentThread().interrupt(); // Restablecer la bandera de interrupción
	        } catch (Exception e) {
	            System.out.println("Error en el try-catch del barbero");
	            Thread.currentThread().interrupt();
	        }// Verificar si ha pasado el tiempo límite
            if (System.currentTimeMillis() >= tiempoLimite) {
                System.out.println("El barbero " + this.nombre + " ha cerrado la barbería.");
                this.interrupt();
                Cliente.interrupted();
                break;
                // Salir del bucle
	    }
	}
    }
}
