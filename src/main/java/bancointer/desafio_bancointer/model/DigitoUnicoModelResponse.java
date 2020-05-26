package bancointer.desafio_bancointer.model;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class DigitoUnicoModelResponse {

	@Id
	private long id;
	public int entrada;
	public int numConcatenacoes;
	public int resultado;
	
	public DigitoUnicoModelResponse(int entrada, int numConcatenacoes, int resultado) {
		this.entrada= entrada;
		this.numConcatenacoes = numConcatenacoes;
		this.resultado = resultado;
	}
	
}


