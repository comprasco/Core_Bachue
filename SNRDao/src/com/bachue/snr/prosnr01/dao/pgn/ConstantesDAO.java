package com.bachue.snr.prosnr01.dao.pgn;

import com.b2bsg.common.dataAccess.oracle.ClobUtils;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ConstanteCommon;

import com.bachue.snr.prosnr01.model.sdb.pgn.Constantes;

import java.io.IOException;

import java.math.BigDecimal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase de manejo de datos para la tabla SDB_PGN_CONSTANTES.
 *
 * @author Heiner Castañeda
 */
public class ConstantesDAO extends com.b2bsg.common.dataAccess2.BaseDAO
{
	/** Constante cs_COLUMNAS_SIN_IMAGENES. */
	private static final String cs_COLUMNAS_SIN_IMAGENES = "ID_CONSTANTE,CARACTER,ENTERO,REAL,FECHA,DESCRIPCION,ID_USUARIO_CREACION,FECHA_CREACION,IP_CREACION,ID_USUARIO_MODIFICACION,FECHA_MODIFICACION,IP_MODIFICACION,TIPO_ARCHIVO,ACTIVO,TEXTO";

	/** Constante cs_COLUMNAS_CON_IMAGENES. */
	private static final String cs_COLUMNAS_CON_IMAGENES = cs_COLUMNAS_SIN_IMAGENES + ",IMAGENES";

	/** Constante cs_DATOS_ACTO_MADRE_CABEZA. */
	private static final String cs_DATOS_ACTO_MADRE_CABEZA = " SELECT PC.CARACTER CODIGO, PC1.CARACTER || ' - ' ||PC2.CARACTER  DESCRIPCION "
		+ " FROM  SDB_PGN_CONSTANTES  PC ,SDB_PGN_CONSTANTES  PC1 ,SDB_PGN_CONSTANTES  PC2  WHERE PC.ID_CONSTANTE  = ?  AND PC1.ID_CONSTANTE = ? AND PC2.ID_CONSTANTE = ?";

	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT " + cs_COLUMNAS_SIN_IMAGENES
		+ " FROM SDB_PGN_CONSTANTES ORDER BY ID_CONSTANTE ASC";

	/** Constante cs_FIND_ALL_ACTIVO. */
	private static final String cs_FIND_ALL_ACTIVO = "SELECT " + cs_COLUMNAS_SIN_IMAGENES
		+ " FROM SDB_PGN_CONSTANTES WHERE ACTIVO='S' ORDER BY DESCRIPCION ASC";

	/** Constante cs_FIND_ALL_ACTIVO_ID. */
	private static final String cs_FIND_ALL_ACTIVO_ID = "SELECT " + cs_COLUMNAS_SIN_IMAGENES
		+ " FROM SDB_PGN_CONSTANTES WHERE ACTIVO='S' ORDER BY ID_CONSTANTE ASC";

	/** Constante cs_FIND_ALL_BY_ID. */
	private static final String cs_FIND_ALL_BY_ID = "SELECT " + cs_COLUMNAS_CON_IMAGENES
		+ " FROM SDB_PGN_CONSTANTES WHERE ID_CONSTANTE=?";

	/** Constante cs_FIND_ENTERO_BY_ID. */
	private static final String cs_FIND_ENTERO_BY_ID = "SELECT ENTERO FROM SDB_PGN_CONSTANTES WHERE ID_CONSTANTE=? "
		+ "AND ACTIVO='S'";

	/** Sentencia SQL para obtener la columna caracter de una constante en busqueda por llave primaria */
	private static final String cs_FIND_CARACTER_BY_ID = "SELECT CARACTER FROM SDB_PGN_CONSTANTES WHERE ID_CONSTANTE=? "
		+ " AND ACTIVO='S'";

	/** Constante cs_FIND_BY_CARACTER. */
	private static final String cs_FIND_BY_CARACTER = "SELECT " + cs_COLUMNAS_SIN_IMAGENES
		+ " FROM SDB_PGN_CONSTANTES WHERE CARACTER=?";

	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT " + cs_COLUMNAS_SIN_IMAGENES
		+ " FROM SDB_PGN_CONSTANTES WHERE ID_CONSTANTE=?";

