/**
 * SUT_CB_ConsultaMigracion_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cb.consultamigracion.v1;


/**
 * Interface que contiene todos las propiedades SUT_CB_ConsultaMigracion_PortType.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 3/06/2020
 */
public interface SUT_CB_ConsultaMigracion_PortType extends java.rmi.Remote
{
	/**
	 * Consultar migracion matriculas.
	 *
	 * @param entrada de entrada
	 * @return el valor de co.gov.supernotariado.www.services.bachue.cb.consultamigracion.consultar migracion matriculas.v 1 . tipo salida consulta migracion matriculas
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public co.gov.supernotariado.www.services.bachue.cb.consultamigracion.consultarMigracionMatriculas.v1.TipoSalidaConsultaMigracionMatriculas consultarMigracionMatriculas(
	    co.gov.supernotariado.www.services.bachue.cb.consultamigracion.consultarMigracionMatriculas.v1.TipoEntradaConsultaMigracionMatriculas entrada
	)
	    throws java.rmi.RemoteException;

	/**
	 * Consultar migracion ORIP.
	 *
	 * @param entrada de entrada
	 * @return el valor de co.gov.supernotariado.www.services.bachue.cb.consultamigracion.consultar migracion ORI p.v 1 . tipo salida consulta migracion ORIP
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public co.gov.supernotariado.www.services.bachue.cb.consultamigracion.consultarMigracionORIP.v1.TipoSalidaConsultaMigracionORIP consultarMigracionORIP(
	    co.gov.supernotariado.www.services.bachue.cb.consultamigracion.consultarMigracionORIP.v1.TipoEntradaConsultaMigracionORIP entrada
	)
	    throws java.rmi.RemoteException;

	/**
	 * Consultar migracion predio.
	 *
	 * @param entrada de entrada
	 * @return el valor de co.gov.supernotariado.www.services.bachue.cb.consultamigracion.consultar migracion predio.v 1 . tipo salida consulta migracion predio
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public co.gov.supernotariado.www.services.bachue.cb.consultamigracion.consultarMigracionPredio.v1.TipoSalidaConsultaMigracionPredio consultarMigracionPredio(
	    co.gov.supernotariado.www.services.bachue.cb.consultamigracion.consultarMigracionPredio.v1.TipoEntradaConsultaMigracionPredio entrada
	)
	    throws java.rmi.RemoteException;
}
