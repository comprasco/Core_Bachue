package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;
import com.bachue.snr.prosnr01.common.constants.TipoContenidoCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;

import com.bachue.snr.prosnr01.model.registro.Archivo;
import com.bachue.snr.prosnr01.model.sdb.pgn.Constantes;

import com.bachue.snr.prosnr01.web.bean.BaseBean;
import com.bachue.snr.prosnr01.web.bean.reference.BeanReference;
import com.bachue.snr.prosnr01.web.util.FacesUtils;

import com.bachue.snr.prosnr02.common.constants.ScriptsCommon;

import org.primefaces.PrimeFaces;

import org.primefaces.event.FileUploadEvent;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;

import java.util.Collection;
import java.util.Set;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;

import javax.servlet.http.HttpServletRequest;


/**
 * Clase que contiene todos las propiedades y acciones de BeanConstantes.
 *
 * @author Julian Vaca
 */
@ManagedBean(name = "beanConstantes")
@SessionScoped
public class BeanConstantes extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 4903603478073131491L;

	/** Propiedad ica archivos log. */
	private Collection<Archivo> ica_archivosLog;

	/** Propiedad ic parametros. */
	private Constantes ic_parametros;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad iss visitados. */
	private Set<String> iss_visitados;

	/** Propiedad isc imagen. */
	private StreamedContent isc_imagen;

	/** Propiedad isc log. */
	private StreamedContent isc_log;

	/** Propiedad isc zip RTFS. */
	private StreamedContent isc_zipRTFS;

	/** Propiedad iuf upload file. */
	private UploadedFile iuf_uploadFile;

	/** Propiedad ib ejecutar proc. */
	private boolean ib_ejecutarProc;

	/** Propiedad ib insertar. */
	private boolean ib_insertar;

	/**  Propiedad viene notificación comunicaciones. */
	private boolean ib_vieneNotificacionComunicaciones;

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
	 * Modifica el valor de ArchivosLog.
	 *
	 * @param aca_ca de aca ca
	 */
	public void setArchivosLog(Collection<Archivo> aca_ca)
	{
		ica_archivosLog = aca_ca;
	}

	/**
	 * Retorna Objeto o variable de valor archivos log.
	 *
	 * @return el valor de archivos log
	 */
	public Collection<Archivo> getArchivosLog()
	{
		return ica_archivosLog;
	}

	/**
	 * Modifica el valor de ejecutar proc.
	 *
	 * @param ab_b asigna el valor a la propiedad ejecutar proc
	 */
	public void setEjecutarProc(boolean ab_b)
	{
		ib_ejecutarProc = ab_b;
	}

	/**
	 * Valida la propiedad ejecutar proc.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en ejecutar proc
	 */
	public boolean isEjecutarProc()
	{
		return ib_ejecutarProc;
	}

	/**
	 * Modifica el valor de imagen.
	 *
	 * @param asc_sc asigna el valor a la propiedad imagen
	 */
	public void setImagen(StreamedContent asc_sc)
	{
		isc_imagen = asc_sc;
	}

	/**
	 * Retorna el valor de imagen.
	 *
	 * @return el valor de imagen
	 */
	public StreamedContent getImagen()
	{
		return isc_imagen;
	}

	/**
	 * Modifica el valor de insertar.
	 *
	 * @param ab_b asigna el valor a la propiedad insertar
	 */
	public void setInsertar(boolean ab_b)
	{
		ib_insertar = ab_b;
	}

	/**
	 * Valida la propiedad insertar.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en insertar
	 */
	public boolean isInsertar()
	{
		return ib_insertar;
	}

	/**
	 * Modifica el valor de Log.
	 *
	 * @param isc_log Variable de tipo <code>StreamedContent</code> que contiene el nuevo valor del log.
	 */
	public void setLog(StreamedContent isc_log)
	{
		this.isc_log = isc_log;
	}

	/**
	 * Retorna Objeto o variable de valor log.
	 *
	 * @return Variable de tipo <code>StreamedContent</code> que contiene el valor del log.
	 */
	public StreamedContent getLog()
	{
		return isc_log;
	}

	/**
	 * Modifica el valor de parametros.
	 *
	 * @param ac_c asigna el valor a la propiedad parametros
	 */
	public void setParametros(Constantes ac_c)
	{
		ic_parametros = ac_c;
	}

	/**
	 * Retorna el valor de parametros.
	 *
	 * @return el valor de parametros
	 */
	public Constantes getParametros()
	{
		if(ic_parametros == null)
			ic_parametros = new Constantes();

		return ic_parametros;
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
	 * Modifica el valor de viene notificación comunicaciones.
	 *
	 * @param ab_b asigna el valor a la propiedad viene notificación comunicaciones.
	 */
	public void setVieneNotificacionComunicaciones(boolean ab_b)
	{
		ib_vieneNotificacionComunicaciones = ab_b;
	}

	/**
	 * Valida la propiedad viene notificación comunicaciones.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en viene notificación comunicaciones.
	 */
	public boolean isVieneNotificacionComunicaciones()
	{
		return ib_vieneNotificacionComunicaciones;
	}

	/**
	 * Modifica el valor de visitados.
	 *
	 * @param ass_ss asigna el valor a la propiedad visitados
	 */
	public void setVisitados(Set<String> ass_ss)
	{
		iss_visitados = ass_ss;
	}

	/**
	 * Retorna el valor de visitados.
	 *
	 * @return el valor de visitados
	 */
	public Set<String> getVisitados()
	{
		return iss_visitados;
	}

	/**
	 * Modifica el valor de zip RTFS.
	 *
	 * @param asc_sc asigna el valor a la propiedad zip RTFS
	 */
	public void setZipRTFS(StreamedContent asc_sc)
	{
		isc_zipRTFS = asc_sc;
	}

	/**
	 * Retorna el valor de zip RTFS.
	 *
	 * @return el valor de zip RTFS
	 */
	public StreamedContent getZipRTFS()
	{
		return isc_zipRTFS;
	}

	/**
	 * Accion click archivo.
	 *
	 * @param aa_archivo de aa archivo
	 * @return el valor de collection
	 */
	public Collection<Archivo> accionClickArchivo(Archivo aa_archivo)
	{
		Collection<Archivo> laa_archivos;

		laa_archivos = null;

		if(aa_archivo != null)
		{
			try
			{
				if(aa_archivo.isRegresar())
					accionRegresarLog(aa_archivo);
				else
				{
					laa_archivos = ipr_parameterRemote.accionClickArchivo(aa_archivo);

					if(!CollectionUtils.isValidCollection(laa_archivos))
					{
						setLog(
						    generarArchivosDescarga(
						        ipr_parameterRemote.descargarLog(aa_archivo), TipoContenidoCommon.TXT,
						        aa_archivo.getNombre()
						    )
						);
						PrimeFaces.current().executeScript(ScriptsCommon.ABRIR_CONFIRMACION_LOG);
					}
					else
					{
						setArchivosLog(laa_archivos);
						PrimeFaces.current().executeScript(ScriptsCommon.LIMPIAR_FILTROS_LOGS);
					}
				}
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);
			}
		}

		return laa_archivos;
	}

	public void accionRegresarLog(Archivo aa_archivo)
	{
		try
		{
			Collection<Archivo> lca_archivos;

			lca_archivos = ipr_parameterRemote.accionRegresarLog(aa_archivo);

			setArchivosLog(lca_archivos);

			PrimeFaces.current().executeScript(ScriptsCommon.LIMPIAR_FILTROS_LOGS);

			addMessage(MessagesKeys.PROCESO_COMPLETADO);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}
	}

	/**
	 * Método para  cambiar estado para saber si se desea insertar una constante.
	 */
	public void cambiarEstado()
	{
		Boolean lb_parametro;
		lb_parametro = FacesUtils.getBooleanFacesParameter("pInsertar");

		if(lb_parametro != null)
			setInsertar(BooleanUtils.getBooleanValue(lb_parametro));
	}

	/**
	 * Cargar lista archivos.
	 */
	public void cargarListaArchivos()
	{
		try
		{
			Collection<Archivo> lca_archivos;

			lca_archivos = ipr_parameterRemote.cargarListaArchivos();

			setArchivosLog(lca_archivos);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}
	}

	/**
	 * Clean.
	 */
	public void clean()
	{
		setArchivosLog(null);
		setDatosParametricosDireccion(null);
		setEjecutarProc(false);
		setIdiomaEspanol(false);
		setImagen(null);
		setInsertar(false);
		setLog(null);
		setModeloTorta(null);
		setMostrarGrafica(false);
		setOpciones(null);
		setParametros(null);
		setTurnosAsg(null);
		setTurnosBloq(null);
		setTurnosTer(null);
		setUploadFile(null);
		setVieneNotificacionComunicaciones(false);
		setVisitados(null);
		setZipRTFS(null);
	}

	/**
	 * Método para consultar en la base de datos la tabla SDB_PGN_CONSTANTES
	 * con un id_constante especifico.
	 */
	public void consultaDetallada()
	{
		try
		{
			Constantes lc_parametros;
			lc_parametros = getParametros();

			if(lc_parametros != null)
			{
				String ls_idConstante;
				ls_idConstante = FacesUtils.getStringFacesParameter("idConstante");

				if(StringUtils.isValidString(ls_idConstante))
				{
					lc_parametros.setIdConstante(ls_idConstante);

					setParametros(
					    isVieneNotificacionComunicaciones() ? ipr_parameterRemote.findConstantByIdCYN(lc_parametros)
					                                        : ipr_parameterRemote.findConstantById(lc_parametros)
					);

					setInsertar(false);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}
	}

	/**
	 * Método para descargar el archivo en formato rtf de la constante.
	 */
	public void descargarConstante()
	{
		try
		{
			String ls_idConstante;
			ls_idConstante = ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest())
					.getParameter("idConstante");

			setImagen(null);

			if(StringUtils.isValidString(ls_idConstante))
			{
				Constantes lc_constante;
				lc_constante = getParametros();

				lc_constante.setIdConstante(ls_idConstante);

				lc_constante = ipr_parameterRemote.findImageById(lc_constante);

				if(lc_constante != null)
				{
					InputStream lis_stream;
					lis_stream = new ByteArrayInputStream(lc_constante.getImagenes());

					String ls_tipoArchivo;
					ls_tipoArchivo = lc_constante.getTipoArchivo();

					setImagen(
					    new DefaultStreamedContent(
					        lis_stream, TipoContenidoCommon.RTF, "ArchivoPdfGenerado_" + ls_idConstante
					        + ls_tipoArchivo
					    )
					);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}
	}

	/**
	 * Método para descargar el archivo en formato rtf de la constante.
	 */
	public void descargarConstantesRtf()
	{
		try
		{
			byte[] lba_zipfinal;

			lba_zipfinal = ipr_parameterRemote.findAllRtf();

			if(lba_zipfinal != null)
				setZipRTFS(
				    generarArchivosDescarga(lba_zipfinal, TipoContenidoCommon.ZIP, ConstanteCommon.CONSTANTES_ZIP)
				);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}
	}

	/**
	 * Método para realizar la ejecucion del procedimiento nocturno.
	 *
	 * @return devuelve el valor de String
	 */
	public String ejecutarProcNocturno()
	{
		String ls_s;
		ls_s = NavegacionCommon.PRINCIPAL;
		setEjecutarProc(true);

		try
		{
			boolean lb_b;
			lb_b = isEjecutarProc();

			if(lb_b)
			{
				ipr_parameterRemote.ejecutarProcNocturno();

				String ls_mensaje = "Ejecución exitosa, verifique los datos.";
				addMessage("I", ls_mensaje);
				PrimeFaces.current().ajax().update("fRegistro:globalMsg");
			}

			else
				throw new B2BException(ErrorKeys.ERROR_SIN_OBSERVACIONES);
		}
		catch(B2BException lb2b_e)
		{
			addMessage(lb2b_e);
		}

		return ls_s;
	}

	/**
	 * Método para traer de la base de datos todos los registros de la tabla SDB_PGN_CONSTANTES.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<Constantes> findAllConstants()
	{
		Collection<Constantes> lcc_constantes;
		lcc_constantes = null;

		try
		{
			lcc_constantes = isVieneNotificacionComunicaciones() ? ipr_parameterRemote.findAllConstantsCYN()
				                                                 : ipr_parameterRemote.findAllConstants();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcc_constantes;
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
	 * Método para insertar o actualizar en la base de datos.
	 *
	 * @return devuelve el valor de String
	 */
	public String salvar()
	{
		String ls_result;

		ls_result = null;

		try
		{
			Constantes   lc_constante;
			UploadedFile luf_file;
			String       ls_idConstante;
			boolean      lb_insertar;

			lc_constante       = getParametros();
			luf_file           = getUploadFile();
			ls_idConstante     = lc_constante.getIdConstante();
			lb_insertar        = isInsertar();

			if(luf_file != null)
				lc_constante.setImagenes(luf_file.getContents());

			if(lb_insertar)
			{
				lc_constante.setIdUsuarioCreacion(getUserId());
				lc_constante.setIpCreacion(getRemoteIpAddress());
			}
			else
			{
				lc_constante.setIdUsuarioModificacion(getUserId());
				lc_constante.setIpModificacion(getRemoteIpAddress());
			}

			if(!ls_idConstante.equals(ConstanteCommon.CONFIGURACION_IDIOMA_PAIS))
			{
				if(lb_insertar)
					lc_constante.setTipo(Constantes.TIPO_MANUAL);

				if(isVieneNotificacionComunicaciones())
					ipr_parameterRemote.salvarConstanteCYN(lc_constante, lb_insertar);
				else
					ipr_parameterRemote.salvarConstante(lc_constante, lb_insertar);

				{
					BeanReference lbr_beanReference;

					lbr_beanReference = getBeanReference();

					lbr_beanReference.setMotivosConsulta(null);
					lbr_beanReference.setRecepcionDocumento(null);
					lbr_beanReference.setCalidadSolicitanteEntrega(null);
					lbr_beanReference.setTiposActosCodigo(null);
					lbr_beanReference.setTiposConsignacion(null);
					lbr_beanReference.setTiposPublicacion(null);
					lbr_beanReference.setTiposConsulta(null);
				}
			}

			setParametros(null);

			ls_result = NavegacionCommon.CONSTANTES;
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return ls_result;
	}
}
