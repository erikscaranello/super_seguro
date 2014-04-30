package br.com.sousuperseguro.repositoryImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.sousuperseguro.connection.CriarConexao;
import br.com.sousuperseguro.entities.InfosPessoais;
import br.com.sousuperseguro.entities.Role;
import br.com.sousuperseguro.entities.Users;
import br.com.sousuperseguro.repository.UserRepository;


@Repository
public class UserRepositoryImpl implements UserRepository{
	
	private CriarConexao criarConexao;
	private Session session;
	
	public UserRepositoryImpl() {
    	criarConexao = new CriarConexao();
 	}
	

	@Override
	public Users verificarEmail(String email) {
		this.session = criarConexao.getSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			Criteria criteria = this.session.createCriteria(InfosPessoais.class);
			criteria.add(Restrictions.eq("email", email));
			criteria.setMaxResults(1);
			InfosPessoais infosPessoais = (InfosPessoais) criteria.uniqueResult();
			
			criteria = this.session.createCriteria(Users.class);
			criteria.add(Restrictions.eq("infosPessoais", infosPessoais));
			criteria.setMaxResults(1);
			Users user = (Users) criteria.uniqueResult();
			return user;
		} catch (HibernateException e) {
			tx.rollback();
			throw e;
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Users> obterListaDeUsuarios() {
		this.session = criarConexao.getSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			Criteria criteria = this.session.createCriteria(Users.class); 
			List<Users> retorno = (List<Users>) criteria.list();
			
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
	public Users verificarUsername(String username) {
		
		this.session = criarConexao.getSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			Criteria criteria = this.session.createCriteria(Users.class); 
			
			criteria.add(Restrictions.eq("username", username));
			criteria.setMaxResults(1);			
			Users retorno = (Users) criteria.uniqueResult();
			
			tx.commit();
			return retorno;
			
		} catch (HibernateException e) {
			tx.rollback();
			throw e;
		} finally {
			session.close();
		}
		
	}

	@SuppressWarnings("finally")
	@Override
	public boolean inserirUser(Users user) {
		boolean retorno = false;
		this.session = criarConexao.getSession();
    	Transaction tx = null;
    	
    	try{
    	
    		tx = session.beginTransaction();
    		session.saveOrUpdate(user); 
    		tx.commit();
    		retorno = true;
    	} catch (HibernateException e) {
    		e.printStackTrace();
    		if (tx!=null) {
    			tx.rollback();
    			retorno = false;
    		}
    		
    	} finally {
    		session.close(); 
    		return retorno;
    	}	
	}
	
	@SuppressWarnings("finally")
	@Override
	public boolean deleteUser(Users user) {
		boolean retorno = false;
		this.session = criarConexao.getSession();
    	Transaction tx = null;
    	
    	try{
    	
    		tx = session.beginTransaction();
    		session.delete(user); 
    		tx.commit();
    		retorno = true;
    	} catch (HibernateException e) {
    		
    		if (tx!=null) {
    			tx.rollback();
    			retorno = false;
    		}
    		
    	} finally {
    		session.close(); 
    		return retorno;
    	}
	}


	@Override
	public Role selecionarRolePorAutoridade(String authority) {
		
		this.session = criarConexao.getSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			Criteria criteria = this.session.createCriteria(Role.class); 
			
			criteria.add(Restrictions.eq("authority", authority));
			criteria.setMaxResults(1);			
			Role retorno = (Role) criteria.uniqueResult();
			
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
	public void updateUser(Users user) {

		this.session = criarConexao.getSession();
    	Transaction tx = null;
    	
    	try{
     		tx = session.beginTransaction();
    		session.saveOrUpdate(user); 
    		tx.commit();
    	} catch (HibernateException e) {
    		e.printStackTrace();
    		if (tx!=null) {
    			tx.rollback();
    		}
    		
    	} finally {
    		session.close(); 
    	}	
	}


	@Override
	public Users obterUserporEmail(String email) {
		this.session = criarConexao.getSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			Criteria criteria = this.session.createCriteria(Users.class, "users"); 
			criteria.createAlias("users.infosPessoais", "infospessoais");
			
			criteria.add(Restrictions.eq("infospessoais.email", email));
			criteria.setMaxResults(1);			
			Users retorno = (Users) criteria.uniqueResult();
			
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
