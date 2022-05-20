package com.bachue.snr.prosnr21.web.bean.conciliaciones;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr01.common.constants.ExtensionCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.TipoContenidoCommon;

import com.bachue.snr.prosnr21.common.constants.ErrorKeys;
import com.bachue.snr.prosnr21.common.constants.EstadoCommon;
import com.bachue.snr.prosnr21.common.constants.MessagesKeys;

import com.bachue.snr.prosnr21.ejb.session.stateless.conciliaciones.ConciliacionesRemote;
import com.bachue.snr.prosnr21.ejb.session.stateless.conciliaciones.ParameterRemote;

import com.bachue.snr.prosnr21.model.pgn.ArchivoDRXC;
import com.bachue.snr.prosnr21.model.pgn.ConPartidaA;
import com.bachue.snr.prosnr21.model.pgn.EntidadRecaudadoraConciliacion;
import com.bachue.snr.prosnr21.model.pgn.EntidadRecaudadoraCuenta;

import org.primefaces.PrimeFaces;

import org.primefaces.event.FileUploadEvent;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.UploadedFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;

import java.time.LocalDateTime;

import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase que contiene todos las propiedades BeanPartidasTipoA.
 *
 * @author  Duvan Beltrán
 */
@ManagedBean(name = "beanPartidasTipoA")
@SessionScoped
public class BeanPartidasTipoA extends BeanArchivoDRXC implements Serializable
{
	/**  Constante serialVersionUID. */
	private static final long serialVersionUID = 4033496417733290077L;

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanPartidasTipoA.class);

	/** Constante cs_ID_GROWL. */
	private static final String cs_ID_GROWL = ":fPartidasTipoA:idGrowl";

	/**  Propiedad dataComprobante. */
	private Collection<ArchivoDRXC> icad_dataComprobante;

	/** Propiedad ils lista alertas. */
	private Collection<ConPartidaA> iccpa_consulta;

	/** Propiedad ils lista alertas. */
	private Collection<String> ils_listaPartidas;

	/** Propiedad ipr conciliaciones remote. */
	@EJB
	private ConciliacionesRemote ipr_conciliacionesRemote;

	/** Propiedad fecha confrontacion. */
	private Date id_fechaConfrontacion;

	/** Propiedad isc file. */
	private DefaultStreamedContent isc_reportes;

	/** Propiedad Entidad Recaudadora cuenta. */
	private EntidadRecaudadoraCuenta ir_entidadRecaudadoraCuenta;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad ls calsificacion partidas. */
	private String is_clasificacionPartidas;

	/** Propiedad id tipo cuenta label. */
	private String is_tipoCuentaLabel;

	/** Propiedad iuf upload file. */
	private UploadedFile iuf_uploadFile;

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
	 * Modifica el valor de ClasificacionPartidas.
	 *
	 * @param as_clasificacionPartidas de as clasificacion partidas
	 */
	public void setClasificacionPartidas(String as_clasificacionPartidas)
	{
		this.is_clasificacionPartidas = as_clasificacionPartidas;
	}

	/**
	 * Retorna Objeto o variable de valor clasificacion partidas.
	 *
	 * @return el valor de clasificacion partidas
	 */
	public String getClasificacionPartidas()
	{
		return is_clasificacionPartidas;
	}

	/**
	 * Modifica el valor de Consulta.
	 *
	 * @param accpa_consulta de consulta
	 */
	public void setConsulta(Collection<ConPartidaA> accpa_consulta)
	{
		iccpa_consulta = accpa_consulta;
	}

	/**
	 * Retorna Objeto o variable de valor consulta.
	 *
	 * @return el valor de consulta
	 */
	public Collection<ConPartidaA> getConsulta()
	{
		return iccpa_consulta;
	}

	/**
	 * Sets the data comprobante.
	 *
	 * @param acad_dataComprobante the new data comprobante
	 */
	public void setDataComprobante(Collection<ArchivoDRXC> acad_dataComprobante)
	{
		icad_dataComprobante = acad_dataComprobante;
	}

	/**
	 * Gets the data comprobante.
	 *
	 * @return Retorna el valor de la propiedad dataComprobante
	 */
	public Collection<ArchivoDRXC> getDataComprobante()
	{
		return icad_dataComprobante;
	}

	/**
	 * Método de asignación del valor de la propiedad.
	 *
	 * @param ar_EntidadRecaudadoraCuenta asigna el valor a la propiedad
	 */
	public void setEntidadRecaudadoraCuenta(EntidadRecaudadoraCuenta ar_EntidadRecaudadoraCuenta)
	{
		ir_entidadRecaudadoraCuenta = ar_EntidadRecaudadoraCuenta;
	}

	/**
	 * Método de obtencion del valor de la propiedad.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public EntidadRecaudadoraCuenta getEntidadRecaudadoraCuenta()
	{
		return (ir_entidadRecaudadoraCuenta != null) ? ir_entidadRecaudadoraCuenta : new EntidadRecaudadoraCuenta();
	}

	/**
	 * Modifica el valor de FechaConfrontacion.
	 *
	 * @param ad_fechaConciliar de ad fecha conciliar
	 */
	public void setFechaConfrontacion(Date ad_fechaConciliar)
	{
		id_fechaConfrontacion = ad_fechaConciliar;
	}

	/**
	 * Retorna Objeto o variable de valor fecha confrontacion.
	 *
	 * @return Retorna el valor de la propiedad id_fechaConciliar
	 */
	public Date getFechaConfrontacion()
	{
		return id_fechaConfrontacion;
	}

	/**
	 * Modifica el valor de Ils_listaAlertas.
	 *
	 * @param als_listaPartidas de ils lista alertas
	 */
	public void setListaPartidas(Collection<String> als_listaPartidas)
	{
		this.ils_listaPartidas = als_listaPartidas;
	}

	/**
	 * Retorna Objeto o variable de valor ils lista alertas.
	 *
	 * @return el valor de ils lista alertas
	 */
	public Collection<String> getListaPartidas()
	{
		if(ils_listaPartidas == null)
		{
			String ls_caracter;
			ls_caracter = null;

			try
			{
				ls_caracter = ipr_parameterRemote.obtenerCaracterConstante(ConstanteCommon.PARTIDAS_TIPO_A);

				if(StringUtils.isValidString(ls_caracter))
				{
					String[] lsa_temp;
					String[] lsa_lista;

					ls_caracter     = ls_caracter.replaceAll("\\.", "");
					lsa_temp        = ls_caracter.split(IdentificadoresCommon.SIMBOLO_COMA_TXT);
					lsa_lista       = new String[lsa_temp.length];

					for(int i_iterador = 0; i_iterador < lsa_temp.length; i_iterador++)
						lsa_lista[i_iterador] = lsa_temp[i_iterador].trim();

					ils_listaPartidas = Arrays.asList(lsa_lista);
				}
			}
			catch(B2BException lb2be_e)
			{
				clh_LOGGER.error("getListaPartidas", lb2be_e);
				addMessage(lb2be_e);
				actualizarComponente(cs_ID_GROWL);
			}
		}

		return ils_listaPartidas;
	}

	/**
	 * Sets the reportes.
	 *
	 * @param asc_reportes the new reportes
	 */
	public void setReportes(DefaultStreamedContent asc_reportes)
	{
		isc_reportes = asc_reportes;
	}

	/**
	 * Gets the reportes.
	 *
	 * @return Retorna el valor de la propiedad reporteExcel
	 */
	public DefaultStreamedContent getReportes()
	{
		return isc_reportes;
	}

	/**
	 * Modifica el valor de TipoCuentaLabel.
	 *
	 * @param tipoCuentaLabel de tipo cuenta label
	 */
	public void setTipoCuentaLabel(String tipoCuentaLabel)
	{
		this.is_tipoCuentaLabel = tipoCuentaLabel;
	}

	/**
	 * Retorna Objeto o variable de valor tipo cuenta label.
	 *
	 * @return el valor de tipo cuenta label
	 */
	public String getTipoCuentaLabel()
	{
		return is_tipoCuentaLabel;
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
	 * Método de actualización de tipo cuenta.
	 */
	public void actualizarTipoCuenta()
	{
		Collection<EntidadRecaudadoraCuenta> lcerc_entidadesRCuentas;

		lcerc_entidadesRCuentas = getEntidadRecaudadoraCuentas();

		if(CollectionUtils.isValidCollection(lcerc_entidadesRCuentas))
		{
			for(EntidadRecaudadoraCuenta lierc_erc : lcerc_entidadesRCuentas)
			{
				if(lierc_erc != null)
				{
					String ls_idCuenta;
					ls_idCuenta = lierc_erc.getIdCuenta();

					if(ls_idCuenta.equals(getIdCuenta()))
						cambiarTipoCuenta(lierc_erc.getTipoCuenta());
				}
			}
		}
	}

	/**
	 * Buscar clasificacion partidas.
	 *
	 * @param acpa_partida the acpa partida
	 * @return el valor de collection
	 */
	public Collection<String> buscarClasificacionPartidas(ConPartidaA acpa_partida)
	{
		Collection<String> lcs_return;
		String             ls_partidas;
		String             ls_busqueda;

		lcs_return      = acpa_partida.getListaClasificacionPartidas();
		ls_partidas     = acpa_partida.getPartidas();
		ls_busqueda     = null;

		if(StringUtils.isValidString(ls_partidas))
		{
			if(ls_partidas.equalsIgnoreCase(IdentificadoresCommon.NOTA_CREDITO2))
				ls_busqueda = "CLASIFICACION_NOTAS_CREDITO";
			else if(ls_partidas.equalsIgnoreCase(IdentificadoresCommon.NOTA_DEBITO))
				ls_busqueda = "CLASIFICACION_NOTAS_DEBITO";
			else
				ls_busqueda = "INGRESOS_CON_AFECTACION_SIN_PRESTACION_DE_SERVICIO";
		}

		if(StringUtils.isValidString(ls_busqueda))
		{
			try
			{
				String ls_caracter;

				ls_caracter = ipr_parameterRemote.obtenerCaracterConstante(ls_busqueda);

				if(StringUtils.isValidString(ls_caracter))
				{
					String[] lsa_temp;
					String[] lsa_lista;

					ls_caracter     = ls_caracter.replaceAll("\\.", "");
					lsa_temp        = ls_caracter.split(IdentificadoresCommon.SIMBOLO_COMA_TXT);
					lsa_lista       = new String[lsa_temp.length];

					for(int i_iterador = 0; i_iterador < lsa_temp.length; i_iterador++)
						lsa_lista[i_iterador] = lsa_temp[i_iterador].trim();

					lcs_return = Arrays.asList(lsa_lista);
					acpa_partida.setListaClasificacionPartidas(lcs_return);
				}
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);
			}
		}

		return lcs_return;
	}

	/**
	 *  Método que retorna todas las entidades recaudadoras de conciliación asignadas a un usuario.
	 *
	 * Retorna un objeto de tipo <code>Collection</code> que contiene los resultados que coincidieron con los criterios de búsqueda.
	 *
	 * @return el valor de collection
	 */
	public Collection<EntidadRecaudadoraConciliacion> buscarEntidadRecaudadoraConciliacion()
	{
		Collection<EntidadRecaudadoraConciliacion> lcpc_procesoConciliacion;

		lcpc_procesoConciliacion = null;

		try
		{
			lcpc_procesoConciliacion = ipr_conciliacionesRemote.findEntidadRecaudadoraConciliacionByUser(getUserId());
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcpc_procesoConciliacion;
	}

	/**
	 * Buscar entidad recaudadora local.
	 *
	 * @return el valor de collection
	 */
	public Collection<EntidadRecaudadoraCuenta> buscarEntidadRecaudadoraLocal()
	{
		return buscarEntidadRecaudadoraCuentaDeUsuario();
	}

	/**
	 * Cambiar tipo cuenta.
	 *
	 * @param as_tipoCuenta the as tipo cuenta
	 */
	public void cambiarTipoCuenta(String as_tipoCuenta)
	{
		String ls_label;

		ls_label = null;

		if(StringUtils.isValidString(as_tipoCuenta))
		{
			if(as_tipoCuenta.equalsIgnoreCase("C"))
				ls_label = "Corriente";
			else if(as_tipoCuenta.equalsIgnoreCase("A"))
				ls_label = "Ahorros";
		}

		setTipoCuentaLabel(ls_label);
	}

	/**
	 * Consultar alertas.
	 */
	public void consultarPartidasTipoA()
	{
		try
		{
			FacesContext lfc_context;
			boolean      lb_focus;
			boolean      lb_focus1;

			lfc_context     = FacesContext.getCurrentInstance();
			lb_focus        = true;
			lb_focus1       = true;

			EntidadRecaudadoraCuenta ler_entidadRecaudadoraCuenta;
			String                   ls_paramBusqueda;

			ler_entidadRecaudadoraCuenta = getEntidadRecaudadoraCuenta();

			if(ler_entidadRecaudadoraCuenta != null)
			{
				Collection<ConPartidaA> lccpa_data;

				lccpa_data           = null;
				ls_paramBusqueda     = getIdEntidadRecaudadora();

				lb_focus     = validateStyles(
					    ":fPartidasTipoA:idEntidadRecaudadoraCuenta", lfc_context, ls_paramBusqueda, lb_focus
					);

				ls_paramBusqueda     = getIdCuenta();

				lb_focus = validateStyles(":fPartidasTipoA:idNumeroCuenta", lfc_context, ls_paramBusqueda, lb_focus);

				Date ld_fechaConsulta;

				ld_fechaConsulta = getFechaConfrontacion();

				if(ld_fechaConsulta == null)
					lb_focus = validateStyles(
						    "fPartidasTipoA:idFechaMovimiento", lfc_context, ld_fechaConsulta, lb_focus
						);
				else if(ld_fechaConsulta.after(new Date()))
				{
					lb_focus1 = validateStyles(
						    ":fPartidasTipoA:idFechaMovimiento", lfc_context, ld_fechaConsulta, lb_focus1
						);
					addMessageInfo("W", MessagesKeys.ERROR_FECHA_MAYOR_A_ACTUAL);
					lb_focus1 = false;
				}
				else
					lb_focus1 = validateStyles(
						    ":fPartidasTipoA:idFechaMovimiento", lfc_context, ld_fechaConsulta, lb_focus1
						);

				PrimeFaces.current().ajax().update("fPartidasTipoA");

				if(!lb_focus || !lb_focus1)
					throw new B2BException(ErrorKeys.ERROR_SELECCION_INFORMACION_CONSULTA);

				lccpa_data = ipr_conciliacionesRemote.findMulticashDetalleByCuentaAndFecha(
					    getIdCuenta(), getFechaConfrontacion(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);

				if(CollectionUtils.isValidCollection(lccpa_data))
				{
					ArchivoDRXC             lad_dataComprobante;
					Collection<ArchivoDRXC> lcad_dataComprobante;
					boolean                 lb_data;
					boolean                 lb_confirmaSiif;
					Date                    ls_fechaSiif;
					String                  ls_comprobanteSiif;

					lb_data                  = false;
					lb_confirmaSiif          = false;
					ls_fechaSiif             = null;
					ls_comprobanteSiif       = null;
					lad_dataComprobante      = new ArchivoDRXC();
					lcad_dataComprobante     = new ArrayList<ArchivoDRXC>(1);

					for(ConPartidaA lcpa_iterador : lccpa_data)
					{
						if(lcpa_iterador != null)
						{
							if(!lb_data)
							{
								String ls_confirma;

								lb_data                = true;
								ls_confirma            = lcpa_iterador.getEnvioSiif();
								lb_confirmaSiif        = StringUtils.isValidString(ls_confirma)
										&& ls_confirma.equalsIgnoreCase(EstadoCommon.S);
								ls_fechaSiif           = lcpa_iterador.getFechaComprobanteSiif();
								ls_comprobanteSiif     = lcpa_iterador.getNumeroComprobanteSiif();
							}

							buscarClasificacionPartidas(lcpa_iterador);
						}
					}

					lad_dataComprobante.setConfirmaNoSiif(lb_confirmaSiif);
					lad_dataComprobante.setNumeroSIIF(ls_comprobanteSiif);
					lad_dataComprobante.setFechaSIIF(ls_fechaSiif);

					lcad_dataComprobante.add(lad_dataComprobante);

					setDataComprobante(lcad_dataComprobante);
					setConsulta(lccpa_data);
					setMostrarPanel(true);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("consultarPartidasTipoA", lb2be_e);
			addMessage(lb2be_e);
			actualizarComponente(cs_ID_GROWL);
		}
	}

	/**
	 * Generar reportes.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void generarReportes()
	    throws IOException
	{
		try
		{
			byte[] lba_archivo;

			lba_archivo = ipr_conciliacionesRemote.generarReportes(
				    getUserId(), getLocalIpAddress(), getRemoteIpAddress(), getConsulta(), getFechaConfrontacion(),
				    getIdCuenta(), getIdEntidadRecaudadora()
				);

			if(lba_archivo != null)
			{
				LocalDateTime     lldt_currentdate;
				DateTimeFormatter ldtf_formatter;

				lldt_currentdate     = LocalDateTime.now();
				ldtf_formatter       = DateTimeFormatter.ofPattern("YYYYMMdd_HHmm");

				setReportes(
				    new DefaultStreamedContent(
				        new ByteArrayInputStream(lba_archivo), TipoContenidoCommon.ZIP,
				        ConstanteCommon.NOMBRE_ARCHIVO_GENERADO_ZIP + IdentificadoresCommon.SIMBOLO_GUION_BAJO
				        + lldt_currentdate.format(ldtf_formatter) + ExtensionCommon.ZIP_PUNTO
				    )
				);
			}
			else
				throw new B2BException(ErrorKeys.NO_SE_GENERO_ARCHIVO);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("generarReportes", lb2be_e);
			addMessage(lb2be_e);
		}
		finally
		{
			actualizarComponente(cs_ID_GROWL);
		}
	}

	/**
	 * Limpiar.
	 */
	public void limpiar()
	{
		setConsulta(null);
		setFechaConfrontacion(null);
		cambiarTipoCuenta(null);
		setTipoCuentaLabel(null);
		setEntidadRecaudadoraCuenta(null);
		setFechaConciliar(null);
		setMostrarPanel(false);
		setListaPartidas(null);
		super.limpiar();
	}

	/**
	 * Limpiar partidas tipo A.
	 */
	public void limpiarPartidasTipoA()
	{
		setConsulta(null);
		setMostrarPanel(false);
		setFechaConfrontacion(null);
		setIdCuenta(null);
		setIdEntidadRecaudadora(null);
		setTipoCuentaLabel(null);
		setUploadFile(null);
	}

	/**
	 * Process file.
	 *
	 * @param afue_event de afue event
	 * @return el valor de string
	 */
	public String processFile(FileUploadEvent afue_event)
	{
		String                  ls_row;
		UploadedFile            luf_file;
		Collection<ConPartidaA> lcpa_datos;

		lcpa_datos     = getConsulta();
		luf_file       = afue_event.getFile();
		ls_row         = (String)afue_event.getComponent().getAttributes().get("idPartidaA");

		if(CollectionUtils.isValidCollection(lcpa_datos) && StringUtils.isValidString(ls_row))
		{
			for(ConPartidaA cpa_iterator : lcpa_datos)
			{
				if(cpa_iterator.getIdPartidaA().equalsIgnoreCase(ls_row))
				{
					cpa_iterator.setBytesArchivo(luf_file.getContents());
					cpa_iterator.setTipoArchivo(luf_file.getContentType());
					cpa_iterator.setIdDocumentoSoporte(ls_row + "-" + luf_file.getFileName());
					cpa_iterator.setDocumentoSoporte(com.bachue.snr.prosnr21.common.constants.IdentificadoresCommon.S);
				}
			}
		}

		if(luf_file != null)
			setUploadFile(luf_file);

		return null;
	}

	/**
	 * Salvar partidas A.
	 */
	public void salvarPartidasA()
	{
		Collection<ConPartidaA> lcpa_datos;

		lcpa_datos = getConsulta();

		try
		{
			if(CollectionUtils.isValidCollection(lcpa_datos))
			{
				for(ConPartidaA lpa_iterator : lcpa_datos)
				{
					lpa_iterator.setFechaEnvioArchivoND(new Date());
					lpa_iterator.setIdUsuarioAnalistaConcilio(getUserId());
					lpa_iterator.setConciliadoAnalista(
					    com.bachue.snr.prosnr21.common.constants.IdentificadoresCommon.S
					);
				}

				ipr_conciliacionesRemote.salvarPartidasA(
				    getUserId(), getLocalIpAddress(), getRemoteIpAddress(), lcpa_datos, getDataComprobante()
				);

				addMessageInfo("I", MessagesKeys.PROCESO_COMPLETADO);
			}
			else
				throw new B2BException(ErrorKeys.NO_FUE_POSIBLE_SALVAR_REGISTROS);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("salvarPartidasA", lb2be_e);
			addMessage(lb2be_e);
			actualizarComponente(cs_ID_GROWL);
		}
	}
}
