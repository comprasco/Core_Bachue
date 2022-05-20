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
 * Clase dedicada al manejo de conexion y transacciones en la base de datos que
 * afecten directamente a la tabla SDB_ACC_DETALLE_CRITERIO_BUSQUEDA.
 *
 * @author hcastaneda
 *
 */
public class DetalleCriterioBusquedaDAO extends BaseDAO
{
	/** Constante cs_INSERTAR_DETALLE_CONSULTA. */
	private static final String cs_INSERTAR_DETALLE_CONSULTA = " INSERT INTO SDB_ACC_DETALLE_CRITERIO_BUSQUEDA(ID_SOLICITUD,"
		+ "CONSECUTIVO_CONSULTA,CONSECUTIVO_CONSULTA_DETALLE,ID_PROCESO_CONSULTA,ID_TIPO_CRITERIO_BUSQUEDA,ID_CAMPO_CRITERIO_BUSQUEDA,VALOR,ID_USUARIO_CREACION,"
		+ "FECHA_CREACION,IP_CREACION) VALUES(?,?,?,?,?,?,?,?,SYSTIMESTAMP,?)";

	/** Constante cs_DELETE_BY_ID_SOLICITUD. */
	private static final String cs_DELETE_BY_ID_SOLICITUD = "DELETE FROM SDB_ACC_DETALLE_CRITERIO_BUSQUEDA WHERE ID_SOLICITUD = ?";

	/** Constante cs_DELETE_DETALLE. */
	private static final String cs_DELETE_DETALLE = "DELETE FROM SDB_ACC_DETALLE_CRITERIO_BUSQUEDA WHERE ID_SOLICITUD = ?"
		+ " AND CONSECUTIVO_CONSULTA = ? AND CONSECUTIVO_CONSULTA_DETALLE = ? AND ID_PROCESO_CONSULTA = ? AND ID_TIPO_CRITERIO_BUSQUEDA = ? AND "
		+ " ID_CAMPO_CRITERIO_BUSQUEDA = ?";

	/** Constante cs_MAXIMO_CONSECUTIVO_CONSULTA_DETALLE. */
	private static final String cs_MAXIMO_CONSECUTIVO_CONSULTA_DETALLE = " SELECT MAX(CONSECUTIVO_CONSULTA_DETALLE) CONSECUTIVO "
		+ "FROM SDB_ACC_DETALLE_CRITERIO_BUSQUEDA WHERE ID_SOLICITUD = ? AND CONSECUTIVO_CONSULTA = ? "
		+ "AND ID_PROCESO_CONSULTA = ? AND ID_TIPO_CRITERIO_BUSQUEDA = ?";

	/** Constante cs_DELETE. */
	private static final String cs_DELETE = "DELETE FROM SDB_ACC_DETALLE_CRITERIO_BUSQUEDA WHERE ID_SOLICITUD = ? AND CONSECUTIVO_CONSULTA = ? "
		+ "AND ID_PROCESO_CONSULTA = ? AND ID_TIPO_CRITERIO_BUSQUEDA = ?";

	/** Constante cs_CONSULTAR_DETALLES_AGREGADOS. */
	private static final String cs_CONSULTAR_DETALLES_AGREGADOS = "SELECT * FROM SDB_ACC_DETALLE_CRITERIO_BUSQUEDA "
		+ " WHERE ID_SOLICITUD = ? AND CONSECUTIVO_CONSULTA = ? AND ID_PROCESO_CONSULTA = ? AND ID_TIPO_CRITERIO_BUSQUEDA = ? "
		+ "	ORDER BY TO_NUMBER(ID_SOLICITUD),TO_NUMBER(CONSECUTIVO_CONSULTA),TO_NUMBER(ID_PROCESO_CONSULTA),TO_NUMBER(ID_TIPO_CRITERIO_BUSQUEDA),TO_NUMBER(CONSECUTIVO_CONSULTA_DETALLE),"
		+ " TO_NUMBER(ID_CAMPO_CRITERIO_BUSQUEDA) ASC";

