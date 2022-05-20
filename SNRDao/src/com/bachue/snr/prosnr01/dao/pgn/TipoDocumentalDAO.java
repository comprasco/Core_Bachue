package com.bachue.snr.prosnr01.dao.pgn;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr01.model.sdb.pgn.TipoDocumental;

import java.math.BigDecimal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las consultas de la tabla SDB_PGN_TIPO_DOCUMENTAL.
 *
 * @author dbeltran
 */
public class TipoDocumentalDAO extends AuditoriaDao
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_PGN_TIPO_DOCUMENTAL WHERE ID_TIPO_DOCUMENTAL = ? ";

	/** Constante cs_FIND_BY_TIPOLOGIAS_BACHUE. */
	private static final String cs_FIND_BY_TIPOLOGIAS_BACHUE = "SELECT * FROM SDB_PGN_TIPO_DOCUMENTAL WHERE TIPOLOGIAS_BACHUE = ? ";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_PGN_TIPO_DOCUMENTAL (ID_TIPO_DOCUMENTAL, NOMBRE, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION, ACTIVO, TIPOLOGIAS_BACHUE, ABREVIATURA) VALUES(?,?,?,SYSTIMESTAMP,?,?,?,?)";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_PGN_TIPO_DOCUMENTAL SET NOMBRE=?, ID_USUARIO_MODIFICACION=?, FECHA_MODIFICACION=SYSTIMESTAMP, IP_MODIFICACION=?, ACTIVO=?, TIPOLOGIAS_BACHUE=?, ABREVIATURA=? WHERE ID_TIPO_DOCUMENTAL=?";

	/** Constante cs_SECUENCE. */
	private static final String cs_SECUENCE = "SELECT SEC_PGN_TIPO_DOCUMENTAL_ID_TIPO_DOCUMENTAL.NEXTVAL FROM DUAL";

	/** Constante cs_FIND_ALL_ACTIVO. */
	private static final String cs_FIND_ALL_ACTIVO = "SELECT * FROM SDB_PGN_TIPO_DOCUMENTAL WHERE ACTIVO = 'S' ORDER BY NOMBRE ASC";

	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_PGN_TIPO_DOCUMENTAL ORDER BY NOMBRE ASC";

	/**
	 * Metodo para encontrar todos los registros existentes en la tabla SDB_PGN_TIPO_DOCUMENTAL que esten en estado activo.
	 *
	 * @return devuelve el valor del objeto collection de TipoDocumental
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see TipoDocumental
	 */
	public Collection<TipoDocumental> findAll()
	    throws B2BException
	{
		Collection<TipoDocumental> lctd_ctd;
		PreparedStatement          lps_ps;
		ResultSet                  lrs_rs;

		lctd_ctd     = new ArrayList<TipoDocumental>();
		lps_ps       = null;
		lrs_rs       = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lctd_ctd.add(getObjetFromResultSet(lrs_rs));
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

		if(!CollectionUtils.isValidCollection(lctd_ctd))
			lctd_ctd = null;

		return lctd_ctd;
	}

	/**
	 * Metodo para encontrar todos los registros existentes en la tabla SDB_PGN_TIPO_DOCUMENTAL que esten en estado activo.
	 *
	 * @return devuelve el valor del objeto collection de TipoDocumental
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see TipoDocumental
	 */
	public Collection<TipoDocumental> findAllActivo()
	    throws B2BException
	{
		Collection<TipoDocumental> lctd_ctd;
		PreparedStatement          lps_ps;
		ResultSet                  lrs_rs;

		lctd_ctd     = new ArrayList<TipoDocumental>();
		lps_ps       = null;
		lrs_rs       = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL_ACTIVO);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lctd_ctd.add(getObjetFromResultSet(lrs_rs));
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

		if(!CollectionUtils.isValidCollection(lctd_ctd))
			lctd_ctd = null;

		return lctd_ctd;
	}

	/**
	 * Metodo para encontrar todos los registros existentes en la tabla SDB_PGN_TIPO_DOCUMENTAL para un id_tipo_documental específico.
	 *
	 * @param atd_param Objeto de tipo TipoDocumental que contiene parametros a utilizar como filtro en la busqueda.
	 * @return devuelve el valor del objeto tipo documental
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see TipoDocumental
	 */
	public TipoDocumental findById(TipoDocumental atd_param)
	    throws B2BException
	{
		return ((atd_param != null) ? findById(atd_param.getIdTipoDocumental()) : null);
	}

	/**
	 * Metodo para encontrar todos los registros existentes en la tabla SDB_PGN_TIPO_DOCUMENTAL para un id_tipo_documental específico.
	 *
	 * @param as_param Objeto de tipo String que contiene parametros a utilizar como filtro en la busqueda.
	 * @return devuelve el valor del objeto tipo documental
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see TipoDocumental
	 */
	public TipoDocumental findById(String as_param)
	    throws B2BException
	{
		TipoDocumental ltd_return;
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
					ltd_return = getObjetFromResultSet(lrs_rs);
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
	 * Metodo para encontrar todos los registros existentes en la tabla SDB_PGN_TIPO_DOCUMENTAL para un tipologia bachue específico.
	 *
	 * @param as_tipologiaBachue Objeto de tipo String que contiene parametros a utilizar como filtro en la busqueda.
	 * @return devuelve el valor del objeto tipo documental
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see TipoDocumental
	 */
	public TipoDocumental findByTipologiasBachue(String as_tipologiaBachue)
	    throws B2BException
	{
		TipoDocumental ltd_return;
		ltd_return = null;

		if(as_tipologiaBachue != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_TIPOLOGIAS_BACHUE);

				lps_ps.setString(1, as_tipologiaBachue);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ltd_return = getObjetFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByTipologiasBachue", lse_e);

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
	 * Metodo para insertar un registro en la base de datos de la tabla SDB_PGN_TIPO_DOCUMENTAL.
	 *
	 * @param atd_param de atd param
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void insert(TipoDocumental atd_param)
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
							atd_param.setIdTipoDocumental(StringUtils.getString(lo_o));
						else
							throw new B2BException(ErrorKeys.PGN_LINEA_PRODUCCION_SEQUENCE);
					}
				}

				lps_ps = getConnection().prepareStatement(cs_INSERT);

				lps_ps.setString(li_column++, atd_param.getIdTipoDocumental());
				lps_ps.setString(li_column++, atd_param.getNombre());
				lps_ps.setString(li_column++, atd_param.getIdUsuarioCreacion());
				lps_ps.setString(li_column++, atd_param.getIpCreacion());
				lps_ps.setString(li_column++, atd_param.getActivo());
				lps_ps.setString(li_column++, atd_param.getTipologiasBachue());
				lps_ps.setString(li_column++, atd_param.getAbreviatura());

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
	 * Metodo para actualizar un registro en la base de datos de la tabla SDB_PGN_CANAL_ORIGEN_SERVICIO.
	 *
	 * @param atd_param de atd param
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void update(TipoDocumental atd_param)
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

				lps_ps.setString(li_column++, atd_param.getNombre());
				lps_ps.setString(li_column++, atd_param.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, atd_param.getIpModificacion());
				lps_ps.setString(li_column++, atd_param.getActivo());
				lps_ps.setString(li_column++, atd_param.getTipologiasBachue());
				lps_ps.setString(li_column++, atd_param.getAbreviatura());
				lps_ps.setString(li_column++, atd_param.getIdTipoDocumental());

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
	 * Metodo para la obtencion del objeto Tipo Documental a partir de un resulset.
	 *
	 * @param lrs_rs objeto contenedor de los resultados de la consulta
	 * @return Objeto de tipo TipoDocumental intanciado con la informacion recuperada de base de datos
	 * @throws SQLException Señala que se ha producido una excepción
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private TipoDocumental getObjetFromResultSet(ResultSet lrs_rs)
	    throws SQLException, B2BException
	{
		TipoDocumental ltd_datosTD;

		ltd_datosTD = new TipoDocumental();

		ltd_datosTD.setIdTipoDocumental(lrs_rs.getString("ID_TIPO_DOCUMENTAL"));
		ltd_datosTD.setNombre(lrs_rs.getString("NOMBRE"));
		ltd_datosTD.setActivo(lrs_rs.getString("ACTIVO"));
		ltd_datosTD.setTipologiasBachue(lrs_rs.getString("TIPOLOGIAS_BACHUE"));
		ltd_datosTD.setAbreviatura(lrs_rs.getString("ABREVIATURA"));

		fillAuditoria(lrs_rs, ltd_datosTD);

		return ltd_datosTD;
	}
}
