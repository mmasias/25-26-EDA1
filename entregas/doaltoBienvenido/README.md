# Iris Message Redirector – Documentación UML

Este documento explica el **modelo UML completo** del sistema *Iris Message Redirector*, las estructuras de datos utilizadas y el flujo de procesamiento de mensajes.  
El archivo **UML principal** se encuentra en: `DocumentosUML/IrisMessageRedirector.puml`

---

## 🧩 Descripción general

Iris Message Redirector gestiona mensajes estructurados, clasificándolos por categorías y redirigiéndolos a clientes suscritos.  

**Flujo general de datos:**

1. **Emisor** envía mensajes.  
2. Los mensajes se agregan a la **ColaMensajesPendientes** (FIFO).  
3. **Procesador** extrae los mensajes de la cola y los clasifica en el **ArbolCategorias**.  
4. Se genera un **ResumenMensajes** por categoría.  
5. **GestorSuscripciones** obtiene los clientes que deben recibir el resumen.  
6. **Redirector** envía los resúmenes a los clientes.

---

## 📂 Paquetes y clases UML

### 1. Mensajes y Historial

- **Mensaje**
  - ID, Contenido, CategoriaID, Timestamp
- **ResumenMensajes**
  - ContenidoResumido
  - MensajesIncluidos → **Lista**
  - CategoriaID
  - FechaGeneracion
- **HistorialMensajes**
  - Guarda mensajes procesados → **Lista**
- **ColaMensajesPendientes**
  - Cola FIFO de mensajes
  - Métodos: `Encolar`, `Desencolar`, `EsVacia`

**Relaciones:**
- Mensaje puede estar en la **cola** o en el **historial**.  
- ResumenMensajes agrupa mensajes relacionados.

---

### 2. Gestión de Categorías

- **NodoCategoria**
  - CategoriaID, Nombre
  - Hijos → **Lista** de subcategorías
  - MensajesAsociados → **Lista**
- **ArbolCategorias**
  - Raíz del árbol
  - Métodos: `Buscar`, `Insertar`

**Relaciones:**
- Mensajes pertenecen a un NodoCategoria.  
- ResumenMensajes resume los mensajes de un NodoCategoria.

---

### 3. Suscripciones

- **Cliente**
  - ClienteID, Dirección de envío
- **Suscripcion**
  - Vincula Cliente a NodoCategoria
- **GestorSuscripciones**
  - Métodos: `Suscribir`, `ObtenerClientes`
  - `ObtenerClientes` devuelve **Lista** de clientes suscritos

**Relaciones:**
- Cliente → Suscripcion → NodoCategoria  
- GestorSuscripciones consulta suscripciones para redirigir mensajes.

---

### 4. Lógica Principal

- **Emisor**
  - Envia mensajes a ColaMensajesPendientes
- **Procesador**
  - Procesa la cola
  - Clasifica mensajes en ArbolCategorias
  - Genera ResumenMensajes
- **Redirector**
  - Redirige ResumenMensajes a clientes
- **Flujo resumido vertical:**
  - `Emisor → ColaMensajesPendientes → Procesador → ArbolCategorias → ResumenMensajes → GestorSuscripciones → Redirector → Cliente`