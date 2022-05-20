package com.bachue.snr.prosnr14.ejb.session.stateless.coexistencia;

import co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarDatosBasicos.v1.TipoEntradaDatosBasicosMatriculas;
import co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarDatosBasicos.v1.TipoSalidaDatosBasicosMatriculas;
import co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarDatosImueble.v1.TipoEntradaDatosInmueble;
import co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarDatosImueble.v1.TipoSalidaDatosInmueble;
import co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarDireccionesAnteriores.v1.TipoEntradaDireccionesAnteriores;
import co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarDireccionesAnteriores.v1.TipoSalidaDireccionesAnteriores;
import co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarMatriculas.v1.TipoEntradaConsultaMatriculas;
import co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarMatriculas.v1.TipoSalidaConsultaMatriculas;
import co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarPropietarios.v1.TipoEntradaDatosPropietario;
import co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarPropietarios.v1.TipoSalidaDatosPropietario;

import com.b2bsg.common.exception.B2BException;

import javax.ejb.Remote;


/**
 * Interface que contiene todos las propiedades InmueblesRemote.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 13/03/2020
 */
@Remote
public interface InmueblesRemote
{
	/**
	 * Consultar datos inmueble.
	 *
	 * @param atedi_entrada de TipoEntradaDatosInmueble
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de tipo salida datos inmueble
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoSalidaDatosInmueble consultarDatosInmueble(
	    TipoEntradaDatosInmueble atedi_entrada, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Consultar propietarios.
	 *
	 * @param atedp_entrada de TipoEntradaDatosPropietario
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de tipo salida datos propietario
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoSalidaDatosPropietario consultarPropietarios(
	    TipoEntradaDatosPropietario atedp_entrada, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Consultar direcciones anteriores.
	 *
	 * @param ateda_entrada de TipoEntradaDireccionesAnteriores
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de tipo salida direcciones anteriores
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoSalidaDireccionesAnteriores consultarDireccionesAnteriores(
	    TipoEntradaDireccionesAnteriores ateda_entrada, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Consultar matriculas.
	 *
	 * @param atecm_entrada de TipoEntradaConsultaMatriculas
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de tipo salida consulta matriculas
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoSalidaConsultaMatriculas consultarMatriculas(
	    TipoEntradaConsultaMatriculas atecm_entrada, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Consultar datos basicos.
	 *
	 * @param atedbm_entrada de TipoEntradaDatosBasicosMatriculas
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de tipo salida datos basicos matriculas
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoSalidaDatosBasicosMatriculas consultarDatosBasicos(
	    TipoEntradaDatosBasicosMatriculas atedbm_entrada, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;
}
