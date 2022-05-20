package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.model.sdb.pgn.CriteriosDeBusqueda;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase dedicada al manejo de conexion y transacciones en la base de datos que afecten
 * directamente a la tabla SDB_ACC_CRITERIO_BUSQUEDA.
 *
 * @author
 */
public class CriterioBusquedaDAO extends BaseDAO
{
	/** Constante cs_CRITERIOS_POR_SOLICITUD. */
	private static final String cs_CRITERIOS_POR_SOLICITUD = " SELECT * FROM SDB_ACC_CRITERIO_BUSQUEDA WHERE ID_SOLICITUD = ? ";

	/** Constante cs_CRITERIOS_POR_SOLICITUD_TIPO_CRITERIO. */
	private static final String cs_CRITERIOS_POR_SOLICITUD_TIPO_CRITERIO = " SELECT * FROM SDB_ACC_CRITERIO_BUSQUEDA WHERE ID_SOLICITUD = ? AND ID_TIPO_CRITERIO_BUSQUEDA = ?";

	/** Constante cs_MAXIMO_CONSECUTIVO_CONSULTA. */
	private static final String cs_MAXIMO_CONSECUTIVO_CONSULTA = " SELECT MAX(CONSECUTIVO_CONSULTA) CONSECUTIVO FROM SDB_ACC_CRITERIO_BUSQUEDA WHERE ID_SOLICITUD = ?  AND ID_PROCESO_CONSULTA = ?";

	/** Constante cs_INSERTAR_CRITERIOS. */
	private static final String cs_INSERTAR_CRITERIOS = " INSERT INTO SDB_ACC_CRITERIO_BUSQUEDA (ID_SOLICITUD,CONSECUTIVO_CONSULTA,ID_PROCESO_CONSULTA,ID_TIPO_CRITERIO_BUSQUEDA,ID_CIRCULO,ID_REGIONAL,CANTIDAD_CONSULTAS,CONSULTA_NACIONAL,TIPO_CONSULTA,"
		+ " ID_USUARIO_CREACION,FECHA_CREACION,IP_CREACION) VALUES (?,?,?,?,?,?,?,?,?,?,SYSTIMESTAMP,?)";

