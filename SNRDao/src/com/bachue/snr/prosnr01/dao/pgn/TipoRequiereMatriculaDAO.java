package com.bachue.snr.prosnr01.dao.pgn;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;

import com.bachue.snr.prosnr01.model.sdb.pgn.TipoRequiereMatricula;

import java.math.BigDecimal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase dedicada al manejo de conexion y transacciones en la base de datos que afecten
 * directamente a la tabla SDB_PGN_TIPO_REQUIERE_MATRICULA
 *
 * @author Manuel Blanco
 *
 */
public class TipoRequiereMatriculaDAO extends BaseDAO
{
	private static final String cs_FIND_ALL      = "SELECT * FROM SDB_PGN_TIPO_REQUIERE_MATRICULA";
	private static final String cs_FIND_BY_ID    = "SELECT * FROM SDB_PGN_TIPO_REQUIERE_MATRICULA WHERE ID_TIPO_REQUIERE_MATRICULA = ?";
	private static final String cs_UPDATE        = "UPDATE SDB_PGN_TIPO_REQUIERE_MATRICULA SET ID_PROCESO = ?, ID_SUBPROCESO = ?, DESCRIPCION = ?,"
		+ "ID_USUARIO_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP, IP_MODIFICACION = ? WHERE ID_TIPO_REQUIERE_MATRICULA = ? ";
	private static final String cs_INSERT        = "INSERT INTO SDB_PNG_ESTADO_PREDIO (ID_TIPO_REQUIERE_MATRICULA, ID_PROCESO, ID_SUBPROCESO,DESCRIPCION, "
		+ " ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION) VALUES(?, ?, ?, ?, ?, SYSTIMESTAMP, ?)";
	private static final String cs_FIND_SECUENCE = "SELECT SEC_PGN_TIPO_REQUIERE_MATRICULA_ID_TIPO_REQUIERE_MATRICULA.NEXTVAL FROM DUAL";

