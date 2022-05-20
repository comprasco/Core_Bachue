package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;

import com.bachue.snr.prosnr01.model.sdb.pgn.TipoOperacion;

import com.bachue.snr.prosnr01.web.bean.BaseBean;
import com.bachue.snr.prosnr01.web.util.FacesUtils;

import org.primefaces.PrimeFaces;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;


/**
 * Clase que contiene todos las propiedades y acciones de BeanTipoOperacion.
 *
 * @author Sebastian Sanchez
 */
@ManagedBean(name = "beanTipoOperacion")
@SessionScoped
public class BeanTipoOperacion extends BaseBean implements Serializable
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanTipoOperacion.class);

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -8398023545211135276L;

	/** Propiedad icto datos auditoria. */
	private Collection<TipoOperacion> icto_datosAuditoria;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad ito tipo operacion. */
	private TipoOperacion ito_tipoOperacion;

	/** Propiedad ib insertar. */
	private boolean ib_insertar;

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * @param icto_datosAuditoria asigna el valor a la propiedad
	 */
	public void setDatosAuditoria(Collection<TipoOperacion> acto_datosAuditoria)
	{
		icto_datosAuditoria = acto_datosAuditoria;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<TipoOperacion> getDatosAuditoria()
	{
		return icto_datosAuditoria;
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
	 * @param ato_to asigna el valor a la propiedad
	 */
	public void setTipoOperacion(TipoOperacion ato_to)
	{
		ito_tipoOperacion = ato_to;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public TipoOperacion getTipoOperacion()
	{
		return ito_tipoOperacion;
	}

	/**
	 * Método para cambiar estado para saber si se desea insertar o modificar un registro
	 * de la tabla SDB_PGN_TIPO_OPERACION
	 */
	public void cambiarEstado()
	{
		setInsertar(true);
		setTipoOperacion((new TipoOperacion()));

		Boolean lb_parametro;

		lb_parametro = FacesUtils.getBooleanFacesParameter("pInsertarTipoOperacion");

		if(lb_parametro != null)
			setInsertar(BooleanUtils.getBooleanValue(lb_parametro));
	}

	/**
	 * Método de consultar un registro detalladamente de SDB_PGN_TIPO_OPERACION por medio de su indicativo
	 *
	 * @param ato_tipoOperacion indicativo de los datos solicitados
	 * @throws B2BException
	 */
	public void consultaDetallada(TipoOperacion ato_tipoOperacion)
	    throws B2BException
	{
		if(ato_tipoOperacion != null)
		{
			TipoOperacion lto_tipoOperacion;

			lto_tipoOperacion = ipr_parameterRemote.findTipoOperacionById(
				    ato_tipoOperacion, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

			if(lto_tipoOperacion != null)
			{
				Collection<TipoOperacion> lcto_cto;

				lcto_cto = new ArrayList<TipoOperacion>();

				lcto_cto.add(lto_tipoOperacion);

				setTipoOperacion(lto_tipoOperacion);
				setDatosAuditoria(lcto_cto);
			}

			setInsertar(false);
		}
	}

	/**
	 *  Método para encontrar todos los registros de la tabla SDB_PGN_TIPO_OPERACION
	 *
	 * @return Collection de TipoOperacion resultante de la consulta
	 */
	public Collection<TipoOperacion> findAllTipoOperacion()
	{
		Collection<TipoOperacion> lcto_tipoOperacion;

		lcto_tipoOperacion = null;

		try
		{
			lcto_tipoOperacion = ipr_parameterRemote.findAllTipoOperacion();
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
		}

		return lcto_tipoOperacion;
	}

	/**
	 * Método para salvar la inserción o actualización de un registro en la tabla SDB_PGN_TIPO_OPERACION
	 *
	 * @return String contenedor de la pagina a regresar
	 */
	public String salvar()
	{
		String ls_result;

		ls_result = null;

		try
		{
			TipoOperacion lto_tipoOperacion;
			boolean       lb_focus;
			FacesContext  lfc_context;

			lb_focus              = true;
			lfc_context           = FacesContext.getCurrentInstance();
			lto_tipoOperacion     = getTipoOperacion();

			if(lto_tipoOperacion != null)
			{
				String ls_validador;

				{
					ls_validador     = lto_tipoOperacion.getNombre();

					lb_focus = validateStyles(":fTipoOperacionDetalle:nombre", lfc_context, ls_validador, lb_focus);

					if(!StringUtils.isValidString(ls_validador))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_NOMBRE);
				}

				{
					ls_validador     = lto_tipoOperacion.getActivo();

					lb_focus = validateStyles(":fTipoOperacionDetalle:idActivo", lfc_context, ls_validador, lb_focus);

					if(!StringUtils.isValidString(ls_validador))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_ACTIVO);
				}

				ipr_parameterRemote.salvarTipoOperacion(
				    lto_tipoOperacion, isInsertar(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

				clear();

				ls_result = NavegacionCommon.TIPO_OPERACION;

				addMessage(MessagesKeys.PROCESO_COMPLETADO);

				{
					ExternalContext lec_externalContext;

					lec_externalContext = FacesContext.getCurrentInstance().getExternalContext();

					if(lec_externalContext != null)
					{
						Flash lf_flash;

						lf_flash = lec_externalContext.getFlash();

						if(lf_flash != null)
							lf_flash.setKeepMessages(true);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update("fTipoOperacionDetalle:globalMsg");
		}

		return ls_result;
	}

	/**
	 * Retorna el valor del objeto de String con la vista.
	 *
	 * @return devuelve el valor de String
	 */
	public String returnPages()
	{
		String ls_return;

		ls_return = NavegacionCommon.TIPO_OPERACION;

		clear();

		return ls_return;
	}

	/**
	 * Método para limpiar la pantalla
	 */
	public void clear()
	{
		setDatosAuditoria(null);
		setTipoOperacion(null);
		setInsertar(false);
	}
}
