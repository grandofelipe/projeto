package br.edu.projeto.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "orcamento")
public class Orcamento {

	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_orcamento")
	private String id;
	
	@NotNull
	@Column(name = "tipo_prioridade")
	private String prioridade;
	
	@NotNull
	@Column(name = "valor_servico")
	private Double val_s;
	
	@NotNull
	@Column(name = "valor_funcionario")
	private Double val_f;
	
	@NotNull
	@Column(name = "valor_itens")
	private Double val_i;
	
	@NotNull
	@Column(name = "data_abertura")
	private Date dt_abr;
	
	@NotNull
	@Column(name = "data_fechamento")
	private Date dt_fec;
	
	@ManyToOne
	private Cliente cliente;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(String prioridade) {
		this.prioridade = prioridade;
	}

	public Double getVal_s() {
		return val_s;
	}

	public void setVal_s(Double val_s) {
		this.val_s = val_s;
	}

	public Double getVal_f() {
		return val_f;
	}

	public void setVal_f(Double val_f) {
		this.val_f = val_f;
	}

	public Double getVal_i() {
		return val_i;
	}

	public void setVal_i(Double val_i) {
		this.val_i = val_i;
	}

	public Date getDt_abr() {
		return dt_abr;
	}

	public void setDt_abr(Date dt_abr) {
		this.dt_abr = dt_abr;
	}

	public Date getDt_fec() {
		return dt_fec;
	}

	public void setDt_fec(Date dt_fec) {
		this.dt_fec = dt_fec;
	}
	
	
}
