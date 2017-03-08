package com.seven4n.beans;

import java.io.Serializable;
import java.util.Date;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import com.seven4n.service.ThomClienteService;
import com.seven4n.vo.ThomClienteVO;

@ManagedBean
@ViewScoped
public class CrearClientesBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EJB
	private ThomClienteService clienteService;
	
	private ThomClienteVO clientesVO;
	
	private Integer nmcliente;

	private String cdcliente;

	private String cdtelefono;

	private String cdusuario;

	private String dscontacto;

	private String dsdireccion;
	
	private String dslogo;
	
	private UploadedFile file;
	
	private String archivo;
	
	private String imagenLogo;

	private String dsmail;

	private String dsnombre;

	private Date febaja;

	private Date feregistro;

	private String snactivo;

	public Integer getNmcliente() {
		return nmcliente;
	}

	public void setNmcliente(Integer nmcliente) {
		this.nmcliente = nmcliente;
	}

	public String getCdcliente() {
		return cdcliente;
	}

	public void setCdcliente(String cdcliente) {
		this.cdcliente = cdcliente;
	}

	public String getCdtelefono() {
		return cdtelefono;
	}

	public void setCdtelefono(String cdtelefono) {
		this.cdtelefono = cdtelefono;
	}

	public String getCdusuario() {
		return cdusuario;
	}

	public void setCdusuario(String cdusuario) {
		this.cdusuario = cdusuario;
	}

	public String getDscontacto() {
		return dscontacto;
	}

	public void setDscontacto(String dscontacto) {
		this.dscontacto = dscontacto;
	}

	public String getDsdireccion() {
		return dsdireccion;
	}

	public void setDsdireccion(String dsdireccion) {
		this.dsdireccion = dsdireccion;
	}

	public String getDslogo() {
		return dslogo;
	}
	
	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public void setDslogo(String dslogo) {
		this.dslogo = dslogo;
	}
	
	public String getArchivo() {
		return archivo;
	}

	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}

	public String getImagenLogo() {
		return imagenLogo;
	}

	public void setImagenLogo(String imagenLogo) {
		this.imagenLogo = imagenLogo;
	}

	public String getDsmail() {
		return dsmail;
	}

	public void setDsmail(String dsmail) {
		this.dsmail = dsmail;
	}

	public String getDsnombre() {
		return dsnombre;
	}

	public void setDsnombre(String dsnombre) {
		this.dsnombre = dsnombre;
	}

	public Date getFebaja() {
		return febaja;
	}

	public void setFebaja(Date febaja) {
		this.febaja = febaja;
	}

	public Date getFeregistro() {
		return feregistro;
	}

	public void setFeregistro(Date feregistro) {
		this.feregistro = feregistro;
	}

	public String getSnactivo() {
		return snactivo;
	}

	public void setSnactivo(String snactivo) {
		this.snactivo = snactivo;
	}
	
	public void save() {
        try {
        	FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Se guardo el cliente " + getDsnombre() + " con cedula " + getCdcliente()));
        	
        	getClienteVO();
			clienteService.registrarCliente(clientesVO, getFile(), getArchivo());
		} catch (Exception ex) {
			ex.printStackTrace();
        }
    }
	
	public void upLoad(FileUploadEvent event){
		setFile(event.getFile());
    	setArchivo(event.getFile().getFileName());
    	setImagenLogo(clienteService.guardarLogoEnFicheroTemp(getFile(), getArchivo()));
	}
	
	public void getClienteVO(){
		clientesVO = new ThomClienteVO();
		
		clientesVO.setCdcliente(getCdcliente());
		clientesVO.setCdtelefono(getCdtelefono());
		clientesVO.setCdusuario(getCdusuario());
		clientesVO.setDscontacto(getDscontacto());
		clientesVO.setDsdireccion(getDsdireccion());
		clientesVO.setDsmail(getDsmail());
		clientesVO.setDsnombre(getDsnombre());
		clientesVO.setSnactivo(getSnactivo());
	}
	
}
