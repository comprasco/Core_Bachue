package com.bachue.snr.prosnr01.dao.pgn;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr01.model.sdb.pgn.CausalRechazoRecurso;
import com.bachue.snr.prosnr01.model.sdb.pgn.DependenciaSNR;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las sentencias de la tabla SDB_PGN_DEPENDENCIA_SNR
 *
 * @author hcastaneda
 */
public class DependenciaSNRDAO extends AuditoriaDao
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_PGN_DEPENDENCIA_SNR WHERE ID_DEPENDENCIA = ?";

	/** Constante cs_FIND_ALL_IND_VISITAS. */
	private static final String cs_FIND_ALL_IND_VISITAS = "SELECT * FROM SDB_PGN_DEPENDENCIA_SNR WHERE IND_VISITAS = 'S' ORDER BY NOMBRE_DEPENDENCIA ASC";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_PGN_DEPENDENCIA_SNR SET NOMBRE_DEPENDENCIA= ?, ACTIVO= ?, IND_VISITAS=?,"
		+ "ID_USUARIO_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP, IP_MODIFICACION = ? WHERE ID_DEPENDENCIA = ?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_PGN_DEPENDENCIA_SNR(ID_DEPENDENCIA, NOMBRE_DEPENDENCIA, ACTIVO,IND_VISITAS, ID_USUARIO_CREACION, IP_CREACION, FECHA_CREACION)"
		+ " VALUES(?, ?, ?, ?, ?, ?, SYSTIMESTAMP)";

	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_PGN_DEPENDENCIA_SNR";

	/**
	 * Método para traer de la base de datos todos lo registros de la tabla SDB_PGN_DEPENDENCIA_SNR.
	 *
	 * @return devuelve el valor del objeto collection
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<DependenciaSNR> findAll()
	    throws B2BException
	{
		Collection<DependenciaSNR> lcdsnr_cdsnr;
		PreparedStatement          lps_ps;
		ResultSet                  lrs_rs;

		lcdsnr_cdsnr     = new ArrayList<DependenciaSNR>();
		lps_ps           = null;
		lrs_rs           = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcdsnr_cdsnr.add(getObjetFromResultSet(lrs_rs));
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

		if(lcdsnr_cdsnr.isEmpty())
			lcdsnr_cdsnr = null;

		return lcdsnr_cdsnr;
	}

	/**
	 * Encuentra todas las dependencias cuyo ind_visitas sea igual a S.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<DependenciaSNR> findAllByIndVisitas()
	    throws B2BException
	{
		Collection<DependenciaSNR> lcdsnr_cdsnr;
		PreparedStatement          lps_ps;
		ResultSet                  lrs_rs;

		lcdsnr_cdsnr     = new ArrayList<DependenciaSNR>();
		lps_ps           = null;
		lrs_rs           = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL_IND_VISITAS);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcdsnr_cdsnr.add(getObjetFromResultSet(lrs_rs));
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

		if(lcdsnr_cdsnr.isEmpty())
			lcdsnr_cdsnr = null;

		return lcdsnr_cdsnr;
	}

	/**
	 * Método para encontrar todos los registros de la tabla SDB_PGN_DEPENDENCIA_SNR
	 * que coíncida con un ID_DEPENDENCIA.
	 *
	 * @param as_idDependencia correspondiente al valor del tipo de objeto DependenciaSNR
	 * @return devuelve el valor del objeto tarifa fija
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public DependenciaSNR findById(String as_idDependencia)
	    throws B2BException
	{
		DependenciaSNR    ls_object;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		ls_object     = null;
		lps_ps        = null;
		lrs_rs        = null;

		try
		{
			if(StringUtils.isValidString(as_idDependencia))
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(1, as_idDependencia);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ls_object = getObjetFromResultSet(lrs_rs);
			}
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

		return ls_object;
	}

	/**
	 * Inserta un registro
	 * con la información suministrada.
	 *
	 * @param adsnr_param objeto contenedor de la información a insertar
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void insert(DependenciaSNR adsnr_param)
	    throws B2BException
	{
		if(adsnr_param != null)
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

				lps_ps.setString(li_column++, adsnr_param.getIdDependencia());
				lps_ps.setString(li_column++, adsnr_param.getNombreDependencia());
				lps_ps.setString(li_column++, adsnr_param.getActivo());
				lps_ps.setString(li_column++, adsnr_param.getIndVisitas());
				lps_ps.setString(li_column++, adsnr_param.getIdUsuarioCreacion());
				lps_ps.setString(li_column++, adsnr_param.getIpCreacion());

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
	 * @param adsnr_param objeto contenedor de la información a insertar
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void update(DependenciaSNR adsnr_param)
	    throws B2BException
	{
		if(adsnr_param != null)
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

				lps_ps.setString(li_column++, adsnr_param.getNombreDependencia());
				lps_ps.setString(li_column++, adsnr_param.getActivo());
				lps_ps.setString(li_column++, adsnr_param.getIndVisitas());
				lps_ps.setString(li_column++, adsnr_param.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, adsnr_param.getIpModificacion());
				lps_ps.setString(li_column++, adsnr_param.getIdDependencia());

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
	 * Método que asigna los resultados de una consulta a los atributos de un nuevo objeto de CausalRechazoRecurso.
	 *
	 * @param ars_rs objeto que recoge el resultado de la consulta
	 * @return el valor de CausalRechazoRecurso
	 * @throws SQLException Señala que se ha producido una excepción
	 * @see CausalRechazoRecurso
	 */
	private DependenciaSNR getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		DependenciaSNR ldsnr_datos;

		ldsnr_datos = new DependenciaSNR();

		if(ars_rs != null)
		{
			ldsnr_datos.setIdDependencia(ars_rs.getString("ID_DEPENDENCIA"));
			ldsnr_datos.setNombreDependencia(ars_rs.getString("NOMBRE_DEPENDENCIA"));
			ldsnr_datos.setActivo(ars_rs.getString("ACTIVO"));
			ldsnr_datos.setIndVisitas(ars_rs.getString("IND_VISITAS"));

			fillAuditoria(ars_rs, ldsnr_datos);
		}

		return ldsnr_datos;
	}
}
