package com.bachue.snr.prosnr05.ejb.session.stateless.consulta.catalogos;

import co.gov.supernotariado.www.schemas.bachue.cb.consultacatalogos.consultarcatalogos.v1.TipoEntradaConsultarCatalogos;
import co.gov.supernotariado.www.schemas.bachue.cb.consultacatalogos.consultarcatalogos.v1.TiposCatalogos;

import com.b2bsg.common.exception.B2BException;

import javax.ejb.Remote;


/**
 * Interface que contiene todos las propiedades ConsultaCatalogosRemote.
 *
 * @author  Manuel Blanco
 * Fecha de Creacion: 16/09/2019
 */
@Remote
public interface ConsultaCatalogosRemote
{
	/**
	 * Consultar catalogos.
	 *
	 * @param atecc_param de TipoEntradaConsultarCatalogos
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de tipos catalogos[]
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TiposCatalogos[] consultarCatalogos(
	    TipoEntradaConsultarCatalogos atecc_param, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;
}
