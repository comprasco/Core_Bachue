package com.bachue.snr.prosnr01.web.bean.consulta.reportes;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.messages.Messages;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.ExtensionCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;
import com.bachue.snr.prosnr01.common.constants.ProcedenciaCommon;
import com.bachue.snr.prosnr01.common.constants.ProcesoCommon;
import com.bachue.snr.prosnr01.common.constants.TipoContenidoCommon;
import com.bachue.snr.prosnr01.common.constants.TipoCriterioBusquedaCommon;
import com.bachue.snr.prosnr01.common.utils.ExpresionRegular;

import com.bachue.snr.prosnr01.ejb.session.stateless.reference.ReferenceRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.registro.RegistroRemote;

import com.bachue.snr.prosnr01.model.integration.OWCC.DocumentoOWCC;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.TipoRecepcion;
import com.bachue.snr.prosnr01.model.sdb.bng.Imagenes;
import com.bachue.snr.prosnr01.model.sdb.pgn.CamposConsulta;
import com.bachue.snr.prosnr01.model.sdb.pgn.CirculoRegistral;
import com.bachue.snr.prosnr01.model.sdb.pgn.CriteriosDeBusqueda;

import com.bachue.snr.prosnr01.web.bean.registro.BeanRegistro;
import com.bachue.snr.prosnr01.web.util.FacesUtils;

import org.primefaces.PrimeFaces;

import org.primefaces.PrimeFaces.Ajax;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.FlowEvent;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.component.UIComponent;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;


/**
 * Clase que contiene todos las propiedades y acciones de BeanConsultaIndices.
 *
 * @author Julian Vaca
 */
