package com.bachue.snr.prosnr02.dao.pgn;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr02.model.pgn.MotivoTramiteTrabajo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Clase para el manejo de datos para la tabla SDB_PGN_MOTIVO_TRAMITE_TRABAJO.
 *
 * @author Sebastian Tafur
 */
public class MotivoTramiteTrabajoDAO extends AuditoriaDao
{
	/** Constante cs_FIND_SECUENCE. */
	private static final String cs_FIND_SECUENCE = "SELECT SEC_PGN_MOTIVO_TRAMITE_ID_MOTIVO_TRAMITE.NEXTVAL FROM DUAL";
	
	/** Constante cs_FIND_ID_ETAPA_ORIGEN. */
	private static final String cs_FIND_ID_ETAPA_ORIGEN = "SELECT DISTINCT ID_ETAPA_ORIGEN FROM SDB_PGN_MOTIVO_TRAMITE_TRABAJO MT WHERE VERSION = ? AND ID_PROCESO = ? AND ID_SUBPROCESO = ? AND NOT EXISTS ( SELECT 1 FROM SDB_PGN_MOTIVO_TRAMITE_TRABAJO MV WHERE MT.VERSION = MV.VERSION AND MT.ID_PROCESO = MV.ID_PROCESO AND MT.ID_SUBPROCESO = MV.ID_SUBPROCESO AND MV.ID_ETAPA_POSTERIOR = MT.ID_ETAPA_ORIGEN)";

	/** Constante cs_INSERT. */
	private static final String cs_INSERTAR = "INSERT INTO SDB_PGN_MOTIVO_TRAMITE_TRABAJO(ID_MOTIVO_TRAMITE,ID_PROCESO,"
		+ "ID_SUBPROCESO,VERSION,ID_MOTIVO,DESCRIPCION,ID_ETAPA_ORIGEN,ID_ETAPA_POSTERIOR,ESTADO_ACTIVIDAD,"
		+ "INDICADOR_DEVOLUCION,TIPO_COMPUERTA_LLEGADA,DECISION_CALIFICACION,FLUJO_DEFECTO,DESCRIPTOR_FIN,ESTADO,"
		+ "ID_USUARIO_CREACION,FECHA_CREACION,IP_CREACION)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,SYSTIMESTAMP,?)";

	   public int findIdEtapaOrigen(int ai_idVersion, String as_idProceso, String as_idSubproceso)
	        throws B2BException
	    {
	        int               li_idEtapa;
	        PreparedStatement lps_ps;
	        ResultSet         lrs_rs;

	        li_idEtapa     = 0;
	        lps_ps         = null;
	        lrs_rs         = null;

	        try
	        {
	            lps_ps = getConnection().prepareStatement(cs_FIND_ID_ETAPA_ORIGEN);

	            lps_ps.setInt(1, ai_idVersion);
	            lps_ps.setString(2, as_idProceso);
	            lps_ps.setString(3, as_idSubproceso);
	            
	            lrs_rs = lps_ps.executeQuery();

	            if(lrs_rs.next())
	                li_idEtapa = lrs_rs.getInt(1);
	        }
	        catch(SQLException lse_e)
	        {
	            logError(this, "findIdEtapaOrigen", lse_e);

	            throw new B2BException(SQL_ERROR, lse_e);
	        }
	        finally
	        {
	            close(lrs_rs);
	            close(lps_ps);
	        }

	        return li_idEtapa;
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

	/**
	 * Inserta un registro en la tabla a partir de un modelo creado en workflow
	 *
	 * @param amtt_flujo Objeto contenedor de la información a insertar
	 * @throws B2BException Si ocurre un error en la comunicación con la base de datos
	 */
	public void insert(MotivoTramiteTrabajo amtt_flujo)
	    throws B2BException
	{
		if(amtt_flujo != null)
		{
			PreparedStatement lps_insercion;

			lps_insercion = null;

			try
			{
				Connection lc_conexion;
				int        li_column;

				lc_conexion       = getConnection();
				li_column         = 1;
				lps_insercion     = lc_conexion.prepareStatement(cs_INSERTAR);

				lps_insercion.setString(li_column++, StringUtils.getString(findSecuence()));
				lps_insercion.setString(li_column++, amtt_flujo.getIdProceso());
				lps_insercion.setString(li_column++, amtt_flujo.getIdSubProceso());

				lps_insercion.setInt(li_column++, amtt_flujo.getVersion());

				setInteger(lps_insercion, amtt_flujo.getMotivo(), li_column++);

				lps_insercion.setString(li_column++, amtt_flujo.getDescripcion());

				setInteger(lps_insercion, amtt_flujo.getIdEtapaOrigen(), li_column++);
				setInteger(lps_insercion, amtt_flujo.getIdEtapaDestino(), li_column++);

				lps_insercion.setString(li_column++, amtt_flujo.getEstadoActividad());
				lps_insercion.setString(li_column++, amtt_flujo.getIndicadorDevolucion());
				lps_insercion.setString(li_column++, amtt_flujo.getTipoCompuertaLlegada());
				lps_insercion.setString(li_column++, amtt_flujo.getDecisionCalificacion());
				lps_insercion.setString(li_column++, StringUtils.getString(amtt_flujo.isFlujoDefecto()));
				lps_insercion.setString(li_column++, amtt_flujo.getDescriptorFin());
				lps_insercion.setString(li_column++, amtt_flujo.getEstado());
				lps_insercion.setString(li_column++, amtt_flujo.getIdUsuarioCreacion());
				lps_insercion.setString(li_column++, amtt_flujo.getIpCreacion());

				lps_insercion.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "insert", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}

			finally
			{
				close(lps_insercion);
			}
		}
	}
}
