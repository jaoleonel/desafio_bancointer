package bancointer.desafio_bancointer.entity;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.tomcat.util.codec.binary.Base64;

import bancointer.desafio_bancointer.util.Criptografia;

@Entity
@Table(name = "usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Lob
	private byte[] nome;

	@Lob
	private byte[] email;

	@OneToMany(targetEntity = DigitoUnico.class, fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	private List<DigitoUnico> listaDigitoUnico;

	@Lob
	protected byte[] byteArrayBase64PublicKey;
	
	@Lob
	protected byte[] byteArrayBase64PrivateKey;

	public Usuario(String nome, String email, List<DigitoUnico> listaDigitoUnico, byte[] byteArrayBase64PublicKey,
			byte[] byteArrayBase64PrivateKey) throws InvalidKeyException, BadPaddingException,
			IllegalBlockSizeException, NoSuchPaddingException, NoSuchAlgorithmException {
//		super(base64CriptoPublicKey, base64CriptoPrivateKey);
		this.byteArrayBase64PublicKey = byteArrayBase64PublicKey;
		this.byteArrayBase64PrivateKey = byteArrayBase64PrivateKey;
		this.nome = Criptografia.criptografar(nome, new String(byteArrayBase64PublicKey));
		this.email = Criptografia.criptografar(email, new String(byteArrayBase64PublicKey));
		this.listaDigitoUnico = listaDigitoUnico;
	}

	public Usuario(String nome, String email, List<DigitoUnico> listaDigitoUnico) throws InvalidKeyException,
			BadPaddingException, IllegalBlockSizeException, NoSuchPaddingException, NoSuchAlgorithmException {
		this.nome = Criptografia.criptografar(nome, new String(byteArrayBase64PrivateKey));
		this.email = Criptografia.criptografar(email, new String(byteArrayBase64PrivateKey));
		this.listaDigitoUnico = listaDigitoUnico;
	}

	public Usuario() {

	}

	public String getNome() throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException,
			BadPaddingException, IllegalBlockSizeException {
		return Criptografia.descriptografar(this.nome, new String(byteArrayBase64PrivateKey));
	}

	public void setNome(String nome) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException,
			NoSuchPaddingException, NoSuchAlgorithmException {
		this.nome = Criptografia.criptografar(nome, new String(byteArrayBase64PublicKey));
	}

	public String getEmail() throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException,
			BadPaddingException, IllegalBlockSizeException {
		return Criptografia.descriptografar(this.email, new String(byteArrayBase64PrivateKey));
	}
	

	public void setEmail(String email) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException, NoSuchPaddingException, NoSuchAlgorithmException {
		this.email = Criptografia.criptografar(email, new String(byteArrayBase64PublicKey));
	}

	public List<DigitoUnico> getListaDigitoUnico() {
		return listaDigitoUnico;
	}

	public void setListaDigitoUnico(List<DigitoUnico> listaDigitoUnico) {
		this.listaDigitoUnico = listaDigitoUnico;
	}

	public void addDigitoUnico(DigitoUnico digitoUnico) {
		this.listaDigitoUnico.add(digitoUnico);
	}

	public byte[] getByteArrayBase64PublicKey() {
		return byteArrayBase64PublicKey;
	}

	public void setByteArrayBase64PublicKey(byte[] byteArrayBase64PublicKey) {
		this.byteArrayBase64PublicKey = byteArrayBase64PublicKey;
	}

	public byte[] getByteArrayBase64PrivateKey() {
		return byteArrayBase64PrivateKey;
	}

	public void setByteArrayBase64PrivateKey(byte[] byteArrayBase64PrivateKey) {
		this.byteArrayBase64PrivateKey = byteArrayBase64PrivateKey;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	

}
