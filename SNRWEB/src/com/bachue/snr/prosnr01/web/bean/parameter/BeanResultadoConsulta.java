package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.BooleanUtils;

import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.reference.ReferenceRemote;

import com.bachue.snr.prosnr01.model.sdb.pgn.ResultadoConsulta;

import com.bachue.snr.prosnr01.web.bean.BaseBean;
import com.bachue.snr.prosnr01.web.util.FacesUtils;

import org.primefaces.PrimeFaces;

import org.primefaces.event.FileUploadEvent;

import org.primefaces.model.UploadedFile;

import java.io.IOException;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;


/**
 * Clase que contiene todos las propiedades y acciones de BeanResultadoConsulta.
 *
 * @author Sebastian Sanchez
 */
@ManagedBean(name = "beanResultadoConsulta")
@SessionScoped
public class BeanResultadoConsulta extends BaseBean implements Serializable
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanResultadoConsulta.class);

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -7354231821424653147L;

	/** Propiedad icrc datos auditoria. */
	private Collection<ResultadoConsulta> icrc_datosAuditoria;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad irr reference remote. */
	@EJB
	private ReferenceRemote irr_referenceRemote;

	/** Propiedad irc resultado consulta. */
	private ResultadoConsulta irc_resultadoConsulta;

	/** Propiedad iuf upload file. */
	private UploadedFile iuf_uploadFile;

	/** Propiedad ib insertar. */
	private boolean ib_insertar;

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * @param acrc_datosAuditoria asigna el valor a la propiedad
	 */
	public void setDatosAuditoria(Collection<ResultadoConsulta> acrc_datosAuditoria)
	{
		icrc_datosAuditoria = acrc_datosAuditoria;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<ResultadoConsulta> getDatosAuditoria()
	{
		return icrc_datosAuditoria;
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
	 * @param aae_ae asigna el valor a la propiedad
	 */
	public void setResultadoConsulta(ResultadoConsulta arc_rc)
	{
		irc_resultadoConsulta = arc_rc;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public ResultadoConsulta getResultadoConsulta()
	{
		return irc_resultadoConsulta;
	}

	/**
	 * Modifica el valor de upload file.
	 *
	 * @param auf_uf asigna el valor a la propiedad upload file
	 */
	public void setUploadFile(UploadedFile auf_uf)
	{
		iuf_uploadFile = auf_uf;
	}

	/**
	 * Retorna el valor de upload file.
	 *
	 * @return el valor de upload file
	 */
	public UploadedFile getUploadFile()
	{
		return iuf_uploadFile;
	}

	/**
	 * Método para cambiar estado para saber si se desea insertar o modificar un registro
	 * de la tabla SDB_PGN_RESULTADO_CONSULTA
	 */
	public void cambiarEstado()
	{
		setInsertar(true);
		setResultadoConsulta((new ResultadoConsulta()));

		Boolean lb_parametro;

		lb_parametro = FacesUtils.getBooleanFacesParameter("pInsertarResultadoConsulta");

		if(lb_parametro != null)
			setInsertar(BooleanUtils.getBooleanValue(lb_parametro));
	}

	/**
	 * Método de consultar un registro detalladamente de SDB_PGN_RESULTADO_CONSULTA por medio de su indicativo
	 *
	 * @param arc_resultadoConsulta indicativo de los datos solicitados
	 * @throws B2BException
	 */
	public void consultaDetallada(ResultadoConsulta arc_resultadoConsulta)
	    throws B2BException
	{
		if(arc_resultadoConsulta != null)
		{
			ResultadoConsulta lrc_resultadoConsulta;

			lrc_resultadoConsulta = ipr_parameterRemote.findResultadoConsultaById(arc_resultadoConsulta);

			if(lrc_resultadoConsulta != null)
			{
				Collection<ResultadoConsulta> lcrc_crc;

				lcrc_crc = new ArrayList<ResultadoConsulta>();

				lcrc_crc.add(lrc_resultadoConsulta);

				setResultadoConsulta(lrc_resultadoConsulta);
				setDatosAuditoria(lcrc_crc);
			}

			setInsertar(false);
		}
	}

	/**
	 *  Método para encontrar todos los registros de la tabla SDB_PGN_RESULTADO_CONSULTA
	 *
	 * @return Collection de ResultadoConsulta resultante de la consulta
	 */
	public Collection<ResultadoConsulta> findAllResultadoConsulta()
	{
		Collection<ResultadoConsulta> lcrc_resultadoConsulta;

		lcrc_resultadoConsulta = null;

		try
		{
			lcrc_resultadoConsulta = irr_referenceRemote.findResultadoConsulta(
				    getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
		}

		return lcrc_resultadoConsulta;
	}

	/**
	 * Método para proceso de carga de archivo.
	 *
	 * @param afue_event correspondiente al valor del tipo de objeto FileUploadEvent
	 * @return devuelve el valor de String
	 */
	public String processFile(FileUploadEvent afue_event)
	{
		UploadedFile luf_file;

		luf_file = afue_event.getFile();

		if(luf_file != null)
			setUploadFile(luf_file);

		return null;
	}

	/**
	 * Método para salvar la inserción o actualización de un registro en la tabla SDB_PGN_RESULTADO_CONSULTA
	 *
	 * @return String contenedor de la pagina a regresar
	 */
	public String salvar()
	{
		String ls_result;

		ls_result = null;

		try
		{
			ResultadoConsulta lrc_resultadoConsulta;

			lrc_resultadoConsulta = getResultadoConsulta();

			if(lrc_resultadoConsulta != null)
			{
				UploadedFile luf_file;

				luf_file = getUploadFile();

				if(luf_file != null)
					lrc_resultadoConsulta.setResultadoBlob(luf_file.getContents());

				ipr_parameterRemote.salvarResultadoConsulta(
				    lrc_resultadoConsulta, isInsertar(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

				clear();

				ls_result = NavegacionCommon.RESULTADO_CONSULTA;

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
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update("fResultadoConsultaDetalle:globalMsg");
		}
		catch(IOException lioe_e)
		{
			addMessage(new B2BException(lioe_e.getLocalizedMessage()));
			clh_LOGGER.error("salvar", lioe_e);
			PrimeFaces.current().ajax().update("fResultadoConsultaDetalle:globalMsg");
		}

		return ls_result;
	}

	/**
	 * Retorna el valor del objeto de String con la vista.
	 *
	 * @return devuelve el valor de String
	 */
	public String returnPages()
	{
		String ls_return;

		ls_return = NavegacionCommon.RESULTADO_CONSULTA;

		clear();

		return ls_return;
	}

	/**
	 * Método para limpiar la pantalla
	 */
	public void clear()
	{
		setDatosAuditoria(null);
		setResultadoConsulta(null);
		setInsertar(false);
		setUploadFile(null);
	}
}
