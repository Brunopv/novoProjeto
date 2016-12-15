package br.com.prova.entidade;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "register_transaction")
public class Transacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "user_cpf")
	private String usuarioCfp;

	@Column(name = "merchant_id")
	private int merchantId;

	@Column(name = "transaction_value")
	private double valorTransacao;

	@Column(name = "transaction_type")
	private String tipoTransacao;

	@Column(name = "transaction_date")
	private Date dataTrasacao;

	public String getUsuarioCfp() {
		return usuarioCfp;
	}

	public void setUsuarioCfp(String usuarioCfp) {
		this.usuarioCfp = usuarioCfp;
	}

	public int getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(int merchantId) {
		this.merchantId = merchantId;
	}

	public double getValorTransacao() {
		return valorTransacao;
	}

	public void setValorTransacao(double valorTransacao) {
		this.valorTransacao = valorTransacao;
	}

	public String getTipoTransacao() {
		return tipoTransacao;
	}

	public void setTipoTransacao(String tipoTransacao) {
		this.tipoTransacao = tipoTransacao;
	}

	public Date getDataTrasacao() {
		return dataTrasacao;
	}

	public void setDataTrasacao(Date dataTrasacao) {
		this.dataTrasacao = dataTrasacao;
	}
}
