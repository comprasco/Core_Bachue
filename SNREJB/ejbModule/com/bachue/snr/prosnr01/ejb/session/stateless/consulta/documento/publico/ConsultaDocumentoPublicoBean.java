package com.bachue.snr.prosnr01.ejb.session.stateless.consulta.documento.publico;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.business.consulta.documento.publico.ConsultaDocumentoPublicoBusiness;

import com.bachue.snr.prosnr01.ejb.session.stateless.Logger;

import com.bachue.snr.prosnr01.model.antiguoSistema.ConsultaDatosDocumento;
import com.bachue.snr.prosnr01.model.sdb.bng.Documento;

import org.perf4j.StopWatch;


/**
 * Clase que contiene todos las propiedades ConsultaDocumentoPublicoBean.
 *
 * @author Manuel Blanco
 */
@javax.ejb.Stateless(name = "ConsultaDocumentoPublico", mappedName = "consultaDocumentoPublicoMappedName")
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class ConsultaDocumentoPublicoBean implements ConsultaDocumentoPublicoRemote
{
	/** Propiedad icdpb business. */
	private ConsultaDocumentoPublicoBusiness icdpb_business;

	/** {@inheritdoc} */
	public Documento findByIdSolicitud(
	    ConsultaDatosDocumento acdd_datosDocumento, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;
		Documento ld_documentos;

		lsw_watch         = Logger.getNewStopWatch();
		ld_documentos     = getConsultaDocumentoPublicoBusiness().findByDatosConsulta(acdd_datosDocumento);

		Logger.log(
		    lsw_watch, "ConsultaSolicitudBusiness", "findByIdSolicitud", as_userId, as_localIp, as_remoteIp, null
		);

		return ld_documentos;
	}

	/**
	 * Retorna el valor de consulta documento publico business.
	 *
	 * @return el valor de consulta documento publico business
	 */
	private ConsultaDocumentoPublicoBusiness getConsultaDocumentoPublicoBusiness()
	{
		if(icdpb_business == null)
			icdpb_business = new ConsultaDocumentoPublicoBusiness();

		return icdpb_business;
	}
}
