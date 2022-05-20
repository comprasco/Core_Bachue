package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;

import com.bachue.snr.prosnr01.dao.IBase;

import com.bachue.snr.prosnr01.model.sdb.acc.TipoRecepcion;

import java.math.BigDecimal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las consultasd de la tabla SDB_ACC_TIPO_RECEPCION.
 *
 * @author Julian Vaca
 */
public class TipoRecepcionDAO extends BaseDAO implements IBase<TipoRecepcion>
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_ACC_TIPO_RECEPCION " + " WHERE ID_TIPO_RECEPCION=?";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_ACC_TIPO_RECEPCION SET NOMBRE=?, "
		+ " HABILITADO_NOTIFICACION=?, HABILITADO_COMUNICACION=?,PROCEDENCIA=?,ID_USUARIO_MODIFICACION=?,IP_MODIFICACION=?,FECHA_MODIFICACION=SYSTIMESTAMP"
		+ " WHERE ID_TIPO_RECEPCION=?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_ACC_TIPO_RECEPCION("
		+ "ID_TIPO_RECEPCION,NOMBRE, HABILITADO_NOTIFICACION, HABILITADO_COMUNICACION, PROCEDENCIA,IP_CREACION,ID_USUARIO_CREACION,FECHA_CREACION)"
		+ "VALUES(?, ?, ?, ?, ?, ?, ?,SYSTIMESTAMP)";

	/** Constante cs_FIND_BY_HABILITADO_NOT. */
	private static final String cs_FIND_BY_HABILITADO_NOT = "SELECT * FROM SDB_ACC_TIPO_RECEPCION "
		+ " WHERE HABILITADO_NOTIFICACION = ?";

	/** Constante cs_FIND_BY_PROCENDENCIA. */
	private static final String cs_FIND_BY_PROCENDENCIA = "SELECT * FROM SDB_ACC_TIPO_RECEPCION "
		+ " WHERE PROCEDENCIA = ? ORDER BY NOMBRE";

	/** Constante cs_FIND_BY_HABILITADO_REC. */
	private static final String cs_FIND_BY_HABILITADO_REC = "SELECT * FROM SDB_ACC_TIPO_RECEPCION "
		+ " WHERE HABILITADO_COMUNICACION = ?";

	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_ACC_TIPO_RECEPCION ";

	/** Constante cs_FIND_SECUENCETIPORECEPCION. */
	private static final String cs_FIND_SECUENCETIPORECEPCION = "SELECT SEC_ACC_TIPO_RECEPCION_ID_TIPO_RECEPCION.NEXTVAL FROM DUAL";

	/**
	 * Retorna el valor del objeto de tipo collection de  TipoRecepcion
	 *
	 * @return devuelve el valor del objeto collection de TipoRecepcion
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<TipoRecepcion> findAll()
	    throws B2BException
	{
		Collection<TipoRecepcion> ltr_tipoRecepcion;
		PreparedStatement         lps_ps;
		ResultSet                 lrs_rs;

		ltr_tipoRecepcion     = new ArrayList<TipoRecepcion>();
		lps_ps                = null;
		lrs_rs                = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				ltr_tipoRecepcion.add(getTipoRecepcion(lrs_rs));
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

		if(ltr_tipoRecepcion.isEmpty())
			ltr_tipoRecepcion = null;

		return ltr_tipoRecepcion;
	}

	/**
	 * Retorna el valor del objeto de tipo collection de  TipoRecepcion
	 *
	 * @return devuelve el valor del objeto collection de TipoRecepcion
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<TipoRecepcion> findAllOrdenado()
	    throws B2BException
	{
		Collection<TipoRecepcion> ltr_tipoRecepcion;
		PreparedStatement         lps_ps;
		ResultSet                 lrs_rs;

		ltr_tipoRecepcion     = new ArrayList<TipoRecepcion>();
		lps_ps                = null;
		lrs_rs                = null;

		try
		{
			StringBuilder sb_consulta;

			sb_consulta = new StringBuilder();

			sb_consulta.append(cs_FIND_ALL);
			sb_consulta.append("ORDER BY NOMBRE ASC");
			lps_ps     = getConnection().prepareStatement(sb_consulta.toString());

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				ltr_tipoRecepcion.add(getTipoRecepcion(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findAllOrdenado", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(ltr_tipoRecepcion.isEmpty())
			ltr_tipoRecepcion = null;

		return ltr_tipoRecepcion;
	}

	/**
	 * Retorna el valor del objeto de tipo Find by habilitado.
	 *
	 * @param at_param correspondiente al valor del tipo de objeto TipoRecepcion
	 * @param ab_not correspondiente al valor del tipo de objeto boolean
	 * @param ab_personaJuridica correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor del objeto collection
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<TipoRecepcion> findByHabilitado(
	    TipoRecepcion at_param, boolean ab_not, boolean ab_personaJuridica
	)
	    throws B2BException
	{
		Collection<TipoRecepcion> lctr_datos;

		lctr_datos = new ArrayList<TipoRecepcion>();

		if(at_param != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;
			StringBuilder     lsb_consulta;

			lps_ps           = null;
			lrs_rs           = null;
			lsb_consulta     = new StringBuilder();

			try
			{
				if(ab_not)
					lsb_consulta.append(cs_FIND_BY_HABILITADO_NOT);
				else
					lsb_consulta.append(cs_FIND_BY_HABILITADO_REC);

				if(ab_personaJuridica)
					lsb_consulta.append(" AND NOMBRE <> 'DIRECCION RESIDENCIA'");

				{
					String ls_soloIncluir;

					ls_soloIncluir = at_param.getSoloIncluir();

					if(StringUtils.isValidString(ls_soloIncluir))
					{
						lsb_consulta.append(" AND ID_TIPO_RECEPCION IN (");
						lsb_consulta.append(ls_soloIncluir);
						lsb_consulta.append(")");
					}
				}

				lps_ps = getConnection().prepareStatement(lsb_consulta.toString());

				lps_ps.setString(
				    1, ab_not ? at_param.getHabilitadoNotificacion() : at_param.getHabilitadoComunicacion()
				);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lctr_datos.add(getTipoRecepcion(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByHabilitado", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lctr_datos.isEmpty())
			lctr_datos = null;

		return lctr_datos;
	}

	/** {@inheritdoc} */
	@Override
	public TipoRecepcion findById(TipoRecepcion at_param)
	    throws B2BException
	{
		TipoRecepcion     ls_object;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		ls_object     = null;
		lps_ps        = null;
		lrs_rs        = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

			lps_ps.setString(1, at_param.getIdTipoRecepcion());

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				ls_object = getTipoRecepcion(lrs_rs);
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

		return ls_object;
	}

	/**
	 * Metodo encargado de buscar un tipo recepcion con su id
	 * @param as_param id de tipo de recepcion
	 * @return Objeto de tipo <code>TipoRecepcion</code> con el resultado de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public TipoRecepcion findById(String as_param)
	    throws B2BException
	{
		TipoRecepcion     ls_object;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		ls_object     = null;
		lps_ps        = null;
		lrs_rs        = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

			lps_ps.setString(1, as_param);

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				ls_object = getTipoRecepcion(lrs_rs);
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

		return ls_object;
	}

	/**
	 * Retorna el valor del objeto de tipo Collection de TipoRecepcion filtrado por procedencia
	 *
	 * @param at_param correspondiente al valor del tipo de objeto TipoRecepcion
	 * @return devuelve el valor del objeto collection de TipoRecepcion
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<TipoRecepcion> findByProcedencia(TipoRecepcion at_param)
	    throws B2BException
	{
		Collection<TipoRecepcion> lctr_datos;
		PreparedStatement         lps_ps;
		ResultSet                 lrs_rs;

		lctr_datos     = new ArrayList<TipoRecepcion>();
		lps_ps         = null;
		lrs_rs         = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_BY_PROCENDENCIA);

			lps_ps.setString(1, at_param.getProcedencia());

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lctr_datos.add(getTipoRecepcion(lrs_rs));
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

		if(lctr_datos.isEmpty())
			lctr_datos = null;

		return lctr_datos;
	}

	/** {@inheritdoc} */
	@Override
	public void insertOrUpdate(TipoRecepcion ac_parametros, boolean ab_query)
	    throws B2BException
	{
		if(ac_parametros != null)
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
					lps_secuencia     = lc_connection.prepareStatement(cs_FIND_SECUENCETIPORECEPCION);

					lrs_rs = lps_secuencia.executeQuery();

					if(lrs_rs.next())
					{
						Object lo_o;
						lo_o = lrs_rs.getObject(1);

						if((lo_o != null) && lo_o instanceof BigDecimal)
							ac_parametros.setIdTipoRecepcion(StringUtils.getString(lo_o));
						else
							throw new B2BException(ErrorKeys.PGN_LINEA_PRODUCCION_SEQUENCE);
					}

					lps_ps.setString(li_column++, ac_parametros.getIdTipoRecepcion());
				}

				lps_ps.setString(li_column++, ac_parametros.getNombre());
				lps_ps.setString(li_column++, ac_parametros.getHabilitadoNotificacion());
				lps_ps.setString(li_column++, ac_parametros.getHabilitadoComunicacion());
				lps_ps.setString(li_column++, ac_parametros.getProcedencia());

				if(ab_query)
				{
					lps_ps.setString(li_column++, ac_parametros.getIpCreacion());
					lps_ps.setString(li_column++, ac_parametros.getIdUsuarioCreacion());
				}
				else
				{
					lps_ps.setString(li_column++, ac_parametros.getIdUsuarioModificacion());
					lps_ps.setString(li_column++, ac_parametros.getIpModificacion());
					lps_ps.setString(li_column++, ac_parametros.getIdTipoRecepcion());
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
	 * Retorna el valor de tipo recepcion.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de tipo recepcion
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private TipoRecepcion getTipoRecepcion(ResultSet ars_rs)
	    throws SQLException
	{
		TipoRecepcion lr_datos;
		lr_datos = new TipoRecepcion();

		lr_datos.setIdTipoRecepcion(ars_rs.getString("ID_TIPO_RECEPCION"));
		lr_datos.setNombre(ars_rs.getString("NOMBRE"));
		lr_datos.setHabilitadoNotificacion(ars_rs.getString("HABILITADO_NOTIFICACION"));
		lr_datos.setHabilitadoComunicacion(ars_rs.getString("HABILITADO_COMUNICACION"));
		lr_datos.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		lr_datos.setIdUsuarioModificacion(ars_rs.getString("ID_USUARIO_MODIFICACION"));
		lr_datos.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		lr_datos.setProcedencia(ars_rs.getString("PROCEDENCIA"));
		lr_datos.setIpCreacion(ars_rs.getString("IP_CREACION"));
		lr_datos.setFechaModificacion(ars_rs.getTimestamp("FECHA_MODIFICACION"));
		lr_datos.setIpModificacion(ars_rs.getString("IP_MODIFICACION"));

		return lr_datos;
	}
}
