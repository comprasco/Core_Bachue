package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;

import com.bachue.snr.prosnr01.model.sdb.aut.Componente;

import com.bachue.snr.prosnr01.web.bean.BaseBean;
import com.bachue.snr.prosnr01.web.util.FacesUtils;

import org.primefaces.PrimeFaces;

import org.primefaces.event.FileUploadEvent;

import org.primefaces.model.UploadedFile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase que contiene todos las propiedades y acciones de BeanAdministracionComponentes.
 *
 * @author Sebastian Sanchez
 */
@ManagedBean(name = "beanAdministracionComponentes")
@SessionScoped
public class BeanAdministracionComponentes extends BaseBean
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -6981209181370888740L;

	/** Propiedad icac datos auditoria. */
	private Collection<Componente> icac_datosAuditoria;

	/** Propiedad iac administracion componentes. */
	private Componente iac_administracionComponentes;

	/** Propiedad icac parametros. */
	private Componente icac_parametros;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad iuf upload file. */
	private UploadedFile iuf_uploadFile;

	/** Propiedad ib insertar. */
	private boolean ib_insertar;

	/**
	 * @param aac_ac asigna el valor a la propiedad
	 */
	public void setAdministracionComponentes(Componente aac_ac)
	{
		iac_administracionComponentes = aac_ac;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public Componente getAdministracionComponentes()
	{
		return iac_administracionComponentes;
	}

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * @param datosAuditoria asigna el valor a la propiedad
	 */
	public void setDatosAuditoria(Collection<Componente> datosAuditoria)
	{
		icac_datosAuditoria = datosAuditoria;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<Componente> getDatosAuditoria()
	{
		return icac_datosAuditoria;
	}

	/**
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setInsertar(boolean ab_b)
	{
		ib_insertar = ab_b;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isInsertar()
	{
		return ib_insertar;
	}

	/**
	 * @param parametros asigna el valor a la propiedad
	 */
	public void setParametros(Componente parametros)
	{
		icac_parametros = parametros;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public Componente getParametros()
	{
		if(icac_parametros == null)
			icac_parametros = new Componente();

		return icac_parametros;
	}

	/**
	 * Modifica el valor de upload file.
	 *
	 * @param auf_uf asigna el valor a la propiedad upload file
	 */
	public void setUploadFile(UploadedFile auf_uf)
	{
		iuf_uploadFile = auf_uf;
	}

	/**
	 * Retorna el valor de upload file.
	 *
	 * @return el valor de upload file
	 */
	public UploadedFile getUploadFile()
	{
		return iuf_uploadFile;
	}

	/**
	 * Método para cambiar estado para saber si se desea insertar una nuevo
	 * Componente
	 */
	public void cambiarEstado()
	{
		setInsertar(true);
		setAdministracionComponentes(new Componente());

		Boolean lb_parametro;
		lb_parametro = FacesUtils.getBooleanFacesParameter("pInsertarAdministracionComponentes");

		if(lb_parametro != null)
			setInsertar(BooleanUtils.getBooleanValue(lb_parametro));
	}

	/**
	 * Método para limpiar la pantalla
	 */
	public void clear()
	{
	}

	/**
	 * Método para consultar en la base de datos la tabla SDB_AUT_COMPONENTE.
	 *
	 * @param aac_ac correspondiente al valor de aac ac
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void consultaDetallada(Componente aac_ac)
	    throws B2BException
	{
		if(aac_ac != null)
		{
			Componente lac_datos;
			lac_datos = ipr_parameterRemote.findAllAdministracionComponentesById(aac_ac);

			if(lac_datos != null)
			{
				Collection<Componente> lcac_cac;
				lcac_cac = new ArrayList<Componente>();

				lcac_cac.add(lac_datos);
				setAdministracionComponentes(lac_datos);

				setDatosAuditoria(lcac_cac);
			}

			setInsertar(false);
		}
	}

	/**
	 *  Método para encontrar todos los registros de la tabla SDB_AUT_COMPONENTE
	 * @return
	 */
	public Collection<Componente> findAdministracionComponentes()
	{
		Collection<Componente> lcac_constantes;

		lcac_constantes = null;

		try
		{
			lcac_constantes = ipr_parameterRemote.findAllAdministracionComponentes();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcac_constantes;
	}

	/**
	 * Método para proceso de carga de archivo.
	 *
	 * @param afue_event correspondiente al valor del tipo de objeto FileUploadEvent
	 * @return devuelve el valor de String
	 */
	public String processFile(FileUploadEvent afue_event)
	{
		UploadedFile luf_file;
		luf_file = afue_event.getFile();

		if(luf_file != null)
			setUploadFile(luf_file);

		return null;
	}

	/**
	 * Método para salvar la inserción o actualización
	 *
	 * @return
	 */
	public String salvar()
	{
		String       ls_result;
		boolean      lb_focus;
		FacesContext lfc_context;

		ls_result       = null;
		lb_focus        = true;
		lfc_context     = FacesContext.getCurrentInstance();

		try
		{
			Componente lac_administracionComponentes;

			lac_administracionComponentes = getAdministracionComponentes();

			if(lac_administracionComponentes != null)
			{
				{
					String ls_nombre;

					ls_nombre     = lac_administracionComponentes.getNombre();

					lb_focus = validateStyles(
						    ":fAdministracionComponentesDetalle:nombre", lfc_context, ls_nombre, lb_focus
						);

					if(!StringUtils.isValidString(ls_nombre))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_NOMBRE);
				}

				{
					Date ld_fechaDesde;

					ld_fechaDesde     = lac_administracionComponentes.getFechaDesde();

					lb_focus = validateStyles(
						    ":fAdministracionComponentesDetalle:idFechaDesde", lfc_context, ld_fechaDesde, lb_focus
						);

					if(ld_fechaDesde == null)
						throw new B2BException(ErrorKeys.ERROR_SIN_FECHA_DESDE);
				}

				{
					String ls_activo;

					ls_activo     = lac_administracionComponentes.getActivo();

					lb_focus = validateStyles(
						    ":fAdministracionComponentesDetalle:idActivo", lfc_context, ls_activo, lb_focus
						);

					if(!StringUtils.isValidString(ls_activo))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_ACTIVO);
				}

				{
					UploadedFile luf_file;

					luf_file = getUploadFile();

					if(luf_file != null)
						lac_administracionComponentes.setImagen(luf_file.getContents());
				}

				ipr_parameterRemote.salvarAdministracionComponentes(
				    lac_administracionComponentes, isInsertar(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

				setParametros(null);

				addMessage(MessagesKeys.PROCESO_COMPLETADO);
				PrimeFaces.current().ajax().update("fAdministracionComponentesDetalle:globalMsg");
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update("fAdministracionComponentesDetalle:globalMsg");
		}

		return ls_result;
	}
}
