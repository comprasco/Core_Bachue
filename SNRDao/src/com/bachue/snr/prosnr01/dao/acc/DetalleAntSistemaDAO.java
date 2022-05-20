package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.model.sdb.acc.DatosAntSistema;
import com.bachue.snr.prosnr01.model.sdb.acc.DetalleAntSistema;
import com.bachue.snr.prosnr01.model.sdb.acc.DetalleAntSistemaUI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Collection;
import java.util.LinkedList;


/**
 * Clase dedicada al manejo de conexion y transacciones en la base de datos que afecten
 * directamente a la tabla SDB_ACC_DETALLE_ANT_SISTEMA.
 *
 * @author Manuel Blanco
 */
public class DetalleAntSistemaDAO extends BaseDAO
{
	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_ACC_DETALLE_ANT_SISTEMA (ID_DATOS_ANT_SISTEMA, ID_DETALLE_ANT_SISTEMA, ID_LIBRO_ANT_SISTEMA, TOMO, FOLIO, PARTIDA, ANIO, NUMERO_PARTIDA, ID_MUNICIPIO_TOMO, ID_DOCUMENTO_TRADICION, ID_MATRICULA, VERSION_DOCUMENTO_TRADICION, ACTIVO, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,SYSTIMESTAMP,?)";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_ACC_DETALLE_ANT_SISTEMA SET ID_LIBRO_ANT_SISTEMA=?, TOMO=?, FOLIO=?, PARTIDA=?, ANIO=?, NUMERO_PARTIDA=?, ID_MUNICIPIO_TOMO=?, ID_DOCUMENTO_TRADICION=?, ID_MATRICULA=?, VERSION_DOCUMENTO_TRADICION=?, ACTIVO=?, ID_USUARIO_MODIFICACION=?, FECHA_MODIFICACION=SYSTIMESTAMP, IP_MODIFICACION=? WHERE ID_DATOS_ANT_SISTEMA=? AND ID_DETALLE_ANT_SISTEMA=?";

	/** Constante cs_FIND_BY_DATOS_ANT_SIS. */
	private static final String cs_FIND_BY_DATOS_ANT_SIS = "SELECT DAS.ID_DETALLE_ANT_SISTEMA, DAS.ID_DATOS_ANT_SISTEMA, DAS.ID_LIBRO_ANT_SISTEMA, DAS.TOMO, DAS.FOLIO, DAS.PARTIDA, DAS.ANIO, DAS.NUMERO_PARTIDA, DAS.ID_MUNICIPIO_TOMO, DAS.ID_DOCUMENTO_TRADICION, DAS.ID_MATRICULA, DAS.VERSION_DOCUMENTO_TRADICION, DAS.ACTIVO, DAS.ID_USUARIO_CREACION, DAS.FECHA_CREACION, DAS.IP_CREACION,  DAS.ID_USUARIO_MODIFICACION, DAS.FECHA_MODIFICACION, DAS.IP_MODIFICACION, LAS.NOMBRE FROM SDB_ACC_DETALLE_ANT_SISTEMA DAS INNER JOIN SDB_PGN_LIBRO_ANT_SISTEMA LAS ON DAS.ID_LIBRO_ANT_SISTEMA = LAS.ID_LIBRO_ANT_SISTEMA WHERE ID_DATOS_ANT_SISTEMA = ? ORDER BY ID_DETALLE_ANT_SISTEMA DESC";

	/** Constante cs_DELETE_BY_ID. */
	private static final String cs_DELETE_BY_ID = "DELETE FROM SDB_ACC_DETALLE_ANT_SISTEMA WHERE ID_DETALLE_ANT_SISTEMA = ?";

	/** Constante cs_DELETE_BY_ID_DATOS_DETALLE_ANT_SIS. */
	private static final String cs_DELETE_BY_ID_DATOS_DETALLE_ANT_SIS = "DELETE FROM SDB_ACC_DETALLE_ANT_SISTEMA WHERE ID_DATOS_ANT_SISTEMA = ? AND ID_DETALLE_ANT_SISTEMA = ?";

