package com.bachue.snr.prosnr01.ejb.session.stateless.aprobador.antiguo.sistema;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.model.aprobacion.Aprobacion;
import com.bachue.snr.prosnr01.model.calificacion.TramiteCantidad;

import java.util.Collection;

import javax.ejb.Remote;


/**
 * Interface que contiene todos las propiedades AprobadorAntiguoSistemaRemote.
 *
 * @author  Juli�n David Vaca Rodriguez
 * Fecha de Creacion: 20/02/2019
 */
@Remote
public interface AprobadorAntiguoSistemaRemote
{
	/**
	 * M�todo que busca toda la informacion que coincida con los par�metros de b�squeda de aa_oa.
	 *
	 * @param aa_oa de Aprobacion
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operaci�n.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operaci�n.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operaci�n.
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Aprobacion> findAllData(
	    Aprobacion aa_oa, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * M�todo que busca toda la informacion para la bandeja que coninciada con as_userId.
	 *
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operaci�n.
	 * @param as_idTurno de id turno
	 * @param as_nir de nir
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<TramiteCantidad> findInboxByUserId(String as_userId, String as_idTurno, String as_nir)
	    throws B2BException;

	/**
	 * M�todo que genera un archivo zip con datos de aoa_oa.
	 *
	 * @param aoa_oa de Aprobacion
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operaci�n.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operaci�n.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operaci�n.
	 * @return el valor de aprobacion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Aprobacion generateZip(Aprobacion aoa_oa, String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException;

	/**
	 * M�todo que se usa para salvar el tr�mite de aprobaci�n.
	 *
	 * @param aca_parametros de Collection<Aprobacion>
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operaci�n.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operaci�n.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operaci�n.
	 * @return el valor de aprobacion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Aprobacion salvarAprobacion(
	    Collection<Aprobacion> aca_parametros, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;
}
