package bancointer.desafio_bancointer.entity;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.tomcat.util.codec.binary.Base64;

import bancointer.desafio_bancointer.util.Criptografia;

public abstract class BaseCriptoEntity {

//	protected String base64CriptoPublicKey;
//	protected String base64CriptoPrivateKey;
//	
//	
//	public BaseCriptoEntity() {
//	}
//
//	public BaseCriptoEntity(String base64CriptoPublicKey, String base64CriptoPrivateKey) {
//		super();
//		this.base64CriptoPublicKey = base64CriptoPublicKey;
//		this.base64CriptoPrivateKey = base64CriptoPrivateKey;
//	}
//
//	@SuppressWarnings("unused")
//	public String criptografar(String dado) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException, NoSuchPaddingException, NoSuchAlgorithmException {
//		return Base64.encodeBase64String(Criptografia.criptografar(dado, base64CriptoPublicKey));
//	}
//	
//	@SuppressWarnings("unused")
//	public String descriptografar(byte[] dado) throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, IllegalBlockSizeException {
//		return Criptografia.descriptografar(dado, base64CriptoPrivateKey);
//	}
//
//	public String getBase64CriptoPublicKey() {
//		return this.base64CriptoPublicKey;
//	}
//
//	public String getBase64CriptoPrivateKey() {
//		return this.base64CriptoPrivateKey;
//	}
	
	
}
