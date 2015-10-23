package br.com.pjworkana.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConectaBancoMVC {

	
	// criando objeto que armazenara a conex�o com banco de dados
		Connection con = null;

		public ConectaBancoMVC() {

			carregarDriver();
		}

		private void carregarDriver() {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				System.out.println("Driver Carregado....");
			} catch (Exception e) {
				System.out.println("O driver n�o pode ser carregado.Erro "
						+ e.getMessage());
			}

		}

		/**
		 * M�todo Responsavel por retornar uma conexao a qualquer classe que o
		 * invocar Caso a conex�o n�o exista ela a criar�
		 *
		 * @return conex�o
		 */
		public Connection obterConexao() {

			try {
				if ((con == null) || (con.isClosed())) {
					con = DriverManager.getConnection(
							"jdbc:mysql://localhost/pj_workana", "root", "root");
					System.out.println("Conex�o obtida com sucesso!!");
				}
			} catch (Exception e) {
				System.out.println("Erro ao conectar com o banco" + e.getMessage());
			}
			return con;

		}

		

	}