	/** Constante cs_DELETE_POR_SOLICITUD. */
	private static final String cs_DELETE_POR_SOLICITUD = " DELETE FROM SDB_ACC_CRITERIO_BUSQUEDA WHERE ID_SOLICITUD = ? ";

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @param acb_cb correspondiente al valor del tipo de objeto CriteriosDeBusqueda
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see CriteriosDeBusqued
	 */
	public Collection<CriteriosDeBusqueda> buscarCriteriosPorsolicitud(CriteriosDeBusqueda acb_cb)
	    throws B2BException
	{
		return buscarCriteriosPorSolicitud((acb_cb != null) ? acb_cb.getIdSolicitud() : null);
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @param as_idSolicitud de id solicitud
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see CriteriosDeBusqued
	 */
	public Collection<CriteriosDeBusqueda> buscarCriteriosPorSolicitud(String as_idSolicitud)
	    throws B2BException
	{
		Collection<CriteriosDeBusqueda> lccb_cb;
		PreparedStatement               lps_ps;
		ResultSet                       lrs_rs;
		int                             li_column;

		li_column     = 1;

		lccb_cb     = new ArrayList<CriteriosDeBusqueda>();
		lps_ps      = null;
		lrs_rs      = null;

		if(StringUtils.isValidString(as_idSolicitud))
		{
			try
			{
				lps_ps = getConnection().prepareStatement(cs_CRITERIOS_POR_SOLICITUD);

				lps_ps.setString(li_column++, as_idSolicitud);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lccb_cb.add(getObjetFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "buscarCriteriosPorsolicitud", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lccb_cb.isEmpty())
			lccb_cb = null;

		return lccb_cb;
	}

	/**
	 * Metodo encargado de consultar criterios por id solicitud y por id tipo criterio busqueda.
	 *
	 * @param acdb_parametros correspondiente al valor del tipo de objeto CriteriosDeBusqueda
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see CriteriosDeBusqued
	 */
	public Collection<CriteriosDeBusqueda> buscarCriteriosPorsolicitudTipoCriterio(CriteriosDeBusqueda acdb_parametros)
	    throws B2BException
	{
		Collection<CriteriosDeBusqueda> lccb_cb;
		PreparedStatement               lps_ps;
		ResultSet                       lrs_rs;
		int                             li_column;

		li_column     = 1;

		lccb_cb     = new ArrayList<CriteriosDeBusqueda>();
		lps_ps      = null;
		lrs_rs      = null;

		if(acdb_parametros != null)
		{
			try
			{
				lps_ps = getConnection().prepareStatement(cs_CRITERIOS_POR_SOLICITUD_TIPO_CRITERIO);

				lps_ps.setString(li_column++, acdb_parametros.getIdSolicitud());
				lps_ps.setString(li_column++, acdb_parametros.getIdTipoCriterio());

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lccb_cb.add(getObjetFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "buscarCriteriosPorsolicitudTipoCriterio", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lccb_cb.isEmpty())
			lccb_cb = null;

		return lccb_cb;
	}

	/**
	 * Metodo encargado de borrar los criterios por ID_SOLICITUD.
	 *
	 * @param acb_cb Argumento de tipo CriteriosDeBusqueda que contiene el
	 * ID_SOLICITUD para borrar los criterios.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void deleteCriteriosByIdSolicitud(CriteriosDeBusqueda acb_cb)
	    throws B2BException
	{
		if(acb_cb != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_DELETE_POR_SOLICITUD);

				lps_ps.setString(1, acb_cb.getIdSolicitud());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "deleteCriteriosByIdSolicitud", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Método para inserción de un registro en la tabla SDB_ACC_CRITERIO_BUSQUEDA.
	 *
	 * @param acb_cb objeto CriteriosDeBusqueda para obtener los datos a insertar
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void insertarCriterio(CriteriosDeBusqueda acb_cb)
	    throws B2BException
	{
		if(acb_cb != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;

				lps_ps = getConnection().prepareStatement(cs_INSERTAR_CRITERIOS);

				lps_ps.setString(li_column++, acb_cb.getIdSolicitud());
				lps_ps.setLong(li_column++, acb_cb.getConsecutivo());
				lps_ps.setString(li_column++, acb_cb.getIdProcesoConsulta());
				lps_ps.setString(li_column++, acb_cb.getIdTipoCriterio());
				lps_ps.setString(li_column++, acb_cb.getIdCirculo());
				lps_ps.setString(li_column++, acb_cb.getRegional());
				setLong(lps_ps, acb_cb.getCantidadConsultas(), li_column++);
				lps_ps.setString(li_column++, acb_cb.getConsultaNacional());
				lps_ps.setString(li_column++, acb_cb.getTipoConsulta());
				lps_ps.setString(li_column++, acb_cb.getIdUsuarioCreacion());
				lps_ps.setString(li_column++, acb_cb.getIpCreacion());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "insertarCriterio", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Retorna el valor del objeto de int.
	 *
	 * @param ls_idSolicitud correspondiente al valor del tipo de objeto String
	 * @param ls_procesoConsulta correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de int
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see int
	 */
	public int maxConsecutivoConsulta(String ls_idSolicitud, String ls_procesoConsulta)
	    throws B2BException
	{
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;
		int               li_column;
		int               li_max;

		li_column     = 1;

		lps_ps     = null;
		lrs_rs     = null;
		li_max     = 0;

		if(StringUtils.isValidString(ls_idSolicitud) && StringUtils.isValidString(ls_procesoConsulta))
		{
			try
			{
				lps_ps = getConnection().prepareStatement(cs_MAXIMO_CONSECUTIVO_CONSULTA);

				lps_ps.setString(li_column++, ls_idSolicitud);
				lps_ps.setString(li_column++, ls_procesoConsulta);
				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					li_max = lrs_rs.getInt("CONSECUTIVO");
			}
			catch(SQLException lse_e)
			{
				logError(this, "maxConsecutivoConsulta", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return li_max;
	}

	/**
	 * Retorna el valor de CriteriosDeBusqueda
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de CriteriosDeBusqueda
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private CriteriosDeBusqueda getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		CriteriosDeBusqueda lcb_cb;

		lcb_cb = new CriteriosDeBusqueda();

		lcb_cb.setIdSolicitud(ars_rs.getString("ID_SOLICITUD"));
		lcb_cb.setIdProcesoConsulta(ars_rs.getString("ID_PROCESO_CONSULTA"));
		lcb_cb.setIdTipoCriterio(ars_rs.getString("ID_TIPO_CRITERIO_BUSQUEDA"));
		lcb_cb.setConsecutivo(ars_rs.getLong("CONSECUTIVO_CONSULTA"));
		lcb_cb.setTipoConsulta(ars_rs.getString("TIPO_CONSULTA"));
		lcb_cb.setCantidadConsultas(getLong(ars_rs, "CANTIDAD_CONSULTAS"));
		lcb_cb.setConsultaNacional(ars_rs.getString("CONSULTA_NACIONAL"));
		lcb_cb.setIpModificacion(ars_rs.getString("IP_MODIFICACION"));
		lcb_cb.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		lcb_cb.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		lcb_cb.setIpCreacion(ars_rs.getString("IP_CREACION"));
		lcb_cb.setIdUsuarioModificacion(ars_rs.getString("ID_USUARIO_MODIFICACION"));
		lcb_cb.setFechaModificacion(ars_rs.getTimestamp("FECHA_MODIFICACION"));
		lcb_cb.setIpModificacion(ars_rs.getString("IP_MODIFICACION"));

		return lcb_cb;
	}
}
