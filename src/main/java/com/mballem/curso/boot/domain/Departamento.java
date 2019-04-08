package com.mballem.curso.boot.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "DEPARTAMENTOS")
public class Departamento extends AbstractEntity<Long> {

	@Column(name = "nome", nullable = false, unique = true, length = 60)
	private String nome;

	/**
	 * Quando temos um relacionamento bidirecional tem que definir qual é o lado
	 * fraco e o lado forte da relação, o lado forte é aquele que contém a chave
	 * estrangeira, no caso cargo, mappedBy serve para dizer qual o atributo que
	 * está fazendo parte do lado forte da relação, esse atributo é o departamento
	 * na classe Cargo
	 */
	@OneToMany(mappedBy = "departamento")
	private List<Cargo> cargos;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Cargo> getCargos() {
		return cargos;
	}

	public void setCargos(List<Cargo> cargos) {
		this.cargos = cargos;
	}

}
