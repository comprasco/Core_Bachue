/**
 * SUT_CE_MensajeroSms_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.ce.mensajerosms.v1;


/**
 * Interface que contiene todos las propiedades SUT_CE_MensajeroSms_PortType.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 26/03/2020
 */
public interface SUT_CE_MensajeroSms_PortType extends java.rmi.Remote
{
	/**
	 * Enviar mensaje texto.
	 *
	 * @param entrada de entrada
	 * @return el valor de co.gov.supernotariado.www.schemas.bachue.ce.mensajerosms.enviarmensajetexto.v 1 . tipo salida enviar mensaje texto
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public co.gov.supernotariado.www.schemas.bachue.ce.mensajerosms.enviarmensajetexto.v1.TipoSalidaEnviarMensajeTexto enviarMensajeTexto(
	    co.gov.supernotariado.www.schemas.bachue.ce.mensajerosms.enviarmensajetexto.v1.TipoEntradaEnviarMensajeTexto entrada
	)
	    throws java.rmi.RemoteException;
}
