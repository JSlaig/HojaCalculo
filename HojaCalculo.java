import java.util.Scanner;

public class HojaCalculo {
	
	private int columnas;
	private int filas;
	
	private String[][] matrizHoja;
	private int[][] matrizResultados;
	public Scanner sc = new Scanner(System.in);

	public HojaCalculo(String dimensiones) throws HojaCalculoException{		
		
		String[] parts = dimensiones.split(" ");
		
		if(Integer.valueOf(parts[0]) < Integer.MAX_VALUE) {
		this.columnas = Integer.valueOf(parts[1]);
		}else {
			throw new HojaCalculoException("Error: Valor de columnas demasiado grande: "+parts[0]);
		}
		if(Integer.valueOf(parts[1]) < Integer.MAX_VALUE) {
		this.filas = Integer.valueOf(parts[0]);
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
		
		System.out.println("Introduzca la hoja:\n");
		
			for(int i = 0; i < this.filas; i++) {
				
				String row = this.sc.nextLine();				
				
				String[] parts = row.split(" ");
				
				if(parts.length == this.columnas) {
					
					for(int j = 0; j < this.columnas; j++) {				
						this.matrizHoja[i][j] = parts[j];
					}
					
				}else {				
					throw new HojaCalculoException("Error: Numero de elementos de la fila "+(i+1)+" incorrecto");
				}
			}	
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
			
			//No funciona el substring
			element = element.substring(1);
			
			String[] parts = element.split("\\+");
			int[] num = new int[parts.length];
			int result = 0;
			
			for(int i = 0; i < parts.length; i++) {
				num[i] = assign(parts[i]);
				result += num[i];
			}
			return result;
		}		
		//Manejo de numeros normales que se deben almacenar en la matriz
		else if(element.contains("\\w+") == true && element.contains("\\D+") == false) {
			return Integer.valueOf(element);
		}else{
			throw new HojaCalculoException("Error: valor inesperado: "+element);
		}
	}
	
	/**
	 * Funcion que se encarga de devolver los valores de las celdas referenciadas en una funcion
	 * @throws HojaCalculoException Excepcion que se lanza si la casilla referenciada no comienza por la letra
	 */
	public int assign(String cell) throws HojaCalculoException {	
		
		int row;
		int column;
		
		
		if(cell.startsWith("\\w+") == true && cell.startsWith("\\D+") == true) {
			
			String letter = cell.substring(0, cell.lastIndexOf("\\D+"));
			letter = letter.toUpperCase();			
			
			String number = cell.substring(cell.lastIndexOf("\\D+"), (cell.length()-1));
			
			row = Integer.valueOf(number);		
			column = alphabet(letter);
		}else {
			throw new HojaCalculoException("Error: Para referenciar a una casilla se debe comenzar por sus letras");
		}
		
		//Falta realizar el retorno del numero contenido en la casilla o en la siguiente
		return decode(this.matrizHoja[row][column]);
	}
	
	/**
	 * Metodo que se encarga de que mediante una o varias letras se obtenga el valor de columna correspondiente
	 * @param letter Secuencia de letras cuyo valor en columna se quiere lograr
	 * @return valor de columna correspondiente
	 * @throws HojaCalculoException Excepcion que se lanza en el caso de que se produzca un error al contar las columnas
	 */
	public int alphabet(String letter) throws HojaCalculoException {
		if(letter.length() == 1) {
			return (letter.codePointAt(0) % 65);			
		} else if(letter.length() == 2) {
			
			String[] parts = letter.split("");	
			
			int firstLetter = ((parts[0].codePointAt(0) % 65)+1) * 26;
			int secondLetter = ((parts[1].codePointAt(0) % 65));
			int columnValue = firstLetter + secondLetter;
			     
			return columnValue;	
			
		} else if(letter.length() == 3) {
			String[] parts = letter.split("");			
			
			int firstLetter = ((parts[0].codePointAt(0) % 65)+1) * 676;
		    int secondLetter = ((parts[0].codePointAt(0) % 65)+1) * 26;
		    int thirdLetter = ((parts[1].codePointAt(0) % 65));
		    int columnValue = firstLetter + secondLetter + thirdLetter;
		     
		    return columnValue;			
			
		} else {
			throw new HojaCalculoException("Error: Numero de columnas excedido");
		}
	}
	
	
	public String toString() {
		String str = "";
		for(int i = 0; i < this.filas; i++) {
			for(int j = 0; j < this.columnas; j++) {
				System.out.print(this.matrizResultados[i][j] + " ");
			}
			System.out.print("\n");
		}
		return str;
	}
}