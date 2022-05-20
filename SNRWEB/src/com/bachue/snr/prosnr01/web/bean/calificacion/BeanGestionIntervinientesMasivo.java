package com.bachue.snr.prosnr01.web.bean.calificacion;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.ProcedenciaCommon;
import com.bachue.snr.prosnr01.common.constants.ProcesoCommon;
import com.bachue.snr.prosnr01.common.constants.TipoContenidoCommon;
import com.bachue.snr.prosnr01.common.constants.TipoRecepcionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.registro.RegistroRemote;

import com.bachue.snr.prosnr01.model.registro.Interviniente;
import com.bachue.snr.prosnr01.model.sdb.acc.Persona;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudInterviniente;
import com.bachue.snr.prosnr01.model.sdb.pgn.Constantes;

import com.bachue.snr.prosnr01.web.bean.dispositivos.BeanDispositivos;
import com.bachue.snr.prosnr01.web.util.ExportFiles;

import org.primefaces.PrimeFaces;

import org.primefaces.event.FileUploadEvent;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


/**
 * Clase que contiene todos las propiedades y acciones de beanGestionIntervinientesMasivo.
 *
 * @author ccalderon
 */
@SessionScoped
@ManagedBean(name = "beanGestionIntervinientesMasivo")
public class BeanGestionIntervinientesMasivo extends BeanDispositivos implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -3498798511335038790L;

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanGestionIntervinientesMasivo.class);

	/** Constante is_messageIdGrowl. */
	private static final String is_messageIdGrowl = "fRegistro:globalMsg";

	/** Propiedad icsi tabla solicitud interviniente. */
	private Collection<SolicitudInterviniente> icsi_tablaSolicitudInterviniente;

	/** Propiedad irr registro remote. */
	@EJB
	private RegistroRemote irr_registroRemote;

	/** Propiedad iso solicitud. */
	private Solicitud iso_solicitud;

	/** Propiedad isi interviniente para eliminar. */
	private SolicitudInterviniente isi_intervinienteParaEliminar;

	/** Propiedad isc imagenPlantillaIntervMasivos. */
	private StreamedContent isc_imagenPlantillaIntervMasivos;

	/** Propiedad isc resultado cargue intervinientes. */
	private StreamedContent isc_resultadoCargueIntervinientes;

	/** Propiedad is id proceso. */
	private String is_idProceso;

	/** Propiedad is id subproceso. */
	private String is_idSubproceso;

	/** Propiedad is pregunta masivo intervinientes. */
	private String is_preguntaMasivoIntervinientes;

	/** Propiedad ib proceso certificados. */
	private boolean ib_procesoCertificados;

	/** Propiedad ib rendered interviniente acto. */
	private boolean ib_renderedIntervinienteActo;

	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * Modifica el valor de id proceso.
	 *
	 * @param as_s asigna el valor a la propiedad id proceso
	 */
	public void setIdProceso(String as_s)
	{
		is_idProceso = as_s;
	}

	/**
	 * Retorna el valor de id proceso.
	 *
	 * @return el valor de id proceso
	 */
	public String getIdProceso()
	{
		return is_idProceso;
	}

	/**
	 * Modifica el valor de id subproceso.
	 *
	 * @param as_s asigna el valor a la propiedad id subproceso
	 */
	public void setIdSubproceso(String as_s)
	{
		is_idSubproceso = as_s;
	}

	/**
	 * Retorna el valor de id subproceso.
	 *
	 * @return el valor de id subproceso
	 */
	public String getIdSubproceso()
	{
		return is_idSubproceso;
	}

	/**
	 * @param Modifica el valor de la propiedad imagenPlantillaIntervMasivos por imagenPlantillaIntervMasivos
	 */
	public void setImagenPlantillaIntervMasivos(StreamedContent asc_sc)
	{
		isc_imagenPlantillaIntervMasivos = asc_sc;
	}

	/**
	 * @return Retorna el valor de la propiedad imagenPlantillaIntervMasivos
	 */
	public StreamedContent getImagenPlantillaIntervMasivos()
	{
		return isc_imagenPlantillaIntervMasivos;
	}

	/**
	 * Modifica el valor de interviniente para eliminar.
	 *
	 * @param asi_si asigna el valor a la propiedad interviniente para eliminar
	 */
	public void setIntervinienteParaEliminar(SolicitudInterviniente asi_si)
	{
		isi_intervinienteParaEliminar = asi_si;
	}

	/**
	 * Retorna el valor de interviniente para eliminar.
	 *
	 * @return el valor de interviniente para eliminar
	 */
	public SolicitudInterviniente getIntervinienteParaEliminar()
	{
		return isi_intervinienteParaEliminar;
	}

	/**
	 * Modifica el valor de pregunta masivo intervinientes.
	 *
	 * @param as_s asigna el valor a la propiedad pregunta masivo intervinientes
	 */
	public void setPreguntaMasivoIntervinientes(String as_s)
	{
		is_preguntaMasivoIntervinientes = as_s;
	}

	/**
	 * Retorna el valor de pregunta masivo intervinientes.
	 *
	 * @return el valor de pregunta masivo intervinientes
	 */
	public String getPreguntaMasivoIntervinientes()
	{
		return is_preguntaMasivoIntervinientes;
	}

	/**
	 * Modifica el valor de proceso certificados.
	 *
	 * @param ab_b asigna el valor a la propiedad proceso certificados
	 */
	public void setProcesoCertificados(boolean ab_b)
	{
		ib_procesoCertificados = ab_b;
	}

	/**
	 * Valida la propiedad proceso certificados.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en proceso certificados
	 */
	public boolean isProcesoCertificados()
	{
		return ib_procesoCertificados;
	}

	/**
	 * Modifica el valor de rendered interviniente acto.
	 *
	 * @param ab_b asigna el valor a la propiedad rendered interviniente acto
	 */
	public void setRenderedIntervinienteActo(boolean ab_b)
	{
		ib_renderedIntervinienteActo = ab_b;
	}

	/**
	 * Valida la propiedad rendered interviniente acto.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en rendered interviniente acto
	 */
	public boolean isRenderedIntervinienteActo()
	{
		return ib_renderedIntervinienteActo;
	}

	/**
	 * Modifica el valor de resultado cargue intervinientes.
	 *
	 * @param asc_sc asigna el valor a la propiedad resultado cargue intervinientes
	 */
	public void setResultadoCargueIntervinientes(StreamedContent asc_sc)
	{
		isc_resultadoCargueIntervinientes = asc_sc;
	}

	/**
	 * Retorna el valor de resultado cargue intervinientes.
	 *
	 * @return el valor de resultado cargue intervinientes
	 */
	public StreamedContent getResultadoCargueIntervinientes()
	{
		return isc_resultadoCargueIntervinientes;
	}

	/**
	 * Modifica el valor de solicitud.
	 *
	 * @param aso_so asigna el valor a la propiedad solicitud
	 */
	public void setSolicitud(Solicitud aso_so)
	{
		iso_solicitud = aso_so;
	}

	/**
	 * Retorna el valor de solicitud.
	 *
	 * @return el valor de solicitud
	 */
	public Solicitud getSolicitud()
	{
		if(iso_solicitud == null)
		{
			iso_solicitud = new Solicitud();

			String ls_idProceso;
			String ls_idSubproceso;

			ls_idProceso        = getIdProceso();
			ls_idSubproceso     = getIdSubproceso();

			if(!StringUtils.isValidString(ls_idProceso))
			{
				if(isProcesoCertificados())
					ls_idProceso = ProcesoCommon.ID_PROCESO_1;
				else
					ls_idProceso = "6";

				ls_idSubproceso = "-1";
			}
			else
			{
				if(ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_6))
					iso_solicitud.setIdCalidadSolicitante("3");
				else if(ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_3))
					iso_solicitud.setParticipaInterviniente(EstadoCommon.S);
				else if(ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_10))
					iso_solicitud.setIdTipoRecepcion(TipoRecepcionCommon.ORIP_DE_ORIGEN);
			}

			iso_solicitud.setIdProcedencia(ProcedenciaCommon.ORIP_DE_ORIGEN);
			iso_solicitud.setIdProceso(ls_idProceso);
			iso_solicitud.setIdSubproceso(ls_idSubproceso);
		}

		return iso_solicitud;
	}

	/**
	 * Modifica el valor de tabla solicitud interviniente.
	 *
	 * @param ac_t asigna el valor a la propiedad tabla solicitud interviniente
	 */
	public void setTablaSolicitudInterviniente(Collection<SolicitudInterviniente> ac_t)
	{
		icsi_tablaSolicitudInterviniente = ac_t;
	}

	/**
	 * Retorna el valor de tabla solicitud interviniente.
	 *
	 * @return el valor de tabla solicitud interviniente
	 */
	public Collection<SolicitudInterviniente> getTablaSolicitudInterviniente()
	{
		return icsi_tablaSolicitudInterviniente;
	}

	/**
	 * Retorna el valor del objeto de byte[].
	 *
	 * @param aba_excel correspondiente al valor del tipo de objeto byte[]
	 * @param as_nameFile correspondiente al valor del tipo de objeto String
	 * @param as_idSolicitud correspondiente al valor de as id solicitud
	 * @param as_userId correspondiente al valor del tipo de objeto String
	 * @throws B2BException Señala que se ha producido una excepción
	 * @throws IOException Señala que se ha producido una excepción de E / S.
	 * @see byte[]
	 */
	public void FileLoadIntervinientes(byte[] aba_excel, String as_nameFile, String as_idSolicitud, String as_userId)
	    throws B2BException, IOException
	{
		try
		{
			if(aba_excel != null)
			{
				Collection<SolicitudInterviniente> lci_resultadoProcesarintervinientes;
				String                             ls_solicitud;
				boolean                            lb_archivoExitoso;
				boolean                            lb_intValidados;

				lci_resultadoProcesarintervinientes     = new ArrayList<SolicitudInterviniente>();

				lb_archivoExitoso     = true;
				lb_intValidados       = false;

				if(StringUtils.isValidString(as_idSolicitud))
					ls_solicitud = as_idSolicitud;
				else
					ls_solicitud = getSolicitud().getIdSolicitud();

				if(StringUtils.isValidString(ls_solicitud))
				{
					lci_resultadoProcesarintervinientes = irr_registroRemote.validarExcelIntervinientesMasivos(
						    aba_excel, ls_solicitud, as_nameFile, as_userId, getLocalIpAddress(), getRemoteIpAddress()
						);

					if(CollectionUtils.isValidCollection(lci_resultadoProcesarintervinientes))
					{
						Collection<SolicitudInterviniente> lcsi_intervinientesActuales;

						lcsi_intervinientesActuales = getTablaSolicitudInterviniente();

						if(CollectionUtils.isValidCollection(lcsi_intervinientesActuales))
						{
							Collection<SolicitudInterviniente> lcsi_intervinientesValidados;
							boolean                            lb_primeraVez;

							lb_primeraVez = true;

							for(SolicitudInterviniente lsi_actual : lci_resultadoProcesarintervinientes)
							{
								if((lsi_actual != null) && lb_archivoExitoso)
								{
									String ls_errorIntervinienteMasivo;

									ls_errorIntervinienteMasivo = lsi_actual.getErrorIntervinienteMasivo();

									if(!StringUtils.isValidString(ls_errorIntervinienteMasivo))
									{
										if(lb_primeraVez)
										{
											lcsi_intervinientesValidados = irr_registroRemote
													.enlistarPorMapaIntervinientes(
													    lcsi_intervinientesActuales, lsi_actual,
													    NumericUtils.DEFAULT_LONG_VALUE, getUserId(),
													    getLocalIpAddress(), getRemoteIpAddress()
													);

											if(CollectionUtils.isValidCollection(lcsi_intervinientesValidados))
											{
												setTablaSolicitudInterviniente(lcsi_intervinientesValidados);
												lb_primeraVez = false;
											}
										}
										else
										{
											Collection<SolicitudInterviniente> lcsi_intervinientesValidadosSegundaVuelta;

											lcsi_intervinientesValidadosSegundaVuelta = getTablaSolicitudInterviniente();

											if(
											    CollectionUtils.isValidCollection(
												        lcsi_intervinientesValidadosSegundaVuelta
												    )
											)
											{
												lcsi_intervinientesValidados = irr_registroRemote
														.enlistarPorMapaIntervinientes(
														    lcsi_intervinientesValidadosSegundaVuelta, lsi_actual,
														    NumericUtils.DEFAULT_LONG_VALUE, getUserId(),
														    getLocalIpAddress(), getRemoteIpAddress()
														);

												if(CollectionUtils.isValidCollection(lcsi_intervinientesValidados))
												{
													setTablaSolicitudInterviniente(lcsi_intervinientesValidados);
													lb_intValidados = true;
												}
											}
										}
									}
									else
										lb_archivoExitoso = false;
								}
							}
						}
						else
						{
							for(SolicitudInterviniente lsi_actual : lci_resultadoProcesarintervinientes)
							{
								if((lsi_actual != null) && lb_archivoExitoso)
								{
									String ls_errorIntervinienteMasivo;

									ls_errorIntervinienteMasivo = lsi_actual.getErrorIntervinienteMasivo();

									if(StringUtils.isValidString(ls_errorIntervinienteMasivo))
										lb_archivoExitoso = false;
								}
							}
						}
					}
				}

				if(lb_archivoExitoso)
				{
					if(!lb_intValidados)
						setTablaSolicitudInterviniente(lci_resultadoProcesarintervinientes);

					setResultadoCargueIntervinientes(null);
					addMessage(MessagesKeys.PROCESAMIENTO_DE_CARGUE_MASIVO_EXITOSO);
					PrimeFaces.current().ajax().update(is_messageIdGrowl);
				}
				else
				{
					byte[]              arch;
					ExportFiles         lef_files;
					Map<String, String> lhm_columns;

					arch            = null;
					lef_files       = new ExportFiles();
					lhm_columns     = new LinkedHashMap<String, String>();

					lhm_columns.put("Rol", "Rol");
					lhm_columns.put("TipoDocumento", "Tipo de documento");
					lhm_columns.put("Documento", "Documento");
					lhm_columns.put("PrimerNombre", "Nombre");
					lhm_columns.put("ErrorIntervinienteMasivo", "Error");

					try
					{
						arch = lef_files.getXSLFromCollection(
							    generarArchivoSalidaIntervinientes(lci_resultadoProcesarintervinientes), lhm_columns
							);
					}
					catch(IOException le_e)
					{
						clh_LOGGER.error("genera", le_e);
					}

					setTablaSolicitudInterviniente(null);

					InputStream stream = new ByteArrayInputStream(arch);
					isc_resultadoCargueIntervinientes = new DefaultStreamedContent(
						    stream, TipoContenidoCommon.XLS_XLSX_XXLS, "ResultadoCargueIntervinientes.xlsx"
						);

					addMessage(MessagesKeys.ERROR_CARGUE_EXCEL);
					PrimeFaces.current().ajax().update(is_messageIdGrowl);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}
	}

	/**
	 * Método encargado de generar un <code>DefaultStreamedContent</code> con la
	 * imagen resultante de la consulta en la BD.
	 * @throws B2BException
	 *
	 */
	public void descargarPlantillaCargueIntervinientesMasivos()
	    throws B2BException
	{
		Constantes lc_constante;

		lc_constante = irr_registroRemote.descargarPlantilla(
			    ConstanteCommon.CARGUE_EXCEL_INTERVINIENTES, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
			);

		if(lc_constante != null)
			setImagenPlantillaIntervMasivos(
			    generarArchivosDescarga(
			        lc_constante.getImagenes(), TipoContenidoCommon.XLS_XLSX_XXLS,
			        ConstanteCommon.NOMBRE_ARCHIVO_GENERADO + IdentificadoresCommon.SIMBOLO_GUION_BAJO
			        + lc_constante.getIdConstante() + lc_constante.getTipoArchivo()
			    )
			);
	}

	/**
	 * Eliminar interviniente.
	 *
	 * @param ap_parametros correspondiente al valor del tipo de objeto SolicitudInterviniente
	 */
	public void eliminarInterviniente(SolicitudInterviniente ap_parametros)
	{
		try
		{
			if(ap_parametros != null)
			{
				Collection<SolicitudInterviniente> lcsi_cllSolicitudInterviniente;

				lcsi_cllSolicitudInterviniente = getTablaSolicitudInterviniente();

				if(CollectionUtils.isValidCollection(lcsi_cllSolicitudInterviniente))
				{
					irr_registroRemote.eliminarInterviniente(ap_parametros, getUserId());

					lcsi_cllSolicitudInterviniente.remove(ap_parametros);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
		}
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @param acsi_resultado correspondiente al valor del tipo de objeto Collection<SolicitudInterviniente>
	 * @return devuelve el valor de Collection
	 *
	 */
	public Collection<Interviniente> generarArchivoSalidaIntervinientes(
	    Collection<SolicitudInterviniente> acsi_resultado
	)
	{
		Collection<Interviniente> lci_salida;

		lci_salida = new ArrayList<Interviniente>();

		for(SolicitudInterviniente lsi_actual : acsi_resultado)
		{
			if(lsi_actual != null)
			{
				Persona lp_persona;

				lp_persona = lsi_actual.getPersona();

				if(lp_persona != null)
				{
					Interviniente li_interviniente;

					li_interviniente = new Interviniente();

					{
						String ls_tipoDocumento;

						ls_tipoDocumento = lp_persona.getIdDocumentoTipo();

						li_interviniente.setTipoDocumento(ls_tipoDocumento);
						li_interviniente.setRol(lsi_actual.getRolPersona());
						li_interviniente.setDocumentoError(lp_persona.getNumeroDocumento());

						if(
						    StringUtils.isValidString(ls_tipoDocumento)
							    && ls_tipoDocumento.equalsIgnoreCase(IdentificadoresCommon.NIT)
						)
							li_interviniente.setPrimerNombre(lp_persona.getRazonSocial());
						else
						{
							StringBuilder lsb_sb;

							lsb_sb = new StringBuilder();

							if(StringUtils.isValidString(lp_persona.getPrimerNombre()))
								lsb_sb.append(lp_persona.getPrimerNombre() + " ");

							if(StringUtils.isValidString(lp_persona.getSegundoNombre()))
								lsb_sb.append(lp_persona.getSegundoNombre() + " ");

							if(StringUtils.isValidString(lp_persona.getPrimerApellido()))
								lsb_sb.append(lp_persona.getPrimerApellido() + " ");

							if(StringUtils.isValidString(lp_persona.getSegundoApellido()))
								lsb_sb.append(lp_persona.getSegundoApellido());

							li_interviniente.setPrimerNombre(lsb_sb.toString());
						}

						li_interviniente.setErrorIntervinienteMasivo(lsi_actual.getErrorIntervinienteMasivo());

						lci_salida.add(li_interviniente);
					}
				}
			}
		}

		return lci_salida;
	}

	/**
	 * Metodo encargado de procesar el cargue de excel de intervinientes.
	 *
	 * @param afue_event Argumento de tipo <code>FileUploadEvent</code> que contiene los bytes del archivo.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void procesarExcelIntervinientes(FileUploadEvent afue_event)
	    throws B2BException
	{
		procesarExcelIntervinientes(afue_event, null, false);
	}

	/**
	 * Metodo encargado de procesar el cargue de excel de intervinientes.
	 *
	 * @param afue_event Argumento de tipo <code>FileUploadEvent</code> que contiene los bytes del archivo.
	 * @param ab_propagarExcepcion Argumento de tipo <code>boolean</code> que indica si se debe propagar excepción <code>true</code> o no <code>false</code>.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void procesarExcelIntervinientes(
	    FileUploadEvent afue_event, String as_idSolicitud, boolean ab_propagarExcepcion
	)
	    throws B2BException
	{
		try
		{
			if(afue_event != null)
			{
				String       ls_mensaje = null;
				UploadedFile luf_file;

				luf_file                = afue_event.getFile();

				if(luf_file != null)
				{
					byte[] lba_excelFile;
					String ls_fileName;

					lba_excelFile     = luf_file.getContents();
					ls_fileName       = luf_file.getFileName();

					if((lba_excelFile != null) && (StringUtils.isValidString(ls_fileName)))
						FileLoadIntervinientes(lba_excelFile, ls_fileName, as_idSolicitud, getUserId());
				}
				else
					throw new B2BException(ErrorKeys.ERROR_CARGUE_ARCHIVOS_EXCEL);

				if(!StringUtils.isValidString(ls_mensaje))
					ls_mensaje = MessagesKeys.ARCHIVO_CARGUE_MASIVO_TERMINADO;
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			if(ab_propagarExcepcion)
				throw lb2be_e;
		}
		catch(Exception lb2be_e)
		{
			addMessage("E", lb2be_e.getMessage());

			if(ab_propagarExcepcion)
				throw new B2BException(ErrorKeys.CONTAINER_ERROR, lb2be_e);
		}
	}
}
