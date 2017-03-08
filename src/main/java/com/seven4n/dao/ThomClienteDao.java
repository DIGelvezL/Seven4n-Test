package com.seven4n.dao;

import java.util.List;

import com.seven4n.entities.ThomCliente;


public interface ThomClienteDao {

	public List<ThomCliente> findAllClientes();

	public void insertCliente(ThomCliente thomCliente);

	public void updateCliente(ThomCliente thomCliente);

	public void deleteCliente(ThomCliente thomCliente);
}
