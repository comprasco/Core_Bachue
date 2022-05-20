package com.bachue.snr.prosnr01.dao.png;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr01.model.sdb.png.ComunidadesEtnicas;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase dedicada al manejo de conexion y transacciones en la base de datos que afecten
 * directamente a la tabla SDB_PNG_COMUNIDADES_ETNICAS.
 *
 * @author Sebastian Sanchez
 */
public class ComunidadesEtnicasDAO extends AuditoriaDao
{
	/** Constante  cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_PNG_COMUNIDADES_ETNICAS ORDER BY ID_COMUNIDAD ASC";

	/** Constante  cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_PNG_COMUNIDADES_ETNICAS WHERE ID_COMUNIDAD = ?";

	/** Constante cs_FIND_BY_TIPO_NUMERO_DOC. */
	private static final String cs_FIND_BY_TIPO_NUMERO_DOC = "SELECT * FROM SDB_PNG_COMUNIDADES_ETNICAS WHERE TIPO_DOCUMENTO = ? AND NUMERO_DOCUMENTO = ?";

	/** Constante  cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_PNG_COMUNIDADES_ETNICAS SET NOMBRE_COMUNIDAD = ?, TIPO_DOCUMENTO = ?,  ID_USUARIO_MODIFICACION = ?, NUMERO_DOCUMENTO=?, FECHA_MODIFICACION = SYSTIMESTAMP, IP_MODIFICACION = ? "
		+ " WHERE ID_COMUNIDAD = ?";

	/** Constante  cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_PNG_COMUNIDADES_ETNICAS (ID_COMUNIDAD,NOMBRE_COMUNIDAD,TIPO_DOCUMENTO,NUMERO_DOCUMENTO,"
		+ " ID_USUARIO_CREACION,IP_CREACION,FECHA_CREACION) VALUES (?,?,?,?,?,?,SYSTIMESTAMP)";

	/** Constante cs_UPDATE_ACTIVO. */
	private static final String cs_DELETE = "DELETE FROM SDB_PNG_COMUNIDADES_ETNICAS WHERE ID_COMUNIDAD=?";

