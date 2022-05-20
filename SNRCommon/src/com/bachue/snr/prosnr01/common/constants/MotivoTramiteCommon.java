package com.bachue.snr.prosnr01.common.constants;


/**
 * Interface que contiene todos las propiedades MotivoTramiteCommon.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 20/02/2020
 */
public interface MotivoTramiteCommon  
{	
	//Motivo reasignacion de turnos
	/** Propiedad reasignacion. */
	public final long REASIGNACION = 05L;

	//Motivo reasignacion de turnos especial
	/** Propiedad reasignacion especial. */
	public final long REASIGNACION_ESPECIAL = 06L;

	//Motivo Solicitud de Registro
	/** Propiedad firmar. */
	public final long FIRMAR	= 10L;

	//Motivo etapa 10
	/** Propiedad digitalizacion core bachue. */
	public final long DIGITALIZACION_CORE_BACHUE	= 50L;

	//Motivo GENERACION RECIBO CAJA etapa 20
	/** Propiedad generacion recibo caja. */
	public final long GENERACION_RECIBO_CAJA	= 10L;

	//Motivo GENERACION CONSULTAS INMEDIATOS etapa 40
	/** Propiedad entrega consultas en la orip. */
	public final long ENTREGA_CONSULTAS_EN_LA_ORIP			= 10L;

	/** Propiedad entrega consultas por email. */
	public final long ENTREGA_CONSULTAS_POR_EMAIL			= 20L;

	/** Propiedad entrega consultas por correspondencia. */
	public final long ENTREGA_CONSULTAS_POR_CORRESPONDENCIA	= 30L;

	/** Propiedad consulta para entidad exenta. */
	public final long CONSULTA_PARA_ENTIDAD_EXENTA			= 40L;
	
	/** Propiedad ANALISTA_DE_RECURSOS etapa 400. */
	public final long ANALISTA_DE_RECURSOS					= 20L;

	//Motivo ANALISTA_SOLICITUD_CONSULTAS_EXENTAS etapa 41
	/** Propiedad generar consulta para entrega en orip. */
	public final long GENERAR_CONSULTA_PARA_ENTREGA_EN_ORIP				= 10L;

	/** Propiedad generar consulta para entrega por email. */
	public final long GENERAR_CONSULTA_PARA_ENTREGA_POR_EMAIL			= 20L;

	/** Propiedad generar consulta para entrega por correspondencia. */
	public final long GENERAR_CONSULTA_PARA_ENTREGA_POR_CORRESPONDENCIA	= 30L;

	//Motivo etapa 80
	/** Propiedad suspension de tramites. */
	public final long SUSPENSION_DE_TRAMITES             					= 1L;

	/** Propiedad confrontacion correctiva. */
	public final long CONFRONTACION_CORRECTIVA           					= 10L;

	/** Propiedad antiguo sistema. */
	public final long ANTIGUO_SISTEMA                    					= 20L;

	/** Propiedad suspension de terminos. */
	public final long SUSPENSION_DE_TERMINOS             					= 30L;

	/** Propiedad correcciones. */
	public final long CORRECCIONES                       					= 40L;

	/** Propiedad mayor valor. */
	public final long MAYOR_VALOR                        					= 50L;

	/** Propiedad nota devolutiva. */
	public final long NOTA_DEVOLUTIVA                    					= 60L;

	/** Propiedad realizar registro. */
	public final long REALIZAR_REGISTRO					 					= 70L;

	/** Propiedad aprobar secuencias. */
	public final long APROBAR_SECUENCIAS                 					= 80L;

	/** Propiedad digitador masivo. */
	public final long DIGITADOR_MASIVO                   					= 90L;

	/** Propiedad registro digitador masivo. */
	public final long REGISTRO_DIGITADOR_MASIVO          					= 100L;

	/** Propiedad realizar registro parcial. */
	public final long REALIZAR_REGISTRO_PARCIAL          					= 110L;

	/** Propiedad cancelacion registro. */
	public final long CANCELACION_REGISTRO               					= 120L;

	/** Propiedad cancelacion nota devolutiva. */
	public final long CANCELACION_NOTA_DEVOLUTIVA        					= 130L;

	/** Propiedad registro medida cautelar. */
	public final long REGISTRO_MEDIDA_CAUTELAR           					= 140L;

	/** Propiedad nota devolutiva medida cautelar. */
	public final long NOTA_DEVOLUTIVA_MEDIDA_CAUTELAR    					= 150L;

	/** Propiedad reproduccion constancia. */
	public final long REPRODUCCION_CONSTANCIA  			 					= 160L;

	/** Propiedad realizar registro venta parcial. */
	public final long REALIZAR_REGISTRO_VENTA_PARCIAL    					= 170L;

	/** Propiedad realizar registro desenglobe. */
	public final long REALIZAR_REGISTRO_DESENGLOBE    	 					= 180L;

	/** Propiedad desistimiento. */
	public final long DESISTIMIENTO    	 				 					= 190L;

	/** Propiedad desistimiento para turno tramite. */
	public final long DESISTIMIENTO_PARA_TURNO_TRAMITE   					= 200L;

	/** Propiedad inscrito adjudicacion remate. */
	public final long INSCRITO_ADJUDICACION_REMATE		 					= 210L;

	/** Propiedad cobro por mayor valor. */
	public final long COBRO_POR_MAYOR_VALOR       		 					= 220L;

	/** Propiedad inscrito con aclaracion. */
	public final long INSCRITO_CON_ACLARACION       	 					= 230L;

	/** Propiedad inscrito con adjudicacion en liquidacion judicial. */
	public final long INSCRITO_CON_ADJUDICACION_EN_LIQUIDACION_JUDICIAL		= 240L;

	/** Propiedad homologacion actos liquidacion mayor valor. */
	public final long HOMOLOGACION_ACTOS_LIQUIDACION_MAYOR_VALOR			= 250L;

	/** Propiedad inscrito adjudicacion de baldio. */
	public final long INSCRITO_ADJUDICACION_DE_BALDIO						= 260L;

	/** Propiedad realizar registro cancelacion automatica. */
	public final long REALIZAR_REGISTRO_CANCELACION_AUTOMATICA				= 270L;

	/** Propiedad rechazo solicitud desistimiento. */
	public final long RECHAZO_SOLICITUD_DESISTIMIENTO	   					= 280L;

	//Motivo etapa 90
	/** Propiedad revisado confrontacion devolver a calificacion. */
	public final long REVISADO_CONFRONTACION_DEVOLVER_A_CALIFICACION = 10L;

	//Motivo etapa 100
	/** Propiedad crear matricula desde antiguo sistema. */
	public final long CREAR_MATRICULA_DESDE_ANTIGUO_SISTEMA 		= 10L;

	/** Propiedad asociar matricula desde antiguo sistema. */
	public final long ASOCIAR_MATRICULA_DESDE_ANTIGUO_SISTEMA 		= 20L;

	/** Propiedad rechazar solicitud desde antiguo sistema. */
	public final long RECHAZAR_SOLICITUD_DESDE_ANTIGUO_SISTEMA 		= 30L;

	/** Propiedad informe de busqueda desde antiguo sistema. */
	public final long INFORME_DE_BUSQUEDA_DESDE_ANTIGUO_SISTEMA 	= 120L;

	/** Propiedad restitucion de turnos. */
	public final long RESTITUCION_DE_TURNOS							= 40L;
	
	/** Propiedad crear matricula desde antiguo sistema firma. */
	public final long CREAR_MATRICULA_DESDE_ANTIGUO_SISTEMA_FIRMA 		= 50L;
	
	/** Propiedad asociar matricula desde antiguo sistema firma. */
	public final long ASOCIAR_MATRICULA_DESDE_ANTIGUO_SISTEMA_FIRMA 	= 60L;
	
	/** Propiedad informe de busqueda desde antiguo sistema firma. */
	public final long INFORME_DE_BUSQUEDA_DESDE_ANTIGUO_SISTEMA_FIRMA 	= 70L;

	//Motivo etapa 101
	/** Propiedad reporte resultados calificacion. */
	public final long REPORTE_RESULTADOS_CALIFICACION 				= 10L;

	/** Propiedad asociar matricula para calificacion. */
	public final long ASOCIAR_MATRICULA_PARA_CALIFICACION 			= 20L;

	/** Propiedad informe de busqueda para calificacion. */
	public final long INFORME_DE_BUSQUEDA_PARA_CALIFICACION 		= 30L;

	/** Propiedad crear matricula desde antiguo sistema 101. */
	public final long CREAR_MATRICULA_DESDE_ANTIGUO_SISTEMA_101 	= 40L;

	/** Propiedad informe de busqueda para grabacion. */
	public final long INFORME_DE_BUSQUEDA_PARA_GRABACION			= 50L;

	/** Propiedad informe de busqueda para certificados. */
	public final long INFORME_DE_BUSQUEDA_PARA_CERTIFICADOS			= 60L;

	/** Propiedad asociar matricula para certificados. */
	public final long ASOCIAR_MATRICULA_PARA_CERTIFICADOS			= 70L;

	/** Propiedad crear matricula para certificados. */
	public final long CREAR_MATRICULA_PARA_CERTIFICADOS				= 80L;

	/** Propiedad informe de busqueda para correcciones. */
	public final long INFORME_DE_BUSQUEDA_PARA_CORRECCIONES			= 90L;

	/** Propiedad crear matricula para correcciones. */
	public final long CREAR_MATRICULA_PARA_CORRECCIONES 			= 100L;

	/** Propiedad asociar matricula para correcciones. */
	public final long ASOCIAR_MATRICULA_PARA_CORRECCIONES			= 110L;
	
	/** Propiedad asociar matricula para calificacion firma. */
	public final long ASOCIAR_MATRICULA_PARA_CALIFICACION_FIRMA 	= 130L;

	/** Propiedad informe de busqueda para calificacion firma. */
	public final long INFORME_DE_BUSQUEDA_PARA_CALIFICACION_FIRMA 	= 140L;

	/** Propiedad crear matricula desde antiguo sistema 101 firma. */
	public final long CREAR_MATRICULA_DESDE_ANTIGUO_SISTEMA_101_FIRMA 	= 150L;
	
	/** Propiedad complemetacion aprobada circulo registral destino envio aprobacion circulo registral origen. */
	public final long COMPLEMETACION_APROBADA_CIRCULO_REGISTRAL_DESTINO_ENVIO_APROBACION_CIRCULO_REGISTRAL_ORIGEN = 160L;
	
	//Motivo etapa 103
	/** Propiedad antiguo sistema grabacion de matriculas. */
	public final long ANTIGUO_SISTEMA_GRABACION_DE_MATRICULAS 		= 10L;

	/** Propiedad negar solicitud de grabacion. */
	public final long NEGAR_SOLICITUD_DE_GRABACION 					= 20L;

	/** Propiedad enviar a correciones. */
	public final long ENVIAR_A_CORRECIONES 							= 30L;

	/** Propiedad generar resolucion de grabacion. */
	public final long GENERAR_RESOLUCION_DE_GRABACION 				= 40L;

	//Motivo etapa 104
	/** Propiedad ejecucion grabacion matricula. */
	public final long EJECUCION_GRABACION_MATRICULA 		= 10L;

	/** Propiedad negar solicitud de grabacion 104. */
	public final long NEGAR_SOLICITUD_DE_GRABACION_104 		= 20L;
	
	//Motivo etapa 105
	/** Propiedad negar solicitud de creacion de matricula de oficio. */
	public final long NEGAR_SOLICITUD_DE_CREACION_DE_MATRICULA_DE_OFICIO 		= 20L;
	
	/** Propiedad crear matricula. */
	public final long CREAR_MATRICULA 											= 30L;
	
	/** Propiedad matricula ya existente. */
	public final long MATRICULA_YA_EXISTENTE 									= 40L;

	//Motivo etapa 110
	/** Propiedad aprobador antiguo sistema firmar. */
	public final long APROBADOR_ANTIGUO_SISTEMA_FIRMAR                                 					 	 = 10L;

	/** Propiedad aprobador antiguo sistema devolver al analista. */
	public final long APROBADOR_ANTIGUO_SISTEMA_DEVOLVER_AL_ANALISTA                    					 = 20L;
	
	/** Propiedad aprobado informe de busqueda para calificacion. */
	public final long APROBADO_INFORME_DE_BUSQUEDA_PARA_CALIFICACION                  						 = 40L;
	
	/** Propiedad SOLICITA R complementació N A OTR O círcul O REGISTRAL. */
	public final long SOLICITAR_COMPLEMENTACIÓN_A_OTRO_CÍRCULO_REGISTRAL                 				     = 90L;

