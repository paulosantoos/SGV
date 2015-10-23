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
@Table(name = "estado")
@NamedQueries({
		@NamedQuery(name = "Estado.listar", query = "SELECT estado FROM Estado estado"),
		@NamedQuery(name = "Estado.buscarPorCodigo", query = "SELECT estado FROM Estado estado WHERE codigo = :codigo") })
public class Estado {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "est_codigo")
	private Integer codigo;

	@Column(name = "est_nome", length = 20, nullable = false)
	private String nome;

	@Column(name = "est_sigla", length = 2, nullable = false)
	private String sigla;

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	@Override
	public String toString() {
		return "Estado [codigo=" + codigo + ", nome=" + nome + ", sigla="
				+ sigla + "]";
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
		Estado other = (Estado) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}
