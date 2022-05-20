package com.bachue.snr.prosnr01.dao.pgn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import com.b2bsg.common.exception.B2BException;
import com.b2bsg.common.util.StringUtils;
import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;
import com.bachue.snr.prosnr01.model.sdb.pgn.ActividadEconomica;


/**
 * Clase dedicada al manejo de conexion y transacciones en la base de datos que afecten
 * directamente a la tabla SDB_PGN_ACTIVIDAD_ECONOMICA.
 *
 * @author Sebastian Sanchez
 */
public class ActividadEconomicaDAO extends AuditoriaDao
{
	/** Constante  cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_PGN_ACTIVIDAD_ECONOMICA ";

	/** Constante  cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_PGN_ACTIVIDAD_ECONOMICA WHERE ID_ACTIVIDAD_ECONOMICA = ?";

	/** Constante  cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_PGN_ACTIVIDAD_ECONOMICA(ID_ACTIVIDAD_ECONOMICA, ID_DIVSION, ID_GRUPO, DESCRIPCION, ACTIVO, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION) VALUES(?, ?, ?, ?, ? , ?, SYSTIMESTAMP, ?)";

	/** Constante  cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_PGN_ACTIVIDAD_ECONOMICA SET ID_DIVSION=?, ID_GRUPO=?, DESCRIPCION=?, ACTIVO=?, ID_USUARIO_MODIFICACION=?, IP_MODIFICACION=?, FECHA_MODIFICACION = SYSTIMESTAMP WHERE ID_ACTIVIDAD_ECONOMICA=?";

	/** Constante  cs_FIND_ALL_ACTIVO. */
	private static final String cs_FIND_ALL_ACTIVO = "SELECT * FROM SDB_PGN_ACTIVIDAD_ECONOMICA WHERE ACTIVO = 'S' ORDER BY LENGTH(ID_ACTIVIDAD_ECONOMICA), ID_ACTIVIDAD_ECONOMICA";

