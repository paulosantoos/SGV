package br.com.pjworkana.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FlowEvent;

import br.com.pjworkana.dao.ClienteDAO;
import br.com.pjworkana.dao.FormaPagamentoDAO;
import br.com.pjworkana.dao.FuncionarioDAO;
import br.com.pjworkana.dao.ItemDAO;
import br.com.pjworkana.dao.ProdutoDAO;
import br.com.pjworkana.dao.VendaDAO;
import br.com.pjworkana.domain.Cliente;
import br.com.pjworkana.domain.FormaPagamento;
import br.com.pjworkana.domain.Funcionario;
import br.com.pjworkana.domain.Item;
import br.com.pjworkana.domain.Produto;
import br.com.pjworkana.domain.Venda;
import br.com.pjworkana.util.JSFUtil;

@ManagedBean(name = "MBVenda")
@ViewScoped
public class VendaBean {
	private List<Produto> produtos;
	private List<Produto> produtosFiltrados;
	private List<Cliente> comboClientes;

	private Item item;

	@ManagedProperty(value = "#{MBAutenticar}")
	private AutenticacaoBean autenticacaoBean;

	private Venda vendaCadastro;
	private List<Item> listaItens;
	private List<FormaPagamento> comboFormasPagamento;

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

	public List<Cliente> getComboClientes() {
		return comboClientes;
	}

	public void setComboClientes(List<Cliente> comboClientes) {
		this.comboClientes = comboClientes;
	}

