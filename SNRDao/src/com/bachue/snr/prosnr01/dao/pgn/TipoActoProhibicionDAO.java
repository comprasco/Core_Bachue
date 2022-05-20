package com.bachue.snr.prosnr01.dao.pgn;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr01.model.sdb.pgn.TipoActoProhibicion;

import java.math.BigDecimal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase dedicada al manejo de conexion y transacciones en la base de datos que afecten
 * directamente a la tabla SDB_PNG_TIPO_ACTO_PROHIBICION.
 *
 * @author Duvan Beltran
 */
public class TipoActoProhibicionDAO extends AuditoriaDao
{
	/** Constante  cs_FIND_ALL. */
	private static String cs_FIND_ALL = "SELECT * FROM SDB_PNG_TIPO_ACTO_PROHIBICION";

	/** Constante  cs_FIND_BY_ID. */
	private static String cs_FIND_BY_ID = "SELECT * FROM SDB_PNG_TIPO_ACTO_PROHIBICION WHERE ID_TIPO_ACTO_PROHIBICION=?";

	/** Constante  cs_INSERT. */
	private static String cs_INSERT = " INSERT INTO SDB_PNG_TIPO_ACTO_PROHIBICION (ID_TIPO_ACTO_PROHIBICION, ID_TIPO_ACTO, VERSION_ACTO, ID_TIPO_ADQUISICION, TIEMPO_VENCIMIENTO, ID_UNIDAD_TIEMPO, ACTIVO, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION,CANCELACION_AUTOMATICA) VALUES (?,?,?,?,?,?,?,?,SYSTIMESTAMP,?,?)";

	/** Constante  cs_UPDATE. */
	private static String cs_UPDATE = "UPDATE SDB_PNG_TIPO_ACTO_PROHIBICION SET ID_TIPO_ACTO=?, VERSION_ACTO=?, ID_TIPO_ADQUISICION=?, TIEMPO_VENCIMIENTO=?, ID_UNIDAD_TIEMPO=?, ACTIVO=?,ID_USUARIO_MODIFICACION=?, FECHA_MODIFICACION=SYSTIMESTAMP, IP_MODIFICACION=?, CANCELACION_AUTOMATICA=? WHERE ID_TIPO_ACTO_PROHIBICION=?";

	/** Constante cs_SECUENCE. */
	private static String cs_SECUENCE = "SELECT SEC_PNG_TIPO_ACTO_PROHIBICION_ID_TIPO_ACTO_PROHIBICION.NEXTVAL FROM DUAL";

