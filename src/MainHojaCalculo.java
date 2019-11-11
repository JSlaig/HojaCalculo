import java.util.Scanner;

public class MainHojaCalculo {
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Introduzca el numero de hojas de calculo:");
		int hojas = scan.nextInt();
		scan.nextLine();
		System.out.println("Introduzca el numero de columnas y filas respectivamente y separadas por un espacio: ");
		String dimensiones = scan.nextLine();
		
		try {
			HojaCalculo hoja = new HojaCalculo(hojas, dimensiones);
			hoja.start();
		} catch (HojaCalculoException e) {
			e.getMessage();
		}
		
		
		scan.close();
	}

}