	public Item getItem() {
		if (item == null) {
			item = new Item();
		}
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public AutenticacaoBean getAutenticacaoBean() {
		return autenticacaoBean;
	}

	public void setAutenticacaoBean(AutenticacaoBean autenticacaoBean) {
		this.autenticacaoBean = autenticacaoBean;
	}

	public Venda getVendaCadastro() {
		if (vendaCadastro == null) {
			vendaCadastro = new Venda();
			vendaCadastro.setValor(0.0);
			vendaCadastro.setDataHora(new Date());
		}
		return vendaCadastro;
	}

	public void setVendaCadastro(Venda vendaCadastro) {
		this.vendaCadastro = vendaCadastro;
	}

	public List<Item> getListaItens() {
		if (listaItens == null) {
			listaItens = new ArrayList<Item>();
		}
		return listaItens;
	}

	public void setListaItens(List<Item> listaItens) {
		this.listaItens = listaItens;
	}

	public List<FormaPagamento> getComboFormasPagamento() {
		return comboFormasPagamento;
	}

	public void setComboFormasPagamento(
			List<FormaPagamento> comboFormasPagamento) {
		this.comboFormasPagamento = comboFormasPagamento;
	}

	public void carregarProdutos() {
		try {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtos = produtoDAO.listar();
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}

	// METODO PARA ADICIONAR O PRODUTO A SER VENDIDO
	public void adicionar(Produto produto) {
		// INICIA UMA VARIAVEL PARA IDENTIFICAR A POSIÇÃO COM O VALOR -1, OU
		// SEJA, NADA FOI ENCONTRADO
		int posicaoEncontrada = -1;

		// FOR ONDE A POSICIÇÃO INICIAL A PERCORRER É ZERO, A POSIÇÃO TEM QUE
		// SER MENOR QUE O TAMANHO DA LISTA E A POSIÇÃO ENCONTRADA MENOR QUE
		// ZERO, DEPOIS INCREMENTA A POSIÇÃO
		for (int posicao = 0; posicao < listaItens.size()
				&& posicaoEncontrada < 0; posicao++) {
			//CRIA UM ITEM TEMPORARIO, PEGANDO A POSIÇAO DELE NA LISTA
			Item itemTemp = listaItens.get(posicao);

			//SE O ITEM TEMPORARIO É IGUAL AO PRODUTO
			if (itemTemp.getProduto().equals(produto)) {
				// A POSIÇÃO QUE O ITEM FOI ENCONTRADO = A POSIÇÃO
				posicaoEncontrada = posicao;
			}
		}

		//INICIALIZA O ONJETO
		Item item = new Item();
		//SETA A FK COM O PRODUTO
		item.setProduto(produto);

		//SE A POSIÇÃO ENCONTRADA É MENOR QUE ZERO
		if (posicaoEncontrada < 0) {
			//ELE PEGA O ITEM E SETA A QUANTIDADE PARA 1
			item.setQuantidade(1.0);
			//SE A QUANTIDADE QUE TEM NO CADASTRO DO PRODUTO É MAIOR OU IGAUL A QUANTIDADE DO ITEM
			if (produto.getQuantidade() >= item.getQuantidade()) {
				//SETA O VALOR E ADICIONA O ITEM NA LISTA
				item.setValor(produto.getValorUnitario());

				listaItens.add(item);
				vendaCadastro.setValor(vendaCadastro.getValor()
						+ produto.getValorUnitario());
			}
		} else {
			//SE O ITEM JÁ TIVER NA LISTA ELE PEGA A POSIÇÃO
			Item itemTemp = listaItens.get(posicaoEncontrada);
			//INCREMENTA A QUANTIDADE
			item.setQuantidade(itemTemp.getQuantidade() + 1.0);
			//SE A QUANTIDADE NO CADASTRO DO PRODUTO É MAIOR OU IGUAL A QUE ESTA TENTANDO ADICIONAR
			if (produto.getQuantidade() >= item.getQuantidade()) {
				//SETA O VALOR E MULTIPLICA PELA QUANTIDADE
				item.setValor(produto.getValorUnitario() * item.getQuantidade());

				listaItens.set(posicaoEncontrada, item);
				vendaCadastro.setValor(vendaCadastro.getValor()
						+ produto.getValorUnitario());
			}
		}

	}

	
	//REMOVO O ITEM DA LISTA
	public void remover(Item item) {
		int posicaoEncontrada = -1;

		//FOR PARA PEGAR A POSIÇÃO
		for (int posicao = 0; posicao < listaItens.size()
				&& posicaoEncontrada < 0; posicao++) {
			Item itemTemp = listaItens.get(posicao);

			//COMPARA SE É IGUAL O PROUTO DO ITEM
			if (itemTemp.getProduto().equals(item.getProduto())) {
				posicaoEncontrada = posicao;
			}
		}

		//VERIFICA SE ELE É O UNICO NA LISTA E DIMINUI OS VALORES
		if (posicaoEncontrada > -1) {
			listaItens.remove(posicaoEncontrada);
			vendaCadastro.setValor(vendaCadastro.getValor() - item.getValor());

			if (vendaCadastro.getValor() < 0) {
				vendaCadastro.setValor(0.0);
			}

			if (listaItens.isEmpty()) {
				vendaCadastro.setDesconto(0D);
			}
		}
	}

	//APLICA UM DESCONTO NA VENDA
	public void descontoVenda() {
		if (vendaCadastro.getDesconto() > vendaCadastro.getValor()) {
			vendaCadastro.setDesconto(0D);
			JSFUtil.adicionarMensagemErro("Valor de desconto não pode ser maior que da venda");
		} else if (vendaCadastro.getDesconto() < 0) {
			vendaCadastro.setDesconto(0D);
			JSFUtil.adicionarMensagemErro("Desconto não poder ser negativo");
		} else if (vendaCadastro.getDesconto() == 0) {
			JSFUtil.adicionarMensagemErro("Desconto deve ser maior que 0");
			vendaCadastro.setDesconto(0D);
		} else {
			vendaCadastro.setValor(vendaCadastro.getValor()
					- vendaCadastro.getDesconto());
			vendaCadastro.setDesconto(vendaCadastro.getDesconto());
		}
	}

	
	//DESFAZ O DESCONTO
	public void desfazerDesconto() {
		vendaCadastro.setValor(vendaCadastro.getValor()
				+ vendaCadastro.getDesconto());
		vendaCadastro.setDesconto(vendaCadastro.getDesconto()
				- vendaCadastro.getDesconto());
	}

	
	//CARREGA O COMBO DE FORMA DE PAGAMENTO
	public void carregarFormasPagamento() {
		try {
			FormaPagamentoDAO formaPagamentoDAO = new FormaPagamentoDAO();
			comboFormasPagamento = formaPagamentoDAO.listar();
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}

	
	// CARREGA O COMBO DE CLIENTE
	public void carregarClientes() {
		try {
			ClienteDAO clienteDAO = new ClienteDAO();
			comboClientes = clienteDAO.listar();
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}

	//METODO PADRÃO DO COMPONENTE DO PRIMEFACES "WIZARD" QUE FAZ A NAVEGAÇÃO DE TELAS
	public String flowHandler(FlowEvent evento) {
		String paginaAtual = evento.getOldStep();
		String proximaPagina = evento.getNewStep();

		if (listaItens.isEmpty()) {
			JSFUtil.adicionarMensagemErro("Carrinho está vazio");
			RequestContext.getCurrentInstance().update("msgGlobal");
			return paginaAtual;
		} else {
			return proximaPagina;
		}
	}

	
	//METODO QUE SALVA A VENDA NO BANCO
	public void finalizarVenda() {
		try {

			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			Funcionario funcionario = funcionarioDAO
					.buscarPorCodigo(autenticacaoBean.getFuncionarioLogado()
							.getCodigo());
			vendaCadastro.setFuncionario(funcionario);

			VendaDAO vendaDAO = new VendaDAO();

			Integer codigoVenda = vendaDAO.salvar(vendaCadastro);
			Venda vendaFK = vendaDAO.buscarPorCodigo(codigoVenda);

			for (Item item : listaItens) {
				item.setVenda(vendaFK);

				ItemDAO itemDAO = new ItemDAO();
				itemDAO.salvar(item);
			}

			vendaCadastro = new Venda();
			vendaCadastro.setValor(0D);

			listaItens = new ArrayList<Item>();

			JSFUtil.adicionarMensagemSucesso("Venda gravada com sucesso!");
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro("Erro ao tentar gravar a venda: "
					+ ex.getMessage());
		}
	}

}
