package com.bachue.snr.prosnr01.dao.pgn;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.DateUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.model.sdb.pgn.CirculoRegistral;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase de acceso a  datos para la tabla SDB_PGN_CIRCULO_REGISTRAL.
 *
 * @author Julian Vaca
 */
public class CirculoRegistralDao extends OficinaOrigenDAO
{
	/** Constante cs_FIND_BY_ID_SOLICITUD_ACTO. */
	private static final String cs_FIND_BY_ID_SOLICITUD_ACTO = "SELECT DISTINCT CR.* FROM SDB_PGN_CIRCULO_REGISTRAL CR INNER JOIN SDB_ACC_ACTO AA ON (CR.ID_CIRCULO = AA.ID_CIRCULO) WHERE AA.ID_SOLICITUD = ? AND CR.ACTIVO = 'S'";

	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT ID_CIRCULO, NOMBRE, NIT, COBRA_IMPUESTO, ULTIMO_ID_MATRICULA, ID_OFICINA_ORIGEN, "
		+ "VERSION, FECHA_PRODUCCION, SISTEMA_ORIGEN, ACTIVO, FECHA_PRODUCCION_BACHUE, ID_USUARIO_CREACION, FECHA_CREACION, TOPE_MAXIMO_REPARTO,"
		+ "TIPO_OFICINA, ID_REGIONAL, IP_CREACION, ID_USUARIO_MODIFICACION, FECHA_MODIFICACION, IP_MODIFICACION, HORARIO_ATENCION FROM SDB_PGN_CIRCULO_REGISTRAL WHERE ID_CIRCULO = ?";

	/** Constante cs_FIND_ALL_ORIPS. */
	private static final String cs_FIND_ALL_ORIPS = "SELECT CR.ID_CIRCULO AS CODIGO_ORIP, CR.NOMBRE AS NOMBRE_ORIP, OO.ID_OFICINA_ORIGEN AS CODIGO_OFICINA_ORIGEN, P.ID_PAIS AS CODIGO_PAIS, "
		+ "P.NOMBRE AS NOMBRE_PAIS, D.ID_DEPARTAMENTO AS CODIGO_DEPARTAMENTO, D.NOMBRE AS NOMBRE_DEPARTAMENTO, M.ID_MUNICIPIO AS CODIGO_MUNICIPIO, M.NOMBRE AS NOMBRE_MUNICIPIO "
		+ "FROM  SDB_PGN_CIRCULO_REGISTRAL CR INNER JOIN SDB_PGN_OFICINA_ORIGEN OO ON OO.ID_OFICINA_ORIGEN = CR.ID_OFICINA_ORIGEN "
		+ "INNER JOIN SDB_PGN_PAIS P ON P.ID_PAIS = OO.ID_PAIS "
		+ "INNER JOIN SDB_PGN_DEPARTAMENTO D ON D.ID_DEPARTAMENTO = OO.ID_DEPARTAMENTO AND D.ID_PAIS = P.ID_PAIS "
		+ "INNER JOIN SDB_PGN_MUNICIPIO M ON M.ID_MUNICIPIO = OO.ID_MUNICIPIO AND M.ID_DEPARTAMENTO = D.ID_DEPARTAMENTO AND M.ID_PAIS = P.ID_PAIS WHERE CR.ACTIVO = 'S'";

