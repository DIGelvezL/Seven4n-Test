package com.seven4n.beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

import com.seven4n.service.ThomClienteService;
import com.seven4n.vo.ThomClienteVO;

@ManagedBean
@RequestScoped
public class ClienteBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EJB
	private ThomClienteService clienteService;
	
	private ThomClienteVO clientesVO;
	private List<ThomClienteVO> clientesVOList;

	public ClienteBean() {
	}

	@PostConstruct
	public void inicializar() {
		try {
			clientesVOList = clienteService.listarClientes();
		} catch (Exception ex) {
			ex.printStackTrace();
        }
	}
	
	public ThomClienteVO getClientesVO() {
		return clientesVO;
	}

	public void setClientesVO(ThomClienteVO clientesVO) {
		this.clientesVO = clientesVO;
	}

	public List<ThomClienteVO> getClientesVOList() {
		return clientesVOList;
	}

	public void setClientesVOList(List<ThomClienteVO> clientesVO) {
		this.clientesVOList = clientesVO;
	}
	
	public void editClientes(RowEditEvent event) {
		try {
			ThomClienteVO clienteVO = (ThomClienteVO) event.getObject();
			if(clienteVO.getSnactivo().equals("1") || clienteVO.getSnactivo().equals("0")){
				clienteService.modificarCliente(clienteVO);
				FacesContext.getCurrentInstance().addMessage(null,
	                    new FacesMessage("Se modifico el cliente " + getClientesVO().getDsnombre() + " con cedula " + getClientesVO().getCdcliente()));
			}else{
				warn();
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
        }
	}
	
	public void warn() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "El estado solo puede ser 0 para inactivo o 1 para activo !!"));
    }
	
	public void eliminarCliente() {
		try {
			clientesVOList.remove(clientesVO);
			clienteService.eliminarCliente(clientesVO);
			FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Se eliminó el cliente " + getClientesVO().getDsnombre() + " con cedula " + getClientesVO().getCdcliente()));
			clientesVO = null;
		} catch (Exception ex) {
			ex.printStackTrace();
        }
	}
}