	/**
	 * Consulta todos los registros de la tabla.
	 *
	 * @return Colección con todos los registros resultantes de la consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<ComunidadesEtnicas> findAll()
	    throws B2BException
	{
		Collection<ComunidadesEtnicas> lcce_cce;
		PreparedStatement              lps_ps;
		ResultSet                      lrs_rs;

		lcce_cce     = new ArrayList<ComunidadesEtnicas>();
		lps_ps       = null;
		lrs_rs       = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcce_cce.add(getObjetFromResultSet(lrs_rs));
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

		if(lcce_cce.isEmpty())
			lcce_cce = null;

		return lcce_cce;
	}

	/**
	 * Consulta todos los registros relacionados con un id comunidad.
	 *
	 * @param ace_parametros de ace parametros
	 * @return Objeto contenedor del resultado de la consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public ComunidadesEtnicas findById(ComunidadesEtnicas ace_parametros)
	    throws B2BException
	{
		return (ace_parametros != null) ? findById(ace_parametros.getIdComunidad()) : null;
	}

	/**
	 * Consulta todos los registros relacionados con un id comunidad.
	 *
	 * @param ai_comunidadesEtnicas Objeto contenedor del id comunidad que será utilizado
	 * como filtro en la consulta
	 * @return Objeto contenedor del resultado de la consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public ComunidadesEtnicas findById(int ai_comunidadesEtnicas)
	    throws B2BException
	{
		ComunidadesEtnicas lce_ce;

		lce_ce = null;

		if(ai_comunidadesEtnicas > 0)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_contador;

				li_contador     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setInt(li_contador++, ai_comunidadesEtnicas);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lce_ce = getObjetFromResultSet(lrs_rs);
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

		return lce_ce;
	}

	/**
	 * Find by tipo numero doc.
	 *
	 * @param as_tipoDocumento de as tipo documento
	 * @param as_numeroDocumento de as numero documento
	 * @return el valor de comunidades etnicas
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public ComunidadesEtnicas findByTipoNumeroDoc(String as_tipoDocumento, String as_numeroDocumento)
	    throws B2BException
	{
		ComunidadesEtnicas lce_ce;

		lce_ce = null;

		if(StringUtils.isValidString(as_tipoDocumento) && StringUtils.isValidString(as_numeroDocumento))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_contador;

				li_contador     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_TIPO_NUMERO_DOC);

				lps_ps.setString(li_contador++, as_tipoDocumento);
				lps_ps.setString(li_contador++, as_numeroDocumento);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lce_ce = getObjetFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByTipoNumeroDoc", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lce_ce;
	}

	/**
	 * Inserta un registro
	 * con la información suministrada.
	 *
	 * @param ace_param objeto contenedor de la información a insertar
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void insert(ComunidadesEtnicas ace_param)
	    throws B2BException
	{
		if(ace_param != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int           li_column;
				StringBuilder lsb_query;

				li_column     = 1;
				lsb_query     = new StringBuilder(cs_INSERT);

				lps_ps = getConnection().prepareStatement(lsb_query.toString());

				lps_ps.setInt(li_column++, ace_param.getIdComunidad());
				lps_ps.setString(li_column++, ace_param.getNombreComunidad());
				lps_ps.setString(li_column++, ace_param.getTipoDocumento());
				lps_ps.setString(li_column++, ace_param.getNumeroDocumento());
				lps_ps.setString(li_column++, ace_param.getIdUsuarioCreacion());
				lps_ps.setString(li_column++, ace_param.getIpCreacion());

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
	 * Actualiza un registro
	 * con la información suministrada.
	 *
	 * @param ace_param objeto contenedor de la información a insertar
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void update(ComunidadesEtnicas ace_param)
	    throws B2BException
	{
		if(ace_param != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int           li_column;
				StringBuilder lsb_query;

				li_column     = 1;
				lsb_query     = new StringBuilder(cs_UPDATE);

				lps_ps = getConnection().prepareStatement(lsb_query.toString());

				lps_ps.setString(li_column++, ace_param.getNombreComunidad());
				lps_ps.setString(li_column++, ace_param.getTipoDocumento());
				lps_ps.setString(li_column++, ace_param.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, ace_param.getNumeroDocumento());
				lps_ps.setString(li_column++, ace_param.getIpModificacion());
				lps_ps.setInt(li_column++, ace_param.getIdComunidad());

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
	 * Método para eliminar un registro en la BD.
	 *
	 * @param ai_idComunidad de ai id comunidad
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void delete(int ai_idComunidad)
	    throws B2BException
	{
		PreparedStatement lps_ps;
		int               li_column;

		lps_ps        = null;
		li_column     = 1;

		if(ai_idComunidad > 0)
		{
			try
			{
				lps_ps = getConnection().prepareStatement(cs_DELETE);

				lps_ps.setInt(li_column++, ai_idComunidad);

				lps_ps.executeQuery();
			}
			catch(SQLException lse_e)
			{
				logError(this, "delete", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Extrae la información de un registro de base de datos, la asigna a un objeto y la retorna.
	 *
	 * @param ars_rs Objeto contenedor de los resultados de la consulta
	 * @return Objeto tipo eje con la información recuperada de la base de datos
	 * @throws SQLException cuando se produce algun error en el proceso
	 */
	private ComunidadesEtnicas getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		ComunidadesEtnicas lce_comunidadesEtnicas;

		lce_comunidadesEtnicas = new ComunidadesEtnicas();

		lce_comunidadesEtnicas.setIdComunidad(ars_rs.getInt("ID_COMUNIDAD"));
		lce_comunidadesEtnicas.setNombreComunidad(ars_rs.getString("NOMBRE_COMUNIDAD"));
		lce_comunidadesEtnicas.setTipoDocumento(ars_rs.getString("TIPO_DOCUMENTO"));
		lce_comunidadesEtnicas.setNumeroDocumento(ars_rs.getString("NUMERO_DOCUMENTO"));
		fillAuditoria(ars_rs, lce_comunidadesEtnicas);

		return lce_comunidadesEtnicas;
	}
}
