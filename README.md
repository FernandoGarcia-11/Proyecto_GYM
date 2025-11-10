# 🏋️ Gym Manager - Sistema de Gestión de Gimnasio

Sistema completo para la gestión de gimnasios desarrollado con Spring Boot y MariaDB. Permite administrar clientes, membresías, entrenamientos, pagos y generar reportes.

---

## 📋 Tabla de Contenidos

- [Demo](#-demo)
- [Características](#-características)
- [Tecnologías](#-tecnologías)
- [Requisitos Previos](#-requisitos-previos)
- [Instalación](#-instalación)
- [Configuración](#-configuración)
- [Ejecución](#-ejecución)
- [Estructura del Proyecto](#-estructura-del-proyecto)
- [API Endpoints](#-api-endpoints)
- [Base de Datos](#-base-de-datos)
- [Solución de Problemas](#-solución-de-problemas)

---

## 🎥 Demo

### Video Demostrativo Completo

Video de Muestra: https://drive.google.com/drive/folders/16gszMtVr13--bWZjFKC3G-w5hMfPq2dJ?usp=sharing
 Muestra completa del sistema en funcionamiento

## ✨ Características

- **Gestión de Clientes**: Registro, actualización y consulta de clientes
- **Sistema de Membresías**: Control de tipos de membresía y vigencias
- **Control de Entrenamientos**: Registro de rutinas y seguimiento
- **Gestión de Pagos**: Registro de pagos y formas de pago
- **Reportes**: Generación de reportes del gimnasio
- **Login de Clientes**: Autenticación mediante DPI y teléfono
- **API RESTful**: Endpoints bien documentados para todas las operaciones

---

## 🛠️ Tecnologías

### Backend
- **Java 21**
- **Spring Boot 3.3.3**
- **Spring Data JPA**
- **Spring Web**
- **Spring Validation**
- **Spring Boot Actuator**

### Base de Datos
- **MariaDB 12.0.2**
- **Conector MariaDB 3.3.3**

### Documentación
- **SpringDoc OpenAPI 2.1.0**
- **Swagger UI**

### Construcción
- **Maven 3.11.0**
- **Lombok** (reducción de código boilerplate)

---

## 📦 Requisitos Previos

Antes de ejecutar el proyecto, asegúrate de tener instalado:

1. **Java Development Kit (JDK) 21**
   - Descarga: [Adoptium Temurin](https://adoptium.net/)
   - Verifica: `java -version`

2. **MariaDB Server 12.0+**
   - Descarga: [MariaDB Downloads](https://mariadb.org/download/)
   - Puerto: `3307` (o configura el tuyo)

3. **IDE (Recomendado)**
   - IntelliJ IDEA (Ultimate o Community)
   - VS Code con Extension Pack for Java

4. **Maven** (opcional, el proyecto incluye Maven Wrapper)
   - Verifica: `mvn -version`

---

## 🚀 Instalación

### 1. Clonar o Descargar el Proyecto

```bash
cd Desktop
cd Proyec_Final/GYM
```

### 2. Configurar la Base de Datos

Abre tu cliente MariaDB y ejecuta:

```sql
CREATE DATABASE IF NOT EXISTS gym_manager;
```

### 3. Configurar las Credenciales

Edita el archivo `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mariadb://localhost:3307/gym_manager
spring.datasource.username=root
spring.datasource.password=TU_CONTRASEÑA_AQUI
```

### 4. Instalar Dependencias

```bash
# Linux/Mac
./mvnw clean install

# Windows
mvnw.cmd clean install
```

---

## ⚙️ Configuración

### Archivo `application.properties`

```properties
# Base de Datos
spring.datasource.url=jdbc:mariadb://localhost:3307/gym_manager
spring.datasource.username=root
spring.datasource.password=123456
spring.datasource.driver-class-name=org.mariadb.jdbc.Driver

# JPA/Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# Servidor
server.port=8080

# Logging
logging.level.root=INFO
logging.level.com.gym=DEBUG
```

### Configuraciones Importantes

- **`spring.jpa.hibernate.ddl-auto=update`**: Las tablas se crean/actualizan automáticamente
- **`spring.sql.init.mode=never`**: No ejecuta `schema.sql` en cada inicio
- **Puerto 8080**: El servidor se ejecuta en `http://localhost:8080`

---

## ▶️ Ejecución

### Opción 1: Desde la Terminal

```bash
# Linux/Mac
./mvnw spring-boot:run

# Windows
mvnw.cmd spring-boot:run
```

### Opción 2: Desde IntelliJ IDEA

1. Abre el proyecto en IntelliJ
2. Navega a: `src/main/java/com/gym/GymApplication.java`
3. Click derecho → **Run 'GymApplication'**

### Opción 3: Desde Maven (Panel Lateral)

1. Abre el panel de Maven (lado derecho)
2. Expande `Plugins` → `spring-boot`
3. Doble click en `spring-boot:run`

### Verificar que Funciona

Cuando el proyecto arranque, deberías ver:

```
Started GymApplication in X.XXX seconds
Tomcat started on port(s): 8080 (http)
```

Abre tu navegador: http://localhost:8080

---

## 📁 Estructura del Proyecto

```
GYM/
├── src/
│   ├── main/
│   │   ├── java/com/gym/
│   │   │   ├── config/
│   │   │   │   └── CorsConfig.java
│   │   │   ├── controller/
│   │   │   │   ├── ClienteController.java
│   │   │   │   ├── EntrenamientoController.java
│   │   │   │   ├── FormaDePagoController.java
│   │   │   │   ├── MembresiaController.java
│   │   │   │   ├── PagoController.java
│   │   │   │   └── ReporteController.java
│   │   │   ├── dto/
│   │   │   │   └── ClienteDTO.java
│   │   │   ├── exception/
│   │   │   │   └── GlobalExceptionHandler.java
│   │   │   ├── mapper/
│   │   │   │   └── ClienteMapper.java
│   │   │   ├── model/
│   │   │   │   ├── Cliente.java
│   │   │   │   ├── Entrenamiento.java
│   │   │   │   ├── FormaDePago.java
│   │   │   │   ├── Membresia.java
│   │   │   │   ├── Pago.java
│   │   │   │   ├── TipoMembresia.java
│   │   │   │   └── TipoTarjeta.java
│   │   │   ├── repository/
│   │   │   │   ├── ClienteRepository.java
│   │   │   │   ├── EntrenamientoRepository.java
│   │   │   │   ├── FormaDePagoRepository.java
│   │   │   │   ├── MembresiaRepository.java
│   │   │   │   └── PagoRepository.java
│   │   │   ├── service/
│   │   │   │   └── ClienteService.java
│   │   │   └── GymApplication.java
│   │   └── resources/
│   │       ├── application.properties
│   │       └── data.sql (opcional)
│   └── test/
├── pom.xml
└── README.md
```

---

## 🔌 API Endpoints

> **Nota:** Los siguientes endpoints están disponibles según la implementación del código. Se recomienda probarlos con Postman o Swagger UI.

### Clientes

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/clientes` | Listar todos los clientes |
| GET | `/api/clientes/{id}` | Obtener cliente por ID |
| GET | `/api/clientes/login?dpi={dpi}&telefono={tel}` | Login de cliente |
| GET | `/api/clientes/buscar?nombre={nombre}` | Buscar por nombre |
| GET | `/api/clientes/activos` | Obtener clientes activos |
| POST | `/api/clientes` | Crear nuevo cliente |
| PUT | `/api/clientes/{id}` | Actualizar cliente |
| DELETE | `/api/clientes/{id}` | Eliminar cliente |

### Membresías

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/membresias` | Listar todas las membresías |
| GET | `/api/membresias/{id}` | Obtener membresía por ID |
| POST | `/api/membresias` | Crear nueva membresía |
| PUT | `/api/membresias/{id}` | Actualizar membresía |
| DELETE | `/api/membresias/{id}` | Eliminar membresía |

### Entrenamientos

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/entrenamientos` | Listar entrenamientos |
| GET | `/api/entrenamientos/{id}` | Obtener entrenamiento por ID |
| POST | `/api/entrenamientos` | Crear entrenamiento |
| PUT | `/api/entrenamientos/{id}` | Actualizar entrenamiento |
| DELETE | `/api/entrenamientos/{id}` | Eliminar entrenamiento |

### Pagos

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/pagos` | Listar todos los pagos |
| GET | `/api/pagos/{id}` | Obtener pago por ID |
| POST | `/api/pagos` | Registrar nuevo pago |
| PUT | `/api/pagos/{id}` | Actualizar pago |
| DELETE | `/api/pagos/{id}` | Eliminar pago |

### Formas de Pago

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/formas-pago` | Listar formas de pago |
| GET | `/api/formas-pago/{id}` | Obtener forma de pago por ID |
| POST | `/api/formas-pago` | Crear forma de pago |
| PUT | `/api/formas-pago/{id}` | Actualizar forma de pago |
| DELETE | `/api/formas-pago/{id}` | Eliminar forma de pago |

### Reportes

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/reportes/clientes` | Reporte de clientes |
| GET | `/api/reportes/ingresos` | Reporte de ingresos |
| GET | `/api/reportes/membresias` | Reporte de membresías |

---

## 🗄️ Base de Datos

### Tablas Principales

#### **clientes**
```sql
- id_cliente (PK)
- nombre
- apellido
- dpi
- telefono_cliente
- email
- fecha_registro
- activo
```

#### **membresias**
```sql
- id_membresia (PK)
- id_cliente (FK)
- tipo_membresia (ENUM)
- fecha_inicio
- fecha_fin
- precio
- activa
```

#### **entrenamientos**
```sql
- id_entrenamiento (PK)
- id_cliente (FK)
- fecha
- duracion
- tipo_entrenamiento
- observaciones
```

#### **pagos**
```sql
- id_pago (PK)
- id_cliente (FK)
- id_membresia (FK)
- id_forma_pago (FK)
- monto
- fecha_pago
- descripcion
```

#### **formas_de_pago**
```sql
- id_forma_pago (PK)
- nombre_forma
- tipo_tarjeta (ENUM)
- activa
```

### Relaciones

- **Cliente** → **Membresía** (1:N)
- **Cliente** → **Entrenamiento** (1:N)
- **Cliente** → **Pago** (1:N)
- **Pago** → **FormaDePago** (N:1)
- **Pago** → **Membresía** (N:1)

---

## 🐛 Solución de Problemas

### Error: "Port 8080 is already in use"

**Solución:**
- Cambia el puerto en `application.properties`:
```properties
server.port=8081
```

### Error: "Access denied for user 'root'@'localhost'"

**Solución:**
- Verifica tu contraseña en `application.properties`
- Verifica que MariaDB esté corriendo
- Crea el usuario si no existe:
```sql
CREATE USER 'root'@'localhost' IDENTIFIED BY 'tu_password';
GRANT ALL PRIVILEGES ON gym_manager.* TO 'root'@'localhost';
FLUSH PRIVILEGES;
```

### Error: "Cannot resolve symbol" en IntelliJ

**Solución:**
1. Click derecho en el proyecto → Maven → Reload Project
2. File → Invalidate Caches / Restart

### Error: "Table doesn't exist"

**Solución:**
- Verifica que `spring.jpa.hibernate.ddl-auto=update` esté configurado
- Las tablas se crean automáticamente al iniciar la aplicación

### El proyecto no arranca

**Verifica:**
1. Java 21 está instalado: `java -version`
2. MariaDB está corriendo en el puerto 3307
3. La base de datos `gym_manager` existe
4. Las credenciales en `application.properties` son correctas

---

## 📚 Documentación Adicional

### Swagger UI (OpenAPI)

Una vez que el proyecto esté corriendo, accede a la documentación interactiva:

```
http://localhost:8080/swagger-ui.html
```

### Actuator Endpoints

Monitoreo y métricas de la aplicación:

```
http://localhost:8080/actuator
http://localhost:8080/actuator/health
```

---

## 👥 Contribución

Este es un proyecto final académico. Si deseas contribuir:

1. Haz un fork del proyecto
2. Crea una rama para tu feature: `git checkout -b feature/nueva-funcionalidad`
3. Commit tus cambios: `git commit -m 'Agregar nueva funcionalidad'`
4. Push a la rama: `git push origin feature/nueva-funcionalidad`
5. Abre un Pull Request

---

## 📄 Licencia

Este proyecto es parte de un trabajo académico para la Universidad.

---

## 📞 Contacto

Para preguntas o soporte:
- **Proyecto:** Gym Manager
- **Versión:** 1.0.0
- **Fecha:** 2025

---

## 🎯 Próximas Mejoras

- [ ] Implementar Spring Security (JWT Authentication)
- [ ] Agregar roles de usuario (Admin, Empleado, Cliente)
- [ ] Dashboard con estadísticas
- [ ] Notificaciones por email
- [ ] Sistema de reservas de clases
- [ ] Frontend con React/Angular
- [ ] API de pagos en línea
- [ ] Reportes en PDF
- [ ] Sistema de asistencia con QR

---

**¡Gracias por usar Gym Manager!** 💪

_Desarrollado con ☕ y Spring Boot_
