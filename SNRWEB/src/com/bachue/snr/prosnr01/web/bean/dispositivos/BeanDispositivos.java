package com.bachue.snr.prosnr01.web.bean.dispositivos;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.BeanNameCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.EventoCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;

import com.bachue.snr.prosnr01.ejb.session.stateless.entrega.EntregaRemote;

import com.bachue.snr.prosnr01.model.sdb.acc.DocumentosSalida;

import com.bachue.snr.prosnr01.web.bean.BaseBean;
import com.bachue.snr.prosnr01.web.bean.entrega.BeanDetalleEntrega;
import com.bachue.snr.prosnr01.web.bean.login.BeanLogin;
import com.bachue.snr.prosnr01.web.util.FacesUtils;

import org.primefaces.PrimeFaces;

import java.io.IOException;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase que contiene todos las propiedades y funcionalidad de los dispositivos.
 *
 * @author  Jorge Patino
 * Fecha de Creacion: 27/12/2019
 */
@SessionScoped
@ManagedBean(name = "beanDispositivos")
public class BeanDispositivos extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -4309699571075165331L;

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanDispositivos.class);

	/** Propiedad ii_indice Impresion. */
	protected int ii_indiceImpresion;

	/** Propiedad ictd documentos imprimir. */
	private Collection<DocumentosSalida> icds_documentosImprimir;

	/** Propiedad ier entrega remote. */
	@EJB
	private EntregaRemote ier_entregaRemote;

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * Modifica el valor de documentos imprimir.
	 *
	 * @param acds_cds asigna el valor a la propiedad documentos imprimir
	 */
	public void setDocumentosImprimir(Collection<DocumentosSalida> acds_cds)
	{
		icds_documentosImprimir = acds_cds;
	}

	/**
	 * Retorna el valor de documentos imprimir.
	 *
	 * @return el valor de documentos imprimir
	 */
	public Collection<DocumentosSalida> getDocumentosImprimir()
	{
		return icds_documentosImprimir;
	}

	/**
	 * Evento de error cuando el BioClient responde con un protocolo de error.
	 */
	public void error()
	{
		String[] loa_evento;

		loa_evento = obtenerParametrosEvento("event");

		{
			B2BException lb2be_e;

			lb2be_e = new B2BException(ErrorKeys.OCURRIO_UN_ERROR_FUNCION, traducirMensajes(loa_evento));

			PrimeFaces.current().executeScript("PF('statusDispositivo').hide();");

			addMessage(lb2be_e);
			clh_LOGGER.error(lb2be_e);
		}
	}

	/**
	 * Se encarga de hacer la validación si hay un error al imprimir.
	 *
	 * @param as_pantalla Variable que indica desde que pantalla se está ejecutando.
	 */
	public void errorImpresion(String as_pantalla)
	{
		try
		{
			Collection<DocumentosSalida> lcds_documentosAImprimir;

			lcds_documentosAImprimir = getDocumentosImprimir();

			if(CollectionUtils.isValidCollection(lcds_documentosAImprimir))
			{
				ArrayList<DocumentosSalida> ldsa_documentosSalidaArray;

				ldsa_documentosSalidaArray = new ArrayList<DocumentosSalida>(lcds_documentosAImprimir);
				ii_indiceImpresion--;

				errorImpresion(ldsa_documentosSalidaArray.get(ii_indiceImpresion));

				{
					PrimeFaces lp_primeFaces;

					lp_primeFaces = PrimeFaces.current();

					ier_entregaRemote.actualizarEstadoImpresion(
					    cambiarEstadoDocumento(
					        lcds_documentosAImprimir, ldsa_documentosSalidaArray, ii_indiceImpresion, EstadoCommon.ERROR
					    ), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
					lp_primeFaces.executeScript(
					    "cambiarEstadoImpresion('" + construirIdEstado(ii_indiceImpresion, as_pantalla) + "', '"
					    + EstadoCommon.ERROR + "')"
					);
					lp_primeFaces.executeScript("PF('botonImprimir').enable()");
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}
	}

	/**
	 * Evento de error cuando el BioClient responde con un protocolo de error en el segundo factor de autenticacion.
	 *
	 * @throws IOException Objeto de tipo IOException, se produce cuando se encuentra algun error de tipo I/O.
	 */
	public void errorSegundoFactor()
	    throws IOException
	{
		String[] loa_evento;

		loa_evento = obtenerParametrosEvento("event");

		{
			B2BException lb2be_e;
			BeanLogin    lbl_beanLogin;
			lbl_beanLogin = (BeanLogin)FacesUtils.getFacesContext().getApplication()
					                                 .evaluateExpressionGet(
					    FacesUtils.getFacesContext(), BeanNameCommon.BEAN_LOGIN, BeanLogin.class
					);

			lbl_beanLogin.cerrarSesion();
			lb2be_e = new B2BException(ErrorKeys.OCURRIO_UN_ERROR_FUNCION, traducirMensajes(loa_evento));

			addMessage(lb2be_e);
			clh_LOGGER.error(lb2be_e);
		}
	}

	
	/**
	 * Evento de firma cuando el BioClient responde con un protocolo de satisfactorio.
	 */
	public void firma()
	{
		try
		{
			String[] loa_evento;

			loa_evento = obtenerParametrosEvento("idFirma");

			{
				String ls_idFirma;

				ls_idFirma = CollectionUtils.isValidCollection(loa_evento) ? loa_evento[0] : null;

				if(StringUtils.isValidString(ls_idFirma))
				{
					BeanDetalleEntrega lbde_bean;

					lbde_bean = (BeanDetalleEntrega)FacesUtils.obtenerInstanciaBean(
						    BeanDetalleEntrega.class, BeanNameCommon.BEAN_DETALLE_ENTREGA
						);

					if(lbde_bean != null)
						PrimeFaces.current().executeScript("accionBotonPadFirmas()");
					else
						throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
				}
				else
					throw new B2BException(
					    "Ocurrio un error de comunicacion con el sistema BioClient: Obteniendo el identificador de firma."
					);
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			clh_LOGGER.error(lb2be_e);
		}
	}

	/**
	 * Método encargado de imprimir los documentos.
	 *
	 * @param ab_boton boolean que válida si se llama desde la pantalla.
	 * @param as_pantalla Variable que contiene el id de la pantalla desde donde se está ejecutando.
	 * @param as_idTurno Variable que contiene el id del turno.
	 * @param as_boton de as boton
	 * @param acds_documentosImprimir Colección que contiene los documentos a imprimir.
	 */
	public void imprimirDocumentos(
	    boolean ab_boton, String as_pantalla, String as_idTurno, String as_boton,
	    Collection<DocumentosSalida> acds_documentosImprimir
	)
	{
		boolean                      lb_reimpresion;
		Collection<DocumentosSalida> lcds_documentosAImprimir;

		lb_reimpresion               = CollectionUtils.isValidCollection(acds_documentosImprimir);
		lcds_documentosAImprimir     = lb_reimpresion ? acds_documentosImprimir : getDocumentosImprimir();

		if(CollectionUtils.isValidCollection(lcds_documentosAImprimir))
		{
			try
			{
				boolean                     lb_continuar;
				DocumentosSalida            lds_documento;
				PrimeFaces                  lp_primeFaces;
				ArrayList<DocumentosSalida> ldsa_documentosSalidaArray;
				int                         li_cantidad;

				lp_primeFaces                  = PrimeFaces.current();
				ldsa_documentosSalidaArray     = new ArrayList<DocumentosSalida>(lcds_documentosAImprimir);
				li_cantidad                    = ldsa_documentosSalidaArray.size();
				lb_continuar                   = ii_indiceImpresion < li_cantidad;
				lds_documento                  = lb_continuar ? ldsa_documentosSalidaArray.get(ii_indiceImpresion) : null;

				if((!lb_reimpresion) || (lb_reimpresion && (lds_documento != null) && lds_documento.isSeleccionado()))
				{
					if((ii_indiceImpresion > 0) && !ab_boton)
					{
						int li_indice;

						li_indice = ii_indiceImpresion - 1;
						ier_entregaRemote.actualizarEstadoImpresion(
						    cambiarEstadoDocumento(
						        lcds_documentosAImprimir, ldsa_documentosSalidaArray, li_indice, EstadoCommon.IMPRESO
						    ), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
						);
						lp_primeFaces.executeScript(
						    "cambiarEstadoImpresion('" + construirIdEstado(li_indice, as_pantalla) + "', '"
						    + EstadoCommon.IMPRESO + "')"
						);
					}

					if(lb_continuar)
					{
						lp_primeFaces.executeScript(
						    "abrirURLBioClientImpresion('"
						    + generarURLImpresion(ldsa_documentosSalidaArray.get(ii_indiceImpresion), as_idTurno)
						    + "')"
						);
						ier_entregaRemote.actualizarEstadoImpresion(
						    cambiarEstadoDocumento(
						        lcds_documentosAImprimir, ldsa_documentosSalidaArray, ii_indiceImpresion,
						        EstadoCommon.IMPRIMIENDO
						    ), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
						);
						lp_primeFaces.executeScript(
						    "cambiarEstadoImpresion('" + construirIdEstado(ii_indiceImpresion, as_pantalla) + "', '"
						    + EstadoCommon.IMPRIMIENDO + "')"
						);
						lp_primeFaces.executeScript("PF('botonImprimir').disable()");
						ii_indiceImpresion++;
					}
					else if(!ab_boton)
					{
						addMessage(MessagesKeys.DOCUMENTOS_IMPRESOS_CORRECTAMENTE);

						if(!lb_reimpresion)
							lp_primeFaces.executeScript("mostrarElementos('" + as_pantalla + as_boton + "')");
						else
							addMessage(MessagesKeys.REIMPRESION_EXITOSA);
					}
					else
						throw new B2BException(ErrorKeys.TODOS_LOS_DOCUMENTOS_IMPRESOS);
				}
				else if(lb_reimpresion && lb_continuar)
				{
					ii_indiceImpresion++;

					imprimirDocumentos(ab_boton, as_pantalla, as_idTurno, as_boton, acds_documentosImprimir);
				}
			}
			catch(B2BException lb2be_e)
			{
				clh_LOGGER.error("imprimirDocumentos", lb2be_e);
				addMessage(lb2be_e);
			}
		}
	}

	/**
	 * Método encargado de imprimir los documentos.
	 *
	 * @param ab_boton boolean que válida si se llama desde la pantalla.
	 * @param as_pantalla Variable que contiene el id de la pantalla desde donde se está ejecutando.
	 * @param as_idTurno Variable que contiene el id del turno.
	 * @param as_boton de as boton
	 */
	public void imprimirDocumentos(boolean ab_boton, String as_pantalla, String as_idTurno, String as_boton)
	{
		imprimirDocumentos(ab_boton, as_pantalla, as_idTurno, as_boton, null);
	}

	/**
	 * Generar URL impresion.
	 *
	 * @param ads_documento de ads documento
	 * @param as_idTurno de as id turno
	 * @return de String
	 * @throws B2BException de b 2 B exception
	 */
	protected String generarURLImpresion(DocumentosSalida ads_documento, String as_idTurno)
	    throws B2BException
	{
		String ls_return;

		ls_return = null;

		if((ads_documento != null) && (StringUtils.isValidString(as_idTurno)))
		{
			try
			{
				if(ads_documento.isEnviadoOwcc())
				{
					StringBuilder lsb_builder;

					lsb_builder = new StringBuilder(IdentificadoresCommon.PROTOCOLO_BACHUE);

					lsb_builder.append(EventoCommon.PDF);
					lsb_builder.append(IdentificadoresCommon.SEPARADOR_DISPOSITIVOS);
					lsb_builder.append(ads_documento.getIdEcm());
					lsb_builder.append(IdentificadoresCommon.SEPARADOR_DISPOSITIVOS);
					lsb_builder.append(ads_documento.getIdNombreDocumento());
					lsb_builder.append(IdentificadoresCommon.SEPARADOR_DISPOSITIVOS);
					lsb_builder.append(getUserId());
					lsb_builder.append(IdentificadoresCommon.SEPARADOR_DISPOSITIVOS);
					lsb_builder.append(as_idTurno);
					lsb_builder.append(IdentificadoresCommon.SEPARADOR_DISPOSITIVOS);
					lsb_builder.append(ads_documento.getNumeroCopias());

					ls_return = lsb_builder.toString();
				}
				else
				{
					Object[] loa_args;

					loa_args        = new String[1];
					loa_args[0]     = ads_documento.getTipo();

					throw new B2BException(ErrorKeys.DOCUMENTO_NO_ENVIADO, loa_args);
				}
			}
			catch(B2BException lb2be_e)
			{
				clh_LOGGER.error("generarURLImpresion", lb2be_e);
				throw lb2be_e;
			}
		}

		return ls_return;
	}

	/**
	 * Método encargado de cambiar el estado del documento.
	 *
	 * @param acds_documentos Colección que contiene los documentos.
	 * @param adsa_documentosArray Colección que contiene los documentos.
	 * @param ai_indice int que indica la posición del documento en la colección.
	 * @param as_estado Variable que contiene el nuevo estado para el documento.
	 * @return el valor de documentos salida
	 */
	private DocumentosSalida cambiarEstadoDocumento(
	    Collection<DocumentosSalida> acds_documentos, ArrayList<DocumentosSalida> adsa_documentosArray, int ai_indice,
	    String as_estado
	)
	{
		DocumentosSalida lds_documento;

		lds_documento = null;

		if(
		    CollectionUtils.isValidCollection(acds_documentos)
			    && CollectionUtils.isValidCollection(adsa_documentosArray) && (ai_indice > -1)
			    && StringUtils.isValidString(as_estado)
		)
		{
			// FORMATO Comentar lambda antes de formatear
			Optional<DocumentosSalida> lods_optional;

			lods_optional = 
				acds_documentos.stream()
				.filter(lds_temp -> lds_temp != null && lds_temp.equals(adsa_documentosArray.get(ai_indice)))
				.findFirst();
			
			if(lods_optional.isPresent())
			{
				lds_documento = lods_optional.get();

				if(lds_documento != null)
					lds_documento.setEstadoImpresion(as_estado);
			}
		}

		return lds_documento;
	}

	/**
	 * Construir id estado.
	 *
	 * @param ai_int de ai int
	 * @param as_pantalla de as pantalla
	 * @return <code>String</code>
	 */
	private String construirIdEstado(int ai_int, String as_pantalla)
	{
		String ls_return;

		ls_return = null;

		if(ai_int >= 0)
		{
			StringBuilder lsb_builder;

			lsb_builder = new StringBuilder(as_pantalla);

			lsb_builder.append(":idDtDocsImprimir:");
			lsb_builder.append(ai_int);
			lsb_builder.append(":estadoDocumento");

			ls_return = lsb_builder.toString();
		}

		return ls_return;
	}

	/**
	 * Método encargado de generar el error en la impresión.
	 *
	 * @param ads_documento Objeto que contiene el documento que presenta error al imprimir.
	 */
	private void errorImpresion(DocumentosSalida ads_documento)
	{
		if(ads_documento != null)
		{
			Object[] loa_argumentos;

			loa_argumentos        = new String[1];
			loa_argumentos[0]     = ads_documento.getTipo();

			{
				B2BException lb2be_e;

				lb2be_e = new B2BException(ErrorKeys.OCURRIO_UN_ERROR_DOCUMENTO, loa_argumentos);

				addMessage(lb2be_e);
				clh_LOGGER.error(lb2be_e);
			}
		}
	}

	/**
	 * Obtener parametros evento.
	 *
	 * @param as_llave de as llave
	 * @return el valor de string[]
	 */
	private String[] obtenerParametrosEvento(String as_llave)
	{
		String[] loa_evento;

		loa_evento = null;

		if(StringUtils.isValidString(as_llave))
		{
			Map<String, String> lmss_params;

			lmss_params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

			if(CollectionUtils.isValidCollection(lmss_params))
			{
				loa_evento        = new String[1];
				loa_evento[0]     = lmss_params.get(as_llave);
			}
		}

		return loa_evento;
	}

	/**
	 * Traducir mensajes.
	 *
	 * @param aoa_param de aoa param
	 * @return el valor de object[]
	 */
	private Object[] traducirMensajes(String[] aoa_param)
	{
		if(aoa_param != null)
		{
			String ls_evento;

			ls_evento = aoa_param[0];

			if(StringUtils.isValidString(ls_evento))
			{
				switch(ls_evento)
				{
					case EventoCommon.SIGN:
						aoa_param[0] = EventoCommon.FIRMA;

						break;

					case EventoCommon.ENROLL:
						aoa_param[0] = EventoCommon.ENROLAMIENTO;

						break;

					case EventoCommon.VERIFY:
						aoa_param[0] = EventoCommon.VERIFICACION;

						break;

					case EventoCommon.RESET:
						aoa_param[0] = EventoCommon.REINICIO_CLAVE;

						break;

					case EventoCommon.PDF:
						aoa_param[0] = EventoCommon.IMPRESION;

						break;

					default:
						break;
				}
			}
		}

		return aoa_param;
	}
}
