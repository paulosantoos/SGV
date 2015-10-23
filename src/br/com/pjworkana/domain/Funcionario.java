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
@Table(name = "funcionario")
@NamedQueries({
		@NamedQuery(name = "Funcionario.listar", query = "SELECT funcionario FROM Funcionario funcionario"),
		@NamedQuery(name = "Funcionario.buscarPorCodigo", query = "SELECT funcionario FROM Funcionario funcionario WHERE codigo = :codigo"),
		@NamedQuery(name = "Funcionario.autenticar", query = "SELECT funcionario FROM Funcionario funcionario WHERE funcionario.nomeUsuario = :nomeUsuario AND funcionario.senha = :senha") })
public class Funcionario {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "fun_codigo")
	private Integer codigo;

	@Column(name = "fun_nome", length = 50, nullable = false)
	private String nome;

	@Column(name = "fun_nomeUsuario", length = 50, nullable = false, unique = true)
	private String nomeUsuario;

	@Column(name = "fun_cpf", length = 15, nullable = false, unique = true)
	private String cpf;

	@Column(name = "fun_senha", length = 32, nullable = false)
	private String senha;

	@Column(name = "fun_celular", length = 15)
	private String celular;

	@Column(name = "fun_telefone", length = 15)
	private String telefone;

	@Temporal(value = TemporalType.DATE)
	@Column(name = "fun_dataNascimento", nullable = false)
	private Date dataNascimento;

	@Temporal(value = TemporalType.DATE)
	@Column(name = "fun_dataCadastro", nullable = false)
	private Date dataCadastro;

	@Column(name = "fun_email", length = 80)
	private String email;

	@Column(name = "fun_endereco", length = 50, nullable = false)
	private String endereco;

	@Column(name = "fun_bairro", length = 20)
	private String bairro;

	@Column(name = "fun_complemento", length = 20)
	private String complemento;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fun_fuc_codigo", referencedColumnName = "fuc_codigo", nullable = false)
	private Funcao funcao = new Funcao();

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fun_cid_codigo", referencedColumnName = "cid_codigo", nullable = false)
	private Cidade cidade = new Cidade();

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fun_est_codigo", referencedColumnName = "est_codigo", nullable = false)
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

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
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

	public Funcao getFuncao() {
		return funcao;
	}

	public void setFuncao(Funcao funcao) {
		this.funcao = funcao;
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
		return "Funcionario [codigo=" + codigo + ", nome=" + nome
				+ ", nomeUsuario=" + nomeUsuario + ", cpf=" + cpf + ", senha="
				+ senha + ", celular=" + celular + ", telefone=" + telefone
				+ ", dataNascimento=" + dataNascimento + ", dataCadastro="
				+ dataCadastro + ", email=" + email + ", endereco=" + endereco
				+ ", bairro=" + bairro + ", complemento=" + complemento
				+ ", funcao=" + funcao + ", cidade=" + cidade + ", estado="
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
		Funcionario other = (Funcionario) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}
