package com.seven4n.beans;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import com.seven4n.service.ThomClienteService;

@ManagedBean
@RequestScoped
public class CargarArchivosBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EJB
	private ThomClienteService clienteService;
	
	private UploadedFile file;
	
	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public void upLoad(FileUploadEvent event){
		setFile(event.getFile());
    	clienteService.guardarArchivosMultiple(getFile(), event.getFile().getFileName());
	}
}
