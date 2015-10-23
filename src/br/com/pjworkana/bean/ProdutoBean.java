package br.com.pjworkana.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.pjworkana.dao.ProdutoDAO;

import br.com.pjworkana.domain.Produto;
import br.com.pjworkana.util.JSFUtil;

@ManagedBean(name = "MBProduto")
@ViewScoped
public class ProdutoBean {
	private Produto produto;

	private List<Produto> produtos;
	private List<Produto> produtosFiltrados;

	// private List<Fornecedor> comboFornecedores;

	private String acao;
	private Integer codigo;

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public List<Produto> getProdutosFiltrados() {
		return produtosFiltrados;
	}

	public void setProdutosFiltrados(List<Produto> produtosFiltrados) {
		this.produtosFiltrados = produtosFiltrados;
	}

	// public List<Fornecedor> getComboFornecedores() {
	// return comboFornecedores;
	// }
	//
	// public void setComboFornecedores(List<Fornecedor> comboFornecedores) {
	// this.comboFornecedores = comboFornecedores;
	// }

	public String getAcao() {
		return acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public void novo() {
		produto = new Produto();
	}

	public void carregarPesquisa() {
		try {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtos = produtoDAO.listar();
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}

	public void carregarCadastro() {
		try {
			if (codigo != null) {
				ProdutoDAO produtoDAO = new ProdutoDAO();
				produto = produtoDAO.buscarPorCodigo(codigo);
			} else {
				produto = new Produto();
			}

			// FornecedorDAO fornecedorDAO = new FornecedorDAO();
			// comboFornecedores = fornecedorDAO.listar();
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}

	public void salvar() {
		try {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtoDAO.salvar(produto);

			produto = new Produto();

			produtos = produtoDAO.listar();

			JSFUtil.adicionarMensagemSucesso("Produto salvo com sucesso!");
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}

	public void excluir() {
		try {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtoDAO.excluir(produto);

			produtos = produtoDAO.listar();

			JSFUtil.adicionarMensagemSucesso("Produto removido com sucesso!");
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}

	// public void carregarFornecedores() {
	// try {
	// FornecedorDAO fornecedorDAO = new FornecedorDAO();
	// comboFornecedores = fornecedorDAO.listar();
	// } catch (RuntimeException ex) {
	// ex.printStackTrace();
	// JSFUtil.adicionarMensagemErro(ex.getMessage());
	// }
	// }

	public void editar() {
		try {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtoDAO.editar(produto);

			produtos = produtoDAO.listar();

			JSFUtil.adicionarMensagemSucesso("Produto editado com sucesso!");
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}

	public void limparCampos() {
		produto = new Produto();
		JSFUtil.limparCampos("frmProduto:pnlProduto");
	}

}
