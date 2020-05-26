package bancointer.desafio_bancointer.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

public class UsuarioModelResponse {

	public Long id;

	public String nome;

	public String email;

	public List<DigitoUnicoModelResponse> listaDigitoUnico;

	public String publicKey;

	public UsuarioModelResponse(Long id, String nome, String email, List<DigitoUnicoModelResponse> listaDigitoUnico,
			String publicKey) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.listaDigitoUnico = listaDigitoUnico;
		this.publicKey = publicKey;
	}

}
