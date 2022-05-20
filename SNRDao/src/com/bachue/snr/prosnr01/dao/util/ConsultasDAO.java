package com.bachue.snr.prosnr01.dao.util;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.model.calificacion.RegistroCalificacion;
import com.bachue.snr.prosnr01.model.entrega.DatosPredioTurnoEntrega;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Clase que contiene todos las consultas para datso de predio por turno de entrega, anotaciones de documentos, actos de solicitud,
 * busqueda de circulos por ID.
 *
 * @author garias
 */
public class ConsultasDAO extends BaseDAO
{
	/** Constante cs_FIND_DATOS_PREDIO_BY_TURNO_ENTREGA. */
	private static final String cs_FIND_DATOS_PREDIO_BY_TURNO_ENTREGA = "SELECT TD.NOMBRE TIPO_DOCUMENTO, SM.NUMERO, SM.FECHA_DOCUMENTO, OO.NOMBRE NOMBRE_OFICINA, TOF.NOMBRE TIPO_OFICINA, PA.NOMBRE PAIS, DTO.NOMBRE DEPARTAMENTO, "
		+ " MUN.NOMBRE MUNICIPIO, S.ENTIDAD_EXENTA,TE.NOMBRE TIPO_ENTIDAD  " + " FROM SDB_BGN_DOCUMENTO SM "
		+ " INNER JOIN SDB_ACC_SOLICITUD S ON SM.ID_DOCUMENTO = S.ID_DOCUMENTO AND SM.VERSION_DOCUMENTO = S.VERSION_DOCUMENTO  "
		+ " INNER JOIN SDB_ACC_TURNO T ON S.ID_SOLICITUD = T.ID_SOLICITUD  "
		+ " LEFT JOIN SDB_PGN_OFICINA_ORIGEN OO ON SM.ID_OFICINA_ORIGEN = OO.ID_OFICINA_ORIGEN  "
		+ " LEFT JOIN SDB_PGN_TIPO_OFICINA TOF ON OO.ID_TIPO_OFICINA = TOF.ID_TIPO_OFICINA "
		+ " LEFT JOIN SDB_PGN_TIPO_ENTIDAD TE ON TE.ID_TIPO_ENTIDAD = TOF.ID_TIPO_ENTIDAD "
		+ " INNER JOIN SDB_PGN_TIPO_DOCUMENTO_PUBLICO TD ON SM.ID_TIPO_DOCUMENTO = TD.ID_TIPO_DOCUMENTO  "
		+ " INNER JOIN SDB_PGN_DEPARTAMENTO DTO ON OO.ID_DEPARTAMENTO = DTO.ID_DEPARTAMENTO "
		+ " INNER JOIN SDB_PGN_PAIS PA ON OO.ID_PAIS = PA.ID_PAIS  "
		+ " INNER JOIN SDB_PGN_MUNICIPIO MUN ON OO.ID_MUNICIPIO = MUN.ID_MUNICIPIO AND OO.ID_PAIS = MUN.ID_PAIS AND OO.ID_DEPARTAMENTO = MUN.ID_DEPARTAMENTO "
		+ " WHERE T.ID_TURNO = ?";

