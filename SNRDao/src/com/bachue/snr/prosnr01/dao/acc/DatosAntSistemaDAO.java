package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.model.sdb.acc.DatosAntSistema;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;


/**
 * Clase dedicada al manejo de conexion y transacciones en la base de datos que afecten
 * directamente a la tabla SDB_ACC_DATOS_ANT_SISTEMA.
 *
 * @author Nicolas Guaneme
 */
public class DatosAntSistemaDAO extends BaseDAO
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT ID_DATOS_ANT_SISTEMA, ADQUISICION_PREDIO, ID_TIPO_PREDIO, ID_CIRCULO, ID_MATRICULA, ID_PAIS, ID_DEPARTAMENTO, "
		+ "ID_MUNICIPIO, ID_LIBRO_ANT_SISTEMA, TOMO, FOLIO, PARTIDA, ID_USUARIO_CREACION, FECHA_CREACION, NOMBRE_PREDIO, ANIO, COMENTARIO, IP_CREACION,ID_USUARIO_MODIFICACION,FECHA_MODIFICACION,IP_MODIFICACION, CONSECUTIVO_PREDIO_ANT_SISTEMA, ID_SOLICITUD, CANTIDAD_CERTIFICADOS, "
		+ "ID_ACTO, ID_TURNO_CERTIFICADO, ID_MATRICULA, REVISADO_ANT_SISTEMA, ACCION, ID_CIRCULO_GRABACION, ID_MATRICULA_GRABACION, REQUIERE_FIRMA_LIBRO  FROM SDB_ACC_DATOS_ANT_SISTEMA WHERE ID_DATOS_ANT_SISTEMA = ?";

	/** Constante cs_UPDATE_NOMBRE_PREDIO_ANT_SISTEMA. */
	private static final String cs_UPDATE_NOMBRE_PREDIO_ANT_SISTEMA = "UPDATE SDB_ACC_DATOS_ANT_SISTEMA SET NOMBRE_PREDIO = ?, ID_USUARIO_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP, IP_MODIFICACION = ? WHERE ID_DATOS_ANT_SISTEMA = ?";

	/** Constante cs_UPDATE_REVISADO_ANT_SISTEMA. */
	private static final String cs_UPDATE_REVISADO_ANT_SISTEMA = "UPDATE SDB_ACC_DATOS_ANT_SISTEMA SET REVISADO_ANT_SISTEMA = ?, ID_USUARIO_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP, IP_MODIFICACION = ? WHERE ID_DATOS_ANT_SISTEMA = ?";

	/** Constante cs_UPDATE_ACCION_ANT_SISTEMA. */
	private static final String cs_UPDATE_ACCION_ANT_SISTEMA = "UPDATE SDB_ACC_DATOS_ANT_SISTEMA SET ACCION = ?, ID_USUARIO_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP, IP_MODIFICACION = ? WHERE ID_DATOS_ANT_SISTEMA = ?";

	/** Constante cs_UPDATE_ACTIVO_REQUIERE_FIRMA_LIBRO. */
	private static final String cs_UPDATE_ACTIVO_REQUIERE_FIRMA_LIBRO = "UPDATE SDB_ACC_DATOS_ANT_SISTEMA SET REQUIERE_FIRMA_LIBRO = 'S', ID_USUARIO_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP, IP_MODIFICACION = ? WHERE ID_DATOS_ANT_SISTEMA = ?";

	/** Constante cs_UPDATE_SOLICITAR_COMPLEMENTACION. */
	private static final String cs_UPDATE_SOLICITAR_COMPLEMENTACION = "UPDATE SDB_ACC_DATOS_ANT_SISTEMA SET SOLICITAR_COMPLEMENTACION = ? , ID_USUARIO_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP, IP_MODIFICACION = ? WHERE ID_DATOS_ANT_SISTEMA = ?";

	/** Constante cs_UPDATE_INACTIVO_REQUIERE_FIRMA_LIBRO. */
	private static final String cs_UPDATE_INACTIVO_REQUIERE_FIRMA_LIBRO = "UPDATE SDB_ACC_DATOS_ANT_SISTEMA SET REQUIERE_FIRMA_LIBRO = 'N', ID_USUARIO_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP, IP_MODIFICACION = ? WHERE ID_DATOS_ANT_SISTEMA = ?";

	/** Constante cs_UPDATE_CONSECUTIVO. */
	private static final String cs_UPDATE_CONSECUTIVO = "UPDATE SDB_ACC_DATOS_ANT_SISTEMA SET CONSECUTIVO_PREDIO_ANT_SISTEMA = ?, ID_USUARIO_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP, IP_MODIFICACION = ? WHERE ID_DATOS_ANT_SISTEMA = ?";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_ACC_DATOS_ANT_SISTEMA SET ADQUISICION_PREDIO=?, ID_TIPO_PREDIO=?, ID_CIRCULO=?, ID_MATRICULA = ?, ID_PAIS=? , "
		+ "ID_DEPARTAMENTO=?, ID_MUNICIPIO=?, ID_LIBRO_ANT_SISTEMA=?, TOMO=?, FOLIO=?, PARTIDA=?, NOMBRE_PREDIO=?, ANIO=?, COMENTARIO=?, CONSECUTIVO_PREDIO_ANT_SISTEMA=?, ID_SOLICITUD=?, CANTIDAD_CERTIFICADOS=?, ID_CIRCULO_GRABACION=?, ID_MATRICULA_GRABACION=?,REVISADO_ANT_SISTEMA = ?, "
		+ "ID_USUARIO_MODIFICACION=?, FECHA_MODIFICACION=SYSTIMESTAMP, IP_MODIFICACION=?, ACCION=? WHERE ID_DATOS_ANT_SISTEMA = ?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_ACC_DATOS_ANT_SISTEMA (ID_DATOS_ANT_SISTEMA, ADQUISICION_PREDIO, ID_TIPO_PREDIO, "
		+ "ID_CIRCULO, ID_MATRICULA, ID_PAIS, ID_DEPARTAMENTO, ID_MUNICIPIO, ID_LIBRO_ANT_SISTEMA, TOMO, FOLIO, PARTIDA, NOMBRE_PREDIO, ANIO, COMENTARIO, CONSECUTIVO_PREDIO_ANT_SISTEMA, ID_SOLICITUD, CANTIDAD_CERTIFICADOS, "
		+ "ID_CIRCULO_GRABACION, ID_MATRICULA_GRABACION,REVISADO_ANT_SISTEMA, ID_USUARIO_CREACION, IP_CREACION, FECHA_CREACION) VALUES(?, ?, ?, ?, ? , ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?, SYSTIMESTAMP)";

	/** Constante cs_FIND_BY_DATA_ANT_SISTEMA. */
	private static final String cs_FIND_BY_DATA_ANT_SISTEMA = "SELECT * FROM SDB_ACC_DATOS_ANT_SISTEMA WHERE ID_CIRCULO = ?";

	/** Constante cs_FIND_BY_ID_SOLICITUD. */
	private static final String cs_FIND_BY_ID_SOLICITUD = "SELECT * FROM SDB_ACC_DATOS_ANT_SISTEMA WHERE ID_SOLICITUD = ? ORDER BY FECHA_CREACION DESC";

	/** Constante cs_FIND_BY_ID_MATRICULA_GRABACION. */
	private static final String cs_FIND_BY_ID_MATRICULA_GRABACION = "SELECT * FROM SDB_ACC_DATOS_ANT_SISTEMA WHERE ID_CIRCULO_GRABACION = ? AND ID_MATRICULA_GRABACION = ?";

	/** Constante cs_FIND_BY_ID_SOLICITUD_CIRCULO. */
	private static final String cs_FIND_BY_ID_SOLICITUD_CIRCULO = "SELECT * FROM SDB_ACC_DATOS_ANT_SISTEMA WHERE ID_SOLICITUD = ? AND ID_CIRCULO = ? ORDER BY CONSECUTIVO_PREDIO_ANT_SISTEMA ASC";

	/** Constante cs_FIND_BY_CIRCULO_MATRICULA. */
	private static final String cs_FIND_BY_CIRCULO_MATRICULA = "SELECT * FROM SDB_ACC_DATOS_ANT_SISTEMA WHERE ID_CIRCULO = ? AND ID_MATRICULA = ? ORDER BY 1 DESC";

	/** Constante cs_SECUENCIA. */
	private static final String cs_SECUENCIA = "SELECT SEC_ACC_DATOS_ANT_SISTEMA_ID_DATOS_ANT_SISTEMA.NEXTVAL FROM DUAL";

	/** Constante cs_DELETE. */
	private static final String cs_DELETE = "DELETE FROM SDB_ACC_DATOS_ANT_SISTEMA WHERE ID_DATOS_ANT_SISTEMA = ?";

	/** Constante cs_DELETE_BY_SOLICITUD. */
	private static final String cs_DELETE_BY_SOLICITUD = "DELETE FROM SDB_ACC_DATOS_ANT_SISTEMA WHERE ID_SOLICITUD = ?";

	/**
	 * Elimina los registros de un ID específico.
	 *
	 * @param adas_param Objeto contenedor del id a eliminar de la tabla
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void delete(DatosAntSistema adas_param)
	    throws B2BException
	{
		if(adas_param != null)
		{
			PreparedStatement lps_ps;
			Connection        lc_connection;

			lps_ps            = null;
			lc_connection     = getConnection();

			try
			{
				int li_column;
				li_column     = 1;

				lps_ps = lc_connection.prepareStatement(cs_DELETE);

				lps_ps.setString(li_column++, adas_param.getIdDatosAntSistema());

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
	 * Elimina los registros de un ID Solicitud específico.
	 *
	 * @param adas_param Objeto contenedor del id a eliminar de la tabla
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void deleteBySolicitud(DatosAntSistema adas_param)
	    throws B2BException
	{
		if(adas_param != null)
		{
			PreparedStatement lps_ps;
			Connection        lc_connection;

			lps_ps            = null;
			lc_connection     = getConnection();

			try
			{
				int li_column;
				li_column     = 1;

				lps_ps = lc_connection.prepareStatement(cs_DELETE_BY_SOLICITUD);

				lps_ps.setString(li_column++, adas_param.getIdSolicitud());

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
	 * Busca todos los registros en la tabla que tengan un id circulo y matricula específico.
	 *
	 * @param adas_param Objeto contenedor de el id solicitud a utilizar como filtro en la consulta
	 * @return registro resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DatosAntSistema
	 */
	public DatosAntSistema findByCirculoMatricula(DatosAntSistema adas_param)
	    throws B2BException
	{
		DatosAntSistema ldas_object;

		ldas_object = null;

		if(adas_param != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_cont;

				li_cont     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_CIRCULO_MATRICULA);

				lps_ps.setString(li_cont++, adas_param.getIdCirculo());
				setLong(lps_ps, adas_param.getIdMatricula(), li_cont++);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ldas_object = getObjetFromResultSet(lrs_rs);
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

		return ldas_object;
	}

	/**
	 * Consulta en la base de datos los registros que coincidan con diferentes filtros de busqueda ingresadas
	 * por el usuario en pantalla.
	 *
	 * @param adas_param Objeto contenedor de los filtros a utilizar en la busqueda
	 * @return Colección de datos ant sistema resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Collection
	 */
	public Collection<DatosAntSistema> findByDataAntSistema(DatosAntSistema adas_param)
	    throws B2BException
	{
		Collection<DatosAntSistema> lcdas_object;

		lcdas_object = new ArrayList<DatosAntSistema>();

		if(adas_param != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;
			StringBuilder     lsb_sb;

			lsb_sb     = new StringBuilder();
			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_count;
				li_count = 1;

				lsb_sb.append(cs_FIND_BY_DATA_ANT_SISTEMA);

				String ls_idTipoPredio;
				String ls_nombrePredio;
				String ls_idPais;
				String ls_idDepartamento;
				String ls_idMunicipio;
				Long   ll_tomo;
				Long   ll_folio;
				String ls_partida;
				Long   ll_anio;
				Long   ll_idLibro;

				ls_idTipoPredio       = adas_param.getIdTipoPredio();
				ls_nombrePredio       = adas_param.getNombrePredio();
				ls_idPais             = adas_param.getIdPais();
				ls_idDepartamento     = adas_param.getIdDepartamento();
				ls_idMunicipio        = adas_param.getIdMunicipio();
				ll_tomo               = adas_param.getTomo();
				ll_folio              = adas_param.getFolio();
				ls_partida            = adas_param.getPartida();
				ll_anio               = adas_param.getAnio();
				ll_idLibro            = adas_param.getIdLibroAntSistema();

				if(NumericUtils.isValidLong(ll_idLibro))
					lsb_sb.append(" AND ID_LIBRO_ANT_SISTEMA = ? ");

				if(StringUtils.isValidString(ls_idTipoPredio))
					lsb_sb.append(" AND ID_TIPO_PREDIO = ? ");

				if(StringUtils.isValidString(ls_nombrePredio))
					lsb_sb.append(" AND NOMBRE_PREDIO = ? ");

				if(StringUtils.isValidString(ls_idPais))
					lsb_sb.append(" AND ID_PAIS = ? ");

				if(StringUtils.isValidString(ls_idDepartamento))
					lsb_sb.append(" AND ID_DEPARTAMENTO = ? ");

				if(StringUtils.isValidString(ls_idMunicipio))
					lsb_sb.append(" AND ID_MUNICIPIO = ? ");

				if(NumericUtils.isValidLong(ll_tomo))
					lsb_sb.append("AND TOMO = ? ");

				if(NumericUtils.isValidLong(ll_folio))
					lsb_sb.append("AND FOLIO = ? ");

				if(StringUtils.isValidString(ls_partida))
					lsb_sb.append(" AND PARTIDA = ? ");

				if(NumericUtils.isValidLong(ll_anio))
					lsb_sb.append(" AND ANIO = ? ");

				lps_ps = getConnection().prepareStatement(lsb_sb.toString());

				lps_ps.setString(li_count++, adas_param.getIdCirculo());

				if(NumericUtils.isValidLong(ll_idLibro))
					setLong(lps_ps, adas_param.getIdLibroAntSistema(), li_count++);

				if(StringUtils.isValidString(ls_idTipoPredio))
					lps_ps.setString(li_count++, adas_param.getIdTipoPredio());

				if(StringUtils.isValidString(ls_nombrePredio))
					lps_ps.setString(li_count++, adas_param.getNombrePredio());

				if(StringUtils.isValidString(ls_idPais))
					lps_ps.setString(li_count++, adas_param.getIdPais());

				if(StringUtils.isValidString(ls_idDepartamento))
					lps_ps.setString(li_count++, adas_param.getIdDepartamento());

				if(StringUtils.isValidString(ls_idMunicipio))
					lps_ps.setString(li_count++, adas_param.getIdMunicipio());

				if(NumericUtils.isValidLong(ll_tomo))
					setLong(lps_ps, adas_param.getTomo(), li_count++);

				if(NumericUtils.isValidLong(ll_folio))
					setLong(lps_ps, adas_param.getFolio(), li_count++);

				if(StringUtils.isValidString(ls_partida))
					lps_ps.setString(li_count++, adas_param.getPartida());

				if(NumericUtils.isValidLong(ll_anio))
					setLong(lps_ps, adas_param.getAnio(), li_count++);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcdas_object.add(getObjetFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByDataAntSistema", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lcdas_object;
	}

	/**
	 * Consulta en la base datos un registro que coincida con un id datos ant sistema específico.
	 *
	 * @param adas_param Objeto contenedor de los filtros a utilizar en la busqueda
	 * @return DatosAntSistema resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DatosAntSistema
	 */
	public DatosAntSistema findById(DatosAntSistema adas_param)
	    throws B2BException
	{
		return (adas_param != null) ? findById(adas_param.getIdDatosAntSistema()) : null;
	}

	/**
	 * Consulta en la base datos un registro que coincida con un id datos ant sistema específico.
	 *
	 * @param as_idDatosAntSistema Objeto contenedor de los filtros a utilizar en la busqueda
	 * @return DatosAntSistema resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DatosAntSistema
	 */
	public DatosAntSistema findById(String as_idDatosAntSistema)
	    throws B2BException
	{
		DatosAntSistema ldas_object;

		ldas_object = null;

		if(StringUtils.isValidString(as_idDatosAntSistema))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(1, as_idDatosAntSistema);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ldas_object = getObjetFromResultSet(lrs_rs);
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

		return ldas_object;
	}

	/**
	 * Metodo encargado de buscar por id_datos_ant_sistema y por id_circulo en la tabla SDB_ACC_DATOS_ANT_SISTEMA.
	 *
	 * @param adas_param Objeto que contiene los criterios de busqueda necesarios para realizar la consulta.
	 * @return Objeto que contiene el resultado de la consulta.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DatosAntSistema
	 */
	public DatosAntSistema findByIdCirculo(DatosAntSistema adas_param)
	    throws B2BException
	{
		DatosAntSistema ldas_object;

		ldas_object = null;

		if(adas_param != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int           li_contador;
				StringBuilder lsb_sb;

				li_contador     = 1;
				lsb_sb          = new StringBuilder(cs_FIND_BY_ID);

				lsb_sb.append(" AND ID_CIRCULO = ? ");

				lps_ps = getConnection().prepareStatement(lsb_sb.toString());

				lps_ps.setString(li_contador++, adas_param.getIdDatosAntSistema());
				lps_ps.setString(li_contador++, adas_param.getIdCirculo());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ldas_object = getObjetFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdIdCirculo", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return ldas_object;
	}

	/**
	 * Busca todos los registros en la tabla que tengan un matricula grabacion específica.
	 *
	 * @param adas_param correspondiente al valor del tipo de objeto DatosAntSistema
	 * @return Colección con los registros resultantes de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Collection
	 */
	public Collection<DatosAntSistema> findByIdMatriculaGrabacion(DatosAntSistema adas_param)
	    throws B2BException
	{
		Collection<DatosAntSistema> lcdas_object;

		lcdas_object = new LinkedList<DatosAntSistema>();

		if(adas_param != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_cont;

				li_cont     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_MATRICULA_GRABACION);

				lps_ps.setString(li_cont++, adas_param.getIdCirculoGrabacion());
				lps_ps.setLong(li_cont++, NumericUtils.getLong(adas_param.getIdMatriculaGrabacion()));

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcdas_object.add(getObjetFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdMatriculaGrabacion", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lcdas_object.isEmpty())
			lcdas_object = null;

		return lcdas_object;
	}

	/**
	 * Busca todos los registros en la tabla que tengan un id solicitud específico.
	 *
	 * @param adas_param Objeto contenedor de el id solicitud a utilizar como filtro en la consulta
	 * @return Colección con los registros resultantes de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Collection
	 */
	public Collection<DatosAntSistema> findByIdSolicitud(DatosAntSistema adas_param)
	    throws B2BException
	{
		return findByIdSolicitud(adas_param, false);
	}

	/**
	 * Busca todos los registros en la tabla que tengan un id solicitud específico.
	 *
	 * @param adas_param Objeto contenedor de el id solicitud a utilizar como filtro en la consulta
	 * @param ab_orderBy correspondiente al valor del tipo de objeto boolean
	 * @return Colección con los registros resultantes de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Collection
	 */
	public Collection<DatosAntSistema> findByIdSolicitud(DatosAntSistema adas_param, boolean ab_orderBy)
	    throws B2BException
	{
		Collection<DatosAntSistema> lcdas_object;

		lcdas_object = new LinkedList<DatosAntSistema>();

		if(adas_param != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				StringBuilder lsb_query;

				lsb_query = new StringBuilder();

				lsb_query.append(cs_FIND_BY_ID_SOLICITUD);

				if(ab_orderBy)
					lsb_query.append(" ORDER BY CONSECUTIVO_PREDIO_ANT_SISTEMA ASC ");

				lps_ps = getConnection().prepareStatement(lsb_query.toString());

				lps_ps.setString(1, adas_param.getIdSolicitud());

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcdas_object.add(getObjetFromResultSet(lrs_rs));
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

		if(lcdas_object.isEmpty())
			lcdas_object = null;

		return lcdas_object;
	}

	/**
	 * Busca todos los registros en la tabla que tengan un id solicitud específico.
	 *
	 * @param adas_param Objeto contenedor de el id solicitud a utilizar como filtro en la consulta
	 * @return Colección con los registros resultantes de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Collection
	 */
	public Collection<DatosAntSistema> findByIdSolicitudCirculo(DatosAntSistema adas_param)
	    throws B2BException
	{
		return (adas_param != null) ? findByIdSolicitudCirculo(adas_param.getIdSolicitud(), adas_param.getIdCirculo())
		                            : null;
	}

	/**
	 * Busca todos los registros en la tabla que tengan un id solicitud específico.
	 *
	 * @param as_idSolicitud Objeto contenedor de el id solicitud a utilizar como filtro en la consulta
	 * @param as_idCirculo Objeto contenedor de el id círculo a utilizar como filtro en la consulta
	 * @return Colección con los registros resultantes de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Collection
	 */
	public Collection<DatosAntSistema> findByIdSolicitudCirculo(String as_idSolicitud, String as_idCirculo)
	    throws B2BException
	{
		Collection<DatosAntSistema> lcdas_object;

		lcdas_object = new LinkedList<DatosAntSistema>();

		if(StringUtils.isValidString(as_idSolicitud) && StringUtils.isValidString(as_idCirculo))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_cont;

				li_cont     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_SOLICITUD_CIRCULO);

				lps_ps.setString(li_cont++, as_idSolicitud);
				lps_ps.setString(li_cont++, as_idCirculo);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcdas_object.add(getObjetFromResultSet(lrs_rs));
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

		if(lcdas_object.isEmpty())
			lcdas_object = null;

		return lcdas_object;
	}

	/**
	 * Método encargado de consultar el primer registro de datos ant sistema que corresponda al id solicitud ingresado.
	 *
	 * @param as_idSolicitud Variable de tipo String que contiene el id solicitud para realizar la consulta.
	 * @return Objeto que contiene la información de antiguo sistema consultada.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DatosAntSistema
	 */
	public DatosAntSistema findByIdSolicitudOne(String as_idSolicitud)
	    throws B2BException
	{
		DatosAntSistema ldas_return;

		ldas_return = null;

		if(StringUtils.isValidString(as_idSolicitud))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_cont;

				li_cont     = 1;
				lps_ps      = getConnection().prepareStatement(cs_FIND_BY_ID_SOLICITUD);

				lps_ps.setString(li_cont++, as_idSolicitud);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ldas_return = getObjetFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdSolicitudOne", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return ldas_return;
	}

	/**
	 * Inserta o actualiza un registro en la tabla.
	 *
	 * @param adas_param Objeto contenedor de la información a actualizar o insertar
	 * @param ab_query true para insertar, false para actualizar
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void insertOrUpdate(DatosAntSistema adas_param, boolean ab_query)
	    throws B2BException
	{
		if(adas_param != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int    li_column;
				String ls_query = ab_query ? cs_INSERT : cs_UPDATE;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(ls_query);

				if(ab_query)
					lps_ps.setString(li_column++, adas_param.getIdDatosAntSistema());

				lps_ps.setString(li_column++, adas_param.getAdquisicionPredio());
				lps_ps.setString(li_column++, adas_param.getIdTipoPredio());
				lps_ps.setString(li_column++, adas_param.getIdCirculo());
				setLong(lps_ps, adas_param.getIdMatricula(), li_column++);
				lps_ps.setString(li_column++, adas_param.getIdPais());
				lps_ps.setString(li_column++, adas_param.getIdDepartamento());
				lps_ps.setString(li_column++, adas_param.getIdMunicipio());
				setLong(lps_ps, adas_param.getIdLibroAntSistema(), li_column++);
				setLong(lps_ps, adas_param.getTomo(), li_column++);
				setLong(lps_ps, adas_param.getFolio(), li_column++);
				lps_ps.setString(li_column++, adas_param.getPartida());
				lps_ps.setString(li_column++, adas_param.getNombrePredio());
				setLong(lps_ps, adas_param.getAnio(), li_column++);
				lps_ps.setString(li_column++, adas_param.getComentario());
				lps_ps.setString(li_column++, adas_param.getConsecutivoPredioAntSistema());
				lps_ps.setString(li_column++, adas_param.getIdSolicitud());
				setLong(lps_ps, adas_param.getCantidadCertificados(), li_column++);
				lps_ps.setString(li_column++, adas_param.getIdCirculoGrabacion());
				setLong(lps_ps, adas_param.getIdMatriculaGrabacion(), li_column++);
				lps_ps.setString(li_column++, adas_param.getRevisadoAntSistema());

				if(ab_query)
				{
					lps_ps.setString(li_column++, adas_param.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, adas_param.getIpCreacion());
				}

				if(!ab_query)
				{
					lps_ps.setString(li_column++, adas_param.getIdUsuarioModificacion());
					lps_ps.setString(li_column++, adas_param.getIpModificacion());
					lps_ps.setString(li_column++, adas_param.getAccion());
					lps_ps.setString(li_column++, adas_param.getIdDatosAntSistema());
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
				close(lps_ps);
			}
		}
	}

	/**
	 * Calcula la secuencia de un nuevo registro e inserta el registro en la tabla.
	 *
	 * @param adas_param Objeto contenedor de los datos a insertar en el registro
	 * @param ab_insertar correspondiente al valor del tipo de objeto boolean
	 * @return Numero de secuencia generado para el registro insertado
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see long
	 */
	public long insertUpdate(DatosAntSistema adas_param, boolean ab_insertar)
	    throws B2BException
	{
		long ll_secuencia;

		ll_secuencia = 0;

		if(adas_param != null)
		{
			PreparedStatement lps_ps;
			PreparedStatement lps_secuencia;
			Connection        lc_connection;
			ResultSet         lrs_rs;

			lps_ps            = null;
			lps_secuencia     = null;
			lc_connection     = getConnection();
			lrs_rs            = null;

			try
			{
				int li_column;
				li_column = 1;

				String ls_query;

				ls_query     = ab_insertar ? cs_INSERT : cs_UPDATE;

				lps_ps = lc_connection.prepareStatement(ls_query);

				if(ab_insertar)
				{
					lps_secuencia     = lc_connection.prepareStatement(cs_SECUENCIA);

					lrs_rs = lps_secuencia.executeQuery();

					if(lrs_rs.next())
					{
						ll_secuencia = lrs_rs.getLong(1);
						lps_ps.setLong(li_column++, ll_secuencia);
					}
				}

				lps_ps.setString(li_column++, adas_param.getAdquisicionPredio());
				lps_ps.setString(li_column++, adas_param.getIdTipoPredio());
				lps_ps.setString(li_column++, adas_param.getIdCirculo());
				setLong(lps_ps, adas_param.getIdMatricula(), li_column++);
				lps_ps.setString(li_column++, adas_param.getIdPais());
				lps_ps.setString(li_column++, adas_param.getIdDepartamento());
				lps_ps.setString(li_column++, adas_param.getIdMunicipio());
				setLong(lps_ps, adas_param.getIdLibroAntSistema(), li_column++);
				setLong(lps_ps, adas_param.getTomo(), li_column++);
				setLong(lps_ps, adas_param.getFolio(), li_column++);
				lps_ps.setString(li_column++, adas_param.getPartida());
				lps_ps.setString(li_column++, adas_param.getNombrePredio());
				setLong(lps_ps, adas_param.getAnio(), li_column++);
				lps_ps.setString(li_column++, adas_param.getComentario());
				lps_ps.setString(li_column++, adas_param.getConsecutivoPredioAntSistema());
				lps_ps.setString(li_column++, adas_param.getIdSolicitud());
				setLong(lps_ps, adas_param.getCantidadCertificados(), li_column++);
				lps_ps.setString(li_column++, adas_param.getIdCirculoGrabacion());
				setLong(lps_ps, adas_param.getIdMatriculaGrabacion(), li_column++);
				lps_ps.setString(li_column++, adas_param.getRevisadoAntSistema());

				if(ab_insertar)
				{
					lps_ps.setString(li_column++, adas_param.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, adas_param.getIpCreacion());
				}
				else
				{
					lps_ps.setString(li_column++, adas_param.getIdUsuarioModificacion());
					lps_ps.setString(li_column++, adas_param.getIpModificacion());
					lps_ps.setString(li_column++, adas_param.getAccion());
					lps_ps.setString(li_column++, adas_param.getIdDatosAntSistema());
				}

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "insertUpdate", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
				close(lps_secuencia);
				close(lrs_rs);
			}
		}

		return ll_secuencia;
	}

	/**
	 * Actualiza el campo accion de un registro con un id datos ant sistema específico.
	 *
	 * @param adas_param Objeto contenedor de los datos a utilizar como filtro en la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void updateAccionAntSistema(DatosAntSistema adas_param)
	    throws B2BException
	{
		if(adas_param != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_UPDATE_ACCION_ANT_SISTEMA);

				lps_ps.setString(li_column++, adas_param.getAccion());
				lps_ps.setString(li_column++, adas_param.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, adas_param.getIpModificacion());
				lps_ps.setString(li_column++, adas_param.getIdDatosAntSistema());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "updateAccionAntSistema", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Actualiza el campo requiere firma libro de un registro con un id datos ant sistema específico.
	 *
	 * @param adas_param Objeto contenedor de los datos a utilizar como filtro en la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void updateActivoRequiereFirmaLibro(DatosAntSistema adas_param)
	    throws B2BException
	{
		if(adas_param != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_UPDATE_ACTIVO_REQUIERE_FIRMA_LIBRO);

				lps_ps.setString(li_column++, adas_param.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, adas_param.getIpModificacion());
				lps_ps.setString(li_column++, adas_param.getIdDatosAntSistema());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "updateActivoRequiereFirmaLibro", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	public void updateComplementacionAntSistema(DatosAntSistema adas_param)
	    throws B2BException
	{
		if(adas_param != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_UPDATE_SOLICITAR_COMPLEMENTACION);

				lps_ps.setString(li_column++, adas_param.getSolicitarComplementacion());
				lps_ps.setString(li_column++, adas_param.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, adas_param.getIpModificacion());
				lps_ps.setString(li_column++, adas_param.getIdDatosAntSistema());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "updateComplementacionAntSistema", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Actualiza el campo consecutivo de un registro con un id datos ant sistema específico.
	 *
	 * @param adas_param Objeto contenedor de los datos a utilizar como filtro en la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void updateConsecutivo(DatosAntSistema adas_param)
	    throws B2BException
	{
		if(adas_param != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_UPDATE_CONSECUTIVO);

				lps_ps.setString(li_column++, adas_param.getConsecutivoPredioAntSistema());
				lps_ps.setString(li_column++, adas_param.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, adas_param.getIpModificacion());
				lps_ps.setString(li_column++, adas_param.getIdDatosAntSistema());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "updateConsecutivo", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Actualiza el campo requiere firma libro de un registro con un id datos ant sistema específico.
	 *
	 * @param adas_param Objeto contenedor de los datos a utilizar como filtro en la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void updateInactivoRequiereFirmaLibro(DatosAntSistema adas_param)
	    throws B2BException
	{
		if(adas_param != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_UPDATE_INACTIVO_REQUIERE_FIRMA_LIBRO);

				lps_ps.setString(li_column++, adas_param.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, adas_param.getIpModificacion());
				lps_ps.setString(li_column++, adas_param.getIdDatosAntSistema());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "updateInactivoRequiereFirmaLibro", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Actualiza el nombre del predio.
	 *
	 * @param adas_param Objeto contenedor del nuevo valor para el nombre del predio
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void updateNombrePredioAntSistema(DatosAntSistema adas_param)
	    throws B2BException
	{
		if(adas_param != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_UPDATE_NOMBRE_PREDIO_ANT_SISTEMA);

				lps_ps.setString(li_column++, adas_param.getNombrePredio());
				lps_ps.setString(li_column++, adas_param.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, adas_param.getIpModificacion());
				lps_ps.setString(li_column++, adas_param.getIdDatosAntSistema());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "updateNombrePredioAntSistema", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Actualiza el campo revisado ant sistema de un registro con un di datos ant sistema específico.
	 *
	 * @param adas_param Objeto contenedor de los datos a utilizar como filtro en la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void updateRevisionAntSistema(DatosAntSistema adas_param)
	    throws B2BException
	{
		if(adas_param != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_UPDATE_REVISADO_ANT_SISTEMA);

				lps_ps.setString(li_column++, adas_param.getRevisadoAntSistema());
				lps_ps.setString(li_column++, adas_param.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, adas_param.getIpModificacion());
				lps_ps.setString(li_column++, adas_param.getIdDatosAntSistema());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "UpdateRevisionAntSistema", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Extrae la información recuperada de la base de datos y la asigna a un objeto DatosAntSistema.
	 *
	 * @param ars_rs Objeto contenedor del resultado de la consulta
	 * @return DatosAntSistema con la información extraida de la consulta
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private DatosAntSistema getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		DatosAntSistema ldas_datos;

		ldas_datos = new DatosAntSistema();

		ldas_datos.setIdDatosAntSistema(ars_rs.getString("ID_DATOS_ANT_SISTEMA"));
		ldas_datos.setAdquisicionPredio(ars_rs.getString("ADQUISICION_PREDIO"));
		ldas_datos.setIdTipoPredio(ars_rs.getString("ID_TIPO_PREDIO"));
		ldas_datos.setIdCirculo(ars_rs.getString("ID_CIRCULO"));
		ldas_datos.setIdMatricula(getLong(ars_rs, "ID_MATRICULA"));
		ldas_datos.setIdPais(ars_rs.getString("ID_PAIS"));
		ldas_datos.setIdDepartamento(ars_rs.getString("ID_DEPARTAMENTO"));
		ldas_datos.setIdMunicipio(ars_rs.getString("ID_MUNICIPIO"));
		ldas_datos.setIdLibroAntSistema(getLong(ars_rs, "ID_LIBRO_ANT_SISTEMA"));
		ldas_datos.setTomo(getLong(ars_rs, "TOMO"));
		ldas_datos.setFolio(getLong(ars_rs, "FOLIO"));
		ldas_datos.setPartida(ars_rs.getString("PARTIDA"));
		ldas_datos.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		ldas_datos.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		ldas_datos.setNombrePredio(ars_rs.getString("NOMBRE_PREDIO"));
		ldas_datos.setAnio(getLong(ars_rs, "ANIO"));
		ldas_datos.setComentario(ars_rs.getString("COMENTARIO"));
		ldas_datos.setIpCreacion(ars_rs.getString("IP_CREACION"));
		ldas_datos.setIdUsuarioModificacion(ars_rs.getString("ID_USUARIO_MODIFICACION"));
		ldas_datos.setFechaModificacion(ars_rs.getTimestamp("FECHA_MODIFICACION"));
		ldas_datos.setIpModificacion(ars_rs.getString("IP_MODIFICACION"));
		ldas_datos.setConsecutivoPredioAntSistema(ars_rs.getString("CONSECUTIVO_PREDIO_ANT_SISTEMA"));
		ldas_datos.setIdSolicitud(ars_rs.getString("ID_SOLICITUD"));
		ldas_datos.setCantidadCertificados(getLong(ars_rs, "CANTIDAD_CERTIFICADOS"));
		ldas_datos.setIdActo(ars_rs.getString("ID_ACTO"));
		ldas_datos.setIdTurnoCertificado(ars_rs.getString("ID_TURNO_CERTIFICADO"));
		ldas_datos.setRevisadoAntSistema(ars_rs.getString("REVISADO_ANT_SISTEMA"));
		ldas_datos.setAccion(ars_rs.getString("ACCION"));
		ldas_datos.setIdCirculoGrabacion(ars_rs.getString("ID_CIRCULO_GRABACION"));
		ldas_datos.setIdMatriculaGrabacion(getLong(ars_rs, "ID_MATRICULA_GRABACION"));
		ldas_datos.setRequiereFirmaLibro(ars_rs.getString("REQUIERE_FIRMA_LIBRO"));

		return ldas_datos;
	}
}
