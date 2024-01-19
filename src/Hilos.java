import java.util.concurrent.Semaphore;
 
//Actualizar desde tres hilos diferentes on un bucle, para que se vaya actualizando de manera ordenada
 
public class Hilos extends Thread{
 
	//Creamos una variable estática que pertenezca a toda la clase (Variable compartida)
	//La van a actualizar todos los hilos
	public static int contador =1;
	
	//Hacemos lo mismo con el semáforo
	//Se inicializa el semáforo a 1 solo uno de los hilos podrá actualizar la variable
	private static Semaphore semaforoMutex = new Semaphore(1); //Este es Mutex, es binario
	
	
	//Vamos sobreescribir el método run
	public void run() {
		for(int i = 1;i<=10;i++) {
			try {
				//lo bloquea si no es 1
				semaforoMutex.acquire();
				//Ponemos un sleep para ver como va
				sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(contador++);
			
			//Lo liberamos
			semaforoMutex.release();
		}
	}
	public static void main(String[] args) {
		
		
		//Inicializamos 3 hilos
		new Hilos().start();
		new Hilos().start();
		new Hilos().start();
 
	}
 
}