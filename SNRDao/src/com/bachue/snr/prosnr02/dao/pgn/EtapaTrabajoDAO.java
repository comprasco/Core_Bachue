package com.bachue.snr.prosnr02.dao.pgn;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr02.model.acc.EtapaTrabajo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase de manejo de datos para la tabla SDB_PGN_ETAPA_TRABAJO.
 *
 * @author Jorge Patiño
 */
public class EtapaTrabajoDAO extends AuditoriaDao
{
	/** Constante cs_EXISTE */
	private static final String cs_EXISTE = "SELECT 1 FROM SDB_PGN_ETAPA_TRABAJO WHERE ID_ETAPA=?";

	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_PGN_ETAPA_TRABAJO WHERE ID_ETAPA=?";

	/** Constante cs_UPDATE. */
	private static final String cs_ACTUALIZAR = "UPDATE SDB_PGN_ETAPA_TRABAJO SET NOMBRE=?, DESCRIPCION=?, ESTADO=?, INDICADOR_PESO=?, INDICADOR_BLOQUEO=?, INDICADOR_DESBORDE=?, DIAS_HABILES_NORMAL=?, INDICADOR_TOPE=?, ID_FASE=?, TIPO_REPARTO=?, ID_UNIDAD_TIEMPO_ESPERA=?, CANTIDAD_TIEMPO_ESPERA=?, GENERAR_QR=?, FECHA_MODIFICACION=SYSTIMESTAMP,ID_USUARIO_MODIFICACION=?, IP_MODIFICACION=? WHERE ID_ETAPA=?";

	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_PGN_ETAPA_TRABAJO ORDER BY ID_ETAPA ASC";

	/** Constante cs_FIND_ALL_ACTIVO. */
	private static final String cs_FIND_ALL_ACTIVO = "SELECT * FROM SDB_PGN_ETAPA_TRABAJO WHERE ESTADO = 'A' ORDER BY NOMBRE ASC";

	/** Constante cs_INSERTAR. */
	private static final String cs_INSERTAR = "INSERT INTO SDB_PGN_ETAPA_TRABAJO (ID_ETAPA,NOMBRE,DESCRIPCION,ESTADO,INDICADOR_PESO,INDICADOR_BLOQUEO,INDICADOR_DESBORDE,DIAS_HABILES_NORMAL,INDICADOR_TOPE,ID_FASE,TIPO_REPARTO,ID_UNIDAD_TIEMPO_ESPERA,CANTIDAD_TIEMPO_ESPERA,GENERAR_QR,ID_USUARIO_CREACION,FECHA_CREACION,IP_CREACION) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,SYSTIMESTAMP,?)";

