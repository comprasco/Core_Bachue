package com.bachue.snr.prosnr21.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.TipoContenidoCommon;

import com.bachue.snr.prosnr01.model.sdb.pgn.Constantes;

import com.bachue.snr.prosnr21.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr21.common.constants.MessagesKeys;
import com.bachue.snr.prosnr21.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr21.ejb.session.stateless.conciliaciones.ParameterRemote;

import com.bachue.snr.prosnr21.web.bean.BaseBean;
import com.bachue.snr.prosnr21.web.util.FacesUtils;

import org.primefaces.PrimeFaces;

import org.primefaces.event.FileUploadEvent;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import java.util.Collection;
import java.util.Set;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import javax.servlet.http.HttpServletRequest;


/**
 * Clase que contiene todos las propiedades y acciones de BeanConstantes.
 *
 * @author dbeltran
 */
@ManagedBean(name = "beanConstantes")
@SessionScoped
public class BeanConstantes extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 4903603478073131491L;

	/** Propiedad ic parametros. */
	private Constantes ic_parametros;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad iss visitados. */
	private Set<String> iss_visitados;

	/** Propiedad isc imagen. */
	private StreamedContent isc_imagen;

	/** Propiedad isc zip RTFS. */
	private StreamedContent isc_zipRTFS;

	/** Propiedad iuf upload file. */
	private UploadedFile iuf_uploadFile;

	/** Propiedad ib ejecutar proc. */
	private boolean ib_ejecutarProc;

	/** Propiedad ib insertar. */
	private boolean ib_insertar;

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
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
	 * Método para  cambiar estado para saber si se desea insertar una constante.
	 */
	public void cambiarEstado()
	{
		Boolean lb_parametro;
		lb_parametro = FacesUtils.getBooleanFacesParameter("pInsertar");

		if(lb_parametro != null)
			setInsertar(BooleanUtils.getBooleanValue(lb_parametro));

		setParametros(null);
	}

	/**
	 * Método para consultar en la base de datos la tabla SDB_PGN_CONSTANTES
	 * con un id_constante especifico.
	 */
	public void consultaDetallada()
	{
		try
		{
			String ls_idConstante;

			ls_idConstante = FacesUtils.getStringFacesParameter("idConstante");

			if(StringUtils.isValidString(ls_idConstante))
			{
				setParametros(ipr_parameterRemote.findConstantById(ls_idConstante));
				setInsertar(false);
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}
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
			lcc_constantes = ipr_parameterRemote.findAllConstants();
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
	 * @throws IOException
	 */
	public String salvar()
	    throws IOException
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

			lb_insertar = isInsertar();

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

				ipr_parameterRemote.salvarConstante(lc_constante, lb_insertar);
			}

			if(!isInsertar())
				addMessageInfo("I", MessagesKeys.MODIFICACION_EXITOSA);
			else
				addMessageInfo("I", MessagesKeys.INCERSION_EXITOSA);

			setParametros(null);

			ls_result = NavegacionCommon.CONSTANTES;

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
				
				FacesContext.getCurrentInstance().getExternalContext().redirect("constantes.jsf");
				PrimeFaces.current().ajax().update("fConstantes:globalMsg");
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return ls_result;
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
				lc_constante = new Constantes();

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
}
