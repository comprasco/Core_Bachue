package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.model.sdb.acc.ConsultaCriterioAntSistema;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Clase dedicada al manejo de conexion y transacciones en la base de datos que afecten
 * directamente a la tabla SDB_ACC_CONSULTA_CRITERIO_ANT_SISTEMA.
 *
 * @author Manuel Blanco
 */
public class ConsultaCriterioAntSistemaDAO extends BaseDAO
{
	
	/** Constante cs_FIND_BY_ID. */
	public static final String cs_FIND_BY_ID = "SELECT ID_CRITERIO_ANT_SISTEMA,ID_TURNO_HISTORIA,ID_DOCUMENTO,ID_DATOS_ANT_SISTEMA,ID_ANOTACION,ID_CIRCULO,ID_MATRICULA,ID_USUARIO_CREACION,FECHA_CREACION,IP_CREACION,ID_USUARIO_MODIFICACION,FECHA_MODIFICACION,IP_MODIFICACION FROM SDB_ACC_CONSULTA_CRITERIO_ANT_SISTEMA WHERE ID_CRITERIO_ANT_SISTEMA = ?";
	
	/** Constante cs_FIND_BY_TH. */
	public static final String cs_FIND_BY_TH = "SELECT ID_CRITERIO_ANT_SISTEMA,ID_TURNO_HISTORIA,ID_DOCUMENTO,ID_DATOS_ANT_SISTEMA,ID_ANOTACION,ID_CIRCULO,ID_MATRICULA,ID_USUARIO_CREACION,FECHA_CREACION,IP_CREACION,ID_USUARIO_MODIFICACION,FECHA_MODIFICACION,IP_MODIFICACION FROM SDB_ACC_CONSULTA_CRITERIO_ANT_SISTEMA WHERE ID_TURNO_HISTORIA = ?";

	/**
	 * Consulta en la base de datos un registro que esté relacionada a un id criterio ant sistema.
	 *
	 * @param accas_param Objeto contenedor de los datos a utilizar como filtro en la consulta
	 * @return ConsultaCriterioAntSistema resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see ConsultaCriterioAntSistema
	 */
	public ConsultaCriterioAntSistema findById(ConsultaCriterioAntSistema accas_param)
	    throws B2BException
	{
		ConsultaCriterioAntSistema lccas_ccas;

		lccas_ccas = null;

		if(accas_param != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				int li_cont;

				li_cont = 1;

				lps_ps.setString(li_cont++, accas_param.getIdCriterioAntSistema());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lccas_ccas = getObjectFromResultSet(lrs_rs);
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

		return lccas_ccas;
	}

	/**
	 * Consulta en la base de datos un registro que esté relacionada a un id turno historia.
	 *
	 * @param accas_param Objeto contenedor de los datos a utilizar como filtro en la consulta
	 * @return ConsultaCriterioAntSistema resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see ConsultaCriterioAntSistema
	 */
	public ConsultaCriterioAntSistema findByTH(ConsultaCriterioAntSistema accas_param)
	    throws B2BException
	{
		ConsultaCriterioAntSistema lccas_object;

		lccas_object = null;

		if(accas_param != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_TH);

				int li_cont;

				li_cont = 1;

				lps_ps.setLong(li_cont++, accas_param.getIdTurnoHistoria());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lccas_object = getObjectFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByTH", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lccas_object;
	}

	/**
	 * Extrae la información recuperada de la base de datos y la asigna a un objeto ConsultaCriterioAntSistema.
	 *
	 * @param ars_param correspondiente al valor del tipo de objeto ResultSet
	 * @return ConsultaCriterioAntSistema con la información extraida de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private ConsultaCriterioAntSistema getObjectFromResultSet(ResultSet ars_param)
	    throws B2BException, SQLException
	{
		ConsultaCriterioAntSistema lccas_ccas;
		lccas_ccas = new ConsultaCriterioAntSistema();

		lccas_ccas.setIdCriterioAntSistema(ars_param.getString("ID_CRITERIO_ANT_SISTEMA"));
		lccas_ccas.setIdTurnoHistoria(ars_param.getLong("ID_TURNO_HISTORIA"));
		lccas_ccas.setIdDocumento(ars_param.getString("ID_DOCUMENTO"));
		lccas_ccas.setIdDatosAntSistema(ars_param.getString("ID_DATOS_ANT_SISTEMA"));
		lccas_ccas.setIdAnotacion(ars_param.getString("ID_ANOTACION"));
		lccas_ccas.setIdCirculo(ars_param.getString("ID_CIRCULO"));
		lccas_ccas.setIdMatricula(getLong(ars_param, "ID_MATRICULA"));
		lccas_ccas.setIdUsuarioCreacion(ars_param.getString("ID_USUARIO_CREACION"));
		lccas_ccas.setFechaCreacion(ars_param.getTimestamp("FECHA_CREACION"));
		lccas_ccas.setIpCreacion(ars_param.getString("IP_CREACION"));
		lccas_ccas.setIdUsuarioModificacion(ars_param.getString("ID_USUARIO_MODIFICACION"));
		lccas_ccas.setFechaModificacion(ars_param.getTimestamp("FECHA_MODIFICACION"));
		lccas_ccas.setIpModificacion(ars_param.getString("IP_MODIFICACION"));

		return lccas_ccas;
	}
}
