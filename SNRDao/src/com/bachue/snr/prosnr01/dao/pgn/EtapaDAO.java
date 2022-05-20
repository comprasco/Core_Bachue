package com.bachue.snr.prosnr01.dao.pgn;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.EtapaCommon;

import com.bachue.snr.prosnr01.dao.IBase;

import com.bachue.snr.prosnr01.model.sdb.pgn.Etapa;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase de manejo de datos para la tabla SDB_PGN_ETAPA.
 *
 * @author Nicolas Guaneme
 */
public class EtapaDAO extends BaseDAO implements IBase<Etapa>
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_PGN_ETAPA WHERE ID_ETAPA=?";

	/** Constante cs_FIND_ETAPA_BY_ROL. */
	private static final String cs_FIND_ETAPA_BY_ROL = "SELECT DISTINCT ET.* FROM SDB_AUT_ROL_OPCION RO INNER JOIN SDB_AUT_OPCION_ETAPA OE ON RO.ID_OPCION = OE.ID_OPCION INNER JOIN SDB_PGN_ETAPA ET ON OE.ID_ETAPA = ET.ID_ETAPA WHERE RO.ID_ROL = ? ORDER BY ET.NOMBRE ASC";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_PGN_ETAPA SET NOMBRE=?, DESCRIPCION=?, ESTADO=?, INDICADOR_PESO=?, INDICADOR_BLOQUEO=?, INDICADOR_DESBORDE=?, DIAS_HABILES_NORMAL=?, INDICADOR_TOPE=?, ID_FASE=?, TIPO_REPARTO=?, ID_UNIDAD_TIEMPO_ESPERA=?, CANTIDAD_TIEMPO_ESPERA=?, GENERAR_QR=?, FECHA_MODIFICACION=SYSTIMESTAMP,ID_USUARIO_MODIFICACION=?, IP_MODIFICACION=? WHERE ID_ETAPA=?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_PGN_ETAPA(ID_ETAPA,ID_USUARIO_CREACION,IP_CREACION, NOMBRE, DESCRIPCION, ESTADO, INDICADOR_PESO, INDICADOR_BLOQUEO, INDICADOR_DESBORDE, DIAS_HABILES_NORMAL, INDICADOR_TOPE, ID_FASE, TIPO_REPARTO, ID_UNIDAD_TIEMPO_ESPERA, CANTIDAD_TIEMPO_ESPERA, GENERAR_QR, FECHA_CREACION) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSTIMESTAMP)";

	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_PGN_ETAPA ORDER BY ID_ETAPA ASC";

	/** Constante cs_FIND_ALL_ACTIVO. */
	private static final String cs_FIND_ALL_ACTIVO = "SELECT * FROM SDB_PGN_ETAPA WHERE ESTADO = 'A'";

	/** Constante cs_FIND_ENTREGA. */
	private static final String cs_FIND_ENTREGA = "SELECT * FROM SDB_PGN_ETAPA WHERE ESTADO = 'A' ";

	/** Constante cs_FIND_ANTIGUO. */
	private static final String cs_FIND_ANTIGUO = "SELECT * FROM SDB_PGN_ETAPA";

	/** Constante cs_FIND_APROBADOR_ANTIGUO_SISTEMA. */
	private static final String cs_FIND_APROBADOR_ANTIGUO_SISTEMA = "SELECT * FROM SDB_PGN_ETAPA WHERE ID_ETAPA >= 110 AND ID_ETAPA <= 114 AND ESTADO = 'A'";

	/** Constante cs_FIND_PROYECTA_RESOLUCION. */
	private static final String cs_FIND_PROYECTA_RESOLUCION = "SELECT * FROM SDB_PGN_ETAPA WHERE ID_ETAPA = 652 AND ESTADO = 'A'";

	/** Constante cs_FIND_TIPO_REPARTO_BY_ETAPA. */
	private static final String cs_FIND_TIPO_REPARTO_BY_ETAPA = "SELECT TIPO_REPARTO FROM SDB_PGN_ETAPA WHERE ID_ETAPA = ?";

	/**
	 * Metodo para encontrar las etapas que se encuentran activas.
	 *
	 * @param ab_orderByIdEtapa true para indicar que se debe ordenar por etapa los resultados de la busqueda
	 * @return devuelve el valor del objeto collection de Etapa
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<Etapa> findAllActivo(boolean ab_orderByIdEtapa)
	    throws B2BException
	{
		Collection<Etapa> lc_data;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lc_data     = new ArrayList<Etapa>();
		lps_ps      = null;
		lrs_rs      = null;

		try
		{
			StringBuilder lsb_query;

			lsb_query = new StringBuilder(cs_FIND_ALL_ACTIVO);

			lsb_query.append(ab_orderByIdEtapa ? " ORDER BY LENGTH(ID_ETAPA),ID_ETAPA " : " ORDER BY NOMBRE ASC ");

			lps_ps     = getConnection().prepareStatement(lsb_query.toString());

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lc_data.add(getObjetFromResultSet(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findAllActivo", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(lc_data.isEmpty())
			lc_data = null;

		return lc_data;
	}

	/**
	 * Metodo para encontrar todos los registros de la tabla SDB_PGN_ETAPA.
	 *
	 * @return devuelve el valor del objeto collection de Etapa
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<Etapa> findAllEtapas()
	    throws B2BException
	{
		Collection<Etapa> lc_data;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lc_data     = new ArrayList<Etapa>();
		lps_ps      = null;
		lrs_rs      = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lc_data.add(getObjetFromResultSet(lrs_rs));
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

		if(lc_data.isEmpty())
			lc_data = null;

		return lc_data;
	}

	/**
	 * Metodo para traer todos los registros de la tabla SDB_PGN_ETAPA que coincidan con la etapa de antiguo sistema.
	 * @param parametro de la etapa de AS a consultar
	 * @return devuelve el valor del objeto collection de Etapa
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<Etapa> findAntiguoSistema(long al_etapa)
	    throws B2BException
	{
		Collection<Etapa> lc_data;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lc_data     = new ArrayList<Etapa>();
		lps_ps      = null;
		lrs_rs      = null;

		try
		{
			StringBuilder lsb_query;
			lsb_query = new StringBuilder(cs_FIND_ANTIGUO);

			if(al_etapa == EtapaCommon.ID_ETAPA_CREACION_MATRICULAS_OFICIO)
				lsb_query.append(" WHERE ID_ETAPA = 105");
			else
				lsb_query.append(" WHERE ID_ETAPA >= 100 AND ID_ETAPA <= 102");

			lsb_query.append(" AND ESTADO = 'A'");

			lps_ps     = getConnection().prepareStatement(lsb_query.toString());

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lc_data.add(getObjetFromResultSet(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findAntiguoSistema", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(lc_data.isEmpty())
			lc_data = null;

		return lc_data;
	}

	/**
	 * Metodo para traer todos los registros de la tabla SDB_PGN_ETAPA que coincidan con la etapa de aporbador antiguo sistema.
	 *
	 * @return devuelve el valor del objeto collection de Etapa
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<Etapa> findAprobadorAntiguoSistema()
	    throws B2BException
	{
		Collection<Etapa> lc_data;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lc_data     = new ArrayList<Etapa>();
		lps_ps      = null;
		lrs_rs      = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_APROBADOR_ANTIGUO_SISTEMA);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lc_data.add(getObjetFromResultSet(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findAprobadorAntiguoSistema", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(lc_data.isEmpty())
			lc_data = null;

		return lc_data;
	}

	/**
	 * Metodo para encontrar todos los registros de la tabla SDB_PGN_ETAPA que coincidan con un ID_ETAPA especifico.
	 *
	 * @param at_param correspondiente al valor del tipo de objeto Etapa
	 * @return devuelve el valor del objeto etapa
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Etapa findById(Etapa at_param)
	    throws B2BException
	{
		return (at_param != null) ? findById(at_param.getIdEtapa()) : null;
	}

	/**
	 * Metodo para encontrar todos los registros de la tabla SDB_PGN_ETAPA que coincidan con un ID_ETAPA especifico.
	 *
	 * @param al_param correspondiente al valor del tipo de objeto long
	 * @return devuelve el valor del objeto etapa
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Etapa findById(long al_param)
	    throws B2BException
	{
		Etapa le_e;

		le_e = null;

		if(al_param > 0)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setLong(1, al_param);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					le_e = getObjetFromResultSet(lrs_rs);
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
		}

		return le_e;
	}

	/**
	 * Metodo para encontrar las etapa de un rol.
	 *
	 * @param as_idRol correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de la colección
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<Etapa> findByRol(String as_idRol)
	    throws B2BException
	{
		Collection<Etapa> lce_data;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lce_data     = new ArrayList<Etapa>();
		lps_ps       = null;
		lrs_rs       = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_ETAPA_BY_ROL);

			lps_ps.setString(1, as_idRol);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lce_data.add(getObjetFromResultSet(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findByRol", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return lce_data;
	}

	/**
	 * Metodo para encontrar todos los registros de la tabla SDB_PGN_ETAPA que se encuentren en etapa de entrega.
	 *
	 * @param asa_etapasNotificacion de as etapas notificacion
	 * @param ab_notaDevolutiva de ab nota devolutiva
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Etapa> findEntrega(String[] asa_etapasNotificacion)
	    throws B2BException
	{
		Collection<Etapa> lce_etapas;

		lce_etapas = new ArrayList<Etapa>();

		if(asa_etapasNotificacion != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				StringBuilder lsb_query;

				lsb_query = new StringBuilder(cs_FIND_ENTREGA);

				lsb_query.append(
				    " AND ID_ETAPA >= 200 AND ID_ETAPA <> 201 AND ID_ETAPA <> 210 AND ID_ETAPA <> 212 AND ID_ETAPA <= 260 AND ID_ETAPA NOT IN ( "
				);

				for(
				    int li_posicion = 0, li_tamanio = asa_etapasNotificacion.length; li_posicion < li_tamanio;
					    li_posicion++
				)
					lsb_query.append(((li_posicion + 1) == li_tamanio) ? "?" : "?, ");

				lsb_query.append(")");

				lps_ps = getConnection().prepareStatement(lsb_query.toString());

				for(
				    int li_posicion = 0, li_tamanio = asa_etapasNotificacion.length; li_posicion < li_tamanio;
					    li_posicion++
				)
					lps_ps.setString(li_posicion + 1, asa_etapasNotificacion[li_posicion]);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lce_etapas.add(getObjetFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findEntrega", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lce_etapas.isEmpty())
			lce_etapas = null;

		return lce_etapas;
	}

	/**
	 * Metodo para traer todos los registros de la tabla SDB_PGN_ETAPA que coincidan con la etapa de proyecta resolucion.
	 *
	 * @return devuelve el valor del objeto collection de Etapa
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<Etapa> findProyectaResolucion()
	    throws B2BException
	{
		Collection<Etapa> lc_data;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lc_data     = new ArrayList<Etapa>();
		lps_ps      = null;
		lrs_rs      = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_PROYECTA_RESOLUCION);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lc_data.add(getObjetFromResultSet(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findProyectaResolucion", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(lc_data.isEmpty())
			lc_data = null;

		return lc_data;
	}

	/**
	 * Metodo para encontrar un tipo de reparto de la tabla SDB_PGN_ETAPA con una etapa.
	 *
	 * @param al_etapa correspondiente al valor del tipo de objeto long
	 * @return devuelve el valor del objeto string
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public String findTipoRepartoByEtapa(long al_etapa)
	    throws B2BException
	{
		String            ls_data;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		ls_data     = null;
		lps_ps      = null;
		lrs_rs      = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_TIPO_REPARTO_BY_ETAPA);

			lps_ps.setLong(1, al_etapa);

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				ls_data = StringUtils.getString(lrs_rs.getString("TIPO_REPARTO"));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findTipoRepartoByEtapa", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return ls_data;
	}

	/**
	 * Metodo para insertar o actualizar en la tabla SDB_PGN_ETAPA.
	 *
	 * @param at_param  objeto a modificar o insertar
	 * @param ab_query indicada si se actualiza o se insertan registros en la tabla SDB_PGN_ETAPA
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	@Override
	public void insertOrUpdate(Etapa at_param, boolean ab_query)
	    throws B2BException
	{
		if(at_param != null)
		{
			int               li_cont;
			PreparedStatement lps_ps;

			li_cont     = 1;
			lps_ps      = null;

			try
			{
				lps_ps = getConnection().prepareStatement(ab_query ? cs_INSERT : cs_UPDATE);

				if(ab_query)
				{
					lps_ps.setLong(li_cont++, at_param.getIdEtapa());
					lps_ps.setString(li_cont++, at_param.getIdUsuarioCreacion());
					lps_ps.setString(li_cont++, at_param.getIpCreacion());
				}

				lps_ps.setString(li_cont++, at_param.getNombre());
				lps_ps.setString(li_cont++, at_param.getDescripcion());
				lps_ps.setString(li_cont++, at_param.getEstado());
				lps_ps.setString(li_cont++, at_param.getIndicadorPeso());
				lps_ps.setString(li_cont++, at_param.getIndicadorBloqueo());
				lps_ps.setString(li_cont++, at_param.getIndicadorDesborde());
				lps_ps.setBigDecimal(li_cont++, at_param.getDiasHabilesNormal());
				lps_ps.setString(li_cont++, at_param.getIndicadorTope());
				lps_ps.setLong(li_cont++, at_param.getIdFase());
				lps_ps.setString(li_cont++, at_param.getTipoReparto());
				lps_ps.setString(li_cont++, at_param.getIdUnidadTiempoEspera());
				lps_ps.setBigDecimal(li_cont++, at_param.getCantidadTiempoEspera());
				lps_ps.setString(li_cont++, at_param.isGeneraQR() ? EstadoCommon.S : EstadoCommon.N);

				if(!ab_query)
				{
					lps_ps.setString(li_cont++, at_param.getIdUsuarioModificacion());
					lps_ps.setString(li_cont++, at_param.getIpModificacion());
					lps_ps.setLong(li_cont++, at_param.getIdEtapa());
				}

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "insertOrUpdate", lse_e);

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
	private Etapa getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		Etapa ld_datos;

		ld_datos = new Etapa();

		ld_datos.setIdEtapa(ars_rs.getLong("ID_ETAPA"));
		ld_datos.setNombre(ars_rs.getString("NOMBRE"));
		ld_datos.setDescripcion(ars_rs.getString("DESCRIPCION"));
		ld_datos.setEstado(ars_rs.getString("ESTADO"));
		ld_datos.setIndicadorPeso(ars_rs.getString("INDICADOR_PESO"));
		ld_datos.setIndicadorBloqueo(ars_rs.getString("INDICADOR_BLOQUEO"));
		ld_datos.setIndicadorDesborde(ars_rs.getString("INDICADOR_DESBORDE"));
		ld_datos.setDiasHabilesNormal(ars_rs.getBigDecimal("DIAS_HABILES_NORMAL"));
		ld_datos.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		ld_datos.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		ld_datos.setIdUsuarioModificacion(ars_rs.getString("ID_USUARIO_MODIFICACION"));
		ld_datos.setFechaModificacion(ars_rs.getTimestamp("FECHA_MODIFICACION"));
		ld_datos.setIpCreacion(ars_rs.getString("IP_CREACION"));
		ld_datos.setIpModificacion(ars_rs.getString("IP_MODIFICACION"));
		ld_datos.setIndicadorTope(ars_rs.getString("INDICADOR_TOPE"));
		ld_datos.setIdFase(ars_rs.getLong("ID_FASE"));
		ld_datos.setTipoReparto(ars_rs.getString("TIPO_REPARTO"));
		ld_datos.setIdUnidadTiempoEspera(ars_rs.getString("ID_UNIDAD_TIEMPO_ESPERA"));
		ld_datos.setCantidadTiempoEspera(ars_rs.getBigDecimal("CANTIDAD_TIEMPO_ESPERA"));
		ld_datos.setGenerarQRString(ars_rs.getString("GENERAR_QR"));
		ld_datos.setGeneraQR(BooleanUtils.getBooleanValue(ars_rs.getString("GENERAR_QR")));

		return ld_datos;
	}
}
