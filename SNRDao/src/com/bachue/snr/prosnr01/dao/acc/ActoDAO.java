package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.model.devolucion.ActoDevolucionDinero;
import com.bachue.snr.prosnr01.model.sdb.acc.Acto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * Clase para poder realizar transacciones con la base de datos en la tabla SDB_ACC_ACTO.
 *
 * @author Julian Vaca
 */
public class ActoDAO extends BaseDAO
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_ACC_ACTO WHERE ID_ACTO = ?";

	/** Constante cs_FIND_DETALLE_ACTO. */
	private static final String cs_FIND_DETALLE_ACTO = "SELECT AA.ID_CIRCULO, PCR.NOMBRE CIRCULO_REGISTRAL, AA.ID_TIPO_ACTO, PTA.NOMBRE TIPO_ACTO, PTA.ACTO_SIN_CUANTIA, AA.CUANTIA, AA.VALOR_AVALUO, AA.CANTIDAD_ACTOS, ATA.NOMBRE TIPO_ADQUISICION, BD.FECHA_EJECUTORIA, AA.NUMERO_PROCESO, AA.CANTIDAD_ACTOS, AA.PORCENTAJE_TRANSFERENCIA, AA.AREA_TOTAL_INMUEBLE, AA.AREA_TRANSFERIR, AA.PERIODICIDAD_CUANTIA, AA.TIEMPO_CANON FROM SDB_ACC_ACTO AA INNER JOIN SDB_PGN_CIRCULO_REGISTRAL PCR ON PCR.ID_CIRCULO = AA.ID_CIRCULO INNER JOIN SDB_PGN_TIPO_ACTO PTA ON PTA.ID_TIPO_ACTO = AA.ID_TIPO_ACTO INNER JOIN SDB_ACC_TIPO_ADQUISICION ATA ON ATA.ID_TIPO_ADQUISICION = AA.ID_TIPO_ADQUISICION INNER JOIN SDB_ACC_SOLICITUD SAS ON SAS.ID_SOLICITUD = AA.ID_SOLICITUD INNER JOIN SDB_BGN_DOCUMENTO BD ON BD.ID_DOCUMENTO = SAS.ID_DOCUMENTO WHERE AA.ID_ACTO = ?";

	/** Constante cs_FIND_ACTOS_DEV_DINERO_BY_ID_TURNO. */
	private static final String cs_FIND_ACTOS_DEV_DINERO_BY_ID_TURNO = "SELECT TUR.ID_TURNO, LIQ.NIR, ACT.ID_ACTO AS ID_ACTO_DEVOLUCION_DINERO, ACT.VERSION AS VERSION_ACTO, TUR.ID_SOLICITUD AS ID_SOLICITUD_INICIAL, ACT.ID_TIPO_ACTO, TA.NOMBRE AS ESPECIFICACION_ACTO, CASE WHEN NVL(LIQ_ITEM.ID_TARIFA_FIJA,'NULL') = 'NULL' THEN CASE WHEN NVL(LIQ_ITEM.ID_TIPO_TARIFA_REGISTRAL,'NULL') = 'NULL' THEN ACT.ID_TIPO_TARIFA_REGISTRAL ELSE LIQ_ITEM.ID_TIPO_TARIFA_REGISTRAL END ELSE LIQ_ITEM.ID_TARIFA_FIJA END TIPO_TARIFA, ACT.CUANTIA AS CUANTIA_ACTO, ACT.VALOR_AVALUO, ACT.CANTIDAD_ACTOS, LIQ_ITEM.VALOR_FINAL AS VALOR_DERECHOS, LIQ_ITEM.VALOR_CONSERV_DOCUMENTAL, LIQ_ITEM.VALOR_TOTAL AS VALOR_TOTAL_LIQUIDADO FROM SDB_ACC_TURNO TUR INNER JOIN SDB_ACC_ACTO ACT ON TUR.ID_SOLICITUD = ACT.ID_SOLICITUD INNER JOIN SDB_PGN_TIPO_ACTO TA ON ACT.ID_TIPO_ACTO = TA.ID_TIPO_ACTO and ACT.VERSION = TA.VERSION INNER JOIN SDB_ACC_LIQUIDACION LIQ ON TUR.ID_SOLICITUD = LIQ.ID_SOLICITUD INNER JOIN SDB_ACC_LIQUIDACION_ITEM LIQ_ITEM ON LIQ_ITEM.ID_LIQUIDACION = LIQ.ID_LIQUIDACION AND LIQ_ITEM.CONSECUTIVO = LIQ.CONSECUTIVO AND ACT.ID_ACTO = LIQ_ITEM.ID_ACTO AND TUR.ID_TURNO = LIQ_ITEM.ID_TURNO WHERE TUR.ID_TURNO = ? AND LIQ.PAGADA = 'S' AND LIQ.ID_TIPO_ESTADO_LIQUIDACION = '2'";

	/** Constante cs_FIND_BY_ID_AND_SOLICITUD. */
	private static final String cs_FIND_BY_ID_AND_SOLICITUD = "SELECT * FROM SDB_ACC_ACTO WHERE ID_ACTO = ? AND ID_SOLICITUD = ?";

	/** Constante cs_FIND_HIJOS_BY_ID. */
	private static final String cs_FIND_HIJOS_BY_ID = "SELECT * FROM SDB_ACC_ACTO WHERE ID_SOLICITUD  = ? AND ID_ACTO_PRINCIPAL = ? ";

	/** Constante cs_UPDATE_ACT. */
	private static final String cs_UPDATE_ACT = "UPDATE SDB_ACC_ACTO SET ID_SOLICITUD=?, ID_CIRCULO=?, ID_ACTO_PRINCIPAL=?, ID_TIPO_DERECHO_REG=?, ID_TIPO_ACTO=?, CUANTIA=?, VALOR_AVALUO=?,CANTIDAD_ACTOS=?,ID_TIPO_ADQUISICION=?,NUMERO_BOLETA_FISCAL=?,FECHA_VENCIMIENTO=?,ID_USUARIO_MODIFICACION=?,FECHA_MODIFICACION=SYSTIMESTAMP,IP_MODIFICACION=?,AREA_ACTO=?,AREA_TOTAL=?,GARANTIA_HIPOTECARIA=?,HIJUELA_PASIVO=?,ID_TIPO_TARIFA_REGISTRAL=?,ORGANISMO_INTERNACIONAL=?,ACTIVO_PRECALIFICACION=?,VERSION=?,CANTIDAD_CERTIF_ANT_SISTEMA=?,REFERENCIA=?,NUMERO_PROCESO=?,ID_PROCESO=?,ID_SUBPROCESO=?,ID_TIPO_REQUIERE_MATRICULA=?,NUMERO_BOLETA_FISCAL_EXT=?,FECHA_PAGO_IMPUESTO=?,FECHA_PAGO_IMPUESTO_EXT=? WHERE ID_ACTO = ?";

	/** Constante cs_UPDATE_ACT. */
	private static final String cs_UPDATE_ACT_2 = "UPDATE SDB_ACC_ACTO SET ID_USUARIO_MODIFICACION=?,FECHA_MODIFICACION=SYSTIMESTAMP,IP_MODIFICACION=?,ID_PROCESO=?,ID_SUBPROCESO=?,ID_TIPO_REQUIERE_MATRICULA=? WHERE ID_ACTO = ?";

	/** Constante cs_INSERT_ACT. */
	private static final String cs_INSERT_ACT = "INSERT INTO SDB_ACC_ACTO"
		+ "(ID_ACTO, ID_SOLICITUD, ID_CIRCULO,ID_ACTO_PRINCIPAL, ID_TIPO_DERECHO_REG, ID_TIPO_ACTO, CUANTIA, VALOR_AVALUO, CANTIDAD_ACTOS, ID_TIPO_ADQUISICION, NUMERO_BOLETA_FISCAL, FECHA_VENCIMIENTO, FECHA_CREACION, ID_USUARIO_CREACION, AREA_ACTO, AREA_TOTAL, GARANTIA_HIPOTECARIA, HIJUELA_PASIVO, ID_TIPO_TARIFA_REGISTRAL, ORGANISMO_INTERNACIONAL, IP_CREACION, ACTIVO_PRECALIFICACION, VERSION, CANTIDAD_CERTIF_ANT_SISTEMA, REFERENCIA, NUMERO_PROCESO) "
		+ " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,SYSTIMESTAMP, ?, ?, ?, ?, ?, ?, ? , ? , ?, ?, ?, ?, ?)";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_ACC_ACTO SET ID_ACTO_PRINCIPAL = ?, CUANTIA = ? , VALOR_AVALUO=?, CANTIDAD_ACTOS=?, FECHA_VENCIMIENTO=?,FECHA_PAGO_IMPUESTO=?, FECHA_PAGO_IMPUESTO_EXT=?,ID_TIPO_ADQUISICION=?, ID_TIPO_TARIFA_REGISTRAL=?, ID_CIRCULO=?, FECHA_MODIFICACION=SYSTIMESTAMP,ID_USUARIO_MODIFICACION = ?, ACTIVO_PRECALIFICACION = ?, REFERENCIA = ?, NUMERO_PROCESO = ?,NUMERO_BOLETA_FISCAL = ? ,NUMERO_BOLETA_FISCAL_EXT = ?, IP_MODIFICACION = ?,MADRE_CABEZA = ?, RESPUESTA_LEY1943 = ?, PORCENTAJE_TRANSFERENCIA = ?, AREA_TRANSFERIR = ?, AREA_TOTAL_INMUEBLE = ?, PERIODICIDAD_CUANTIA = ?, TIEMPO_CANON = ? ";

	/** Constante cs_UPDATE_ID_CIRCULO. */
	private static final String cs_UPDATE_ID_CIRCULO = "UPDATE SDB_ACC_ACTO SET ID_CIRCULO=?, ID_USUARIO_MODIFICACION=?, FECHA_MODIFICACION=SYSTIMESTAMP, IP_MODIFICACION=? WHERE ID_ACTO_PRINCIPAL = ?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_ACC_ACTO(ID_ACTO ,ID_TIPO_ACTO,ID_SOLICITUD,ID_CIRCULO, ID_ACTO_PRINCIPAL, CUANTIA,VALOR_AVALUO,CANTIDAD_ACTOS,FECHA_VENCIMIENTO,FECHA_PAGO_IMPUESTO,FECHA_PAGO_IMPUESTO_EXT,ID_TIPO_ADQUISICION, ID_TIPO_TARIFA_REGISTRAL, ID_USUARIO_CREACION,FECHA_CREACION,ACTIVO_PRECALIFICACION, REFERENCIA, NUMERO_PROCESO,NUMERO_BOLETA_FISCAL,NUMERO_BOLETA_FISCAL_EXT,IP_CREACION,MADRE_CABEZA,IND_MAYOR_VALOR,RESPUESTA_LEY1943,PORCENTAJE_TRANSFERENCIA,AREA_TRANSFERIR,AREA_TOTAL_INMUEBLE,PERIODICIDAD_CUANTIA,TIEMPO_CANON) "
		+ " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,SYSTIMESTAMP, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	/** Constante cs_FIND_SECUENCE. */
	private static final String cs_FIND_SECUENCE = "  SELECT SEC_ACC_ACTO_ID_ACTO.NEXTVAL FROM DUAL";

	/** Constante cs_UPDATE_ACTO. */
	private static final String cs_UPDATE_ACTO = "UPDATE SDB_ACC_ACTO SET CANTIDAD_CERTIF_ANT_SISTEMA = ?, ID_USUARIO_MODIFICACION = ?, IP_MODIFICACION = ? WHERE ID_ACTO = ?";

	/** Constante cs_UPDATE_CANTIDAD_ACTOS. */
	private static final String cs_UPDATE_CANTIDAD_ACTOS = "UPDATE SDB_ACC_ACTO SET CANTIDAD_ACTOS = ?, ID_USUARIO_MODIFICACION = ?, IP_MODIFICACION = ? ";

	/** Constante cs_FIND_ACTOS_CIRCULOS_BY_ID_SOLICITUD. */
	private static final String cs_FIND_ACTOS_CIRCULOS_BY_ID_SOLICITUD = "SELECT DISTINCT ID_CIRCULO FROM SDB_ACC_ACTO WHERE ID_SOLICITUD = ? ORDER BY ID_CIRCULO DESC";

	/** Constante cs_FIND_BY_ID_SOLICITUD. */
	private static final String cs_FIND_BY_ID_SOLICITUD = "SELECT * FROM SDB_ACC_ACTO WHERE ID_SOLICITUD = ?";

	/** Constante cs_FIND_BY_ID_SOLICITUD IMPUESTO REGISTRO. */
	private static final String cs_FIND_BY_ID_SOLICITUD_IMPUESTO_REGISTRO = "SELECT AC.* FROM SDB_ACC_ACTO AC INNER JOIN SDB_PGN_TIPO_ACTO APT ON APT.ID_TIPO_ACTO = AC.ID_TIPO_ACTO WHERE AC.ID_SOLICITUD = ? AND APT.IMPUESTO_REGISTRO = 'S'";

	/** Constante cs_FIND_BY_ID_ACTO IMPUESTO REGISTRO. */
	private static final String cs_FIND_BY_ID_ACTO_IMPUESTO_REGISTRO = "SELECT AC.* FROM SDB_ACC_ACTO AC INNER JOIN SDB_PGN_TIPO_ACTO APT ON APT.ID_TIPO_ACTO = AC.ID_TIPO_ACTO WHERE AC.ID_ACTO = ? AND APT.IMPUESTO_REGISTRO = 'S'";

	/** Constante cs_FIND_BY_ID_SOLICITUD. */
	private static final String cs_FIND_BY_ID_SOLICITUD_MAYOR_VALOR = "SELECT * FROM SDB_ACC_ACTO WHERE ID_SOLICITUD = ? AND IND_MAYOR_VALOR = 'S'";

	/** Constante cs_FIND_BY_ID_SOLICITUD_ACTO_PRINCIPAL_IS_NULL. */
	private static final String cs_FIND_BY_ID_SOLICITUD_ACTO_PRINCIPAL_IS_NULL = "SELECT * FROM SDB_ACC_ACTO WHERE ID_SOLICITUD = ? AND ID_ACTO_PRINCIPAL IS NULL";

	/** Constante cs_FIND_BY_ID_SOLICITUD_ID_CIRCULO. */
	private static final String cs_FIND_BY_ID_SOLICITUD_ID_CIRCULO = "SELECT * FROM SDB_ACC_ACTO WHERE ID_SOLICITUD = ? AND ID_CIRCULO = ?";

	/** Constante cs_FIND_BY_ID_SOLICITUD_ACTIVO_PREC. */
	private static final String cs_FIND_BY_ID_SOLICITUD_ACTIVO_PREC = "SELECT * FROM SDB_ACC_ACTO WHERE ID_SOLICITUD = ? AND ID_CIRCULO = ? AND NVL(ACTIVO_PRECALIFICACION, 'S') != 'N'";

	/** Constante cs_FIND_BY_ID_SOLICITUD_TIPOACTO. */
	private static final String cs_FIND_BY_ID_SOLICITUD_TIPOACTO = "SELECT * FROM SDB_ACC_ACTO WHERE ID_SOLICITUD = ? AND ID_TIPO_ACTO = ?";

	/** Constante cs_FIND_BY_ID_SOLICITUD_ID_CIRCULO_TIPOACTO. */
	private static final String cs_FIND_BY_ID_SOLICITUD_ID_CIRCULO_TIPOACTO = "SELECT * FROM SDB_ACC_ACTO WHERE ID_SOLICITUD = ? AND ID_CIRCULO = ? AND ID_TIPO_ACTO = ?";

	/** Constante cs_DELETE. */
	private static final String cs_DELETE = "DELETE FROM SDB_ACC_ACTO WHERE ID_ACTO = ?";

	/** Constante cs_DELETE_ACTOS_HIJOS. */
	private static final String cs_DELETE_ACTOS_HIJOS = "DELETE FROM SDB_ACC_ACTO WHERE ID_ACTO_PRINCIPAL = ? AND ID_CIRCULO = ? AND ID_SOLICITUD = ?";

	/** Constante cs_FIND_BY_ID_SOLICITUD_TIPO_ACTO. */
	private static final String cs_FIND_BY_ID_SOLICITUD_TIPO_ACTO = "SELECT * FROM SDB_ACC_ACTO WHERE ID_SOLICITUD = ? AND ID_TIPO_ACTO = ? ";

	/** Constante cs_FIND_ACTOS_BY_ID_TURNO. */
	private static final String cs_FIND_ACTOS_BY_ID_TURNO = "SELECT AA.*, UPPER(PTA.NOMBRE) ESPECIFICACION, PTA.ACTO_SIN_CUANTIA ACTO_SIN_CUANTIA "
		+ "FROM SDB_ACC_ACTO AA "
		+ "INNER JOIN (SELECT ID_ACTO FROM SDB_ACC_SOLICITUD_MATRICULA_ACTO WHERE ID_TURNO = ? GROUP BY ID_ACTO) SMA ON SMA.ID_ACTO = AA.ID_ACTO "
		+ "INNER JOIN SDB_PGN_TIPO_ACTO PTA ON PTA.ID_TIPO_ACTO  = AA.ID_TIPO_ACTO  AND PTA.VERSION = AA.VERSION ";

	/** Constante cs_UPDATE_IND_MAYOR_VALOR. */
	private static final String cs_UPDATE_IND_MAYOR_VALOR = "UPDATE SDB_ACC_ACTO SET IND_MAYOR_VALOR = ?, FECHA_MODIFICACION = SYSTIMESTAMP, ID_USUARIO_MODIFICACION = ?, IP_MODIFICACION = ? WHERE ID_ACTO = ?";

	/** Constante cs_FIND_BY_SOLICITUD_AND_ANOTACION_PREDIO. */
	private static final String cs_FIND_BY_SOLICITUD_AND_ANOTACION_PREDIO = "SELECT A.* FROM SDB_ACC_ACTO A INNER JOIN SDB_ACC_ANOTACION_PREDIO AP ON A.ID_SOLICITUD = AP.ID_SOLICITUD WHERE A.ID_SOLICITUD = ? AND AP.ID_ANOTACION_PREDIO = ?";

	/**
	 * Método encargado de eliminar un registro de la tabla SDB_ACC_ACTO por ID_ACTO.
	 *
	 * @param aa_param Argumento de tipo <code>Acto</code> que contiene el ID_ACTO a eliminar.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void delete(Acto aa_param)
	    throws B2BException
	{
		delete((aa_param != null) ? aa_param.getIdActo() : null);
	}

	/**
	 * Método encargado de eliminar un registro de la tabla SDB_ACC_ACTO por ID_ACTO.
	 *
	 * @param as_idActo Argumento de tipo <code>String</code> que contiene el ID_ACTO a eliminar.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void delete(String as_idActo)
	    throws B2BException
	{
		if(StringUtils.isValidString(as_idActo))
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_DELETE);

				lps_ps.setString(1, as_idActo);

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
	 * Método para eliminar todos los actos hijos de un acto padre.
	 *
	 * @param aa_param objeto Acto padre para obtener  la información para eliminar los hijos
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void deleteActosHijos(com.bachue.snr.prosnr01.model.registro.Acto aa_param)
	    throws B2BException
	{
		if(aa_param != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_DELETE_ACTOS_HIJOS);

				lps_ps.setString(li_column++, aa_param.getIdActoDb());
				lps_ps.setString(li_column++, aa_param.getIdCirculo());
				lps_ps.setString(li_column++, aa_param.getIdSolicitud());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "deleteActosHijos", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Metodo encargado de consultar todos los actos de un turno en las tablas SDB_ACC_ACTO y SDB_ACC_SOLICITUD_MATRICULA_ACTO.
	 *
	 * @param as_idTurno Argumento de tipo String que contiene el turno a consultar.
	 * @return Colección de actos que cumplen con los criterios de busqueda
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public Collection<com.bachue.snr.prosnr01.model.registro.Acto> findActosByIdTurno(String as_idTurno)
	    throws B2BException
	{
		Collection<com.bachue.snr.prosnr01.model.registro.Acto> lca_actos;
		PreparedStatement                                       lps_ps;
		ResultSet                                               lrs_rs;

		lca_actos     = new ArrayList<com.bachue.snr.prosnr01.model.registro.Acto>();
		lps_ps        = null;
		lrs_rs        = null;

		if(StringUtils.isValidString(as_idTurno))
		{
			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_ACTOS_BY_ID_TURNO);

				lps_ps.setString(1, as_idTurno);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lca_actos.add(getObjectFromResultSet(lrs_rs, true));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findActosByIdTurno", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
				close(lrs_rs);
			}
		}

		if(lca_actos.isEmpty())
			lca_actos = null;

		return lca_actos;
	}

	/**
	 * Metodo encargado de consultar todos los actos de un turno en las tablas SDB_ACC_ACTO y SDB_ACC_SOLICITUD_MATRICULA_ACTO
	 * que contengan en el campo IND_MAYOR_VALOR 'I' o 'M'.
	 *
	 * @param as_idTurno Argumento de tipo String que contiene el turno a consultar.
	 * @return Colección de actos que cumplen con los criterios de busqueda
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public Collection<com.bachue.snr.prosnr01.model.registro.Acto> findActosByIdTurnoIndMayorValor(String as_idTurno)
	    throws B2BException
	{
		return findActosByIdTurnoIndMayorValor(as_idTurno, null);
	}

	/**
	 * Metodo encargado de consultar todos los actos de un turno en las tablas SDB_ACC_ACTO y SDB_ACC_SOLICITUD_MATRICULA_ACTO
	 * que contengan en el campo IND_MAYOR_VALOR 'I' o 'M'.
	 *
	 * @param as_idTurno Argumento de tipo String que contiene el turno a consultar.
	 * @param as_indMayorValor Argumento de tipo String que contiene indicador de mayor valor.
	 * @return Colección de actos que cumplen con los criterios de busqueda
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public Collection<com.bachue.snr.prosnr01.model.registro.Acto> findActosByIdTurnoIndMayorValor(
	    String as_idTurno, String as_indMayorValor
	)
	    throws B2BException
	{
		Collection<com.bachue.snr.prosnr01.model.registro.Acto> lca_actos;
		PreparedStatement                                       lps_ps;
		ResultSet                                               lrs_rs;

		lca_actos     = new ArrayList<com.bachue.snr.prosnr01.model.registro.Acto>();
		lps_ps        = null;
		lrs_rs        = null;

		if(StringUtils.isValidString(as_idTurno))
		{
			try
			{
				StringBuilder lsb_sb;
				boolean       lb_indMayorValor;
				int           li_contador;

				lsb_sb               = new StringBuilder(cs_FIND_ACTOS_BY_ID_TURNO);
				lb_indMayorValor     = StringUtils.isValidString(as_indMayorValor);
				li_contador          = 1;

				lsb_sb.append(" AND AA.IND_MAYOR_VALOR ");
				lsb_sb.append(lb_indMayorValor ? "= ?" : "IN ('I', 'M')");

				lps_ps = getConnection().prepareStatement(lsb_sb.toString());

				lps_ps.setString(li_contador++, as_idTurno);

				if(lb_indMayorValor)
					lps_ps.setString(li_contador++, as_indMayorValor);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lca_actos.add(getObjectFromResultSet(lrs_rs, true));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findActosByIdTurnoIndMayorValor", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
				close(lrs_rs);
			}
		}

		if(lca_actos.isEmpty())
			lca_actos = null;

		return lca_actos;
	}

	/**
	 * Método para encontrar todos los circulos de los actos por el idSolicitud.
	 *
	 * @param aa_acto objeto Acto para obtener el idSolicitud
	 * @return Colleccion de Actos
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public Collection<Acto> findActosCirculosBySolicitid(Acto aa_acto)
	    throws B2BException
	{
		Collection<Acto>  lca_actos;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lca_actos     = new ArrayList<Acto>();
		lps_ps        = null;
		lrs_rs        = null;

		if(aa_acto != null)
		{
			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_ACTOS_CIRCULOS_BY_ID_SOLICITUD);

				lps_ps.setString(1, aa_acto.getIdSolicitud());

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
				{
					Acto la_acto;

					la_acto = new Acto();

					la_acto.setIdCirculo(lrs_rs.getString("ID_CIRCULO"));
					lca_actos.add(la_acto);
				}
			}
			catch(SQLException lse_e)
			{
				logError(this, "findActosCirculosBySolicitid", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lca_actos.isEmpty())
			lca_actos = null;

		return lca_actos;
	}

	/**
	 * Método para encontrar todos los actos para bandeja de edvoluciones de dinero por idTurno.
	 *
	 * @param as_idTurno objeto con información para filtrar en la BD
	 * @return Colección de DevolucionesDineroUI
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public Collection<ActoDevolucionDinero> findActosDevDineroByTurno(String as_idTurno)
	    throws B2BException
	{
		Collection<ActoDevolucionDinero> ladd_devDinero;

		ladd_devDinero = new ArrayList<ActoDevolucionDinero>();

		if(StringUtils.isValidString(as_idTurno))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_ACTOS_DEV_DINERO_BY_ID_TURNO);

				lps_ps.setString(1, as_idTurno);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					ladd_devDinero.add(getActoDevDineroFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findActosDevDineroByTurno", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(ladd_devDinero.isEmpty())
			ladd_devDinero = null;

		return ladd_devDinero;
	}

	/**
	 * Retorna el valor del objeto de Acto.
	 *
	 * @param aa_param correspondiente al valor del tipo de objeto Acto
	 * @return devuelve el valor de Acto
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Acto
	 */
	public Acto findById(Acto aa_param)
	    throws B2BException
	{
		return (aa_param != null) ? findById(aa_param.getIdActo()) : null;
	}

	/**
	 * Método para encontrar todos los actos por el idActo.
	 *
	 * @param as_idActo correspondiente al valor del tipo de objeto String
	 * @return objeto Acto
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Acto
	 */
	public Acto findById(String as_idActo)
	    throws B2BException
	{
		Acto              la_object;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		la_object     = null;
		lps_ps        = null;
		lrs_rs        = null;

		if(StringUtils.isValidString(as_idActo))
		{
			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(1, as_idActo);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					la_object = getObjetFromResultSet(lrs_rs);
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

		return la_object;
	}

	/**
	 * Método para encontrar todos los actos por el id acto con tipo acto impuesto registro en S.
	 *
	 * @param as_idActo Objeto contenedor del id solicitud a utilizar como filtro en la busqueda
	 * @return Colección de actos resultante de la consulta
	 * @throws B2BException si ocurre un error en la ejecución o extracción de información de la consulta
	 */
	public Acto findByIdActoImpuestoRegistro(String as_idActo)
	    throws B2BException
	{
		Acto la_acto;

		la_acto = new Acto();

		if(StringUtils.isValidString(as_idActo))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_ACTO_IMPUESTO_REGISTRO);

				lps_ps.setString(1, as_idActo);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					la_acto = getObjetFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdActoImpuestoRegistro", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return la_acto;
	}

	/**
	 * Método para encontrar todos los actos por el idActo.
	 *
	 * @param aa_param objeto con información para filtrar en la BD
	 * @return objeto Acto
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Acto
	 */
	public Acto findByIdAndSolicitud(Acto aa_param)
	    throws B2BException
	{
		Acto              la_object;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		la_object     = null;
		lps_ps        = null;
		lrs_rs        = null;

		if(aa_param != null)
		{
			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_FIND_BY_ID_AND_SOLICITUD);

				lps_ps.setString(li_column++, aa_param.getIdActo());
				lps_ps.setString(li_column++, aa_param.getIdSolicitud());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					la_object = getObjetFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdAndSolicitud", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return la_object;
	}

	/**
	 * Método para encontrar todos los actos por el idSolicitud.
	 *
	 * @param aa_param objeto con información para filtrar en la BD
	 * @return Colección de Actos
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public Collection<Acto> findByIdSolicitud(Acto aa_param)
	    throws B2BException
	{
		return (aa_param != null) ? findByIdSolicitud(aa_param.getIdSolicitud()) : null;
	}

	/**
	 * Método para encontrar todos los actos por el idSolicitud.
	 *
	 * @param as_idSolicitud Objeto contenedor del id solicitud a utilizar como filtro en la busqueda
	 * @return Colección de actos resultante de la consulta
	 * @throws B2BException si ocurre un error en la ejecución o extracción de información de la consulta
	 */
	public Collection<Acto> findByIdSolicitud(String as_idSolicitud)
	    throws B2BException
	{
		Collection<Acto> lca_actos;

		lca_actos = new ArrayList<Acto>();

		if(StringUtils.isValidString(as_idSolicitud))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_SOLICITUD);

				lps_ps.setString(1, as_idSolicitud);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lca_actos.add(getObjetFromResultSet(lrs_rs));
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

		if(lca_actos.isEmpty())
			lca_actos = null;

		return lca_actos;
	}

	/**
	 * Método para encontrar todos los actos por el idSolicitud y por el idCirculo.
	 *
	 * @param aa_param objeto con información para filtrar en la BD
	 * @return Colección de Actos
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public Collection<Acto> findByIdSolicitudActivoPrec(Acto aa_param)
	    throws B2BException
	{
		Collection<Acto>  lca_actos;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lca_actos     = new ArrayList<Acto>();
		lps_ps        = null;
		lrs_rs        = null;

		if(aa_param != null)
		{
			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_SOLICITUD_ACTIVO_PREC);

				lps_ps.setString(1, aa_param.getIdSolicitud());
				lps_ps.setString(2, aa_param.getIdCirculo());

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lca_actos.add(getObjetFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdSolicitudActivoPrec", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lca_actos.isEmpty())
			lca_actos = null;

		return lca_actos;
	}

	/**
	 * Método para encontrar todos los actos por el idSolicitud y por el idCirculo.
	 *
	 * @param aa_param objeto con información para filtrar en la BD
	 * @return Colección de Actos de registro.
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public Collection<com.bachue.snr.prosnr01.model.registro.Acto> findByIdSolicitudActivoPrecRegistro(
	    com.bachue.snr.prosnr01.model.registro.Acto aa_param
	)
	    throws B2BException
	{
		Collection<com.bachue.snr.prosnr01.model.registro.Acto> lca_actos;
		PreparedStatement                                       lps_ps;
		ResultSet                                               lrs_rs;

		lca_actos     = new ArrayList<com.bachue.snr.prosnr01.model.registro.Acto>();
		lps_ps        = null;
		lrs_rs        = null;

		if(aa_param != null)
		{
			try
			{
				int li_contador;

				li_contador     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_SOLICITUD_ACTIVO_PREC);

				lps_ps.setString(li_contador++, aa_param.getIdSolicitud());
				lps_ps.setString(li_contador++, aa_param.getIdCirculo());

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lca_actos.add(getObjectFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdSolicitudActivoPrec", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lca_actos.isEmpty())
			lca_actos = null;

		return lca_actos;
	}

	/**
	 * Método para encontrar todos los actos por el idSolicitud cuando el idActoPrincipal es <code>null</code>.
	 *
	 * @param aa_param objeto con información para filtrar en la BD
	 * @return Colección de Actos
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Acto
	 */
	public Acto findByIdSolicitudActoPrincipalIsNull(Acto aa_param)
	    throws B2BException
	{
		Acto la_acto;

		la_acto = null;

		if(aa_param != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_SOLICITUD_ACTO_PRINCIPAL_IS_NULL);

				lps_ps.setString(1, aa_param.getIdSolicitud());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					la_acto = getObjetFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdSolicitudActoPrincipalIsNull", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return la_acto;
	}

	/**
	 * Find by id solicitud circulo.
	 *
	 * @param aa_param de aa param
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Acto> findByIdSolicitudCirculo(Acto aa_param)
	    throws B2BException
	{
		if(aa_param != null)
			return findByIdSolicitudCirculo(aa_param.getIdSolicitud(), aa_param.getIdCirculo());
		else

			return null;
	}

	/**
	 * Find by id solicitud circulo.
	 *
	 * @param as_idSolicitud de as id solicitud
	 * @param as_idCirculo de as id circulo
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Acto> findByIdSolicitudCirculo(String as_idSolicitud, String as_idCirculo)
	    throws B2BException
	{
		Collection<Acto> lca_actos;

		lca_actos = new ArrayList<Acto>();

		if(StringUtils.isValidString(as_idSolicitud) && StringUtils.isValidString(as_idCirculo))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_count;

				li_count     = 1;
				lps_ps       = getConnection().prepareStatement(cs_FIND_BY_ID_SOLICITUD_ID_CIRCULO);

				lps_ps.setString(li_count++, as_idSolicitud);
				lps_ps.setString(li_count++, as_idCirculo);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lca_actos.add(getObjetFromResultSet(lrs_rs));
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

		if(lca_actos.isEmpty())
			lca_actos = null;

		return lca_actos;
	}

	/**
	 * Método para encontrar todos los actos por el idSolicitud con tipo acto impuesto registro en S.
	 *
	 * @param as_idSolicitud Objeto contenedor del id solicitud a utilizar como filtro en la busqueda
	 * @return Colección de actos resultante de la consulta
	 * @throws B2BException si ocurre un error en la ejecución o extracción de información de la consulta
	 */
	public Collection<Acto> findByIdSolicitudImpuestoRegistro(String as_idSolicitud)
	    throws B2BException
	{
		Collection<Acto> lca_actos;

		lca_actos = new ArrayList<Acto>();

		if(StringUtils.isValidString(as_idSolicitud))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_SOLICITUD_IMPUESTO_REGISTRO);

				lps_ps.setString(1, as_idSolicitud);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lca_actos.add(getObjetFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdSolicitudImpuestoRegistro", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lca_actos.isEmpty())
			lca_actos = null;

		return lca_actos;
	}

	/**
	 * Método para encontrar todos los actos de mayor valor por el idSolicitud.
	 *
	 * @param as_idSolicitud Objeto contenedor del id solicitud a utilizar como filtro en la busqueda
	 * @return Colección de actos resultante de la consulta
	 * @throws B2BException si ocurre un error en la ejecución o extracción de información de la consulta
	 */
	public Collection<Acto> findByIdSolicitudMayorValor(String as_idSolicitud)
	    throws B2BException
	{
		Collection<Acto> lca_actos;

		lca_actos = new ArrayList<Acto>();

		if(StringUtils.isValidString(as_idSolicitud))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_SOLICITUD_MAYOR_VALOR);

				lps_ps.setString(1, as_idSolicitud);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lca_actos.add(getObjetFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdSolicitudMayorValor", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lca_actos.isEmpty())
			lca_actos = null;

		return lca_actos;
	}

	/**
	 * Método para encontrar todos los actos por el idSolicitud,y el idTipoActo.
	 *
	 * @param aa_param objeto con información para filtrar en la BD
	 * @return Acto
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Acto
	 */
	public Acto findByIdSolicitudTipoActo(Acto aa_param)
	    throws B2BException
	{
		Acto la_object;

		la_object = null;

		if(aa_param != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_SOLICITUD_TIPO_ACTO);

				lps_ps.setString(1, aa_param.getIdSolicitud());
				lps_ps.setString(2, aa_param.getIdTipoActo());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					la_object = getObjetFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdSolicitudTipoActo", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return la_object;
	}

	/**
	 * Método para encontrar todos los actos por solicitud y anotacionPredio.
	 *
	 * @param as_idSolicitud correspondiente al valor del tipo de objeto String
	 * @param as_idAnotacionPredio correspondiente al valor del tipo de objeto String
	 * @return Colección de objetos Acto
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Acto
	 */
	public Collection<Acto> findBySolicitudAntPredio(String as_idSolicitud, String as_idAnotacionPredio)
	    throws B2BException
	{
		Collection<Acto>  la_object;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		la_object     = new ArrayList<Acto>();
		lps_ps        = null;
		lrs_rs        = null;

		if(StringUtils.isValidString(as_idSolicitud) && StringUtils.isValidString(as_idAnotacionPredio))
		{
			try
			{
				int li_column;

				li_column     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_SOLICITUD_AND_ANOTACION_PREDIO);

				lps_ps.setString(li_column++, as_idSolicitud);
				lps_ps.setString(li_column++, as_idAnotacionPredio);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					la_object.add(getObjetFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findBySolicitudAntPredio", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return la_object;
	}

	/**
	 * Método para encontrar todos los actos por el idSolicitud, idCirculo e idTipoActo.
	 *
	 * @param aa_param objeto con información para filtrar en la BD
	 * @return Acto
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Acto
	 */
	public Acto findBySolicitudIdCirculoTipoActo(Acto aa_param)
	    throws B2BException
	{
		Acto la_object;

		la_object = null;

		if(aa_param != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_contador;
				li_contador     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_SOLICITUD_ID_CIRCULO_TIPOACTO);

				lps_ps.setString(li_contador++, aa_param.getIdSolicitud());
				lps_ps.setString(li_contador++, aa_param.getIdCirculo());
				lps_ps.setString(li_contador++, aa_param.getIdTipoActo());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					la_object = getObjetFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findBySolicitudIdCirculoTipoActo", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return la_object;
	}

	/**
	 * Método para encontrar todos los actos por el idSolicitud y por el idTipoActo.
	 *
	 * @param aa_param objeto con información para filtrar en la BD
	 * @return Acto
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Acto
	 */
	public Acto findBySolicitudTipoActo(Acto aa_param)
	    throws B2BException
	{
		return (aa_param != null) ? findBySolicitudTipoActo(aa_param.getIdSolicitud(), aa_param.getIdTipoActo()) : null;
	}

	/**
	 * Método para encontrar todos los actos por el idSolicitud y por el idTipoActo.
	 *
	 * @param as_idSolicitud id solicitud a utilizar como filtro en la busqueda
	 * @param as_idTipoActo id tipo acto a utilizar como filtro en la busqueda
	 * @return acto resultante de la consulta
	 * @throws B2BException Si ocurre un error en la conexión o consulta en la base de datos
	 */
	public Acto findBySolicitudTipoActo(String as_idSolicitud, String as_idTipoActo)
	    throws B2BException
	{
		Acto la_object;

		la_object = null;

		if(StringUtils.isValidString(as_idSolicitud) && StringUtils.isValidString(as_idTipoActo))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_SOLICITUD_TIPOACTO);

				lps_ps.setString(1, as_idSolicitud);
				lps_ps.setString(2, as_idTipoActo);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					la_object = getObjetFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findBySolicitudTipoActo", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return la_object;
	}

	/**
	 * Método encargado de consultar la información del acto.
	 *
	 * @param as_idActo Variable que contiene el id del acto a consultar.
	 * @return Mapa que contiene la información del acto.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Map<String, Object> findDetalleActo(String as_idActo)
	    throws B2BException
	{
		Map<String, Object> lmso_return;
		PreparedStatement   lps_ps;
		ResultSet           lrs_rs;

		lmso_return     = new LinkedHashMap<String, Object>();
		lps_ps          = null;
		lrs_rs          = null;

		try
		{
			if(StringUtils.isValidString(as_idActo))
			{
				int               li_columns;
				ResultSetMetaData lrsmd_md;

				lps_ps = getConnection().prepareStatement(cs_FIND_DETALLE_ACTO);

				lps_ps.setString(1, as_idActo);

				lrs_rs         = lps_ps.executeQuery();
				lrsmd_md       = lrs_rs.getMetaData();
				li_columns     = lrsmd_md.getColumnCount();

				if(lrs_rs.next())
				{
					for(int i = 1; i <= li_columns; ++i)
						lmso_return.put(lrsmd_md.getColumnLabel(i), lrs_rs.getObject(i));
				}
			}
		}
		catch(SQLException lse_e)
		{
			logError(this, "findDetalleActo", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return lmso_return;
	}

	/**
	 * Método para encontrar todos los hijos actos por el idSolicitud y por el idActo del papa.
	 *
	 * @param aa_param objeto con información para filtrar en la BD
	 * @return Colección de Actos
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public Collection<com.bachue.snr.prosnr01.model.registro.Acto> findHijosById(
	    com.bachue.snr.prosnr01.model.registro.Acto aa_param
	)
	    throws B2BException
	{
		Collection<com.bachue.snr.prosnr01.model.registro.Acto> lca_actos;

		lca_actos = new ArrayList<com.bachue.snr.prosnr01.model.registro.Acto>();

		if(aa_param != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_HIJOS_BY_ID);

				lps_ps.setString(1, aa_param.getIdSolicitud());
				lps_ps.setString(2, aa_param.getIdActoDb());

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lca_actos.add(getObjectFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findHijosByIdSolicitud", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lca_actos.isEmpty())
			lca_actos = null;

		return lca_actos;
	}

	/**
	 * Método para insertar un registro en la BD.
	 *
	 * @param aa_param objeto Acto con información para insertar en la BD
	 * @return String
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see String
	 */
	public String insert(Acto aa_param)
	    throws B2BException
	{
		String ls_idActo;
		ls_idActo = null;

		if(aa_param != null)
		{
			PreparedStatement lps_ps;
			PreparedStatement lps_secuencia;
			ResultSet         lrs_rs;

			lps_ps            = null;
			lps_secuencia     = null;
			lrs_rs            = null;

			try
			{
				int        li_column;
				Connection lc_connection;

				li_column         = 1;
				lc_connection     = getConnection();

				lps_ps            = lc_connection.prepareStatement(cs_INSERT_ACT);
				lps_secuencia     = lc_connection.prepareStatement(cs_FIND_SECUENCE);

				lrs_rs = lps_secuencia.executeQuery();

				if(lrs_rs.next())
					ls_idActo = StringUtils.getString(lrs_rs.getLong(1));

				lps_ps.setString(li_column++, ls_idActo);
				lps_ps.setString(li_column++, aa_param.getIdSolicitud());
				lps_ps.setString(li_column++, aa_param.getIdCirculo());
				lps_ps.setString(li_column++, aa_param.getIdActoPrincipal());
				lps_ps.setString(li_column++, aa_param.getIdTipoDerechoReg());
				lps_ps.setString(li_column++, aa_param.getIdTipoActo());
				lps_ps.setBigDecimal(li_column++, aa_param.getCuantia());
				lps_ps.setBigDecimal(li_column++, aa_param.getValorAvaluo());
				lps_ps.setBigDecimal(li_column++, aa_param.getCantidadActos());
				lps_ps.setString(li_column++, aa_param.getIdTipoAdquisicion());
				lps_ps.setString(li_column++, aa_param.getNumeroBoletaFiscal());
				setDate(lps_ps, aa_param.getFechaVencimiento(), li_column++);
				lps_ps.setString(li_column++, aa_param.getIdUsuarioCreacion());
				setDouble(lps_ps, aa_param.getAreaActo(), li_column++);
				setDouble(lps_ps, aa_param.getAreaTotal(), li_column++);
				lps_ps.setString(li_column++, aa_param.getGarantiaHipoteca());
				lps_ps.setString(li_column++, aa_param.getHijuelaPasivo());
				lps_ps.setString(li_column++, aa_param.getIdTipoTarifaRegistral());
				lps_ps.setString(li_column++, aa_param.getOrganismoInternacional());
				lps_ps.setString(li_column++, aa_param.getIpCreacion());
				lps_ps.setString(li_column++, aa_param.getActivoPrecalificacion());
				lps_ps.setInt(li_column++, aa_param.getVersion());
				lps_ps.setInt(li_column++, aa_param.getCantidadCertificadosAntSistema());
				lps_ps.setString(li_column++, aa_param.getReferencia());
				lps_ps.setString(li_column++, aa_param.getNumeroProceso());

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

		return ls_idActo;
	}

	/**
	 * Método para insertar o actualizar en la BD.
	 *
	 * @param aa_parametros objeto Acto con información para actualizar o insertar en la BD
	 * @param ab_query booleana para saber si actualizar o insertar
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void insertOrUpdate(com.bachue.snr.prosnr01.model.registro.Acto aa_parametros, boolean ab_query)
	    throws B2BException
	{
		if(aa_parametros != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int     li_column;
				String  ls_indMayorValor;
				boolean lb_indMayorValor;

				li_column            = 1;
				ls_indMayorValor     = aa_parametros.getIndMayorValor();
				lb_indMayorValor     = StringUtils.isValidString(ls_indMayorValor);

				{
					StringBuilder lsb_sb;

					lsb_sb = new StringBuilder(cs_UPDATE);

					if(lb_indMayorValor)
						lsb_sb.append(" ,IND_MAYOR_VALOR = ? ");

					lsb_sb.append(" WHERE ID_ACTO = ? AND ID_TIPO_ACTO = ?  AND ID_SOLICITUD = ? ");

					lps_ps = getConnection().prepareStatement(ab_query ? cs_INSERT : lsb_sb.toString());
				}

				if(ab_query)
				{
					lps_ps.setString(li_column++, aa_parametros.getSecuence());
					lps_ps.setString(li_column++, String.valueOf(aa_parametros.getCodigo()));
					lps_ps.setString(li_column++, aa_parametros.getIdSolicitud());
					lps_ps.setString(li_column++, aa_parametros.getIdCirculo());
					lps_ps.setString(li_column++, aa_parametros.getIdActoPrincipal());
					lps_ps.setBigDecimal(li_column++, aa_parametros.getCuantiaActo());
					lps_ps.setBigDecimal(li_column++, aa_parametros.getValorAvaluo());
					setInteger(lps_ps, aa_parametros.getCantidadActos(), li_column++);
					setDate(lps_ps, aa_parametros.getFechaVencimiento(), li_column++);
					setDate(lps_ps, aa_parametros.getFechaPagoImpuesto(), li_column++);
					setDate(lps_ps, aa_parametros.getFechaPagoImpuestoExtemporaneo(), li_column++);
					lps_ps.setString(li_column++, aa_parametros.getTipoAdquisicion());
					lps_ps.setString(li_column++, "NORMAL");
					lps_ps.setString(li_column++, aa_parametros.getUserId());
					lps_ps.setString(li_column++, aa_parametros.getActivoPrecalificacion());
					lps_ps.setString(li_column++, aa_parametros.getReferencia());
					lps_ps.setString(li_column++, aa_parametros.getNumeroProceso());
					lps_ps.setString(li_column++, aa_parametros.getImpuestoRegistro());
					lps_ps.setString(li_column++, aa_parametros.getImpuestoRegistroExtemporaneo());
					lps_ps.setString(li_column++, aa_parametros.getIpCreacion());
					lps_ps.setString(li_column++, aa_parametros.getMadreCabeza());
					lps_ps.setString(li_column++, aa_parametros.getIndMayorValor());
					lps_ps.setString(li_column++, aa_parametros.getRespuestaLey1943());
					setDouble(lps_ps, aa_parametros.getPorcentajeTransferencia(), li_column++);
					setDouble(lps_ps, aa_parametros.getAreaTransferir(), li_column++);
					setDouble(lps_ps, aa_parametros.getAreaTotalInmueble(), li_column++);
					lps_ps.setString(li_column++, aa_parametros.getPeriodicidadCuantia());
					setLong(lps_ps, aa_parametros.getTiempoCanon(), li_column++);
				}
				else
				{
					lps_ps.setString(li_column++, aa_parametros.getIdActoPrincipal());
					lps_ps.setBigDecimal(li_column++, aa_parametros.getCuantiaActo());
					lps_ps.setBigDecimal(li_column++, aa_parametros.getValorAvaluo());
					setInteger(lps_ps, aa_parametros.getCantidadActos(), li_column++);
					setDate(lps_ps, aa_parametros.getFechaVencimiento(), li_column++);
					setDate(lps_ps, aa_parametros.getFechaPagoImpuesto(), li_column++);
					setDate(lps_ps, aa_parametros.getFechaPagoImpuestoExtemporaneo(), li_column++);
					lps_ps.setString(li_column++, aa_parametros.getTipoAdquisicion());
					lps_ps.setString(li_column++, "NORMAL");
					lps_ps.setString(li_column++, aa_parametros.getIdCirculo());
					lps_ps.setString(li_column++, aa_parametros.getUserId());
					lps_ps.setString(li_column++, aa_parametros.getActivoPrecalificacion());
					lps_ps.setString(li_column++, aa_parametros.getReferencia());
					lps_ps.setString(li_column++, aa_parametros.getNumeroProceso());
					lps_ps.setString(li_column++, aa_parametros.getImpuestoRegistro());
					lps_ps.setString(li_column++, aa_parametros.getImpuestoRegistroExtemporaneo());
					lps_ps.setString(li_column++, aa_parametros.getIpModificacion());
					lps_ps.setString(li_column++, aa_parametros.getMadreCabeza());
					lps_ps.setString(li_column++, aa_parametros.getRespuestaLey1943());
					setDouble(lps_ps, aa_parametros.getPorcentajeTransferencia(), li_column++);
					setDouble(lps_ps, aa_parametros.getAreaTransferir(), li_column++);
					setDouble(lps_ps, aa_parametros.getAreaTotalInmueble(), li_column++);
					lps_ps.setString(li_column++, aa_parametros.getPeriodicidadCuantia());
					setLong(lps_ps, aa_parametros.getTiempoCanon(), li_column++);

					if(lb_indMayorValor)
						lps_ps.setString(li_column++, aa_parametros.getIndMayorValor());

					lps_ps.setString(li_column++, aa_parametros.getSecuence());
					lps_ps.setString(li_column++, aa_parametros.getCodigo());
					lps_ps.setString(li_column++, aa_parametros.getIdSolicitud());
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
	 * Método para actualizar un campo en la BD.
	 *
	 * @param aa_param objeto Acto con información para actualizar en la BD
	 * @return String
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see String
	 */
	public String update(Acto aa_param)
	    throws B2BException
	{
		String ls_idActo;
		ls_idActo = null;

		if(aa_param != null)
		{
			PreparedStatement lps_ps;

			lps_ps = null;

			try
			{
				int        li_column;
				Connection lc_connection;

				li_column         = 1;
				lc_connection     = getConnection();

				lps_ps = lc_connection.prepareStatement(cs_UPDATE_ACT);

				lps_ps.setString(li_column++, aa_param.getIdSolicitud());
				lps_ps.setString(li_column++, aa_param.getIdCirculo());
				lps_ps.setString(li_column++, aa_param.getIdActoPrincipal());
				lps_ps.setString(li_column++, aa_param.getIdTipoDerechoReg());
				lps_ps.setString(li_column++, aa_param.getIdTipoActo());
				lps_ps.setBigDecimal(li_column++, aa_param.getCuantia());
				lps_ps.setBigDecimal(li_column++, aa_param.getValorAvaluo());
				lps_ps.setBigDecimal(li_column++, aa_param.getCantidadActos());
				lps_ps.setString(li_column++, aa_param.getIdTipoAdquisicion());
				lps_ps.setString(li_column++, aa_param.getNumeroBoletaFiscal());
				setDate(lps_ps, aa_param.getFechaVencimiento(), li_column++);
				lps_ps.setString(li_column++, aa_param.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, aa_param.getIpModificacion());
				setDouble(lps_ps, aa_param.getAreaActo(), li_column++);
				setDouble(lps_ps, aa_param.getAreaTotal(), li_column++);
				lps_ps.setString(li_column++, aa_param.getGarantiaHipoteca());
				lps_ps.setString(li_column++, aa_param.getHijuelaPasivo());
				lps_ps.setString(li_column++, aa_param.getIdTipoTarifaRegistral());
				lps_ps.setString(li_column++, aa_param.getOrganismoInternacional());
				lps_ps.setString(li_column++, aa_param.getActivoPrecalificacion());
				lps_ps.setInt(li_column++, aa_param.getVersion());
				lps_ps.setInt(li_column++, aa_param.getCantidadCertificadosAntSistema());
				lps_ps.setString(li_column++, aa_param.getReferencia());
				lps_ps.setString(li_column++, aa_param.getNumeroProceso());
				lps_ps.setString(li_column++, aa_param.getIdProceso());
				lps_ps.setString(li_column++, aa_param.getIdSubproceso());
				lps_ps.setString(li_column++, aa_param.getIdTipoRequiereMatricula());
				lps_ps.setString(li_column++, aa_param.getNumeroBoletaFiscalExt());
				setDate(lps_ps, aa_param.getFechaPagoImpuesto(), li_column++);
				setDate(lps_ps, aa_param.getFechaPagoImpuestoExtemporaneo(), li_column++);
				lps_ps.setString(li_column++, aa_param.getIdActo());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "update", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}

		return ls_idActo;
	}

	/**
	 * Método para actualizar la cantidad de actos.
	 *
	 * @param aa_param objeto Acto con informacion del acto a actualizar
	 * @param ab_b correspondiente al valor del tipo de objeto boolean
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void updateCantidadActos(Acto aa_param, boolean ab_b)
	    throws B2BException
	{
		if(aa_param != null)
		{
			PreparedStatement lps_ps;
			StringBuilder     lsb_sb;

			lps_ps     = null;
			lsb_sb     = new StringBuilder(cs_UPDATE_CANTIDAD_ACTOS);

			try
			{
				int li_column;
				li_column = 1;

				if(ab_b)
					lsb_sb = lsb_sb.append(" WHERE ID_ACTO = ?");
				else
					lsb_sb = lsb_sb.append(" WHERE ID_SOLICITUD = ? AND ID_CIRCULO = ?");

				lps_ps = getConnection().prepareStatement(lsb_sb.toString());

				lps_ps.setBigDecimal(li_column++, aa_param.getCantidadActos());
				lps_ps.setString(li_column++, aa_param.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, aa_param.getIpModificacion());

				if(ab_b)
					lps_ps.setString(li_column++, aa_param.getIdActo());
				else
				{
					lps_ps.setString(li_column++, aa_param.getIdSolicitud());
					lps_ps.setString(li_column++, aa_param.getIdCirculo());
				}

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "updateCantidadActos", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Método para actualizar cantidad de certificados de antiguo sistema.
	 *
	 * @param aa_param objeto Acto con información del campo a actualizar
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void updateCertificadosAntSistemaActo(Acto aa_param)
	    throws B2BException
	{
		if(aa_param != null)
		{
			PreparedStatement lps_ps;

			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_UPDATE_ACTO);

				lps_ps.setInt(li_column++, aa_param.getCantidadCertificadosAntSistema());
				lps_ps.setString(li_column++, aa_param.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, aa_param.getIpModificacion());
				lps_ps.setString(li_column++, aa_param.getIdActo());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "updateCertificadosAntSistemaActo", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Método para actualizar id circulo de los actos hijos de un acto principal.
	 *
	 * @param aa_param objeto Acto con información de acto a actualizar
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void updateCirculoActosHijos(Acto aa_param)
	    throws B2BException
	{
		if(aa_param != null)
		{
			PreparedStatement lps_ps;

			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_UPDATE_ID_CIRCULO);

				lps_ps.setString(li_column++, aa_param.getIdCirculo());
				lps_ps.setString(li_column++, aa_param.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, aa_param.getIpModificacion());
				lps_ps.setString(li_column++, aa_param.getIdActoPrincipal());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "updateCirculoActosHijos", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Método encargado de actualizar el campo IND_MAYOR_VALOR.
	 *
	 * @param aa_parametros Argumento de tipo <code>Acto</code> que contiene los valores a actualizar.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void updateIndMayorValor(Acto aa_parametros)
	    throws B2BException
	{
		if(aa_parametros != null)
		{
			PreparedStatement lps_ps;

			lps_ps = null;

			try
			{
				int li_column;
				li_column     = 1;

				lps_ps = getConnection().prepareStatement(cs_UPDATE_IND_MAYOR_VALOR);

				lps_ps.setString(li_column++, aa_parametros.getIndMayorValor());
				lps_ps.setString(li_column++, aa_parametros.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, aa_parametros.getIpModificacion());
				lps_ps.setString(li_column++, aa_parametros.getIdActo());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "updateIndMayorValor", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Método para actualizar un campo en la BD.
	 *
	 * @param aa_param objeto Acto con información para actualizar en la BD
	 * @return String
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see String
	 */
	public String updateSub(Acto aa_param)
	    throws B2BException
	{
		String ls_idActo;
		ls_idActo = null;

		if(aa_param != null)
		{
			PreparedStatement lps_ps;

			lps_ps = null;

			try
			{
				int        li_column;
				Connection lc_connection;

				li_column         = 1;
				lc_connection     = getConnection();

				lps_ps = lc_connection.prepareStatement(cs_UPDATE_ACT_2);

				lps_ps.setString(li_column++, aa_param.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, aa_param.getIpModificacion());
				lps_ps.setString(li_column++, aa_param.getIdProceso());
				lps_ps.setString(li_column++, aa_param.getIdSubproceso());
				lps_ps.setString(li_column++, aa_param.getIdTipoRequiereMatricula());

				lps_ps.setString(li_column++, aa_param.getIdActo());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "update", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}

		return ls_idActo;
	}

	/**
	 * Asigna los resultados de una consulta a los atributos de un objeto ActoDevolucionDinero.
	 *
	 * @param ars_rs Contenedor de los datos resultantes de la consulta
	 * @return Objeto Acto al cual se le asignan los valores resultante de la
	 * consulta
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private ActoDevolucionDinero getActoDevDineroFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		ActoDevolucionDinero ladd_actoDevolucionDinero;

		ladd_actoDevolucionDinero = new ActoDevolucionDinero();

		ladd_actoDevolucionDinero.setIdTurno(ars_rs.getString("ID_TURNO"));
		ladd_actoDevolucionDinero.setNir(ars_rs.getString("NIR"));
		ladd_actoDevolucionDinero.setIdActoDevolucionDinero(ars_rs.getString("ID_ACTO_DEVOLUCION_DINERO"));
		ladd_actoDevolucionDinero.setVersionActo(ars_rs.getString("VERSION_ACTO"));
		ladd_actoDevolucionDinero.setIdSolicitudInicial(ars_rs.getString("ID_SOLICITUD_INICIAL"));
		ladd_actoDevolucionDinero.setIdTipoActo(ars_rs.getString("ID_TIPO_ACTO"));
		ladd_actoDevolucionDinero.setEspecificacionActo(ars_rs.getString("ESPECIFICACION_ACTO"));
		ladd_actoDevolucionDinero.setTipoTarifaRegistral(ars_rs.getString("TIPO_TARIFA"));
		ladd_actoDevolucionDinero.setCuantiaActo(ars_rs.getLong("CUANTIA_ACTO"));
		ladd_actoDevolucionDinero.setCantidadActos(ars_rs.getLong("CANTIDAD_ACTOS"));
		ladd_actoDevolucionDinero.setValorDerechos(ars_rs.getLong("VALOR_DERECHOS"));
		ladd_actoDevolucionDinero.setValorConservacionDocumental(ars_rs.getLong("VALOR_CONSERV_DOCUMENTAL"));
		ladd_actoDevolucionDinero.setValorTotalLiquidado(ars_rs.getLong("VALOR_TOTAL_LIQUIDADO"));

		return ladd_actoDevolucionDinero;
	}

	/**
	 * Método para extraer los datos de la consulta a la BD, ponerlos en un objeto acto y retornarlo.
	 *
	 * @param ars_rs Objeto con el resultSet de la consulta a la BD
	 * @param ab_datosEspecificos Argumento que determina si se consultan valores especificos cuando se encuentra en true.
	 * @return objeto Acto
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private com.bachue.snr.prosnr01.model.registro.Acto getObjectFromResultSet(
	    ResultSet ars_rs, boolean ab_datosEspecificos
	)
	    throws SQLException
	{
		com.bachue.snr.prosnr01.model.registro.Acto la_acto;

		la_acto = new com.bachue.snr.prosnr01.model.registro.Acto();

		la_acto.setIdActoDb(ars_rs.getString("ID_ACTO"));
		la_acto.setIdSolicitud(ars_rs.getString("ID_SOLICITUD"));
		la_acto.setIdCirculo(ars_rs.getString("ID_CIRCULO"));
		la_acto.setValorAvaluo(getBigDecimal(ars_rs, "VALOR_AVALUO"));
		la_acto.setFechaVencimiento(ars_rs.getTimestamp("FECHA_VENCIMIENTO"));
		la_acto.setActivoPrecalificacion(ars_rs.getString("ACTIVO_PRECALIFICACION"));
		la_acto.setReferencia(ars_rs.getString("REFERENCIA"));
		la_acto.setNumeroProceso(ars_rs.getString("NUMERO_PROCESO"));
		la_acto.setIdActoPrincipal(ars_rs.getString("ID_ACTO_PRINCIPAL"));
		la_acto.setCodigo(ars_rs.getString("ID_TIPO_ACTO"));
		la_acto.setCuantiaActo(getBigDecimal(ars_rs, "CUANTIA"));
		la_acto.setVersion(ars_rs.getLong("VERSION"));
		la_acto.setIdProceso(ars_rs.getString("ID_PROCESO"));
		la_acto.setIdSubProceso(ars_rs.getString("ID_SUBPROCESO"));
		la_acto.setIndMayorValor(ars_rs.getString("IND_MAYOR_VALOR"));
		la_acto.setCantidadActos(getInteger(ars_rs, "CANTIDAD_ACTOS"));
		la_acto.setImpuestoRegistro(ars_rs.getString("NUMERO_BOLETA_FISCAL"));
		la_acto.setImpuestoRegistroExtemporaneo(ars_rs.getString("NUMERO_BOLETA_FISCAL_EXT"));
		la_acto.setFechaPagoImpuesto(ars_rs.getDate("FECHA_PAGO_IMPUESTO"));
		la_acto.setFechaPagoImpuestoExtemporaneo(ars_rs.getDate("FECHA_PAGO_IMPUESTO_EXT"));
		la_acto.setAreaActo(getDouble(ars_rs, "AREA_ACTO"));
		la_acto.setTipoAdquisicion(ars_rs.getString("ID_TIPO_ADQUISICION"));
		la_acto.setFechaVencimiento(ars_rs.getTimestamp("FECHA_VENCIMIENTO"));
		la_acto.setAreaActo(NumericUtils.getDoubleWrapper(ars_rs.getDouble("AREA_ACTO")));
		la_acto.setAreaTotal(NumericUtils.getDoubleWrapper(ars_rs.getDouble("AREA_TOTAL")));
		la_acto.setGarantiaHipotecaria(ars_rs.getString("GARANTIA_HIPOTECARIA"));
		la_acto.setHijuelaPasivo(ars_rs.getString("HIJUELA_PASIVO"));
		la_acto.setIdTipoTarifaRegistral(ars_rs.getString("ID_TIPO_TARIFA_REGISTRAL"));
		la_acto.setOrganismoInternacional(ars_rs.getString("ORGANISMO_INTERNACIONAL"));
		la_acto.setVersion(ars_rs.getInt("VERSION"));
		la_acto.setCantidadCertifAntSistema(getLong(ars_rs, "CANTIDAD_CERTIF_ANT_SISTEMA"));
		la_acto.setMadreCabeza(ars_rs.getString("MADRE_CABEZA"));
		la_acto.setPorcentajeTransferencia(getDouble(ars_rs, "PORCENTAJE_TRANSFERENCIA"));
		la_acto.setAreaTransferir(getDouble(ars_rs, "AREA_TRANSFERIR"));
		la_acto.setAreaTotalInmueble(getDouble(ars_rs, "AREA_TOTAL_INMUEBLE"));
		la_acto.setRespuestaLey1943(ars_rs.getString("RESPUESTA_LEY1943"));
		la_acto.setPeriodicidadCuantia(ars_rs.getString("PERIODICIDAD_CUANTIA"));
		la_acto.setTiempoCanon(getLong(ars_rs, "TIEMPO_CANON"));

		if(ab_datosEspecificos)
		{
			la_acto.setEspecificacion(ars_rs.getString("ESPECIFICACION"));
			la_acto.setActoSinCuantia(ars_rs.getString("ACTO_SIN_CUANTIA"));
		}

		return la_acto;
	}

	/**
	 * Método para extraer los datos de la consulta a la BD, ponerlos en un objeto acto y retornarlo.
	 *
	 * @param ars_rs Objeto con el resultSet de la consulta a la BD
	 * @return objeto Acto
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private com.bachue.snr.prosnr01.model.registro.Acto getObjectFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		return getObjectFromResultSet(ars_rs, false);
	}

	/**
	 * Asigna los resultados de una consulta a los atributos de un objeto Acto.
	 *
	 * @param ars_rs Contenedor de los datos resultantes de la consulta
	 * @return Objeto Acto al cual se le asignan los valores resultante de la
	 * consulta
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private Acto getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		Acto la_acto;

		la_acto = new Acto();

		la_acto.setIdActo(ars_rs.getString("ID_ACTO"));
		la_acto.setIdSolicitud(ars_rs.getString("ID_SOLICITUD"));
		la_acto.setIdCirculo(ars_rs.getString("ID_CIRCULO"));
		la_acto.setIdTipoDerechoReg(ars_rs.getString("ID_TIPO_DERECHO_REG"));
		la_acto.setIdTipoActo(ars_rs.getString("ID_TIPO_ACTO"));
		la_acto.setCuantia(ars_rs.getBigDecimal("CUANTIA"));
		la_acto.setValorAvaluo(getBigDecimal(ars_rs, "VALOR_AVALUO"));
		la_acto.setCantidadActos(getBigDecimal(ars_rs, "CANTIDAD_ACTOS"));
		la_acto.setIdTipoAdquisicion(ars_rs.getString("ID_TIPO_ADQUISICION"));
		la_acto.setNumeroBoletaFiscal(ars_rs.getString("NUMERO_BOLETA_FISCAL"));
		la_acto.setFechaVencimiento(ars_rs.getTimestamp("FECHA_VENCIMIENTO"));
		la_acto.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		la_acto.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		la_acto.setAreaActo(NumericUtils.getDoubleWrapper(ars_rs.getDouble("AREA_ACTO")));
		la_acto.setAreaTotal(NumericUtils.getDoubleWrapper(ars_rs.getDouble("AREA_TOTAL")));
		la_acto.setGarantiaHipoteca(ars_rs.getString("GARANTIA_HIPOTECARIA"));
		la_acto.setHijuelaPasivo(ars_rs.getString("HIJUELA_PASIVO"));
		la_acto.setIdTipoTarifaRegistral(ars_rs.getString("ID_TIPO_TARIFA_REGISTRAL"));
		la_acto.setOrganismoInternacional(ars_rs.getString("ORGANISMO_INTERNACIONAL"));
		la_acto.setIpCreacion(ars_rs.getString("IP_CREACION"));
		la_acto.setActivoPrecalificacion(ars_rs.getString("ACTIVO_PRECALIFICACION"));
		la_acto.setVersion(ars_rs.getInt("VERSION"));
		la_acto.setCantidadCertificadosAntSistema(ars_rs.getInt("CANTIDAD_CERTIF_ANT_SISTEMA"));
		la_acto.setIdActoPrincipal(ars_rs.getString("ID_ACTO_PRINCIPAL"));
		la_acto.setReferencia(ars_rs.getString("REFERENCIA"));
		la_acto.setNumeroProceso(ars_rs.getString("NUMERO_PROCESO"));
		la_acto.setNumeroBoletaFiscalExt(ars_rs.getString("NUMERO_BOLETA_FISCAL_EXT"));
		la_acto.setIdTicket(ars_rs.getString("ID_TICKET"));
		la_acto.setEstadoLiquidacionImpuesto(ars_rs.getString("ESTADO_LIQUIDACION_IMPUESTO"));
		la_acto.setFechaVencimientoImpuesto(ars_rs.getTimestamp("FECHA_VENCIMIENTO_IMPUESTO"));
		la_acto.setFechaPagoImpuesto(ars_rs.getTimestamp("FECHA_PAGO_IMPUESTO"));
		la_acto.setIdProceso(ars_rs.getString("ID_PROCESO"));
		la_acto.setIdSubproceso(ars_rs.getString("ID_SUBPROCESO"));
		la_acto.setIdTipoRequiereMatricula(ars_rs.getString("ID_TIPO_REQUIERE_MATRICULA"));
		la_acto.setMadreCabeza(ars_rs.getString("MADRE_CABEZA"));
		la_acto.setIndMayorValor(ars_rs.getString("IND_MAYOR_VALOR"));
		la_acto.setFechaPagoImpuestoExtemporaneo(ars_rs.getTimestamp("FECHA_PAGO_IMPUESTO_EXT"));

		return la_acto;
	}
}
