# Reto 006

# pyEdlin

EDLIN es un editor de líneas incluido en MS-DOS y más tarde en los sistemas operativos Microsoft. Proporciona capacidades rudimentarias para editar ficheros de texto plano a través de una interfaz de comandos. Los números de línea son especificados usando números, y las operaciones son especificadas usando un sólo carácter alfabético.

EDLIN era el único editor de textos proporcionado con el MS-DOS antes de la versión 5.0 de ese sistema, cuando fue reemplazado por el editor a pantalla completa edit. Fue eliminado en la versión 6, y está probablemente diseñado a partir de los editores de líneas QED o ed.

Edlin fue creado por Tim Paterson en dos semanas en 1980, y se esperaba que tuviese una vida de seis meses. Edlin realmente fue escrito originalmente para el QDOS de Seattle Computer Products, que más tarde fue comprado por Microsoft convirtiéndose finalmente en el MS-DOS.

## RETO

Implementar una versión de edlin, que permita gestionar ficheros.

> NOTA: Aunque los ficheros que gestiona esta versión de edlin son ficheros de texto, es probable que haga falta un formato específico para su almacenamiento.

### RETOS BASE

1. Defina e implemente una manera de representar la información a almacenar, teniendo en cuenta que gestionará un fichero de texto.
1. Implemente la salida de información en pantalla, mostrando el fichero y las líneas que lo conforman.
1. Agregue a su versión de edlin la capacidad de especificar la línea activa.
1. Agregue a su versión de edlin la capacidad de editar el contenido de la línea activa.
1. Agregue a su versión de edlin la capacidad de eliminar el contenido de una línea.
1. Agregue a su versión de edlin la capacidad de intercambiar el contenido de dos líneas.

### RETOS EXTRA

- Agregue a su versión de edlin la capacidad de albergar metadatos.
- En base a lo definido arriba, plantee como implementaría la opción de deshacer (CTRL+Z) y rehacer (CTRL+Y).
- Agregue a su edlin la capacidad de guardar versiones del documento, dentro del mismo documento (de modo similar a lo que propone Google Docs).
- Una vez implementada la gesión de versiones, agregue a su edlin la capacidad de permitir la gestión colaborativa, es decir, saber qué usuario editó cada versión del mismo.

## Propuestas de interfaz

### Inicio de programa

```console

c:\> edlin edlin.eln
- Indique nombre de usuario: Manuel

```

### Gestión habitual

```console
EDLIN - Archivo: edlin.eln - Editando: Manuel
==============================================================
 1 : EDLIN es un editor de líneas incluido
 2 : en MS-DOS y más tarde en los sistemas
 3 : operativos Microsoft. Proporciona capacidades 
 4*: rudimentarias para editar ficheros de 
 5 : texto plano a través de una interfaz 
 6 : de comandos. Los números de línea son  
 7 : especificados usando números, y las 
 8 : operaciones son especificadas usando 
 9 : un sólo carácter alfabético.
10 :
==============================================================
[L]ínea activa: 4 / [E]ditar / e[L]iminar / [I]ntercambiar
[D]eshacer / [R]ehacer / [V]ersiones
```

### Gestión de versiones

```console
Archivo: autoexec.bat

Version - Autor - Fecha
==============================================================
1         Manuel   12/05/2025 12:25:05h
2         Carlos   13/05/2025 15:30:00h
3         Agustín  13/05/2025 16:30:00h
4 *       Manuel   14/05/2025 18:30:00h 
==============================================================
Versión activa: 4 / [V]olver a una versión anterior
```