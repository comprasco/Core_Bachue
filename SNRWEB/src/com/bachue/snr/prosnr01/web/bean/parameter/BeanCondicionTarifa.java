package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;

import com.bachue.snr.prosnr01.model.sdb.acc.TipoTarifaRegistral;
import com.bachue.snr.prosnr01.model.sdb.pgn.CondicionTarifa;
import com.bachue.snr.prosnr01.model.sdb.pgn.TarifaFija;

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
 * Clase para el manejo de la capa web para Condicion Tarifa.
 *
 * @author Julian Vaca
 */
@ManagedBean(name = "beanCondicionTarifa")
@SessionScoped
public class BeanCondicionTarifa extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 6573575237467799977L;

	/** Propiedad icma datos auditoria. */
	private Collection<CondicionTarifa> icma_datosAuditoria;

	/** Propiedad ima condicion tarifa. */
	private CondicionTarifa ima_condicionTarifa;

	/** Propiedad ima parametros. */
	private CondicionTarifa ima_parametros;

	/** Propiedad ll version tarifa fija. */
	private Long ll_versionTarifaFija;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad ib insertar. */
	private boolean ib_insertar;

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		setVersionTarifaFija(null);

		return null;
	}

	/**
	 * Modifica el valor de datos auditoria.
	 *
	 * @param acma_cma asigna el valor a la propiedad datos auditoria
	 */
	public void setDatosAuditoria(Collection<CondicionTarifa> acma_cma)
	{
		icma_datosAuditoria = acma_cma;
	}

	/**
	 * Retorna el valor de datos auditoria.
	 *
	 * @return el valor de datos auditoria
	 */
	public Collection<CondicionTarifa> getDatosAuditoria()
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
	 * @param act_ct asigna el valor a la propiedad parametros
	 */
	public void setParametros(CondicionTarifa act_ct)
	{
		ima_parametros = act_ct;
	}

	/**
	 * Retorna el valor de parametros.
	 *
	 * @return el valor de parametros
	 */
	public CondicionTarifa getParametros()
	{
		if(ima_parametros == null)
			ima_parametros = new CondicionTarifa();

		return ima_parametros;
	}

	/**
	 * Modifica el valor de version tarifa fija.
	 *
	 * @param al_l asigna el valor a la propiedad version tarifa fija
	 */
	public void setVersionTarifaFija(Long al_l)
	{
		ll_versionTarifaFija = al_l;
	}

	/**
	 * Retorna el valor de version tarifa fija.
	 *
	 * @return el valor de version tarifa fija
	 */
	public Long getVersionTarifaFija()
	{
		return ll_versionTarifaFija;
	}

	/**
	 * Método para cambiar estado para saber si se desea insertar una nueva
	 * Condición Tarifa.
	 */
	public void cambiarEstado()
	{
		setInsertar(true);
		setVersionTarifaFija(null);
		setcondicionTarifa(new CondicionTarifa());

		Boolean lb_parametro;
		lb_parametro = FacesUtils.getBooleanFacesParameter("pInsertarCondicionTarifa");

		if(lb_parametro != null)
			setInsertar(BooleanUtils.getBooleanValue(lb_parametro));
	}

	/**
	 * Método para consultar en la base de datos la tabla SDB_PGN_CONDICION_TARIFA.
	 *
	 * @param acad_cad objeto del que se extrae el id para realizar la consulta en la base de datos
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void consultaDetallada(CondicionTarifa acad_cad)
	    throws B2BException
	{
		if(acad_cad != null)
		{
			CondicionTarifa lcad_datos;
			String          ls_s;

			ls_s           = acad_cad.getIdCondicionTarifa();
			lcad_datos     = null;

			if(StringUtils.isValidString(ls_s))
				lcad_datos = ipr_parameterRemote.findCondicionTarifaById(ls_s);

			if(lcad_datos != null)
			{
				Collection<CondicionTarifa> lccad_ccad;
				lccad_ccad = new ArrayList<CondicionTarifa>();

				long ll_version;

				ll_version = NumericUtils.getInt(lcad_datos.getVersionTarifaFija());

				if(ll_version > 0)
					setVersionTarifaFija(NumericUtils.getLongWrapper(NumericUtils.getLong(ll_version)));

				lccad_ccad.add(lcad_datos);
				setcondicionTarifa(lcad_datos);

				setDatosAuditoria(lccad_ccad);
			}

			setInsertar(false);
		}
	}

	/**
	 *  Método para encontrar todos los registros de la tabla SDB_PGN_CONDICION_TARIFA.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<CondicionTarifa> findAllCondicionTarifa()
	{
		Collection<CondicionTarifa> lcr_constantes;
		lcr_constantes = null;

		try
		{
			lcr_constantes = ipr_parameterRemote.findAllCondicionTarifa();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcr_constantes;
	}

	/**
	 * Método para encontrar todos los registros de la tabla SDB_PGN_CONDICION_TARIFA
	 * que coincida con un id específico.
	 */
	public void findCondicionTarifaById()
	{
		try
		{
			CondicionTarifa ld_parametros;

			ld_parametros = getcondicionTarifa();

			if(ld_parametros != null)
			{
				String ls_s;

				ls_s = ld_parametros.getIdCondicionTarifa();

				if(StringUtils.isValidString(ls_s))
				{
					ld_parametros.setIdCondicionTarifa(ld_parametros.getIdCondicionTarifa());
					setParametros(ipr_parameterRemote.findCondicionTarifaById(ls_s));
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
	 * Método para consultar en la base de datos la tabla SDB_PGN_CONDICION_TARIFA.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void findVersionTarifaFija()
	    throws B2BException
	{
		TarifaFija ltf_datos;
		String     ls_idTarifaFija;

		ltf_datos           = new TarifaFija();
		ls_idTarifaFija     = getcondicionTarifa().getIdTarifaFija();

		if(StringUtils.isValidString(ls_idTarifaFija))
		{
			ltf_datos.setIdTarifaFija(ls_idTarifaFija);

			ltf_datos = ipr_parameterRemote.findTarifaFijaById(ltf_datos);

			if(ltf_datos != null)
			{
				long ls_versionTarifaFija;

				ls_versionTarifaFija = NumericUtils.getLong(ltf_datos.getVersion());

				if(NumericUtils.isValidLong(NumericUtils.getLongWrapper(ls_versionTarifaFija)))
					getcondicionTarifa().setVersionTarifaFija(StringUtils.getString(ls_versionTarifaFija));
			}

			PrimeFaces.current().ajax().update("idVersionTarifaFija");
		}
	}

	/**
	 * Método para consultar en la base de datos la tabla SDB_PGN_CONDICION_TARIFA.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void findVersionTarifaRegistral()
	    throws B2BException
	{
		TipoTarifaRegistral lcad_datos;
		CondicionTarifa     lct_condicion;

		lcad_datos     = new TipoTarifaRegistral();

		lct_condicion = getcondicionTarifa();

		if(lct_condicion != null)
		{
			lcad_datos.setIdTipoTarifa((lct_condicion.getIdTipoTarifaRegistral()));
			lcad_datos.setValidTipoId(true);

			lcad_datos = ipr_parameterRemote.findTiposTarifas(
				    lcad_datos, true, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

			if(lcad_datos != null)
			{
				Collection<TipoTarifaRegistral> lttr_tarifa;

				lttr_tarifa     = new ArrayList<TipoTarifaRegistral>();

				lttr_tarifa = lcad_datos.getInfoAll();

				Long ll_version;

				for(TipoTarifaRegistral lddp_iterador : lttr_tarifa)
				{
					ll_version = lddp_iterador.getVersion();
					getcondicionTarifa().setVersionTarifaRegistral(StringUtils.getString(ll_version));
				}
			}
		}

		PrimeFaces.current().ajax().update("idVersionTarifaRegistral");
	}

	/**
	 * Retorna el valor de condicion tarifa.
	 *
	 * @return el valor de condicion tarifa
	 */
	public CondicionTarifa getcondicionTarifa()
	{
		return ima_condicionTarifa;
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
			CondicionTarifa lma_parametros;

			lma_parametros = getcondicionTarifa();

			if(lma_parametros != null)
			{
				{
					String ls_condicionTarifa;
					ls_condicionTarifa     = lma_parametros.getIdCondicionTarifa();

					lb_focus = validateStyles(
						    ":fCondicionTarifaDetalle:idCondicionTarifa", lfc_context, ls_condicionTarifa, lb_focus
						);

					if(!StringUtils.isValidString(ls_condicionTarifa))
						throw new B2BException(ErrorKeys.ERROR_SIN_ID_CONDICION_TAFIRA);
				}

				{
					String ls_descripcion;
					ls_descripcion     = lma_parametros.getDescripcion();

					lb_focus = validateStyles(
						    ":fCondicionTarifaDetalle:idDescripcion", lfc_context, ls_descripcion, lb_focus
						);

					if(!StringUtils.isValidString(ls_descripcion))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_DESCRIPCION);
				}

				{
					String ls_estado;
					ls_estado     = lma_parametros.getActiva();

					lb_focus = validateStyles(":fCondicionTarifaDetalle:idActivo", lfc_context, ls_estado, lb_focus);

					if(!StringUtils.isValidString(ls_estado))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ACTIVO);
				}

				{
					String ls_codigoSql;
					ls_codigoSql     = lma_parametros.getCodigoSqlValidacion();

					lb_focus = validateStyles(
						    ":fCondicionTarifaDetalle:idCodigoSqlValidacion", lfc_context, ls_codigoSql, lb_focus
						);

					if(!StringUtils.isValidString(ls_codigoSql))
						throw new B2BException(ErrorKeys.ERROR_CODIGO);
				}

				ipr_parameterRemote.salvarCondicionTarifa(
				    lma_parametros, isInsertar(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

				setParametros(null);
				setcondicionTarifa(null);

				ls_result = NavegacionCommon.CONDICION_TARIFA;
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return ls_result;
	}

	/**
	 * Modifica el valor de condicion tarifa.
	 *
	 * @param ama_ma asigna el valor a la propiedad condicion tarifa
	 */
	public void setcondicionTarifa(CondicionTarifa ama_ma)
	{
		ima_condicionTarifa = ama_ma;
	}
}
