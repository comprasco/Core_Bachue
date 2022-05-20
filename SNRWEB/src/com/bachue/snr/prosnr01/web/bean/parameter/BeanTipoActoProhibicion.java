package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.reference.ReferenceRemote;

import com.bachue.snr.prosnr01.model.sdb.pgn.TipoActo;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoActoProhibicion;

import com.bachue.snr.prosnr01.web.bean.BaseBean;
import com.bachue.snr.prosnr01.web.util.FacesUtils;

import org.primefaces.PrimeFaces;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase que contiene todos las propiedades y acciones de BeanTipoActoProhibicion.
 *
 * @author Duvan Beltran
 */
@ManagedBean(name = "beanTipoActoProhibicion")
@SessionScoped
public class BeanTipoActoProhibicion extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 2377957135897382730L;

	/** Propiedad icta tipo acto versiones. */
	private Collection<TipoActo> icta_tipoActoVersiones;

	/** Propiedad ictap datos auditoria. */
	private Collection<TipoActoProhibicion> ictap_datosAuditoria;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad irr reference remote. */
	@EJB
	private ReferenceRemote irr_referenceRemote;

	/** Propiedad itap tipo acto prohibicion. */
	private TipoActoProhibicion itap_tipoActoProhibicion;

	/** Propiedad ib insertar. */
	private boolean ib_insertar;

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * Modifica el valor de DatosAuditoria.
	 *
	 * @param actd_datosAuditoria asigna el valor a la propiedad
	 */
	public void setDatosAuditoria(Collection<TipoActoProhibicion> actd_datosAuditoria)
	{
		ictap_datosAuditoria = actd_datosAuditoria;
	}

	/**
	 * Retorna Objeto o variable de valor datos auditoria.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<TipoActoProhibicion> getDatosAuditoria()
	{
		return ictap_datosAuditoria;
	}

	/**
	 * Modifica el valor de Insertar.
	 *
	 * @param ab_a asigna el valor a la propiedad
	 */
	public void setInsertar(boolean ab_a)
	{
		ib_insertar = ab_a;
	}

	/**
	 * Retorna Objeto o variable de valor insertar.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean getInsertar()
	{
		return ib_insertar;
	}

	/**
	 * Valida la propiedad insertar.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isInsertar()
	{
		return ib_insertar;
	}

	/**
	 * Modifica el valor de TipoActoProhibicion.
	 *
	 * @param atd_td asigna el valor a la propiedad
	 */
	public void setTipoActoProhibicion(TipoActoProhibicion atd_td)
	{
		itap_tipoActoProhibicion = atd_td;
	}

	/**
	 * Retorna Objeto o variable de valor tipo acto prohibicion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public TipoActoProhibicion getTipoActoProhibicion()
	{
		return itap_tipoActoProhibicion;
	}

	/**
	 * Modifica el valor de TipoActoVersiones.
	 *
	 * @param acta_cta asigna el valor a la propiedad
	 */
	public void setTipoActoVersiones(Collection<TipoActo> acta_cta)
	{
		icta_tipoActoVersiones = acta_cta;
	}

	/**
	 * Retorna Objeto o variable de valor tipo acto versiones.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<TipoActo> getTipoActoVersiones()
	{
		return icta_tipoActoVersiones;
	}

	/**
	 * Método para cambiar estado para saber si se desea insertar un nuevo registro.
	 */
	public void cambiarEstado()
	{
		setInsertar(true);
		setTipoActoProhibicion((new TipoActoProhibicion()));

		Boolean lb_parametro;
		lb_parametro = FacesUtils.getBooleanFacesParameter("pInsertarTipoActoProhibicion");

		if(lb_parametro != null)
			setInsertar(BooleanUtils.getBooleanValue(lb_parametro));
	}

	/**
	 * Limpia las variables del Bean.
	 */
	public void clean()
	{
		setTipoActoVersiones(null);
		setDatosAuditoria(null);
		setInsertar(false);
	}

	/**
	 * Consulta detallada.
	 *
	 * @param atd_td de atd td
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void consultaDetallada(TipoActoProhibicion atd_td)
	    throws B2BException
	{
		if(atd_td != null)
		{
			String ls_idTipoActoProhibicion;

			ls_idTipoActoProhibicion = atd_td.getIdTipoActoProhibicion();

			if(StringUtils.isValidString(ls_idTipoActoProhibicion))
			{
				atd_td = ipr_parameterRemote.findAllTipoActoProhibicionById(ls_idTipoActoProhibicion);

				if(atd_td != null)
				{
					Collection<TipoActoProhibicion> lctd_td;

					lctd_td = new ArrayList<TipoActoProhibicion>();

					lctd_td.add(atd_td);
					setTipoActoProhibicion(atd_td);

					setDatosAuditoria(lctd_td);
				}

				setInsertar(false);
			}
		}
	}

/**
 * Método de consulta para encontrar todos los tipo acto prohibicion.
 *
 * @return una colección con los datos solicitados
 */
	public Collection<TipoActoProhibicion> findAllTipoActoProhibicion()
	{
		Collection<TipoActoProhibicion> lccc_cc;
		lccc_cc = null;

		try
		{
			lccc_cc = ipr_parameterRemote.findAllTipoActoProhibicion();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lccc_cc;
	}

/**
 * Método de consulta para encontrar todos los tipos actos por su indicativo.
 */
	public void findTipoActoById()
	{
		try
		{
			TipoActoProhibicion ltap_tipoActoProhibicion;

			ltap_tipoActoProhibicion = getTipoActoProhibicion();

			if(ltap_tipoActoProhibicion != null)
			{
				Collection<TipoActo> lcta_cllTipoActo;

				lcta_cllTipoActo = irr_referenceRemote.findTipoActoById(ltap_tipoActoProhibicion.getIdTipoActo());

				if(CollectionUtils.isValidCollection(lcta_cllTipoActo))
					setTipoActoVersiones(lcta_cllTipoActo);
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}
	}

	/**
	 * Método para salvar la inserción o actualización.
	 *
	 * @return el valor de string
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
			TipoActoProhibicion ltap_tipoActoProhibicion;

			ltap_tipoActoProhibicion = getTipoActoProhibicion();

			if(ltap_tipoActoProhibicion != null)
			{
				{
					String ls_tipoActo;

					ls_tipoActo     = ltap_tipoActoProhibicion.getIdTipoActo();

					lb_focus = validateStyles(
						    ":fTipoActoProhibicionDetalle:idTipoActo", lfc_context, ls_tipoActo, lb_focus
						);

					if(!StringUtils.isValidString(ls_tipoActo))
						throw new B2BException(ErrorKeys.ERROR_TIPO_ACTO_INVALIDO);
				}

				{
					String ls_versionActo;

					ls_versionActo     = ltap_tipoActoProhibicion.getVersionActo();

					lb_focus = validateStyles(
						    ":fTipoActoProhibicionDetalle:idVersion", lfc_context, ls_versionActo, lb_focus
						);

					if(!StringUtils.isValidString(ls_versionActo))
						throw new B2BException(ErrorKeys.ERROR_SIN_VERSION_ACTO);
				}

				{
					Long ls_tiempoVencimiento;

					ls_tiempoVencimiento     = ltap_tipoActoProhibicion.getTiempoVencimiento();

					lb_focus = validateStyles(
						    ":fTipoActoProhibicionDetalle:idTiempoVencimiento", lfc_context, ls_tiempoVencimiento,
						    lb_focus
						);

					if(!NumericUtils.isValidLong(ls_tiempoVencimiento))
						throw new B2BException(ErrorKeys.DEBE_ELEGIR_TIEMPO_VENCIMIENTO);
				}

				{
					String ls_idUnidadTiempo;

					ls_idUnidadTiempo     = ltap_tipoActoProhibicion.getIdUnidadTiempo();

					lb_focus = validateStyles(
						    ":fTipoActoProhibicionDetalle:idUnidadTiempo", lfc_context, ls_idUnidadTiempo, lb_focus
						);

					if(!StringUtils.isValidString(ls_idUnidadTiempo))
						throw new B2BException(ErrorKeys.DEBE_ELEGIR_UNIDAD_TIEMPO);
				}

				{
					String ls_cancelacionAutomatica;

					ls_cancelacionAutomatica     = ltap_tipoActoProhibicion.getCancelacionAutomatica();

					lb_focus = validateStyles(
						    ":fTipoActoProhibicionDetalle:idCancelacionAutomatica", lfc_context,
						    ls_cancelacionAutomatica, lb_focus
						);

					if(!StringUtils.isValidString(ls_cancelacionAutomatica))
						throw new B2BException(ErrorKeys.DEBE_ELEGIR_CANCELACION_AUTOMATICA);
				}

				ipr_parameterRemote.salvarTipoActoProhibicion(
				    ltap_tipoActoProhibicion, isInsertar(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);
				//setTipoActoProhibicion(null);
				ls_result = NavegacionCommon.TIPO_ACTO_PROHIBICION;
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update("fTipoActoProhibicionDetalle:globalMsg");
		}

		return ls_result;
	}
}
