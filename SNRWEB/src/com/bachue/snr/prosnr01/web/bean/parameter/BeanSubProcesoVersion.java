package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.reference.ReferenceRemote;

import com.bachue.snr.prosnr01.model.sdb.acc.Proceso;
import com.bachue.snr.prosnr01.model.sdb.acc.SubprocesoVersion;

import com.bachue.snr.prosnr01.web.bean.BaseBean;
import com.bachue.snr.prosnr01.web.util.FacesUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;


/**
 * Clase que contiene todos las propiedades y acciones de BeanSubProcesoVersion.
 *
 * @author Sebastian Sanchez
 */
@ManagedBean(name = "beanSubProcesoVersion")
@SessionScoped
public class BeanSubProcesoVersion extends BaseBean
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 2706894528269065464L;

	/** Propiedad icsp datos auditoria. */
	private Collection<SubprocesoVersion> icsp_datosAuditoria;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad irr reference remote. */
	@EJB
	private ReferenceRemote irr_referenceRemote;

	/** Propiedad inj parametros. */
	private SubprocesoVersion icsp_parametros;

	/** Propiedad isp subproceso. */
	private SubprocesoVersion isp_subProceso;

	/** Propiedad ib insertar. */
	private boolean ib_insertar;

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * @param datosAuditoria asigna el valor a la propiedad
	 */
	public void setDatosAuditoria(Collection<SubprocesoVersion> datosAuditoria)
	{
		icsp_datosAuditoria = datosAuditoria;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<SubprocesoVersion> getDatosAuditoria()
	{
		return icsp_datosAuditoria;
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
	public void setParametros(SubprocesoVersion parametros)
	{
		icsp_parametros = parametros;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public SubprocesoVersion getParametros()
	{
		if(icsp_parametros == null)
			icsp_parametros = new SubprocesoVersion();

		return icsp_parametros;
	}

	/**
	 * @param asp_sp asigna el valor a la propiedad
	 */
	public void setSubProceso(SubprocesoVersion asp_sp)
	{
		isp_subProceso = asp_sp;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public SubprocesoVersion getSubProceso()
	{
		return isp_subProceso;
	}

	/**
	 * Método para cambiar estado para saber si se desea insertar un nuevo
	 * SubProceso
	 */
	public void cambiarEstado()
	{
		setInsertar(true);
		setSubProceso(new SubprocesoVersion());

		Boolean lb_parametro;

		lb_parametro = FacesUtils.getBooleanFacesParameter("pInsertarSubProcesoVersion");

		if(lb_parametro != null)
			setInsertar(BooleanUtils.getBooleanValue(lb_parametro));
	}

	/**
	 * Metodo para traer los procesos de la base de datos
	 *
	 * @return Colección de procesos resultante de la consulta
	 */
	public Collection<Proceso> cargarProcesosOrigen()
	{
		Collection<Proceso> lp_proceso;
		lp_proceso = new LinkedList<Proceso>();

		try
		{
			lp_proceso = irr_referenceRemote.findAllProcesosActivos(
				    getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lp_proceso;
	}

	/**
	 * Método para limpiar la pantalla
	 */
	public void clear()
	{
	}

	/**
	 * Método para consultar en la base de datos la tabla SDB_ACC_SUBPROCESO_VERSION
	 *
	 * @param asp_sp Objeto de tipo SubProcesoVersion
	 * @throws B2BException
	 */
	public void consultaDetallada(SubprocesoVersion asp_sp)
	    throws B2BException
	{
		if(asp_sp != null)
		{
			SubprocesoVersion lcsp_datos;

			lcsp_datos = ipr_parameterRemote.findSubProcesoVersionById(asp_sp);

			if(lcsp_datos != null)
			{
				Collection<SubprocesoVersion> lccsp_ccsp;

				lccsp_ccsp = new ArrayList<SubprocesoVersion>();

				lccsp_ccsp.add(lcsp_datos);
				setSubProceso(lcsp_datos);

				setDatosAuditoria(lccsp_ccsp);
			}

			setInsertar(false);
		}
	}

	/**
	 * Método para encontrar todos los registros de la tabla SDB_ACC_SUBPROCESO_VERSION
	 *
	 * @return Collection<SubprocesoVersion> Colección con objetos tipo SubprocesoVersion
	 */
	public Collection<SubprocesoVersion> findSubprocesosVersion()
	{
		Collection<SubprocesoVersion> lcsv_subProcesosVersion;
		lcsv_subProcesosVersion = null;

		try
		{
			lcsv_subProcesosVersion = irr_referenceRemote.findAllSubprocesosVersion(
				    getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcsv_subProcesosVersion;
	}

	/**
	 * Método para salvar la inserción o actualización
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
			SubprocesoVersion ls_parametros;

			ls_parametros = getSubProceso();

			if(ls_parametros != null)
			{
				{
					String ls_idProceso;

					ls_idProceso     = ls_parametros.getIdProceso();

					lb_focus = validateStyles(
						    ":fSubProcesoVersionDetalle:idProceso", lfc_context, ls_idProceso, lb_focus
						);

					if(!StringUtils.isValidString(ls_idProceso))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_PROCESO);
				}

				{
					String ls_idSubProceso;

					ls_idSubProceso     = ls_parametros.getIdSubproceso();

					lb_focus = validateStyles(
						    ":fSubProcesoVersionDetalle:idSubproceso", lfc_context, ls_idSubProceso, lb_focus
						);

					if(!StringUtils.isValidString(ls_idSubProceso))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_SUBPROCESO);
				}

				{
					Long ll_versionSubproceso;

					ll_versionSubproceso     = ls_parametros.getVersionSubproceso();

					lb_focus = validateStyles(
						    ":fSubProcesoVersionDetalle:idVersion", lfc_context, ll_versionSubproceso, lb_focus
						);

					if(!NumericUtils.isValidLong(ll_versionSubproceso))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_NUMERO_VERSION);
				}

				ipr_parameterRemote.salvarSubprocesoVersion(
				    ls_parametros, isInsertar(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

				setParametros(null);
				setSubProceso(null);

				ls_result = NavegacionCommon.SUBPROCESO_VERSION;

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
			addMessage(lb2be_e);
		}

		return ls_result;
	}
}