	/** Constante cs_FIND_BY_ID_REGIONAL. */
	private static final String cs_FIND_BY_ID_REGIONAL = "SELECT PCR.ID_CIRCULO, PCR.NOMBRE,  AD.ORDEN, PCR.NIT, PCR.COBRA_IMPUESTO, PCR.ULTIMO_ID_MATRICULA, PCR.ID_OFICINA_ORIGEN, PCR.VERSION, PCR.FECHA_PRODUCCION, PCR.SISTEMA_ORIGEN, PCR.ACTIVO, PCR.FECHA_PRODUCCION_BACHUE, PCR.ID_USUARIO_CREACION, PCR.FECHA_CREACION, PCR.TOPE_MAXIMO_REPARTO, PCR.TIPO_OFICINA, PCR.ID_REGIONAL, PCR.IP_CREACION, PCR.ID_USUARIO_MODIFICACION, PCR.FECHA_MODIFICACION, PCR.IP_MODIFICACION "
		+ "FROM SDB_PGN_CIRCULO_REGISTRAL PCR LEFT OUTER JOIN SDB_ACC_DESBORDE AD ON PCR.ID_CIRCULO = AD.ID_CIRCULO_DESBORDE AND AD.ID_CIRCULO_ORIGEN = ? "
		+ "WHERE PCR.ID_REGIONAL = ? AND PCR.ID_CIRCULO <> ? AND PCR.ACTIVO = 'S' ORDER BY PCR.NOMBRE ASC";

	/** Constante cs_FIND_BY_TURNO_AND_ETAPA. */
	private static final String cs_FIND_BY_TURNO_AND_ETAPA = "SELECT * FROM SDB_PGN_CIRCULO_REGISTRAL WHERE ID_REGIONAL = (SELECT ID_REGIONAL FROM SDB_PGN_CIRCULO_REGISTRAL WHERE ID_CIRCULO = (SELECT ID_CIRCULO_USUARIO FROM SDB_ACC_TURNO_HISTORIA WHERE ID_TURNO = ? AND ID_ETAPA = ? AND ESTADO_ACTIVIDAD = 'ASG')) AND ID_CIRCULO != (SELECT ID_CIRCULO FROM SDB_PGN_CIRCULO_REGISTRAL WHERE ID_CIRCULO = (SELECT ID_CIRCULO_USUARIO FROM SDB_ACC_TURNO_HISTORIA WHERE ID_TURNO = ? AND ID_ETAPA = ? AND ESTADO_ACTIVIDAD = 'ASG'))";

	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT ID_CIRCULO, NOMBRE, NIT, COBRA_IMPUESTO, ULTIMO_ID_MATRICULA, ID_OFICINA_ORIGEN, "
		+ "VERSION, FECHA_PRODUCCION, SISTEMA_ORIGEN, ACTIVO, FECHA_PRODUCCION_BACHUE, ID_USUARIO_CREACION, FECHA_CREACION, TOPE_MAXIMO_REPARTO,"
		+ "TIPO_OFICINA, ID_REGIONAL, IP_CREACION, ID_USUARIO_MODIFICACION, FECHA_MODIFICACION, IP_MODIFICACION, HORARIO_ATENCION FROM SDB_PGN_CIRCULO_REGISTRAL ORDER BY NOMBRE ASC ";

	/** Constante cs_FIND_ALL_ACTIVO. */
	private static final String cs_FIND_ALL_ACTIVO = "SELECT ID_CIRCULO, NOMBRE, NIT, COBRA_IMPUESTO, ULTIMO_ID_MATRICULA, ID_OFICINA_ORIGEN, "
		+ "VERSION, FECHA_PRODUCCION, SISTEMA_ORIGEN, ACTIVO, FECHA_PRODUCCION_BACHUE, ID_USUARIO_CREACION, FECHA_CREACION, TOPE_MAXIMO_REPARTO,"
		+ "TIPO_OFICINA, ID_REGIONAL, IP_CREACION, ID_USUARIO_MODIFICACION, FECHA_MODIFICACION, IP_MODIFICACION, HORARIO_ATENCION FROM SDB_PGN_CIRCULO_REGISTRAL WHERE ACTIVO='S' ORDER BY NOMBRE ASC ";

