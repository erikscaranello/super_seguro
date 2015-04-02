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
import br.com.sousuperseguro.entities.Proposta;
import br.com.sousuperseguro.entities.RecebidoSouSuperSeguro;
import br.com.sousuperseguro.entities.recusadas.RecebidoSouSuperSeguroCobrancaRecusada;
import br.com.sousuperseguro.entities.recusadas.RecebidoSouSuperSeguroPagamentoMensalidadeRecusada;
import br.com.sousuperseguro.entities.recusadas.RecebidoSouSuperSeguroRecusada;
import br.com.sousuperseguro.enums.Categoria;
import br.com.sousuperseguro.enums.TipoCobranca;
import br.com.sousuperseguro.repository.UploadDeArquivosRepository;

@Repository
public class UploadDeArquivosRepositoryImpl implements UploadDeArquivosRepository{

	private CriarConexao criarConexao;
	private Session session;
	
	public UploadDeArquivosRepositoryImpl() {
    	criarConexao = new CriarConexao();
 	}
	
//	@Override
	public void insertDados(RecebidoSouSuperSeguro infosContratante) {
		this.session = criarConexao.getSession();
    	Transaction tx = null;
    	
    	try{
    	
    		tx = session.beginTransaction();
    		session.saveOrUpdate(infosContratante); 
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
	
//	@Override
	public void insertDados(RecebidoSouSuperSeguroRecusada infosContratante) {
		this.session = criarConexao.getSession();
    	Transaction tx = null;
    	
    	try {
    	
    		tx = session.beginTransaction();
    		session.saveOrUpdate(infosContratante); 
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

//	@Override
	public void insertDadosCobranca(RecebidoSouSuperSeguroCobrancaRecusada retornoEntidade) {
		
		this.session = criarConexao.getSession();
    	Transaction tx = null;
    	
    	try {
    	
    		tx = session.beginTransaction();
    		session.saveOrUpdate(retornoEntidade); 
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

//	@Override
	public void insertDadosCobranca(RecebidoSouSuperSeguroPagamentoMensalidadeRecusada recebidoSouSuperSeguro) {
		
		this.session = criarConexao.getSession();
    	Transaction tx = null;
    	
    	try {
    	
    		tx = session.beginTransaction();
    		session.saveOrUpdate(recebidoSouSuperSeguro); 
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

//	@Override
	public void delete(RecebidoSouSuperSeguroRecusada retornoNovaEntidade) {
		this.session = criarConexao.getSession();
    	Transaction tx = null;
    	
    	try{
    	
    		tx = session.beginTransaction();
    		session.delete(retornoNovaEntidade); 
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

//	@Override
	public RecebidoSouSuperSeguro insertDadosComSelect(RecebidoSouSuperSeguro retorno) {
		this.session = criarConexao.getSession();
    	Transaction tx = null;
    	
    	try{
    	
    		tx = session.beginTransaction();
    		session.save(retorno);
    		
    		Criteria criteria = this.session.createCriteria(RecebidoSouSuperSeguro.class);
			criteria.addOrder(Order.desc("id"));
			criteria.setMaxResults(1);
			RecebidoSouSuperSeguro recebido = (RecebidoSouSuperSeguro) criteria.uniqueResult();
    		
    		tx.commit();
    		return recebido;
    	
    	} catch (HibernateException e) {
    		e.printStackTrace();
    		if (tx!=null) {
    			tx.rollback();
    			throw e;
    		}
    		return null;
    		
    	} finally {
    		session.close(); 
    	}
	}

	@Override
	public RecebidoSouSuperSeguro obterRecebidoPorCpf(String cpf) {
		
		this.session = criarConexao.getSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			Criteria criteria = this.session.createCriteria(RecebidoSouSuperSeguro.class); 
			
			criteria.add(Restrictions.eq("cpf", cpf));
			criteria.addOrder(Order.desc("id"));
			criteria.setMaxResults(1);
			RecebidoSouSuperSeguro retorno = (RecebidoSouSuperSeguro) criteria.uniqueResult();
			
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
	public void delete(RecebidoSouSuperSeguro recebido) {
		this.session = criarConexao.getSession();
    	Transaction tx = null;
    	
    	try{
    		tx = session.beginTransaction();
    		
    		Criteria criteria = this.session.createCriteria(Proposta.class); 
			
			criteria.add(Restrictions.eq("idRecebidoSouSuperSeguro", recebido));
			criteria.addOrder(Order.desc("id"));
			criteria.setMaxResults(1);
			Proposta retornoProposta = (Proposta) criteria.uniqueResult();
    		
			if(retornoProposta != null) {
				session.delete(retornoProposta);
			} else {
				session.delete(recebido);
			}
			 
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
	public List<RecebidoSouSuperSeguro> obterDadosSemProposta() {
		this.session = criarConexao.getSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			Criteria criteria = this.session.createCriteria(RecebidoSouSuperSeguro.class); 
			
			
			criteria.add(Restrictions.isNull("nroProposta"));
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

	@SuppressWarnings("unchecked")
	@Override
	public List<RecebidoSouSuperSeguro> obterDadosNaoEnviadoCobrancaTitular() {
		this.session = criarConexao.getSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			Criteria criteria = this.session.createCriteria(RecebidoSouSuperSeguro.class); 
			
			
//			criteria.add(Restrictions.eq("envioEmail", false));
			criteria.add(Restrictions.eq("cCategoria", Categoria.TITULAR));
			criteria.add(Restrictions.eq("emailEnviado", false));
			
			criteria.createAlias("recebidoSouSuperSeguroPagamentoMensalidade", "recebido");
			
			criteria.add(Restrictions.eq("recebido.tpCobr", TipoCobranca.BOLETOBANCARIO_BOLETOBANCARIO));
			criteria.setMaxResults(30);
			
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
	public List<RecebidoSouSuperSeguro> obterListaRecebidosPorCpf(String cpf) {
		this.session = criarConexao.getSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			Criteria criteria = this.session.createCriteria(RecebidoSouSuperSeguro.class); 
			
			criteria.add(Restrictions.eq("cpf", cpf));
			
			@SuppressWarnings("unchecked")
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
		
}
