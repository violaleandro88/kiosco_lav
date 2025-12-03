🧾 Proyecto Final – Kiosco Ventas

Programación Orientada a Objetos (POO)

Este repositorio contiene el trabajo práctico final del módulo de Programación Orientada a Objetos.
El objetivo del proyecto es completar las partes faltantes del sistema para que funcione correctamente, trabajando sobre una base ya creada y conectada en la nube.

✔️ Objetivo del trabajo

Completar una entidad faltante del sistema.

Implementar lo necesario en su:

Repositorio

Service

Controller

No modificar el SQL, las vistas ni la configuración de la base.

Guiarse por las demás entidades, que ya están completas.

✔️ Tecnologías utilizadas

Java

Spring MVC

Spring JDBC (JdbcTemplate)

RowMapper con lambdas

MySQL en la nube

✔️ Cambios importantes respecto a clases previas
JdbcTemplate

Reemplaza el uso manual de DataSource.

Maneja conexiones, prepared statements, parámetros, ejecución y cierre automático.

Convierte SQLException en excepciones de Spring (DataAccessException).

RowMapper

Reemplaza el mapeo manual dentro del while.

Usa lambdas para convertir cada fila en un objeto Java.

Controllers

Nuevas formas de manejar mensajes y redirecciones.

Ejemplos explicados dentro de ClienteController.