	/** Constante cs_DELETE_BY_DATOS_ANT_SIS. */
	private static final String cs_DELETE_BY_DATOS_ANT_SIS = "DELETE FROM SDB_ACC_DETALLE_ANT_SISTEMA WHERE ID_DATOS_ANT_SISTEMA = ?";

	/** Constante cs_FIND_BY_DETALLE_Y_DATOS_ANT_SIS. */
	private static final String cs_FIND_BY_DETALLE_Y_DATOS_ANT_SIS = "SELECT ADAS.ID_DETALLE_ANT_SISTEMA, ADAS.ID_DATOS_ANT_SISTEMA, ADAS.ID_LIBRO_ANT_SISTEMA, ADAS.TOMO, ADAS.FOLIO, ADAS.PARTIDA, ADAS.ANIO, ADAS.NUMERO_PARTIDA, ADAS.ID_MUNICIPIO_TOMO, ADAS.ID_DOCUMENTO_TRADICION, ADAS.ID_MATRICULA, ADAS.VERSION_DOCUMENTO_TRADICION, ADAS.ACTIVO, ADAS.ID_USUARIO_CREACION, ADAS.FECHA_CREACION, ADAS.IP_CREACION, ADAS.ID_USUARIO_MODIFICACION, ADAS.FECHA_MODIFICACION, ADAS.IP_MODIFICACION, PLAS.NOMBRE FROM SDB_ACC_DETALLE_ANT_SISTEMA ADAS LEFT JOIN SDB_PGN_LIBRO_ANT_SISTEMA PLAS ON PLAS.ID_LIBRO_ANT_SISTEMA = ADAS.ID_LIBRO_ANT_SISTEMA WHERE ADAS.ID_DETALLE_ANT_SISTEMA = ? AND ADAS.ID_DATOS_ANT_SISTEMA = ?";

	/** Constante cs_FIND_BY_DETALLE_DATOS_ANT_SIS. */
	private static final String cs_FIND_BY_DETALLE_DATOS_ANT_SIS = "SELECT ID_DETALLE_ANT_SISTEMA, ID_DATOS_ANT_SISTEMA, ID_LIBRO_ANT_SISTEMA, TOMO, FOLIO, PARTIDA, ANIO, NUMERO_PARTIDA, ID_MUNICIPIO_TOMO, ID_DOCUMENTO_TRADICION, ID_MATRICULA, VERSION_DOCUMENTO_TRADICION, ACTIVO, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION, ID_USUARIO_MODIFICACION, FECHA_MODIFICACION, IP_MODIFICACION FROM SDB_ACC_DETALLE_ANT_SISTEMA WHERE ID_DETALLE_ANT_SISTEMA = ?";

	/** Constante cs_FIND_ALL_BY_DETALLE. */
	private static final String cs_FIND_ALL_BY_DETALLE = "SELECT DE.* FROM SDB_ACC_DETALLE_ANT_SISTEMA DE WHERE DE.ID_LIBRO_ANT_SISTEMA = ? AND DE.TOMO = ? AND DE.NUMERO_PARTIDA = ? ";

	/** Constante cs_FIND_ALL_BY_DATOS_DETALLE. */
	private static final String cs_FIND_ALL_BY_DATOS_DETALLE = "SELECT DE.* FROM SDB_ACC_DETALLE_ANT_SISTEMA DE INNER JOIN SDB_ACC_DATOS_ANT_SISTEMA DA ON DE.ID_DATOS_ANT_SISTEMA = DA.ID_DATOS_ANT_SISTEMA WHERE DE.ID_LIBRO_ANT_SISTEMA = ? AND DE.TOMO = ? AND DE.NUMERO_PARTIDA = ? ";

