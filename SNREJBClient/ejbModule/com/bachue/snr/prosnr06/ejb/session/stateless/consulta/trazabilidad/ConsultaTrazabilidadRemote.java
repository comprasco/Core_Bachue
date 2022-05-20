package com.bachue.snr.prosnr06.ejb.session.stateless.consulta.trazabilidad;

import co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultaActosTurno.v1.TipoEntradaConsultaActosTurno;
import co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultaActosTurno.v1.TipoSalidaConsultaActosTurno;
import co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultaDetalleCertificado.v1.TipoEntradaConsultaDetalleCertificado;
import co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultaDetalleCertificado.v1.TipoSalidaConsultaDetalleCertificado;
import co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultartrazabilidad.v1.TipoEntradaConsultarTrazabilidad;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.model.consulta.trazabilidad.ConsultaTrazabilidad;

import java.rmi.RemoteException;

import javax.ejb.Remote;


/**
 * Interface que contiene todos las propiedades ConsultaTrazabilidadRemote.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 24/09/2019
 */
@Remote
public interface ConsultaTrazabilidadRemote
{
	/**
	 * Consulta actos turno.
	 *
	 * @param atecat_peticion correspondiente al valor de entrada
	 * @param as_userId Objeto de tipo String que contiene el usuario que realiza la peticion
	 * @param as_localIp Objeto de tipo String que contiene IP local que realiza la peticion
	 * @param as_remoteIp Objeto de tipo String que contiene IP remota que realiza la peticion
	 * @return el valor de co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consulta actos turno.v 1 . tipo salida consulta actos turno
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public TipoSalidaConsultaActosTurno consultaActosTurno(
	    TipoEntradaConsultaActosTurno atecat_peticion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Consulta detalle certificado.
	 *
	 * @param atecdc_peticion correspondiente al valor de entrada
	 * @param as_userId Objeto de tipo String que contiene el usuario que realiza la peticion
	 * @param as_localIp Objeto de tipo String que contiene IP local que realiza la peticion
	 * @param as_remoteIp Objeto de tipo String que contiene IP remota que realiza la peticion
	 * @return el valor de co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consulta detalle certificado.v 1 . tipo salida consulta detalle certificado
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public TipoSalidaConsultaDetalleCertificado consultaDetalleCertificado(
	    TipoEntradaConsultaDetalleCertificado atecdc_peticion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Consultar trazabilidad.
	 *
	 * @param atect_entrada de TipoEntradaConsultarTrazabilidad
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de consulta trazabilidad
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public ConsultaTrazabilidad consultarTrazabilidad(
	    TipoEntradaConsultarTrazabilidad atect_entrada, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;
}