	/** Propiedad autoriza firma libro antiguo sistema. */
	public final long AUTORIZA_FIRMA_LIBRO_AS                      						 					 = 300L;
	
	/** Propiedad devolver a autoriza firma libro antiguo sistema. */
	public final long DEVOLVER_A_AUTORIZAR_FIRMA_LIBRO_AS                      			 					 = 310L;
	
	/** Propiedad aprobada negacion solicitud de creacion de matricula por oficio orip. */
	public final long APROBADA_NEGACION_SOLICITUD_DE_CREACION_DE_MATRICULA_POR_OFICIO_ORIP 					 = 320L;
	
	/** Propiedad aprobada negacion solicitud de creacion de matricula por oficio. */
	public final long APROBADA_NEGACION_SOLICITUD_DE_CREACION_DE_MATRICULA_POR_OFICIO 						 = 330L;
	
	/** Propiedad aprobada negacion solicitud de creacion de matricula por oficio correo electronico. */
	public final long APROBADA_NEGACION_SOLICITUD_DE_CREACION_DE_MATRICULA_POR_OFICIO_CORREO_ELECTRONICO  	 = 340L;
	
	/** Propiedad devolver a etapa anterior 105. */
	public final long DEVOLVER_A_ETAPA_ANTERIOR_105  	 													 = 350L;
	
	/** Propiedad negar solicitud de creacion de matricula de oficio ya existente orip. */
	public final long NEGAR_SOLICITUD_DE_CREACION_DE_MATRICULA_DE_OFICIO_YA_EXISTENTE_ORIP  				 = 360L;
	
	/** Propiedad negar solicitud de creacion de matricula de oficio ya existente. */
	public final long NEGAR_SOLICITUD_DE_CREACION_DE_MATRICULA_DE_OFICIO_YA_EXISTENTE   				     = 370L;
	
	/** Propiedad negar solicitud de creacion de matricula de oficio ya existente correo. */
	public final long NEGAR_SOLICITUD_DE_CREACION_DE_MATRICULA_DE_OFICIO_YA_EXISTENTE_CORREO   				 = 380L;
	
	/** Propiedad aprobada solicitud de creacion de matricula de oficio orip. */
	public final long APROBADA_SOLICITUD_DE_CREACION_DE_MATRICULA_DE_OFICIO_ORIP    						 = 390L;
	
	/** Propiedad aprobada solicitud de creacion de matricula de oficio. */
	public final long APROBADA_SOLICITUD_DE_CREACION_DE_MATRICULA_DE_OFICIO  								 = 400L;
	
	/** Propiedad aprobada solicitud de creacion de matricula correo electronico. */
	public final long APROBADA_SOLICITUD_DE_CREACION_DE_MATRICULA_CORREO_ELECTRONICO   						 = 410L;
	
	/** Propiedad proceso creacion de matriculas finalizado negado. */
	public final long PROCESO_CREACION_DE_MATRICULAS_FINALIZADO_NEGADO   								  	 = 420L;
	
	/** Propiedad proceso creacion de matriculas finalizado aprobado. */
	public final long PROCESO_CREACION_DE_MATRICULAS_FINALIZADO_APROBADO   								  	 = 430L;
	
	public final long COMPLEMENTACION_DE_OTRO_CIRCULO_APROBADA_ENVIO_A_GENERACION_DE_CERTIFICADOS            = 440L;
	
	/** Propiedad devolver a etapa 105. */
	public final long DEVOLVER_A_ETAPA_105                   = 350L;
	
	//Motivo etapa 114
	/** Propiedad generar resolución autorización firma libros as. */
	public final long GENERAR_RESOLUCION_AUTORIZACION_FIRMA_LIBROS_AS 		= 10L;
	
	/** Propiedad negación autorización firma libros as. */
	public final long NEGACION_AUTORIZACION_FIRMA_LIBROS_AS 				= 20L;
	
	//Motivo etapa 191

	/** Propiedad devolver al analista oficina destino. */
	public final long DEVOLVER_AL_ANALISTA_OFICINA_DESTINO  = 60L;
	
	/** Propiedad devolver traslado masivo a la oficina origen. */
	public final long DEVOLVER_TRASLADO_MASIVO_A_LA_OFICINA_ORIGEN = 20L;
	
	/** Propiedad aprobador antiguo sistema enviar al analista para generar busqueda. */
	public final long APROBADOR_ANTIGUO_SISTEMA_ENVIAR_AL_ANALISTA_PARA_GENERAR_BUSQUEDA = 30L;

	/** Propiedad aprobador antiguo sistema aprobar solicitud complementacion. */
	public final long APROBADOR_ANTIGUO_SISTEMA_APROBAR_SOLICITUD_COMPLEMENTACION        = 40L;

	/** Propiedad aprobador antiguo sistema devolver al calificador. */
	public final long APROBADOR_ANTIGUO_SISTEMA_DEVOLVER_AL_CALIFICADOR                  = 50L;

	/** Propiedad devolver al buscador de antiguo sistema. */
	public final long DEVOLVER_AL_BUSCADOR_DE_ANTIGUO_SISTEMA 	    					 = 60L;
	
	/** Propiedad creacion de matricula existosa para certificados. */
	public final long CREACION_DE_MATRICULA_EXISTOSA_PARA_CERTIFICADOS					 = 110L;

	/** Propiedad asociacion de matricula existosa para certificados. */
	public final long ASOCIACION_DE_MATRICULA_EXISTOSA_PARA_CERTIFICADOS				 = 130L;

	/** Propiedad creacion de matricula existosa para correcciones. */
	public final long CREACION_DE_MATRICULA_EXISTOSA_PARA_CORRECCIONES					 = 140L;

	/** Propiedad asociacion de matricula existosa para correcciones. */
	public final long ASOCIACION_DE_MATRICULA_EXISTOSA_PARA_CORRECCIONES				 = 150L;
	
	/** Propiedad asociacion de matricula existosa para correcciones. */
	public final long INFORME_DE_BUSQUEDA_CORRECCIONES_AS								 = 160L;

	/** Propiedad aprobado rechazar solicitud desde antiguo sistema. */
	public final long APROBADO_RECHAZAR_SOLICITUD_DESDE_ANTIGUO_SISTEMA                  = 200L;

	/** Propiedad aprobador antiguo sistema devolver aprobador antiguo sistema. */
	public final long APROBADOR_ANTIGUO_SISTEMA_DEVOLVER_APROBADOR_ANTIGUO_SISTEMA       = 210L;

	//Motivo etapa 120
	/** Propiedad tramite desistimiento con turno en fase entrega o finalizado. */
	public final long TRAMITE_DESISTIMIENTO_CON_TURNO_EN_FASE_ENTREGA_O_FINALIZADO       = 10L;
	
	/** Propiedad entrega de Negacion de la solicitud de correccion Orip. */
	public final long ENTREGA_NEGACION_SOLICITUD_CORRECCION_ORIP						 = 80L;
	
	/** Propiedad entrega de Negacion de la solicitud de correccion correo electronico. */
	public final long ENTREGA_NEGACION_SOLICITUD_CORRECCION_CORREO						 = 90L;
	
	/** Propiedad entrega de Negacion de la solicitud de correccion correspondencia. */
	public final long ENTREGA_NEGACION_SOLICITUD_CORRECCION_CORRESPONDENCIA				 = 100L;

	//Motivo etapa 130
	/** Propiedad proceso correcciones. */
	public final long PROCESO_CORRECCIONES									= 1L;

	/** Propiedad aprobar prorroga documentos. */
	public final long APROBAR_PRORROGA_DOCUMENTOS							= 10L;

	/** Propiedad negar prorroga documentos. */
	public final long NEGAR_PRORROGA_DOCUMENTOS								= 20L;

	/** Propiedad modificar matriculas. */
	public final long MODIFICAR_MATRICULAS									= 30L;

	/** Propiedad permisos para correcciones. */
	public final long PERMISOS_PARA_CORRECCIONES							= 40L;

	/** Propiedad antiguo sistema correcciones. */
	public final long ANTIGUO_SISTEMA_CORRECCIONES							= 50L;

	/** Propiedad cobro por mayor valor correcciones. */
	public final long COBRO_POR_MAYOR_VALOR_CORRECCIONES					= 60L;

	/** Propiedad solicitud documentacion para proceso de correcciones. */
	public final long SOLICITUD_DOCUMENTACION_PARA_PROCESO_DE_CORRECCIONES	= 70L;

	/** Propiedad no procedencia de la correccion. */
	public final long NO_PROCEDENCIA_DE_LA_CORRECCION						= 80L;
	
	/** Propiedad no procedencia de la correccion. */
	public final long ENVIAR_A_GRABACION_MATRICULAS							= 100L;

	//Motivo etapa 132
	/** Propiedad realizar correcciones. */
	public final long REALIZAR_CORRECCIONES				= 10L;

	/** Propiedad devolver al analista correcciones. */
	public final long DEVOLVER_AL_ANALISTA_CORRECCIONES	= 20L;

	/** Propiedad aprobar secuencias 132. */
	public final long APROBAR_SECUENCIAS_132			= 30L;
	
	//Motivo etapa 140
	/** Propiedad enviar a calificador digitador masivo. */
	public final long ENVIAR_A_CALIFICADOR_DIGITADOR_MASIVO = 10L;

	//Motivo etapa 150
	/** Propiedad restitucion de turnos aprobado. */
	public final long RESTITUCION_DE_TURNOS_APROBADO	= 10L;

	/** Propiedad restitucion de turnos negado. */
	public final long RESTITUCION_DE_TURNOS_NEGADO		= 20L;

	//Motivo etapa 165
	/** Propiedad devuelta a calificacion por homologacion actos. */
	public final long DEVUELTA_A_CALIFICACION_POR_HOMOLOGACION_ACTOS = 10L;

	//Motivo etapa 180
	/** Propiedad constancia reproduccion. */
	public final long CONSTANCIA_REPRODUCCION = 10L;

	/** Propiedad constancia reproduccion sin registros. */
	public final long CONSTANCIA_REPRODUCCION_SIN_REGISTROS = 20L;

	//Motivo etapa 181
	/** Propiedad cancelacion realizar registro. */
	public final long CANCELACION_REALIZAR_REGISTRO = 10L;

	//Motivo etapa 190
	/** Propiedad default. */
	public final long DEFAULT                                               					= 0L;

	/** Propiedad suspension articulo 18. */
	public final long SUSPENSION_ARTICULO_18                                					= 10L;

	/** Propiedad suspension proceso registro terminado. */
	public final long SUSPENSION_PROCESO_REGISTRO_TERMINADO                 					= 10L;

	/** Propiedad suspension articulo 19 correo electronico. */
	public final long SUSPENSION_ARTICULO_19_CORREO_ELECTRONICO             					= 20L;

	/** Propiedad suspension certificado devuelto al publico. */
	public final long SUSPENSION_CERTIFICADO_DEVUELTO_AL_PUBLICO            					= 20L;

	/** Propiedad suspension articulo 19 direccion residencia. */
	public final long SUSPENSION_ARTICULO_19_DIRECCION_RESIDENCIA_          					= 30L;

	/** Propiedad suspension articulo 19 direccion correspondencia. */
	public final long SUSPENSION_ARTICULO_19_DIRECCION_CORRESPONDENCIA      					= 40L;

	/** Propiedad suspension articulo 19 orip. */
	public final long SUSPENSION_ARTICULO_19_ORIP                           					= 50L;

	/** Propiedad entrega nota devolutva correo. */
	public final long ENTREGA_NOTA_DEVOLUTVA_CORREO                         					= 140L;

	/** Propiedad entrega nota devolutva correspondencia. */
	public final long ENTREGA_NOTA_DEVOLUTVA_CORRESPONDENCIA                					= 150L;

	/** Propiedad entrega nota devolutva orip. */
	public final long ENTREGA_NOTA_DEVOLUTVA_ORIP                           					= 60L;

	/** Propiedad entrega nota devolutva residencia. */
	public final long ENTREGA_NOTA_DEVOLUTVA_RESIDENCIA                     					= 150L;

	/** Propiedad inscripcion registro correo electronico. */
	public final long INSCRIPCION_REGISTRO_CORREO_ELECTRONICO               					= 70L;

	/** Propiedad inscripcion registro direccion residencia. */
	public final long INSCRIPCION_REGISTRO_DIRECCION_RESIDENCIA             					= 80L;

	/** Propiedad inscripcion registro direccion correspondencia. */
	public final long INSCRIPCION_REGISTRO_DIRECCION_CORRESPONDENCIA        					= 90L;

	/** Propiedad inscripcion registro orip. */
	public final long INSCRIPCION_REGISTRO_ORIP                             					= 100L;

