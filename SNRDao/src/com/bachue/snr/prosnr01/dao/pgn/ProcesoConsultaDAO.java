package com.bachue.snr.prosnr01.dao.pgn;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr01.model.sdb.pgn.ProcesoConsulta;

import java.math.BigDecimal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase dedicada al manejo de conexion y transacciones en la base de datos que afecten
 * directamente a la tabla SDB_PGN_PROCESO_CONSULTA.
 *
 * @author Sebastian Sanchez
 */
public class ProcesoConsultaDAO extends AuditoriaDao
{
	/** Constante  cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_PGN_PROCESO_CONSULTA";

	/** Constante  cs_FIND_ALL_ACTIVO. */
	private static final String cs_FIND_ALL_ACTIVO = "SELECT ID_PROCESO_CONSULTA, ID_PROCESO, ID_SUBPROCESO, SENTENCIA_SQL, ACTIVO, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION, ID_USUARIO_MODIFICACION, FECHA_MODIFICACION, IP_MODIFICACION FROM SDB_PGN_PROCESO_CONSULTA WHERE ACTIVO = 'S' ORDER BY LENGTH(ID_PROCESO_CONSULTA), ID_PROCESO_CONSULTA ";

	/** Constante  cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = " SELECT * FROM SDB_PGN_PROCESO_CONSULTA WHERE ID_PROCESO_CONSULTA = ? ";

	/** Constante cs_FIND_BY_PROCESO_SUBPROCESO. */
	private static final String cs_FIND_BY_PROCESO_SUBPROCESO = "SELECT * FROM SDB_PGN_PROCESO_CONSULTA WHERE ID_PROCESO = ? AND ID_SUBPROCESO = ?";

	/** Constante  cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_PGN_PROCESO_CONSULTA(ID_PROCESO_CONSULTA, ID_PROCESO, ID_SUBPROCESO, SENTENCIA_SQL, ACTIVO, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION) VALUES(?, ?, ?, ?, ?, ?, SYSTIMESTAMP, ?)";

	/** Constante  cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_PGN_PROCESO_CONSULTA SET ID_PROCESO=?, ID_SUBPROCESO=?, SENTENCIA_SQL=?, ACTIVO=?, ID_USUARIO_MODIFICACION=?, IP_MODIFICACION=?, FECHA_MODIFICACION = SYSTIMESTAMP WHERE ID_PROCESO_CONSULTA=?";

	/** Constante  cs_FIND_SECUENCIA. */
	private static final String cs_FIND_SECUENCIA = "SELECT SEC_PGN_PROCESO_CONSULTA_ID_PROCESO_CONSULTA.NEXTVAL FROM DUAL";

