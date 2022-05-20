package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.reference.ReferenceRemote;

import com.bachue.snr.prosnr01.model.sdb.acc.Proceso;
import com.bachue.snr.prosnr01.model.sdb.acc.Subproceso;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoRequiereMatricula;

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
 * Clase para el manejo de la capa web para Tipo requiere matricula.
 *
 * @author Luis Chacón
 */
@ManagedBean(name = "beanTipoRequiereMatricula")
@SessionScoped
public class BeanTipoRequiereMatricula extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 8502702683559427421L;

	/** Propiedad itrm datos auditoria. */
	private Collection<TipoRequiereMatricula> itrm_datosAuditoria;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad irr reference remote. */
	@EJB
	private ReferenceRemote irr_referenceRemote;

	/** Propiedad itrm parametros. */
	private TipoRequiereMatricula itrm_parametros;

	/** Propiedad itrm tipo requiere matricula. */
	private TipoRequiereMatricula itrm_tipoRequiereMatricula;

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
	 * @param ac_c asigna el valor a la propiedad datos auditoria
	 */
	public void setDatosAuditoria(Collection<TipoRequiereMatricula> ac_c)
	{
		itrm_datosAuditoria = ac_c;
	}

	/**
	 * Retorna el valor de datos auditoria.
	 *
	 * @return el valor de datos auditoria
	 */
	public Collection<TipoRequiereMatricula> getDatosAuditoria()
	{
		return itrm_datosAuditoria;
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
	 * @param atrm_param asigna el valor a la propiedad parametros
	 */
	public void setParametros(TipoRequiereMatricula atrm_param)
	{
		itrm_parametros = atrm_param;
	}

	/**
	 * Retorna el valor de parametros.
	 *
	 * @return el valor de parametros
	 */
	public TipoRequiereMatricula getParametros()
	{
		if(itrm_parametros == null)
			itrm_parametros = new TipoRequiereMatricula();

		return itrm_parametros;
	}

	/**
	 * Modifica el valor de tipo requiere matricula.
	 *
	 * @param at_t asigna el valor a la propiedad tipo requiere matricula
	 */
	public void setTipoRequiereMatricula(TipoRequiereMatricula at_t)
	{
		itrm_tipoRequiereMatricula = at_t;
	}

	/**
	 * Retorna el valor de tipo requiere matricula.
	 *
	 * @return el valor de tipo requiere matricula
	 */
	public TipoRequiereMatricula getTipoRequiereMatricula()
	{
		return itrm_tipoRequiereMatricula;
	}

	/**
	 * Método para cambiar estado para saber si se desea insertar un nuevo
	 * Tipo requiere matricula.
	 */
	public void cambiarEstado()
	{
		setInsertar(true);
		setTipoRequiereMatricula(new TipoRequiereMatricula());

		Boolean lb_parametro;
		lb_parametro = FacesUtils.getBooleanFacesParameter("pInsertarTipoRequiereMatricula");

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
	 * Método para consultar en la base de datos la tabla SDB_PGN_TIPO_REQUIERE_MATRICULA.
	 *
	 * @param trm_trm correspondiente al valor del tipo de objeto TipoRequiereMatricula
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void consultaDetallada(TipoRequiereMatricula trm_trm)
	    throws B2BException
	{
		if(trm_trm != null)
		{
			TipoRequiereMatricula len_dato;

			len_dato     = null;

			len_dato = ipr_parameterRemote.findTipoRequiereMatriculaById(trm_trm);

			if(len_dato != null)
			{
				Collection<TipoRequiereMatricula> lctrm_trm;
				lctrm_trm = new ArrayList<TipoRequiereMatricula>();

				lctrm_trm.add(len_dato);
				setTipoRequiereMatricula(len_dato);

				setDatosAuditoria(lctrm_trm);
			}

			setInsertar(false);
		}
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<Proceso> findAllProcesos()
	{
		Collection<Proceso> lcp_datos;
		lcp_datos = null;

		try
		{
			lcp_datos = irr_referenceRemote.findAllProcesosActivos(
				    getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcp_datos;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<Subproceso> findAllSubprocesos()
	{
		Collection<Subproceso> lcs_datos;
		lcs_datos = null;

		try
		{
			lcs_datos = irr_referenceRemote.findSubprocesos(true);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcs_datos;
	}

	/**
	 *  Método para encontrar todos los registros de la tabla SDB_PGN_TIPO_REQUIERE_MATRICULA.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<TipoRequiereMatricula> findAllTipoRequiereMatricula()
	{
		Collection<TipoRequiereMatricula> ltrm_trm;
		ltrm_trm = null;

		try
		{
			ltrm_trm = ipr_parameterRemote.findAllTipoRequiereMatricula();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return ltrm_trm;
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
			TipoRequiereMatricula len_parametros;

			len_parametros = getTipoRequiereMatricula();

			if(len_parametros != null)
			{
				{
					String ls_idTipoRequiereMatricula;
					ls_idTipoRequiereMatricula     = len_parametros.getIdTipoRequiereMatricula();

					lb_focus = validateStyles(
						    ":fTipoRequiereMatriculaDetalle:idTipoRequiereMatricula", lfc_context,
						    ls_idTipoRequiereMatricula, lb_focus
						);

					if(!StringUtils.isValidString(ls_idTipoRequiereMatricula))
						throw new B2BException(ErrorKeys.ERROR_SIN_ID_ESTADO_PREDIO);
				}

				{
					String ls_idProceso;
					ls_idProceso     = len_parametros.getIdProceso();

					lb_focus = validateStyles(
						    ":fTipoRequiereMatriculaDetalle:idProceso", lfc_context, ls_idProceso, lb_focus
						);

					if(!StringUtils.isValidString(ls_idProceso))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_PROCESO);
				}

				{
					String ls_descripcion;
					ls_descripcion     = len_parametros.getDescripcion();

					lb_focus = validateStyles(
						    ":fTipoRequiereMatriculaDetalle:idDescripcion", lfc_context, ls_descripcion, lb_focus
						);

					if(!StringUtils.isValidString(ls_descripcion))
						throw new B2BException(ErrorKeys.ERROR_DESCRIPCION);
				}

				{
					String ls_idProceso;
					ls_idProceso     = len_parametros.getIdProceso();

					lb_focus = validateStyles(
						    ":fTipoRequiereMatriculaDetalle:idSubproceso", lfc_context, ls_idProceso, lb_focus
						);

					if(!StringUtils.isValidString(ls_idProceso))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_SUBPROCESO);
				}

				ipr_parameterRemote.salvarTipoRequiereMatricula(
				    len_parametros, isInsertar(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

				getBeanReference().setRequiereMatriculaActivos(null);

				setParametros(null);
				setTipoRequiereMatricula(null);

				ls_result = NavegacionCommon.TIPO_REQUIERE_MATRICULA;
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return ls_result;
	}
}