	/** Constante cs_FIND_BY_LIKE. */
	private static final String cs_FIND_BY_LIKE = "SELECT " + cs_COLUMNAS_SIN_IMAGENES
		+ " FROM SDB_PGN_CONSTANTES WHERE ID_CONSTANTE LIKE ?";

	/** Sentencia SQL para obtener todos los ID_CONSTANTE que coincidan con un patro y el valor de la columna CARACTER
	 * concida con un valor */
	private static final String cs_FIND_ALL_IDS_BY_ID_LIKE_CARACTER = "SELECT ID_CONSTANTE FROM SDB_PGN_CONSTANTES "
		+ "WHERE ID_CONSTANTE LIKE ? AND CARACTER=? AND ACTIVO='S' ORDER BY ID_CONSTANTE";

	/** Constante cs_FIND_IMAGE_WORKFLOW. */
	private static final String cs_FIND_IMAGE_WORKFLOW = "SELECT to_clob(IMAGENES) as IMAGENES FROM SDB_PGN_CONSTANTES WHERE ID_CONSTANTE = ?";

	/** Constante cs_MOTIVOS_CONSULTA. */
	private static final String cs_MOTIVOS_CONSULTA = " SELECT ENTERO,CARACTER FROM SDB_PGN_CONSTANTES  "
		+ " WHERE  ID_CONSTANTE IN ('MOTIVO_CONSULTA_EXPEDIENTE','MOTIVO_CONSULTA_PROCESO','MOTIVO_CONSULTA_REFERENCIA')";

	/** Constante cs_FIND_IMAGE. */
	private static final String cs_FIND_IMAGE = "SELECT IMAGENES,TIPO_ARCHIVO FROM SDB_PGN_CONSTANTES WHERE ID_CONSTANTE=?";