	/** Propiedad analisis de completitud complementacion. */
	public final long ANALISIS_DE_COMPLETITUD_COMPLEMENTACION               					= 110L;

	/** Propiedad enviar a calificacion. */
	public final long ENVIAR_A_CALIFICACION                                 					= 120L;

	/** Propiedad ampliar complementacion de otro circulo. */
	public final long AMPLIAR_COMPLEMENTACION_DE_OTRO_CIRCULO               					= 190L;

	/** Propiedad devolver a calificacion. */
	public final long DEVOLVER_A_CALIFICACION                               					= 200L;

	/** Propiedad entrega reproduccion constancia orip. */
	public final long ENTREGA_REPRODUCCION_CONSTANCIA_ORIP                  					= 220L;

	/** Propiedad entrega medida cautelar. */
	public final long ENTREGA_MEDIDA_CAUTELAR			                    					= 230L;

	/** Propiedad entrega orip tramite desistimiento. */
	public final long ENTREGA_ORIP_TRAMITE_DESISTIMIENTO			   							= 240L;

	/** Propiedad entrega por correspondencia tramite desistimiento. */
	public final long ENTREGA_POR_CORRESPONDENCIA_TRAMITE_DESISTIMIENTO							= 250L;

	/** Propiedad entrega orip tramite restitucion. */
	public final long ENTREGA_ORIP_TRAMITE_RESTITUCION			   								= 260L;

	/** Propiedad entrega por correo electronico tramite desistimiento. */
	public final long ENTREGA_POR_CORREO_ELECTRONICO_TRAMITE_DESISTIMIENTO						= 270L;

	/** Propiedad entrega por correspondencia tramite restitucion. */
	public final long ENTREGA_POR_CORRESPONDENCIA_TRAMITE_RESTITUCION							= 280L;

	/** Propiedad entrega por correo electronico tramite restitucion. */
	public final long ENTREGA_POR_CORREO_ELECTRONICO_TRAMITE_RESTITUCION						= 290L; 

	/** Propiedad devolucion desde aprobador del registrador tramites vinculados. */
	public final long DEVOLUCION_DESDE_APROBADOR_DEL_REGISTRADOR_TRAMITES_VINCULADOS			= 300L;

	/** Propiedad devolver a analista de desistimiento. */
	public final long DEVOLVER_A_ANALISTA_DE_DESISTIMIENTO                  					= 310L;

	/** Propiedad mayor valor correo electronico. */
	public final long MAYOR_VALOR_CORREO_ELECTRONICO											= 320L;

	/** Propiedad mayor valor correspondencia. */
	public final long MAYOR_VALOR_CORRESPONDENCIA												= 330L;

	/** Propiedad mayor valor orip. */
	public final long MAYOR_VALOR_ORIP															= 340L;

	/** Propiedad entrega certificados orip. */
	public final long ENTREGA_CERTIFICADOS_ORIP					    							= 440L;

	/** Propiedad entrega certificados correo electronico. */
	public final long ENTREGA_CERTIFICADOS_CORREO_ELECTRONICO   								= 450L;

	/** Propiedad entrega negacion de la solicitud de grabacion orip. */
	public final long ENTREGA_NEGACION_DE_LA_SOLICITUD_DE_GRABACION_ORIP     		    		= 350L;

	/** Propiedad entrega negacion de la solicitud de correo electronico. */
	public final long ENTREGA_NEGACION_DE_LA_SOLICITUD_DE_CORREO_ELECTRONICO   		    		= 360L;

	/** Propiedad entrega negacion de la solicitud de correspondencia. */
	public final long ENTREGA_NEGACION_DE_LA_SOLICITUD_DE_CORRESPONDENCIA     		    		= 370L;

	/** Propiedad aprobacion resolucion para grabacion de matricula. */
	public final long APROBACION_RESOLUCION_PARA_GRABACION_DE_MATRICULA     		    		= 380L;

	/** Propiedad entrega matricula grabada orip. */
	public final long ENTREGA_MATRICULA_GRABADA_ORIP     		  				   	    		= 390L;

	/** Propiedad entrega matricula grabada orip de correo electronico. */
	public final long ENTREGA_MATRICULA_GRABADA_ORIP_DE_CORREO_ELECTRONICO     		    		= 400L;

	/** Propiedad entrega matricula grabada orip de correspondencia. */
	public final long ENTREGA_MATRICULA_GRABADA_ORIP_DE_CORRESPONDENCIA     		    		= 410L;

	/** Propiedad devolver a analista de grabacion. */
	public final long DEVOLVER_A_ANALISTA_DE_GRABACION											= 420L;

	/** Propiedad devolver al ejecutor de la grabacion. */
	public final long DEVOLVER_AL_EJECUTOR_DE_LA_GRABACION										= 430L;

	/** Propiedad devolucion desde aprobador del registrador certificados. */
	public final long DEVOLUCION_DESDE_APROBADOR_DEL_REGISTRADOR_CERTIFICADOS					= 460L;

	/** Propiedad DEVOLVER_A_RECHAZO_DE_RECURSOS. */
	public final long DEVOLVER_A_RECHAZO_DE_RECURSOS											= 480L;
	
	/** Propiedad aprobado rechazo solicitud desistimiento orip. */
	public final long APROBADO_RECHAZO_SOLICITUD_DESISTIMIENTO_ORIP								= 500L;

	/** Propiedad aprobado rechazo solicitud desistimiento correo electronico. */
	public final long APROBADO_RECHAZO_SOLICITUD_DESISTIMIENTO_CORREO_ELECTRONICO				= 510L;

	/** Propiedad aprobado rechazo solicitud desistimiento correspondencia. */
	public final long APROBADO_RECHAZO_SOLICITUD_DESISTIMIENTO_CORRESPONDENCIA					= 520L;

	/** Propiedad no aprueba rechazo de desistimiento. */
	public final long NO_APRUEBA_RECHAZO_DE_DESISTIMIENTO										= 530L;

	/** Propiedad devolucion de aprobacion por desistimiento. */
	public final long DEVOLUCION_DE_APROBACION_POR_DESISTIMIENTO								= 540L;
	
	/** Propiedad devolucion de aprobacion por desistimiento. */
	public final long NOTA_DE_NEGACION_COPIAS_ORIP												= 600L;
	
	/** Propiedad devolucion de aprobacion por desistimiento. */
	public final long APROBACION_DE_GENERACION_DE_COPIAS_610									= 610L;
	
	/** Propiedad devolucion de aprobacion por desistimiento. */
	public final long NOTA_DE_NEGACION_COPIAS_CORREO_ELECTRONICO_620							= 620L;
	
	/** Propiedad devolucion de aprobacion por desistimiento. */
	public final long APROBACION_DE_GENERACION_DE_COPIAS_630									= 630L;
	
	/** Propiedad devolucion de aprobacion por desistimiento. */
	public final long NOTA_DE_NEGACION_COPIAS_CORREO_ELECTRONICO_640							= 640L;
	
	/** Propiedad devolucion de aprobacion por desistimiento. */
	public final long APROBACION_DE_GENERACION_DE_CORREO_ELECTRONICO							= 650L;
	
	/** Propiedad testamento entrega direccion residencia correspondencia 190. */
	public final long TESTAMENTO_ENTREGA_DIRECCION_RESIDENCIA_CORRESPONDENCIA_190    			= 690L;
	
	/**  Propiedad testamento entrega orip. */
	public final long TESTAMENTO_ENTREGA_ORIP 													= 710L;
	
	/**  Propiedad entraga orip reproduccion testamentos. */
	public final long ENTREGA_ORIP_REPRODUCCION_TESTAMENTOS 									= 710L;
	
	/**  Propiedad entraga correo electronico reproduccion testamentos. */
	public final long ENTREGA_CORREO_ELECTRONICO_REPRODUCCION_TESTAMENTOS 						= 720L;
	
	/** Propiedad entrega direcciones reproduccion testamentos. */
	public final long ENTREGA_DIRECCIONES_REPRODUCCION_TESTAMENTOS    							= 730L;  
	
	/**  Propiedad entrega certificados negacion orip. */
	public final long ENTREGA_CERTIFICADOS_NEGADO_ORIP					    					= 750L;
	
	/** Propiedad entrega certificados correspondencia. */
	public final long ENTREGA_CERTIFICADOS_NEGADO_CORRESPONDENCIA                        		= 760L;
	
	/** Propiedad entrega certificados correspondencia. */
	public final long ENTREGA_CERTIFICADOS_NEGADO_CORREO                       			 		= 770L;
	
	/** Propiedad GENERAR_CITATORIO_PARA_RECHAZO_DE_RECURSO. */
	public final long GENERAR_CITATORIO_PARA_RECHAZO_DE_RECURSO                       	 		= 780L;
	
	/** Propiedad DEVOLVER_A_COORDINADOR_JURIDICO. */
	public final long DEVOLVER_A_COORDINADOR_JURIDICO                       			 		= 790L;
	
	/** Propiedad GENERAR_CITATORIO_DE_NOTIFICACION_AUTO_DE_PRUEBAS. */
	public final long GENERAR_CITATORIO_DE_NOTIFICACION_AUTO_DE_PRUEBAS                  		= 800L;
	
	/** Propiedad GENERAR_CITATORIO_DE_NOTIFICACION_RESOLUCION. */
	public final long GENERAR_CITATORIO_DE_NOTIFICACION_RESOLUCION                       		= 810L;
	
	/** Propiedad ETAPA_190_AUTO_PRUEBAS_APROBADO_ESPERA_PRUEBAS_ENTREGA_CORRESPONDENCIA. */
	public final long ETAPA_190_AUTO_PRUEBAS_APROBADO_ESPERA_PRUEBAS_ENTREGA_CORRESPONDENCIA 	= 820L;
	
	/** Propiedad ETAPA_190_AUTO_PRUEBAS_APROBADO_ESPERA_PRUEBAS_ENTREGA_CORREO. */
	public final long ETAPA_190_AUTO_PRUEBAS_APROBADO_ESPERA_PRUEBAS_ENTREGA_CORREO 			= 830L;

	//Motivo etapa 195 Aprobación correcciones
	/** Propiedad entrega orip solicitud documentacion. */
	public final long ENTREGA_ORIP_SOLICITUD_DOCUMENTACION								= 10L;

	/** Propiedad entrega correo electronico solicitud documentacion. */
	public final long ENTREGA_CORREO_ELECTRONICO_SOLICITUD_DOCUMENTACION				= 20L;

	/** Propiedad entrega correo electronico cobro por mayor valor. */
	public final long ENTREGA_CORREO_ELECTRONICO_COBRO_POR_MAYOR_VALOR					= 30L;

	/** Propiedad entrega correspondencia cobro por mayor valor. */
	public final long ENTREGA_CORRESPONDENCIA_COBRO_POR_MAYOR_VALOR						= 40L;

	/** Propiedad entrega orip cobro por mayor valor. */
	public final long ENTREGA_ORIP_COBRO_POR_MAYOR_VALOR								= 50L;

	/** Propiedad devolver analista de correcciones. */
	public final long DEVOLVER_ANALISTA_DE_CORRECCIONES									= 60L;

	/** Propiedad devolver a ejecutor de correcciones. */
	public final long DEVOLVER_A_EJECUTOR_DE_CORRECCIONES								= 70L;

	/** Propiedad entrega negacion de la solicitud de correccion orip. */
	public final long ENTREGA_NEGACION_DE_LA_SOLICITUD_DE_CORRECCION_ORIP				= 80L;

	/** Propiedad entrega negacion de la solicitud de correccion correo electronico. */
	public final long ENTREGA_NEGACION_DE_LA_SOLICITUD_DE_CORRECCION_CORREO_ELECTRONICO	= 90L;

	/** Propiedad entrega negacion de la solicitud de correccion correspondencia. */
	public final long ENTREGA_NEGACION_DE_LA_SOLICITUD_DE_CORRECCION_CORRESPONDENCIA	= 100L;

	/** Propiedad reapertura de folio por aprobacion. */
	public final long REAPERTURA_DE_FOLIO_POR_APROBACION								= 110L;

	/** Propiedad entrega correspondencia solicitud documentacion. */
	public final long ENTREGA_CORRESPONDENCIA_SOLICITUD_DOCUMENTACION					= 120L;
	
	/** Propiedad correccion realizada entrega orip. */
	public final long CORRECCION_REALIZADA_ENTREGA_ORIP									= 130L;

	/** Propiedad correccion realizada entrega correspondencia. */
	public final long CORRECCION_REALIZADA_ENTREGA_CORRESPONDENCIA						= 140L;

