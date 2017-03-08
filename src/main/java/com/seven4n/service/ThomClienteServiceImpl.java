package com.seven4n.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.UploadedFile;

import com.seven4n.dao.ThomClienteDao;
import com.seven4n.entities.ThomCliente;
import com.seven4n.vo.ThomClienteVO;


@Stateless
public class ThomClienteServiceImpl implements ThomClienteService, ThomClienteServiceRemote {
	
	@Resource
	private SessionContext contexto;
	
	@EJB
	private ThomClienteDao clienteDao;
	
	@Override
	public List<ThomClienteVO> listarClientes() {
		List<ThomCliente> clientes;
		List<ThomClienteVO> clientesVO;
		
		try{
			clientes = clienteDao.findAllClientes();
			if (clientes != null) {
				
				clientesVO = new ArrayList<ThomClienteVO>();
				for (ThomCliente item : clientes) {
					ThomClienteVO vo = new ThomClienteVO();
					vo.setNmcliente(item.getNmcliente());
					vo.setCdcliente(item.getCdcliente());
					vo.setCdtelefono(item.getCdtelefono());
					vo.setCdusuario(item.getCdusuario());
					vo.setNombreCarpeta(item.getCdusuario());
					vo.setDscontacto(item.getDscontacto());
					vo.setDsdireccion(item.getDsdireccion());
					
					if(item.getDslogo() != null)
						vo.setDslogo(new DefaultStreamedContent(new FileInputStream(item.getDslogo()), "image/png"));
				    
					vo.setUrlImagen(item.getDslogo());
					vo.setDsmail(item.getDsmail());
					vo.setDsnombre(item.getDsnombre());
					if(item.getFebaja() != null)
						vo.setFebaja(item.getFebaja());
					if(item.getFeregistro() != null)
						vo.setFeregistro(item.getFeregistro());
					vo.setSnactivo(item.getSnactivo());
	
					clientesVO.add(vo);
				}
				
				return clientesVO;
			}
			else
				return clientesVO = null;
			}catch(Throwable t){
				contexto.setRollbackOnly();
				return null;
			}	
	}

	@Override
	public void registrarCliente(ThomClienteVO thomClienteVO, UploadedFile file, String nombreArchivo) {
		String ubicacionArchivo = null;
		ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
		
		String path = servletContext.getRealPath("") + File.separatorChar + "resources" + File.separatorChar + "images"
				+ File.separatorChar + "logos" + File.separatorChar + nombreArchivo;
		
		String archivoLocal = "D:\\logos" + File.separatorChar + thomClienteVO.getCdusuario();
		
		File borrarLogo;
		File crearCarpeta;
		
		try{
			borrarLogo = new File(path);
			if(borrarLogo.exists())
				borrarLogo.delete();
			
			crearCarpeta = new File("D:\\logos");
			if(!crearCarpeta.exists())
				crearCarpeta.mkdirs();
			
			crearCarpeta = new File(archivoLocal);
			if(!crearCarpeta.exists())
				crearCarpeta.mkdirs();
			
			FileInputStream in = (FileInputStream) file.getInputstream(); 
			FileOutputStream out = new FileOutputStream(archivoLocal + File.separatorChar + nombreArchivo);
			byte[] buffer = new byte [(int) file.getSize()];
			int c = 0;
			while((c = in.read(buffer)) != -1 ){
				out.write(buffer, 0, c);
			}
			in.close();
			out.flush();
			out.close();
			ubicacionArchivo = archivoLocal + File.separatorChar + nombreArchivo;
			
			ThomCliente clientes = new ThomCliente();
			
			clientes.setCdcliente(thomClienteVO.getCdcliente());
			clientes.setCdtelefono(thomClienteVO.getCdtelefono());
			clientes.setCdusuario(thomClienteVO.getCdusuario());
			clientes.setDscontacto(thomClienteVO.getDscontacto());
			clientes.setDsdireccion(thomClienteVO.getDsdireccion());
			clientes.setDslogo(ubicacionArchivo);
			clientes.setDsmail(thomClienteVO.getDsmail());
			clientes.setDsnombre(thomClienteVO.getDsnombre());
			clientes.setSnactivo(thomClienteVO.getSnactivo());
			
			if(clientes.getSnactivo().equals("0")){
				Calendar calendar = Calendar.getInstance();
				Date now = calendar.getTime();
				Timestamp fBaja = new Timestamp(now.getTime());
				clientes.setFebaja(fBaja);
			}
			
			if(clientes.getSnactivo().equals("1")){
				Calendar calendar = Calendar.getInstance();
				Date now = calendar.getTime();
				Timestamp fRegistro = new Timestamp(now.getTime());
				clientes.setFeregistro(fRegistro);
			}
						
			clienteDao.insertCliente(clientes);
		}catch(Throwable t){
			contexto.setRollbackOnly();
		}	
	}
	
