```mermaid
classDiagram
direction LR

class Usuario {
  -String idUsuario
  -String telefonoE164
  -boolean activo
}

class Asignatura {
  -String idAsignatura
  -String codigo
  -String nombre
}

class Suscripcion {
  -String idSuscripcion
  -String idUsuario
  -String idAsignatura
  -boolean habilitada
  -long fechaAltaEpochMs
}

class Correo {
  -String idMensaje
  -String remitente
  -String asuntoOriginal
  -String cuerpoOriginal
  -long fechaRecepcionEpochMs
}

class Adjunto {
  -String idAdjunto
  -String nombreArchivo
  -String tipoMime
  -long tamanoBytes
}

class EstadoEntrega {
  -String idEntrega
  -String idMensaje
  -String idUsuario
  -String idAsignatura
  -String estado
  -int intentos
  -long siguienteIntentoEpochMs
  -String ultimoCodigoError
}

class IngestorCorreo {
  +ingerirCrudo(payloadCrudo)
}

class AnalizadorCorreo {
  <<interface>>
  +analizar(payloadCrudo)
}

class ResolutorAsignatura {
  <<interface>>
  +resolver(correo)
}

class ServicioNotificaciones {
  +procesarCorreo(correo)
}

class Despachador {
  +encolarEntregas(idMensaje, idAsignatura)
}

class TrabajadorPlanificado {
  +procesarEnvios()
}

class LimitadorTasa {
  +intentarAdquirir(clave)
}

class PoliticaReintento {
  +siguienteEsperaMs(intento)
  +debeReintentar(codigoError, intento)
}

class RegistroAuditoria {
  +registrar(tipoEvento, correlacion, detalle)
}

class PasarelaWhatsApp {
  <<interface>>
  +enviarMensaje(telefono, texto, claveIdempotencia)
}

class RepositorioCorreos {
  <<interface>>
  +existePorIdMensaje(idMensaje)
  +guardar(correo, idAsignatura)
}

class RepositorioOutbox {
  <<interface>>
  +agregarPendiente(idMensaje, idAsignatura, idUsuario)
  +cargarVencidos()
  +marcarEnviado()
  +marcarFallido()
}

class IndiceAsignaturas {
  -ArbolBalanceado
  -TrieAlias
}

class IndiceSuscripciones {
  -TreeMapAsignaturaUsuarios
}

class CacheCorreosLRU {
  -int capacidad
}

Usuario "1" <-- "0..*" Suscripcion
Asignatura "1" <-- "0..*" Suscripcion
Correo "1" o-- "0..*" Adjunto

IngestorCorreo --> AnalizadorCorreo
IngestorCorreo --> ServicioNotificaciones
ServicioNotificaciones --> ResolutorAsignatura
ServicioNotificaciones --> Despachador
ServicioNotificaciones --> RepositorioCorreos
ServicioNotificaciones --> IndiceAsignaturas
ServicioNotificaciones --> CacheCorreosLRU
Despachador --> IndiceSuscripciones
Despachador --> RepositorioOutbox
TrabajadorPlanificado --> RepositorioOutbox
TrabajadorPlanificado --> PasarelaWhatsApp
TrabajadorPlanificado --> LimitadorTasa
TrabajadorPlanificado --> PoliticaReintento
TrabajadorPlanificado --> RegistroAuditoria
