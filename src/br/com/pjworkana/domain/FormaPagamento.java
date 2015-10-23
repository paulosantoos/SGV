package br.com.pjworkana.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "forma_pagamento")
@NamedQueries({
		@NamedQuery(name = "FormaPagamento.listar", query = "SELECT formaPagamento FROM FormaPagamento formaPagamento"),
		@NamedQuery(name = "FormaPagamento.buscarPorCodigo", query = "SELECT formaPagamento FROM FormaPagamento formaPagamento WHERE formaPagamento.codigo = :codigo") })
public class FormaPagamento {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "fpg_codigo")
	private Integer codigo;

	@Column(name = "fpg_descricao", length = 25, nullable = false)
	private String descricao;

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return "FormaPagamento [codigo=" + codigo + ", descricao=" + descricao
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FormaPagamento other = (FormaPagamento) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}
