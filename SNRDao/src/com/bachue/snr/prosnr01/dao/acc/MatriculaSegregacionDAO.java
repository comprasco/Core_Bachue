package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.model.sdb.acc.MatriculaSegregacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las consultas de SDB_ACC_MATRICULA_SEGREGACION
 *
 * @author garias
 */
public class MatriculaSegregacionDAO extends BaseDAO
{
	/** Constante cs_INSERT. */
	private static final String cs_INSERT = " INSERT INTO SDB_ACC_MATRICULA_SEGREGACION (ID_MATRICULA_SEGREGACION,ID_MATRICULA_MATRIZ,ID_CIRCULO_MATRIZ,ID_SOLICITUD,FECHA_CREACION,"
		+ " ID_USUARIO_CREACION,NOMBRE_PREDIO, IP_CREACION,CANTIDAD_CERTIFICADOS,COEFICIENTE,AREA_PRIVADA,AREA_TERRENO,COMPLEMENTO_DIRECCION,AREA_CONSTRUIDA,ID_ANOTACION_APERTURA,ID_TURNO)"
		+ "  VALUES (?,?,?,?,SYSTIMESTAMP,?,?,?,?,?,?,?,?,?,?,?)";

	/** Constante cs_FIND_SECUENCE. */
	private static final String cs_FIND_SECUENCE = "SELECT SEC_ACC_MATRICULA_SEGREGACION_ID_MATRICULA_SEGREGACION.NEXTVAL FROM DUAL";

	/** Constante cs_FIND_BY_ID_SOLICITUD. */
	private static final String cs_FIND_BY_ID_SOLICITUD = "SELECT * FROM SDB_ACC_MATRICULA_SEGREGACION WHERE ID_SOLICITUD = ? ";

	/** Constante cs_FIND_BY_ID_SOLICITUD_WITH_ID_AREA. */
	private static final String cs_FIND_BY_ID_SOLICITUD_WITH_ID_AREA = "SELECT AMS.*,AAP.ID_AREA FROM SDB_ACC_MATRICULA_SEGREGACION AMS "
		+ "INNER JOIN SDB_ACC_AREA_PREDIO AAP ON AMS.ID_MATRICULA_TEMPORAL = AAP.ID_MATRICULA AND AAP.ID_CIRCULO = AMS.ID_CIRCULO_TEMPORAL WHERE AMS.ID_SOLICITUD = ? AND AMS.ID_CIRCULO_MATRIZ = ? AND AMS.ID_MATRICULA_MATRIZ = ? ";

	/** Constante cs_FIND_BY_ID_TURNO_CIRCULO_MATRICULA_ANOTACION. */
	private static final String cs_FIND_BY_ID_TURNO_CIRCULO_MATRICULA = "SELECT * FROM SDB_ACC_MATRICULA_SEGREGACION WHERE ID_TURNO = ? AND ID_CIRCULO_MATRIZ = ? AND ID_MATRICULA_MATRIZ = ? ";

	/** Constante cs_FIND_UNIDAD_MEDIDA_BY_ID_AREA. */
	private static final String cs_FIND_UNIDAD_MEDIDA_BY_ID_AREA = "SELECT * FROM SDB_ACC_DETALLE_AREA_PREDIO WHERE ID_AREA_PREDIO = ?";

	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_ACC_MATRICULA_SEGREGACION WHERE ID_MATRICULA_SEGREGACION = ?";

	/** Constante cs_UPDATE_BY_ID. */
	private static final String cs_UPDATE_ID_PREDIO_REGISTRO_CIRCULO_MATRICULA_BY_ID_SOLICITUD = "UPDATE SDB_ACC_MATRICULA_SEGREGACION SET ID_CIRCULO_TEMPORAL = ?, ID_MATRICULA_TEMPORAL = ?, ID_PREDIO_REGISTRO = ?,"
		+ "FECHA_MODIFICACION = SYSTIMESTAMP, ID_USUARIO_MODIFICACION = ?, IP_MODIFICACION = ? WHERE ID_SOLICITUD = ?";

