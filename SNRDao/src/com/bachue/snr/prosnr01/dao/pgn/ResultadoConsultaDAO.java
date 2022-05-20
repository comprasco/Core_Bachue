package com.bachue.snr.prosnr01.dao.pgn;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.security.Digest;

import com.bachue.snr.prosnr01.model.sdb.pgn.ResultadoConsulta;

import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las propiedades ResultadoConsultaDAO.
 *
 * @author  Julian Vaca
 * Fecha de Creacion: 10/08/2020
 */
public class ResultadoConsultaDAO extends BaseDAO
{
	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_PGN_RESULTADO_CONSULTA(ID_INSTANCIA, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION, ";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_PGN_RESULTADO_CONSULTA SET ";

	/** Constante cs_FIND_SECUENCE. */
	private static final String cs_FIND_SECUENCE = "SELECT SEC_PGN_RESULTADO_CONSULTA_ID_INSTANCIA.NEXTVAL FROM DUAL";

	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_PGN_RESULTADO_CONSULTA ";

	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_PGN_RESULTADO_CONSULTA WHERE ID_INSTANCIA = ?";

	/**
	 * Retorna el valor del objeto de tipo Collection de ResultadoConsulta con todos los registros de la tabla.
	 *
	 * @return devuelve el valor del objeto collection
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see ResultadoConsulta
	 */
	public Collection<ResultadoConsulta> findAll()
	    throws B2BException
	{
		Collection<ResultadoConsulta> lp_pais;
		PreparedStatement             lps_ps;
		ResultSet                     lrs_rs;
		StringBuilder                 lsb_sb;
		lsb_sb     = new StringBuilder(cs_FIND_ALL);

		lp_pais     = new ArrayList<ResultadoConsulta>();
		lps_ps      = null;
		lrs_rs      = null;

		try
		{
			lsb_sb     = lsb_sb.append(" ORDER BY ID_INSTANCIA ASC");
			lps_ps     = getConnection().prepareStatement(lsb_sb.toString());

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lp_pais.add(getResultadoConsulta(lrs_rs));
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

		if(lp_pais.isEmpty())
			lp_pais = null;

		return lp_pais;
	}

	/**
	 * Busca un registro de la tabla por medio de su id_identificador.
	 *
	 * @param arc_param Objeto contenedor de los filtros a usar en la consulta
	 * @return ResultadoConsulta resultante de la consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public ResultadoConsulta findById(ResultadoConsulta arc_param)
	    throws B2BException
	{
		ResultadoConsulta lrc_param;

		lrc_param = null;

		if(arc_param != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setLong(1, arc_param.getIdInstancia());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lrc_param = getResultadoConsulta(lrs_rs);
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

		return lrc_param;
	}

	/**
	 * Retorna el valor del Id correspondiente al insert del nuevo registro.
	 *
	 * @param at_param correspondiente al valor del tipo de objeto ResultadoConsulta
	 * @return devuelve el valor del objeto long
	 * @throws B2BException Señala que se ha producido una excepción
	 * @throws IOException Señala que se ha producido una excepción de E / S.
	 */
	public long insert(ResultadoConsulta at_param)
	    throws B2BException, IOException
	{
		long ll_secuence;
		ll_secuence = 0;

		if(at_param != null)
		{
			int               li_cont;
			PreparedStatement lps_ps;
			PreparedStatement lps_secuencia;
			Connection        lc_connection;
			ResultSet         lrs_rs;
			StringBuilder     lsb_sb;
			byte[]            lba_imagenBlob;
			boolean           lb_validBlob;

			lps_secuencia      = null;
			lrs_rs             = null;
			li_cont            = 1;
			lps_ps             = null;
			lsb_sb             = new StringBuilder(cs_INSERT);
			lba_imagenBlob     = at_param.getResultadoBlob();

			try
			{
				lc_connection     = getConnection();
				lps_secuencia     = lc_connection.prepareStatement(cs_FIND_SECUENCE);
				lb_validBlob      = lba_imagenBlob != null;

				if(lb_validBlob)
					lsb_sb = lsb_sb.append(" RESULTADO, HASH_CONSULTA ) VALUES(?, ?, SYSTIMESTAMP, ?, ?, ?)");
				else
					lsb_sb = lsb_sb.append(" HASH_CONSULTA) VALUES(?, ?, SYSTIMESTAMP, ?, ?)");

				lps_ps     = getConnection().prepareStatement(lsb_sb.toString());
				lrs_rs     = lps_secuencia.executeQuery();

				if(lrs_rs.next())
				{
					ll_secuence = lrs_rs.getLong(1);
					lps_ps.setLong(li_cont++, ll_secuence);
				}

				{
					lps_ps.setString(li_cont++, at_param.getIdUsuarioCreacion());
					lps_ps.setString(li_cont++, at_param.getIpCreacion());

					if(lb_validBlob)
					{
						InputStream lis_is;

						lis_is = new ByteArrayInputStream(lba_imagenBlob);

						lps_ps.setBinaryStream(li_cont++, lis_is);
						lps_ps.setString(li_cont++, Digest.digest(lba_imagenBlob, "SHA-1"));

						lis_is.close();
					}
					else
						lps_ps.setString(li_cont++, at_param.getHashConsulta());
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
				close(lps_secuencia);
				close(lrs_rs);
			}
		}

		return ll_secuence;
	}

	/**
	 * Actualiza el registro en la tabla.
	 *
	 * @param at_param correspondiente al valor del tipo de objeto ResultadoConsulta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @throws IOException Señala que se ha producido una excepción de E / S.
	 */
	public void update(ResultadoConsulta at_param)
	    throws B2BException, IOException
	{
		if(at_param != null)
		{
			int               li_cont;
			PreparedStatement lps_ps;

			li_cont     = 1;
			lps_ps      = null;

			try
			{
				StringBuilder lsb_sb;

				lsb_sb = new StringBuilder(cs_UPDATE);

				{
					byte[] lba_imagenBlob;

					lba_imagenBlob = at_param.getResultadoBlob();

					lsb_sb.append(
					    (lba_imagenBlob != null)
					    ? "RESULTADO=?, HASH_CONSULTA=?, ID_USUARIO_MODIFICACION=?, FECHA_MODIFICACION=SYSTIMESTAMP, IP_MODIFICACION=? WHERE ID_INSTANCIA=?"
					    : "HASH_CONSULTA=?, ID_USUARIO_MODIFICACION=?, FECHA_MODIFICACION=SYSTIMESTAMP, IP_MODIFICACION=? WHERE ID_INSTANCIA=?"
					);

					lps_ps = getConnection().prepareStatement(lsb_sb.toString());

					if(lba_imagenBlob != null)
					{
						InputStream lis_is;

						lis_is = new ByteArrayInputStream(lba_imagenBlob);

						lps_ps.setBinaryStream(li_cont++, lis_is);
						lps_ps.setString(li_cont++, Digest.digest(lba_imagenBlob, "SHA-1"));

						lis_is.close();
					}
					else
						lps_ps.setString(li_cont++, at_param.getHashConsulta());

					lps_ps.setString(li_cont++, at_param.getIdUsuarioModificacion());
					lps_ps.setString(li_cont++, at_param.getIpModificacion());
				}

				lps_ps.setLong(li_cont++, at_param.getIdInstancia());

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
	 * Retorna el valor de resultado consulta.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de resultado consulta
	 * @throws SQLException Señala que se ha producido una excepción
	 * @see ResultadoConsulta
	 */
	private ResultadoConsulta getResultadoConsulta(ResultSet ars_rs)
	    throws SQLException
	{
		ResultadoConsulta lrc_result;
		lrc_result = new ResultadoConsulta();

		byte[] lba_blob;
		Blob   lb_blob;

		lb_blob = ars_rs.getBlob("RESULTADO");

		try
		{
			lba_blob = IOUtils.toByteArray(lb_blob.getBinaryStream());
		}
		catch(Exception e)
		{
			lba_blob = null;
		}

		lrc_result.setIdInstancia(ars_rs.getLong("ID_INSTANCIA"));
		lrc_result.setResultadoBlob(lba_blob);
		lrc_result.setHashConsulta(ars_rs.getString("HASH_CONSULTA"));
		lrc_result.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		lrc_result.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		lrc_result.setIpCreacion(ars_rs.getString("IP_CREACION"));
		lrc_result.setIdUsuarioModificacion(ars_rs.getString("ID_USUARIO_MODIFICACION"));
		lrc_result.setFechaModificacion(ars_rs.getTimestamp("FECHA_MODIFICACION"));
		lrc_result.setIpModificacion(ars_rs.getString("IP_MODIFICACION"));

		return lrc_result;
	}
}
