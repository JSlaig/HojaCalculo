import java.util.Scanner;

public class HojaCalculo {
	
	private int columnas;
	private int filas;
	
	private String[][] matrizHoja;
	private int[][] matrizResultados;

	public HojaCalculo(String dimensiones) throws HojaCalculoException{		
		
		String[] parts = dimensiones.split(" ");
		
		if(Integer.valueOf(parts[0]) < Integer.MAX_VALUE) {
		this.columnas = Integer.valueOf(parts[0]);
		}else {
			throw new HojaCalculoException("Error: Valor de columnas demasiado grande: "+parts[0]);
		}
		if(Integer.valueOf(parts[1]) < Integer.MAX_VALUE) {
		this.filas = Integer.valueOf(parts[1]);
		}else {
			throw new HojaCalculoException("Error: Valor de columnas demasiado grande: "+parts[1]);
		}
		
		this.matrizHoja = new String[filas][columnas];
		this.matrizResultados = new int[filas][columnas];
	}
	
	/**
	 * Funcion que se encarga de introducir los valores de cada casilla
	 * @throws HojaCalculoException Excepcion que se lanza en el caso de que el numero de elementos por fila no coincida con el esperado
	 */
	public void write() throws HojaCalculoException {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Introduzca la hoja:\n");
		
			for(int i = 0; i < this.filas; i++) {
				
				String fila = scan.nextLine();
				
				String[] parts = fila.split(" ");
				if(parts.length == this.columnas) {
					for(int j = 0; j < this.columnas; j++) {				
						this.matrizHoja[i][j] = parts[j];
					}
				}else {
					scan.close();
					throw new HojaCalculoException("Error: Numero de elementos de la fila "+(i+1)+" incorrecto");
				}
			}
		
		scan.close();
	}
	
	/**
	 * Funcion que se encarga de resolver las formulas y meterlas en la matriz de resultados
	 * @throws HojaCalculoException excepcion anidada de la funcion decode
	 */
	public void resolve() throws HojaCalculoException {
		for(int i = 0; i < this.filas; i++) {
			for(int j = 0; j < this.columnas; j++) {				
					this.matrizResultados[i][j] = decode(this.matrizHoja[i][j]);
			}			
		}		
	}
	
	/**
	 * Funcion que se encarga de comprobar que los valores introducidos en la hoja son correctos
	 * @param element elemento del cual se va a extraer la formula o numero
	 * @return valor numerico que retorna la funcion
	 * @throws HojaCalculoException Excepcion que se lanza si aparecen caracteres inesperados en el elemento
	 */
	public int decode(String element) throws HojaCalculoException {
		//Manejo de formulas
		if(element.startsWith("=") == true) {
			//Se debe implementar la identificacion de filas con letras y numeros, puede ser problematico si la matriz es de enteros y no de strings
			
			
			return 1;
		}
		//Manejo de numeros normales que se deben almacenar en la matriz
		else if(element.contains("[a-zA-Z]+") == false) {
			return Integer.valueOf(element);
		}else{
			throw new HojaCalculoException("Error: valor inesperado: "+element);
		}
	}
	
	/**
	 * Funcion que se encarga de devolver los valores de las celdas referenciadas en una funcion
	 * @throws HojaCalculoException Excepcion que se lanza si la casilla referenciada no comienza por la letra
	 */
	public void assign(String cell) throws HojaCalculoException {	
		
		int fila;
		int columna;
		
		
		if(cell.startsWith("[a-zA-Z]+") == true) {
			
			String letra = cell.substring(0, cell.lastIndexOf("[a-zA-Z]+"));
			String numero = cell.substring(cell.lastIndexOf("[a-zA-Z]+"), (cell.length()-1));
			
			fila = Integer.valueOf(numero);
			
		}else {
			throw new HojaCalculoException("Error: Para referenciar a una casilla se debe comenzar por sus letras");
		}		
	}
	
}