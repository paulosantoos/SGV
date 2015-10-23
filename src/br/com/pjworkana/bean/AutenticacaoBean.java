package br.com.pjworkana.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.codec.digest.DigestUtils;

import br.com.pjworkana.dao.FuncionarioDAO;
import br.com.pjworkana.domain.Funcionario;
import br.com.pjworkana.util.JSFUtil;

@ManagedBean(name = "MBAutenticar")
// INDICA QUE ESSA CLASSE � UM BEAN E DA UM NOME PRA ELE
@SessionScoped
// CRIA UM ESCOPO DE SESS�O PARA ESSA CLASSE, OU SEJA, O OBJETO DESSA CLASSE
// FICA NA MEMORIA ENQUANTO A APLICA��O EST� EM EXECU��O
public class AutenticacaoBean {

	// CRIA UM VARIAVEL DO FUNCIONARIO
	private Funcionario funcionarioLogado;

	// SE O OBJETO � NULO, ELE D� UM NEW
	public Funcionario getFuncionarioLogado() {
		if (funcionarioLogado == null) {
			funcionarioLogado = new Funcionario();
		}
		return funcionarioLogado;
	}

	public void setFuncionarioLogado(Funcionario funcionarioLogado) {
		this.funcionarioLogado = funcionarioLogado;
	}

	// M�TODO PARA AUTENTICA��O QUE � CHAMADO NA TELA
	public String autenticar() {

		// VARIVEL PARA URL
		String entrar = null;

		try {
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();

			// CHAMA O METODO DE AUTENTICAR DO DAO E O DigestUtils � PARA GERAR
			// O MD5 DA SENHA
			funcionarioLogado = funcionarioDAO.autenticar(
					funcionarioLogado.getNomeUsuario(),
					DigestUtils.md5Hex(funcionarioLogado.getSenha()));

			// SE O OBJETO FOR NULO, SIGNIFICA QUE N�O AUTENTICOU
			if (funcionarioLogado == null) {
				JSFUtil.adicionarMensagemErro("Nome de usu�rio e/ou senha inv�lidos");
			} else {
				// SE DER CERTO, REDIRECIONA PARA A PAGINA PRINCIPAL
				entrar = "/pages/principal.xhtml?faces-redirect=true";
			}
		} catch (RuntimeException ex) {
			JSFUtil.adicionarMensagemErro("Erro ao tentar autenticar no sistema: "
					+ ex.getMessage());
		}
		return entrar;
	}

	// METODO PARA SAIR DA APLICA��O
	public String sair() {
		// RETORNA PARA A PAGINA DE LOGIN
		funcionarioLogado = null;
		return "/pages/autenticacao.xhtml?faces-redirect=true";
	}

}