@SessionScoped
@ManagedBean(name = "beanConsultaIndices")
public class BeanConsultaIndices extends BeanRegistro implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -2096507104369954298L;

	/** Constante cs_ID_FORM. */
	public static final String cs_ID_FORM = "fConsultaIndicesId";

	/** Constante cs_ID_GROWL. */
	private static final String cs_ID_GROWL = cs_ID_FORM + ":globalMsg";

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanConsultaIndices.class);
	/** Propiedad iccb criterios. */
	Collection<CriteriosDeBusqueda> iccb_criterios;
	/** Propiedad iccb_criteriosCopias. */
	Collection<CriteriosDeBusqueda> iccb_criteriosCopias;

	/** Propiedad iccc archivos cargados. */
	private Collection<CamposConsulta> iccc_archivosCargados;

	/** Propiedad iccc campos consulta. */
	private Collection<CamposConsulta> iccc_camposConsulta;

	/** Propiedad icdo documentos OWCC. */
	private Collection<DocumentoOWCC> icdo_documentosOWCC;

	/**
	 * Propiedad ici tipos documentales blob.
	 */
	private Collection<Imagenes> ici_tiposDocumentalesBlob;

	/** Propiedad ictr medios notificar. */
	private Collection<TipoRecepcion> ictr_mediosNotificar;

	/** Propiedad icdb criterios de busqueda. */
	private CriteriosDeBusqueda icdb_criteriosDeBusqueda;

	/** Propiedad icdb criterios de consulta. */
	private CriteriosDeBusqueda icdb_criteriosDeConsulta;

	/** Propiedad irr reference remote. */
	@EJB
	private ReferenceRemote irr_referenceRemote;

	/** Propiedad irr registro remote. */
	@EJB
	private RegistroRemote irr_registroRemote;

	/** Propiedad isc archivo A descargar. */
	private StreamedContent isc_archivoADescargar;

	/** Propiedad is nir. */
	private String is_nir;
	private boolean ib_deshabilitarConsultaNacional;

	/** Propiedad ib deshabilitar regional. */
	private boolean ib_deshabilitarRegional;

	/** Propiedad ib ib ocultar siguiente. */
	private boolean ib_ib_ocultarSiguiente;

	/** Propiedad ib mostrar atras. */
	private boolean ib_mostrarAtras;

	/** Propiedad ib mostrar cancelar. */
	private boolean ib_mostrarCancelar;

	/**
	 * Modifica el valor de archivo A descargar.
	 *
	 * @param asc_sc asigna el valor a la propiedad archivo A descargar
	 */
	public void setArchivoADescargar(StreamedContent asc_sc)
	{
		isc_archivoADescargar = asc_sc;
	}

	/**
	 * Retorna el valor de archivo A descargar.
	 *
	 * @return el valor de archivo A descargar
	 */
	public StreamedContent getArchivoADescargar()
	{
		return isc_archivoADescargar;
	}

	/**
	 * Modifica el valor de archivos cargados.
	 *
	 * @param accc_ccc asigna el valor a la propiedad archivos cargados
	 */
	public void setArchivosCargados(Collection<CamposConsulta> accc_ccc)
	{
		iccc_archivosCargados = accc_ccc;
	}

	/**
	 * Retorna el valor de archivos cargados.
	 *
	 * @return el valor de archivos cargados
	 */
	public Collection<CamposConsulta> getArchivosCargados()
	{
		return iccc_archivosCargados;
	}

	/**
	 * Modifica el valor de campos consulta.
	 *
	 * @param accc_ccc asigna el valor a la propiedad campos consulta
	 */
	public void setCamposConsulta(Collection<CamposConsulta> accc_ccc)
	{
		iccc_camposConsulta = accc_ccc;
	}

	/**
	 * Retorna el valor de campos consulta.
	 *
	 * @return el valor de campos consulta
	 */
	public Collection<CamposConsulta> getCamposConsulta()
	{
		return iccc_camposConsulta;
	}

	/**
	 * Modifica el valor de criterios.
	 *
	 * @param accb_cb asigna el valor a la propiedad criterios
	 */
	public void setCriterios(Collection<CriteriosDeBusqueda> accb_cb)
	{
		iccb_criterios = accb_cb;
	}

	/**
	 * Retorna el valor de criterios.
	 *
	 * @return el valor de criterios
	 */
	public Collection<CriteriosDeBusqueda> getCriterios()
	{
		return iccb_criterios;
	}

	/**
	 * Modifica el valor de criterios copias.
	 *
	 * @param accb_cb asigna el valor a la propiedad criterios copias
	 */
	public void setCriteriosCopias(Collection<CriteriosDeBusqueda> accb_cb)
	{
		iccb_criteriosCopias = accb_cb;
	}

	/**
	 * Retorna el valor de criterios copias.
	 *
	 * @return el valor de criterios copias
	 */
	public Collection<CriteriosDeBusqueda> getCriteriosCopias()
	{
		return iccb_criteriosCopias;
	}

	/**
	 * Modifica el valor de criterios de busqueda.
	 *
	 * @param acdb_cdb asigna el valor a la propiedad criterios de busqueda
	 */
	public void setCriteriosDeBusqueda(CriteriosDeBusqueda acdb_cdb)
	{
		icdb_criteriosDeBusqueda = acdb_cdb;
	}

	/**
	 * Retorna el valor de criterios de busqueda.
	 *
	 * @return el valor de criterios de busqueda
	 */
	public CriteriosDeBusqueda getCriteriosDeBusqueda()
	{
		if(icdb_criteriosDeBusqueda == null)
			icdb_criteriosDeBusqueda = new CriteriosDeBusqueda();

		return icdb_criteriosDeBusqueda;
	}

	/**
	 * Modifica el valor de criterios de consulta.
	 *
	 * @param acdb_cdb asigna el valor a la propiedad criterios de consulta
	 */
	public void setCriteriosDeConsulta(CriteriosDeBusqueda acdb_cdb)
	{
		icdb_criteriosDeConsulta = acdb_cdb;
	}

	/**
	 * Retorna el valor de criterios de consulta.
	 *
	 * @return el valor de criterios de consulta
	 */
	public CriteriosDeBusqueda getCriteriosDeConsulta()
	{
		if(icdb_criteriosDeConsulta == null)
			icdb_criteriosDeConsulta = new CriteriosDeBusqueda();

		return icdb_criteriosDeConsulta;
	}

	/**
	 * @param ab_b Modifica el valor de la propiedad deshabilitarConsultaNacional por deshabilitarConsultaNacional
	 */
	public void setDeshabilitarConsultaNacional(boolean ab_b)
	{
		ib_deshabilitarConsultaNacional = ab_b;
	}

	/**
	 * Checks if is deshabilitar consulta nacional.
	 *
	 * @return Retorna el valor de la propiedad deshabilitarConsultaNacional
	 */
	public boolean isDeshabilitarConsultaNacional()
	{
		return ib_deshabilitarConsultaNacional;
	}

	/**
	 * Sets the deshabilitar regional.
	 *
	 * @param ab_b the new deshabilitar regional
	 */
	public void setDeshabilitarRegional(boolean ab_b)
	{
		ib_deshabilitarRegional = ab_b;
	}

	/**
	 * Checks if is deshabilitar regional.
	 *
	 * @return Retorna el valor de la propiedad deshabilitarRegional
	 */
	public boolean isDeshabilitarRegional()
	{
		return ib_deshabilitarRegional;
	}

	/**
	 * Metodo que asigna el valor a la propiedad.
	 * @param acdo_cdo Valor a asignar.
	 */
	public void setDocumentosOWCC(Collection<DocumentoOWCC> acdo_cdo)
	{
		icdo_documentosOWCC = acdo_cdo;
	}

	/**
	 *  Metodo que trae el valor de la propiedad.
	 *
	 * @return the documentos OWCC
	 */
	public Collection<DocumentoOWCC> getDocumentosOWCC()
	{
		return icdo_documentosOWCC;
	}

	/** {@inheritdoc} */
	public void setMediosNotificar(Collection<TipoRecepcion> actr_ctr)
	{
		ictr_mediosNotificar = actr_ctr;
	}

	/** {@inheritdoc} */
	public Collection<TipoRecepcion> getMediosNotificar()
	{
		return ictr_mediosNotificar;
	}

	/** {@inheritdoc} */
	public void setMostrarAtras(boolean ab_b)
	{
		ib_mostrarAtras = ab_b;
	}

	/** {@inheritdoc} */
	public boolean isMostrarAtras()
	{
		return ib_mostrarAtras;
	}

	/** {@inheritdoc} */
	public void setMostrarCancelar(boolean ab_b)
	{
		ib_mostrarCancelar = ab_b;
	}

	/** {@inheritdoc} */
	public boolean isMostrarCancelar()
	{
		return ib_mostrarCancelar;
	}

	/**
	 * Modifica el valor de la propiedad.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNir(String as_s)
	{
		is_nir = as_s;
	}

	/**
	 * Retorna el valor de la propiedad.
	 *
	 * @return el valor de la propiedad.
	 */
	public String getNir()
	{
		return is_nir;
	}

	/** {@inheritdoc} */
	public void setOcultarSiguiente(boolean ab_b)
	{
		ib_ib_ocultarSiguiente = ab_b;
	}

	/** {@inheritdoc} */
	public boolean isOcultarSiguiente()
	{
		return ib_ib_ocultarSiguiente;
	}

	/**
	 * Sets the tipos documentales blob.
	 *
	 * @param aci_ci the new tipos documentales blob
	 */

	/** {@inheritdoc} */
	public void setTiposDocumentalesBlob(Collection<Imagenes> aci_ci)
	{
		ici_tiposDocumentalesBlob = aci_ci;
	}

	/**
	 * Gets the tipos documentales blob.
	 *
	 * @return the tipos documentales blob
	 */

	/** {@inheritdoc} */
	public Collection<Imagenes> getTiposDocumentalesBlob()
	{
		return ici_tiposDocumentalesBlob;
	}

	/**
	 * Metodo encargado de agregar el archivo del tipo documental seleccionado.
	 * @param afue_event Argumento de tipo FileUploadEvent que contiene el archivo examinado.
	 * @return Variable de tipo String para retornar a la pantalla desde donde se invocó la acción.
	 */
	public String agregarArchivoTipoDocumental(FileUploadEvent afue_event)
	{
		if(afue_event != null)
		{
			try
			{
				UploadedFile luf_uploadedFile;

				luf_uploadedFile = afue_event.getFile();

				if((luf_uploadedFile != null))
				{
					if(luf_uploadedFile.getSize() <= 500000)
					{
						byte[] lba_pdfFile;
						String ls_fileName;

						lba_pdfFile     = luf_uploadedFile.getContents();
						ls_fileName     = luf_uploadedFile.getFileName();

						if(
						    (lba_pdfFile != null) && StringUtils.isValidString(ls_fileName)
							    && ls_fileName.toUpperCase().endsWith(ExtensionCommon.PDF_MAYUS_PUNTO)
						)
						{
							boolean lb_isEncrypted;
							int     li_lastTrailerIndex;
							String  ls_pdfContent;

							lb_isEncrypted = false;

							if(lba_pdfFile.length < 1)
								throw new B2BException(ErrorKeys.ARCHIVO_DANADO);

							ls_pdfContent           = new String(lba_pdfFile);
							li_lastTrailerIndex     = ls_pdfContent.lastIndexOf("trailer");

							if((li_lastTrailerIndex >= 0) && (li_lastTrailerIndex < ls_pdfContent.length()))
							{
								String ls_partEncrypted;
								int    ls_firstEOFIndex;
								String ls_trailer;

								ls_partEncrypted     = ls_pdfContent.substring(
									    li_lastTrailerIndex, ls_pdfContent.length()
									);
								ls_firstEOFIndex     = ls_partEncrypted.indexOf("%%EOF");
								ls_trailer           = ls_partEncrypted.substring(0, ls_firstEOFIndex);

								lb_isEncrypted = ls_trailer.contains("/Encrypt");
							}

							if(lb_isEncrypted)
								throw new B2BException(ErrorKeys.ARCHIVO_PROTEGIDO);

							{
								Collection<Imagenes> lci_tiposDocumentalesBlob;

								lci_tiposDocumentalesBlob = getTiposDocumentalesBlob();

								if(CollectionUtils.isValidCollection(lci_tiposDocumentalesBlob))
								{
									for(Imagenes li_iterador : lci_tiposDocumentalesBlob)
									{
										if(
										    (li_iterador != null)
											    && ls_fileName.equalsIgnoreCase(li_iterador.getNombreArchivo())
										)
											throw new B2BException(ErrorKeys.ARCHIVO_NOMBRE_EXISTE);
									}
								}
							}

							{
								String       ls_observaciones;
								FacesContext lfc_context;

								ls_observaciones     = null;
								lfc_context          = FacesContext.getCurrentInstance();

								if(lfc_context != null)
									ls_observaciones = lfc_context.getExternalContext().getRequestParameterMap()
											                          .get(cs_ID_FORM + ":descripcionTipoDocumental");

								{
									Imagenes li_imagenes;

									li_imagenes = new Imagenes();

									li_imagenes.setObservaciones(ls_observaciones);
									li_imagenes.setNombreArchivo(ls_fileName);
									li_imagenes.setImagenBlob(lba_pdfFile);

									agregarTipoDocumental(li_imagenes);
								}
							}
						}
					}
					else
						throw new B2BException(ErrorKeys.ERROR_TAMANIO_ARCHIVO_PDF);
				}
				else
					throw new B2BException(ErrorKeys.ERROR_CARGUE_ARCHIVOS_PDF);

				addMessage(MessagesKeys.PROCESAMINETO_DE_CARGUE_TERMINADO);
			}
			catch(B2BException lb2be_e)
			{
				clh_LOGGER.error("agregarArchivoTipoDocumental", lb2be_e);
				addMessage(lb2be_e);
			}
			catch(Exception le_e)
			{
				clh_LOGGER.error("agregarArchivoTipoDocumental", le_e);
				addMessage(le_e.getMessage());
			}
			finally
			{
				actualizarComponente(cs_ID_GROWL);
			}
		}

		return null;
	}

	/**
	 * Metodo encargado de agregar criterios al detalle de criterios.
	 *
	 * @param acc_camposConsulta Argumento de tipo CamposConsulta que contiene los criterios a agregar.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void agregarCriterio(CamposConsulta acc_camposConsulta)
	    throws B2BException
	{
		try
		{
			if(acc_camposConsulta != null)
			{
				{
					Collection<CamposConsulta> lccc_coleccionCamposPantalla;
					lccc_coleccionCamposPantalla = acc_camposConsulta.getDataCamposConsulta();

					if(CollectionUtils.isValidCollection(lccc_coleccionCamposPantalla))
					{
						boolean lb_esNIT;
						String  ls_idTipoCriterioBusqueda;

						lb_esNIT                      = false;
						ls_idTipoCriterioBusqueda     = acc_camposConsulta.getIdTipoCriterioBusqueda();

						for(CamposConsulta lcc_campoPantalla : lccc_coleccionCamposPantalla)
						{
							if(lcc_campoPantalla != null)
							{
								String  ls_nombreTabla;
								String  ls_nombreCampo;
								boolean lb_esObligatorio;

								ls_nombreTabla     = lcc_campoPantalla.getNombreTabla();
								ls_nombreCampo     = lcc_campoPantalla.getNombreCampo();

								String ls_campo;
								ls_campo           = lcc_campoPantalla.getValorCampo();

								if(StringUtils.isValidString(ls_nombreCampo))
								{
									if(
									    StringUtils.isValidString(ls_nombreTabla)
										    && ls_nombreTabla.equalsIgnoreCase("SDB_ACC_PERSONA")
									)
									{
										if(
										    StringUtils.isValidString(ls_campo)
											    && ls_campo.equalsIgnoreCase(IdentificadoresCommon.NIT)
										)
											lb_esNIT = true;
									}

									if(lb_esNIT)
									{
										if(ls_nombreCampo.equalsIgnoreCase("RAZON_SOCIAL"))
											lb_esObligatorio = true;
										else
										{
											lb_esObligatorio = false;
											lcc_campoPantalla.setRojoPantalla(false);
										}
									}
									else
										lb_esObligatorio = lcc_campoPantalla.isObligatoriedad();

									String ls_numeroCampo;
									ls_numeroCampo = lcc_campoPantalla.getIdCampoCriterioBusqueda();

									if(
									    StringUtils.isValidString(ls_numeroCampo)
										    && StringUtils.isValidString(ls_idTipoCriterioBusqueda)
									)
									{
										if(ls_idTipoCriterioBusqueda.equalsIgnoreCase("1"))
										{
											if(ls_numeroCampo.equalsIgnoreCase("2"))
												validarCamposPanelesCriteriosConsulta(
												    lcc_campoPantalla, ls_campo, true
												);
											else if(
											    ls_numeroCampo.equalsIgnoreCase("3")
												    || ls_numeroCampo.equalsIgnoreCase("4")
												    || ls_numeroCampo.equalsIgnoreCase("5")
												    || ls_numeroCampo.equalsIgnoreCase("6")
											)
												validarCamposPanelesCriteriosConsulta(
												    lcc_campoPantalla, ls_campo, false
												);
										}
										else if(ls_idTipoCriterioBusqueda.equalsIgnoreCase("2"))
										{
											if(ls_numeroCampo.equalsIgnoreCase("2"))
												validarCamposPanelesCriteriosConsulta(
												    lcc_campoPantalla, ls_campo, true
												);
										}

										else if(ls_idTipoCriterioBusqueda.equalsIgnoreCase("3"))
										{
											if(
											    ls_numeroCampo.equalsIgnoreCase("5")
												    || ls_numeroCampo.equalsIgnoreCase("7")
												    || ls_numeroCampo.equalsIgnoreCase("9")
												    || ls_numeroCampo.equalsIgnoreCase("10")
											)
												validarCamposPanelesCriteriosConsulta(
												    lcc_campoPantalla, ls_campo, false
												);
										}
										else if(ls_idTipoCriterioBusqueda.equalsIgnoreCase("4"))
										{
											if(
											    ls_numeroCampo.equalsIgnoreCase("1")
												    || ls_numeroCampo.equalsIgnoreCase("2")
											)
												validarCamposPanelesCriteriosConsulta(
												    lcc_campoPantalla, ls_campo, true
												);
										}
										else if(ls_idTipoCriterioBusqueda.equalsIgnoreCase("5"))
										{
											if(ls_numeroCampo.equalsIgnoreCase("2"))
												validarCamposPanelesCriteriosConsulta(
												    lcc_campoPantalla, ls_campo, true
												);
										}
										else if(ls_idTipoCriterioBusqueda.equalsIgnoreCase("6"))
										{
											if(ls_numeroCampo.equalsIgnoreCase("1"))
												validarCamposPanelesCriteriosConsulta(
												    lcc_campoPantalla, ls_campo, true
												);
										}
									}

									if(lb_esObligatorio)
									{
										String ls_tipoCampo;
										ls_tipoCampo = lcc_campoPantalla.getTipoCampo();

										if(StringUtils.isValidString(ls_tipoCampo))
										{
											if(StringUtils.isValidString(ls_campo))
												lcc_campoPantalla.setRojoPantalla(false);
											else
												lcc_campoPantalla.setRojoPantalla(true);
										}
									}
								}
							}
						}
					}
				}

				String ls_userId;
				String ls_localIp;
				String ls_remoteIp;

				ls_userId       = getUserId();
				ls_localIp      = getLocalIpAddress();
				ls_remoteIp     = getRemoteIpAddress();

				irr_registroRemote.guardarCriteriosCampos(acc_camposConsulta, ls_userId, ls_localIp, ls_remoteIp);

				{
					CriteriosDeBusqueda lcdb_criteriosDeBusqueda;

					lcdb_criteriosDeBusqueda = acc_camposConsulta.getCriteriosDeBusqueda();

					if(lcdb_criteriosDeBusqueda != null)
					{
						Collection<CriteriosDeBusqueda> lccdb_criteriosDeBusqueda;

						lccdb_criteriosDeBusqueda = irr_registroRemote.consultarDetallesAgregados(
							    lcdb_criteriosDeBusqueda, ls_userId, ls_localIp, ls_remoteIp
							);

						if(CollectionUtils.isValidCollection(lccdb_criteriosDeBusqueda))
							acc_camposConsulta.setCriteriosAgregados(lccdb_criteriosDeBusqueda);
					}
				}

				acc_camposConsulta.setMostrarBotonAgregar(calcularMostrarBotonAgregar(acc_camposConsulta));
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("agregarCriterio", lb2be_e);
			addMessage(lb2be_e);
		}
		finally
		{
			actualizarComponente(cs_ID_FORM);
		}
	}

	/**
	 * Agregar separador fecha.
	 *
	 * @param acc_campoConsulta correspondiente al valor del tipo de objeto CamposConsulta
	 * @param as_valor correspondiente al valor del tipo de objeto String
	 */
	public void agregarSeparadorFecha(CamposConsulta acc_campoConsulta, String as_valor)
	{
		if(acc_campoConsulta != null)
		{
			String        ls_valor;
			StringBuilder lsb_sb;

			ls_valor     = acc_campoConsulta.getValorCampo();
			lsb_sb       = new StringBuilder(ls_valor);

			if(StringUtils.isValidString(ls_valor) && ((ls_valor.length() == 2) || (ls_valor.length() == 5)))
				lsb_sb.append("/");

			acc_campoConsulta.setValorCampo(lsb_sb.toString());
		}
	}

	/**
	 * Metodo encargado de agregar los tipos documentales a atributo en sesión para ser
	 * salvado cuando se oprima el botón siguiente en pantalla.
	 */
	public void agregarTipoDocumental()
	{
		try
		{
			agregarTipoDocumental(null);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("agregarTipoDocumental", lb2be_e);
			addMessage(lb2be_e);
		}
		finally
		{
			actualizarComponente(cs_ID_GROWL);
		}
	}

	/**
	 * Metodo encargado de agregar los tipos documentales a atributo en sesión para ser
	 * salvado cuando se oprima el botón siguiente en pantalla.
	 *
	 * @param ai_imagenes Argumento de tipo Imagenes que contiene el archivo cargado
	 * para el tipo documental.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void agregarTipoDocumental(Imagenes ai_imagenes)
	    throws B2BException
	{
		String ls_tipoDocumental;

		ls_tipoDocumental = getTipoDocumental();

		{
			FacesContext lfc_context;

			lfc_context = FacesUtils.getFacesContext();

			validateStyles(cs_ID_FORM + ":idSOMTiposDoc", lfc_context, ls_tipoDocumental, true);
		}

		if(!StringUtils.isValidString(ls_tipoDocumental))
			throw new B2BException(ErrorKeys.ERROR_SIN_TIPO_DOCUMENTAL);

		{
			Collection<Imagenes> lci_tiposDocumentalesBlob;
			Imagenes             li_imagenes;
			String               ls_observaciones;
			int                  li_contador;

			lci_tiposDocumentalesBlob     = getTiposDocumentalesBlob();
			li_imagenes                   = new Imagenes();
			ls_observaciones              = getObservacionesTipoDocumental();
			li_contador                   = 1;

			{
				String[] lsa_tipoDocumental;

				lsa_tipoDocumental = ls_tipoDocumental.split("-");

				if(CollectionUtils.isValidCollection(lsa_tipoDocumental) && (lsa_tipoDocumental.length > 1))
				{
					int    li_posicion;
					String ls_nombreTipoDocumental;
					String ls_tipoDocumentalTmp;

					li_posicion                 = 0;
					ls_nombreTipoDocumental     = lsa_tipoDocumental[li_posicion++];
					ls_tipoDocumentalTmp        = lsa_tipoDocumental[li_posicion++];

					li_imagenes.setNombreTipoDocumental(ls_nombreTipoDocumental);
					li_imagenes.setTipoDocumental(ls_tipoDocumentalTmp);
				}
			}

			if(ai_imagenes != null)
			{
				byte[] lba_archivo;

				lba_archivo = ai_imagenes.getImagenBlob();

				if(lba_archivo != null)
				{
					li_imagenes.setImagenBlob(lba_archivo);
					li_imagenes.setNombreArchivo(ai_imagenes.getNombreArchivo());
				}

				ls_observaciones = ai_imagenes.getObservaciones();
			}

			li_imagenes.setObservaciones(ls_observaciones);

			if(!CollectionUtils.isValidCollection(lci_tiposDocumentalesBlob))
				lci_tiposDocumentalesBlob = new ArrayList<Imagenes>();
			else
			{
				for(Imagenes li_iterador : lci_tiposDocumentalesBlob)
				{
					if(li_iterador != null)
					{
						int li_contadorIterador;

						li_contadorIterador = li_iterador.getContador();

						if(li_contadorIterador >= li_contador)
							li_contador = li_contadorIterador + 1;
					}
				}
			}

			li_imagenes.setContador(li_contador);

			lci_tiposDocumentalesBlob.add(li_imagenes);

			setTiposDocumentalesBlob(lci_tiposDocumentalesBlob);
			setTipoDocumental(null);
			setObservacionesTipoDocumental(null);
		}
	}

	/**
	 * Agregar valor fecha.
	 *
	 * @param acc_campoConsulta correspondiente al valor del tipo de objeto CamposConsulta
	 */
	public void agregarValorFecha(CamposConsulta acc_campoConsulta)
	{
		if(acc_campoConsulta != null)
		{
			Date ld_valor;

			ld_valor = acc_campoConsulta.getValorCampoFecha();

			if(ld_valor != null)
				acc_campoConsulta.setValorCampoFecha(ld_valor);
		}
	}

	/**
	 * Metodo encargado de llenar los datos de los campos multivalor dinámicos.
	 * @param acc_panel Argumento de tipo CamposConsulta que contiene los criterios para realizar la búsqueda.
	 */
	public void cambiarDatosMultivaloresDinamicos(CamposConsulta acc_panel)
	{
		if(acc_panel != null)
		{
			try
			{
				cambiarDatosMultivaloresCampoCriterio(acc_panel);
			}
			catch(B2BException lb2be_e)
			{
				clh_LOGGER.error("cambiarDatosMultivaloresDinamicos", lb2be_e);
				addMessage(lb2be_e);
			}
			finally
			{
				actualizarComponente(cs_ID_GROWL);
			}
		}
	}

	/**
	 * Cambiar minusculas A mayusculas.
	 *
	 * @param acc_campoConsulta correspondiente al valor del tipo de objeto CamposConsulta
	 */
	public void cambiarMinusculasAMayusculas(CamposConsulta acc_campoConsulta)
	{
		if(acc_campoConsulta != null)
		{
			String ls_valor;

			ls_valor = acc_campoConsulta.getValorCampo();

			if(StringUtils.isValidString(ls_valor))
				acc_campoConsulta.setValorCampo(ls_valor.toUpperCase());
		}
	}

	/**
	 * Cambiar valor deshabilitar consulta nacional.
	 */
	public void cambiarValorDeshabilitarConsultaNacional()
	{
		CriteriosDeBusqueda lcdb_criteriosConsulta;

		lcdb_criteriosConsulta = getCriteriosDeConsulta();

		if(lcdb_criteriosConsulta != null)
		{
			String ls_regional;

			ls_regional = lcdb_criteriosConsulta.getRegional();

			if(StringUtils.isValidString(ls_regional))
				setDeshabilitarConsultaNacional(true);
			else
				setDeshabilitarConsultaNacional(false);
		}
	}

	/**
	 * Cambiar valor deshabilitar regional.
	 */
	public void cambiarValorDeshabilitarRegional()
	{
		CriteriosDeBusqueda lcdb_criteriosConsulta;

		lcdb_criteriosConsulta = getCriteriosDeConsulta();

		if(lcdb_criteriosConsulta != null)
		{
			if(lcdb_criteriosConsulta.isConsultaNacional())
				setDeshabilitarRegional(true);
			else
				setDeshabilitarRegional(false);
		}
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @return devuelve el valor de String
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public String cancelarConsulta()
	    throws B2BException
	{
		limpiarRegistro();

		return NavegacionCommon.PRINCIPAL;
	}

	/**
	 * Cargar direccion completa.
	 *
	 * @param acc_panel correspondiente al valor del tipo de objeto CamposConsulta
	 */
	public void cargarDireccionCompleta(CamposConsulta acc_panel)
	{
		try
		{
			if(acc_panel != null)
				cargarDireccionCompletaCriteriosBusqueda(acc_panel);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("cargarDireccionCompleta", lb2be_e);
			addMessage(lb2be_e);
		}
		finally
		{
			actualizarComponente(cs_ID_GROWL);
		}
	}

	/**
	 * Metodo encargado de cargar excel para agregar valoreas a detalle criterio busqueda.
	 *
	 * @param afue_event Argumento de tipo FileUploadEvent que contiene el archivo cargado
	 * y los datos necesarios para realizar la inserción de detalle criterio busqueda.
	 * @return Variable de tipo String para volver a la pantalla sin afectar valores
	 * precargados.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public String cargarExcelCamposCriterios(FileUploadEvent afue_event)
	    throws B2BException
	{
		if(afue_event != null)
		{
			try
			{
				cargarExcelCamposCriterios(afue_event, false);
			}
			catch(B2BException lb2be_e)
			{
				clh_LOGGER.error("cargarExcelCamposCriterios", lb2be_e);
				addMessage(lb2be_e);
			}
			catch(Exception le_e)
			{
				clh_LOGGER.error("cargarExcelCamposCriterios", le_e);
				addMessage(le_e.getMessage());
			}
			finally
			{
				actualizarComponente(cs_ID_GROWL);
			}
		}

		return null;
	}

	/**
	 * Consultar persona documento indices.
	 *
	 * @param ab_mostrarAlertas the ab mostrar alertas
	 */
	public void consultarPersonaDocumentoIndices(boolean ab_mostrarAlertas)
	{
		consultarPersonaDocumentoIndices(ab_mostrarAlertas, null, cs_ID_FORM);
	}

	/**
	 * Consultar persona documento indices.
	 *
	 * @param ab_mostrarAlertas correspondiente al valor del tipo de objeto boolean
	 * @param as_tipoRecepcion correspondiente al valor de tipo recepcion
	 * @param as_idForm Argumento de tipo <code>String</code> que contiene el id del formulario desde donde se realiza la petición.
	 */
	public void consultarPersonaDocumentoIndices(boolean ab_mostrarAlertas, String as_tipoRecepcion, String as_idForm)
	{
		try
		{
			setCriteriosDeConsulta(null);
			setCriterios(null);

			consultarPersonaDocumento(ab_mostrarAlertas, as_tipoRecepcion, as_idForm);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("consultarPersonaDocumentoIndices", lb2be_e);
		}
		finally
		{
			actualizarComponente(cs_ID_GROWL);
		}
	}

	/**
	 * Descargar excel.
	 *
	 * @param acc_camposConsulta correspondiente al valor del tipo de objeto CamposConsulta
	 */
	public void descargarExcel(CamposConsulta acc_camposConsulta)
	{
		if(acc_camposConsulta != null)
		{
			byte[] lba_excel;

			lba_excel = acc_camposConsulta.getArchivoCargue();

			if(lba_excel != null)
			{
				CriteriosDeBusqueda lcdb_criteriosDeBusqueda;

				lcdb_criteriosDeBusqueda = acc_camposConsulta.getCriteriosDeBusqueda();

				if(lcdb_criteriosDeBusqueda != null)
				{
					String ls_etiquetaCampo;

					ls_etiquetaCampo = lcdb_criteriosDeBusqueda.getDescripcion();

					if(StringUtils.isValidString(ls_etiquetaCampo))
						setArchivoADescargar(
						    generarArchivosDescarga(
						        lba_excel, TipoContenidoCommon.EXCEL_FILE_2007,
						        ls_etiquetaCampo.trim() + ExtensionCommon.XLSX_PUNTO
						    )
						);
				}
			}
		}
	}

	/**
	 * Metodo encargado de eliminar criterios al detalle de criterios.
	 * @param acc_camposConsulta Argumento de tipo CamposConsulta que contiene los criterios a
	 * consultar para mostrar los detalles.
	 * @param acdb_registroAEliminar Argumento de tipo CriteriosDeBusqueda que contiene los
	 * criterios a eliminar.
	 */
	public void eliminarDetalle(CamposConsulta acc_camposConsulta, CriteriosDeBusqueda acdb_registroAEliminar)
	{
		try
		{
			if((acc_camposConsulta != null) && (acdb_registroAEliminar != null))
			{
				String ls_userId;
				String ls_localIp;
				String ls_remoteIp;

				ls_userId       = getUserId();
				ls_localIp      = getLocalIpAddress();
				ls_remoteIp     = getRemoteIpAddress();

				irr_registroRemote.deleteDetalle(acdb_registroAEliminar, ls_userId, ls_localIp, ls_remoteIp);

				{
					CriteriosDeBusqueda lcdb_criteriosDeBusqueda;

					lcdb_criteriosDeBusqueda = acc_camposConsulta.getCriteriosDeBusqueda();

					if(lcdb_criteriosDeBusqueda != null)
					{
						Collection<CriteriosDeBusqueda> lccdb_criteriosDeBusqueda;

						lccdb_criteriosDeBusqueda = irr_registroRemote.consultarDetallesAgregados(
							    lcdb_criteriosDeBusqueda, ls_userId, ls_localIp, ls_remoteIp
							);

						acc_camposConsulta.setCriteriosAgregados(lccdb_criteriosDeBusqueda);
					}
				}

				acc_camposConsulta.setMostrarBotonAgregar(calcularMostrarBotonAgregar(acc_camposConsulta));
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("eliminarDetalle", lb2be_e);
		}
		finally
		{
			actualizarComponente(cs_ID_GROWL);
		}
	}

	/**
	 * Metodo encargado de eliminar tipos documentales agregados.
	 * @param ai_imagenes Argumento de tipo Imagenes que contiene el indicador
	 * por el cual se borra el tipo documental.
	 */
	public void eliminarTipoDocumental(Imagenes ai_imagenes)
	{
		if(ai_imagenes != null)
		{
			Collection<Imagenes> lci_tiposDocumentalesBlob;

			lci_tiposDocumentalesBlob = getTiposDocumentalesBlob();

			if(CollectionUtils.isValidCollection(lci_tiposDocumentalesBlob))
			{
				Collection<Imagenes> lci_tiposDocumentales;

				lci_tiposDocumentales = new ArrayList<Imagenes>();

				for(Imagenes li_iterador : lci_tiposDocumentalesBlob)
				{
					if(li_iterador != null)
					{
						if(li_iterador.getContador() != ai_imagenes.getContador())
							lci_tiposDocumentales.add(li_iterador);
					}
				}

				setTiposDocumentalesBlob(lci_tiposDocumentales);
			}
		}
	}

	/**
	 * Metodo encargado de consultar todos los circulos registrales de una regional.
	 * @return Colección de CirculoRegistral que contiene los resultados que cumplieron con los criterios de búsqueda.
	 */
	public Collection<CirculoRegistral> findByIdRegionalOnly()
	{
		Collection<CirculoRegistral> lcr_datos;

		lcr_datos = null;

		try
		{
			CriteriosDeBusqueda lcdb_criteriosDeBusqueda;

			lcdb_criteriosDeBusqueda = getCriteriosDeConsulta();

			if(lcdb_criteriosDeBusqueda != null)
			{
				String ls_regional;

				ls_regional = lcdb_criteriosDeBusqueda.getRegional();

				if(StringUtils.isValidString(ls_regional))
				{
					CirculoRegistral lcr_parametros;
					lcr_parametros = new CirculoRegistral();

					lcr_parametros.setIdRegional(ls_regional);
					lcr_datos = irr_referenceRemote.findByIdRegionalOnly(
						    lcr_parametros, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
						);
				}
				else
					lcr_datos = irr_referenceRemote.findAllCirculosRegistralesActivos(
						    true, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
						);
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcr_datos;
	}

	/** {@inheritdoc} */
	public String flowListener(FlowEvent afe_event)
	{
		String ls_return;
		ls_return = null;

		if(afe_event != null)
		{
			String ls_oldStep;
			String ls_newStep;

			ls_oldStep     = afe_event.getOldStep();
			ls_newStep     = afe_event.getNewStep();
			ls_return      = ls_newStep;

			try
			{
				FacesContext lfc_context;

				lfc_context = FacesContext.getCurrentInstance();

				if(StringUtils.isValidString(ls_oldStep) && StringUtils.isValidString(ls_newStep))
				{
					String    ls_datosInteresado;
					String    ls_recepcionDocumentos;
					String    ls_criteriosBusqueda;
					String    ls_criteriosConsulta;
					String    ls_liquidacion;
					String    ls_impresion;
					Solicitud ls_solicitud;

					ls_datosInteresado         = "idDatosInteresado";
					ls_recepcionDocumentos     = "idRecepcionDocumentos";
					ls_criteriosBusqueda       = "idCriteriosBusqueda";
					ls_criteriosConsulta       = "idCriteriosConsulta";
					ls_liquidacion             = "idLiquidacion";
					ls_impresion               = "impresion_id";
					ls_solicitud               = getSolicitud();

					if(ls_oldStep.equalsIgnoreCase(ls_datosInteresado))
					{
						setMostrarAtras(true);
						setMostrarCancelar(true);

						limpiarEstilosDatosInteresado(cs_ID_FORM + IdentificadoresCommon.DOS_PUNTOS, lfc_context, true);

						if(ls_solicitud != null)
						{
							String ls_entidadExenta;

							ls_entidadExenta = ls_solicitud.getEntidadExenta();

							if(!StringUtils.isValidString(ls_entidadExenta))
								throw new B2BException(ErrorKeys.ERROR_SIN_ENTIDAD_EXENTA);

							ls_solicitud.setIdProcedencia(ProcedenciaCommon.ORIP_DE_ORIGEN);
							setSolicitud(ls_solicitud);
						}

						salvarInteresado();
					}
					else if(
					    ls_oldStep.equalsIgnoreCase(ls_recepcionDocumentos)
						    && ls_newStep.equalsIgnoreCase(ls_criteriosBusqueda)
					)
					{
						Collection<Imagenes> lci_ci;

						lci_ci = getTiposDocumentalesBlob();

						if(CollectionUtils.isValidCollection(lci_ci))
							irr_registroRemote.insertarImagenesConsultaIndices(
							    lci_ci, ls_solicitud, getLocalIpAddress(), getRemoteIpAddress(), getUserId()
							);
						else
						{
							ExternalContext lec_externalContext;

							lec_externalContext = FacesContext.getCurrentInstance().getExternalContext();

							addMessage(MessagesKeys.SIN_DOCUMENTOS_SOPORTE);

							if(lec_externalContext != null)
							{
								Flash lf_flash;

								lf_flash = lec_externalContext.getFlash();

								if(lf_flash != null)
								{
									lf_flash.setKeepMessages(true);
									PrimeFaces.current().ajax().update(cs_ID_GROWL);
								}
							}
						}
					}
					else if(
					    ls_oldStep.equalsIgnoreCase(ls_criteriosBusqueda)
						    && ls_newStep.equalsIgnoreCase(ls_criteriosConsulta)
					)
					{
						CriteriosDeBusqueda lcb_citeriosConsulta;

						lcb_citeriosConsulta = getCriteriosDeConsulta();

						if(lcb_citeriosConsulta != null)
						{
							String ls_tipoConsulta;
							String ls_idProcesoConsulta;

							ls_tipoConsulta          = lcb_citeriosConsulta.getTipoConsulta();
							ls_idProcesoConsulta     = lcb_citeriosConsulta.getIdProcesoConsulta();

							validateStyles(cs_ID_FORM + ":idSOMTipoConsulta", lfc_context, ls_tipoConsulta, true);
							validateStyles(cs_ID_FORM + ":idSOMTipoBusqueda", lfc_context, ls_idProcesoConsulta, true);

							if(!StringUtils.isValidString(ls_tipoConsulta))
								throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO_CONSULTA);

							if(!StringUtils.isValidString(ls_idProcesoConsulta))
								throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO_BUSQUEDA);

							{
								boolean lb_nacional;

								lb_nacional = lcb_citeriosConsulta.isConsultaNacional();

								if(!lb_nacional)
								{
									String ls_regional;
									String ls_circuloRegistral;

									ls_regional             = lcb_citeriosConsulta.getRegional();
									ls_circuloRegistral     = lcb_citeriosConsulta.getIdCirculo();

									if(
									    !StringUtils.isValidString(ls_regional)
										    && !StringUtils.isValidString(ls_circuloRegistral)
									)
										throw new B2BException(ErrorKeys.ERROR_NACIONAL_REGIONAL_REGISTRAL);

									validateStyles(cs_ID_FORM + ":idSOMRegional", lfc_context, ls_regional, true);
									validateStyles(
									    cs_ID_FORM + ":idSOMCirculo", lfc_context, ls_circuloRegistral, true
									);

									if(
									    !StringUtils.isValidString(ls_regional)
										    && !StringUtils.isValidString(ls_circuloRegistral)
									)
										throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_REGIONAL);

									if(
									    !StringUtils.isValidString(ls_circuloRegistral)
										    && !StringUtils.isValidString(ls_regional)
									)
										throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_CIRCULO_REGISTRAL);
								}
							}

							{
								Long ll_cantidadConsultas;

								ll_cantidadConsultas = lcb_citeriosConsulta.getCantidadConsultas();

								validateStyles(
								    cs_ID_FORM + ":idCantidadConsultas", lfc_context, ll_cantidadConsultas, true
								);

								if(!NumericUtils.isValidLong(ll_cantidadConsultas))
									throw new B2BException(ErrorKeys.DEBE_DIGITAR_CANTIDAD_CONSULTAS);

								if(!NumericUtils.isValidLong(ll_cantidadConsultas, 1L))
								{
									Long ll_tmp;

									ll_tmp = null;

									validateStyles(cs_ID_FORM + ":idCantidadConsultas", lfc_context, ll_tmp, true);

									throw new B2BException(ErrorKeys.CANTIDAD_CONSULTAS_MAYOR_A_CERO);
								}
							}

							{
								Collection<CriteriosDeBusqueda> lc_criteriosDeBusqueda;

								lc_criteriosDeBusqueda = getCriterios();

								if(CollectionUtils.isValidCollection(lc_criteriosDeBusqueda))
								{
									lcb_citeriosConsulta.setCriteriosSeleccionados(lc_criteriosDeBusqueda);
									lcb_citeriosConsulta.setDatosSolicitud(getSolicitud());

									irr_registroRemote.guardarCriterios(
									    lcb_citeriosConsulta, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
									);

									if(ls_solicitud != null)
									{
										String ls_entidadExenta;

										ls_entidadExenta = ls_solicitud.getEntidadExenta();

										if(
										    StringUtils.isValidString(ls_entidadExenta)
											    && ls_entidadExenta.equalsIgnoreCase(EstadoCommon.S)
										)
											ls_return = ls_liquidacion;
										else
											mostrarPaneles(null);
									}

									setMostrarAtras(false);
								}
							}

							setArchivosCargados(null);
						}
					}
					else if(
					    ls_oldStep.equalsIgnoreCase(ls_criteriosConsulta)
						    && ls_newStep.equalsIgnoreCase(ls_liquidacion)
					)
					{
						CriteriosDeBusqueda lcdb_criterioDeBusqueda;

						lcdb_criterioDeBusqueda = new CriteriosDeBusqueda();

						lcdb_criterioDeBusqueda.setIdSolicitud(ls_solicitud.getIdSolicitud());

						irr_registroRemote.validarCantidadDetalleCriterioBusqueda(
						    lcdb_criterioDeBusqueda, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
						);
					}
					else if(ls_oldStep.equalsIgnoreCase(ls_liquidacion) && ls_newStep.equalsIgnoreCase(ls_impresion))
					{
						if(isProcesoTerminado())
						{
							Solicitud lso_solicitud;

							lso_solicitud = getSolicitud();

							if(lso_solicitud != null)
							{
								ii_indiceImpresion = 0;

								setDocumentosImprimir(
								    irr_registroRemote.cargarDocumentosSolicitud(
								        IdentificadoresCommon.CONSULTAS_NO_EXENTAS, lso_solicitud.getIdSolicitud(),
								        getUserId(), getLocalIpAddress(), getRemoteIpAddress()
								    )
								);
								setOcultarSiguiente(true);
							}
						}
						else
							throw new B2BException(ErrorKeys.ERROR_TERMINAR_PROCESO);
					}
				}
			}
			catch(B2BException lb2be_e)
			{
				clh_LOGGER.error(lb2be_e);
				addMessage(lb2be_e);
				PrimeFaces.current().ajax().update(cs_ID_GROWL);
				ls_return = ls_oldStep;
			}
		}

		return ls_return;
	}

	/**
	 * Imprime los documentos.
	 *
	 * @param as_pantalla Variable que indica desde que pantalla se ejecuta.
	 */
	public void imprimirDocumentos(String as_pantalla)
	{
		imprimirDocumentos(false, as_pantalla);
	}

	/**
	 * Imprime los documentos.
	 *
	 * @param ab_boton boolean que indica si se ejecutó mediante la pantalla.
	 * @param as_pantalla Variable que indica desde que pantalla se ejecuta.
	 */
	public void imprimirDocumentos(boolean ab_boton, String as_pantalla)
	{
		imprimirDocumentos(ab_boton, as_pantalla, getNir(), ":fAccionCancelar:botonFinalizar");
	}

	/**
	 * Limpiar datos.
	 */
	public void limpiarDatos()
	{
		setCriterios(null);
		setArchivosCargados(null);
		setCamposConsulta(null);
		setMediosNotificar(null);
		setCriteriosDeBusqueda(null);
		setCriteriosDeConsulta(null);
		setArchivoADescargar(null);
		setMostrarAtras(false);
		setMostrarCancelar(false);
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException the b 2 B exception
	 */
	public Collection<CriteriosDeBusqueda> listarTiposCriteriosBusqueda()
	    throws B2BException
	{
		return listarTiposCriteriosBusqueda(
		    null, ProcesoCommon.ID_PROCESO_2, getCriteriosDeConsulta().getIdProcesoConsulta()
		);
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @param as_idTipoCriterioBusqueda the as id tipo criterio busqueda
	 * @param as_idProceso Argumento de tipo <code>String</code> que contiene el id del proceso a consultar.
	 * @param as_idProcesoConsulta Argumento de tipo <code>String</code> que contiene el id del proceso consulta.
	 * @return devuelve el valor de Collection
	 * @throws B2BException the b 2 B exception
	 */
	public Collection<CriteriosDeBusqueda> listarTiposCriteriosBusqueda(
	    String as_idTipoCriterioBusqueda, String as_idProceso, String as_idProcesoConsulta
	)
	    throws B2BException
	{
		Collection<CriteriosDeBusqueda> lccb_ccb;
		lccb_ccb = null;

		try
		{
			if(StringUtils.isValidString(as_idProceso) && StringUtils.isValidString(as_idProcesoConsulta))
			{
				lccb_ccb = irr_referenceRemote.listarTiposCriteriosBusqueda(
					    getUserId(), getLocalIpAddress(), getRemoteIpAddress(), as_idTipoCriterioBusqueda, as_idProceso,
					    as_idProcesoConsulta
					);

				setCriterios(lccb_ccb);

				if(CollectionUtils.isValidCollection(lccb_ccb))
				{
					Collection<CriteriosDeBusqueda> lccdb_criteriosCopias;

					lccdb_criteriosCopias = new ArrayList<CriteriosDeBusqueda>();

					for(CriteriosDeBusqueda lcdb_iterador : lccb_ccb)
					{
						if(lcdb_iterador != null)
						{
							String ls_idTipoCriterioBusqueda;

							ls_idTipoCriterioBusqueda = lcdb_iterador.getIdTipoCriterio();

							if(
							    StringUtils.isValidString(ls_idTipoCriterioBusqueda)
								    && !ls_idTipoCriterioBusqueda.equalsIgnoreCase(
								        TipoCriterioBusquedaCommon.DOCUMENTO_ANTIGUO_SISTEMA
								    )
							)
								lccdb_criteriosCopias.add(lcdb_iterador);
						}
					}

					setCriteriosCopias(lccdb_criteriosCopias);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			throw lb2be_e;
		}

		return lccb_ccb;
	}

	/**
	 * Mostrar paneles.
	 *
	 * @param accc_archivoCargado correspondiente al valor del tipo de objeto Collection<CamposConsulta>
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void mostrarPaneles(Collection<CamposConsulta> accc_archivoCargado)
	    throws B2BException
	{
		Collection<CamposConsulta> lccc_dataCampos;

		lccc_dataCampos = irr_registroRemote.camposPorCriterio(
			    getSolicitud(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
			);

		if(CollectionUtils.isValidCollection(lccc_dataCampos))
		{
			CriteriosDeBusqueda lcdb_criteriosDeConsulta;

			lcdb_criteriosDeConsulta = getCriteriosDeConsulta();

			if(lcdb_criteriosDeConsulta != null)
			{
				Collection<CamposConsulta> lccc_camposPanel;
				String                     ls_tipoConsulta;

				ls_tipoConsulta      = lcdb_criteriosDeConsulta.getTipoConsulta();
				lccc_camposPanel     = getCamposConsulta();

				if(
				    StringUtils.isValidString(ls_tipoConsulta) && ls_tipoConsulta.equalsIgnoreCase(EstadoCommon.MASIVO)
					    && CollectionUtils.isValidCollection(accc_archivoCargado)
					    && CollectionUtils.isValidCollection(lccc_camposPanel)
				)
				{
					Iterator<CamposConsulta> li_iteradorConsulta;
					boolean                  lb_encontrado;

					li_iteradorConsulta     = lccc_camposPanel.iterator();
					lb_encontrado           = false;

					while(li_iteradorConsulta.hasNext() && !lb_encontrado)
					{
						CamposConsulta lcc_consulta;

						lcc_consulta = li_iteradorConsulta.next();

						if(lcc_consulta != null)
						{
							Iterator<CamposConsulta> li_iteradorArchivos;
							boolean                  lb_archivoEncontrado;

							li_iteradorArchivos      = accc_archivoCargado.iterator();
							lb_archivoEncontrado     = false;

							while(li_iteradorArchivos.hasNext() && !lb_archivoEncontrado)
							{
								CamposConsulta lcc_archivos;

								lcc_archivos = li_iteradorArchivos.next();

								if(lcc_archivos != null)
								{
									String ls_idTipoConsulta;
									String ls_idTipoArchivos;

									ls_idTipoConsulta     = lcc_consulta.getIdTipoCriterioBusqueda();
									ls_idTipoArchivos     = lcc_archivos.getIdTipoCriterioBusqueda();

									if(
									    StringUtils.isValidString(ls_idTipoConsulta)
										    && StringUtils.isValidString(ls_idTipoArchivos)
										    && ls_idTipoConsulta.equalsIgnoreCase(ls_idTipoArchivos)
									)
									{
										lcc_consulta.setArchivoCargue(lcc_archivos.getArchivoCargue());

										{
											String ls_mensajeRespuesta;

											ls_mensajeRespuesta = lcc_archivos.getMensajeRespuestaCargue();

											if(StringUtils.isValidString(ls_mensajeRespuesta))
											{
												Messages lm_messages;

												lm_messages = getMessages();

												if(lm_messages != null)
												{
													String ls_mensaje;

													ls_mensaje = lm_messages.getString(ls_mensajeRespuesta);

													if(StringUtils.isValidString(ls_mensaje))
														addMessage(ls_mensajeRespuesta);
													else
														addMessage(
														    lcc_archivos.isCargueCorrecto() ? "I" : "W",
														    ls_mensajeRespuesta
														);
												}
											}
										}

										lb_encontrado            = true;
										lb_archivoEncontrado     = true;
									}
								}
							}
						}
					}
				}
				else
				{
					for(CamposConsulta lcc_iterador : lccc_dataCampos)
					{
						if(lcc_iterador != null)
							cambiarDatosMultivaloresCampoCriterio(lcc_iterador);
					}

					setCamposConsulta(lccc_dataCampos);
				}
			}
		}
	}

	/**
	 * Pre liquidar consultas.
	 */
	public void preLiquidarConsultas()
	{
		try
		{
			preLiquidar();
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("preLiquidarConsultas", lb2be_e);
			addMessage(lb2be_e);
		}
		finally
		{
			actualizarComponente(cs_ID_GROWL);
			actualizarComponente("fConsultaIndicesId:idOPDetalleLiquidacion");
		}
	}

	/**
	 * Reset.
	 */
	public void reset()
	{
		setPersona(null);
		setRegistroDatosConsultados(null);
		setMostrarAtras(false);
		setMostrarCancelar(false);
		setProcesoTerminado(false);
		setTipoDocInteresado(null);
		setDocumentoInteresado(null);
		setComentario(null);
		setCriterios(null);
		setCamposConsulta(null);
	}

	/**
	 * Metodo encargado de terminar el proceso de consultas.
	 *
	 * @throws B2BException the b 2 B exception
	 */
	public void terminarProcesoConsultas()
	    throws B2BException
	{
		terminarProcesoConsultas(false, false, "");
		buscarDetalleLiquidacion();
	}

	/**
	 * Metodo encargado de terminar el proceso de consultas.
	 *
	 * @param ab_copias Argumento de tipo <code>boolean</code> que determina si es un proceso de copias.
	 * @param ab_conDocumentosOWCC Argumento de tipo <code>boolean</code> que determina si existen documentos del OWCC para proceso de copias.
	 * @throws B2BException the b 2 B exception
	 */
	public void terminarProcesoConsultas(boolean ab_copias, boolean ab_conDocumentosOWCC, String as_observaciones)
	    throws B2BException
	{
		try
		{
			CriteriosDeBusqueda lcdb_criteriosDeBusqueda;

			lcdb_criteriosDeBusqueda = new CriteriosDeBusqueda();

			lcdb_criteriosDeBusqueda.setDatosSolicitud(getSolicitud());
			lcdb_criteriosDeBusqueda.setCopias(ab_copias);
			lcdb_criteriosDeBusqueda.setConDocumentosOWCC(ab_conDocumentosOWCC);
			lcdb_criteriosDeBusqueda.setObservaciones(as_observaciones);

			lcdb_criteriosDeBusqueda = irr_registroRemote.terminarProcesoConsultas(
				    lcdb_criteriosDeBusqueda, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

			if(lcdb_criteriosDeBusqueda != null)
			{
				byte[] lba_archivoPdf;

				lba_archivoPdf = lcdb_criteriosDeBusqueda.getArchivoPdf();

				if(lba_archivoPdf != null)
				{
					InputStream lis_stream;
					lis_stream = new ByteArrayInputStream(lba_archivoPdf);

					setArchivoPdf(
					    new DefaultStreamedContent(
					        lis_stream, TipoContenidoCommon.PDF,
					        ConstanteCommon.NOMBRE_ARCHIVO_GENERADO + ExtensionCommon.PDF_PUNTO
					    )
					);
				}

				setPdfLiquidacion(lcdb_criteriosDeBusqueda.getPdfLiquidacion());

				{
					PrimeFaces lpf_current;
					Ajax       la_ajax;

					lpf_current     = PrimeFaces.current();
					la_ajax         = lpf_current.ajax();

					la_ajax.update(cs_ID_FORM + ":idClnkRespuestaLiquidacion");
					la_ajax.update(cs_ID_FORM + ":cbDescargarPdf");

					{
						Solicitud ls_solicitud;

						ls_solicitud = lcdb_criteriosDeBusqueda.getDatosSolicitud();

						if(ls_solicitud != null)
						{
							String ls_nir;

							ls_nir = ls_solicitud.getNir();

							if(StringUtils.isValidString(ls_nir))
							{
								setNir(ls_nir);
								setMensajeTerminarProceso("Ha finalizado su solicitud bajo el NIR: " + ls_nir);
								lpf_current.executeScript("PF('idDialogNir').show();");
								la_ajax.update("fDialog2:otMensaje");

								setProcesoTerminado(true);

								limpiarDatos();
							}
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("terminarProcesoConsultas", lb2be_e);
			addMessage(lb2be_e);
			throw lb2be_e;
		}
		finally
		{
			actualizarComponente(cs_ID_GROWL);
		}
	}

	/**
	 * Validar campos paneles criterios consulta.
	 *
	 * @param acc_campoPantalla correspondiente al valor del tipo de objeto CamposConsulta
	 * @param as_campo correspondiente al valor del tipo de objeto String
	 * @param ab_validarSinGuion correspondiente al valor del tipo de objeto boolean
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void validarCamposPanelesCriteriosConsulta(
	    CamposConsulta acc_campoPantalla, String as_campo, boolean ab_validarSinGuion
	)
	    throws B2BException
	{
		if(acc_campoPantalla != null)
		{
			String ls_campoEtiqueta;
			ls_campoEtiqueta = acc_campoPantalla.getEtiquetaCampo();

			if(ab_validarSinGuion)
			{
				if(
				    StringUtils.isValidString(as_campo)
					    && ExpresionRegular.getExpresionRegular().contieneCaracteresEspecialesSinGuion(as_campo)
				)
				{
					if(StringUtils.isValidString(ls_campoEtiqueta))
					{
						Object[] aoa_messageArgs = new String[1];
						aoa_messageArgs[0] = StringUtils.getString(ls_campoEtiqueta);

						acc_campoPantalla.setRojoPantalla(true);
						throw new B2BException(ErrorKeys.ERROR_CONTIENE_CARACTERES_ESPECIALES, aoa_messageArgs);
					}
				}
				else
					acc_campoPantalla.setRojoPantalla(false);
			}
			else
			{
				if(
				    StringUtils.isValidString(as_campo)
					    && ExpresionRegular.getExpresionRegular().contieneCaracteresEspeciales(as_campo)
				)
				{
					if(StringUtils.isValidString(ls_campoEtiqueta))
					{
						Object[] aoa_messageArgs = new String[1];
						aoa_messageArgs[0] = StringUtils.getString(ls_campoEtiqueta);

						acc_campoPantalla.setRojoPantalla(true);
						throw new B2BException(ErrorKeys.ERROR_CONTIENE_CARACTERES_ESPECIALES, aoa_messageArgs);
					}
				}
				else
					acc_campoPantalla.setRojoPantalla(false);
			}
		}
	}

	/**
	 * Metodo encargado de cargar excel para agregar valoreas a detalle criterio busqueda.
	 *
	 * @param afue_event Argumento de tipo FileUploadEvent que contiene el archivo cargado
	 * y los datos necesarios para realizar la inserción de detalle criterio busqueda.
	 * @param ab_copias Argumento de tipo <code>boolean</code> que indica si es un proceso de copias <code>true</code> de la contrario <code>false</code>.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	protected void cargarExcelCamposCriterios(FileUploadEvent afue_event, boolean ab_copias)
	    throws B2BException
	{
		if(afue_event != null)
		{
			try
			{
				UploadedFile luf_uploadedFile;

				luf_uploadedFile = afue_event.getFile();

				if((luf_uploadedFile != null))
				{
					byte[] lba_file;
					String ls_fileName;

					lba_file        = luf_uploadedFile.getContents();
					ls_fileName     = luf_uploadedFile.getFileName();

					if(
					    (lba_file != null) && StringUtils.isValidString(ls_fileName)
						    && ls_fileName.toUpperCase().endsWith(ExtensionCommon.EXCEL_2007)
					)
					{
						if(lba_file.length < 1)
							throw new B2BException(ErrorKeys.ARCHIVO_DANADO);

						UIComponent luc_component;

						luc_component = afue_event.getComponent();

						if(luc_component != null)
						{
							Map<String, Object> lmso_attributes;

							lmso_attributes = luc_component.getAttributes();

							if(lmso_attributes != null)
							{
								Object lo_attribute;

								lo_attribute = lmso_attributes.get("nAttrCamposConsulta");

								if((lo_attribute != null) && lo_attribute instanceof CamposConsulta)
								{
									Collection<CamposConsulta> lccc_archivosACargar;
									CamposConsulta             lcc_camposConsulta;

									lcc_camposConsulta = (CamposConsulta)lo_attribute;

									lcc_camposConsulta.setArchivoCargue(lba_file);

									lccc_archivosACargar = new ArrayList<CamposConsulta>();
									lccc_archivosACargar.add(lcc_camposConsulta);

									if(ab_copias)
									{
										lccc_archivosACargar = irr_registroRemote.cargarExcelCamposCriteriosCopias(
											    lccc_archivosACargar, ab_copias, getUserId(), getLocalIpAddress(),
											    getRemoteIpAddress()
											);

										if(CollectionUtils.isValidCollection(lccc_archivosACargar))
										{
											CamposConsulta lcc_resultados;

											lcc_resultados = lccc_archivosACargar.iterator().next();

											if(lcc_resultados != null)
											{
												Collection<DocumentoOWCC> lcdowcc_documentos;
												lcdowcc_documentos = lcc_resultados.getDocumentosOWCC();

												if(CollectionUtils.isValidCollection(lcdowcc_documentos))
													setDocumentosOWCC(lcdowcc_documentos);
											}
										}
									}
									else
										lccc_archivosACargar = irr_registroRemote.cargarExcelCamposCriterios(
											    lccc_archivosACargar, getUserId(), getLocalIpAddress(),
											    getRemoteIpAddress()
											);

									mostrarPaneles(lccc_archivosACargar);
								}
							}
						}
					}
					else
						throw new B2BException(ErrorKeys.ERROR_CARGUE_ARCHIVOS_EXCEL);
				}
				else
					throw new B2BException(ErrorKeys.ERROR_CARGUE_ARCHIVOS_EXCEL);
			}
			catch(B2BException lb2be_e)
			{
				clh_LOGGER.error("cargarExcelCamposCriterios", lb2be_e);
			}
			catch(Exception le_e)
			{
				clh_LOGGER.error("cargarExcelCamposCriterios", le_e);
			}
		}
	}

	/**
	 * Calcular mostrar boton agregar.
	 *
	 * @param acc_camposConsulta correspondiente al valor del tipo de objeto CamposConsulta
	 * @return verdadero, si se cumple la condicion, de lo contario retorna falso
	 */
	private boolean calcularMostrarBotonAgregar(CamposConsulta acc_camposConsulta)
	{
		boolean lb_mostrarBoton;

		lb_mostrarBoton = false;

		try
		{
			CriteriosDeBusqueda lcdb_criterios;

			lcdb_criterios = getCriteriosDeConsulta();

			if((acc_camposConsulta != null) && (lcdb_criterios != null))
			{
				long ll_cantidad;
				long ll_maximo;

				ll_cantidad     = NumericUtils.getLong(lcdb_criterios.getCantidadConsultas());
				ll_maximo       = irr_registroRemote.maxConsecutivoConsultaDetalle(
					    acc_camposConsulta, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);

				lb_mostrarBoton = ll_maximo >= ll_cantidad;
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("agregarCriterio", lb2be_e);
		}
		finally
		{
			actualizarComponente(cs_ID_GROWL);
		}

		return lb_mostrarBoton;
	}
}
