package com.bachue.snr.prosnr01.dao.pgn;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr01.model.sdb.pgn.CamposCertificado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase dedicada al manejo de conexion y transacciones en la base de datos que afecten
 * directamente a la tabla SDB_PGN_CAMPOS_CERTIFICADO.
 *
 * @author Sebastian Sanchez
 */
public class CamposCertificadoDAO extends AuditoriaDao
{
	/** Constante  cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_PGN_CAMPOS_CERTIFICADO ";

	/** Constante  cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_PGN_CAMPOS_CERTIFICADO WHERE PLANTILLA = ? AND CAMPO = ?";

	/** Constante  cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_PGN_CAMPOS_CERTIFICADO(PLANTILLA, CAMPO, ACTIVO, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION) VALUES(?, ?, ?, ?, SYSTIMESTAMP, ?)";

	/** Constante  cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_PGN_CAMPOS_CERTIFICADO SET ACTIVO=?, ID_USUARIO_MODIFICACION=?, IP_MODIFICACION=?, FECHA_MODIFICACION = SYSTIMESTAMP WHERE PLANTILLA=? AND CAMPO=?";

	/**
	 * Consulta en base de datos todos los registros que se encuentren existentes.
	 *
	 * @return Colección de CamposCertificado resultante de la consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<CamposCertificado> findAll()
	    throws B2BException
	{
		Collection<CamposCertificado> lccc_ccc;
		PreparedStatement             lps_ps;
		ResultSet                     lrs_rs;

		lccc_ccc     = new ArrayList<CamposCertificado>();
		lps_ps       = null;
		lrs_rs       = null;

		try
		{
			StringBuilder lsb_sb;

			lsb_sb     = new StringBuilder(cs_FIND_ALL);

			lps_ps     = getConnection().prepareStatement(lsb_sb.toString());

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lccc_ccc.add(getObjetFromResultSet(lrs_rs));
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

		if(lccc_ccc.isEmpty())
			lccc_ccc = null;

		return lccc_ccc;
	}

	/**
	 * Busca un registro de la tabla por medio de su id_identificador.
	 *
	 * @param acc_param Objeto de tipo CamposCertificado contenedor de los filtros a usar en la consulta
	 * @return CamposCertificado resultante de la consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public CamposCertificado findById(CamposCertificado acc_param)
	    throws B2BException
	{
		return (acc_param != null) ? findById(acc_param.getPlantilla(), acc_param.getCampo()) : null;
	}

	/**
	 * Busca un registro de la tabla por medio de su id_identificador.
	 *
	 * @param as_plantilla Objeto contenedor de los filtros a usar en la consulta
	 * @param as_campo Objeto contenedor de los filtros a usar en la consulta
	 * @return CamposCertificado resultante de la consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public CamposCertificado findById(String as_plantilla, String as_campo)
	    throws B2BException
	{
		CamposCertificado lcc_param;

		lcc_param = null;

		if(StringUtils.isValidString(as_plantilla) && StringUtils.isValidString(as_campo))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(1, as_plantilla);
				lps_ps.setString(2, as_campo);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lcc_param = getObjetFromResultSet(lrs_rs);
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

		return lcc_param;
	}

	/**
	 * Inserta un registro
	 * con la información suministrada.
	 *
	 * @param acc_param objeto contenedor de la información a insertar
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void insert(CamposCertificado acc_param)
	    throws B2BException
	{
		if(acc_param != null)
		{
			int               li_column;
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps        = null;
			lrs_rs        = null;
			li_column     = 1;

			try
			{
				Connection lc_C;

				lc_C       = getConnection();
				lps_ps     = lc_C.prepareStatement(cs_INSERT);

				lps_ps.setString(li_column++, acc_param.getPlantilla());
				lps_ps.setString(li_column++, acc_param.getCampo());
				lps_ps.setString(li_column++, acc_param.getActivo());
				lps_ps.setString(li_column++, acc_param.getIdUsuarioCreacion());
				lps_ps.setString(li_column++, acc_param.getIpCreacion());

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
			}
		}
	}

	/**
	 * Actualiza un registro
	 * con la información suministrada.
	 *
	 * @param aae_param objeto contenedor de la información a insertar
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void update(CamposCertificado acc_param)
	    throws B2BException
	{
		if(acc_param != null)
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

				lps_ps.setString(li_column++, acc_param.getActivo());
				lps_ps.setString(li_column++, acc_param.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, acc_param.getIpModificacion());
				lps_ps.setString(li_column++, acc_param.getPlantilla());
				lps_ps.setString(li_column++, acc_param.getCampo());

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
	private CamposCertificado getObjetFromResultSet(ResultSet lrs_rs)
	    throws SQLException
	{
		CamposCertificado ls_solicitud;

		ls_solicitud = new CamposCertificado();

		ls_solicitud.setPlantilla(lrs_rs.getString("PLANTILLA"));
		ls_solicitud.setCampo(lrs_rs.getString("CAMPO"));
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
