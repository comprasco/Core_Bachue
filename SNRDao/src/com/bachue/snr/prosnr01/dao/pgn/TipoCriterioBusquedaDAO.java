package com.bachue.snr.prosnr01.dao.pgn;

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
 * Clase que contiene todos las propiedades de TipoCriterioBusquedaDAO.
 *
 * @author dbeltran
 */
public class TipoCriterioBusquedaDAO extends BaseDAO
{
	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT TCB.DESCRIPCION , TCB.ID_TIPO_CRITERIO_BUSQUEDA "
		+ "FROM SDB_PGN_DETALLE_PROCESO_CONSULTA DPC "
		+ "INNER JOIN SDB_PGN_PROCESO_CONSULTA PPC ON PPC.ID_PROCESO_CONSULTA = DPC.ID_PROCESO_CONSULTA AND PPC.ACTIVO = 'S' "
		+ "INNER JOIN SDB_ACC_PROCESO SAP ON SAP.ID_PROCESO = PPC.ID_PROCESO AND SAP.ID_PROCESO = ? "
		+ "INNER JOIN SDB_PGN_TIPO_CRITERIO_BUSQUEDA TCB ON  TCB.ID_TIPO_CRITERIO_BUSQUEDA = DPC.ID_TIPO_CRITERIO_BUSQUEDA AND TCB.ACTIVO = 'S' "
		+ "WHERE DPC.ID_PROCESO_CONSULTA = ? AND DPC.ACTIVO = 'S'";

	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_PGN_TIPO_CRITERIO_BUSQUEDA WHERE ID_TIPO_CRITERIO_BUSQUEDA = ?";

	/**Constante cs_FIND_ALL_JUST_TABLE*/
	private static final String cs_FIND_ALL_JUST_TABLE = "SELECT * FROM SDB_PGN_TIPO_CRITERIO_BUSQUEDA ORDER BY DESCRIPCION ASC";

	/** Constante cs_FIND_INFO_TABS. */
	private static final String cs_FIND_INFO_TABS = "SELECT SPCCB.ETIQUETA_CAMPO, SADCB.VALOR FROM SDB_ACC_CRITERIO_BUSQUEDA SACB "
		+ "INNER JOIN SDB_PGN_CAMPO_CRITERIO_BUSQUEDA SPCCB ON SPCCB.ID_TIPO_CRITERIO_BUSQUEDA = SACB.ID_TIPO_CRITERIO_BUSQUEDA "
		+ "INNER JOIN SDB_ACC_DETALLE_CRITERIO_BUSQUEDA SADCB ON SADCB.ID_SOLICITUD = SACB.ID_SOLICITUD AND SADCB.ID_TIPO_CRITERIO_BUSQUEDA = SACB.ID_TIPO_CRITERIO_BUSQUEDA "
		+ "AND SPCCB.ID_TIPO_CRITERIO_BUSQUEDA = SADCB.ID_TIPO_CRITERIO_BUSQUEDA AND SPCCB.ID_CAMPO_CRITERIO_BUSQUEDA = SADCB.ID_CAMPO_CRITERIO_BUSQUEDA "
		+ "WHERE SACB.ID_SOLICITUD = ? AND SACB.ID_TIPO_CRITERIO_BUSQUEDA = ? AND SADCB.CONSECUTIVO_CONSULTA_DETALLE = ? "
		+ "ORDER BY SADCB.ID_CAMPO_CRITERIO_BUSQUEDA ASC";

	/** Constante cs_FIND_ALL_ACTIVO. */
	private static final String cs_FIND_ALL_ACTIVO = "SELECT * FROM SDB_PGN_TIPO_CRITERIO_BUSQUEDA WHERE ACTIVO = 'S' ORDER BY LENGTH(ID_TIPO_CRITERIO_BUSQUEDA), ID_TIPO_CRITERIO_BUSQUEDA";

	/**
	 * Método para traer de la base de datos todos lo registros de la tabla SDB_PGN_TIPO_CRITERIO_BUSQUEDA.
	 *
	 * @param as_idProceso Argumento de tipo <code>String</code> que contiene el id_proceso a consultar
	 * @param as_procesoBusqueda correspondiente al valor del tipo de objeto String
	 * @param as_tipoCriterioBusqueda Argumento de tipo <code>String</code> que contiene el id_tipo_criterio_busqueda a consultar
	 * @return devuelve el valor del objeto collection
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<CriteriosDeBusqueda> findAll(
	    String as_idProceso, String as_procesoBusqueda, String as_tipoCriterioBusqueda
	)
	    throws B2BException
	{
		Collection<CriteriosDeBusqueda> lccb_object;
		PreparedStatement               lps_ps;
		ResultSet                       lrs_rs;
		int                             li_i;

		lccb_object     = new ArrayList<CriteriosDeBusqueda>();
		lps_ps          = null;
		lrs_rs          = null;
		li_i            = 1;

		try
		{
			StringBuilder lsb_sb;
			boolean       lb_tipoCriterioBusqueda;

			lsb_sb                      = new StringBuilder(cs_FIND_ALL);
			lb_tipoCriterioBusqueda     = StringUtils.isValidString(as_tipoCriterioBusqueda);

			if(lb_tipoCriterioBusqueda)
				lsb_sb.append(" AND TCB.ID_TIPO_CRITERIO_BUSQUEDA = ?");

			lps_ps = getConnection().prepareStatement(lsb_sb.toString());

			lps_ps.setString(li_i++, as_idProceso);
			lps_ps.setString(li_i++, as_procesoBusqueda);

			if(lb_tipoCriterioBusqueda)
				lps_ps.setString(li_i++, as_tipoCriterioBusqueda);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lccb_object.add(getObjetFromResultSet(lrs_rs));
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

		if(lccb_object.isEmpty())
			lccb_object = null;

		return lccb_object;
	}

	/**
	 * Consulta en base de datos todos los registros que se encuentren activos
	 * @return Colección de Proceso Consulta resultante de la consulta
	 * @throws B2BException
	 */
	public Collection<CriteriosDeBusqueda> findAllActivo()
	    throws B2BException
	{
		Collection<CriteriosDeBusqueda> lp_criterioBusqueda;
		PreparedStatement               lps_ps;
		ResultSet                       lrs_rs;

		lp_criterioBusqueda     = new ArrayList<CriteriosDeBusqueda>();
		lps_ps                  = null;
		lrs_rs                  = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL_ACTIVO);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lp_criterioBusqueda.add(getObjetFromResultSet(lrs_rs));
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

