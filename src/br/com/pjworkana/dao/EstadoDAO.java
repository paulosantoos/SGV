package br.com.pjworkana.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.pjworkana.domain.Estado;
import br.com.pjworkana.util.HibernateUtil;

public class EstadoDAO {
	@SuppressWarnings("unchecked")
	public List<Estado> listar() {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Estado> estados = null;

		try {
			Query consulta = sessao.getNamedQuery("Estado.listar");
			estados = consulta.list();
		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			sessao.close();
		}
		return estados;
	}

	public Estado buscarPorCodigo(Integer codigo) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Estado estado = null;

		try {
			Query consulta = sessao.getNamedQuery("Estado.buscarPorCodigo");
			consulta.setInteger("codigo", codigo);
			estado = (Estado) consulta.uniqueResult();
		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			sessao.close();
		}
		return estado;
	}

}
