package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;

import com.bachue.snr.prosnr01.model.sdb.acc.Liquidacion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las consultas de la tabla SDB_ACC_LIQUIDACION_ITEM.
 *
 * @author garias
 */
public class LiquidacionItemDAO extends BaseDAO
{
	/** Constante cs_FIND_BY_ID_TURNO. */
	private static final String cs_FIND_BY_ID_TURNO = "SELECT * FROM SDB_ACC_LIQUIDACION_ITEM WHERE ID_TURNO = ? ";

	/** Constante cs_FIND_ACTO_LIQUIDACION. */
	private static final String cs_FIND_ACTO_LIQUIDACION = " SELECT  LI.ID_LIQUIDACION , LI.CONSECUTIVO,LI.ID_ITEM, LI.VALOR_TOTAL FROM SDB_ACC_LIQUIDACION  AL  "
		+ "INNER JOIN SDB_ACC_LIQUIDACION_ITEM LI ON LI.ID_LIQUIDACION = AL.ID_LIQUIDACION AND LI.CONSECUTIVO = AL.CONSECUTIVO  WHERE AL.ID_SOLICITUD =?  ";

	/** Constante cs_DELETE. */
	private static final String cs_DELETE = " DELETE FROM SDB_ACC_LIQUIDACION_ITEM WHERE ID_LIQUIDACION = ? AND CONSECUTIVO = ? AND ID_ITEM = ? ";

	/** Constante cs_EJECUCUCION_LIQUIDACION. */
	private static final String cs_EJECUCUCION_LIQUIDACION = " SELECT COUNT(*)TOTAL FROM SDB_ACC_LIQUIDACION_ITEM WHERE ID_LIQUIDACION = ? AND CONSECUTIVO = 1";

	/** Constante cs_FIND_LIQUIDACION_BY_ACTO_TURNO. */
	private static final String cs_FIND_LIQUIDACION_BY_ACTO_TURNO = " SELECT * FROM SDB_ACC_LIQUIDACION_ITEM WHERE ID_ACTO = ? AND ID_TURNO = ? ORDER BY CONSECUTIVO DESC";

	/** Constante cs_FIND_BY_ACTO_TURNO_LIQUI_CONSE. */
	private static final String cs_FIND_BY_ACTO_TURNO_LIQUI_CONSE = " SELECT * FROM SDB_ACC_LIQUIDACION_ITEM WHERE ID_ACTO = ? AND ID_TURNO = ? AND ID_LIQUIDACION = ? AND CONSECUTIVO = ?";

	/** Constante cs_FIND_BY_ACTO_CIRCULO_LIQUI_CONSE. */
	private static final String cs_FIND_BY_ACTO_CIRCULO_LIQUI_CONSE = " SELECT * FROM SDB_ACC_LIQUIDACION_ITEM WHERE ID_ACTO = ? AND ID_CIRCULO = ? AND ID_LIQUIDACION = ? AND CONSECUTIVO = ?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_ACC_LIQUIDACION_ITEM (ID_LIQUIDACION, CONSECUTIVO, ID_ITEM, ID_ACTO, ID_CIRCULO, ID_TIPO_ACTO, "
		+ " VERSION, ID_PROCESO, ID_SUBPROCESO, ID_TIPO_TARIFA_REGISTRAL, VERSION_TARIFA_REGISTRAL, ID_TARIFA_FIJA, VERSION_TARIFA_FIJA, ID_SALARIO, ID_RANGO, "
		+ " CANTIDAD, VALOR, VALOR_IMPUESTOS, ID_CONDICION_TARIFA, VALOR_FINAL, VALOR_IMPUESTOS_FINAL, VALOR_TOTAL, MOTIVO_MAYOR_VALOR, VALOR_MAYOR_VALOR, "
		+ " VALOR_IMPUESTO_MAYOR_VALOR, TOTAL_MAYOR_VALOR, MOTIVO_SALDO_FAVOR, VALOR_SALDO_FAVOR, VALOR_IMPUESTO_SALDO_FAVOR, TOTAL_SALDO_FAVOR, "
		+ " ACTIVO, ID_TARIFA_CONSERV_DOCUMENTAL, VERSION_TARIFA_CONSERV_DOCUMENTAL, VALOR_CONSERV_DOCUMENTAL, ID_TURNO, ID_USUARIO_CREACION, "
		+ " FECHA_CREACION, IP_CREACION) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,SYSTIMESTAMP,?)";