	/** Propiedad correccion realizada entrega correo electronico. */
	public final long CORRECCION_REALIZADA_ENTREGA_CORREO_ELECTRONICO					= 150L;

	/** Propiedad correccion interna finalizada. */
	public final long CORRECCION_INTERNA_FINALIZADA										= 160L;
	
	/** Propiedad NOTIFICACION_PERSONAL_AUTO_APERTURA_220 etapa 195. */
	public final long NOTIFICACION_PERSONAL_AUTO_APERTURA_220 = 220L;

	/** Propiedad NOTIFICACION_PERSONAL_AUTO_APERTURA_200 etapa 195. */
	public final long NOTIFICACION_PERSONAL_AUTO_APERTURA_200 = 200L;

	/** Propiedad NOTIFICACION_PERSONAL_AUTO_APERTURA_215 etapa 195. */
	public final long NOTIFICACION_PERSONAL_AUTO_APERTURA_215 = 210L;

	/** Propiedad NEGACION_APERTURA_ENTREGA_CORREO_ELECTRONICO etapa 195. */
	public final long NEGACION_APERTURA_ENTREGA_CORREO_ELECTRONICO = 180L;

	/** Propiedad NEGACION_APERTURA_ENTREGA_ORIP etapa 195. */
	public final long NEGACION_APERTURA_ENTREGA_ORIP = 170L;

	/** Propiedad NEGACION_APERTURA_ENTREGA_DIRECCION_DE_RESIDENCIA_CORRESPONDENCIA etapa 195. */
	public final long NEGACION_APERTURA_ENTREGA_DIRECCION_DE_RESIDENCIA_CORRESPONDENCIA = 190L;

	/** Propiedad NOTIFICACION_PERSONAL_AUTO_PRUEBAS_220 etapa 195. */
	public final long NOTIFICACION_PERSONAL_AUTO_PRUEBAS_220 = 250L;

	/** Propiedad NOTIFICACION_PERSONAL_AUTO_PRUEBAS_200 etapa 195. */
	public final long NOTIFICACION_PERSONAL_AUTO_PRUEBAS_200 = 230L;

	/** Propiedad NOTIFICACION_PERSONAL_AUTO_PRUEBAS_215 etapa 195. */
	public final long NOTIFICACION_PERSONAL_AUTO_PRUEBAS_215 = 240L;

	/** Propiedad NOTIFICACION_PERSONAL_RESOLUCION_DECISION_220 etapa 195. */
	public final long NOTIFICACION_PERSONAL_RESOLUCION_DECISION_220 = 280L;

	/** Propiedad NOTIFICACION_PERSONAL_RESOLUCION_DECISION_200 etapa 195. */
	public final long NOTIFICACION_PERSONAL_RESOLUCION_DECISION_200 = 260L;

	/** Propiedad NOTIFICACION_PERSONAL_RESOLUCION_DECISION_215 etapa 195. */
	public final long NOTIFICACION_PERSONAL_RESOLUCION_DECISION_215 = 270L;

	/** Propiedad NOTIFICACION_PERSONAL_ACLARACION_RESOLUCION_DECISION_200 etapa 195. */
	public final long NOTIFICACION_PERSONAL_ACLARACION_RESOLUCION_DECISION_200 = 290L;

	/** Propiedad NOTIFICACION_PERSONAL_ACLARACION_RESOLUCION_DECISION_215 etapa 195. */
	public final long NOTIFICACION_PERSONAL_ACLARACION_RESOLUCION_DECISION_215 = 300L;

	/** Propiedad NOTIFICACION_PERSONAL_ACLARACION_RESOLUCION_DECISION_220 etapa 195. */
	public final long NOTIFICACION_PERSONAL_ACLARACION_RESOLUCION_DECISION_220 = 310L;
	
	//Motivo etapa 200
	/** Propiedad comunicacion auto de pruebas enviada. */
	public final long COMUNICACION_AUTO_DE_PRUEBAS_ENVIADA           				= 291L;
	
	/** Propiedad comunicacion auto de pruebas enviada 288. */
	public final long COMUNICACION_AUTO_DE_PRUEBAS_ENVIADA_288           			= 288L;
	
	//Motivo etapa 30 y 200 a 220
	/** Propiedad proceso de registro finalizado. */
	public final long PROCESO_DE_REGISTRO_FINALIZADO           						= 10L;

	/** Propiedad suspension de tramite de registro a prevencion. */
	public final long SUSPENSION_DE_TRAMITE_DE_REGISTRO_A_PREVENCION				= 10L;

	/** Propiedad finalizacion proceso de registro parcial. */
	public final long FINALIZACION_PROCESO_DE_REGISTRO_PARCIAL 						= 20L;

	/** Propiedad finalizacion proceso incripcion solicitud inicial. */
	public final long FINALIZACION_PROCESO_INCRIPCION_SOLICITUD_INICIAL 			= 20L;

	/** Propiedad entrega por correo electronico. */
	public final long ENTREGA_POR_CORREO_ELECTRONICO           						= 30L;

	/** Propiedad finalizacion proceso nota devolutiva solicitud inicial. */
	public final long FINALIZACION_PROCESO_NOTA_DEVOLUTIVA_SOLICITUD_INICIAL		= 30L;

	/** Propiedad solicitud documentacion para proceso de correcciones entrega. */
	public final long SOLICITUD_DOCUMENTACION_PARA_PROCESO_DE_CORRECCIONES_ENTREGA	= 40L;

	/** Propiedad pendiente pago mayor valor. */
	public final long PENDIENTE_PAGO_MAYOR_VALOR									= 50L;

	/** Propiedad proceso de grabacion finalizado negado. */
	public final long PROCESO_DE_GRABACION_FINALIZADO_NEGADO    					= 60L;

	/** Propiedad proceso de grabacion finalizado aprobado. */
	public final long PROCESO_DE_GRABACION_FINALIZADO_APROBADO           			= 70L;

	/** Propiedad correccion externa finalizada. */
	public final long CORRECCION_EXTERNA_FINALIZADA				           			= 80L;

	/** Propiedad correccion externa negada finalizada. */
	public final long CORRECCION_EXTERNA_NEGADA_FINALIZADA				           	= 90L;

	/** Propiedad aprobado rechazo solicitud desistimiento. */
	public final long APROBADO_RECHAZO_SOLICITUD_DESISTIMIENTO			           	= 100L;

	/** Propiedad proceso finalizado por desistimiento. */
	public final long PROCESO_FINALIZADO_POR_DESISTIMIENTO				           	= 110L;

	/** Propiedad prorroga mayor valor finalizada. */
	public final long PRORROGA_MAYOR_VALOR_FINALIZADA				           		= 120L;

	/** Propiedad pendiente pago mayor valor correcciones. */
	public final long PENDIENTE_PAGO_MAYOR_VALOR_CORRECCIONES				        = 130L;

	/** Propiedad entrega consulta finalizada. */
	public final long ENTREGA_CONSULTA_FINALIZADA           						= 220L;

	/** Propiedad suspension de terminos entrega. */
	public final long SUSPENSION_DE_TERMINOS_ENTREGA           						= 280L;
	
	/** Propiedad proceso creacion de matriculas finalizado negado 200. */
	public final long PROCESO_CREACION_DE_MATRICULAS_FINALIZADO_NEGADO_200          = 100L;

	/** Propiedad proceso creacion de matriculas finalizado aprobado 200. */
	public final long PROCESO_CREACION_DE_MATRICULAS_FINALIZADO_APROBADO_200        = 110L;
	
	//Motivo etapa 202 y 203 entrega documentos correspondencia y correo electrónico
	/** Propiedad solicitud documento electronico owcc 5. */
	public final long SOLICITUD_DOCUMENTO_ELECTRONICO_OWCC_5	= 5L;
	
	/** Propiedad solicitud documento electronico owcc. */
	public final long SOLICITUD_DOCUMENTO_ELECTRONICO_OWCC	= 10L;
	
	/** Propiedad solicitud documentacion para proceso de correcciones 202. */
	public final long SOLICITUD_DOCUMENTACION_PARA_PROCESO_DE_CORRECCIONES_202	= 40L;
	
	/** Propiedad pendiente pago mayor valor 202 50. */
	public final long PENDIENTE_PAGO_MAYOR_VALOR_202_50	= 50L;
	
	/** Propiedad aprobado rechazo solicitud desistimiento 202. */
	public final long APROBADO_RECHAZO_SOLICITUD_DESISTIMIENTO_202	= 100L;
	
	/** Propiedad pendiente pago mayor valor 202. */
	public final long PENDIENTE_PAGO_MAYOR_VALOR_202	= 130L;
	
	/** Propiedad negacion apertura actuaciones administrativas. */
	public final long NEGACION_APERTURA_ACTUACIONES_ADMINISTRATIVAS	= 160L;
	
	/** Propiedad SUSPENSIO N TEMPORA L DE L trámit E D E REGISTRO. */
	public final long SUSPENSION_TEMPORAL_DEL_TRAMITE_DE_REGISTRO	= 280L;
	
	/** Propiedad suspension de tramite de registro a prevencion 202. */
	public final long SUSPENSION_DE_TRAMITE_DE_REGISTRO_A_PREVENCION_202	= 281L;
	
	//Motivo etapa 205 a 207 Nota devolutiva
	/** Propiedad en espera termino para interponer recursos. */
	public final long EN_ESPERA_TERMINO_PARA_INTERPONER_RECURSOS	= 30L;
	
	/** Propiedad nota devolutiva notificada. */
	public final long NOTA_DEVOLUTIVA_NOTIFICADA = 10L;

	//Motivo etapas 215,219 ENVIO_CORRESPONDENCIA_FISICA Y ELECTRONICA
	/** Propiedad acta notificacion enviada en espera acuse recibo. */
	public final long ACTA_NOTIFICACION_ENVIADA_EN_ESPERA_ACUSE_RECIBO = 50L;

	/** Propiedad envio correspondencia fisica direccion. */
	public final long ENVIO_CORRESPONDENCIA_FISICA_DIRECCION	= 310L;

	//Motivo etapas 216 EN_ESPERA_ACUSE_RECIBIDO_CORRESPONDENCIA
	/** Propiedad en espera acuse correspondencia orip. */
	public final long EN_ESPERA_ACUSE_CORRESPONDENCIA_ORIP	= 10L;

	/** Propiedad en espera acuse correspondencia direccion. */
	public final long EN_ESPERA_ACUSE_CORRESPONDENCIA_DIRECCION	= 20L;
	
	/** Propiedad en espera acuse actuaciones. */
	public final long EN_ESPERA_ACUSE_ACTUACIONES	= 20L;

	//Motivo etapas 220 EN_ESPERA_ACUSE_RECIBIDO_EMAIL
	/** Propiedad en espera acuse email orip. */
	public final long EN_ESPERA_ACUSE_EMAIL_ORIP	= 200L;

	/** Propiedad en espera acuse email correo electronico. */
	public final long EN_ESPERA_ACUSE_EMAIL_CORREO_ELECTRONICO	= 210L;

	//Motivo etapas 208 EN_ESPERA_ACUSE_RECIBIDO_CITATORIO
	/** Propiedad en espera acuse citatorio notificado. */
	public final long EN_ESPERA_ACUSE_CITATORIO_NOTIFICADO	= 10L;

	//Motivo etapas 231 EN_ESPERA_ACUSE_RECIBIDO_AVISO
	/** Propiedad en espera acuse aviso notificado. */
	public final long EN_ESPERA_ACUSE_AVISO_NOTIFICADO	= 10L;
	
	//Motivo etapas 239 VALIDACION_PERSONAS_Y_O_TERCEROS_NOTIFICADAS
	/** Propiedad notificaciones revisadas. */
	public final long NOTIFICACIONES_REVISADAS = 10L;
	
	//Motivos etapa 244 CIERRE EXPEDIENTE
	/** Propiedad proceso certificado finalizado aprobado. */
	public final long PROCESO_CERTIFICADO_FINALIZADO_APROBADO = 10L;
	
	/** Propiedad proceso certificado finalizado negado. */
	public final long PROCESO_CERTIFICADO_FINALIZADO_NEGADO = 20L;
	
	/** Propiedad proceso consultas finalizado aprobado. */
	public final long PROCESO_CONSULTAS_FINALIZADO_APROBADO = 30L;
	
	/** Propiedad proceso de registro finalizado 244. */
	public final long PROCESO_DE_REGISTRO_FINALIZADO_244 = 40L;
	
	/** Propiedad proceso finalizado traslado aprobado y ejecutado. */
	public final long PROCESO_FINALIZADO_TRASLADO_APROBADO_Y_EJECUTADO  = 60L;
	
