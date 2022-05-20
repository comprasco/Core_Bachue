package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudAsociada;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las consultas de SDB_ACC_SOLICITUD_ASOCIADA.
 *
 * @author lchacon
 */
public class SolicitudAsociadaDAO extends BaseDAO
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT ID_SOLICITUD, ID_SOLICITUD1, FECHA_CREACION FROM SDB_ACC_SOLICITUD_ASOCIADA WHERE ID_SOLICITUD=? AND ID_SOLICITUD1=?";

	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_MAX_BY_ID_SOLICITUD = "SELECT MAX(ID_SOLICITUD1) FROM SDB_ACC_SOLICITUD_ASOCIADA WHERE ID_SOLICITUD=?";

	/** Constante cs_FIND_BY_ID_IND_VINCULADO. */
	private static final String cs_FIND_BY_ID_IND_VINCULADO = "SELECT ID_SOLICITUD, ID_SOLICITUD1, FECHA_CREACION FROM SDB_ACC_SOLICITUD_ASOCIADA WHERE ID_SOLICITUD=? AND NVL(INDICADOR_VINCULADO,'N') = ?";

	/** Constante cs_FIND_BY_ID_SOLICITUD1. */
	private static final String cs_FIND_BY_ID_SOLICITUD1 = "SELECT * FROM SDB_ACC_SOLICITUD_ASOCIADA WHERE ID_SOLICITUD1=?";

	/** Constante cs_FIND_BY_ID_SOLICITUD1_VINCULADO. */
	private static final String cs_FIND_BY_ID_SOLICITUD1_VINCULADO = "SELECT * FROM SDB_ACC_SOLICITUD_ASOCIADA WHERE ID_SOLICITUD1=? AND INDICADOR_VINCULADO = 'S'";

	/** Constante cs_FIND_BY_ID_SOLICITUD. */
	private static final String cs_FIND_BY_ID_SOLICITUD = "SELECT ID_SOLICITUD, ID_SOLICITUD1, FECHA_CREACION FROM SDB_ACC_SOLICITUD_ASOCIADA WHERE ID_SOLICITUD=?";

	/** Constante cs_FIND_LAST_BY_ID_SOLICITUD_ID_PROCESO. */
	private static final String cs_FIND_LAST_BY_ID_SOLICITUD_ID_PROCESO = "SELECT * FROM SDB_ACC_SOLICITUD_ASOCIADA SA INNER JOIN SDB_ACC_SOLICITUD S ON S.ID_SOLICITUD = SA.ID_SOLICITUD1 WHERE SA.ID_SOLICITUD= ? AND S.ID_PROCESO = ? ORDER BY SA.FECHA_CREACION DESC";

	/** Constante cs_FIND_BY_ID_SOLICITUD_PROCESO_SUBPROCESO. */
	private static final String cs_FIND_BY_ID_SOLICITUD_PROCESO_SUBPROCESO = "SELECT SA.ID_SOLICITUD, SA.ID_SOLICITUD1, SA.FECHA_CREACION "
		+ " FROM SDB_ACC_SOLICITUD_ASOCIADA SA INNER JOIN SDB_ACC_SOLICITUD S ON S.ID_SOLICITUD = SA.ID_SOLICITUD1  "
		+ " WHERE SA.ID_SOLICITUD = ? AND S.ID_PROCESO = ? AND S.ID_SUBPROCESO = ? ";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_ACC_SOLICITUD_ASOCIADA SET ID_SOLICITUD1 = ?, INDICADOR_VINCULADO = ?, ID_USUARIO_MODIFICACION = ?, FECHA_MODIFICACION=SYSTIMESTAMP ,IP_MODIFICACION = ?  WHERE ID_SOLICITUD=? ";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_ACC_SOLICITUD_ASOCIADA(ID_SOLICITUD, ID_USUARIO_CREACION,IP_CREACION, ID_SOLICITUD1, INDICADOR_VINCULADO, FECHA_CREACION) VALUES(?, ?, ?, ?, ?, SYSTIMESTAMP)";

	/** Constante cs_PROCESOS_IDSOLICITUD_ASOCIADAS. */
	private static final String cs_PROCESOS_IDSOLICITUD_ASOCIADAS = " SELECT AP.NOMBRE NOMBRE_PROCESO,ASP.NOMBRE NOMBRE_SUBPROCESO,ASA.NIR ,NVL(ACS.INDICADOR_VINCULADO,'N')INDICADOR_VINCULADO"
		+ " FROM SDB_ACC_SOLICITUD_ASOCIADA ACS  ";

	/** Constante cs_FIND_BY_ID_SOLICITUD_PROCESO. */
	private static final String cs_FIND_BY_ID_SOLICITUD_PROCESO = " SELECT SASA.* FROM SDB_ACC_SOLICITUD_ASOCIADA SASA INNER JOIN "
		+ " SDB_ACC_SOLICITUD SAS ON (SAS.ID_SOLICITUD = SASA.ID_SOLICITUD1) WHERE SASA.ID_SOLICITUD = ? AND SAS.ID_PROCESO = ? "
		+ " ORDER BY SASA.ID_SOLICITUD1 DESC ";

	/**
	 * Método sobrecargado para solo enviar String de idSOlicitud.
	 *
	 * @param asa_sa Objeto asa_sa con información a extraer para realizar la búsqueda
	 * @param ab_asociada correspondiente al valor del tipo de objeto boolean
	 * @return Colección de objeto de tipo SolicitudAsociada
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<SolicitudAsociada> findAllByIdSolicitud(SolicitudAsociada asa_sa, boolean ab_asociada)
	    throws B2BException
	{
		Collection<SolicitudAsociada> lcsa_datos;

		lcsa_datos = null;

		if(asa_sa != null)
		{
			String ls_idSolicitud;

			ls_idSolicitud     = asa_sa.getIdSolicitud();

			lcsa_datos = findAllByIdSolicitud(ls_idSolicitud, ab_asociada);
		}

		return lcsa_datos;
	}

	/**
	 * Retorna el valor del objeto de Collection de SolicitudAsociada filtrado por id solicitud.
	 *
	 * @param as_idSolicitud parametro con el que se ejecutará la busqueda
	 * @param ab_asociada correspondiente al valor del tipo de objeto boolean
	 * @return Colección de objeto de tipo SolicitudAsociada
	 * @throws B2BException Método con el cuál se realiza la busqueda en la tabla SDB_ACC_SOLICITUD_ASOCIADA con el ID_SOLICITUD
	 * @see SolicitudAsociada
	 */
	public Collection<SolicitudAsociada> findAllByIdSolicitud(String as_idSolicitud, boolean ab_asociada)
	    throws B2BException
	{
		Collection<SolicitudAsociada> lcsa_datos;

		lcsa_datos = null;

		if(StringUtils.isValidString(as_idSolicitud))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				StringBuilder lsb_sb;

				lsb_sb         = new StringBuilder(cs_FIND_BY_ID_SOLICITUD);
				lcsa_datos     = new ArrayList<SolicitudAsociada>();

				if(ab_asociada)
					lsb_sb.append(" AND NVL(INDICADOR_VINCULADO,'N') = 'S'");

				lsb_sb.append(" ORDER BY FECHA_CREACION DESC");
				lps_ps = getConnection().prepareStatement(lsb_sb.toString());

				lps_ps.setString(1, as_idSolicitud);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcsa_datos.add(getObjetFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findAllByIdSolicitud", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lcsa_datos;
	}

	/**
	 * Find by id.
	 *
	 * @param at_param de at param
	 * @return el valor de solicitud asociada
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public SolicitudAsociada findById(SolicitudAsociada at_param)
	    throws B2BException
	{
		SolicitudAsociada ls_object;

		ls_object = null;

		if(at_param != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(1, at_param.getIdSolicitud());
				lps_ps.setString(2, at_param.getIdSolicitud1());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ls_object = getObjetFromResultSet(lrs_rs);
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

		return ls_object;
	}

	/**
	 * Retorna el valor del objeto de SolicitudAsociada.
	 *
	 * @param as_idSolicitud correspondiente al valor del tipo de objeto String
	 * @param ab_b correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor de SolicitudAsociada
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see SolicitudAsociada
	 */
	public SolicitudAsociada findByIdProceso(String as_idSolicitud, boolean ab_b)
	    throws B2BException
	{
		SolicitudAsociada ls_object;

		ls_object = null;

		if(StringUtils.isValidString(as_idSolicitud))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				StringBuilder lsb_sb;

				lsb_sb = new StringBuilder(cs_PROCESOS_IDSOLICITUD_ASOCIADAS);

				if(ab_b)
					lsb_sb.append(" INNER JOIN SDB_ACC_SOLICITUD ASA ON ASA.ID_SOLICITUD = ACS.ID_SOLICITUD1");
				else
					lsb_sb.append(" INNER JOIN SDB_ACC_SOLICITUD ASA ON ASA.ID_SOLICITUD = ACS.ID_SOLICITUD");

				lsb_sb.append(
				    " INNER JOIN SDB_ACC_PROCESO AP ON AP.ID_PROCESO = ASA .ID_PROCESO INNER JOIN SDB_ACC_SUBPROCESO ASP ON ASP.ID_PROCESO = ASA .ID_PROCESO AND ASP.ID_SUBPROCESO = ASA.ID_SUBPROCESO  "
				);

				if(ab_b)
					lsb_sb.append(" WHERE ACS.ID_SOLICITUD = ?");
				else
					lsb_sb.append(" WHERE ACS.ID_SOLICITUD1 = ?");

				lps_ps = getConnection().prepareStatement(lsb_sb.toString());

				lps_ps.setString(1, as_idSolicitud);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ls_object = procesosSolicitud(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdProceso", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return ls_object;
	}

	/**
	 * Retorna el valor del objeto de SolicitudAsociada.
	 *
	 * @param asa_param de asa param
	 * @return objeto de tipo SolicitudAsociada
	 * @throws B2BException Método con el cuál se realiza la busqueda en la tabla SDB_ACC_SOLICITUD_ASOCIADA con el ID_SOLICITUD1
	 * @see SolicitudAsociada
	 */
	public SolicitudAsociada findByIdSol1(SolicitudAsociada asa_param)
	    throws B2BException
	{
		return (asa_param != null) ? findByIdSol1(asa_param.getIdSolicitud1()) : null;
	}

	/**
	 * Retorna el valor del objeto de SolicitudAsociada.
	 *
	 * @param as_idSolicitud1 de as id solicitud 1
	 * @return objeto de tipo SolicitudAsociada
	 * @throws B2BException Método con el cuál se realiza la busqueda en la tabla SDB_ACC_SOLICITUD_ASOCIADA con el ID_SOLICITUD1
	 * @see SolicitudAsociada
	 */
	public SolicitudAsociada findByIdSol1(String as_idSolicitud1)
	    throws B2BException
	{
		return findByIdSol1(as_idSolicitud1, false);
	}

	/**
	 * Find by id sol 1.
	 *
	 * @param as_idSolicitud1 the as id solicitud 1
	 * @param ab_vinculado the ab vinculado
	 * @return the solicitud asociada
	 * @throws B2BException the b 2 B exception
	 */
	public SolicitudAsociada findByIdSol1(String as_idSolicitud1, boolean ab_vinculado)
	    throws B2BException
	{
		SolicitudAsociada lsa_object;

		lsa_object = null;

		if(StringUtils.isValidString(as_idSolicitud1))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection()
						         .prepareStatement(
						    ab_vinculado ? cs_FIND_BY_ID_SOLICITUD1_VINCULADO : cs_FIND_BY_ID_SOLICITUD1
						);

				lps_ps.setString(1, as_idSolicitud1);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lsa_object = getObjetFromResultSet(lrs_rs, true);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdSol1", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lsa_object;
	}

	/**
	 * Método para realizar una consulta a la BD usando el idSolicitud y el indicador vinculado.
	 *
	 * @param at_param parámetro de tipo <code>SolicitudAsociada</code> del cual se extraerá la información necesaria para la consulta en la BD
	 * @return <code>Collection<SolicitudAsociada></code> llena con la información traída de la BD
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see SolicitudAsociada
	 */
	public SolicitudAsociada findByIdSolicitud(SolicitudAsociada at_param)
	    throws B2BException
	{
		SolicitudAsociada ls_object;

		ls_object = null;

		if(at_param != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_SOLICITUD);

				lps_ps.setString(1, at_param.getIdSolicitud());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ls_object = getObjetFromResultSet(lrs_rs);
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
		}

		return ls_object;
	}

	/**
	 * Método para realizar una consulta a la BD usando el idSolicitud y el indicador vinculado.
	 *
	 * @param at_param parámetro de tipo <code>SolicitudAsociada</code> del cual se extraerá la información necesaria para la consulta en la BD
	 * @return <code>Collection<SolicitudAsociada></code> llena con la información traída de la BD
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see SolicitudAsociada
	 */
	public Collection<SolicitudAsociada> findByIdSolicitudIndVinculado(SolicitudAsociada at_param)
	    throws B2BException
	{
		Collection<SolicitudAsociada> lcsa_solicitudesAsociadas;

		lcsa_solicitudesAsociadas = null;

		if(at_param != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_FIND_BY_ID_IND_VINCULADO);

				lps_ps.setString(li_column++, at_param.getIdSolicitud());
				lps_ps.setString(li_column++, at_param.getIndVinculado());

				lrs_rs                        = lps_ps.executeQuery();
				lcsa_solicitudesAsociadas     = new ArrayList<SolicitudAsociada>();

				while(lrs_rs.next())
					lcsa_solicitudesAsociadas.add(getObjetFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdSolicitudIndVinculado", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lcsa_solicitudesAsociadas;
	}

	/**
	 * Método encargado de buscar los registros que coincidan con ID_SOLICITUD y ID_PROCESO enviados.
	 *
	 * @param as_idSolicitud Argumento de tipo <code>String</code> que contiene el ID_SOLICITUD a consultar.
	 * @param as_idProceso Argumento de tipo <code>String</code> que contiene el ID_PROCESO a consultar.
	 * @return objeto de tipo <code>SolicitudAsociada</code> que contiene los registros que coincidieron con los criterios de consulta.
	 * @throws B2BException Se genera cuando se presenta una excepción.
	 * @see SolicitudAsociada
	 */
	public SolicitudAsociada findByIdSolicitudProceso(String as_idSolicitud, String as_idProceso)
	    throws B2BException
	{
		SolicitudAsociada lsa_object;

		lsa_object = null;

		if(StringUtils.isValidString(as_idSolicitud))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_contador;

				li_contador     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_SOLICITUD_PROCESO);

				lps_ps.setString(li_contador++, as_idSolicitud);
				lps_ps.setString(li_contador++, as_idProceso);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lsa_object = getObjetFromResultSet(lrs_rs, true);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdSolicitudProceso", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lsa_object;
	}

	/**
	 * Método para realizar una consulta a la BD usando el idSolicitud para un proceso y subproceso determinado.
	 *
	 * @param at_param parámetro de tipo <code>SolicitudAsociada</code> del cual se extraerá la información necesaria para la consulta en la BD
	 * @return <code>Collection<SolicitudAsociada></code> llena con la información traída de la BD
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see SolicitudAsociada
	 */
	public SolicitudAsociada findByIdSolicitudProcesoSubProceso(SolicitudAsociada at_param)
	    throws B2BException
	{
		SolicitudAsociada ls_object;

		ls_object = null;

		if(at_param != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_count;

				li_count     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_SOLICITUD_PROCESO_SUBPROCESO);

				lps_ps.setString(li_count++, at_param.getIdSolicitud());
				lps_ps.setString(li_count++, at_param.getIdProceso());
				lps_ps.setString(li_count++, at_param.getIdSubProceso());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ls_object = getObjetFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdSolicitudProcesoSubProceso", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return ls_object;
	}

	/**
	 * Método encargado de buscar el ultimo registro insertado que coincida con ID_SOLICITUD y ID_PROCESO enviados.
	 *
	 * @param as_idSolicitud Argumento de tipo <code>String</code> que contiene el ID_SOLICITUD a consultar.
	 * @param as_idProceso Argumento de tipo <code>String</code> que contiene el ID_PROCESO a consultar.
	 * @return objeto de tipo <code>SolicitudAsociada</code> que contiene el ultimo registro insertado que coincida con ID_SOLICITUD y ID_PROCESO enviados.
	 * @throws B2BException Se genera cuando se presenta una excepción.
	 * @see SolicitudAsociada
	 */
	public SolicitudAsociada findLastByIdSolicitudProceso(String as_idSolicitud, String as_idProceso)
	    throws B2BException
	{
		SolicitudAsociada lsa_object;

		lsa_object = null;

		if(StringUtils.isValidString(as_idSolicitud))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_contador;

				li_contador     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_LAST_BY_ID_SOLICITUD_ID_PROCESO);

				lps_ps.setString(li_contador++, as_idSolicitud);
				lps_ps.setString(li_contador++, as_idProceso);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lsa_object = getObjetFromResultSet(lrs_rs, true);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findLastByIdSolicitudProceso", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lsa_object;
	}

	/**
	 * Método que retorna el id solicitud más grande de las solicitudes asociadas.
	 *
	 * @param as_param Argumento de tipo <code>String</code> que contiene el id de la solicitud
	 * @return devuelve el valor del id solicitud más grande.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public String findMaxByIdSolicitud(String as_param)
	    throws B2BException
	{
		String ls_idMax;

		ls_idMax = null;

		if(as_param != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_MAX_BY_ID_SOLICITUD);

				lps_ps.setString(1, as_param);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ls_idMax = lrs_rs.getString(1);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findMaxByIdSolicitud", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return ls_idMax;
	}

	/**
	 * Insert or update.
	 *
	 * @param at_param de at param
	 * @param ab_query de ab query
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void insertOrUpdate(SolicitudAsociada at_param, boolean ab_query)
	    throws B2BException
	{
		if(at_param != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(ab_query ? cs_INSERT : cs_UPDATE);

				if(ab_query)
				{
					lps_ps.setString(li_column++, at_param.getIdSolicitud());
					lps_ps.setString(li_column++, at_param.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, at_param.getIpCreacion());
				}

				lps_ps.setString(li_column++, at_param.getIdSolicitud1());
				lps_ps.setString(li_column++, at_param.getIndVinculado());

				if(!ab_query)
				{
					lps_ps.setString(li_column++, at_param.getIdUsuarioModificacion());
					lps_ps.setString(li_column++, at_param.getIpModificacion());
					lps_ps.setString(li_column++, at_param.getIdSolicitud());
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
			}
		}
	}

	/**
	 * Retorna el valor de SolicitudAsociada.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de SolicitudAsociada
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private SolicitudAsociada getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		return getObjetFromResultSet(ars_rs, false);
	}

	/**
	 * Retorna el valor de SolicitudAsociada.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @param ab_withIndicator correspondiente al valor del tipo de objeto boolean
	 * @return el valor de SolicitudAsociada
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private SolicitudAsociada getObjetFromResultSet(ResultSet ars_rs, boolean ab_withIndicator)
	    throws SQLException
	{
		SolicitudAsociada ls_solicitud;

		ls_solicitud = new SolicitudAsociada();

		ls_solicitud.setIdSolicitud(ars_rs.getString("ID_SOLICITUD"));
		ls_solicitud.setIdSolicitud1(ars_rs.getString("ID_SOLICITUD1"));
		ls_solicitud.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));

		if(ab_withIndicator)
			ls_solicitud.setIndVinculado(ars_rs.getString("INDICADOR_VINCULADO"));

		return ls_solicitud;
	}

	/**
	 * Retorna el valor del objeto de SolicitudAsociada.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return devuelve el valor de SolicitudAsociada
	 * @throws SQLException Señala que se ha producido una excepción
	 * @see SolicitudAsociada
	 */
	private SolicitudAsociada procesosSolicitud(ResultSet ars_rs)
	    throws SQLException
	{
		SolicitudAsociada ls_solicitud;

		ls_solicitud = new SolicitudAsociada();

		ls_solicitud.setNombreProceso(ars_rs.getString("NOMBRE_PROCESO"));
		ls_solicitud.setNombreSubProceso(ars_rs.getString("NOMBRE_SUBPROCESO"));
		ls_solicitud.setNirSolicitud1(ars_rs.getString("NIR"));
		ls_solicitud.setIndVinculado(ars_rs.getString("INDICADOR_VINCULADO"));

		return ls_solicitud;
	}
}
