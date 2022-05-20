package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.dataAccess.oracle.ClobUtils;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr01.model.sdb.acc.Proceso;
import com.bachue.snr.prosnr01.model.sdb.acc.SubprocesoVersion;

import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Controla las acciones CRUD sobre la tabla SDB_ACC_SUBPROCESO_VERSION.
 *
 * @author Gabriel Arias
 */
public class SubprocesoVersionDAO extends AuditoriaDao
{
	/** Constante cs_FIND_BY . */
	private static final String cs_FIND_BY = "SELECT * FROM SDB_ACC_SUBPROCESO_VERSION WHERE ID_PROCESO = ? AND ID_SUBPROCESO = ? AND VERSION_SUBPROCESO = ?";

	/** Constante cs_FIND_BY_MAX_VERSION . */
	private static final String cs_FIND_BY_MAX_VERSION = "SELECT ASV.* FROM SDB_ACC_SUBPROCESO_VERSION ASV WHERE ASV.ID_PROCESO = ? AND ASV.ID_SUBPROCESO = ? AND VERSION_SUBPROCESO = (SELECT MAX(VERSION_SUBPROCESO) FROM SDB_ACC_SUBPROCESO_VERSION ASV1 WHERE ASV1.ID_PROCESO = ASV.ID_PROCESO AND ASV1.ID_SUBPROCESO = ASV.ID_SUBPROCESO)";

	/** Constante cs_ACTUALIZAR_DEFINICION . */
	private static final String cs_ACTUALIZAR_DEFINICION = "UPDATE SDB_ACC_SUBPROCESO_VERSION SET DEFINICION =?, ID_USUARIO_MODIFICACION=?, IP_MODIFICACION=? WHERE ID_PROCESO =? AND ID_SUBPROCESO =? AND VERSION_SUBPROCESO =?";