	/** Constante cs_UPDATE_BY_ID. */
	private static final String cs_UPDATE_BY_ID = "UPDATE SDB_ACC_MATRICULA_SEGREGACION SET ID_MATRICULA_MATRIZ = ?, NOMBRE_PREDIO = ?, CANTIDAD_CERTIFICADOS = ?, "
		+ "FECHA_MODIFICACION = SYSTIMESTAMP, ID_USUARIO_MODIFICACION = ?, IP_MODIFICACION = ?, COEFICIENTE = ?, AREA_PRIVADA = ?, AREA_TERRENO = ?, AREA_CONSTRUIDA = ? WHERE ID_MATRICULA_SEGREGACION = ?";

	/** Constante cs_DELETE. */
	private static final String cs_DELETE = "DELETE FROM SDB_ACC_MATRICULA_SEGREGACION WHERE ID_MATRICULA_SEGREGACION = ?";

	/** Constante cs_DELETE_BY_SOLICITUD. */
	private static final String cs_DELETE_BY_SOLICITUD = "DELETE FROM SDB_ACC_MATRICULA_SEGREGACION WHERE ID_SOLICITUD = ? ";

	/** Constante cs_DELETE_BY_SOLICITUD_CIRCULO_MATRICULA. */
	private static final String cs_DELETE_BY_SOLICITUD_CIRCULO_MATRICULA = "DELETE FROM SDB_ACC_MATRICULA_SEGREGACION WHERE ID_SOLICITUD = ? AND ID_CIRCULO_MATRIZ = ? AND ID_MATRICULA_MATRIZ = ?";

