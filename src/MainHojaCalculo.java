import java.util.Scanner;

public class MainHojaCalculo {
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Introduzca el numero de hojas de calculo:");
		int nmohojas = scan.nextInt();
		//Llamada a scanner para limpiar el buffer
		scan.nextLine();
		
		System.out.println("Introduzca el numero de columnas y filas respectivamente y separadas por un espacio: ");
		String dimensiones = scan.nextLine();
		
		scan.close();
		
		HojaCalculo[] hojas = new HojaCalculo[nmohojas];
		
		try {
			for(int i = 0; i < nmohojas; i++) {
				hojas[i] = new HojaCalculo(dimensiones);
				hojas[i].write();
				System.out.println("\nHoja de calculo "+(i+1)+" rellenada.\n");
			}
		} catch (HojaCalculoException e) {
			e.getMessage();
		}	
	}

}