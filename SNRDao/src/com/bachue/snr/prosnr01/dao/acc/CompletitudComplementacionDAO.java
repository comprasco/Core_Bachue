package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.NumericUtils;

import com.bachue.snr.prosnr01.model.sdb.acc.CompletitudComplementacion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Clase dedicada al manejo de conexion y transacciones en la base de datos que afecten
 * directamente a la tabla SDB_ACC_COMPLETITUD_COMPLEMENTACION.
 *
 * @author Manuel Blanco
 */
public class CompletitudComplementacionDAO extends BaseDAO
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_ACC_COMPLETITUD_COMPLEMENTACION WHERE ID_TURNO = ? "
		+ "AND ID_CIRCULO_REGISTRAL_DESTINO = ? AND ID_TURNO_HISTORIA = ? AND ID_COMPLEMENTACION = ?";

	/** Constante cs_FIND_BY_TURNO_TH. */
	private static final String cs_FIND_BY_TURNO_TH = "SELECT * FROM SDB_ACC_COMPLETITUD_COMPLEMENTACION WHERE ID_TURNO = ? AND ID_TURNO_HISTORIA = ?";

	/** Constante cs_FIND_BY_TURNO. */
	private static final String cs_FIND_BY_TURNO = "SELECT * FROM SDB_ACC_COMPLETITUD_COMPLEMENTACION WHERE ID_TURNO = ?";

	/** Constante cs_FIND_BY_ID_NO_COMPLEMENTACION. */
	private static final String cs_FIND_BY_ID_NO_COMPLEMENTACION = "SELECT * FROM SDB_ACC_COMPLETITUD_COMPLEMENTACION WHERE ID_TURNO = ? "
		+ "AND ID_CIRCULO_REGISTRAL_DESTINO = ? AND ID_TURNO_HISTORIA = ?";

	/** Constante cs_FIND_SECUENCE. */
	private static final String cs_FIND_SECUENCE = "";

	/** Constante cs_INSERT_COMPLEMENTACION. */
	private static final String cs_INSERT_COMPLEMENTACION = "INSERT INTO SDB_ACC_COMPLETITUD_COMPLEMENTACION "
		+ "(ID_TURNO,ID_CIRCULO_REGISTRAL_DESTINO,ID_TURNO_HISTORIA,ID_COMPLEMENTACION,RESPUESTA_BUSQUEDA,ID_USUARIO_CREACION,FECHA_CREACION,"
		+ "IP_CREACION) VALUES (?,?,?,?,?,?,SYSTIMESTAMP,?)";

	/** Constante cs_INSERT_COMPLEMENTACION. */
	private static final String cs_INSERT_COMPLEMENTACION_WITH_JUSTIFICACION = "INSERT INTO SDB_ACC_COMPLETITUD_COMPLEMENTACION "
		+ "(ID_TURNO,ID_CIRCULO_REGISTRAL_DESTINO,ID_TURNO_HISTORIA,ID_COMPLEMENTACION,RESPUESTA_BUSQUEDA,ID_USUARIO_CREACION,FECHA_CREACION,"
		+ "IP_CREACION, JUSTIFICACION) VALUES (?,?,?,?,?,?,SYSTIMESTAMP,?,?)";

	/** Constante cs_UPDATE_COMPLEMENTACION. */
	private static final String cs_UPDATE_COMPLEMENTACION = "UPDATE SDB_ACC_COMPLETITUD_COMPLEMENTACION "
		+ "SET RESPUESTA_BUSQUEDA = ?, OBSERVACIONES_NO_HALLAZGO = ?, INFORMACION_COMPLEMENTACION = ?, ID_USUARIO_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP, IP_MODIFICACION = ?, ID_COMPLEMENTACION = ? "
		+ "WHERE ID_TURNO = ? AND ID_CIRCULO_REGISTRAL_DESTINO = ? AND ID_TURNO_HISTORIA = ?";

	/**
	 * Consulta en la base de datos una completitud complementación que esté relacionada a un id turno,
	 * id círculo registral destino, id turno historia e id complementación.
	 *
	 * @param acc_param Objeto contenedor de los datos a utilizar como filtro en la consulta
	 * @return CompletitudComplementacion resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see CompletitudComplementacion
	 */
	public CompletitudComplementacion findById(CompletitudComplementacion acc_param)
	    throws B2BException
	{
		CompletitudComplementacion lcc_cc;

		lcc_cc = null;

		if(acc_param != null)
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

				lps_ps.setString(li_cont++, acc_param.getIdTurno());
				lps_ps.setString(li_cont++, acc_param.getIdCirculoRegistralDestino());
				setLong(lps_ps, acc_param.getIdTurnoHistoria(), li_cont++);
				setLong(lps_ps, acc_param.getIdComplementacion(), li_cont++);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lcc_cc = getObjetFromResultSet(lrs_rs);
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

		return lcc_cc;
	}

	/**
	 * Consulta en la base de datos una completitud complementación que esté relacionada a un id turno,
	 * id círculo registral destino e id turno historia.
	 *
	 * @param acc_param Objeto contenedor de los datos a utilizar como filtro en la consulta
	 * @return CompletitudComplementacion resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see CompletitudComplementacion
	 */
	public CompletitudComplementacion findByIdNoComplementacion(CompletitudComplementacion acc_param)
	    throws B2BException
	{
		CompletitudComplementacion lcc_object;

		lcc_object = null;

		if(acc_param != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_NO_COMPLEMENTACION);

				int li_cont;

				li_cont = 1;

				lps_ps.setString(li_cont++, acc_param.getIdTurno());
				lps_ps.setString(li_cont++, acc_param.getIdCirculoRegistralDestino());
				setLong(lps_ps, acc_param.getIdTurnoHistoria(), li_cont++);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lcc_object = getObjetFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdNoComplementacion", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lcc_object;
	}

	/**
	 * Consulta en la base de datos una completitud complementación que esté relacionada a un id turno.
	 *
	 * @param acc_param Objeto contenedor de los datos a utilizar como filtro en la consulta
	 * @return CompletitudComplementacion resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see CompletitudComplementacion
	 */
	public CompletitudComplementacion findByTurno(CompletitudComplementacion acc_param)
	    throws B2BException
	{
		CompletitudComplementacion lcc_object;

		lcc_object = null;

		if(acc_param != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_TURNO);

				int li_cont;

				li_cont = 1;

				lps_ps.setString(li_cont++, acc_param.getIdTurno());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lcc_object = getObjetFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByTurno", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lcc_object;
	}

	/**
	 * Consulta en la base de datos una completitud complementación que esté relacionada a un id turno
	 * e id turno historia.
	 *
	 * @param acc_param Objeto contenedor de los datos a utilizar como filtro en la consulta
	 * @return CompletitudComplementacion resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see CompletitudComplementacion
	 */
	public CompletitudComplementacion findByTurnoTH(CompletitudComplementacion acc_param)
	    throws B2BException
	{
		CompletitudComplementacion lcc_object;

		lcc_object = null;

		if(acc_param != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_TURNO_TH);

				int li_cont;

				li_cont = 1;

				lps_ps.setString(li_cont++, acc_param.getIdTurno());
				setLong(lps_ps, acc_param.getIdTurnoHistoria(), li_cont++);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lcc_object = getObjetFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByTurnoTH", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lcc_object;
	}

	/**
	 * Encuentra el número de secuencia para asignarlo al id de un registro a insertar.
	 *
	 * @return Número de secuencia encontrado
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see int
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
	 * Inserta un nuevo registro en la tabla.
	 *
	 * @param acc_cc Objeto contenedor de los datos a insertar en el nuevo registro
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void insert(CompletitudComplementacion acc_cc)
	    throws B2BException
	{
		if(acc_cc != null)
		{
			int               li_cont;
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			li_cont     = 1;
			lps_ps      = null;
			lrs_rs      = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_INSERT_COMPLEMENTACION);

				lps_ps.setString(li_cont++, acc_cc.getIdTurno());
				lps_ps.setString(li_cont++, acc_cc.getIdCirculoRegistralDestino());
				setLong(lps_ps, acc_cc.getIdTurnoHistoria(), li_cont++);
				setLong(lps_ps, acc_cc.getIdComplementacion(), li_cont++);
				lps_ps.setString(li_cont++, acc_cc.getRespuestaBusqueda());
				lps_ps.setString(li_cont++, acc_cc.getIdUsuarioCreacion());
				lps_ps.setString(li_cont++, acc_cc.getIpCreacion());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "insert", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}
	}

	/**
	 * Inserta un nuevo registro en la tabla.
	 *
	 * @param acc_cc Objeto contenedor de los datos a insertar en el nuevo registro
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void insertWithJustificacion(CompletitudComplementacion acc_cc)
	    throws B2BException
	{
		if(acc_cc != null)
		{
			int               li_cont;
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			li_cont     = 1;
			lps_ps      = null;
			lrs_rs      = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_INSERT_COMPLEMENTACION_WITH_JUSTIFICACION);

				lps_ps.setString(li_cont++, acc_cc.getIdTurno());
				lps_ps.setString(li_cont++, acc_cc.getIdCirculoRegistralDestino());
				setLong(lps_ps, acc_cc.getIdTurnoHistoria(), li_cont++);
				setLong(lps_ps, acc_cc.getIdComplementacion(), li_cont++);
				lps_ps.setString(li_cont++, acc_cc.getRespuestaBusqueda());
				lps_ps.setString(li_cont++, acc_cc.getIdUsuarioCreacion());
				lps_ps.setString(li_cont++, acc_cc.getIpCreacion());
				lps_ps.setString(li_cont++, acc_cc.getJjustificacion());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "insertWithJustificacion", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}
	}

	/**
	 * Actualiza un registro en la tabla por medio de su id turno, id círculo registral destino e id
	 * turno historia.
	 *
	 * @param acc_cc Objeto contenedor de los datos a actualizar en el registro existente
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void update(CompletitudComplementacion acc_cc)
	    throws B2BException
	{
		if(acc_cc != null)
		{
			int               li_cont;
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			li_cont     = 1;
			lps_ps      = null;
			lrs_rs      = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_UPDATE_COMPLEMENTACION);

				lps_ps.setString(li_cont++, acc_cc.getRespuestaBusqueda());
				lps_ps.setString(li_cont++, acc_cc.getObservacionesNoHallazgo());
				lps_ps.setString(li_cont++, acc_cc.getInformacionComplementacion());
				lps_ps.setString(li_cont++, acc_cc.getIdUsuarioModificacion());
				lps_ps.setString(li_cont++, acc_cc.getIpModificacion());
				setLong(lps_ps, acc_cc.getIdComplementacion(), li_cont++);

				lps_ps.setString(li_cont++, acc_cc.getIdTurno());
				lps_ps.setString(li_cont++, acc_cc.getIdCirculoRegistralDestino());
				setLong(lps_ps, acc_cc.getIdTurnoHistoria(), li_cont++);

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "update", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}
	}

	/**
	 * Extrae la información recuperada de la base de datos y la asigna a un objeto CompletitudComplementacion.
	 *
	 * @param ars_rs Objeto contenedor del resultado de la consulta
	 * @return CompletitudComplementacion con la información extraida de la consulta
	 * @throws SQLException Señala que se ha producido una excepción
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private CompletitudComplementacion getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException, B2BException
	{
		CompletitudComplementacion lcc_cc;

		lcc_cc = new CompletitudComplementacion();

		lcc_cc.setIdTurno(ars_rs.getString("ID_TURNO"));
		lcc_cc.setIdCirculoRegistralDestino(ars_rs.getString("ID_CIRCULO_REGISTRAL_DESTINO"));
		lcc_cc.setIdTurnoHistoria(NumericUtils.getLongWrapper(ars_rs.getLong("ID_TURNO_HISTORIA")));
		lcc_cc.setIdComplementacion(NumericUtils.getLongWrapper(ars_rs.getLong("ID_COMPLEMENTACION")));
		lcc_cc.setRespuestaBusqueda(ars_rs.getString("RESPUESTA_BUSQUEDA"));
		lcc_cc.setJustificacion(ars_rs.getString("JUSTIFICACION"));
		lcc_cc.setObservacionesNoHallazgo(ars_rs.getString("OBSERVACIONES_NO_HALLAZGO"));
		lcc_cc.setInformacionComplementacion(ars_rs.getString("INFORMACION_COMPLEMENTACION"));
		lcc_cc.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		lcc_cc.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		lcc_cc.setIpCreacion(ars_rs.getString("IP_CREACION"));
		lcc_cc.setIdUsuarioModificacion(ars_rs.getString("ID_USUARIO_MODIFICACION"));
		lcc_cc.setFechaModificacion(ars_rs.getTimestamp("FECHA_MODIFICACION"));
		lcc_cc.setIpModificacion(ars_rs.getString("IP_MODIFICACION"));

		return lcc_cc;
	}
}
