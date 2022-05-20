package com.bachue.snr.prosnr01.dao.pgn;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.dao.IBase;

import com.bachue.snr.prosnr01.model.sdb.pgn.InstanciaConsulta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las consultas de la tabla SDB_PGN_INSTANCIA_CONSULTA
 *
 * @author Julian Vaca
 */
public class InstanciaConsultaDAO extends com.b2bsg.common.dataAccess2.BaseDAO implements IBase<InstanciaConsulta>
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_PGN_INSTANCIA_CONSULTA WHERE ID_INSTANCIA = ? AND ID_CAMPO = ?";

	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_PGN_INSTANCIA_CONSULTA ";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_PGN_INSTANCIA_CONSULTA SET "
		+ " ID_CONSULTA = ?, VALOR = ?, ID_USUARIO_MODIFICACION=?, FECHA_MODIFICACION= SYSTIMESTAMP, IP_MODIFICACION=? WHERE ID_INSTANCIA = ? AND ID_CAMPO = ?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_PGN_INSTANCIA_CONSULTA(ID_INSTANCIA, ID_CAMPO, ID_CONSULTA, "
		+ "VALOR, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION) VALUES(?, ?, ?, ?, ?, SYSTIMESTAMP, ?)";

	/**
	 * Metodo para traer de la base de datos todos los registros de la tabla SDB_PGN_INSTANCIA_CONSULTA.
	 *
	 * @param ab_b correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor del objeto collection
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<InstanciaConsulta> findAll(boolean ab_b)
	    throws B2BException
	{
		Collection<InstanciaConsulta> lcic_instancia;
		PreparedStatement             lps_ps;
		ResultSet                     lrs_rs;
		StringBuilder                 lsb_sbf;

		lcic_instancia     = new ArrayList<InstanciaConsulta>();
		lps_ps             = null;
		lrs_rs             = null;
		lsb_sbf            = new StringBuilder(cs_FIND_ALL);

		try
		{
			if(ab_b)
				lsb_sbf = lsb_sbf.append(" ORDER BY ID_INSTANCIA ASC");
			else
				lsb_sbf = lsb_sbf.append(" ORDER BY ID_INSTANCIA ASC ");

			lps_ps     = getConnection().prepareStatement(lsb_sbf.toString());

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcic_instancia.add(getInstanciaConsulta(lrs_rs));
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

		if(lcic_instancia.isEmpty())
			lcic_instancia = null;

		return lcic_instancia;
	}

	/** {@inheritdoc} */
	@Override
	public InstanciaConsulta findById(InstanciaConsulta ap_parametros)
	    throws B2BException
	{
		InstanciaConsulta lic_instancia;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lic_instancia     = null;
		lps_ps            = null;
		lrs_rs            = null;

		try
		{
			int li_contador;
			li_contador     = 1;

			lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

			lps_ps.setLong(li_contador++, ap_parametros.getIdInstancia());
			lps_ps.setLong(li_contador++, ap_parametros.getIdCampo());

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				lic_instancia = getInstanciaConsulta(lrs_rs);
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

		return lic_instancia;
	}

	/**
	 * Metodo para actualizar o insertar registros en la tabla SDB_PGN_INSTANCIA_CONSULTA.
	 *
	 * @param ac_parametros correspondiente al valor del tipo de objeto InstanciaConsulta
	 * @param ab_query correspondiente al valor del tipo de objeto boolean
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	@Override
	public void insertOrUpdate(InstanciaConsulta ac_parametros, boolean ab_query)
	    throws B2BException
	{
		if(ac_parametros != null)
		{
			PreparedStatement lps_ps;
			PreparedStatement lps_secuencia;
			ResultSet         lrs_rs;

			lps_ps            = null;
			lps_secuencia     = null;
			lrs_rs            = null;

			try
			{
				int        li_column;
				Connection lc_connection;

				li_column         = 1;
				lc_connection     = getConnection();

				lps_ps = lc_connection.prepareStatement(ab_query ? cs_INSERT : cs_UPDATE);

				if(ab_query)
				{
					lps_ps.setLong(li_column++, ac_parametros.getIdInstancia());
					lps_ps.setLong(li_column++, ac_parametros.getIdCampo());
					lps_ps.setLong(li_column++, ac_parametros.getIdConsulta());
					lps_ps.setString(li_column++, ac_parametros.getValor());
					lps_ps.setString(li_column++, ac_parametros.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, ac_parametros.getIpCreacion());
				}
				else
				{
					lps_ps.setLong(li_column++, ac_parametros.getIdConsulta());
					lps_ps.setString(li_column++, ac_parametros.getValor());
					lps_ps.setString(li_column++, ac_parametros.getIdUsuarioModificacion());
					lps_ps.setString(li_column++, ac_parametros.getIpModificacion());
					lps_ps.setLong(li_column++, ac_parametros.getIdInstancia());
					lps_ps.setLong(li_column++, ac_parametros.getIdCampo());
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

				if(ab_query)
				{
					close(lps_secuencia);
					close(lrs_rs);
				}
			}
		}
	}

	/**
	 * Método para obtener objeto de tipo instancia consulta.
	 *
	 * @param ars_rs objeto que representa el resultado de una sentencia sql en la base de datos
	 * @return el valor de instancia consulta
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private InstanciaConsulta getInstanciaConsulta(ResultSet ars_rs)
	    throws SQLException
	{
		InstanciaConsulta ld_datos;
		ld_datos = new InstanciaConsulta();

		ld_datos.setIdInstancia(ars_rs.getLong("ID_INSTANCIA"));
		ld_datos.setIdConsulta(ars_rs.getLong("ID_CONSULTA"));
		ld_datos.setIdCampo(ars_rs.getLong("ID_CAMPO"));
		ld_datos.setValor(ars_rs.getString("VALOR"));
		ld_datos.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		ld_datos.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		ld_datos.setIpCreacion(ars_rs.getString("IP_CREACION"));
		ld_datos.setIdUsuarioModificacion(ars_rs.getString("ID_USUARIO_MODIFICACION"));
		ld_datos.setFechaModificacion(ars_rs.getTimestamp("FECHA_MODIFICACION"));
		ld_datos.setIpModificacion(ars_rs.getString("IP_MODIFICACION"));

		return ld_datos;
	}
}
