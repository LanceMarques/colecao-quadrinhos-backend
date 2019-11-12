package com.ihc.quadrinhosorientais.infra.amigo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="AMIGO")
public class Amigo {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="NOME")
	private String nome; 
	
	@Column(name="APELIDO")
	private String apelido;
	
	@Column(name="NUMERO_CELULAR")
	private String celular;
	
	public Amigo() {
		super();
	}

	public Amigo(Integer id, String nome, String apelido, String celular) {
		super();
		this.id = id;
		this.nome = nome;
		this.apelido = apelido;
		this.celular = celular;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getApelido() {
		return apelido;
	}

	public void setApelido(String apelido) {
		this.apelido = apelido;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}
		
}