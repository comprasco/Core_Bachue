package com.bachue.snr.prosnr01.dao.pgn;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.b2bsg.common.exception.B2BException;
import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;
import com.bachue.snr.prosnr01.model.numeracion.NumeracionResolucionCirculo;
import com.bachue.snr.prosnr01.model.sdb.pgn.LibroTestamento;


/**
 * Clase que contiene todos las consultas de la tabla SDB_PGN_LIBRO_TESTAMENTO
 *
 * @author dbeltran
 */
public class NumeracionResolucionCirculoDAO extends AuditoriaDao
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_PGN_NUMERACION_RESOLUCION_CIRCULO WHERE ID_CIRCULO = ? AND ANIO_VIGENTE = ?";

	/** Constante cs_UPDATE*/
	private static final String cs_UPDATE = "UPDATE SDB_PGN_NUMERACION_RESOLUCION_CIRCULO SET CONSECUTIVO_RESOLUCION = ?, ACTIVO = ?, ID_USUARIO_MODIFICACION = ? , FECHA_MODIFICACION = SYSTIMESTAMP, IP_MODIFICACION = ? WHERE ID_CIRCULO = ? AND ANIO_VIGENTE = ? ";

	/** */
	private static final String cs_INSERT = "INSERT INTO SDB_PGN_NUMERACION_RESOLUCION_CIRCULO (ID_CIRCULO, ANIO_VIGENTE, CONSECUTIVO_RESOLUCION, ACTIVO, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION) VALUES (?,?,?,?,?,SYSTIMESTAMP,?)";

	/**
	 * Metodo para encontrar todos los registros existentes en la tabla SDB_PGN_NUMERACION_RESOLUCION_CIRCULO para un numeracion resolcion.
	 *
	 * @param alt_param Objeto de tipo String que contiene parametros a utilizar como filtro en la busqueda.
	 * @return devuelve el valor del objeto Numeracion Resolucio nCirculo
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see LibroTestamento
	 */
	public NumeracionResolucionCirculo findById(NumeracionResolucionCirculo alt_param)
	    throws B2BException
	{
		NumeracionResolucionCirculo ltd_return;
		ltd_return = null;

		if(alt_param != null)
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

				lps_ps.setString(li_column++, alt_param.getIdCirculo());
				lps_ps.setString(li_column++, alt_param.getAnoVigente());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ltd_return = getObjetFromResultSet(lrs_rs);
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

		return ltd_return;
	}

	/**
	 * Metodo para actualizar un registro en la base de datos de la tabla SDB_PGN_NUMERACION_RESOLUCION_CIRCULO
	 */
	public void insert(NumeracionResolucionCirculo anrc_param)
	    throws B2BException
	{
		if(anrc_param != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;

				lps_ps = getConnection().prepareStatement(cs_INSERT);

				lps_ps.setString(li_column++, anrc_param.getIdCirculo());
				lps_ps.setString(li_column++, anrc_param.getAnoVigente());
				setLong(lps_ps, anrc_param.getConsecutivoResolucion(), li_column++);
				lps_ps.setString(li_column++, anrc_param.getActivo());
				lps_ps.setString(li_column++, anrc_param.getIdUsuarioCreacion());
				lps_ps.setString(li_column++, anrc_param.getIpCreacion());

				lps_ps.executeUpdate();
			}

			catch(SQLException lse_e)
			{
				logError(this, "update", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}

			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Metodo para actualizar un registro en la base de datos de la tabla SDB_PGN_NUMERACION_RESOLUCION_CIRCULO
	 */
	public void update(NumeracionResolucionCirculo anrc_param)
	    throws B2BException
	{
		if(anrc_param != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;

				lps_ps = getConnection().prepareStatement(cs_UPDATE);

				setLong(lps_ps, anrc_param.getConsecutivoResolucion(), li_column++);
				lps_ps.setString(li_column++, anrc_param.getActivo());
				lps_ps.setString(li_column++, anrc_param.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, anrc_param.getIpModificacion());
				lps_ps.setString(li_column++, anrc_param.getIdCirculo());
				lps_ps.setString(li_column++, anrc_param.getAnoVigente());

				lps_ps.executeUpdate();
			}

			catch(SQLException lse_e)
			{
				logError(this, "update", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}

			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Metodo para la obtencion del objeto Tipo Documental a partir de un resulset.
	 *
	 * @param lrs_rs objeto contenedor de los resultados de la consulta
	 * @return Objeto de tipo LibroTestamento intanciado con la informacion recuperada de base de datos
	 * @throws SQLException Señala que se ha producido una excepción
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private NumeracionResolucionCirculo getObjetFromResultSet(ResultSet lrs_rs)
	    throws SQLException, B2BException
	{
		NumeracionResolucionCirculo lnrc_numeroResolucionCirculo;

		lnrc_numeroResolucionCirculo = new NumeracionResolucionCirculo();

		lnrc_numeroResolucionCirculo.setConsecutivoResolucion(getLong(lrs_rs, "CONSECUTIVO_RESOLUCION"));
		lnrc_numeroResolucionCirculo.setActivo(lrs_rs.getString("ACTIVO"));
		lnrc_numeroResolucionCirculo.setAnoVigente(lrs_rs.getString("ANIO_VIGENTE"));
		lnrc_numeroResolucionCirculo.setIdCirculo(lrs_rs.getString("ID_CIRCULO"));

		fillAuditoria(lrs_rs, lnrc_numeroResolucionCirculo);

		return lnrc_numeroResolucionCirculo;
	}
}
