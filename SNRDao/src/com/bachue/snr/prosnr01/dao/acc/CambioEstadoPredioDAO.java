package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.dao.IBase;

import com.bachue.snr.prosnr01.model.sdb.acc.CambioEstadoPredio;

import java.math.BigDecimal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase dedicada al manejo de conexion y transacciones en la base de datos que afecten
 * directamente a la tabla SDB_ACC_CAMBIO_ESTADO_PREDIO.
 *
 * @author Julian Vaca
 */
public class CambioEstadoPredioDAO extends BaseDAO implements IBase<CambioEstadoPredio>
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_ACC_CAMBIO_ESTADO_PREDIO WHERE ID_CIRCULO = ? AND ID_MATRICULA = ? AND ID_TURNO = ?";

	/** Constante cs_FIND_BY_ID_TURNO. */
	private static final String cs_FIND_BY_ID_TURNO = "SELECT * FROM SDB_ACC_CAMBIO_ESTADO_PREDIO WHERE ID_TURNO = ?";

	/** Constante cs_FIND_ALL_ACTIVO. */
	private static final String cs_FIND_ALL_ACTIVO = "SELECT * FROM SDB_ACC_CAMBIO_ESTADO_PREDIO WHERE ACTIVO = 'S' ";

	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_ACC_CAMBIO_ESTADO_PREDIO";

	/** Constante cs_FIND_SECUENCIA. */
	private static final String cs_FIND_SECUENCIA = "SELECT SEC_ACC_CAMBIO_ESTADO_PREDIO_ID_CAMBIO_ESTADO_PREDIO.NEXTVAL FROM DUAL";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_ACC_CAMBIO_ESTADO_PREDIO (ID_CAMBIO_ESTADO_PREDIO, ID_CIRCULO, ID_MATRICULA, ID_TURNO, ESTADO_PREDIO, JUSTIFICACION_CAMBIO, "
		+ "MOTIVO_CAMBIO_ESTADO, ACTIVO, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, SYSTIMESTAMP, ?)";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_ACC_CAMBIO_ESTADO_PREDIO SET ESTADO_PREDIO = ?, JUSTIFICACION_CAMBIO = ?, MOTIVO_CAMBIO_ESTADO = ?, ACTIVO = ?, ID_USUARIO_MODIFICACION = ?, "
		+ "FECHA_MODIFICACION = SYSTIMESTAMP, IP_MODIFICACION = ? WHERE ID_CAMBIO_ESTADO_PREDIO = ? AND ID_CIRCULO = ? AND ID_MATRICULA = ? AND ID_TURNO = ?";

	/**
	 * Consulta en la base de datos todos los registros de la tabla.
	 *
	 * @return Colección de cambio estado predio con los resultados de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public Collection<CambioEstadoPredio> findAll()
	    throws B2BException
	{
		Collection<CambioEstadoPredio> lcep_return;
		PreparedStatement              lps_ps;
		ResultSet                      lrs_rs;

		lcep_return     = new ArrayList<CambioEstadoPredio>();
		lps_ps          = null;
		lrs_rs          = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcep_return.add(getObjetFromResultSet(lrs_rs));
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

		if(lcep_return.isEmpty())
			lcep_return = null;

		return lcep_return;
	}

	/**
	 * Consulta en la base de datos todos los registros de la tabla que se encuentren con estado activo.
	 *
	 * @return Colección de cambio estado predio con los resultados de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public Collection<CambioEstadoPredio> findAllActivo()
	    throws B2BException
	{
		Collection<CambioEstadoPredio> lcep_return;
		PreparedStatement              lps_ps;
		ResultSet                      lrs_rs;

		lcep_return     = new ArrayList<CambioEstadoPredio>();
		lps_ps          = null;
		lrs_rs          = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL_ACTIVO);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcep_return.add(getObjetFromResultSet(lrs_rs));
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

		if(lcep_return.isEmpty())
			lcep_return = null;

		return lcep_return;
	}

	/**
	 * Consulta en la tabla un registro con un id círculo, id matrícula e id turno determinados.
	 *
	 * @param acep_param Objeto contenedor de los datos a utilizar como filtro en la consulta
	 * @return Objeto cambio estado predio resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see CambioEstadoPredio
	 */
	public CambioEstadoPredio findById(CambioEstadoPredio acep_param)
	    throws B2BException
	{
		CambioEstadoPredio lcep_return;
		PreparedStatement  lps_ps;
		ResultSet          lrs_rs;

		lcep_return     = null;
		lps_ps          = null;
		lrs_rs          = null;

		if(acep_param != null)
		{
			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(li_column++, acep_param.getIdCirculo());
				setLong(lps_ps, acep_param.getIdMatricula(), li_column++);
				lps_ps.setString(li_column++, acep_param.getIdTurno());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lcep_return = getObjetFromResultSet(lrs_rs);
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

		return lcep_return;
	}

	/**
	 * Consulta en la base de datos un registro con un id turno específico.
	 *
	 * @param acep_param Objeto contenedor de los datos a utilizar como filtro en la consulta
	 * @return Objeto cambio estado predio resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see CambioEstadoPredio
	 */
	public CambioEstadoPredio findByIdTurno(CambioEstadoPredio acep_param)
	    throws B2BException
	{
		CambioEstadoPredio lcep_return;
		PreparedStatement  lps_ps;
		ResultSet          lrs_rs;

		lcep_return     = null;
		lps_ps          = null;
		lrs_rs          = null;

		if(acep_param != null)
		{
			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_FIND_BY_ID_TURNO);

				lps_ps.setString(li_column++, acep_param.getIdTurno());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lcep_return = getObjetFromResultSet(lrs_rs);
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

		return lcep_return;
	}

	/**
	 * Inserta o actualiza un registro en la tabla.
	 *
	 * @param acep_param Objeto contenedor de la información del registro que se va a insertar o actualizar
	 * @param ab_query Si es true, indica que se va a insertar el registro, false para actualizar
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void insertOrUpdate(CambioEstadoPredio acep_param, boolean ab_query)
	    throws B2BException
	{
		if(acep_param != null)
		{
			PreparedStatement lps_ps;
			PreparedStatement lps_secuencia;
			ResultSet         lrs_rs;

			lps_ps            = null;
			lps_secuencia     = null;
			lrs_rs            = null;

			try
			{
				int        li_column;
				Connection lc_connection;

				li_column         = 1;
				lc_connection     = getConnection();

				{
					String ls_tmp;

					ls_tmp     = ab_query ? cs_INSERT : cs_UPDATE;
					lps_ps     = lc_connection.prepareStatement(ls_tmp);
				}

				if(ab_query)
				{
					lps_secuencia     = lc_connection.prepareStatement(cs_FIND_SECUENCIA);
					lrs_rs            = lps_secuencia.executeQuery();

					if(lrs_rs.next())
					{
						Object lo_o;

						lo_o = lrs_rs.getObject(1);

						if((lo_o != null) && lo_o instanceof BigDecimal)
						{
							acep_param.setIdCambioEstadoPredio(((BigDecimal)lo_o).toString());

							lps_ps.setObject(li_column++, acep_param.getIdCambioEstadoPredio());
							lps_ps.setString(li_column++, acep_param.getIdCirculo());
							setLong(lps_ps, acep_param.getIdMatricula(), li_column++);
							lps_ps.setString(li_column++, acep_param.getIdTurno());
						}
					}
				}

				lps_ps.setString(li_column++, acep_param.getEstadoPredio());
				lps_ps.setString(li_column++, acep_param.getJustificacionCambio());
				lps_ps.setString(li_column++, acep_param.getMotivoCambioEstado());
				lps_ps.setString(li_column++, acep_param.getActivo());

				if(!ab_query)
				{
					lps_ps.setString(li_column++, acep_param.getIdUsuarioModificacion());
					lps_ps.setString(li_column++, acep_param.getIpModificacion());

					lps_ps.setString(li_column++, acep_param.getIdCambioEstadoPredio());
					lps_ps.setString(li_column++, acep_param.getIdCirculo());
					setLong(lps_ps, acep_param.getIdMatricula(), li_column++);
					lps_ps.setString(li_column++, acep_param.getIdTurno());
				}
				else
				{
					lps_ps.setString(li_column++, acep_param.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, acep_param.getIpCreacion());
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
				close(lrs_rs);
				close(lps_secuencia);
				close(lps_ps);
			}
		}
	}

	/**
	 * Extrae la información recuperada de la base de datos y la asigna a un objeto CambioEstadoPredio.
	 *
	 * @param ars_rs Objeto contenedor del resultado de la consulta
	 * @return CambioEstadoPredio con la información extraida de la consulta
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private CambioEstadoPredio getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		CambioEstadoPredio lcep_return;

		lcep_return = new CambioEstadoPredio();

		lcep_return.setIdCambioEstadoPredio(ars_rs.getString("ID_CAMBIO_ESTADO_PREDIO"));
		lcep_return.setIdCirculo(ars_rs.getString("ID_CIRCULO"));
		lcep_return.setIdMatricula(getLong(ars_rs, "ID_MATRICULA"));
		lcep_return.setEstadoPredio(ars_rs.getString("ESTADO_PREDIO"));
		lcep_return.setJustificacionCambio(ars_rs.getString("JUSTIFICACION_CAMBIO"));
		lcep_return.setIdTurno(ars_rs.getString("ID_TURNO"));
		lcep_return.setMotivoCambioEstado(ars_rs.getString("MOTIVO_CAMBIO_ESTADO"));
		lcep_return.setActivo(ars_rs.getString("ACTIVO"));
		lcep_return.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		lcep_return.setFechaCreacion(ars_rs.getDate("FECHA_CREACION"));
		lcep_return.setIpCreacion(ars_rs.getString("IP_CREACION"));
		lcep_return.setIdUsuarioModificacion(ars_rs.getString("ID_USUARIO_MODIFICACION"));
		lcep_return.setFechaModificacion(ars_rs.getDate("FECHA_MODIFICACION"));
		lcep_return.setIpModificacion(ars_rs.getString("IP_MODIFICACION"));

		return lcep_return;
	}
}
