package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.dao.ConstantesDao;

import com.bachue.snr.prosnr01.model.sdb.acc.RegistroAnotacionProhibicion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Collection;
import java.util.LinkedList;


/**
 * Clase que contiene todos las propiedades de RegistroAnotacionProhibicionDAO.
 *
 * @author mblanco
 */
public class RegistroAnotacionProhibicionDAO extends BaseDAO
{
	/** Constante cs_FIND_BY_CIRCULO_MATRICULA. */
	private static final String cs_FIND_BY_CIRCULO_MATRICULA = "SELECT * FROM @@_NOMBRE_TABLA_@@ WHERE ID_CIRCULO = ? AND ID_MATRICULA = ? AND ACTIVO = 'S'";

	/**
	 * Retorna el valor de todos los registros asociados a un id círculo e id matrícula
	 *
	 * @param as_idCirculo identificador de circulo con el cúal se realizará la consulta en la base de datos.
	 * @param al_idMatricula identificador de mátricula con el cúal se realizará la consulta en la base de datos.
	 * @param ab_bng bandera que indica que tabla se usará en la consulta
	 * @return objeto de tipo RegistroAnotacionProhibicion
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see RegistroAnotacionProhibicion
	 */
	public Collection<RegistroAnotacionProhibicion> findAllByCirculoMatricula(
	    String as_idCirculo, long al_idMatricula, boolean ab_bng
	)
	    throws B2BException
	{
		Collection<RegistroAnotacionProhibicion> lcrap_object;

		lcrap_object = new LinkedList<RegistroAnotacionProhibicion>();

		if(StringUtils.isValidString(as_idCirculo) && (al_idMatricula > 0L))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int    li_column;
				String ls_query;

				li_column     = 1;
				ls_query      = cs_FIND_BY_CIRCULO_MATRICULA;

				ls_query     = ls_query.replace(
					    ConstantesDao.NOMBRE_TABLA,
					    ab_bng ? "SDB_BNG_REGISTRO_ANOTACION_PROHIBICION" : "SDB_ACC_REGISTRO_ANOTACION_PROHIBICION"
					);

				lps_ps = getConnection().prepareStatement(ls_query);

				lps_ps.setString(li_column++, as_idCirculo);
				lps_ps.setLong(li_column++, al_idMatricula);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcrap_object.add(getObjetFromResultSet(lrs_rs, ab_bng));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findAllByCirculoMatricula", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lcrap_object.isEmpty())
			lcrap_object = null;

		return lcrap_object;
	}

	/**
	 * Retorna el valor del objeto de RegistroAnotacionProhibicion.
	 *
	 * @param as_idCirculo identificador de circulo con el cúal se realizará la consulta en la base de datos.
	 * @param al_idMatricula identificador de mátricula con el cúal se realizará la consulta en la base de datos.
	 * @param ab_bng bandera que indica que tabla se usará en la consulta
	 * @return objeto de tipo RegistroAnotacionProhibicion
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see RegistroAnotacionProhibicion
	 */
	public RegistroAnotacionProhibicion findByCirculoMatricula(
	    String as_idCirculo, long al_idMatricula, boolean ab_bng
	)
	    throws B2BException
	{
		RegistroAnotacionProhibicion lrap_object;
		PreparedStatement            lps_ps;
		ResultSet                    lrs_rs;
		int                          li_column;

		lrap_object     = null;
		lps_ps          = null;
		lrs_rs          = null;
		li_column       = 1;

		try
		{
			if(StringUtils.isValidString(as_idCirculo) && (al_idMatricula > 0L))
			{
				String ls_query;

				ls_query     = cs_FIND_BY_CIRCULO_MATRICULA;

				ls_query     = ls_query.replace(
					    ConstantesDao.NOMBRE_TABLA,
					    ab_bng ? "SDB_BNG_REGISTRO_ANOTACION_PROHIBICION" : "SDB_ACC_REGISTRO_ANOTACION_PROHIBICION"
					);

				lps_ps = getConnection().prepareStatement(ls_query);

				lps_ps.setString(li_column++, as_idCirculo);
				lps_ps.setLong(li_column++, al_idMatricula);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lrap_object = getObjetFromResultSet(lrs_rs, ab_bng);
			}
		}
		catch(SQLException lse_e)
		{
			logError(this, "findByCirculoMatricula", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return lrap_object;
	}

	/**
	 * Retorna el valor deRegistroAnotacionProhibicion
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @param ab_bng correspondiente al valor del tipo de objeto boolean
	 * @return el valor de RegistroAnotacionProhibicion
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private RegistroAnotacionProhibicion getObjetFromResultSet(ResultSet ars_rs, boolean ab_bng)
	    throws SQLException
	{
		RegistroAnotacionProhibicion lrap_datos;

		lrap_datos = new RegistroAnotacionProhibicion();

		lrap_datos.setIdCirculo(ars_rs.getString("ID_CIRCULO"));
		lrap_datos.setIdMatricula(ars_rs.getLong("ID_MATRICULA"));
		lrap_datos.setIdAnotacion(ars_rs.getLong("ID_ANOTACION"));
		lrap_datos.setTipoAdquisicion(ars_rs.getString("ID_TIPO_ADQUISICION"));
		lrap_datos.setIdTurno(ars_rs.getString("ID_TURNO"));
		lrap_datos.setFechaVencimiento(ars_rs.getTimestamp("FECHA_VENCIMIENTO"));
		lrap_datos.setActivo(ars_rs.getString("ACTIVO"));
		lrap_datos.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		lrap_datos.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		lrap_datos.setIpCreacion(ars_rs.getString("IP_CREACION"));
		lrap_datos.setIdUsuarioModificacion(ars_rs.getString("ID_USUARIO_MODIFICACION"));
		lrap_datos.setFechaModificacion(ars_rs.getTimestamp("FECHA_CREACION"));
		lrap_datos.setIpModificacion(ars_rs.getString("IP_MODIFICACION"));

		if(!ab_bng)
			lrap_datos.setIdSolicitud(ars_rs.getString("ID_SOLICITUD"));

		return lrap_datos;
	}
}
