🧾 Proyecto Final – Kiosco Ventas

Sistema desarrollado con el modelo MVC aplicando Programación Orientada a Objetos.

Este proyecto corresponde al trabajo práctico final del módulo de POO. El objetivo fue completar las partes faltantes del sistema para garantizar su funcionamiento, trabajando sobre una base de datos ya creada y conectada en la nube.

📘 Conceptos técnicos aplicados

Modelo MVC — Organización en capas (Repositorio → Service → Controller) — Patrón de responsabilidad por capas — Validación de datos y manejo de flujos — Uso de plantillas existentes sin modificar vistas ni SQL — Integración con base de datos mediante abstracción y mapeo de entidades — Manejo de mensajes y redirecciones en controladores.

🏦 Objetivo funcional del trabajo

Completar la entidad faltante del sistema e implementar su repositorio, servicio y controlador correspondiente, respetando la estructura de las demás entidades ya completas y sin modificar vistas, SQL ni configuración de la base.

🛠️ Tecnologías y herramientas utilizadas

Java — Spring MVC — Spring JDBC con JdbcTemplate — RowMapper con expresiones lambda — MySQL en la nube como base de datos.

🔧 Mejoras respecto a lo visto en clase

JdbcTemplate: reemplaza el uso manual de DataSource, administra conexiones, prepared statements, ejecución y cierre automático, y convierte SQLException en excepciones runtime (DataAccessException).
RowMapper: simplifica el mapeo de filas, reemplazando el proceso manual dentro del while; permite convertir registros en objetos Java mediante lambdas.
Controllers: incorporan un manejo más claro de mensajes y redirecciones, con ejemplos documentados en ClienteController.
