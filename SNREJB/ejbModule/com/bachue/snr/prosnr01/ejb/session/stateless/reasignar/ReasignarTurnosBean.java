package com.bachue.snr.prosnr01.ejb.session.stateless.reasignar;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.business.reasignar.ReasignarTurnosBusiness;

import com.bachue.snr.prosnr01.ejb.session.stateless.Logger;

import com.bachue.snr.prosnr01.model.reasignar.ReasignarTurnos;
import com.bachue.snr.prosnr01.model.sdb.aut.Rol;
import com.bachue.snr.prosnr01.model.sdb.aut.Usuario;
import com.bachue.snr.prosnr01.model.sdb.pgn.CirculoRegistral;
import com.bachue.snr.prosnr01.model.sdb.pgn.Etapa;

import org.perf4j.StopWatch;

import java.util.Collection;


/**
 * Clase que contiene todos las propiedades ReasignarTurnosBean.
 *
 * @author Julian Vaca
 */
@javax.ejb.Stateless(name = "ReasignarTurnos", mappedName = "reasignarTurnosMappedName")
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class ReasignarTurnosBean implements ReasignarTurnosRemote
{
	/** Propiedad irt business. */
	private ReasignarTurnosBusiness irt_business;

	/**
	 * Modifica el valor de reasignar turnos.
	 *
	 * @param reasignarTurnos asigna el valor a la propiedad reasignar turnos
	 */
	public void setReasignarTurnos(ReasignarTurnosBusiness reasignarTurnos)
	{
		this.irt_business = reasignarTurnos;
	}

	/**
	 * Retorna el valor de reasignar turnos.
	 *
	 * @return el valor de reasignar turnos
	 */
	public ReasignarTurnosBusiness getReasignarTurnosBusiness()
	{
		if(irt_business == null)
			irt_business = new ReasignarTurnosBusiness();

		return irt_business;
	}

	/**
	 * Método encargado de actualizar el campo REASIGNADO_ESPECIAL de la tabla SDB_ACC_TURNO.
	 *
	 * @param acrt_data Colección Tipo ReasignarTurnos con todos los turnos de la colección
	 * @param as_idUsuario de as id usuario
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	@Override
	public void actualizarReasignadoEspecial(
	    Collection<ReasignarTurnos> acrt_data, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getReasignarTurnosBusiness().actualizarReasignadoEspecial(acrt_data, as_idUsuario, as_remoteIp);

		Logger.log(
		    lsw_watch, "ReasignarTurnosBusiness", "actualizarReasignadoEspecial", as_idUsuario, as_localIp, as_remoteIp,
		    null
		);
	}

	/**
	 * Método encargado de consultar etapas.
	 *
	 * @param as_idRol String con id rol de consultas
	 * @return Colección de objeto Etapa con resultados de consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	@Override
	public Collection<Etapa> consultarEtapas(String as_idRol)
	    throws B2BException
	{
		StopWatch         lsw_watch;
		Collection<Etapa> lce_return;

		lsw_watch     = Logger.getNewStopWatch();

		lce_return = getReasignarTurnosBusiness().consultarEtapas(as_idRol);

		Logger.log(lsw_watch, "ReasignarTurnosBusiness", "consultarEtapas", null, null, null, null);

		return lce_return;
	}

	/** {@inheritdoc} */
	@Override
	public void enviarAbogadoSustanciador(
	    ReasignarTurnos ar_reasignarTurnos, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getReasignarTurnosBusiness().enviarAbogadoSustanciador(ar_reasignarTurnos, as_userId, as_remoteIp);

		Logger.log(
		    lsw_watch, "ReasignarTurnosBusiness", "enviarAbogadoSustanciador", as_userId, as_localIp, as_remoteIp, null
		);
	}

	/**
	 * Método encargado de extraer individualmente el registro seleccionado.
	 *
	 * @param acrt_data de acrt data
	 * @return lrt_return Objeto ResignarTurnos con el turno extraído
	 */
	@Override
	public ReasignarTurnos extraerSeleccionado(Collection<ReasignarTurnos> acrt_data)
	{
		StopWatch       lsw_watch;
		ReasignarTurnos lrt_return;

		lsw_watch     = Logger.getNewStopWatch();

		lrt_return = getReasignarTurnosBusiness().extraerSeleccionado(acrt_data);

		Logger.log(lsw_watch, "ReasignarTurnosBusiness", "extraerSeleccionado", null, null, null, null);

		return lrt_return;
	}

	/** {@inheritdoc} */
	@Override
	public Collection<ReasignarTurnos> findAllByEtapaUsuario(
	    long al_etapa, String as_idCirculo, String as_idUsuario, boolean ab_porCantidad, Integer ai_cantidad
	)
	    throws B2BException
	{
		StopWatch                   lsw_watch;
		Collection<ReasignarTurnos> lcoo_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcoo_datos = getReasignarTurnosBusiness()
				             .findAllByEtapaUsuario(al_etapa, as_idCirculo, as_idUsuario, ab_porCantidad, ai_cantidad);

		Logger.log(
		    lsw_watch, "ReasignarTurnosBusiness", "findAllByEtapaUsuario", StringUtils.getString(al_etapa), as_idUsuario,
		    as_idUsuario, null
		);

		return lcoo_datos;
	}

	/** {@inheritdoc} */
	@Override
	public Collection<ReasignarTurnos> findAllByEtapaUsuario(
	    long al_etapa, String as_idCirculo, String as_idUsuario, boolean ab_porCantidad, Integer ai_cantidad,
	    boolean ab_opcionSelected
	)
	    throws B2BException
	{
		StopWatch                   lsw_watch;
		Collection<ReasignarTurnos> lcoo_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcoo_datos = getReasignarTurnosBusiness()
				             .findAllByEtapaUsuario(
				    al_etapa, as_idCirculo, as_idUsuario, ab_porCantidad, ai_cantidad, ab_opcionSelected
				);

		Logger.log(
		    lsw_watch, "ReasignarTurnosBusiness", "findAllByEtapaUsuario", StringUtils.getString(al_etapa), as_idUsuario,
		    as_idUsuario, null
		);

		return lcoo_datos;
	}

	/** {@inheritdoc} */
	public Collection<Rol> findAllRoles()
	    throws B2BException
	{
		StopWatch       lsw_watch;
		Collection<Rol> lcr_return;

		lsw_watch     = Logger.getNewStopWatch();

		lcr_return = getReasignarTurnosBusiness().findAllRoles();

		Logger.log(lsw_watch, "ReasignarTurnosBusiness", "findAllRoles", null, null, null, null);

		return lcr_return;
	}

	/** {@inheritdoc} */
	public Collection<Rol> findAllRolesRecursos()
	    throws B2BException
	{
		StopWatch       lsw_watch;
		Collection<Rol> lcr_return;

		lsw_watch      = Logger.getNewStopWatch();
		lcr_return     = getReasignarTurnosBusiness().findAllRolesRecursos();

		Logger.log(lsw_watch, "ReasignarTurnosBusiness", "findAllRolesRecursos", null, null, null, null);

		return lcr_return;
	}

	/** {@inheritdoc} */
	public Collection<Rol> findAllRolesSegundaInstancia()
	    throws B2BException
	{
		StopWatch       lsw_watch;
		Collection<Rol> lcr_return;

		lsw_watch      = Logger.getNewStopWatch();
		lcr_return     = getReasignarTurnosBusiness().findAllRolesSegundaInstancia();

		Logger.log(lsw_watch, "ReasignarTurnosBusiness", "findAllRolesSegundaInstancia", null, null, null, null);

		return lcr_return;
	}

	/** {@inheritdoc} */
	@Override
	public Collection<CirculoRegistral> findCirculosDesborde(String as_idCirculoSelected)
	    throws B2BException
	{
		StopWatch                    lsw_watch;
		Collection<CirculoRegistral> lccr_return;

		lsw_watch     = Logger.getNewStopWatch();

		lccr_return = getReasignarTurnosBusiness().findCirculosDesborde(as_idCirculoSelected);

		Logger.log(lsw_watch, "ReasignarTurnosBusiness", "findCirculosDesborde", null, null, null, null);

		return lccr_return;
	}

	/** {@inheritdoc} */
	@Override
	public String findNombreEtapa(String as_idEtapa, String as_idUsuario, String as_localIp, String as_remoteIp)
	    throws B2BException
	{
		StopWatch lsw_watch;
		String    ls_return;

		lsw_watch     = Logger.getNewStopWatch();

		ls_return = getReasignarTurnosBusiness().findNombreEtapa(as_idEtapa);

		Logger.log(
		    lsw_watch, "ReasignarTurnosBusiness", "findNombreEtapa", as_idUsuario, as_localIp, as_remoteIp, null
		);

		return ls_return;
	}

	/** {@inheritdoc} */
	public Collection<Usuario> findUsuariosByEtapaRolCirculo(
	    long as_etapa, String as_idRol, String as_idCirculo, boolean ab_rolCirculoActivo
	)
	    throws B2BException
	{
		StopWatch           lsw_watch;
		Collection<Usuario> lte_datos;

		lsw_watch     = Logger.getNewStopWatch();
		lte_datos     = getReasignarTurnosBusiness()
				                .findUsuariosByEtapaRolCirculo(as_etapa, as_idRol, as_idCirculo, ab_rolCirculoActivo);

		Logger.log(lsw_watch, "ReferenceBean", "findUsuariosByEtapaRolCirculo", null, null, null, null);

		return lte_datos;
	}

	/** {@inheritdoc} */
	@Override
	public Collection<ReasignarTurnos> reasignarTurnos(
	    Collection<ReasignarTurnos> ic_data, String as_idCirculo, String as_idRol, String as_idEtapa,
	    String as_idUsuarioSelected, String as_idUsuarioAsignar, String as_observaciones,
	    String as_justificacionReasignadoEspecial, boolean ab_paramAutomatico, String as_idUsuario, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                   lsw_watch;
		Collection<ReasignarTurnos> lcth_return;

		lsw_watch     = Logger.getNewStopWatch();

		lcth_return = getReasignarTurnosBusiness()
				              .reasignarTurnos(
				    ic_data, as_idCirculo, as_idRol, as_idEtapa, as_idUsuarioSelected, as_idUsuarioAsignar,
				    as_observaciones, as_justificacionReasignadoEspecial, ab_paramAutomatico, as_idUsuario, as_localIp,
				    as_remoteIp
				);

		Logger.log(
		    lsw_watch, "ReasignarTurnosBusiness", "reasignarTurnos", as_idUsuario, as_localIp, as_remoteIp, null
		);

		return lcth_return;
	}

	/**
	 * Método encargado de validar si los turnos vinculados de los turnos seleccionados estan bloqueados.
	 *
	 * @param acrt_data Colección Tipo ReasignarTurnos con todos los turnos de la colección
	 * @return booleana con validacion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	@Override
	public boolean validarTurnosVinculadosBloqueados(Collection<ReasignarTurnos> acrt_data)
	    throws B2BException
	{
		StopWatch lsw_watch;
		boolean   lb_return;

		lsw_watch     = Logger.getNewStopWatch();

		lb_return = getReasignarTurnosBusiness().validarTurnosVinculadosBloqueados(acrt_data);

		Logger.log(lsw_watch, "ReasignarTurnosBusiness", "validarTurnosVinculadosBloqueados", null, null, null, null);

		return lb_return;
	}
}
