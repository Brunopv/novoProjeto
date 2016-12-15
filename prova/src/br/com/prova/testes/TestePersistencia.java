package br.com.prova.testes;

import br.com.prova.dao.PersistenciaUsuario;
import br.com.prova.entidade.Usuario;

public class TestePersistencia {

	public static void main(String[] args) {
		// teste para validar os metodos de persistencia
		PersistenciaUsuario persistenciaUsuario = new PersistenciaUsuario();
		Usuario usuario = new Usuario();

		usuario.setNome("Bruno");
		usuario.setUsuarioCpf("112354646");
		usuario.setBalanco(2.21);

		persistenciaUsuario.salvar(usuario);
	}

}
