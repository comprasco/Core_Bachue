package com.bachue.snr.prosnr01.web.bean.consulta.cuentaCupos;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.ExtensionCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;
import com.bachue.snr.prosnr01.common.constants.TipoArchivoCommon;
import com.bachue.snr.prosnr01.common.constants.TipoContenidoCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.consulta.cuentaCupos.ConsultaCuentaCuposRemote;

import com.bachue.snr.prosnr01.model.cuentaCupos.ConsultaCuentaCupos;

import com.bachue.snr.prosnr01.web.bean.BaseBean;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import java.util.Date;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase para el manejo de eventos de la pantalla de consulta cuenta cupos.
 *
 * @author Manuel Blanco
 */
@SessionScoped
@ManagedBean(name = "beanConsultaCuentaCupos")
public class BeanConsultaCuentaCupos extends BaseBean
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -130620329718861898L;

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanConsultaCuentaCupos.class);

	/** Constante is_idForm. */
	private static final String is_idForm = "fConsultaCuentaCupos";

	/** Propiedad iccc consulta movimientos. */
	private ConsultaCuentaCupos iccc_consultaMovimientos;

	/** Propiedad iccc consulta recarga. */
	private ConsultaCuentaCupos iccc_consultaRecarga;

	/** Propiedad icccr consulta cuenta cupos remote. */
	@EJB
	private ConsultaCuentaCuposRemote icccr_consultaCuentaCuposRemote;

	/** Propiedad id fecha final. */
	private Date id_fechaFinal;

	/** Propiedad id fecha inicial. */
	private Date id_fechaInicial;

	/** Propiedad idsc documento consulta. */
	private StreamedContent isc_documentoConsulta;

	/** Propiedad is id cuenta cupo. */
	private String is_idCuentaCupo;

	/** Propiedad is opcion excel. */
	private String is_opcionExcel;

	/** Propiedad is opcion pdf. */
	private String is_opcionPdf;

	/** Propiedad is seleccion tipo archivo. */
	private String is_seleccionTipoArchivo;

	/** Propiedad ib consulta exitosa. */
	private boolean ib_consultaExitosa;

	/** Propiedad ib consulta movimientos exitosa. */
	private boolean ib_consultaMovimientosExitosa;

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
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
	 * Clean.
	 */
	public void clean()
	{
		setIdCuentaCupo(null);
		setFechaInicial(null);
		setFechaFinal(null);
		setConsultaExitosa(false);
		setConsultaRecarga(null);
		setConsultaMovimientos(null);
		setOpcionExcel(null);
		setOpcionPdf(null);
		setConsultaMovimientosExitosa(false);
		setSeleccionTipoArchivo(null);
		setDocumentoConsulta(null);

		{
			FacesContext lfc_context;

			lfc_context = FacesContext.getCurrentInstance();

			validateStyles(is_idForm + ":idSOMTipoArchivo", lfc_context, IdentificadoresCommon.DATO_VALIDO, true);
			validateStyles(is_idForm + ":idCalFechaInicial", lfc_context, IdentificadoresCommon.DATO_VALIDO, true);
			validateStyles(is_idForm + ":idCalFechaFinal", lfc_context, IdentificadoresCommon.DATO_VALIDO, true);
		}
	}

	/**
	 * Obtiene el valor de documento consulta.
	 *
	 * @return el valor de documento consulta
	 */
	public StreamedContent getDocumentoConsulta()
	{
		return isc_documentoConsulta;
	}

	/**
	 * Cambia el valor de documento consulta.
	 *
	 * @param asc_dsc el nuevo valor de documento consulta
	 */
	public void setDocumentoConsulta(StreamedContent asc_dsc)
	{
		isc_documentoConsulta = asc_dsc;
	}

	/**
	 * Obtiene el valor de seleccion tipo archivo.
	 *
	 * @return el valor de seleccion tipo archivo
	 */
	public String getSeleccionTipoArchivo()
	{
		return is_seleccionTipoArchivo;
	}

	/**
	 * Cambia el valor de seleccion tipo archivo.
	 *
	 * @param as_s el nuevo valor de seleccion tipo archivo
	 */
	public void setSeleccionTipoArchivo(String as_s)
	{
		is_seleccionTipoArchivo = as_s;
	}

	/**
	 * Obtiene el valor de opcion excel.
	 *
	 * @return el valor de opcion excel
	 */
	public String getOpcionExcel()
	{
		if(!StringUtils.isValidString(is_opcionExcel))
			is_opcionExcel = IdentificadoresCommon.EXCEL_TXT;

		return is_opcionExcel;
	}

	/**
	 * Cambia el valor de opcion excel.
	 *
	 * @param as_s el nuevo valor de opcion excel
	 */
	public void setOpcionExcel(String as_s)
	{
		is_opcionExcel = as_s;
	}

	/**
	 * Obtiene el valor de opcion pdf.
	 *
	 * @return el valor de opcion pdf
	 */
	public String getOpcionPdf()
	{
		if(!StringUtils.isValidString(is_opcionPdf))
			is_opcionPdf = IdentificadoresCommon.PDF_TXT;

		return is_opcionPdf;
	}

	/**
	 * Cambia el valor de opcion pdf.
	 *
	 * @param as_s el nuevo valor de opcion pdf
	 */
	public void setOpcionPdf(String as_s)
	{
		is_opcionPdf = as_s;
	}

	/**
	 * Verifica si es consulta movimientos exitosa.
	 *
	 * @return true, si es consulta movimientos exitosa
	 */
	public boolean isConsultaMovimientosExitosa()
	{
		return ib_consultaMovimientosExitosa;
	}

	/**
	 * Cambia el valor de consulta movimientos exitosa.
	 *
	 * @param ab_b el nuevo valor de consulta movimientos exitosa
	 */
	public void setConsultaMovimientosExitosa(boolean ab_b)
	{
		ib_consultaMovimientosExitosa = ab_b;
	}

	/**
	 * Obtiene el valor de id cuenta cupo.
	 *
	 * @return el valor de id cuenta cupo
	 */
	public String getIdCuentaCupo()
	{
		return is_idCuentaCupo;
	}

	/**
	 * Cambia el valor de id cuenta cupo.
	 *
	 * @param as_s el nuevo valor de id cuenta cupo
	 */
	public void setIdCuentaCupo(String as_s)
	{
		is_idCuentaCupo = as_s;
	}

	/**
	 * Obtiene el valor de fecha inicial.
	 *
	 * @return el valor de fecha inicial
	 */
	public Date getFechaInicial()
	{
		return id_fechaInicial;
	}

	/**
	 * Cambia el valor de fecha inicial.
	 *
	 * @param ad_d el nuevo valor de fecha inicial
	 */
	public void setFechaInicial(Date ad_d)
	{
		id_fechaInicial = ad_d;
	}

	/**
	 * Obtiene el valor de fecha final.
	 *
	 * @return el valor de fecha final
	 */
	public Date getFechaFinal()
	{
		return id_fechaFinal;
	}

	/**
	 * Cambia el valor de fecha final.
	 *
	 * @param ad_d el nuevo valor de fecha final
	 */
	public void setFechaFinal(Date ad_d)
	{
		id_fechaFinal = ad_d;
	}

	/**
	 * Checks if is consulta exitosa.
	 *
	 * @return true, if is consulta exitosa
	 */
	public boolean isConsultaExitosa()
	{
		return ib_consultaExitosa;
	}

	/**
	 * Cambia el valor de consulta exitosa.
	 *
	 * @param ab_b el nuevo valor de consulta exitosa
	 */
	public void setConsultaExitosa(boolean ab_b)
	{
		ib_consultaExitosa = ab_b;
	}

	/**
	 * Obtiene el valor de consulta recarga.
	 *
	 * @return el valor de consulta recarga
	 */
	public ConsultaCuentaCupos getConsultaRecarga()
	{
		if(iccc_consultaRecarga == null)
			iccc_consultaRecarga = new ConsultaCuentaCupos();

		return iccc_consultaRecarga;
	}

	/**
	 * Cambia el valor de consulta recarga.
	 *
	 * @param accc_ccc el nuevo valor de consulta recarga
	 */
	public void setConsultaRecarga(ConsultaCuentaCupos accc_ccc)
	{
		iccc_consultaRecarga = accc_ccc;
	}

	/**
	 * Obtiene el valor de consulta movimientos.
	 *
	 * @return el valor de consulta movimientos
	 */
	public ConsultaCuentaCupos getConsultaMovimientos()
	{
		if(iccc_consultaMovimientos == null)
			iccc_consultaMovimientos = new ConsultaCuentaCupos();

		return iccc_consultaMovimientos;
	}

	/**
	 * Cambia el valor de consulta movimientos.
	 *
	 * @param accc_ccc el nuevo valor de consulta movimientos
	 */
	public void setConsultaMovimientos(ConsultaCuentaCupos accc_ccc)
	{
		iccc_consultaMovimientos = accc_ccc;
	}

	/**
	 * Consultar id cuenta cupo.
	 */
	public void consultarIdCuentaCupo()
	{
		try
		{
			String              ls_idCuentaCupo;
			ConsultaCuentaCupos lccc_consultaRecarga;

			ls_idCuentaCupo = getIdCuentaCupo();

			validateStyles(is_idForm + ":idCuentaCupo", FacesContext.getCurrentInstance(), ls_idCuentaCupo, true);

			if(!StringUtils.isValidString(ls_idCuentaCupo))
				throw new B2BException(ErrorKeys.ERROR_SIN_ID_CUENTA_CUPO);

			lccc_consultaRecarga = icccr_consultaCuentaCuposRemote.obtenerDatosUltimaRecarga(
				    ls_idCuentaCupo, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

			if(lccc_consultaRecarga != null)
			{
				setConsultaExitosa(true);
				addMessage(MessagesKeys.CONSULTA_EXITOSA_2);
			}
			else
				throw new B2BException(ErrorKeys.ERROR_CUENTA_CUPO_NO_EXISTENTE);

			setConsultaRecarga(lccc_consultaRecarga);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			clh_LOGGER.error("consultarIdCuentaCupo", lb2be_e);
		}
	}

	/**
	 * Consultar movimientos.
	 */
	public void consultarMovimientos()
	{
		try
		{
			FacesContext        lfc_context;
			ConsultaCuentaCupos lccc_parametros;

			lfc_context         = FacesContext.getCurrentInstance();
			lccc_parametros     = new ConsultaCuentaCupos();

			lccc_parametros.setIdCuentaCupo(getIdCuentaCupo());

			validarFechasRango(lccc_parametros, lfc_context);

			{
				ConsultaCuentaCupos lccc_movimientos;

				lccc_movimientos = icccr_consultaCuentaCuposRemote.obtenerMovimientosCuentaCupo(
					    lccc_parametros, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);

				setConsultaMovimientosExitosa(lccc_movimientos != null);

				setConsultaMovimientos(lccc_movimientos);
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			clh_LOGGER.error("consultarIdCuentaCupo", lb2be_e);
			setConsultaMovimientosExitosa(false);
			setDocumentoConsulta(null);
			setConsultaMovimientos(null);
		}
	}

	/**
	 * Genera el documento con los movimientos consultados.
	 */
	public void exportarArchivoConsulta()
	{
		try
		{
			FacesContext        lfc_context;
			ConsultaCuentaCupos lccc_parametros;
			boolean             lb_archivoPdf;

			lfc_context         = FacesContext.getCurrentInstance();
			lccc_parametros     = new ConsultaCuentaCupos();

			lccc_parametros.setIdCuentaCupo(getIdCuentaCupo());

			validarFechasRango(lccc_parametros, lfc_context);

			{
				String ls_tipoArchivo;

				ls_tipoArchivo = getSeleccionTipoArchivo();

				validateStyles(is_idForm + ":idSOMTipoArchivo", lfc_context, ls_tipoArchivo, true);

				if(!StringUtils.isValidString(ls_tipoArchivo))
					throw new B2BException(ErrorKeys.ERROR_SIN_SELECCION_TIPO_ARCHIVO);

				lb_archivoPdf = ls_tipoArchivo.equals(getOpcionPdf());

				lccc_parametros.setTipoArchivoReporte(ls_tipoArchivo);
			}

			{
				byte[] lba_documento;

				lba_documento = icccr_consultaCuentaCuposRemote.generarDocumentoMovimientos(
					    lccc_parametros, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);

				{
					DefaultStreamedContent ldsc_documento;

					ldsc_documento = generarArchivosDescarga(
						    lba_documento, lb_archivoPdf ? TipoContenidoCommon.PDF : TipoContenidoCommon.XLS_XLSX_XXLS,
						    TipoArchivoCommon.REPORTE_MOVIMIENTOS
						    + (lb_archivoPdf ? ExtensionCommon.PDF_MAYUS_PUNTO : ExtensionCommon.XLSX_PUNTO)
						);

					if(ldsc_documento == null)
						throw new B2BException(ErrorKeys.ERROR_GENERANDO_PLANTILLA);

					setDocumentoConsulta(ldsc_documento);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			clh_LOGGER.error("exportarArchivoConsulta", lb2be_e);
		}
	}

	/**
	 * Verifica que los datos de las fechas ingresadas sean válidas.
	 *
	 * @param accc_parametros Objeto al cual se van a asignar las fechas
	 * @param afc_context Contexto de la pantalla
	 * @throws B2BException Si no se cumple una regla de negocio
	 */
	private void validarFechasRango(ConsultaCuentaCupos accc_parametros, FacesContext afc_context)
	    throws B2BException
	{
		if((accc_parametros != null) && (afc_context != null))
		{
			{
				Date ld_fechaInicial;

				ld_fechaInicial = getFechaInicial();

				validateStyles(is_idForm + ":idCalFechaInicial", afc_context, ld_fechaInicial, true);

				if(ld_fechaInicial == null)
					throw new B2BException(ErrorKeys.ERROR_SIN_FECHA_INICIAL);

				accc_parametros.setFechaInicial(ld_fechaInicial);
			}

			{
				Date ld_fechaFinal;

				ld_fechaFinal = getFechaFinal();

				validateStyles(is_idForm + ":idCalFechaFinal", afc_context, ld_fechaFinal, true);

				if(ld_fechaFinal == null)
					throw new B2BException(ErrorKeys.ERROR_SIN_FECHA_FINAL);

				accc_parametros.setFechaFinal(ld_fechaFinal);
			}
		}
	}
}