	/** Propiedad proceso finalizado traslado individual aprobado. */
	public final long PROCESO_FINALIZADO_TRASLADO_INDIVIDUAL_APROBADO = 60L;
	
	/** Propiedad proceso de grabacion finalizado negado 244. */
	public final long PROCESO_DE_GRABACION_FINALIZADO_NEGADO_244 = 60L;
	
	/** Propiedad proceso de grabacion finalizado aprobado 244. */
	public final long PROCESO_DE_GRABACION_FINALIZADO_APROBADO_244 = 70L;
	
	/** Propiedad correccion externa finalizada 244. */
	public final long CORRECCION_EXTERNA_FINALIZADA_244 = 80L;
	
	/** Propiedad correccion externa negada finalizada 244. */
	public final long CORRECCION_EXTERNA_NEGADA_FINALIZADA_244 = 90L;
	
	/** Propiedad proceso finalizado por desistimiento 244. */
	public final long PROCESO_FINALIZADO_POR_DESISTIMIENTO_244 = 110L;
	
	/** Propiedad prorroga mayor valor finalizada 244. */
	public final long PRORROGA_MAYOR_VALOR_FINALIZADA_244 = 120L;
	
	/** Propiedad entrega finalizada testamentos 244. */
	public final long ENTREGA_FINALIZADA_TESTAMENTOS_244 = 150L;
	
	/** Propiedad nota de negacion copias orip 244. */
	public final long NOTA_DE_NEGACION_COPIAS_ORIP_244 = 230L;
	
	/** Propiedad procesode copias finalizado aprobado 244 244. */
	public final long PROCESO_DE_COPIAS_FINALIZADO_APROBADO_244 = 240L;
	
	/** Propiedad finalizacion proceso de testamentos 244. */
	public final long FINALIZACION_PROCESO_DE_TESTAMENTOS_244 = 250L;

	//Motivo etapa 240 Notificacion
	/** Propiedad RESOLUCION_CONCEDIENDO_RECURSO_DE_REPOSICIÓN. */
	public final long RESOLUCION_CONCEDIENDO_RECURSO_DE_REPOSICION  = 40L;
	
	/** Propiedad RESOLUCION_NEGANDO_EL_RECURSO_DE_REPOSICION_DEVUELTO_AL_PUBLICO_NOTA_DEVOLUTIVA. */
	public final long RESOLUCION_NEGANDO_EL_RECURSO_DE_REPOSICION_DEVUELTO_AL_PUBLICO_NOTA_DEVOLUTIVA  = 50L;
	
	/** Propiedad RESOLUCION_NEGANDO_RECURSO_REPOSICION_Y_CONCEDIENDO_APELACION. */
	public final long RESOLUCION_NEGANDO_RECURSO_REPOSICION_Y_CONCEDIENDO_APELACION  = 70L;
	
	/** Propiedad REVOCA_PARCIALMENTE_DECISION_Y_CONCEDE_APELACION_EN_LO_DEMAS. */
	public final long REVOCA_PARCIALMENTE_DECISION_Y_CONCEDE_APELACION_EN_LO_DEMAS   = 80L;
	
	/** Propiedad RESOLUCION_CONCEDIENDO_RECURSO_DE_APELACION. */
	public final long RESOLUCION_CONCEDIENDO_RECURSO_DE_APELACION   = 90L;
	
	/** Propiedad RESOLUCION_NEGANDO_RECURSO_APELACION. */
	public final long RESOLUCION_NEGANDO_RECURSO_APELACION   = 100L;

	/** Propiedad motivo 110 etapa 240. */
	public final long MOTIVO_110_ETAPA_240   = 110L;

	/** Propiedad motivo 120 etapa 240. */
	public final long MOTIVO_120_ETAPA_240   = 120L;

	/** Propiedad motivo 130 etapa 240. */
	public final long MOTIVO_130_ETAPA_240   = 130L;

	/** Propiedad motivo 150 etapa 240. */
	public final long MOTIVO_150_ETAPA_240   = 150L;

	/** Propiedad motivo 160 etapa 240. */
	public final long MOTIVO_160_ETAPA_240   = 160L;

	/** Propiedad motivo 170 etapa 240. */
	public final long MOTIVO_170_ETAPA_240   = 170L;

	/** Propiedad motivo 180 etapa 240. */
	public final long MOTIVO_180_ETAPA_240   = 180L;

	/** Propiedad motivo 140 etapa 240. */
	public final long MOTIVO_140_ETAPA_240   = 140L;

	/** Propiedad motivo 190 etapa 240. */
	public final long MOTIVO_190_ETAPA_240   = 190L;

	/** Propiedad motivo 200 etapa 240. */
	public final long MOTIVO_200_ETAPA_240   = 200L;

	//Motivo etapa 250 reanotacion
	/** Propiedad tramite de reanotacion devolucion calificacion. */
	public final long TRAMITE_DE_REANOTACION_DEVOLUCION_CALIFICACION		= 10L;

	/** Propiedad tramite de reanotacion. */
	public final long TRAMITE_DE_REANOTACION								= 40L;

	//Motivo etapa 281
	/** Propiedad recepcion de documentacion completa. */
	public final long RECEPCION_DE_DOCUMENTACION_COMPLETA		  			= 20L;

	//Motivo etapa 300
	/** Propiedad entrega certificados en la orip. */
	public final long ENTREGA_CERTIFICADOS_EN_LA_ORIP 			  			= 10L;

	/** Propiedad entrega certificados por correo electronico. */
	public final long ENTREGA_CERTIFICADOS_POR_CORREO_ELECTRONICO			= 20L;

	//Motivo etapa 310
	/** Propiedad generacion certificado especial. */
	public final long GENERACION_CERTIFICADO_ESPECIAL 			  			= 10L;

	/** Propiedad enviar a antiguo sistema. */
	public final long ENVIAR_A_ANTIGUO_SISTEMA 					 			= 20L;

	/** Propiedad entrega de certificados finalizado. */
	public final long ENTREGA_DE_CERTIFICADOS_FINALIZADO		 		    = 10L;

	/** Propiedad analisis de desistimiento. */
	public final long ANALISIS_DE_DESISTIMIENTO		 		   			    = 2L;

	//Motivo entrega de certificados y consultas por KIOSKO
	/** Propiedad entrega de certificados kiosko. */
	public final long ENTREGA_DE_CERTIFICADOS_KIOSKO		=200L;

	/** Propiedad entrega de consultas kiosko. */
	public final long ENTREGA_DE_CONSULTAS_KIOSKO			=210L;
	
	/** Propiedad actuaciones administrativas. */
	public final long ACTUACIONES_ADMINISTRATIVAS = 90L;

	/** Propiedad ANALISIS_EXPEDIENTE_NOTA_DEVOLUTIVA etapa 170. */
	public final long ANALISIS_EXPEDIENTE_ACTOS_DE_INSCRIPCION_O_ANOTACION = 50L;

	/** Propiedad ANALISIS_EXPEDIENTE_NOTA_DEVOLUTIVA etapa 170. */
	public final long ANALISIS_EXPEDIENTE_DEVOLUCION_DE_DINERO = 40L;

	/** Propiedad ANALISIS_EXPEDIENTE_NOTA_DEVOLUTIVA etapa 170. */
	public final long ANALISIS_EXPEDIENTE_ACTUACION_ADMINISTRATIVA = 30L;

	/** Propiedad ANALISIS_EXPEDIENTE_NOTA_DEVOLUTIVA etapa 170. */
	public final long ANALISIS_EXPEDIENTE_NOTA_DEVOLUTIVA = 20L;

	/** Propiedad antiguo sistema etapa 175. */
	public final long ANTIGUO_SISTEMA_ACTUACIONES_ADMINISTRATIVAS = 80L;

	/** Propiedad negración apertura etapa 175. */
	public final long NEGACION_APERTURA = 40L;

	/** Propiedad proceso finalizacion proceso de testamentos. */
	public final long FINALIZACION_PROCESO_DE_TESTAMENTOS = 250L;

	/**  Propiedad nota devolutiva entrega correspondencia. */
	public final long NOTA_DEVOLUTIVA_ENTREGA_CORRESPONDENCIA = 150L;

	/**  Propiedad nota devolutiva entrega correo electronico. */
	public final long NOTA_DEVOLUTIVA_ENTREGA_CORREO_ELECTRONICO = 140L;

	/**  Propiedad nota devolutiva entrega orip. */
	public final long NOTA_DEVOLUTIVA_ENTREGA_ORIP = 60L;

	/** Propiedad reproduccion testamentos. */
	public final long REPRODUCCION_TESTAMENTOS_ORIP = 710L;

	/** Propiedad reproduccion testamentos correo electronico. */
	public final long REPRODUCCION_TESTAMENTOS_CORRREO_ELECTRONICO 						= 720L;

	/** Propiedad reproduccion testamentos entrega direcciones. */
	public final long REPRODUCCION_TESTAMENTOS_ENTREGA_DIRECCIONES = 730L;

	/** Propiedad proceso de entrega finalizada testamentos. */
	public final long ENTREGA_FINALIZADA_TESTAMENTOS = 150L;

	/**  Propiedad testamento entrega orip. */
	public final long TESTAMENTO_ENTREGA_CORREO_ELECTRONICO = 680L;

	/** Propiedad proceso de copias finalizado aprobado. */
	public final long PROCESO_DE_COPIAS_FINALIZADO_APROBADO = 240L;

	/**  Propiedad APROBACION_DE_GENERACION_DE_COPIAS_CORREO. */
	public final long APROBACION_DE_GENERACION_DE_COPIAS_CORREO = 630L;

	/**  Propiedad APROBACION_DE_GENERACION_DE_COPIAS_EXENTA_CORREO. */
	public final long APROBACION_DE_GENERACION_DE_COPIAS_EXENTA_CORREO = 650L;

	/**  Propiedad APROBACION_DE_GENERACION_DE_COPIAS_EXENTA_ORIP. */
	public final long APROBACION_DE_GENERACION_DE_COPIAS_EXENTA_ORIP = 610L;

	/**  Propiedad APROBACION_DE_GENERACION_DE_COPIAS_ORIP. */
	public final long APROBACION_DE_GENERACION_DE_COPIAS_ORIP = 610L;

	/** Propiedad proceso de copias finalizado negado. */
	public final long PROCESO_DE_COPIAS_FINALIZADO_NEGADO = 230L;

	/** Propiedad entrega medidas cautelares con registro parcial. */
	public final long ENTREGA_MEDIDAS_CAUTELARES_CON_REGISTRO_PARCIAL = 550L;

	/** Propiedad auto de aclaración etapa 175. */
	public final long AUTO_DE_ACLARACION = 30L;

	/** Propiedad auto de apertura etapa 175. */
	public final long AUTO_DE_APERTURA = 20L;
	
	/** Propiedad auto de apertura de pruebas etapa 410. */
	public final long ETAPA_410_AUTO_DE_APERTURA_DE_PRUEBAS = 10L;
	
	/** Propiedad resolución concediendo recurso de reposicion etapa 410. */
	public final long ETAPA_410_RESOLUCION_CONCEDIENDO_RECURSO_DE_REPOSICION = 20L;
	
	/** Propiedad resolución negando recurso de reposicion etapa 410. */
	public final long ETAPA_410_RESOLUCION_NEGANDO_RECURSO_DE_REPOSICION = 30L;
	
	/** Propiedad resolución negando recurso de reposicion concediendo apelación etapa 410. */
	public final long ETAPA_410_RESOLUCION_NEGANDO_RECURSO_REPOSICION_CONCEDIENDO_APELACION = 40L;
	
	/** Propiedad resolución negando recurso de reposicion concediendo apelación etapa 410. */
	public final long ETAPA_410_RESOLUCION_CONCEDIENDO_PARCIALMENTE_RECURSO_REPOSICION = 50L;
	
	/** Propiedad resolución rechazando recurso etapa 410. */
	public final long ETAPA_410_RESOLUCION_RECHAZANDO_RECURSO = 60L;
	
	/** Propiedad resolución aprobando recurso de apelación etapa 410. */
	public final long ETAPA_410_RESOLUCION_APROBANDO_RECURSO_APELACION = 70L;
	
	//Etapa 430 Motivos
	/** Propiedad auto de apertura de pruebas etapa 430. */
	public final long ETAPA_430_AUTO_DE_APERTURA_DE_PRUEBAS = 10L;
	
	/** Propiedad resolución concediendo recurso de reposicion etapa 430. */
	public final long ETAPA_430_RESOLUCION_CONCEDIENDO_RECURSO_DE_APELACION = 20L;
	
	/** Propiedad resolución negando recurso de apelacion etapa 430. */
	public final long ETAPA_430_RESOLUCION_NEGANDO_RECURSO_DE_APELACION = 30L;
	