	/** Constante cs_DETALLE_DIRECCION. */
	private static final String cs_DETALLE_DIRECCION = " SELECT   LTRIM(RTRIM(PNGTE.NOMBRE || ' '  || BGNDP.DATO_EJE_PRINCIPAL  || ' '  || PNGTE1.NOMBRE  || ' '  || BGNDP.DATO_EJE_SECUNDARIO  || ' '  || BGNDP.COMPLEMENTO_DIRECCION "
		+ " || ' '   || PGND.NOMBRE  || ' '   || PGNM.NOMBRE  || ' '   || PGNV.NOMBRE)) DIRECCION FROM SDB_ACC_SOLICITUD ACCS"
		+ " RIGHT JOIN SDB_ACC_SOLICITUD_MATRICULA ACCSM ON ACCS.ID_SOLICITUD = ACCSM.ID_SOLICITUD "
		+ " RIGHT JOIN SDB_ACC_PREDIO_REGISTRO BPR ON ACCSM.ID_CIRCULO    = BPR.ID_CIRCULO  AND ACCSM.ID_MATRICULA = BPR.ID_MATRICULA"
		+ " RIGHT JOIN SDB_PGN_ZONA_REGISTRAL ZR ON BPR.ID_ZONA_REGISTRAL = ZR.ID_ZONA_REGISTRAL "
		+ " RIGHT JOIN SDB_COL_PREDIO_TIPO PT ON BPR.ID_TIPO_PREDIO = PT.ID_TIPO_PREDIO"
		+ " RIGHT JOIN SDB_PGN_DEPARTAMENTO PGND ON ZR.ID_PAIS          = PGND.ID_PAIS  AND ZR.ID_DEPARTAMENTO =PGND.ID_DEPARTAMENTO"
		+ " RIGHT JOIN SDB_PGN_MUNICIPIO PGNM ON ZR.ID_PAIS          = PGNM.ID_PAIS AND ZR.ID_DEPARTAMENTO =PGNM.ID_DEPARTAMENTO AND ZR.ID_MUNICIPIO    = PGNM.ID_MUNICIPIO"
		+ " LEFT JOIN SDB_ACC_DIRECCION_PREDIO BGNDP ON BPR.ID_CIRCULO    = BGNDP.ID_CIRCULO AND BPR.ID_MATRICULA = BGNDP.ID_MATRICULA"
		+ " LEFT JOIN SDB_PNG_TIPO_EJE PNGTE  ON BGNDP.ID_TIPO_EJE_PRINCIPAL = PNGTE.ID_TIPO_EJE LEFT JOIN SDB_PNG_TIPO_EJE PNGTE1 ON BGNDP.ID_TIPO_EJE_SECUNDARIO = PNGTE1.ID_TIPO_EJE"
		+ " RIGHT JOIN SDB_PGN_VEREDA PGNV ON ZR.ID_PAIS           = PGNV.ID_PAIS  AND ZR.ID_DEPARTAMENTO = PGNV.ID_DEPARTAMENTO AND ZR.ID_MUNICIPIO = PGNV.ID_MUNICIPIO AND ZR.ID_VEREDA = PGNV.ID_VEREDA"
		+ " WHERE BPR.ID_CIRCULO = ?  AND  BPR.ID_MATRICULA = ?";

	/** Constante cs_DETALLE_DIRECCION. */
	private static final String cs_DETALLE_DIRECCION_COMPLETA = " SELECT   LTRIM(RTRIM(BGNDP.DIRECCION_COMPLETA)) DIRECCION FROM SDB_ACC_SOLICITUD ACCS"
		+ " RIGHT JOIN SDB_ACC_SOLICITUD_MATRICULA ACCSM ON ACCS.ID_SOLICITUD = ACCSM.ID_SOLICITUD "
		+ " RIGHT JOIN SDB_ACC_PREDIO_REGISTRO BPR ON ACCSM.ID_CIRCULO    = BPR.ID_CIRCULO  AND ACCSM.ID_MATRICULA = BPR.ID_MATRICULA"
		+ " RIGHT JOIN SDB_PGN_ZONA_REGISTRAL ZR ON BPR.ID_ZONA_REGISTRAL = ZR.ID_ZONA_REGISTRAL "
		+ " RIGHT JOIN SDB_COL_PREDIO_TIPO PT ON BPR.ID_TIPO_PREDIO = PT.ID_TIPO_PREDIO"
		+ " RIGHT JOIN SDB_PGN_DEPARTAMENTO PGND ON ZR.ID_PAIS          = PGND.ID_PAIS  AND ZR.ID_DEPARTAMENTO =PGND.ID_DEPARTAMENTO"
		+ " RIGHT JOIN SDB_PGN_MUNICIPIO PGNM ON ZR.ID_PAIS          = PGNM.ID_PAIS AND ZR.ID_DEPARTAMENTO =PGNM.ID_DEPARTAMENTO AND ZR.ID_MUNICIPIO    = PGNM.ID_MUNICIPIO"
		+ " LEFT JOIN SDB_ACC_DIRECCION_PREDIO BGNDP ON BPR.ID_CIRCULO    = BGNDP.ID_CIRCULO AND BPR.ID_MATRICULA = BGNDP.ID_MATRICULA"
		+ " LEFT JOIN SDB_PNG_TIPO_EJE PNGTE  ON BGNDP.ID_TIPO_EJE_PRINCIPAL = PNGTE.ID_TIPO_EJE LEFT JOIN SDB_PNG_TIPO_EJE PNGTE1 ON BGNDP.ID_TIPO_EJE_SECUNDARIO = PNGTE1.ID_TIPO_EJE"
		+ " RIGHT JOIN SDB_PGN_VEREDA PGNV ON ZR.ID_PAIS           = PGNV.ID_PAIS  AND ZR.ID_DEPARTAMENTO = PGNV.ID_DEPARTAMENTO AND ZR.ID_MUNICIPIO = PGNV.ID_MUNICIPIO AND ZR.ID_VEREDA = PGNV.ID_VEREDA"
		+ " WHERE BPR.ID_CIRCULO = ?  AND  BPR.ID_MATRICULA = ?";

