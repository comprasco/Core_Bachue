package com.bachue.snr.prosnr01.dao.pgn;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr01.model.sdb.pgn.CirculoCatastro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase dedicada al manejo de conexion y transacciones en la base de datos que afecten
 * directamente a la tabla SDB_PGN_CIRCULO_CATASTRO.
 *
 * @author Sebastian Sanchez
 */
public class CirculoCatastroDAO extends AuditoriaDao
{
	/** Constante  cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_PGN_CIRCULO_CATASTRO ";

	/** Constante  cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_PGN_CIRCULO_CATASTRO WHERE ID_CIRCULO = ? AND ID_CATASTRO=?";

	/** Constante  cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_PGN_CIRCULO_CATASTRO(ID_CATASTRO_CIRCULO, ID_CIRCULO, ID_CATASTRO, ACTIVO, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION) VALUES(?, ?, ?, ? , ?, SYSTIMESTAMP, ?)";

	/** Constante  cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_PGN_CIRCULO_CATASTRO SET ID_CATASTRO_CIRCULO=?, ACTIVO=?, ID_USUARIO_MODIFICACION=?, IP_MODIFICACION=?, FECHA_MODIFICACION = SYSTIMESTAMP WHERE ID_CIRCULO=? AND ID_CATASTRO=?";

	/** Constante cs_FIND_SECUENCIA. */
	private static final String cs_FIND_SECUENCIA = "SELECT SEC_PGN_CIRCULO_CATASTRO_ID_CATASTRO_CIRCULO.NEXTVAL FROM DUAL";

	/**
	 * Consulta en base de datos todos los registros que se encuentren existentes.
	 *
	 * @return Colección de CirculoCatastro resultante de la consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<CirculoCatastro> findAll()
	    throws B2BException
	{
		Collection<CirculoCatastro> lccc_ccc;
		PreparedStatement           lps_ps;
		ResultSet                   lrs_rs;

		lccc_ccc     = new ArrayList<CirculoCatastro>();
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
	 * @param as_idCirculo Objeto contenedor de los filtros a usar en la consulta
	 * @param as_idCatastro Objeto contenedor de los filtros a usar en la consulta
	 * @return CirculoCatastro resultante de la consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public CirculoCatastro findById(String as_idCirculo, String as_idCatastro)
	    throws B2BException
	{
		CirculoCatastro lcc_param;

		lcc_param = null;

		if(StringUtils.isValidString(as_idCirculo) && StringUtils.isValidString(as_idCatastro))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(1, as_idCirculo);
				lps_ps.setString(2, as_idCatastro);

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
	public void insert(CirculoCatastro acc_param)
	    throws B2BException
	{
		if(acc_param != null)
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
					lps_ps.setString(li_column++, acc_param.getIdCirculo());
					lps_ps.setString(li_column++, acc_param.getIdCatastro());
					lps_ps.setString(li_column++, acc_param.getActivo());
					lps_ps.setString(li_column++, acc_param.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, acc_param.getIpCreacion());
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
	 * @param acc_param objeto contenedor de la información a insertar
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void update(CirculoCatastro acc_param)
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

				lps_ps.setString(li_column++, acc_param.getIdCirculoCatastro());
				lps_ps.setString(li_column++, acc_param.getActivo());
				lps_ps.setString(li_column++, acc_param.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, acc_param.getIpModificacion());
				lps_ps.setString(li_column++, acc_param.getIdCirculo());
				lps_ps.setString(li_column++, acc_param.getIdCatastro());

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
	private CirculoCatastro getObjetFromResultSet(ResultSet lrs_rs)
	    throws SQLException
	{
		CirculoCatastro lcc_circuloCatastro;

		lcc_circuloCatastro = new CirculoCatastro();

		lcc_circuloCatastro.setIdCirculoCatastro(lrs_rs.getString("ID_CATASTRO_CIRCULO"));
		lcc_circuloCatastro.setIdCirculo(lrs_rs.getString("ID_CIRCULO"));
		lcc_circuloCatastro.setIdCatastro(lrs_rs.getString("ID_CATASTRO"));
		lcc_circuloCatastro.setActivo(lrs_rs.getString("ACTIVO"));
		lcc_circuloCatastro.setIdUsuarioCreacion(lrs_rs.getString("ID_USUARIO_CREACION"));
		lcc_circuloCatastro.setFechaCreacion(lrs_rs.getTimestamp("FECHA_CREACION"));
		lcc_circuloCatastro.setIpCreacion(lrs_rs.getString("IP_CREACION"));
		lcc_circuloCatastro.setIdUsuarioModificacion(lrs_rs.getString("ID_USUARIO_MODIFICACION"));
		lcc_circuloCatastro.setFechaModificacion(lrs_rs.getTimestamp("FECHA_MODIFICACION"));
		lcc_circuloCatastro.setIpModificacion(lrs_rs.getString("IP_MODIFICACION"));

		return lcc_circuloCatastro;
	}
}
