package br.edu.projeto.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table (name = "estado")
public class Estado {

	@Id
	@NotNull
	private String sigla;
	
	@NotNull
	private String real;

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getReal() {
		return real;
	}

	public void setReal(String real) {
		this.real = real;
	}
	
	
}
