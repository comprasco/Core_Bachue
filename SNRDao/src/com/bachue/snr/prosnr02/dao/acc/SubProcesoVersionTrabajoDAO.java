package com.bachue.snr.prosnr02.dao.acc;

import com.b2bsg.common.dataAccess.oracle.ClobUtils;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;
import com.bachue.snr.prosnr02.model.acc.EtapaTrabajo;
import com.bachue.snr.prosnr02.model.acc.SubProcesoVersionTrabajo;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Controla las acciones CRUD sobre la tabla SDB_ACC_SUBPROCESO_VERSION_TRABAJO.
 *
 * @author jpatino
 */
public class SubProcesoVersionTrabajoDAO extends AuditoriaDao
{
  /** Constante cs_UPDATE_ID_ETAPA_INICIAL. */
  private static final String cs_UPDATE_ID_ETAPA_INICIAL = "UPDATE SDB_ACC_SUBPROCESO_VERSION_TRABAJO SET ID_ETAPA_INICIAL=?, ESTADO_ACTIVIDAD=?,  ID_USUARIO_MODIFICACION=?, FECHA_MODIFICACION=SYSTIMESTAMP, IP_MODIFICACION = ? WHERE VERSION = ? AND ID_PROCESO = ? AND ID_SUBPROCESO = ?";
  
	/** Constante cs_FIND_ALL_BY_PROCESO_SUBPROCESO. */
	private static final String cs_FIND_ALL_BY_PROCESO_SUBPROCESO = "SELECT * FROM SDB_ACC_SUBPROCESO_VERSION_TRABAJO WHERE ID_PROCESO=? AND ID_SUBPROCESO=?";

