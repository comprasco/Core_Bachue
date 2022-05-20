package com.bachue.snr.prosnr01.web.bean.recursos;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.EtapaCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;

import com.bachue.snr.prosnr01.ejb.session.stateless.actuaciones.administrativas.ActuacionesAdministrativasRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.registro.RegistroRemote;

import com.bachue.snr.prosnr01.model.sdb.pgn.TagPlantillaResolucion;

import org.primefaces.PrimeFaces;

import java.io.Serializable;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


/**
 * Clase que contiene todos las propiedades y acciones de BeanRecursos.
 *
 * @author hcastaneda
 *
 */
@SessionScoped
@ManagedBean(name = "beanResolucionRecurso")
public class BeanResolucionRecurso extends BeanRechazaRecurso implements Serializable
{
	private static final long serialVersionUID = 8292629734996600776L;

	/** Constante CS_ID_FORM. */
	private static final String CS_ID_FORM = "fResolucionRecurso";

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanResolucionRecurso.class);

	/** Constante CS_ID_GROWL. */
	private static final String CS_ID_GROWL = CS_ID_FORM + ":globalMsg";

	/** Atributo laar_actuacionesAdministrativasRemote */
	@EJB
	private ActuacionesAdministrativasRemote iaar_actuacionesAdministrativasRemote;

	/** Atributo irr registro remote */
	@EJB
	private RegistroRemote irr_registroRemote;

	/**
	 * Metodo encargado de cargar la información de recursos.
	 * @throws B2BException
	 */
	public void cargarDatosResolucionRecursos()
	    throws B2BException
	{
		cargarDatosActuacionesAdministrativas(true);
	}

	/**
	 * Metodo encargado de cargar la información de municipios para actuaciones administrativas.
	 */
	public String enviarAResolucionRecurso()
	{
		return enviarResponsableActuacionesAdmin(
		    true, EtapaCommon.ID_ETAPA_ANALISIS_DE_RECURSOS_RESOLUCION_DE_RECURSOS
		);
	}

	/**
	 * Metodo encargado de generar los documentos de actuaciones administrativas.
	 */
	public void generarDocumentosResolucionRecurso()
	{
		try
		{
			TagPlantillaResolucion laa_actuacionesAdministrativas;
			long                   ll_idMotivo;
			String                 ls_tipoArchivo;

			laa_actuacionesAdministrativas     = new TagPlantillaResolucion();
			ll_idMotivo                        = getIdMotivo();
			ls_tipoArchivo                     = StringUtils.getStringNotNull(getTipoArchivo());

			laa_actuacionesAdministrativas.setOficiosTexto(convertirOficiosTexto(getOficiosTexto()));
			laa_actuacionesAdministrativas.setSolicitud(getSolicitud());
			laa_actuacionesAdministrativas.setIdTurno(getIdTurno());
			laa_actuacionesAdministrativas.setIdTurnoHistoria(getIdTurnoHistoria());
			laa_actuacionesAdministrativas.setResultadosNotificacion(getResultadosNotificacion());
			laa_actuacionesAdministrativas.setTiposDocumentales(getTiposDocumentales());
			laa_actuacionesAdministrativas.setTipoArchivo(ls_tipoArchivo);
			laa_actuacionesAdministrativas.setRecurso(true);

			laa_actuacionesAdministrativas = iaar_actuacionesAdministrativasRemote.generarDocumentosActuacionesAdmin(
				    laa_actuacionesAdministrativas, ll_idMotivo, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

			if(laa_actuacionesAdministrativas != null)
			{
				PrimeFaces.current().executeScript("PF('wvPoll').start();");

				setMostrarDescargarZip(true);
				setDocumentosEnviados(false);

				{
					Object[] loa_object;

					loa_object     = new String[1];

					loa_object[0] = ls_tipoArchivo.replace("_", " ");

					addMessage(EstadoCommon.I, MessagesKeys.SE_GENERO_CORRECTAMENTE_AUTO, loa_object);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("generarDocumentosResolucionRecurso", lb2be_e);

			addMessage(lb2be_e);
		}
		finally
		{
			actualizarComponente(CS_ID_GROWL);
		}
	}

	/**
	 * Metodo encargado de visualizar los cambios realizados en la resolución.
	 *
	 * @return Retorna a la misma pantalla que lo invocó.
	 */
	public String visualizarCambiosResolucionRecurso()
	{
		return visualizarCambiosResolucion(true);
	}
}
