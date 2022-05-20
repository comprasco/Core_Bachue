package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.model.antiguoSistema.CausalDevolucion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Collection;
import java.util.LinkedList;


/**
 * Clase dedicada al manejo de conexion y transacciones en la base de datos que afecten
 * directamente a la tabla SDB_ACC_CAUSAL_DEVOLUCION.
 *
 * @author Manuel Blanco
 */
public class CausalDevolucionDAO extends BaseDAO
{
	
	/** Constante cs_FIND_SECUENCE_CAUSAL_DEVOLUCION. */
	private static final String cs_FIND_SECUENCE_CAUSAL_DEVOLUCION = "SELECT SEC_ACC_CAUSAL_DEVOLUCION_ID_CAUSAL_DEVOLUCION.NEXTVAL FROM DUAL";
	
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID                      = "SELECT * FROM SDB_ACC_CAUSAL_DEVOLUCION WHERE ID_CAUSAL_DEVOLUCION = ?";
	
	/** Constante cs_FIND_BY_TURNO_TH. */
	private static final String cs_FIND_BY_TURNO_TH                = "SELECT * FROM SDB_ACC_CAUSAL_DEVOLUCION WHERE ID_TURNO = ? AND ID_TURNO_HISTORIA = ?";
	
	/** Constante cs_SAVE_TIPOS_CAUSALES_DEVOLUCION. */
	private static final String cs_SAVE_TIPOS_CAUSALES_DEVOLUCION  = "INSERT INTO SDB_ACC_CAUSAL_DEVOLUCION (ID_CAUSAL_DEVOLUCION,ID_TURNO,ID_CAUSAL,VERSION,ID_TURNO_HISTORIA,FECHA_REGISTRO,ID_USUARIO_CREACION,FECHA_CREACION,IP_CREACION) VALUES (?,?,?,?,?,SYSTIMESTAMP,?,SYSTIMESTAMP,?)";
	
	/** Constante cs_DELETE_BY_ID. */
	private static final String cs_DELETE_BY_ID                    = "DELETE FROM SDB_ACC_CAUSAL_DEVOLUCION WHERE ID_CAUSAL_DEVOLUCION = ?";