	/** Constante cs_BUSCAR_TODO_ORIGEN_DESTINO_ACTIVO. */
	private static final String cs_BUSCAR_TODO_ORIGEN_DESTINO_ACTIVO = "SELECT CR.ID_CIRCULO, CR.NOMBRE, CR.NIT, CR.COBRA_IMPUESTO, CR.ULTIMO_ID_MATRICULA, CR.ID_OFICINA_ORIGEN, "
		+ " CR.VERSION, CR.FECHA_PRODUCCION, CR.SISTEMA_ORIGEN, CR.ACTIVO, CR.FECHA_PRODUCCION_BACHUE, CR.ID_USUARIO_CREACION, CR.FECHA_CREACION, CR.TOPE_MAXIMO_REPARTO,"
		+ " CR.TIPO_OFICINA, CR.ID_REGIONAL, CR.IP_CREACION, CR.ID_USUARIO_MODIFICACION, CR.FECHA_MODIFICACION, CR.IP_MODIFICACION, CR.HORARIO_ATENCION FROM SDB_PGN_CIRCULO_REGISTRAL CR";

	/** Constante cs_FIND_ALL_ACTIVO_ID. */
	private static final String cs_FIND_ALL_ACTIVO_ID = "SELECT ID_CIRCULO, NOMBRE, NIT, COBRA_IMPUESTO, ULTIMO_ID_MATRICULA, ID_OFICINA_ORIGEN, "
		+ "VERSION, FECHA_PRODUCCION, SISTEMA_ORIGEN, ACTIVO, FECHA_PRODUCCION_BACHUE, ID_USUARIO_CREACION, FECHA_CREACION, TOPE_MAXIMO_REPARTO,"
		+ "TIPO_OFICINA, ID_REGIONAL, IP_CREACION, ID_USUARIO_MODIFICACION, FECHA_MODIFICACION, IP_MODIFICACION, HORARIO_ATENCION FROM SDB_PGN_CIRCULO_REGISTRAL "
		+ "WHERE ACTIVO='S' ORDER BY ID_CIRCULO ASC ";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_PGN_CIRCULO_REGISTRAL (ID_CIRCULO,NOMBRE,NIT,COBRA_IMPUESTO,ULTIMO_ID_MATRICULA,ID_OFICINA_ORIGEN,VERSION,FECHA_PRODUCCION,SISTEMA_ORIGEN,ACTIVO,FECHA_PRODUCCION_BACHUE,ID_REGIONAL,TIPO_OFICINA,TOPE_MAXIMO_REPARTO,ID_USUARIO_CREACION,IP_CREACION,FECHA_CREACION) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,SYSTIMESTAMP)";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_PGN_CIRCULO_REGISTRAL SET NOMBRE=?, NIT=?, COBRA_IMPUESTO=?, ULTIMO_ID_MATRICULA=?, ID_OFICINA_ORIGEN=?, VERSION=?, FECHA_PRODUCCION=?, SISTEMA_ORIGEN=?, ACTIVO=?, FECHA_PRODUCCION_BACHUE=?, ID_REGIONAL=?, TIPO_OFICINA=?, TOPE_MAXIMO_REPARTO=?, ID_USUARIO_MODIFICACION=?, FECHA_MODIFICACION=SYSTIMESTAMP, IP_MODIFICACION=? WHERE ID_CIRCULO=?";

	/** Constante cs_FIND_BY_ID_REGIONAL_ONLY. */
	private static final String cs_FIND_BY_ID_REGIONAL_ONLY = "SELECT * FROM SDB_PGN_CIRCULO_REGISTRAL WHERE ID_REGIONAL = ? ORDER BY NOMBRE ASC";

	/** Constante cs_FIND_ALL_ACTIVO_BY_USUARIO. */
	private static final String cs_FIND_ALL_ACTIVO_BY_USUARIO = "SELECT CR.* FROM SDB_PGN_CIRCULO_REGISTRAL CR INNER JOIN SDB_AUT_USUARIO_CIRCULO UC ON CR.ID_CIRCULO = UC.ID_CIRCULO WHERE UC.ID_USUARIO = ? AND CR.ACTIVO = 'S' ORDER BY CR.ID_CIRCULO ASC";

	/** Constante cs_FIND_CIRCULOS_DESBORDE_BY_CIRCULO_ORIGEN. */
	private static final String cs_FIND_CIRCULOS_DESBORDE_BY_CIRCULO_ORIGEN = "SELECT CR.* FROM SDB_PGN_CIRCULO_REGISTRAL CR INNER JOIN SDB_ACC_DESBORDE DES ON CR.ID_CIRCULO = DES.ID_CIRCULO_DESBORDE WHERE DES.ID_CIRCULO_ORIGEN = ? ORDER BY ID_CIRCULO ASC";

	/**
	 * Metodo para la Busqueda de todos los circulos registrales origen destino que se encuentren en estado activo.
	 *
	 * @return devuelve el valor del objeto collection de CirculoRegistral
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<CirculoRegistral> buscarTodosCirculosRegistralesOrigenDestinoActivos(
	    boolean ab_circuloOrigen, String as_idCirculoOrigen
	)
	    throws B2BException
	{
		Collection<CirculoRegistral> lccr_ccr;
		PreparedStatement            lps_ps;
		ResultSet                    lrs_rs;

		lccr_ccr     = new ArrayList<CirculoRegistral>();
		lps_ps       = null;
		lrs_rs       = null;

		try
		{
			StringBuilder lsb_query;

			lsb_query = new StringBuilder(cs_BUSCAR_TODO_ORIGEN_DESTINO_ACTIVO);

			lsb_query.append(
			    " INNER JOIN (SELECT"
			    + (ab_circuloOrigen ? " DISTINCT(ID_CIRCULO_ORIGEN) AS ID_CIRCULO_ORIGEN " : " * ")
			);
			lsb_query.append("  FROM SDB_PNG_CIRCULO_ORIGEN_DESTINO WHERE NVL(ACTIVO,'S') ='S') COD ON  ");
			lsb_query.append(
			    " ( CR.ID_CIRCULO = COD." + (ab_circuloOrigen ? "ID_CIRCULO_ORIGEN" : "ID_CIRCULO_DESTINO")
			    + " ) WHERE NVL(CR.ACTIVO,'S') ='S' "
			);

			if(StringUtils.isValidString(as_idCirculoOrigen) && !ab_circuloOrigen)
				lsb_query.append(" AND COD.ID_CIRCULO_ORIGEN = ? ");

			lsb_query.append(" ORDER BY CR.ID_CIRCULO ASC ");

			lps_ps = getConnection().prepareStatement(lsb_query.toString());

			if(StringUtils.isValidString(as_idCirculoOrigen) && !ab_circuloOrigen)
				lps_ps.setString(1, as_idCirculoOrigen);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lccr_ccr.add(getCirculoRegistral(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "buscarTodosCirculosRegistralesOrigenDestinoActivos", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(!CollectionUtils.isValidCollection(lccr_ccr))
			lccr_ccr = null;

		return lccr_ccr;
	}

	/**
	 * Metodo para la Busqueda de todos los circulos registrales que se encuentren en estado activo.
	 *
	 * @param ab_b correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor del objeto collection de CirculoRegistral
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<CirculoRegistral> findAllActivo(boolean ab_b)
	    throws B2BException
	{
		Collection<CirculoRegistral> lccr_ccr;
		PreparedStatement            lps_ps;
		ResultSet                    lrs_rs;

		lccr_ccr     = new ArrayList<CirculoRegistral>();
		lps_ps       = null;
		lrs_rs       = null;

		try
		{
			if(ab_b)
				lps_ps = getConnection().prepareStatement(cs_FIND_ALL_ACTIVO);
			else
				lps_ps = getConnection().prepareStatement(cs_FIND_ALL_ACTIVO_ID);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lccr_ccr.add(getCirculoRegistral(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findAllActivo", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(!CollectionUtils.isValidCollection(lccr_ccr))
			lccr_ccr = null;

		return lccr_ccr;
	}

	/**
	 * Find all activo by usuario.
	 *
	 * @param as_idUsuario de as id usuario
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<CirculoRegistral> findAllActivoByUsuario(String as_idUsuario)
	    throws B2BException
	{
		Collection<CirculoRegistral> lccr_ccr;

		lccr_ccr = new ArrayList<CirculoRegistral>();

		if(StringUtils.isValidString(as_idUsuario))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_cont;
				li_cont     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_ALL_ACTIVO_BY_USUARIO);

				lps_ps.setString(li_cont++, as_idUsuario);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lccr_ccr.add(getCirculoRegistral(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findAllActivoByUsuario", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(!CollectionUtils.isValidCollection(lccr_ccr))
			lccr_ccr = null;

		return lccr_ccr;
	}

	/**
	 * Metodo para encontrar todos los registros existentes en la tabla SDB_PGN_CIRCULO_REGISTRAL.
	 *
	 * @return devuelve el valor del objeto collection de CirculoRegistral
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<CirculoRegistral> findAllCR()
	    throws B2BException
	{
		Collection<CirculoRegistral> lccr_ccr;
		PreparedStatement            lps_ps;
		ResultSet                    lrs_rs;

		lccr_ccr     = new ArrayList<CirculoRegistral>();
		lps_ps       = null;
		lrs_rs       = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lccr_ccr.add(getCirculoRegistral(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findAllCR", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(!CollectionUtils.isValidCollection(lccr_ccr))
			lccr_ccr = null;

		return lccr_ccr;
	}

	/**
	 * Metodo para la Busqueda de todos los circulos registrales que se encuentren en estado activo.
	 *
	 * @return devuelve el valor del objeto collection de CirculoRegistral
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<CirculoRegistral> findAllOrips()
	    throws B2BException
	{
		Collection<CirculoRegistral> lccr_ccr;
		PreparedStatement            lps_ps;
		ResultSet                    lrs_rs;

		lccr_ccr     = new ArrayList<CirculoRegistral>();
		lps_ps       = null;
		lrs_rs       = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL_ORIPS);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lccr_ccr.add(getOrips(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findAllOrips", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(!CollectionUtils.isValidCollection(lccr_ccr))
			lccr_ccr = null;

		return lccr_ccr;
	}

	/**
	 * Metodo para la Busqueda de  registros en la tabla SDB_PGN_CIRCULO_REGISTRAL que
	 * coincidan con un id_circulo especifico.
	 *
	 * @param acr_param correspondiente al valor del tipo de objeto CirculoRegistral
	 * @return Objeto círculo registral resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public CirculoRegistral findById(CirculoRegistral acr_param)
	    throws B2BException
	{
		return (acr_param != null) ? findById(acr_param.getIdCirculo()) : null;
	}

	/**
	 * Metodo para la Busqueda de  registros en la tabla SDB_PGN_CIRCULO_REGISTRAL que
	 * coincidan con un id_circulo especifico.
	 *
	 * @param as_idCirculo id del círculo a consultar
	 * @return Objeto círculo registral resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public CirculoRegistral findById(String as_idCirculo)
	    throws B2BException
	{
		CirculoRegistral lcr_cr;

		lcr_cr = null;

		if(StringUtils.isValidString(as_idCirculo))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(1, as_idCirculo);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lcr_cr = getCirculoRegistral(lrs_rs);
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

		return lcr_cr;
	}

	/**
	 * metodo para encontrar todos los registros que coincidan
	 * con un id_regional y un id_circulo especifico.
	 *
	 * @param acr_param correspondiente al valor del tipo de objeto CirculoRegistral
	 * @return devuelve el valor del objeto collection
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<CirculoRegistral> findByIdRegional(CirculoRegistral acr_param)
	    throws B2BException
	{
		Collection<CirculoRegistral> lccr_ccr;

		lccr_ccr = new ArrayList<CirculoRegistral>();

		if(acr_param != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_cont;
				li_cont     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_REGIONAL);

				lps_ps.setString(li_cont++, acr_param.getIdCirculo());
				lps_ps.setString(li_cont++, acr_param.getIdRegional());
				lps_ps.setString(li_cont++, acr_param.getIdCirculo());

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lccr_ccr.add(getCirculoRegistralDesbordes(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdRegional", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(!CollectionUtils.isValidCollection(lccr_ccr))
			lccr_ccr = null;

		return lccr_ccr;
	}

	/**
	 * Metodo encargado de consultar todos los circulos con un ID_REGIONAL enviado como criterio de consulta.
	 *
	 * @param acr_param Argumento de tipo CirculoRegistral que contiene los criterios necesarios para realizar la consulta.
	 * @return Colección de CirculoRegistral que contiene los resultados que cumplieron con los criterios de búsqueda.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<CirculoRegistral> findByIdRegionalOnly(CirculoRegistral acr_param)
	    throws B2BException
	{
		Collection<CirculoRegistral> lccr_ccr;

		lccr_ccr = new ArrayList<CirculoRegistral>();

		if(acr_param != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_REGIONAL_ONLY);

				lps_ps.setString(1, acr_param.getIdRegional());

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lccr_ccr.add(getCirculoRegistral(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdRegionalOnly", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lccr_ccr.isEmpty())
			lccr_ccr = null;

		return lccr_ccr;
	}

	/**
	 * Metodo para la Busqueda de todos los registros existentes en la tabla SDB_PGN_CIRCULO_REGISTRAL que coincidadn
	 * con un id_solicitud especifico.
	 *
	 * @param as_idSolicitud id_solicitud para consultar en la tabla SDB_PGN_CIRCULO_REGISTRAL
	 * @return devuelve el valor del objeto collection
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<CirculoRegistral> findByIdSolicitudActo(String as_idSolicitud)
	    throws B2BException
	{
		Collection<CirculoRegistral> lccr_ccr;
		PreparedStatement            lps_ps;
		ResultSet                    lrs_rs;

		lccr_ccr     = new ArrayList<CirculoRegistral>();
		lps_ps       = null;
		lrs_rs       = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_SOLICITUD_ACTO);

			lps_ps.setString(1, as_idSolicitud);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lccr_ccr.add(getCirculoRegistral(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findByIdSolicitudActo", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(!CollectionUtils.isValidCollection(lccr_ccr))
			lccr_ccr = null;

		return lccr_ccr;
	}

	/**
	 * Find by turno and etapa.
	 *
	 * @param acs_param correspondiente al valor de acs param
	 * @param acs_param1 correspondiente al valor de acs param 1
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<CirculoRegistral> findByTurnoEtapa(String acs_param, String acs_param1)
	    throws B2BException
	{
		Collection<CirculoRegistral> lccr_ccr;

		lccr_ccr = new ArrayList<CirculoRegistral>(1);

		if((acs_param != null) && (acs_param1 != null))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_TURNO_AND_ETAPA);

				lps_ps.setString(1, acs_param);
				lps_ps.setString(2, acs_param1);
				lps_ps.setString(3, acs_param);
				lps_ps.setString(4, acs_param1);
				
				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lccr_ccr.add(getCirculoRegistral(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByTurnoAndEtapa", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lccr_ccr.isEmpty())
			lccr_ccr = null;

		return lccr_ccr;
	}

	/**
	 * Find circulos desborde by circulo origen.
	 *
	 * @param as_idCirculoSelected de as id circulo selected
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<CirculoRegistral> findCirculosDesbordeByCirculoOrigen(String as_idCirculoSelected)
	    throws B2BException
	{
		Collection<CirculoRegistral> lccr_ccr;

		lccr_ccr = new ArrayList<CirculoRegistral>();

		if(StringUtils.isValidString(as_idCirculoSelected))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_CIRCULOS_DESBORDE_BY_CIRCULO_ORIGEN);

				lps_ps.setString(1, as_idCirculoSelected);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lccr_ccr.add(getCirculoRegistral(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findCirculosDesbordeByCirculoOrigen", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(!CollectionUtils.isValidCollection(lccr_ccr))
			lccr_ccr = null;

		return lccr_ccr;
	}

	/**
	 * metodo encargado de insertar o actualizar en la base de datos.
	 *
	 * @param acr_param correspondiente al valor del tipo de objeto CirculoRegistral
	 * @param ab_query valor en true inserta
	 * valor en false actualiza
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void insertOrUpdate(CirculoRegistral acr_param, boolean ab_query)
	    throws B2BException
	{
		if(acr_param != null)
		{
			int               li_cont;
			PreparedStatement lps_ps;

			li_cont     = 1;
			lps_ps      = null;

			try
			{
				lps_ps = getConnection().prepareStatement(ab_query ? cs_INSERT : cs_UPDATE);

				if(ab_query)
					lps_ps.setString(li_cont++, acr_param.getIdCirculo());

				lps_ps.setString(li_cont++, acr_param.getNombre());
				lps_ps.setString(li_cont++, acr_param.getNit());
				lps_ps.setString(li_cont++, acr_param.getCobraImpuesto());
				lps_ps.setBigDecimal(li_cont++, acr_param.getUltimoIdMatricula());
				lps_ps.setString(li_cont++, acr_param.getIdOficinaOrigen());
				lps_ps.setBigDecimal(li_cont++, acr_param.getVersion());
				lps_ps.setDate(li_cont++, DateUtils.getCleanSQLDate(acr_param.getFechaProduccion()));
				lps_ps.setString(li_cont++, acr_param.getSistemaOrigen());
				lps_ps.setString(li_cont++, acr_param.getActivo());
				lps_ps.setDate(li_cont++, DateUtils.getCleanSQLDate(acr_param.getFechaProduccionBachue()));
				lps_ps.setString(li_cont++, acr_param.getIdRegional());
				lps_ps.setString(li_cont++, acr_param.getTipoOficina());
				lps_ps.setBigDecimal(li_cont++, acr_param.getTopeRepartoCalificador());

				if(ab_query)
				{
					lps_ps.setString(li_cont++, acr_param.getIdUsuarioCreacion());
					lps_ps.setString(li_cont++, acr_param.getIpCreacion());
				}
				else
				{
					lps_ps.setString(li_cont++, acr_param.getIdUsuarioModificacion());
					lps_ps.setString(li_cont++, acr_param.getIpModificacion());
					lps_ps.setString(li_cont++, acr_param.getIdCirculo());
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
	 * Metodo de obtencion del objeto cirulo registral dependiendo el valor
	 * de la sentencia realizada.
	 *
	 * @param ars_rs objeto que representa el resultado de una sentencia sql en la base de datos
	 * @return el valor de circulo registral
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private CirculoRegistral getCirculoRegistral(ResultSet ars_rs)
	    throws SQLException
	{
		CirculoRegistral lcr_datos;
		lcr_datos = new CirculoRegistral();

		{
			String ls_idCirculo;
			String ls_nombre;

			ls_idCirculo     = ars_rs.getString("ID_CIRCULO");
			ls_nombre        = ars_rs.getString("NOMBRE");

			lcr_datos.setIdCirculo(ls_idCirculo);
			lcr_datos.setNombre(ls_nombre);
			lcr_datos.setCodigoNombre(ls_idCirculo + " - " + ls_nombre);
			lcr_datos.setNombreCirculoRegistral(ls_nombre);
		}

		lcr_datos.setNit(ars_rs.getString("NIT"));
		lcr_datos.setCobraImpuesto(ars_rs.getString("COBRA_IMPUESTO"));
		lcr_datos.setUltimoIdMatricula(ars_rs.getBigDecimal("ULTIMO_ID_MATRICULA"));
		lcr_datos.setIdOficinaOrigen(ars_rs.getString("ID_OFICINA_ORIGEN"));
		lcr_datos.setVersion(ars_rs.getBigDecimal("VERSION"));
		lcr_datos.setFechaProduccion(ars_rs.getTimestamp("FECHA_PRODUCCION"));
		lcr_datos.setSistemaOrigen(ars_rs.getString("SISTEMA_ORIGEN"));
		lcr_datos.setActivo(ars_rs.getString("ACTIVO"));
		lcr_datos.setFechaProduccionBachue(ars_rs.getTimestamp("FECHA_PRODUCCION_BACHUE"));
		lcr_datos.setTopeRepartoCalificador(ars_rs.getBigDecimal("TOPE_MAXIMO_REPARTO"));
		lcr_datos.setTipoOficina(ars_rs.getString("TIPO_OFICINA"));
		lcr_datos.setIdRegional(ars_rs.getString("ID_REGIONAL"));
		lcr_datos.setHorarioAtencion(ars_rs.getString("HORARIO_ATENCION"));

		fillAuditoria(ars_rs, lcr_datos);

		return lcr_datos;
	}

	/**
	 * Retorna el valor de circulo registral desbordes.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de circulo registral desbordes
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private CirculoRegistral getCirculoRegistralDesbordes(ResultSet ars_rs)
	    throws SQLException
	{
		CirculoRegistral ld_datos;
		ld_datos = new CirculoRegistral();

		ld_datos.setIdCirculo(ars_rs.getString("ID_CIRCULO"));
		ld_datos.setNombre(ars_rs.getString("NOMBRE"));
		ld_datos.setNit(ars_rs.getString("NIT"));
		ld_datos.setCobraImpuesto(ars_rs.getString("COBRA_IMPUESTO"));
		ld_datos.setUltimoIdMatricula(ars_rs.getBigDecimal("ULTIMO_ID_MATRICULA"));
		ld_datos.setIdOficinaOrigen(ars_rs.getString("ID_OFICINA_ORIGEN"));
		ld_datos.setVersion(ars_rs.getBigDecimal("VERSION"));
		ld_datos.setFechaProduccion(ars_rs.getTimestamp("FECHA_PRODUCCION"));
		ld_datos.setSistemaOrigen(ars_rs.getString("SISTEMA_ORIGEN"));
		ld_datos.setOrden(getLong(ars_rs, "ORDEN"));
		ld_datos.setActivo(ars_rs.getString("ACTIVO"));
		ld_datos.setFechaProduccionBachue(ars_rs.getTimestamp("FECHA_PRODUCCION_BACHUE"));
		ld_datos.setTopeRepartoCalificador(ars_rs.getBigDecimal("TOPE_MAXIMO_REPARTO"));
		ld_datos.setTipoOficina(ars_rs.getString("TIPO_OFICINA"));
		ld_datos.setIdRegional(ars_rs.getString("ID_REGIONAL"));

		fillAuditoria(ars_rs, ld_datos);

		return ld_datos;
	}

	/**
	 * Retorna el valor de CirculoRegistral.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de CirculoRegistral
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private CirculoRegistral getOrips(ResultSet ars_rs)
	    throws SQLException
	{
		CirculoRegistral lcr_datos;

		lcr_datos = new CirculoRegistral();

		lcr_datos.setIdCirculo(ars_rs.getString("CODIGO_ORIP"));
		lcr_datos.setNombreCirculoRegistral(ars_rs.getString("NOMBRE_ORIP"));

		fillOficinaOrigen(ars_rs, lcr_datos);

		return lcr_datos;
	}
}
