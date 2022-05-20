package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.model.sdb.acc.DetalleAreaPredio;

import java.math.BigInteger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;


/**
 * Clase para poder realizar transacciones con la base de datos en la tabla SDB_ACC_DETALLE_AREA_PREDIO.
 *
 * @author Julian Vaca
 */
public class AccDetalleAreaPredioDAO extends BaseDAO
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT ID_DETALLE_AREA_PREDIO,ID_AREA_PREDIO,ID_TIPO_AREA,ID_UNIDAD_MEDIDA,AREA,ID_CIRCULO,ID_MATRICULA,"
		+ "ID_USUARIO_CREACION,FECHA_CREACION,IP_CREACION,ID_USUARIO_MODIFICACION,FECHA_MODIFICACION,IP_MODIFICACION FROM SDB_ACC_DETALLE_AREA_PREDIO"
		+ " WHERE ID_DETALLE_AREA_PREDIO = ? AND ID_AREA_PREDIO = ? AND ID_CIRCULO = ? AND ID_MATRICULA = ? ";

	/** Constante cs_FIND_BY_ID_AREA_PREDIO. */
	private static final String cs_FIND_BY_ID_AREA_PREDIO = "SELECT ID_DETALLE_AREA_PREDIO,ID_AREA_PREDIO,ID_TIPO_AREA,ID_UNIDAD_MEDIDA,AREA,ID_CIRCULO,ID_MATRICULA,"
		+ "ID_USUARIO_CREACION,FECHA_CREACION,IP_CREACION,ID_USUARIO_MODIFICACION,FECHA_MODIFICACION,IP_MODIFICACION FROM SDB_ACC_DETALLE_AREA_PREDIO"
		+ " WHERE ID_AREA_PREDIO = ? AND ID_CIRCULO = ? AND ID_MATRICULA = ? ";

	/** Constante cs_FIND_BY_ID_CIRCULO_ID_MATRICULA. */
	private static final String cs_FIND_BY_ID_CIRCULO_ID_MATRICULA = "SELECT * FROM SDB_ACC_DETALLE_AREA_PREDIO"
		+ " WHERE ID_CIRCULO = ? AND ID_MATRICULA = ? ";

	/** Constante cs_FIND_BY_ID_AREA_PREDIO_TIPO. */
	private static final String cs_FIND_BY_ID_AREA_PREDIO_TIPO = "SELECT ID_DETALLE_AREA_PREDIO,ID_AREA_PREDIO,ID_TIPO_AREA,ID_UNIDAD_MEDIDA,AREA,ID_CIRCULO,ID_MATRICULA,"
		+ "ID_USUARIO_CREACION,FECHA_CREACION,IP_CREACION,ID_USUARIO_MODIFICACION,FECHA_MODIFICACION,IP_MODIFICACION FROM SDB_ACC_DETALLE_AREA_PREDIO"
		+ " WHERE ID_AREA_PREDIO = ? AND ID_CIRCULO = ? AND ID_MATRICULA = ? AND ID_TIPO_AREA = ?";

	/** Constante cs_FIND_MAX_ID_DETALLE_AREA_PREDIO. */
	private static final String cs_FIND_MAX_ID_DETALLE_AREA_PREDIO = "SELECT MAX(ID_DETALLE_AREA_PREDIO) ID_DETALLE_AREA_PREDIO FROM SDB_ACC_DETALLE_AREA_PREDIO WHERE ID_AREA_PREDIO = ? AND ID_CIRCULO = ? AND ID_MATRICULA = ?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_ACC_DETALLE_AREA_PREDIO (ID_DETALLE_AREA_PREDIO,ID_AREA_PREDIO,ID_CIRCULO,ID_MATRICULA,"
		+ "ID_USUARIO_CREACION,IP_CREACION,ID_TIPO_AREA,ID_UNIDAD_MEDIDA,AREA,FECHA_CREACION) VALUES (?,?,?,?,?,?,?,?,?,SYSTIMESTAMP)";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_ACC_DETALLE_AREA_PREDIO SET ID_UNIDAD_MEDIDA = ?, AREA = ?, ID_USUARIO_MODIFICACION = ?, "
		+ "FECHA_MODIFICACION = SYSTIMESTAMP, IP_MODIFICACION = ? WHERE ID_DETALLE_AREA_PREDIO = ? AND ID_AREA_PREDIO = ? AND ID_CIRCULO = ? AND ID_MATRICULA = ? AND ID_TIPO_AREA = ?";

	/** Constante cs_DELETE. */
	private static final String cs_DELETE = "DELETE FROM SDB_ACC_DETALLE_AREA_PREDIO WHERE ID_DETALLE_AREA_PREDIO = ? AND ID_AREA_PREDIO = ? AND ID_CIRCULO = ? AND ID_MATRICULA = ? AND ID_TIPO_AREA = ?";

	/** Constante cs_FIND_BY_ID_AREA_PREDIO_TIPO_UNIDAD_MEDIDA. */
	private static final String cs_FIND_BY_ID_AREA_PREDIO_TIPO_UNIDAD_MEDIDA = "SELECT ID_DETALLE_AREA_PREDIO,ID_AREA_PREDIO,ID_TIPO_AREA,ID_UNIDAD_MEDIDA,AREA,ID_CIRCULO,ID_MATRICULA,"
		+ "ID_USUARIO_CREACION,FECHA_CREACION,IP_CREACION,ID_USUARIO_MODIFICACION,FECHA_MODIFICACION,IP_MODIFICACION FROM SDB_ACC_DETALLE_AREA_PREDIO"
		+ " WHERE ID_AREA_PREDIO = ? AND ID_CIRCULO = ? AND ID_MATRICULA = ? AND ID_TIPO_AREA = ? AND ID_UNIDAD_MEDIDA= ?";

	/**
	 * Método para eliminar un registro en la BD.
	 *
	 * @param adap_param objeto DetalleAreaPredio para eliminar el registro
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void delete(DetalleAreaPredio adap_param)
	    throws B2BException
	{
		PreparedStatement lps_ps;
		int               li_column;

		lps_ps        = null;
		li_column     = 1;

		if(adap_param != null)
		{
			try
			{
				lps_ps = getConnection().prepareStatement(cs_DELETE);

				lps_ps.setString(li_column++, adap_param.getIdDetalleAreaPredio());
				lps_ps.setString(li_column++, adap_param.getIdAreaPredio());
				lps_ps.setString(li_column++, adap_param.getIdCirculo());
				setLong(lps_ps, adap_param.getIdMatricula(), li_column++);
				lps_ps.setString(li_column++, adap_param.getIdTipoArea());

				lps_ps.executeQuery();
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
	 * Método para encontrar todos los registros de la BD de un filtro especifico.
	 *
	 * @param adap_param objeto DetalleAreaPredio para obtener los datos para filtrar
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public Collection<DetalleAreaPredio> findAllByIdCirculoIdMatricula(String as_idCirculo, Long al_idMatricula)
	    throws B2BException
	{
		Collection<DetalleAreaPredio> lcadap_areas;
		PreparedStatement             lps_ps;
		ResultSet                     lrs_rs;

		lcadap_areas     = null;
		lps_ps           = null;
		lrs_rs           = null;

		if(StringUtils.isValidString(as_idCirculo) && NumericUtils.isValidLong(al_idMatricula))
		{
			try
			{
				int li_count;

				li_count     = 1;
				lps_ps       = getConnection().prepareStatement(cs_FIND_BY_ID_CIRCULO_ID_MATRICULA);

				lps_ps.setString(li_count++, as_idCirculo);
				setLong(lps_ps, al_idMatricula, li_count++);

				lrs_rs           = lps_ps.executeQuery();
				lcadap_areas     = new ArrayList<DetalleAreaPredio>(1);

				while(lrs_rs.next())
					lcadap_areas.add(getObjetFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findAllByIdCirculoIdMatricula", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lcadap_areas;
	}

	/**
	 * Find all by id area predio tipo.
	 *
	 * @param adap_param de adap param
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<DetalleAreaPredio> findAllByIdAreaPredioTipo(DetalleAreaPredio adap_param)
	    throws B2BException
	{
		Collection<DetalleAreaPredio> lcadap_areas;
		PreparedStatement             lps_ps;
		ResultSet                     lrs_rs;

		lcadap_areas     = new ArrayList<DetalleAreaPredio>();
		lps_ps           = null;
		lrs_rs           = null;

		if(adap_param != null)
		{
			try
			{
				int li_count;

				li_count     = 1;
				lps_ps       = getConnection().prepareStatement(cs_FIND_BY_ID_AREA_PREDIO_TIPO);

				lps_ps.setString(li_count++, adap_param.getIdAreaPredio());
				lps_ps.setString(li_count++, adap_param.getIdCirculo());
				setLong(lps_ps, adap_param.getIdMatricula(), li_count++);
				lps_ps.setString(li_count++, adap_param.getIdTipoArea());

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcadap_areas.add(getObjetFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findAllByIdAreaPredioTipo", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lcadap_areas.isEmpty())
			lcadap_areas = null;

		return lcadap_areas;
	}

	/**
	 * Método para encontrar todos los registros de la BD de un filtro especifico.
	 *
	 * @param adap_param objeto DetalleAreaPredio para obtener los datos para filtrar
	 * @return Hashmap
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see HashMap
	 */
	public HashMap<String, DetalleAreaPredio> findAllByIdAreaPredioTipoDetalle(DetalleAreaPredio adap_param)
	    throws B2BException
	{
		HashMap<String, DetalleAreaPredio> lhmsadap_areas;
		PreparedStatement                  lps_ps;
		ResultSet                          lrs_rs;

		lhmsadap_areas     = new HashMap<String, DetalleAreaPredio>();
		lps_ps             = null;
		lrs_rs             = null;

		if(adap_param != null)
		{
			try
			{
				int li_count;

				li_count     = 1;
				lps_ps       = getConnection().prepareStatement(cs_FIND_BY_ID_AREA_PREDIO_TIPO);

				lps_ps.setString(li_count++, adap_param.getIdAreaPredio());
				lps_ps.setString(li_count++, adap_param.getIdCirculo());
				setLong(lps_ps, adap_param.getIdMatricula(), li_count++);
				lps_ps.setString(li_count++, adap_param.getIdTipoArea());

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
				{
					DetalleAreaPredio ladap_area;

					ladap_area = getObjetFromResultSet(lrs_rs);

					if(ladap_area != null)
					{
						String ls_idDetalleAreaPredio;

						ls_idDetalleAreaPredio = ladap_area.getIdDetalleAreaPredio();

						if(StringUtils.isValidString(ls_idDetalleAreaPredio))
							lhmsadap_areas.put(ls_idDetalleAreaPredio, ladap_area);
					}
				}
			}
			catch(SQLException lse_e)
			{
				logError(this, "findAllByIdAreaPredioTipoDetalle", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lhmsadap_areas.isEmpty())
			lhmsadap_areas = null;

		return lhmsadap_areas;
	}

	/**
	 * Retorna el valor del objeto de DetalleAreaPredio.
	 *
	 * @param as_idAreaPredio correspondiente al valor del tipo de objeto String
	 * @param as_idCirculo correspondiente al valor del tipo de objeto String
	 * @param al_idMatricula correspondiente al valor del tipo de objeto Long
	 * @param as_idTipoArea correspondiente al valor del tipo de objeto String
	 * @param as_idUnidadMedida correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de DetalleAreaPredio
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DetalleAreaPredio
	 */
	public DetalleAreaPredio findAllByIdAreaPredioTipoUnidad(
	    String as_idAreaPredio, String as_idCirculo, Long al_idMatricula, String as_idTipoArea, String as_idUnidadMedida
	)
	    throws B2BException
	{
		DetalleAreaPredio ldap_return;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		ldap_return     = null;
		lps_ps          = null;
		lrs_rs          = null;

		try
		{
			if(
			    StringUtils.isValidString(as_idAreaPredio) && StringUtils.isValidString(as_idCirculo)
				    && StringUtils.isValidString(as_idTipoArea) && StringUtils.isValidString(as_idUnidadMedida)
				    && NumericUtils.isValidLong(al_idMatricula)
			)
			{
				int li_count;

				li_count     = 1;
				lps_ps       = getConnection().prepareStatement(cs_FIND_BY_ID_AREA_PREDIO_TIPO_UNIDAD_MEDIDA);

				lps_ps.setString(li_count++, as_idAreaPredio);
				lps_ps.setString(li_count++, as_idCirculo);
				setLong(lps_ps, al_idMatricula, li_count++);
				lps_ps.setString(li_count++, as_idTipoArea);
				lps_ps.setString(li_count++, as_idUnidadMedida);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ldap_return = getObjetFromResultSet(lrs_rs);
			}
		}
		catch(SQLException lse_e)
		{
			logError(this, "findAllByIdAreaPredioTipoUnidad", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return ldap_return;
	}

	/**
	 * * Método para encontrar todos los registros de la BD filtrando por el ID.
	 *
	 * @param adap_param objeto DetalleAreaPredio para obtener los datos para filtrar
	 * @return devuelve el valor de DetalleAreaPredio
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DetalleAreaPredio
	 */
	public DetalleAreaPredio findById(DetalleAreaPredio adap_param)
	    throws B2BException
	{
		DetalleAreaPredio ladap_detalleAreaPredio;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		ladap_detalleAreaPredio     = null;
		lps_ps                      = null;
		lrs_rs                      = null;

		if(adap_param != null)
		{
			try
			{
				int li_count;

				li_count     = 1;
				lps_ps       = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(li_count++, adap_param.getIdDetalleAreaPredio());
				lps_ps.setString(li_count++, adap_param.getIdAreaPredio());
				lps_ps.setString(li_count++, adap_param.getIdCirculo());
				setLong(lps_ps, adap_param.getIdMatricula(), li_count++);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ladap_detalleAreaPredio = getObjetFromResultSet(lrs_rs);
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

		return ladap_detalleAreaPredio;
	}

	/**
	 * Método para encontrar todos los registros de la BD de un filtro especifico.
	 *
	 * @param adap_param objeto DetalleAreaPredio para obtener los datos para filtrar
	 * @return coleccion de DetalleAreaPredio
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public Collection<DetalleAreaPredio> findByIdAreaPredio(DetalleAreaPredio adap_param)
	    throws B2BException
	{
		Collection<DetalleAreaPredio> lcdap_Predio;
		PreparedStatement             lps_ps;
		ResultSet                     lrs_rs;

		lcdap_Predio     = new ArrayList<DetalleAreaPredio>();
		lps_ps           = null;
		lrs_rs           = null;

		if(adap_param != null)
		{
			try
			{
				int li_count;

				li_count     = 1;
				lps_ps       = getConnection().prepareStatement(cs_FIND_BY_ID_AREA_PREDIO);

				lps_ps.setString(li_count++, adap_param.getIdAreaPredio());
				lps_ps.setString(li_count++, adap_param.getIdCirculo());
				setLong(lps_ps, adap_param.getIdMatricula(), li_count++);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcdap_Predio.add(getObjetFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdAreaPredio", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lcdap_Predio.isEmpty())
			lcdap_Predio = null;

		return lcdap_Predio;
	}

	/**
	 * Retorna el valor del objeto de tipo Collection de DetalleAreaPredio filtrado por el id area predio, id circulo, id matricula
	 * y id tipo de area.
	 *
	 * @param as_idAreaPredio correspondiente al valor del id area predio
	 * @param as_idCirculo correspondiente al valor del id círculo
	 * @param al_idMatricula correspondiente al valor del id matrícula
	 * @param as_idTipoArea correspondiente al valor del tipo area
	 * @return devuelve el valor del objeto collection de DetalleAreaPredio
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DetalleAreaPredio
	 */
	public DetalleAreaPredio findByIdAreaPredioTipo(
	    String as_idAreaPredio, String as_idCirculo, Long al_idMatricula, String as_idTipoArea
	)
	    throws B2BException
	{
		DetalleAreaPredio lcadap_areas;

		lcadap_areas = null;

		if(
		    StringUtils.isValidString(as_idAreaPredio) && StringUtils.isValidString(as_idCirculo)
			    && NumericUtils.isValidLong(al_idMatricula) && StringUtils.isValidString(as_idTipoArea)
		)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_count;

				li_count     = 1;
				lps_ps       = getConnection().prepareStatement(cs_FIND_BY_ID_AREA_PREDIO_TIPO);

				lps_ps.setString(li_count++, as_idAreaPredio);
				lps_ps.setString(li_count++, as_idCirculo);
				setLong(lps_ps, al_idMatricula, li_count++);
				lps_ps.setString(li_count++, as_idTipoArea);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lcadap_areas = getObjetFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdAreaPredioTipo", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lcadap_areas;
	}

	/**
	 * Método para encontrar todos los registros de la BD de un filtro especifico.
	 *
	 * @param adap_param objeto DetalleAreaPredio para obtener los datos para filtrar
	 * @return objeto DetalleAreaPredio
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DetalleAreaPredio
	 */
	public DetalleAreaPredio findByIdAreaPredioTipo(DetalleAreaPredio adap_param)
	    throws B2BException
	{
		return (adap_param != null)
		? findByIdAreaPredioTipo(
		    adap_param.getIdAreaPredio(), adap_param.getIdCirculo(), adap_param.getIdMatricula(),
		    adap_param.getIdTipoArea()
		) : null;
	}

	/**
	 * Método para encontrar todos los registros de la BD de un filtro especifico.
	 *
	 * @param adap_param objeto DetalleAreaPredio para obtener los datos para filtrar
	 * @return BigInteger
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see BigInteger
	 */
	public BigInteger findByMaxIdDetalleAreaPredio(DetalleAreaPredio adap_param)
	    throws B2BException
	{
		BigInteger        lbi_idDetalleAreaPredio;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lbi_idDetalleAreaPredio     = null;
		lps_ps                      = null;
		lrs_rs                      = null;

		if(adap_param != null)
		{
			try
			{
				int li_count;

				li_count     = 1;
				lps_ps       = getConnection().prepareStatement(cs_FIND_MAX_ID_DETALLE_AREA_PREDIO);

				lps_ps.setString(li_count++, adap_param.getIdAreaPredio());
				lps_ps.setString(li_count++, adap_param.getIdCirculo());
				setLong(lps_ps, adap_param.getIdMatricula(), li_count++);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
				{
					String ls_idDetalleAreaPredio;

					ls_idDetalleAreaPredio      = lrs_rs.getString("ID_DETALLE_AREA_PREDIO");
					lbi_idDetalleAreaPredio     = StringUtils.isValidString(ls_idDetalleAreaPredio)
						? NumericUtils.getBigInteger(ls_idDetalleAreaPredio) : BigInteger.ZERO;
				}
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByMaxIdDetalleAreaPredio", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lbi_idDetalleAreaPredio;
	}

	/**
	 * Método para insertar o modificar un registro en la BD.
	 *
	 * @param adap_param objeto DetalleAreaPredio para obtener los datos para filtrar
	 * @param ab_query booleano para saber que accion realizar (Insertar/Modificar)
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void insertOrUpdate(DetalleAreaPredio adap_param, boolean ab_query)
	    throws B2BException
	{
		if(adap_param != null)
		{
			PreparedStatement lps_ps;

			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(ab_query ? cs_INSERT : cs_UPDATE);

				if(ab_query)
				{
					lps_ps.setString(li_column++, adap_param.getIdDetalleAreaPredio());
					lps_ps.setString(li_column++, adap_param.getIdAreaPredio());
					lps_ps.setString(li_column++, adap_param.getIdCirculo());
					setLong(lps_ps, adap_param.getIdMatricula(), li_column++);
					lps_ps.setString(li_column++, adap_param.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, adap_param.getIpCreacion());
					lps_ps.setString(li_column++, adap_param.getIdTipoArea());
				}

				lps_ps.setString(li_column++, adap_param.getIdUnidadMedida());
				setDouble(lps_ps, adap_param.getArea(), li_column++);

				if(!ab_query)
				{
					lps_ps.setString(li_column++, adap_param.getIdUsuarioModificacion());
					lps_ps.setString(li_column++, adap_param.getIpModificacion());
					lps_ps.setString(li_column++, adap_param.getIdDetalleAreaPredio());
					lps_ps.setString(li_column++, adap_param.getIdAreaPredio());
					lps_ps.setString(li_column++, adap_param.getIdCirculo());
					setLong(lps_ps, adap_param.getIdMatricula(), li_column++);
					lps_ps.setString(li_column++, adap_param.getIdTipoArea());
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
	 * Método para extraer los datos de la consulta a la BD y ponerlos en un objeto de DetalleAreaPredio y lo retorna.
	 *
	 * @param ars_rs objeto ResultSet con el resultado de la consulta de la BD
	 * @return objeto DetalleAreaPredio
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private DetalleAreaPredio getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		DetalleAreaPredio ldap_areaPredio;

		ldap_areaPredio = new DetalleAreaPredio();

		ldap_areaPredio.setIdDetalleAreaPredio(ars_rs.getString("ID_DETALLE_AREA_PREDIO"));
		ldap_areaPredio.setIdAreaPredio(ars_rs.getString("ID_AREA_PREDIO"));
		ldap_areaPredio.setIdTipoArea(ars_rs.getString("ID_TIPO_AREA"));
		ldap_areaPredio.setIdUnidadMedida(ars_rs.getString("ID_UNIDAD_MEDIDA"));

		{
			Double ld_area;

			ld_area = getDouble(ars_rs, "AREA");

			ldap_areaPredio.setArea(ld_area);

			if(NumericUtils.isValidDouble(ld_area, 0, false))
				ldap_areaPredio.setAreaLectura(StringUtils.getString(ld_area));
		}

		ldap_areaPredio.setIdCirculo(ars_rs.getString("ID_CIRCULO"));
		ldap_areaPredio.setIdMatricula(getLong(ars_rs, "ID_MATRICULA"));
		ldap_areaPredio.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		ldap_areaPredio.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		ldap_areaPredio.setIpCreacion(ars_rs.getString("IP_CREACION"));
		ldap_areaPredio.setIdUsuarioModificacion(ars_rs.getString("ID_USUARIO_MODIFICACION"));
		ldap_areaPredio.setFechaModificacion(ars_rs.getTimestamp("FECHA_MODIFICACION"));
		ldap_areaPredio.setIpModificacion(ars_rs.getString("IP_MODIFICACION"));

		return ldap_areaPredio;
	}
}
