package br.com.sousuperseguro.repositoryImpl;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.sousuperseguro.connection.CriarConexao;
import br.com.sousuperseguro.entities.ErrosRetorno;
import br.com.sousuperseguro.repository.ErrosRetornoRepository;

@Repository
public class ErrosRetornoRepositoryImpl implements ErrosRetornoRepository{
	
	private CriarConexao criarConexao;
	private Session session;
	
	public ErrosRetornoRepositoryImpl() {
    	criarConexao = new CriarConexao();
 	}
	
	@Override
	public ErrosRetorno obterErro(String codigoErro) {
		
		this.session = criarConexao.getSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			
			Criteria criteria = this.session.createCriteria(ErrosRetorno.class); 
			criteria.add(Restrictions.eq("numeroErro", codigoErro));
			criteria.setMaxResults(1);
			
			ErrosRetorno retorno = (ErrosRetorno) criteria.uniqueResult();
			
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
