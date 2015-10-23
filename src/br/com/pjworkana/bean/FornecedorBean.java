package br.com.pjworkana.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.pjworkana.dao.CidadeDAO;
import br.com.pjworkana.dao.EstadoDAO;
import br.com.pjworkana.dao.FornecedorDAO;
import br.com.pjworkana.domain.Cidade;
import br.com.pjworkana.domain.Estado;
import br.com.pjworkana.domain.Fornecedor;
import br.com.pjworkana.util.JSFUtil;

@ManagedBean(name = "MBFornecedor")
@ViewScoped
public class FornecedorBean {
	private Fornecedor fornecedor;

	private List<Fornecedor> fornecedores;
	private List<Fornecedor> fornecedoresFiltrados;

	private List<Cidade> comboCidades;
	private List<Estado> comboEstados;

	private String acao;
	private Integer codigo;

	public Fornecedor getFornecedor() {
		if (fornecedor == null) {
			fornecedor = new Fornecedor();
		}
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public List<Fornecedor> getFornecedores() {
		return fornecedores;
	}

	public void setFornecedores(List<Fornecedor> fornecedores) {
		this.fornecedores = fornecedores;
	}

	public List<Fornecedor> getFornecedoresFiltrados() {
		return fornecedoresFiltrados;
	}

	public void setFornecedoresFiltrados(List<Fornecedor> fornecedoresFiltrados) {
		this.fornecedoresFiltrados = fornecedoresFiltrados;
	}

	public List<Cidade> getComboCidades() {
		return comboCidades;
	}

	public void setComboCidades(List<Cidade> comboCidades) {
		this.comboCidades = comboCidades;
	}

	public List<Estado> getComboEstados() {
		return comboEstados;
	}

	public void setComboEstados(List<Estado> comboEstados) {
		this.comboEstados = comboEstados;
	}

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
		fornecedor = new Fornecedor();
	}

	public void carregarPesquisa() {
		try {
			FornecedorDAO fornecedorDAO = new FornecedorDAO();
			fornecedores = fornecedorDAO.listar();
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}

	public void carregarCadastro() {
		try {
			if (codigo != null) {
				FornecedorDAO fornecedorDAO = new FornecedorDAO();
				fornecedor = fornecedorDAO.buscarPorCodigo(codigo);
			}
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}

	public void salvar() {
		try {
			FornecedorDAO fornecedorDAO = new FornecedorDAO();
			fornecedorDAO.salvar(fornecedor);

			fornecedor = new Fornecedor();

			fornecedores = fornecedorDAO.listar();

			JSFUtil.adicionarMensagemSucesso("Fornecedor salvo com sucesso!");
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}

	public void excluir() {
		try {
			FornecedorDAO fornecedorDAO = new FornecedorDAO();
			fornecedorDAO.excluir(fornecedor);

			fornecedores = fornecedorDAO.listar();

			JSFUtil.adicionarMensagemSucesso("Fornecedor removido com sucesso!");
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}

	public void carregarCidades() {
		try {
			if (fornecedor != null && fornecedor.getEstado() != null
					&& fornecedor.getEstado().getCodigo() != null) {
				CidadeDAO cidadeDAO = new CidadeDAO();
				comboCidades = cidadeDAO
						.buscarCidadesPorCodigoEstado(fornecedor.getEstado()
								.getCodigo());
			} else {
				comboCidades = new ArrayList<Cidade>();
			}
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}

	public void carregarEstados() {
		try {
			EstadoDAO estadoDAO = new EstadoDAO();
			comboEstados = estadoDAO.listar();
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}

	public void editar() {
		try {
			FornecedorDAO FornecedorDAO = new FornecedorDAO();
			FornecedorDAO.editar(fornecedor);

			fornecedores = FornecedorDAO.listar();

			JSFUtil.adicionarMensagemSucesso("Fornecedor editado com sucesso!");
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}

	public void limparCampos(String frmPnl) {
		fornecedor = new Fornecedor();
		JSFUtil.limparCampos("frmPnl");
	}

}
