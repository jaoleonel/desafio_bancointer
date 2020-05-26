package bancointer.desafio_bancointer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import bancointer.desafio_bancointer.entity.DigitoUnico;
import bancointer.desafio_bancointer.entity.Usuario;
import bancointer.desafio_bancointer.repository.UsuarioRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UsuarioRepositoryIntegrationTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Test
	public void whenFindByEmail_thenReturnUsuario() {

//		Usuario alex = new Usuario("alex", "alex@alexandria.com", new DigitoUnico[] {DigitoUnico.obterDigitoUnico(1, 1)});
//		entityManager.persist(alex);
//		entityManager.flush();
//
//		Usuario found = usuarioRepository.findByEmail(alex.getEmail());
//
//		assertThat(found.getEmail()).isEqualTo(alex.getEmail());
	}

	@Test
	public void whenFindAllByName_thenReturnUsuario() {

//		Usuario alex = new Usuario("alex", "alex@alexandria.com", new DigitoUnico[] {DigitoUnico.obterDigitoUnico(1, 1)});
//		Usuario alex2 = new Usuario("alex", "alex2@alexandria.com", new DigitoUnico[] {DigitoUnico.obterDigitoUnico(1, 1)});
//		entityManager.persist(alex);
//		entityManager.persist(alex2);
//		entityManager.flush();
//
//		var found = usuarioRepository.findAllByNome(alex.getNome());
//		assertTrue(found.size() == 2);
//		assertTrue(found.get(0).getNome().equalsIgnoreCase(found.get(1).getNome()));
//		assertTrue(!found.get(0).getEmail().equalsIgnoreCase(found.get(1).getEmail()));
	}

	@Test
	public void deleteUsuarioSuccessfuly() {

//		Usuario alex = new Usuario("alex", "alex@alexandria.com", new DigitoUnico[] {DigitoUnico.obterDigitoUnico(1, 1)});
//		Usuario alex2 = new Usuario("alex", "alex2@alexandria.com", new DigitoUnico[] {DigitoUnico.obterDigitoUnico(1, 1)});
//		entityManager.persist(alex);
//		entityManager.persist(alex2);
//		entityManager.flush();
//
//		usuarioRepository.deleteById(alex.getEmail());
//
//		var last = usuarioRepository.findAllByNome("alex");
//
//		assertTrue(last.size() == 1);
//		assertTrue(last.get(0).getEmail().equalsIgnoreCase(alex2.getEmail()));
//
//		usuarioRepository.deleteById(alex2.getEmail());
//
//		last = usuarioRepository.findAllByNome("alex");
//
//		assertTrue(last.size() == 0);
	}

}
