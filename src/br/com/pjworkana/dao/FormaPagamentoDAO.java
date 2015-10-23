package br.com.pjworkana.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.pjworkana.domain.FormaPagamento;
import br.com.pjworkana.util.HibernateUtil;

public class FormaPagamentoDAO {
	public void salvar(FormaPagamento formaPagamento) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.save(formaPagamento);
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

	@SuppressWarnings("unchecked")
	public List<FormaPagamento> listar() {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<FormaPagamento> formasPagamento = null;

		try {
			Query consulta = sessao.getNamedQuery("FormaPagamento.listar");
			formasPagamento = consulta.list();
		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			sessao.close();
		}
		return formasPagamento;
	}

	public FormaPagamento buscarPorCodigo(Integer codigo) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		FormaPagamento formaPagamento = null;

		try {
			Query consulta = sessao
					.getNamedQuery("FormaPagamento.buscarPorCodigo");
			consulta.setInteger("codigo", codigo);
			formaPagamento = (FormaPagamento) consulta.uniqueResult();
		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			sessao.close();
		}
		return formaPagamento;
	}

	public void excluir(FormaPagamento formaPagamento) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.delete(formaPagamento);
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

	public void editar(FormaPagamento formaPagamento) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.update(formaPagamento);
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

}