	/**
	 * Delete.
	 *
	 * @param ams_param correspondiente al valor del tipo de objeto MatriculaSegregacion
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void delete(MatriculaSegregacion ams_param)
	    throws B2BException
	{
		if(ams_param != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_DELETE);

				lps_ps.setString(li_column++, ams_param.getMatriculaSegregacion());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "delete", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Elimina una solicitud.
	 *
	 * @param ams_param correspondiente al valor del tipo de objeto MatriculaSegregacion
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void deleteBySolicitud(MatriculaSegregacion ams_param)
	    throws B2BException
	{
		if(ams_param != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_DELETE_BY_SOLICITUD);

				lps_ps.setString(li_column++, ams_param.getIdSolicitud());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "deleteBySolicitud", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Elimina una solicitud circulo matricula.
	 *
	 * @param ams_param correspondiente al valor del tipo de objeto MatriculaSegregacion
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void deleteBySolicitudCirculoMatricula(MatriculaSegregacion ams_param)
	    throws B2BException
	{
		if(ams_param != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_DELETE_BY_SOLICITUD_CIRCULO_MATRICULA);

				lps_ps.setString(li_column++, ams_param.getIdSolicitud());
				lps_ps.setString(li_column++, ams_param.getIdCirculoMatriz());
				setLong(lps_ps, ams_param.getMatriculaMatriz(), li_column++);

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "deleteBySolicitudCirculoMatricula", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Retorna el valor del objeto de MatriculaSegregacion.
	 *
	 * @param ams_param correspondiente al valor del tipo de objeto MatriculaSegregacion
	 * @return devuelve el valor de MatriculaSegregacion
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see MatriculaSegregacion
	 */
	public MatriculaSegregacion findById(MatriculaSegregacion ams_param)
	    throws B2BException
	{
		MatriculaSegregacion lms_object;
		PreparedStatement    lps_ps;
		ResultSet            lrs_rs;

		lms_object     = null;
		lps_ps         = null;
		lrs_rs         = null;

		if(ams_param != null)
		{
			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(1, ams_param.getMatriculaSegregacion());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lms_object = getObjetFromResultSet(lrs_rs);
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

		return lms_object;
	}

	/**
	 * Retorna el valor del objeto de Collection de MatriculaSegregacion
	 *
	 * @param ams_ms correspondiente al valor del tipo de objeto MatriculaSegregacion
	 * @param ab_b correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor de Collection de MatriculaSegregacion
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see MatriculaSegregacion
	 */
	public Collection<MatriculaSegregacion> findByIdSolicitud(MatriculaSegregacion ams_ms, boolean ab_b)
	    throws B2BException
	{
		return (ams_ms != null)
		? findByIdSolicitud(ams_ms.getIdSolicitud(), ams_ms.getIdCirculoMatriz(), ams_ms.getMatriculaMatriz(), ab_b)
		: null;
	}

	/**
	 * Retorna el valor del objeto de Collection de MatriculaSegregacion
	 *
	 * @param as_idSolicitud Corresponde al valor del id solicitud
	 * @param as_idCirculo Corresponde al valor del id circulo
	 * @param al_idMatricula Corresponde al valor del id matricula
	 * @param ab_b correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor de Collection de MatriculaSegregacion
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<MatriculaSegregacion> findByIdSolicitud(
	    String as_idSolicitud, String as_idCirculo, Long al_idMatricula, boolean ab_b
	)
	    throws B2BException
	{
		Collection<MatriculaSegregacion> lcms_matriculasSegregacion;
		PreparedStatement                lps_ps;
		ResultSet                        lrs_rs;
		StringBuilder                    lsb_sb;
		int                              li_column;

		li_column     = 1;

		lcms_matriculasSegregacion     = new ArrayList<MatriculaSegregacion>();
		lps_ps                         = null;
		lrs_rs                         = null;
		lsb_sb                         = new StringBuilder(cs_FIND_BY_ID_SOLICITUD);

		if(StringUtils.isValidString(as_idSolicitud))
		{
			try
			{
				if(!ab_b)
					lsb_sb.append(" AND ID_CIRCULO_MATRIZ = ? AND ID_MATRICULA_MATRIZ = ?");

				lps_ps = getConnection().prepareStatement(lsb_sb.toString());

				lps_ps.setString(li_column++, as_idSolicitud);

				if(!ab_b)
				{
					lps_ps.setString(li_column++, as_idCirculo);
					setLong(lps_ps, al_idMatricula, li_column++);
				}

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcms_matriculasSegregacion.add(getObjetFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdSolicitud", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lcms_matriculasSegregacion.isEmpty())
			lcms_matriculasSegregacion = null;

		return lcms_matriculasSegregacion;
	}

	public Collection<MatriculaSegregacion> findByIdSolicitudWithUnidadMedida(
	    String as_idSolicitud, String as_idCirculo, Long al_idMatricula
	)
	    throws B2BException
	{
		Collection<MatriculaSegregacion> lcms_matriculasSegregacion;
		PreparedStatement                lps_ps;
		ResultSet                        lrs_rs;
		int                              li_column;

		li_column     = 1;

		lcms_matriculasSegregacion     = new ArrayList<MatriculaSegregacion>();
		lps_ps                         = null;
		lrs_rs                         = null;

		if(StringUtils.isValidString(as_idSolicitud))
		{
			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_SOLICITUD_WITH_ID_AREA);

				lps_ps.setString(li_column++, as_idSolicitud);

				lps_ps.setString(li_column++, as_idCirculo);
				setLong(lps_ps, al_idMatricula, li_column++);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
				{
					MatriculaSegregacion lms_matricula;

					lms_matricula = getObjetFromResultSet(lrs_rs);

					if(lms_matricula != null)
					{
						{
							String ls_idArea;

							ls_idArea = lrs_rs.getString("ID_AREA");

							if(StringUtils.isValidString(ls_idArea))
							{
								PreparedStatement lps_psUnidadMedida;
								ResultSet         lrs_rsUnidadMedida;

								lps_psUnidadMedida     = null;
								lrs_rsUnidadMedida     = null;

								try
								{
									lps_psUnidadMedida = getConnection()
											                     .prepareStatement(cs_FIND_UNIDAD_MEDIDA_BY_ID_AREA);

									lps_psUnidadMedida.setString(1, ls_idArea);

									lrs_rsUnidadMedida = lps_psUnidadMedida.executeQuery();

									if(lrs_rsUnidadMedida.next())
										lms_matricula.setUnidad(getLong(lrs_rsUnidadMedida, "ID_UNIDAD_MEDIDA"));
								}
								catch(SQLException lse_e)
								{
									logError(this, "findByIdSolicitudWithUnidadMedida", lse_e);

									throw new B2BException(SQL_ERROR, lse_e);
								}
								finally
								{
									close(lps_psUnidadMedida);
									close(lrs_rsUnidadMedida);
								}
							}
						}

						lcms_matriculasSegregacion.add(lms_matricula);
					}
				}
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdSolicitudWithUnidadMedida", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lcms_matriculasSegregacion.isEmpty())
			lcms_matriculasSegregacion = null;

		return lcms_matriculasSegregacion;
	}

	/**
	 * Retorna el valor del objeto de MatriculaSegregacion.
	 *
	 * @param ams_matriculaSegregacion correspondiente al valor del tipo de objeto MatriculaSegregacion
	 * @return devuelve el valor de MatriculaSegregacion
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see MatriculaSegregacion
	 */
	public MatriculaSegregacion findByIdSolicitudCirculo(MatriculaSegregacion ams_matriculaSegregacion)
	    throws B2BException
	{
		MatriculaSegregacion lms_matriculaSegregacion;
		PreparedStatement    lps_ps;
		ResultSet            lrs_rs;

		lms_matriculaSegregacion     = null;
		lps_ps                       = null;
		lrs_rs                       = null;

		if(ams_matriculaSegregacion != null)
		{
			try
			{
				int           li_count;
				StringBuilder lsb_sb;

				li_count     = 1;
				lsb_sb       = new StringBuilder();

				lsb_sb.append(cs_FIND_BY_ID_SOLICITUD);
				lsb_sb.append("AND ID_CIRCULO_MATRIZ = ? ");

				lps_ps = getConnection().prepareStatement(lsb_sb.toString());

				lps_ps.setString(li_count++, ams_matriculaSegregacion.getIdSolicitud());
				lps_ps.setString(li_count++, ams_matriculaSegregacion.getIdCirculoMatriz());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lms_matriculaSegregacion = getObjetFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdSolicitudCirculo", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lms_matriculaSegregacion;
	}

	/**
	 * Consulta las matrículas segregación para un turno, circulo y matrículas especificos.
	 *
	 * @param as_idTurno Variable que contiene el id del turno.
	 * @param as_idCirculo Variable que contiene el id del circulo.
	 * @param al_idMatricula Variable que contiene el id de la matrícula
	 * @param as_idAnotacionApertura Variable que contiene el id de la anotación.
	 * @return devuelve el valor de Collection de MatriculaSegregacion
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<MatriculaSegregacion> findByIdTurnoCirculoMatricula(
	    String as_idTurno, String as_idCirculo, Long al_idMatricula, String as_idAnotacionApertura
	)
	    throws B2BException
	{
		Collection<MatriculaSegregacion> lcms_matriculasSegregacion;
		PreparedStatement                lps_ps;
		ResultSet                        lrs_rs;

		lcms_matriculasSegregacion     = new ArrayList<MatriculaSegregacion>();
		lps_ps                         = null;
		lrs_rs                         = null;

		if(StringUtils.isValidString(as_idTurno))
		{
			try
			{
				int           li_column;
				StringBuilder lsb_sb;

				li_column     = 1;
				lsb_sb        = new StringBuilder(cs_FIND_BY_ID_TURNO_CIRCULO_MATRICULA);

				if(StringUtils.isValidString(as_idAnotacionApertura))
					lsb_sb.append(" AND ID_ANOTACION_APERTURA = ? ");

				lps_ps = getConnection().prepareStatement(lsb_sb.toString());

				lps_ps.setString(li_column++, as_idTurno);
				lps_ps.setString(li_column++, as_idCirculo);
				setLong(lps_ps, al_idMatricula, li_column++);

				if(StringUtils.isValidString(as_idAnotacionApertura))
					lps_ps.setString(li_column++, as_idAnotacionApertura);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcms_matriculasSegregacion.add(getObjetFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdTurnoCirculoMatricula", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lcms_matriculasSegregacion.isEmpty())
			lcms_matriculasSegregacion = null;

		return lcms_matriculasSegregacion;
	}

	/**
	 * Método que se encarga de consultar un registro con base al id solicitud.
	 *
	 * @param as_arg Variable de tipo String que contiene el id solicitud para realizar la consulta.
	 * @return Objeto que contiene el resultado.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see MatriculaSegregacion
	 */
	public MatriculaSegregacion findBySolicitud(String as_arg)
	    throws B2BException
	{
		MatriculaSegregacion lms_return;
		PreparedStatement    lps_ps;
		ResultSet            lrs_rs;

		lms_return     = null;
		lps_ps         = null;
		lrs_rs         = null;

		if(StringUtils.isValidString(as_arg))
		{
			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_FIND_BY_ID_SOLICITUD);

				lps_ps.setString(li_column++, as_arg);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lms_return = getObjetFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findBySolicitud", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lms_return;
	}

	/**
	 * Inserta un nuevo registro en la tabla
	 *
	 * @param at_param correspondiente al valor del tipo de objeto MatriculaSegregacion
	 * @param as_userId correspondiente al valor del tipo de objeto String
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void insert(MatriculaSegregacion at_param, String as_userId)
	    throws B2BException
	{
		if(at_param != null)
		{
			PreparedStatement lps_ps;
			PreparedStatement lps_secuencia;
			Connection        lc_connection;
			ResultSet         lrs_rs;

			lc_connection     = getConnection();
			lps_ps            = null;
			lps_secuencia     = null;
			lrs_rs            = null;

			try
			{
				int li_column;

				li_column         = 1;
				lps_ps            = lc_connection.prepareStatement(cs_INSERT);
				lps_secuencia     = lc_connection.prepareStatement(cs_FIND_SECUENCE);

				lrs_rs = lps_secuencia.executeQuery();

				if(lrs_rs.next())
					lps_ps.setString(li_column++, StringUtils.getString(lrs_rs.getInt(1)));

				setLong(lps_ps, at_param.getMatriculaMatriz(), li_column++);
				lps_ps.setString(li_column++, at_param.getIdCirculoMatriz());
				lps_ps.setString(li_column++, at_param.getIdSolicitud());
				lps_ps.setString(li_column++, as_userId);
				lps_ps.setString(li_column++, at_param.getNombrePredio());
				lps_ps.setString(li_column++, at_param.getIpCreacion());
				setLong(lps_ps, at_param.getCantidadCertificados(), li_column++);
				setDouble(lps_ps, at_param.getCoeficiente(), li_column++);
				setDouble(lps_ps, at_param.getAreaPrivada(), li_column++);
				setDouble(lps_ps, at_param.getAreaTerreno(), li_column++);
				lps_ps.setString(li_column++, at_param.getDireccion());
				setDouble(lps_ps, at_param.getAreaConstruida(), li_column++);
				lps_ps.setString(li_column++, at_param.getIdAnotacionApertura());
				lps_ps.setString(li_column++, at_param.getIdTurno());

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
				close(lps_secuencia);
				close(lrs_rs);
			}
		}
	}

	/**
	 * Actualiza el registro en la tabla por id.
	 *
	 * @param ams_matriculasSegregacion correspondiente al valor del tipo de objeto MatriculaSegregacion
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void updateById(MatriculaSegregacion ams_matriculasSegregacion)
	    throws B2BException
	{
		PreparedStatement lps_ps;

		lps_ps = null;

		try
		{
			if(ams_matriculasSegregacion != null)
			{
				int li_column;

				li_column     = 1;

				lps_ps = getConnection().prepareStatement(cs_UPDATE_BY_ID);

				setLong(lps_ps, ams_matriculasSegregacion.getMatriculaMatriz(), li_column++);
				lps_ps.setString(li_column++, ams_matriculasSegregacion.getNombrePredio());
				setLong(lps_ps, ams_matriculasSegregacion.getCantidadCertificados(), li_column++);
				lps_ps.setString(li_column++, ams_matriculasSegregacion.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, ams_matriculasSegregacion.getIpModificacion());
				setDouble(lps_ps, ams_matriculasSegregacion.getCoeficiente(), li_column++);
				setDouble(lps_ps, ams_matriculasSegregacion.getAreaPrivada(), li_column++);
				setDouble(lps_ps, ams_matriculasSegregacion.getAreaTerreno(), li_column++);
				setDouble(lps_ps, ams_matriculasSegregacion.getAreaConstruida(), li_column++);
				lps_ps.setString(li_column++, ams_matriculasSegregacion.getMatriculaSegregacion());

				lps_ps.executeUpdate();
			}
		}
		catch(SQLException lse_e)
		{
			logError(this, "UpdateById", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lps_ps);
		}
	}

	/**
	 * Actualiza el registro en la tabla por id.
	 *
	 * @param ams_matriculasSegregacion correspondiente al valor del tipo de objeto MatriculaSegregacion
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void updateIdPredioRegistroIdCirculoIdMatriculaByIdSolicitud(MatriculaSegregacion ams_matriculasSegregacion)
	    throws B2BException
	{
		PreparedStatement lps_ps;

		lps_ps = null;

		try
		{
			if(ams_matriculasSegregacion != null)
			{
				int li_column;

				li_column     = 1;

				lps_ps = getConnection().prepareStatement(
					    cs_UPDATE_ID_PREDIO_REGISTRO_CIRCULO_MATRICULA_BY_ID_SOLICITUD
					);

				lps_ps.setString(li_column++, ams_matriculasSegregacion.getIdCirculoTemporal());
				setLong(lps_ps, ams_matriculasSegregacion.getMatriculaTemporal(), li_column++);
				lps_ps.setString(li_column++, ams_matriculasSegregacion.getIdPredioRegistro());
				lps_ps.setString(li_column++, ams_matriculasSegregacion.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, ams_matriculasSegregacion.getIpModificacion());
				lps_ps.setString(li_column++, ams_matriculasSegregacion.getIdSolicitud());

				lps_ps.executeUpdate();
			}
		}
		catch(SQLException lse_e)
		{
			logError(this, "updateIdPredioRegistroIdCirculoIdMatriculaByIdSolicitud", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lps_ps);
		}
	}

	/**
	 * Retorna el valor de MatriculaSegregacion
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de MatriculaSegregacion
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private MatriculaSegregacion getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		MatriculaSegregacion lms_matriculaSegregacion;

		lms_matriculaSegregacion = new MatriculaSegregacion();

		lms_matriculaSegregacion.setMatriculaSegregacion(ars_rs.getString("ID_MATRICULA_SEGREGACION"));
		lms_matriculaSegregacion.setIdSolicitud(ars_rs.getString("ID_SOLICITUD"));
		lms_matriculaSegregacion.setIdCirculoMatriz(ars_rs.getString("ID_CIRCULO_MATRIZ"));
		lms_matriculaSegregacion.setMatriculaMatriz(getLong(ars_rs, "ID_MATRICULA_MATRIZ"));
		lms_matriculaSegregacion.setNombrePredio(ars_rs.getString("NOMBRE_PREDIO"));
		lms_matriculaSegregacion.setComplementoDireccion(ars_rs.getString("COMPLEMENTO_DIRECCION"));
		lms_matriculaSegregacion.setAreaTerreno(getDouble(ars_rs, "AREA_TERRENO"));
		lms_matriculaSegregacion.setAreaPrivada(getDouble(ars_rs, "AREA_PRIVADA"));
		lms_matriculaSegregacion.setAreaConstruida(getDouble(ars_rs, "AREA_CONSTRUIDA"));
		lms_matriculaSegregacion.setCoeficiente(getDouble(ars_rs, "COEFICIENTE"));
		lms_matriculaSegregacion.setCantidadCertificados(getLong(ars_rs, "CANTIDAD_CERTIFICADOS"));
		lms_matriculaSegregacion.setTurnoCertificado(ars_rs.getString("ID_TURNO_CERTIFICADO"));
		lms_matriculaSegregacion.setIdUser(ars_rs.getString("ID_USUARIO_CREACION"));
		lms_matriculaSegregacion.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		lms_matriculaSegregacion.setIpCreacion(ars_rs.getString("IP_CREACION"));
		lms_matriculaSegregacion.setUsuarioGestion(ars_rs.getString("ID_USUARIO_MODIFICACION"));
		lms_matriculaSegregacion.setFechaModificacion(ars_rs.getTimestamp("FECHA_MODIFICACION"));
		lms_matriculaSegregacion.setIpModificacion(ars_rs.getString("IP_MODIFICACION"));

		return lms_matriculaSegregacion;
	}
}
