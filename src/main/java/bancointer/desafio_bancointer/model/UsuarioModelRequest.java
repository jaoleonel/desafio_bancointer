package bancointer.desafio_bancointer.model;

import java.util.List;

public class UsuarioModelRequest {

	public String nome;
	
	public String email;
	
	public List<DigitoUnicoModelRequest> listaDigitoUnico;
	
	public UsuarioModelRequest(String nome, String email, List<DigitoUnicoModelRequest> listaDigitoUnico) {
		this.nome = nome;
		this.email = email;
		this.listaDigitoUnico = listaDigitoUnico;
	}
	
}

