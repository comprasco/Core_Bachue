package com.bachue.snr.prosnr01.dao.png;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.NumericUtils;

import com.bachue.snr.prosnr01.model.sdb.pgn.CamposCorreccion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las consultar relacionadas a la tabla SDB_PGN_CAMPOS_CORRECCION de la base de datos
 *
 * @author Gabriel Arias
 */
public class CamposCorreccionDAO extends BaseDAO
{
	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_PGN_CAMPOS_CORRECCION ORDER BY 2 ASC";

	/** Constante  cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_PGN_CAMPOS_CORRECCION WHERE ID_CAMPO_CORRECCION = ? AND ID_CAUSAL_CORRECCION = ?";

	/** Constante  cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_PGN_CAMPOS_CORRECCION(ID_CAMPO_CORRECCION, ID_CAUSAL_CORRECCION, DESCRIPCION_CAMPO, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION) VALUES(?, ?, ?, ?, SYSTIMESTAMP, ?)";

	/** Constante  cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_PGN_CAMPOS_CORRECCION SET DESCRIPCION_CAMPO=?, ID_USUARIO_MODIFICACION=?, IP_MODIFICACION=?, FECHA_MODIFICACION = SYSTIMESTAMP WHERE ID_CAMPO_CORRECCION=? AND ID_CAUSAL_CORRECCION=?";

	/**
	 * Metodo para encontrar todos los registros de la tabla SDB_PGN_CAMPOS_CORRECCION.
	 *
	 * @return devuelve el valor del objeto collection
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<CamposCorreccion> findAll()
	    throws B2BException
	{
		Collection<CamposCorreccion> lccc_cc;
		PreparedStatement            lps_ps;
		ResultSet                    lrs_rs;

		lccc_cc     = new ArrayList<CamposCorreccion>();
		lps_ps      = null;
		lrs_rs      = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL);
			lrs_rs     = lps_ps.executeQuery();

			while(lrs_rs.next())
				lccc_cc.add(getCamposCorreccion(lrs_rs));
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

		if(lccc_cc.isEmpty())
			lccc_cc = null;

		return lccc_cc;
	}

	/**
	 * Busca un registro de la tabla por medio de su id_identificador.
	 *
	 * @param acc_param Objeto de tipo CamposCorreccion contenedor de los filtros a usar en la consulta
	 * @return CamposCertificado resultante de la consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public CamposCorreccion findById(CamposCorreccion acc_param)
	    throws B2BException
	{
		return (acc_param != null) ? findById(acc_param.getIdCampoCorreccion(), acc_param.getIdCausalCorreccion()) : null;
	}

	/**
	 * Busca un registro de la tabla por medio de su id_identificador.
	 *
	 * @param al_idCampoCorreccion Objeto contenedor de los filtros a usar en la consulta
	 * @param al_idCausalCorrreccion Objeto contenedor de los filtros a usar en la consulta
	 * @return CamposCorreccion resultante de la consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public CamposCorreccion findById(Long al_idCampoCorreccion, Long al_idCausalCorrreccion)
	    throws B2BException
	{
		CamposCorreccion lcc_param;

		lcc_param = null;

		if(NumericUtils.isValidLong(al_idCampoCorreccion) && NumericUtils.isValidLong(al_idCausalCorrreccion))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				setLong(lps_ps, al_idCampoCorreccion, 1);
				setLong(lps_ps, al_idCausalCorrreccion, 2);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lcc_param = getCamposCorreccion(lrs_rs);
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
	public void insert(CamposCorreccion acc_param)
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

				setLong(lps_ps, acc_param.getIdCampoCorreccion(), li_column++);
				setLong(lps_ps, acc_param.getIdCausalCorreccion(), li_column++);
				lps_ps.setString(li_column++, acc_param.getDescripcionCampo());
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
	public void update(CamposCorreccion acc_param)
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

				lps_ps.setString(li_column++, acc_param.getDescripcionCampo());
				lps_ps.setString(li_column++, acc_param.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, acc_param.getIpModificacion());
				setLong(lps_ps, acc_param.getIdCampoCorreccion(), li_column++);
				setLong(lps_ps, acc_param.getIdCausalCorreccion(), li_column++);

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
	 * Retorna el valor de campos correccion.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de campos correccion de tipo CamposCorreccion
	 * @throws SQLException Señala que se ha producido una excepción
	 * @see CamposCorreccion
	 */
	private CamposCorreccion getCamposCorreccion(ResultSet ars_rs)
	    throws SQLException
	{
		CamposCorreccion lcc_return;

		lcc_return = new CamposCorreccion();

		lcc_return.setIdCampoCorreccion(getLong(ars_rs, "ID_CAMPO_CORRECCION"));
		lcc_return.setIdCausalCorreccion(getLong(ars_rs, "ID_CAUSAL_CORRECCION"));
		lcc_return.setDescripcionCampo(ars_rs.getString("DESCRIPCION_CAMPO"));
		lcc_return.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		lcc_return.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		lcc_return.setIpCreacion(ars_rs.getString("IP_CREACION"));
		lcc_return.setIdUsuarioModificacion(ars_rs.getString("ID_USUARIO_MODIFICACION"));
		lcc_return.setFechaModificacion(ars_rs.getTimestamp("FECHA_MODIFICACION"));
		lcc_return.setIpModificacion(ars_rs.getString("IP_MODIFICACION"));

		return lcc_return;
	}
}
