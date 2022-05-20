package com.bachue.snr.prosnr01.ejb.session.stateless.consulta.trazabilidad;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.model.consulta.trazabilidad.ConsultaTrazabilidad;
import com.bachue.snr.prosnr01.model.consulta.trazabilidad.InformacionDocumento;
import com.bachue.snr.prosnr01.model.consulta.trazabilidad.Trazabilidad;
import com.bachue.snr.prosnr01.model.reasignar.ReasignarTurnos;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudMatricula;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.ui.FoliosSirUI;

import java.util.Collection;

import javax.ejb.Remote;


/**
 * Interface que contiene todos las propiedades ConsultaTrazabilidadRemote.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 20/02/2019
 */
@Remote
public interface ConsultaTrazabilidadRemote
{
	/**
	 * Cargar bandeja historicos.
	 *
	 * @param at_turnoActualizado de at turno actualizado
	 * @param as_idUsuario de as id usuario
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de FoliosSirUI
	 * @throws B2BException de b 2 B exception
	 */
	public FoliosSirUI cargarBandejaHistoricos(
	    Turno at_turnoActualizado, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Cargar info bandeja consulta trazabilidad.
	 *
	 * @param at_parametros de at parametros
	 * @param ab_onlyTurno de ab only turno
	 * @return el valor de consulta trazabilidad
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public ConsultaTrazabilidad cargarInfoBandejaConsultaTrazabilidad(Trazabilidad at_parametros, boolean ab_onlyTurno)
	    throws B2BException;

	/**
	 * Detalle turno.
	 *
	 * @param at_turno de turno
	 * @return el valor de trazabilidad
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Trazabilidad detalleTurno(Turno at_turno)
	    throws B2BException;

	/**
	 * Find consulta traza.
	 *
	 * @param at_parametros de Turno
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<ConsultaTrazabilidad> findConsultaTraza(Turno at_parametros)
	    throws B2BException;

	/**
	 * Find consulta traza solicitud.
	 *
	 * @param as_parametros de acct parametros
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<ConsultaTrazabilidad> findConsultaTrazaSolicitud(String as_parametros)
	    throws B2BException;

	/**
	 * Find data asociada.
	 *
	 * @param as_idTurno de as id turno
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Trazabilidad> findDataAsociada(String as_idTurno)
	    throws B2BException;

	/**
	 * Find datos reasignacion.
	 *
	 * @param lrt_rt de ReasignarTurnos
	 * @return el valor de reasignar turnos
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public ReasignarTurnos findDatosReasignacion(ReasignarTurnos lrt_rt)
	    throws B2BException;

	/**
	 * Find info doc.
	 *
	 * @param at_parametros de Turno
	 * @return el valor de informacion documento
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public InformacionDocumento findInfoDoc(Turno at_parametros)
	    throws B2BException;

	/**
	 * Find matriculas.
	 *
	 * @param as_idTurno de id turno
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<SolicitudMatricula> findMatriculas(String as_idTurno)
	    throws B2BException;

	/**
	 * Find proceso.
	 *
	 * @param at_parametros de Trazabilidad
	 * @return el valor de trazabilidad
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Trazabilidad findProceso(Trazabilidad at_parametros)
	    throws B2BException;

	/**
	 * Find trazabilidad.
	 *
	 * @param at_parametros de Trazabilidad
	 * @param ab_onlyTurno de ab only turno
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Trazabilidad> findTrazabilidad(Trazabilidad at_parametros, boolean ab_onlyTurno)
	    throws B2BException;

	/**
	 * Find trazabilidad vinculados.
	 *
	 * @param at_parametros de Trazabilidad
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Trazabilidad> findTrazabilidadVinculados(Trazabilidad at_parametros)
	    throws B2BException;

	/**
	 * Find turnos derivados con indicador vinculado.
	 *
	 * @param ls_turno de id turno
	 * @return el valor de string
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public String findTurnosDerivadosConIndicadorVinculado(String ls_turno)
	    throws B2BException;
}
