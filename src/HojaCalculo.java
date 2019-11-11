
public class HojaCalculo {
	
	int hojas;
	
	int columnas;
	int filas;

	public HojaCalculo(int hojas, String dimensiones) throws HojaCalculoException{
		this.hojas = hojas;
		
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
	}
	
	public void start() {
		
	}
	
}
