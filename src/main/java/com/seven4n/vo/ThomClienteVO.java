package com.seven4n.vo;

import java.io.Serializable;
import java.util.Date;

import org.primefaces.model.StreamedContent;

public class ThomClienteVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer nmcliente;

	private String cdcliente;

	private String cdtelefono;

	private String cdusuario;
	
	private String nombreCarpeta;

	private String dscontacto;

	private String dsdireccion;

	private StreamedContent dslogo;
	
	private String urlImagen;

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
	
	public String getNombreCarpeta() {
		return nombreCarpeta;
	}

	public void setNombreCarpeta(String nombreCarpeta) {
		this.nombreCarpeta = nombreCarpeta;
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

	public StreamedContent getDslogo() {
		return dslogo;
	}

	public void setDslogo(StreamedContent dslogo) {
		this.dslogo = dslogo;
	}
	
	public String getUrlImagen() {
		return urlImagen;
	}

	public void setUrlImagen(String urlImagen) {
		this.urlImagen = urlImagen;
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
	
	
}