	/** Constante cs_FIND_BY_ID_SOLICITUD_TIPO_CRITERIO_BUSQUEDA. */
	private static final String cs_FIND_BY_ID_SOLICITUD_TIPO_CRITERIO_BUSQUEDA = "SELECT ID_SOLICITUD, CONSECUTIVO_CONSULTA, ID_PROCESO_CONSULTA,ID_TIPO_CRITERIO_BUSQUEDA, TO_NUMBER(ID_CAMPO_CRITERIO_BUSQUEDA) AS ID_CAMPO_CRITERIO_BUSQUEDA, VALOR, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION, ID_USUARIO_MODIFICACION, FECHA_MODIFICACION, IP_MODIFICACION, CONSECUTIVO_CONSULTA_DETALLE FROM SDB_ACC_DETALLE_CRITERIO_BUSQUEDA WHERE ID_SOLICITUD = ? AND ID_TIPO_CRITERIO_BUSQUEDA = ? ORDER BY CONSECUTIVO_CONSULTA_DETALLE ASC, ID_CAMPO_CRITERIO_BUSQUEDA ASC";

	/** Constante cs_FIND_BY_ID_SOLICITUD_TIPO_CRITERIO_BUSQUEDA_CONSECUTIVO_CONSULTA. */
	private static final String cs_FIND_BY_ID_SOLICITUD_TIPO_CRITERIO_BUSQUEDA_CONSECUTIVO_CONSULTA = "SELECT ID_SOLICITUD, CONSECUTIVO_CONSULTA, ID_PROCESO_CONSULTA,ID_TIPO_CRITERIO_BUSQUEDA, TO_NUMBER(ID_CAMPO_CRITERIO_BUSQUEDA) AS ID_CAMPO_CRITERIO_BUSQUEDA, VALOR, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION, ID_USUARIO_MODIFICACION, FECHA_MODIFICACION, IP_MODIFICACION, CONSECUTIVO_CONSULTA_DETALLE FROM SDB_ACC_DETALLE_CRITERIO_BUSQUEDA WHERE ID_SOLICITUD = ? AND ID_TIPO_CRITERIO_BUSQUEDA = ? AND CONSECUTIVO_CONSULTA_DETALLE = ? ORDER BY ID_CAMPO_CRITERIO_BUSQUEDA ASC";

	/** Constante cs_FIND_BY_ID_SOLICITUD_TIPO_CRITERIO_BUSQUEDA. */
	private static final String cs_FIND_BY_ID_SOLICITUD = "SELECT ID_SOLICITUD, CONSECUTIVO_CONSULTA, ID_PROCESO_CONSULTA,ID_TIPO_CRITERIO_BUSQUEDA, TO_NUMBER(ID_CAMPO_CRITERIO_BUSQUEDA) AS ID_CAMPO_CRITERIO_BUSQUEDA, VALOR, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION, ID_USUARIO_MODIFICACION, FECHA_MODIFICACION, IP_MODIFICACION, CONSECUTIVO_CONSULTA_DETALLE FROM SDB_ACC_DETALLE_CRITERIO_BUSQUEDA WHERE ID_SOLICITUD = ? ORDER BY CONSECUTIVO_CONSULTA_DETALLE ASC, ID_CAMPO_CRITERIO_BUSQUEDA ASC";

	/** Constante cs_FIND_MAX_CONSECUTIVO_CONSULTA_BY_SOLICITUD. */
	private static final String cs_FIND_MAX_CONSECUTIVO_CONSULTA_BY_SOLICITUD = "SELECT MAX(CONSECUTIVO_CONSULTA_DETALLE) AS CANTIDAD FROM SDB_ACC_DETALLE_CRITERIO_BUSQUEDA WHERE ID_SOLICITUD = ? AND ID_TIPO_CRITERIO_BUSQUEDA = ?";

	/** Constante cs_BUSCAR_DETALLE_EXISTENTE. */
	private static final String cs_BUSCAR_DETALLE_EXISTENTE = "SELECT COUNT(0) CONTADOR FROM SDB_ACC_DETALLE_CRITERIO_BUSQUEDA WHERE "
		+ " ID_SOLICITUD = ? AND ID_TIPO_CRITERIO_BUSQUEDA = ? AND ID_CAMPO_CRITERIO_BUSQUEDA = ? AND VALOR = ?";

	/** Constante cs_BUSCAR_DETALLE. */
	private static final String cs_BUSCAR_DETALLE = "SELECT COUNT(0) CONTADOR FROM SDB_ACC_DETALLE_CRITERIO_BUSQUEDA WHERE "
		+ " ID_SOLICITUD = ? AND CONSECUTIVO_CONSULTA = ? AND CONSECUTIVO_CONSULTA_DETALLE = ? AND ID_PROCESO_CONSULTA = ? AND ID_TIPO_CRITERIO_BUSQUEDA = ? AND ID_CAMPO_CRITERIO_BUSQUEDA = ?";

