package com.bachue.snr.prosnr01.web.bean.cuentaCupos;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.cuentaCupos.CuentaCuposRemote;

import com.bachue.snr.prosnr01.model.cuentaCupos.AprobacionCuentaCupos;
import com.bachue.snr.prosnr01.model.sdb.acc.CuentaCupo;

import org.primefaces.PrimeFaces;

import org.primefaces.event.FileUploadEvent;

import java.util.Map;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase que contiene todos las propiedades BeanCuentaCuposInactivacion.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 8/09/2020
 */
@SessionScoped
@ManagedBean(name = "beanCuentaCuposInactivacion")
public class BeanCuentaCuposInactivacion extends BeanCuentaCupos
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 7606078588982914094L;

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanCuentaCuposInactivacion.class);

	/** Constante is_idForm. */
	private static final String is_idForm = "fCuentaCuposInactivacion";

	/** Constante is_messageIdGrowl. */
	private static final String is_messageIdGrowl = "fCuentaCuposInactivacion:idGrowl";

	/** Propiedad cuenta cupo. */
	private CuentaCupo icc_cuentaCupo;

	/** Propiedad iccr cuenta cupos remote. */
	@EJB
	private CuentaCuposRemote iccr_cuentaCuposRemote;

	/** Propiedad is id cuenta cupo. */
	private String is_idCuentaCupo;

	/** Propiedad justificacion. */
	private String justificacion;

	/** Propiedad ib consulta exitosa. */
	private boolean ib_consultaExitosa;

	/**
	 *  {@inheritdoc}.
	 *
	 * @return el valor de application
	 */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * Retorna Objeto o variable de valor id cuenta cupo.
	 *
	 * @return el valor de id cuenta cupo
	 */
	public String getIdCuentaCupo()
	{
		return is_idCuentaCupo;
	}

	/**
	 * Modifica el valor de IdCuentaCupo.
	 *
	 * @param as_s de as s
	 */
	public void setIdCuentaCupo(String as_s)
	{
		is_idCuentaCupo = as_s;
	}

	/**
	 * Valida la propiedad consulta exitosa.
	 *
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code> en consulta exitosa
	 */
	public boolean isConsultaExitosa()
	{
		return ib_consultaExitosa;
	}

	/**
	 * Modifica el valor de ConsultaExitosa.
	 *
	 * @param ab_b de ab b
	 */
	public void setConsultaExitosa(boolean ab_b)
	{
		ib_consultaExitosa = ab_b;
	}

	/**
	 * Retorna Objeto o variable de valor cuenta cupo.
	 *
	 * @return el valor de cuenta cupo
	 */
	public CuentaCupo getCuentaCupo()
	{
		return icc_cuentaCupo;
	}

	/**
	 * Modifica el valor de CuentaCupo.
	 *
	 * @param ac_c de ac c
	 */
	public void setCuentaCupo(CuentaCupo ac_c)
	{
		icc_cuentaCupo = ac_c;
	}

	/**
	 * Retorna Objeto o variable de valor justificacion.
	 *
	 * @return Retorna el valor de la propiedad justificacion
	 */
	public String getJustificacion()
	{
		return justificacion;
	}

	/**
	 * Modifica el valor de Justificacion.
	 *
	 * @param as_s de as s
	 */
	public void setJustificacion(String as_s)
	{
		justificacion = as_s;
	}

	/**
	 * Clean.
	 */
	public void clean()
	{
		setIdCuentaCupo(null);
		setConsultaExitosa(false);
		setDocumentosCargados(null);
		setJustificacion(null);
	}

	/**
	 * Regresar.
	 *
	 * @return el valor de string
	 */
	public String regresar()
	{
		String ls_return;

		ls_return = null;

		if(isConsultaExitosa())
			clean();
		else
			ls_return = NavegacionCommon.PRINCIPAL;

		return ls_return;
	}

	/**
	 * Procesa el cargue de archivos pdf para el soporte de rechazar una solicitud.
	 *
	 * @param afue_event Objeto contenedor del arreglo de bytes correspondiente al archivo cargado
	 */
	public void cargarDocumentoPdf(FileUploadEvent afue_event)
	{
		cargarDocumentoPdf(afue_event, is_messageIdGrowl);
	}

	/**
	 * Consultar id cuenta cupo.
	 */
	public void consultarIdCuentaCupo()
	{
		try
		{
			String     ls_idCuentaCupo;
			CuentaCupo lc_cuentaCupo;

			ls_idCuentaCupo = getIdCuentaCupo();

			validateStyles(is_idForm + ":idCuentaCupo", FacesContext.getCurrentInstance(), ls_idCuentaCupo, true);

			if(!StringUtils.isValidString(ls_idCuentaCupo))
				throw new B2BException(ErrorKeys.ERROR_SIN_ID_CUENTA_CUPO);

			lc_cuentaCupo = iccr_cuentaCuposRemote.buscarPorIdCuentaCupo(
				    ls_idCuentaCupo, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

			if(lc_cuentaCupo != null)
			{
				String ls_estado;

				ls_estado = lc_cuentaCupo.getEstado();

				if(StringUtils.isValidString(ls_estado) && ls_estado.equalsIgnoreCase(EstadoCommon.ACTIVO_TXT))
				{
					setConsultaExitosa(true);
					addMessage(MessagesKeys.CONSULTA_EXITOSA_2);
				}
				else
					throw new B2BException(ErrorKeys.ERROR_CUENTA_CUPO_CONSULTADA_ESTADO);
			}
			else
				throw new B2BException(ErrorKeys.ERROR_CUENTA_CUPO_NO_EXISTENTE);

			setCuentaCupo(lc_cuentaCupo);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			clh_LOGGER.error("consultarIdCuentaCupo", lb2be_e);
		}
	}

	/**
	 * Inactivar cuenta cupo.
	 */
	public void inactivarCuentaCupo()
	{
		try
		{
			CuentaCupo lcc_cuentaCupo;

			lcc_cuentaCupo = getCuentaCupo();

			if(lcc_cuentaCupo != null)
			{
				AprobacionCuentaCupos lacc_dataAprobacion;

				lacc_dataAprobacion = new AprobacionCuentaCupos();

				lacc_dataAprobacion.setIdSolicitud(lcc_cuentaCupo.getIdSolicitud());
				lacc_dataAprobacion.setIdCuentaCupo(getIdCuentaCupo());

				{
					Map<String, byte[]> lmsba_archivosCargados;

					lmsba_archivosCargados = getArchivosCargados();

					if(!CollectionUtils.isValidCollection(lmsba_archivosCargados))
						throw new B2BException(ErrorKeys.ERROR_SIN_DOCUMENTOS_ADJUNTOS);

					lacc_dataAprobacion.setArchivosCargados(lmsba_archivosCargados);
				}

				{
					String ls_justificacion;

					ls_justificacion = getJustificacion();

					validateStyles(
					    is_idForm + ":idITJustificacion", FacesContext.getCurrentInstance(), ls_justificacion, true
					);

					if(!StringUtils.isValidString(ls_justificacion))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_JUSTIFICACION);

					lacc_dataAprobacion.setObservaciones(ls_justificacion);
				}

				iccr_cuentaCuposRemote.inactivarCuentaCupo(
				    lacc_dataAprobacion, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

				clean();
			}
			else
				throw new B2BException(ErrorKeys.ERROR_CUENTA_CUPO_NO_EXISTENTE);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			clh_LOGGER.error("inactivarCuentaCupo", lb2be_e);
			PrimeFaces.current().ajax().update(is_messageIdGrowl);
		}
	}
}
