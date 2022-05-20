package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.reference.ReferenceRemote;

import com.bachue.snr.prosnr01.model.sdb.acc.Proceso;
import com.bachue.snr.prosnr01.model.sdb.acc.Subproceso;
import com.bachue.snr.prosnr01.model.sdb.pgn.ProcesoConsulta;

import com.bachue.snr.prosnr01.web.bean.BaseBean;
import com.bachue.snr.prosnr01.web.util.FacesUtils;

import org.primefaces.PrimeFaces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase que contiene todos las propiedades BeanProcesoConsulta.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 31/07/2020
 */
@ManagedBean(name = "beanProcesoConsulta")
@SessionScoped
public class BeanProcesoConsulta extends BaseBean
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1702238349907544937L;

	/** Propiedad icpc datos auditoria. */
	private Collection<ProcesoConsulta> icpc_datosAuditoria;

	/** Propiedad icsp sub proceso. */
	private Collection<Subproceso> icsp_subProceso;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad icpc parametros. */
	private ProcesoConsulta icpc_parametros;

	/** Propiedad ipc proceso consulta. */
	private ProcesoConsulta ipc_procesoConsulta;

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
	 * Modifica el valor de DatosAuditoria.
	 *
	 * @param datosAuditoria de datos auditoria
	 */
	public void setDatosAuditoria(Collection<ProcesoConsulta> datosAuditoria)
	{
		icpc_datosAuditoria = datosAuditoria;
	}

	/**
	 * Retorna Objeto o variable de valor datos auditoria.
	 *
	 * @return el valor de datos auditoria
	 */
	public Collection<ProcesoConsulta> getDatosAuditoria()
	{
		return icpc_datosAuditoria;
	}

	/**
	 * Modifica el valor de Insertar.
	 *
	 * @param ab_b de ab b
	 */
	public void setInsertar(boolean ab_b)
	{
		ib_insertar = ab_b;
	}

	/**
	 * Valida la propiedad insertar.
	 *
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code> en insertar
	 */
	public boolean isInsertar()
	{
		return ib_insertar;
	}

	/**
	 * Modifica el valor de Parametros.
	 *
	 * @param parametros de parametros
	 */
	public void setParametros(ProcesoConsulta parametros)
	{
		icpc_parametros = parametros;
	}

	/**
	 * Retorna Objeto o variable de valor parametros.
	 *
	 * @return el valor de parametros
	 */
	public ProcesoConsulta getParametros()
	{
		if(icpc_parametros == null)
			icpc_parametros = new ProcesoConsulta();

		return icpc_parametros;
	}

	/**
	 * Modifica el valor de ProcesoConsulta.
	 *
	 * @param apc_pc de apc pc
	 */
	public void setProcesoConsulta(ProcesoConsulta apc_pc)
	{
		ipc_procesoConsulta = apc_pc;
	}

	/**
	 * Retorna Objeto o variable de valor proceso consulta.
	 *
	 * @return el valor de proceso consulta
	 */
	public ProcesoConsulta getProcesoConsulta()
	{
		return ipc_procesoConsulta;
	}

	/**
	 * Modifica el valor de Subproceso.
	 *
	 * @param acsp_csp de acsp csp
	 */
	public void setSubproceso(Collection<Subproceso> acsp_csp)
	{
		icsp_subProceso = acsp_csp;
	}

	/**
	 * Retorna Objeto o variable de valor subproceso.
	 *
	 * @return el valor de subproceso
	 */
	public Collection<Subproceso> getSubproceso()
	{
		return icsp_subProceso;
	}

	/**
	 * Método para cambiar estado para saber si se desea insertar una nueva
	 * Proceso Consulta.
	 */
	public void cambiarEstado()
	{
		setInsertar(true);
		setProcesoConsulta(new ProcesoConsulta());

		Boolean lb_parametro;
		lb_parametro = FacesUtils.getBooleanFacesParameter("pInsertarProcesoConsulta");

		if(lb_parametro != null)
			setInsertar(BooleanUtils.getBooleanValue(lb_parametro));
	}

	/**
	 * Metodo para traer los procesos de la base de datos.
	 *
	 * @return Colección de subprocesos resultante de la consulta
	 */
	public Collection<Subproceso> cargarSubProcesosOrigen()
	{
		Collection<Subproceso> lp_subProceso;
		lp_subProceso = new LinkedList<Subproceso>();

		try
		{
			lp_subProceso = irr_referenceRemote.findAllSubProcesosActivos(
				    getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lp_subProceso;
	}

	/**
	 * Método para limpiar la pantalla.
	 */
	public void clear()
	{
	}

	/**
	 * Método para consultar en la base de datos la tabla SDB_PGN_PROCESO_CONSULTA.
	 *
	 * @param apc_pc de apc pc
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void consultaDetallada(ProcesoConsulta apc_pc)
	    throws B2BException
	{
		if(apc_pc != null)
		{
			ProcesoConsulta lcpc_datos;
			lcpc_datos = ipr_parameterRemote.findProcesoConsultaById(apc_pc);

			if(lcpc_datos != null)
			{
				Collection<ProcesoConsulta> lcccr_cccr;
				lcccr_cccr = new ArrayList<ProcesoConsulta>();

				lcccr_cccr.add(lcpc_datos);
				setProcesoConsulta(lcpc_datos);

				setDatosAuditoria(lcccr_cccr);
			}

			setInsertar(false);
		}
	}

	/**
	 * Método para encontrar todos los procesos de la base de datos.
	 *
	 * @return el valor de collection
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
	 * Encuentra todos subprocesos en la base de datos.
	 *
	 * @param as_idProceso de as id proceso
	 * @return el valor de collection
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
	 *  Método para encontrar todos los registros de la tabla SDB_PGN_PROCESO_CONSULTA.
	 *
	 * @return el valor de collection
	 */
	public Collection<ProcesoConsulta> findProcesoConsulta()
	{
		Collection<ProcesoConsulta> lcpc_constantes;
		lcpc_constantes = null;

		try
		{
			lcpc_constantes = ipr_parameterRemote.findAllProcesoConsulta();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcpc_constantes;
	}

	/**
	 * Método para salvar la inserción o actualización.
	 */
	public void salvar()
	{
		boolean      lb_focus;
		FacesContext lfc_context;

		lb_focus        = true;
		lfc_context     = FacesContext.getCurrentInstance();

		try
		{
			ProcesoConsulta lpc_parametros;

			lpc_parametros = getProcesoConsulta();

			{
				String ls_idProceso;
				ls_idProceso     = lpc_parametros.getIdProceso();

				lb_focus = validateStyles(
					    ":fProcesoConsultaDetalle:idProcesoCons", lfc_context, ls_idProceso, lb_focus
					);

				if(!StringUtils.isValidString(ls_idProceso))
					throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_PROCESO);
			}

			{
				String ls_idSubProceso;
				ls_idSubProceso     = lpc_parametros.getIdSubproceso();

				lb_focus = validateStyles(
					    ":fProcesoConsultaDetalle:idSubprocesoCons", lfc_context, ls_idSubProceso, lb_focus
					);

				if(!StringUtils.isValidString(ls_idSubProceso))
					throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_SUBPROCESO);
			}

			{
				String ls_sentenciaSql;
				ls_sentenciaSql     = lpc_parametros.getSentenciaSql();

				lb_focus = validateStyles(
					    ":fProcesoConsultaDetalle:sentenciaSql", lfc_context, ls_sentenciaSql, lb_focus
					);

				if(!StringUtils.isValidString(ls_sentenciaSql))
					throw new B2BException(ErrorKeys.DEBE_DIGITAR_SENTENCIA_SQL);
			}

			{
				String ls_activo;
				ls_activo     = lpc_parametros.getActivo();

				lb_focus = validateStyles(":fProcesoConsultaDetalle:idActivo", lfc_context, ls_activo, lb_focus);

				if(!StringUtils.isValidString(ls_activo))
					throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ACTIVO);
			}

			ipr_parameterRemote.salvarProcesoConsulta(
			    lpc_parametros, isInsertar(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
			);

			setParametros(null);

			addMessage(MessagesKeys.PROCESO_COMPLETADO);
			PrimeFaces.current().ajax().update("fProcesoConsultanDetalle:globalMsg");
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update("fProcesoConsultaDetalle:globalMsg");
		}
	}
}