	/** Constante cs_BUSCAR_DETALLE. */
	private static final String cs_BUSCAR_DETALLE_CAMPO = "SELECT * FROM SDB_ACC_DETALLE_CRITERIO_BUSQUEDA WHERE "
		+ " ID_SOLICITUD = ? AND CONSECUTIVO_CONSULTA = ? AND CONSECUTIVO_CONSULTA_DETALLE = ? AND ID_PROCESO_CONSULTA = ? AND ID_TIPO_CRITERIO_BUSQUEDA = ? AND ID_CAMPO_CRITERIO_BUSQUEDA = ?";

	/** Constante cs_INSERTAR_DETALLE_CONSULTA. */
	private static final String cs_UPDATE_VALORES_DETALLE = " UPDATE SDB_ACC_DETALLE_CRITERIO_BUSQUEDA SET VALOR = ?, FECHA_MODIFICACION = SYSTIMESTAMP, ID_USUARIO_MODIFICACION = ?, IP_MODIFICACION = ? "
		+ " WHERE ID_SOLICITUD = ? AND CONSECUTIVO_CONSULTA = ? AND CONSECUTIVO_CONSULTA_DETALLE = ? AND ID_PROCESO_CONSULTA = ? AND ID_TIPO_CRITERIO_BUSQUEDA = ? AND ID_CAMPO_CRITERIO_BUSQUEDA = ?";

