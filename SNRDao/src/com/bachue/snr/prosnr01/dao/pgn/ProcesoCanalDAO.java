package com.bachue.snr.prosnr01.dao.pgn;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr01.model.sdb.png.ProcesoCanal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase de acceso a datos que permite administrar los datos de la tabla SDB_PGN_PROCESO_CANAL.
 *
 * @author dbeltran
 */
public class ProcesoCanalDAO extends AuditoriaDao
{
	/** Propiedad cs FIND ALL. */
	private static String cs_FIND_ALL = "SELECT * FROM SDB_PGN_PROCESO_CANAL";

	/** Propiedad cs FIND BY ID. */
	private static String cs_FIND_BY_ID = "SELECT * FROM SDB_PGN_PROCESO_CANAL WHERE ID_PROCESO=? AND ID_SUBPROCESO=? AND ID_TIPO_CANAL_ORIGEN=? ";

	/** Propiedad cs INSERT. */
	private static String cs_INSERT = "INSERT INTO SDB_PGN_PROCESO_CANAL(ID_PROCESO,ID_SUBPROCESO, ID_TIPO_CANAL_ORIGEN, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION, ACTIVO) VALUES (?,?,?,?,SYSTIMESTAMP,?,?)";

	/** Propiedad cs UPDATE. */
	private static String cs_UPDATE = "UPDATE SDB_PGN_PROCESO_CANAL SET ID_USUARIO_MODIFICACION=?,FECHA_MODIFICACION=SYSTIMESTAMP, IP_MODIFICACION=?, ACTIVO=? WHERE ID_PROCESO=? AND ID_SUBPROCESO=? AND ID_TIPO_CANAL_ORIGEN=?";

	/**
	 * Metodo para encontrar todos los registros existentes en la tabla SDB_PGN_PROCESO_CANAL.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<ProcesoCanal> findAll()
	    throws B2BException
	{
		Collection<ProcesoCanal> lcpc_pc;
		PreparedStatement        lps_ps;
		ResultSet                lrs_rs;

		lcpc_pc     = new ArrayList<ProcesoCanal>();
		lps_ps      = null;
		lrs_rs      = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcpc_pc.add(getProcesoCanal(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findAll", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lps_ps);
			close(lrs_rs);
		}

		if(!CollectionUtils.isValidCollection(lcpc_pc))
			lcpc_pc = null;

		return lcpc_pc;
	}

	/**
	 * Find by id.
	 *
	 * @param as_idProceso de as id proceso
	 * @param as_idSubproceso de as id subproceso
	 * @param as_idTipoCanalOrigen de as id tipo canal origen
	 * @return el valor de proceso canal
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public ProcesoCanal findById(String as_idProceso, String as_idSubproceso, String as_idTipoCanalOrigen)
	    throws B2BException
	{
		ProcesoCanal      lcpc_procesoCanal;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lcpc_procesoCanal     = null;
		lps_ps                = null;
		lrs_rs                = null;

		try
		{
			if(
			    StringUtils.isValidString(as_idProceso) && StringUtils.isValidString(as_idSubproceso)
				    && StringUtils.isValidString(as_idTipoCanalOrigen)
			)
			{
				int li_contador;

				li_contador     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(li_contador++, as_idProceso);
				lps_ps.setString(li_contador++, as_idSubproceso);
				lps_ps.setString(li_contador++, as_idTipoCanalOrigen);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lcpc_procesoCanal = getProcesoCanal(lrs_rs);
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

		return lcpc_procesoCanal;
	}

	/**
	 * Insert.
	 *
	 * @param aro_param de aro param
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void insert(ProcesoCanal aro_param)
	    throws B2BException
	{
		if(aro_param != null)
		{
			PreparedStatement lps_ps;

			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;

				lps_ps = getConnection().prepareStatement(cs_INSERT);

				lps_ps.setString(li_column++, aro_param.getIdProceso());
				lps_ps.setString(li_column++, aro_param.getIdSubProceso());
				lps_ps.setString(li_column++, aro_param.getIdTipoCanalOrigen());

				lps_ps.setString(li_column++, aro_param.getIdUsuarioCreacion());
				lps_ps.setString(li_column++, aro_param.getIpCreacion());

				lps_ps.setString(li_column++, aro_param.getActivo());

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
	 * Update.
	 *
	 * @param aro_param de aro param
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void update(ProcesoCanal aro_param)
	    throws B2BException
	{
		if(aro_param != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;

				lps_ps = getConnection().prepareStatement(cs_UPDATE);

				lps_ps.setString(li_column++, aro_param.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, aro_param.getIpModificacion());

				lps_ps.setString(li_column++, aro_param.getActivo());

				lps_ps.setString(li_column++, aro_param.getIdProceso());
				lps_ps.setString(li_column++, aro_param.getIdSubProceso());
				lps_ps.setString(li_column++, aro_param.getIdTipoCanalOrigen());

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
	 * Metodo que se encarga de llenar un objeto de tipo Proceso Canal con lo consultado y almacenado en un objeto ResultSet.
	 *
	 * @param ars_rs Argumento de tipo ResultSet que contiene los datos que serán almacenados en el objeto Proceso Canal
	 * @return Objeto de tipo Proceso Canal con lo consultado y almacenado en un objeto ResultSet.
	 * @throws SQLException cuando se produce algun error en el proceso
	 */
	private ProcesoCanal getProcesoCanal(ResultSet ars_rs)
	    throws SQLException
	{
		ProcesoCanal lpc_datos;

		lpc_datos = new ProcesoCanal();

		lpc_datos.setIdProceso(ars_rs.getString("ID_PROCESO"));
		lpc_datos.setIdSubProceso(ars_rs.getString("ID_SUBPROCESO"));
		lpc_datos.setIdTipoCanalOrigen(ars_rs.getString("ID_TIPO_CANAL_ORIGEN"));
		lpc_datos.setActivo(ars_rs.getString("ACTIVO"));

		fillAuditoria(ars_rs, lpc_datos);

		return lpc_datos;
	}
}
