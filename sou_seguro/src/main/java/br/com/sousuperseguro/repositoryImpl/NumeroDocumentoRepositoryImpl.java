package br.com.sousuperseguro.repositoryImpl;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.sousuperseguro.connection.CriarConexao;
import br.com.sousuperseguro.entities.NumeroDocumento;
import br.com.sousuperseguro.entities.RecebidoSouSuperSeguro;
import br.com.sousuperseguro.repository.NumeroDocumentoRepository;


@Repository
public class NumeroDocumentoRepositoryImpl implements NumeroDocumentoRepository{

	private CriarConexao criarConexao;
	private Session session;
	
	public NumeroDocumentoRepositoryImpl() {
    	criarConexao = new CriarConexao();
 	}
	
	
	@Override
	public NumeroDocumento obterUltimoNumeroDocumento() {
		this.session = criarConexao.getSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			Criteria criteria = this.session.createCriteria(NumeroDocumento.class); 
			
			criteria.addOrder(Order.desc("id"));
			criteria.setMaxResults(1);
			
			NumeroDocumento retorno = (NumeroDocumento) criteria.uniqueResult();
			
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
	public void insertNumeroDocumento(NumeroDocumento numeroDocumento) {
		
		this.session = criarConexao.getSession();
    	Transaction tx = null;
    	
    	try{
    	
    		tx = session.beginTransaction();
    		session.save(numeroDocumento); 
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
	public NumeroDocumento verificarEnviadoEmail(RecebidoSouSuperSeguro dadosRecebidoEmailNaoEnviado) {
		this.session = criarConexao.getSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			Criteria criteria = this.session.createCriteria(NumeroDocumento.class); 
			
			criteria.add(Restrictions.eq("idRecebidoSouSuperSeguro", dadosRecebidoEmailNaoEnviado));
			criteria.setMaxResults(1);
			
			NumeroDocumento retorno = (NumeroDocumento) criteria.uniqueResult();
			
			tx.commit();	
			return retorno;
		
		} catch (HibernateException e) {
			tx.rollback();
			throw e;
		} finally {
			session.close();
		}
	}

}
