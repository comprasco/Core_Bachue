package com.bachue.snr.prosnr14.ejb.session.stateless.coexistencia;

import co.gov.supernotariado.www.services.bachue.cb.tramitesencurso.consultarTramites.v1.TipoEntradaDatosTramites;
import co.gov.supernotariado.www.services.bachue.cb.tramitesencurso.consultarTramites.v1.TipoSalidaDatosTramites;

import com.b2bsg.common.exception.B2BException;

import javax.ejb.Remote;


/**
 * Interface que contiene todos las propiedades TramitesEnCursoRemote.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 13/03/2020
 */
@Remote
public interface TramitesEnCursoRemote
{
	/**
	 * Consultar tramites.
	 *
	 * @param atedt_entrada de TipoEntradaDatosTramites
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de tipo salida datos tramites
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoSalidaDatosTramites consultarTramites(
	    TipoEntradaDatosTramites atedt_entrada, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;
}
