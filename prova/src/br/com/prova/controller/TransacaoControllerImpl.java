package br.com.prova.controller;

import java.math.BigDecimal;

import br.com.prova.dao.PersistenciaTransacao;
import br.com.prova.dao.PersistenciaUsuario;
import br.com.prova.entidade.Transacao;
import br.com.prova.entidade.Usuario;

public class TransacaoControllerImpl implements TransacaoController {

	@Override
	public void calcularCashback(Transacao transacao) {
		BigDecimal valorCashBack = BigDecimal.ZERO;
		BigDecimal percentual = BigDecimal.ZERO;
		Double valorSaldo = 0.00;

		PersistenciaUsuario persistenceUsuario = new PersistenciaUsuario();
		PersistenciaTransacao persistenciaTransacao = new PersistenciaTransacao();

		// carrega usuario caso exista na base
		Usuario usuario = persistenceUsuario.carregarUsuarioCpf(transacao.getUsuarioCfp());

		if (usuario == null) {
			usuario = new Usuario();
			usuario.setUsuarioCpf(transacao.getUsuarioCfp());
		}

		// identifico tipo de transacao
		if (!transacao.getTipoTransacao().equals("TP_1")) {

			// simula os percentuais de cashback do estabelecimento
			percentual = new Estabelecimento().retornarPercentualCashback(transacao.getDataTrasacao());

			// calcula valor cashback
			percentual = percentual.divide(new BigDecimal(100));
			valorCashBack = percentual.multiply(new BigDecimal(transacao.getValorTransacao()));

			// soma saldo + cashback calculado
			valorCashBack = valorCashBack.add(new BigDecimal(usuario.getBalanco()));

			// atualiza saldo usuario
			usuario.setBalanco(valorCashBack.doubleValue());
		} else {
			// trata o tipo de transacao = TP_1
			valorSaldo = usuario.getBalanco() - transacao.getValorTransacao();

			// garantir que saldo nao fique negativo
			if (valorSaldo < 0) {
				usuario.setBalanco(0.00);
			} else {
				usuario.setBalanco(valorSaldo);
			}
		}

		// persiste usuario com saldo altualizado
		persistenceUsuario.salvar(usuario);

		// persiste transacao
		persistenciaTransacao.salvar(transacao);
	}
}
