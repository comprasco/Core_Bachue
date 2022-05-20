package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;

import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudCamposCorreccion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.HashMap;
import java.util.Map;


/**
 * Clase para el manejo de datos para la tabla SDB_ACC_SOLICITUD_CAMPOS_CORRECCION
 * @author Gabriel Arias
 *
 */
public class SolicitudCamposCorreccionDAO extends BaseDAO
{
	private static final String cs_UPDATE                       = "UPDATE SDB_ACC_SOLICITUD_CAMPOS_CORRECCION SET ID_SOLICITUD_CAMPOS_CORRECCION=?,ID_SOLICITUD_CORRECCION=?,ID_SOLICITUD=?,"
		+ "ID_CIRCULO=?,ID_MATRICULA=?,ID_CAMPOS_CORRECION=?,ID_CAUSAL_CORRECCION=?,ID_TURNO_HISTORIA=?,ID_CIRCULO_RELACIONADO=?,ID_MATRICULA_RELACIONADO=?,ID_ANOTACION_RELACIONADO=?,"
		+ "ID_PERSONA=?,ROL_PERSONA=?,ID_DIRECCION=?,ID_DETALLE_ANT_SISTEMA=?,CANTIDAD_MATRICULAS_APERTURAR=?,ID_USUARIO_MODIFICACION=?,FECHA_MODIFICACION=?,IP_MODIFICACION=? WHERE ID_SOLICITUD = ? AND ID_CIRCULO = ? AND ID_MATRICULA = ?";
	private static final String cs_DELETE                       = "DELETE FROM SDB_ACC_SOLICITUD_CAMPOS_CORRECCION WHERE ID_SOLICITUD = ? AND ID_CIRCULO = ? AND ID_MATRICULA = ?";
	private static final String cs_INSERT                       = "INSERT INTO SDB_ACC_SOLICITUD_CAMPOS_CORRECCION(ID_SOLICITUD_CAMPOS_CORRECCION,ID_SOLICITUD_CORRECCION,ID_SOLICITUD,"
		+ "ID_CIRCULO,ID_MATRICULA,ID_CAMPOS_CORRECION,ID_CAUSAL_CORRECCION,ID_TURNO_HISTORIA,ID_CIRCULO_RELACIONADO,ID_MATRICULA_RELACIONADO,ID_ANOTACION_RELACIONADO,ID_PERSONA,"
		+ "ROL_PERSONA,ID_DIRECCION,ID_DETALLE_ANT_SISTEMA,CANTIDAD_MATRICULAS_APERTURAR,ID_USUARIO_CREACION,FECHA_CREACION,IP_CREACION)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,SYSTIMESTAMP,?)";
	private static final String cs_FIND_ALL_BY_ID_CAUSAL        = "SELECT * FROM SDB_ACC_SOLICITUD_CAMPOS_CORRECCION WHERE ID_SOLICITUD = ? AND ID_CIRCULO = ? "
		+ "AND ID_MATRICULA = ? AND ID_CAUSAL_CORRECCION = ?";
	private static final String cs_FIND_ALL_BY_ID_CAUSAL_CAMPOS = "SELECT * FROM SDB_ACC_SOLICITUD_CAMPOS_CORRECCION WHERE ID_SOLICITUD = ? AND ID_CIRCULO = ? "
		+ "AND ID_MATRICULA = ? AND ID_CAUSAL_CORRECCION = ? AND ID_CAMPOS_CORRECION = ? ";
	private static final String cs_FIND_SECUENCE                = "  SELECT SEC_ACC_SOLICITUD_CAMPOS_CORRECCION_ID_SOLICITUD_CAMPOS_CORRECCION.NEXTVAL FROM DUAL";

