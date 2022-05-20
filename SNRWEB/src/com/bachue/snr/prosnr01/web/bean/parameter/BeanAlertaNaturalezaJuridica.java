package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.reference.ReferenceRemote;

import com.bachue.snr.prosnr01.model.sdb.pgn.AlertaNaturalezaJuridica;
import com.bachue.snr.prosnr01.model.sdb.png.NaturalezaJuridica;

import com.bachue.snr.prosnr01.web.bean.BaseBean;
import com.bachue.snr.prosnr01.web.util.FacesUtils;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase para el manejo de la capa web para Causal Aprobación Devolución.
 *
 * @author Sebastian Tafur
 */
@ManagedBean(name = "beanAlertaNaturalezaJuridica")
@SessionScoped
public class BeanAlertaNaturalezaJuridica extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 465090287696328832L;

	/** Propiedad ianj alerta naturaleza juridica. */
	private AlertaNaturalezaJuridica ianj_alertaNaturalezaJuridica;

	/** Propiedad ima parametros. */
	private AlertaNaturalezaJuridica ima_parametros;

	/** Propiedad icma datos auditoria. */
	private Collection<AlertaNaturalezaJuridica> icma_datosAuditoria;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad irr reference remote. */
	@EJB
	private ReferenceRemote irr_referenceRemote;

	/** Propiedad is id naturaleza juridica. */
	private String is_idNaturalezaJuridica;

	/** Propiedad is version. */
	private String is_version;

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
	 * @param acma_cma asigna el valor a la propiedad datos auditoria
	 */
	public void setDatosAuditoria(Collection<AlertaNaturalezaJuridica> acma_cma)
	{
		icma_datosAuditoria = acma_cma;
	}

	/**
	 * Retorna el valor de datos auditoria.
	 *
	 * @return el valor de datos auditoria
	 */
	public Collection<AlertaNaturalezaJuridica> getDatosAuditoria()
	{
		return icma_datosAuditoria;
	}

	/**
	 * Modifica el valor de id naturaleza juridica.
	 *
	 * @param idNaturalezaJuridica asigna el valor a la propiedad id naturaleza juridica
	 */
	public void setIdNaturalezaJuridica(String idNaturalezaJuridica)
	{
		this.is_idNaturalezaJuridica = idNaturalezaJuridica;
	}

	/**
	 * Retorna el valor de id naturaleza juridica.
	 *
	 * @return el valor de id naturaleza juridica
	 */
	public String getIdNaturalezaJuridica()
	{
		return is_idNaturalezaJuridica;
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
	public void setParametros(AlertaNaturalezaJuridica parametros)
	{
		ima_parametros = parametros;
	}

	/**
	 * Retorna el valor de parametros.
	 *
	 * @return el valor de parametros
	 */
	public AlertaNaturalezaJuridica getParametros()
	{
		if(ima_parametros == null)
			ima_parametros = new AlertaNaturalezaJuridica();

		return ima_parametros;
	}

	/**
	 * Modifica el valor de version.
	 *
	 * @param as_s asigna el valor a la propiedad version
	 */
	public void setVersion(String as_s)
	{
		is_version = as_s;
	}

	/**
	 * Retorna el valor de version.
	 *
	 * @return el valor de version
	 */
	public String getVersion()
	{
		return is_version;
	}

	/**
	 * Método para cambiar estado para saber si se desea insertar un nuevo
	 * Causal Aprobación Devolución.
	 */
	public void cambiarEstado()
	{
		setInsertar(true);
		setalertaNaturalezaJuridica(new AlertaNaturalezaJuridica());

		Boolean lb_parametro;
		lb_parametro = FacesUtils.getBooleanFacesParameter("pInsertarAlertaNaturalezaJuridica");

		if(lb_parametro != null)
			setInsertar(BooleanUtils.getBooleanValue(lb_parametro));
	}

	/**
	 * Método para consultar en la base de datos la tabla SDB_PGN_CAUSAL_APROBACION_DEVOLUCION.
	 *
	 * @param aanj_anj correspondiente al valor del tipo de objeto AlertaNaturalezaJuridica
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void consultaDetallada(AlertaNaturalezaJuridica aanj_anj)
	    throws B2BException
	{
		if(aanj_anj != null)
		{
			AlertaNaturalezaJuridica lcad_datos;

			lcad_datos = ipr_parameterRemote.findAlertaNaturalezaJuridicaById(aanj_anj);

			if(lcad_datos != null)
			{
				Collection<AlertaNaturalezaJuridica> lccad_ccad;
				lccad_ccad = new ArrayList<AlertaNaturalezaJuridica>();

				lccad_ccad.add(lcad_datos);
				setalertaNaturalezaJuridica(lcad_datos);

				setDatosAuditoria(lccad_ccad);
			}

			setInsertar(false);
		}
	}

	/**
	 * Método para encontrar todos los registros de la tabla SDB_PGN_CAUSAL_APROBACION_DEVOLUCION
	 * que coincida con un id específico.
	 */
	public void findAlertaNaturalezaJuridicaById()
	{
		try
		{
			AlertaNaturalezaJuridica lanj_parametros;
			lanj_parametros = getalertaNaturalezaJuridica();

			if(lanj_parametros != null)
			{
				if(StringUtils.isValidString(lanj_parametros.getIdNaturalezaJuridica()))
				{
					setParametros(ipr_parameterRemote.findAlertaNaturalezaJuridicaById(lanj_parametros));
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
	 *  Método para encontrar todos los registros de la tabla SDB_PGN_CAUSAL_APROBACION_DEVOLUCION.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<AlertaNaturalezaJuridica> findAllAlertaNaturalezaJuridica()
	{
		Collection<AlertaNaturalezaJuridica> lcr_constantes;
		lcr_constantes = null;

		try
		{
			lcr_constantes = ipr_parameterRemote.findAllAlertaNaturalezaJuridica();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcr_constantes;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<NaturalezaJuridica> findAllNaturalezaJuridica()
	{
		Collection<NaturalezaJuridica> lcnj_datos;
		lcnj_datos = null;

		try
		{
			lcnj_datos = irr_referenceRemote.findAllNaturalezaJuridica(false);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcnj_datos;
	}

	/**
	 * Método para encontrar todas las versiones para determinado Naturaleza jurídica.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<NaturalezaJuridica> findAllVersionByNaturalezaJuridica()
	{
		Collection<NaturalezaJuridica> lcnj_datos;
		lcnj_datos = null;

		AlertaNaturalezaJuridica lanj_alerta;
		lanj_alerta = getalertaNaturalezaJuridica();

		if(lanj_alerta != null)
		{
			try
			{
				String ls_idAlertaNat;
				ls_idAlertaNat     = lanj_alerta.getIdNaturalezaJuridica();
				lcnj_datos         = irr_referenceRemote.findAllVersionById(ls_idAlertaNat);
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);
			}
		}

		return lcnj_datos;
	}

	/**
	 * Retorna el valor de alerta naturaleza juridica.
	 *
	 * @return el valor de alerta naturaleza juridica
	 */
	public AlertaNaturalezaJuridica getalertaNaturalezaJuridica()
	{
		return ianj_alertaNaturalezaJuridica;
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
			AlertaNaturalezaJuridica lma_parametros;

			lma_parametros = getalertaNaturalezaJuridica();

			{
				String ls_idNaturalezaJuridica;
				ls_idNaturalezaJuridica     = lma_parametros.getIdNaturalezaJuridica();

				lb_focus = validateStyles(
					    ":fAlertaNaturalezaJuridicaDetalle:idNaturalezaJuridica", lfc_context, ls_idNaturalezaJuridica,
					    lb_focus
					);

				if(!StringUtils.isValidString(ls_idNaturalezaJuridica))
					throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_NATURALEZA_JURIDICA);
			}

			{
				String ls_version;

				ls_version     = getVersion();

				lb_focus = validateStyles(
					    ":fAlertaNaturalezaJuridicaDetalle:idVersion", lfc_context, ls_version, lb_focus
					);

				if(!StringUtils.isValidString(ls_version))
					throw new B2BException(ErrorKeys.DEBE_DIGITAR_VERSION);

				Long ll_version;
				ll_version     = NumericUtils.getLongWrapper(ls_version, -1);

				lb_focus = validateStyles(
					    ":fAlertaNaturalezaJuridicaDetalle:idVersion", lfc_context, ll_version, lb_focus
					);

				if(!NumericUtils.isValidLong(ll_version))
					throw new B2BException(ErrorKeys.DEBE_DIGITAR_VERSION);

				lma_parametros.setVersion(ll_version);
			}

			{
				String ls_codigo;
				ls_codigo     = lma_parametros.getNombreAlerta();

				lb_focus = validateStyles(
					    ":fAlertaNaturalezaJuridicaDetalle:idNombreAlerta", lfc_context, ls_codigo, lb_focus
					);

				if(!StringUtils.isValidString(ls_codigo))
					throw new B2BException(ErrorKeys.DEBE_DIGITAR_NOMBRE_ALERTA);
			}

			{
				String ls_estado;
				ls_estado     = lma_parametros.getActivo();

				lb_focus = validateStyles(
					    ":fAlertaNaturalezaJuridicaDetalle:idActivo", lfc_context, ls_estado, lb_focus
					);

				if(!StringUtils.isValidString(ls_estado))
					throw new B2BException(ErrorKeys.DEBE_DIGITAR_ACTIVO);
			}

			ipr_parameterRemote.salvarAlertaNaturalezaJuridica(
			    lma_parametros, isInsertar(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
			);

			setParametros(null);

			setalertaNaturalezaJuridica(null);

			ls_result = NavegacionCommon.ALERTA_NATURALEZA_JURIDICA;
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return ls_result;
	}

	/**
	 * Modifica el valor de alerta naturaleza juridica.
	 *
	 * @param ama_ma asigna el valor a la propiedad alerta naturaleza juridica
	 */
	public void setalertaNaturalezaJuridica(AlertaNaturalezaJuridica ama_ma)
	{
		ianj_alertaNaturalezaJuridica = ama_ma;
	}
}
