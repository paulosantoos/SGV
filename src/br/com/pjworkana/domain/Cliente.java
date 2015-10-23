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
@Table(name = "cliente")
@NamedQueries({
		@NamedQuery(name = "Cliente.listar", query = "SELECT cliente FROM Cliente cliente"),
		@NamedQuery(name = "Cliente.buscarPorCodigo", query = "SELECT cliente FROM Cliente cliente WHERE codigo = :codigo") })
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cli_codigo")
	private Integer codigo;

	@Column(name = "cli_nome", length = 50, nullable = false)
	private String nome;

	@Column(name = "cli_cpf", length = 15, nullable = false, unique = true) //UNIQUE SIGNIFICA QUE ESSE VALOR SERÁ UNICO NO BANCO DE DADOS
	private String cpf;

	// @Column(name = "cli_sexo", length = 1, nullable = false)
	// private String sexo;

	@Column(name = "cli_celular", length = 15)
	private String celular;

	@Column(name = "cli_telefone", length = 15)
	private String telefone;

	@Temporal(value = TemporalType.DATE) // ANOTAÇÃO PARA DIZER QUE É TEMPO, SERVE PARA DATA E HORA
	@Column(name = "cli_dataNascimento", nullable = false)
	private Date dataNascimento;

	@Temporal(value = TemporalType.DATE)
	@Column(name = "cli_dataCadastro", nullable = false)
	private Date dataCadastro;

	@Column(name = "cli_email", length = 80)
	private String email;

	@Column(name = "cli_endereco", length = 45, nullable = false)
	private String endereco;

	@Column(name = "cli_bairro", length = 20, nullable = false)
	private String bairro;

	@Column(name = "cli_complemento", length = 20, nullable = false)
	private String complemento;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cli_cid_codigo", referencedColumnName = "cid_codigo", nullable = false)
	private Cidade cidade = new Cidade(); //AQUI FOI DADO NULL NO OBJETO PARA QUE ASSIM TODA A VEZ QUE CHAMAR A CLASSE, AS FK TBM SEJAM INICIADAS. ISSO AJUDA PARA CARREGAR OS COMBO BOX

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cli_est_codigo", referencedColumnName = "est_codigo", nullable = false)
	private Estado estado = new Estado();

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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
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

	@Override
	public String toString() {
		return "Cliente [codigo=" + codigo + ", nome=" + nome + ", cpf=" + cpf
				+ ", celular=" + celular + ", telefone=" + telefone
				+ ", dataNascimento=" + dataNascimento + ", dataCadastro="
				+ dataCadastro + ", email=" + email + ", endereco=" + endereco
				+ ", bairro=" + bairro + ", complemento=" + complemento
				+ ", cidade=" + cidade + ", estado=" + estado + "]";
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
		Cliente other = (Cliente) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}
