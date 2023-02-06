package br.edu.projeto.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CNPJ;

@Entity
@Table (name = "fornecedor")
public class Fornecedor {

	@Id
	@NotNull
	@CNPJ
	private String cnpj;
	
	@NotNull
	private String nome;
	
	@NotNull
	private String estado;
	
	@NotNull
	private String cep;
}
