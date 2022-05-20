package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.model.calificacion.RegistroCalificacion;
import com.bachue.snr.prosnr01.model.sdb.acc.AnotacionCancelacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase para poder realizar transacciones con la base de datos en la tabla SDB_ACC_ANOTACION_CANCELACION.
 *
 * @author Julian Vaca
 */
public class AnotacionCancelacionDAO extends BaseDAO
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_ACC_ANOTACION_CANCELACION "
		+ "WHERE ID_ANOTACION_CANCELACION = ? AND ID_TURNO_HISTORIA = ?";

	/** Constante cs_FIND_BY_PARAMETERS. */
	private static final String cs_FIND_BY_PARAMETERS = "SELECT * FROM SDB_ACC_ANOTACION_CANCELACION WHERE ";

	/** Constante cs_FIND_BY_PARAMETERSBYANOTACION. */
	private static final String cs_FIND_BY_PARAMETERSBYANOTACION = "SELECT * FROM SDB_ACC_ANOTACION_CANCELACION WHERE ID_ANOTACION = ? AND ID_SOLICITUD = ? AND  ID_CIRCULO = ? AND ID_MATRICULA = ?  ";

	/** Constante cs_FIND_SECUENCE. */
	private static final String cs_FIND_SECUENCE = "SELECT SEC_ACC_ANOTACION_CANCELACION_ID_ANOTACION_CANCELACION.NEXTVAL FROM DUAL";

	/** Constante cs_FIND_BY_CIRCULO_MATRICULA_TURNO. */
	private static final String cs_FIND_BY_CIRCULO_MATRICULA_TURNO = "SELECT * FROM SDB_ACC_ANOTACION_CANCELACION "
		+ "WHERE ID_ANOTACION = ? AND  ID_CIRCULO = ? AND ID_MATRICULA = ? AND ID_TURNO = ? ORDER BY ID_ANOTACION ASC";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_ACC_ANOTACION_CANCELACION (ID_ANOTACION_CANCELACION,"
		+ "ID_TURNO_HISTORIA,ID_CIRCULO,ID_MATRICULA,ID_ANOTACION,ID_ANOTACION1,DESCRIPCION,ID_USUARIO_CREACION,FECHA_CREACION,"
		+ "IP_CREACION,ID_TURNO,ID_SOLICITUD) VALUES (?,?,?,?,?,?,?,?,SYSTIMESTAMP,?,?,?)";

	/** Constante cs_FIND_ANOTACIONES_CANCELACION. */
	private static final String cs_FIND_ANOTACIONES_CANCELACION = " SELECT DISTINCT  BAP.ID_ANOTACION, BAP.ID_DOCUMENTO,BAP.ID_CIRCULO ID_CIRCULO_PREDIO ,BAP.ID_MATRICULA ID_MATRICULA_PREDIO,BAP.ID_ESTADO_ANOTACION,"
		+ "  BAP.FECHA_REGISTRO ,BAP.RADICACION,PEA.NOMBRE ESTADO_ANOTACION ,TDP.NOMBRE NOMBRE_DOCUMENTO, TO_CHAR(SBD.FECHA_DOCUMENTO, 'DD/MM/YYYY')FECHA_DOCUMENTO, NVL(POO.NOMBRE,' ') OFICINA_ORIGEN ,"
		+ "  NVL(PBJ.ID_NATURALEZA_JURIDICA,' ') CODIGO_ACTO,NVL(PBJ.NOMBRE,' ') DESCRIPCION_ACTO  FROM  SDB_ACC_ANOTACION_CANCELACION  AAC  INNER JOIN SDB_BNG_ANOTACION_PREDIO BAP "
		+ "  ON  BAP.ID_CIRCULO = AAC.ID_CIRCULO  AND BAP.ID_MATRICULA = AAC.ID_MATRICULA  AND NVL(BAP.ANOTACION_CANCELADA,'N') = 'N' INNER JOIN SDB_PNG_NATURALEZA_JURIDICA PBJ ON PBJ.ID_NATURALEZA_JURIDICA  = BAP.ID_NATURALEZA_JURIDICA"
		+ "	 INNER JOIN SDB_BGN_DOCUMENTO SBD ON  SBD.ID_DOCUMENTO = BAP.ID_DOCUMENTO  INNER JOIN SDB_PGN_TIPO_DOCUMENTO_PUBLICO TDP ON  TDP.ID_TIPO_DOCUMENTO = SBD.ID_TIPO_DOCUMENTO"
		+ "  INNER JOIN SDB_PNG_ESTADO_ANOTACION PEA ON  PEA.ID_ESTADO_ANOTACION = BAP.ID_ESTADO_ANOTACION  LEFT JOIN SDB_PGN_TIPO_OFICINA PTO ON PTO.ID_TIPO_OFICINA = SBD.ID_TIPO_OFICINA"
		+ "  LEFT JOIN SDB_PGN_OFICINA_ORIGEN POO ON POO.ID_OFICINA_ORIGEN = SBD.ID_OFICINA_ORIGEN WHERE  AAC.ID_SOLICITUD = ?  AND AAC.ID_CIRCULO = ?  AND AAC.ID_MATRICULA = ? ORDER BY BAP.ID_ANOTACION ASC";

	/** Constante cs_DELETE_BY_CIRCULO_MATRICULA. */
	private static final String cs_DELETE_BY_CIRCULO_MATRICULA = "  DELETE  FROM SDB_ACC_ANOTACION_CANCELACION WHERE ID_SOLICITUD = ? AND ID_CIRCULO =?  AND ID_MATRICULA = ?";

	/** Constante cs_DELETE_BY_CIR_MAT_ANOT_TURNO. */
	private static final String cs_DELETE_BY_CIR_MAT_ANOT_TURNO = "DELETE  FROM SDB_ACC_ANOTACION_CANCELACION WHERE ";

	/** Constante cs_FIND_BY_CIR_MAT_ANOT_TURNO. */
	private static final String cs_FIND_BY_CIR_MAT_ANOT_TURNO = "SELECT * FROM SDB_ACC_ANOTACION_CANCELACION WHERE ID_CIRCULO =?  AND ID_MATRICULA = ? AND ID_ANOTACION = ? AND ID_TURNO = ?";

	/** Constante cs_FIND_BY_CIR_MAT_ANOT_SOL. */
	private static final String cs_FIND_BY_CIR_MAT_ANOT_SOL = "SELECT * FROM SDB_ACC_ANOTACION_CANCELACION WHERE ID_CIRCULO=? AND ID_MATRICULA=? AND ID_ANOTACION1=? AND ID_SOLICITUD=?";

	/** Constante cs_DELETE. */
	private static final String cs_DELETE = "DELETE FROM SDB_ACC_ANOTACION_CANCELACION WHERE ID_ANOTACION_CANCELACION=?";

	/**
	 * Método para encontrar las anotacionesCancelacion por el idSolicitud, idCirculo, y idMatricula.
	 *
	 * @param arc_rc anotacionesCancelacion con la información para filtrar en la BD
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Collection
	 */
	public Collection<RegistroCalificacion> anotacionesCancelacion(RegistroCalificacion arc_rc)
	    throws B2BException
	{
		Collection<RegistroCalificacion> lcrc_data;
		PreparedStatement                lps_ps;
		ResultSet                        lrs_rs;
		int                              li_column;

		li_column     = 1;
		lcrc_data     = new ArrayList<RegistroCalificacion>();
		lps_ps        = null;
		lrs_rs        = null;

		if(arc_rc != null)
		{
			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_ANOTACIONES_CANCELACION);

				lps_ps.setString(li_column++, arc_rc.getIdSolicitud());
				lps_ps.setString(li_column++, arc_rc.getIdCirculo());
				setLong(lps_ps, arc_rc.getIdMatricula(), li_column++);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcrc_data.add(getAnotacionACancelar(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "anotacionesCancelacion", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lcrc_data.isEmpty())
			lcrc_data = null;

		return lcrc_data;
	}

	/**
	 * Método para eliminar las anotacionesCancelacion por el idEspecifico.
	 *
	 * @param aac_parametros objeto anotacion para eliminar su registro
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void delete(AnotacionCancelacion aac_parametros)
	    throws B2BException
	{
		if(aac_parametros != null)
		{
			PreparedStatement lps_ps;

			lps_ps = null;

			try
			{
				Connection lc_connection;
				int        li_column;

				lc_connection     = getConnection();
				li_column         = 1;

				lps_ps = lc_connection.prepareStatement(cs_DELETE);

				lps_ps.setString(li_column++, aac_parametros.getIdAnotacionCancelacion());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				getLog().error("delete", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Método para eliminar AnotacionCancelacion por un filtro especifico.
	 *
	 * @param aac_param objeto AnotacionCancelacion con información para filtrar y eliminar el registro de la BD
	 * @param ab_b booleano para saber que agregar a la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void deleteByCirculoMatriculaAnotacionTruno(AnotacionCancelacion aac_param, boolean ab_b)
	    throws B2BException
	{
		PreparedStatement lps_ps;
		int               li_column;
		StringBuilder     lsb_sb;

		li_column     = 1;
		lps_ps        = null;
		lsb_sb        = new StringBuilder(cs_DELETE_BY_CIR_MAT_ANOT_TURNO);

		if(aac_param != null)
		{
			try
			{
				if(ab_b)
					lsb_sb.append(
					    "  ID_SOLICITUD= ? AND  ID_CIRCULO =?  AND ID_MATRICULA = ? AND ID_ANOTACION = ? AND ID_TURNO = ?"
					);
				else
					lsb_sb.append(" ID_CIRCULO =?  AND ID_MATRICULA = ? AND ID_ANOTACION = ? AND ID_TURNO = ?");

				lps_ps = getConnection().prepareStatement(lsb_sb.toString());

				if(ab_b)
					lps_ps.setString(li_column++, aac_param.getIdSolicitud());

				lps_ps.setString(li_column++, aac_param.getIdCirculo());
				lps_ps.setLong(li_column++, aac_param.getIdMatricula());
				setLong(lps_ps, aac_param.getIdAnotacion(), li_column++);
				lps_ps.setString(li_column++, aac_param.getIdTurno());

				lps_ps.executeQuery();
			}
			catch(SQLException lse_e)
			{
				logError(this, "deleteByCirculoMatriculaAnotacionTruno", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Método para eliminar un registro en la BD por la solicitud, matricula y circulo.
	 *
	 * @param arc_param objeto RegistroCalificacion para filtrar los registros a eliminar
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void deleteByCirculoMatriculaSolicitud(RegistroCalificacion arc_param)
	    throws B2BException
	{
		PreparedStatement lps_ps;
		int               li_column;

		li_column     = 1;

		lps_ps = null;

		if(arc_param != null)
		{
			try
			{
				lps_ps = getConnection().prepareStatement(cs_DELETE_BY_CIRCULO_MATRICULA);

				lps_ps.setString(li_column++, arc_param.getIdSolicitud());
				lps_ps.setString(li_column++, arc_param.getIdCirculo());
				setLong(lps_ps, arc_param.getIdMatricula(), li_column++);

				lps_ps.executeQuery();
			}
			catch(SQLException lse_e)
			{
				logError(this, "deleteByCirculoMatriculaSolicitud", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Método para traer todos los registros por un filtro especifico.
	 *
	 * @param aac_param objeto AnotacionCancelacion con información para filtrar en la BD
	 * @return Coleccion de AnotacionCancelacion
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Collection
	 */
	public Collection<AnotacionCancelacion> findAllByCirculoMatriculaAnotacinoTurno(AnotacionCancelacion aac_param)
	    throws B2BException
	{
		Collection<AnotacionCancelacion> lcac_AnotacionCancelacion;
		PreparedStatement                lps_ps;
		ResultSet                        lrs_rs;

		lcac_AnotacionCancelacion     = new ArrayList<AnotacionCancelacion>();
		lps_ps                        = null;
		lrs_rs                        = null;

		if(aac_param != null)
		{
			try
			{
				int li_contador;
				li_contador     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_CIR_MAT_ANOT_TURNO);

				lps_ps.setString(li_contador++, aac_param.getIdCirculo());
				lps_ps.setLong(li_contador++, aac_param.getIdMatricula());
				lps_ps.setLong(li_contador++, NumericUtils.getLong(aac_param.getIdAnotacion()));
				lps_ps.setString(li_contador++, aac_param.getIdTurno());

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcac_AnotacionCancelacion.add(getObjetFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findAllByCirculoMatriculaAnotacinoTurno", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lcac_AnotacionCancelacion.isEmpty())
			lcac_AnotacionCancelacion = null;

		return lcac_AnotacionCancelacion;
	}

	/**
	 * Método para traer todos los registros por un filtro especifico.
	 *
	 * @param aac_param objeto AnotacionCancelacion con información para filtrar en la BD
	 * @return Coleccion de AnotacionCancelacion
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Collection
	 */
	public Collection<AnotacionCancelacion> findAllByCirculoMatriculaByTurno(AnotacionCancelacion aac_param)
	    throws B2BException
	{
		Collection<AnotacionCancelacion> lcac_datos;
		PreparedStatement                lps_ps;
		ResultSet                        lrs_rs;
		StringBuilder                    lsb_sb;

		lcac_datos     = new ArrayList<AnotacionCancelacion>();
		lps_ps         = null;
		lrs_rs         = null;
		lsb_sb         = new StringBuilder(cs_FIND_BY_PARAMETERS);

		if(aac_param != null)
		{
			try
			{
				int li_contador;
				li_contador = 1;

				lsb_sb.append(" ID_TURNO = ?  AND  ID_CIRCULO = ? AND ID_MATRICULA = ? AND ID_ANOTACION = ? ");

				lps_ps = getConnection().prepareStatement(lsb_sb.toString());

				lps_ps.setString(li_contador++, aac_param.getIdTurno());
				lps_ps.setString(li_contador++, aac_param.getIdCirculo());
				lps_ps.setLong(li_contador++, aac_param.getIdMatricula());
				setLong(lps_ps, aac_param.getIdAnotacion(), li_contador++);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcac_datos.add(getObjetFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findAllByCirculoMatriculaByTurno", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(!CollectionUtils.isValidCollection(lcac_datos))
			lcac_datos = null;

		return lcac_datos;
	}

	/**
	 * Busca todos los registros asociados a una matrícula e id anotación.
	 *
	 * @param al_idAnotacion id de la anotación asociada a la matrícula
	 * @param as_idCirculo id del círculo al cual pertenece la matrícula
	 * @param al_idMatricula id de la matrícula a utilizar como filtro en la consulta
	 * @param as_idTurno correspondiente al valor del tipo de objeto String
	 * @return Colección resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Collection
	 */
	public Collection<AnotacionCancelacion> findByAnotaciones(
	    long al_idAnotacion, String as_idCirculo, long al_idMatricula, String as_idTurno
	)
	    throws B2BException
	{
		Collection<AnotacionCancelacion> lcap_datos;

		lcap_datos = new ArrayList<AnotacionCancelacion>();

		if(
		    (al_idAnotacion > 0L) && StringUtils.isValidString(as_idCirculo) && (al_idMatricula > 0L)
			    && StringUtils.isValidString(as_idTurno)
		)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_contador;

				li_contador     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_CIRCULO_MATRICULA_TURNO);

				lps_ps.setLong(li_contador++, al_idAnotacion);
				lps_ps.setString(li_contador++, as_idCirculo);
				lps_ps.setLong(li_contador++, al_idMatricula);
				lps_ps.setString(li_contador++, as_idTurno);

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
	 * Método para traer todos los registros por un filtro especifico.
	 *
	 * @param aac_param objeto AnotacionCancelacion con información para filtrar en la BD
	 * @return objeto AnotacionCancelacion
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see AnotacionCancelacion
	 */
	public AnotacionCancelacion findByCirMatAnotSol(AnotacionCancelacion aac_param)
	    throws B2BException
	{
		return (aac_param != null)
		? findByCirMatAnotSol(
		    aac_param.getIdCirculo(), aac_param.getIdMatricula(), aac_param.getIdAnotacion1(),
		    aac_param.getIdSolicitud()
		) : null;
	}

	/**
	 * Retorna el valor del objeto de AnotacionCancelacion.
	 *
	 * @param as_idCirculo correspondiente al valor del tipo de objeto String
	 * @param al_idMatricula correspondiente al valor del tipo de objeto long
	 * @param idAnotacion1 correspondiente al valor del tipo de objeto Long
	 * @param as_idSolicitud correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de AnotacionCancelacion
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see AnotacionCancelacion
	 */
	public AnotacionCancelacion findByCirMatAnotSol(
	    String as_idCirculo, long al_idMatricula, Long idAnotacion1, String as_idSolicitud
	)
	    throws B2BException
	{
		AnotacionCancelacion lac_predio;
		PreparedStatement    lps_ps;
		ResultSet            lrs_rs;

		lac_predio     = null;
		lps_ps         = null;
		lrs_rs         = null;

		if(
		    StringUtils.isValidString(as_idCirculo) && StringUtils.isValidString(as_idSolicitud)
			    && (al_idMatricula > 0) && NumericUtils.isValidLong(idAnotacion1)
		)
		{
			try
			{
				int li_contador;

				li_contador     = 1;
				lps_ps          = getConnection().prepareStatement(cs_FIND_BY_CIR_MAT_ANOT_SOL);

				lps_ps.setString(li_contador++, as_idCirculo);
				lps_ps.setLong(li_contador++, al_idMatricula);
				setLong(lps_ps, idAnotacion1, li_contador++);
				lps_ps.setString(li_contador++, as_idSolicitud);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lac_predio = getObjetFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByCirMatAnotSol", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lac_predio;
	}

	/**
	 * Método para traer todos los registros por un filtro especifico.
	 *
	 * @param aac_param objeto AnotacionCancelacion con información para filtrar en la BD
	 * @param ab_b booleana para saber que agregar a la consulta
	 * @return Colección de AnotaciónCancelación
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Collection
	 */
	public Collection<AnotacionCancelacion> findByCirculoMatricula(AnotacionCancelacion aac_param, boolean ab_b)
	    throws B2BException
	{
		Collection<AnotacionCancelacion> lcac_datos;
		PreparedStatement                lps_ps;
		ResultSet                        lrs_rs;
		StringBuilder                     lsb_sb;

		lcac_datos     = new ArrayList<AnotacionCancelacion>();
		lps_ps         = null;
		lrs_rs         = null;
		lsb_sb         = new StringBuilder(cs_FIND_BY_PARAMETERS);

		if(aac_param != null)
		{
			try
			{
				int li_contador;
				li_contador = 1;

				if(aac_param.isSolicitud())
					lsb_sb.append(" ID_SOLICITUD = ? ");

				else
				{
					if(ab_b)
						lsb_sb.append(" ID_SOLICITUD = ? AND  ID_CIRCULO = ? AND ID_MATRICULA = ?  ");

					else
						lsb_sb.append(" ID_TURNO = ? ");
				}

				lsb_sb.append(" ORDER BY ID_ANOTACION ASC");

				lps_ps = getConnection().prepareStatement(lsb_sb.toString());

				if(aac_param.isSolicitud())
					lps_ps.setString(li_contador++, aac_param.getIdSolicitud());

				else
				{
					if(ab_b)
					{
						lps_ps.setString(li_contador++, aac_param.getIdSolicitud());
						lps_ps.setString(li_contador++, aac_param.getIdCirculo());
						lps_ps.setLong(li_contador++, aac_param.getIdMatricula());
					}
					else
						lps_ps.setString(li_contador++, aac_param.getIdTurno());
				}

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcac_datos.add(getObjetFromResultSet(lrs_rs));
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

		if(!CollectionUtils.isValidCollection(lcac_datos))
			lcac_datos = null;

		return lcac_datos;
	}

	/**
	 * Método para Encontrar un registro por un filtro en especifico en la BD.
	 *
	 * @param aac_param objeto AnotacionCancelacion para extraer la información con la cual va a filtrar en la BD
	 * @return objeto AnotacionCancelacion
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see AnotacionCancelacion
	 */
	public AnotacionCancelacion findByCirculoMatriculaAnotacinoTurno(AnotacionCancelacion aac_param)
	    throws B2BException
	{
		AnotacionCancelacion lac_AnotacionCancelacion;
		PreparedStatement    lps_ps;
		ResultSet            lrs_rs;

		lac_AnotacionCancelacion     = null;
		lps_ps                       = null;
		lrs_rs                       = null;

		if(aac_param != null)
		{
			try
			{
				int li_contador;
				li_contador     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_CIR_MAT_ANOT_TURNO);

				lps_ps.setString(li_contador++, aac_param.getIdCirculo());
				lps_ps.setLong(li_contador++, aac_param.getIdMatricula());
				lps_ps.setLong(li_contador++, NumericUtils.getLong(aac_param.getIdAnotacion()));
				lps_ps.setString(li_contador++, aac_param.getIdTurno());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lac_AnotacionCancelacion = getObjetFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByCirculoMatriculaAnotacinoTurno", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lac_AnotacionCancelacion;
	}

	/**
	 * Método para Encontrar un registro por un filtro en especifico en la BD.
	 *
	 * @param aac_param objeto AnotacionCancelacion para extraer la información con la cual va a filtrar en la BD
	 * @return objeto AnotacionCancelacion
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Collection
	 */
	public Collection<AnotacionCancelacion> findByCirculoMatriculaAnotaciones(AnotacionCancelacion aac_param)
	    throws B2BException
	{
		Collection<AnotacionCancelacion> lcac_datos;
		PreparedStatement                lps_ps;
		ResultSet                        lrs_rs;

		lcac_datos     = new ArrayList<AnotacionCancelacion>();
		lps_ps         = null;
		lrs_rs         = null;

		if(aac_param != null)
		{
			try
			{
				int li_contador;
				li_contador     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_PARAMETERSBYANOTACION);

				setLong(lps_ps, aac_param.getIdAnotacion(), li_contador++);
				lps_ps.setString(li_contador++, aac_param.getIdSolicitud());
				lps_ps.setString(li_contador++, aac_param.getIdCirculo());
				lps_ps.setLong(li_contador++, aac_param.getIdMatricula());

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcac_datos.add(getObjetFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByCirculoMatriculaAnotaciones", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(!CollectionUtils.isValidCollection(lcac_datos))
			lcac_datos = null;

		return lcac_datos;
	}

	/**
	 * Método para Encontrar un registro por un filtro en especifico en la BD.
	 *
	 * @param aac_param objeto AnotacionCancelacion para extraer la información con la cual va a filtrar en la BD
	 * @return objeto AnotacionCancelacion
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see AnotacionCancelacion
	 */
	public AnotacionCancelacion findById(AnotacionCancelacion aac_param)
	    throws B2BException
	{
		AnotacionCancelacion lac_predio;
		PreparedStatement    lps_ps;
		ResultSet            lrs_rs;

		lac_predio     = null;
		lps_ps         = null;
		lrs_rs         = null;

		if(aac_param != null)
		{
			try
			{
				int li_contador;
				li_contador     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(li_contador++, aac_param.getIdAnotacionCancelacion());
				lps_ps.setLong(li_contador++, aac_param.getIdTurnoHistoria());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lac_predio = getObjetFromResultSet(lrs_rs);
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

		return lac_predio;
	}

	/**
	 * Método para insertar un registro de AnotacionCancelación en la BD.
	 *
	 * @param aac_parametros objeto AnotacionCancelacion para obtener todos los datos a insertar en la BD
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void insert(AnotacionCancelacion aac_parametros)
	    throws B2BException
	{
		if(aac_parametros != null)
		{
			PreparedStatement lps_ps;
			PreparedStatement lps_secuencia;
			ResultSet         lrs_rs;

			lps_ps            = null;
			lps_secuencia     = null;
			lrs_rs            = null;

			try
			{
				Connection lc_connection;
				int        li_column;

				lc_connection     = getConnection();
				li_column         = 1;

				lps_ps            = lc_connection.prepareStatement(cs_INSERT);
				lps_secuencia     = lc_connection.prepareStatement(cs_FIND_SECUENCE);

				lrs_rs = lps_secuencia.executeQuery();

				if(lrs_rs.next())
					lps_ps.setString(li_column++, StringUtils.getString(lrs_rs.getInt(1)));

				lps_ps.setLong(li_column++, aac_parametros.getIdTurnoHistoria());
				lps_ps.setString(li_column++, aac_parametros.getIdCirculo());
				lps_ps.setLong(li_column++, aac_parametros.getIdMatricula());
				setLong(lps_ps, aac_parametros.getIdAnotacion(), li_column++);
				setLong(lps_ps, aac_parametros.getIdAnotacion1(), li_column++);
				lps_ps.setString(li_column++, aac_parametros.getDescripcion());
				lps_ps.setString(li_column++, aac_parametros.getIdUsuarioCreacion());
				lps_ps.setString(li_column++, aac_parametros.getIpCreacion());
				lps_ps.setString(li_column++, aac_parametros.getIdTurno());
				lps_ps.setString(li_column++, aac_parametros.getIdSolicitud());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				getLog().error("insert", lse_e);

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
	 * Método para extraer los datos de una consulta de la BD, ponerlos en un objeto y retornarlo.
	 *
	 * @param ars_rs objeto Resultset con la información de la consulta a la BD
	 * @return objeto RegistroCalificacion
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private RegistroCalificacion getAnotacionACancelar(ResultSet ars_rs)
	    throws SQLException
	{
		RegistroCalificacion lrc_datos;
		lrc_datos = new RegistroCalificacion();

		lrc_datos.setIdDocumento(ars_rs.getString("ID_DOCUMENTO"));
		lrc_datos.setCodEstadoAnotacion(ars_rs.getString("ID_ESTADO_ANOTACION"));
		lrc_datos.setIdMatricula(getLong(ars_rs, "ID_MATRICULA_PREDIO"));
		lrc_datos.setIdCirculo(ars_rs.getString("ID_CIRCULO_PREDIO"));
		lrc_datos.setIdAnotacion(getLong(ars_rs, "ID_ANOTACION"));
		lrc_datos.setFechaRegistro(ars_rs.getTimestamp("FECHA_REGISTRO"));
		lrc_datos.setRadicacion(ars_rs.getString("RADICACION"));
		lrc_datos.setIdEstadoAnotacion(ars_rs.getString("ESTADO_ANOTACION"));
		lrc_datos.setDocumento(ars_rs.getString("NOMBRE_DOCUMENTO"));
		lrc_datos.setFechaDocumentoStr(ars_rs.getString("FECHA_DOCUMENTO"));
		lrc_datos.setNombreOficinaOrigen(ars_rs.getString("OFICINA_ORIGEN"));
		lrc_datos.setCodActo(ars_rs.getString("CODIGO_ACTO"));
		lrc_datos.setNombreActo(ars_rs.getString("DESCRIPCION_ACTO"));
		lrc_datos.setRevision(false);

		return lrc_datos;
	}

	/**
	 * Método para extraer los datos de una consulta de la BD, ponerlos en un objeto y retornarlo.
	 *
	 * @param ars_rs objeto Resultset con la información de la consulta a la BD
	 * @return objeto AnotacionCancelacion
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private AnotacionCancelacion getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		AnotacionCancelacion lac_ap;

		lac_ap = new AnotacionCancelacion();

		lac_ap.setIdAnotacionCancelacion(ars_rs.getString("ID_ANOTACION_CANCELACION"));
		lac_ap.setIdTurnoHistoria(ars_rs.getLong("ID_TURNO_HISTORIA"));
		lac_ap.setIdCirculo(ars_rs.getString("ID_CIRCULO"));
		lac_ap.setIdMatricula(ars_rs.getLong("ID_MATRICULA"));
		lac_ap.setIdAnotacion(getLong(ars_rs, "ID_ANOTACION"));
		lac_ap.setIdAnotacion1(getLong(ars_rs, "ID_ANOTACION1"));
		lac_ap.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		lac_ap.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		lac_ap.setIdSolicitud(ars_rs.getString("ID_SOLICITUD"));

		return lac_ap;
	}
}
