# 🎬 Videoclub Management System

Sistema web de gestión de videoclubs desarrollado con **Spring Boot**, **Thymeleaf** y **Oracle Autonomous Database**.

## 📋 Requisitos Previos

- Java 21 o superior
- Maven 3.6+
- Oracle Autonomous Database configurado
- Wallet de Oracle descargado

## 🚀 Instalación y Configuración

### 1. Configurar la Base de Datos Oracle

1. Ir a https://cloud.oracle.com
2. Crear una Autonomous Database (Transaction Processing)
3. Descargar el Wallet
4. Extraer en: `C:/wallet/videoclub`

### 2. Configurar `application.properties`

Editar `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:oracle:thin:@videoclub_high?TNS_ADMIN=C:/wallet/videoclub
spring.datasource.username=ADMIN
spring.datasource.password=TU_PASSWORD
```

### 3. Instalar Dependencias

```bash
mvn clean install
```

### 4. Ejecutar la Aplicación

```bash
mvn spring-boot:run
```

La aplicación estará disponible en: **http://localhost:8080**

## 📁 Estructura del Proyecto

```
videoclubweb/
├── src/main/java/com/videoclub/
│   ├── controller/          # Controladores MVC
│   ├── service/             # Lógica de negocio
│   ├── repository/          # Acceso a datos
│   ├── entity/              # Entidades JPA
│   └── VideoclubwebApplication.java
├── src/main/resources/
│   ├── templates/           # Vistas Thymeleaf
│   │   ├── index.html
│   │   └── cintas/
│   │       ├── lista.html
│   │       └── formulario.html
│   ├── static/
│   │   ├── css/style.css
│   │   └── js/app.js
│   └── application.properties
└── pom.xml
```

## 🎯 Funcionalidades

- ✅ Listar todas las cintas
- ✅ Agregar nueva cinta
- ✅ Editar cinta existente
- ✅ Eliminar cinta
- ✅ Interfaz responsiva con Thymeleaf

## 🏗️ Arquitectura

### Flujo de Datos

```
Usuario → Controller → Service → Repository → DB (Oracle)
↓
Service → Controller → Thymeleaf → HTML → Usuario
```

### Capas

1. **Controller**: Recibe peticiones HTTP, maneja vistas
2. **Service**: Contiene lógica de negocio
3. **Repository**: Acceso a datos con JPA
4. **Entity**: Mapeo de tablas de BD

## 🛠️ Tecnologías Utilizadas

- **Spring Boot 3.2.0**
- **Spring Data JPA** - Acceso a datos automático
- **Thymeleaf** - Motor de plantillas HTML
- **Oracle JDBC Driver** - Conexión a BD
- **Lombok** - Reducir código boilerplate
- **Maven** - Gestión de dependencias

## 📝 Ejemplos de Código

### Entity (Cinta.java)

```java
@Entity
@Table(name = "CINTAS")
@Data
public class Cinta {
    @Id
    private Integer codigoCinta;
    private String titulo;
    private String director;
}
```

### Service

```java
@Service
public class CintaService {
    public List<Cinta> listar() {
        return repository.findAll();
    }
}
```

### Controller

```java
@Controller
@RequestMapping("/cintas")
public class CintaController {
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("cintas", service.listar());
        return "cintas/lista";
    }
}
```

### Template (Thymeleaf)

```html
<tr th:each="c : ${cintas}">
    <td th:text="${c.codigoCinta}"></td>
    <td th:text="${c.titulo}"></td>
</tr>
```

## 🐛 Troubleshooting

### Error de conexión a Oracle
- Verificar que el Wallet está en la ruta correcta
- Revisar credenciales en `application.properties`
- Confirmar que la BD está activa

### Puerto 8080 en uso
- Cambiar puerto en `application.properties`: `server.port=9090`

### Lombok no funciona
- Instalar plugin de Lombok en el IDE
- Rebuildear el proyecto

## 📖 Recursos Adicionales

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Thymeleaf Guide](https://www.thymeleaf.org)
- [Oracle Database Docs](https://docs.oracle.com/en/database/)

## 📄 Licencia

Este proyecto es de uso educativo.

---

**Autor**: Yerick  
**Fecha**: Marzo 2026
