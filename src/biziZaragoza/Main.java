package biziZaragoza;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Formatter;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Esta clase presenta un programa que sirve para realizar recuentos
 * con los datos publicos de Bizi Zaragoza.
 * 
 * @Author: Nerea García Barranco
 */

public class Main {
	static Scanner entrada;
	/**
	 * Pre: ---
	 * Post: Este metodo main muestra por pantalla un menu para que 
	 * el usuario elija la accion deseada hasta que decida apagar
	 * el programa introduciendo un 4.
	 */
	public static void main(String[] args) {
		System.out.println("¡Bienvenido al programa de trabajo "
				+ "con ficheros de texto de Bizi Zaragoza!");
		while(true) {
			mostrarMenu();
			try {
				entrada = new Scanner(System.in);
				int menu = entrada.nextInt();
				if (menu == 1) {
					ficherosPrueba();
				} else if (menu == 2) {
					contarUsos();
				} else if (menu == 3) {
					contarUsosPorUsuario();
				} else if (menu == 4) {
					break;
				} else {
					System.out.println("Introduce un numero correcto, ¡por favor!");
				}
			// Por si el usuario introduce una cadena de caracteres
			} catch (InputMismatchException e) {
				System.out.println("Introduce un dato valido, ¡por favor!\n");
			}	
		}
	}
	
	/**
	 * Pre: ---
	 * Post: Este metodo muestra por pantalla el menu del programa.
	 */
	public static void mostrarMenu() {
		System.out.println("¿Qué operacion desea realizar?");
		System.out.println("Obtener ficheros de prueba [introduzca 1]");
		System.out.println("Contar usos de un fichero [introduzca 2]");
		System.out.println("Contar usos por usuario de un fichero [introduzca 3]");
		System.out.println("Apagar el programa [introduzca 4]");
	}
	
	/**
	 * Pre: ---
	 * Post: Este metodo sirve para crear dos ficheros nuevos sin interaccion
	 * por parte del usuario. Uno de ellos contiene 10 lineas y el otro 2000
	 * y sirven para realizar pruebas de manera mas rapida.
	 */
	public static void ficherosPrueba() {
		// Doble barra en las rutas porque si no lo detecta como caracter especial
		String ruta16 = "C:\\Users\\Nerea\\Desktop\\usos-16.csv";
		String ruta17 = "C:\\Users\\Nerea\\Desktop\\usos-17.csv";
		String rutaNueva16 = "C:\\Users\\Nerea\\Desktop\\pruebas-10.csv";
		String rutaNueva17 = "C:\\Users\\Nerea\\Desktop\\pruebas-2000.csv";
		// Creamos el primer archivo de prueba de 10 lineas
		try {
			File fichero = new File (ruta16);
			Formatter fichero2 = new Formatter (rutaNueva16);
			// Asociamos el fichero a un objeto de tipo Scanner para poder leerlo
			Scanner f = new Scanner(fichero);
			// El bucle tiene 10 iteraciones porque solo queremos 10 lineas
			for (int i = 0; i < 10; i++) {
				String linea = f.nextLine();
				fichero2.format(linea + "\n");
				fichero2.flush();
			}
			f.close();
			fichero2.close();
		} catch (FileNotFoundException e) {
			System.out.println("No se ha podido acceder al fichero.");
			e.printStackTrace();
		}	
		// Creamos el segundo archivo de prueba de 2000 lineas
		try {
			File fichero = new File (ruta17);
			Formatter fichero2 = new Formatter (rutaNueva17);
			// Asociamos el fichero a un objeto de tipo Scanner para poder leerlo
			Scanner f = new Scanner(fichero);
			// El bucle tiene 2000 iteraciones porque queremos 2000 lineas
			for (int i = 0; i < 2000; i++) {
				String linea = f.nextLine();
				fichero2.format(linea + "\n");
				fichero2.flush();
			}
			f.close();
			fichero2.close();
		} catch (FileNotFoundException e) {
			System.out.println("No se ha podido acceder al fichero.");
			e.printStackTrace();
		}	
	}
	
	/**
	 * Pre: ---
	 * Post: Este metodo recorre un fichero de usos del sistema Bizi
	 * de Zaragoza dado por el usuario y muestra por consola el numero 
	 * total de usos, el numero de traslados normales y el numero de 
	 * traslados circulares. Para ello se apoya en un objeto de la clase
	 * UsoBizi cuyos atributos se machacan cada vez que se lee una linea.
	 */
	public static void contarUsos() {
		System.out.println("Escriba la ruta y el nombre de un fichero "
				+ "de usos del sistema Bizi de Zaragoza:");
		String rutaUsos = entrada.next();
		File ficheroUsos = new File (rutaUsos);
		try {
			// Asociamos el Scanner al fichero para poder leerlo
			Scanner f = new Scanner(ficheroUsos);
			int contadorTotal = 0;
			int contadorCirculares = 0;
			int contadorTraslados = 0;
			// Mientras el fichero tenga lineas
			while(f.hasNextLine()) {
				String uso = f.nextLine();
				if (contadorTotal != 0) {
					// Separamos los campos por su separador [;]
					String[] datos = uso.split(";");
					// Obtenemos los datos que nos interesan
					int usuario = Integer.parseInt(datos[0]);
					int estacionRetiro = Integer.parseInt(datos[2]);
					int estacionAnclaje = Integer.parseInt(datos[4]);
					// Con esos datos creamos un objeto
					UsoBizi usoObjeto = new UsoBizi (usuario, estacionRetiro, 
							 estacionAnclaje);
					// Si las estaciones son iguales es un uso circular
					if (usoObjeto.getEstacionRetiro() 
							== usoObjeto.getEstacionAnclaje()) {
						contadorCirculares++;
					} // Si no cuenta como traslado
					else 
					{
						contadorTraslados++;
					}
				}
				// Cada linea es un registro 
				contadorTotal++;
			}
			f.close();
			System.out.println("Número de usos como traslado: " + contadorTraslados);
			System.out.println("Número de usos circulares: " + contadorCirculares);
			// A contadorTotal se le resta 1 porque la cabecera del csv no cuenta
			System.out.println("Número total de usos: " + (contadorTotal - 1));		
		} catch (FileNotFoundException e) {
			System.out.println("No se ha podido acceder al fichero.");
			e.printStackTrace();
		}		
	}
	
