---
config:
  theme: dark
---
erDiagram

    USUARIO {
        int id PK
        string nombre
        string email
        string password_hash
        datetime creado_en
    }

    PROFESOR {
        int id PK
        string nombre
        string email
    }

    ASIGNATURA {
        int id PK
        string nombre
        string codigo
    }

    SUSCRIPCION {
        int id PK
        int usuario_id FK
        int asignatura_id FK
        datetime creado_en
    }

    MENSAJE_CORREO {
        int id PK
        string remitente
        string asunto
        string cuerpo
        datetime recibido_en
        boolean procesado
    }

    MENSAJE_PROCESADO {
        int id PK
        int correo_id FK
        int profesor_id FK
        int asignatura_id FK
        string mensaje_final
        datetime enviado_whatsapp_en
    }

    USUARIO ||--o{ SUSCRIPCION : se_suscribe
    ASIGNATURA ||--o{ SUSCRIPCION : es_suscrita

    PROFESOR ||--o{ MENSAJE_PROCESADO : crea
    ASIGNATURA ||--o{ MENSAJE_PROCESADO : pertenece
    MENSAJE_CORREO ||--|| MENSAJE_PROCESADO : se_procesa_en