	/** Propiedad ETAPA_430_RESOLUCION_CONCEDIENDO_RECURSO_DE_QUEJA. */
	public final long ETAPA_430_RESOLUCION_CONCEDIENDO_RECURSO_DE_QUEJA = 40L;
	
	/** Propiedad ETAPA_430_RESOLUCION_NEGANDO_RECURSO_DE_QUEJA. */
	public final long ETAPA_430_RESOLUCION_NEGANDO_RECURSO_DE_QUEJA = 50L;
	
	/** Propiedad ETAPA_430_RESOLUCION_CONCEDIENDO_RECURSO_DE_REVOCATORIA_DIRECTA. */
	public final long ETAPA_430_RESOLUCION_CONCEDIENDO_RECURSO_DE_REVOCATORIA_DIRECTA = 60L;
	
	/** Propiedad ETAPA_430_RESOLUCION_NEGANDO_RECURSO_DE_REVOCATORIA_DIRECTA. */
	public final long ETAPA_430_RESOLUCION_NEGANDO_RECURSO_DE_REVOCATORIA_DIRECTA = 70L;

	/** Propiedad ETAPA_430_RESOLUCION_DE_ACLARATORIA. */
	public final long ETAPA_430_RESOLUCION_DE_ACLARATORIA  = 80L;
	
	/** Propiedad ETAPA_430_AUTO_INHIBITORIO. */
	public final long ETAPA_430_AUTO_INHIBITORIO = 90L;
	
	/** Propiedad ETAPA_430_ANTIGUO_SISTEMA. */
	public final long ETAPA_430_ANTIGUO_SISTEMA = 100L;
	
	/** Propiedad auto de pruebas etapa 175. */
	public final long AUTO_DE_PRUEBAS = 50L;

	/** Propiedad resolución de la decisión etapa 175. */
	public final long RESOLUCION_DE_LA_DECISION = 60L;

	/** Propiedad resolución aclaratoria de la decisión etapa 175. */
	public final long RESOLUCION_ACLARATORIA_DE_LA_DECISION = 70L;

	/** Propiedad asociar o desasociar_matriculas etapa 170. */
	public final long ASOCIAR_O_DESASOCIAR_MATRICULAS = 10L;

	/** Propiedad asignar a etapa 170. */
	public final long ASIGNAR_A = 60L;

	/** Propiedad digitalización 350. */
	public final long ETAPA_350_DIGITALIZACION = 10L;
	
	/** Propiedad generar liquidación 350. */
	public final long ETAPA_350_GENERAR_LIQUIDACION = 20L;
	
	/** Propiedad nota de negacion de copias etapa 350. */
	public final long ETAPA_350_NOTA_DE_NEGACION_COPIAS = 30L;
	
	/** Propiedad buscar en antiguo sistema etapa 350. */
	public final long ETAPA_350_BUSCAR_EN_ANTIGUO_SISTEMA = 40L;

	/** Propiedad envio citatorio en espera acuse recibo. */
	public final long ENVIO_CITATORIO_EN_ESPERA_ACUSE_RECIBO = 10L;

	/** Propiedad COPIAS_CON_INFORMACION_DIGITALIZADAS etapa 20. */
	public final long COPIAS_CON_INFORMACION_DIGITALIZADAS = 20L;

	/** Propiedad CON_INFORMACION_EN_OWCC_PENDIENTE_PAGO etapa 12. */
	public final long CON_INFORMACION_EN_OWCC_PENDIENTE_PAGO = 20L;

	/** Propiedad reproduccion de constancia de testamentos. */
	public final long REPRODUCCION_DE_CONSTANCIA_DE_TESTAMENTOS = 70L;

	/** Propiedad reproduccion de constancia automatica. */
	public final long REPRODUCCION_DE_CONSTANCIA_AUTOMATICA = 30L;

	/** Propiedad aprobar testamentos. */
	public final long APROBAR_TESTAMENTOS = 20L;

	/** Propiedad aprobacion modificacion testamento. */
	public final long APROBACION_MODIFICACION_TESTAMENTO = 30L;

	/** Propiedad aprobción anulación testamento. */
	public final long APROBACION_ANULACION_TESTAMENTO = 40L;

	/** Propiedad aprobción revocatoria testamento. */
	public final long APROBACION_REVOCATORIA_TESTAMENTO = 50L;

	/** Propiedad inscrito medidas cautelares con registro parcial. */
	public final long INSCRITO_MEDIDAS_CAUTELARES_CON_REGISTRO_PARCIAL = 290L;

	/** Propiedad cancelacion cuenta cupo aprobada. */
	public final long CANCELACION_CUENTA_CUPO_APROBADA = 50L;

	/** Propiedad cancelacion cuenta cupo rechazada. */
	public final long CANCELACION_CUENTA_CUPO_RECHAZADA = 60L;

	/** Propiedad constancia reproduccion testamentos. */
	public final long CONSTANCIA_REPRODUCCION_TESTAMENTO = 30L;

	/** Propiedad constancia reproduccion testamento sin registros. */
	public final long CONSTANCIA_REPRODUCCION_TESTAMENTO_SIN_REGISTROS = 40L;

	/** Propiedad creacion cuenta cupo aprobada. */
	public final long CREACION_CUENTA_CUPO_APROBADA = 10L;

	/** Propiedad creacion cuenta cupo rechazada. */
	public final long CREACION_CUENTA_CUPO_RECHAZADA = 20L;

	/** Propiedad modificacion cuenta cupo aprobada. */
	public final long MODIFICACION_CUENTA_CUPO_APROBADA = 30L;

	/** Propiedad modificacion cuenta cupo rechazada. */
	public final long MODIFICACION_CUENTA_CUPO_RECHAZADA = 40L;

	/**  Propiedad DEVOLUCION_DOCUMENTOS_DESDE_RESPONSABLE_DE_ACTUACIONES_ADMINISTRATIVAS. */
	public final long DEVOLUCION_DOCUMENTOS_DESDE_RESPONSABLE_DE_ACTUACIONES_ADMINISTRATIVAS = 320L;
	
	/** Propiedad aprueba prorroga documentación etapa 175. */
	public final long APRUEBA_PRORROGA_DOCUMENTACION  = 90L;

	/** Propiedad devolver a reproduccion constancia de testamentos. */
	public final long DEVOLVER_A_REPRODUCCION_CONSTANCIA_TESTAMENTOS = 740L;

	/** Propiedad devolver a analista de testamentos. */
	public final long DEVOLVER_A_ANALISTA_DE_TESTAMENTOS = 660L;

	/**  Propiedad NOTA INFORMATIVA DE NEGACION ETAPA 350. */
	public final long NOTA_INFORMATIVA_DE_NEGACION = 30L;

	/** Propiedad finalizacion proceso notificaciones por renuncia a terminos. */
	public final long FINALIZACION_PROCESO_NOTIFICACIONES_POR_RENUNCIA_A_TERMINOS = 10L;
	
	/** Propiedad acto administrativo notificado en espera termino para interponer recursos. */
	public final long ACTO_ADMINISTRATIVO_NOTIFICADO_EN_ESPERA_TERMINO_PARA_INTERPONER_RECURSOS = 20L;
	
	/** Propiedad en espera vencimiento auto de pruebas. */
	public final long EN_ESPERA_VENCIMIENTO_AUTO_DE_PRUEBAS = 30L;
	
	/** Propiedad finalizacion proceso devuelto al publico nota devolutiva. */
	public final long FINALIZACION_PROCESO_DEVUELTO_AL_PUBLICO_NOTA_DEVOLUTIVA = 10L;

	/** Propiedad nota devolutiva notificada en espera termino para interponer recursos. */
	public final long NOTA_DEVOLUTIVA_NOTIFICADA_EN_ESPERA_TERMINO_PARA_INTERPONER_RECURSOS = 20L;

	/** Propiedad inicio tramite. */
	public final long INICIO_TRAMITE_CALIFICACION = 10L;

	/** Propiedad pendiente pago impuesto registro. */
	public final long PENDIENTE_PAGO_IMPUESTO_REGISTRO = 20L;

	/** Propiedad modificacion datos básicos. */
	public final long MODIFICACION_DATOS_BASICOS_TESTAMENTOS = 10L;

	/** Propiedad notificacion personal correo electronico. */
	public final long NOTIFICACION_PERSONAL_CORREO_ELECTRONICO = 30L;

	/** Propiedad notificacion personal direccion de residencia. */
	public final long NOTIFICACION_PERSONAL_DIRECCION_DE_RESIDENCIA = 20L;

	/** Propiedad notificacion personal orip. */
	public final long NOTIFICACION_PERSONAL_ORIP = 10L;

	/** Propiedad predio inconsistente. */
	public final long PREDIO_INCONSISTENTE = 85L;

	/** Propiedad aviso enviado en espera fecha de acuse. */
	public final long AVISO_ENVIADO_EN_ESPERA_FECHA_DE_ACUSE = 10L;

	/** Propiedad aviso fijado. */
	public final long AVISO_FIJADO = 10L;

	/** Propiedad aviso desfijado. */
	public final long AVISO_DESFIJADO = 10L;

	/** Propiedad antiguo sistema certificados. */
	public final long ANTIGUO_SISTEMA_CERTIFICADOS = 20L;

	/** Propiedad certificado finalizado negado. */
	public final long CERTIFICADO_FINALIZADO_NEGADO = 290L;

	//Motivo etapa 214  IMPRESION DE DOCUMENTOS CORRESPONDENCIA
	/** Propiedad impresion de documentos para correspondencia. */
	public final long IMPRESION_DE_DOCUMENTOS_PARA_CORRESPONDENCIA = 10L;

	/** Propiedad mod matriculas. */
	public final long MOD_MATRICULAS = 30L;

	/** Propiedad generar consulta para entrega por email. */
	public final long NEGAR_SOLICITUD_DE_CERTIFICADOS = 40L;

	/** Propiedad negar solicitud de certificados correo electronico. */
	public final long NEGAR_SOLICITUD_DE_CERTIFICADOS_CORREO_ELECTRONICO = 770L;

	/** Propiedad negar solicitud de certificados correspondencia. */
	public final long NEGAR_SOLICITUD_DE_CERTIFICADOS_CORRESPONDENCIA = 760L;

	/** Propiedad negar solicitud de certificados orip. */
	public final long NEGAR_SOLICITUD_DE_CERTIFICADOS_ORIP = 750L;

	/** Propiedad notificado personal. */
	public final long NOTIFICADO_PERSONAL = 300L;

	/** Propiedad nota devolutiva notificacion. */
	public final long NOTA_DEVOLUTIVA_NOTIFICACION = 60L;

	/** Propiedad desistimiento entrega finalizado. */
	public final long DESISTIMIENTO_FINALIZADO = 10L;

	//Entrega Correccio SIPROCEDE
	/** Propiedad entrega Correccion Siprocede entrega orip. */
	public final long ENTREGA_CORRECCION_SI_PROCEDE_ORIP =	130L;

	/** Propiedad entrega Correccion Siprocede entrega correspondencia. */
	public final long ENTREGA_CORRECCION_SI_PROCEDE_CORRESPONDENCIA =	140L;

	/** Propiedad entrega Correccion Siprocede entrega orip. */
	public final long ENTREGA_CORRECCION_SI_PROCEDE_CORREO =	150L;

	/** Propiedad entrega correccion si procede corrección interna Finalizada. */
	public final long ENTREGA_CORRECCION_INTERNA_FINALIZADA =  160L;

	//Motivo etapa 415
	/**  Propiedad AUTO_DE_APERTURA_DE_PRUEBAS. */
	public final long AUTO_DE_APERTURA_DE_PRUEBAS  = 10L;
	
	/**  Propiedad RECHAZO_DE_RECURSOS. */
	public final long RECHAZO_DE_RECURSOS  = 20L;

	//Motivo etapa 415 ASIGNACION DE TURNOS MANUAL - RECHAZO DE RECURSOS
	/** Propiedad ETAPA_415_ANALISIS_EXPEDIENTE_RECURSO_ACTOS_DE_INSCRIPCION_O_ANOTACION. */
	public final long ETAPA_415_ANALISIS_EXPEDIENTE_RECURSO_ACTOS_DE_INSCRIPCION_O_ANOTACION = 60L;

	/** Propiedad ETAPA_415_ANALISIS_EXPEDIENTE_RECURSO_ACTUACION_ADMINISTRATIVA. */
	public final long ETAPA_415_ANALISIS_EXPEDIENTE_RECURSO_ACTUACION_ADMINISTRATIVA = 40L;

