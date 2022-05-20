package com.bachue.snr.prosnr01.dao.bng;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.model.sdb.bng.ComplementacionPredio;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Clase que contiene todos las consultas de la tabla SDB_BNG_COMPLEMENTACION_PREDIO
 *
 * @author garias
 */
public class ComplementacionPredioDAO extends BaseDAO
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_BNG_COMPLEMENTACION_PREDIO "
		+ "WHERE ID_COMPLEMENTACION = ?";

	/** Constante cs_FIND_SECUENCE. */
	private static final String cs_FIND_SECUENCE = "SELECT SEC_BNG_COMPLEMENTACION_PREDIO_ID_COMPLEMENTACION.NEXTVAL FROM DUAL";

	/** Constante cs_INSERT_COMPLEMENTACION. */
	private static final String cs_INSERT_COMPLEMENTACION = "INSERT INTO SDB_BNG_COMPLEMENTACION_PREDIO"
		+ "(ID_COMPLEMENTACION,COMPLEMENTACION,ID_USUARIO_CREACION,FECHA_CREACION,ID_DATOS_ANT_SISTEMA,IP_CREACION) "
		+ "VALUES (?,?,?,SYSTIMESTAMP,?,?)";

	/** Constante cs_UPDATE_BY_ID. */
	private static final String cs_UPDATE_BY_ID = "UPDATE SDB_BNG_COMPLEMENTACION_PREDIO SET COMPLEMENTACION = ?, "
		+ "ID_USUARIO_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP, IP_MODIFICACION = ? WHERE ID_COMPLEMENTACION = ? ";

	/**
	 * Busca un registro asociado a un id específico.
	 *
	 * @param acp_param Objeto contenedor del id a utilizar como filtro en la busqueda
	 * @return Complmentación predio resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see ComplementacionPredio
	 */
	public ComplementacionPredio findById(ComplementacionPredio acp_param)
	    throws B2BException
	{
		return (acp_param != null) ? findById(acp_param.getIdComplementacion()) : null;
	}

	/**
	 * Busca un registro asociado a un id específico.
	 *
	 * @param as_idComplementacion Objeto contenedor del id a utilizar como filtro en la busqueda
	 * @return Complmentación predio resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see ComplementacionPredio
	 */
	public ComplementacionPredio findById(String as_idComplementacion)
	    throws B2BException
	{
		ComplementacionPredio lcp_return;

		lcp_return = null;

		if(StringUtils.isValidString(as_idComplementacion))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(1, as_idComplementacion);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lcp_return = getObjetFromResultSet(lrs_rs);
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

		return lcp_return;
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

	/**
	 * Inserta un nuevo registro en la tabla
	 *
	 * @param lcp_nueva correspondiente al valor del tipo de objeto ComplementacionPredio
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void insert(ComplementacionPredio lcp_nueva)
	    throws B2BException
	{
		if(lcp_nueva != null)
		{
			ComplementacionPredio lcp_complementacion;
			PreparedStatement     lps_ps;
			ResultSet             lrs_rs;
			int                   li_cont;

			lcp_complementacion     = lcp_nueva;
			li_cont                 = 1;
			lps_ps                  = null;
			lrs_rs                  = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_INSERT_COMPLEMENTACION);

				lps_ps.setString(li_cont++, lcp_complementacion.getIdComplementacion());
				lps_ps.setString(li_cont++, lcp_complementacion.getComplementacion());
				lps_ps.setString(li_cont++, lcp_complementacion.getIdUsuarioCreacion());
				lps_ps.setString(li_cont++, lcp_complementacion.getIdDatosAntSistema());
				lps_ps.setString(li_cont++, lcp_complementacion.getIpCreacion());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "insert", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}
	}

	/**
	 * Actualiza el registro en la base de datos
	 *
	 * @param acp_cp correspondiente al valor del tipo de objeto ComplementacionPredio
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void updateById(ComplementacionPredio acp_cp)
	    throws B2BException
	{
		if(acp_cp != null)
		{
			ComplementacionPredio lcp_complemento;
			int                   li_cont;
			PreparedStatement     lps_ps;
			ResultSet             lrs_rs;

			lcp_complemento     = acp_cp;
			li_cont             = 1;
			lps_ps              = null;
			lrs_rs              = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_UPDATE_BY_ID);

				lps_ps.setString(li_cont++, lcp_complemento.getComplementacion());
				lps_ps.setString(li_cont++, lcp_complemento.getIdUsuarioModificacion());
				lps_ps.setString(li_cont++, lcp_complemento.getIpModificacion());
				lps_ps.setString(li_cont++, lcp_complemento.getIdComplementacion());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "updateById", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}
	}

	/**
	 * Retorna el valor de ComplementacionPredio
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de ComplementacionPredio
	 * @throws SQLException Señala que se ha producido una excepción
	 * @see ComplementacionPredio
	 */
	private ComplementacionPredio getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		ComplementacionPredio lcp_cp;

		lcp_cp = new ComplementacionPredio();

		lcp_cp.setIdComplementacion(ars_rs.getString("ID_COMPLEMENTACION"));
		lcp_cp.setComplementacion(ars_rs.getString("COMPLEMENTACION"));
		lcp_cp.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		lcp_cp.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));

		return lcp_cp;
	}
}
