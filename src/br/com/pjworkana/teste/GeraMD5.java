package br.com.pjworkana.teste;

import org.apache.commons.codec.digest.DigestUtils;

import br.com.pjworkana.dao.FuncionarioDAO;
import br.com.pjworkana.domain.Funcionario;

public class GeraMD5 {

	public static void main (String args[]){
	
	
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		Funcionario funcionarioLogado = new Funcionario();

		funcionarioLogado.setNomeUsuario("paulo");
		funcionarioLogado.setSenha("123456");
		
		// CHAMA O METODO DE AUTENTICAR DO DAO E O DigestUtils É PARA GERAR
		// O MD5 DA SENHA
		
		String senhaa = null;
				senhaa = DigestUtils.md5Hex(funcionarioLogado.getSenha());
				System.out.println(senhaa);

		
	
	}
		
}
