package br.com.sousuperseguro.repositoryImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.sousuperseguro.connection.CriarConexao;
import br.com.sousuperseguro.entities.ArquivosEnvio;
import br.com.sousuperseguro.entities.RecebidoSouSuperSeguro;
import br.com.sousuperseguro.repository.ArquivosEnvioRepository;

@Repository
public class ArquivosEnvioRepositoryImpl implements ArquivosEnvioRepository {

	
	private CriarConexao criarConexao;
	private Session session;
	
	public ArquivosEnvioRepositoryImpl() {
    	criarConexao = new CriarConexao();
 	}

	
	
	@Override
	public ArquivosEnvio obterUltimoArquivoDeEnvio() {
	
		this.session = criarConexao.getSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			Criteria criteria = this.session.createCriteria(ArquivosEnvio.class); 
			criteria.addOrder(Order.desc("id"));
			criteria.setMaxResults(1);
			ArquivosEnvio retorno = (ArquivosEnvio) criteria.uniqueResult();
			
			tx.commit();
			
			return retorno;
		} catch (HibernateException e) {
			tx.rollback();
			throw e;
		} finally {
			session.close();
		}
		
	}



	@SuppressWarnings("unchecked")
	@Override
	public List<RecebidoSouSuperSeguro> selecionarRecebidosSuperSeguro() {

		this.session = criarConexao.getSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			Criteria criteria = this.session.createCriteria(RecebidoSouSuperSeguro.class); 
			
			criteria.add(Restrictions.eq("enviado", false));
			List<RecebidoSouSuperSeguro> retorno = criteria.list();
			
			tx.commit();	
			return retorno;
		
		} catch (HibernateException e) {
			tx.rollback();
			throw e;
		} finally {
			session.close();
		}
		
		
	}

	
	
	@Override
	public void insertNovoArquivo(ArquivosEnvio arquivoEnvioInsert) {
		this.session = criarConexao.getSession();
    	Transaction tx = null;
    	
    	try{
    	
    		tx = session.beginTransaction();
    		session.saveOrUpdate(arquivoEnvioInsert); 
    		tx.commit();
    	
    	} catch (HibernateException e) {
    		e.printStackTrace();
    		if (tx!=null) {
    			tx.rollback();
    			throw e;
    		}
    		
    	} finally {
    		session.close(); 
    	}
		
	}



	@Override
	public void insertRecebidoEnviado(RecebidoSouSuperSeguro recebidoEnviado) {
		this.session = criarConexao.getSession();
    	Transaction tx = null;
    	
    	try{
    		tx = session.beginTransaction();
    		session.saveOrUpdate(recebidoEnviado); 
    		tx.commit();
    	
    	} catch (HibernateException e) {
    		e.printStackTrace();
    		if (tx!=null) {
    			tx.rollback();
    			throw e;
    		}
    		
    	} finally {
    		session.close(); 
    	}	
	}



	@SuppressWarnings("unchecked")
	@Override
	public List<ArquivosEnvio> obterListaNaoRecebidosErro() {
		
		this.session = criarConexao.getSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			Criteria criteria = this.session.createCriteria(ArquivosEnvio.class); 
			criteria.add(Restrictions.eq("recebidoErro", false));
			
			List<ArquivosEnvio> retorno = criteria.list();
			
			tx.commit();
			
			return retorno;
		} catch (HibernateException e) {
			tx.rollback();
			throw e;
		} finally {
			session.close();
		}
		
	}



	@Override
	public void updateArquivosParaLido(ArquivosEnvio arquivoEnvioInsert) {
		this.session = criarConexao.getSession();
    	Transaction tx = null;
    	
    	try{
    		tx = session.beginTransaction();
    		session.saveOrUpdate(arquivoEnvioInsert); 
    		tx.commit();
    	
    	} catch (HibernateException e) {
    		e.printStackTrace();
    		if (tx!=null) {
    			tx.rollback();
    			throw e;
    		}
    		
    	} finally {
    		session.close(); 
    	}
	}
}
