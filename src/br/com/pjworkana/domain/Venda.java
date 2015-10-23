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
@Table(name = "venda")
@NamedQueries({
		@NamedQuery(name = "Venda.listar", query = "SELECT venda FROM Venda venda"),
		@NamedQuery(name = "Venda.buscarPorCodigo", query = "SELECT venda FROM Venda venda WHERE venda.codigo = :codigo") })
public class Venda {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ven_codigo")
	private Integer codigo;

	@Column(name = "ven_valor", nullable = false)
	private Double valor;

	@Column(name = "ven_desconto", nullable = false)
	private Double desconto;

	@Temporal(value = TemporalType.DATE)
	@Column(name = "ven_data", nullable = false)
	private Date dataHora;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ven_fun_codigo", referencedColumnName = "fun_codigo", nullable = false)
	private Funcionario funcionario = new Funcionario();

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ven_fpg_codigo", referencedColumnName = "fpg_codigo", nullable = false)
	private FormaPagamento formaPagamento = new FormaPagamento();

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ven_cli_codigo", referencedColumnName = "cli_codigo", nullable = false)
	private Cliente cliente = new Cliente();

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Double getDesconto() {
		if (desconto == null) {
			desconto = 0.0;
		}
		return desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	public Date getDataHora() {
		return dataHora;
	}

	public void setDataHora(Date dataHora) {
		this.dataHora = dataHora;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public String toString() {
		return "Venda [codigo=" + codigo + ", valor=" + valor + ", desconto="
				+ desconto + ", dataHora=" + dataHora + ", funcionario="
				+ funcionario + ", formaPagamento=" + formaPagamento
				+ ", cliente=" + cliente + "]";
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
		Venda other = (Venda) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}
