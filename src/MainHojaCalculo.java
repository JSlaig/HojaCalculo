import java.util.Scanner;

public class MainHojaCalculo {
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Introduzca el numero de hojas de calculo:");
		int nmohojas = scan.nextInt();
		//Llamada a scanner para limpiar el buffer
		scan.nextLine();
		
		System.out.println("Introduzca el numero de filas y columnas respectivamente y separadas por un espacio: ");
		String dimensiones = scan.nextLine();	
		
		HojaCalculo[] hojas = new HojaCalculo[nmohojas];
		
		try {
			for(int i = 0; i < nmohojas; i++) {
				hojas[i] = new HojaCalculo(dimensiones);
				hojas[i].write();
				System.out.println("\nHoja de calculo "+(i+1)+" rellenada.\n");
			}
			
			for(int i = 0; i < nmohojas; i++) {				
				hojas[i].resolve();
				System.out.println("Hoja: "+(i+1));
				hojas[i].toString();
				System.out.println("\nHoja de calculo "+(i+1)+" resuelta.\n");
			}
		} catch (HojaCalculoException e) {
			System.err.println(e.getMessage());
		}			
	}
}