	/** Constante cs_DATA_DOCUMENTO_ANOTACION. */
	private static final String cs_DATA_DOCUMENTO_ANOTACION = " SELECT   SM.NUMERO,  SM.FECHA_DOCUMENTO,  OO.NOMBRE NOMBRE_OFICINA,  MUN.NOMBRE MUNICIPIO"
		+ " FROM SDB_BGN_DOCUMENTO SM INNER JOIN SDB_ACC_ANOTACION_PREDIO S ON SM.ID_DOCUMENTO = S.ID_DOCUMENTO"
		+ " RIGHT JOIN SDB_PGN_OFICINA_ORIGEN OO ON SM.ID_OFICINA_ORIGEN = OO.ID_OFICINA_ORIGEN"
		+ " INNER JOIN SDB_PGN_TIPO_DOCUMENTO_PUBLICO TD ON SM.ID_TIPO_DOCUMENTO = TD.ID_TIPO_DOCUMENTO"
		+ " INNER JOIN SDB_PGN_DEPARTAMENTO DTO ON OO.ID_DEPARTAMENTO = DTO.ID_DEPARTAMENTO"
		+ " INNER JOIN SDB_PGN_PAIS PA ON OO.ID_PAIS = PA.ID_PAIS"
		+ " INNER JOIN SDB_PGN_MUNICIPIO MUN ON OO.ID_MUNICIPIO = MUN.ID_MUNICIPIO  AND OO.ID_PAIS  = MUN.ID_PAIS AND OO.ID_DEPARTAMENTO = MUN.ID_DEPARTAMENTO WHERE S.ID_ANOTACION_PREDIO = ?";

	/** Constante cs_FIND_ID_CIRCULO. */
	private static final String cs_FIND_ID_CIRCULO = "SELECT ACTO.ID_CIRCULO FROM SDB_ACC_SOLICITUD SOL, SDB_ACC_ACTO ACTO WHERE SOL.NIR= ? AND SOL.ID_SOLICITUD=ACTO.ID_SOLICITUD AND ACTO.ID_ACTO_PRINCIPAL IS NULL";

	/**
	 * Retorna el valor de los datos Basicos del Docuemnto
	 *
	 * @param as_idAnotacionPredio correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor del objeto registro calificacion
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see RegistroCalificacion
	 */
	public RegistroCalificacion findBasicosDocumento(String as_idAnotacionPredio)
	    throws B2BException
	{
		RegistroCalificacion lca_data;
		PreparedStatement    lps_ps;
		ResultSet            lrs_rs;
		int                  li_column;
		li_column     = 1;
		lca_data      = null;
		lps_ps        = null;
		lrs_rs        = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_DATA_DOCUMENTO_ANOTACION);

