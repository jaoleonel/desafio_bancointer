package bancointer.desafio_bancointer.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bancointer.desafio_bancointer.entity.Usuario;
import bancointer.desafio_bancointer.repository.UsuarioRepository;
import bancointer.desafio_bancointer.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public Usuario save(Usuario usuario) {
		Usuario retorno = null;
		try {
		 retorno =  usuarioRepository.saveAndFlush(usuario);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return retorno;
	}
	
	@Override
	public void deleteById(Long id) {
		usuarioRepository.deleteById(id);
	}

	@Override
	public Optional<Usuario> findById(Long id) {
		return usuarioRepository.findById(id);
	}
}
