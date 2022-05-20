/**
 * SUT_CN_Mensajero_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cn.mensajero.v1;


/**
 * Interface que contiene todos las propiedades SUT_CN_Mensajero_PortType.
 *
 * @author  Manuel Blanco
 *
 */
public interface SUT_CN_Mensajero_PortType extends java.rmi.Remote
{
	/**
	 * Enviar mensaje.
	 *
	 * @param entrada de entrada
	 * @return el valor de co.gov.supernotariado.www.schemas.bachue.cn.mensajero.enviarmensaje.v 1 . tipo salida enviar mensaje
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public co.gov.supernotariado.www.schemas.bachue.cn.mensajero.enviarmensaje.v1.TipoSalidaEnviarMensaje enviarMensaje(
	    co.gov.supernotariado.www.schemas.bachue.cn.mensajero.enviarmensaje.v1.TipoEntradaEnviarMensaje entrada
	)
	    throws java.rmi.RemoteException;

	/**
	 * Consultar envio.
	 *
	 * @param entrada de entrada
	 * @return el valor de co.gov.supernotariado.www.schemas.bachue.cn.mensajero.consultarenvio.v 1 . tipo salida consultar envio
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public co.gov.supernotariado.www.schemas.bachue.cn.mensajero.consultarenvio.v1.TipoSalidaConsultarEnvio consultarEnvio(
	    co.gov.supernotariado.www.schemas.bachue.cn.mensajero.consultarenvio.v1.TipoEntradaConsultarEnvio entrada
	)
	    throws java.rmi.RemoteException;
}