			lps_ps.setString(li_column++, as_idAnotacionPredio);

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				lca_data = getDetelleDocumento(lrs_rs);
		}
		catch(SQLException lse_e)
		{
			logError(this, "findBasicosDocumento", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return lca_data;
	}

	/**
	 * Retorna el valor de los datos del predio por turno entrega
	 *
	 * @param at_param correspondiente al valor del tipo de objeto DatosPredioTurnoEntrega
	 * @return devuelve el valor del objeto datos predio turno entrega
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DatosPredioTurnoEntrega
	 */
	public DatosPredioTurnoEntrega findDatosPredioByTurnoEntrega(DatosPredioTurnoEntrega at_param)
	    throws B2BException
	{
		DatosPredioTurnoEntrega ls_object;
		PreparedStatement       lps_ps;
		ResultSet               lrs_rs;

		ls_object     = null;
		lps_ps        = null;
		lrs_rs        = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_DATOS_PREDIO_BY_TURNO_ENTREGA);

			lps_ps.setString(1, at_param.getIdTurno());

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				ls_object = getDatosPredioTurnoEntregaFromResultSet(lrs_rs);
		}
		catch(SQLException lse_e)
		{
			logError(this, "findDatosPredioByTurnoEntrega", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return ls_object;
	}

	/**
	 * Retorna el valor de la direccion de matricula
	 *
	 * @param as_circulo correspondiente al valor del tipo de objeto String
	 * @param al_matricula correspondiente al valor del tipo de objeto Long
	 * @return devuelve el valor del objeto string
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public String findDireccionMatricula(String as_circulo, Long al_matricula)
	    throws B2BException
	{
		return findDireccionMatricula(as_circulo, al_matricula, false);
	}

	/**
	 * Retorna el valor de la direccion de matricula
	 *
	 * @param as_circulo correspondiente al valor del tipo de objeto String
	 * @param al_matricula correspondiente al valor del tipo de objeto Long
	 * @return devuelve el valor del objeto string
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public String findDireccionMatricula(String as_circulo, Long al_matricula, boolean ab_direccionCompleta)
	    throws B2BException
	{
		String            ls_direccion;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;
		int               li_column;
		li_column        = 1;
		ls_direccion     = null;
		lps_ps           = null;
		lrs_rs           = null;

		try
		{
			lps_ps = getConnection()
					         .prepareStatement(
					    ab_direccionCompleta ? cs_DETALLE_DIRECCION_COMPLETA : cs_DETALLE_DIRECCION
					);

			lps_ps.setString(li_column++, as_circulo);
			setLong(lps_ps, al_matricula, li_column++);

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				ls_direccion = lrs_rs.getString("DIRECCION");
		}
		catch(SQLException lse_e)
		{
			logError(this, "findDireccionMatricula", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return ls_direccion;
	}

	/**
	 * Retorna el valor del ID_CIRCULO
	 *
	 * @param as_nirGenerado correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor del objeto string
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public String findIdCirculo(String as_nirGenerado)
	    throws B2BException
	{
		String            ls_idCirculo;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;
		int               li_column;
		lps_ps           = null;
		lrs_rs           = null;
		ls_idCirculo     = null;
		li_column        = 1;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_ID_CIRCULO);

			lps_ps.setString(li_column++, as_nirGenerado);

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				ls_idCirculo = lrs_rs.getString("ID_CIRCULO");
		}
		catch(SQLException lse_e)
		{
			logError(this, "findDireccionMatricula", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return ls_idCirculo;
	}

	/**
	 * Retorna el valor de datos predio turno entrega contenido en el ResultSet
	 *
	 * @param lrs_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de datos predio turno entrega from result set
	 * @throws SQLException Señala que se ha producido una excepción
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DatosPredioTurnoEntrega
	 */
	private DatosPredioTurnoEntrega getDatosPredioTurnoEntregaFromResultSet(ResultSet lrs_rs)
	    throws SQLException, B2BException
	{
		DatosPredioTurnoEntrega ldpte_datosPredioTurnoEntrega;

		ldpte_datosPredioTurnoEntrega = new DatosPredioTurnoEntrega();

		ldpte_datosPredioTurnoEntrega.setTipoDocumento(lrs_rs.getString("TIPO_DOCUMENTO"));
		ldpte_datosPredioTurnoEntrega.setNumero(lrs_rs.getString("NUMERO"));
		ldpte_datosPredioTurnoEntrega.setFechaDocumento(lrs_rs.getTimestamp("FECHA_DOCUMENTO"));
		ldpte_datosPredioTurnoEntrega.setNombreOficina(lrs_rs.getString("NOMBRE_OFICINA"));
		ldpte_datosPredioTurnoEntrega.setTipoOficina(lrs_rs.getString("TIPO_OFICINA"));
		ldpte_datosPredioTurnoEntrega.setPais(lrs_rs.getString("PAIS"));
		ldpte_datosPredioTurnoEntrega.setDepartamento(lrs_rs.getString("DEPARTAMENTO"));
		ldpte_datosPredioTurnoEntrega.setMunicipio(lrs_rs.getString("MUNICIPIO"));
		ldpte_datosPredioTurnoEntrega.setEntidadExenta(lrs_rs.getString("ENTIDAD_EXENTA"));
		ldpte_datosPredioTurnoEntrega.setTipoEntidad(lrs_rs.getString("TIPO_ENTIDAD"));

		return ldpte_datosPredioTurnoEntrega;
	}

	/**
	 * Retorna el valor de registro calificacion contenido en el ResultSet
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de detelle documento
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private RegistroCalificacion getDetelleDocumento(ResultSet ars_rs)
	    throws SQLException
	{
		RegistroCalificacion lorc_rc;
		lorc_rc = new RegistroCalificacion();

		lorc_rc.setCodDocumento(ars_rs.getString("NUMERO"));
		lorc_rc.setFechaDocumento(ars_rs.getDate("FECHA_DOCUMENTO"));
		lorc_rc.setNombreOficina(ars_rs.getString("NOMBRE_OFICINA"));
		lorc_rc.setNombreMunicipio(ars_rs.getString("MUNICIPIO"));

		return lorc_rc;
	}
}