	/** Propiedad ETAPA_415_ANALISIS_EXPEDIENTE_RECURSO_DEVOLUCION_DE_DINERO. */
	public final long ETAPA_415_ANALISIS_EXPEDIENTE_RECURSO_DEVOLUCION_DE_DINERO = 50L;

	/** Propiedad ETAPA_415_ANALISIS_EXPEDIENTE_RECURSO_NOTA_DEVOLUTIVA. */
	public final long ETAPA_415_ANALISIS_EXPEDIENTE_RECURSO_NOTA_DEVOLUTIVA = 30L;

	/** Propiedad ETAPA_415_ASIGNAR_A. */
	public final long ETAPA_415_ASIGNAR_A = 10L;

	/** Propiedad ETAPA_415_ASIGNAR_A. */
	public final long ETAPA_415_RECHAZA_RECURSO = 20L;

	//Motivo etapa 420
	/** Propiedad DEVOLVER_DESDE_COORDINADOR_JURIDICO. */
	public final long DEVOLVER_DESDE_COORDINADOR_JURIDICO = 10L;

	/** Propiedad REVISION_AUTO_DE_PRUEBAS_APROBADA_POR_COORDINADOR_JURIDICO. */
	public final long REVISION_AUTO_DE_PRUEBAS_APROBADA_POR_COORDINADOR_JURIDICO = 20L;

	/** Propiedad REVISION_RESOLUCION_APROBADA_POR_COORDINADOR_JURIDICO. */
	public final long REVISION_RESOLUCION_APROBADA_POR_COORDINADOR_JURIDICO = 30L;

	/** Propiedad APROBAR_PRORROGA_DOCUMENTACION. */
	public final long APROBAR_PRORROGA_DOCUMENTACION = 40L;

	/** Propiedad NEGAR_PRORROGA_DOCUMENTACION. */
	public final long NEGAR_PRORROGA_DOCUMENTACION = 50L;

	//Motivo etapa 650
	/** Propiedad PROYECTAR_RESOLUCION_TRASLADOS. */
	public final long PROYECTAR_RESOLUCION_TRASLADOS = 10L;

	/** Propiedad SOLICITUD_DOCUMENTACION. */
	public final long SOLICITUD_DOCUMENTACION = 20L;

	/** Propiedad NEGACION_SOLICITUD_TRASLADOS. */
	public final long NEGACION_SOLICITUD_TRASLADOS = 30L;

	/** Propiedad PROYECTAR_RESOLUCION_ACEPTACION. */
	public final long PROYECTAR_RESOLUCION_ACEPTACION = 40L;

	//Motivo etapa 660	
	/** Propiedad ejecutar traslado individual. */
	public final long EJECUTAR_TRASLADO_INDIVIDUAL = 10L;

	/** Propiedad ejecutar traslado masivo. */
	public final long EJECUTAR_TRASLADO_MASIVO = 20L;

	//Motivo etapa 190, proceso 39
	/** Propiedad aprobacion resolucion traslado. */
	public final long APROBACION_RESOLUCION_TRASLADO = 820L;

	/** Propiedad negacion solicitud translados orip. */
	public final long NEGACION_SOLICITUD_TRANSLADOS_ORIP = 850L;

	/** Propiedad devolver analista translados. */
	public final long DEVOLVER_ANALISTA_TRANSLADOS = 860L;

	/**  Propiedad EN ESPERA_ENTREGA_DOCUMENTACION_TRASLADOS_CORREO_ELECTRONICO. */
	public final long ESPERA_ENTREGA_DOCUMENTACION_TRASLADOS_CORREO_ELECTRONICO = 880L;

	/**  Propiedad ESPERA_ENTREGA DOCUMENTACION DIRECCION RESIDENCIA. */
	public final long ESPERA_ENTREGA_DOCUMENTACION_TRASLADOS_DIRECCION_RESIDENCIA = 890L;

	/** Propiedad NEGACIÓN SOLICITUD DE TRASLADOS CORREO ELECTRONICO. */
	public final long NEGACION_SOLICITUD_DE_TRASLADOS_CORREO_ELECTRONICO = 830L;

	/** Propiedad negacion solicitud de traslados direccion correspondencia. */
	public final long NEGACION_SOLICITUD_DE_TRASLADOS_DIRECCION_CORRESPONDENCIA = 840L;

	/** Propiedad negacion solicitud de traslados orip. */
	public final long NEGACION_SOLICITUD_DE_TRASLADOS_ORIP = 850L;

	/**  Propiedad ESPERA_ENTREGA_DOCUMENTACION_TRASLADOS_ORIP. */
	public final long ESPERA_ENTREGA_DOCUMENTACION_TRASLADOS_ORIP = 870L;

	/** Propiedad en espera de entrega documentacion traslados correo electronico. */
	public final long EN_ESPERA_DE_ENTREGA_DOCUMENTACION_TRASLADOS_CORREO_ELECTRONICO = 880L;

	/** Propiedad en espera de entrega documentacion traslados direccion residencia. */
	public final long EN_ESPERA_DE_ENTREGA_DOCUMENTACION_TRASLADOS_DIRECCION_RESIDENCIA = 890L;

	/** Propiedad en espera de entrega documentacion traslados orip. */
	public final long EN_ESPERA_DE_ENTREGA_DOCUMENTACION_TRASLADOS_ORIP = 870L;

	/** Propiedad en espera documentacion traslados. */
	public final long EN_ESPERA_DOCUMENTACION_TRASLADOS = 290L;

	/** Propiedad en espera documentacion traslados traslado matriculas. */
	public final long EN_ESPERA_DOCUMENTACION_TRASLADOS_TRASLADO_MATRICULAS = 290L;

	/** Propiedad PROYECTAR_RESOLUCION_TRASLADOS_MASIVA. */
	public final long PROYECTAR_RESOLUCION_TRASLADOS_MASIVA = 40L;

	/** Propiedad proceso finalizado traslado individual negeado. */
	public final long PROCESO_FINALIZADO_TRASLADO_INDIVIDUAL_NEGADO = 50L;

	//Motivo etapa 200 proceso 38
	/** Propiedad solicitud documento electronico owcc traslado matriculas. */
	public final long SOLICITUD_DOCUMENTO_ELECTRONICO_OWCC_TRASLADO_MATRICULAS = 5L;
	
	//Motivo etapa 191
	
	/** Propiedad proceso traslado masivo. */
	public final long PROCESO_TRASLADO_MASIVO = 20L;
	
	/** Propiedad proceso traslado masivo. */
	public final long PROCESO_TRASLADO_INDIVIDUAL = 10L;
	
	/** Propiedad proceso finalizado traslado individual aprobado 30. */
	public final long PROCESO_FINALIZADO_TRASLADO_INDIVIDUAL_APROBADO_30 = 30L;
	
	/** Propiedad proceso finalizado traslado individual aprobado 40. */
	public final long PROCESO_FINALIZADO_TRASLADO_INDIVIDUAL_APROBADO_40 = 40L;
	
	/** Propiedad proceso finalizado traslado individual aprobado 50. */
	public final long PROCESO_FINALIZADO_TRASLADO_INDIVIDUAL_APROBADO_50 = 50L;
	
	//Motivo etapa 232
	/** Propiedad resolucion traslado individual publicada envio a ejecutor. */
	public final long RESOLUCION_TRASLADO_INDIVIDUAL_PUBLICADA_ENVIO_A_EJECUTOR = 30L;
	
	//Motivo etapa 671
	/** Propiedad proceso finalizado traslado masivo aprobado 30. */
	public final long PROCESO_FINALIZADO_TRASLADO_MASIVO_APROBADO_30 = 30L;
	
	/** Propiedad proceso finalizado traslado masivo aprobado 40. */
	public final long PROCESO_FINALIZADO_TRASLADO_MASIVO_APROBADO_40 = 40L;
	
	/** Propiedad proceso finalizado traslado masivo aprobado 50. */
	public final long PROCESO_FINALIZADO_TRASLADO_MASIVO_APROBADO_50 = 50L;
	
	//Motivo etapa 670
	/** Propiedad PUBLICACION ACTOS  ADMINISTIVOS  OFICINA ORIGEN . */
	public final long PUBLICACION_ACTOS_ADMINISTIVOS_OFICINA_ORIGEN  = 10L;
	
	//Motivo etapa 660
	/** Propiedad EJECUTAR_TRASLADO_MASIVO 10 . */
	public final long EJECUTAR_TRASLADO_MASIVO_10 = 10L;
	
	//Motivo etapa 651
	/** Propiedad aceptacion traslado proyectar resolucion. */
	public final long ACEPTACION_TRASLADO_PROYECTAR_RESOLUCION  	= 10L;
	
	/** Propiedad aceptacion traslado proyectar resolucion. */
	public final long ACEPTACION_TRASLADO_PROYECTAR_RESOLUCION_20  	= 20L;
	
	//Motivo etapa 675
	/** Propiedad aprobar resolución traslado envío preaprobar resolución. */
	public final long APROBAR_RESOLUCION_TRASLADO_ENVIO_PREAPROBAR_RESOLUCION  	= 10L;
	
	/** Propiedad devolver desde revisión juridica. */
	public final long DEVOLVER_DESDE_REVISION_JURIDICA 							= 20L;
	
	//Motivo etapa 680
	/** Propiedad aprobación resolución de creación, suspesión o modificación. */
	public final long APROBACION_RESOLUCION_CREACION_SUPRESION_MODIFICACION  	= 10L;
	
	/** Propiedad devolver desde revisión superintendente. */
	public final long DEVOLVER_DESDE_REVISION_SUPERINTENDENTE  					= 20L;
	
	//Motivo etapa 685
	/** Propiedad aprobación resolución etapa 680. */
	public final long APROBACION_RESOLUCION_680  	= 10L;
	
	/** Propiedad devolver desde despacho. */
	public final long DEVOLVER_DESDE_DESPACHO   	= 20L;
	
	//Motivo etapa 460
	/** Propiedad Asignar a 460. */
	public final long ASIGNAR_A_460  												= 10L;
	
	/** Propiedad no procede segunda instancia. */
	public final long NO_PROCEDE_SEGUNDA_INSTANCIA  								= 20L;
	
	/** Propiedad Analisis expediente recurso nota devolutiva. */
	public final long ANALISIS_EXPEDIENTE_RECURSO_NOTA_DEVOLUTIVA  					= 30L;
	
	/** Propiedad analisis expediente recurso actuación administrativa. */
	public final long ANALISIS_EXPEDIENTE_RECURSO_ACTUACION_ADMINISTRATIVA  		= 40L;
	
	/** Propiedad analisis expediente recursos devolución de dinero. */
	public final long ANALISIS_EXPEDIENTE_RECURSO_DEVOLUCION_DE_DINERO   			= 50L;
	
	/** Propiedad analisis expediente recursos actos de inscripción o anotación. */
	public final long ANALISIS_EXPEDIENTE_RECURSO_ACTOS_DE_INSCRIPCION_O_ANOTACION	= 60L;
	
	/** Propiedad asociar y o desasociar matriculas. */
	public final long ASOCIAR_Y_O_DESASOCIAR_MATRICULAS   							= 70L;
	
	/** Propiedad rechazo de recursos por ley. */
	public final long RECHAZO_DE_RECURSO_POR_LEY   									= 80L;
	
	/** Propiedad procede segunda instancia. */
	public final long PROCEDE_SEGUNDA_INSTANCIA   									= 90L;
	
	/** Propiedad en espera ratificación agente oficioso. */
	public final long EN_ESPERA_RATIFICACION_AGENTE_OFICIOSO   						= 100L;
	
	//Motivo etapa 800
	/** Propiedad generacion auto visitas. */
	public final long GENERACION_AUTO_VISITAS   	= 10L;
	
	/** Propiedad generacion resolucion visitas. */
	public final long GENERACION_RESOLUCION_VISITAS   	= 20L;
	
	//Motivo etapa 820
	/** Propiedad generacion resolucion visitas. */
	public final long RESOLUCION_APROBADA_SUPERINTENDENTE   	= 10L;
	
	//Motivo etapa 470
	/**  Propiedad resolucion de recurso aprobada. */
	public final long RESOLUCION_DE_RECURSO_APROBADA = 10L;
	
	/**  Propiedad devolcer a analista segunda instancia. */
	public final long DEVOLVER_A_ANALISTA_SEGUNDA_INSTANCIA = 20L;
	
	/**  Propiedad Auto de pruebas aprobado. */
	public final long AUTO_DE_PRUEBAS_APROBADO =30L;

	/**  Propiedad Probado no procede segunda instancia. */
	public final long APROBADO_NO_PROCEDE_SEGUNDA_INSTANCIA = 40L;
	
