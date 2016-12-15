package br.com.prova.testes;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.hibernate.validator.AssertTrue;
import org.junit.Assert;
import org.junit.Test;

import br.com.prova.controller.TransacaoController;
import br.com.prova.controller.TransacaoControllerImpl;
import br.com.prova.dao.PersistenciaTransacao;
import br.com.prova.dao.PersistenciaUsuario;
import br.com.prova.dao.UsuarioDAO;
import br.com.prova.entidade.Transacao;
import br.com.prova.entidade.Usuario;
import br.com.prova.util.Util;

public class TesteTransacao {

	/*
	 * O TESTE PARA VALIDAR REGRA DE NEGOCIO -- SIMULAR OS VALORES DESCRITOS NA
	 * PROVA
	 * 
	 */

	@Test
	public void testarTransacao() {

		// ************ TESTE 1**************

		// carrega os usuarios e seus respctivos saldos
		cargaDeDados();

		// monto data para ter ambiente controlado
		Calendar data = Util.montaData(2016, 12, 13, 22, 30, 00);

		// simula a primeira transacao
		Transacao transacao1 = new Transacao();
		transacao1.setUsuarioCfp("11111111111");
		transacao1.setMerchantId(1);
		transacao1.setValorTransacao(01.00);
		transacao1.setTipoTransacao("TP_2");
		transacao1.setDataTrasacao(data.getTime());

		// calcula a valor da transacao
		TransacaoControllerImpl controller = new TransacaoControllerImpl();
		controller.calcularCashback(transacao1);

		// carrega obj do banco e verifico se saldo foi atualizado.
		PersistenciaUsuario persistenceUsuario = new PersistenciaUsuario();
		Usuario usuario1 = persistenceUsuario.carregarUsuarioCpf(transacao1.getUsuarioCfp());

		// VERIFICA SE VALOR FOI AUTALIZADO
		assertTrue("Não atualizou saldo usuario", usuario1.getBalanco() == 12.15);

		// ************ TESTE 2**************

		Transacao transacao2 = new Transacao();
		transacao2.setUsuarioCfp("22222222222");
		transacao2.setMerchantId(1);
		transacao2.setValorTransacao(3.00);
		transacao2.setTipoTransacao("TP_3");
		transacao2.setDataTrasacao(data.getTime());

		controller.calcularCashback(transacao2);

		Usuario usuario2 = persistenceUsuario.carregarUsuarioCpf(transacao2.getUsuarioCfp());
		assertTrue("Não atualizou saldo usuario", usuario2.getBalanco() == 52.47);

		// ************ TESTE 3**************

		Transacao transacao3 = new Transacao();
		transacao3.setUsuarioCfp("22222222222");
		transacao3.setMerchantId(1);
		transacao3.setValorTransacao(3.00);
		transacao3.setTipoTransacao("TP_3");
		transacao3.setDataTrasacao(data.getTime());

		controller.calcularCashback(transacao3);
		PersistenciaUsuario persistenceUsuario3 = new PersistenciaUsuario();
		Usuario usuario3 = persistenceUsuario3.carregarUsuarioCpf(transacao3.getUsuarioCfp());
		assertTrue("Não atualizou saldo usuario", usuario3.getBalanco() == 52.92);

		// ************ TESTE 4**************

		Transacao transacao4 = new Transacao();
		transacao4.setUsuarioCfp("33333333333");
		transacao4.setMerchantId(1);
		transacao4.setValorTransacao(5.00);
		transacao4.setTipoTransacao("TP_1");
		transacao4.setDataTrasacao(data.getTime());

		controller.calcularCashback(transacao4);

		Usuario usuario4 = persistenceUsuario.carregarUsuarioCpf(transacao4.getUsuarioCfp());

		assertTrue("Não atualizou saldo usuario", usuario4.getBalanco() == 0.0);

	}

	private void cargaDeDados() {

		// limpo base teste
		PersistenciaUsuario persistenciaUsuario = new PersistenciaUsuario();
		persistenciaUsuario.removerTodosUsuario();

		ArrayList<Usuario> listaUsuario = new ArrayList<>();

		Usuario usuario1 = new Usuario();
		usuario1.setUsuarioCpf("11111111111");
		usuario1.setNome("João Primeiro");
		usuario1.setBalanco(12.00);
		listaUsuario.add(usuario1);

		Usuario usuario2 = new Usuario();
		usuario2.setUsuarioCpf("22222222222");
		usuario2.setNome("Maria Segunda");
		usuario2.setBalanco(52.02);
		listaUsuario.add(usuario2);

		Usuario usuario3 = new Usuario();
		usuario3.setUsuarioCpf("33333333333");
		usuario3.setNome("Emerson Terceiro");
		usuario3.setBalanco(2.25);
		listaUsuario.add(usuario3);

		Usuario usuario4 = new Usuario();
		usuario4.setUsuarioCpf("44444444444");
		usuario4.setNome("Mario Quarto");
		usuario4.setBalanco(89.66);
		listaUsuario.add(usuario4);

		// salva usuario banco
		for (Usuario usr : listaUsuario) {
			persistenciaUsuario.salvar(usr);
		}
	}
	
//RESULTA FINAL NO BANCO DE DADOS MYSQL	
/*<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE ROOT SYSTEM "resultado.dtd">
<ROOT xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
    <row>
      <field name="id">15</field>
      <field name="balance">12.15</field>
      <field name="name">João Primeiro</field>
      <field name="usr_cpf">11111111111</field>
    </row>
    <row>
      <field name="id">16</field>
      <field name="balance">52.92</field>
      <field name="name">Maria Segunda</field>
      <field name="usr_cpf">22222222222</field>
    </row>
    <row>
      <field name="id">17</field>
      <field name="balance">2.25</field>
      <field name="name">Emerson Terceiro</field>
      <field name="usr_cpf">33333333333</field>
    </row>
    <row>
      <field name="id">18</field>
      <field name="balance">89.66</field>
      <field name="name">Mario Quarto</field>
      <field name="usr_cpf">44444444444</field>
    </row>
</ROOT>
	 */
	
}
