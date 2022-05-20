package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;

import com.bachue.snr.prosnr01.dao.IBase;

import com.bachue.snr.prosnr01.model.sdb.acc.CausalAprobacionDevolucion;

import java.math.BigDecimal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;


/**
 * Clase para el manejo de datos para la tabla SDB_PGN_CAUSAL_APROBACION_DEVOLUCION.
 *
 * @author Sebastian Tafur
 */
public class CausalAprobacionDevolucionDAO extends BaseDAO implements IBase<CausalAprobacionDevolucion>
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_PGN_CAUSAL_APROBACION_DEVOLUCION  WHERE CODIGO=?";

	/** Constante cs_FIND_BY_ETAPA_ANTERIOR. */
	private static final String cs_FIND_BY_ETAPA_ANTERIOR = "SELECT * FROM SDB_PGN_CAUSAL_APROBACION_DEVOLUCION  WHERE ID_ETAPA_DEVOLUCION=?";

	/** Constante cs_FIND_BY_ETAPA_ANTERIOR_SECUENCIA. */
	private static final String cs_FIND_BY_ETAPA_ANTERIOR_SECUENCIA = "SELECT * FROM SDB_PGN_CAUSAL_APROBACION_DEVOLUCION WHERE ID_ETAPA_DEVOLUCION=? AND CODIGO = 'SE' OR CODIGO = 'NS'";

	/** Constante cs_FIND_BY_ETAPA_ANTERIOR_RESOLUCION. */
	private static final String cs_FIND_BY_ETAPA_ANTERIOR_RESOLUCION = "SELECT * FROM SDB_PGN_CAUSAL_APROBACION_DEVOLUCION WHERE ID_ETAPA_DEVOLUCION=? AND CODIGO = 'DEVOAP'";

	/** Constante cs_FIND_BY_ETAPA_ANTERIOR_ASESORIA_JURIDICA. */
	private static final String cs_FIND_BY_ETAPA_ANTERIOR_ASESORIA_JURIDICA = "SELECT * FROM SDB_PGN_CAUSAL_APROBACION_DEVOLUCION WHERE ID_ETAPA_DEVOLUCION=? AND CODIGO = 'MODR'";
	private static final String CS_FIND_BY_ETAPA_ANTERIOR_DEVOLUCION_111 = "SELECT * FROM SDB_PGN_CAUSAL_APROBACION_DEVOLUCION WHERE ID_ETAPA_DEVOLUCION = ? AND CODIGO = 'MSC' OR CODIGO = 'DEVAOO'";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_PGN_CAUSAL_APROBACION_DEVOLUCION SET CODIGO=?, "
		+ " CAUSAL_DEVOLUCION=?, ACCION=?,ID_ETAPA_DEVOLUCION=?,ESTADO=?,ID_USUARIO_MODIFICACION=?,IP_MODIFICACION=?,FECHA_MODIFICACION=SYSTIMESTAMP"
		+ " WHERE ID_CAUSAL_APROBACION_DEVOLUCION=?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_PGN_CAUSAL_APROBACION_DEVOLUCION("
		+ "ID_CAUSAL_APROBACION_DEVOLUCION,CODIGO, CAUSAL_DEVOLUCION, ACCION, ID_ETAPA_DEVOLUCION,ESTADO,IP_CREACION,ID_USUARIO_CREACION,FECHA_CREACION)"
		+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?,SYSTIMESTAMP)";

	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_PGN_CAUSAL_APROBACION_DEVOLUCION ";

	/** Constante cs_FIND_SECUENCE_CAUSAL_APROBACION_DEVOLUCION. */
	private static final String cs_FIND_SECUENCE_CAUSAL_APROBACION_DEVOLUCION = "SELECT SEC_PGN_CAUSAL_APROBACION_DEVOLUCION_ID_CAUSAL_APROBACION_DEVOLUCION.NEXTVAL FROM DUAL";