	/**
	 * Metodo encargado de actualizar en la tabla SDB_ACC_DETALLE_CRITERIO_BUSQUEDA.
	 *
	 * @param acb_parametros Argumento de tipo <code>CriteriosDeBusqueda</code> que contiene todos los datos a actualizar en la tabla.
	 * @throws B2BException Señala que se ha generado una excepción
	 */
	public void actualizarValoresDetalleCriterio(CriteriosDeBusqueda acb_parametros)
	    throws B2BException
	{
		if(acb_parametros != null)
		{
			PreparedStatement lps_ps;

			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_UPDATE_VALORES_DETALLE);

				lps_ps.setString(li_column++, acb_parametros.getValorCampo());
				lps_ps.setString(li_column++, acb_parametros.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, acb_parametros.getIpModificacion());
				lps_ps.setString(li_column++, acb_parametros.getIdSolicitud());
				lps_ps.setLong(li_column++, acb_parametros.getConsecutivo());
				lps_ps.setLong(li_column++, acb_parametros.getConsecutivoConsultaDetalle());
				lps_ps.setString(li_column++, acb_parametros.getIdProcesoConsulta());
				lps_ps.setString(li_column++, acb_parametros.getIdTipoCriterio());
				lps_ps.setString(li_column++, acb_parametros.getIdCampoCriterioBusqueda());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "actualizarValoresDetalleCriterio", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Metodo encargado de consultar un detalle existente por llave.
	 *
	 * @param acdb_criteriosDeBusqueda Argumento de tipo CriteriosDeBusqueda que contiene los criterios necesarios para realizar la consulta.
	 * @return Variable de tipo int que contiene la cantidad de registros que coincidan con los criterios de búsqueda.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see int
	 */
	public int buscarDetalle(CriteriosDeBusqueda acdb_criteriosDeBusqueda)
	    throws B2BException
	{
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;
		int               li_contador;

		lps_ps          = null;
		lrs_rs          = null;
		li_contador     = 0;

		if(acdb_criteriosDeBusqueda != null)
		{
			try
			{
				int li_column;

				li_column     = 1;

				lps_ps = getConnection().prepareStatement(cs_BUSCAR_DETALLE);

				lps_ps.setString(li_column++, acdb_criteriosDeBusqueda.getIdSolicitud());
				lps_ps.setLong(li_column++, acdb_criteriosDeBusqueda.getConsecutivo());
				lps_ps.setLong(li_column++, acdb_criteriosDeBusqueda.getConsecutivoConsultaDetalle());
				lps_ps.setString(li_column++, acdb_criteriosDeBusqueda.getIdProcesoConsulta());
				lps_ps.setString(li_column++, acdb_criteriosDeBusqueda.getIdTipoCriterio());
				lps_ps.setString(li_column++, acdb_criteriosDeBusqueda.getIdCampoCriterioBusqueda());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					li_contador = lrs_rs.getInt("CONTADOR");
			}
			catch(SQLException lse_e)
			{
				logError(this, "buscarDetalle", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return li_contador;
	}

	/**
	 * Metodo encargado de consultar un detalle existente por llave.
	 *
	 * @param acdb_criteriosDeBusqueda Argumento de tipo <code>CriteriosDeBusqueda</code> que contiene los criterios necesarios para realizar la consulta.
	 * @return Variable de tipo CriteriosDeBusqueda que contiene el registro que coincidan con los criterios de búsqueda.
	 * @throws B2BException Señala que se ha generado una excepción
	 * @see int
	 */
	public CriteriosDeBusqueda buscarDetalleCampo(CriteriosDeBusqueda acdb_criteriosDeBusqueda)
	    throws B2BException
	{
		PreparedStatement   lps_ps;
		ResultSet           lrs_rs;
		CriteriosDeBusqueda lcdb_criterios;

		lps_ps             = null;
		lrs_rs             = null;
		lcdb_criterios     = null;

		if(acdb_criteriosDeBusqueda != null)
		{
			try
			{
				int li_column;

				li_column     = 1;

				lps_ps = getConnection().prepareStatement(cs_BUSCAR_DETALLE_CAMPO);

				lps_ps.setString(li_column++, acdb_criteriosDeBusqueda.getIdSolicitud());
				lps_ps.setLong(li_column++, acdb_criteriosDeBusqueda.getConsecutivo());
				lps_ps.setLong(li_column++, acdb_criteriosDeBusqueda.getConsecutivoConsultaDetalle());
				lps_ps.setString(li_column++, acdb_criteriosDeBusqueda.getIdProcesoConsulta());
				lps_ps.setString(li_column++, acdb_criteriosDeBusqueda.getIdTipoCriterio());
				lps_ps.setString(li_column++, acdb_criteriosDeBusqueda.getIdCampoCriterioBusqueda());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lcdb_criterios = getObjetFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "buscarDetalleCampo", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lcdb_criterios;
	}

	/**
	 * Metodo encargado de consultar un detalle existente por solicitud.
	 *
	 * @param acdb_criteriosDeBusqueda            Argumento de tipo CriteriosDeBusqueda que contiene los criterios
	 *            necesarios para realizar la consulta.
	 * @return Variable de tipo int que contiene la cantidad de registros que
	 *         coincidan con los criterios de búsqueda.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see int
	 */
	public int buscarDetalleExistente(CriteriosDeBusqueda acdb_criteriosDeBusqueda)
	    throws B2BException
	{
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;
		int               li_contador;

		lps_ps          = null;
		lrs_rs          = null;
		li_contador     = 0;

		if(acdb_criteriosDeBusqueda != null)
		{
			try
			{
				int li_column;

				li_column     = 1;

				lps_ps = getConnection().prepareStatement(cs_BUSCAR_DETALLE_EXISTENTE);

				lps_ps.setString(li_column++, acdb_criteriosDeBusqueda.getIdSolicitud());
				lps_ps.setString(li_column++, acdb_criteriosDeBusqueda.getIdTipoCriterio());
				lps_ps.setString(li_column++, acdb_criteriosDeBusqueda.getIdCampoCriterioBusqueda());
				lps_ps.setString(li_column++, acdb_criteriosDeBusqueda.getValorCampo());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					li_contador = lrs_rs.getInt("CONTADOR");
			}
			catch(SQLException lse_e)
			{
				logError(this, "buscarDetalleExistente", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return li_contador;
	}

	/**
	 * Metodo encargado de consultar los detalles agregados a la tabla
	 * SDB_ACC_DETALLE_CRITERIO_BUSQUEDA.
	 *
	 * @param acb_cb            Argumento de tipo CriteriosDeBusqueda que contiene los criterios
	 *            de búsqueda para la consulta.
	 * @return Colección de tipo CriteriosDeBusqueda que contiene los detalles
	 *         agregados.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see CriteriosDeBusqueda
	 */
	public Collection<CriteriosDeBusqueda> consultarDetallesAgregados(CriteriosDeBusqueda acb_cb)
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

		if(acb_cb != null)
		{
			try
			{
				lps_ps = getConnection().prepareStatement(cs_CONSULTAR_DETALLES_AGREGADOS);

				lps_ps.setString(li_column++, acb_cb.getIdSolicitud());
				lps_ps.setLong(li_column++, acb_cb.getConsecutivo());
				lps_ps.setString(li_column++, acb_cb.getIdProcesoConsulta());
				lps_ps.setString(li_column++, acb_cb.getIdTipoCriterio());

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lccb_cb.add(getObjetFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "consultarDetallesAgregados", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lccb_cb;
	}

	/**
	 * Metodo encargado de borrar los registros de la tabla
	 * SDB_ACC_DETALLE_CRITERIO_BUSQUEDA por ID_SOLICITUD.
	 *
	 * @param acb_cb            Argumento de tipo CriteriosDeBusqueda que contiene los
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void deleteByIdSolicitud(CriteriosDeBusqueda acb_cb)
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
				lps_ps        = getConnection().prepareStatement(cs_DELETE_BY_ID_SOLICITUD);

				lps_ps.setString(li_column++, acb_cb.getIdSolicitud());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "deleteByIdSolicitud", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Metodo encargado de eliminar de la tabla SDB_ACC_DETALLE_CRITERIO_BUSQUEDA
	 * por llave primaria.
	 *
	 * @param acb_cb            Argumento de tipo CriteriosDeBusqueda que contiene los criterios
	 *            necesarios para realizar la eliminación del detalle.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void deleteDetalle(CriteriosDeBusqueda acb_cb)
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
				lps_ps        = getConnection().prepareStatement(cs_DELETE_DETALLE);

				lps_ps.setString(li_column++, acb_cb.getIdSolicitud());
				lps_ps.setLong(li_column++, acb_cb.getConsecutivo());
				lps_ps.setLong(li_column++, acb_cb.getConsecutivoConsultaDetalle());
				lps_ps.setString(li_column++, acb_cb.getIdProcesoConsulta());
				lps_ps.setString(li_column++, acb_cb.getIdTipoCriterio());
				lps_ps.setString(li_column++, acb_cb.getIdCampoCriterioBusqueda());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "deleteDetalle", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Método encargado de eliminar los registros que existen en la tabla
	 * SDB_ACC_DETALLE_CRITERIO_BUSQUEDA con
	 * id_solicitud,consecutivo_consulta,id_proceso_consulta,id_tipo_criterio_busqueda.
	 *
	 * @param acb_cb            Objeto de tipo <code>CriteriosDeBusqueda</code> del cuál se
	 *            extraerán los parámetros necesarios para llevar a cabo la
	 *            eliminación
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void deleteDetallesCriterio(CriteriosDeBusqueda acb_cb)
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
				lps_ps        = getConnection().prepareStatement(cs_DELETE);

				lps_ps.setString(li_column++, acb_cb.getIdSolicitud());
				lps_ps.setLong(li_column++, acb_cb.getConsecutivo());
				lps_ps.setString(li_column++, acb_cb.getIdProcesoConsulta());
				lps_ps.setString(li_column++, acb_cb.getIdTipoCriterio());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "deleteDetallesCriterio", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Metodo encargado de consultar los detalles agregados a la tabla
	 * SDB_ACC_DETALLE_CRITERIO_BUSQUEDA.
	 *
	 * @param as_idSolicitud            Argumento de tipo String que contiene los criterios
	 *            de búsqueda para la consulta.
	 * @return Colección de tipo CriteriosDeBusqueda que contiene los detalles
	 *         agregados.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see CriteriosDeBusqueda
	 */
	public Collection<CriteriosDeBusqueda> findByIdSolicitud(String as_idSolicitud)
	    throws B2BException
	{
		Collection<CriteriosDeBusqueda> lccb_cb;
		PreparedStatement               lps_ps;
		ResultSet                       lrs_rs;

		lccb_cb     = null;
		lps_ps      = null;
		lrs_rs      = null;

		if(as_idSolicitud != null)
		{
			try
			{
				int li_column;

				li_column     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_SOLICITUD);

				lps_ps.setString(li_column++, as_idSolicitud);

				lrs_rs     = lps_ps.executeQuery();

				lccb_cb = new ArrayList<CriteriosDeBusqueda>();

				while(lrs_rs.next())
					lccb_cb.add(getObjetFromResultSet(lrs_rs));
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

		return lccb_cb;
	}

	/**
	 * Metodo encargado de consultar los detalles agregados a la tabla
	 * SDB_ACC_DETALLE_CRITERIO_BUSQUEDA.
	 *
	 * @param acb_criterio            Argumento de tipo CriteriosDeBusqueda que contiene los criterios
	 *            de búsqueda para la consulta.
	 * @return Colección de tipo CriteriosDeBusqueda que contiene los detalles
	 *         agregados.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see CriteriosDeBusqueda
	 */
	public Collection<CriteriosDeBusqueda> findByIdSolicitudTipoCriterioBusqueda(CriteriosDeBusqueda acb_criterio)
	    throws B2BException
	{
		Collection<CriteriosDeBusqueda> lccb_cb;
		PreparedStatement               lps_ps;
		ResultSet                       lrs_rs;

		lccb_cb     = null;
		lps_ps      = null;
		lrs_rs      = null;

		if(acb_criterio != null)
		{
			try
			{
				int li_column;

				li_column     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_SOLICITUD_TIPO_CRITERIO_BUSQUEDA);

				lps_ps.setString(li_column++, acb_criterio.getIdSolicitud());
				lps_ps.setString(li_column++, acb_criterio.getIdTipoCriterio());

				lrs_rs     = lps_ps.executeQuery();

				lccb_cb = new ArrayList<CriteriosDeBusqueda>();

				while(lrs_rs.next())
					lccb_cb.add(getObjetFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdSolicitudTipoCriterioBusqueda", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lccb_cb;
	}

	/**
	 * Find max consecutivo consulta by solicitud.
	 *
	 * @param acdb_criteriosDeBusqueda de acdb criterios de busqueda
	 * @return el valor de long
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public long findMaxConsecutivoConsultaBySolicitud(CriteriosDeBusqueda acdb_criteriosDeBusqueda)
	    throws B2BException
	{
		return (acdb_criteriosDeBusqueda != null)
		? findMaxConsecutivoConsultaBySolicitud(
		    acdb_criteriosDeBusqueda.getIdSolicitud(), acdb_criteriosDeBusqueda.getIdTipoCriterio()
		) : 0;
	}

	/**
	 * Find max consecutivo consulta by solicitud.
	 *
	 * @param as_idSolicitud de as id solicitud
	 * @param as_idTipoCriterio de as id tipo criterio
	 * @return el valor de long
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public long findMaxConsecutivoConsultaBySolicitud(String as_idSolicitud, String as_idTipoCriterio)
	    throws B2BException
	{
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;
		long              ll_max;

		lps_ps     = null;
		lrs_rs     = null;
		ll_max     = 0L;

		if(StringUtils.isValidString(as_idSolicitud) && StringUtils.isValidString(as_idTipoCriterio))
		{
			try
			{
				int li_column;

				li_column     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_MAX_CONSECUTIVO_CONSULTA_BY_SOLICITUD);

				lps_ps.setString(li_column++, as_idSolicitud);
				lps_ps.setString(li_column++, as_idTipoCriterio);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ll_max = lrs_rs.getLong("CANTIDAD");
			}
			catch(SQLException lse_e)
			{
				logError(this, "findMaxConsecutivoConsultaBySolicitud", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return ll_max;
	}

	/**
	 * Metodo encargado de insertar en la talb SDB_ACC_DETALLE_CRITERIO_BUSQUEDA.
	 *
	 * @param acb_cb            Argumento de tipo CriteriosDeBusqueda que contiene todos los datos
	 *            a insertar en la tabla.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void insertarDetalleCriterios(CriteriosDeBusqueda acb_cb)
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
				lps_ps        = getConnection().prepareStatement(cs_INSERTAR_DETALLE_CONSULTA);

				lps_ps.setString(li_column++, acb_cb.getIdSolicitud());
				lps_ps.setLong(li_column++, acb_cb.getConsecutivo());
				lps_ps.setLong(li_column++, acb_cb.getConsecutivoConsultaDetalle());
				lps_ps.setString(li_column++, acb_cb.getIdProcesoConsulta());
				lps_ps.setString(li_column++, acb_cb.getIdTipoCriterio());
				lps_ps.setString(li_column++, acb_cb.getIdCampoCriterioBusqueda());
				lps_ps.setString(li_column++, acb_cb.getValorCampo());
				lps_ps.setString(li_column++, acb_cb.getIdUsuarioCreacion());
				lps_ps.setString(li_column++, acb_cb.getIpCreacion());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "insertarDetalleCriterios", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Metodo encargado de consultar el consecutivo consulta detalle máximo.
	 *
	 * @param acdb_criteriosDeBusqueda            Argumento de tipo CriteriosDeBusqueda que contiene los criterios
	 *            necesarios para calcular el consecutivo máximo.
	 * @return Variable de tipo long que contiene el consecutivo consulta detalle
	 *         máximo.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see long
	 */
	public long maxConsecutivoConsultaDetalle(CriteriosDeBusqueda acdb_criteriosDeBusqueda)
	    throws B2BException
	{
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;
		long              ll_max;

		lps_ps     = null;
		lrs_rs     = null;
		ll_max     = 0L;

		if(acdb_criteriosDeBusqueda != null)
		{
			try
			{
				int li_column;

				li_column     = 1;

				lps_ps = getConnection().prepareStatement(cs_MAXIMO_CONSECUTIVO_CONSULTA_DETALLE);

				lps_ps.setString(li_column++, acdb_criteriosDeBusqueda.getIdSolicitud());
				lps_ps.setLong(li_column++, acdb_criteriosDeBusqueda.getConsecutivo());
				lps_ps.setString(li_column++, acdb_criteriosDeBusqueda.getIdProcesoConsulta());
				lps_ps.setString(li_column++, acdb_criteriosDeBusqueda.getIdTipoCriterio());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ll_max = lrs_rs.getLong("CONSECUTIVO");
			}
			catch(SQLException lse_e)
			{
				logError(this, "maxConsecutivoConsultaDetalle", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return ll_max;
	}

	/**
	 * Find by id solicitud tipo criterio busqueda consecutivo consulta.
	 *
	 * @param as_idSolicitud de as id solicitud
	 * @param as_tipoCriterioBusqueda de as tipo criterio busqueda
	 * @param ai_consecutivoConsultaDetalle de ai consecutivo consulta detalle
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<CriteriosDeBusqueda> findByIdSolicitudTipoCriterioBusquedaConsecutivoConsulta(
	    String as_idSolicitud, String as_tipoCriterioBusqueda, long ai_consecutivoConsultaDetalle
	)
	    throws B2BException
	{
		Collection<CriteriosDeBusqueda> lccb_cb;

		lccb_cb = null;

		if(StringUtils.isValidString(as_idSolicitud) && StringUtils.isValidString(as_tipoCriterioBusqueda))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_column;

				li_column     = 1;

				lps_ps = getConnection()
						         .prepareStatement(cs_FIND_BY_ID_SOLICITUD_TIPO_CRITERIO_BUSQUEDA_CONSECUTIVO_CONSULTA);

				lps_ps.setString(li_column++, as_idSolicitud);
				lps_ps.setString(li_column++, as_tipoCriterioBusqueda);
				lps_ps.setLong(li_column++, ai_consecutivoConsultaDetalle);

				lrs_rs     = lps_ps.executeQuery();

				lccb_cb = new ArrayList<CriteriosDeBusqueda>();

				while(lrs_rs.next())
					lccb_cb.add(getObjetFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdSolicitudTipoCriterioBusquedaConsecutivoConsulta", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lccb_cb;
	}

	/**
	 * Retorna el valor de CriteriosDeBusqueda.
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
		lcb_cb.setConsecutivo(ars_rs.getLong("CONSECUTIVO_CONSULTA"));
		lcb_cb.setIdProcesoConsulta(ars_rs.getString("ID_PROCESO_CONSULTA"));
		lcb_cb.setIdTipoCriterio(ars_rs.getString("ID_TIPO_CRITERIO_BUSQUEDA"));
		lcb_cb.setIdCampoCriterioBusqueda(ars_rs.getString("ID_CAMPO_CRITERIO_BUSQUEDA"));
		lcb_cb.setValorCampo(ars_rs.getString("VALOR"));
		lcb_cb.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		lcb_cb.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		lcb_cb.setIpCreacion(ars_rs.getString("IP_CREACION"));
		lcb_cb.setIdUsuarioModificacion(ars_rs.getString("ID_USUARIO_MODIFICACION"));
		lcb_cb.setFechaModificacion(ars_rs.getTimestamp("FECHA_MODIFICACION"));
		lcb_cb.setIpModificacion(ars_rs.getString("IP_MODIFICACION"));
		lcb_cb.setConsecutivoConsultaDetalle(ars_rs.getLong("CONSECUTIVO_CONSULTA_DETALLE"));

		return lcb_cb;
	}
}