	/**
	 * Consulta en base de datos todos los registros que se encuentren existentes.
	 *
	 * @return Colección de ActividadEconomica resultante de la consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<ActividadEconomica> findAll()
	    throws B2BException
	{
		Collection<ActividadEconomica> lcae_cae;
		PreparedStatement              lps_ps;
		ResultSet                      lrs_rs;

		lcae_cae     = new ArrayList<ActividadEconomica>();
		lps_ps       = null;
		lrs_rs       = null;

		try
		{
			StringBuilder lsb_sb;

			lsb_sb     = new StringBuilder(cs_FIND_ALL);

			lps_ps     = getConnection().prepareStatement(lsb_sb.toString());

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcae_cae.add(getObjetFromResultSet(lrs_rs));
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

		if(lcae_cae.isEmpty())
			lcae_cae = null;

		return lcae_cae;
	}

	/**
	 * Consulta en base de datos todos los registros que se encuentren en estado activo.
	 *
	 * @return Colección de ActividadEconomica resultante de la consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<ActividadEconomica> findAllActivo()
	    throws B2BException
	{
		Collection<ActividadEconomica> lcae_cae;
		PreparedStatement              lps_ps;
		ResultSet                      lrs_rs;

		lcae_cae     = new ArrayList<ActividadEconomica>();
		lps_ps       = null;
		lrs_rs       = null;

		try
		{
			StringBuilder lsb_sb;

			lsb_sb     = new StringBuilder(cs_FIND_ALL_ACTIVO);

			lps_ps     = getConnection().prepareStatement(lsb_sb.toString());

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcae_cae.add(getObjetFromResultSet(lrs_rs));
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

		if(lcae_cae.isEmpty())
			lcae_cae = null;

		return lcae_cae;
	}

	/**
	 * Busca un registro de la tabla por medio de su id_identificador.
	 *
	 * @param aae_param Objeto de tipo ActividadEconomica contenedor de los filtros a usar en la consulta
	 * @return ActividadEconomica resultante de la consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public ActividadEconomica findById(ActividadEconomica aae_param)
	    throws B2BException
	{
		return (aae_param != null) ? findById(aae_param.getIdActividadEconomica()) : null;
	}

	/**
	 * Busca un registro de la tabla por medio de su id_identificador.
	 *
	 * @param as_param Objeto contenedor de los filtros a usar en la consulta
	 * @return ActividadEconomica resultante de la consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public ActividadEconomica findById(String as_param)
	    throws B2BException
	{
		ActividadEconomica lae_param;

		lae_param = null;

		if(StringUtils.isValidString(as_param))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(1, as_param);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lae_param = getObjetFromResultSet(lrs_rs);
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

		return lae_param;
	}

	/**
	 * Inserta un registro
	 * con la información suministrada.
	 *
	 * @param aae_param objeto contenedor de la información a insertar
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void insert(ActividadEconomica aae_param)
	    throws B2BException
	{
		if(aae_param != null)
		{
			int               li_column;
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps            = null;
			lrs_rs            = null;
			li_column         = 1;

			try
			{
				Connection lc_C;

				lc_C       = getConnection();
				lps_ps     = lc_C.prepareStatement(cs_INSERT);

				{
					lps_ps.setString(li_column++, aae_param.getIdActividadEconomica());
					lps_ps.setString(li_column++, aae_param.getIdDivision());
					lps_ps.setString(li_column++, aae_param.getIdGrupo());
					lps_ps.setString(li_column++, aae_param.getDescripcion());
					lps_ps.setString(li_column++, aae_param.getActivo());
					lps_ps.setString(li_column++, aae_param.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, aae_param.getIpCreacion());
				}

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
				close(lrs_rs);
			}
		}
	}

	/**
	 * Actualiza un registro
	 * con la información suministrada.
	 *
	 * @param aae_param objeto contenedor de la información a insertar
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void update(ActividadEconomica aae_param)
	    throws B2BException
	{
		if(aae_param != null)
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

				lps_ps.setString(li_column++, aae_param.getIdDivision());
				lps_ps.setString(li_column++, aae_param.getIdGrupo());
				lps_ps.setString(li_column++, aae_param.getDescripcion());
				lps_ps.setString(li_column++, aae_param.getActivo());
				lps_ps.setString(li_column++, aae_param.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, aae_param.getIpModificacion());
				lps_ps.setString(li_column++, aae_param.getIdActividadEconomica());

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
	 * Permite almacenar los datos que retornó la consulta en los atributos
	 * de un objeto.
	 *
	 * @param lrs_rs contenedor del resultado de a consulta
	 * @return objeto contenedor de los datos que retornó la consulta
	 * @throws SQLException cuando se produce algun error en el proceso
	 */
	private ActividadEconomica getObjetFromResultSet(ResultSet lrs_rs)
	    throws SQLException
	{
		ActividadEconomica ls_solicitud;

		ls_solicitud = new ActividadEconomica();

		ls_solicitud.setIdActividadEconomica(lrs_rs.getString("ID_ACTIVIDAD_ECONOMICA"));
		ls_solicitud.setIdDivision(lrs_rs.getString("ID_DIVSION"));
		ls_solicitud.setIdGrupo(lrs_rs.getString("ID_GRUPO"));
		ls_solicitud.setDescripcion(lrs_rs.getString("DESCRIPCION"));
		ls_solicitud.setActivo(lrs_rs.getString("ACTIVO"));
		ls_solicitud.setIdUsuarioCreacion(lrs_rs.getString("ID_USUARIO_CREACION"));
		ls_solicitud.setFechaCreacion(lrs_rs.getTimestamp("FECHA_CREACION"));
		ls_solicitud.setIpCreacion(lrs_rs.getString("IP_CREACION"));
		ls_solicitud.setIdUsuarioModificacion(lrs_rs.getString("ID_USUARIO_MODIFICACION"));
		ls_solicitud.setFechaModificacion(lrs_rs.getTimestamp("FECHA_MODIFICACION"));
		ls_solicitud.setIpModificacion(lrs_rs.getString("IP_MODIFICACION"));

		return ls_solicitud;
	}
}
