package com.bachue.snr.prosnr14.ejb.session.stateless.coexistencia;

import co.gov.supernotariado.www.services.bachue.cb.SBB_CB_HistoricoPropietarios.consultarHistoricoPropiedades.v1.TipoEntradaConsultarHistoricoPropiedades;
import co.gov.supernotariado.www.services.bachue.cb.SBB_CB_HistoricoPropietarios.consultarHistoricoPropiedades.v1.TipoSalidaConsultarHistoricoPropiedades;
import co.gov.supernotariado.www.services.bachue.cb.SBB_CB_HistoricoPropietarios.consultarHistoricoPropietarios.v1.TipoEntradaConsultarHistoricoPropietarios;
import co.gov.supernotariado.www.services.bachue.cb.SBB_CB_HistoricoPropietarios.consultarHistoricoPropietarios.v1.TipoSalidaConsultarHistoricoPropietarios;

import com.b2bsg.common.exception.B2BException;

import javax.ejb.Remote;


/**
 * Interface que contiene todos las propiedades InmueblesRemote.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 16/03/2020
 */
@Remote
public interface HistoricoPropietariosRemote
{
	/**
	 * Consultar historico propiedades.
	 *
	 * @param atechp_entrada de TipoEntradaConsultarHistoricoPropiedades
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de tipo salida consultar historico propiedades
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoSalidaConsultarHistoricoPropiedades consultarHistoricoPropiedades(
	    TipoEntradaConsultarHistoricoPropiedades atechp_entrada, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Consultar historico propietarios.
	 *
	 * @param atechp_entrada de TipoEntradaConsultarHistoricoPropietarios
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de tipo salida consultar historico propietarios
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoSalidaConsultarHistoricoPropietarios consultarHistoricoPropietarios(
	    TipoEntradaConsultarHistoricoPropietarios atechp_entrada, String as_userId, String as_localIp,
	    String                                    as_remoteIp
	)
	    throws B2BException;
}
