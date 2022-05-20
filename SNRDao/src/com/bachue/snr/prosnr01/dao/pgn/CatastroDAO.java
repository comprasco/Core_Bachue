package com.bachue.snr.prosnr01.dao.pgn;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr01.model.sdb.pgn.Catastro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase dedicada al manejo de conexion y transacciones en la base de datos que afecten
 * directamente a la tabla SDB_PGN_CATASTRO.
 *
 * @author Sebastian Sanchez
 */
public class CatastroDAO extends AuditoriaDao
{
	/** Constante cs_BUSCAR_IDS_CATASTROS_ACTIVOS. */
	private static final String cs_BUSCAR_IDS_CATASTROS_ACTIVOS = "SELECT * FROM SDB_PGN_CATASTRO ";

	/** Constante  cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_PGN_CATASTRO ";

	/** Constante  cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_PGN_CATASTRO WHERE ID_CATASTRO = ?";

	/** Constante  cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_PGN_CATASTRO(ID_CATASTRO, NOMBRE, ID_PAIS, ID_DEPARTAMENTO, ID_MUNICIPIO, DIRECCION, CORREO_ELECTRONICO, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION) VALUES(?, ?, ?, ?, ? , ?, ?, ?, SYSTIMESTAMP, ?)";

	/** Constante  cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_PGN_CATASTRO SET NOMBRE=?, ID_PAIS=?, ID_DEPARTAMENTO=?, ID_MUNICIPIO=?, DIRECCION=?, CORREO_ELECTRONICO=?, ID_USUARIO_MODIFICACION=?, IP_MODIFICACION=?, FECHA_MODIFICACION = SYSTIMESTAMP WHERE ID_CATASTRO=?";

	/**
	 * Consulta en base de datos todos los registros que se encuentren existentes.
	 *
	 * @return Colección de Catastro resultante de la consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Catastro> findAll()
	    throws B2BException
	{
		Collection<Catastro> lcc_cc;
		PreparedStatement    lps_ps;
		ResultSet            lrs_rs;

		lcc_cc     = new ArrayList<Catastro>();
		lps_ps     = null;
		lrs_rs     = null;

		try
		{
			StringBuilder lsb_sb;

			lsb_sb     = new StringBuilder(cs_FIND_ALL);

			lps_ps     = getConnection().prepareStatement(lsb_sb.toString());

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcc_cc.add(getObjetFromResultSet(lrs_rs));
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

		if(lcc_cc.isEmpty())
			lcc_cc = null;

		return lcc_cc;
	}

	/**
	 * Busca un registro de la tabla por medio de su id_identificador.
	 *
	 * @param as_param Objeto contenedor de los filtros a usar en la consulta
	 * @return Catastro resultante de la consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Catastro findById(String as_param)
	    throws B2BException
	{
		Catastro lc_param;

		lc_param = null;

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
					lc_param = getObjetFromResultSet(lrs_rs);
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

		return lc_param;
	}

	/**
	 * Inserta un registro
	 * con la información suministrada.
	 *
	 * @param ac_param objeto contenedor de la información a insertar
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void insert(Catastro ac_param)
	    throws B2BException
	{
		if(ac_param != null)
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

				lps_ps.setString(li_column++, ac_param.getIdCatastro());
				lps_ps.setString(li_column++, ac_param.getNombre());
				lps_ps.setString(li_column++, ac_param.getIdPais());
				lps_ps.setString(li_column++, ac_param.getIdDepartamento());
				lps_ps.setString(li_column++, ac_param.getIdMunicipio());
				lps_ps.setString(li_column++, ac_param.getDireccion());
				lps_ps.setString(li_column++, ac_param.getCorreoElectronico());
				lps_ps.setString(li_column++, ac_param.getIdUsuarioCreacion());
				lps_ps.setString(li_column++, ac_param.getIpCreacion());

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
	 * @param ac_param objeto contenedor de la información a insertar
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void update(Catastro ac_param)
	    throws B2BException
	{
		if(ac_param != null)
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

				lps_ps.setString(li_column++, ac_param.getNombre());
				lps_ps.setString(li_column++, ac_param.getIdPais());
				lps_ps.setString(li_column++, ac_param.getIdDepartamento());
				lps_ps.setString(li_column++, ac_param.getIdMunicipio());
				lps_ps.setString(li_column++, ac_param.getDireccion());
				lps_ps.setString(li_column++, ac_param.getCorreoElectronico());
				lps_ps.setString(li_column++, ac_param.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, ac_param.getIpModificacion());
				lps_ps.setString(li_column++, ac_param.getIdCatastro());

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
	 * Consulta en base de datos todos los registros que se encuentren activos.
	 *
	 * @return Colección de String resultante de la consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<String> buscarIdsCatastrosActivos()
	    throws B2BException
	{
		Collection<String> lcc_cc;
		PreparedStatement  lps_ps;
		ResultSet          lrs_rs;

		lcc_cc     = new ArrayList<String>();
		lps_ps     = null;
		lrs_rs     = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_BUSCAR_IDS_CATASTROS_ACTIVOS);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcc_cc.add(lrs_rs.getString("ID_CATASTRO"));
		}
		catch(SQLException lse_e)
		{
			logError(this, "buscarIdsCatastrosActivos", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(lcc_cc.isEmpty())
			lcc_cc = null;

		return lcc_cc;
	}

	/**
	 * Permite almacenar los datos que retornó la consulta en los atributos
	 * de un objeto.
	 *
	 * @param lrs_rs contenedor del resultado de a consulta
	 * @return objeto contenedor de los datos que retornó la consulta
	 * @throws SQLException cuando se produce algun error en el proceso
	 */
	private Catastro getObjetFromResultSet(ResultSet lrs_rs)
	    throws SQLException
	{
		Catastro lc_catastro;

		lc_catastro = new Catastro();

		lc_catastro.setIdCatastro(lrs_rs.getString("ID_CATASTRO"));
		lc_catastro.setNombre(lrs_rs.getString("NOMBRE"));
		lc_catastro.setIdPais(lrs_rs.getString("ID_PAIS"));
		lc_catastro.setIdDepartamento(lrs_rs.getString("ID_DEPARTAMENTO"));
		lc_catastro.setIdMunicipio(lrs_rs.getString("ID_MUNICIPIO"));
		lc_catastro.setDireccion(lrs_rs.getString("DIRECCION"));
		lc_catastro.setCorreoElectronico(lrs_rs.getString("CORREO_ELECTRONICO"));
		lc_catastro.setIdUsuarioCreacion(lrs_rs.getString("ID_USUARIO_CREACION"));
		lc_catastro.setFechaCreacion(lrs_rs.getTimestamp("FECHA_CREACION"));
		lc_catastro.setIpCreacion(lrs_rs.getString("IP_CREACION"));
		lc_catastro.setIdUsuarioModificacion(lrs_rs.getString("ID_USUARIO_MODIFICACION"));
		lc_catastro.setFechaModificacion(lrs_rs.getTimestamp("FECHA_MODIFICACION"));
		lc_catastro.setIpModificacion(lrs_rs.getString("IP_MODIFICACION"));

		return lc_catastro;
	}
}
