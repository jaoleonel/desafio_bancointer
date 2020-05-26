package bancointer.desafio_bancointer.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "digito_unico")
public class DigitoUnico {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	private int entrada;
	private int numConcatenacoes;
	private int resultado;

	private DigitoUnico(int entrada, int numConcatenacoes, int resultado) {
		this.entrada = entrada;
		this.numConcatenacoes = numConcatenacoes;
		this.resultado = resultado;
	}
	
	public DigitoUnico() {}
	

	public static DigitoUnico obterDigitoUnico(int entrada, int numConcatenacoes) throws IllegalArgumentException {

		if (entrada < 1 || numConcatenacoes < 1) {
			throw new IllegalArgumentException("Argumentos devem ser inteiros maiores que 0.");
		}
		var entradaStr = String.valueOf(entrada);
		var concatenados = "";

		for (int i = 0; i < numConcatenacoes; i++) {
			concatenados += entradaStr;
		}

		return new DigitoUnico(entrada, numConcatenacoes, obterSomaDigitoUnico(concatenados));
	}

	private static int obterSomaDigitoUnico(String entrada) {
		int retorno = 0;
		for (int i = 0; i < entrada.length(); i++) {
			retorno += Integer.parseInt(entrada.substring(i, i + 1));
		}
		if (retorno > 9) {
			retorno = obterSomaDigitoUnico(String.valueOf(retorno));
		}
		return retorno;
	}
	
	public Long getId() {
		return id;
	}

		public int getEntrada() {
		return entrada;
	}

	public int getNumConcatenacoes() {
		return numConcatenacoes;
	}

	public int getResultado() {
		return resultado;
	}
}
