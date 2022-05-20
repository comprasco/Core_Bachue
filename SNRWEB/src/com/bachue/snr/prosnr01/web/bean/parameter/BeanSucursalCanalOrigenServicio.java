package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;

import com.bachue.snr.prosnr01.web.bean.BaseBean;
import com.bachue.snr.prosnr01.web.util.FacesUtils;
import com.bachue.snr.prosnr04.model.pgn.CanalOrigenServicio;
import com.bachue.snr.prosnr04.model.pgn.SucursalCanalOrigenServicio;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase para el manejo de la capa web para Sucursal Canal Origen Servicio.
 *
 * @author Luis Chacón
 */
@ManagedBean(name = "beanSucursalCanalOrigenServicio")
@SessionScoped
public class BeanSucursalCanalOrigenServicio extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 2241794128239578435L;

	/** Propiedad icta datos auditoria. */
	private Collection<SucursalCanalOrigenServicio> icta_datosAuditoria;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad ita sucursal canal origen servicio. */
	private SucursalCanalOrigenServicio ita_SucursalCanalOrigenServicio;

	/** Propiedad ita parametros. */
	private SucursalCanalOrigenServicio ita_parametros;

	/** Propiedad ib insertar. */
	private boolean ib_insertar;

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * Modifica el valor de datos auditoria.
	 *
	 * @param actr_tr asigna el valor a la propiedad datos auditoria
	 */
	public void setDatosAuditoria(Collection<SucursalCanalOrigenServicio> actr_tr)
	{
		icta_datosAuditoria = actr_tr;
	}

	/**
	 * Retorna el valor de datos auditoria.
	 *
	 * @return el valor de datos auditoria
	 */
	public Collection<SucursalCanalOrigenServicio> getDatosAuditoria()
	{
		return icta_datosAuditoria;
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
	 * @param atr_tr asigna el valor a la propiedad parametros
	 */
	public void setParametros(SucursalCanalOrigenServicio atr_tr)
	{
		ita_parametros = atr_tr;
	}

	/**
	 * Retorna el valor de parametros.
	 *
	 * @return el valor de parametros
	 */
	public SucursalCanalOrigenServicio getParametros()
	{
		if(ita_parametros == null)
			ita_parametros = new SucursalCanalOrigenServicio();

		return ita_parametros;
	}

	/**
	 * Modifica el valor de sucursal canal origen servicio.
	 *
	 * @param ata_ta asigna el valor a la propiedad sucursal canal origen servicio
	 */
	public void setSucursalCanalOrigenServicio(SucursalCanalOrigenServicio ata_ta)
	{
		ita_SucursalCanalOrigenServicio = ata_ta;
	}

	/**
	 * Retorna el valor de sucursal canal origen servicio.
	 *
	 * @return el valor de sucursal canal origen servicio
	 */
	public SucursalCanalOrigenServicio getSucursalCanalOrigenServicio()
	{
		return ita_SucursalCanalOrigenServicio;
	}

	/**
	 * Método para cambiar estado para saber si se desea insertar un nuevo
	 * Sucursal Canal Origen Servicio.
	 */
	public void cambiarEstado()
	{
		setInsertar(true);
		setSucursalCanalOrigenServicio(new SucursalCanalOrigenServicio());

		Boolean lb_parametro;
		lb_parametro = FacesUtils.getBooleanFacesParameter("pInsertarSucursalCanalOrigenServicio");

		if(lb_parametro != null)
			setInsertar(BooleanUtils.getBooleanValue(lb_parametro));
	}

	/**
	 * Metodo que se encarga de reiniciar variables.
	 */
	public void clear()
	{
		setParametros(null);
		setInsertar(false);
	}

	/**
	 * Método para consultar en la base de datos la tabla SDB_PGN_SUCURSAL_CANAL_ORIGEN_SERVICIO.
	 *
	 * @param ata_ta correspondiente al valor del tipo de objeto SucursalCanalOrigenServicio
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void consultaDetallada(SucursalCanalOrigenServicio ata_ta)
	    throws B2BException
	{
		if(ata_ta != null)
		{
			SucursalCanalOrigenServicio lta_dato;

			lta_dato     = null;

			lta_dato = ipr_parameterRemote.findSucursalCanalOrigenServicioById(ata_ta);

			if(lta_dato != null)
			{
				Collection<SucursalCanalOrigenServicio> lcta_ta;
				lcta_ta = new ArrayList<SucursalCanalOrigenServicio>();

				lcta_ta.add(lta_dato);
				setSucursalCanalOrigenServicio(lta_dato);

				setDatosAuditoria(lcta_ta);
			}

			setInsertar(false);
		}
	}

	/**
	 *  Método para encontrar todos los registros de la tabla SDB_PGN_SUCURSAL_CANAL_ORIGEN_SERVICIO.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<SucursalCanalOrigenServicio> findAllSucursalCanalOrigenServicio()
	{
		Collection<SucursalCanalOrigenServicio> lctr_tr;
		lctr_tr = null;

		try
		{
			lctr_tr = ipr_parameterRemote.findAllSucursalCanalOrigenServicio();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lctr_tr;
	}
	
	/**
	 *  Método para encontrar todos los registros de canal origen servicio.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<CanalOrigenServicio> findAllCanalOrigenServicio()
	{
		Collection<CanalOrigenServicio> lctr_tr;
		lctr_tr = null;

		try
		{
			lctr_tr = ipr_parameterRemote.findAllCanalOrigenServicio();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lctr_tr;
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
			SucursalCanalOrigenServicio lta_parametros;

			lta_parametros = getSucursalCanalOrigenServicio();

			if(lta_parametros != null)
			{

				{
					String ls_datoValidar;
					ls_datoValidar     = lta_parametros.getCodigoSucursalCanalOrigenServicio();

					lb_focus = validateStyles(
						    ":fSucursalCanalOrigenServicioDetalle:idCodigoSucursalCanalOrigenServicio", lfc_context,
						    ls_datoValidar, lb_focus
						);

					if(!StringUtils.isValidString(ls_datoValidar))
						throw new B2BException(ErrorKeys.ERROR_CODIGO_SUCURSAL_CANAL_ORIGEN_SERVICIO_NO_VALIDO);
				}

				{
					String ls_datoValidar;
					ls_datoValidar     = lta_parametros.getIdCanalOrigenServicio();

					lb_focus = validateStyles(
						    ":fSucursalCanalOrigenServicioDetalle:idCanalOrigenServicio", lfc_context, ls_datoValidar,
						    lb_focus
						);

					if(!StringUtils.isValidString(ls_datoValidar))
						throw new B2BException(ErrorKeys.ERROR_ID_CANAL_ORIGEN_SERVICIO_EXISTE);
				}

				{
					String ls_datoValidar;
					ls_datoValidar     = lta_parametros.getNombreSucursalCanalOrigenServicio();

					lb_focus = validateStyles(
						    ":fSucursalCanalOrigenServicioDetalle:idNombreSucursalCanalOrigenServicio", lfc_context,
						    ls_datoValidar, lb_focus
						);

					if(!StringUtils.isValidString(ls_datoValidar))
						throw new B2BException(ErrorKeys.ERROR_SIN_NOMBRE_SUCURSAL_CANAL_ORIGEN_SERVICIO);
				}

				{
					String ls_datoValidar;
					ls_datoValidar     = lta_parametros.getActivo();

					lb_focus = validateStyles(
						    ":fSucursalCanalOrigenServicioDetalle:idActivo", lfc_context, ls_datoValidar, lb_focus
						);

					if(!StringUtils.isValidString(ls_datoValidar))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ACTIVO);
				}

				ipr_parameterRemote.salvarSucursalCanalOrigenServicio(
				    lta_parametros, isInsertar(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

				setParametros(null);
				setSucursalCanalOrigenServicio(null);

				ls_result = NavegacionCommon.SUCURSAL_CANAL_ORIGEN_SERVICIO;
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return ls_result;
	}
}
