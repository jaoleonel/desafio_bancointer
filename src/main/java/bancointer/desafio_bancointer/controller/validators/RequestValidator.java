package bancointer.desafio_bancointer.controller.validators;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import bancointer.desafio_bancointer.model.UsuarioModelRequest;

public class RequestValidator {

	public static final String EMAIL_INVALIDO_MSG = "Insira um email válido.";
	public static final String NOME_INVALIDO_MSG = "Insira um nome válido.";

	public List<String> errosDeValidacao;

	private static boolean validarEmailInternal(String email) {
		String regex = "^(.+)@(.+)$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

	public static String validarEmail(String email) {
		var retorno = "";
		if (!validarEmailInternal(email))
			retorno = EMAIL_INVALIDO_MSG;
		return retorno;

	}

	public static List<String> validarUsuario(UsuarioModelRequest usuario) {
		var erros = new ArrayList<String>();
		if (!validarEmailInternal(usuario.email))
			erros.add(EMAIL_INVALIDO_MSG);
		if (usuario.nome == null || usuario.nome.isEmpty())
			erros.add(NOME_INVALIDO_MSG);
		return erros;
	}
}
