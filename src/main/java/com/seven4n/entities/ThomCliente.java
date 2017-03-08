package com.seven4n.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the thom_cliente database table.
 * 
 */
@Entity
@Table(name="thom_cliente")
@NamedQuery(name="ThomCliente.findAll", query="SELECT t FROM ThomCliente t")
public class ThomCliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer nmcliente;

	private String cdcliente;

	private String cdtelefono;

	private String cdusuario;

	private String dscontacto;

	private String dsdireccion;

	private String dslogo;

	private String dsmail;

	private String dsnombre;

	private Timestamp febaja;

	private Timestamp feregistro;

	private String snactivo;

	public ThomCliente() {
	}

	public Integer getNmcliente() {
		return this.nmcliente;
	}

	public void setNmcliente(Integer nmcliente) {
		this.nmcliente = nmcliente;
	}

	public String getCdcliente() {
		return this.cdcliente;
	}

	public void setCdcliente(String cdcliente) {
		this.cdcliente = cdcliente;
	}

	public String getCdtelefono() {
		return this.cdtelefono;
	}

	public void setCdtelefono(String cdtelefono) {
		this.cdtelefono = cdtelefono;
	}

	public String getCdusuario() {
		return this.cdusuario;
	}

	public void setCdusuario(String cdusuario) {
		this.cdusuario = cdusuario;
	}

	public String getDscontacto() {
		return this.dscontacto;
	}

	public void setDscontacto(String dscontacto) {
		this.dscontacto = dscontacto;
	}

	public String getDsdireccion() {
		return this.dsdireccion;
	}

	public void setDsdireccion(String dsdireccion) {
		this.dsdireccion = dsdireccion;
	}

	public String getDslogo() {
		return this.dslogo;
	}

	public void setDslogo(String dslogo) {
		this.dslogo = dslogo;
	}

	public String getDsmail() {
		return this.dsmail;
	}

	public void setDsmail(String dsmail) {
		this.dsmail = dsmail;
	}

	public String getDsnombre() {
		return this.dsnombre;
	}

	public void setDsnombre(String dsnombre) {
		this.dsnombre = dsnombre;
	}

	public Timestamp getFebaja() {
		return this.febaja;
	}

	public void setFebaja(Timestamp febaja) {
		this.febaja = febaja;
	}

	public Timestamp getFeregistro() {
		return this.feregistro;
	}

	public void setFeregistro(Timestamp feregistro) {
		this.feregistro = feregistro;
	}

	public String getSnactivo() {
		return this.snactivo;
	}

	public void setSnactivo(String snactivo) {
		this.snactivo = snactivo;
	}

}