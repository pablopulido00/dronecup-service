# 🏁 DroneCup Backend API

## 📁 Archivos incluidos en el repositorio

```
postman/DroneCup API.postman_collection.json
postman/DroneCup (template).postman_environment.json
```

La colección utiliza la variable `{{baseUrl}}`.

Para ejecutarla, es necesario configurar dicha variable en un entorno de Postman o usar el *environment template* incluido.

---

## 🛠️ Ejecución en local (opcional)

Clona el repositorio:

```bash
git clone https://github.com/pablopulido00/dronecup-service
```

Configura las variables de entorno de MySQL:

- `SPRING_DATASOURCE_URL`
- `SPRING_DATASOURCE_USERNAME`
- `SPRING_DATASOURCE_PASSWORD`

Arranca la aplicación.

---

## ✅ Tests

El proyecto incluye tests unitarios con **JUnit 5** y **Mockito**, centrados en la lógica de negocio, especialmente:

- CRUD de equipos (`TeamService`)
- Recalculo de clasificaciones (`StandingService`)
- Validación de conflictos y reglas en resultados (`HeatResultService`)

Los tests se enfocan en verificar **comportamiento y reglas**, no en cobertura artificial.

---

## 📌 Estado del proyecto

Proyecto finalizado como versión **v1**:

- API funcional y desplegada en Railway
- Reglas de negocio implementadas
- Tests unitarios clave
- Demo preparada con Postman

---

## 🏁 Conclusión

**DroneCup** es un proyecto backend orientado a demostrar:

- Modelado de dominio realista
- Aplicación de reglas de negocio en servicios
- Gestión correcta de errores HTTP
- Testing unitario enfocado a lógica de negocio
- Preparación de una demo clara mediante Postman
