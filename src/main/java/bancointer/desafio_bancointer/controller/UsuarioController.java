package bancointer.desafio_bancointer.controller;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import bancointer.desafio_bancointer.controller.validators.RequestValidator;
import bancointer.desafio_bancointer.entity.DigitoUnico;
import bancointer.desafio_bancointer.entity.Usuario;
import bancointer.desafio_bancointer.model.DigitoUnicoModelRequest;
import bancointer.desafio_bancointer.model.DigitoUnicoModelResponse;
import bancointer.desafio_bancointer.model.UsuarioModelRequest;
import bancointer.desafio_bancointer.model.UsuarioModelResponse;
import bancointer.desafio_bancointer.service.UsuarioService;
import bancointer.desafio_bancointer.util.Criptografia;

@RestController
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping("/usuarios/{id}")
	public @ResponseBody ResponseEntity<Object> getUsuarioById(@RequestHeader("public_key") String base64PublicKey,
			@PathVariable long id) {

		try {

			var usuarioData = usuarioService.findById(id);

			if (usuarioData.isPresent()) {
				return new ResponseEntity<>(getUsuarioDescriptografado(usuarioData.get()), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);

			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/usuarios")
	public @ResponseBody ResponseEntity<Object> createUsuario(@RequestBody UsuarioModelRequest usuario) {

		var erros = RequestValidator.validarUsuario(usuario);
		if (erros.size() > 0)
			return new ResponseEntity<>(erros, HttpStatus.BAD_REQUEST);

		List<DigitoUnico> listaDigitoUnico = new ArrayList<DigitoUnico>();
		usuario.listaDigitoUnico
				.forEach(dig -> listaDigitoUnico.add(DigitoUnico.obterDigitoUnico(dig.entrada, dig.numConcatenacoes)));

		try {
			var cripto = new Criptografia();
			var usuarioEntidade = new Usuario(usuario.nome, usuario.email, listaDigitoUnico,
					Base64.getEncoder().encode(cripto.getPublicKey().getEncoded()),
					Base64.getEncoder().encode(cripto.getPrivateKey().getEncoded()));
			var result = usuarioService.save(usuarioEntidade);
			var retorno = getUsuarioDescriptografado(result);
			return new ResponseEntity<>(retorno, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}

	@PutMapping("/usuarios/{id}")
	public @ResponseBody ResponseEntity<Object> entireUsuarioUpdate(@RequestHeader("public_key") String base64PublicKey,
			@PathVariable("id") Long id, @RequestBody UsuarioModelRequest usuario) {

		try {
			return new ResponseEntity<>(updateUsuarioInternal(id, usuario, base64PublicKey, true), HttpStatus.OK);
		}catch (ValidationException e) {
			return new ResponseEntity<>(e.getErros(), HttpStatus.BAD_REQUEST);
 
		}
		catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@PatchMapping("/usuarios/{id}")
	public @ResponseBody ResponseEntity<Object> partialUsuarioUpdate(
			@RequestHeader("public_key") String base64PublicKey, @PathVariable("id") Long id,
			@RequestBody UsuarioModelRequest usuario) {

		try {
			return new ResponseEntity<>(updateUsuarioInternal(id, usuario, base64PublicKey, false), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@DeleteMapping("/usuarios/{id}")
	public ResponseEntity<String> deleteUsuario(@RequestHeader("public_key") String base64PublicKey,
			@PathVariable("id") long id) {

		try {
			usuarioService.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}

	@PatchMapping(value = { "/usuarios/{id}/digitos-unicos/{digEntrada}/{numConcatenacoes}",
			"/usuarios/digitos-unicos/{digEntrada}/{numConcatenacoes}" })
	public ResponseEntity<Object> updateOrOnlyGetDigitoUnicoDeUsuario(
			@RequestHeader("public_key") String base64PublicKey, @PathVariable int entrada,
			@PathVariable int numConcatenacoes, @PathVariable Optional<Long> id) {
		if (id.isPresent()) {

			var _usuario = new UsuarioModelRequest(null, null,
					Arrays.asList(new DigitoUnicoModelRequest(entrada, numConcatenacoes)));
			try {
				return new ResponseEntity<>(updateUsuarioInternal(id.get(), _usuario, base64PublicKey, false),
						HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<>(DigitoUnico.obterDigitoUnico(entrada, numConcatenacoes), HttpStatus.OK);
		}
	}

	@GetMapping("/usuario/{id}/digitos-unicos")
	public ResponseEntity<Object> obterDigitoUnico(@RequestHeader("public_key") String base64PublicKey,
			@PathVariable Long id) {

		try {
			var usuarioData = usuarioService.findById(id);
			if (usuarioData.isPresent()) {
				return new ResponseEntity<>(usuarioData.get().getListaDigitoUnico(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	private Usuario updateUsuarioInternal(Long id, UsuarioModelRequest usuario, String base64PublicKey, boolean isPut)
			throws PersistenceException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException,
			NoSuchPaddingException, NoSuchAlgorithmException, ValidationException {

		var usuarioData = usuarioService.findById(id);

		Usuario _usuario;
		if (usuarioData.isPresent()) {

			_usuario = usuarioData.get();
			if(!usuario.nome.isEmpty())  _usuario.setNome(usuario.nome);			
			if(!usuario.email.isEmpty())  _usuario.setEmail(usuario.email);

			if (isPut) {
				List<DigitoUnico> listaDigitoUnicoLocal = new ArrayList<DigitoUnico>();

				usuario.listaDigitoUnico.forEach(
						dig -> listaDigitoUnicoLocal.add(DigitoUnico.obterDigitoUnico(dig.entrada, dig.entrada)));
				_usuario.setListaDigitoUnico(listaDigitoUnicoLocal);

			} else {
				
				for(var dig : usuario.listaDigitoUnico) {
					_usuario.getListaDigitoUnico().add(DigitoUnico.obterDigitoUnico(dig.entrada, dig.entrada));
				}
			}
		} else if (isPut) {
			var erros = RequestValidator.validarUsuario(usuario);
			if (erros.size() > 0)
				throw new ValidationException(erros);
			
			Criptografia cripto = new Criptografia();
			List<DigitoUnico> listaDigitoUnicoLocal = new ArrayList<DigitoUnico>();
			
			usuario.listaDigitoUnico.forEach(
					dig -> listaDigitoUnicoLocal.add(DigitoUnico.obterDigitoUnico(dig.entrada, dig.numConcatenacoes)));

			_usuario = new Usuario(usuario.nome, usuario.email, listaDigitoUnicoLocal,
					Base64.getEncoder().encode(cripto.getPublicKey().getEncoded()),
					Base64.getEncoder().encode(cripto.getPrivateKey().getEncoded()));
		} else {
			throw new PersistenceException();
		}

		return usuarioService.save(_usuario);
	}

	private UsuarioModelResponse getUsuarioDescriptografado(Usuario usuario) throws InvalidKeyException,
			BadPaddingException, IllegalBlockSizeException, NoSuchPaddingException, NoSuchAlgorithmException {
		var listaDigitoLocal = new ArrayList<DigitoUnicoModelResponse>();
		usuario.getListaDigitoUnico().forEach(dig -> listaDigitoLocal
				.add(new DigitoUnicoModelResponse(dig.getEntrada(), dig.getNumConcatenacoes(), dig.getResultado())));

		return new UsuarioModelResponse(usuario.getId(), usuario.getNome(), usuario.getEmail(), listaDigitoLocal,
				new String(usuario.getByteArrayBase64PublicKey()));
	}

}
