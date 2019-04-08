package com.mballem.curso.boot.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "CARGOS")
public class Cargo extends AbstractEntity<Long> {

	@Column(name = "nome", nullable = false, unique = true, length = 60)
	private String nome;

	/**
	 * @ManyToOne: Tem como objetivo mapear o atributo referente a chave estrangeira em um
	 * relacionamento 1xN. A classe anotada com essa anotação é considerada o lado
	 * forte da relação 1xN.
	 */
	@ManyToOne
	@JoinColumn(name = "id_departamento_fk")
	private Departamento departamento;

	/**
	 * @OneToMany: Para mapear o lado fraco da relação um para muitos. O lado fraco é o lado 1,
	 * já lado forte seria o lado N da relação. Nessa anotação devemos incluir o
	 * atributo mappedBy com o nome do atributo que representa o lado fraco lá no
	 * mapeamento da classe de lado forte.
	 */
	@OneToMany(mappedBy = "cargo")
	private List<Funcionario> funcionarios;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

}