/**
 * Método para traer de la base de datos todos lo registros de la tabla SDB_PGN_CAUSAL_APROBACION_DEVOLUCION.
 *
 * @param ab_activo correspondiente al valor del tipo de objeto boolean
 * @return devuelve el valor de Collection
 * @throws B2BException Señala que se ha producido una excepción
 *
 */
	public Collection<CausalAprobacionDevolucion> findAll(boolean ab_activo)
	    throws B2BException
	{
		Collection<CausalAprobacionDevolucion> lccad_ccad;
		PreparedStatement                      lps_ps;
		ResultSet                              lrs_rs;

		lccad_ccad                                                       = new ArrayList<CausalAprobacionDevolucion>();
		lps_ps                                                           = null;
		lrs_rs                                                           = null;

		try
		{
			StringBuilder lsb_consulta;

			lsb_consulta = new StringBuilder();

			lsb_consulta.append(cs_FIND_ALL);

			if(ab_activo)
				lsb_consulta.append(" WHERE ESTADO = 'A'");

			lps_ps     = getConnection().prepareStatement(lsb_consulta.toString());

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lccad_ccad.add(getObjetFromResultSet(lrs_rs));
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

		if(lccad_ccad.isEmpty())
			lccad_ccad = null;

		return lccad_ccad;
	}

	public Collection<CausalAprobacionDevolucion> findByEtapaDevolucion111(CausalAprobacionDevolucion acad_param)
	    throws B2BException
	{
		Collection<CausalAprobacionDevolucion> lccad_ccad;

		lccad_ccad = new LinkedList<CausalAprobacionDevolucion>();

		if(acad_param != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(CS_FIND_BY_ETAPA_ANTERIOR_DEVOLUCION_111);

				setLong(lps_ps, NumericUtils.getLongWrapper(acad_param.getIdEtapaDevolucion()), 1);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lccad_ccad.add(getObjetFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByEtapaDevolucion111", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lccad_ccad.isEmpty())
			lccad_ccad = null;

		return lccad_ccad;
	}

	/**
	 * Método para encontrar todos los registros de la tabla SDB_PGN_CAUSAL_APROBACION_DEVOLUCION
	 * que coíncida con un CODIGO específico.
	 *
	 * @param acad_param correspondiente al valor del tipo de objeto CausalAprobacionDevolucion
	 * @return devuelve el valor de CausalAprobacionDevolucion
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see CausalAprobacionDevolucion
	 */
	public CausalAprobacionDevolucion findById(CausalAprobacionDevolucion acad_param)
	    throws B2BException
	{
		CausalAprobacionDevolucion lcad_cad;

		lcad_cad = null;

		if(acad_param != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(1, acad_param.getCodigo());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lcad_cad = getObjetFromResultSet(lrs_rs);
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

		return lcad_cad;
	}

	/**
	 * Metodo para obtener los causales de devolución en aprobación filtrados por una etapa especifica
	 * determinada previamente.
	 *
	 * @param acad_param Objeto contenedor de la etapa a usar como filtro
	 * @return Colección de causales resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public Collection<CausalAprobacionDevolucion> findByIdEtapaAnterior(CausalAprobacionDevolucion acad_param)
	    throws B2BException
	{
		Collection<CausalAprobacionDevolucion> lccad_ccad;

		lccad_ccad = new LinkedList<CausalAprobacionDevolucion>();

		if(acad_param != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				if(acad_param.isEsAprobadorSecuencia())
					lps_ps = getConnection().prepareStatement(cs_FIND_BY_ETAPA_ANTERIOR_SECUENCIA);
				else if(acad_param.isEsAprobadorResolucion())
					lps_ps = getConnection().prepareStatement(cs_FIND_BY_ETAPA_ANTERIOR_RESOLUCION);
				else if(acad_param.isEsAprobadorAsesoriaJuridica())
					lps_ps = getConnection().prepareStatement(cs_FIND_BY_ETAPA_ANTERIOR_ASESORIA_JURIDICA);
				else
					lps_ps = getConnection().prepareStatement(cs_FIND_BY_ETAPA_ANTERIOR);

				setLong(lps_ps, NumericUtils.getLongWrapper(acad_param.getIdEtapaDevolucion()), 1);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lccad_ccad.add(getObjetFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdEtapaAnterior", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lccad_ccad.isEmpty())
			lccad_ccad = null;

		return lccad_ccad;
	}

	/**
	 * Método para insertar o actualizar registros en la base de datos.
	 *
	 * @param acad_parametros objeto a insertar o modificar
	 * @param ab_query indica si se desea insertar o actualizar(true inserta, false modifica)
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void insertOrUpdate(CausalAprobacionDevolucion acad_parametros, boolean ab_query)
	    throws B2BException
	{
		if(acad_parametros != null)
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
					lps_secuencia     = lc_connection.prepareStatement(cs_FIND_SECUENCE_CAUSAL_APROBACION_DEVOLUCION);

					lrs_rs = lps_secuencia.executeQuery();

					if(lrs_rs.next())
					{
						Object lo_o;
						lo_o = lrs_rs.getObject(1);

						if((lo_o != null) && lo_o instanceof BigDecimal)
							acad_parametros.setIdCausalAprobacionDevolucion(StringUtils.getString(lo_o));
						else
							throw new B2BException(ErrorKeys.PGN_CAUSAL_APROBACION_DEVOLUCION);
					}

					lps_ps.setString(li_column++, acad_parametros.getIdCausalAprobacionDevolucion());
				}

				lps_ps.setString(li_column++, acad_parametros.getCodigo());
				lps_ps.setString(li_column++, acad_parametros.getCausalDevolucion());
				lps_ps.setString(li_column++, acad_parametros.getAccion());
				lps_ps.setObject(li_column++, acad_parametros.getIdEtapaDevolucion());
				lps_ps.setString(li_column++, acad_parametros.getEstado());

				if(ab_query)
				{
					lps_ps.setString(li_column++, acad_parametros.getIpCreacion());
					lps_ps.setString(li_column++, acad_parametros.getIdUsuarioCreacion());
				}
				else
				{
					lps_ps.setString(li_column++, acad_parametros.getIdUsuarioModificacion());
					lps_ps.setString(li_column++, acad_parametros.getIpModificacion());
					lps_ps.setString(li_column++, acad_parametros.getIdCausalAprobacionDevolucion());
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
 * Método que asigna los resultados de una consulta a los atributos de un nuevo objeto de causal aprobación devolución.
 *
 * @param ars_rs objeto que recoge el resultado de la consulta
 * @return el valor de objet from result set
 * @throws SQLException Señala que se ha producido una excepción
 */
	private CausalAprobacionDevolucion getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		CausalAprobacionDevolucion lcad_datos;

		lcad_datos = new CausalAprobacionDevolucion();

		lcad_datos.setIdCausalAprobacionDevolucion(ars_rs.getString("ID_CAUSAL_APROBACION_DEVOLUCION"));
		lcad_datos.setCodigo(ars_rs.getString("CODIGO"));
		lcad_datos.setCausalDevolucion(ars_rs.getString("CAUSAL_DEVOLUCION"));
		lcad_datos.setAccion(ars_rs.getString("ACCION"));
		lcad_datos.setIdEtapaDevolucion(ars_rs.getBigDecimal("ID_ETAPA_DEVOLUCION"));
		lcad_datos.setEstado(ars_rs.getString("ESTADO"));
		lcad_datos.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		lcad_datos.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		lcad_datos.setIpCreacion(ars_rs.getString("IP_CREACION"));
		lcad_datos.setIdUsuarioModificacion(ars_rs.getString("ID_USUARIO_MODIFICACION"));
		lcad_datos.setFechaModificacion(ars_rs.getTimestamp("FECHA_MODIFICACION"));
		lcad_datos.setIpModificacion(ars_rs.getString("IP_MODIFICACION"));

		return lcad_datos;
	}
}
