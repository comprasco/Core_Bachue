package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.reference.ReferenceRemote;

import com.bachue.snr.prosnr01.model.sdb.acc.Proceso;
import com.bachue.snr.prosnr01.model.sdb.acc.Subproceso;
import com.bachue.snr.prosnr01.model.sdb.pgn.MotivoTramite;

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
 * Clase para el manejo de la capa web para Motivo Tramite.
 *
 * @author Sebastian Tafur
 */
@ManagedBean(name = "beanMotivoTramite")
@SessionScoped
public class BeanMotivoTramite extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 5486536522831184683L;

	/** Propiedad icmt datos auditoria. */
	private Collection<MotivoTramite> icmt_datosAuditoria;

	/** Propiedad imt motivo tramite. */
	private MotivoTramite imt_motivoTramite;

	/** Propiedad imt parametros. */
	private MotivoTramite imt_parametros;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad irr reference remote. */
	@EJB
	private ReferenceRemote irr_referenceRemote;

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
	 * @param datosAuditoria asigna el valor a la propiedad datos auditoria
	 */
	public void setDatosAuditoria(Collection<MotivoTramite> datosAuditoria)
	{
		icmt_datosAuditoria = datosAuditoria;
	}

	/**
	 * Retorna el valor de datos auditoria.
	 *
	 * @return el valor de datos auditoria
	 */
	public Collection<MotivoTramite> getDatosAuditoria()
	{
		return icmt_datosAuditoria;
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
	 * Modifica el valor de motivo tramite.
	 *
	 * @param amt_mt asigna el valor a la propiedad motivo tramite
	 */
	public void setMotivoTramite(MotivoTramite amt_mt)
	{
		imt_motivoTramite = amt_mt;
	}

	/**
	 * Retorna el valor de motivo tramite.
	 *
	 * @return el valor de motivo tramite
	 */
	public MotivoTramite getMotivoTramite()
	{
		if(imt_motivoTramite == null)
			imt_motivoTramite = new MotivoTramite();

		return imt_motivoTramite;
	}

	/**
	 * Modifica el valor de parametros.
	 *
	 * @param amt_mt asigna el valor a la propiedad parametros
	 */
	public void setParametros(MotivoTramite amt_mt)
	{
		imt_parametros = amt_mt;
	}

	/**
	 * Retorna el valor de parametros.
	 *
	 * @return el valor de parametros
	 */
	public MotivoTramite getParametros()
	{
		if(imt_parametros == null)
			imt_parametros = new MotivoTramite();

		return imt_parametros;
	}

	/**
	 * Método para cambiar estado para saber si se desea insertar un nuevo
	 * Motivo Trámite.
	 *
	 * @return devuelve el valor de String
	 */
	public String cambiarEstado()
	{
		setInsertar(true);
		setMotivoTramite(new MotivoTramite());

		Boolean lb_parametro;
		lb_parametro = FacesUtils.getBooleanFacesParameter("pInsertarCausalAprobacionDevolucion");

		if(lb_parametro != null)
			setInsertar(BooleanUtils.getBooleanValue(lb_parametro));

		return NavegacionCommon.MOTIVO_TRAMITE_DETALLE;
	}

	/**
	 * Método para consultar en la base de datos la tabla SDB_PGN_MOTIVO_TRAMITE.
	 *
	 * @param acad_cad correspondiente al valor del tipo de objeto MotivoTramite
	 * @return devuelve el valor de String
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public String consultaDetallada(MotivoTramite acad_cad)
	    throws B2BException
	{
		String ls_return;
		ls_return = NavegacionCommon.MOTIVO_TRAMITE_DETALLE;

		try
		{
			if(acad_cad != null)
			{
				MotivoTramite lcad_datos;
				String        ls_idMotivoTramite;

				ls_idMotivoTramite = acad_cad.getIdMotivoTramite();

				if(StringUtils.isValidString(ls_idMotivoTramite))
				{
					lcad_datos = ipr_parameterRemote.findMotivoTramiteById(ls_idMotivoTramite);

					if(lcad_datos != null)
					{
						Collection<MotivoTramite> lccad_ccad;
						lccad_ccad = new ArrayList<MotivoTramite>();

						lccad_ccad.add(lcad_datos);
						setMotivoTramite(lcad_datos);

						setDatosAuditoria(lccad_ccad);
					}

					setInsertar(false);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			ls_return = null;
		}

		return ls_return;
	}

	/**
	 *  Método para encontrar todos los registros de la tabla SDB_PGN_MOTIVO_TRAMITE.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<MotivoTramite> findAllMotivoTramite()
	{
		Collection<MotivoTramite> lcr_constantes;

		lcr_constantes = null;

		try
		{
			lcr_constantes = ipr_parameterRemote.findAllMotivoTramite();
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
	public Collection<MotivoTramite> findAllMotivoTramiteByEtapa()
	{
		Collection<MotivoTramite> lcr_constantes;

		lcr_constantes = null;

		try
		{
			lcr_constantes = ipr_parameterRemote.findEtapaById();
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
	 * @param as_idProceso correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de Collection
	 */
	public Collection<Subproceso> findAllSubprocesos(String as_idProceso)
	{
		Collection<Subproceso> lcs_datos;
		lcs_datos = null;

		try
		{
			if(StringUtils.isValidString(as_idProceso))
			{
				Subproceso ls_subproceso;

				ls_subproceso = new Subproceso();

				ls_subproceso.setIdProceso(as_idProceso);

				lcs_datos = irr_referenceRemote.findSubprocesosByProceso(
					    ls_subproceso, false, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcs_datos;
	}

	/**
	 * Método para salvar la inserción o actualización para la tabla SDB_PGN_MOTIVO_TRAMITE.
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
			MotivoTramite lr_parametros;

			lr_parametros = getMotivoTramite();

			{
				long ll_etapa;

				ll_etapa     = lr_parametros.getIdEtapaOrigen();

				lb_focus = validateStyles(
					    ":fMotivoTramiteDetalle:idEtapa", lfc_context, NumericUtils.getLongWrapper(ll_etapa), lb_focus
					);

				if(ll_etapa <= 0)
				{
					lb_focus = validateStyles(":fMotivoTramiteDetalle:idEtapa", lfc_context, "", lb_focus);
					throw new B2BException(ErrorKeys.ERROR_ETAPA_INVALIDA);
				}
			}

			{
				String ls_idProceso;

				ls_idProceso     = lr_parametros.getIdProceso();

				lb_focus = validateStyles(
					    ":fMotivoTramiteDetalle:idSOMProceso", lfc_context, NumericUtils.getLongWrapper(ls_idProceso),
					    lb_focus
					);

				if(!StringUtils.isValidString(ls_idProceso))
				{
					lb_focus = validateStyles(":fMotivoTramiteDetalle:idSOMProceso", lfc_context, "", lb_focus);
					throw new B2BException(ErrorKeys.ERROR_DEBE_SELECCIONAR_ID_PROCESO);
				}
			}

			{
				String ls_idSubProceso;

				ls_idSubProceso     = lr_parametros.getIdSubproceso();

				lb_focus = validateStyles(
					    ":fMotivoTramiteDetalle:idSOMSubproceso", lfc_context,
					    NumericUtils.getLongWrapper(ls_idSubProceso), lb_focus
					);

				if(!StringUtils.isValidString(ls_idSubProceso))
				{
					lb_focus = validateStyles(":fMotivoTramiteDetalle:idSOMSubproceso", lfc_context, "", lb_focus);
					throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_SUBPROCESO);
				}
			}

			{
				Long ll_version;

				ll_version     = lr_parametros.getVersionSubproceso();

				lb_focus = validateStyles(":fMotivoTramiteDetalle:idVersion", lfc_context, ll_version, lb_focus);

				if(!NumericUtils.isValidLong(ll_version) || (NumericUtils.getLong(ll_version) == 0L))
					throw new B2BException(ErrorKeys.DEBE_DIGITAR_NUMERO_VERSION_DOCUMENTAL);
			}

			{
				Long ll_idMotivo;

				ll_idMotivo     = NumericUtils.getLongWrapper(lr_parametros.getIdMotivo());

				lb_focus = validateStyles(":fMotivoTramiteDetalle:idMotivo", lfc_context, ll_idMotivo, lb_focus);

				if(!NumericUtils.isValidLong(ll_idMotivo) || (NumericUtils.getLong(ll_idMotivo) == 0L))
					throw new B2BException(ErrorKeys.ERROR_MOTIVO);
			}

			{
				String ls_descripcion;

				ls_descripcion     = lr_parametros.getDescripcion();

				lb_focus = validateStyles(
					    ":fMotivoTramiteDetalle:idDescripcion", lfc_context, ls_descripcion, lb_focus
					);

				if(!StringUtils.isValidString(ls_descripcion))
					throw new B2BException(ErrorKeys.DEBE_DIGITAR_DESCRIPCION);
			}

			{
				String ls_estado;

				ls_estado     = lr_parametros.getEstado();

				lb_focus = validateStyles(":fMotivoTramiteDetalle:idEstado", lfc_context, ls_estado, lb_focus);

				if(!StringUtils.isValidString(ls_estado))
					throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ESTADO);
			}

			{
				String ls_idEtapaPosterior;

				ls_idEtapaPosterior     = StringUtils.getString(lr_parametros.getIdEtapaPosterior());

				lb_focus = validateStyles(
					    ":fMotivoTramiteDetalle:idEtapaPosterior", lfc_context, ls_idEtapaPosterior, lb_focus
					);

				if(!StringUtils.isValidString(ls_idEtapaPosterior))
					throw new B2BException(ErrorKeys.ERROR_ETAPA_POSTERIOR);
			}

			{
				String ls_estadoActividad;

				ls_estadoActividad     = lr_parametros.getEstadoActividad();

				lb_focus = validateStyles(
					    ":fMotivoTramiteDetalle:idEstadoActividad", lfc_context, ls_estadoActividad, lb_focus
					);

				if(!StringUtils.isValidString(ls_estadoActividad))
					throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ESTADO);
			}

			ipr_parameterRemote.salvarMotivoTramite(
			    lr_parametros, isInsertar(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
			);

			setParametros(null);

			setMotivoTramite(null);

			ls_result = NavegacionCommon.MOTIVO_TRAMITE;
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return ls_result;
	}
}
