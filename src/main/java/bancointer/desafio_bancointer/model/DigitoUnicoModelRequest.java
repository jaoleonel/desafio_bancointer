package bancointer.desafio_bancointer.model;

import javax.persistence.Entity;
import javax.persistence.Id;
public class DigitoUnicoModelRequest {

	public int entrada;
	public int numConcatenacoes;
	
	public DigitoUnicoModelRequest(int entrada, int numConcatenacoes) {
		this.entrada= entrada;
		this.numConcatenacoes = numConcatenacoes;
	}
	
}