		if(lp_criterioBusqueda.isEmpty())
			lp_criterioBusqueda = null;

		return lp_criterioBusqueda;
	}

	/**
	 * Método para consultar todos los tipos criterrios de busqueda
	 * @return Una collección de Criterios De Búsqueda con la información de la tabla
	 */
	public Collection<CriteriosDeBusqueda> findAllTipoCriterioBusqueda()
	    throws B2BException
	{
		Collection<CriteriosDeBusqueda> lccb_criterios;
		PreparedStatement               lps_ps;
		ResultSet                       lrs_rs;

		lccb_criterios     = new ArrayList<CriteriosDeBusqueda>();
		lps_ps             = null;
		lrs_rs             = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL_JUST_TABLE);
			lrs_rs     = lps_ps.executeQuery();

			while(lrs_rs.next())
				lccb_criterios.add(getObjetFromResultSet(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findAllJustTable", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return lccb_criterios;
	}

	/**
	 * Método para traer de la base de datos todos lo registros de la tabla SDB_PGN_TIPO_CRITERIO_BUSQUEDA
	 * consultando por un idTipoCriterioBusqueda especifico.
	 *
	 * @param acb_criteriosDeBusqueda correspondiente al valor del tipo de objeto CriteriosDeBusqueda
	 * @return devuelve el valor del objeto criterios de busqueda
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public CriteriosDeBusqueda findById(CriteriosDeBusqueda acb_criteriosDeBusqueda)
	    throws B2BException
	{
		CriteriosDeBusqueda lcb_object;
		PreparedStatement   lps_ps;
		ResultSet           lrs_rs;
		int                 li_i;

		lcb_object     = new CriteriosDeBusqueda();
		lps_ps         = null;
		lrs_rs         = null;
		li_i           = 1;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

			lps_ps.setString(li_i++, acb_criteriosDeBusqueda.getIdTipoCriterio());

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				lcb_object = getObjetFromResultSet(lrs_rs);
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

		return lcb_object;
	}

	/**
	 * Método para traer de la base de datos todos lo registros TABS por solicitud.
	 *
	 * @param acb_criteriosBusqueda correspondiente al valor del tipo de objeto CriteriosDeBusqueda
	 * @param ai_tipoCriterio correspondiente al valor del tipo de objeto int
	 * @return devuelve el valor del objeto collection
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<CriteriosDeBusqueda> findInfoTabs(CriteriosDeBusqueda acb_criteriosBusqueda, int ai_tipoCriterio)
	    throws B2BException
	{
		Collection<CriteriosDeBusqueda> lccb_object;
		PreparedStatement               lps_ps;
		ResultSet                       lrs_rs;
		int                             li_i;

		lccb_object     = new ArrayList<CriteriosDeBusqueda>();
		lps_ps          = null;
		lrs_rs          = null;
		li_i            = 1;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_INFO_TABS);

			lps_ps.setString(li_i++, acb_criteriosBusqueda.getIdSolicitud());
			lps_ps.setString(li_i++, acb_criteriosBusqueda.getIdTipoCriterio());
			lps_ps.setLong(li_i++, ai_tipoCriterio);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lccb_object.add(getObjetFromResultSetTabs(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findInfoTabs", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(lccb_object.isEmpty())
			lccb_object = null;

		return lccb_object;
	}

	/**
	 * Método que asigna los resultados de una consulta a los atributos de un nuevo objeto de criterios de busqueda.
	 *
	 * @param ars_rs objeto que recoge el resultado de la consulta
	 * @return el valor de CriteriosDeBusqueda
	 * @throws SQLException Señala que se ha producido una excepción
	 * @see CriteriosDeBusqueda
	 */
	private CriteriosDeBusqueda getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		CriteriosDeBusqueda ls_cb;

		ls_cb = new CriteriosDeBusqueda();

		ls_cb.setDescripcion(ars_rs.getString("DESCRIPCION"));
		ls_cb.setIdTipoCriterio(ars_rs.getString("ID_TIPO_CRITERIO_BUSQUEDA"));

		return ls_cb;
	}

	/**
	 * Método que asigna los resultados de una consulta a los atributos de un nuevo objeto de criterios de busqueda.
	 *
	 * @param ars_rs objeto que recoge el resultado de la consulta
	 * @return el valor de CriteriosDeBusqueda
	 * @throws SQLException Señala que se ha producido una excepción
	 * @see CriteriosDeBusqueda
	 */
	private CriteriosDeBusqueda getObjetFromResultSetTabs(ResultSet ars_rs)
	    throws SQLException
	{
		CriteriosDeBusqueda ls_cb;

		ls_cb = new CriteriosDeBusqueda();

		ls_cb.setEtiquetaCampo(ars_rs.getString("ETIQUETA_CAMPO"));
		ls_cb.setValorCampo(ars_rs.getString("VALOR"));

		return ls_cb;
	}
}
