package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.EstadoCommon;

import com.bachue.snr.prosnr01.model.sdb.acc.TurnoDerivado;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las consultas de la tabla SDB_ACC_TURNO_DERIVADO
 *
 * @author garias
 */
public class TurnoDerivadoDAO extends BaseDAO
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT ID_TURNO_PADRE, ID_TURNO_HIJO, ANIO_PADRE, ID_CIRCULO_PADRE, "
		+ "ID_PROCESO_PADRE, ANIO_HIJO, ID_CIRCULO_HIJO, ID_PROCESO_HIJO, INDICADOR_VINCULADO FROM SDB_ACC_TURNO_DERIVADO "
		+ "WHERE ID_TURNO_PADRE=? AND ID_TURNO_HIJO=?";

	/** Constante cs_FIND_BY_ID_HIJO. */
	private static final String cs_FIND_BY_ID_HIJO = "SELECT ID_TURNO_PADRE, ID_TURNO_HIJO, ANIO_PADRE, ID_CIRCULO_PADRE, "
		+ "ID_PROCESO_PADRE, ANIO_HIJO, ID_CIRCULO_HIJO, ID_PROCESO_HIJO, INDICADOR_VINCULADO FROM SDB_ACC_TURNO_DERIVADO "
		+ "WHERE ID_TURNO_HIJO=?";

	/** Constante cs_FIND_BY_ID_HIJO_ID_PROCESO_HIJO. */
	private static final String cs_FIND_BY_ID_PADRE_ID_PROCESO_HIJO = "SELECT ID_TURNO_PADRE, ID_TURNO_HIJO, ANIO_PADRE, ID_CIRCULO_PADRE, "
		+ "ID_PROCESO_PADRE, ANIO_HIJO, ID_CIRCULO_HIJO, ID_PROCESO_HIJO, INDICADOR_VINCULADO FROM SDB_ACC_TURNO_DERIVADO "
		+ "WHERE ID_TURNO_PADRE = ? AND ID_PROCESO_HIJO = ?";

	/** Constante cs_FIND_TURNO_MAYOR_PRORROGA. */
	private static final String cs_FIND_TURNO_MAYOR_PRORROGA = "SELECT * FROM SDB_ACC_TURNO_DERIVADO WHERE ID_TURNO_PADRE = ? AND ID_PROCESO_HIJO = '42'";

	/** Constante cs_FIND_BY_ID_TURNO_PADRE. */
	private static final String cs_FIND_BY_ID_TURNO_PADRE = "SELECT ID_TURNO_PADRE, ID_TURNO_HIJO, ANIO_PADRE, ID_CIRCULO_PADRE, "
		+ "ID_PROCESO_PADRE, ANIO_HIJO, ID_CIRCULO_HIJO, ID_PROCESO_HIJO, NVL(INDICADOR_VINCULADO,'N') INDICADOR_VINCULADO FROM SDB_ACC_TURNO_DERIVADO "
		+ "WHERE ID_TURNO_PADRE=?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_ACC_TURNO_DERIVADO (ID_TURNO_PADRE, ID_TURNO_HIJO, ANIO_PADRE, ID_CIRCULO_PADRE, ID_PROCESO_PADRE, ANIO_HIJO, ID_CIRCULO_HIJO, ID_PROCESO_HIJO, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION, INDICADOR_VINCULADO) VALUES (?,?,?,?,?,?,?,?,?,SYSTIMESTAMP,?,?)";

	/** Constante cs_SELECT_TRAMITE_VINCULADO. */
	private static final String cs_SELECT_TRAMITE_VINCULADO = "SELECT PKG_REGISTRO.FUNC_DESCRIPCION_VINCULADO(ID_TURNO_PADRE) TRAMITE FROM SDB_ACC_TURNO_DERIVADO WHERE INDICADOR_VINCULADO = 'S' AND SDB_ACC_TURNO_DERIVADO.ID_TURNO_PADRE = ?";

	/**
	 * Consulta en la base de datos un registro que coincida con un id específico.
	 *
	 * @param atd_param Objeto contenedor del id a utilizar como filtro en la consulta
	 * @return Objeto resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public TurnoDerivado findById(TurnoDerivado atd_param)
	    throws B2BException
	{
		TurnoDerivado ltd_object;

		ltd_object = null;

		if(atd_param != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_contador;
				li_contador     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(li_contador++, atd_param.getIdTurnoPadre());
				lps_ps.setString(li_contador++, atd_param.getIdTurnoHijo());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ltd_object = getObjetFromResultSet(lrs_rs);
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

		return ltd_object;
	}

	/**
	 * Consulta en la base de datos un registro que coincida con un id específico.
	 *
	 * @param atd_param Objeto contenedor del id a utilizar como filtro en la consulta
	 * @return Objeto resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public TurnoDerivado findByIdHijo(TurnoDerivado atd_param)
	    throws B2BException
	{
		return (atd_param != null) ? findByIdHijo(atd_param.getIdTurnoHijo()) : null;
	}

	/**
	 * Consulta en la base de datos un registro que coincida con un id específico.
	 *
	 * @param as_idTurnoHijo Objeto contenedor del id a utilizar como filtro en la consulta
	 * @return Objeto resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public TurnoDerivado findByIdHijo(String as_idTurnoHijo)
	    throws B2BException
	{
		TurnoDerivado ltd_object;

		ltd_object = null;

		if(as_idTurnoHijo != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_contador;
				li_contador     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_HIJO);

				lps_ps.setString(li_contador++, as_idTurnoHijo);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ltd_object = getObjetFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdHijo", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return ltd_object;
	}

	/**
	 * Consulta en la base de datos todos los registros que coincidan con un id turno hijo y un id proceso hijo específicos.
	 *
	 * @param as_idTurnoPadre String contenedor del id a utilizar como filtro en la consulta
	 * @param ls_idProcesoHijo String contenedor del id a utilizar como filtro en la consulta
	 * @return Colección resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<TurnoDerivado> findByIdPadreIdProcesoHijo(String as_idTurnoPadre, String ls_idProcesoHijo)
	    throws B2BException
	{
		Collection<TurnoDerivado> lctd_datos;

		lctd_datos = new ArrayList<TurnoDerivado>();

		if(StringUtils.isValidString(as_idTurnoPadre) && StringUtils.isValidString(ls_idProcesoHijo))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_count;

				li_count     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_PADRE_ID_PROCESO_HIJO);

				lps_ps.setString(li_count++, as_idTurnoPadre);
				lps_ps.setString(li_count++, ls_idProcesoHijo);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lctd_datos.add(getObjetFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdPadreIdProcesoHijo", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(!CollectionUtils.isValidCollection(lctd_datos))
			lctd_datos = null;

		return lctd_datos;
	}

	/**
	 * Consulta en la base de datos un registro que coincida con un id Turno específico.
	 *
	 * @param as_turno correspondiente al valor del tipo de objeto String
	 * @return Objeto resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public TurnoDerivado findByIdTurnoMayorProrroga(String as_turno)
	    throws B2BException
	{
		TurnoDerivado ltd_object;

		ltd_object = null;

		if(StringUtils.isValidString(as_turno))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_contador;
				li_contador     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_TURNO_MAYOR_PRORROGA);

				lps_ps.setString(li_contador++, as_turno);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ltd_object = getObjetFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdTurnoMayorProrroga", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return ltd_object;
	}

	/**
	 * Consulta en la base de datos todos los registros que coincidan con un id específico.
	 *
	 * @param atd_param Objeto contenedor del id a utilizar como filtro en la consulta
	 * @return Colección resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<TurnoDerivado> findByIdTurnoPadre(TurnoDerivado atd_param)
	    throws B2BException
	{
		return (atd_param != null) ? findByIdTurnoPadre(atd_param.getIdTurnoPadre()) : null;
	}

	/**
	 * Consulta en la base de datos todos los registros que coincidan con un id específico.
	 *
	 * @param as_idTurno Objeto contenedor del id a utilizar como filtro en la consulta
	 * @return Colección resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<TurnoDerivado> findByIdTurnoPadre(String as_idTurno)
	    throws B2BException
	{
		Collection<TurnoDerivado> lctd_datos;

		lctd_datos = new ArrayList<TurnoDerivado>();

		if(as_idTurno != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_TURNO_PADRE);

				lps_ps.setString(1, as_idTurno);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lctd_datos.add(getObjetFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdTurnoPadre", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lctd_datos.isEmpty())
			lctd_datos = null;

		return lctd_datos;
	}

	/**
	 * Consulta en la base de datos todos los registros que coincidan con un id específico.
	 *
	 * @param as_idTurnoPadre Objeto contenedor del id a utilizar como filtro en la consulta
	 * @return Colección resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public TurnoDerivado findByIdTurnoPadreVinculado(String as_idTurnoPadre, boolean ab_derivado)
	    throws B2BException
	{
		TurnoDerivado ltd_return;
		ltd_return = null;

		if(StringUtils.isValidString(as_idTurnoPadre))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int           li_column;
				StringBuilder lsb_sb;

				li_column     = 1;
				lsb_sb        = new StringBuilder(cs_FIND_BY_ID_TURNO_PADRE);

				lsb_sb.append(" AND INDICADOR_VINCULADO = ? ");

				lps_ps = getConnection().prepareStatement(lsb_sb.toString());

				lps_ps.setString(li_column++, as_idTurnoPadre);
				lps_ps.setString(li_column++, ab_derivado ? EstadoCommon.S : EstadoCommon.N);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					ltd_return = getObjetFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdTurnoPadreVinculado", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return ltd_return;
	}

	/**
	 * Consulta en la base de datos todos los registros que coincidan con un id específico y que estén vinculados
	 * a el turno hijo.
	 *
	 * @param atd_param Objeto contenedor del id a utilizar como filtro en la consulta
	 * @return Colección resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<TurnoDerivado> findByIdTurnoPadreVinculados(TurnoDerivado atd_param)
	    throws B2BException
	{
		return (atd_param != null) ? findByIdTurnoPadreVinculados(atd_param.getIdTurnoPadre()) : null;
	}

	/**
	 * Consulta en la base de datos todos los registros que coincidan con un id específico y que estén vinculados
	 * a el turno hijo.
	 *
	 * @param as_idTurnoPadre Objeto contenedor del id a utilizar como filtro en la consulta
	 * @return Colección resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<TurnoDerivado> findByIdTurnoPadreVinculados(String as_idTurnoPadre)
	    throws B2BException
	{
		Collection<TurnoDerivado> lctd_datos;

		lctd_datos = new ArrayList<TurnoDerivado>();

		if(StringUtils.isValidString(as_idTurnoPadre))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				StringBuilder lsb_sb;

				lsb_sb = new StringBuilder();

				lsb_sb.append(cs_FIND_BY_ID_TURNO_PADRE);
				lsb_sb.append(" AND INDICADOR_VINCULADO = 'S' ");

				lps_ps = getConnection().prepareStatement(lsb_sb.toString());

				lps_ps.setString(1, as_idTurnoPadre);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lctd_datos.add(getObjetFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdTurnoPadreVinculados", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(!CollectionUtils.isValidCollection(lctd_datos))
			lctd_datos = null;

		return lctd_datos;
	}

	/**
	 * Retorna el valor del objeto de tramite vinculado.
	 *
	 * @param as_param correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor del objeto string
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public String findTramiteVinculado(String as_param)
	    throws B2BException
	{
		String ls_return;

		ls_return = null;

		if(as_param != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_contador;

				li_contador     = 1;
				lps_ps          = getConnection().prepareStatement(cs_SELECT_TRAMITE_VINCULADO);

				lps_ps.setString(li_contador++, as_param);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ls_return = lrs_rs.getString("TRAMITE");
			}
			catch(SQLException lse_e)
			{
				logError(this, "findTramiteVinculado", lse_e);

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
	 * Inserta un registro en la tabla.
	 *
	 * @param atd_param Objeto contenedor de la información a almacenar en la tabla
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void insert(TurnoDerivado atd_param)
	    throws B2BException
	{
		if(atd_param != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_column;
				li_column     = 1;

				lps_ps = getConnection().prepareStatement(cs_INSERT);

				lps_ps.setString(li_column++, atd_param.getIdTurnoPadre());
				lps_ps.setString(li_column++, atd_param.getIdTurnoHijo());
				lps_ps.setString(li_column++, atd_param.getAnioPadre());
				lps_ps.setString(li_column++, atd_param.getIdCirculoPadre());
				lps_ps.setString(li_column++, atd_param.getIdProcesoPadre());
				lps_ps.setString(li_column++, atd_param.getAnioHijo());
				lps_ps.setString(li_column++, atd_param.getIdCirculoHijo());
				lps_ps.setString(li_column++, atd_param.getIdProcesoHijo());
				lps_ps.setString(li_column++, atd_param.getIdUsuarioCreacion());
				lps_ps.setString(li_column++, atd_param.getIpCreacion());
				lps_ps.setString(li_column++, atd_param.getIndicadorVinculado());

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
				close(lrs_rs);
			}
		}
	}

	/**
	 * Extrae la información obtenida de una consulta.
	 *
	 * @param ars_rs Objeto contenedor del resultado de la consulta
	 * @return TurnoDerivado con el resultado de la consulta
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private TurnoDerivado getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		TurnoDerivado ltd_td;

		ltd_td = new TurnoDerivado();

		ltd_td.setIdTurnoPadre(ars_rs.getString("ID_TURNO_PADRE"));
		ltd_td.setIdTurnoHijo(ars_rs.getString("ID_TURNO_HIJO"));
		ltd_td.setAnioPadre(ars_rs.getString("ANIO_PADRE"));
		ltd_td.setIdCirculoPadre(ars_rs.getString("ID_CIRCULO_PADRE"));
		ltd_td.setIdProcesoPadre(ars_rs.getString("ID_PROCESO_PADRE"));
		ltd_td.setAnioHijo(ars_rs.getString("ANIO_HIJO"));
		ltd_td.setIdCirculoHijo(ars_rs.getString("ID_CIRCULO_HIJO"));
		ltd_td.setIdProcesoHijo(ars_rs.getString("ID_PROCESO_HIJO"));
		ltd_td.setIndVinculado(BooleanUtils.getBooleanValue(ars_rs.getString("INDICADOR_VINCULADO")));

		return ltd_td;
	}
}
