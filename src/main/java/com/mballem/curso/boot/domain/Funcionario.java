package com.mballem.curso.boot.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.*;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "FUNCIONARIOS")
public class Funcionario extends AbstractEntity<Long> {

	@Column(nullable = false, unique = true)
	private String nome;

	/**
	 * O atributo columnDefinition serve para definir o tipo de dado no banco de
	 * dados
	 */
	@Column(nullable = false, columnDefinition = "DECIMAL(7,2) DEFAULT 0.00")
	private BigDecimal salario;

	@Column(name = "data_entrada", nullable = false, columnDefinition = "DATE")
	private LocalDate dataEntrada;

	@Column(name = "data_saida", columnDefinition = "DATE")
	private LocalDate dataSaida;

	/**
	 * Quando for inserir um funcionário no banco, també será insrido por cascada um
	 * endereço, a mesma coisa será caso precise editar um funcionário já edita
	 * também o endereço e quando excluir o funcionário, o endereço também será
	 * excluido junto.
	 */
	@OneToOne(cascade = CascadeType.ALL)
	/**
	 * @JoinColumn: Uma de suas funções é nomear a chave estrangeira de um
	 * relacionamento 1xN ou 1x1. Deve ser declarada sobre o atributo
	 * que representa a chave estrangeira.
	 */
	@JoinColumn(name = "endereco_id_fk")
	private Endereco endereco;

	/**
	 * Um cargo vai ter muitos funcionários
	 */
	@ManyToOne
	@JoinColumn(name = "cargo_id_fk")
	private Cargo cargo;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}

	public LocalDate getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(LocalDate dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public LocalDate getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(LocalDate dataSaida) {
		this.dataSaida = dataSaida;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

}
