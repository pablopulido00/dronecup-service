# 🏁 DroneCup Backend API

DroneCup es una **API REST backend desarrollada con Java y Spring Boot**
para gestionar competiciones de carreras de drones.

El proyecto está diseñado para demostrar buenas prácticas de desarrollo
backend:

-   Modelado de dominio realista
-   Arquitectura en capas (Controller → Service → Repository)
-   Reglas de negocio implementadas en servicios
-   Manejo correcto de errores HTTP
-   Testing unitario con JUnit y Mockito
-   Demo preparada con Postman
-   Ejecución local mediante Docker

------------------------------------------------------------------------

# 📂 Archivos incluidos en el repositorio

    postman/DroneCup API.postman_collection.json
    postman/DroneCup (template).postman_environment.json

La colección utiliza la variable:

    {{baseUrl}}

Para ejecutarla es necesario configurar esta variable en un
**environment de Postman** o usar el **environment template incluido**.

------------------------------------------------------------------------

# 🐳 Ejecución con Docker

El proyecto incluye soporte para **Docker y Docker Compose** para
facilitar la ejecución local sin necesidad de instalar MySQL
manualmente.

## Requisitos

-   Docker
-   Docker Compose

## Pasos

Clonar el repositorio:

``` bash
git clone https://github.com/pablopulido00/dronecup-service
cd dronecup-service
```

Levantar los servicios:

``` bash
docker compose up --build
```

Esto iniciará:

-   La API de **DroneCup**
-   Una base de datos **MySQL**

La API estará disponible en:

    http://localhost:8080

## Detener los contenedores

``` bash
docker compose down
```

------------------------------------------------------------------------

# 🧪 Tests

El proyecto incluye **tests unitarios con JUnit 5 y Mockito**, centrados
en la lógica de negocio.

Se prueban especialmente:

-   CRUD de equipos (**TeamService**)
-   Recalculo de clasificaciones (**StandingService**)
-   Validación de conflictos y reglas en resultados
    (**HeatResultService**)

Los tests se enfocan en verificar **comportamiento y reglas de
negocio**, no en cobertura artificial.

------------------------------------------------------------------------

# 📌 Estado del proyecto

Proyecto finalizado como **versión v1**.

Incluye:

-   API REST completamente funcional
-   Modelado de dominio completo
-   Reglas de negocio implementadas en la capa de servicios
-   Manejo de errores HTTP (400 / 404 / 409)
-   Tests unitarios clave
-   Demo preparada con Postman
-   Ejecución local mediante Docker

------------------------------------------------------------------------

# 🧠 Conclusión

DroneCup es un proyecto backend orientado a demostrar:

-   Modelado de dominio realista
-   Aplicación de reglas de negocio en servicios
-   Gestión correcta de errores HTTP
-   Testing unitario enfocado a lógica de negocio
-   Preparación de una demo clara mediante Postman
-   Ejecución reproducible con Docker