	/**
	 * Busca todos los registros existentes en la tabla SDB_PGN_TIPO_REQUIERE_MATRICULA y los retorna
	 * en una colección de objetos
	 *
	 * @return Colección de objetos TipoRequiereMatricula con los registros resultantes de la consulta
	 * @throws B2BException
	 */
	public Collection<TipoRequiereMatricula> findAll(boolean ab_b)
	    throws B2BException
	{
		Collection<TipoRequiereMatricula> lctrm_object;
		PreparedStatement                 lps_ps;
		ResultSet                         lrs_rs;
		StringBuilder                      lsb_sb;

		lctrm_object     = new ArrayList<TipoRequiereMatricula>();
		lps_ps           = null;
		lrs_rs           = null;
		lsb_sb           = new StringBuilder(cs_FIND_ALL);

		try
		{
			if(ab_b)
				lsb_sb = lsb_sb.append(" WHERE ACTIVO = 'S' ");

			lps_ps     = getConnection().prepareStatement(lsb_sb.toString());

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lctrm_object.add(getObjetFromResultSet(lrs_rs));
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

		if(lctrm_object.isEmpty())
			lctrm_object = null;

		return lctrm_object;
	}

	/**
	 * Consulta todos los registros relacionados con un id de datos antiguo sistema
	 *
	 * @param atrm_param Objeto contenedor de el id de datos ant sistema que será utilizado
	 * como filtro en la consulta
	 * @return Colección con todos los registros resultantes de la consulta
	 * @throws B2BException
	 */
	public TipoRequiereMatricula findById(TipoRequiereMatricula atrm_param)
	    throws B2BException
	{
		TipoRequiereMatricula ltrm_object;
		PreparedStatement     lps_ps;
		ResultSet             lrs_rs;

		ltrm_object     = null;
		lps_ps          = null;
		lrs_rs          = null;

		if(atrm_param != null)
		{
			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(1, atrm_param.getIdTipoRequiereMatricula());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ltrm_object = getObjetFromResultSet(lrs_rs);
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

		return ltrm_object;
	}

	/**
	 * Busca todos los registros existentes en la tabla SDB_PGN_TIPO_REQUIERE_MATRICULA y los retorna
	 * en una colección de objetos
	 *
	 * @return Colección de objetos TipoRequiereMatricula con los registros resultantes de la consulta
	 * @throws B2BException
	 */
	public Collection<TipoRequiereMatricula> findByTipoRequiereMatricula(boolean ab_requiereMatricula)
	    throws B2BException
	{
		Collection<TipoRequiereMatricula> lctrm_object;
		PreparedStatement                 lps_ps;
		ResultSet                         lrs_rs;
		StringBuilder                      lsb_sb;

		lctrm_object     = new ArrayList<TipoRequiereMatricula>();
		lps_ps           = null;
		lrs_rs           = null;
		lsb_sb           = new StringBuilder(cs_FIND_ALL);

		try
		{
			lsb_sb     = lsb_sb.append(" WHERE ACTIVO = 'S' AND (ID_TIPO_REQUIERE_MATRICULA = ");

			lsb_sb     = lsb_sb.append(ab_requiereMatricula ? " '1' " : " '2' ");
			lsb_sb     = lsb_sb.append(" OR ID_TIPO_REQUIERE_MATRICULA = '3')");

			lps_ps     = getConnection().prepareStatement(lsb_sb.toString());

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lctrm_object.add(getObjetFromResultSet(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findByTipoRequiereMatricula", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(lctrm_object.isEmpty())
			lctrm_object = null;

		return lctrm_object;
	}

	/**
	 * Metodo para insertar o actualizar un registro en la base de datos de la tabla SDB_PGN_TIPO_REQUIERE_MATRICULA
	 */
	public void insertOrUpdate(TipoRequiereMatricula atrmd_param, boolean ab_query)
	    throws B2BException
	{
		{
			PreparedStatement lps_ps;
			PreparedStatement lps_secuencia;
			ResultSet         lrs_rs;

			lps_ps            = null;
			lps_secuencia     = null;
			lrs_rs            = null;

			try
			{
				if(atrmd_param != null)
				{
					Connection lc_connection;
					int        li_column;

					lc_connection     = getConnection();
					li_column         = 1;

					lps_ps = lc_connection.prepareStatement(ab_query ? cs_INSERT : cs_UPDATE);

					if(ab_query)
					{
						lps_secuencia     = lc_connection.prepareStatement(cs_FIND_SECUENCE);

						lrs_rs = lps_secuencia.executeQuery();

						if(lrs_rs.next())
						{
							Object lo_o;

							lo_o = lrs_rs.getObject(1);

							if((lo_o != null) && lo_o instanceof BigDecimal)
							{
								atrmd_param.setIdTipoRequiereMatricula(((BigDecimal)lo_o).toString());

								lps_ps.setString(li_column++, atrmd_param.getIdTipoRequiereMatricula());
							}
							else
								throw new B2BException(ErrorKeys.PGN_TIPO_REQUIERE_MATRICULA_SEQUENCE);
						}
					}

					lps_ps.setString(li_column++, atrmd_param.getIdProceso());
					lps_ps.setString(li_column++, atrmd_param.getIdSubproceso());
					lps_ps.setString(li_column++, atrmd_param.getDescripcion());

					if(ab_query)
					{
						lps_ps.setString(li_column++, atrmd_param.getIdUsuarioCreacion());
						lps_ps.setString(li_column++, atrmd_param.getIpCreacion());
					}
					else
					{
						lps_ps.setString(li_column++, atrmd_param.getIdUsuarioModificacion());
						lps_ps.setString(li_column++, atrmd_param.getIpModificacion());
						lps_ps.setString(li_column++, atrmd_param.getIdTipoRequiereMatricula());
					}

					lps_ps.executeUpdate();
				}
			}
			catch(SQLException lse_e)
			{
				logError(this, "insertOrUpdate", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
				close(lps_secuencia);
				close(lrs_rs);
			}
		}
	}

	/**
	 * Extrae la información resultante de un registro de una consulta, la cual se encuentra almacenada
	 * en un objeto ResultSet y la almacena en el objeto correspondiente a la abstraccion de la tabla.
	 *
	 * @param ars_rs Objeto contenedor del registro resultante de una consulta a la tabla
	 * @return Objeto TipoRequiereMatricula con la información que se extrajo del resultado de la consulta
	 * @throws SQLException
	 */
	private TipoRequiereMatricula getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		TipoRequiereMatricula ltrm_object;

		ltrm_object = new TipoRequiereMatricula();

		ltrm_object.setIdTipoRequiereMatricula(ars_rs.getString("ID_TIPO_REQUIERE_MATRICULA"));
		ltrm_object.setIdProceso(ars_rs.getString("ID_PROCESO"));
		ltrm_object.setIdSubproceso(ars_rs.getString("ID_SUBPROCESO"));
		ltrm_object.setDescripcion(ars_rs.getString("DESCRIPCION"));
		ltrm_object.setActivo(ars_rs.getString("ACTIVO"));
		ltrm_object.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		ltrm_object.setFechaCreacion(ars_rs.getDate("FECHA_CREACION"));
		ltrm_object.setIpCreacion(ars_rs.getString("IP_CREACION"));
		ltrm_object.setIdUsuarioModificacion(ars_rs.getString("ID_USUARIO_MODIFICACION"));
		ltrm_object.setFechaModificacion(ars_rs.getDate("FECHA_MODIFICACION"));
		ltrm_object.setIpModificacion(ars_rs.getString("IP_MODIFICACION"));

		return ltrm_object;
	}
}