	/**
	 * Elimina los registros de un ID causal devolución específico.
	 *
	 * @param acd_param Objeto contenedor del id a eliminar de la tabla
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void DeleteById(CausalDevolucion acd_param)
	    throws B2BException
	{
		CausalDevolucion lcd_object;

		lcd_object = acd_param;

		if(acd_param != null)
		{
			PreparedStatement lps_ps;

			lps_ps = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_DELETE_BY_ID);

				lps_ps.setString(1, lcd_object.getIdCausalDevolucion());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "DeleteById", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Consulta en la base de datos un causal devolución que tenga un id específico.
	 *
	 * @param acd_param Objeto contenedor del id causal devolución a utilizar como filtro en la consulta
	 * @return Causal devolución con el resultado de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see CausalDevolucion
	 */
	public CausalDevolucion findById(CausalDevolucion acd_param)
	    throws B2BException
	{
		CausalDevolucion lcd_object;

		lcd_object = null;

		if(acd_param != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(1, acd_param.getIdCausalDevolucion());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lcd_object = getObjectFromResultSet(lrs_rs);
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

		return lcd_object;
	}

	/**
	 * Consulta en la base de datos todos los causales devolución que estén relacionados a un id turno
	 * e id turno historia específicos.
	 *
	 * @param acd_param Objeto contenedor de los datos a utilizar como filtro en la consulta
	 * @return Colección de causal devolución resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 * 
	 */
	public Collection<CausalDevolucion> findByTurnoTH(CausalDevolucion acd_param)
	    throws B2BException
	{
		Collection<CausalDevolucion> lccd_causales;

		lccd_causales = new LinkedList<CausalDevolucion>();

		if(acd_param != null)
		{
			CausalDevolucion  lcd_object;
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lcd_object     = null;
			lps_ps         = null;
			lrs_rs         = null;

			try
			{
				int li_cont;
				li_cont     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_TURNO_TH);

				lps_ps.setString(li_cont++, acd_param.getIdTurno());
				lps_ps.setLong(li_cont++, acd_param.getIdTurnoHistoria());

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
				{
					lcd_object = getObjectFromResultSet(lrs_rs);

					if(lcd_object != null)
						lccd_causales.add(lcd_object);
				}
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

		if(lccd_causales.isEmpty())
			lccd_causales = null;

		return lccd_causales;
	}

	/**
	 * Encuentra el número de secuencia para asignarlo al id de un registro a insertar.
	 *
	 * @return Número de secuencia encontrado
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see int
	 */
	public int findSecuenceCausalDevolucion()
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
			lps_ps     = getConnection().prepareStatement(cs_FIND_SECUENCE_CAUSAL_DEVOLUCION);

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				li_secuencia = lrs_rs.getInt(1);
		}
		catch(SQLException lse_e)
		{
			logError(this, "findSecuenceCausalDevolucion", lse_e);

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
	 * @param acd_param Objeto contenedor de los datos a insertar en el nuevo registro
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void insert(CausalDevolucion acd_param)
	    throws B2BException
	{
		if(acd_param != null)
		{
			int               li_cont;
			PreparedStatement lps_ps;

			li_cont     = 1;
			lps_ps      = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_SAVE_TIPOS_CAUSALES_DEVOLUCION);

				lps_ps.setString(li_cont++, acd_param.getIdCausalDevolucion());
				lps_ps.setString(li_cont++, acd_param.getIdTurno());
				lps_ps.setString(li_cont++, acd_param.getIdCausal());
				lps_ps.setLong(li_cont++, acd_param.getVersion());
				lps_ps.setLong(li_cont++, acd_param.getIdTurnoHistoria());
				lps_ps.setString(li_cont++, acd_param.getIdUsuarioCreacion());
				lps_ps.setString(li_cont++, acd_param.getIpCreacion());

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
	 * Extrae la información recuperada de la base de datos y la asigna a un objeto CausalDevolucion.
	 *
	 * @param ars_rs Objeto contenedor del resultado de la consulta
	 * @return CausalDevolucion con la información extraida de la consulta
	 * @throws SQLException Señala que se ha producido una excepción
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private CausalDevolucion getObjectFromResultSet(ResultSet ars_rs)
	    throws SQLException, B2BException
	{
		CausalDevolucion lcd_causalDevolucion;

		lcd_causalDevolucion = new CausalDevolucion();

		lcd_causalDevolucion.setIdCausalDevolucion(ars_rs.getString("ID_CAUSAL_DEVOLUCION"));
		lcd_causalDevolucion.setIdTurno(ars_rs.getString("ID_TURNO"));
		lcd_causalDevolucion.setIdCausal(ars_rs.getString("ID_CAUSAL"));
		lcd_causalDevolucion.setVersion(ars_rs.getLong("ID_CAUSAL"));
		lcd_causalDevolucion.setIdTurnoHistoria(ars_rs.getLong("ID_TURNO_HISTORIA"));
		lcd_causalDevolucion.setFechaRegistro(ars_rs.getTimestamp("FECHA_REGISTRO"));
		lcd_causalDevolucion.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		lcd_causalDevolucion.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		lcd_causalDevolucion.setIpCreacion(ars_rs.getString("IP_CREACION"));
		lcd_causalDevolucion.setIdUsuarioModificacion(ars_rs.getString("ID_USUARIO_MODIFICACION"));
		lcd_causalDevolucion.setFechaModificacion(ars_rs.getTimestamp("FECHA_MODIFICACION"));
		lcd_causalDevolucion.setIpModificacion(ars_rs.getString("IP_MODIFICACION"));

		return lcd_causalDevolucion;
	}
}
