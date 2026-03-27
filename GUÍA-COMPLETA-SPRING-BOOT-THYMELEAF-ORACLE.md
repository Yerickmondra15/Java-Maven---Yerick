# GUÍA COMPLETA - SPRING BOOT + THYMELEAF + ORACLE (FORMATO COPILOT)

## 1. DESCRIPCIÓN GENERAL

Este proyecto consiste en desarrollar un sistema web usando:

- Spring Boot (backend)
- Thymeleaf (vistas HTML dinámicas)
- Oracle Autonomous Database (base de datos en la nube)

Arquitectura utilizada: MVC (Modelo - Vista - Controlador)

Flujo:
Usuario → Controller → Service → Repository → DB → Service → Controller → Thymeleaf → HTML → Usuario

---

## 2. ARQUITECTURA POR CAPAS

### Controller:
- Recibe peticiones HTTP
- Retorna vistas

### Service:
- Contiene lógica de negocio

### Repository:
- Acceso a datos (CRUD automático con JPA)

### Entity:
- Representa tablas de la base de datos

---

## 3. TECNOLOGÍAS CLAVE

### Spring Boot:
- Configuración automática
- Inyección de dependencias

### Thymeleaf:
- HTML dinámico con atributos:
  - `th:text`
  - `th:each`
  - `th:href`
  - `th:action`
  - `th:field`

### Oracle Autonomous DB:
- Base de datos en la nube
- Conexión segura mediante WALLET

---

## 4. CREACIÓN DE BASE DE DATOS

1. Ir a https://cloud.oracle.com
2. Crear Autonomous Database
3. Tipo: Transaction Processing (ATP)
4. Usuario: ADMIN
5. Descargar Wallet
6. Extraer en: `C:/wallet/videoclub`

---

## 5. CREACIÓN DEL PROYECTO

Usar Spring Initializr:
- Language: Java
- Spring Boot: 3.x
- Group: `com.videoclub`
- Artifact: `videoclubweb`
- Java: 17 o 21

Dependencias:
- Spring Web
- Spring Data JPA
- Thymeleaf
- Oracle Driver
- Lombok
- Validation
- DevTools

---

## 6. CONFIGURACIÓN `application.properties`

```
spring.application.name=videoclubweb
server.port=8080

spring.datasource.url=jdbc:oracle:thin:@videoclub_high?TNS_ADMIN=C:/wallet/videoclub
spring.datasource.username=ADMIN
spring.datasource.password=TU_PASSWORD
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver

spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=true
spring.jpa.database-platform=org.hibernate.dialect.OracleDialect
```

---

## 7. ESTRUCTURA DEL PROYECTO

- `controller/`
- `service/`
- `repository/`
- `entity/`

---

## 8. ENTIDADES (EJEMPLO)

```java
@Entity
@Table(name = "CINTAS")
public class Cinta {

    @Id
    private Integer codigoCinta;

    private String estanteria;
    private String estante;
    private String fila;

    // getters y setters
}
```

---

## 9. REPOSITORIOS

```java
public interface CintaRepository extends JpaRepository<Cinta, Integer> {}
```

---

## 10. SERVICIOS

```java
@Service
public class CintaService {

    private final CintaRepository repo;

    public CintaService(CintaRepository repo) {
        this.repo = repo;
    }

    public List<Cinta> listar() {
        return repo.findAll();
    }

    public Cinta guardar(Cinta c) {
        return repo.save(c);
    }

    public void eliminar(Integer id) {
        repo.deleteById(id);
    }
}
```

---

## 11. CONTROLADORES

```java
@Controller
@RequestMapping("/cintas")
public class CintaController {

    private final CintaService service;

    public CintaController(CintaService service) {
        this.service = service;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("cintas", service.listar());
        return "cintas/lista";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Cinta c) {
        service.guardar(c);
        return "redirect:/cintas";
    }
}
```

---

## 12. THYMELEAF - EJEMPLOS

Mostrar datos:
- `<td th:text="${c.codigoCinta}"></td>`

Iterar:
- `<tr th:each="c : ${cintas}">`

Formulario:
```
<form th:action="@{/cintas/guardar}" th:object="${cinta}" method="post">
    <input type="text" th:field="*{estanteria}" />
</form>
```

---

## 13. VISTAS

- `index.html`: menú principal
- `cintas/lista.html`
- `cintas/formulario.html`

---

## 14. EJECUCIÓN

En terminal:
- `./mvnw spring-boot:run`

Abrir navegador:
- `http://localhost:8080`

---

## 15. CONCEPTOS CLAVE PARA COPILOT

- Usar anotaciones: `@Controller`, `@Service`, `@Repository`, `@Entity`
- Seguir patrón MVC
- CRUD automático con `JpaRepository`
- Thymeleaf para renderizar HTML dinámico
- No escribir SQL manual (JPA lo hace)
- Inyección de dependencias (NO usar `new`)

---

FIN DE LA GUÍA
