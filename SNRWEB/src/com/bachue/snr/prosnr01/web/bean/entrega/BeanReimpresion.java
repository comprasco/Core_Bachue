package com.bachue.snr.prosnr01.web.bean.entrega;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.biometrico.servicios.ws.ObtenerFirmaDTO;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.model.entrega.TramiteTurno;
import com.bachue.snr.prosnr01.model.sdb.acc.DocumentosSalida;
import com.bachue.snr.prosnr01.model.sdb.acc.Persona;
import com.bachue.snr.prosnr01.model.sdb.pgn.CausalReimpresion;

import com.bachue.snr.prosnr01.web.util.EntregaUI;

import org.primefaces.PrimeFaces;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


/**
 * Clase que contiene todos las propiedades y funcionalidad de
 * BeanReimpresion.
 *
 * @author Sebastian Sanchez
 */
@SessionScoped
@ManagedBean(name = "beanReimpresion")
public class BeanReimpresion extends BeanDetalleEntrega implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -1073032689893083677L;

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanReimpresion.class);

	/** Propiedad icr documentos reimpresion persona. */
	private Collection<DocumentosSalida> icds_documentosReimpresionPersona;

	/** Propiedad icr documentos reimpresion. */
	private Collection<TramiteTurno> icth_documentosReimpresion;

	/** Propiedad ir reimpresion documento. */
	private DocumentosSalida ids_reimpresionDocumento;

	/** Propiedad ieui entrega reimpresion. */
	private EntregaUI ieui_entregaReimpresion;

	/** Propiedad ip persona reimpresion. */
	private Persona ip_personaReimpresion;

	/** Propiedad is id turno reimpresion. */
	private String is_idTurnoReimpresion;

	/** Propiedad is nir reimpresion. */
	private String is_nirReimpresion;

	/** Propiedad is turno reimprimir. */
	private String is_turnoReimprimir;

	/** Propiedad is url reimpresion. */
	private String is_urlReimpresion;

	/** Propiedad ib impresion final. */
	private boolean ib_impresionFinal;

	/** Propiedad ib mostrar documentos. */
	private boolean ib_mostrarDocumentos;

	/** Propiedad ib mostrar documentos reimprimir. */
	private boolean ib_mostrarDocumentosReimprimir;

	/** Propiedad ib mostrar Generar Documento Reimpresion. */
	private boolean ib_mostrarGenerarDocumentoReimpresion;

	/** Propiedad ib mostrar visualizar Generar Documento Reimpresion. */
	private boolean ib_mostrarVisualizarGenerarDocumentoReimpresion;

	/** Propiedad ib mostrar documentos reimprimir. */
	private boolean ib_seleccionadoReimpresion;

	/** Propiedad ii contador reimpresion. */
	private int ii_contadorReimpresion;

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
	 * Modifica el valor de documentos reimpresion.
	 *
	 * @param acds_cds
	 *                   asigna el valor a la propiedad documentos reimpresion
	 */
	public void setDocumentosReimpresion(Collection<TramiteTurno> acds_cds)
	{
		icth_documentosReimpresion = acds_cds;
	}

	/**
	 * Retorna el valor de documentos reimpresion.
	 *
	 * @return el valor de documentos reimpresion
	 */
	public Collection<TramiteTurno> getDocumentosReimpresion()
	{
		return icth_documentosReimpresion;
	}

	/**
	 * Modifica el valor de documentos reimpresion persona.
	 *
	 * @param acds_cds
	 *                   asigna el valor a la propiedad documentos reimpresion persona
	 */
	public void setDocumentosReimpresionPersona(Collection<DocumentosSalida> acds_cds)
	{
		icds_documentosReimpresionPersona = acds_cds;
	}

	/**
	 * Retorna el valor de documentos reimpresion persona.
	 *
	 * @return el valor de documentos reimpresion persona
	 */
	public Collection<DocumentosSalida> getDocumentosReimpresionPersona()
	{
		return icds_documentosReimpresionPersona;
	}

	/**
	 * Modifica el valor de entrega reimpresion.
	 *
	 * @param ae_e asigna el valor a la propiedad entrega reimpresion
	 */
	public void setEntregaReimpresion(EntregaUI ae_e)
	{
		ieui_entregaReimpresion = ae_e;
	}

	/**
	 * Retorna el valor de entrega reimpresion.
	 *
	 * @return el valor de datos entrega reimpresion
	 */
	public EntregaUI getEntregaReimpresion()
	{
		return ieui_entregaReimpresion;
	}

	/**
	 * Modifica el valor de id turno reimpresion.
	 *
	 * @param as_s
	 *                 asigna el valor a la propiedad id turno reimpresion
	 */
	public void setIdTurnoReimpresion(String as_s)
	{
		is_idTurnoReimpresion = as_s;
	}

	/**
	 * Retorna el valor de id turno reimpresion.
	 *
	 * @return el valor de id turno reimpresion
	 */
	public String getIdTurnoReimpresion()
	{
		return is_idTurnoReimpresion;
	}

	/**
	 * Modifica el valor de impresion final.
	 *
	 * @param ab_b
	 *                 asigna el valor a la propiedad mostrar documentos
	 */
	public void setImpresionFinal(boolean ab_b)
	{
		ib_impresionFinal = ab_b;
	}

	/**
	 * Retorna el valor de impresion final.
	 *
	 * @return el valor de impresion final
	 */
	public boolean isImpresionFinal()
	{
		return ib_impresionFinal;
	}

	/**
	 * Modifica el valor de mostrar documentos.
	 *
	 * @param ab_b
	 *                 asigna el valor a la propiedad mostrar documentos
	 */
	public void setMostrarDocumentos(boolean ab_b)
	{
		ib_mostrarDocumentos = ab_b;
	}

	/**
	 * Retorna el valor de mostrar documentos.
	 *
	 * @return el valor de mostrar documentos
	 */
	public boolean isMostrarDocumentos()
	{
		return ib_mostrarDocumentos;
	}

	/**
	 * Modifica el valor de mostrar documentos reimprimir.
	 *
	 * @param ab_b
	 *                 asigna el valor a la propiedad mostrar documentos reimprimir
	 */
	public void setMostrarDocumentosReimprimir(boolean ab_b)
	{
		ib_mostrarDocumentosReimprimir = ab_b;
	}

	/**
	 * Retorna el valor de mostrar documentos reimprimir.
	 *
	 * @return el valor de mostrar documentos reimprimir
	 */
	public boolean isMostrarDocumentosReimprimir()
	{
		return ib_mostrarDocumentosReimprimir;
	}

	/**
	 * Modifica el valor de MostrarGenerarDocumentoReimpresion.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setMostrarGenerarDocumentoReimpresion(boolean ab_b)
	{
		ib_mostrarGenerarDocumentoReimpresion = ab_b;
	}

	/**
	 * Valida la propiedad mostrar generar documento reimpresion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isMostrarGenerarDocumentoReimpresion()
	{
		return ib_mostrarGenerarDocumentoReimpresion;
	}

	/**
	 * Modifica el valor de MostrarVisualizarGenerarDocumentoReimpresion.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setMostrarVisualizarGenerarDocumentoReimpresion(boolean ab_b)
	{
		ib_mostrarVisualizarGenerarDocumentoReimpresion = ab_b;
	}

	/**
	 * Valida la propiedad mostrar visualizar generar documento reimpresion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isMostrarVisualizarGenerarDocumentoReimpresion()
	{
		return ib_mostrarVisualizarGenerarDocumentoReimpresion;
	}

	/**
	 * Modifica el valor de nir reimpresion.
	 *
	 * @param as_s
	 *                 asigna el valor a la propiedad nir reimpresion
	 */
	public void setNirReimpresion(String as_s)
	{
		is_nirReimpresion = as_s;
	}

	/**
	 * Retorna el valor de nir reimpresion.
	 *
	 * @return el valor de nir reimpresion
	 */
	public String getNirReimpresion()
	{
		return is_nirReimpresion;
	}

	/**
	 * Modifica el valor de persona reimpresion.
	 *
	 * @param ap_p asigna el valor a la propiedad persona reimpresion
	 */
	public void setPersonaReimpresion(Persona ap_p)
	{
		ip_personaReimpresion = ap_p;
	}

	/**
	 * Retorna el valor de persona reimpresion.
	 *
	 * @return el valor de datos persona reimpresion
	 */
	public Persona getPersonaReimpresion()
	{
		return ip_personaReimpresion;
	}

	/**
	 * Modifica el valor de reimpresion documento.
	 *
	 * @param ads_ds
	 *                   asigna el valor a la propiedad reimpresion documento
	 */
	public void setReimpresionDocumento(DocumentosSalida ads_ds)
	{
		ids_reimpresionDocumento = ads_ds;
	}

	/**
	 * Retorna el valor de reimpresion documento.
	 *
	 * @return el valor de reimpresion documento
	 */
	public DocumentosSalida getReimpresionDocumento()
	{
		return ids_reimpresionDocumento;
	}

	/**
	 * Modifica el valor de SeleccionadoReimpresion.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setSeleccionadoReimpresion(boolean ab_b)
	{
		ib_seleccionadoReimpresion = ab_b;
	}

	/**
	 * Valida la propiedad seleccionado reimpresion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isSeleccionadoReimpresion()
	{
		return ib_seleccionadoReimpresion;
	}

	/**
	 * Modifica el valor de turno reimprimir.
	 *
	 * @param as_s
	 *                 asigna el valor a la propiedad turno reimprimir
	 */
	public void setTurnoReimprimir(String as_s)
	{
		is_turnoReimprimir = as_s;
	}

	/**
	 * Retorna el valor de turno reimprimir.
	 *
	 * @return el valor de turno reimprimir
	 */
	public String getTurnoReimprimir()
	{
		return is_turnoReimprimir;
	}

	/**
	 * Modifica el valor de url reimpresion.
	 *
	 * @param as_s
	 *                 asigna el valor a la propiedad url reimpresion
	 */
	public void setURLReimpresion(String as_s)
	{
		is_urlReimpresion = as_s;
	}

	/**
	 * Retorna el valor de url reimpresion.
	 *
	 * @return el valor de url reimpresion
	 */
	public String getURLReimpresion()
	{
		return is_urlReimpresion;
	}

	/**
	 * Metodo para abrir el pad de firmas.
	 */
	public void abrirPadFirmasReimpresion()
	{
		try
		{
			Collection<DocumentosSalida> lcds_documentosSalidaPersona;
			boolean                      lb_mostrar;

			lcds_documentosSalidaPersona     = getDocumentosReimpresionPersona();
			lb_mostrar                       = false;

			if(CollectionUtils.isValidCollection(lcds_documentosSalidaPersona))
			{
				Iterator<DocumentosSalida> lids_iterador;

				lids_iterador = lcds_documentosSalidaPersona.iterator();

				while(lids_iterador.hasNext())
				{
					DocumentosSalida lds_temp;

					lds_temp = lids_iterador.next();

					if(lds_temp != null)
					{
						lb_mostrar = lds_temp.isSeleccionado();

						String ls_causalReimpresion;

						ls_causalReimpresion = lds_temp.getCausalReimpresionValor();

						if(!StringUtils.isValidString(ls_causalReimpresion) && lb_mostrar)
							throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_CAUSAL_REIMPRESION);
					}
				}

				String ls_url;

				ls_url = generarURLPadFirmasReimpresion();
				PrimeFaces.current().executeScript("PF('statusDispositivo').show();");

				if(StringUtils.isValidString(ls_url))
					PrimeFaces.current().executeScript("abrirURLBioClient('" + ls_url + "');");
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("abrirPadFirmasReimpresion", lb2be_e);
			addMessage(lb2be_e);
		}
	}

	/**
	 * Metodo para traer los procesos de la base de datos.
	 *
	 * @return Colección de procesos resultante de la consulta
	 */
	public Collection<CausalReimpresion> cargarCausalReimpresion()
	{
		Collection<CausalReimpresion> lcr_causalReimpresion;

		lcr_causalReimpresion = new ArrayList<CausalReimpresion>();

		try
		{
			lcr_causalReimpresion = ier_entregaRemote.cargarCausalReimpresion(
				    getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcr_causalReimpresion;
	}

	/**
	 * Metodo para cargar los documentos a reimprimir asociados un idTurno.
	 *
	 * @return el valor collection de reimpresion
	 */
	public Collection<DocumentosSalida> cargarDocumentosAReimprimir()
	{
		Collection<DocumentosSalida> lcr_documentosAReimprimir;

		lcr_documentosAReimprimir = null;

		try
		{
			lcr_documentosAReimprimir = ier_entregaRemote.cargarDocumentosAReimprimir(
				    getTurnoReimprimir(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

			if(CollectionUtils.isValidCollection(lcr_documentosAReimprimir))
			{
				setDocumentosReimpresionPersona(lcr_documentosAReimprimir);
				setMostrarDocumentosReimprimir(true);
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcr_documentosAReimprimir;
	}

	/**
	 * Metodo para cargar los documentos asociados a un nir o a un idTurno.
	 *
	 * @return el valor collection de reimpresion
	 */
	public Collection<TramiteTurno> cargarDocumentosReimpresion()
	{
		Collection<TramiteTurno> lcr_documentosSalida;

		lcr_documentosSalida = null;

		try
		{
			lcr_documentosSalida = ier_entregaRemote.cargarDocumentosReimpresion(
				    getIdTurnoReimpresion(), getNirReimpresion(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

			if(CollectionUtils.isValidCollection(lcr_documentosSalida))
			{
				setDocumentosReimpresion(lcr_documentosSalida);
				setMostrarDocumentos(true);
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcr_documentosSalida;
	}

	/**
	 * Método para limpiar la pantalla.
	 */
	public void clean()
	{
		setNirReimpresion(null);
		setIdTurnoReimpresion(null);
		setDocumentosReimpresion(null);
		setMostrarDocumentos(false);
		setReimpresionDocumento(null);
		setEntregaReimpresion(null);
		setPersonaReimpresion(null);
		setTurnoReimprimir(null);
		setDocumentosReimpresionPersona(null);
		setMostrarDocumentosReimprimir(false);
		setMostrarGenerarDocumentoReimpresion(false);
		setMostrarVisualizarGenerarDocumentoReimpresion(false);
		setSeleccionadoReimpresion(false);
		setImpresionFinal(false);
		setURLReimpresion(null);
		ii_indiceImpresion = 0;
	}

	/**
	 * Consulta y carga en pantalla toda la información relacionada a un turno seleccionado.
	 *
	 * @param att_tt Argumento de tipo <code>TramiteTurno</code> que contiene el trámite turno de la operación.
	 * @return el valor Variable de tipo <code>String</code> que contiene el detalle de reimpresión.
	 */
	public String consultaDetallada(TramiteTurno att_tt)
	{
		String ls_detalleReimpresion;

		ls_detalleReimpresion = null;

		try
		{
			if(att_tt != null)
			{
				Persona lp_personaReimpreson;

				lp_personaReimpreson = ier_entregaRemote.cargarDatosPersonaReimpresion(
					    att_tt.getIdTurno(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);

				setIdEtapa(NumericUtils.getLong(att_tt.getIdEtapa()));
				setFechaEntrega(att_tt.getFechaAsignacion());
				setPersonaReimpresion(lp_personaReimpreson);
				setTurnoReimprimir(att_tt.getIdTurno());
				ii_indiceImpresion = 0;

				cargarTurno(att_tt);

				{
					EntregaUI leui_entrega;

					leui_entrega = getEntrega();

					if(leui_entrega != null)
						setEntregaReimpresion(leui_entrega);
				}

				ls_detalleReimpresion = NavegacionCommon.DETALLE_REIMPRESION;
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update("fReimpresion");
		}

		return ls_detalleReimpresion;
	}

	/** {@inheritdoc} */
	public void firma()
	{
		Persona lp_persona;

		lp_persona = getPersonaReimpresion();

		if(lp_persona != null)
		{
			String    ls_numeroDocumento;
			EntregaUI leui_entrega;

			ls_numeroDocumento     = lp_persona.getNumeroDocumento();
			leui_entrega           = getEntregaReimpresion();

			if(StringUtils.isValidString(ls_numeroDocumento) && (leui_entrega != null))
			{
				String ls_idTurno;

				ls_idTurno = leui_entrega.getIdTurno();

				if(StringUtils.isValidString(ls_idTurno) && StringUtils.isValidString(ls_numeroDocumento))
				{
					StringBuilder lsb_builder;

					lsb_builder = new StringBuilder(ls_idTurno);

					lsb_builder.append(ls_numeroDocumento);
					setIdFirma(lsb_builder.toString());
					setSeleccionadoReimpresion(false);
					setMostrarGenerarDocumentoReimpresion(true);
				}
			}
		}
	}

	/**
	 * Método para Generar documento.
	 */
	public void generarDocumento()
	{
		try
		{
			Collection<DocumentosSalida> lcds_documentos;

			lcds_documentos = filtrarDocumentos();

			if(CollectionUtils.isValidCollection(lcds_documentos))
			{
				ii_contadorReimpresion = lcds_documentos.size();

				Persona lp_persona;

				lp_persona = getPersonaReimpresion();

				if(lp_persona != null)
				{
					EntregaUI leui_entrega;
					leui_entrega = getEntregaReimpresion();

					if(leui_entrega != null)
					{
						String ls_idTurno;

						ls_idTurno = leui_entrega.getIdTurno();

						if(StringUtils.isValidString(ls_idTurno))
						{
							String ls_idFirma;

							ls_idFirma = getIdFirma();

							if(StringUtils.isValidString(ls_idFirma))
							{
								ObtenerFirmaDTO lofdto_firma;

								lofdto_firma = new ObtenerFirmaDTO(getUserId(), ls_idFirma);

								setDocumentoEntrega(
								    ier_entregaRemote.generarDocumentoReimpresion(
								        lofdto_firma, ls_idTurno, lp_persona, lcds_documentos, getUserId(),
								        getLocalIpAddress(), getRemoteIpAddress()
								    )
								);
								setMostrarGenerarDocumentoReimpresion(false);
								setMostrarVisualizarGenerarDocumentoReimpresion(true);
							}
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("generarDocumento", lb2be_e);
			addMessage(lb2be_e);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("generarDocumento", le_e);
		}

		PrimeFaces.current().ajax().update("fDetalleReimpresion:padFirmasInter");
	}

	/**
	 * Metodo encargado de generar la URL del pad de firmas.
	 *
	 * @return el Variable de tipo <code>String</code> que contiene la url del pad de firmas.
	 */
	public String generarURLPadFirmasReimpresion()
	{
		Persona lp_persona;
		String  ls_url;

		lp_persona     = getPersonaReimpresion();
		ls_url         = null;

		if(lp_persona != null)
		{
			EntregaUI le_entrega;

			le_entrega = getEntregaReimpresion();

			if(le_entrega != null)
				ls_url = generarURLBioClient(
					    le_entrega.getIdTurno(), lp_persona.getNumeroDocumento(), lp_persona.getIdDocumentoTipo()
					);
		}

		return ls_url;
	}

	/** {@inheritdoc} */
	public void guardarDocumentoEntrega()
	    throws B2BException
	{
		try
		{
			EntregaUI leui_entrega;

			leui_entrega = getEntregaReimpresion();

			if(leui_entrega != null)
			{
				String ls_idTurno;

				ls_idTurno = leui_entrega.getIdTurno();

				if(StringUtils.isValidString(ls_idTurno))
				{
					ier_entregaRemote.guardarDocumentoOWCC(
					    ls_idTurno, new ObtenerFirmaDTO(getUserId(), getIdFirma()), getUserId(), getLocalIpAddress(),
					    getRemoteIpAddress()
					);

					setDocumentoEnOwcc(true);
					setMostrarVisualizarGenerarDocumentoReimpresion(false);
					setImpresionFinal(true);
					PrimeFaces.current().executeScript("PF('botonImprimir').enable()");
					addMessage(MessagesKeys.PROCESO_COMPLETADO);
				}
				else
					throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
			}
			else
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("guardarDocumentoEntrega", le_e);
			addMessage(new B2BException(le_e.getMessage()));
		}
	}

	/**
	 * Imprime los documentos correspondientes a los datos de la pantalla.
	 *
	 * @param as_pantalla Variable que indica desde que pantalla se ejecuta.
	 */
	public void imprimirDocumentos(String as_pantalla)
	{
		try
		{
			boolean lb_final;

			lb_final = false;

			imprimirDocumentos(false, as_pantalla);

			ii_contadorReimpresion--;

			if(ii_contadorReimpresion == 0)
			{
				Collection<DocumentosSalida> lcds_documentosSalida;

				lcds_documentosSalida = filtrarDocumentos();

				if(CollectionUtils.isValidCollection(lcds_documentosSalida))
					ier_entregaRemote.salvarReimpresionDocumentos(
					    lcds_documentosSalida, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);

				lb_final = true;
			}

			{
				Collection<DocumentosSalida> lcds_documentoEstado;

				lcds_documentoEstado = getDocumentosReimpresionPersona();

				if(CollectionUtils.isValidCollection(lcds_documentoEstado))
				{
					for(DocumentosSalida lc_temp : lcds_documentoEstado)
					{
						if(lc_temp != null)
							lc_temp.setEstadoImpresion(EstadoCommon.IMPRESO);
					}
				}
			}

			if(lb_final)
			{
				Collection<DocumentosSalida> lcds_documentosSalidaFinal;

				lcds_documentosSalidaFinal = ier_entregaRemote.cargarDocumentosAReimprimir(
					    getTurnoReimprimir(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);

				if(CollectionUtils.isValidCollection(lcds_documentosSalidaFinal))
					setDocumentosReimpresionPersona(lcds_documentosSalidaFinal);

				addMessage(MessagesKeys.PROCESO_COMPLETADO);
				PrimeFaces.current().ajax().update("fDetalleReimpresion:idDtDocsImprimir");
			}
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("salvarReimpresionDocumentos", le_e);
			addMessage(new B2BException(le_e.getMessage()));
		}
	}

	/**
	 * Imprime los documentos.
	 *
	 * @param ab_boton boolean que indica si se ejecutó mediante la pantalla.
	 * @param as_pantalla Variable que indica desde que pantalla se ejecuta.
	 */
	public void imprimirDocumentos(boolean ab_boton, String as_pantalla)
	{
		EntregaUI leui_entrega;

		leui_entrega = getEntregaReimpresion();

		if(leui_entrega != null)
			imprimirDocumentos(
			    ab_boton, as_pantalla, leui_entrega.getIdTurno(), ":idCBEnviar", getDocumentosReimpresionPersona()
			);
	}

	/**
	 * Retorna el valor del objeto de String con la vista.
	 *
	 * @return devuelve el valor de String
	 */
	public String returnPages()
	{
		String ls_return;

		ls_return = NavegacionCommon.REIMPRESION;

		try
		{
			clear(true);
			clean();
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			ls_return = null;
		}

		return ls_return;
	}

	/**
	 * Metodo para generar la URL del pad de firmas.
	 */
	public void validarSeleccionado()
	{
		Collection<DocumentosSalida> lcds_documentosSalidaPersona;

		boolean                      lb_mostrar;

		lcds_documentosSalidaPersona     = getDocumentosReimpresionPersona();
		lb_mostrar                       = false;

		if(CollectionUtils.isValidCollection(lcds_documentosSalidaPersona))
		{
			{
				boolean ib_impresionFinal;

				ib_impresionFinal = isImpresionFinal();

				if(ib_impresionFinal)
				{
					setImpresionFinal(false);
					ii_indiceImpresion = 0;
				}
			}

			Iterator<DocumentosSalida> lids_iterador;

			lids_iterador = lcds_documentosSalidaPersona.iterator();

			while(lids_iterador.hasNext() && !lb_mostrar)
			{
				DocumentosSalida lds_temp;

				lds_temp = lids_iterador.next();

				if(lds_temp != null)
					lb_mostrar = lds_temp.isSeleccionado();
			}

			setSeleccionadoReimpresion(lb_mostrar);
		}
	}

	/**
	 * Filtrar documentos.
	 *
	 * @return el valor de collection
	 */
	private Collection<DocumentosSalida> filtrarDocumentos()
	{
		Collection<DocumentosSalida> lcds_documentosFiltrados;
		Collection<DocumentosSalida> lcds_documentos;

		lcds_documentosFiltrados     = null;
		lcds_documentos              = getDocumentosReimpresionPersona();

		if(CollectionUtils.isValidCollection(lcds_documentos))
		{
			lcds_documentosFiltrados = new ArrayList<DocumentosSalida>();

			for(DocumentosSalida lds_temp : lcds_documentos)
			{
				if((lds_temp != null) && lds_temp.isSeleccionado())
					lcds_documentosFiltrados.add(lds_temp);
			}
		}

		return lcds_documentosFiltrados;
	}
}
