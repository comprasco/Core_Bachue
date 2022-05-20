package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr01.model.sdb.acc.Recurso;

import java.math.BigDecimal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las propiedades RecursoDAO.
 *
 * @author  Andrés Rocha
 * Fecha de Creacion: 19/06/2020
 */
public class RecursoDAO extends AuditoriaDao
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_ACC_RECURSO  WHERE ID_RECURSO = ?";

	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_SOLICITUD_RECURSO = "SELECT * FROM SDB_ACC_RECURSO WHERE ID_SOLICITUD_RECURSO = ?";

	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_ACC_RECURSO ORDER BY ID_RECURSO ASC";

	/** Constante cs_FIND_SECUENCIA. */
	private static final String cs_FIND_SECUENCE = "SELECT SEC_SDB_ACC_RECURSO_ID_RECURSO.NEXTVAL FROM DUAL";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_ACC_RECURSO(ID_RECURSO, ID_TURNO, FECHA_RECURSO, DIGITALIZADO, "
		+ "ID_DOCUMENTO, VERSION_DOCUMENTO, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION, ID_SOLICITUD,ID_SOLICITUD_RECURSO) VALUES(?,?,?,?,?,?,?,SYSTIMESTAMP,?,?,?)";

	/** Constante cs_UPDATE_BY_ID. */
	private static final String cs_UPDATE = "UPDATE SDB_ACC_RECURSO SET ID_TURNO = ?, FECHA_RECURSO = ?, DIGITALIZADO = ?,  ID_DOCUMENTO = ?, VERSION_DOCUMENTO = ?,  "
		+ "ID_USUARIO_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP,  IP_MODIFICACION = ? ,ID_SOLICITUD = ? , ID_SOLICITUD_RECURSO = ? WHERE ID_RECURSO = ?";

	/**
	 * Método que retorna una colección con todos los recursos.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Recurso> findAll()
	    throws B2BException
	{
		Collection<Recurso> lr_recurso;
		PreparedStatement   lps_ps;
		ResultSet           lrs_rs;

		lr_recurso     = new ArrayList<Recurso>();
		lps_ps         = null;
		lrs_rs         = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lr_recurso.add(getRecurso(lrs_rs));
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

		if(lr_recurso.isEmpty())
			lr_recurso = null;

		return lr_recurso;
	}

	/**
	 * Método que retorna un recurso específico según el id.
	 *
	 * @param ar_parametros Argumento de tipo <code>Recurso</code> que contiene el recurso.
	 * @return devuelve el valor del objeto recurso.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Recurso findById(Recurso ar_parametros)
	    throws B2BException
	{
		Recurso           lr_recurso;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lr_recurso     = null;
		lps_ps         = null;
		lrs_rs         = null;

		try
		{
			int li_contador;
			li_contador     = 1;

			lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

			lps_ps.setObject(li_contador++, ar_parametros.getIdRecurso());

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				lr_recurso = getRecurso(lrs_rs);
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

		return lr_recurso;
	}

	/**
	 * Método que retorna un recurso específico según el id solicitud.
	 *
	 * @param ar_parametros Argumento de tipo <code>Recurso</code> que contiene el recurso.
	 * @return devuelve el valor del objeto recurso.
	 * @throws B2BException Señala que se ha generado una excepción
	 */
	public Recurso findByIdSolicitud(Recurso ar_parametros)
	    throws B2BException
	{
		Recurso           lr_recurso;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lr_recurso     = null;
		lps_ps         = null;
		lrs_rs         = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_BY_SOLICITUD_RECURSO);

			lps_ps.setObject(1, ar_parametros.getIdSolicitud());

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				lr_recurso = getRecurso(lrs_rs);
		}
		catch(SQLException lse_e)
		{
			logError(this, "findByIdSolicitud", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return lr_recurso;
	}

	/**
	 * Método que retorna un recurso específico según el id solicitud recurso.
	 *
	 * @param ar_parametros Argumento de tipo <code>Recurso</code> que contiene el recurso.
	 * @return devuelve el valor del objeto recurso.
	 * @throws B2BException Señala que se ha generado una excepción
	 */
	public Recurso findByIdSolicitudRecurso(Recurso ar_parametros)
	    throws B2BException
	{
		Recurso           lr_recurso;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lr_recurso     = null;
		lps_ps         = null;
		lrs_rs         = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_BY_SOLICITUD_RECURSO);

			lps_ps.setObject(1, ar_parametros.getIdSolicitudRecurso());

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				lr_recurso = getRecurso(lrs_rs);
		}
		catch(SQLException lse_e)
		{
			logError(this, "findByIdSolicitudRecurso", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return lr_recurso;
	}

	/**
	 * Inserta o actualiza el registro en la tabla.
	 *
	 * @param ar_parametros Argumento de tipo <code>Recurso</code> que contiene el valor del recurso.
	 * @param ab_query Argumento de tipo <code>Boolean</code> que indica la sentencia a ejecutar.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void insertOrUpdate(Recurso ar_parametros, boolean ab_query)
	    throws B2BException
	{
		if(ar_parametros != null)
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
							ar_parametros.setIdRecurso(((BigDecimal)lo_o).toString());
						else
							throw new B2BException(ErrorKeys.ACC_RECURSO_SEQUENCE);
					}

					lps_ps.setString(li_column++, ar_parametros.getIdRecurso());
				}

				lps_ps.setString(li_column++, ar_parametros.getIdTurno());
				setDate(lps_ps, ar_parametros.getFechaRecurso(), li_column++);
				lps_ps.setString(li_column++, ar_parametros.getDigitalizado());
				lps_ps.setString(li_column++, ar_parametros.getIdDocumento());
				setLong(lps_ps, ar_parametros.getVersionDocumento(), li_column++);

				if(ab_query)
				{
					lps_ps.setString(li_column++, ar_parametros.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, ar_parametros.getIpCreacion());
				}
				else
				{
					lps_ps.setString(li_column++, ar_parametros.getIdUsuarioModificacion());
					lps_ps.setString(li_column++, ar_parametros.getIpModificacion());
				}

				lps_ps.setString(li_column++, ar_parametros.getIdSolicitud());
				lps_ps.setString(li_column++, ar_parametros.getIdSolicitudRecurso());

				if(!ab_query)
					lps_ps.setString(li_column++, ar_parametros.getIdRecurso());

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
	 * Método que retorna el valor del recurso.
	 *
	 * @param ars_rs Argumento de tipo <code>ResultSet</code> correspondiente al valor del tipo de objeto ResultSet.
	 * @return el valor de recurso.
	 * @throws SQLException Señala que se ha producido una excepción.
	 */
	private Recurso getRecurso(ResultSet ars_rs)
	    throws SQLException
	{
		Recurso lr_datos;

		lr_datos = new Recurso();

		lr_datos.setIdRecurso(ars_rs.getString("ID_RECURSO"));
		lr_datos.setIdTurno(ars_rs.getString("ID_TURNO"));
		lr_datos.setFechaRecurso(ars_rs.getTimestamp("FECHA_RECURSO"));
		lr_datos.setDigitalizado(ars_rs.getString("DIGITALIZADO"));
		lr_datos.setIdDocumento(ars_rs.getString("ID_DOCUMENTO"));
		lr_datos.setVersionDocumento(getLong(ars_rs, "VERSION_DOCUMENTO"));
		lr_datos.setIdSolicitud(ars_rs.getString("ID_SOLICITUD"));
		lr_datos.setIdSolicitudRecurso(ars_rs.getString("ID_SOLICITUD_RECURSO"));

		fillAuditoria(ars_rs, lr_datos);

		return lr_datos;
	}
}
