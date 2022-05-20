package com.bachue.snr.prosnr04.dao.pgn;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr04.model.pgn.PuntoRecaudo;

import java.math.BigDecimal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase para el manejo de datos para la tabla SDB_PGN_PUNTO_RECAUDO.
 *
 * @author Andres Rocha
 */
public class PuntoRecaudoDAO extends AuditoriaDao
{
	/**Constante FIND BY CODIGO*/
	private static final String cs_FIND_BY_CODIGO = "SELECT * FROM SDB_PGN_PUNTO_RECAUDO WHERE CODIGO_PUNTO_RECAUDO = ? AND ID_ENTIDAD_RECAUDADORA = ?";

	/**Constante FIND ALL*/
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_PGN_PUNTO_RECAUDO";

	/**Constante FIND BY ID*/
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_PGN_PUNTO_RECAUDO WHERE ID_PUNTO_RECAUDO = ?";

	/**Constante UPDATE*/
	private static final String cs_UPDATE = "UPDATE SDB_PGN_PUNTO_RECAUDO SET NOMBRE_PUNTO_RECAUDO = ?, CODIGO_PUNTO_RECAUDO = ?,"
		+ " ID_PAIS = ?, ID_DEPARTAMENTO = ?, ID_MUNICIPIO = ?, ID_ENTIDAD_RECAUDADORA = ?, ID_MEDIO_RECAUDO = ?, ACTIVO = ?,"
		+ " ID_USUARIO_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP, IP_MODIFICACION = ? WHERE ID_PUNTO_RECAUDO = ?";

	/**Constante  INSERT*/
	private static final String cs_INSERT = "INSERT INTO SDB_PGN_PUNTO_RECAUDO (ID_PUNTO_RECAUDO, NOMBRE_PUNTO_RECAUDO, CODIGO_PUNTO_RECAUDO, ID_PAIS, ID_DEPARTAMENTO, ID_MUNICIPIO, ID_ENTIDAD_RECAUDADORA, ID_MEDIO_RECAUDO, ACTIVO,"
		+ " ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION)"
		+ " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSTIMESTAMP, ?)";

	/**Cosntante FIND SECUENCE*/
	private static final String cs_FIND_SECUENCE = "SELECT SEC_PGN_PUNTO_RECAUDO_ID_PUNTO_RECAUDO.NEXTVAL FROM DUAL";

	/**
	 * Metodo para encontrar todos los registros de la tabla SDB_PGN_CANAL_ORIGEN_SERVICIO
	 */
	public Collection<PuntoRecaudo> findAll()
	    throws B2BException
	{
		Collection<PuntoRecaudo> lcpr_cllPuntoRecaudo;
		PreparedStatement        lps_ps;
		ResultSet                lrs_rs;

		lcpr_cllPuntoRecaudo     = new ArrayList<PuntoRecaudo>();
		lps_ps                   = null;
		lrs_rs                   = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcpr_cllPuntoRecaudo.add(getObjetFromResultSet(lrs_rs));
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

		if(!CollectionUtils.isValidCollection(lcpr_cllPuntoRecaudo))
			lcpr_cllPuntoRecaudo = null;

		return lcpr_cllPuntoRecaudo;
	}

