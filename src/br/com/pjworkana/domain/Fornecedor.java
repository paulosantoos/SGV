package br.com.pjworkana.domain;

import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "fornecedor")
@NamedQueries({
		@NamedQuery(name = "Fornecedor.listar", query = "SELECT fornecedor FROM Fornecedor fornecedor"),
		@NamedQuery(name = "Fornecedor.buscarPorCodigo", query = "SELECT fornecedor FROM Fornecedor fornecedor WHERE fornecedor.codigo = :codigo") })
public class Fornecedor {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "for_codigo")
	private Integer codigo;

	@Column(name = "for_nomeFantasia", length = 40, nullable = false)
	private String nomeFantasia;

	@Column(name = "for_cnpj", length = 18, nullable = false)
	private String cnpj;

	@Column(name = "for_inscricaoEstadual", length = 20)
	private String inscricaoEstadual;

	@Column(name = "for_telefone", length = 13)
	private String telefone;

	@Column(name = "for_celular", length = 13)
	private String celular;

	@Column(name = "for_email", length = 80)
	private String email;

	@Column(name = "for_endereco", length = 45, nullable = false)
	private String endereco;

	@Column(name = "for_bairro", length = 45)
	private String bairro;

	@Column(name = "for_complemento", length = 35)
	private String complemento;

	@Column(name = "for_contato", length = 45)
	private String contato;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "for_cid_codigo", referencedColumnName = "cid_codigo", nullable = false)
	private Cidade cidade = new Cidade();

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "for_est_codigo", referencedColumnName = "est_codigo", nullable = false)
	private Estado estado = new Estado();

	@Temporal(value = TemporalType.DATE)
	@Column(name = "for_dataCadastro", nullable = false)
	private Date dataCadastro;

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getInscricaoEstadual() {
		return inscricaoEstadual;
	}

	public void setInscricaoEstadual(String inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	@Override
	public String toString() {
		return "Fornecedor [codigo=" + codigo + ", nomeFantasia="
				+ nomeFantasia + ", cnpj=" + cnpj + ", inscricaoEstadual="
				+ inscricaoEstadual + ", telefone=" + telefone + ", celular="
				+ celular + ", email=" + email + ", endereco=" + endereco
				+ ", bairro=" + bairro + ", complemento=" + complemento
				+ ", contato=" + contato + ", cidade=" + cidade + ", estado="
				+ estado + ", dataCadastro=" + dataCadastro + "]";
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
		Fornecedor other = (Fornecedor) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}