	public String guardarLogoEnFicheroTemp(UploadedFile file, String nombreArchivo){
		String ubicacionArchivo = null;
		ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
		
		String path = servletContext.getRealPath("") + File.separatorChar + "resources" + File.separatorChar + "images"
				+ File.separatorChar + "logos" + File.separatorChar + nombreArchivo;
		
		try {
			FileInputStream in = (FileInputStream) file.getInputstream(); 
			FileOutputStream out = new FileOutputStream(path);
			byte[] buffer = new byte [(int) file.getSize()];
			int c = 0;
			while((c = in.read(buffer)) != -1 ){
				out.write(buffer, 0, c);
			}
			in.close();
			out.flush();
			out.close();
			ubicacionArchivo = "./resources/images/logos/" + nombreArchivo;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ubicacionArchivo;
	}
	
	public void guardarArchivosMultiple(UploadedFile file, String nombreArchivo){
		ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
		
		String path = servletContext.getRealPath("") + File.separatorChar + "resources" + File.separatorChar + "images"
				+ File.separatorChar + "logos" + File.separatorChar + nombreArchivo;
		
		try {
			FileInputStream in = (FileInputStream) file.getInputstream(); 
			FileOutputStream out = new FileOutputStream(path);
			byte[] buffer = new byte [(int) file.getSize()];
			int c = 0;
			while((c = in.read(buffer)) != -1 ){
				out.write(buffer, 0, c);
			}
			in.close();
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void modificarCliente(ThomClienteVO thomClienteVO) {
		try{
			String archivoLocalAntiguo = "D:\\logos" + File.separatorChar + thomClienteVO.getNombreCarpeta();
			String archivoLocalNuevo = "D:\\logos" + File.separatorChar + thomClienteVO.getCdusuario();

			File carpetaAntigua;
			File carpetaNueva;
			
			carpetaAntigua = new File(archivoLocalAntiguo);
			carpetaNueva = new File(archivoLocalNuevo);
			if(carpetaAntigua.exists())
				carpetaAntigua.renameTo(carpetaNueva);
			
			ThomCliente clientes = new ThomCliente();
			
			clientes.setNmcliente(thomClienteVO.getNmcliente());
			clientes.setCdcliente(thomClienteVO.getCdcliente());
			clientes.setCdtelefono(thomClienteVO.getCdtelefono());
			clientes.setCdusuario(thomClienteVO.getCdusuario());
			clientes.setDscontacto(thomClienteVO.getDscontacto());
			clientes.setDsdireccion(thomClienteVO.getDsdireccion());
			clientes.setDslogo(thomClienteVO.getUrlImagen());
			clientes.setDsmail(thomClienteVO.getDsmail());
			clientes.setDsnombre(thomClienteVO.getDsnombre());
			clientes.setSnactivo(thomClienteVO.getSnactivo());
			if(clientes.getSnactivo().equals("0") && thomClienteVO.getFebaja() == null){
				Calendar calendar = Calendar.getInstance();
				Date now = calendar.getTime();
				Timestamp fBaja = new Timestamp(now.getTime());
				clientes.setFebaja(fBaja);
			}else if(clientes.getSnactivo().equals("0") && thomClienteVO.getFebaja() != null){
				Date now = thomClienteVO.getFebaja();
				Timestamp fBaja = new Timestamp(now.getTime());
				clientes.setFebaja(fBaja);
			}
			if(clientes.getSnactivo().equals("1") && thomClienteVO.getFeregistro() == null){
				Calendar calendar = Calendar.getInstance();
				Date now = calendar.getTime();
				Timestamp fRegistro = new Timestamp(now.getTime());
				clientes.setFeregistro(fRegistro);
			}else if(clientes.getSnactivo().equals("1") && thomClienteVO.getFeregistro() != null){
				Date now = thomClienteVO.getFeregistro();
				Timestamp fRegistro = new Timestamp(now.getTime());
				clientes.setFeregistro(fRegistro);
			}
			
			clienteDao.updateCliente(clientes);
		}catch(Throwable t){
			contexto.setRollbackOnly();
		}	
	}

	@Override
	public void eliminarCliente(ThomClienteVO thomClienteVO) {
		try{
			String archivoLocal = "D:\\logos" + File.separatorChar + thomClienteVO.getCdusuario();

			File borrarCarpeta;
			
			borrarCarpeta = new File(archivoLocal);
			if(borrarCarpeta.exists())
				borrarCarpeta.delete();
			
			ThomCliente clientes = new ThomCliente();
			
			clientes.setNmcliente(thomClienteVO.getNmcliente());
			clientes.setCdcliente(thomClienteVO.getCdcliente());
			clientes.setCdtelefono(thomClienteVO.getCdtelefono());
			clientes.setCdusuario(thomClienteVO.getCdusuario());
			clientes.setDscontacto(thomClienteVO.getDscontacto());
			clientes.setDsdireccion(thomClienteVO.getDsdireccion());
			clientes.setDsmail(thomClienteVO.getDsmail());
			clientes.setDsnombre(thomClienteVO.getDsnombre());
			clientes.setSnactivo(thomClienteVO.getSnactivo());

			clienteDao.deleteCliente(clientes);
		}catch(Throwable t){
			contexto.setRollbackOnly();
		}	
	}

}
