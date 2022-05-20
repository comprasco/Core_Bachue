package com.bachue.snr.prosnr01.dao.col;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;

import com.bachue.snr.prosnr01.model.sdb.col.ParteInteresada;

import java.math.BigDecimal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las consultas de la tabla SDB_COL_PARTE_INTERESADA de la base de datos
 *
 * @author garias
 */
public class ParteInteresadaDAO extends BaseDAO
{
	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_COL_PARTE_INTERESADA";

	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_COL_PARTE_INTERESADA WHERE ID_PARTE_INTERESADA=?";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_COL_PARTE_INTERESADA SET ILICODE = ?, DESCRIPCION = ?, ACTIVO = ?,"
		+ " ID_USUARIO_MODIFICACION = ?, IP_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP WHERE ID_PARTE_INTERESADA = ?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_COL_PARTE_INTERESADA (ID_PARTE_INTERESADA,ILICODE,DESCRIPCION,"
		+ " ACTIVO,ID_USUARIO_CREACION,FECHA_CREACION,IP_CREACION) VALUES (?,?,?,?,?,SYSTIMESTAMP,?)";

	/** Constante cs_FIND_SECUENCE. */
	private static final String cs_FIND_SECUENCE = "SELECT SEC_COL_PARTE_INTERESADA_ID_PARTE_INTERESADA.NEXTVAL FROM DUAL";

	/**
	 * Retorna el valor del objeto de tipo Collection de ParteInteresada consultando todo en la base de datos.
	 *
	 * @return devuelve el valor del objeto collection de ParteInteresada
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see ParteInteresada
	 */
	public Collection<ParteInteresada> findAll()
	    throws B2BException
	{
		Collection<ParteInteresada> lcpt_pt;
		PreparedStatement           lps_ps;
		ResultSet                   lrs_rs;

		lcpt_pt     = new ArrayList<ParteInteresada>();
		lps_ps      = null;
		lrs_rs      = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcpt_pt.add(getObjetFromResultSet(lrs_rs));
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

		if(!CollectionUtils.isValidCollection(lcpt_pt))
			lcpt_pt = null;

		return lcpt_pt;
	}

	/**
	 * Busca un registro asociado a un id específico.
	 *
	 * @param api_param Objeto contenedor del id a utilizar como filtro en la busqueda
	 * @return parte interesada resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public ParteInteresada findById(ParteInteresada api_param)
	    throws B2BException
	{
		return (api_param != null) ? findById(api_param.getIdParteInteresada()) : null;
	}

	/**
	 * Busca un registro asociado a un id específico.
	 *
	 * @param as_idParteInteresada Objeto contenedor del id a utilizar como filtro en la busqueda
	 * @return parte interesada resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public ParteInteresada findById(String as_idParteInteresada)
	    throws B2BException
	{
		ParteInteresada lcpt_pt;

		lcpt_pt = null;

		if(StringUtils.isValidString(as_idParteInteresada))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(1, as_idParteInteresada);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lcpt_pt = getObjetFromResultSet(lrs_rs);
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

		return lcpt_pt;
	}

	/**
	 * Inserta o Actualiza en la base de datos el tipo que se recibe por parametro
	 *
	 * @param api_parametros correspondiente al valor del tipo de objeto ParteInteresada
	 * @param ab_query <b>verdadero</b> para insertar un nuevo registro en la base de datos, <b>falso</b> para actualizar
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void insertOrUpdate(ParteInteresada api_parametros, boolean ab_query)
	    throws B2BException
	{
		if(api_parametros != null)
		{
			PreparedStatement lps_ps;
			PreparedStatement lps_secuencia;
			ResultSet         lrs_rs;

			lps_secuencia     = null;
			lps_ps            = null;
			lrs_rs            = null;

			try
			{
				Connection lc_connection;
				int        li_column;

				li_column         = 1;
				lps_ps            = getConnection().prepareStatement(ab_query ? cs_INSERT : cs_UPDATE);
				lc_connection     = getConnection();

				lps_secuencia     = lc_connection.prepareStatement(cs_FIND_SECUENCE);

				lrs_rs = lps_secuencia.executeQuery();

				if(ab_query)
				{
					if(lrs_rs.next())
					{
						Object lo_o;

						lo_o = lrs_rs.getObject(1);

						if((lo_o != null) && lo_o instanceof BigDecimal)
							api_parametros.setIdParteInteresada(((BigDecimal)lo_o).toString());
						else
							throw new B2BException(ErrorKeys.COL_PARTE_INTERESADA_SEQUENCE);
					}
				}

				if(ab_query)
					lps_ps.setString(li_column++, api_parametros.getIdParteInteresada());

				lps_ps.setString(li_column++, api_parametros.getIlicode());
				lps_ps.setString(li_column++, api_parametros.getDescripcion());
				lps_ps.setString(li_column++, api_parametros.getActivo());

				if(ab_query)
				{
					lps_ps.setString(li_column++, api_parametros.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, api_parametros.getIpCreacion());
				}
				else
				{
					lps_ps.setString(li_column++, api_parametros.getIdUsuarioModificacion());
					lps_ps.setString(li_column++, api_parametros.getIpModificacion());
					lps_ps.setString(li_column++, api_parametros.getIdParteInteresada());
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
				close(lps_secuencia);
				close(lrs_rs);
			}
		}
	}

	/**
	 * Retorna el valor de ParteInteresada
	 *
	 * @param lrs_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de ParteInteresada
	 * @throws SQLException Señala que se ha producido una excepción
	 * @see ParteInteresada
	 */
	private ParteInteresada getObjetFromResultSet(ResultSet lrs_rs)
	    throws SQLException
	{
		ParteInteresada lpt_pt;

		lpt_pt = new ParteInteresada();

		lpt_pt.setIdParteInteresada(lrs_rs.getString("ID_PARTE_INTERESADA"));
		lpt_pt.setIlicode(lrs_rs.getString("ILICODE"));
		lpt_pt.setDescripcion(lrs_rs.getString("DESCRIPCION"));
		lpt_pt.setActivo(lrs_rs.getString("ACTIVO"));
		lpt_pt.setIdUsuarioCreacion(lrs_rs.getString("ID_USUARIO_CREACION"));
		lpt_pt.setFechaCreacion(lrs_rs.getTimestamp("FECHA_CREACION"));
		lpt_pt.setIdUsuarioModificacion(lrs_rs.getString("ID_USUARIO_MODIFICACION"));
		lpt_pt.setIpCreacion(lrs_rs.getString("IP_CREACION"));
		lpt_pt.setIpCreacion(lrs_rs.getString("IP_MODIFICACION"));

		return lpt_pt;
	}
}