	/**
	 * Elimina los registros de un ID específico.
	 *
	 * @param adas_param Objeto contenedor del id a eliminar de la tabla
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void deleteByDatosAntSis(DetalleAntSistema adas_param)
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

				lps_ps = lc_connection.prepareStatement(cs_DELETE_BY_DATOS_ANT_SIS);

				lps_ps.setString(li_column++, adas_param.getIdDatosAntSistema());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "deleteByDatosAntSis", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Elimina los registros de un ID específico.
	 *
	 * @param adas_param Objeto contenedor del id a eliminar de la tabla
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void deleteById(DetalleAntSistema adas_param)
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

				lps_ps = lc_connection.prepareStatement(cs_DELETE_BY_ID);

				lps_ps.setString(li_column++, adas_param.getIdDetalleAntSistema());

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
	 * Elimina los registros de una llave compuesta específica.
	 *
	 * @param adas_param Objeto contenedor de llave compuesta a eliminar de la tabla
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void deleteByIdDetalleAntSistemaUI(DetalleAntSistemaUI adas_param)
	    throws B2BException
	{
		if(adas_param != null)
			deleteByIdDetalleAntSistemaUI(adas_param.getIdDatosAntSistema(), adas_param.getIdDetalleAntSistema());
	}

	/**
	 * Elimina los registros de una llave compuesta específica.
	 *
	 * @param as_idDatosAntSistema Objeto contenedor de llave compuesta a eliminar de la tabla
	 * @param as_idDetalleAntSistema Objeto contenedor de llave compuesta a eliminar de la tabla
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void deleteByIdDetalleAntSistemaUI(String as_idDatosAntSistema, String as_idDetalleAntSistema)
	    throws B2BException
	{
		if(StringUtils.isValidString(as_idDatosAntSistema) && StringUtils.isValidString(as_idDetalleAntSistema))
		{
			PreparedStatement lps_ps;
			Connection        lc_connection;

			lps_ps            = null;
			lc_connection     = getConnection();

			try
			{
				int li_column;
				li_column     = 1;

				lps_ps = lc_connection.prepareStatement(cs_DELETE_BY_ID_DATOS_DETALLE_ANT_SIS);

				lps_ps.setString(li_column++, as_idDatosAntSistema);
				lps_ps.setString(li_column++, as_idDetalleAntSistema);

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "deleteByIdDetalleAntSistemaUI", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Metodo que consulta todos los detalles de la tabla SDB_ACC_DETALLE_ANT_SISTEMA que cumplan con los parametros enviados.
	 *
	 * @param adas_param Objeto que contiene los datos necesarios para realizar la consulta detallada en la tabla SDB_ACC_DETALLE_ANT_SISTEMA.
	 * @return Colección de detalles de la tabla SDB_ACC_DETALLE_ANT_SISTEMA.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DetalleAntSistema
	 */
	public Collection<DetalleAntSistema> findAllByDatosDetalle(DetalleAntSistema adas_param)
	    throws B2BException
	{
		return findAllByDatosDetalle(adas_param, null);
	}

