/**
 * SBB_EF_OperacionesFinancieras_PortType.java
 *
 * This file was auto-generated from WSDL
 * by Objeto resultante de la respuesta de la operación  Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.ef.operacionesfinancieras.v2;

import co.gov.supernotariado.www.schemas.bachue.ef.operacionesfinancieras.actualizardatossolicitante.v2.TipoEntradaActualizarDatosSolicitante;
import co.gov.supernotariado.www.schemas.bachue.ef.operacionesfinancieras.actualizardatossolicitante.v2.TipoSalidaActualizarDatosSolicitante;
import co.gov.supernotariado.www.schemas.bachue.ef.operacionesfinancieras.consultarestadoliquidacion.v2.TipoEntradaConsultarEstadoLiquidacion;
import co.gov.supernotariado.www.schemas.bachue.ef.operacionesfinancieras.consultarestadoliquidacion.v2.TipoSalidaConsultarEstadoLiquidacion;
import co.gov.supernotariado.www.schemas.bachue.ef.operacionesfinancieras.consultartarifaservicio.v2.TipoEntradaConsultarTarifaServicio;
import co.gov.supernotariado.www.schemas.bachue.ef.operacionesfinancieras.consultartarifaservicio.v2.TipoSalidaConsultarTarifaServicio;
import co.gov.supernotariado.www.schemas.bachue.ef.operacionesfinancieras.generarliquidacion.v2.TipoEntradaGenerarLiquidacion;
import co.gov.supernotariado.www.schemas.bachue.ef.operacionesfinancieras.generarliquidacion.v2.TipoSalidaGenerarLiquidacion;
import co.gov.supernotariado.www.schemas.bachue.ef.operacionesfinancieras.notificarpago.v2.TipoEntradaNotificarPago;
import co.gov.supernotariado.www.schemas.bachue.ef.operacionesfinancieras.notificarpago.v2.TipoSalidaNotificarPago;
import co.gov.supernotariado.www.schemas.bachue.ef.operacionesfinancieras.obtenerpdfliquidacion.v2.TipoEntradaObtenerPDFLiquidacion;
import co.gov.supernotariado.www.schemas.bachue.ef.operacionesfinancieras.obtenerpdfliquidacion.v2.TipoSalidaObtenerPDFLiquidacion;

import java.rmi.Remote;
import java.rmi.RemoteException;


/**
 * Interfaz para especificar las operaciones a consumir del bus de Operaciones Financieras.
 *
 * @author Manuel Blanco
 */
public interface SBB_EF_OperacionesFinancieras_PortType extends Remote
{
	/**
	 * Consultar estado liquidacion.
	 *
	 * @param atecel_entrada Objeto contenedor de los parametros de entrada para la ejecución de la operación atecel entrada
	 * @return Objeto resultante de la respuesta de la operación  tipo salida consultar estado liquidacion
	 * @throws RemoteException Si ocurre un error en la comunicación con el sistema que expone la operación
	 */
	public TipoSalidaConsultarEstadoLiquidacion consultarEstadoLiquidacion(
	    TipoEntradaConsultarEstadoLiquidacion atecel_entrada
	)
	    throws RemoteException;

	/**
	 * Consultar tarifa servicio.
	 *
	 * @param atects_entrada Objeto contenedor de los parametros de entrada para la ejecución de la operación atects entrada
	 * @return Objeto resultante de la respuesta de la operación  tipo salida consultar tarifa servicio
	 * @throws RemoteException Si ocurre un error en la comunicación con el sistema que expone la operación
	 */
	public TipoSalidaConsultarTarifaServicio consultarTarifaServicio(TipoEntradaConsultarTarifaServicio atects_entrada)
	    throws RemoteException;

	/**
	 * Generar liquidacion.
	 *
	 * @param ategl_entrada Objeto contenedor de los parametros de entrada para la ejecución de la operación ategl entrada
	 * @return Objeto resultante de la respuesta de la operación  tipo salida generar liquidacion
	 * @throws RemoteException Si ocurre un error en la comunicación con el sistema que expone la operación
	 */
	public TipoSalidaGenerarLiquidacion generarLiquidacion(TipoEntradaGenerarLiquidacion ategl_entrada)
	    throws RemoteException;

	/**
	 * Notificar pago.
	 *
	 * @param atenp_entrada Objeto contenedor de los parametros de entrada para la ejecución de la operación atenp entrada
	 * @return Objeto resultante de la respuesta de la operación  tipo salida notificar pago
	 * @throws RemoteException Si ocurre un error en la comunicación con el sistema que expone la operación
	 */
	public TipoSalidaNotificarPago notificarPago(TipoEntradaNotificarPago atenp_entrada)
	    throws RemoteException;

	/**
	 * Obtener PDF liquidacion.
	 *
	 * @param ateopl_entrada Objeto contenedor de los parametros de entrada para la ejecución de la operación ateopl entrada
	 * @return Objeto resultante de la respuesta de la operación  tipo salida obtener PDF liquidacion
	 * @throws RemoteException Si ocurre un error en la comunicación con el sistema que expone la operación
	 */
	public TipoSalidaObtenerPDFLiquidacion obtenerPDFLiquidacion(TipoEntradaObtenerPDFLiquidacion ateopl_entrada)
	    throws RemoteException;

	/**
	 * Actualizar datos solicitante.
	 *
	 * @param ateads_entrada Objeto contenedor de los parametros de entrada para la ejecución de la operación ateads entrada
	 * @return Objeto resultante de la respuesta de la operación  tipo salida actualizar datos solicitante
	 * @throws RemoteException Si ocurre un error en la comunicación con el sistema que expone la operación
	 */
	public TipoSalidaActualizarDatosSolicitante actualizarDatosSolicitante(
	    TipoEntradaActualizarDatosSolicitante ateads_entrada
	)
	    throws RemoteException;
}
