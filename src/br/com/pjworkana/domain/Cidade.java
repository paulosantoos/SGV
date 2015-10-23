package br.com.pjworkana.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity //ESSA ANOTAÇÃO DIS QUE ESSA CLASSE É UMA ENTIDADE DO BANCO DE DADOS, OU SEJA UMA TABELA
@Table(name = "cidade") //ESSA ANOTAÇÃO DIZ QUAL O NOME DA TABELA NO BANCO
@NamedQueries({ // AS NAMED QUERY, SÃO AS QUERY NOMEADAS, OU SEJA, CONSULTAS PARA O BANCO DE DADOS
		@NamedQuery(name = "Cidade.listar", query = "SELECT cidade FROM Cidade cidade"),
		@NamedQuery(name = "Cidade.buscarPorCodigo", query = "SELECT cidade FROM Cidade cidade WHERE codigo = :codigo"),
		@NamedQuery(name = "Cidade.buscarCidadesPorCodigoEstado", query = "SELECT cidade FROM Cidade cidade WHERE estado.codigo = :codigo") })
public class Cidade {
	@Id //DEFINE QUE É A CHAVE PRIMARIA
	@GeneratedValue(strategy = GenerationType.AUTO) //DEFINE QUE O VALOR SERÁ GERADO AUTOMATICAMENTE
	@Column(name = "cid_codigo") //DEFINE A COLUNA CORRESPONDENTE NO BANCO DE DADOS
	private Integer codigo;

	@ManyToOne(fetch = FetchType.EAGER) //DEFINE A CHAVE ESTRANGEIRA, FETCH.EAGER, SIGNIFICA QUE QUANDO CHAMAR A CLASSE CIDADE, CARREGARA TBM OS DADOS DO ESTADO
	@JoinColumn(name = "cid_est_codigo", referencedColumnName = "est_codigo", nullable = false) //DEFINE QUAIS OS CAMPOS DAS TABELAS, NULLABLE = FALSE SINIFICA QUE NÃO PODE SER NULO
	private Estado estado;

	@Column(name = "cid_nome", length = 50, nullable = false) //LENGTH = 50 DEFINE O TAMANHO DO CAMPO
	private String nome;

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "Cidade [codigo=" + codigo + ", nome=" + nome + ", estado="
				+ estado + "]";
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
		Cidade other = (Cidade) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}
