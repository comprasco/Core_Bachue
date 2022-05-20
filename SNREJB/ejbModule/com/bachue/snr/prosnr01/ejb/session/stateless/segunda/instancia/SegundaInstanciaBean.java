package com.bachue.snr.prosnr01.ejb.session.stateless.segunda.instancia;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.business.segundaInstancia.SegundaInstanciaBusiness;

import com.bachue.snr.prosnr01.ejb.session.stateless.Logger;

import com.bachue.snr.prosnr01.model.registro.Registro;
import com.bachue.snr.prosnr01.model.sdb.pgn.TagPlantillaResolucion;

import org.perf4j.StopWatch;


/**
 * Clase que contiene todos las propiedades SegundaInstanciaBean.
 *
 * @author Duvan Beltrán
 */
@javax.ejb.Stateless(name = "segundaInstancia", mappedName = "segundaInstanciaMappedName")
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class SegundaInstanciaBean implements SegundaInstanciaRemote
{
	/**  Atributo iaab_segundaInstanciaBusinessBusiness. */
	private SegundaInstanciaBusiness iaab_segundaInstanciaBusinessBusiness;

	/**
	 * Metodo encargado de retornar el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public SegundaInstanciaBusiness getSegundaInstanciaBusiness()
	{
		if(iaab_segundaInstanciaBusinessBusiness == null)
			iaab_segundaInstanciaBusinessBusiness = new SegundaInstanciaBusiness();

		return iaab_segundaInstanciaBusinessBusiness;
	}

	/** {@inheritdoc} */
	public TagPlantillaResolucion cargarDatosSegundaInstancia(
	    TagPlantillaResolucion aaa_parametros, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch              lsw_watch;
		TagPlantillaResolucion laa_actuacionesAdministrativas;

		lsw_watch                          = Logger.getNewStopWatch();
		laa_actuacionesAdministrativas     = getSegundaInstanciaBusiness()
				                                     .cargarDatosSegundaInstancia(
				    aaa_parametros, as_userId, as_remoteIp
				);

		Logger.log(
		    lsw_watch, "ReferenceBean", "cargarDatosActuacionesAdministrativas", as_userId, as_localIp, as_remoteIp,
		    null
		);

		return laa_actuacionesAdministrativas;
	}

	/** {@inheritdoc} */
	public TagPlantillaResolucion generarDocumentosSegundaInstancia(
	    TagPlantillaResolucion aaa_parametros, String as_userId, String as_localIp, String as_remoteIp, long al_motivo
	)
	    throws B2BException
	{
		StopWatch              lsw_watch;
		TagPlantillaResolucion lsi_segundaInstancia;

		lsw_watch                = Logger.getNewStopWatch();
		lsi_segundaInstancia     = getSegundaInstanciaBusiness()
				                           .generarDocumentosSegundaInstancia(
				    aaa_parametros, as_userId, as_remoteIp, al_motivo
				);

		Logger.log(
		    lsw_watch, "ReferenceBean", "generarDocumentosSegundaInstancia", as_userId, as_localIp, as_remoteIp, null
		);

		return lsi_segundaInstancia;
	}

	/** {@inheritdoc} */
	public void enviarAprobador(
	    TagPlantillaResolucion aaa_parametros, long al_idMotivo, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();
		getSegundaInstanciaBusiness().enviarAprobador(aaa_parametros, as_userId, as_remoteIp, al_idMotivo);

		Logger.log(
		    lsw_watch, "ReferenceBean", "enviarResponsableActuacionesAdmin", as_userId, as_localIp, as_remoteIp, null
		);
	}

	/** {@inheritdoc} */
	public TagPlantillaResolucion visualizarCambiosResolucion(
	    TagPlantillaResolucion aaa_parametros, long al_motivo, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch              lsw_watch;
		TagPlantillaResolucion laa_actuacionesAdministrativas;

		lsw_watch                          = Logger.getNewStopWatch();
		laa_actuacionesAdministrativas     = getSegundaInstanciaBusiness()
				                                     .visualizarCambiosResolucion(
				    aaa_parametros, as_userId, as_remoteIp, al_motivo
				);

		Logger.log(lsw_watch, "ReferenceBean", "visualizarCambiosResolucion", as_userId, as_localIp, as_remoteIp, null);

		return laa_actuacionesAdministrativas;
	}

	/** {@inheritdoc} */
	public Registro buscarPersonasAsociadasSegundaInstancia(
	    String as_idSolicitud, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;
		Registro  lr_registro;

		lsw_watch       = Logger.getNewStopWatch();
		lr_registro     = getSegundaInstanciaBusiness().buscarPersonasSegundaInstancia(as_idSolicitud);

		Logger.log(
		    lsw_watch, "ReferenceBean", "buscarPersonasAsociadasRecursos", as_userId, as_localIp, as_remoteIp, null
		);

		return lr_registro;
	}
}
