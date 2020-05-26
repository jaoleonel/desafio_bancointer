package bancointer.desafio_bancointer.service;


import java.util.Optional;

import bancointer.desafio_bancointer.entity.Usuario;

public interface UsuarioService {

	Optional<Usuario> findById(Long id);
	Usuario save(Usuario usuario);
	void deleteById(Long id);
}
