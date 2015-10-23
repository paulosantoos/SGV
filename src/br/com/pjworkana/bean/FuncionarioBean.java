package br.com.pjworkana.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.codec.digest.DigestUtils;

import br.com.pjworkana.dao.CidadeDAO;
import br.com.pjworkana.dao.EstadoDAO;
import br.com.pjworkana.dao.FuncaoDAO;
import br.com.pjworkana.dao.FuncionarioDAO;
import br.com.pjworkana.domain.Cidade;
import br.com.pjworkana.domain.Estado;
import br.com.pjworkana.domain.Funcao;
import br.com.pjworkana.domain.Funcionario;
import br.com.pjworkana.util.JSFUtil;

@ManagedBean(name = "MBFuncionario")
@ViewScoped
public class FuncionarioBean {
	private Funcionario funcionario;

	private List<Funcionario> funcionarios;
	private List<Funcionario> funcionariosFiltrados;

	private List<Funcao> comboFuncoes;

	private List<Cidade> comboCidades;
	private List<Estado> comboEstados;

	private String acao;
	private Integer codigo;

	public Funcionario getFuncionario() {
		if (funcionario == null) {
			funcionario = new Funcionario();
		}
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public List<Funcionario> getFuncionariosFiltrados() {
		return funcionariosFiltrados;
	}

	public void setFuncionariosFiltrados(List<Funcionario> funcionariosFiltrados) {
		this.funcionariosFiltrados = funcionariosFiltrados;
	}

	public List<Funcao> getComboFuncoes() {
		return comboFuncoes;
	}

	public void setComboFuncoes(List<Funcao> comboFuncoes) {
		this.comboFuncoes = comboFuncoes;
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
		funcionario = new Funcionario();
	}

	public void carregarPesquisa() {
		try {
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarios = funcionarioDAO.listar();
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}

	public void carregarCadastro() {
		try {
			if (codigo != null) {
				FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
				funcionario = funcionarioDAO.buscarPorCodigo(codigo);
			}
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}

	public void salvar() {
		try {
			//QUANDO MANDA SALVAR ELE PEGA A SENHA DIGITADA E GERA UM MD5 DELA
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionario.setSenha(DigestUtils.md5Hex((funcionario.getSenha())));
			funcionarioDAO.salvar(funcionario);

			funcionario = new Funcionario();

			funcionarios = funcionarioDAO.listar();

			JSFUtil.adicionarMensagemSucesso("Funcionário salvo com sucesso!");
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}

	public void excluir() {
		try {
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarioDAO.excluir(funcionario);

			funcionarios = funcionarioDAO.listar();

			JSFUtil.adicionarMensagemSucesso("Funcionário removido com sucesso!");
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}

	public void carregarCidades() {
		try {
			if (funcionario != null && funcionario.getEstado() != null
					&& funcionario.getEstado().getCodigo() != null) {
				CidadeDAO cidadeDAO = new CidadeDAO();
				comboCidades = cidadeDAO
						.buscarCidadesPorCodigoEstado(funcionario.getEstado()
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

	public void carregarFuncoes() {
		try {
			FuncaoDAO funcaoDAO = new FuncaoDAO();
			comboFuncoes = funcaoDAO.listar();
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}

	public void editar() {
		try {
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionario.setSenha(DigestUtils.md5Hex((funcionario.getSenha())));
			funcionarioDAO.editar(funcionario);

			funcionarios = funcionarioDAO.listar();

			JSFUtil.adicionarMensagemSucesso("Funcionário editado com sucesso!");
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}

	public void limparCampos(String frmPnl) {
		funcionario = new Funcionario();
		JSFUtil.limparCampos("frmPnl");
	}

}
