package com.bachue.snr.prosnr01.dao.pgn;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.dao.IBase;

import com.bachue.snr.prosnr01.model.sdb.pgn.TipoPersona;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las consultas de la tabla SDB_PGN_TIPO_PERSONA
 *
 * @author Julian Vaca
 */
public class TipoPersonaDAO extends com.b2bsg.common.dataAccess2.BaseDAO implements IBase<TipoPersona>
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT ID_TIPO_PERSONA, DESCRIPCION, ID_USUARIO_CREACION, "
		+ " FECHA_CREACION, FECHA_MODIFICACION, ID_USUARIO_MODIFICACION, IP_MODIFICACION FROM SDB_PGN_TIPO_PERSONA WHERE ID_TIPO_PERSONA=?";

	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_DESCRIPCION = "SELECT * FROM SDB_PGN_TIPO_PERSONA WHERE DESCRIPCION=?";

	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT ID_TIPO_PERSONA, DESCRIPCION, ID_USUARIO_CREACION, "
		+ " FECHA_CREACION, FECHA_MODIFICACION, ID_USUARIO_MODIFICACION, IP_MODIFICACION FROM SDB_PGN_TIPO_PERSONA";

	/** Constante cs_FIND_SECUENCE. */
	private static final String cs_FIND_SECUENCE = "SELECT TIPO_PERSONA_SEC.NEXTVAL FROM DUAL";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_PGN_TIPO_PERSONA SET DESCRIPCION=?, "
		+ " ID_USUARIO_MODIFICACION=?, IP_MODIFICACION=?, FECHA_MODIFICACION=SYSTIMESTAMP WHERE ID_TIPO_PERSONA=?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_PGN_TIPO_PERSONA (ID_TIPO_PERSONA, "
		+ " DESCRIPCION, ID_USUARIO_CREACION, IP_CREACION, FECHA_CREACION) VALUES(?, ?, ?, SYSTIMESTAMP)";

	/**
	 * Instancia un nuevo objeto tipo persona DAO.
	 */
	public TipoPersonaDAO()
	{
	}

	/**
	 * Retorna el valor del objeto de tipo Collection de TipoPersona con todos los registros de la tabla
	 *
	 * @return devuelve el valor del objeto collection
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see TipoPersona
	 */
	public Collection<TipoPersona> findAll()
	    throws B2BException
	{
		Collection<TipoPersona> lctp_ctp;
		PreparedStatement       lps_ps;
		ResultSet               lrs_rs;

		lctp_ctp     = new ArrayList<TipoPersona>();
		lps_ps       = null;
		lrs_rs       = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lctp_ctp.add(getTipoPersona(lrs_rs));
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

		if(lctp_ctp.isEmpty())
			lctp_ctp = null;

		return lctp_ctp;
	}

	/** {@inheritdoc} */
	@Override
	public TipoPersona findById(TipoPersona atp_parametros)
	    throws B2BException
	{
		return (atp_parametros != null) ? findById(atp_parametros.getIdTipoPersona()) : null;
	}

	/**
	 * Busca un registro asociado a un id específico
	 *
	 * @param as_idTipoPersona id del tipo persona a buscar.
	 * @return Tipo persona resultante de la busqueda.
	 * @throws B2BException Si ocurre un error en base de datos
	 */
	public TipoPersona findById(String as_idTipoPersona)
	    throws B2BException
	{
		TipoPersona ltp_tp;

		ltp_tp = null;

		if(StringUtils.isValidString(as_idTipoPersona))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(1, as_idTipoPersona);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ltp_tp = getTipoPersona(lrs_rs);
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

		return ltp_tp;
	}

	/**
	 * Busca un registro asociado a un id específico
	 *
	 * @param as_descripcion id del tipo persona a buscar.
	 * @return Tipo persona resultante de la busqueda.
	 * @throws B2BException Si ocurre un error en base de datos
	 */
	public TipoPersona findByDescripcion(String as_descripcion)
	    throws B2BException
	{
		TipoPersona ltp_tp;

		ltp_tp = null;

		if(StringUtils.isValidString(as_descripcion))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_DESCRIPCION);

				lps_ps.setString(1, as_descripcion);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ltp_tp = getTipoPersona(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByDescripcion", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return ltp_tp;
	}

	/**
	 * Retorna el valor de la secuencia
	 *
	 * @return devuelve el valor del objeto int
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public int findSecuence()
	    throws B2BException
	{
		int               li_secuencia;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		li_secuencia     = 0;
		lps_ps           = null;
		lrs_rs           = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_SECUENCE);

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				li_secuencia = lrs_rs.getInt(1);
		}
		catch(SQLException lse_e)
		{
			logError(this, "findSecuence", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return li_secuencia;
	}

	/** {@inheritdoc} */
	@Override
	public void insertOrUpdate(TipoPersona atp_parametros, boolean ab_query)
	    throws B2BException
	{
		if(atp_parametros != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(ab_query ? cs_INSERT : cs_UPDATE);

				if(ab_query)
					lps_ps.setString(li_column++, atp_parametros.getIdTipoPersona());

				lps_ps.setString(li_column++, atp_parametros.getDescripcion());

				if(!ab_query)
				{
					lps_ps.setString(li_column++, atp_parametros.getIdUsuarioModificacion());
					lps_ps.setString(li_column++, atp_parametros.getIpModificacion());
					lps_ps.setString(li_column++, atp_parametros.getIdTipoPersona());
				}
				else
				{
					lps_ps.setString(li_column++, atp_parametros.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, atp_parametros.getIpCreacion());
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
	 * Retorna el valor de tipo persona.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de tipo persona
	 * @throws SQLException Señala que se ha producido una excepción
	 * @see TipoPersona
	 */
	private TipoPersona getTipoPersona(ResultSet ars_rs)
	    throws SQLException
	{
		TipoPersona ltp_tipoPersona;
		ltp_tipoPersona = new TipoPersona();

		ltp_tipoPersona.setIdTipoPersona(ars_rs.getString("ID_TIPO_PERSONA"));
		ltp_tipoPersona.setDescripcion(ars_rs.getString("DESCRIPCION"));
		ltp_tipoPersona.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		ltp_tipoPersona.setIdUsuarioModificacion(ars_rs.getString("ID_USUARIO_MODIFICACION"));
		ltp_tipoPersona.setIpModificacion(ars_rs.getString("IP_MODIFICACION"));
		ltp_tipoPersona.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		ltp_tipoPersona.setFechaModificacion(ars_rs.getTimestamp("FECHA_MODIFICACION"));

		return ltp_tipoPersona;
	}
}
