package com.bachue.snr.prosnr02.dao.acc;

import com.b2bsg.common.dataAccess.oracle.ClobUtils;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr02.model.acc.ProcesoTrabajo;
import com.bachue.snr.prosnr02.model.acc.SubProcesoTrabajo;
import com.bachue.snr.prosnr02.model.acc.SubProcesoVersionTrabajo;

import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las consultas de la tabla SDB_ACC_PROCESO_TRABAJO_TRABAJO
 *
 * @author jpatino
 */
public class ProcesoTrabajoDAO extends AuditoriaDao
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_ACC_PROCESO_TRABAJO WHERE ID_PROCESO=?";

	/** Constante cs_EXISTE. */
	private static final String cs_EXISTE = "SELECT 1 FROM SDB_ACC_PROCESO_TRABAJO WHERE ID_PROCESO=?";

	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_ACC_PROCESO_TRABAJO ORDER BY ID_PROCESO ASC";

	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL_APROBACION = "SELECT DISTINCT PT.* FROM SDB_ACC_PROCESO_TRABAJO PT, SDB_ACC_SUBPROCESO_VERSION_TRABAJO SPVT WHERE SPVT.ESTADO_FLUJO = 'APROBADOR' AND SPVT.ID_PROCESO = PT.ID_PROCESO ORDER BY PT.ID_PROCESO ASC";

	/** Constante cs_FIND_ALL_ACTIVO. */
	private static final String cs_FIND_ALL_ACTIVO = "SELECT * FROM SDB_ACC_PROCESO_TRABAJO WHERE ACTIVO = 'S' ORDER BY ID_PROCESO ASC";

	/** Constante cs_FUNCION_MAXIMA_VERSION. */
	private static final String cs_FUNCION_MAXIMA_VERSION = "SELECT PKG_WORKFLOW.FUNC_ULTIMA_VERSION_TRABAJO(?, ?) FROM DUAL";

	/** Constante cs_ACTUALIZAR_PROCESO. */
	private static final String cs_ACTUALIZAR_PROCESO = "UPDATE SDB_ACC_PROCESO_TRABAJO SET NOMBRE=?, RECEPCION_DOCUMENTO=?, ID_USUARIO_MODIFICACION=?, FECHA_MODIFICACION=SYSTIMESTAMP, IP_MODIFICACION = ? WHERE ID_PROCESO=?";

	/** Constante cs_INSERTAR_PROCESO. */
	private static final String cs_INSERTAR_PROCESO = "INSERT INTO SDB_ACC_PROCESO_TRABAJO (ID_PROCESO,NOMBRE,RECEPCION_DOCUMENTO,ID_USUARIO_CREACION,FECHA_CREACION,IP_CREACION) VALUES(?,?,?,?,SYSTIMESTAMP,?)";

	/** Constante cs_ACTUALIZAR_SUB_PROCESO. */
	private static final String cs_ACTUALIZAR_SUB_PROCESO = "UPDATE SDB_ACC_SUBPROCESO_TRABAJO SET NOMBRE=?, ID_USUARIO_MODIFICACION=?, FECHA_MODIFICACION=SYSTIMESTAMP, IP_MODIFICACION = ? WHERE ID_PROCESO=? AND ID_SUBPROCESO =?";

	/** Constante cs_INSERTAR_SUB_PROCESO. */
	private static final String cs_INSERTAR_SUB_PROCESO = "INSERT INTO SDB_ACC_SUBPROCESO_TRABAJO (ID_PROCESO,ID_SUBPROCESO,NOMBRE,ID_USUARIO_CREACION,FECHA_CREACION,IP_CREACION) VALUES(?,?,?,?,SYSTIMESTAMP,?)";

	/** Constante cs_ACTUALIZAR_VERSION. */
	private static final String cs_ACTUALIZAR_VERSION = "UPDATE SDB_ACC_SUBPROCESO_VERSION_TRABAJO SET ID_ETAPA_INICIAL=?, ESTADO_ACTIVIDAD=?, SOLICITUD_CERTIFICADO=?, PLANTILLA=?, OBTENER_RECIBO_CAJA=?, CONSERVACION_DOCUMENTAL=?, DEFINICION=?, ESTADO_FLUJO=?, ID_USUARIO_MODIFICACION=?, FECHA_MODIFICACION=SYSTIMESTAMP, IP_MODIFICACION = ? WHERE ID_PROCESO=? AND ID_SUBPROCESO =? AND VERSION=?";

	/** Constante cs_INSERTAR_VERSION. */
	private static final String cs_INSERTAR_VERSION = "INSERT INTO SDB_ACC_SUBPROCESO_VERSION_TRABAJO (ID_PROCESO,ID_SUBPROCESO,VERSION,ID_ETAPA_INICIAL,ESTADO_ACTIVIDAD,SOLICITUD_CERTIFICADO,PLANTILLA,OBTENER_RECIBO_CAJA,CONSERVACION_DOCUMENTAL,DEFINICION,ESTADO_FLUJO,VERSION_BASE,ID_USUARIO_CREACION,FECHA_CREACION,IP_CREACION) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,SYSTIMESTAMP,?)";

	/** Constante cs_ELIMINAR_FLUJOS. */
	private static final String cs_ELIMINAR_FLUJOS = "DELETE FROM SDB_PGN_MOTIVO_TRAMITE_TRABAJO WHERE ID_PROCESO=? AND ID_SUBPROCESO =? AND VERSION=?";

	/**
	 * Indica si existe un registro de proceso
	 *
	 * @param as_id Id del proceso a validar
	 * @return true si el proceso existe
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see ProcesoTrabajo
	 */
	public boolean existe(String as_id)
	    throws B2BException
	{
		boolean lb_existe;

		lb_existe = false;

		if(StringUtils.isValidString(as_id))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_EXISTE);

				lps_ps.setString(1, as_id);

				lrs_rs        = lps_ps.executeQuery();
				lb_existe     = lrs_rs.next();
			}
			catch(SQLException lse_e)
			{
				logError(this, "existe", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lb_existe;
	}

	/**
	 * Retorna el valor del objeto de Collection de Proceso.
	 *
	 * @param ab_b correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor de Collection de Proceso
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see ProcesoTrabajo
	 */
	public Collection<ProcesoTrabajo> findAll()
	    throws B2BException
	{
		Collection<ProcesoTrabajo> lcpt_procesos;
		PreparedStatement          lps_ps;
		ResultSet                  lrs_rs;

		lcpt_procesos     = null;
		lps_ps            = null;
		lrs_rs            = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL);

			lrs_rs            = lps_ps.executeQuery();
			lcpt_procesos     = new ArrayList<ProcesoTrabajo>();

			while(lrs_rs.next())
				lcpt_procesos.add(getObjetFromResultSet(lrs_rs));
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

		return lcpt_procesos;
	}

	/**
	 * Retorna el valor del objeto de Collection de Proceso.
	 *
	 * @return devuelve el valor de Collection de Proceso
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see ProcesoTrabajo
	 */
	public Collection<ProcesoTrabajo> findAllActivo()
	    throws B2BException
	{
		Collection<ProcesoTrabajo> lcpt_procesos;
		PreparedStatement          lps_ps;
		ResultSet                  lrs_rs;

		lcpt_procesos     = new ArrayList<ProcesoTrabajo>();
		lps_ps            = null;
		lrs_rs            = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL_ACTIVO);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcpt_procesos.add(getObjetFromResultSet(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findAllActivo", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(lcpt_procesos.isEmpty())
			lcpt_procesos = null;

		return lcpt_procesos;
	}

	/**
	 * Retorna el valor del objeto de Collection de Proceso.
	 *
	 * @param ab_b correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor de Collection de Proceso
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see ProcesoTrabajo
	 */
	public Collection<ProcesoTrabajo> findAllAprobacion()
	    throws B2BException
	{
		Collection<ProcesoTrabajo> lcpt_procesos;
		PreparedStatement          lps_ps;
		ResultSet                  lrs_rs;

		lcpt_procesos     = null;
		lps_ps            = null;
		lrs_rs            = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL_APROBACION);

			lrs_rs            = lps_ps.executeQuery();
			lcpt_procesos     = new ArrayList<ProcesoTrabajo>();

			while(lrs_rs.next())
				lcpt_procesos.add(getObjetFromResultSet(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findAllAprobacion", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return lcpt_procesos;
	}

	/**
	 * Retorna el valor del objeto de Proceso.
	 *
	 * @param apt_proceso correspondiente al valor del tipo de objeto Proceso
	 * @return devuelve el valor de Proceso
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see ProcesoTrabajo
	 */
	public ProcesoTrabajo findById(ProcesoTrabajo apt_proceso)
	    throws B2BException
	{
		ProcesoTrabajo lpt_proceso;

		lpt_proceso = null;

		if(apt_proceso != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(1, apt_proceso.getId());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lpt_proceso = getObjetFromResultSet(lrs_rs);
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

		return lpt_proceso;
	}

	/**
	 * Método de transacción con la base de datos para la búsqueda de función por su máxxima versión
	 * @param as_idProceso con el id del proceso de la función
	 * @param as_idSubProceso con el id del subproceso de la función
	 * @return de tipo int con el resultado
	 * @throws B2BException
	 */
	public int findMaxVersion(String as_idProceso, String as_idSubProceso)
	    throws B2BException
	{
		int li_version;

		li_version = 0;

		if(StringUtils.isValidString(as_idProceso) && StringUtils.isValidString(as_idSubProceso))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FUNCION_MAXIMA_VERSION);

				lps_ps.setString(1, as_idProceso);
				lps_ps.setString(2, as_idSubProceso);
				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					li_version = lrs_rs.getInt(1);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findMaxVersion", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
				close(lrs_rs);
			}
		}

		return li_version;
	}

	/**
	 * Inserta un registro en la tabla a partir de un modelo creado en workflow
	 *
	 * @param apt_proceso Objeto contenedor de la información a insertar
	 * @throws B2BException Si ocurre un error en la comunicación con la base de datos
	 */
	public void insert(
	    ProcesoTrabajo apt_proceso, boolean ab_actualizarProceso, boolean ab_actualizarSubproceso,
	    boolean ab_actualizarVersion
	)
	    throws B2BException
	{
		if(apt_proceso != null)
		{
			PreparedStatement lps_accionProceso;
			PreparedStatement lps_accionSubProceso;
			PreparedStatement lps_accionVersion;
			PreparedStatement lps_eliminarFlujos;

			lps_accionProceso        = null;
			lps_accionSubProceso     = null;
			lps_accionVersion        = null;
			lps_eliminarFlujos       = null;

			try
			{
				SubProcesoTrabajo lspt_subProceso;

				lspt_subProceso = apt_proceso.getSubProceso();

				if(lspt_subProceso != null)
				{
					SubProcesoVersionTrabajo lspvt_version;

					lspvt_version = lspt_subProceso.getVersion();

					if(lspvt_version != null)
					{
						Connection lc_conexion;
						String     ls_idProceso;
						String     ls_idSubProceso;
						String     ls_userId;
						String     ls_remoteIp;
						int        li_version;

						lc_conexion         = getConnection();
						ls_idProceso        = apt_proceso.getId();
						ls_idSubProceso     = lspt_subProceso.getIdSubproceso();
						ls_userId           = ab_actualizarProceso ? apt_proceso.getIdUsuarioModificacion()
							                                       : apt_proceso.getIdUsuarioCreacion();
						ls_remoteIp         = ab_actualizarProceso ? apt_proceso.getIpModificacion()
							                                       : apt_proceso.getIpCreacion();
						li_version          = lspvt_version.getVersion();

						lps_accionProceso        = lc_conexion.prepareStatement(
							    ab_actualizarProceso ? cs_ACTUALIZAR_PROCESO : cs_INSERTAR_PROCESO
							);
						lps_accionSubProceso     = lc_conexion.prepareStatement(
							    ab_actualizarSubproceso ? cs_ACTUALIZAR_SUB_PROCESO : cs_INSERTAR_SUB_PROCESO
							);
						lps_accionVersion        = lc_conexion.prepareStatement(
							    ab_actualizarVersion ? cs_ACTUALIZAR_VERSION : cs_INSERTAR_VERSION
							);
						lps_eliminarFlujos       = lc_conexion.prepareStatement(cs_ELIMINAR_FLUJOS);

						{
							int li_contador;

							li_contador = 1;

							if(!ab_actualizarProceso)
								lps_accionProceso.setString(li_contador++, ls_idProceso);

							lps_accionProceso.setString(li_contador++, apt_proceso.getNombre());
							lps_accionProceso.setString(li_contador++, apt_proceso.getRecepcionDocumentos());
							lps_accionProceso.setString(li_contador++, ls_userId);
							lps_accionProceso.setString(li_contador++, ls_remoteIp);

							if(ab_actualizarProceso)
								lps_accionProceso.setString(li_contador++, ls_idProceso);

							lps_accionProceso.executeUpdate();
						}

						{
							int li_contador;

							li_contador = 1;

							if(!ab_actualizarSubproceso)
							{
								lps_accionSubProceso.setString(li_contador++, ls_idProceso);
								lps_accionSubProceso.setString(li_contador++, ls_idSubProceso);
							}

							lps_accionSubProceso.setString(li_contador++, lspt_subProceso.getNombre());
							lps_accionSubProceso.setString(li_contador++, ls_userId);
							lps_accionSubProceso.setString(li_contador++, ls_remoteIp);

							if(ab_actualizarSubproceso)
							{
								lps_accionSubProceso.setString(li_contador++, ls_idProceso);
								lps_accionSubProceso.setString(li_contador++, ls_idSubProceso);
							}

							lps_accionSubProceso.executeUpdate();
						}

						{
							int li_contador;

							li_contador = 1;

							if(!ab_actualizarVersion)
							{
								lps_accionVersion.setString(li_contador++, ls_idProceso);
								lps_accionVersion.setString(li_contador++, ls_idSubProceso);
								lps_accionVersion.setInt(li_contador++, li_version);
							}

							lps_accionVersion.setLong(li_contador++, lspvt_version.getIdEtapaInicial());
							lps_accionVersion.setString(li_contador++, lspvt_version.getEstadoActividad());
							lps_accionVersion.setString(li_contador++, lspvt_version.getSolicitudCertificado());
							lps_accionVersion.setString(li_contador++, lspvt_version.getPlantilla());
							lps_accionVersion.setString(li_contador++, lspvt_version.getObtenerReciboCaja());
							lps_accionVersion.setString(li_contador++, lspvt_version.getConservacionDocumental());

							{
								Clob lc_definicion;

								lc_definicion = ClobUtils.stringToClob(
									    lspvt_version.getXml(), lc_conexion.createClob()
									);

								lps_accionVersion.setClob(li_contador++, lc_definicion);
							}

							lps_accionVersion.setString(li_contador++, lspvt_version.getEstadoFlujo());

							if(!ab_actualizarVersion)
								lps_accionVersion.setInt(li_contador++, lspvt_version.getVersionBase());

							lps_accionVersion.setString(li_contador++, ls_userId);
							lps_accionVersion.setString(li_contador++, ls_remoteIp);

							if(ab_actualizarVersion)
							{
								lps_accionVersion.setString(li_contador++, ls_idProceso);
								lps_accionVersion.setString(li_contador++, ls_idSubProceso);
								lps_accionVersion.setInt(li_contador++, li_version);
							}

							lps_accionVersion.executeUpdate();
						}

						if(ab_actualizarVersion)
						{
							int li_contador;

							li_contador = 1;

							lps_eliminarFlujos.setString(li_contador++, ls_idProceso);
							lps_eliminarFlujos.setString(li_contador++, ls_idSubProceso);
							lps_eliminarFlujos.setInt(li_contador++, li_version);
							lps_eliminarFlujos.executeUpdate();
						}
					}
				}
			}
			catch(SQLException lse_e)
			{
				logError(this, "insert", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_accionProceso);
				close(lps_accionSubProceso);
				close(lps_accionVersion);
				close(lps_eliminarFlujos);
			}
		}
	}

	/**
	 * Retorna el valor de Proceso
	 *
	 * @param lrs_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de Proceso
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private ProcesoTrabajo getObjetFromResultSet(ResultSet lrs_rs)
	    throws SQLException
	{
		ProcesoTrabajo lpt_proceso;

		lpt_proceso = new ProcesoTrabajo();

		lpt_proceso.setId(lrs_rs.getString("ID_PROCESO"));
		lpt_proceso.setNombre(lrs_rs.getString("NOMBRE"));
		lpt_proceso.setRecepcionDocumentos(lrs_rs.getString("RECEPCION_DOCUMENTO"));

		fillAuditoria(lrs_rs, lpt_proceso);

		return lpt_proceso;
	}
}
