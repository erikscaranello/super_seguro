package br.com.sousuperseguro.repositoryImpl;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.sousuperseguro.connection.CriarConexao;
import br.com.sousuperseguro.entities.recusadas.RecebidoSouSuperSeguroRecusada;
import br.com.sousuperseguro.repository.ArquivosRecusadosRepository;

@Repository
public class ArquivosRecusadosRepositoryImpl implements ArquivosRecusadosRepository {
	
	private CriarConexao criarConexao;
	private Session session;
	
	public ArquivosRecusadosRepositoryImpl() {
    	criarConexao = new CriarConexao();
 	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RecebidoSouSuperSeguroRecusada> obterArquivosRecusadosLimitCinco() {
		
		this.session = criarConexao.getSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			Criteria criteria = this.session.createCriteria(RecebidoSouSuperSeguroRecusada.class);
			criteria.add(Restrictions.eq("recebidoBradesco", false));
			criteria.setMaxResults(5);
			
			
			List<RecebidoSouSuperSeguroRecusada> listaRetorno = criteria.list();
			return listaRetorno;
			
		} catch (HibernateException e) {
			tx.rollback();
			throw e;
		} finally {
			session.close();
		}
		
	}

	@Override
	public RecebidoSouSuperSeguroRecusada obterArquivoRecusado(BigInteger numeroDados) {
		this.session = criarConexao.getSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			Criteria criteria = this.session.createCriteria(RecebidoSouSuperSeguroRecusada.class);
			criteria.add(Restrictions.eq("id", numeroDados));
			criteria.setMaxResults(1);
			
			RecebidoSouSuperSeguroRecusada arquivoRetorno = (RecebidoSouSuperSeguroRecusada) criteria.uniqueResult();
			return arquivoRetorno;
			
		} catch (HibernateException e) {
			tx.rollback();
			throw e;
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RecebidoSouSuperSeguroRecusada> obterArquivosRecusadosBradescoLimitCinco() {
		
		this.session = criarConexao.getSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			Criteria criteria = this.session.createCriteria(RecebidoSouSuperSeguroRecusada.class);
			criteria.add(Restrictions.eq("recebidoBradesco", true));
			criteria.setMaxResults(5);
			
			
			List<RecebidoSouSuperSeguroRecusada> listaRetorno = criteria.list();
			return listaRetorno;
			
		} catch (HibernateException e) {
			tx.rollback();
			throw e;
		} finally {
			session.close();
		}
		
	}
	
}
