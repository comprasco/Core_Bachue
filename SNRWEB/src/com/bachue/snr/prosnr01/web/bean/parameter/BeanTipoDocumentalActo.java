package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.reference.ReferenceRemote;

import com.bachue.snr.prosnr01.model.registro.TipoDocumentalActo;
import com.bachue.snr.prosnr01.model.sdb.acc.Proceso;
import com.bachue.snr.prosnr01.model.sdb.acc.Subproceso;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoActo;

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
 * Clase que contiene todos las propiedades y acciones de BeanTipoDocumental.
 *
 * @author Duvan Beltran
 */
@ManagedBean(name = "beanTipoDocumentalActo")
@SessionScoped
public class BeanTipoDocumentalActo extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 298936945875723070L;

	/** Propiedad icta tipo acto versiones. */
	private Collection<TipoActo> icta_tipoActoVersiones;

	/** Propiedad ictap datos auditoria. */
	private Collection<TipoDocumentalActo> ictap_datosAuditoria;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad irr reference remote. */
	@EJB
	private ReferenceRemote irr_referenceRemote;

	/** Propiedad itda tipo documental acto. */
	private TipoDocumentalActo itda_tipoDocumentalActo;

	/** Propiedad ib insertar. */
	private boolean ib_insertar;

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * @param actda_datosAuditoria asigna el valor a la propiedad
	 */
	public void setDatosAuditoria(Collection<TipoDocumentalActo> actda_datosAuditoria)
	{
		ictap_datosAuditoria = actda_datosAuditoria;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<TipoDocumentalActo> getDatosAuditoria()
	{
		return ictap_datosAuditoria;
	}

	/**
	 * @param ab_insertar asigna el valor a la propiedad
	 */
	public void setInsertar(boolean ab_insertar)
	{
		ib_insertar = ab_insertar;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isInsertar()
	{
		return ib_insertar;
	}

	/**
	 * @param acta_tipoActoVersiones asigna el valor a la propiedad
	 */
	public void setTipoActoVersiones(Collection<TipoActo> acta_tipoActoVersiones)
	{
		icta_tipoActoVersiones = acta_tipoActoVersiones;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<TipoActo> getTipoActoVersiones()
	{
		return icta_tipoActoVersiones;
	}

	/**
	 * @param atda_tipoDocumentalActo asigna el valor a la propiedad
	 */
	public void setTipoDocumentalActo(TipoDocumentalActo atda_tipoDocumentalActo)
	{
		itda_tipoDocumentalActo = atda_tipoDocumentalActo;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public TipoDocumentalActo getTipoDocumentalActo()
	{
		return itda_tipoDocumentalActo;
	}

	/**
	 * Método para cambiar estado para saber si se desea insertar un nuevo
	 * Tipo Documental Acto
	 */
	public void cambiarEstado()
	{
		setInsertar(true);
		setTipoDocumentalActo((new TipoDocumentalActo()));

		Boolean lb_parametro;
		lb_parametro = FacesUtils.getBooleanFacesParameter("pInsertarTipoDocumentalActo");

		if(lb_parametro != null)
			setInsertar(BooleanUtils.getBooleanValue(lb_parametro));
	}

	/**
	 * Método para limpiar la pantalla
	 */
	public void clean()
	{
		setTipoActoVersiones(null);
		setDatosAuditoria(null);
		setInsertar(false);
	}

	/**
	 * Método de consulta detallada de tipo acto prohibición por medio del indicativo de los datos solicitados
	 * @param atd_td Indicativo  del registro solicitado
	 * @throws B2BException
	 */
	public void consultaDetallada(TipoDocumentalActo atd_td)
	    throws B2BException
	{
		if(atd_td != null)
		{
			long ls_idTipoDocumentalActo;

			ls_idTipoDocumentalActo     = atd_td.getIdTipoDocumentalActo();

			atd_td = ipr_parameterRemote.findAllTipoDocumentalActoById(ls_idTipoDocumentalActo);

			if(atd_td != null)
			{
				Collection<TipoDocumentalActo> lctd_td;

				lctd_td = new ArrayList<TipoDocumentalActo>();

				lctd_td.add(atd_td);
				setTipoDocumentalActo(atd_td);

				setDatosAuditoria(lctd_td);
			}

			setInsertar(false);
		}
	}

	/**
	 * Método de consulta para encontrar todos los procesos
	 * @return una colección de procesos de tipo proceso con los datos solicitados.
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
	 * Método de consulta para encontrar todos los subprocesos de un proceso
	 * @param as_idProceso el indicativo del proceso
	 * @return Una colección de subproceso con la información solicitada
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
	 * Método de consulta para encontrar todos los tipo documental acto
	 * @return una colección de tipo documnetal acto con los datos solicitados
	 */
	public Collection<TipoDocumentalActo> findAllTipoDocumentalActo()
	{
		Collection<TipoDocumentalActo> lccc_cc;
		lccc_cc = null;

		try
		{
			lccc_cc = ipr_parameterRemote.findAllTipoDocumentalActo();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lccc_cc;
	}

	/**
	 * Método de coonsulta para encontrar el tipo acto por medio de su indicativo
	 */
	public void findTipoActoById()
	{
		try
		{
			TipoDocumentalActo ltap_tipoActoProhibicion;

			ltap_tipoActoProhibicion = getTipoDocumentalActo();

			if(ltap_tipoActoProhibicion != null)
			{
				Collection<TipoActo> lcta_cllTipoActo;

				lcta_cllTipoActo = irr_referenceRemote.findTipoActoById(ltap_tipoActoProhibicion.getIdTipoActo());

				if(CollectionUtils.isValidCollection(lcta_cllTipoActo))
					setTipoActoVersiones(lcta_cllTipoActo);
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}
	}

	/**
	 * Método para salvar la inserción o actualización
	 *
	 * @return string con los datos
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
			TipoDocumentalActo ltap_tipoDocumentalActo;

			ltap_tipoDocumentalActo = getTipoDocumentalActo();

			if(ltap_tipoDocumentalActo != null)
			{
				{
					String ls_tipoDocumental;

					ls_tipoDocumental     = ltap_tipoDocumentalActo.getIdTipoDocumental();

					lb_focus = validateStyles(
						    ":fTipoDocumentalActoDetalle:idTipoDocumental", lfc_context, ls_tipoDocumental, lb_focus
						);

					if(!StringUtils.isValidString(ls_tipoDocumental))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO_DOCUMENTAL);
				}

				{
					String ls_obligatorioo;

					ls_obligatorioo     = ltap_tipoDocumentalActo.getObligatorio();

					lb_focus = validateStyles(
						    ":fTipoDocumentalActoDetalle:idSelectobligatorio", lfc_context, ls_obligatorioo, lb_focus
						);

					if(!StringUtils.isValidString(ls_obligatorioo))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_OBLIGATORIO);
				}

				ipr_parameterRemote.salvarTipoDocumentalActo(
				    ltap_tipoDocumentalActo, isInsertar(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);
				//setTipoDocumentalActo(null);
				ls_result = NavegacionCommon.TIPO_DOCUMENTAL_ACTO;
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update("fTipoDocumentalActoDetalle:globalMsg");
		}

		return ls_result;
	}
}