	/**
	 * Indica si existe un registro de etapa
	 *
	 * @param as_idProceso Id de la etapa a validar
	 *
	 * @return true si el subproceso existe
	
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public boolean existe(long al_id)
	    throws B2BException
	{
		boolean           lb_existe;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lb_existe     = false;
		lps_ps        = null;
		lrs_rs        = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_EXISTE);

			lps_ps.setLong(1, al_id);

			lrs_rs        = lps_ps.executeQuery();
			lb_existe     = lrs_rs.next();
		}
		catch(SQLException lse_e)
		{
			logError(this, "existe", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return lb_existe;
	}

	/**
	 * Metodo para encontrar las etapas que se encuentran activas.
	 *
	 * @return devuelve el valor del objeto collection de Etapa
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<EtapaTrabajo> findAllActivo()
	    throws B2BException
	{
		Collection<EtapaTrabajo> lcet_etapas;
		PreparedStatement        lps_ps;
		ResultSet                lrs_rs;

		lcet_etapas     = new ArrayList<EtapaTrabajo>();
		lps_ps          = null;
		lrs_rs          = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL_ACTIVO);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcet_etapas.add(getObjetFromResultSet(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findAll", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(lcet_etapas.isEmpty())
			lcet_etapas = null;

		return lcet_etapas;
	}

	/**
	 * Metodo para encontrar todos los registros de la tabla SDB_PGN_ETAPA_TRABAJO.
	 *
	 * @return devuelve el valor del objeto collection de Etapa
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<EtapaTrabajo> findAllEtapas()
	    throws B2BException
	{
		Collection<EtapaTrabajo> lcet_etapas;
		PreparedStatement        lps_ps;
		ResultSet                lrs_rs;

		lcet_etapas     = new ArrayList<EtapaTrabajo>();
		lps_ps          = null;
		lrs_rs          = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcet_etapas.add(getObjetFromResultSet(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findAllEtapas", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(lcet_etapas.isEmpty())
			lcet_etapas = null;

		return lcet_etapas;
	}

	/**
	 * Metodo para encontrar todos los registros de la tabla SDB_PGN_ETAPA_TRABAJO que coincidan con un ID_ETAPA especifico.
	 *
	 * @param aet_etapa correspondiente al valor del tipo de objeto Etapa
	 * @return devuelve el valor del objeto etapa
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public EtapaTrabajo findById(EtapaTrabajo aet_etapa)
	    throws B2BException
	{
		return (aet_etapa != null) ? findById(aet_etapa.getIdEtapa()) : null;
	}

	/**
	 * Metodo para encontrar todos los registros de la tabla SDB_PGN_ETAPA_TRABAJO que coincidan con un ID_ETAPA especifico.
	 *
	 * @param al_id correspondiente al valor del tipo de objeto long
	 * @return devuelve el valor del objeto etapa
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public EtapaTrabajo findById(long al_id)
	    throws B2BException
	{
		EtapaTrabajo      let_etapa;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		let_etapa     = null;
		lps_ps        = null;
		lrs_rs        = null;

		try
		{
			if(al_id > 0)
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setLong(1, al_id);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					let_etapa = getObjetFromResultSet(lrs_rs);
			}
		}
		catch(SQLException lse_e)
		{
			logError(this, "findById", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return let_etapa;
	}

	/**
	 * Inserta un registro en la tabla a partir de un modelo creado en workflow
	 *
	 * @param aet_etapa Objeto contenedor de la información a insertar
	 * @throws B2BException Si ocurre un error en la comunicación con la base de datos
	 */
	public void insert(EtapaTrabajo aet_etapa)
	    throws B2BException
	{
		if(aet_etapa != null)
		{
			PreparedStatement lps_ps;

			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_INSERTAR);

				lps_ps.setLong(li_column++, aet_etapa.getIdEtapa());
				lps_ps.setString(li_column++, aet_etapa.getNombre());
				lps_ps.setString(li_column++, aet_etapa.getDescripcion());
				lps_ps.setString(li_column++, aet_etapa.getEstado());
				lps_ps.setString(li_column++, aet_etapa.getIndicadorPeso());
				lps_ps.setString(li_column++, aet_etapa.getIndicadorBloqueo());
				lps_ps.setString(li_column++, aet_etapa.getIndicadorDesborde());
				lps_ps.setBigDecimal(li_column++, aet_etapa.getDiasHabilesNormal());
				lps_ps.setString(li_column++, aet_etapa.getIndicadorTope());
				lps_ps.setLong(li_column++, aet_etapa.getIdFase());
				lps_ps.setString(li_column++, aet_etapa.getTipoReparto());
				lps_ps.setString(li_column++, aet_etapa.getIdUnidadTiempoEspera());
				lps_ps.setBigDecimal(li_column++, aet_etapa.getCantidadTiempoEspera());
				lps_ps.setString(li_column++, StringUtils.getString(aet_etapa.isGeneraQR()));
				lps_ps.setString(li_column++, aet_etapa.getIdUsuarioCreacion());
				lps_ps.setString(li_column++, aet_etapa.getIpCreacion());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "insert", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}

			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Actualiza un registro en la tabla a partir de un modelo creado en workflow
	 *
	 * @param aet_etapa Objeto contenedor de la información que se actualizará
	 * @throws B2BException Si ocurre un error en la comunicación con la base de datos
	 */
	public void update(EtapaTrabajo aet_etapa)
	    throws B2BException
	{
		if(aet_etapa != null)
		{
			PreparedStatement lps_ps;

			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_ACTUALIZAR);

				lps_ps.setString(li_column++, aet_etapa.getNombre());
				lps_ps.setString(li_column++, aet_etapa.getDescripcion());
				lps_ps.setString(li_column++, aet_etapa.getEstado());
				lps_ps.setString(li_column++, aet_etapa.getIndicadorPeso());
				lps_ps.setString(li_column++, aet_etapa.getIndicadorBloqueo());
				lps_ps.setString(li_column++, aet_etapa.getIndicadorDesborde());
				lps_ps.setBigDecimal(li_column++, aet_etapa.getDiasHabilesNormal());
				lps_ps.setString(li_column++, aet_etapa.getIndicadorTope());
				lps_ps.setLong(li_column++, aet_etapa.getIdFase());
				lps_ps.setString(li_column++, aet_etapa.getTipoReparto());
				lps_ps.setString(li_column++, aet_etapa.getIdUnidadTiempoEspera());
				lps_ps.setBigDecimal(li_column++, aet_etapa.getCantidadTiempoEspera());
				lps_ps.setString(li_column++, StringUtils.getString(aet_etapa.isGeneraQR()));
				lps_ps.setString(li_column++, aet_etapa.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, aet_etapa.getIpModificacion());
				lps_ps.setLong(li_column++, aet_etapa.getIdEtapa());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "update", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}

			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Metodo para obtener el objeto de tipo Etapa.
	 *
	 * @param ars_rs objeto que representa el resultado de una sentencia sql en la base de datos
	 * @return el valor de Etapa
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private EtapaTrabajo getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		EtapaTrabajo let_etapa;

		let_etapa = new EtapaTrabajo();

		let_etapa.setIdEtapa(ars_rs.getLong("ID_ETAPA"));
		let_etapa.setNombre(ars_rs.getString("NOMBRE"));
		let_etapa.setDescripcion(ars_rs.getString("DESCRIPCION"));
		let_etapa.setEstado(ars_rs.getString("ESTADO"));
		let_etapa.setIndicadorPeso(ars_rs.getString("INDICADOR_PESO"));
		let_etapa.setIndicadorBloqueo(ars_rs.getString("INDICADOR_BLOQUEO"));
		let_etapa.setIndicadorDesborde(ars_rs.getString("INDICADOR_DESBORDE"));
		let_etapa.setDiasHabilesNormal(ars_rs.getBigDecimal("DIAS_HABILES_NORMAL"));
		let_etapa.setIndicadorTope(ars_rs.getString("INDICADOR_TOPE"));
		let_etapa.setIdFase(ars_rs.getLong("ID_FASE"));
		let_etapa.setTipoReparto(ars_rs.getString("TIPO_REPARTO"));
		let_etapa.setIdUnidadTiempoEspera(ars_rs.getString("ID_UNIDAD_TIEMPO_ESPERA"));
		let_etapa.setCantidadTiempoEspera(ars_rs.getBigDecimal("CANTIDAD_TIEMPO_ESPERA"));
		let_etapa.setGeneraQR(BooleanUtils.getBooleanValue(ars_rs.getString("GENERAR_QR")));
		fillAuditoria(ars_rs, let_etapa);

		return let_etapa;
	}
}
