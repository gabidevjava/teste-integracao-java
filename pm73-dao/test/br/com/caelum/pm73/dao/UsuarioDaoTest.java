package br.com.caelum.pm73.dao;

import static org.junit.Assert.assertEquals;

import org.hibernate.Session;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.caelum.pm73.dominio.Usuario;

public class UsuarioDaoTest {
	
	private Session session;
	private UsuarioDao usuarioDao;

	@Before
	public void antes() {
		session = new CriadorDeSessao().getSession();
        usuarioDao = new UsuarioDao(session);
	}
	
	@After
	public void depois() {
		session.close();
	}
	
	@Test
    public void deveEncontrarPeloNomeEEmail() {
        Usuario novoUsuario = new Usuario("João da Silva", "joao@dasilva.com.br");
        usuarioDao.salvar(novoUsuario);

        Usuario usuarioDoBanco = usuarioDao.porNomeEEmail("João da Silva", "joao@dasilva.com.br");

        assertEquals("João da Silva", usuarioDoBanco.getNome());
        assertEquals("joao@dasilva.com.br", usuarioDoBanco.getEmail());
    }
	
	@Test
	public void deveRetornatNuloSeNaoEncontrarUsuario() {
		Usuario usuario = usuarioDao.porNomeEEmail("gaby", "gaby@gmail.com");
		Assert.assertNull(usuario);	
	}
}