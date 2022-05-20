/**
 * SDI_CB_ConsultaTrazabilidad_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cb.consultatrazabilidad.v1;


/**
 * Interface que contiene todos las propiedades SDI_CB_ConsultaTrazabilidad_PortType.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 3/09/2020
 */
public interface SDI_CB_ConsultaTrazabilidad_PortType extends java.rmi.Remote
{
	/**
	 * Consulta actos turno.
	 *
	 * @param entrada correspondiente al valor de entrada
	 * @return el valor de co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consulta actos turno.v 1 . tipo salida consulta actos turno
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultaActosTurno.v1.TipoSalidaConsultaActosTurno consultaActosTurno(
	    co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultaActosTurno.v1.TipoEntradaConsultaActosTurno entrada
	)
	    throws java.rmi.RemoteException;

	/**
	 * Consulta detalle certificado.
	 *
	 * @param entrada correspondiente al valor de entrada
	 * @return el valor de co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consulta detalle certificado.v 1 . tipo salida consulta detalle certificado
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultaDetalleCertificado.v1.TipoSalidaConsultaDetalleCertificado consultaDetalleCertificado(
	    co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultaDetalleCertificado.v1.TipoEntradaConsultaDetalleCertificado entrada
	)
	    throws java.rmi.RemoteException;

	/**
	 * Consultar trazabilidad.
	 *
	 * @param entrada correspondiente al valor de entrada
	 * @return el valor de co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultartrazabilidad.v 1 . tipo salida consultar trazabilidad
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultartrazabilidad.v1.TipoSalidaConsultarTrazabilidad consultarTrazabilidad(
	    co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultartrazabilidad.v1.TipoEntradaConsultarTrazabilidad entrada
	)
	    throws java.rmi.RemoteException;
}