	/** Constante cs_FIND_ALL_ACTIVO. */
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_ACC_SUBPROCESO_VERSION ";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_ACC_SUBPROCESO_VERSION SET ID_ETAPA_INICIAL=?, SOLICITUD_CERTIFICADO = ?, ESTADO_ACTIVIDAD = ?, PLANTILLA = ?, CONSERVACION_DOCUMENTAL = ?, "
		+ "OBTENER_RECIBO_CAJA = ?, ESTADO_FLUJO = ?, TIEMPO_VENCIMIENTO = ?, ID_UNIDAD_TIEMPO = ?, ID_USUARIO_MODIFICACION=?,IP_MODIFICACION=?,FECHA_MODIFICACION = SYSTIMESTAMP WHERE ID_PROCESO=? AND ID_SUBPROCESO=? AND VERSION_SUBPROCESO=?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_ACC_SUBPROCESO_VERSION(ID_PROCESO, ID_SUBPROCESO, VERSION_SUBPROCESO,ID_ETAPA_INICIAL,ESTADO_ACTIVIDAD,SOLICITUD_CERTIFICADO,PLANTILLA,CONSERVACION_DOCUMENTAL,OBTENER_RECIBO_CAJA,ESTADO_FLUJO,TIEMPO_VENCIMIENTO,ID_UNIDAD_TIEMPO,ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION) "
		+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSTIMESTAMP,?)";

	/**
	 * Retorna el valor del objeto de Collection de SubprocesoVersión.
	 *
	 * @return devuelve el valor de Collection de SubprocesoVersión
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Proceso
	 */
	public Collection<SubprocesoVersion> findAll()
	    throws B2BException
	{
		Collection<SubprocesoVersion> lp_proceso;
		PreparedStatement             lps_ps;
		ResultSet                     lrs_rs;

		lp_proceso     = new ArrayList<SubprocesoVersion>();
		lps_ps         = null;
		lrs_rs         = null;

		try
		{
			StringBuilder lsb_query;

			lsb_query = new StringBuilder(cs_FIND_ALL);

			lsb_query.append(" ORDER BY LENGTH(ID_PROCESO),ID_PROCESO ");
			lps_ps     = getConnection().prepareStatement(lsb_query.toString());

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lp_proceso.add(getObjetFromResultSet(lrs_rs));
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

		if(lp_proceso.isEmpty())
			lp_proceso = null;

		return lp_proceso;
	}

	/**
	 * Método encargado de consultar con base a las llaves primarias.
	 *
	 * @param as_idProceso Variable que contiene el valor de id proceso
	 * @param as_idSubproceso Varible que contiene el valor de id subproceso
	 * @param al_versionSubproceso Variable que contiene el valor de version subproceso
	 * @param ab_obtenerDefincion Indica si debe o no cargar la información del modelo de proceso
	 * @return Objeto que contiene la información consultada
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public SubprocesoVersion findById(
	    String as_idProceso, String as_idSubproceso, Long al_versionSubproceso, boolean ab_obtenerDefincion
	)
	    throws B2BException
	{
		SubprocesoVersion ls_return;

		ls_return = null;

		if(
		    StringUtils.isValidString(as_idProceso) && StringUtils.isValidString(as_idSubproceso)
			    && NumericUtils.isValidLong(al_versionSubproceso)
		)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_FIND_BY);

				lps_ps.setString(li_column++, as_idProceso);
				lps_ps.setString(li_column++, as_idSubproceso);
				setLong(lps_ps, al_versionSubproceso, li_column++);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ls_return = getObjetFromResultSet(lrs_rs, ab_obtenerDefincion);
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

		return ls_return;
	}

	/**
	 * Método encargado de consultar con base a las llaves primarias.
	 *
	 * @param as_idProceso Variable que contiene el valor de id proceso
	 * @param as_idSubproceso Varible que contiene el valor de id subproceso
	 * @param al_versionSubproceso Variable que contiene el valor de version subproceso
	 * @return Objeto que contiene la información consultada
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public SubprocesoVersion findById(String as_idProceso, String as_idSubproceso, Long al_versionSubproceso)
	    throws B2BException
	{
		return findById(as_idProceso, as_idSubproceso, al_versionSubproceso, false);
	}

	/**
	 * Método encargado de consultar con base a las llaves primarias.
	 *
	 * @param as_idProceso Variable que contiene el valor de id proceso
	 * @param as_idSubproceso Varible que contiene el valor de id subproceso
	 * @return Objeto que contiene la información consultada
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public SubprocesoVersion findByIdMaxVersion(String as_idProceso, String as_idSubproceso)
	    throws B2BException
	{
		SubprocesoVersion ls_return;

		ls_return = null;

		if(StringUtils.isValidString(as_idProceso) && StringUtils.isValidString(as_idSubproceso))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_FIND_BY_MAX_VERSION);

				lps_ps.setString(li_column++, as_idProceso);
				lps_ps.setString(li_column++, as_idSubproceso);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ls_return = getObjetFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdMaxVersion", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return ls_return;
	}

	/**
	 * Dependiendo del procedimiento seleccionado, inserta o actualiza un registro
	 * con la información del subproceso suministrada.
	 *
	 * @param as_param de as param
	 * @param ab_query de ab query
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void insertOrUpdate(SubprocesoVersion as_param, boolean ab_query)
	    throws B2BException
	{
		if(as_param != null)
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
					lps_ps.setString(li_column++, as_param.getIdProceso());
					lps_ps.setString(li_column++, as_param.getIdSubproceso());
					setLong(lps_ps, as_param.getVersionSubproceso(), li_column++);
					setLong(lps_ps, as_param.getIdEtapaInicial(), li_column++);
					lps_ps.setString(li_column++, as_param.getEstadoActividad());
					lps_ps.setString(li_column++, as_param.getSolicitudCertificado());
					lps_ps.setString(li_column++, as_param.getPlantilla());
					lps_ps.setString(li_column++, as_param.getConservacionDocumental());
					lps_ps.setString(li_column++, as_param.getObtenerReciboCaja());
					lps_ps.setString(li_column++, as_param.getEstadoFlujo());
					setLong(lps_ps, as_param.getTiempoVencimiento(), li_column++);
					lps_ps.setString(li_column++, as_param.getIdUnidadTiempo());
					lps_ps.setString(li_column++, as_param.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, as_param.getIpCreacion());
				}
				else
				{
					setLong(lps_ps, as_param.getIdEtapaInicial(), li_column++);
					lps_ps.setString(li_column++, as_param.getSolicitudCertificado());
					lps_ps.setString(li_column++, as_param.getEstadoActividad());
					lps_ps.setString(li_column++, as_param.getPlantilla());
					lps_ps.setString(li_column++, as_param.getConservacionDocumental());
					lps_ps.setString(li_column++, as_param.getObtenerReciboCaja());
					lps_ps.setString(li_column++, as_param.getEstadoFlujo());
					setLong(lps_ps, as_param.getTiempoVencimiento(), li_column++);
					lps_ps.setString(li_column++, as_param.getIdUnidadTiempo());
					lps_ps.setString(li_column++, as_param.getIdUsuarioModificacion());
					lps_ps.setString(li_column++, as_param.getIpModificacion());
					lps_ps.setString(li_column++, as_param.getIdProceso());
					lps_ps.setString(li_column++, as_param.getIdSubproceso());
					setLong(lps_ps, as_param.getVersionSubproceso(), li_column++);
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
	 * Inserta un registro con la información del subproceso versión suministrada.
	 *
	 * @param as_xml de as xml
	 * @param as_idProceso de as id proceso
	 * @param as_idSubProceso de as id sub proceso
	 * @param ai_version de ai version
	 * @param as_userId de as user id
	 * @param as_ipRemote de as ip remote
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void updateDefinicion(
	    String as_xml, String as_idProceso, String as_idSubProceso, int ai_version, String as_userId, String as_ipRemote
	)
	    throws B2BException
	{
		if(
		    StringUtils.isValidString(as_xml) && StringUtils.isValidString(as_idProceso)
			    && StringUtils.isValidString(as_idSubProceso) && (ai_version > 0)
			    && StringUtils.isValidString(as_userId) && StringUtils.isValidString(as_ipRemote)
		)
		{
			PreparedStatement lps_ps;

			lps_ps = null;

			try
			{
				Connection lc_conexion;
				int        li_contador;

				lc_conexion     = getConnection();
				li_contador     = 1;
				lps_ps          = lc_conexion.prepareStatement(cs_ACTUALIZAR_DEFINICION);

				{
					Clob lc_definicion;

					lc_definicion = ClobUtils.stringToClob(as_xml, lc_conexion.createClob());

					lps_ps.setClob(li_contador++, lc_definicion);
				}

				lps_ps.setString(li_contador++, as_userId);
				lps_ps.setString(li_contador++, as_ipRemote);
				lps_ps.setString(li_contador++, as_idProceso);
				lps_ps.setString(li_contador++, as_idSubProceso);
				lps_ps.setInt(li_contador++, ai_version);

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "updateDefinicion", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Retorna Objeto o variable de valor objet from result set.
	 *
	 * @param ars_rs de ars rs
	 * @param ab_obtenerDefincion de ab obtener defincion
	 * @return el valor de objet from result set
	 * @throws SQLException cuando se produce algun error en el proceso
	 */
	private SubprocesoVersion getObjetFromResultSet(ResultSet ars_rs, boolean ab_obtenerDefincion)
	    throws SQLException
	{
		SubprocesoVersion lsv_return;

		lsv_return = new SubprocesoVersion();

		lsv_return.setConservacionDocumental(ars_rs.getString("CONSERVACION_DOCUMENTAL"));
		lsv_return.setEstadoActividad(ars_rs.getString("ESTADO_ACTIVIDAD"));
		lsv_return.setEstadoFlujo(ars_rs.getString("ESTADO_FLUJO"));
		lsv_return.setIdEtapaInicial(getLong(ars_rs, "ID_ETAPA_INICIAL"));
		lsv_return.setIdProceso(ars_rs.getString("ID_PROCESO"));
		lsv_return.setIdSubproceso(ars_rs.getString("ID_SUBPROCESO"));
		lsv_return.setIdUnidadTiempo(ars_rs.getString("ID_UNIDAD_TIEMPO"));
		lsv_return.setObtenerReciboCaja(ars_rs.getString("OBTENER_RECIBO_CAJA"));
		lsv_return.setPlantilla(ars_rs.getString("PLANTILLA"));
		lsv_return.setSolicitudCertificado(ars_rs.getString("SOLICITUD_CERTIFICADO"));
		lsv_return.setTiempoVencimiento(getLong(ars_rs, "TIEMPO_VENCIMIENTO"));
		lsv_return.setVersionSubproceso(getLong(ars_rs, "VERSION_SUBPROCESO"));

		if(ab_obtenerDefincion)
			lsv_return.setXml(ClobUtils.clobToString(ars_rs.getClob("DEFINICION")));

		fillAuditoria(ars_rs, lsv_return);

		return lsv_return;
	}

	/**
	 * Retorna Objeto o variable de valor objet from result set.
	 *
	 * @param ars_rs de ars rs
	 * @return el valor de objet from result set
	 * @throws SQLException cuando se produce algun error en el proceso
	 */
	private SubprocesoVersion getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		return getObjetFromResultSet(ars_rs, false);
	}
}
