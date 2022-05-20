package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.CalidadSolicitanteCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr01.model.sdb.acc.CalidadSolicitante;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase dedicada al manejo de conexion y transacciones en la base de datos que afecten
 * directamente a la tabla SDB_ACC_CALIDAD_SOLICITANTE.
 *
 * @author Nicolas Guaneme
 */
public class CalidadSolicitanteDAO extends AuditoriaDao
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT ID_CALIDAD_SOLICITANTE, NOMBRE, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION, ID_USUARIO_MODIFICACION, FECHA_MODIFICACION, IP_MODIFICACION, ACTIVO"
		+ " FROM SDB_ACC_CALIDAD_SOLICITANTE WHERE ID_CALIDAD_SOLICITANTE = ?";

	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT ID_CALIDAD_SOLICITANTE, NOMBRE, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION, ID_USUARIO_MODIFICACION, FECHA_MODIFICACION, IP_MODIFICACION, ACTIVO"
		+ " FROM SDB_ACC_CALIDAD_SOLICITANTE WHERE ACTIVO = 'S'";

	/** Constante cs_FIND_TRASLADO. */
	private static final String cs_FIND_TRASLADO = "SELECT ID_CALIDAD_SOLICITANTE, NOMBRE, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION, ID_USUARIO_MODIFICACION, FECHA_MODIFICACION, IP_MODIFICACION, ACTIVO"
		+ " FROM SDB_ACC_CALIDAD_SOLICITANTE WHERE ID_CALIDAD_SOLICITANTE IN ('2','4')";

	/** Constante cs_FIND_ALL_SIN_FUNCIONARIO. */
	private static final String cs_FIND_ALL_SIN_FUNCIONARIO = "SELECT ID_CALIDAD_SOLICITANTE, NOMBRE, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION, ID_USUARIO_MODIFICACION, FECHA_MODIFICACION, IP_MODIFICACION, ACTIVO"
		+ " FROM SDB_ACC_CALIDAD_SOLICITANTE WHERE ID_CALIDAD_SOLICITANTE <> 2 AND ACTIVO = 'S'";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_ACC_CALIDAD_SOLICITANTE SET NOMBRE=?, ID_USUARIO_MODIFICACION=?, IP_MODIFICACION=?, FECHA_MODIFICACION = SYSTIMESTAMP, ACTIVO=? WHERE ID_CALIDAD_SOLICITANTE=?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_ACC_CALIDAD_SOLICITANTE(ID_CALIDAD_SOLICITANTE, NOMBRE, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION, ACTIVO) VALUES(?, ?, ?, SYSTIMESTAMP, ?, ?)";

	/** Constante cs_FIND_SECUENCIA. */
	private static final String cs_FIND_SECUENCIA = "SELECT SEC_ACC_CALIDAD_SOLICITANTE_ID_CALIDAD_SOLICITANTE.NEXTVAL FROM DUAL";

	/**
	 * Consulta en base de datos todos los registros de la tabla.
	 *
	 * @param ab_bool correspondiente al valor del tipo de objeto boolean
	 * @return Colección de calidad solicitante con los resultados de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public Collection<CalidadSolicitante> findAll(boolean ab_bool)
	    throws B2BException
	{
		Collection<CalidadSolicitante> lccs_object;
		PreparedStatement              lps_ps;
		ResultSet                      lrs_rs;

		lccs_object     = new ArrayList<CalidadSolicitante>();
		lps_ps          = null;
		lrs_rs          = null;

		try
		{
			if(ab_bool)
				lps_ps = getConnection().prepareStatement(cs_FIND_ALL);
			else
				lps_ps = getConnection().prepareStatement(cs_FIND_ALL_SIN_FUNCIONARIO);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lccs_object.add(getObjetFromResultSet(lrs_rs));
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

		if(lccs_object.isEmpty())
			lccs_object = null;

		return lccs_object;
	}

	/**
	 * Busca los tipos de calidad solicitante que esten definidos y habilitados para las etapas
	 * de entrega.
	 *
	 * @param asa_filtros Arreglo de cadenas de caracteres que sirven como definición de los
	 * filtros para la busqueda
	 * @return Colección con los registros resultantes de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public Collection<CalidadSolicitante> findAllEntrega(String[] asa_filtros)
	    throws B2BException
	{
		Collection<CalidadSolicitante> lccs_object;

		lccs_object = new ArrayList<CalidadSolicitante>();

		if(asa_filtros != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				StringBuilder lsb_consulta;
				int           li_length;

				lsb_consulta     = new StringBuilder();
				li_length        = asa_filtros.length;

				lsb_consulta.append(cs_FIND_ALL);
				lsb_consulta.append(" AND ");

				for(int li_i = 0; li_i < li_length; li_i++)
				{
					lsb_consulta.append(" ID_CALIDAD_SOLICITANTE != ? ");

					if((li_i + 1) != li_length)
						lsb_consulta.append(" AND ");
				}

				lps_ps = getConnection().prepareStatement(lsb_consulta.toString());

				for(int li_i = 0; li_i < li_length; li_i++)
				{
					String ls_id;
					ls_id = asa_filtros[li_i];

					lps_ps.setString(li_i + 1, ls_id);
				}

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lccs_object.add(getObjetFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findAllEntrega", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lccs_object.isEmpty())
			lccs_object = null;

		return lccs_object;
	}

	/**
	 * Consulta en base de datos todos los registros de la tabla que contengan los valores enviados como argumento.
	 *
	 * @param acs_parametros Argumento de tipo <code>CalidadSolicitante</code> que contiene los criterios necesarios para realizar la consulta.
	 * @return Colección de tipo <code>CalidadSolicitante</code> con los resultados de la consulta.
	 * @throws B2BException Señala que se prodújo una excepción.
	 *
	 */
	public Collection<CalidadSolicitante> findAllInCalidadSolicitante(CalidadSolicitante acs_parametros)
	    throws B2BException
	{
		Collection<CalidadSolicitante> lccs_object;
		PreparedStatement              lps_ps;
		ResultSet                      lrs_rs;

		lccs_object     = new ArrayList<CalidadSolicitante>();
		lps_ps          = null;
		lrs_rs          = null;

		if(acs_parametros != null)
		{
			try
			{
				StringBuilder lsb_sb;
				String        ls_soloIncluir;

				lsb_sb             = new StringBuilder(cs_FIND_ALL);
				ls_soloIncluir     = acs_parametros.getSoloIncluir();

				if(
				    StringUtils.isValidString(ls_soloIncluir)
					    && ls_soloIncluir.equalsIgnoreCase(IdentificadoresCommon.INDICES)
				)
				{
					StringBuilder lsb_tmp;

					lsb_tmp     = new StringBuilder();

					ls_soloIncluir = lsb_tmp.toString();

					lsb_sb.append(" AND ID_CALIDAD_SOLICITANTE IN (");
					lsb_sb.append("'");
					lsb_sb.append(CalidadSolicitanteCommon.INTERESADO);
					lsb_sb.append("','");
					lsb_sb.append(CalidadSolicitanteCommon.INTERVINIENTE);
					lsb_sb.append("'");
					lsb_sb.append(")");
				}

				lps_ps     = getConnection().prepareStatement(lsb_sb.toString());

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lccs_object.add(getObjetFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findAllInCalidadSolicitante", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}

			if(lccs_object.isEmpty())
				lccs_object = null;
		}

		return lccs_object;
	}

	/**
	 * Busca un registro de la tabla por medio de su id_identificador.
	 *
	 * @param acs_param Objeto contenedor de los filtros a usar en la consulta
	 * @return Registro resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see CalidadSolicitante
	 */
	public CalidadSolicitante findById(CalidadSolicitante acs_param)
	    throws B2BException
	{
		return (acs_param != null) ? findById(acs_param.getIdCalidadSolicitante()) : null;
	}

	/**
	 * Busca un registro de la tabla por medio de su id_identificador.
	 *
	 * @param as_idCalidadSolicitante Objeto contenedor de los filtros a usar en la consulta
	 * @return Registro resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see CalidadSolicitante
	 */
	public CalidadSolicitante findById(String as_idCalidadSolicitante)
	    throws B2BException
	{
		CalidadSolicitante lcs_object;

		lcs_object = null;

		if(StringUtils.isValidString(as_idCalidadSolicitante))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(1, as_idCalidadSolicitante);

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
	 * Método encargado de consultar las calidades del solicitante para el proceso de traslado.
	 *
	 * @return Colección de calidad solicitante con los resultados de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<CalidadSolicitante> findTraslado()
	    throws B2BException
	{
		Collection<CalidadSolicitante> lccs_object;
		PreparedStatement              lps_ps;
		ResultSet                      lrs_rs;

		lccs_object     = new ArrayList<CalidadSolicitante>();
		lps_ps          = null;
		lrs_rs          = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_TRASLADO);
			lrs_rs     = lps_ps.executeQuery();

			while(lrs_rs.next())
				lccs_object.add(getObjetFromResultSet(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findTraslado", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(lccs_object.isEmpty())
			lccs_object = null;

		return lccs_object;
	}

	/**
	 * Dependiendo del procedimiento seleccionado, inserta o actualiza un registro
	 * con la información de la calidad solicitante suministrada.
	 *
	 * @param as_param objeto contenedor de la información a actualizar o insertar
	 * @param ab_query define el proceso seleccionado, true para insertar un nuevo
	 * registro, false para actualizar un registro existente.
	 * @throws B2BException
	 */
	public void insertOrUpdate(CalidadSolicitante as_param, boolean ab_query)
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
					lrs_rs            = lps_secuencia.executeQuery();

					if(lrs_rs.next())
						lps_ps.setString(li_column++, StringUtils.getString(lrs_rs.getInt(1)));
					else
						throw new B2BException(ErrorKeys.ERROR_GENERANDO_SECUENCIA);
				}

				if(ab_query)
				{
					lps_ps.setString(li_column++, as_param.getNombre());
					lps_ps.setString(li_column++, as_param.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, as_param.getIpCreacion());
					lps_ps.setString(li_column++, as_param.getActivo());
				}
				else
				{
					lps_ps.setString(li_column++, as_param.getNombre());
					lps_ps.setString(li_column++, as_param.getIdUsuarioModificacion());
					lps_ps.setString(li_column++, as_param.getIpModificacion());
					lps_ps.setString(li_column++, as_param.getActivo());
					lps_ps.setString(li_column++, as_param.getIdCalidadSolicitante());
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
				close(lps_ps);
				close(lps_secuencia);
			}
		}
	}

	/**
	 * Extrae la información recuperada de la base de datos y la asigna a un objeto CalidadSolicitante.
	 *
	 * @param ars_rs Objeto contenedor del resultado de la consulta
	 * @return CalidadSolicitante con la información extraida de la consulta
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private CalidadSolicitante getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		CalidadSolicitante lcs_data;

		lcs_data = new CalidadSolicitante();

		lcs_data.setIdCalidadSolicitante(ars_rs.getString("ID_CALIDAD_SOLICITANTE"));
		lcs_data.setNombre(ars_rs.getString("NOMBRE"));
		lcs_data.setActivo(ars_rs.getString("ACTIVO"));

		fillAuditoria(ars_rs, lcs_data);

		return lcs_data;
	}
}