	/**
	 * Consulta en base de datos todos los registros que se encuentren.
	 *
	 * @return Colección de Proceso Consulta resultante de la consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<ProcesoConsulta> findAll()
	    throws B2BException
	{
		Collection<ProcesoConsulta> lcpc_cpc;
		PreparedStatement           lps_ps;
		ResultSet                   lrs_rs;

		lcpc_cpc     = new ArrayList<ProcesoConsulta>();
		lps_ps       = null;
		lrs_rs       = null;

		try
		{
			StringBuilder lsb_consulta;

			lsb_consulta = new StringBuilder();

			lsb_consulta.append(cs_FIND_ALL);

			lps_ps     = getConnection().prepareStatement(lsb_consulta.toString());

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcpc_cpc.add(getObjetFromResultSet(lrs_rs));
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

		if(lcpc_cpc.isEmpty())
			lcpc_cpc = null;

		return lcpc_cpc;
	}

	/**
	 * Consulta en base de datos todos los registros que se encuentren activos.
	 *
	 * @return Colección de Proceso Consulta resultante de la consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<ProcesoConsulta> findAllActivo()
	    throws B2BException
	{
		Collection<ProcesoConsulta> lp_procesoConsulta;
		PreparedStatement           lps_ps;
		ResultSet                   lrs_rs;

		lp_procesoConsulta     = new ArrayList<ProcesoConsulta>();
		lps_ps                 = null;
		lrs_rs                 = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL_ACTIVO);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lp_procesoConsulta.add(getObjetFromResultSet(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findAllActivo", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(lp_procesoConsulta.isEmpty())
			lp_procesoConsulta = null;

		return lp_procesoConsulta;
	}

	/**
	 * Busca un registro de la tabla por medio de su id_identificador.
	 *
	 * @param apc_param Objeto contenedor de los filtros a usar en la consulta
	 * @return Registro resultante de la consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public ProcesoConsulta findById(ProcesoConsulta apc_param)
	    throws B2BException
	{
		ProcesoConsulta lcs_object;

		lcs_object = null;

		if(apc_param != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(1, apc_param.getIdProcesoConsulta());

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
	 * Find by proceso subproceso.
	 *
	 * @param as_proceso de as proceso
	 * @param as_subProceso de as sub proceso
	 * @return el valor de proceso consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public ProcesoConsulta findByProcesoSubproceso(String as_proceso, String as_subProceso)
	    throws B2BException
	{
		ProcesoConsulta lcs_object;

		lcs_object = null;

		if(StringUtils.isValidString(as_proceso) && StringUtils.isValidString(as_subProceso))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_PROCESO_SUBPROCESO);

				lps_ps.setString(1, as_proceso);
				lps_ps.setString(2, as_subProceso);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lcs_object = getObjetFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByProcesoSubproceso", lse_e);

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
	 * con la información del proceso consulta suministrada.
	 *
	 * @param apt_parametros de apt parametros
	 * @param ab_query define el proceso seleccionado, true para insertar un nuevo
	 * registro, false para actualizar un registro existente.
	 * @throws B2BException     public void insertOrUpdate(ProcesoConsulta as_param, boolean ab_query)
	 *         throws B2BException
	 *     {
	 *         if(as_param != null)
	 *         {
	 *             int               li_column;
	 *             PreparedStatement lps_ps;
	 *             PreparedStatement lps_secuencia;
	 *             ResultSet         lrs_rs;
	 *
	 *             lps_ps            = null;
	 *             lps_secuencia     = null;
	 *             lrs_rs            = null;
	 *             li_column         = 1;
	 *
	 *             try
	 *             {
	 *                 Connection lc_C;
	 *
	 *                 lc_C       = getConnection();
	 *                 lps_ps     = lc_C.prepareStatement(ab_query ? cs_INSERT : cs_UPDATE);
	 *
	 *                 if(ab_query)
	 *                 {
	 *                     lps_secuencia     = lc_C.prepareStatement(cs_FIND_SECUENCIA);
	 *
	 *                     lrs_rs = lps_secuencia.executeQuery();
	 *
	 *                     if(lrs_rs.next())
	 *                         lps_ps.setString(li_column++, StringUtils.getString(lrs_rs.getInt(1)));
	 *                     else
	 *                         throw new B2BException(ErrorKeys.ERROR_GENERANDO_SECUENCIA);
	 *                 }
	 *
	 *                 if(ab_query)
	 *                 {
	 *                     lps_ps.setString(li_column++, as_param.getIdProceso());
	 *                     lps_ps.setString(li_column++, as_param.getIdSubproceso());
	 *                     lps_ps.setString(li_column++, as_param.getSentenciaSql());
	 *                     lps_ps.setString(li_column++, as_param.getActivo());
	 *                     lps_ps.setString(li_column++, as_param.getIdUsuarioCreacion());
	 *                     lps_ps.setString(li_column++, as_param.getIpCreacion());
	 *                 }
	 *                 else
	 *                 {
	 *                     lps_ps.setString(li_column++, as_param.getSentenciaSql());
	 *                     lps_ps.setString(li_column++, as_param.getActivo());
	 *                     lps_ps.setString(li_column++, as_param.getIdUsuarioModificacion());
	 *                     lps_ps.setString(li_column++, as_param.getIpModificacion());
	 *                     lps_ps.setString(li_column++, as_param.getIdProcesoConsulta());
	 *                     lps_ps.setString(li_column++, as_param.getIdProceso());
	 *                     lps_ps.setString(li_column++, as_param.getIdSubproceso());
	 *                 }
	 *
	 *                 lps_ps.executeUpdate();
	 *             }
	 *             catch(SQLException lse_e)
	 *             {
	 *                 logError(this, "insertOrUpdate", lse_e);
	 *
	 *                 throw new B2BException(SQL_ERROR, lse_e);
	 *             }
	 *             finally
	 *             {
	 *                 close(lps_ps);
	 *             }
	 *         }
	 *     }
	 */
	public void insertOrUpdate(ProcesoConsulta apt_parametros, boolean ab_query)
	    throws B2BException
	{
		if(apt_parametros != null)
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
					lps_secuencia     = lc_connection.prepareStatement(cs_FIND_SECUENCIA);

					lrs_rs = lps_secuencia.executeQuery();

					if(lrs_rs.next())
					{
						Object lo_o;
						lo_o = lrs_rs.getObject(1);

						if((lo_o != null) && lo_o instanceof BigDecimal)
							apt_parametros.setIdProcesoConsulta(lo_o.toString());
						else
							throw new B2BException(ErrorKeys.PGN_LINEA_PRODUCCION_SEQUENCE);
					}

					lps_ps.setString(li_column++, apt_parametros.getIdProcesoConsulta());
				}

				lps_ps.setString(li_column++, apt_parametros.getIdProceso());
				lps_ps.setString(li_column++, apt_parametros.getIdSubproceso());
				lps_ps.setString(li_column++, apt_parametros.getSentenciaSql());
				lps_ps.setString(li_column++, apt_parametros.getActivo());

				if(ab_query)
				{
					lps_ps.setString(li_column++, apt_parametros.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, apt_parametros.getIpCreacion());
				}
				else
				{
					lps_ps.setString(li_column++, apt_parametros.getIdUsuarioModificacion());
					lps_ps.setString(li_column++, apt_parametros.getIpModificacion());
					lps_ps.setString(li_column++, apt_parametros.getIdProcesoConsulta());
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
	 * Permite almacenar los datos que retornó la consulta en los atributos
	 * de un objeto.
	 *
	 * @param lrs_rs contenedor del resultado de a consulta
	 * @return objeto contenedor de los datos que retornó la consulta
	 * @throws SQLException cuando se produce algun error en el proceso
	 */
	private ProcesoConsulta getObjetFromResultSet(ResultSet lrs_rs)
	    throws SQLException
	{
		ProcesoConsulta ls_solicitud;

		ls_solicitud = new ProcesoConsulta();

		ls_solicitud.setIdProcesoConsulta(lrs_rs.getString("ID_PROCESO_CONSULTA"));
		ls_solicitud.setIdProceso(lrs_rs.getString("ID_PROCESO"));
		ls_solicitud.setIdSubproceso(lrs_rs.getString("ID_SUBPROCESO"));
		ls_solicitud.setSentenciaSql(lrs_rs.getString("SENTENCIA_SQL"));
		ls_solicitud.setActivo(lrs_rs.getString("ACTIVO"));

		fillAuditoria(lrs_rs, ls_solicitud);

		return ls_solicitud;
	}
}
