package com.bachue.snr.prosnr01.web.bean.consulta.reparto.calificacion;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;

import com.bachue.snr.prosnr01.ejb.session.stateless.consulta.reparto.calificacion.ConsultaRepartoCalificacionRemote;

import com.bachue.snr.prosnr01.model.consulta.reparto.calificacion.UsuarioConsulta;
import com.bachue.snr.prosnr01.model.sdb.aut.Usuario;

import com.bachue.snr.prosnr01.web.bean.BaseBean;

import org.primefaces.PrimeFaces;

import org.primefaces.event.TransferEvent;

import org.primefaces.model.DualListModel;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;

import javax.faces.application.FacesMessage;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import javax.faces.context.FacesContext;


/**
 * Clase que contiene todos las propiedades y acciones de BeanConsultaRepartoCalificacion.
 *
 * @author Julian Vaca
 */
@ManagedBean(name = "beanConsultaRepartoCalificacion")
@ViewScoped
public class BeanConsultaRepartoCalificacion extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -5319075701026895433L;

	/** Propiedad icr calificion remote. */
	@EJB
	private ConsultaRepartoCalificacionRemote icr_calificionRemote;

	/** Propiedad irrcr reparto calificacion remote. */
	@EJB
	private ConsultaRepartoCalificacionRemote irrcr_repartoCalificacionRemote;

	/** Propiedad idlmuc usuarios consulta. */
	private DualListModel<UsuarioConsulta> idlmuc_usuariosConsulta;

	/** Propiedad iluc usuarios consultados. */
	private List<UsuarioConsulta> iluc_usuariosConsultados;

	/** {@inheritdoc} */
	public String getApplication()
	{
		return null;
	}

	/**
	 * Modifica el valor de usuarios consulta.
	 *
	 * @param adlmuc_dlmuc asigna el valor a la propiedad usuarios consulta
	 */
	public void setUsuariosConsulta(DualListModel<UsuarioConsulta> adlmuc_dlmuc)
	{
		idlmuc_usuariosConsulta = adlmuc_dlmuc;
	}

	/**
	 * Retorna el valor de usuarios consulta.
	 *
	 * @return el valor de usuarios consulta
	 */
	public DualListModel<UsuarioConsulta> getUsuariosConsulta()
	{
		return idlmuc_usuariosConsulta;
	}

	/**
	 * Modifica el valor de usuarios consultados.
	 *
	 * @param aluc_luc asigna el valor a la propiedad usuarios consultados
	 */
	public void setUsuariosConsultados(List<UsuarioConsulta> aluc_luc)
	{
		iluc_usuariosConsultados = aluc_luc;
	}

	/**
	 * Retorna el valor de usuarios consultados.
	 *
	 * @return el valor de usuarios consultados
	 */
	public List<UsuarioConsulta> getUsuariosConsultados()
	{
		return iluc_usuariosConsultados;
	}

	/**
	 * Consulta detallada.
	 */
	public void consultaDetallada()
	{
		try
		{
			List<UsuarioConsulta>     lluc_usuariosConsulta;
			Iterator<UsuarioConsulta> lliuc_lista;

			lluc_usuariosConsulta     = new ArrayList<UsuarioConsulta>();
			lliuc_lista               = getUsuariosConsulta().getTarget().iterator();

			if(lliuc_lista != null)
			{
				while(lliuc_lista.hasNext())
				{
					Object lo_o;

					lo_o = lliuc_lista.next();

					if((lo_o != null) && lo_o instanceof UsuarioConsulta)
					{
						UsuarioConsulta luc_usuario;

						luc_usuario = (UsuarioConsulta)lo_o;

						if(luc_usuario != null)
						{
							Usuario lu_usduario;

							lu_usduario = luc_usuario.getUsuario();

							if(lu_usduario != null)
								lluc_usuariosConsulta.add(luc_usuario);
						}
					}
				}

				setUsuariosConsultados(lluc_usuariosConsulta);

				String ls_mensaje = "Consulta exitosa, verifique los datos.";
				addMessage("I", ls_mensaje);
				PrimeFaces.current().ajax().update("fConsultaReparto:globalMsg");
			}

			else
				throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_USUARIOS);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}
	}

	/**
	 * Find all.
	 */
	public void findAll()
	{
		try
		{
			String                         ls_idUsuario;
			Collection<Usuario>            lcu_usuarios;
			List<UsuarioConsulta>          lluc_usuariosConsultaSource;
			List<UsuarioConsulta>          lluc_usuariosConsultaTarget;
			DualListModel<UsuarioConsulta> ldlmuc_return;

			ls_idUsuario                    = getUserId();
			lcu_usuarios                    = icr_calificionRemote.findByORIP(ls_idUsuario);
			lluc_usuariosConsultaSource     = new ArrayList<UsuarioConsulta>();
			lluc_usuariosConsultaTarget     = new ArrayList<UsuarioConsulta>();

			if(CollectionUtils.isValidCollection(lcu_usuarios))
			{
				for(Usuario lu_iterador : lcu_usuarios)
				{
					if(lu_iterador != null)
					{
						UsuarioConsulta luc_usuarioActual;
						luc_usuarioActual = new UsuarioConsulta();

						luc_usuarioActual.setConsultaRepartoCalificacion(
						    irrcr_repartoCalificacionRemote.findByUser(lu_iterador)
						);
						luc_usuarioActual.setNombreCompleto(llenarNombre(lu_iterador));
						luc_usuarioActual.setUsuario(lu_iterador);

						lluc_usuariosConsultaSource.add(luc_usuarioActual);
					}
				}
			}
			else
				lcu_usuarios = new ArrayList<Usuario>();

			ldlmuc_return = new DualListModel<UsuarioConsulta>(
				    lluc_usuariosConsultaSource, lluc_usuariosConsultaTarget
				);

			setUsuariosConsulta(ldlmuc_return);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}
	}

	/**
	 * Método encargado de instanciar usuariosconsulta y usuariosconsultados al
	 * llamar la clase.
	 */
	public void iniciar()
	{
		setUsuariosConsulta(new DualListModel<UsuarioConsulta>());
		setUsuariosConsultados(new ArrayList<UsuarioConsulta>());
		findAll();
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param au_u correspondiente al valor del tipo de objeto Usuario
	 * @return devuelve el valor de String
	 */
	public String llenarNombre(Usuario au_u)
	{
		String ls_primerNombre;
		String ls_segundoNombre;
		String ls_primerApellido;
		String ls_segundoApellido;

		ls_primerNombre        = au_u.getPrimerNombre();
		ls_segundoNombre       = au_u.getSegundoNombre();
		ls_primerApellido      = au_u.getPrimerApellido();
		ls_segundoApellido     = au_u.getSegundoApellido();

		String ls_nombreCompleto = "";

		if(StringUtils.isValidString(ls_primerNombre))
			ls_nombreCompleto = ls_nombreCompleto + ls_primerNombre + " ";

		if(StringUtils.isValidString(ls_segundoNombre))
			ls_nombreCompleto = ls_nombreCompleto + ls_segundoNombre + " ";

		if(StringUtils.isValidString(ls_primerApellido))
			ls_nombreCompleto = ls_nombreCompleto + ls_primerApellido + " ";

		if(StringUtils.isValidString(ls_segundoApellido))
			ls_nombreCompleto = ls_nombreCompleto + ls_segundoApellido + " ";

		return ls_nombreCompleto;
	}

	/**
	 * On transfer.
	 *
	 * @param ate_event correspondiente al valor del tipo de objeto TransferEvent
	 */
	public void onTransfer(TransferEvent ate_event)
	{
		StringBuilder lsb_builder = new StringBuilder();

		for(Object lo_item : ate_event.getItems())
			lsb_builder.append(((UsuarioConsulta)lo_item).getNombreCompleto()).append("<br />");

		FacesMessage lfm_msg = new FacesMessage();
		lfm_msg.setSeverity(FacesMessage.SEVERITY_INFO);
		lfm_msg.setSummary("Items Transferred");
		lfm_msg.setDetail(lsb_builder.toString());

		FacesContext.getCurrentInstance().addMessage(null, lfm_msg);
	}
}
