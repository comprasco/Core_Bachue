package com.bachue.snr.prosnr01.ejb.session.stateless.consulta.reparto.calificacion;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.model.consulta.reparto.calificacion.ConsultaRepartoCalificacion;
import com.bachue.snr.prosnr01.model.sdb.aut.Usuario;

import java.util.Collection;

import javax.ejb.Remote;


/**
 * Interface que contiene todos las propiedades ConsultaRepartoCalificacionRemote.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 20/02/2019
 */
@Remote
public interface ConsultaRepartoCalificacionRemote
{
	
	/**
	 * Buscar por ORIP.
	 *
	 * @param as_usuario de as usuario
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Usuario> findByORIP(String as_usuario)
	    throws B2BException;

	/**
	 * Buscar por user.
	 *
	 * @param au_usuario de au usuario
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<ConsultaRepartoCalificacion> findByUser(Usuario au_usuario)
	    throws B2BException;
}
