package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.EstadoCommon;

import com.bachue.snr.prosnr01.model.sdb.acc.AccPredioRegistro;
import com.bachue.snr.prosnr01.model.sdb.bng.AreaPredio;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase para poder realizar transacciones con la base de datos en la tabla SDB_ACC_PREDIO_REGISTRO.
 *
 * @author Julian Vaca
 */
public class AccPredioRegistroDAO extends BaseDAO
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT ID_PREDIO_REGISTRO, ID_TURNO_HISTORIA, ID_CIRCULO, ID_MATRICULA, ID_ZONA_REGISTRAL, "
		+ "NVL(NUPRE,'')NUPRE, NUMERO_PREDIAL, NUMERO_PREDIAL_ANT, FOLIO_GRABACION, ID_TIPO_PREDIO, ID_TIPO_USO_SUELO, ID_DOCUMENTO, "
		+ "RADICACION, PREDIO_DEFINITIVO, ID_ESTADO_PREDIO, FECHA_APERTURA, ANOTACION_CIERRE, ID_COMPLEMENTACION, COMENTARIO, ID_DATOS_ANT_SISTEMA, "
		+ "TURNO_BLOQUEO, NVL(PREDIO_INCONSISTENTE,'N')PREDIO_INCONSISTENTE, ID_ESTADO_NUPRE, VERSION_DOCUMENTO, ID_USUARIO_CREACION, "
		+ "FECHA_CREACION, IP_CREACION, ID_USUARIO_MODIFICACION, FECHA_MODIFICACION, IP_MODIFICACION, ID_TURNO, NOMBRE_PREDIO "
		+ "FROM SDB_ACC_PREDIO_REGISTRO WHERE ID_PREDIO_REGISTRO = ? ";

	/** Constante cs_FIND_BY_CIRCULO_MATRICULA. */
	private static final String cs_FIND_BY_CIRCULO_MATRICULA = "SELECT ID_PREDIO_REGISTRO, ID_TURNO_HISTORIA, ID_CIRCULO, ID_MATRICULA, ID_ZONA_REGISTRAL, "
		+ "NVL(NUPRE,'')NUPRE, NUMERO_PREDIAL, NUMERO_PREDIAL_ANT, FOLIO_GRABACION, ID_TIPO_PREDIO, ID_TIPO_USO_SUELO, ID_DOCUMENTO, "
		+ "RADICACION, PREDIO_DEFINITIVO, ID_ESTADO_PREDIO, FECHA_APERTURA, ANOTACION_CIERRE, ID_COMPLEMENTACION, COMENTARIO, ID_DATOS_ANT_SISTEMA, "
		+ "TURNO_BLOQUEO, NVL(PREDIO_INCONSISTENTE,'N')PREDIO_INCONSISTENTE, ID_ESTADO_NUPRE, VERSION_DOCUMENTO, ID_USUARIO_CREACION, "
		+ "FECHA_CREACION, IP_CREACION, ID_USUARIO_MODIFICACION, FECHA_MODIFICACION, IP_MODIFICACION, ID_TURNO, NOMBRE_PREDIO "
		+ "FROM SDB_ACC_PREDIO_REGISTRO WHERE ID_CIRCULO = ? AND ID_MATRICULA = ?";

	/** Constante cs_FIND_BY_ID_TURNO. */
	private static final String cs_FIND_BY_ID_TURNO = "SELECT ID_PREDIO_REGISTRO, ID_TURNO_HISTORIA, ID_CIRCULO, ID_MATRICULA, ID_ZONA_REGISTRAL, "
		+ "NVL(NUPRE,'')NUPRE, NUMERO_PREDIAL, NUMERO_PREDIAL_ANT, FOLIO_GRABACION, ID_TIPO_PREDIO, ID_TIPO_USO_SUELO, ID_DOCUMENTO, "
		+ "RADICACION, PREDIO_DEFINITIVO, ID_ESTADO_PREDIO, FECHA_APERTURA, ANOTACION_CIERRE, ID_COMPLEMENTACION, COMENTARIO, ID_DATOS_ANT_SISTEMA, "
		+ "TURNO_BLOQUEO, NVL(PREDIO_INCONSISTENTE,'N')PREDIO_INCONSISTENTE, ID_ESTADO_NUPRE, VERSION_DOCUMENTO, ID_USUARIO_CREACION, "
		+ "FECHA_CREACION, IP_CREACION, ID_USUARIO_MODIFICACION, FECHA_MODIFICACION, IP_MODIFICACION, ID_TURNO, NOMBRE_PREDIO "
		+ "FROM SDB_ACC_PREDIO_REGISTRO WHERE ID_TURNO = ?";

	/** Constante cs_FIND_BY_ID_TURNO_ANT_SISTEMA. */
	private static final String cs_FIND_BY_ID_TURNO_ANT_SISTEMA = "SELECT ID_PREDIO_REGISTRO, ID_TURNO_HISTORIA, ID_CIRCULO, ID_MATRICULA, ID_ZONA_REGISTRAL, "
		+ "NVL(NUPRE,'')NUPRE, NUMERO_PREDIAL, NUMERO_PREDIAL_ANT, FOLIO_GRABACION, ID_TIPO_PREDIO, ID_TIPO_USO_SUELO, ID_DOCUMENTO, "
		+ "RADICACION, PREDIO_DEFINITIVO, ID_ESTADO_PREDIO, FECHA_APERTURA, ANOTACION_CIERRE, ID_COMPLEMENTACION, COMENTARIO, ID_DATOS_ANT_SISTEMA, "
		+ "TURNO_BLOQUEO, NVL(PREDIO_INCONSISTENTE,'N')PREDIO_INCONSISTENTE, ID_ESTADO_NUPRE, VERSION_DOCUMENTO, ID_USUARIO_CREACION, "
		+ "FECHA_CREACION, IP_CREACION, ID_USUARIO_MODIFICACION, FECHA_MODIFICACION, IP_MODIFICACION, ID_TURNO, NOMBRE_PREDIO "
		+ "FROM SDB_ACC_PREDIO_REGISTRO WHERE ID_TURNO = ? AND ID_DATOS_ANT_SISTEMA = ?";

	/** Constante cs_FIND_BY_ID_TURNO_HISTORIA. */
	private static final String cs_FIND_BY_ID_TURNO_HISTORIA = "SELECT ID_PREDIO_REGISTRO, ID_TURNO_HISTORIA, ID_CIRCULO, ID_MATRICULA, ID_ZONA_REGISTRAL, "
		+ "NVL(NUPRE,'')NUPRE, NUMERO_PREDIAL, NUMERO_PREDIAL_ANT, FOLIO_GRABACION, ID_TIPO_PREDIO, ID_TIPO_USO_SUELO, ID_DOCUMENTO, "
		+ "RADICACION, PREDIO_DEFINITIVO, ID_ESTADO_PREDIO, FECHA_APERTURA, ANOTACION_CIERRE, ID_COMPLEMENTACION, COMENTARIO, ID_DATOS_ANT_SISTEMA, "
		+ "TURNO_BLOQUEO, NVL(PREDIO_INCONSISTENTE,'N')PREDIO_INCONSISTENTE, ID_ESTADO_NUPRE, VERSION_DOCUMENTO, ID_USUARIO_CREACION, "
		+ "FECHA_CREACION, IP_CREACION, ID_USUARIO_MODIFICACION, FECHA_MODIFICACION, IP_MODIFICACION, ID_TURNO, NOMBRE_PREDIO "
		+ "FROM SDB_ACC_PREDIO_REGISTRO WHERE ID_TURNO_HISTORIA = ?";

	/** Constante cs_FIND_BY_ID_TURNO_HISTORIA. */
	private static final String cs_FIND_BY_ID_TURNO_HISTORIA_ORDER_BY_FECHA_CREACION = "SELECT ID_PREDIO_REGISTRO, ID_TURNO_HISTORIA, ID_CIRCULO, ID_MATRICULA, ID_ZONA_REGISTRAL, "
		+ "NVL(NUPRE,'')NUPRE, NUMERO_PREDIAL, NUMERO_PREDIAL_ANT, FOLIO_GRABACION, ID_TIPO_PREDIO, ID_TIPO_USO_SUELO, ID_DOCUMENTO, "
		+ "RADICACION, PREDIO_DEFINITIVO, ID_ESTADO_PREDIO, FECHA_APERTURA, ANOTACION_CIERRE, ID_COMPLEMENTACION, COMENTARIO, ID_DATOS_ANT_SISTEMA, "
		+ "TURNO_BLOQUEO, NVL(PREDIO_INCONSISTENTE,'N')PREDIO_INCONSISTENTE, ID_ESTADO_NUPRE, VERSION_DOCUMENTO, ID_USUARIO_CREACION, "
		+ "FECHA_CREACION, IP_CREACION, ID_USUARIO_MODIFICACION, FECHA_MODIFICACION, IP_MODIFICACION, ID_TURNO, NOMBRE_PREDIO "
		+ "FROM SDB_ACC_PREDIO_REGISTRO WHERE ID_TURNO_HISTORIA = ? ORDER BY FECHA_CREACION DESC";

	/** Constante cs_FIND_SECUENCIA. */
	private static final String cs_FIND_SECUENCIA = "SELECT SEC_ACC_PREDIO_REGISTRO_ID_PREDIO_REGISTRO.NEXTVAL FROM DUAL";

	/** Constante cs_UPDATE_BY_ID. */
	private static final String cs_UPDATE_BY_ID = "UPDATE SDB_ACC_PREDIO_REGISTRO SET ID_CIRCULO = ?, ID_MATRICULA = ?, ID_ZONA_REGISTRAL = ?, "
		+ "NUPRE = ?, NUMERO_PREDIAL = ?, NUMERO_PREDIAL_ANT = ?, FOLIO_GRABACION = ?, ID_TIPO_PREDIO = ?, ID_TIPO_USO_SUELO = ?, ID_DOCUMENTO = ?, "
		+ "RADICACION = ?, PREDIO_DEFINITIVO = ?, ID_ESTADO_PREDIO = ?, FECHA_APERTURA = ?, ANOTACION_CIERRE = ?, ID_COMPLEMENTACION = ?, "
		+ "COMENTARIO = ?, ID_DATOS_ANT_SISTEMA = ?, TURNO_BLOQUEO = ?, PREDIO_INCONSISTENTE = ?, ID_ESTADO_NUPRE = ?, VERSION_DOCUMENTO = ?, "
		+ "ID_USUARIO_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP, ID_TURNO = ?, IP_MODIFICACION = ?, NOMBRE_PREDIO = ? "
		+ "WHERE ID_PREDIO_REGISTRO = ?";

	/** Constante cs_ACTUALIZAR_REVISION. */
	private static final String cs_ACTUALIZAR_REVISION = "UPDATE SDB_ACC_PREDIO_REGISTRO SET  ID_USUARIO_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP,IP_MODIFICACION = ? ";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_ACC_PREDIO_REGISTRO (ID_PREDIO_REGISTRO, ID_TURNO_HISTORIA, ID_CIRCULO, "
		+ "ID_MATRICULA, ID_ZONA_REGISTRAL, NUPRE, NUMERO_PREDIAL, NUMERO_PREDIAL_ANT, FOLIO_GRABACION, ID_TIPO_PREDIO, ID_TIPO_USO_SUELO, "
		+ "ID_DOCUMENTO, RADICACION, PREDIO_DEFINITIVO, ID_ESTADO_PREDIO, FECHA_APERTURA, ANOTACION_CIERRE, ID_COMPLEMENTACION, COMENTARIO, "
		+ "ID_DATOS_ANT_SISTEMA, TURNO_BLOQUEO, PREDIO_INCONSISTENTE, ID_ESTADO_NUPRE, VERSION_DOCUMENTO, ID_USUARIO_CREACION, IP_CREACION, "
		+ "FECHA_CREACION, ID_TURNO, NOMBRE_PREDIO) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,SYSTIMESTAMP,?,?)";

	/** Constante cs_CONSULTA_MATRICULAS_INFORMACION. */
	private static final String cs_CONSULTA_MATRICULAS_INFORMACION = "SELECT NVL(APR.REVISADO_CALIFICADOR,'N')REVISADO_CALIFICADOR ,NVL(APR.REVISADO_DIGITADOR,'N')REVISADO_DIGITADOR, APR.ID_PREDIO_REGISTRO, APR.ID_CIRCULO, APR.ID_MATRICULA, ACP.COEFICIENTE, NVL (ADP.DIRECCION_COMPLETA,'') "
		+ "DIRECCION_COMPLETA, NVL(AMS.NOMBRE_PREDIO,'')NOMBRE_PREDIO, TPS.DESCRIPTION, ADP.ID_DIRECCION_PREDIO, DAP1.AREA AREA_TERRENO, DAP2.AREA AREA_CONSTRUIDA, DAP3.AREA AREA_PRIVADA "
		+ "FROM SDB_ACC_PREDIO_REGISTRO APR INNER JOIN SDB_ACC_AREA_PREDIO ACP ON ACP.ID_CIRCULO = APR.ID_CIRCULO AND ACP.ID_MATRICULA = APR.ID_MATRICULA AND ACP.ID_TURNO_HISTORIA = APR.ID_TURNO_HISTORIA "
		+ "LEFT JOIN SDB_ACC_DIRECCION_PREDIO ADP  ON ADP.ID_DIRECCION_PREDIO = (SELECT MAX(ID_DIRECCION_PREDIO) FROM SDB_ACC_DIRECCION_PREDIO WHERE ID_CIRCULO  = APR.ID_CIRCULO AND ID_MATRICULA = APR.ID_MATRICULA AND ID_TURNO_HISTORIA = APR.ID_TURNO_HISTORIA) "
		+ "LEFT JOIN SDB_COL_TIPO_USO_SUELO TPS ON APR.ID_TIPO_USO_SUELO = TPS.ID_TIPO_USO_SUELO LEFT JOIN SDB_ACC_MATRICULA_SEGREGACION AMS ON AMS.ID_MATRICULA_SEGREGACION = ACP.ID_MATRICULA_SEGREGACION "
		+ "LEFT JOIN SDB_ACC_DETALLE_AREA_PREDIO DAP1 ON DAP1.ID_AREA_PREDIO = ACP.ID_AREA AND DAP1.ID_TIPO_AREA = '1' LEFT JOIN SDB_ACC_DETALLE_AREA_PREDIO DAP2 ON DAP2.ID_AREA_PREDIO = ACP.ID_AREA "
		+ "AND DAP2.ID_TIPO_AREA = '2' LEFT JOIN SDB_ACC_DETALLE_AREA_PREDIO DAP3 ON DAP3.ID_AREA_PREDIO = ACP.ID_AREA AND DAP3.ID_TIPO_AREA = '3' WHERE APR.ID_TURNO_HISTORIA = ?";

	/** Constante cs_CONSULTA_MATRICULAS_INFORMACION_APERTURA. */
	private static final String cs_CONSULTA_MATRICULAS_INFORMACION_APERTURA = "SELECT NVL(APR.REVISADO_CALIFICADOR,'N')REVISADO_CALIFICADOR ,NVL(APR.REVISADO_DIGITADOR,'N')REVISADO_DIGITADOR, APR.ID_PREDIO_REGISTRO, APR.ID_CIRCULO, APR.ID_MATRICULA, ACP.COEFICIENTE, NVL (ADP.DIRECCION_COMPLETA,'') "
		+ "DIRECCION_COMPLETA, NVL(AMS.NOMBRE_PREDIO,'')NOMBRE_PREDIO, TPS.DESCRIPTION, ADP.ID_DIRECCION_PREDIO, DAP1.AREA AREA_TERRENO, DAP2.AREA AREA_CONSTRUIDA, DAP3.AREA AREA_PRIVADA "
		+ "FROM SDB_ACC_PREDIO_REGISTRO APR INNER JOIN SDB_ACC_AREA_PREDIO ACP ON ACP.ID_CIRCULO = APR.ID_CIRCULO AND ACP.ID_MATRICULA = APR.ID_MATRICULA AND ACP.ID_TURNO_HISTORIA = APR.ID_TURNO_HISTORIA "
		+ "LEFT JOIN SDB_ACC_DIRECCION_PREDIO ADP  ON ADP.ID_DIRECCION_PREDIO = (SELECT MAX(ID_DIRECCION_PREDIO) FROM SDB_ACC_DIRECCION_PREDIO WHERE ID_CIRCULO  = APR.ID_CIRCULO AND ID_MATRICULA = APR.ID_MATRICULA AND ID_TURNO_HISTORIA = APR.ID_TURNO_HISTORIA) "
		+ "LEFT JOIN SDB_COL_TIPO_USO_SUELO TPS ON APR.ID_TIPO_USO_SUELO = TPS.ID_TIPO_USO_SUELO LEFT JOIN SDB_ACC_MATRICULA_SEGREGACION AMS ON AMS.ID_MATRICULA_SEGREGACION = ACP.ID_MATRICULA_SEGREGACION "
		+ "LEFT JOIN SDB_ACC_DETALLE_AREA_PREDIO DAP1 ON DAP1.ID_AREA_PREDIO = ACP.ID_AREA AND DAP1.ID_TIPO_AREA = '1' LEFT JOIN SDB_ACC_DETALLE_AREA_PREDIO DAP2 ON DAP2.ID_AREA_PREDIO = ACP.ID_AREA "
		+ "AND DAP2.ID_TIPO_AREA = '2' LEFT JOIN SDB_ACC_DETALLE_AREA_PREDIO DAP3 ON DAP3.ID_AREA_PREDIO = ACP.ID_AREA AND DAP3.ID_TIPO_AREA = '3' WHERE APR.ID_TURNO_HISTORIA = ? AND AMS.ID_CIRCULO_MATRIZ = ? AND AMS.ID_MATRICULA_MATRIZ = ? ";

	/** Constante cs_CONSULTA_MATRICULAS_INF_BY_TURNO. */
	private static final String cs_CONSULTA_MATRICULAS_INF_BY_TURNO = "SELECT NVL(APR.REVISADO_CALIFICADOR,'N')REVISADO_CALIFICADOR ,NVL(APR.REVISADO_DIGITADOR,'N')REVISADO_DIGITADOR,APR.ID_PREDIO_REGISTRO, APR.ID_CIRCULO, APR.ID_MATRICULA, ACP.COEFICIENTE, NVL (ADP.DIRECCION_COMPLETA,'') "
		+ "DIRECCION_COMPLETA, NVL(AMS.NOMBRE_PREDIO,'')NOMBRE_PREDIO, TPS.DESCRIPTION, ADP.ID_DIRECCION_PREDIO, DAP1.AREA AREA_TERRENO, DAP2.AREA AREA_CONSTRUIDA, DAP3.AREA AREA_PRIVADA "
		+ "FROM SDB_ACC_PREDIO_REGISTRO APR INNER JOIN SDB_ACC_AREA_PREDIO ACP ON ACP.ID_CIRCULO = APR.ID_CIRCULO AND ACP.ID_MATRICULA = APR.ID_MATRICULA "
		+ "LEFT JOIN SDB_ACC_DIRECCION_PREDIO ADP ON ADP.ID_DIRECCION_PREDIO    = (SELECT MAX(ID_DIRECCION_PREDIO) FROM SDB_ACC_DIRECCION_PREDIO WHERE ID_CIRCULO  = APR.ID_CIRCULO AND ID_MATRICULA = APR.ID_MATRICULA)"
		+ "LEFT JOIN SDB_COL_TIPO_USO_SUELO TPS ON APR.ID_TIPO_USO_SUELO = TPS.ID_TIPO_USO_SUELO "
		+ "LEFT JOIN SDB_ACC_MATRICULA_SEGREGACION AMS ON AMS.ID_MATRICULA_SEGREGACION = ACP.ID_MATRICULA_SEGREGACION LEFT JOIN SDB_ACC_DETALLE_AREA_PREDIO DAP1 ON DAP1.ID_AREA_PREDIO = ACP.ID_AREA "
		+ "AND DAP1.ID_TIPO_AREA = '1' LEFT JOIN SDB_ACC_DETALLE_AREA_PREDIO DAP2 ON DAP2.ID_AREA_PREDIO = ACP.ID_AREA AND DAP2.ID_TIPO_AREA = '2' LEFT JOIN SDB_ACC_DETALLE_AREA_PREDIO DAP3 "
		+ "ON DAP3.ID_AREA_PREDIO = ACP.ID_AREA AND DAP3.ID_TIPO_AREA = '3' WHERE APR.ID_TURNO = ?";

	/**
	 * Método para actualizar un registro en la base de datos por su ID.
	 *
	 * @param aapr_param objeto AccPredioRegistro con los datos a actualizar
	 * @param ab_b correspondiente al valor del tipo de objeto boolean
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void actualizarRevision(AccPredioRegistro aapr_param, boolean ab_b)
	    throws B2BException
	{
		if(aapr_param != null)
		{
			PreparedStatement lps_ps;
			StringBuilder     lsb_sb;
			int               li_column;

			lps_ps        = null;
			lsb_sb        = new StringBuilder(cs_ACTUALIZAR_REVISION);
			li_column     = 1;

			try
			{
				if(ab_b)
					lsb_sb.append(" ,REVISADO_CALIFICADOR = ? ");
				else
					lsb_sb.append(",REVISADO_DIGITADOR = ?");

				lsb_sb.append(" WHERE ID_PREDIO_REGISTRO = ?");

				lps_ps = getConnection().prepareStatement(lsb_sb.toString());

				lps_ps.setString(li_column++, aapr_param.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, aapr_param.getIpModificacion());
				lps_ps.setString(li_column++, aapr_param.getRevision());

				lps_ps.setString(li_column++, aapr_param.getIdPredioRegistro());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "actualizarRevision", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Método para encontrar todos los predioRegistro por el turno historia.
	 *
	 * @param aapr_accPredioRegistro correspondiente al valor del tipo de objeto AccPredioRegistro
	 * @return objeto AccPredioRegistro
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public Collection<AccPredioRegistro> findAllByTurnoHistoria(AccPredioRegistro aapr_accPredioRegistro)
	    throws B2BException
	{
		return (aapr_accPredioRegistro != null) ? findAllByTurnoHistoria(aapr_accPredioRegistro.getIdTurnoHistoria())
		                                        : null;
	}

	/**
	 * Método para encontrar todos los predioRegistro por el turno historia.
	 *
	 * @param al_idTurnoHistoria Objeto Long para poder filtrar en la BD.
	 * @return objeto AccPredioRegistro
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public Collection<AccPredioRegistro> findAllByTurnoHistoria(Long al_idTurnoHistoria)
	    throws B2BException
	{
		Collection<AccPredioRegistro> lcapr_Predio;
		PreparedStatement             lps_ps;
		ResultSet                     lrs_rs;
		int                           li_column;

		li_column     = 1;

		lcapr_Predio     = new ArrayList<AccPredioRegistro>();
		lps_ps           = null;
		lrs_rs           = null;

		if(al_idTurnoHistoria != null)
		{
			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_TURNO_HISTORIA);

				setLong(lps_ps, al_idTurnoHistoria, li_column++);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcapr_Predio.add(getObjetFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findAllByTurnoHistoria", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(!CollectionUtils.isValidCollection(lcapr_Predio))
			lcapr_Predio = null;

		return lcapr_Predio;
	}

	/**
	 * Find all by turno historia order by fecha creacion.
	 *
	 * @param al_idTurnoHistoria correspondiente al valor de al id turno historia
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<AccPredioRegistro> findAllByTurnoHistoriaOrderByFechaCreacion(Long al_idTurnoHistoria)
	    throws B2BException
	{
		Collection<AccPredioRegistro> lcapr_Predio;
		PreparedStatement             lps_ps;
		ResultSet                     lrs_rs;
		int                           li_column;

		li_column     = 1;

		lcapr_Predio     = new ArrayList<AccPredioRegistro>();
		lps_ps           = null;
		lrs_rs           = null;

		if(al_idTurnoHistoria != null)
		{
			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_TURNO_HISTORIA_ORDER_BY_FECHA_CREACION);

				setLong(lps_ps, al_idTurnoHistoria, li_column++);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcapr_Predio.add(getObjetFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findAllByTurnoHistoria", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(!CollectionUtils.isValidCollection(lcapr_Predio))
			lcapr_Predio = null;

		return lcapr_Predio;
	}

	/**
	 * Método para encontrar todos los predioRegistro por el circulo y matricula.
	 *
	 * @param aapr_param objeto AccPredioRegistro para poder filtrar en la BD
	 * @return objeto AccPredioRegistro
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see AccPredioRegistro
	 */
	public AccPredioRegistro findByCirculoMatricula(AccPredioRegistro aapr_param)
	    throws B2BException
	{
		AccPredioRegistro lapr_predio;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;
		int               li_column;

		li_column     = 1;

		lapr_predio     = null;
		lps_ps          = null;
		lrs_rs          = null;

		if(aapr_param != null)
		{
			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_CIRCULO_MATRICULA);

				lps_ps.setString(li_column++, aapr_param.getIdCirculo());
				setLong(lps_ps, aapr_param.getIdMatricula(), li_column++);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lapr_predio = getObjetFromResultSet(lrs_rs);
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

		return lapr_predio;
	}

	/**
	 * Método para encontrar todos los predioRegistro por el circulo y matricula.
	 *
	 * @param aapr_param objeto AccPredioRegistro para poder filtrar en la BD
	 * @return objeto AccPredioRegistro
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see AccPredioRegistro
	 */
	public AccPredioRegistro findByCirculoMatriculaTurno(AccPredioRegistro aapr_param)
	    throws B2BException
	{
		return (aapr_param != null)
		? findByCirculoMatriculaTurno(aapr_param.getIdCirculo(), aapr_param.getIdMatricula(), aapr_param.getIdTurno())
		: null;
	}

	/**
	 * Método para encontrar todos los predioRegistro por el circulo y matricula.
	 *
	 * @param as_idCirculo id del círculo a utilizar como filtro en la busqueda
	 * @param al_idMatricula id de la matrícula a utilizar como filtro en la busqueda
	 * @param as_idTurno id turno a utilizar como filtro en la busqueda
	 * @return objeto AccPredioRegistro
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see AccPredioRegistro
	 */
	public AccPredioRegistro findByCirculoMatriculaTurno(String as_idCirculo, Long al_idMatricula, String as_idTurno)
	    throws B2BException
	{
		AccPredioRegistro lapr_predio;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lapr_predio     = null;
		lps_ps          = null;
		lrs_rs          = null;

		if(
		    StringUtils.isValidString(as_idCirculo) && NumericUtils.isValidLong(al_idMatricula)
			    && StringUtils.isValidString(as_idTurno)
		)
		{
			try
			{
				int           li_column;
				StringBuilder lsb_sb;

				li_column     = 1;
				lsb_sb        = new StringBuilder();

				lsb_sb.append(cs_FIND_BY_CIRCULO_MATRICULA);
				lsb_sb.append(" AND ID_TURNO = ? ");

				lps_ps = getConnection().prepareStatement(lsb_sb.toString());

				lps_ps.setString(li_column++, as_idCirculo);
				setLong(lps_ps, al_idMatricula, li_column++);
				lps_ps.setString(li_column++, as_idTurno);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lapr_predio = getObjetFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByCirculoMatriculaTurno", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lapr_predio;
	}

	/**
	 * Método para encontrar todos los predioRegistro por el id.
	 *
	 * @param aapr_param objeto AccPredioRegistro para poder filtrar en la BD
	 * @return objeto AccPredioRegistro
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see AccPredioRegistro
	 */
	public AccPredioRegistro findById(AccPredioRegistro aapr_param)
	    throws B2BException
	{
		AccPredioRegistro lapr_predio;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;
		int               li_column;

		li_column     = 1;

		lapr_predio     = null;
		lps_ps          = null;
		lrs_rs          = null;

		if(aapr_param != null)
		{
			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(li_column++, aapr_param.getIdPredioRegistro());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lapr_predio = getObjetFromResultSet(lrs_rs);
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

		return lapr_predio;
	}

	/**
	 * Find by id turno.
	 *
	 * @param aapr_param de aapr param
	 * @param ab_predioDefinitivo de ab predio definitivo
	 * @return el valor de acc predio registro
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public AccPredioRegistro findByIdTurno(AccPredioRegistro aapr_param, boolean ab_predioDefinitivo)
	    throws B2BException
	{
		if(aapr_param != null)
			return findByIdTurno(aapr_param.getIdTurno(), ab_predioDefinitivo);
		else

			return null;
	}

	/**
	 * Método para encontrar todos los predioRegistro por el turno historia.
	 *
	 * @param as_idTurno String para poder filtrar en la BD
	 * @param ab_predioDefinitivo booleana para saber si agregarle un filtro a la consulta o no
	 * @return objeto AccPredioRegistro
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see AccPredioRegistro
	 */
	public AccPredioRegistro findByIdTurno(String as_idTurno, boolean ab_predioDefinitivo)
	    throws B2BException
	{
		AccPredioRegistro lapr_return;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lapr_return     = null;
		lps_ps          = null;
		lrs_rs          = null;

		if(StringUtils.isValidString(as_idTurno))
		{
			try
			{
				int           li_column;
				StringBuilder lsb_sb;

				li_column     = 1;
				lsb_sb        = new StringBuilder();

				lsb_sb.append(cs_FIND_BY_ID_TURNO);

				if(ab_predioDefinitivo)
					lsb_sb.append(" AND PREDIO_DEFINITIVO = 'T' ");

				lps_ps = getConnection().prepareStatement(lsb_sb.toString());

				lps_ps.setString(li_column++, as_idTurno);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lapr_return = getObjetFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdTurno", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lapr_return;
	}

	/**
	 * Método para encontrar todos los predioRegistro por el turno.
	 * Sobrecargado para manejo de booleana.
	 *
	 * @param aapr_param objeto AccPredioRegistro para poder filtrar en la BD
	 * @param ab_predioDefinitivo booleana para saber si agregarle un filtro a la consulta o no
	 * @return Collection of AccPredioRegistro
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public Collection<AccPredioRegistro> findByTurno(AccPredioRegistro aapr_param, boolean ab_predioDefinitivo)
	    throws B2BException
	{
		Collection<AccPredioRegistro> lcapr_return;

		lcapr_return = new ArrayList<AccPredioRegistro>();

		if(aapr_param != null)
			lcapr_return = findByTurno(aapr_param.getIdTurno(), ab_predioDefinitivo);

		return lcapr_return;
	}

	/**
	 * Método para encontrar todos los predioRegistro por el turno.
	 *
	 * @param as_param objeto AccPredioRegistro para poder filtrar en la BD
	 * @param ab_predioDefinitivo booleana para saber si agregarle un filtro a la consulta o no
	 * @return Collection of AccPredioRegistro
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public Collection<AccPredioRegistro> findByTurno(String as_param, boolean ab_predioDefinitivo)
	    throws B2BException
	{
		Collection<AccPredioRegistro> lcapr_predio;
		PreparedStatement             lps_ps;
		ResultSet                     lrs_rs;

		lcapr_predio     = new ArrayList<AccPredioRegistro>();
		lps_ps           = null;
		lrs_rs           = null;

		if(StringUtils.isValidString(as_param))
		{
			try
			{
				int           li_column;
				StringBuilder lsb_sb;

				li_column     = 1;
				lsb_sb        = new StringBuilder();

				lsb_sb.append(cs_FIND_BY_ID_TURNO);

				if(ab_predioDefinitivo)
					lsb_sb.append(" AND PREDIO_DEFINITIVO = 'T' ");

				lps_ps = getConnection().prepareStatement(lsb_sb.toString());

				lps_ps.setString(li_column++, as_param);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcapr_predio.add(getObjetFromResultSet(lrs_rs));
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

		if(!CollectionUtils.isValidCollection(lcapr_predio))
			lcapr_predio = null;

		return lcapr_predio;
	}

	/**
	 * Método para encontrar todos los predioRegistro por el turno e id datos ant sistema.
	 *
	 * @param aapr_param objeto AccPredioRegistro para poder filtrar en la BD
	 * @param ab_predioDefinitivo booleana para saber si agregarle un filtro a la consulta o no
	 * @return Collection of AccPredioRegistro
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public Collection<AccPredioRegistro> findByTurnoAntSistema(
	    AccPredioRegistro aapr_param, boolean ab_predioDefinitivo
	)
	    throws B2BException
	{
		Collection<AccPredioRegistro> lcapr_predio;
		PreparedStatement             lps_ps;
		ResultSet                     lrs_rs;

		lcapr_predio     = new ArrayList<AccPredioRegistro>();
		lps_ps           = null;
		lrs_rs           = null;

		if(aapr_param != null)
		{
			try
			{
				int           li_column;
				StringBuilder lsb_sb;

				li_column     = 1;
				lsb_sb        = new StringBuilder();

				lsb_sb.append(cs_FIND_BY_ID_TURNO_ANT_SISTEMA);

				if(ab_predioDefinitivo)
					lsb_sb.append(" AND PREDIO_DEFINITIVO = 'T' ");

				lps_ps = getConnection().prepareStatement(lsb_sb.toString());

				lps_ps.setString(li_column++, aapr_param.getIdTurno());
				lps_ps.setString(li_column++, aapr_param.getIdDatosAntSistema());

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcapr_predio.add(getObjetFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByTurnoAntSistema", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(!CollectionUtils.isValidCollection(lcapr_predio))
			lcapr_predio = null;

		return lcapr_predio;
	}

	/**
	 * Método para encontrar todos los predioRegistro por el turno historia.
	 *
	 * @param aapr_param objeto AccPredioRegistro para poder filtrar en la BD
	 * @return objeto AccPredioRegistro
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see AccPredioRegistro
	 */
	public AccPredioRegistro findByTurnoHistoria(AccPredioRegistro aapr_param)
	    throws B2BException
	{
		AccPredioRegistro lapr_predio;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;
		int               li_column;

		li_column     = 1;

		lapr_predio     = null;
		lps_ps          = null;
		lrs_rs          = null;

		if(aapr_param != null)
		{
			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_TURNO_HISTORIA);

				setLong(lps_ps, aapr_param.getIdTurnoHistoria(), li_column++);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lapr_predio = getObjetFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByTurnoHistoria", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lapr_predio;
	}

	/**
	 * Método encargado de consultar las matrículas del turno excluyendo a la matrícula actual.
	 *
	 * @param as_idTurno Variable que contiene el id del turno.
	 * @param al_idMatricula Variable que contiene el id de la matrícula.
	 * @return Colección de predios consultados.
	 * @throws B2BException
	 */
	public Collection<AccPredioRegistro> findByTurnoMenosActual(String as_idTurno, Long al_idMatricula)
	    throws B2BException
	{
		Collection<AccPredioRegistro> lcapr_predio;
		PreparedStatement             lps_ps;
		ResultSet                     lrs_rs;

		lcapr_predio     = new ArrayList<AccPredioRegistro>();
		lps_ps           = null;
		lrs_rs           = null;

		if(StringUtils.isValidString(as_idTurno) && NumericUtils.isValidLong(al_idMatricula))
		{
			try
			{
				int li_column;

				li_column     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_TURNO);

				lps_ps.setString(li_column++, as_idTurno);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
				{
					Long ll_idMatricula;

					ll_idMatricula = getLong(lrs_rs, "ID_MATRICULA");

					if(ll_idMatricula.compareTo(al_idMatricula) != 0)
						lcapr_predio.add(getObjetFromResultSet(lrs_rs));
				}
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByTurnoMenosActual", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(!CollectionUtils.isValidCollection(lcapr_predio))
			lcapr_predio = null;

		return lcapr_predio;
	}

	/**
	 * Método para encontrar todos los predioRegistro por el turno.
	 *
	 * @param as_idTurno correspondiente al valor del tipo de objeto String
	 * @return Collection of AreaPredio
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public Collection<AreaPredio> findMatriculasInfByTurno(String as_idTurno)
	    throws B2BException
	{
		Collection<AreaPredio> lcap_predio;
		PreparedStatement      lps_ps;
		ResultSet              lrs_rs;
		int                    li_column;

		li_column       = 1;
		lcap_predio     = null;
		lps_ps          = null;
		lrs_rs          = null;
		lcap_predio     = new ArrayList<AreaPredio>();

		if(StringUtils.isValidString(as_idTurno))
		{
			try
			{
				lps_ps = getConnection().prepareStatement(cs_CONSULTA_MATRICULAS_INF_BY_TURNO);

				lps_ps.setString(li_column++, as_idTurno);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcap_predio.add(getMatriculasInformacion(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findMatriculasInfByTurno", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lcap_predio;
	}

	/**
	 * Método para encontrar todos los areaPredio filtrando por el turnoHistoria.
	 *
	 * @param al_turnoHistoria Long de un turnoHistoria
	 * @return colección de areaPredio
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public Collection<AreaPredio> findMatriculasInformacion(Long al_turnoHistoria)
	    throws B2BException
	{
		Collection<AreaPredio> lcap_predio;
		PreparedStatement      lps_ps;
		ResultSet              lrs_rs;
		int                    li_column;

		li_column       = 1;
		lcap_predio     = null;
		lps_ps          = null;
		lrs_rs          = null;
		lcap_predio     = new ArrayList<AreaPredio>();

		if(NumericUtils.isValidLong(al_turnoHistoria))
		{
			try
			{
				lps_ps = getConnection().prepareStatement(cs_CONSULTA_MATRICULAS_INFORMACION);

				setLong(lps_ps, al_turnoHistoria, li_column++);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcap_predio.add(getMatriculasInformacion(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findMatriculasInformacion", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lcap_predio;
	}

	/**
	 * Método encargado de traer le información de apertura con base a los criterios de apertura.
	 *
	 * @param al_turnoHistoria Variable que contiene el id del turno historia del proceso.
	 * @param as_idCirculo Variable que contiene el id del ciruclo que dio apertura.
	 * @param al_idMatricula Variable que contiene el id de la matrícula que dio apertura.
	 * @param as_idAnotacion Variable que contiene el id de la anotación que dio apertura.
	 * @return Colección de areaPredio
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<AreaPredio> findMatriculasInformacion(
	    Long al_turnoHistoria, String as_idCirculo, Long al_idMatricula, String as_idAnotacion
	)
	    throws B2BException
	{
		Collection<AreaPredio> lcap_predio;
		PreparedStatement      lps_ps;
		ResultSet              lrs_rs;

		lcap_predio     = null;
		lps_ps          = null;
		lrs_rs          = null;
		lcap_predio     = new ArrayList<AreaPredio>();

		if(NumericUtils.isValidLong(al_turnoHistoria))
		{
			try
			{
				int           li_column;
				StringBuilder lsb_sb;

				li_column     = 1;
				lsb_sb        = new StringBuilder(cs_CONSULTA_MATRICULAS_INFORMACION_APERTURA);

				if(StringUtils.isValidString(as_idAnotacion))
					lsb_sb.append(" AND ID_ANOTACION_APERTURA = ? ");
				else
					lsb_sb.append(" AND ID_ANOTACION_APERTURA IS NULL ");

				lps_ps = getConnection().prepareStatement(lsb_sb.toString());

				setLong(lps_ps, al_turnoHistoria, li_column++);
				lps_ps.setString(li_column++, as_idCirculo);
				setLong(lps_ps, al_idMatricula, li_column++);

				if(StringUtils.isValidString(as_idAnotacion))
					lps_ps.setString(li_column++, as_idAnotacion);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcap_predio.add(getMatriculasInformacion(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findMatriculasInformacion", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lcap_predio;
	}

	/**
	 * Método para hallar la siguiente secuencia de la tabla.
	 *
	 * @return int
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see int
	 */
	public int findSecuencia()
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
			lps_ps     = getConnection().prepareStatement(cs_FIND_SECUENCIA);

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
	 * Método para insertar un registro en la BD.
	 *
	 * @param aapr_param objeto AccPredioRegistro con los datos a insertar
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void insert(AccPredioRegistro aapr_param)
	    throws B2BException
	{
		if(aapr_param != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_INSERT);

				lps_ps.setString(li_column++, aapr_param.getIdPredioRegistro());
				setLong(lps_ps, aapr_param.getIdTurnoHistoria(), li_column++);
				lps_ps.setString(li_column++, aapr_param.getIdCirculo());
				setLong(lps_ps, aapr_param.getIdMatricula(), li_column++);
				lps_ps.setString(li_column++, aapr_param.getIdZonaRegistral());
				lps_ps.setString(li_column++, aapr_param.getNupre());
				lps_ps.setString(li_column++, aapr_param.getNumeroPredial());
				lps_ps.setString(li_column++, aapr_param.getNumeroPredialAnt());
				lps_ps.setString(li_column++, aapr_param.getFolioGrabacion());
				lps_ps.setString(li_column++, aapr_param.getIdTipoPredio());
				lps_ps.setString(li_column++, aapr_param.getIdTipoUsoSuelo());
				lps_ps.setString(li_column++, aapr_param.getIdDocumento());
				lps_ps.setString(li_column++, aapr_param.getRadicacion());
				lps_ps.setString(li_column++, aapr_param.getPredioDefinitivo());
				lps_ps.setString(li_column++, aapr_param.getIdEstadoPredio());
				lps_ps.setDate(li_column++, (Date)aapr_param.getFechaApertura());
				setLong(lps_ps, NumericUtils.getLongWrapper(aapr_param.getAnotacionCierre()), li_column++);
				setLong(lps_ps, aapr_param.getIdComplementacion(), li_column++);
				lps_ps.setString(li_column++, aapr_param.getComentario());
				lps_ps.setString(li_column++, aapr_param.getIdDatosAntSistema());
				lps_ps.setString(li_column++, aapr_param.getTurnoBloqueo());
				lps_ps.setString(li_column++, aapr_param.getPredioInconsistente());
				lps_ps.setString(li_column++, aapr_param.getIdEstadoNupre());
				setLong(lps_ps, NumericUtils.getLongWrapper(aapr_param.getVersionDocumento()), li_column++);
				lps_ps.setString(li_column++, aapr_param.getIdUsuarioCreacion());
				lps_ps.setString(li_column++, aapr_param.getIpCreacion());
				lps_ps.setString(li_column++, aapr_param.getIdTurno());
				lps_ps.setString(li_column++, aapr_param.getNombrePredio());

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
	 * Método para actualizar un registro en la base de datos por su ID.
	 *
	 * @param aapr_param objeto AccPredioRegistro con los datos a actualizar
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void updateById(AccPredioRegistro aapr_param)
	    throws B2BException
	{
		if(aapr_param != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_UPDATE_BY_ID);

				lps_ps.setString(li_column++, aapr_param.getIdCirculo());
				setLong(lps_ps, aapr_param.getIdMatricula(), li_column++);
				lps_ps.setString(li_column++, aapr_param.getIdZonaRegistral());
				lps_ps.setString(li_column++, aapr_param.getNupre());
				lps_ps.setString(li_column++, aapr_param.getNumeroPredial());
				lps_ps.setString(li_column++, aapr_param.getNumeroPredialAnt());
				lps_ps.setString(li_column++, aapr_param.getFolioGrabacion());
				lps_ps.setString(li_column++, aapr_param.getIdTipoPredio());
				lps_ps.setString(li_column++, aapr_param.getIdTipoUsoSuelo());
				lps_ps.setString(li_column++, aapr_param.getIdDocumento());
				lps_ps.setString(li_column++, aapr_param.getRadicacion());
				lps_ps.setString(li_column++, aapr_param.getPredioDefinitivo());
				lps_ps.setString(li_column++, aapr_param.getIdEstadoPredio());
				setDate(lps_ps, aapr_param.getFechaApertura(), li_column++);
				lps_ps.setBigDecimal(li_column++, aapr_param.getAnotacionCierre());
				setLong(lps_ps, aapr_param.getIdComplementacion(), li_column++);
				lps_ps.setString(li_column++, aapr_param.getComentario());
				lps_ps.setString(li_column++, aapr_param.getIdAntiguoSistema());
				lps_ps.setString(li_column++, aapr_param.getTurnoBloqueo());
				lps_ps.setString(li_column++, aapr_param.getPredioInconsistente());
				lps_ps.setString(li_column++, aapr_param.getIdEstadoNupre());
				lps_ps.setBigDecimal(li_column++, aapr_param.getVersionDocumento());
				lps_ps.setString(li_column++, aapr_param.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, aapr_param.getIdTurno());
				lps_ps.setString(li_column++, aapr_param.getIpModificacion());
				lps_ps.setString(li_column++, aapr_param.getNombrePredio());

				lps_ps.setString(li_column++, aapr_param.getIdPredioRegistro());

				lps_ps.executeUpdate();
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
	}

	/**
	 * Método para extraer los datos de la consulta a la BD, ponerlos en un objeto y retornarlo.
	 *
	 * @param ars_rs objeto con la consulta traida de la base de datos para extraer su información
	 * @return objeto AreaPredio
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private AreaPredio getMatriculasInformacion(ResultSet ars_rs)
	    throws SQLException
	{
		AreaPredio lap_areaPredio;

		lap_areaPredio = new AreaPredio();

		lap_areaPredio.setComplementoDireccion(ars_rs.getString("DIRECCION_COMPLETA"));
		lap_areaPredio.setIdPredioRegistro(ars_rs.getString("ID_PREDIO_REGISTRO"));
		lap_areaPredio.setIdCirculo(ars_rs.getString("ID_CIRCULO"));
		lap_areaPredio.setIdMatricula(ars_rs.getLong("ID_MATRICULA"));
		lap_areaPredio.setCoeficiente(ars_rs.getBigDecimal("COEFICIENTE"));
		lap_areaPredio.setDescription(ars_rs.getString("DESCRIPTION"));
		lap_areaPredio.setNombrePredio(ars_rs.getString("NOMBRE_PREDIO"));
		lap_areaPredio.setIdDireccionPredio(ars_rs.getString("ID_DIRECCION_PREDIO"));
		lap_areaPredio.setAreaPredio(ars_rs.getBigDecimal("AREA_TERRENO"));
		lap_areaPredio.setAreaConstruida(ars_rs.getBigDecimal("AREA_CONSTRUIDA"));
		lap_areaPredio.setAreaPrivadaConstruida(ars_rs.getBigDecimal("AREA_PRIVADA"));
		lap_areaPredio.setRevisado(ars_rs.getString("REVISADO_CALIFICADOR").equalsIgnoreCase(EstadoCommon.S));
		lap_areaPredio.setRevisadoDigitador(ars_rs.getString("REVISADO_DIGITADOR").equalsIgnoreCase(EstadoCommon.S));

		return lap_areaPredio;
	}

	/**
	 * * Método para extraer los datos de la consulta a la BD, ponerlos en un objeto y retornarlo.
	 *
	 * @param ars_rs objeto con la consulta traida de la base de datos para extraer su información
	 * @return objeto AccPredioRegistro
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private AccPredioRegistro getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		AccPredioRegistro lapr_predioRegistro;

		lapr_predioRegistro = new AccPredioRegistro();

		lapr_predioRegistro.setIdPredioRegistro(ars_rs.getString("ID_PREDIO_REGISTRO"));
		lapr_predioRegistro.setIdTurnoHistoria(getLong(ars_rs, "ID_TURNO_HISTORIA"));
		lapr_predioRegistro.setIdCirculo(ars_rs.getString("ID_CIRCULO"));
		lapr_predioRegistro.setIdMatricula(getLong(ars_rs, "ID_MATRICULA"));
		lapr_predioRegistro.setIdZonaRegistral(ars_rs.getString("ID_ZONA_REGISTRAL"));
		lapr_predioRegistro.setNupre(ars_rs.getString("NUPRE"));
		lapr_predioRegistro.setNumeroPredial(ars_rs.getString("NUMERO_PREDIAL"));
		lapr_predioRegistro.setNumeroPredialAnt(ars_rs.getString("NUMERO_PREDIAL_ANT"));
		lapr_predioRegistro.setFolioGrabacion(ars_rs.getString("FOLIO_GRABACION"));
		lapr_predioRegistro.setIdTipoPredio(ars_rs.getString("ID_TIPO_PREDIO"));
		lapr_predioRegistro.setIdTipoUsoSuelo(ars_rs.getString("ID_TIPO_USO_SUELO"));
		lapr_predioRegistro.setIdDocumento(ars_rs.getString("ID_DOCUMENTO"));
		lapr_predioRegistro.setRadicacion(ars_rs.getString("RADICACION"));
		lapr_predioRegistro.setPredioDefinitivo(ars_rs.getString("PREDIO_DEFINITIVO"));
		lapr_predioRegistro.setIdEstadoPredio(ars_rs.getString("ID_ESTADO_PREDIO"));
		lapr_predioRegistro.setFechaApertura(ars_rs.getTimestamp("FECHA_APERTURA"));
		lapr_predioRegistro.setAnotacionCierre(ars_rs.getBigDecimal("ANOTACION_CIERRE"));
		lapr_predioRegistro.setIdComplementacion(getLong(ars_rs, "ID_COMPLEMENTACION"));
		lapr_predioRegistro.setComentario(ars_rs.getString("COMENTARIO"));
		lapr_predioRegistro.setIdAntiguoSistema(ars_rs.getString("ID_DATOS_ANT_SISTEMA"));
		lapr_predioRegistro.setIdDatosAntSistema(ars_rs.getString("ID_DATOS_ANT_SISTEMA"));
		lapr_predioRegistro.setTurnoBloqueo(ars_rs.getString("TURNO_BLOQUEO"));
		lapr_predioRegistro.setPredioInconsistente(ars_rs.getString("PREDIO_INCONSISTENTE"));
		lapr_predioRegistro.setIdEstadoNupre(ars_rs.getString("ID_ESTADO_NUPRE"));
		lapr_predioRegistro.setVersionDocumento(ars_rs.getBigDecimal("VERSION_DOCUMENTO"));
		lapr_predioRegistro.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		lapr_predioRegistro.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		lapr_predioRegistro.setIpCreacion(ars_rs.getString("IP_CREACION"));
		lapr_predioRegistro.setIdUsuarioModificacion(ars_rs.getString("ID_USUARIO_MODIFICACION"));
		lapr_predioRegistro.setFechaModificacion(ars_rs.getTimestamp("FECHA_MODIFICACION"));
		lapr_predioRegistro.setIpModificacion(ars_rs.getString("IP_MODIFICACION"));
		lapr_predioRegistro.setIdTurno(ars_rs.getString("ID_TURNO"));
		lapr_predioRegistro.setNombrePredio(ars_rs.getString("NOMBRE_PREDIO"));

		return lapr_predioRegistro;
	}
}
