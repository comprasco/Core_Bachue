package com.bachue.snr.prosnr01.dao.png;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;

import com.bachue.snr.prosnr01.model.sdb.png.GrupoNaturalezaJuridica;

import java.math.BigDecimal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las consultas relacionadas con la tabla SDB_PNG_GRUPO_NATURALEZA_JURIDICA de la base de datos
 *
 * @author ssanchez
 */
public class GrupoNaturalezaJuridicaDAO extends BaseDAO
{
	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_PNG_GRUPO_NATURALEZA_JURIDICA";

	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_PNG_GRUPO_NATURALEZA_JURIDICA WHERE ID_GRUPO_NAT_JURIDICA = ?";

	/** Constante cs_FIND_ALL_ACTIVO. */
	private static final String cs_FIND_ALL_ACTIVO = "SELECT * FROM SDB_PNG_GRUPO_NATURALEZA_JURIDICA WHERE ACTIVO='S' ORDER BY NOMBRE ASC ";

	/** Constante cs_FIND_ALL_ACTIVO_ID. */
	private static final String cs_FIND_ALL_ACTIVO_ID = "SELECT * FROM SDB_PNG_GRUPO_NATURALEZA_JURIDICA WHERE ACTIVO='S' ORDER BY LENGTH(ID_GRUPO_NAT_JURIDICA), ID_GRUPO_NAT_JURIDICA ";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_PNG_GRUPO_NATURALEZA_JURIDICA SET NOMBRE = ?, ACTIVO = ?, "
		+ "ID_USUARIO_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP, IP_MODIFICACION = ? WHERE ID_GRUPO_NAT_JURIDICA = ?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_PNG_GRUPO_NATURALEZA_JURIDICA (ID_GRUPO_NAT_JURIDICA, NOMBRE, ACTIVO, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION) "
		+ "VALUES(?, ?, ?, ?, SYSTIMESTAMP, ?)";

	/** Constante cs_FIND_SECUENCE. */
	private static final String cs_FIND_SECUENCE = "SELECT SEC_PNG_GRUPO_NATURALEZA_JURIDICA_ID_GRUPO_NAT_JURIDICA.NEXTVAL FROM DUAL";

