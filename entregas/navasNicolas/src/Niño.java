public class Niño {
	private String nombre;
	private Pizarra pizarra;

	public Niño(String nombre) {
		this.nombre = nombre;
		this.pizarra = new Pizarra();
	}

	public void copiarYModificar(Pizarra pizarraOrigen) {
		String letras = pizarraOrigen.getLetras();
		char[] arr = letras.toCharArray();
		int cambios = (int) (Math.random() * 2) + 1;
		for (int i = 0; i < cambios; i++) {
			int idx = (int) (Math.random() * arr.length);
			arr[idx] = (char) ('A' + (int) (Math.random() * 26));
		}
		pizarra.setLetras(new String(arr));
	}

	public void copiarYModificar(Niño anterior) {
		copiarYModificar(anterior.pizarra);
	}

	public String getLetras() {
		return pizarra.getLetras();
	}

	public String getNombre() {
		return nombre;
	}
}