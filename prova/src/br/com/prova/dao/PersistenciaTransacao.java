package br.com.prova.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import br.com.prova.entidade.Transacao;

public class PersistenciaTransacao {

	EntityManagerFactory emf;
	EntityManager em;

	public PersistenciaTransacao() {
		emf = Persistence.createEntityManagerFactory("mohr");
		em = emf.createEntityManager();
	}

	// salva transacao no banco
	public void salvar(Transacao transacao) {
		try {
			em.getTransaction().begin();
			em.merge(transacao);
			em.getTransaction().commit();
			emf.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
