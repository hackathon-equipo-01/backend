<div id="top"></div>

# ♻️ Green Lab — Waste Management System (Backend)

> **Hackathon F5 · Febrero 2026**
> Sistema robusto de gestión de residuos construido con arquitectura REST API para centros educativos.

<p align="center">
  <img src="https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=java" alt="Java" />
  <img src="https://img.shields.io/badge/Spring_Boot-3.x-green?style=for-the-badge&logo=springboot" alt="Spring Boot" />
  <img src="https://img.shields.io/badge/PostgreSQL-LATEST-blue?style=for-the-badge&logo=postgresql" alt="PostgreSQL" />
</p>

---

## 👥 Authors / Autores

<table>
  <tr>
    <td align="center">
      <a href="https://github.com/Nuclea88">
        <img src="https://github.com/Nuclea88.png" width="80px" style="border-radius:50%"/><br/>
        <b>Ingrid López</b><br/>
        <sub>@Nuclea88</sub>
      </a>
    </td>
    <td align="center">
      <a href="https://github.com/krissvinti-ux">
        <img src="https://github.com/krissvinti-ux.png" width="80px" style="border-radius:50%"/><br/>
        <b>Cristina Viejó</b><br/>
        <sub>@krissvinti-ux</sub>
      </a>
    </td>
    <td align="center">
      <a href="https://github.com/FacundoGaOl">
        <img src="https://github.com/FacundoGaOl.png" width="80px" style="border-radius:50%"/><br/>
        <b>Facundo Garavaglia</b><br/>
        <sub>@FacundoGaOl</sub>
      </a>
    </td>
    <td align="center">
      <a href="https://github.com/gzifoto-spec">
        <img src="https://github.com/gzifoto-spec.png" width="80px" style="border-radius:50%"/><br/>
        <b>Xavier Piñeiro</b><br/>
        <sub>@gzifoto-spec</sub>
      </a>
    </td>
    <td align="center">
      <a href="https://github.com/Sergiofer1994">
        <img src="https://github.com/Sergiofer1994.png" width="80px" style="border-radius:50%"/><br/>
        <b>Sergio Fernandez</b><br/>
        <sub>@Sergiofer1994</sub>
      </a>
    </td>
    <td align="center">
      <a href="https://github.com/alvarezmarlen">
        <img src="https://github.com/alvarezmarlen.png" width="80px" style="border-radius:50%"/><br/>
        <b>Marlen Alvarez</b><br/>
        <sub>@alvarezmarlen</sub>
      </a>
    </td>
  </tr>
</table>

---

## 🌐 English Version

### 📖 Description
Green Lab is a full-stack solution to gamify recycling in schools. This repository contains the **Core API**, which handles point logic, container capacity alerts, and user authentication.

### 🚀 Tech Stack
- **Spring Boot 3.5**: Core framework.
- **Spring Data JPA**: Database abstraction.
- **PostgreSQL**: Main database.
- **JUnit 5 & Mockito**: Unit and Integration testing (ensuring business logic reliability).
- **H2**: In-memory database for testing environments.

### 🛠️ API Endpoints (Highlights)
- `POST /api/auth/login`: User authentication.
- `POST /api/discarded-waste`: Process recycling (Validates type and adds +10 pts).
- `GET /api/classrooms/{id}`: Fetch classroom points and statistics.

---

## 🌐 Versión en Español

### 📖 Descripción
Green Lab es una solución integral para gamificar el reciclaje en las escuelas. Este repositorio contiene la **API Principal**, encargada de la lógica de puntos, alertas de capacidad en contenedores y autenticación de usuarios.

### 🚀 Tecnologías
- **Spring Boot 3.5**: Framework principal.
- **Spring Data JPA**: Abstracción de base de datos.
- **PostgreSQL**: Base de datos principal.
- **JUnit 5 & Mockito**: Tests unitarios y de integración (garantizando la fiabilidad de la lógica).
- **H2**: Base de datos en memoria para entornos de test.

### 🛠️ Puntos de Entrada API (Destacados)
- `POST /api/auth/login`: Autenticación de usuarios.
- `POST /api/discarded-waste`: Procesar reciclaje (Valida el tipo y suma +10 pts).
- `GET /api/classrooms/{id}`: Obtener puntos y estadísticas del aula.

---

## 📂 Estructura del Proyecto
```text
src/main/java/com/hackaton/waste/
  ├── controller/   # Controladores REST (Endpoints)
  ├── service/      # Lógica de Negocio (Puntos, validaciones)
  ├── entity/        # Entidades (User, Classroom, Waste, Container)
  ├── repository/   # Interfaces de persistencia
  └── config/       # Configuración de CORS y Seguridad
