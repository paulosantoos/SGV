package br.com.pjworkana.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;



// ESSA CLASSE � RESPONSAVEL POR CHAMAR A CLASSE HBERNATE UTIL, ABRINDO ASSIM UMA SESS�O.
//ESSA CLASSE � CARREGADA JUNTO COM O TOMCAT, ASSIM O SISTEMA FIA MUITO MAIS R�PIDO PARA EXCUTAR
public class ContextListener implements ServletContextListener {

	
	public void contextDestroyed(ServletContextEvent arg0) {
		HibernateUtil.getSessionFactory().close();
	}

	
	public void contextInitialized(ServletContextEvent arg0) {
		HibernateUtil.getSessionFactory().openSession();
	}


	

}
