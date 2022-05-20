package com.bachue.snr.prosnr01.dao.pgn;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr04.model.pgn.MedioRecaudo;

import java.math.BigDecimal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase de manejo de datos para la tabla SDB_PGN_MEDIO_RECAUDO.
 *
 * @author Carlos Calderón
 */
public class MedioRecaudoDAO extends AuditoriaDao
{
	
	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL      = "SELECT * FROM SDB_PGN_MEDIO_RECAUDO ORDER BY NOMBRE_MEDIO_RECAUDO ASC ";
	
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID    = "SELECT * FROM SDB_PGN_MEDIO_RECAUDO WHERE ID_MEDIO_RECAUDO = ?";
	
	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE        = "UPDATE SDB_PGN_MEDIO_RECAUDO SET NOMBRE_MEDIO_RECAUDO = ?, ACTIVO = ?, "
		+ "ID_USUARIO_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP, IP_MODIFICACION = ? WHERE ID_MEDIO_RECAUDO = ?";
	
	/** Constante cs_INSERT. */
	private static final String cs_INSERT        = "INSERT INTO SDB_PGN_MEDIO_RECAUDO (ID_MEDIO_RECAUDO, NOMBRE_MEDIO_RECAUDO, ACTIVO, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION) "
		+ "VALUES(?, ?, ?, ?, SYSTIMESTAMP, ?)";
	
	/** Constante cs_FIND_SECUENCE. */
	private static final String cs_FIND_SECUENCE = "SELECT SEC_PGN_MEDIO_RECAUDO_ID_MEDIO_RECAUDO.NEXTVAL FROM DUAL";

	/**
	 * Metodo para encontrar todos los registros de la tabla SDB_PGN_MEDIO_RECAUDO.
	 *
	 * @return devuelve el valor del objeto collection
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<MedioRecaudo> findAll()
	    throws B2BException
	{
		Collection<MedioRecaudo> lcmr_cllMedioRecaudo;
		PreparedStatement        lps_ps;
		ResultSet                lrs_rs;

		lcmr_cllMedioRecaudo     = new ArrayList<MedioRecaudo>();
		lps_ps                   = null;
		lrs_rs                   = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcmr_cllMedioRecaudo.add(getObjetFromResultSet(lrs_rs));
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

		if(!CollectionUtils.isValidCollection(lcmr_cllMedioRecaudo))
			lcmr_cllMedioRecaudo = null;

		return lcmr_cllMedioRecaudo;
	}

	/**
	 * Metodo para encontrar todos los registros que coincidan
	 * con un codigoMensaje específico de la tabla SDB_PGN_MEDIO_RECAUDO.
	 *
	 * @param as_param correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor del objeto medio recaudo
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public MedioRecaudo findById(String as_param)
	    throws B2BException
	{
		MedioRecaudo      lmr_medioRecaudo;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lmr_medioRecaudo     = null;
		lps_ps               = null;
		lrs_rs               = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

			lps_ps.setString(1, as_param);

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				lmr_medioRecaudo = getObjetFromResultSet(lrs_rs);
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

		return lmr_medioRecaudo;
	}

	/**
	 * Metodo para encontrar todos los registros que coincidan
	 * con un codigoMensaje específico de la tabla SDB_PGN_MEDIO_RECAUDO.
	 *
	 * @param amr_param correspondiente al valor del tipo de objeto MedioRecaudo
	 * @return devuelve el valor del objeto medio recaudo
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public MedioRecaudo findById(MedioRecaudo amr_param)
	    throws B2BException
	{
		MedioRecaudo      lmr_medioRecaudo;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lmr_medioRecaudo     = null;
		lps_ps               = null;
		lrs_rs               = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

			lps_ps.setString(1, amr_param.getIdMedioRecaudo());

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				lmr_medioRecaudo = getObjetFromResultSet(lrs_rs);
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

		return lmr_medioRecaudo;
	}

	/**
	 * Metodo para insertar un registro en la base de datos de la tabla SDB_PGN_MEDIO_RECAUDO.
	 *
	 * @param amr_param correspondiente al valor del tipo de objeto MedioRecaudo
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void insert(MedioRecaudo amr_param)
	    throws B2BException
	{
		if(amr_param != null)
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

				lps_ps     = lc_connection.prepareStatement(cs_INSERT);

				lps_secuencia     = lc_connection.prepareStatement(cs_FIND_SECUENCE);

				lrs_rs = lps_secuencia.executeQuery();

				if(lrs_rs.next())
				{
					Object lo_o;

					lo_o = lrs_rs.getObject(1);

					if((lo_o != null) && lo_o instanceof BigDecimal)
					{
						amr_param.setIdMedioRecaudo(((BigDecimal)lo_o).toString());

						lps_ps.setString(li_column++, amr_param.getIdMedioRecaudo());
					}
					else
						throw new B2BException(ErrorKeys.PGN_MEDIO_RECAUDO_SEQUENCE);
				}

				lps_ps.setString(li_column++, amr_param.getNombreMedioRecaudo());
				lps_ps.setString(li_column++, amr_param.getActivo());
				lps_ps.setString(li_column++, amr_param.getIdUsuarioCreacion());
				lps_ps.setString(li_column++, amr_param.getIpCreacion());

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
				close(lps_secuencia);
				close(lrs_rs);
			}
		}
	}

	/**
	 * Metodo para actualizar un registro en la base de datos de la tabla SDB_PGN_MEDIO_RECAUDO.
	 *
	 * @param amr_param correspondiente al valor del tipo de objeto MedioRecaudo
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void update(MedioRecaudo amr_param)
	    throws B2BException
	{
		if(amr_param != null)
		{
			PreparedStatement lps_ps;

			lps_ps = null;

			try
			{
				Connection lc_connection;
				int        li_column;

				lc_connection     = getConnection();
				li_column         = 1;

				lps_ps = lc_connection.prepareStatement(cs_UPDATE);

				lps_ps.setString(li_column++, amr_param.getNombreMedioRecaudo());
				lps_ps.setString(li_column++, amr_param.getActivo());
				lps_ps.setString(li_column++, amr_param.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, amr_param.getIpModificacion());
				lps_ps.setString(li_column++, amr_param.getIdMedioRecaudo());

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
	 * Método para la obtencion del objeto MedioRecaudo.
	 *
	 * @param ars_rs objeto que representa el resultado de una sentencia sql en la base de datos
	 * @return el valor de MedioRecaudo
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private MedioRecaudo getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		MedioRecaudo lmr_mr;

		lmr_mr = new MedioRecaudo();

		lmr_mr.setIdMedioRecaudo(ars_rs.getString("ID_MEDIO_RECAUDO"));
		lmr_mr.setNombreMedioRecaudo(ars_rs.getString("NOMBRE_MEDIO_RECAUDO"));
		lmr_mr.setActivo(ars_rs.getString("ACTIVO"));

		fillAuditoria(ars_rs, lmr_mr);

		return lmr_mr;
	}
}
