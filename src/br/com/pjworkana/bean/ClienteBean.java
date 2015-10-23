package br.com.pjworkana.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.pjworkana.dao.CidadeDAO;
import br.com.pjworkana.dao.ClienteDAO;
import br.com.pjworkana.dao.EstadoDAO;
import br.com.pjworkana.domain.Cidade;
import br.com.pjworkana.domain.Cliente;
import br.com.pjworkana.domain.Estado;
import br.com.pjworkana.util.JSFUtil;

@ManagedBean(name = "MBCliente")
@ViewScoped
public class ClienteBean {
	// VARIAVEL DO DOMAIN
	private Cliente cliente;

	// LISTA PARA ARMAZENAR OS CLIENTES
	private List<Cliente> clientes;
	// LISTA PARA ARMAZENAR OS CLIENTES FILTRADOS
	private List<Cliente> clientesFiltrados;
	// LISTA PARA CARREGAR O COMBO
	private List<Cidade> comboCidades;
	// LISTA PARA CARREGAR O COMBO
	private List<Estado> comboEstados;
	// VARIAVEL PARA SABER QUAL É A AÇÃO A SER EXECUTADA, NOVO/EXCLUIR/ALTERAR
	private String acao;
	private Integer codigo;

	
	//GETS SETS
	public Cliente getCliente() {
		if (cliente == null) {
			cliente = new Cliente();
		}
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public List<Cliente> getClientesFiltrados() {
		return clientesFiltrados;
	}

	public void setClientesFiltrados(List<Cliente> clientesFiltrados) {
		this.clientesFiltrados = clientesFiltrados;
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
		cliente = new Cliente();
	}
	
	//METODO USADO PARA CARREGAR OS DADOS NA TABELA

	public void carregarPesquisa() {
		try {
			//CHAMA O DAO E POPULA A LISTA CRIADA ACIMA
			ClienteDAO clienteDAO = new ClienteDAO();
			clientes = clienteDAO.listar();
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}

	

	
	public void carregarCadastro() {
		try {
			if (codigo != null) {
				ClienteDAO clienteDAO = new ClienteDAO();
				cliente = clienteDAO.buscarPorCodigo(codigo);
			}
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}

	
	//METODO PARA SALVAR, CHAMA O DAO E SALVA O OBJETO, DEPOIS MANDA LISTAR NOVAMENTE
	public void salvar() {
		try {
			ClienteDAO clienteDAO = new ClienteDAO();
			clienteDAO.salvar(cliente);

			cliente = new Cliente();

			clientes = clienteDAO.listar();

			JSFUtil.adicionarMensagemSucesso("Cliente salvo com sucesso!");
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}

	//METODO PARA EXCLUIR, CHAMA O DAO E EXCLUI O OBJETO, DEPOIS MANDA LISTAR NOVAMENTE
	public void excluir() {
		try {
			ClienteDAO clienteDAO = new ClienteDAO();
			clienteDAO.excluir(cliente);

			clientes = clienteDAO.listar();

			JSFUtil.adicionarMensagemSucesso("Cliente removido com sucesso!");
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}


//CARREGA O COBO DE CIDADES DE ACORDO COM O ESTADO SELECIONADO
	public void carregarCidades() {
		try {
			if (cliente != null && cliente.getEstado() != null
					&& cliente.getEstado().getCodigo() != null) {
				CidadeDAO cidadeDAO = new CidadeDAO();
				comboCidades = cidadeDAO.buscarCidadesPorCodigoEstado(cliente
						.getEstado().getCodigo());
			} else {
				comboCidades = new ArrayList<Cidade>();
			}
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}

	
	//CARREGA O COMBO DE ESTADOS
	public void carregarEstados() {
		try {
			EstadoDAO estadoDAO = new EstadoDAO();
			comboEstados = estadoDAO.listar();
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}

	//METODO PARA SALVAR, CHAMA O DAO E SALVA O OBJETO, DEPOIS MANDA LISTAR NOVAMENTE
	public void editar() {
		try {
			ClienteDAO clienteDAO = new ClienteDAO();
			clienteDAO.editar(cliente);

			clientes = clienteDAO.listar();

			JSFUtil.adicionarMensagemSucesso("Cliente editado com sucesso!");
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}

	//METODO PARA LIMPAR OS CAMPOS NA TELA
	public void limparCampos(String frmPnl) {
		cliente = new Cliente();
		JSFUtil.limparCampos("frmPnl");
	}

}
