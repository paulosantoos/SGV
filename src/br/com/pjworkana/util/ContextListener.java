package br.com.pjworkana.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;



// ESSA CLASSE É RESPONSAVEL POR CHAMAR A CLASSE HBERNATE UTIL, ABRINDO ASSIM UMA SESSÃO.
//ESSA CLASSE É CARREGADA JUNTO COM O TOMCAT, ASSIM O SISTEMA FIA MUITO MAIS RÁPIDO PARA EXCUTAR
public class ContextListener implements ServletContextListener {

	
	public void contextDestroyed(ServletContextEvent arg0) {
		HibernateUtil.getSessionFactory().close();
	}

	
	public void contextInitialized(ServletContextEvent arg0) {
		HibernateUtil.getSessionFactory().openSession();
	}


	

}