	/**  Propiedad aprobado rechazo de recurso por ley. */
	public final long APROBADO_RECHAZO_DE_RECURSO_POR_LEY = 50L;
	
	/**  Propiedad auto pruebas aprobado espera pruebas entrega correspondencia. */
	public final long AUTO_PRUEBAS_APROBADO_ESPERA_PRUEBAS_ENTREGA_CORRESPONDENCIA = 60L;
	
	/**  Propiedad auto pruebas aprobado espera pruebas entrega correo. */
	public final long AUTO_PRUEBAS_APROBADO_ESPERA_PRUEBAS_ENTREGA_CORREO = 70L; 
	
	//Motivo 370
	/** Propiedad presupuestal turno registral. */
	public final long PRESUPUESTAL_TURNO_REGISTRAL = 10L; 
	
	/** Propiedad presupuestal consultas nacionales. */
	public final long PRESUPUESTAL_CONSULTAS_NACIONALES = 20L;
	
	/** Propiedad no presupuestal consignacion errada. */
	public final long NO_PRESUPUESTAL_CONSIGNACION_ERRADA = 30L;
	
	/** Propiedad no presupuestal cuenta cupo. */
	public final long NO_PRESUPUESTAL_CUENTA_CUPO = 40L;
	
	/** Propiedad solicitud extemporanea. */
	public final long SOLICITUD_EXTEMPORANEA = 50L;
	
	//Motivo 375
	/** Propiedad revisión presupuestal turno registral. */
	public final long REVISION_PRESUPUESTAL_TURNO_REGISTRAL = 10L; 
	
	/** Propiedad revisión presupuestal consultas nacionales. */
	public final long REVISION_PRESUPUESTAL_CONSULTAS_NACIONALES = 20L;
	
	/** Propiedad revisión presupuestal consignación errada. */
	public final long REVISION_PRESUPUESTAL_CONSIGNACION_ERRADA = 30L;
	
	//Motivo 380
	/** Propiedad generar resolución de negación. */
	public final long GENERAR_RESOLUCION_DE_NEGACION = 20L;
	
	
	//Motivos tramite etapa 377
	
	/**  Propiedad aprobacion devolucion dinero. */
	public final long APROBACION_DEVOLUCION_DINERO =	10L;
	
	/**  Propiedad negacion devolucion dinero. */
	public final long NEGACION_DEVOLUCION_DINERO =	20L;
	
	//Motivo 385
	
	/** Propiedad devolver a etapa anterior 385. */
	public final long DEVOLVER_ETAPA_ANTERIOR_385 =	30L;
	
	//Motivo 760
	/** Propiedad aprobado solicitud apoyo nacional. */
	public final long APROBADO_SOLICITUD_APOYO_NACIONAL =	10L;
	
	/** Propiedad negar solicitud apoyo nacional. */
	public final long NEGAR_SOLICITUD_APOYO_NACIONAL =	20L;
	
	//Motivos 110 
	/** Propiedad negar solicitud creacion matricula oficio. */
	public final long NEGAR_SOLICITUD_CREACION_MATRICULA_OFICIO =	20L;
	
	/** Propiedad negar solicitud creacion matriculaa ya exstente. */
	public final long NEGAR_SOLICITUD_CREACION_MATRICULA_YA_EXISTENTE_OFICIO =	40L;
	
	/** Propiedad aprobador circulo destino. */
	public final long APROBADOR_CIRCULO_DESTINO = 10L;
	
	/** Propiedad aprobador circulo destino. */
	public final long APROBADO_CIRCULO_DESTINO = 30L;
	
	//Motivo 111
	/** Propiedad devolver buscar antiguo sistema. */
	public final long DEVOLVER_BUSCAR_ANTIGUO_SISTEMA = 20L;
	
	/** Propiedad devolver buscar antiguo sistema circulo destino. */
	public final long DEVOLVER_BUSCAR_ANTIGUO_SISTEMA_CIRCULO_DESTINO = 40L;
	
    /** Propiedad envio documentos ejecucion visitas. */
    //Motivo etapa 830 ejecución visitas
	public final long ENVIO_DOCUMENTOS_EJECUCION_VISITAS = 10L;
	
	//Motivo etapa 835 visitas
	
	/** Propiedad envio aprobacion delegado reasignacion. */
	public final long ENVIO_APROBACION_DELEGADO_REASIGNACION = 10L; 
	
	/** Propiedad envio visitador reasignacion. */
	public final long ENVIO_VISITADOR_REASIGNACION = 20L;
	
	/** Propiedad envio aprobacion delegado prorroga. */
	public final long ENVIO_APROBACION_DELEGADO_PRORROGA = 40L;
	
	/** Propiedad envio aprobacion lider prorroga. */
	public final long ENVIO_APROBACION_LIDER_PRORROGA = 30L;
	
	/** Propiedad envio visitador prorroga. */
	public final long ENVIO_VISITADOR_PRORROGA = 30L;
	
	/** Propiedad envio aprobacion lider suspension. */
	public final long ENVIO_APROBACION_LIDER_SUSPENSION = 50L;
	
	/** Propiedad envio suspension tramite visitas. */
	public final long ENVIO_SUSPENSION_TRAMITE_VISITAS = 60L;
	
	/** Propiedad envio aprobacion lider reanudacion. */
	public final long ENVIO_APROBACION_LIDER_REANUDACION = 70L;
	
	/** Propiedad envio visitador reanudacion. */
	public final long ENVIO_VISITADOR_REANUDACION = 80L;
	
	/** Propiedad envio aprobacion delegado revocatoria. */
	public final long ENVIO_APROBACION_DELEGADO_REVOCATORIA = 90L;
	
	/** Propiedad finalizado por revocatoria. */
	public final long FINALIZADO_POR_REVOCATORIA = 100L;
	
	/** Propiedad envio aprobacion delegado anulacion. */
	public final long ENVIO_APROBACION_DELEGADO_ANULACION = 110L;
	
	/** Propiedad finalizado por anulacion turno. */
	public final long FINALIZADO_POR_ANULACION_TURNO = 120L;
	
	/** Propiedad envio aprobacion delegado cierre. */
	public final long ENVIO_APROBACION_DELEGADO_CIERRE = 130L;
	
	/** Propiedad envio aprobacion visitador cierre. */
	public final long ENVIO_APROBACION_VISITADOR_CIERRE = 140L;
	
	/** Propiedad finalizado por cierre de turno. */
	public final long FINALIZADO_POR_CIERRE_DE_TURNO = 140L;
	
	/** Propiedad generacion resolucion prorroga intervencion visitador. */
	public final long GENERACION_RESOLUCION_PRORROGA_INTERVENCION_VISITADOR = 140L;
	
	/** Propiedad generacion resolucion prorroga intervencion lider. */
	public final long GENERACION_RESOLUCION_PRORROGA_INTERVENCION_LIDER = 150L;
	
	/** Propiedad generacion resolucion prorroga intervencion delegado. */
	public final long GENERACION_RESOLUCION_PRORROGA_INTERVENCION_DELEGADO = 160L;
	
	/** Propiedad resolucion levantamiento intervencion visitador. */
	public final long RESOLUCION_LEVANTAMIENTO_INTERVENCION_VISITADOR = 170L;

	/** Propiedad resolucion levantamiento intervencion lider. */
	public final long RESOLUCION_LEVANTAMIENTO_INTERVENCION_LIDER = 180L;

	/** Propiedad resolucion levantamiento intervencion delegado. */
	public final long RESOLUCION_LEVANTAMIENTO_INTERVENCION_DELEGADO = 190L;
	
	/** Propiedad oficio requerimientos. */
	public final long OFICIO_REQUERIMIENTOS = 200L;
	
	/** Propiedad resolucion levantamiento intervencion aprobacion. */
	public final long RESOLUCION_LEVANTAMIENTO_INTERVENCION_APROBACION = 10L; 
	
	/** Propiedad resolucion prorroga intervencion aprobacion. */
	public final long RESOLUCION_PRORROGA_INTERVENCION_APROBACION = 20L;
	
	/** Propiedad informe final. */
	public final long INFORME_FINAL = 300L; 
	
	/** Propiedad informe semanal. */
	public final long INFORME_SEMANAL = 310L; 
	
	/** Propiedad informe intervencion. */
	public final long INFORME_INTERVENCION = 320L; 
	
	/** Propiedad aviso convocatoria de audiencia publica de reclamaciones. */
	public final long AVISO_CONVOCATORIA_DE_AUDIENCIA_PUBLICA_DE_RECLAMACIONES = 330L; 
	
	
	/** Propiedad informe final 830. */
	public final long INFORME_FINAL_830 = 310L; 
	
	/** Propiedad informe semanal 830. */
	public final long INFORME_SEMANAL_830 = 320L; 
	
	/** Propiedad informe intervencion 830. */
	public final long INFORME_INTERVENCION_830 = 330L;
	
	/** Propiedad aviso convocatoria de audiencia publica de reclamaciones 830. */
	public final long AVISO_CONVOCATORIA_DE_AUDIENCIA_PUBLICA_DE_RECLAMACIONES_830 = 340L; 
	
	/** Propiedad resolucion levantamiento intervencion. */
	//Motivos etapa 840
	public final long RESOLUCION_LEVANTAMIENTO_INTERVENCION = 200L;
	
	/** Propiedad resolucion prorroga intervencion. */
	public final long RESOLUCION_PRORROGA_INTERVENCION = 210L;
	
	/** Propiedad resolucion levantamiento intervencion 260. */
	public final long RESOLUCION_LEVANTAMIENTO_INTERVENCION_260 = 260L;
	
	/** Propiedad resolucion prorroga intervencion 270. */
	public final long RESOLUCION_PRORROGA_INTERVENCION_270 = 270L;
	
	/** Propiedad auto reasignacion turno. */
	public final long AUTO_REASIGNACION_TURNO = 10L;
	
	/** Propiedad auto de prorroga de visita. */
	public final long AUTO_DE_PRORROGA_DE_VISITA = 30L;
	
	/** Propiedad auto de suspension del turno de visita. */
	public final long AUTO_DE_SUSPENSION_DEL_TURNO_DE_VISITA = 50L;
	
	/** Propiedad reanudacion del turno de visita. */
	public final long REANUDACION_DEL_TURNO_DE_VISITA = 70L;
	
	/** Propiedad revocatoria del turno de visita. */
	public final long REVOCATORIA_DEL_TURNO_DE_VISITA = 90L;
	
	/** Propiedad anulacion del turno de visita. */
	public final long ANULACION_DEL_TURNO_DE_VISITA = 110L;
	
	/** Propiedad cierre del turno de visita. */
	public final long CIERRE_DEL_TURNO_DE_VISITA = 130L;
	
	/** Propiedad resolucion levantamiento intervencion 840. */
	public final long RESOLUCION_LEVANTAMIENTO_INTERVENCION_840 = 180L;
	
	/** Propiedad resolucion prorroga intervencion 840. */
	public final long RESOLUCION_PRORROGA_INTERVENCION_840 = 150L;
	
	/** Propiedad auto de prorroga de visita visitador. */
	public final long AUTO_DE_PRORROGA_DE_VISITA_VISITADOR = 230L;
	
	/** Propiedad cierre del turno de visita visitador. */
	public final long CIERRE_DEL_TURNO_DE_VISITA_VISITADOR = 240L;
	
	/** Propiedad resolucion levantamiento intervencion visitador 840. */
	public final long RESOLUCION_LEVANTAMIENTO_INTERVENCION_VISITADOR_840 = 220L;

	/** Propiedad generacion resolucion prorroga intervencion visitador 840. */
	public final long GENERACION_RESOLUCION_PRORROGA_INTERVENCION_VISITADOR_840 = 250L;
	
	/** Propiedad auto de prorroga de visita visitador 840. */
	public final long AUTO_DE_PRORROGA_DE_VISITA_VISITADOR_840 = 140L;
	
	/** Propiedad cierre del turno de visita visitador 840. */
	public final long CIERRE_DEL_TURNO_DE_VISITA_VISITADOR_840 = 150L;
	
	/** Propiedad resolucion levantamiento intervencion 840 a 840. */
	public final long RESOLUCION_LEVANTAMIENTO_INTERVENCION_840_A_840 = 260L;
	
	/** Propiedad resolucion prorroga intervencion 840 a 840. */
	public final long RESOLUCION_PRORROGA_INTERVENCION_840_A_840 = 270L;
	
	/** Propiedad oficio requerimientos 840. */
	public final long OFICIO_REQUERIMIENTOS_840 = 400L; 

  /** Propiedad generar liquidacion entrega orip. */
  public final long GENERAR_LIQUIDACION_ENTREGA_ORIP = 100L;
}