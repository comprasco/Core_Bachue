package com.bachue.snr.prosnr01.dao.bng;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr01.model.sdb.bng.SalvedadAnotacion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase dedicada al manejo de conexion y transacciones en la base de datos que afecten
 * directamente a la tabla SDB_BNG_SALVEDAD_PREDIO.
 *
 * @author Manuel Blanco
 */
public class SalvedadAnotacionDAO extends AuditoriaDao
{
	/** Constante cs_FIND. */
	private static final String cs_FIND = "SELECT * FROM SDB_BNG_SALVEDAD_ANOTACION WHERE ID_CIRCULO = ? AND ID_MATRICULA = ? AND ID_ANOTACION = ?";

	/** Constante cs_FIND_BY_ID_CIRCULO_ID_MATRICULA. */
	private static final String cs_FIND_BY_ID_CIRCULO_ID_MATRICULA = "SELECT * FROM SDB_BNG_SALVEDAD_ANOTACION WHERE ID_CIRCULO = ? AND ID_MATRICULA = ?";

	/**
	 * Consulta los registros asociados a un id círculo e id matrícula específicos.
	 *
	 * @param asp_param Objeto contenedor de los filtros a utilizar en la consulta
	 * @return Colección resultante de la consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public SalvedadAnotacion find(SalvedadAnotacion asp_param)
	    throws B2BException
	{
		return (asp_param != null)
		? find(asp_param.getIdCirculo(), asp_param.getIdMatricula(), asp_param.getIdAnotacion()) : null;
	}

	/**
	 * Consulta los registros asociados a un id círculo e id matrícula específicos.
	 *
	 * @param as_idCirculo de as id circulo
	 * @param al_idMatricula de al id matricula
	 * @param ll_idAnotacion de ll id anotacion
	 * @return Colección resultante de la consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public SalvedadAnotacion find(String as_idCirculo, Long al_idMatricula, Long ll_idAnotacion)
	    throws B2BException
	{
		SalvedadAnotacion lsa_return;

		lsa_return = null;

		if(
		    StringUtils.isValidString(as_idCirculo) && NumericUtils.isValidLong(al_idMatricula)
			    && NumericUtils.isValidLong(ll_idAnotacion)
		)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_cont;

				li_cont     = 1;
				lps_ps      = getConnection().prepareStatement(cs_FIND);

				lps_ps.setString(li_cont++, as_idCirculo);
				setLong(lps_ps, al_idMatricula, li_cont++);
				setLong(lps_ps, ll_idAnotacion, li_cont++);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lsa_return = getObjetFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "find", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lsa_return;
	}

	/**
	 * Find by circulo matricula.
	 *
	 * @param as_idCirculo de as id circulo
	 * @param al_idMatricula de al id matricula
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<SalvedadAnotacion> findByCirculoMatricula(String as_idCirculo, Long al_idMatricula)
	    throws B2BException
	{
		Collection<SalvedadAnotacion> lcsa_return;

		lcsa_return = new ArrayList<SalvedadAnotacion>();

		if(StringUtils.isValidString(as_idCirculo) && NumericUtils.isValidLong(al_idMatricula))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_cont;

				li_cont     = 1;
				lps_ps      = getConnection().prepareStatement(cs_FIND_BY_ID_CIRCULO_ID_MATRICULA);

				lps_ps.setString(li_cont++, as_idCirculo);
				setLong(lps_ps, al_idMatricula, li_cont++);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcsa_return.add(getObjetFromResultSet(lrs_rs));
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
		}

		if(lcsa_return.isEmpty())
			lcsa_return = null;

		return lcsa_return;
	}

	/**
	 * Extrae la información resultante de la consulta.
	 *
	 * @param ars_rs Objeto contenedor del resultado de la consulta
	 * @return Objeto SalvedadPredio resultante de la consulta
	 * @throws SQLException cuando se produce algun error en el proceso
	 */
	private SalvedadAnotacion getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		SalvedadAnotacion lsa_return;

		lsa_return = new SalvedadAnotacion();

		lsa_return.setIdCirculo(ars_rs.getString("ID_CIRCULO"));
		lsa_return.setIdMatricula(getLong(ars_rs, "ID_MATRICULA"));
		lsa_return.setIdAnotacion(getLong(ars_rs, "ID_ANOTACION"));
		lsa_return.setIdSalvedadAnotacion(getBigDecimal(ars_rs, "ID_SALVEDAD_ANOTACION"));
		lsa_return.setFechaRegistro(ars_rs.getTimestamp("FECHA_REGISTRO"));
		lsa_return.setDescripcion(ars_rs.getString("DESCRIPCION"));
		lsa_return.setRadicacion(ars_rs.getString("RADICACION"));

		fillAuditoria(ars_rs, lsa_return);

		return lsa_return;
	}
}
