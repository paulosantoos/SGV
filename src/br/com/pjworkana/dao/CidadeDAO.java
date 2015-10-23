package br.com.pjworkana.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.pjworkana.domain.Cidade;
import br.com.pjworkana.util.HibernateUtil;

public class CidadeDAO {
	
	//METODO PARA SALVAR
	public void salvar(Cidade cidade) {
		
		//ABRE A SESSÃO DO HIBERNATE
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			//INICIA A TRANSAÇÃO
			transacao = sessao.beginTransaction();
			//SALVA O OBJETO QUE SERÁ PASSADO PELO BEAN
			sessao.save(cidade);
			//CONFIRMA A TRANSAÇÃO
			transacao.commit();
		} catch (RuntimeException ex) {
			
			//SE DER FALHA, A TRANSAÇÃO É DESFEITA
			if (transacao != null) {
				transacao.rollback();
			}
			throw ex;
		} finally {
			//QUANDO TUDO TERMINAR ELE FECHA A SESSAO
			sessao.close();
		}
	}

	@SuppressWarnings("unchecked")
	//METODO LISTAR
	public List<Cidade> listar() {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Cidade> cidades = null;

		try {
			//AQUI O HIBERNATE PEGA A NAMEDQUERY QUE ESTÁ DECLARADO NO DOMAIN DO OBEJTO
			Query consulta = sessao.getNamedQuery("Cidade.listar");
			//MANDA LISTAR
			cidades = consulta.list();
		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			sessao.close();
		}
		//RETORNA A LISTA
		return cidades;
	}

	//METODO PARA BUSCAR O CODIGO
	public Cidade buscarPorCodigo(Integer codigo) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Cidade cidade = null;

		try {
			Query consulta = sessao.getNamedQuery("Cidade.buscarPorCodigo");
			//AQUI É PASSADO O PARAMETRO PARA A NAMEDQUERY
			consulta.setInteger("codigo", codigo);
			//AQUI BUSCA UM UNICO RESULTADO
			cidade = (Cidade) consulta.uniqueResult();
		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			sessao.close();
		}
		return cidade;
	}

	
	//METODO PARA EXCLUIR
	public void excluir(Cidade cidade) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.delete(cidade);
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

	
	//METODO PARA EDITAR
	public void editar(Cidade cidade) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.update(cidade);
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
	public List<Cidade> buscarCidadesPorCodigoEstado(Integer codigo) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Cidade> cidades = null;

		try {
			Query consulta = sessao
					.getNamedQuery("Cidade.buscarCidadesPorCodigoEstado");
			consulta.setInteger("codigo", codigo);
			cidades = consulta.list();
		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			sessao.close();
		}
		return cidades;
	}

}
