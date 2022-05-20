package com.bachue.snr.prosnr01.dao.pgn;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr01.model.sdb.pgn.CausalReimpresion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase dedicada al manejo de conexion y transacciones en la base de datos que afecten
 * directamente a la tabla SDB_PGN_CAUSAL_REIMPRESION
 *
 * @author Sebastian Sanchez
 *
 */
public class CausalReimpresionDAO extends AuditoriaDao
{
	/** Constante  cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT ID_CAUSAL_REIMPRESION, DESCRIPCION, GRUPO_CAUSAL, ACTIVO, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION, ID_USUARIO_MODIFICACION, FECHA_MODIFICACION, IP_MODIFICACION FROM SDB_PGN_CAUSAL_REIMPRESION ";

	/** Constante  cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = " SELECT * FROM SDB_PGN_CAUSAL_REIMPRESION WHERE ID_CAUSAL_REIMPRESION = ?";

	/** Constante  cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_PGN_CAUSAL_REIMPRESION(ID_CAUSAL_REIMPRESION, DESCRIPCION, GRUPO_CAUSAL, ACTIVO, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION) VALUES(?, ?, ?, ?, ?, SYSTIMESTAMP, ?)";

	/** Constante  cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_PGN_CAUSAL_REIMPRESION SET DESCRIPCION=?, GRUPO_CAUSAL=?, ACTIVO=?, ID_USUARIO_MODIFICACION=?,IP_MODIFICACION=?,FECHA_MODIFICACION = SYSTIMESTAMP WHERE ID_CAUSAL_REIMPRESION=?";

	/** Constante  cs_FIND_SECUENCIA. */
	private static final String cs_FIND_SECUENCIA = "SELECT SEC_PGN_CAUSAL_REIMPRESION_ID_CAUSAL_REIMPRESION.NEXTVAL FROM DUAL";

	/**
	 * Consulta en base de datos todos los registros que se encuentren
	 * @return Colección de Causal Reimpresion resultante de la consulta
	 * @throws B2BException
	 */
	public Collection<CausalReimpresion> findAll()
	    throws B2BException
	{
		return findAll(false);
	}

	/**
	 * Consulta en base de datos todos los registros que se encuentren
	 * @return Colección de Causal Reimpresion resultante de la consulta
	 * @throws B2BException
	 */
	public Collection<CausalReimpresion> findAll(boolean ab_activo)
	    throws B2BException
	{
		Collection<CausalReimpresion> lccr_ccr;
		PreparedStatement             lps_ps;
		ResultSet                     lrs_rs;

		lccr_ccr     = new ArrayList<CausalReimpresion>();
		lps_ps       = null;
		lrs_rs       = null;

		try
		{
			StringBuilder lsb_consulta;

			lsb_consulta = new StringBuilder();

			lsb_consulta.append(cs_FIND_ALL);

			if(ab_activo)
				lsb_consulta.append(" WHERE ACTIVO = 'S' ");

			lps_ps     = getConnection().prepareStatement(lsb_consulta.toString());

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lccr_ccr.add(getObjetFromResultSet(lrs_rs));
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

		if(lccr_ccr.isEmpty())
			lccr_ccr = null;

		return lccr_ccr;
	}

	/**
	 * Busca un registro de la tabla por medio de su id_identificador
	 *
	 * @param acr_param Objeto contenedor de los filtros a usar en la consulta
	 * @return Registro resultante de la consulta
	 * @throws B2BException
	 */
	public CausalReimpresion findById(CausalReimpresion acr_param)
	    throws B2BException
	{
		CausalReimpresion lcs_object;

		lcs_object = null;

		if(acr_param != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(1, acr_param.getIdTipoCausal());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lcs_object = getObjetFromResultSet(lrs_rs);
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

		return lcs_object;
	}

	/**
	 * Dependiendo del procedimiento seleccionado, inserta o actualiza un registro
	 * con la información del causal reimpresion suministrada.
	 *
	 * @param as_param objeto contenedor de la información a actualizar o insertar
	 * @param ab_query define el proceso seleccionado, true para insertar un nuevo
	 * registro, false para actualizar un registro existente.
	 * @throws B2BException
	 */
	public void insertOrUpdate(CausalReimpresion as_param, boolean ab_query)
	    throws B2BException
	{
		if(as_param != null)
		{
			int               li_column;
			PreparedStatement lps_ps;
			PreparedStatement lps_secuencia;
			ResultSet         lrs_rs;

			lps_ps            = null;
			lps_secuencia     = null;
			lrs_rs            = null;
			li_column         = 1;

			try
			{
				Connection lc_c;

				lc_c       = getConnection();
				lps_ps     = lc_c.prepareStatement(ab_query ? cs_INSERT : cs_UPDATE);

				if(ab_query)
				{
					lps_secuencia     = lc_c.prepareStatement(cs_FIND_SECUENCIA);

					lrs_rs = lps_secuencia.executeQuery();

					if(lrs_rs.next())
						lps_ps.setString(li_column++, StringUtils.getString(lrs_rs.getInt(1)));
					else
						throw new B2BException(ErrorKeys.ERROR_GENERANDO_SECUENCIA);
				}

				if(ab_query)
				{
					lps_ps.setString(li_column++, as_param.getDescripcion());
					lps_ps.setString(li_column++, as_param.getGrupoCausal());
					lps_ps.setString(li_column++, as_param.getActivo());
					lps_ps.setString(li_column++, as_param.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, as_param.getIpCreacion());
				}
				else
				{
					lps_ps.setString(li_column++, as_param.getDescripcion());
					lps_ps.setString(li_column++, as_param.getGrupoCausal());
					lps_ps.setString(li_column++, as_param.getActivo());
					lps_ps.setString(li_column++, as_param.getIdUsuarioModificacion());
					lps_ps.setString(li_column++, as_param.getIpModificacion());
					lps_ps.setString(li_column++, as_param.getIdTipoCausal());
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
				close(lrs_rs);
				close(lps_secuencia);
				close(lps_ps);
			}
		}
	}

	/**
	 * Permite almacenar los datos que retornó la consulta en los atributos
	 * de un objeto.
	 *
	 * @param lrs_rs contenedor del resultado de a consulta
	 * @return objeto contenedor de los datos que retornó la consulta
	 * @throws SQLException
	 */
	private CausalReimpresion getObjetFromResultSet(ResultSet lrs_rs)
	    throws SQLException
	{
		CausalReimpresion ls_solicitud;

		ls_solicitud = new CausalReimpresion();

		ls_solicitud.setIdTipoCausal(lrs_rs.getString("ID_CAUSAL_REIMPRESION"));
		ls_solicitud.setDescripcion(lrs_rs.getString("DESCRIPCION"));
		ls_solicitud.setGrupoCausal(lrs_rs.getString("GRUPO_CAUSAL"));
		ls_solicitud.setActivo(lrs_rs.getString("ACTIVO"));
		ls_solicitud.setIdUsuarioCreacion(lrs_rs.getString("ID_USUARIO_CREACION"));
		ls_solicitud.setFechaCreacion(lrs_rs.getTimestamp("FECHA_CREACION"));
		ls_solicitud.setIpCreacion(lrs_rs.getString("IP_CREACION"));
		ls_solicitud.setIdUsuarioModificacion(lrs_rs.getString("ID_USUARIO_MODIFICACION"));
		ls_solicitud.setFechaModificacion(lrs_rs.getTimestamp("FECHA_MODIFICACION"));
		ls_solicitud.setIpModificacion(lrs_rs.getString("IP_MODIFICACION"));

		return ls_solicitud;
	}
}
