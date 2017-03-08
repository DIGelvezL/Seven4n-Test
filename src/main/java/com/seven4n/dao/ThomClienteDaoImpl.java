package com.seven4n.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.seven4n.entities.ThomCliente;


@Stateless
public class ThomClienteDaoImpl implements ThomClienteDao {
	
	@PersistenceContext(unitName = "Seven4NPU")
	EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ThomCliente> findAllClientes() {
		return em.createNamedQuery("ThomCliente.findAll").getResultList();
	}

	@Override
	public void insertCliente(ThomCliente thomCliente) {
		em.persist(thomCliente);
	}

	@Override
	public void updateCliente(ThomCliente thomCliente) {
		em.merge( thomCliente );
	}

	@Override
	public void deleteCliente(ThomCliente thomCliente) {
		thomCliente = em.find(ThomCliente.class, thomCliente.getNmcliente());
		em.remove( thomCliente );
	}

}
