package br.com.prova.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.com.prova.entidade.Usuario;

public class PersistenciaUsuario {

	EntityManagerFactory emf;
	EntityManager em;

	public PersistenciaUsuario() {
		emf = Persistence.createEntityManagerFactory("mohr");
		em = emf.createEntityManager();
	}

	// salva usuario no banco
	public void salvar(Usuario usuario) {
		try {
			em.getTransaction().begin();
			em.merge(usuario);
			em.getTransaction().commit();
			//em.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// remove usuario banco
	public void remover(Usuario usuario) {
		try {
			em.getTransaction().begin();
			em.remove(usuario);
			em.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// retorna usuario pelo id
	public Usuario carregarUsuario(int codigo) {
		return em.find(Usuario.class, codigo);
	}

	// retorna usuario pelo cpf
	public Usuario carregarUsuarioCpf(String cpf) {
		Usuario usuario = null;
		try {
			Query query = em.createQuery("select t from Usuario as t where t.usuarioCpf = :cpf");
			query.setParameter("cpf", cpf);
			usuario = (Usuario) query.getSingleResult();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return usuario;
	}

	// remove todos usuario
	public void removerTodosUsuario() {
		try {
			List<Usuario> lista = em.createQuery("select t from Usuario as t").getResultList();
			em.getTransaction().begin();
			for (Usuario usuario : lista) {
				em.remove(usuario);
			}
			em.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