	/** Constante cs_BUSCAR_POR_ID_LIQUIDACION. */
	private static final String cs_BUSCAR_POR_ID_LIQUIDACION = "SELECT * FROM SDB_ACC_LIQUIDACION_ITEM WHERE ID_LIQUIDACION = ? ";

	/** Constante cs_ELIMINAR_ITEM_POR_ACTO. */
	private static final String cs_ELIMINAR_ITEM_POR_ACTO = "DELETE FROM SDB_ACC_LIQUIDACION_ITEM WHERE ID_ACTO = ?";

	/** Constante cs_BUSCAR_ITEM_POR_ACTO. */
	private static final String cs_BUSCAR_ITEM_POR_ACTO = "SELECT * FROM SDB_ACC_LIQUIDACION_ITEM WHERE ID_ACTO = ?";

	/**
	 * Metodo encargado de consultar todos los registros de la tabla SDB_ACC_LIQUIDACION_ITEM para un ID_LIQUIDACION específico.
	 *
	 * @param al_liquidacion Argumento de tipo <code>Liquidacion</code> que contiene los criterios necesarios para realizar la consulta.
	 * @return Colección de Liquidacion que contiene los resultados que coinciden con los criterios de consulta.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Liquidacion
	 */
	public Collection<Liquidacion> buscarLiquidacionItem(Liquidacion al_liquidacion)
	    throws B2BException
	{
		Collection<Liquidacion> ls_object;
		PreparedStatement       lps_ps;
		ResultSet               lrs_rs;

		ls_object     = new ArrayList<Liquidacion>();
		lps_ps        = null;
		lrs_rs        = null;

		if(al_liquidacion != null)
		{
			try
			{
				int           li_column;
				StringBuilder lsb_sb;
				int           li_consecutivo;
				boolean       lb_consecutivo;
				String        ls_tipoMayorValor;
				boolean       lb_tipoMayorValor;

				li_column             = 1;
				lsb_sb                = new StringBuilder(cs_BUSCAR_POR_ID_LIQUIDACION);
				li_consecutivo        = al_liquidacion.getConsecutivo();
				lb_consecutivo        = li_consecutivo >= 0;
				ls_tipoMayorValor     = al_liquidacion.getIdTipoMayorValor();
				lb_tipoMayorValor     = StringUtils.isValidString(ls_tipoMayorValor)
						&& !ls_tipoMayorValor.equalsIgnoreCase(IdentificadoresCommon.N);

				if(lb_consecutivo)
					lsb_sb.append(" AND CONSECUTIVO = ?");

				if(lb_tipoMayorValor)
					lsb_sb.append(" AND IND_MAYOR_VALOR IS NOT NULL");

				lps_ps = getConnection().prepareStatement(lsb_sb.toString());

				lps_ps.setString(li_column++, al_liquidacion.getIdLiquidacion());

				if(lb_consecutivo)
					lps_ps.setInt(li_column++, li_consecutivo);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					ls_object.add(getObjectFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "buscarLiquidacionItem", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return ls_object;
	}

	/**
	 * Metodo encargado de consultar todos los registros de SDB_ACC_LIQUIDACION_ITEM por ID_ACTO.
	 *
	 * @param al_parametros Argumento de tipo Liquidacion que contiene todos los criterios necesarios para realizar la búsqueda.
	 * @return Objeto de tipo Liquidacion que contiene contiene todos datos que cumplen con los criterios de búsqueda.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Liquidacion
	 */
	public Collection<Liquidacion> buscarLiquidacionItemByActo(Liquidacion al_parametros)
	    throws B2BException
	{
		Collection<Liquidacion> lcl_liquidacion;
		PreparedStatement       lps_ps;
		ResultSet               lrs_rs;

		lcl_liquidacion     = new ArrayList<Liquidacion>();
		lps_ps              = null;
		lrs_rs              = null;

		if(al_parametros != null)
		{
			try
			{
				lps_ps = getConnection().prepareStatement(cs_BUSCAR_ITEM_POR_ACTO);

				lps_ps.setString(1, al_parametros.getIdActo());

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcl_liquidacion.add(getObjetFromResultSet(lrs_rs, true));
			}
			catch(SQLException lse_e)
			{
				logError(this, "buscarLiquidacionItemByActo", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lcl_liquidacion;
	}

	/**
	 * Metodo encargado de consultar todos los registros de la tabla SDB_ACC_LIQUIDACION_ITEM para un ID_LIQUIDACION específico.
	 *
	 * @param al_liquidacion Argumento de tipo <code>Liquidacion</code> que contiene los criterios necesarios para realizar la consulta.
	 * @return Liquidacion que contiene los resultados que coinciden con los criterios de consulta.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Liquidacion
	 */
	public Liquidacion buscarLiquidacionItemTipoActo(Liquidacion al_liquidacion)
	    throws B2BException
	{
		Liquidacion       ls_object;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		ls_object     = null;
		lps_ps        = null;
		lrs_rs        = null;

		if(al_liquidacion != null)
		{
			try
			{
				int           li_column;
				StringBuilder lsb_sb;

				li_column     = 1;
				lsb_sb        = new StringBuilder(cs_BUSCAR_POR_ID_LIQUIDACION);

				lsb_sb.append(" AND CONSECUTIVO = '1' AND ID_CIRCULO = ? AND ID_TIPO_ACTO = ? ");

				lps_ps = getConnection().prepareStatement(lsb_sb.toString());

				lps_ps.setString(li_column++, al_liquidacion.getIdLiquidacion());
				lps_ps.setString(li_column++, al_liquidacion.getIdCirculo());
				lps_ps.setString(li_column++, al_liquidacion.getIdTipoActo());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ls_object = getObjectFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "buscarLiquidacionItemTipoActo", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return ls_object;
	}

	/**
	 * Elimina el registro en la tabla.
	 *
	 * @param at_param correspondiente al valor del tipo de objeto Liquidacion
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void deleteLiquidacionItem(Liquidacion at_param)
	    throws B2BException
	{
		if(at_param != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_DELETE);

				lps_ps.setString(li_column++, at_param.getIdLiquidacion());
				lps_ps.setInt(li_column++, at_param.getConsecutivo());
				lps_ps.setString(li_column++, at_param.getIdItem());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "deleteLiquidacionItem", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Método encargado de eliminar un registro en la tabla LIQUIDACION_ITEM por ID_ACTO.
	 *
	 * @param as_idActo Argumento de tipo <code>String</code> que contiene el item a eliminar.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void eliminarItemPorActo(String as_idActo)
	    throws B2BException
	{
		if(StringUtils.isValidString(as_idActo))
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_ELIMINAR_ITEM_POR_ACTO);

				lps_ps.setString(1, as_idActo);

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "eliminarItemPorActo", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Metodo encargado de consultar todos los registros de SDB_ACC_LIQUIDACION_ITEM por ID_ACTO, ID_CIRCULO, ID_LIQUIDACION, CONSECUTIVO.
	 *
	 * @param al_parametros Argumento de tipo Liquidacion que contiene todos los criterios necesarios para realizar la búsqueda.
	 * @return Objeto de tipo Liquidacion que contiene contiene todos datos que cumplen con los criterios de búsqueda.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Liquidacion
	 */
	public Liquidacion findByActoCirculoLiquidacionConsecutivo(Liquidacion al_parametros)
	    throws B2BException
	{
		Liquidacion       ll_liquidacion;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		ll_liquidacion     = null;
		lps_ps             = null;
		lrs_rs             = null;

		if(al_parametros != null)
		{
			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_FIND_BY_ACTO_CIRCULO_LIQUI_CONSE);

				lps_ps.setString(li_column++, al_parametros.getIdActo());
				lps_ps.setString(li_column++, al_parametros.getIdCirculo());
				lps_ps.setString(li_column++, al_parametros.getIdLiquidacion());
				lps_ps.setInt(li_column++, al_parametros.getConsecutivo());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ll_liquidacion = getObjectFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByActoCirculoLiquidacionConsecutivo", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return ll_liquidacion;
	}

	/**
	 * Metodo encargado de consultar todos los registros de SDB_ACC_LIQUIDACION_ITEM por ID_ACTO, ID_TURNO.
	 *
	 * @param al_parametros Argumento de tipo Liquidacion que contiene todos los criterios necesarios para realizar la búsqueda.
	 * @return Objeto de tipo Liquidacion que contiene contiene todos datos que cumplen con los criterios de búsqueda.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Liquidacion
	 */
	public Liquidacion findByActoTurno(Liquidacion al_parametros)
	    throws B2BException
	{
		Liquidacion       ll_liquidacion;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		ll_liquidacion     = null;
		lps_ps             = null;
		lrs_rs             = null;

		if(al_parametros != null)
		{
			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_FIND_LIQUIDACION_BY_ACTO_TURNO);

				lps_ps.setString(li_column++, al_parametros.getIdActo());
				lps_ps.setString(li_column++, al_parametros.getIdTurno());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ll_liquidacion = getObjetFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByActoTurno", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return ll_liquidacion;
	}

	/**
	 * Metodo encargado de consultar todos los registros de SDB_ACC_LIQUIDACION_ITEM por ID_ACTO, ID_TURNO, ID_LIQUIDACION, CONSECUTIVO.
	 *
	 * @param al_parametros Argumento de tipo Liquidacion que contiene todos los criterios necesarios para realizar la búsqueda.
	 * @return Objeto de tipo Liquidacion que contiene contiene todos datos que cumplen con los criterios de búsqueda.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Liquidacion
	 */
	public Liquidacion findByActoTurnoLiquidacionConsecutivo(Liquidacion al_parametros)
	    throws B2BException
	{
		Liquidacion       ll_liquidacion;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		ll_liquidacion     = null;
		lps_ps             = null;
		lrs_rs             = null;

		if(al_parametros != null)
		{
			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_FIND_BY_ACTO_TURNO_LIQUI_CONSE);

				lps_ps.setString(li_column++, al_parametros.getIdActo());
				lps_ps.setString(li_column++, al_parametros.getIdTurno());
				lps_ps.setString(li_column++, al_parametros.getIdLiquidacion());
				lps_ps.setInt(li_column++, al_parametros.getConsecutivo());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ll_liquidacion = getObjetFromResultSet(lrs_rs, true);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByActoTurnoLiquidacionConsecutivo", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return ll_liquidacion;
	}

	/**
	 * Retorna el valor del objeto de Collection de Liquidacion.
	 *
	 * @param at_param correspondiente al valor del tipo de objeto Liquidacion
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Liquidacion
	 */
	public Collection<Liquidacion> findByIdSolicitud(Liquidacion at_param)
	    throws B2BException
	{
		Collection<Liquidacion> ls_object;
		PreparedStatement       lps_ps;
		ResultSet               lrs_rs;

		ls_object     = new ArrayList<Liquidacion>();
		lps_ps        = null;
		lrs_rs        = null;

		if(at_param != null)
		{
			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_FIND_ACTO_LIQUIDACION);

				lps_ps.setString(li_column++, at_param.getIdSolicitud());

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					ls_object.add(getObjetFromResultSet(lrs_rs));
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

		return ls_object;
	}

	/**
	 * Find by id turno.
	 *
	 * @param at_param de at param
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Liquidacion> findByIdTurno(Liquidacion at_param)
	    throws B2BException
	{
		if(at_param != null)
			return findByIdTurno(at_param.getIdTurno());
		else

			return null;
	}

	/**
	 * Find by id turno.
	 *
	 * @param as_idTurno de as id turno
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Liquidacion> findByIdTurno(String as_idTurno)
	    throws B2BException
	{
		Collection<Liquidacion> lcl_liquidacion;
		PreparedStatement       lps_ps;
		ResultSet               lrs_rs;

		lcl_liquidacion = new ArrayList<Liquidacion>();
		;
		lps_ps     = null;
		lrs_rs     = null;

		if(StringUtils.isValidString(as_idTurno))
		{
			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_FIND_BY_ID_TURNO);

				lps_ps.setString(li_column++, as_idTurno);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcl_liquidacion.add(getObjectFromResultSet(lrs_rs));
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

		return lcl_liquidacion;
	}

	/**
	 * Find ejecucion liquidacion.
	 *
	 * @param at_param correspondiente al valor del tipo de objeto Liquidacion
	 * @return verdadero, si se cumple la condicion, de lo contario retorna falso
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public boolean findEjecucionLiquidacion(Liquidacion at_param)
	    throws B2BException
	{
		boolean           ls_object;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		ls_object     = false;
		lps_ps        = null;
		lrs_rs        = null;

		if(at_param != null)
		{
			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_EJECUCUCION_LIQUIDACION);

				lps_ps.setString(li_column++, at_param.getIdLiquidacion());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
				{
					if(lrs_rs.getInt("TOTAL") > 0)
						ls_object = true;
				}
			}
			catch(SQLException lse_e)
			{
				logError(this, "findEjecucionLiquidacion", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return ls_object;
	}

	/**
	 * Metodo encargado de insertar en la tabla SDB_ACC_LIQUIDACION_ITEM.
	 *
	 * @param al_parametros Argumento de tipo Liquidacion que contiene los datos a insertar.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void insert(Liquidacion al_parametros)
	    throws B2BException
	{
		if(al_parametros != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_INSERT);

				lps_ps.setString(li_column++, al_parametros.getIdLiquidacion());
				lps_ps.setInt(li_column++, al_parametros.getConsecutivo());
				lps_ps.setString(li_column++, al_parametros.getIdItem());

				lps_ps.setString(li_column++, al_parametros.getIdActo());
				lps_ps.setString(li_column++, al_parametros.getIdCirculo());
				lps_ps.setString(li_column++, al_parametros.getIdTipoActo());
				lps_ps.setString(li_column++, al_parametros.getVersion());
				lps_ps.setString(li_column++, al_parametros.getIdProceso());
				lps_ps.setString(li_column++, al_parametros.getIdSubproceso());
				lps_ps.setString(li_column++, al_parametros.getIdTipoTarifaRegistral());
				setLong(lps_ps, al_parametros.getVersionTarifaRegistral(), li_column++);
				lps_ps.setString(li_column++, al_parametros.getIdTarifaFija());
				setLong(lps_ps, al_parametros.getVersionTarifaFija(), li_column++);
				lps_ps.setString(li_column++, al_parametros.getIdSalario());
				lps_ps.setString(li_column++, al_parametros.getIdRango());
				setLong(lps_ps, al_parametros.getCantidad(), li_column++);
				setDouble(lps_ps, NumericUtils.getDoubleWrapper(al_parametros.getValor()), li_column++);
				setDouble(lps_ps, NumericUtils.getDoubleWrapper(al_parametros.getValorImpuestos()), li_column++);
				lps_ps.setString(li_column++, al_parametros.getIdCondicionTarifa());
				setDouble(lps_ps, NumericUtils.getDoubleWrapper(al_parametros.getValorFinal()), li_column++);
				setDouble(lps_ps, al_parametros.getValorImpuestosFinal(), li_column++);
				setDouble(lps_ps, NumericUtils.getDoubleWrapper(al_parametros.getValorTotal()), li_column++);
				lps_ps.setString(li_column++, al_parametros.getMotivoMayorValor());
				setDouble(lps_ps, NumericUtils.getDoubleWrapper(al_parametros.getValorMayorValor()), li_column++);
				setDouble(
				    lps_ps, NumericUtils.getDoubleWrapper(al_parametros.getValorImpuestoMayorValor()), li_column++
				);
				setDouble(lps_ps, NumericUtils.getDoubleWrapper(al_parametros.getTotalMayorValor()), li_column++);
				lps_ps.setString(li_column++, al_parametros.getMotivoSaldoFavor());
				setDouble(lps_ps, NumericUtils.getDoubleWrapper(al_parametros.getValorSaldoFavor()), li_column++);
				setDouble(
				    lps_ps, NumericUtils.getDoubleWrapper(al_parametros.getValorImpuestoSaldoFavor()), li_column++
				);
				setDouble(lps_ps, NumericUtils.getDoubleWrapper(al_parametros.getTotalSaldoFavor()), li_column++);
				lps_ps.setString(li_column++, al_parametros.getActivo());
				lps_ps.setString(li_column++, al_parametros.getIdTarifaConservDocumental());
				setDouble(
				    lps_ps, NumericUtils.getDoubleWrapper(al_parametros.getVersionTarifaConservDocumental()),
				    li_column++
				);
				setDouble(
				    lps_ps, NumericUtils.getDoubleWrapper(al_parametros.getValorConservDocumental()), li_column++
				);
				lps_ps.setString(li_column++, al_parametros.getIdTurno());
				lps_ps.setString(li_column++, al_parametros.getIdUsuarioCreacion());
				lps_ps.setString(li_column++, al_parametros.getIpCreacion());

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
	 * Metodo encargado de cargar los datos consultados y almacenados en el objeto de tipo <code>ResultSet</code>,
	 * en un objeto de tipo <code>Liquidacion</code>.
	 *
	 * @param lrs_rs Argumento de tipo <code>ResultSet</code> que contiene los datos consultados.
	 * @return Objeto de tipo <code>Liquidacion</code> que retornará los datos consultados.
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private Liquidacion getObjectFromResultSet(ResultSet lrs_rs)
	    throws SQLException
	{
		Liquidacion ll_liquidacion;

		ll_liquidacion = new Liquidacion();

		ll_liquidacion.setIdLiquidacion(lrs_rs.getString("ID_LIQUIDACION"));
		ll_liquidacion.setConsecutivo(lrs_rs.getInt("CONSECUTIVO"));
		ll_liquidacion.setIdItem(lrs_rs.getString("ID_ITEM"));
		ll_liquidacion.setIdActo(lrs_rs.getString("ID_ACTO"));
		ll_liquidacion.setIdCirculo(lrs_rs.getString("ID_CIRCULO"));
		ll_liquidacion.setIdTipoActo(lrs_rs.getString("ID_TIPO_ACTO"));
		ll_liquidacion.setVersion(lrs_rs.getString("VERSION"));
		ll_liquidacion.setIdProceso(lrs_rs.getString("ID_PROCESO"));
		ll_liquidacion.setIdSubproceso(lrs_rs.getString("ID_SUBPROCESO"));
		ll_liquidacion.setIdTipoTarifaRegistral(lrs_rs.getString("ID_TIPO_TARIFA_REGISTRAL"));
		ll_liquidacion.setVersionTarifaRegistral(getLong(lrs_rs, "VERSION_TARIFA_REGISTRAL"));
		ll_liquidacion.setIdTarifaFija(lrs_rs.getString("ID_TARIFA_FIJA"));
		ll_liquidacion.setVersionTarifaFija(getLong(lrs_rs, "VERSION_TARIFA_FIJA"));
		ll_liquidacion.setIdSalario(lrs_rs.getString("ID_SALARIO"));
		ll_liquidacion.setIdRango(lrs_rs.getString("ID_RANGO"));
		ll_liquidacion.setCantidad(getLong(lrs_rs, "CANTIDAD"));
		ll_liquidacion.setValor(getBigDecimal(lrs_rs, "VALOR"));
		ll_liquidacion.setValorImpuestos(getBigDecimal(lrs_rs, "VALOR_IMPUESTOS"));
		ll_liquidacion.setIdCondicionTarifa(lrs_rs.getString("ID_CONDICION_TARIFA"));
		ll_liquidacion.setValorFinal(getDouble(lrs_rs, "VALOR_FINAL"));
		ll_liquidacion.setValorImpuestoFinal(getBigDecimal(lrs_rs, "VALOR_IMPUESTOS_FINAL"));
		ll_liquidacion.setValorTotal(getBigDecimal(lrs_rs, "VALOR_TOTAL"));
		ll_liquidacion.setMotivoMayorValor(lrs_rs.getString("MOTIVO_MAYOR_VALOR"));
		ll_liquidacion.setValorMayorValor(getBigDecimal(lrs_rs, "VALOR_MAYOR_VALOR"));
		ll_liquidacion.setValorImpuestoMayorValor(getBigDecimal(lrs_rs, "VALOR_IMPUESTO_MAYOR_VALOR"));
		ll_liquidacion.setTotalMayorValor(getBigDecimal(lrs_rs, "TOTAL_MAYOR_VALOR"));
		ll_liquidacion.setMotivoSaldoFavor(lrs_rs.getString("MOTIVO_SALDO_FAVOR"));
		ll_liquidacion.setValorSaldoFavor(getBigDecimal(lrs_rs, "VALOR_SALDO_FAVOR"));
		ll_liquidacion.setValorImpuestoSaldoFavor(getBigDecimal(lrs_rs, "VALOR_IMPUESTO_SALDO_FAVOR"));
		ll_liquidacion.setTotalSaldoFavor(getBigDecimal(lrs_rs, "TOTAL_SALDO_FAVOR"));
		ll_liquidacion.setActivo(lrs_rs.getString("ACTIVO"));
		ll_liquidacion.setIdUsuarioCreacion(lrs_rs.getString("ID_USUARIO_CREACION"));
		ll_liquidacion.setFechaCreacion(lrs_rs.getTimestamp("FECHA_CREACION"));
		ll_liquidacion.setIpCreacion(lrs_rs.getString("IP_CREACION"));
		ll_liquidacion.setIdUsuarioModificacion(lrs_rs.getString("ID_USUARIO_MODIFICACION"));
		ll_liquidacion.setFechaModificacion(lrs_rs.getTimestamp("FECHA_MODIFICACION"));
		ll_liquidacion.setIpModificacion(lrs_rs.getString("IP_MODIFICACION"));
		ll_liquidacion.setIdTarifaConservDocumental(lrs_rs.getString("ID_TARIFA_CONSERV_DOCUMENTAL"));
		ll_liquidacion.setVersionTarifaConservDocumental(getLong(lrs_rs, "VERSION_TARIFA_CONSERV_DOCUMENTAL"));
		ll_liquidacion.setValorConservDocumental(getDouble(lrs_rs, "VALOR_CONSERV_DOCUMENTAL"));
		ll_liquidacion.setIdTurno(lrs_rs.getString("ID_TURNO"));
		ll_liquidacion.setIndMayorValor(lrs_rs.getString("IND_MAYOR_VALOR"));

		return ll_liquidacion;
	}

	/**
	 * Retorna el valor de Liquidacion.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de Liquidacion
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private Liquidacion getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		return getObjetFromResultSet(ars_rs, false);
	}

	/**
	 * Retorna el valor de Liquidacion.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @param ab_valores correspondiente al valor del tipo de objeto boolean
	 * @return el valor de Liquidacion
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private Liquidacion getObjetFromResultSet(ResultSet ars_rs, boolean ab_valores)
	    throws SQLException
	{
		Liquidacion ll_liquidacion;

		ll_liquidacion = new Liquidacion();

		ll_liquidacion.setIdLiquidacion(ars_rs.getString("ID_LIQUIDACION"));
		ll_liquidacion.setConsecutivo(ars_rs.getInt("CONSECUTIVO"));
		ll_liquidacion.setIdItem(ars_rs.getString("ID_ITEM"));
		ll_liquidacion.setValorMayorValor(getBigDecimal(ars_rs, "VALOR_MAYOR_VALOR"));
		ll_liquidacion.setValorSaldoFavor(getBigDecimal(ars_rs, "VALOR_SALDO_FAVOR"));
		ll_liquidacion.setValorTotal(getBigDecimal(ars_rs, "VALOR_TOTAL"));

		if(ab_valores)
			ll_liquidacion.setTotalMayorValor(getBigDecimal(ars_rs, "TOTAL_MAYOR_VALOR"));

		return ll_liquidacion;
	}
}
