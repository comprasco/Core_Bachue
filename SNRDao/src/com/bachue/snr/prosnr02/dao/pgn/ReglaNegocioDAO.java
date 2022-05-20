package com.bachue.snr.prosnr02.dao.pgn;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.DateUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr02.model.pgn.ReglaNegocio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase para el manejo de datos para la tabla SDB_PGN_REGLA_NEGOCIO.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 6/07/2020
 */
public class ReglaNegocioDAO extends AuditoriaDao
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT ID_REGLA_NEGOCIO, NOMBRE, r.DESCRIPCION, NOMBRE_PROCEDIMIENTO, r.ID_TOPOLOGIA_REGLA, FECHA_DESDE, FECHA_HASTA, TIPO_REGLA, TEXTO_VALIDACION, r.ACTIVO, r.ID_USUARIO_CREACION, r.FECHA_CREACION, r.IP_CREACION, r.ID_USUARIO_MODIFICACION, r.FECHA_MODIFICACION, r.IP_MODIFICACION, t.DESCRIPCION AS NOMBRE_TOPOLOGIA FROM SDB_PGN_REGLA_NEGOCIO r JOIN SDB_PGN_TOPOLOGIA_REGLA t ON t.ID_TOPOLOGIA_REGLA = r.ID_TOPOLOGIA_REGLA WHERE ID_REGLA_NEGOCIO = ?";

	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT ID_REGLA_NEGOCIO, NOMBRE, r.DESCRIPCION, NOMBRE_PROCEDIMIENTO, r.ID_TOPOLOGIA_REGLA, FECHA_DESDE, FECHA_HASTA, TIPO_REGLA, TEXTO_VALIDACION, r.ACTIVO, r.ID_USUARIO_CREACION, r.FECHA_CREACION, r.IP_CREACION, r.ID_USUARIO_MODIFICACION, r.FECHA_MODIFICACION, r.IP_MODIFICACION, t.DESCRIPCION AS NOMBRE_TOPOLOGIA FROM SDB_PGN_REGLA_NEGOCIO r JOIN SDB_PGN_TOPOLOGIA_REGLA t ON t.ID_TOPOLOGIA_REGLA = r.ID_TOPOLOGIA_REGLA WHERE r.ACTIVO = 'S' ORDER BY r.NOMBRE ASC";

	/** Constante  cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_PGN_REGLA_NEGOCIO(ID_REGLA_NEGOCIO, NOMBRE, DESCRIPCION, NOMBRE_PROCEDIMIENTO, ID_TOPOLOGIA_REGLA, FECHA_DESDE, FECHA_HASTA, TIPO_REGLA, TEXTO_VALIDACION, ACTIVO, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSTIMESTAMP, ?)";

	/** Constante  cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_PGN_REGLA_NEGOCIO SET NOMBRE=?, DESCRIPCION=?, NOMBRE_PROCEDIMIENTO=?, ID_TOPOLOGIA_REGLA=?, FECHA_DESDE=?, FECHA_HASTA=?, TIPO_REGLA=?, TEXTO_VALIDACION=?, ACTIVO=?, ID_USUARIO_MODIFICACION=?, IP_MODIFICACION=?, FECHA_MODIFICACION = SYSTIMESTAMP WHERE ID_REGLA_NEGOCIO=?";

	/** Constate cs_FIND_SECUENCIA. */
	private static final String cs_FIND_SECUENCIA = "SELECT SEC_PGN_REGLA_NEGOCIO_ID_REGLA_NEGOCIO.NEXTVAL FROM DUAL";

	/**
	 * Find all.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<ReglaNegocio> findAll()
	    throws B2BException
	{
		Collection<ReglaNegocio> lcrn_reglas;
		PreparedStatement        lps_ps;
		ResultSet                lrs_rs;

		lcrn_reglas     = null;
		lps_ps          = null;
		lrs_rs          = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL);

			lrs_rs          = lps_ps.executeQuery();
			lcrn_reglas     = new ArrayList<ReglaNegocio>(1);

			while(lrs_rs.next())
				lcrn_reglas.add(getObjectFromResultSet(lrs_rs));
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

		return lcrn_reglas;
	}

	/**
	 * Find by id.
	 *
	 * @param as_idRegla de as id regla
	 * @return el valor de regla negocio
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public ReglaNegocio findById(String as_idRegla)
	    throws B2BException
	{
		ReglaNegocio      lrn_regla;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lrn_regla     = null;
		lps_ps        = null;
		lrs_rs        = null;

		try
		{
			if(StringUtils.isValidString(as_idRegla))
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(1, as_idRegla);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lrn_regla = getObjectFromResultSet(lrs_rs);
			}
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

		return lrn_regla;
	}

	/**
	 * Inserta un registro de regla de negocio con la información suministrada.
	 *
	 * @param arn_regla objeto de tipo <code>ReglaNegocio</code> contenedor de la información a insertar.
	 * @throws B2BException Objeto de tipo <code>B2BException</code>, se produce cuando se encuentra algun error controlado.
	 */
	public void insert(ReglaNegocio arn_regla)
	    throws B2BException
	{
		if(arn_regla != null)
		{
			int               li_column;
			PreparedStatement lps_ps;
			PreparedStatement lps_secuencia;
			ResultSet         lrs_rs;

			lps_ps            = null;
			lrs_rs            = null;
			lps_secuencia     = null;
			li_column         = 1;

			try
			{
				Connection lc_conexion;

				lc_conexion     = getConnection();

				lps_ps            = lc_conexion.prepareStatement(cs_INSERT);
				lps_secuencia     = lc_conexion.prepareStatement(cs_FIND_SECUENCIA);

				lrs_rs = lps_secuencia.executeQuery();

				if(lrs_rs.next())
					lps_ps.setString(li_column++, StringUtils.getString(lrs_rs.getInt(1)));
				else
					throw new B2BException(ErrorKeys.ERROR_GENERANDO_SECUENCIA);

				lps_ps.setString(li_column++, arn_regla.getNombre());
				lps_ps.setString(li_column++, arn_regla.getDescripcion());
				lps_ps.setString(li_column++, arn_regla.getNombreProcedimiento());
				lps_ps.setString(li_column++, arn_regla.getIdTopologiaRegla());
				lps_ps.setDate(li_column++, DateUtils.getSQLDate(arn_regla.getFechaDesde()));
				lps_ps.setDate(li_column++, DateUtils.getSQLDate(arn_regla.getFechaHasta()));
				lps_ps.setString(li_column++, arn_regla.getTipoRegla());
				lps_ps.setString(li_column++, arn_regla.getTextoValidacion());
				lps_ps.setString(li_column++, arn_regla.getActivo());
				lps_ps.setString(li_column++, arn_regla.getIdUsuarioCreacion());
				lps_ps.setString(li_column++, arn_regla.getIpCreacion());

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
				close(lrs_rs);
				close(lps_secuencia);
			}
		}
	}

	/**
	 * Actualiza un registro de regla negocio con la información suministrada.
	 *
	 * @param arn_regla objeto de tipo <code>ReglaNegocio</code> contenedor de la información a insertar.
	 * @throws B2BException Objeto de tipo <code>B2BException</code>, se produce cuando se encuentra algun error controlado.
	 */
	public void update(ReglaNegocio arn_regla)
	    throws B2BException
	{
		if(arn_regla != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int li_column;
				li_column     = 1;

				lps_ps = getConnection().prepareStatement(cs_UPDATE);

				lps_ps.setString(li_column++, arn_regla.getNombre());
				lps_ps.setString(li_column++, arn_regla.getDescripcion());
				lps_ps.setString(li_column++, arn_regla.getNombreProcedimiento());
				lps_ps.setString(li_column++, arn_regla.getIdTopologiaRegla());
				lps_ps.setDate(li_column++, DateUtils.getSQLDate(arn_regla.getFechaDesde()));
				lps_ps.setDate(li_column++, DateUtils.getSQLDate(arn_regla.getFechaHasta()));
				lps_ps.setString(li_column++, arn_regla.getTipoRegla());
				lps_ps.setString(li_column++, arn_regla.getTextoValidacion());
				lps_ps.setString(li_column++, arn_regla.getActivo());
				lps_ps.setString(li_column++, arn_regla.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, arn_regla.getIpModificacion());
				lps_ps.setString(li_column++, arn_regla.getIdReglaNegocio());

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
	 * Retorna Objeto o variable de valor object from result set.
	 *
	 * @param ars_rs de ars rs
	 * @return el valor de object from result set
	 * @throws SQLException cuando se produce algun error en el proceso
	 */
	private ReglaNegocio getObjectFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		ReglaNegocio lrn_regla;

		lrn_regla = new ReglaNegocio();

		lrn_regla.setIdReglaNegocio(ars_rs.getString("ID_REGLA_NEGOCIO"));
		lrn_regla.setNombre(ars_rs.getString("NOMBRE"));
		lrn_regla.setDescripcion(ars_rs.getString("DESCRIPCION"));
		lrn_regla.setNombreProcedimiento(ars_rs.getString("NOMBRE_PROCEDIMIENTO"));
		lrn_regla.setIdTopologiaRegla(ars_rs.getString("ID_TOPOLOGIA_REGLA"));
		lrn_regla.setFechaDesde(ars_rs.getTimestamp("FECHA_DESDE"));
		lrn_regla.setFechaHasta(ars_rs.getTimestamp("FECHA_HASTA"));
		lrn_regla.setTipoRegla(ars_rs.getString("TIPO_REGLA"));
		lrn_regla.setTextoValidacion(ars_rs.getString("TEXTO_VALIDACION"));
		lrn_regla.setActivo(ars_rs.getString("ACTIVO"));
		lrn_regla.setNombreTopologia(ars_rs.getString("NOMBRE_TOPOLOGIA"));

		fillAuditoria(ars_rs, lrn_regla);

		return lrn_regla;
	}
}
