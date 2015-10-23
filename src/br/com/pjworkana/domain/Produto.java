package br.com.pjworkana.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "produto")
@NamedQueries({
		@NamedQuery(name = "Produto.listar", query = "SELECT produto FROM Produto produto"),
		@NamedQuery(name = "Produto.buscarPorCodigo", query = "SELECT produto FROM Produto produto WHERE codigo = :codigo") })
public class Produto {

	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "pro_codigo")
	private Integer codigo;

	@Column(name = "pro_descricao", length = 200, nullable = false)
	private String descricao;

	@Column(name = "pro_valorUnitario", nullable = false)
	private double valorUnitario;

	@Column(name = "pro_valorCompra", nullable = false)
	private double valorCompra;
	
	@Column(name = "pro_quantidade", nullable = false)
	private double quantidade;

	@Temporal(value = TemporalType.DATE)
	@Column(name = "pro_dataCadastro", nullable = false)
	private Date dataCadastro;

	
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

	public double getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public double getValorCompra() {
		return valorCompra;
	}

	public void setValorCompra(double valorCompra) {
		this.valorCompra = valorCompra;
	}

	public double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(double quantidade) {
		this.quantidade = quantidade;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	@Override
	public String toString() {
		return "Produto [codigo=" + codigo
				+ ", descricao=" + descricao + ", valorUnitario="
				+ valorUnitario + ", valorCompra=" + valorCompra
				+ ", quantidade=" + quantidade + ", dataCadastro="
				+ dataCadastro + "]";
	}

	
	


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result
				+ ((dataCadastro == null) ? 0 : dataCadastro.hashCode());
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		long temp;
		temp = Double.doubleToLongBits(quantidade);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorCompra);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorUnitario);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Produto other = (Produto) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (dataCadastro == null) {
			if (other.dataCadastro != null)
				return false;
		} else if (!dataCadastro.equals(other.dataCadastro))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (Double.doubleToLongBits(quantidade) != Double
				.doubleToLongBits(other.quantidade))
			return false;
		if (Double.doubleToLongBits(valorCompra) != Double
				.doubleToLongBits(other.valorCompra))
			return false;
		if (Double.doubleToLongBits(valorUnitario) != Double
				.doubleToLongBits(other.valorUnitario))
			return false;
		return true;
	}

	// @ManyToOne(fetch = FetchType.EAGER)
	// @JoinColumn(name = "pro_for_codigo", referencedColumnName = "for_codigo",
	// nullable = false)
	// private Fornecedor fornecedor = new Fornecedor();




}