	/**
	 * Metodo para encontrar los registros que coincidan
	 * con un codigo Punto Recaudo específico de la tabla SDB_PGN_PUNTO_RECAUDO
	 */
	public PuntoRecaudo findByCodigo(String as_codigoPuntoRecaudo, String as_codigoEntidadRecaudadora)
	    throws B2BException
	{
		PuntoRecaudo      lpr_return;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lpr_return     = null;
		lps_ps         = null;
		lrs_rs         = null;

		try
		{
			if(
			    StringUtils.isValidString(as_codigoPuntoRecaudo)
				    && StringUtils.isValidString(as_codigoEntidadRecaudadora)
			)
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_CODIGO);

				lps_ps.setString(1, as_codigoPuntoRecaudo);
				lps_ps.setString(2, as_codigoEntidadRecaudadora);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lpr_return = getObjetFromResultSet(lrs_rs);
			}
		}
		catch(SQLException lse_e)
		{
			logError(this, "findByCodigo", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return lpr_return;
	}

	/**
	 * Metodo para encontrar los registros que coincidan
	 * con un codigo Punto Recaudo específico de la tabla SDB_PGN_PUNTO_RECAUDO
	 */
	public PuntoRecaudo findById(PuntoRecaudo apr_pr)
	    throws B2BException
	{
		PuntoRecaudo      lcos_canalOrigenServicio;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lcos_canalOrigenServicio     = null;
		lps_ps                       = null;
		lrs_rs                       = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

			lps_ps.setString(1, apr_pr.getIdPuntoRecaudo());

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				lcos_canalOrigenServicio = getObjetFromResultSet(lrs_rs);
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

		return lcos_canalOrigenServicio;
	}

	/**
	 * Metodo para encontrar los registros que coincidan
	 * con un codigo Punto Recaudo específico de la tabla SDB_PGN_PUNTO_RECAUDO
	 */
	public PuntoRecaudo findById(String as_s)
	    throws B2BException
	{
		PuntoRecaudo      lcos_canalOrigenServicio;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lcos_canalOrigenServicio     = null;
		lps_ps                       = null;
		lrs_rs                       = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

			lps_ps.setString(1, as_s);

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				lcos_canalOrigenServicio = getObjetFromResultSet(lrs_rs);
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

		return lcos_canalOrigenServicio;
	}

	/**
	 * Metodo para insertar un registro en la base de datos de la tabla SDB_PGN_PUNTO_RECAUDO
	 */
	public void insert(PuntoRecaudo apr_param)
	    throws B2BException
	{
		if(apr_param != null)
		{
			PreparedStatement lps_ps;
			PreparedStatement lps_secuencia;
			ResultSet         lrs_rs;

			lps_ps            = null;
			lps_secuencia     = null;
			lrs_rs            = null;

			try
			{
				Connection lc_connection;
				int        li_column;

				lc_connection     = getConnection();
				li_column         = 1;

				lps_ps     = lc_connection.prepareStatement(cs_INSERT);

				lps_secuencia     = lc_connection.prepareStatement(cs_FIND_SECUENCE);

				lrs_rs = lps_secuencia.executeQuery();

				if(lrs_rs.next())
				{
					Object lo_o;

					lo_o = lrs_rs.getObject(1);

					if((lo_o != null) && lo_o instanceof BigDecimal)
					{
						apr_param.setIdPuntoRecaudo(((BigDecimal)lo_o).toString());

						lps_ps.setString(li_column++, apr_param.getIdPuntoRecaudo());
					}
					else
						throw new B2BException(ErrorKeys.PGN_CANAL_ORIGEN_SERVICIO_SEQUENCE);
				}

				lps_ps.setString(li_column++, apr_param.getNombrePuntoRecaudo());
				lps_ps.setString(li_column++, apr_param.getCodigoPuntoRecaudo());
				lps_ps.setString(li_column++, apr_param.getIdPais());
				lps_ps.setString(li_column++, apr_param.getIdDepartamento());
				lps_ps.setString(li_column++, apr_param.getIdMunicipio());
				lps_ps.setString(li_column++, apr_param.getIdEntidadRecaudadora());
				lps_ps.setString(li_column++, apr_param.getIdMedioRecaudo());
				lps_ps.setString(li_column++, apr_param.getActivo());
				lps_ps.setString(li_column++, apr_param.getIdUsuarioCreacion());
				lps_ps.setString(li_column++, apr_param.getIpCreacion());

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
	 * Metodo para actualizar un registro en la base de datos de la tabla SDB_PGN_PUNTO_RECAUDO
	 */
	public void update(PuntoRecaudo apr_param)
	    throws B2BException
	{
		if(apr_param != null)
		{
			PreparedStatement lps_ps;
			PreparedStatement lps_secuencia;
			ResultSet         lrs_rs;

			lps_ps            = null;
			lps_secuencia     = null;
			lrs_rs            = null;

			try
			{
				Connection lc_connection;
				int        li_column;

				lc_connection     = getConnection();
				li_column         = 1;

				lps_ps = lc_connection.prepareStatement(cs_UPDATE);
				lps_ps.setString(li_column++, apr_param.getNombrePuntoRecaudo());
				lps_ps.setString(li_column++, apr_param.getCodigoPuntoRecaudo());
				lps_ps.setString(li_column++, apr_param.getIdPais());
				lps_ps.setString(li_column++, apr_param.getIdDepartamento());
				lps_ps.setString(li_column++, apr_param.getIdMunicipio());
				lps_ps.setString(li_column++, apr_param.getIdEntidadRecaudadora());
				lps_ps.setString(li_column++, apr_param.getIdMedioRecaudo());
				lps_ps.setString(li_column++, apr_param.getActivo());
				lps_ps.setString(li_column++, apr_param.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, apr_param.getIpModificacion());
				lps_ps.setString(li_column++, apr_param.getIdPuntoRecaudo());

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
				close(lps_secuencia);
				close(lrs_rs);
			}
		}
	}

	/**
	 * Método para la obtencion del objeto EntidadRecaudadora
	 * @param ars_rs objeto que representa el resultado de una sentencia sql en la base de datos
	 * @return
	 * @throws SQLException
	 */
	private PuntoRecaudo getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		PuntoRecaudo lcos_cos;

		lcos_cos = new PuntoRecaudo();

		lcos_cos.setIdPuntoRecaudo(ars_rs.getString("ID_PUNTO_RECAUDO"));
		lcos_cos.setNombrePuntoRecaudo(ars_rs.getString("NOMBRE_PUNTO_RECAUDO"));
		lcos_cos.setCodigoPuntoRecaudo(ars_rs.getString("CODIGO_PUNTO_RECAUDO"));
		lcos_cos.setIdPais(ars_rs.getString("ID_PAIS"));
		lcos_cos.setIdDepartamento(ars_rs.getString("ID_DEPARTAMENTO"));
		lcos_cos.setIdMunicipio(ars_rs.getString("ID_MUNICIPIO"));
		lcos_cos.setIdEntidadRecaudadora(ars_rs.getString("ID_ENTIDAD_RECAUDADORA"));
		lcos_cos.setIdMedioRecaudo(ars_rs.getString("ID_MEDIO_RECAUDO"));
		lcos_cos.setActivo(ars_rs.getString("ACTIVO"));

		fillAuditoria(ars_rs, lcos_cos);

		return lcos_cos;
	}
}