	/**
	 * Método encargado de eliminar los registros para la llave id_solicitud, id_circulo y id_matricula
	 *
	 * @param ascc_param Objeto de tipo <code>SolicitudCamposCorreccion</code> que contiene campos para ejecutar la sentencia
	 * @throws B2BException
	 */
	public void delete(SolicitudCamposCorreccion ascc_param)
	    throws B2BException
	{
		PreparedStatement lps_ps;

		lps_ps = null;

		if(ascc_param != null)
		{
			try
			{
				int li_columns;

				li_columns     = 1;
				lps_ps         = getConnection().prepareStatement(cs_DELETE);

				lps_ps.setString(li_columns++, ascc_param.getIdSolicitud());
				lps_ps.setString(li_columns++, ascc_param.getIdCirculo());
				setLong(lps_ps, ascc_param.getIdMatricula(), li_columns++);

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "delete", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Método encargado de consultar con base al id causal.
	 *
	 * @param ascc_arg Objeto que contiene los datos para realizar la consulta.
	 * @return Objeto consultado.
	 * @throws B2BException
	 */
	public SolicitudCamposCorreccion findByIdCausal(SolicitudCamposCorreccion ascc_arg)
	    throws B2BException
	{
		SolicitudCamposCorreccion lscc_return;
		PreparedStatement         lps_ps;
		ResultSet                 lrs_rs;

		lscc_return     = null;
		lps_ps          = null;
		lrs_rs          = null;

		try
		{
			if(ascc_arg != null)
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_FIND_ALL_BY_ID_CAUSAL);

				lps_ps.setString(li_column++, ascc_arg.getIdSolicitud());
				lps_ps.setString(li_column++, ascc_arg.getIdCirculo());
				setLong(lps_ps, ascc_arg.getIdMatricula(), li_column++);
				setLong(lps_ps, ascc_arg.getIdCausalCorreccion(), li_column++);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lscc_return = getObjetFromResultSet(lrs_rs);
			}
		}
		catch(SQLException lse_e)
		{
			logError(this, "findByIdCausal", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return lscc_return;
	}

	/**
	 * Método encargado de consultar todos los registros para la llave id_solicitud, id_circulo, id_matricula y id_causal_correccion
	 *
	 * @param ascc_arg Objeto de tipo <code>SolicitudCamposCorreccion</code> que contiene campos para ejecutar la sentencia
	 * @param ab_anotacion Variable de tipo <code>boolean</code> que agrega parametro de consulta ID_ANOTACION_RELACIONADO
	 * @param ab_interviniente Variable de tipo <code>boolean</code> que agrega parametro de consulta <code>ID_PERSONA</code> y <code>ROL_PERSONA</code>
	 * @return
	 * @throws B2BException
	 */
	public Map<String, SolicitudCamposCorreccion> findMapAllByIdCausal(
	    SolicitudCamposCorreccion ascc_arg, boolean ab_anotacion, boolean ab_interviniente
	)
	    throws B2BException
	{
		Map<String, SolicitudCamposCorreccion> lmsscc_return;
		PreparedStatement                      lps_ps;
		ResultSet                              lrs_rs;

		lmsscc_return     = new HashMap<String, SolicitudCamposCorreccion>();
		lps_ps            = null;
		lrs_rs            = null;

		try
		{
			if(ascc_arg != null)
			{
				StringBuilder lsb_sb;
				int           li_column;

				li_column     = 1;
				lsb_sb        = new StringBuilder(cs_FIND_ALL_BY_ID_CAUSAL);

				if(ab_anotacion)
					lsb_sb.append(" AND ID_ANOTACION_RELACIONADO = ? ");

				if(ab_interviniente)
					lsb_sb.append(" AND ID_PERSONA = ? AND ROL_PERSONA = ? ");

				lps_ps = getConnection().prepareStatement(lsb_sb.toString());

				lps_ps.setString(li_column++, ascc_arg.getIdSolicitud());
				lps_ps.setString(li_column++, ascc_arg.getIdCirculo());
				setLong(lps_ps, ascc_arg.getIdMatricula(), li_column++);
				setLong(lps_ps, ascc_arg.getIdCausalCorreccion(), li_column++);

				if(ab_anotacion)
					lps_ps.setString(li_column++, ascc_arg.getIdAnotacionRelacionado());

				if(ab_interviniente)
				{
					lps_ps.setString(li_column++, ascc_arg.getIdPersona());
					lps_ps.setString(li_column++, ascc_arg.getRolPersona());
				}

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
				{
					SolicitudCamposCorreccion lscc_data;

					lscc_data = getObjetFromResultSet(lrs_rs);

					if(lscc_data != null)
					{
						String las_key;

						las_key = lscc_data.getIdCamposCorreccion();

						if(StringUtils.isValidString(las_key))
							lmsscc_return.put(las_key, lscc_data);
					}
				}
			}
		}
		catch(SQLException lse_e)
		{
			logError(this, "findMapAllByIdCausal", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(lmsscc_return.isEmpty())
			lmsscc_return = null;

		return lmsscc_return;
	}

	/**
	 * Método encargado de consultar todos los registros para la llave id_solicitud, id_circulo, id_matricula, id_causal_correccion y Id_Campos_Correccion
	 *
	 * @param ascc_arg Objeto de tipo <code>SolicitudCamposCorreccion</code> que contiene campos para ejecutar la sentencia
	 * @param ab_anotacion Variable de tipo <code>boolean</code> que agrega parametro de consulta ID_ANOTACION_RELACIONADO
	 * @param ab_keyDireccion Variable de tipo <code>boolean</code> que parametriza llave id_direccion del mapa a devolver
	 * @param ab_keyDetalleAntSistema Variable de tipo <code>boolean</code> que parametriza llave id_detalle_ant_sistema del mapa a devolver
	 * @return Objeto de tipo  <code>Map<String, SolicitudCamposCorreccion></code> con llave parametrica  (id_direccion,id_detalle_ant_sistema,id_circulo-id_matricula[defecto])
	 * @throws B2BException
	 */
	public Map<String, SolicitudCamposCorreccion> findMapAllByIdCausalCirculoMatricula(
	    SolicitudCamposCorreccion ascc_arg, boolean ab_anotacion, boolean ab_keyDireccion,
	    boolean                   ab_keyDetalleAntSistema
	)
	    throws B2BException
	{
		return findMapAllByIdCausalCirculoMatricula(
		    ascc_arg, ab_anotacion, ab_keyDireccion, ab_keyDetalleAntSistema, false
		);
	}

	/**
	 * Método encargado de consultar todos los registros para la llave id_solicitud, id_circulo, id_matricula, id_causal_correccion y Id_Campos_Correccion
	 *
	 * @param ascc_arg Objeto de tipo <code>SolicitudCamposCorreccion</code> que contiene campos para ejecutar la sentencia
	 * @param ab_anotacion Variable de tipo <code>boolean</code> que agrega parametro de consulta ID_ANOTACION_RELACIONADO
	 * @param ab_keyDireccion Variable de tipo <code>boolean</code> que parametriza llave id_direccion del mapa a devolver
	 * @param ab_keyDetalleAntSistema Variable de tipo <code>boolean</code> que parametriza llave id_detalle_ant_sistema del mapa a devolver
	 * @param ab_segregacion Variable de tipo <code>boolean</code> que parametriza si son predios segregados o abiertos con base en
	 * @return Objeto de tipo  <code>Map<String, SolicitudCamposCorreccion></code> con llave parametrica  (id_direccion,id_detalle_ant_sistema,id_circulo-id_matricula[defecto])
	 * @throws B2BException
	 */
	public Map<String, SolicitudCamposCorreccion> findMapAllByIdCausalCirculoMatricula(
	    SolicitudCamposCorreccion ascc_arg, boolean ab_anotacion, boolean ab_keyDireccion,
	    boolean                   ab_keyDetalleAntSistema, boolean ab_segregacion
	)
	    throws B2BException
	{
		Map<String, SolicitudCamposCorreccion> lmsscc_return;
		PreparedStatement                      lps_ps;
		ResultSet                              lrs_rs;

		lmsscc_return     = new HashMap<String, SolicitudCamposCorreccion>();
		lps_ps            = null;
		lrs_rs            = null;

		try
		{
			if(ascc_arg != null)
			{
				int           li_column;
				StringBuilder lsb_sb;

				li_column     = 1;
				lsb_sb        = new StringBuilder(cs_FIND_ALL_BY_ID_CAUSAL_CAMPOS);

				if(ab_anotacion)
					lsb_sb.append(" AND ID_ANOTACION_RELACIONADO = ? ");

				lps_ps = getConnection().prepareStatement(lsb_sb.toString());

				lps_ps.setString(li_column++, ascc_arg.getIdSolicitud());
				lps_ps.setString(li_column++, ascc_arg.getIdCirculo());
				setLong(lps_ps, ascc_arg.getIdMatricula(), li_column++);
				setLong(lps_ps, ascc_arg.getIdCausalCorreccion(), li_column++);
				lps_ps.setString(li_column++, ascc_arg.getIdCamposCorreccion());

				if(ab_anotacion)
					lps_ps.setString(li_column++, ascc_arg.getIdAnotacionRelacionado());

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
				{
					SolicitudCamposCorreccion lscc_data;

					lscc_data = getObjetFromResultSet(lrs_rs);

					if(lscc_data != null)
					{
						String ls_key;

						if(ab_keyDireccion)
							ls_key = lscc_data.getIdDireccion();
						else if(ab_keyDetalleAntSistema)
							ls_key = lscc_data.getIdDetalleAntSistema();
						else if(ab_segregacion)
							ls_key = lscc_data.getIdCirculoRelacionado() + IdentificadoresCommon.SIMBOLO_GUION
								+ lscc_data.getIdMatriculaRelacionado();
						else
							ls_key = lscc_data.getIdCirculo() + IdentificadoresCommon.SIMBOLO_GUION
								+ lscc_data.getIdMatricula();

						if(StringUtils.isValidString(ls_key))
							lmsscc_return.put(ls_key, lscc_data);
					}
				}
			}
		}
		catch(SQLException lse_e)
		{
			logError(this, "findMapAllByIdCausalCirculoMatricula", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(lmsscc_return.isEmpty())
			lmsscc_return = null;

		return lmsscc_return;
	}

	/**
	 * Método encargado de insertar o actualizar registros para la llave Id_Solicitud_Campos_Correccion
	 *
	 * @param ascc_arg Objeto de tipo <code>SolicitudCamposCorreccion</code> que contiene campos para ejecutar la sentencia
	 * @param ab_query Variable de tipo <code>boolean</code> que parametriza si se inserta o se actualizar
	 * @throws B2BException
	 */
	public void insertOrUpdate(SolicitudCamposCorreccion ascc_param, boolean ab_query)
	    throws B2BException
	{
		if(ascc_param != null)
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

				if(ab_query)
				{
					lps_secuencia     = lc_connection.prepareStatement(cs_FIND_SECUENCE);
					lrs_rs            = lps_secuencia.executeQuery();

					if(lrs_rs.next())
						ascc_param.setIdSolicitudCamposCorreccion(StringUtils.getString(lrs_rs.getLong(1)));
				}

				lps_ps = lc_connection.prepareStatement(ab_query ? cs_INSERT : cs_UPDATE);

				lps_ps.setString(li_column++, ascc_param.getIdSolicitudCamposCorreccion());
				lps_ps.setString(li_column++, ascc_param.getIdSolicitudCorreccion());
				lps_ps.setString(li_column++, ascc_param.getIdSolicitud());
				lps_ps.setString(li_column++, ascc_param.getIdCirculo());
				setLong(lps_ps, ascc_param.getIdMatricula(), li_column++);
				lps_ps.setString(li_column++, ascc_param.getIdCamposCorreccion());
				setLong(lps_ps, ascc_param.getIdCausalCorreccion(), li_column++);
				setLong(lps_ps, ascc_param.getIdTurnoHistoria(), li_column++);
				lps_ps.setString(li_column++, ascc_param.getIdCirculoRelacionado());
				setLong(lps_ps, ascc_param.getIdMatriculaRelacionado(), li_column++);
				lps_ps.setString(li_column++, ascc_param.getIdAnotacionRelacionado());
				lps_ps.setString(li_column++, ascc_param.getIdPersona());
				lps_ps.setString(
				    li_column++,
				    StringUtils.isValidString(ascc_param.getRolPersona()) ? ascc_param.getRolPersona().trim() : null
				);
				lps_ps.setString(li_column++, ascc_param.getIdDireccion());
				lps_ps.setString(li_column++, ascc_param.getIdDetalleAntSistema());
				setLong(lps_ps, ascc_param.getCantidadAperturar(), li_column++);

				if(ab_query)
				{
					lps_ps.setString(li_column++, ascc_param.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, ascc_param.getIpCreacion());
				}
				else
				{
					lps_ps.setString(li_column++, ascc_param.getIdUsuarioModificacion());
					lps_ps.setString(li_column++, ascc_param.getIpModificacion());
					lps_ps.setString(li_column++, ascc_param.getIdSolicitud());
					lps_ps.setString(li_column++, ascc_param.getIdCirculo());
					setLong(lps_ps, ascc_param.getIdMatricula(), li_column++);
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
				close(lps_secuencia);
				close(lrs_rs);
			}
		}
	}

	/**
	 * Método encaragdo de obtener datos a partir de un ResultSet enviado
	 *
	 * @param ars_rs Objeto de tipo <code>ResultSet</code> que contiene campos a extraer de la sentencia realizada
	 * @return
	 * @throws SQLException
	 */
	private SolicitudCamposCorreccion getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		SolicitudCamposCorreccion lscc_return;

		lscc_return = new SolicitudCamposCorreccion();

		lscc_return.setIdSolicitudCamposCorreccion(ars_rs.getString("ID_SOLICITUD_CAMPOS_CORRECCION"));
		lscc_return.setIdSolicitudCorreccion(ars_rs.getString("ID_SOLICITUD_CORRECCION"));
		lscc_return.setIdSolicitud(ars_rs.getString("ID_SOLICITUD"));
		lscc_return.setIdCirculo(ars_rs.getString("ID_CIRCULO"));
		lscc_return.setIdMatricula(getLong(ars_rs, "ID_MATRICULA"));
		lscc_return.setIdCamposCorreccion(ars_rs.getString("ID_CAMPOS_CORRECION"));
		lscc_return.setIdCausalCorreccion(getLong(ars_rs, "ID_CAUSAL_CORRECCION"));
		lscc_return.setIdTurnoHistoria(getLong(ars_rs, "ID_TURNO_HISTORIA"));
		lscc_return.setIdCirculoRelacionado(ars_rs.getString("ID_CIRCULO_RELACIONADO"));
		lscc_return.setIdMatriculaRelacionado(getLong(ars_rs, "ID_MATRICULA_RELACIONADO"));
		lscc_return.setIdAnotacionRelacionado(ars_rs.getString("ID_ANOTACION_RELACIONADO"));
		lscc_return.setIdPersona(ars_rs.getString("ID_PERSONA"));
		lscc_return.setRolPersona(ars_rs.getString("ROL_PERSONA"));
		lscc_return.setIdDireccion(ars_rs.getString("ID_DIRECCION"));
		lscc_return.setIdDetalleAntSistema(ars_rs.getString("ID_DETALLE_ANT_SISTEMA"));
		lscc_return.setCantidadAperturar(getLong(ars_rs, "CANTIDAD_MATRICULAS_APERTURAR"));
		lscc_return.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		lscc_return.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		lscc_return.setIpCreacion(ars_rs.getString("IP_CREACION"));
		lscc_return.setIdUsuarioModificacion(ars_rs.getString("ID_USUARIO_MODIFICACION"));
		lscc_return.setFechaModificacion(ars_rs.getTimestamp("FECHA_MODIFICACION"));
		lscc_return.setIpModificacion(ars_rs.getString("IP_MODIFICACION"));

		return lscc_return;
	}
}
