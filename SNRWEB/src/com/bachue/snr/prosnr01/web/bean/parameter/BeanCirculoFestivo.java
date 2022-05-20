package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;

import com.bachue.snr.prosnr01.model.sdb.pgn.CirculoFestivo;

import com.bachue.snr.prosnr01.web.bean.BaseBean;
import com.bachue.snr.prosnr01.web.util.FacesUtils;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase para el manejo de la capa web para Causal Aprobación Devolución.
 *
 * @author Sebastian Tafur
 */
@ManagedBean(name = "beanCirculoFestivo")
@SessionScoped
public class BeanCirculoFestivo extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 6573575237467799977L;

	/** Propiedad ima circulo festivo. */
	private CirculoFestivo ima_CirculoFestivo;

	/** Propiedad ima parametros. */
	private CirculoFestivo ima_parametros;

	/** Propiedad icma datos auditoria. */
	private Collection<CirculoFestivo> icma_datosAuditoria;

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
	 * Modifica el valor de circulo festivo.
	 *
	 * @param ama_ma asigna el valor a la propiedad circulo festivo
	 */
	public void setCirculoFestivo(CirculoFestivo ama_ma)
	{
		ima_CirculoFestivo = ama_ma;
	}

	/**
	 * Retorna el valor de circulo festivo.
	 *
	 * @return el valor de circulo festivo
	 */
	public CirculoFestivo getCirculoFestivo()
	{
		return ima_CirculoFestivo;
	}

	/**
	 * Modifica el valor de datos auditoria.
	 *
	 * @param acma_cma asigna el valor a la propiedad datos auditoria
	 */
	public void setDatosAuditoria(Collection<CirculoFestivo> acma_cma)
	{
		icma_datosAuditoria = acma_cma;
	}

	/**
	 * Retorna el valor de datos auditoria.
	 *
	 * @return el valor de datos auditoria
	 */
	public Collection<CirculoFestivo> getDatosAuditoria()
	{
		return icma_datosAuditoria;
	}

	/**
	 * Modifica el valor de insertar.
	 *
	 * @param ab_b asigna el valor a la propiedad insertar
	 */
	public void setInsertar(boolean ab_b)
	{
		ib_insertar = ab_b;
	}

	/**
	 * Valida la propiedad insertar.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en insertar
	 */
	public boolean isInsertar()
	{
		return ib_insertar;
	}

	/**
	 * Modifica el valor de parametros.
	 *
	 * @param parametros asigna el valor a la propiedad parametros
	 */
	public void setParametros(CirculoFestivo parametros)
	{
		ima_parametros = parametros;
	}

	/**
	 * Retorna el valor de parametros.
	 *
	 * @return el valor de parametros
	 */
	public CirculoFestivo getParametros()
	{
		if(ima_parametros == null)
			ima_parametros = new CirculoFestivo();

		return ima_parametros;
	}

	/**
	 * Método para cambiar estado para saber si se desea insertar un nuevo
	 * Causal Aprobación Devolución.
	 */
	public void cambiarEstado()
	{
		setInsertar(true);
		setCirculoFestivo(new CirculoFestivo());

		Boolean lb_parametro;
		lb_parametro = FacesUtils.getBooleanFacesParameter("pInsertarCirculoFestivo");

		if(lb_parametro != null)
			setInsertar(BooleanUtils.getBooleanValue(lb_parametro));
	}

	/**
	 * Método para consultar en la base de datos la tabla SDB_PGN_CAUSAL_APROBACION_DEVOLUCION.
	 *
	 * @param acf_cf correspondiente al valor del tipo de objeto CirculoFestivo
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void consultaDetallada(CirculoFestivo acf_cf)
	    throws B2BException
	{
		if(acf_cf != null)
		{
			CirculoFestivo lcad_datos;
			String         ls_s;

			ls_s           = acf_cf.getIdCirculoFestivo();
			lcad_datos     = null;

			if(StringUtils.isValidString(ls_s))
				lcad_datos = ipr_parameterRemote.findCirculoFestivoById(ls_s);

			if(lcad_datos != null)
			{
				Collection<CirculoFestivo> lccf_cf;
				lccf_cf = new ArrayList<CirculoFestivo>();

				lccf_cf.add(lcad_datos);
				setCirculoFestivo(lcad_datos);

				setDatosAuditoria(lccf_cf);
			}

			setInsertar(false);
		}
	}

	/**
	 *  Método para encontrar todos los registros de la tabla SDB_PGN_CAUSAL_APROBACION_DEVOLUCION.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<CirculoFestivo> findAllCirculoFestivo()
	{
		Collection<CirculoFestivo> lccf_circulosFestivos;
		lccf_circulosFestivos = null;

		try
		{
			lccf_circulosFestivos = ipr_parameterRemote.findAllCirculoFestivo();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lccf_circulosFestivos;
	}

	/**
	 * Método para encontrar todos los registros de la tabla SDB_PGN_CAUSAL_APROBACION_DEVOLUCION
	 * que coincida con un id específico.
	 */
	public void findCirculoFestivoById()
	{
		try
		{
			CirculoFestivo lcf_parametros;

			lcf_parametros = getCirculoFestivo();

			if(lcf_parametros != null)
			{
				String ls_s;

				ls_s = lcf_parametros.getIdCirculoFestivo();

				if(StringUtils.isValidString(ls_s))
				{
					lcf_parametros.setIdCirculoFestivo(lcf_parametros.getIdCirculoFestivo());
					setParametros(ipr_parameterRemote.findCirculoFestivoById(ls_s));
					setInsertar(false);
				}
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
	 * @return devuelve el valor de String
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
			CirculoFestivo lcf_parametros;

			lcf_parametros = getCirculoFestivo();

			if(lcf_parametros != null)
			{
				{
					String ls_idCirculo;
					ls_idCirculo     = lcf_parametros.getIdCirculo();

					lb_focus = validateStyles(":fCirculoFestivoDetalle:idCirculo", lfc_context, ls_idCirculo, lb_focus);

					if(!StringUtils.isValidString(ls_idCirculo))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_CIRCULO_REGISTRAL);
				}

				{
					Date ls_fecha;
					ls_fecha     = lcf_parametros.getFecha();

					lb_focus = validateStyles(":fCirculoFestivoDetalle:idFecha", lfc_context, ls_fecha, lb_focus);

					if(ls_fecha == null)
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_FECHA);
				}

				{
					String ls_descripcion;
					ls_descripcion     = lcf_parametros.getDescripcion();

					lb_focus = validateStyles(
						    ":fCirculoFestivoDetalle:idDescripcion", lfc_context, ls_descripcion, lb_focus
						);

					if(!StringUtils.isValidString(ls_descripcion))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_DESCRIPCION);
				}

				{
					String ls_activo;
					ls_activo     = lcf_parametros.getActivo();

					lb_focus = validateStyles(":fCirculoFestivoDetalle:idActivo", lfc_context, ls_activo, lb_focus);

					if(!StringUtils.isValidString(ls_activo))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ACTIVO);
				}

				ipr_parameterRemote.salvarCirculoFestivo(
				    lcf_parametros, isInsertar(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

				setParametros(null);
				setCirculoFestivo(null);

				ls_result = NavegacionCommon.CIRCULO_FESTIVO;
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return ls_result;
	}
}