	/**
	 * Metodo para la Busqueda de todos los grupo naturaleza juridica que se encuentren en estado activo
	 * @return
	 * @throws B2BException
	 */
	public Collection<GrupoNaturalezaJuridica> findAllActivo(boolean ab_b)
	    throws B2BException
	{
		Collection<GrupoNaturalezaJuridica> lcgnj_cgnj;
		PreparedStatement                   lps_ps;
		ResultSet                           lrs_rs;

		lcgnj_cgnj     = new ArrayList<GrupoNaturalezaJuridica>();
		lps_ps         = null;
		lrs_rs         = null;

		try
		{
			if(ab_b)
				lps_ps = getConnection().prepareStatement(cs_FIND_ALL_ACTIVO);
			else
				lps_ps = getConnection().prepareStatement(cs_FIND_ALL_ACTIVO_ID);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcgnj_cgnj.add(getObjetFromResultSet(lrs_rs));
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

		if(!CollectionUtils.isValidCollection(lcgnj_cgnj))
			lcgnj_cgnj = null;

		return lcgnj_cgnj;
	}

	/**
	 * Metodo para encontrar todos los registros de la tabla SDB_PNG_GRUPO_NATURALEZA_JURIDICA.
	 *
	 * @return devuelve el valor del objeto collection
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<GrupoNaturalezaJuridica> findAll()
	    throws B2BException
	{
		Collection<GrupoNaturalezaJuridica> lcgnj_grupoNaturalezaJuridica;
		PreparedStatement                   lps_ps;
		ResultSet                           lrs_rs;

		lcgnj_grupoNaturalezaJuridica     = new ArrayList<GrupoNaturalezaJuridica>();
		lps_ps                            = null;
		lrs_rs                            = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcgnj_grupoNaturalezaJuridica.add(getObjetFromResultSet(lrs_rs));
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

		if(!CollectionUtils.isValidCollection(lcgnj_grupoNaturalezaJuridica))
			lcgnj_grupoNaturalezaJuridica = null;

		return lcgnj_grupoNaturalezaJuridica;
	}

	/**
	 * Metodo para encontrar todos los registros que coincidan con un idGrupoNaturalezaJuridica específico.
	 *
	 * @param agnj_param Objeto contenedor del id a utilizar como filtro en la busqueda
	 * @return Grupo naturaleza jurídica resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public GrupoNaturalezaJuridica findById(GrupoNaturalezaJuridica agnj_param)
	    throws B2BException
	{
		return (agnj_param != null) ? findById(agnj_param.getIdGrupoNatJuridica()) : null;
	}

	/**
	 * Metodo para encontrar todos los registros que coincidan con un idGrupoNaturalezaJuridica específico.
	 *
	 * @param as_idGrupoNatJuridica Objeto contenedor del id a utilizar como filtro en la busqueda
	 * @return Grupo naturaleza jurídica resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public GrupoNaturalezaJuridica findById(String as_idGrupoNatJuridica)
	    throws B2BException
	{
		GrupoNaturalezaJuridica lgnj_object;

		lgnj_object = null;

		if(StringUtils.isValidString(as_idGrupoNatJuridica))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(1, as_idGrupoNatJuridica);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lgnj_object = getObjetFromResultSet(lrs_rs);
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

		return lgnj_object;
	}

	/**
	 * Metodo para insertar o actualizar un registro en la base de datos de la tabla SDB_PNG_GRUPO_NATURALEZA_JURIDICA.
	 *
	 * @param agnj_param correspondiente al valor del tipo de objeto GrupoNaturalezaJuridica
	 * @param ab_query correspondiente al valor del tipo de objeto boolean
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void insertOrUpdate(GrupoNaturalezaJuridica agnj_param, boolean ab_query)
	    throws B2BException
	{
		if(agnj_param != null)
		{
			PreparedStatement lps_ps;
			PreparedStatement lps_secuencia;
			ResultSet         lrs_rs;

			lps_ps            = null;
			lps_secuencia     = null;
			lrs_rs            = null;

			try
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
							agnj_param.setIdGrupoNatJuridica(((BigDecimal)lo_o).toString());

							lps_ps.setString(li_column++, agnj_param.getIdGrupoNatJuridica());
						}
						else
							throw new B2BException(ErrorKeys.PGN_GRUPO_NATURALEZA_JURIDICA_SEQUENCE);
					}
				}

				lps_ps.setString(li_column++, agnj_param.getNombre());
				lps_ps.setString(li_column++, agnj_param.getActivo());

				if(ab_query)
				{
					lps_ps.setString(li_column++, agnj_param.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, agnj_param.getIpCreacion());
				}
				else
				{
					lps_ps.setString(li_column++, agnj_param.getIdUsuarioModificacion());
					lps_ps.setString(li_column++, agnj_param.getIpModificacion());
					lps_ps.setString(li_column++, agnj_param.getIdGrupoNatJuridica());
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
	 * Retorna el valor de GrupoNaturalezaJuridica contenido en el ResultSet
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de GrupoNaturalezaJuridica
	 * @throws SQLException Señala que se ha producido una excepción
	 * @see GrupoNaturalezaJuridica
	 */
	private GrupoNaturalezaJuridica getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		GrupoNaturalezaJuridica lgnj_grupoNaturalezaJuridica;

		lgnj_grupoNaturalezaJuridica = new GrupoNaturalezaJuridica();

		lgnj_grupoNaturalezaJuridica.setIdGrupoNatJuridica(ars_rs.getString("ID_GRUPO_NAT_JURIDICA"));
		lgnj_grupoNaturalezaJuridica.setNombre(ars_rs.getString("NOMBRE"));
		lgnj_grupoNaturalezaJuridica.setActivo(ars_rs.getString("ACTIVO"));
		lgnj_grupoNaturalezaJuridica.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		lgnj_grupoNaturalezaJuridica.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		lgnj_grupoNaturalezaJuridica.setIpCreacion(ars_rs.getString("IP_CREACION"));
		lgnj_grupoNaturalezaJuridica.setIdUsuarioModificacion(ars_rs.getString("ID_USUARIO_MODIFICACION"));
		lgnj_grupoNaturalezaJuridica.setFechaModificacion(ars_rs.getTimestamp("FECHA_MODIFICACION"));
		lgnj_grupoNaturalezaJuridica.setIpModificacion(ars_rs.getString("IP_MODIFICACION"));

		return lgnj_grupoNaturalezaJuridica;
	}
}
