package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;

import com.bachue.snr.prosnr01.model.numeracion.NumeracionOficiosCirculo;

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
 * Clase que contiene todos las propiedades y acciones de BeanNumeracionOficiosCirculo.
 *
 * @author Sebastian Sanchez
 */
@ManagedBean(name = "beanNumeracionOficiosCirculo")
@SessionScoped
public class BeanNumeracionOficiosCirculo extends BaseBean implements Serializable
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanNumeracionOficiosCirculo.class);

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 693862218353991070L;

	/** Propiedad icnoc datos auditoria. */
	private Collection<NumeracionOficiosCirculo> icnoc_datosAuditoria;

	/** Propiedad inoc numeracion oficios circulo. */
	private NumeracionOficiosCirculo inoc_numeracionOficiosCirculo;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad ib insertar. */
	private boolean ib_insertar;

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * @param acnoc_datosAuditoria asigna el valor a la propiedad
	 */
	public void setDatosAuditoria(Collection<NumeracionOficiosCirculo> acnoc_datosAuditoria)
	{
		icnoc_datosAuditoria = acnoc_datosAuditoria;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<NumeracionOficiosCirculo> getDatosAuditoria()
	{
		return icnoc_datosAuditoria;
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
	 * @param anoc_noc asigna el valor a la propiedad
	 */
	public void setNumeracionOficiosCirculo(NumeracionOficiosCirculo anoc_noc)
	{
		inoc_numeracionOficiosCirculo = anoc_noc;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public NumeracionOficiosCirculo getNumeracionOficiosCirculo()
	{
		return inoc_numeracionOficiosCirculo;
	}

	/**
	 * Método para cambiar estado para saber si se desea insertar o modificar un registro
	 * de la tabla SDB_PGN_NUMERACION_OFICIOS_CIRCULO
	 */
	public void cambiarEstado()
	{
		setInsertar(true);
		setNumeracionOficiosCirculo((new NumeracionOficiosCirculo()));

		Boolean lb_parametro;

		lb_parametro = FacesUtils.getBooleanFacesParameter("pInsertarNumeracionOficiosCirculo");

		if(lb_parametro != null)
			setInsertar(BooleanUtils.getBooleanValue(lb_parametro));
	}

	/**
	 * Método de consultar un registro detalladamente de SDB_PGN_NUMERACION_OFICIOS_CIRCULO por medio de su indicativo
	 *
	 * @param anoc_numeracionOficiosCirculo indicativo de los datos solicitados
	 * @throws B2BException
	 */
	public void consultaDetallada(NumeracionOficiosCirculo anoc_numeracionOficiosCirculo)
	    throws B2BException
	{
		if(anoc_numeracionOficiosCirculo != null)
		{
			NumeracionOficiosCirculo lnoc_numeracionOficiosCirculo;

			lnoc_numeracionOficiosCirculo = ipr_parameterRemote.findNumeracionOficiosCirculoById(
				    anoc_numeracionOficiosCirculo, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

			if(lnoc_numeracionOficiosCirculo != null)
			{
				Collection<NumeracionOficiosCirculo> lcnoc_cnoc;

				lcnoc_cnoc = new ArrayList<NumeracionOficiosCirculo>();

				lcnoc_cnoc.add(lnoc_numeracionOficiosCirculo);

				setNumeracionOficiosCirculo(lnoc_numeracionOficiosCirculo);
				setDatosAuditoria(lcnoc_cnoc);
			}

			setInsertar(false);
		}
	}

	/**
	 *  Método para encontrar todos los registros de la tabla SDB_PGN_NUMERACION_OFICIOS_CIRCULO
	 *
	 * @return Collection de ActividadEconomica resultante de la consulta
	 */
	public Collection<NumeracionOficiosCirculo> findAllNumeracionOficiosCirculo()
	{
		Collection<NumeracionOficiosCirculo> lcnoc_numeracionOficiosCirculo;

		lcnoc_numeracionOficiosCirculo = null;

		try
		{
			lcnoc_numeracionOficiosCirculo = ipr_parameterRemote.findAllNumeracionOficiosCirculo();
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
		}

		return lcnoc_numeracionOficiosCirculo;
	}

	/**
	 * Método para salvar la inserción o actualización de un registro en la tabla SDB_PGN_NUMERACION_OFICIOS_CIRCULO
	 *
	 * @return String contenedor de la pagina a regresar
	 */
	public String salvar()
	{
		String ls_result;

		ls_result = null;

		try
		{
			NumeracionOficiosCirculo lnoc_numeracionOficiosCirculo;
			boolean                  lb_focus;
			FacesContext             lfc_context;

			lb_focus                          = true;
			lfc_context                       = FacesContext.getCurrentInstance();
			lnoc_numeracionOficiosCirculo     = getNumeracionOficiosCirculo();

			if(lnoc_numeracionOficiosCirculo != null)
			{
				String ls_validador;
				Long   ll_validador;

				{
					ls_validador     = lnoc_numeracionOficiosCirculo.getIdCirculo();

					lb_focus = validateStyles(
						    ":fNumeracionOficiosCirculoDetalle:idCirculo", lfc_context, ls_validador, lb_focus
						);

					if(!StringUtils.isValidString(ls_validador))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_CIRCULO_REGISTRAL);
				}

				{
					ls_validador     = lnoc_numeracionOficiosCirculo.getIdDependencia();

					lb_focus = validateStyles(
						    ":fNumeracionOficiosCirculoDetalle:idDependencia", lfc_context, ls_validador, lb_focus
						);

					if(!StringUtils.isValidString(ls_validador))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_DEPENDENCIA);
				}

				{
					ls_validador     = lnoc_numeracionOficiosCirculo.getAnoVigente();

					lb_focus = validateStyles(
						    ":fNumeracionOficiosCirculoDetalle:anoVigente", lfc_context, ls_validador, lb_focus
						);

					if(!StringUtils.isValidString(ls_validador))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_ANIO);
				}

				{
					ll_validador     = lnoc_numeracionOficiosCirculo.getConsecutivoOficio();

					lb_focus = validateStyles(
						    ":fNumeracionOficiosCirculoDetalle:consecutivoOficio", lfc_context, ls_validador, lb_focus
						);

					if(!NumericUtils.isValidLong(ll_validador))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_CONSECUTIVO_OFICIO);
				}

				{
					ls_validador     = lnoc_numeracionOficiosCirculo.getActivo();

					lb_focus = validateStyles(
						    ":fNumeracionOficiosCirculoDetalle:idActivo", lfc_context, ls_validador, lb_focus
						);

					if(!StringUtils.isValidString(ls_validador))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_ACTIVO);
				}

				ipr_parameterRemote.salvarNumeracionOficiosCirculo(
				    lnoc_numeracionOficiosCirculo, isInsertar(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

				clear();

				ls_result = NavegacionCommon.NUMERACION_OFICIOS_CIRCULO;

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
			PrimeFaces.current().ajax().update("fNumeracionOficiosCirculoDetalle:globalMsg");
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

		ls_return = NavegacionCommon.ACTIVIDAD_ECONOMICA;

		clear();

		return ls_return;
	}

	/**
	 * Método para limpiar la pantalla
	 */
	public void clear()
	{
		setDatosAuditoria(null);
		setNumeracionOficiosCirculo(null);
		setInsertar(false);
	}
}
