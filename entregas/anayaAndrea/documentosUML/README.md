```mermaid
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
