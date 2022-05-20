package com.bachue.snr.prosnr01.dao.bng;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.model.sdb.bng.AnotacionCancelacion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las consultas de la tabla SDB_BNG_ANOTACION_CANCELACION
 *
 * @author garias
 */
public class AnotacionCancelacionDAO extends BaseDAO
{
	/** Constante cs_FIND_BY_CIRCULO_MATRICULA. */
	private static final String cs_FIND_BY_CIRCULO_MATRICULA = "SELECT * FROM SDB_BNG_ANOTACION_CANCELACION "
		+ "WHERE ID_ANOTACION = ? AND  ID_CIRCULO = ? AND ID_MATRICULA = ? ORDER BY ID_ANOTACION ASC";

	/**
	 * Busca todos los registros asociados a una matrícula e id anotación.
	 *
	 * @param aac_param Objeto contenedor de los datos a utilizar como filtro en la busqueda
	 * @return Colección resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<AnotacionCancelacion> findByAnotaciones(AnotacionCancelacion aac_param)
	    throws B2BException
	{
		return (aac_param != null)
		? findByAnotaciones(aac_param.getIdAnotacion(), aac_param.getIdCirculo(), aac_param.getIdMatricula()) : null;
	}

	/**
	 * Busca todos los registros asociados a una matrícula e id anotación.
	 *
	 * @param al_idAnotacion id de la anotación asociada a la matrícula
	 * @param as_idCirculo id del círculo al cual pertenece la matrícula
	 * @param al_idMatricula id de la matrícula a utilizar como filtro en la consulta
	 * @return Colección resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<AnotacionCancelacion> findByAnotaciones(
	    long al_idAnotacion, String as_idCirculo, long al_idMatricula
	)
	    throws B2BException
	{
		Collection<AnotacionCancelacion> lcap_datos;

		lcap_datos = new ArrayList<AnotacionCancelacion>();

		if((al_idAnotacion > 0L) && StringUtils.isValidString(as_idCirculo) && (al_idMatricula > 0L))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_contador;

				li_contador     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_CIRCULO_MATRICULA);

				lps_ps.setLong(li_contador++, al_idAnotacion);
				lps_ps.setString(li_contador++, as_idCirculo);
				lps_ps.setLong(li_contador++, al_idMatricula);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcap_datos.add(getObjetFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByAnotaciones", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(!CollectionUtils.isValidCollection(lcap_datos))
			lcap_datos = null;

		return lcap_datos;
	}

	/**
	 * Retorna el valor de AnotacionCancelacion
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de AnotacionCancelacion
	 * @throws SQLException Señala que se ha producido una excepción
	 * @see AnotacionCancelacion
	 */
	private AnotacionCancelacion getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		AnotacionCancelacion lap_ap;

		lap_ap = new AnotacionCancelacion();

		lap_ap.setIdCirculo(ars_rs.getString("ID_CIRCULO"));
		lap_ap.setIdMatricula(ars_rs.getLong("ID_MATRICULA"));
		lap_ap.setIdAnotacion(ars_rs.getLong("ID_ANOTACION"));
		lap_ap.setIdAnotacion1(ars_rs.getLong("ID_ANOTACION1"));
		lap_ap.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		lap_ap.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));

		return lap_ap;
	}
}