	/**
	 * Consulta en base de datos todos los registros que se encuentren.
	 *
	 * @return Colección de TipoActoProhibicion resultante de la consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<TipoActoProhibicion> findAll()
	    throws B2BException
	{
		Collection<TipoActoProhibicion> lccmv_cmv;
		PreparedStatement               lps_ps;
		ResultSet                       lrs_rs;

		lccmv_cmv     = new ArrayList<TipoActoProhibicion>();
		lps_ps        = null;
		lrs_rs        = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lccmv_cmv.add(getTipoActoProhibicion(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findAll", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lps_ps);
			close(lrs_rs);
		}

		if(!CollectionUtils.isValidCollection(lccmv_cmv))
			lccmv_cmv = null;

		return lccmv_cmv;
	}

	/**
	 * Busca un registro de la tabla por medio de su id_identificador.
	 *
	 * @param atap_param Objeto contenedor de los filtros a usar en la consulta
	 * @return TipoActoProhibicion resultante de la consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoActoProhibicion findById(TipoActoProhibicion atap_param)
	    throws B2BException
	{
		return ((atap_param != null) ? findById(atap_param.getIdTipoActoProhibicion()) : null);
	}

	/**
	 * Busca un registro de la tabla por medio de su id_identificador.
	 *
	 * @param as_param Objeto contenedor de los filtros a usar en la consulta
	 * @return TipoActoProhibicion resultante de la consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoActoProhibicion findById(String as_param)
	    throws B2BException
	{
		TipoActoProhibicion ltd_return;
		ltd_return = null;

		if(as_param != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(1, as_param);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ltd_return = getTipoActoProhibicion(lrs_rs);
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

		return ltd_return;
	}

	/**
	 * Inserta un registro
	 * con la información suministrada.
	 *
	 * @param atd_param objeto contenedor de la información a insertar
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void insert(TipoActoProhibicion atd_param)
	    throws B2BException
	{
		if(atd_param != null)
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

				{
					lps_secuencia     = lc_connection.prepareStatement(cs_SECUENCE);

					lrs_rs = lps_secuencia.executeQuery();

					if(lrs_rs.next())
					{
						Object lo_o;
						lo_o = lrs_rs.getObject(1);

						if((lo_o != null) && lo_o instanceof BigDecimal)
							atd_param.setIdTipoActoProhibicion(StringUtils.getString(lo_o));
						else
							throw new B2BException(ErrorKeys.PGN_LINEA_PRODUCCION_SEQUENCE);
					}
				}

				lps_ps = getConnection().prepareStatement(cs_INSERT);

				lps_ps.setString(li_column++, atd_param.getIdTipoActoProhibicion());
				lps_ps.setString(li_column++, atd_param.getIdTipoActo());
				lps_ps.setString(li_column++, atd_param.getVersionActo());
				lps_ps.setString(li_column++, atd_param.getIdTipoAdquisicion());
				setLong(lps_ps, atd_param.getTiempoVencimiento(), li_column++);
				lps_ps.setString(li_column++, atd_param.getIdUnidadTiempo());
				lps_ps.setString(li_column++, atd_param.getActivo());
				lps_ps.setString(li_column++, atd_param.getIdUsuarioCreacion());
				lps_ps.setString(li_column++, atd_param.getIpCreacion());
				lps_ps.setString(li_column++, atd_param.getCancelacionAutomatica());

				lps_ps.executeUpdate();
			}

			catch(SQLException lse_e)
			{
				logError(this, "insert", lse_e);

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
	 * Actualiza un registro
	 * con la información suministrada.
	 *
	 * @param atd_param objeto contenedor de la información a insertar
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void update(TipoActoProhibicion atd_param)
	    throws B2BException
	{
		if(atd_param != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;

				lps_ps = getConnection().prepareStatement(cs_UPDATE);

				lps_ps.setString(li_column++, atd_param.getIdTipoActo());
				lps_ps.setString(li_column++, atd_param.getVersionActo());
				lps_ps.setString(li_column++, atd_param.getIdTipoAdquisicion());

				setLong(lps_ps, atd_param.getTiempoVencimiento(), li_column++);
				lps_ps.setString(li_column++, atd_param.getIdUnidadTiempo());

				lps_ps.setString(li_column++, atd_param.getActivo());

				lps_ps.setString(li_column++, atd_param.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, atd_param.getIpModificacion());

				lps_ps.setString(li_column++, atd_param.getCancelacionAutomatica());

				lps_ps.setString(li_column++, atd_param.getIdTipoActoProhibicion());

				lps_ps.executeUpdate();
			}

			catch(SQLException lse_e)
			{
				logError(this, "update", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}

			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Permite almacenar los datos que retornó la consulta en los atributos
	 * de un objeto.
	 *
	 * @param ars_rs contenedor del resultado de a consulta
	 * @return lc_datos objeto contenedor de los datos que retornó la consulta
	 * @throws SQLException cuando se produce algun error en el proceso
	 */
	private TipoActoProhibicion getTipoActoProhibicion(ResultSet ars_rs)
	    throws SQLException
	{
		TipoActoProhibicion lc_datos;

		lc_datos = new TipoActoProhibicion();

		lc_datos.setIdTipoActoProhibicion(ars_rs.getString("ID_TIPO_ACTO_PROHIBICION"));
		lc_datos.setIdTipoActo(ars_rs.getString("ID_TIPO_ACTO"));
		lc_datos.setVersionActo(ars_rs.getString("VERSION_ACTO"));
		lc_datos.setIdTipoAdquisicion(ars_rs.getString("ID_TIPO_ADQUISICION"));
		lc_datos.setTiempoVencimiento(getLong(ars_rs, "TIEMPO_VENCIMIENTO"));
		lc_datos.setIdUnidadTiempo(ars_rs.getString("ID_UNIDAD_TIEMPO"));
		lc_datos.setActivo(ars_rs.getString("ACTIVO"));
		lc_datos.setCancelacionAutomatica(ars_rs.getString("CANCELACION_AUTOMATICA"));

		fillAuditoria(ars_rs, lc_datos);

		return lc_datos;
	}
}
