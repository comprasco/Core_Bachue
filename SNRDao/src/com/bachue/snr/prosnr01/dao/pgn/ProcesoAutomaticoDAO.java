package com.bachue.snr.prosnr01.dao.pgn;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;

import com.bachue.snr.prosnr01.dao.IBase;

import com.bachue.snr.prosnr01.model.sdb.pgn.ProcesoAutomatico;

import java.math.BigDecimal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase de manejo de datos para la tabla SDB_PGN_PROCESO_AUTOMATICO
 * @author Luis Chacón
 *
 */
public class ProcesoAutomaticoDAO extends com.b2bsg.common.dataAccess2.BaseDAO implements IBase<ProcesoAutomatico>
{
	/** Constante  cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_PGN_PROCESO_AUTOMATICO WHERE ID_PROCESO_AUTOMATICO = ?";

	/** Constante  cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_PGN_PROCESO_AUTOMATICO ";

	/** Constante  cs_FIND_ALL. */
	private static final String cs_FIND_SECUENCE = "SELECT SEC_PGN_PROCESO_AUTOMATICO_ID_PROCESO_AUTOMATICO.NEXTVAL FROM DUAL";

	/** Constante  cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_PGN_PROCESO_AUTOMATICO SET NOMBRE_PROCESO = ?, "
		+ " FECHA_MODIFICACION = SYSTIMESTAMP, DESCRIPCION = ?, NOMBRE_PROC_ALMACENADO = ?, ID_USUARIO_MODIFICACION = ?,"
		+ " IP_MODIFICACION = ?, ESTADO = ? WHERE ID_PROCESO_AUTOMATICO = ? ";

	/** Constante  cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_PGN_PROCESO_AUTOMATICO (ID_PROCESO_AUTOMATICO, "
		+ "NOMBRE_PROCESO, DESCRIPCION, NOMBRE_PROC_ALMACENADO, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION, ESTADO) "
		+ "VALUES (?,?,?,?,?,SYSTIMESTAMP,?,?) ";

	/**
	 * Metodo para traer de la base de datos todos los registros de la tabla SDB_PGN_PROCESO_AUTOMATICO
	 * @return
	 * @throws B2BException
	 */
	public Collection<ProcesoAutomatico> findAll(boolean ab_b)
	    throws B2BException
	{
		Collection<ProcesoAutomatico> lp_procesoAut;
		PreparedStatement             lps_ps;
		ResultSet                     lrs_rs;
		StringBuilder                 lsb_sbf;

		lp_procesoAut     = new ArrayList<ProcesoAutomatico>();
		lps_ps            = null;
		lrs_rs            = null;
		lsb_sbf           = new StringBuilder(cs_FIND_ALL);

		try
		{
			if(ab_b)
				lsb_sbf = lsb_sbf.append(
					    " WHERE ESTADO = 'A' ORDER BY LENGTH(ID_PROCESO_AUTOMATICO),ID_PROCESO_AUTOMATICO "
					);
			else
				lsb_sbf = lsb_sbf.append(" ORDER BY LENGTH(ID_PROCESO_AUTOMATICO),ID_PROCESO_AUTOMATICO ");

			lps_ps     = getConnection().prepareStatement(lsb_sbf.toString());

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lp_procesoAut.add(getProcesoAutomatico(lrs_rs));
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

		if(lp_procesoAut.isEmpty())
			lp_procesoAut = null;

		return lp_procesoAut;
	}

	@Override
	public ProcesoAutomatico findById(ProcesoAutomatico ap_parametros)
	    throws B2BException
	{
		ProcesoAutomatico lp_pais;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lp_pais     = null;
		lps_ps      = null;
		lrs_rs      = null;

		try
		{
			int li_contador;
			li_contador     = 1;

			lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

			lps_ps.setString(li_contador++, ap_parametros.getIdProcesoAutomatico());

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				lp_pais = getProcesoAutomatico(lrs_rs);
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

		return lp_pais;
	}

	/**
	 * Método para hallar el numero siguiente de la secuencia
	 * @return el valor de la secuencia
	 * @throws B2BException
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
	 * Metodo para actualizar o insertar registros en la tabla SDB_PGN_PROCESO_AUTOMATICO
	 * @param apt_parametros true para insertar false para actualizar
	 */
	@Override
	public void insertOrUpdate(ProcesoAutomatico ac_parametros, boolean ab_query)
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
					lps_secuencia     = lc_connection.prepareStatement(cs_FIND_SECUENCE);

					lrs_rs = lps_secuencia.executeQuery();

					if(lrs_rs.next())
					{
						Object lo_o;
						lo_o = lrs_rs.getObject(1);

						if((lo_o != null) && lo_o instanceof BigDecimal)
							ac_parametros.setIdProcesoAutomatico(((BigDecimal)lo_o).toString());
						else
							throw new B2BException(ErrorKeys.PGN_PROCESO_AUTOMATICO_SEQUENCE);
					}

					lps_ps.setString(li_column++, ac_parametros.getIdProcesoAutomatico());
					lps_ps.setString(li_column++, ac_parametros.getNombreProceso());
					lps_ps.setString(li_column++, ac_parametros.getDescripcion());
					lps_ps.setString(li_column++, ac_parametros.getNombreProcAlmacenado());
					lps_ps.setString(li_column++, ac_parametros.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, ac_parametros.getIpCreacion());
					lps_ps.setString(li_column++, ac_parametros.getEstado());
				}
				else
				{
					lps_ps.setString(li_column++, ac_parametros.getNombreProceso());
					lps_ps.setString(li_column++, ac_parametros.getDescripcion());
					lps_ps.setString(li_column++, ac_parametros.getNombreProcAlmacenado());
					lps_ps.setString(li_column++, ac_parametros.getIdUsuarioModificacion());
					lps_ps.setString(li_column++, ac_parametros.getIpModificacion());
					lps_ps.setString(li_column++, ac_parametros.getEstado());
					lps_ps.setString(li_column++, ac_parametros.getIdProcesoAutomatico());
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
	 * Metodo para obtener objeto de tipo proceso automatico
	 * @param ars_rs objeto que representa el resultado de una sentencia sql en la base de datos
	 * @return
	 * @throws SQLException
	 */
	private ProcesoAutomatico getProcesoAutomatico(ResultSet ars_rs)
	    throws SQLException
	{
		ProcesoAutomatico ld_datos;
		ld_datos = new ProcesoAutomatico();

		ld_datos.setIdProcesoAutomatico(ars_rs.getString("ID_PROCESO_AUTOMATICO"));
		ld_datos.setNombreProceso(ars_rs.getString("NOMBRE_PROCESO"));
		ld_datos.setDescripcion(ars_rs.getString("DESCRIPCION"));
		ld_datos.setNombreProcAlmacenado(ars_rs.getString("NOMBRE_PROC_ALMACENADO"));
		ld_datos.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		ld_datos.setEstado(ars_rs.getString("ESTADO"));
		ld_datos.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		ld_datos.setIpCreacion(ars_rs.getString("IP_CREACION"));
		ld_datos.setIdUsuarioModificacion(ars_rs.getString("ID_USUARIO_MODIFICACION"));
		ld_datos.setFechaModificacion(ars_rs.getTimestamp("FECHA_MODIFICACION"));
		ld_datos.setIpModificacion(ars_rs.getString("IP_MODIFICACION"));

		return ld_datos;
	}
}
