package com.bachue.snr.prosnr01.dao.png;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.model.sdb.png.CirculoOrigenDestino;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


/**
 * Clase dedicada al manejo de conexion y transacciones en la base de datos que afecten
 * directamente a la tabla SDB_PNG_CIRCULO_ORIGEN_DESTINO.
 *
 * @author Gabriel Arias
 */
public class CirculoOrigenDestinoDAO extends com.b2bsg.common.dataAccess2.BaseDAO
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_PNG_CIRCULO_ORIGEN_DESTINO WHERE ID_CIRCULO_ORIGEN = ? AND ID_CIRCULO_DESTINO = ?";

	/** Constante cs_FIND_BY_ID_CIRCULO_ORIGEN. */
	private static final String cs_FIND_BY_ID_CIRCULO_ORIGEN = "SELECT * FROM SDB_PNG_CIRCULO_ORIGEN_DESTINO WHERE ID_CIRCULO_ORIGEN = ?";

	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_PNG_CIRCULO_ORIGEN_DESTINO";

	/** Constante cs_FIND_ALL_ACTIVO. */
	private static final String cs_FIND_ALL_ACTIVO = "SELECT * FROM SDB_PNG_CIRCULO_ORIGEN_DESTINO WHERE ACTIVO = 'S'";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_PNG_CIRCULO_ORIGEN_DESTINO SET ACTIVO = ?, ID_USUARIO_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP, IP_MODIFICACION = ? WHERE ID_CIRCULO_ORIGEN = ? AND ID_CIRCULO_DESTINO = ?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_PNG_CIRCULO_ORIGEN_DESTINO (ID_CIRCULO_ORIGEN,ID_CIRCULO_DESTINO,ACTIVO,ID_USUARIO_CREACION,FECHA_CREACION,IP_CREACION) VALUES (?,?,?,?,SYSTIMESTAMP,?)";

	/**
	 * Consulta todos los registros de la tabla.
	 *
	 * @param ab_validarActivo Boolean que válida si consulta los registros activos o todos.
	 * @return Colección con todos los registros resultantes de la consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<CirculoOrigenDestino> findAll(boolean ab_validarActivo)
	    throws B2BException
	{
		Collection<CirculoOrigenDestino> lccod_return;
		PreparedStatement                lps_ps;
		ResultSet                        lrs_rs;

		lccod_return     = new ArrayList<CirculoOrigenDestino>();
		lps_ps           = null;
		lrs_rs           = null;

		try
		{
			StringBuilder lsb_sb;

			lsb_sb = new StringBuilder();

			if(ab_validarActivo)
				lsb_sb.append(cs_FIND_ALL_ACTIVO);
			else
				lsb_sb.append(cs_FIND_ALL);

			lps_ps     = getConnection().prepareStatement(lsb_sb.toString());
			lrs_rs     = lps_ps.executeQuery();

			while(lrs_rs.next())
				lccod_return.add(getObjectFromResult(lrs_rs));
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

		if(lccod_return.isEmpty())
			lccod_return = null;

		return lccod_return;
	}

	/**
	 * Consulta todos los registros relacionados con el circulo origen y destino.
	 *
	 * @param as_origen Objeto contenedor del id circulo origen que será utilizado como filtro en la consulta
	 * @param as_destino Objeto contenedor del id circulo destino que será utilizado como filtro en la consulta
	 * @return Objeto contenedor del resultado de la consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public CirculoOrigenDestino findById(String as_origen, String as_destino)
	    throws B2BException
	{
		CirculoOrigenDestino lcod_return;

		lcod_return = null;

		if(StringUtils.isValidString(as_origen) && StringUtils.isValidString(as_destino))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_contador;

				li_contador     = 1;
				lps_ps          = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(li_contador++, as_origen);
				lps_ps.setString(li_contador++, as_destino);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lcod_return = getObjectFromResult(lrs_rs);
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

		return lcod_return;
	}

	/**
	 * Consulta todos los registros relacionados con el circulo origen y destino.
	 *
	 * @param as_origen Objeto contenedor del id circulo origen que será utilizado como filtro en la consulta
	 * @return Objeto contenedor del resultado de la consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Map<String, String> findByIdCirculoOrigen(String as_origen)
	    throws B2BException
	{
		Map<String, String> lmss_return;

		lmss_return = new HashMap<String, String>();

		if(StringUtils.isValidString(as_origen))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_contador;

				li_contador     = 1;
				lps_ps          = getConnection().prepareStatement(cs_FIND_BY_ID_CIRCULO_ORIGEN);

				lps_ps.setString(li_contador++, as_origen);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
				{
					String ls_idCirculoDestino;

					ls_idCirculoDestino = lrs_rs.getString("ID_CIRCULO_DESTINO");

					if(StringUtils.isValidString(ls_idCirculoDestino))
						lmss_return.put(ls_idCirculoDestino, ls_idCirculoDestino);
				}
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdCirculoOrigen", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lmss_return.isEmpty())
			lmss_return = null;

		return lmss_return;
	}

	/**
	 * Inserta un registro de la tabla SDB_PNG_CIRCULO_ORIGEN_DESTINO.
	 *
	 * @param acod_parametros de acod parametros
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void insert(CirculoOrigenDestino acod_parametros)
	    throws B2BException
	{
		if(acod_parametros != null)
		{
			PreparedStatement lps_ps;

			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_INSERT);

				lps_ps.setString(li_column++, acod_parametros.getIdCirculoOrigen());
				lps_ps.setString(li_column++, acod_parametros.getIdCirculoDestino());
				lps_ps.setString(li_column++, acod_parametros.getActivo());
				lps_ps.setString(li_column++, acod_parametros.getIdUsuarioCreacion());
				lps_ps.setString(li_column++, acod_parametros.getIpCreacion());

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
			}
		}
	}

	/**
	 * Actualiza un registro de la tabla SDB_PNG_CIRCULO_ORIGEN_DESTINO.
	 *
	 * @param acod_parametros de acod parametros
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void update(CirculoOrigenDestino acod_parametros)
	    throws B2BException
	{
		if(acod_parametros != null)
		{
			PreparedStatement lps_ps;

			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_UPDATE);

				lps_ps.setString(li_column++, acod_parametros.getActivo());

				lps_ps.setString(li_column++, acod_parametros.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, acod_parametros.getIpModificacion());
				lps_ps.setString(li_column++, acod_parametros.getIdCirculoOrigen());
				lps_ps.setString(li_column++, acod_parametros.getIdCirculoDestino());

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
	 * Extrae la información de un registro de base de datos, la asigna a un objeto y la retorna.
	 *
	 * @param ars_rs Objeto contenedor de los resultados de la consulta
	 * @return Objeto circulo origen destino con la información recuperada de la base de datos
	 * @throws SQLException cuando se produce algun error en el proceso
	 */
	private CirculoOrigenDestino getObjectFromResult(ResultSet ars_rs)
	    throws SQLException
	{
		CirculoOrigenDestino lcod_return;

		lcod_return = new CirculoOrigenDestino();

		lcod_return.setIdCirculoOrigen(ars_rs.getString("ID_CIRCULO_ORIGEN"));
		lcod_return.setIdCirculoDestino(ars_rs.getString("ID_CIRCULO_DESTINO"));
		lcod_return.setActivo(ars_rs.getString("ACTIVO"));
		lcod_return.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		lcod_return.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		lcod_return.setIpCreacion(ars_rs.getString("IP_CREACION"));
		lcod_return.setIdUsuarioModificacion(ars_rs.getString("ID_USUARIO_MODIFICACION"));
		lcod_return.setFechaModificacion(ars_rs.getTimestamp("FECHA_MODIFICACION"));
		lcod_return.setIpModificacion(ars_rs.getString("IP_MODIFICACION"));

		return lcod_return;
	}
}
