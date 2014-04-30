package br.com.sousuperseguro.connection;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class CriarConexao {
	private static SessionFactory factory;
	private static ServiceRegistry serviceRegistry;
	
	public CriarConexao() {
		Configuration configuration = new Configuration();
		configuration.configure("hibernate.cfg.xml");
		
		serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry(); 
		factory = configuration.buildSessionFactory(serviceRegistry);
	}

	public Session getSession() {
		return factory.openSession();
	}
}
