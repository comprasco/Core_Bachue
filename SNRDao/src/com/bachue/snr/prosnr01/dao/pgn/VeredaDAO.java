package com.bachue.snr.prosnr01.dao.pgn;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.dao.IBase;

import com.bachue.snr.prosnr01.model.sdb.pgn.Vereda;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las consultas de la tabla SDB_PGN_VEREDA
 *
 * @author garias
 */
public class VeredaDAO extends com.b2bsg.common.dataAccess2.BaseDAO implements IBase<Vereda>
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_PGN_VEREDA WHERE ID_PAIS = ? AND "
		+ " ID_DEPARTAMENTO = ? AND ID_MUNICIPIO = ? AND ID_VEREDA = ?";

	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_PGN_VEREDA ORDER BY NOMBRE ASC";

	/** Constante cs_FIND_BY_IDS. */
	private static final String cs_FIND_BY_IDS = "SELECT * FROM SDB_PGN_VEREDA WHERE ID_PAIS = ? AND "
		+ " ID_DEPARTAMENTO = ? AND ID_MUNICIPIO = ? AND ACTIVO = 'S' ORDER BY NOMBRE";

	/** Constante cs_FIND_ALL_BY_DEPARTAMENT. */
	private static final String cs_FIND_ALL_BY_DEPARTAMENT = "SELECT * FROM SDB_PGN_VEREDA "
		+ " WHERE ID_DEPARTAMENTO = ? AND ID_PAIS = ? ORDER BY NOMBRE ASC";

	/** Constante cs_FIND_SECUENCE. */
	private static final String cs_FIND_SECUENCE = "SELECT DEPARTAMENTO_SEC.NEXTVAL FROM DUAL";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_PGN_VEREDA SET "
		+ " NOMBRE = ?,ID_USUARIO_MODIFICACION = ?, IP_MODIFICACION = ?, CABECERA_MUNICIPAL = ?, FECHA_MODIFICACION = SYSTIMESTAMP, ACTIVO = ?"
		+ " WHERE ID_PAIS = ? AND ID_DEPARTAMENTO = ? AND ID_MUNICIPIO = ? AND ID_VEREDA = ?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_PGN_VEREDA (ID_PAIS,ID_DEPARTAMENTO,"
		+ " ID_MUNICIPIO,ID_VEREDA,NOMBRE,ID_USUARIO_CREACION,FECHA_CREACION,IP_CREACION,CABECERA_MUNICIPAL,ACTIVO) VALUES (?,?,?,?,?,?,SYSTIMESTAMP,?,?,?)";

	/**
	 * Instancia un nuevo objeto vereda DAO.
	 */
	public VeredaDAO()
	{
	}

	/**
	 * Retorna el valor del objeto de tipo Collection de Vereda con todos los registros
	 *
	 * @return devuelve el valor del objeto collection
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Vereda
	 */
	public Collection<Vereda> findAll()
	    throws B2BException
	{
		Collection<Vereda> lv_vereda;
		PreparedStatement  lps_ps;
		ResultSet          lrs_rs;

		lv_vereda     = new ArrayList<Vereda>();
		lps_ps        = null;
		lrs_rs        = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lv_vereda.add(getVereda(lrs_rs));
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

		if(lv_vereda.isEmpty())
			lv_vereda = null;

		return lv_vereda;
	}

	/**
	 * Retorna el valor del objeto de tipo Collection de Vereda filtrado por el id del departamento
	 *
	 * @param am_parametros correspondiente al valor del tipo de objeto Vereda
	 * @return devuelve el valor del objeto collection
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Vereda
	 */
	public Collection<Vereda> findAllByDepartament(Vereda am_parametros)
	    throws B2BException
	{
		Collection<Vereda> lv_vereda;
		PreparedStatement  lps_ps;
		ResultSet          lrs_rs;

		lv_vereda     = new ArrayList<Vereda>();
		lps_ps        = null;
		lrs_rs        = null;

		try
		{
			int li_contador;
			li_contador     = 1;

			lps_ps = getConnection().prepareStatement(cs_FIND_ALL_BY_DEPARTAMENT);

			lps_ps.setString(li_contador++, am_parametros.getIdDepartamento());
			lps_ps.setString(li_contador++, am_parametros.getIdPais());

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lv_vereda.add(getVereda(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findAllByDepartament", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(lv_vereda.isEmpty())
			lv_vereda = null;

		return lv_vereda;
	}

	/**
	 * Obtiene una vereda asociada a un id específico.
	 *
	 * @param av_parametros id de la vereda a utilizar como filtro en la busqueda
	 * @return Vereda resutante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Vereda findById(Vereda av_parametros)
	    throws B2BException
	{
		return (av_parametros != null)
		? findById(
		    av_parametros.getIdPais(), av_parametros.getIdDepartamento(), av_parametros.getIdMunicipio(),
		    av_parametros.getIdVereda()
		) : null;
	}

	/**
	 * Obtiene una vereda asociada a un id específico.
	 *
	 * @param as_idPais id del país al cual pertenece la vereda
	 * @param as_idDepartamento id del departamento al cual pertenece la vereda
	 * @param as_idMunicipio id del municipio al cual pertenece la vereda
	 * @param as_idVereda id de la vereda a utilizar como filtro en la busqueda
	 * @return Vereda resutante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Vereda findById(String as_idPais, String as_idDepartamento, String as_idMunicipio, String as_idVereda)
	    throws B2BException
	{
		Vereda lv_return;

		lv_return = null;

		if(
		    StringUtils.isValidString(as_idPais) && StringUtils.isValidString(as_idDepartamento)
			    && StringUtils.isValidString(as_idMunicipio) && StringUtils.isValidString(as_idVereda)
		)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_contador;
				li_contador     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(li_contador++, as_idPais);
				lps_ps.setString(li_contador++, as_idDepartamento);
				lps_ps.setString(li_contador++, as_idMunicipio);
				lps_ps.setString(li_contador++, as_idVereda);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lv_return = getVereda(lrs_rs);
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

		return lv_return;
	}

	/**
	 * Retorna el valor del objeto de tipo Collection de Vereda filtrado por el id pais, id del departamento, id municipío
	 *
	 * @param ap_parametros correspondiente al valor del tipo de objeto Vereda
	 * @return devuelve el valor del objeto collection de Vereda
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<Vereda> findByIds(Vereda ap_parametros)
	    throws B2BException
	{
		Collection<Vereda> lv_vereda;
		PreparedStatement  lps_ps;
		ResultSet          lrs_rs;

		lv_vereda     = new ArrayList<Vereda>();
		lps_ps        = null;
		lrs_rs        = null;

		try
		{
			int li_contador;
			li_contador     = 1;

			lps_ps = getConnection().prepareStatement(cs_FIND_BY_IDS);

			lps_ps.setString(li_contador++, ap_parametros.getIdPais());
			lps_ps.setString(li_contador++, ap_parametros.getIdDepartamento());
			lps_ps.setString(li_contador++, ap_parametros.getIdMunicipio());

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lv_vereda.add(getVereda(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findByIds", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(lv_vereda.isEmpty())
			lv_vereda = null;

		return lv_vereda;
	}

	/**
	 * Retorna el valor de la secuencia
	 *
	 * @return devuelve el valor del objeto int
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public int findSecuence()
	    throws B2BException
	{
		int               li_secuencia;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		li_secuencia     = 0;
		lps_ps           = null;
		lrs_rs           = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_SECUENCE);

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				li_secuencia = lrs_rs.getInt(1);
		}
		catch(SQLException lse_e)
		{
			logError(this, "findSecuence", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return li_secuencia;
	}

	/** {@inheritdoc} */
	@Override
	public void insertOrUpdate(Vereda apt_parametros, boolean ab_query)
	    throws B2BException
	{
		if(apt_parametros != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(ab_query ? cs_INSERT : cs_UPDATE);

				if(ab_query)
				{
					lps_ps.setString(li_column++, apt_parametros.getIdPais());
					lps_ps.setString(li_column++, apt_parametros.getIdDepartamento());
					lps_ps.setString(li_column++, apt_parametros.getIdMunicipio());
					lps_ps.setString(li_column++, apt_parametros.getIdVereda());
					lps_ps.setString(li_column++, apt_parametros.getNombre());
					lps_ps.setString(li_column++, apt_parametros.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, apt_parametros.getIpCreacion());
					lps_ps.setString(li_column++, apt_parametros.getCabeceraMunicipal());
					lps_ps.setString(li_column++, apt_parametros.getActivo());
				}

				if(!ab_query)
				{
					lps_ps.setString(li_column++, apt_parametros.getNombre());
					lps_ps.setString(li_column++, apt_parametros.getIdUsuarioModificacion());
					lps_ps.setString(li_column++, apt_parametros.getIpModificacion());
					lps_ps.setString(li_column++, apt_parametros.getCabeceraMunicipal());
					lps_ps.setString(li_column++, apt_parametros.getActivo());
					lps_ps.setString(li_column++, apt_parametros.getIdPais());
					lps_ps.setString(li_column++, apt_parametros.getIdDepartamento());
					lps_ps.setString(li_column++, apt_parametros.getIdMunicipio());
					lps_ps.setString(li_column++, apt_parametros.getIdVereda());
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
			}
		}
	}

	/**
	 * Retorna el valor de vereda.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de vereda
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private Vereda getVereda(ResultSet ars_rs)
	    throws SQLException
	{
		Vereda lm_datos;
		lm_datos = new Vereda();

		lm_datos.setIdPais(ars_rs.getString("ID_PAIS"));
		lm_datos.setIdDepartamento(ars_rs.getString("ID_DEPARTAMENTO"));
		lm_datos.setIdMunicipio(ars_rs.getString("ID_MUNICIPIO"));
		lm_datos.setIdVereda(ars_rs.getString("ID_VEREDA"));
		lm_datos.setNombre(ars_rs.getString("NOMBRE"));
		lm_datos.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		lm_datos.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		lm_datos.setCabeceraMunicipal(ars_rs.getString("CABECERA_MUNICIPAL"));
		lm_datos.setActivo(ars_rs.getString("ACTIVO"));

		return lm_datos;
	}
}
