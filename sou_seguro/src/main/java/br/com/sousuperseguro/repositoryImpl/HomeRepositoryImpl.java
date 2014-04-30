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
import br.com.sousuperseguro.entities.Users;
import br.com.sousuperseguro.entities.recusadas.RecebidoSouSuperSeguroRecusada;
import br.com.sousuperseguro.repository.HomeRepository;

@Repository
public class HomeRepositoryImpl implements HomeRepository {
	
	private CriarConexao criarConexao;
	private Session session;
	
	public HomeRepositoryImpl() {
    	criarConexao = new CriarConexao();
 	}
	
	
	@Override
	public RecebidoSouSuperSeguroRecusada selecionarRecebidoRecusadoPorId(BigInteger id) {
	
		this.session = criarConexao.getSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			Criteria criteria = this.session.createCriteria(RecebidoSouSuperSeguroRecusada.class); 
			criteria.add(Restrictions.eq("id", id));
			RecebidoSouSuperSeguroRecusada retorno = (RecebidoSouSuperSeguroRecusada) criteria.uniqueResult();
			
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