	/** Constante cs_FIND_ALL_APROBACION. */
	private static final String cs_FIND_ALL_APROBACION = "SELECT * FROM SDB_ACC_SUBPROCESO_VERSION_TRABAJO WHERE ESTADO_FLUJO = 'APROBADOR' AND ID_PROCESO = ?  AND ID_SUBPROCESO = ?";

	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_ACC_SUBPROCESO_VERSION_TRABAJO WHERE ID_PROCESO=? AND ID_SUBPROCESO=? AND VERSION=?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_ACC_SUBPROCESO_VERSION_TRABAJO (ID_PROCESO,ID_SUBPROCESO,VERSION,ID_ETAPA_INICIAL,ESTADO_ACTIVIDAD,SOLICITUD_CERTIFICADO,PLANTILLA,OBTENER_RECIBO_CAJA,CONSERVACION_DOCUMENTAL,DEFINICION,ESTADO_FLUJO,ID_USUARIO_CREACION,FECHA_CREACION,IP_CREACION) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,SYSTIMESTAMP,?)";

	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_ACC_SUBPROCESO_VERSION_TRABAJO T INNER JOIN (SELECT ID_PROCESO, ID_SUBPROCESO, MAX(VERSION) MVERSION FROM SDB_ACC_SUBPROCESO_VERSION_TRABAJO GROUP BY ID_PROCESO,ID_SUBPROCESO ) M ON T.ID_PROCESO= M.ID_PROCESO AND T.ID_SUBPROCESO= M.ID_SUBPROCESO AND T.VERSION=M.MVERSION INNER JOIN SDB_ACC_SUBPROCESO_TRABAJO SP ON T.ID_PROCESO = SP.ID_PROCESO AND T.ID_SUBPROCESO=SP.ID_SUBPROCESO ORDER BY TO_NUMBER(T.ID_PROCESO),TO_NUMBER(T.ID_SUBPROCESO),SP.NOMBRE,T.VERSION";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_ACC_SUBPROCESO_VERSION_TRABAJO SET ID_ETAPA_INICIAL=?, ESTADO_ACTIVIDAD=?, SOLICITUD_CERTIFICADO=?, PLANTILLA=?, OBTENER_RECIBO_CAJA=?, CONSERVACION_DOCUMENTAL=?, DEFINICION=?, ESTADO_FLUJO=?, ID_USUARIO_MODIFICACION=?, FECHA_MODIFICACION=SYSTIMESTAMP, IP_MODIFICACION = ? WHERE ID_PROCESO=? AND ID_SUBPROCESO =? AND VERSION=?";

	/**
     * Inserta un registro con la información del subproceso versión suministrada.
     *
     * @param aspvt_param objeto contenedor de la información que actualizará
     * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
     */
    public void updateIdEtapaInicial(
        EtapaTrabajo ai_etapaOrigen, int ai_idVersion, String as_idProceso, String as_idSubproceso, String as_userId, String as_remoteIp
    )
        throws B2BException
    {
        if((ai_etapaOrigen != null) && (ai_idVersion > 0))
        {
            PreparedStatement lps_ps;

            lps_ps = null;

            try
            {
                Connection lc_conexion;
                int        li_contador;

                lc_conexion     = getConnection();
                li_contador     = 1;
                lps_ps          = lc_conexion.prepareStatement(cs_UPDATE_ID_ETAPA_INICIAL);

                lps_ps.setLong(li_contador++, ai_etapaOrigen.getIdEtapa());
                lps_ps.setString(li_contador++, ai_etapaOrigen.getEstadoActividad());
                lps_ps.setString(li_contador++, as_userId);
                lps_ps.setString(li_contador++, as_remoteIp);
                lps_ps.setInt(li_contador++, ai_idVersion);
                lps_ps.setString(li_contador++, as_idProceso);
                lps_ps.setString(li_contador++, as_idSubproceso);

                lps_ps.executeUpdate();
            }
            catch(SQLException lse_e)
            {
                logError(this, "updateIdEtapaInicial", lse_e);

                throw new B2BException(SQL_ERROR, lse_e);
            }
            finally
            {
                close(lps_ps);
            }
        }
    }
	
	/**
	 * Consulta todos los subprocesos y los filtra por un id proceso previamente definido.
	 *
	 * @return Colección de Subprocesos resultante de la consulta
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<SubProcesoVersionTrabajo> findAllSubProcesos()
	    throws B2BException
	{
		Collection<SubProcesoVersionTrabajo> lcspt_subProcesos;

		lcspt_subProcesos = null;

		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lps_ps     = null;
		lrs_rs     = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL);

			lrs_rs                = lps_ps.executeQuery();
			lcspt_subProcesos     = new ArrayList<SubProcesoVersionTrabajo>();

			while(lrs_rs.next())
				lcspt_subProcesos.add(getObjectFromResultSet(lrs_rs, true));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findAllSubProcesos", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return lcspt_subProcesos;
	}

	/**
	 * Consulta en la base de datos un subproceso, filtrando por un id de
	 * subproceso definido con anterioridad.
	 *
	 * @param as_idProceso de as id proceso
	 * @param as_idSubProceso de as id sub proceso
	 * @return Subproceso resultante de la ejecución de la consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 * @throws IOException Objeto de tipo IOException, se produce cuando se encuentra algun error de tipo I/O.
	 */
	public Collection<SubProcesoVersionTrabajo> findByAllAprobacion(String as_idProceso, String as_idSubProceso)
	    throws B2BException, IOException
	{
		Collection<SubProcesoVersionTrabajo> lcspvt_object;

		lcspvt_object = null;

		if(StringUtils.isValidString(as_idProceso) && StringUtils.isValidString(as_idSubProceso))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_FIND_ALL_APROBACION);

				lps_ps.setString(li_column++, as_idProceso);
				lps_ps.setString(li_column++, as_idSubProceso);

				lrs_rs            = lps_ps.executeQuery();
				lcspvt_object     = new ArrayList<SubProcesoVersionTrabajo>();

				while(lrs_rs.next())
					lcspvt_object.add(getObjectFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByAllAprobacion", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lcspvt_object;
	}

	/**
	 * Consulta en la base de datos un subproceso, filtrando por un id de
	 * subproceso definido con anterioridad.
	 *
	 * @param as_idProceso de as id proceso
	 * @param as_idSubProceso de as id sub proceso
	 * @param ai_version de ai version
	 * @return Subproceso resultante de la ejecución de la consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 * @throws IOException Objeto de tipo IOException, se produce cuando se encuentra algun error de tipo I/O.
	 */
	public SubProcesoVersionTrabajo findById(String as_idProceso, String as_idSubProceso, int ai_version)
	    throws B2BException
	{
		SubProcesoVersionTrabajo lspvt_version;

		lspvt_version = null;

		if(StringUtils.isValidString(as_idProceso) && StringUtils.isValidString(as_idSubProceso))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(li_column++, as_idProceso);
				lps_ps.setString(li_column++, as_idSubProceso);
				lps_ps.setInt(li_column++, ai_version);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lspvt_version = getObjectFromResultSet(lrs_rs);
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

		return lspvt_version;
	}

	/**
	 * Consulta en la base de datos un subproceso, filtrando por un id de
	 * subproceso definido con anterioridad.
	 *
	 * @param as_idProceso de as id proceso
	 * @param as_idSubProceso de as id sub proceso
	 * @return Subproceso resultante de la ejecución de la consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 * @throws IOException Objeto de tipo IOException, se produce cuando se encuentra algun error de tipo I/O.
	 */
	public Collection<SubProcesoVersionTrabajo> findByProcesoSubproceso(String as_idProceso, String as_idSubProceso)
	    throws B2BException, IOException
	{
		Collection<SubProcesoVersionTrabajo> lcspvt_versiones;

		lcspvt_versiones = null;

		if(StringUtils.isValidString(as_idProceso) && StringUtils.isValidString(as_idSubProceso))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_FIND_ALL_BY_PROCESO_SUBPROCESO);

				lps_ps.setString(li_column++, as_idProceso);
				lps_ps.setString(li_column++, as_idSubProceso);

				lrs_rs               = lps_ps.executeQuery();
				lcspvt_versiones     = new ArrayList<SubProcesoVersionTrabajo>();

				while(lrs_rs.next())
					lcspvt_versiones.add(getObjectFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByProcesoSubproceso", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lcspvt_versiones;
	}

	/**
	 * Inserta un registro con la información del subproceso versión suministrada.
	 *
	 * @param aspvt_version objeto contenedor de la información a insertar
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void insert(SubProcesoVersionTrabajo aspvt_version)
	    throws B2BException
	{
		if(aspvt_version != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_INSERT);

				lps_ps.setString(li_column++, aspvt_version.getIdProceso());
				lps_ps.setString(li_column++, aspvt_version.getIdSubproceso());
				lps_ps.setLong(li_column++, aspvt_version.getVersion());
				lps_ps.setLong(li_column++, aspvt_version.getIdEtapaInicial());
				lps_ps.setString(li_column++, aspvt_version.getEstadoActividad());
				lps_ps.setString(li_column++, aspvt_version.getSolicitudCertificado());
				lps_ps.setString(li_column++, aspvt_version.getPlantilla());
				lps_ps.setString(li_column++, aspvt_version.getObtenerReciboCaja());
				lps_ps.setString(li_column++, aspvt_version.getConservacionDocumental());
				lps_ps.setBinaryStream(li_column++, new ByteArrayInputStream(aspvt_version.getDefinicion()));
				lps_ps.setString(li_column++, aspvt_version.getEstadoFlujo());
				lps_ps.setString(li_column++, aspvt_version.getIdUsuarioCreacion());
				lps_ps.setString(li_column++, aspvt_version.getIpCreacion());
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
	 * Inserta un registro con la información del subproceso versión suministrada.
	 *
	 * @param aspvt_param objeto contenedor de la información que actualizará
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void update(SubProcesoVersionTrabajo aspvt_param)
	    throws B2BException
	{
		if(aspvt_param != null)
		{
			PreparedStatement lps_ps;

			lps_ps = null;

			try
			{
				Connection lc_conexion;
				int        li_contador;

				lc_conexion     = getConnection();
				li_contador     = 1;
				lps_ps          = lc_conexion.prepareStatement(cs_UPDATE);

				lps_ps.setLong(li_contador++, aspvt_param.getIdEtapaInicial());
				lps_ps.setString(li_contador++, aspvt_param.getEstadoActividad());
				lps_ps.setString(li_contador++, aspvt_param.getSolicitudCertificado());
				lps_ps.setString(li_contador++, aspvt_param.getPlantilla());
				lps_ps.setString(li_contador++, aspvt_param.getObtenerReciboCaja());
				lps_ps.setString(li_contador++, aspvt_param.getConservacionDocumental());

				{
					Clob lc_definicion;

					lc_definicion = ClobUtils.stringToClob(aspvt_param.getXml(), lc_conexion.createClob());

					lps_ps.setClob(li_contador++, lc_definicion);
				}

				lps_ps.setString(li_contador++, aspvt_param.getEstadoFlujo());
				lps_ps.setString(li_contador++, aspvt_param.getIdUsuarioModificacion());
				lps_ps.setString(li_contador++, aspvt_param.getIpModificacion());
				lps_ps.setString(li_contador++, aspvt_param.getIdProceso());
				lps_ps.setString(li_contador++, aspvt_param.getIdSubproceso());
				lps_ps.setInt(li_contador++, aspvt_param.getVersion());

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
	 * Permite almacenar los datos que retornó la consulta en los atributos
	 * de un objeto.
	 *
	 * @param ars_rs contenedor del resultado de a consulta
	 * @return objeto contenedor de los datos que retornó la consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 * @throws SQLException cuando se produce algun error en el proceso
	 */
	private SubProcesoVersionTrabajo getObjectFromResultSet(ResultSet ars_rs)
	    throws B2BException, SQLException
	{
		return getObjectFromResultSet(ars_rs, false);
	}

	/**
	 * Permite almacenar los datos que retornó la consulta en los atributos
	 * de un objeto.
	 *
	 * @param ars_rs contenedor del resultado de a consulta
	 * @param ab_nombre de ab nombre
	 * @return objeto contenedor de los datos que retornó la consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 * @throws SQLException cuando se produce algun error en el proceso
	 */
	private SubProcesoVersionTrabajo getObjectFromResultSet(ResultSet ars_rs, boolean ab_nombre)
	    throws B2BException, SQLException
	{
		SubProcesoVersionTrabajo lspvt_version;

		lspvt_version = new SubProcesoVersionTrabajo();

		lspvt_version.setIdProceso(ars_rs.getString("ID_PROCESO"));
		lspvt_version.setIdSubproceso(ars_rs.getString("ID_SUBPROCESO"));
		lspvt_version.setVersion(ars_rs.getInt("VERSION"));
		lspvt_version.setIdEtapaInicial(ars_rs.getLong("ID_ETAPA_INICIAL"));
		lspvt_version.setEstadoActividad(ars_rs.getString("ESTADO_ACTIVIDAD"));
		lspvt_version.setSolicitudCertificado(ars_rs.getString("SOLICITUD_CERTIFICADO"));
		lspvt_version.setPlantilla(ars_rs.getString("PLANTILLA"));
		lspvt_version.setObtenerReciboCaja(ars_rs.getString("OBTENER_RECIBO_CAJA"));
		lspvt_version.setConservacionDocumental(ars_rs.getString("CONSERVACION_DOCUMENTAL"));
		lspvt_version.setXml(ClobUtils.clobToString(ars_rs.getClob("DEFINICION")));
		lspvt_version.setEstadoFlujo(ars_rs.getString("ESTADO_FLUJO"));

		if(ab_nombre)
			lspvt_version.setNombre(ars_rs.getString("NOMBRE"));

		fillAuditoria(ars_rs, lspvt_version);

		return lspvt_version;
	}
}