	/** Constante cs_FIND_ALL_RTF. */
	private static final String cs_FIND_ALL_RTF = "SELECT ID_CONSTANTE,CARACTER,ENTERO,REAL,FECHA,DESCRIPCION, ID_USUARIO_CREACION,"
		+ "FECHA_CREACION,IP_CREACION,ID_USUARIO_MODIFICACION,FECHA_MODIFICACION, IP_MODIFICACION, IMAGENES, TIPO_ARCHIVO,ACTIVO,TEXTO FROM SDB_PGN_CONSTANTES WHERE TIPO_ARCHIVO IN ('.rtf','.xlsx')";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_PGN_CONSTANTES(ID_CONSTANTE,CARACTER,ENTERO,REAL,FECHA,"
		+ "DESCRIPCION,TIPO_ARCHIVO,ACTIVO,ID_USUARIO_CREACION,IP_CREACION,FECHA_CREACION,TIPO";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_PGN_CONSTANTES SET CARACTER=?,ENTERO=?,"
		+ "REAL=?,FECHA=?,DESCRIPCION=?,TIPO_ARCHIVO=?,ACTIVO=?,ID_USUARIO_MODIFICACION=?,IP_MODIFICACION=?,FECHA_MODIFICACION=SYSTIMESTAMP";

	/** Constante cs_UPDATE_STRING_CONSTANT. */
	private static final String cs_UPDATE_STRING_CONSTANT = "UPDATE SDB_PGN_CONSTANTES SET CARACTER=?,"
		+ "FECHA_MODIFICACION=SYSTIMESTAMP,ID_USUARIO_MODIFICACION=? WHERE ID_CONSTANTE=?";

	/**
	 * Instancia un nuevo objeto constantes DAO.
	 */
	public ConstantesDAO()
	{
	}

	/**
	 * Metodo para obtener todos los motivos de consulta.
	 *
	 * @return devuelve el valor del objeto collection de Constantes
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<Constantes> buscarMotivosConsulta()
	    throws B2BException
	{
		PreparedStatement      lps_ps;
		ResultSet              lrs_rs;
		Collection<Constantes> lcc_constant;

		lps_ps           = null;
		lrs_rs           = null;
		lcc_constant     = new ArrayList<Constantes>();

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_MOTIVOS_CONSULTA);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcc_constant.add(getMotivos(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "buscarMotivosConsulta", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return lcc_constant;
	}

	/**
	 * Metodo para traer todos los registros de la tabla SDB_PGN_CONSTANTES.
	 *
	 * @return devuelve el valor del objeto collection de Constantes
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<Constantes> findAll()
	    throws B2BException
	{
		Collection<Constantes> lp_pais;
		PreparedStatement      lps_ps;
		ResultSet              lrs_rs;

		lp_pais     = new ArrayList<Constantes>();
		lps_ps      = null;
		lrs_rs      = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lp_pais.add(getConstantes(lrs_rs, false));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findAll", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(lp_pais.isEmpty())
			lp_pais = null;

		return lp_pais;
	}

	/**
	 * Metodo para la Busqueda de todos las constantes que se encuentren en estado activo.
	 *
	 * @param ab_b de ab b
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Constantes> findAllActivo(boolean ab_b)
	    throws B2BException
	{
		Collection<Constantes> lcc_cc;
		PreparedStatement      lps_ps;
		ResultSet              lrs_rs;

		lcc_cc     = new ArrayList<Constantes>();
		lps_ps     = null;
		lrs_rs     = null;

		try
		{
			if(ab_b)
				lps_ps = getConnection().prepareStatement(cs_FIND_ALL_ACTIVO_ID);
			else
				lps_ps = getConnection().prepareStatement(cs_FIND_ALL_ACTIVO);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcc_cc.add(getConstantes(lrs_rs));
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

		if(!CollectionUtils.isValidCollection(lcc_cc))
			lcc_cc = null;

		return lcc_cc;
	}

	/**
	 * Obtener todos los ID_CONSTANTE que coincidan con un patrón y su el valor de la columna CARACTER concida sea
	 * igual a un valor
	 *
	 * @param as_IdLike Patrón sobre el cual se realizará búsqueda tipo like en en campo ID_CONSTANTE
	 * @param as_caracter Valor de coincidencia del campo CARACTER
	 * @return Listado de ID_CONSTANTE cuyo valor de la columna CARACTER concida con un patron
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<String> findAllIdsByIdLikeCaracter(String as_IdLike, String as_caracter)
	    throws B2BException
	{
		Collection<String> lcs_ids;
		PreparedStatement  lps_ps;
		ResultSet          lrs_rs;

		lcs_ids     = new ArrayList<String>();
		lps_ps      = null;
		lrs_rs      = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_ALL_IDS_BY_ID_LIKE_CARACTER);

			lps_ps.setString(1, as_IdLike);
			lps_ps.setString(2, as_caracter);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcs_ids.add(StringUtils.getString(lrs_rs.getString("ID_CONSTANTE")));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findAllIdsByIdLikeCaracter", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(lcs_ids.isEmpty())
			lcs_ids = null;

		return lcs_ids;
	}

	/**
	 * Metodo encargado de consultar la bd para retornar todas las constantes con formato .rtf
	 *
	 * @return devuelve el valor del objeto collection de Constantes
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<Constantes> findAllRTF()
	    throws B2BException
	{
		Collection<Constantes> lcc_constantes;
		PreparedStatement      lps_ps;
		ResultSet              lrs_rs;

		lcc_constantes     = new ArrayList<Constantes>();
		lps_ps             = null;
		lrs_rs             = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL_RTF);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcc_constantes.add(getConstantes(lrs_rs, false));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findAllRTF", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(lcc_constantes.isEmpty())
			lcc_constantes = null;

		return lcc_constantes;
	}

	/**
	 * Metodo para encontrar todas las imagenes de la tabla SDB_PGN_CONSTANTES que coincidan con un caracter especifico.
	 *
	 * @param ap_parametros objeto de tipo constantes
	 * @return devuelve el valor del objeto constantes
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Constantes findByCaracter(Constantes ap_parametros)
	    throws B2BException
	{
		Constantes        lc_c;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lc_c       = null;
		lps_ps     = null;
		lrs_rs     = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_BY_CARACTER);

			lps_ps.setString(1, ap_parametros.getCaracter());

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				lc_c = getConstantes(lrs_rs);
		}
		catch(SQLException lse_e)
		{
			logError(this, "findByCaracter", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return lc_c;
	}

	/**
	 * Metodo encargado de validar si el parametro enviado es diferente de null invocar el metodo findById de lo contrario retorna null.
	 *
	 * @param ap_parametros objeto de tipo constantes
	 * @return devuelve el valor del objeto constantes
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Constantes findById(Constantes ap_parametros)
	    throws B2BException
	{
		return (ap_parametros != null) ? findById(ap_parametros.getIdConstante()) : null;
	}

	/**
	 * Metodo para encontrar todos los registros de la tabla SDB_PGN_CONSTANTES que coincidan con un id_constante especifico.
	 *
	 * @param as_id id de la constante para realizar la consulta
	 * @return devuelve el valor del objeto constantes
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Constantes findById(String as_id)
	    throws B2BException
	{
		Constantes        lc_c;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;
		int               li_column;

		li_column     = 1;

		lc_c       = null;
		lps_ps     = null;
		lrs_rs     = null;

		try
		{
			if(StringUtils.isValidString(as_id))
			{
				boolean lb_b;
				String  ls_consulta;

				lb_b            = as_id.equalsIgnoreCase(ConstanteCommon.ACTO_PATRIMONIO_FAMILIA);
				ls_consulta     = cs_FIND_BY_ID;

				if(lb_b)
					ls_consulta = cs_DATOS_ACTO_MADRE_CABEZA;

				lps_ps = getConnection().prepareStatement(ls_consulta);

				lps_ps.setString(li_column++, as_id);

				if(lb_b)
				{
					lps_ps.setString(li_column++, ConstanteCommon.PATRIMONIO_FAMILIA_CABEZA_FAMILIA_SI);
					lps_ps.setString(li_column++, ConstanteCommon.PATRIMONIO_FAMILIA_CABEZA_FAMILIA_NO);
				}

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lc_c = getConstantes(lrs_rs, lb_b);
			}
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

		return lc_c;
	}

	/**
	 * Metodo para encontrar todas las imagenes de la tabla SDB_PGN_CONSTANTES que coincidan con un id_constante especifico.
	 *
	 * @param ac_parametros objeto de tipo constantes
	 * @return devuelve el valor del objeto constantes
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Constantes findByIdWithImage(Constantes ac_parametros)
	    throws B2BException
	{
		return (ac_parametros != null) ? findByIdWithImage(ac_parametros.getIdConstante()) : null;
	}

	/**
	 * Metodo para encontrar todas las imagenes de la tabla SDB_PGN_CONSTANTES que coincidan con un id_constante especifico.
	 *
	 * @param as_idConstante correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor del objeto constantes
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Constantes findByIdWithImage(String as_idConstante)
	    throws B2BException
	{
		Constantes lc_c;
		lc_c = null;

		if(StringUtils.isValidString(as_idConstante))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_ALL_BY_ID);

				lps_ps.setString(1, as_idConstante);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lc_c = getConstantes(lrs_rs, false);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdWithImage", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lc_c;
	}

	/**
	 * Find by id without image.
	 *
	 * @param as_id de as id
	 * @return el valor de constantes
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Constantes findByIdWithoutImage(String as_id)
	    throws B2BException
	{
		Constantes        lc_c;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lc_c       = null;
		lps_ps     = null;
		lrs_rs     = null;

		try
		{
			if(StringUtils.isValidString(as_id))
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(li_column++, as_id);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lc_c = getConstantes(lrs_rs, false, false);
			}
		}
		catch(SQLException lse_e)
		{
			logError(this, "findByIdWithoutImage", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return lc_c;
	}

	/**
	 * Metodo para obtener el ENTERO de una constante en la base de datos.
	 *
	 * @param as_id ID_CONSTENTE a consultar
	 * @return devuelve el valor del objeto long
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Long findEntero(String as_id)
	    throws B2BException
	{
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;
		Long              ll_constant;

		lps_ps          = null;
		lrs_rs          = null;
		ll_constant     = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

			lps_ps.setString(1, as_id);

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				ll_constant = getLong(lrs_rs, "ENTERO");
		}
		catch(SQLException lse_e)
		{
			logError(this, "findEntero", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return ll_constant;
	}

	/**
	 * Find entero by id.
	 *
	 * @param as_idConstante de as id constante
	 * @return el valor de long
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public long findEnteroById(String as_idConstante)
	    throws B2BException
	{
		long lc_c;
		lc_c = 0L;

		if(StringUtils.isValidString(as_idConstante))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_ENTERO_BY_ID);

				lps_ps.setString(1, as_idConstante);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lc_c = getEntero(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findEnteroById", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lc_c;
	}

	/**
	 * Metodo para encontrar las imagenes de la tabla SDB_PGN_COSNTANTES.
	 *
	 * @param ap_parametros objeto de tipo constantes
	 * @return devuelve el valor del objeto constantes
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Constantes findImagen(Constantes ap_parametros)
	    throws B2BException
	{
		Constantes        lc_constante;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lc_constante     = null;
		lps_ps           = null;
		lrs_rs           = null;

		try
		{
			int li_contador;
			li_contador     = 1;

			lps_ps = getConnection().prepareStatement(cs_FIND_IMAGE);

			lps_ps.setString(li_contador++, ap_parametros.getIdConstante());

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
			{
				lc_constante = new Constantes();

				lc_constante.setImagenes(getImagenes(lrs_rs));
				lc_constante.setTipoArchivo(lrs_rs.getString("TIPO_ARCHIVO"));
			}
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

		return lc_constante;
	}

	/**
	 * Retorna el valor del objeto de tipo byte correspondiente a la imagen.
	 *
	 * @param as_id correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor del objeto byte[]
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public byte[] findImagenes(String as_id)
	    throws B2BException
	{
		byte[]            lba_contante;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lba_contante     = null;
		lps_ps           = null;
		lrs_rs           = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_IMAGE);

			lps_ps.setString(1, as_id);

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				lba_contante = getImagenes(lrs_rs);
		}
		catch(SQLException lse_e)
		{
			logError(this, "findImagenes", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return lba_contante;
	}

	/**
	 * Metodo para obtener el caracter de una constante en la base de datos.
	 *
	 * @param as_id correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor del objeto string
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public String findString(String as_id)
	    throws B2BException
	{
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;
		String            ls_constant;

		lps_ps          = null;
		lrs_rs          = null;
		ls_constant     = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_CARACTER_BY_ID);

			lps_ps.setString(1, as_id);

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				ls_constant = StringUtils.getString(lrs_rs.getString("CARACTER"));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findString", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return ls_constant;
	}

	/**
	 * Metodo para encontrar todos los registros de la tabla SDB_PGN_CONSTANTES que coincidan con un id_constante especifico.
	 *
	 * @param as_id id de la constante para realizar la consulta
	 * @return devuelve el valor del objeto constantes
	 * @throws B2BException Señala que se ha producido una excepción
	 * @throws IOException Objeto de tipo IOException, se produce cuando se encuentra algun error de tipo I/O.
	 */
	public String findXMLWorkflowById(String as_id)
	    throws B2BException, IOException
	{
		String            ls_s;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		ls_s       = null;
		lps_ps     = null;
		lrs_rs     = null;

		try
		{
			if(StringUtils.isValidString(as_id))
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_FIND_IMAGE_WORKFLOW);

				lps_ps.setString(li_column++, as_id);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ls_s = getWorkflow(lrs_rs);
			}
		}
		catch(SQLException lse_e)
		{
			logError(this, "findXMLWorkflowById", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return ls_s;
	}

	/**
	 * Metodo para traer todos los registros que conincidan con el argumento enviado  de la tabla SDB_PGN_CONSTANTES.
	 *
	 * @param as_like Objeto de tipo String que contiene llave por la cual buscar
	 * @return devuelve el valor del objeto collection
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<Constantes> findbyLike(String as_like)
	    throws B2BException
	{
		Collection<Constantes> lp_constante;
		PreparedStatement      lps_ps;
		ResultSet              lrs_rs;

		lp_constante     = new ArrayList<Constantes>();
		lps_ps           = null;
		lrs_rs           = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_BY_LIKE);

			lps_ps.setString(1, as_like);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lp_constante.add(getConstantes(lrs_rs, false));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findbyLike", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(lp_constante.isEmpty())
			lp_constante = null;

		return lp_constante;
	}

	/**
	 * Metodo para insertar o actualizar en la tabla SDB_PGN_CONSTANTES.
	 *
	 * @param ac_parametros correspondiente al valor del tipo de objeto Constantes
	 * @param ab_insert correspondiente al valor del tipo de objeto boolean
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void insertOrUpdate(Constantes ac_parametros, boolean ab_insert)
	    throws B2BException
	{
		if(ac_parametros != null)
		{
			PreparedStatement lps_ps;

			lps_ps = null;

			try
			{
				boolean       lb_imagen;
				int           li_column;
				StringBuilder lsb_statement;

				li_column         = 1;
				lsb_statement     = new StringBuilder(ab_insert ? cs_INSERT : cs_UPDATE);
				lb_imagen         = ac_parametros.getImagenes() != null;

				if(ab_insert)
				{
					if(lb_imagen)
						lsb_statement.append(",IMAGENES");

					lsb_statement.append(")VALUES(?,?,?,?,?,?,?,?,?,?,SYSTIMESTAMP,?");

					if(lb_imagen)
						lsb_statement.append(",?");

					lsb_statement.append(")");
				}
				else
				{
					if(lb_imagen)
						lsb_statement.append(",IMAGENES=?");

					lsb_statement.append(" WHERE ID_CONSTANTE=?");
				}

				lps_ps = getConnection().prepareStatement(lsb_statement.toString());

				if(ab_insert)
					lps_ps.setString(li_column++, ac_parametros.getIdConstante());

				lps_ps.setString(li_column++, ac_parametros.getCaracter());

				lps_ps.setObject(li_column++, ac_parametros.getEntero());

				setDouble(lps_ps, ac_parametros.getReal(), li_column++);

				setDate(lps_ps, ac_parametros.getFecha(), li_column++);

				lps_ps.setString(li_column++, ac_parametros.getDescripcion());
				lps_ps.setString(li_column++, ac_parametros.getTipoArchivo());
				lps_ps.setString(li_column++, ac_parametros.getActivo());

				if(ab_insert)
				{
					String ls_tipo;

					ls_tipo = ac_parametros.getTipo();

					if(!StringUtils.isValidString(ls_tipo))
					{
						ls_tipo = Constantes.TIPO_AUTOMATICA;

						ac_parametros.setTipo(ls_tipo);
					}

					lps_ps.setString(li_column++, ac_parametros.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, ac_parametros.getIpCreacion());
					lps_ps.setString(li_column++, ls_tipo);
				}
				else
				{
					lps_ps.setString(li_column++, ac_parametros.getIdUsuarioModificacion());
					lps_ps.setString(li_column++, ac_parametros.getIpModificacion());
				}

				if(lb_imagen)
					lps_ps.setBinaryStream(li_column++, new java.io.ByteArrayInputStream(ac_parametros.getImagenes()));

				if(!ab_insert)
					lps_ps.setString(li_column++, ac_parametros.getIdConstante());

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
	 * Metodo para actualizar el caracter en la base de datos.
	 *
	 * @param as_id representa el id_constante con el que se va a realizar la consulta
	 * @param as_string representa el caracter que se va a insertar
	 * @param as_user es el usuario que hace modificacion
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void updateString(String as_id, String as_string, String as_user)
	    throws B2BException
	{
		PreparedStatement lps_ps;

		lps_ps = null;

		try
		{
			int li_column;

			li_column     = 1;
			lps_ps        = getConnection().prepareStatement(cs_UPDATE_STRING_CONSTANT);

			lps_ps.setString(li_column++, as_string);
			lps_ps.setString(li_column++, as_user);
			lps_ps.setString(li_column++, as_id);

			lps_ps.executeUpdate();
		}
		catch(SQLException lse_e)
		{
			logError(this, "updateString", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lps_ps);
		}
	}

	/**
	 * Metodo para obtener objeto de tipo constantes.
	 *
	 * @param ars_rs objeto que representa el resultado de una sentencia sql en la base de datos
	 * @param ab_b correspondiente al valor del tipo de objeto boolean
	 * @param ab_imagen correspondiente al valor del tipo de objeto boolean
	 * @return el valor de constantes
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private Constantes getConstantes(ResultSet ars_rs, boolean ab_b, boolean ab_imagen)
	    throws SQLException
	{
		Constantes lc_datos;

		lc_datos = new Constantes();

		if(ab_b)
		{
			lc_datos.setCaracter(ars_rs.getString("CODIGO"));
			lc_datos.setDescripcion(ars_rs.getString("DESCRIPCION"));
		}
		else
		{
			lc_datos.setIdConstante(ars_rs.getString("ID_CONSTANTE"));
			lc_datos.setCaracter(ars_rs.getString("CARACTER"));
			lc_datos.setReal(getDouble(ars_rs, "REAL"));
			lc_datos.setFecha(ars_rs.getTimestamp("FECHA"));
			lc_datos.setDescripcion(ars_rs.getString("DESCRIPCION"));
			lc_datos.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
			lc_datos.setIdUsuarioModificacion(ars_rs.getString("ID_USUARIO_MODIFICACION"));
			lc_datos.setIpModificacion(ars_rs.getString("IP_MODIFICACION"));
			lc_datos.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
			lc_datos.setFechaModificacion(ars_rs.getTimestamp("FECHA_MODIFICACION"));
			lc_datos.setTipoArchivo(ars_rs.getString("TIPO_ARCHIVO"));
			lc_datos.setActivo(ars_rs.getString("ACTIVO"));
			lc_datos.setTexto(ars_rs.getString("TEXTO"));

			{
				Object lo_o;

				lo_o = ars_rs.getObject("ENTERO");

				if((lo_o != null) && lo_o instanceof BigDecimal)
					lc_datos.setEntero(((BigDecimal)lo_o).toBigInteger());
			}

			if(ab_imagen)
				lc_datos.setImagenes(getImagenes(ars_rs));
		}

		return lc_datos;
	}

	/**
	 * Metodo para obtener objeto de tipo constantes.
	 *
	 * @param ars_rs objeto que representa el resultado de una sentencia sql en la base de datos
	 * @param ab_b correspondiente al valor del tipo de objeto boolean
	 * @return el valor de constantes
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private Constantes getConstantes(ResultSet ars_rs, boolean ab_b)
	    throws SQLException
	{
		return getConstantes(ars_rs, ab_b, true);
	}

	/**
	 * Metodo para obtener objeto de tipo constantes.
	 *
	 * @param ars_rs objeto que representa el resultado de una sentencia sql en la base de datos
	 * @return el valor de constantes
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private Constantes getConstantes(ResultSet ars_rs)
	    throws SQLException
	{
		return getConstantes(ars_rs, false, false);
	}

	/**
	 * Retorna Objeto o variable de valor entero.
	 *
	 * @param ars_rs de ars rs
	 * @return el valor de entero
	 * @throws SQLException cuando se produce algun error en el proceso
	 */
	private long getEntero(ResultSet ars_rs)
	    throws SQLException
	{
		return ars_rs.getLong("ENTERO");
	}

	/**
	 * Retorna el valor de imagenes.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de imagenes
	 */
	private byte[] getImagenes(ResultSet ars_rs)
	{
		byte[] lba_data;

		try
		{
			java.sql.Blob lb_blob;

			lb_blob      = ars_rs.getBlob("IMAGENES");
			lba_data     = org.apache.commons.io.IOUtils.toByteArray(lb_blob.getBinaryStream());
		}
		catch(Exception le_e)
		{
			lba_data = null;
		}

		return lba_data;
	}

	/**
	 * Retorna el valor de motivos.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de motivos
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private Constantes getMotivos(ResultSet ars_rs)
	    throws SQLException
	{
		Constantes lc_datos;
		Object     lo_o;
		lc_datos = new Constantes();
		lc_datos.setCaracter(ars_rs.getString("CARACTER"));
		lo_o = ars_rs.getObject("ENTERO");

		if((lo_o != null) && lo_o instanceof BigDecimal)
			lc_datos.setEntero(((BigDecimal)lo_o).toBigInteger());

		return lc_datos;
	}

	/**
	 * Metodo para obtener objeto de tipo constantes.
	 *
	 * @param ars_rs objeto que representa el resultado de una sentencia sql en la base de datos
	 * @return el valor de constantes
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private String getWorkflow(ResultSet ars_rs)
	    throws B2BException
	{
		try
		{
			return ClobUtils.clobToString(ars_rs.getClob("IMAGENES"));
		}
		catch(SQLException lsqle_e)
		{
			throw new B2BException(SQL_ERROR, lsqle_e);
		}
	}
}
