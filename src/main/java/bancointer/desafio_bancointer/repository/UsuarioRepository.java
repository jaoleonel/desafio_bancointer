package bancointer.desafio_bancointer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import bancointer.desafio_bancointer.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
}
