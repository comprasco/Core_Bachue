package com.bachue.snr.prosnr01.dao.pgn;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr01.model.sdb.pgn.TipoCriterioBusquedaPGN;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase dedicada al manejo de conexion y transacciones en la base de datos que afecten
 * directamente a la tabla SDB_PGN_TIPO_CRITERIO_BUSQUEDA.
 *
 * @author Sebastian Sanchez
 */
public class TipoCriterioBusquedaPGNDAO extends AuditoriaDao
{
	/** Constante  cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_PGN_TIPO_CRITERIO_BUSQUEDA ";

	/** Constante  cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_PGN_TIPO_CRITERIO_BUSQUEDA WHERE ID_TIPO_CRITERIO_BUSQUEDA= ?";

	/** Constante  cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_PGN_TIPO_CRITERIO_BUSQUEDA(ID_TIPO_CRITERIO_BUSQUEDA, DESCRIPCION, ACTIVO, CRITERIO_CONDICION, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION) VALUES(?, ?, ?, ?, ? , SYSTIMESTAMP, ?)";

	/** Constante  cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_PGN_TIPO_CRITERIO_BUSQUEDA SET DESCRIPCION=?, ACTIVO=?, CRITERIO_CONDICION=?, ID_USUARIO_MODIFICACION=?, IP_MODIFICACION=?, FECHA_MODIFICACION = SYSTIMESTAMP WHERE ID_TIPO_CRITERIO_BUSQUEDA=?";

	/** Constante cs_FIND_SECUENCIA. */
	private static final String cs_FIND_SECUENCIA = "SELECT SEC_PGN_TIPO_CRITERIO_BUSQUEDA_ID_TIPO_CRITERIO_BUSQUEDA.NEXTVAL FROM DUAL";

	/**
	 * Consulta en base de datos todos los registros que se encuentren existentes.
	 *
	 * @return Colección de TipoCriterioBusquedaPGN resultante de la consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<TipoCriterioBusquedaPGN> findAll()
	    throws B2BException
	{
		Collection<TipoCriterioBusquedaPGN> lctcb_ctcb;
		PreparedStatement                   lps_ps;
		ResultSet                           lrs_rs;

		lctcb_ctcb     = new ArrayList<TipoCriterioBusquedaPGN>();
		lps_ps         = null;
		lrs_rs         = null;

		try
		{
			StringBuilder lsb_sb;

			lsb_sb     = new StringBuilder(cs_FIND_ALL);

			lps_ps     = getConnection().prepareStatement(lsb_sb.toString());

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lctcb_ctcb.add(getObjetFromResultSet(lrs_rs));
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

		if(lctcb_ctcb.isEmpty())
			lctcb_ctcb = null;

		return lctcb_ctcb;
	}

	/**
	 * Busca un registro de la tabla por medio de su id_identificador.
	 *
	 * @param aae_param Objeto de tipo TipoCriterioBusquedaPGN contenedor de los filtros a usar en la consulta
	 * @return TipoCriterioBusquedaPGN resultante de la consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoCriterioBusquedaPGN findById(TipoCriterioBusquedaPGN atcb_param)
	    throws B2BException
	{
		return (atcb_param != null) ? findById(atcb_param.getIdTipoCriterioBusqueda()) : null;
	}

	/**
	 * Busca un registro de la tabla por medio de su id_identificador.
	 *
	 * @param as_param Objeto contenedor de los filtros a usar en la consulta
	 * @return TipoCriterioBusquedaPGN resultante de la consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoCriterioBusquedaPGN findById(String as_param)
	    throws B2BException
	{
		TipoCriterioBusquedaPGN ltcb_param;

		ltcb_param = null;

		if(StringUtils.isValidString(as_param))
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
					ltcb_param = getObjetFromResultSet(lrs_rs);
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

		return ltcb_param;
	}

	/**
	 * Inserta un registro
	 * con la información suministrada.
	 *
	 * @param atcb_param objeto contenedor de la información a insertar
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void insert(TipoCriterioBusquedaPGN atcb_param)
	    throws B2BException
	{
		if(atcb_param != null)
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
				Connection lc_C;

				lc_C       = getConnection();
				lps_ps     = lc_C.prepareStatement(cs_INSERT);

				lps_secuencia     = lc_C.prepareStatement(cs_FIND_SECUENCIA);

				lrs_rs = lps_secuencia.executeQuery();

				if(lrs_rs.next())
					lps_ps.setString(li_column++, StringUtils.getString(lrs_rs.getInt(1)));
				else
					throw new B2BException(ErrorKeys.ERROR_GENERANDO_SECUENCIA);

				{
					lps_ps.setString(li_column++, atcb_param.getDescripcion());
					lps_ps.setString(li_column++, atcb_param.getActivo());
					lps_ps.setString(li_column++, atcb_param.getCriterioCondicion());
					lps_ps.setString(li_column++, atcb_param.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, atcb_param.getIpCreacion());
				}

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
				close(lrs_rs);
				close(lps_secuencia);
			}
		}
	}

	/**
	 * Actualiza un registro
	 * con la información suministrada.
	 *
	 * @param atcb_param objeto contenedor de la información a insertar
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void update(TipoCriterioBusquedaPGN atcb_param)
	    throws B2BException
	{
		if(atcb_param != null)
		{
			PreparedStatement lps_ps;

			lps_ps = null;

			try
			{
				int           li_column;
				StringBuilder lsb_query;

				li_column     = 1;
				lsb_query     = new StringBuilder(cs_UPDATE);

				lps_ps = getConnection().prepareStatement(lsb_query.toString());

				lps_ps.setString(li_column++, atcb_param.getDescripcion());
				lps_ps.setString(li_column++, atcb_param.getActivo());
				lps_ps.setString(li_column++, atcb_param.getCriterioCondicion());
				lps_ps.setString(li_column++, atcb_param.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, atcb_param.getIpModificacion());
				lps_ps.setString(li_column++, atcb_param.getIdTipoCriterioBusqueda());

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
	 * @param lrs_rs contenedor del resultado de a consulta
	 * @return objeto contenedor de los datos que retornó la consulta
	 * @throws SQLException cuando se produce algun error en el proceso
	 */
	private TipoCriterioBusquedaPGN getObjetFromResultSet(ResultSet lrs_rs)
	    throws SQLException
	{
		TipoCriterioBusquedaPGN ls_solicitud;

		ls_solicitud = new TipoCriterioBusquedaPGN();

		ls_solicitud.setIdTipoCriterioBusqueda(lrs_rs.getString("ID_TIPO_CRITERIO_BUSQUEDA"));
		ls_solicitud.setCriterioCondicion(lrs_rs.getString("CRITERIO_CONDICION"));
		ls_solicitud.setDescripcion(lrs_rs.getString("DESCRIPCION"));
		ls_solicitud.setActivo(lrs_rs.getString("ACTIVO"));
		ls_solicitud.setIdUsuarioCreacion(lrs_rs.getString("ID_USUARIO_CREACION"));
		ls_solicitud.setFechaCreacion(lrs_rs.getTimestamp("FECHA_CREACION"));
		ls_solicitud.setIpCreacion(lrs_rs.getString("IP_CREACION"));
		ls_solicitud.setIdUsuarioModificacion(lrs_rs.getString("ID_USUARIO_MODIFICACION"));
		ls_solicitud.setFechaModificacion(lrs_rs.getTimestamp("FECHA_MODIFICACION"));
		ls_solicitud.setIpModificacion(lrs_rs.getString("IP_MODIFICACION"));

		return ls_solicitud;
	}
}
