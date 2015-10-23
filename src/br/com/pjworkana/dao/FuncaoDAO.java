package br.com.pjworkana.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.pjworkana.domain.Funcao;
import br.com.pjworkana.util.HibernateUtil;

public class FuncaoDAO {
	@SuppressWarnings("unchecked")
	public List<Funcao> listar() {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Funcao> funcoes = null;

		try {
			Query consulta = sessao.getNamedQuery("Funcao.listar");
			funcoes = consulta.list();
		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			sessao.close();
		}
		return funcoes;
	}

	public void salvar(Funcao funcao) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.save(funcao);
			transacao.commit();
		} catch (RuntimeException ex) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw ex;
		} finally {
			sessao.close();
		}
	}

	public Funcao buscarPorCodigo(Integer codigo) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Funcao funcao = null;

		try {
			Query consulta = sessao.getNamedQuery("Funcao.buscarPorCodigo");
			consulta.setInteger("codigo", codigo);
			funcao = (Funcao) consulta.uniqueResult();
		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			sessao.close();
		}
		return funcao;
	}

}