	/**
	 * Metodo que consulta todos los detalles de la tabla SDB_ACC_DETALLE_ANT_SISTEMA que cumplan con los parametros enviados.
	 *
	 * @param adas_param Objeto que contiene los datos necesarios para realizar la consulta detallada en la tabla SDB_ACC_DETALLE_ANT_SISTEMA.
	 * @param adas_datosParam Objeto que contiene los datos necesarios para realizar la consulta detallada en la tabla SDB_ACC_DETALLE_ANT_SISTEMA
	 * @return Colección de detalles de la tabla SDB_ACC_DETALLE_ANT_SISTEMA.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DetalleAntSistema
	 */
	public Collection<DetalleAntSistema> findAllByDatosDetalle(
	    DetalleAntSistema adas_param, DatosAntSistema adas_datosParam
	)
	    throws B2BException
	{
		Collection<DetalleAntSistema> ldas_object;

		ldas_object = new LinkedList<DetalleAntSistema>();

		if(adas_param != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_contador;

				String ls_idDatosAntSistema;
				String ls_idCirculo;
				String ls_idTipoPredio;
				String ls_folio;
				String ls_partida;
				String ls_municipioTomo;
				String ls_anio;

				boolean lb_idDatosAntSistema;
				boolean lb_idCirculo;
				boolean lb_idTipoPredio;
				boolean lb_folio;
				boolean lb_partida;
				boolean lb_municipioTomo;
				boolean lb_anio;

				StringBuilder lsb_sb;

				li_contador     = 1;

				ls_idDatosAntSistema     = (adas_datosParam != null) ? adas_datosParam.getIdDatosAntSistema() : null;
				ls_idCirculo             = adas_param.getIdCirculo();
				ls_idTipoPredio          = adas_param.getTipoPredio();
				ls_folio                 = adas_param.getFolio();
				ls_partida               = adas_param.getPartida();
				ls_municipioTomo         = adas_param.getIdMunicipioTomo();
				ls_anio                  = adas_param.getAnio();

				lb_idDatosAntSistema     = StringUtils.isValidString(ls_idDatosAntSistema);
				lb_idCirculo             = StringUtils.isValidString(ls_idCirculo);
				lb_idTipoPredio          = StringUtils.isValidString(ls_idTipoPredio);
				lb_folio                 = StringUtils.isValidString(ls_folio);
				lb_partida               = StringUtils.isValidString(ls_partida);
				lb_municipioTomo         = StringUtils.isValidString(ls_municipioTomo);
				lb_anio                  = StringUtils.isValidString(ls_anio);

				lsb_sb = new StringBuilder(
					    (lb_idCirculo || lb_idTipoPredio || lb_idDatosAntSistema) ? cs_FIND_ALL_BY_DATOS_DETALLE
					                                                              : cs_FIND_ALL_BY_DETALLE
					);

				if(lb_idCirculo)
					lsb_sb.append(" AND DA.ID_CIRCULO = ? ");

				if(lb_idTipoPredio)
					lsb_sb.append(" AND DA.ID_TIPO_PREDIO = ? ");

				if(lb_idDatosAntSistema)
					lsb_sb.append(" AND DA.ID_DATOS_ANT_SISTEMA = ? ");

				if(lb_folio)
					lsb_sb.append(" AND DE.FOLIO = ? ");

				if(lb_partida)
					lsb_sb.append(" AND DE.PARTIDA = ? ");

				if(lb_municipioTomo)
					lsb_sb.append(" AND DE.ID_MUNICIPIO_TOMO = ? ");

				if(lb_anio)
					lsb_sb.append(" AND DE.ANIO = ? ");

				lps_ps = getConnection().prepareStatement(lsb_sb.toString());

				setLong(lps_ps, adas_param.getIdLibroAntSistema(), li_contador++);
				lps_ps.setString(li_contador++, adas_param.getTomo());
				setLong(lps_ps, adas_param.getNumeroPartida(), li_contador++);

				if(lb_idCirculo)
					lps_ps.setString(li_contador++, ls_idCirculo);

				if(lb_idTipoPredio)
					lps_ps.setString(li_contador++, ls_idTipoPredio);

				if(lb_idDatosAntSistema)
					lps_ps.setString(li_contador++, ls_idDatosAntSistema);

				if(lb_folio)
					lps_ps.setString(li_contador++, ls_folio);

				if(lb_partida)
					lps_ps.setString(li_contador++, ls_partida);

				if(lb_municipioTomo)
					lps_ps.setString(li_contador++, ls_municipioTomo);

				if(lb_anio)
					lps_ps.setString(li_contador++, ls_anio);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					ldas_object.add(getObjetFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findAllByDatosDetalle", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(ldas_object.isEmpty())
			ldas_object = null;

		return ldas_object;
	}

	/**
	 * Consulta todos los registros relacionados con un id de datos antiguo sistema.
	 *
	 * @param adas_param correspondiente al valor del tipo de objeto DetalleAntSistema
	 * @return Colección con todos los registros resultantes de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DetalleAntSistema
	 */
	public Collection<DetalleAntSistema> findByDatosAntSis(DetalleAntSistema adas_param)
	    throws B2BException
	{
		return (adas_param != null) ? findByDatosAntSis(adas_param.getIdDatosAntSistema()) : null;
	}

	/**
	 * Consulta todos los registros relacionados con un id de datos antiguo sistema.
	 *
	 * @param as_idDatosAntSis correspondiente al valor del tipo de objeto String
	 * @return Colección con todos los registros resultantes de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DetalleAntSistema
	 */
	public Collection<DetalleAntSistema> findByDatosAntSis(String as_idDatosAntSis)
	    throws B2BException
	{
		Collection<DetalleAntSistema> lcdas_object;

		lcdas_object = new LinkedList<DetalleAntSistema>();

		if(StringUtils.isValidString(as_idDatosAntSis))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_DATOS_ANT_SIS);

				lps_ps.setString(1, as_idDatosAntSis);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcdas_object.add(getObjetFromResultSet(lrs_rs, true));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByDatosAntSis", lse_e);

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
	 * Consulta todos los registros relacionados con un id detalle datos antiguo sistema.
	 *
	 * @param adas_param Objeto contenedor de el id detalle datos ant sistema que será utilizado
	 * como filtro en la consulta
	 * @return Colección con todos los registros resultantes de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DetalleAntSistema
	 */
	public Collection<DetalleAntSistema> findByDetalleDatosAntSis(DetalleAntSistema adas_param)
	    throws B2BException
	{
		Collection<DetalleAntSistema> ldas_object;
		PreparedStatement             lps_ps;
		ResultSet                     lrs_rs;

		ldas_object     = new LinkedList<DetalleAntSistema>();
		lps_ps          = null;
		lrs_rs          = null;

		if(adas_param != null)
		{
			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_DETALLE_DATOS_ANT_SIS);

				lps_ps.setString(1, adas_param.getIdDetalleAntSistema());

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					ldas_object.add(getObjetFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByDetalleDatosAntSis", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(ldas_object.isEmpty())
			ldas_object = null;

		return ldas_object;
	}

	/**
	 * Consulta el registros relacionados con un id detalle datos antiguo sistema y id datos ant sistema.
	 * Método sobrecargado
	 *
	 * @param adas_param Objeto contenedor de el id detalle datos ant sistema y id datos ant sistema que serán utilizados
	 * como filtro en la consulta
	 * @return Objeto que contiene el registros resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DetalleAntSistema
	 */
	public DetalleAntSistema findByDetalleYDatosAntSis(DetalleAntSistema adas_param)
	    throws B2BException
	{
		DetalleAntSistema ldas_return;

		ldas_return = null;

		if(adas_param != null)
			ldas_return = findByDetalleYDatosAntSis(
				    adas_param.getIdDetalleAntSistema(), adas_param.getIdDatosAntSistema()
				);

		return ldas_return;
	}

	/**
	 * Consulta el registros relacionados con un id detalle datos antiguo sistema y id datos ant sistema.
	 *
	 * @param as_idDetalleAntSistema Objeto contenedor de el id detalle datos ant sistema
	 * @param as_idDatosAntSistema Objeto contenedor de el id datos ant sistema que serán utilizados como filtro en la consulta
	 * @return Objeto que contiene el registros resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DetalleAntSistema
	 */
	public DetalleAntSistema findByDetalleYDatosAntSis(String as_idDetalleAntSistema, String as_idDatosAntSistema)
	    throws B2BException
	{
		DetalleAntSistema ldas_object;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		ldas_object     = null;
		lps_ps          = null;
		lrs_rs          = null;

		if(StringUtils.isValidString(as_idDetalleAntSistema) && StringUtils.isValidString(as_idDatosAntSistema))
		{
			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_FIND_BY_DETALLE_Y_DATOS_ANT_SIS);

				lps_ps.setString(li_column++, as_idDetalleAntSistema);
				lps_ps.setString(li_column++, as_idDatosAntSistema);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ldas_object = getObjetFromResultSet(lrs_rs, true);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByDetalleYDatosAntSis", lse_e);

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
	 * Busca en base de datos si existen detalles con exactamente la misma información que la que se envía
	 * como parametro.
	 *
	 * @param adas_param Objeto contenedor de la información a utilizar como filtro en la busqueda
	 * @return Colección de detalle ant sistema con los resultados de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DetalleAntSistema
	 */
	public Collection<DetalleAntSistema> findDetalleRegistro(DetalleAntSistema adas_param)
	    throws B2BException
	{
		Collection<DetalleAntSistema> lcdas_object;

		lcdas_object = new LinkedList<DetalleAntSistema>();

		if(adas_param != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				StringBuilder lsb_query;
				boolean       lb_libroMatriculas;
				Long          ll_matricula;
				int           li_cont;

				lsb_query              = new StringBuilder();
				lb_libroMatriculas     = false;
				ll_matricula           = adas_param.getIdMatricula();
				li_cont                = 1;

				lb_libroMatriculas = NumericUtils.isValidLong(ll_matricula, 1);

				lsb_query.append(cs_FIND_ALL_BY_DETALLE);
				lsb_query.append(" AND DE.ID_MUNICIPIO_TOMO = ? AND DE.FOLIO = ? ");

				if(lb_libroMatriculas)
					lsb_query.append(" AND DE.ID_MATRICULA = ? ");
				else
					lsb_query.append(" AND DE.PARTIDA = ? AND DE.ANIO = ? ");

				lps_ps = getConnection().prepareStatement(lsb_query.toString());

				setLong(lps_ps, adas_param.getIdLibroAntSistema(), li_cont++);
				lps_ps.setString(li_cont++, adas_param.getTomo());
				setLong(lps_ps, adas_param.getNumeroPartida(), li_cont++);
				lps_ps.setString(li_cont++, adas_param.getIdMunicipioTomo());
				lps_ps.setString(li_cont++, adas_param.getFolio());

				if(lb_libroMatriculas)
					setLong(lps_ps, ll_matricula, li_cont++);
				else
				{
					lps_ps.setString(li_cont++, adas_param.getPartida());
					lps_ps.setString(li_cont++, adas_param.getAnio());
				}

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcdas_object.add(getObjetFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findDetalleRegistro", lse_e);

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
	 * Find one by datos ant sis.
	 *
	 * @param as_idDatosAntSis correspondiente al valor de as id datos ant sis
	 * @return el valor de detalle ant sistema
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public DetalleAntSistema findOneByDatosAntSis(String as_idDatosAntSis)
	    throws B2BException
	{
		DetalleAntSistema lcdas_object;

		lcdas_object = new DetalleAntSistema();

		if(StringUtils.isValidString(as_idDatosAntSis))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_DATOS_ANT_SIS);

				lps_ps.setString(1, as_idDatosAntSis);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcdas_object = getObjetFromResultSet(lrs_rs, true);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findOneByDatosAntSis", lse_e);

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
	 * Calcula la secuencia de un nuevo registro e inserta el registro en la tabla.
	 *
	 * @param adas_param Objeto contenedor de los datos a insertar en el registro
	 * @param ab_insert correspondiente al valor del tipo de objeto boolean
	 * @return Numero de secuencia generado para el registro insertado
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see long
	 */
	public long insertOrUpdate(DetalleAntSistema adas_param, boolean ab_insert)
	    throws B2BException
	{
		long ll_secuencia;

		ll_secuencia = 0;

		if(adas_param != null)
		{
			PreparedStatement lps_ps;

			lps_ps = null;

			try
			{
				int        li_column;
				Connection lc_connection;
				String     lb_query;

				li_column         = 1;
				lc_connection     = getConnection();
				lb_query          = ab_insert ? cs_INSERT : cs_UPDATE;

				lps_ps = lc_connection.prepareStatement(lb_query);

				if(ab_insert)
				{
					lps_ps.setString(li_column++, adas_param.getIdDatosAntSistema());
					lps_ps.setString(li_column++, adas_param.getIdDetalleAntSistema());
				}

				setLong(lps_ps, adas_param.getIdLibroAntSistema(), li_column++);
				lps_ps.setString(li_column++, adas_param.getTomo());
				lps_ps.setString(li_column++, adas_param.getFolio());
				lps_ps.setString(li_column++, adas_param.getPartida());
				lps_ps.setString(li_column++, adas_param.getAnio());
				setLong(lps_ps, adas_param.getNumeroPartida(), li_column++);
				lps_ps.setString(li_column++, adas_param.getIdMunicipioTomo());
				lps_ps.setString(li_column++, adas_param.getIdDocumentoTradicion());
				setLong(lps_ps, adas_param.getIdMatricula(), li_column++);
				lps_ps.setString(li_column++, adas_param.getVersionDocumentoTradicion());
				lps_ps.setString(li_column++, adas_param.getActivo());

				if(ab_insert)
				{
					lps_ps.setString(li_column++, adas_param.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, adas_param.getIpCreacion());
				}
				else
				{
					lps_ps.setString(li_column++, adas_param.getIdUsuarioModificacion());
					lps_ps.setString(li_column++, adas_param.getIpModificacion());

					lps_ps.setString(li_column++, adas_param.getIdDatosAntSistema());
					lps_ps.setString(li_column++, adas_param.getIdDetalleAntSistema());
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

		return ll_secuencia;
	}

	/**
	 * Extrae los resultados de la consulta y las retorna en un objeto detalle ant sistema.
	 *
	 * @param ars_rs Objeto contenedor de los resultados de la consulta
	 * @return Detalle ant sistema resultante de la consulta
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private DetalleAntSistema getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		return getObjetFromResultSet(ars_rs, false);
	}

	/**
	 * Extrae los resultados de la consulta y las retorna en un objeto detalle ant sistema.
	 *
	 * @param ars_rs Objeto contenedor de los resultados de la consulta
	 * @param ab_nombreLibro true para traer el nombre del libro asociado al detalle
	 * @return Detalle ant sistema resultante de la consulta
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private DetalleAntSistema getObjetFromResultSet(ResultSet ars_rs, boolean ab_nombreLibro)
	    throws SQLException
	{
		DetalleAntSistema ldas_datos;

		ldas_datos = new DetalleAntSistema();

		ldas_datos.setIdDetalleAntSistema(ars_rs.getString("ID_DETALLE_ANT_SISTEMA"));
		ldas_datos.setIdDatosAntSistema(ars_rs.getString("ID_DATOS_ANT_SISTEMA"));
		ldas_datos.setIdLibroAntSistema(getLong(ars_rs, "ID_LIBRO_ANT_SISTEMA"));
		ldas_datos.setTomo(ars_rs.getString("TOMO"));
		ldas_datos.setFolio(ars_rs.getString("FOLIO"));
		ldas_datos.setPartida(ars_rs.getString("PARTIDA"));
		ldas_datos.setAnio(ars_rs.getString("ANIO"));
		ldas_datos.setNumeroPartida(getLong(ars_rs, "NUMERO_PARTIDA"));
		ldas_datos.setIdMunicipioTomo(ars_rs.getString("ID_MUNICIPIO_TOMO"));
		ldas_datos.setIdDocumentoTradicion(ars_rs.getString("ID_DOCUMENTO_TRADICION"));
		ldas_datos.setIdMatricula(getLong(ars_rs, "ID_MATRICULA"));
		ldas_datos.setVersionDocumentoTradicion(ars_rs.getString("VERSION_DOCUMENTO_TRADICION"));
		ldas_datos.setActivo(ars_rs.getString("ACTIVO"));

		ldas_datos.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		ldas_datos.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		ldas_datos.setIpCreacion(ars_rs.getString("IP_CREACION"));
		ldas_datos.setIdUsuarioModificacion(ars_rs.getString("ID_USUARIO_MODIFICACION"));
		ldas_datos.setFechaModificacion(ars_rs.getTimestamp("FECHA_MODIFICACION"));
		ldas_datos.setIpModificacion(ars_rs.getString("IP_MODIFICACION"));

		if(ab_nombreLibro)
			ldas_datos.setNombreLibro(ars_rs.getString("NOMBRE"));

		return ldas_datos;
	}
}
