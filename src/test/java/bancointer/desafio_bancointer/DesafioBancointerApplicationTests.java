package bancointer.desafio_bancointer;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import bancointer.desafio_bancointer.entity.DigitoUnico;
import bancointer.desafio_bancointer.util.Criptografia;
import bancointer.desafio_bancointer.util.Util;

@SpringBootTest
class DesafioBancointerApplicationTests {

	@Test
	public void digitoUnicoTestSuccessfuly() {

		int result = DigitoUnico.obterDigitoUnico(2, 1).getResultado();
		assertTrue(result == 2);

		result = DigitoUnico.obterDigitoUnico(3, 1).getResultado();
		assertTrue(result == 3);

		result = DigitoUnico.obterDigitoUnico(2, 2).getResultado();
		assertTrue(result == 4);

		result = DigitoUnico.obterDigitoUnico(3, 2).getResultado();
		assertTrue(result == 6);

		result = DigitoUnico.obterDigitoUnico(11, 1).getResultado();
		assertTrue(result == 2);

		result = DigitoUnico.obterDigitoUnico(22, 1).getResultado();
		assertTrue(result == 4);

		result = DigitoUnico.obterDigitoUnico(11, 2).getResultado();
		assertTrue(result == 4);

		result = DigitoUnico.obterDigitoUnico(22, 2).getResultado();
		assertTrue(result == 8);

		result = DigitoUnico.obterDigitoUnico(33, 2).getResultado();
		assertTrue(result == 3);

		result = DigitoUnico.obterDigitoUnico(1111, 2).getResultado();
		assertTrue(result == 8);

		result = DigitoUnico.obterDigitoUnico(1111, 3).getResultado();
		assertTrue(result == 3);

		result = DigitoUnico.obterDigitoUnico(11, 10).getResultado();
		assertTrue(result == 2);

		result = DigitoUnico.obterDigitoUnico(9875, 4).getResultado();
		assertTrue(result == 8);
	}

	@Test
	public void TestarCriptografia() throws IOException, GeneralSecurityException {
		var cripto = new Criptografia();
		var testeCript = cripto.criptografar("teste", Base64.getEncoder().encodeToString(cripto.getPublicKey().getEncoded()));
		var testeDecript = cripto.descriptografar(testeCript, cripto.getPrivateKey());
		
		assertTrue(testeDecript.equals("teste"));
	}
}