	/**
	 * Pre: ---
	 * Post: Este metodo pide al usuario un archivo de registro de usos
	 * de Bizi Zaragoza y muestra por pantalla el numero de usuarios
	 * diferentes que hay en ese fichero y de ellos los 15 usuarios que 
	 * han cogido mas veces una bicicleta, su numero total de usos y 
	 * el numero de traslados normales y circulares. 
	 */
	public static void contarUsosPorUsuario() {
		System.out.println("Escriba la ruta y el nombre de un fichero "
				+ "de usos del sistema Bizi de Zaragoza:");
		String rutaUsuario = entrada.next();
		File ficheroUsuarios = new File (rutaUsuario);
		try {
			Scanner f = new Scanner(ficheroUsuarios);
			ArrayList<UsuarioBizi> usuarios = new ArrayList<UsuarioBizi>();
			ArrayList<UsoBizi> datosFichero = new ArrayList<UsoBizi>();
			int contador = 0;
			while(f.hasNextLine()) {
				String uso = f.nextLine();
				// Asi evitamos la cabecera del archivo
				if (contador != 0) {
					String[] datos = uso.split(";");
					// Guardamos los atributos que nos interesan (las fechas no)
					int identificador = Integer.parseInt(datos[0]);
					int estacionRetiro = Integer.parseInt(datos[2]);
					int estacionAnclaje = Integer.parseInt(datos[4]);
					/*
					 * A la vez que leemos el fichero vamos almacenando los registros
					 * en objetos de tipo UsoBizi para luego sacar los usos de cada
					 * usuario desde este ArrayList en vez de desde el fichero. 
					 */
					UsoBizi usoObjeto = new UsoBizi (identificador, estacionRetiro, 
							estacionAnclaje);
					datosFichero.add(usoObjeto);
					/*
					 * Comprobamos si el usuario ha sido introducido ya o no en el 
					 * vector de tipo UsuarioBizi para añadirlo o no.
					 */
					agregarUsuario(usuarios, identificador);
				}
				contador++;
			}
			f.close();
			/*
			 * Con este bucle recorremos el ArrayList de objetos de tipo UsuarioBizi
			 * y se añaden los usos de cada uno de los usuarios teniendo en cuenta
			 * los datos guardados en el vector [datosFichero].
			 */
			for (int i = 0; i < usuarios.size(); i++) {
				obtenerUsosPorUsuario(datosFichero, usuarios.get(i));
			}
			// Ordenamos el ArrayList por mayor numero de usos
			Collections.sort(usuarios);
			// El numero de usuarios totales es igual a la longitud del array
			System.out.println("Numero de usuarios distintos: " + usuarios.size());
			System.out.println("Usuario  " + "Traslados  " + "Circular  " + "Total");
			System.out.println("=======  " + "=========  " + "========  " + "=====");
			// Mostramos por pantalla solo los 15 usuarios con mas usos
			for (int i = 0; i < 15; i++) {
				System.out.println(usuarios.get(i).toString());
			}
		} catch (FileNotFoundException e) {
			System.out.println("No se ha podido acceder al fichero.");
			e.printStackTrace();
		}
	}
	
	/**
	 * Pre: ---
	 * Post: Este metodo recibe un identificador y lo busca en el ArrayList
	 * pasado por parametro. Si no lo encuentra añade un usuario a la lista
	 * con ese numero de identificador.
	 */
	public static void agregarUsuario(ArrayList<UsuarioBizi> usuarios, 
			int identificador) {
		// El primer usuario siempre se introduce 
		if (usuarios.size() == 0) {
			UsuarioBizi usuario = new UsuarioBizi(identificador);
			usuarios.add(usuario);
		} // En el resto de casos comprobamos si esta en el vector
		else 
		{ 
			for (int i = 0; i < usuarios.size(); i++) {
				// Si ya esta se acaba el metodo
				if (usuarios.get(i).getIdentificador() == identificador) {
					return;
				}
			}
			// Si no esta se añade
			UsuarioBizi usuario = new UsuarioBizi(identificador);
			usuarios.add(usuario);
		}
	}
	
	/**
	 * Pre: ---
	 * Post: Este metodo recibe un vector de objetos de tipo UsoBizi
	 * y un Usuario para añadirle sus usos totales, sus traslados
	 * normales y sus traslados circulares.
	 */
	public static void obtenerUsosPorUsuario
		(ArrayList<UsoBizi> datosFichero, UsuarioBizi usuario) {
		for (int i = 0; i < datosFichero.size(); i++) {
			// Si el identificador coincide con el del objeto
			if (usuario.getIdentificador() == datosFichero.get(i).getUsuario()) {
				// Siempre cuenta como uso total
				usuario.setTotal(usuario.getTotal() + 1);
				// Si las estaciones son iguales es uso circular
				if (datosFichero.get(i).getEstacionRetiro() == 
						datosFichero.get(i).getEstacionAnclaje()) {
					usuario.setCirculares(usuario.getCirculares() + 1);
				}
				// Si no es un traslado normal
				else 
				{
					usuario.setTraslados(usuario.getTraslados() + 1);
				}
			}
		}
	}
}
