package br.com.pjworkana.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConectaBancoMVC {

	
	// criando objeto que armazenara a conexão com banco de dados
		Connection con = null;

		public ConectaBancoMVC() {

			carregarDriver();
		}

		private void carregarDriver() {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				System.out.println("Driver Carregado....");
			} catch (Exception e) {
				System.out.println("O driver não pode ser carregado.Erro "
						+ e.getMessage());
			}

		}

		/**
		 * Método Responsavel por retornar uma conexao a qualquer classe que o
		 * invocar Caso a conexão não exista ela a criará
		 *
		 * @return conexão
		 */
		public Connection obterConexao() {

			try {
				if ((con == null) || (con.isClosed())) {
					con = DriverManager.getConnection(
							"jdbc:mysql://localhost/pj_workana", "root", "root");
					System.out.println("Conexão obtida com sucesso!!");
				}
			} catch (Exception e) {
				System.out.println("Erro ao conectar com o banco" + e.getMessage());
			}
			return con;

		}

		

	}
