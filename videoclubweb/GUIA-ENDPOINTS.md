# 🎯 Guía de Uso y API Endpoints

## Endpoints disponibles

### Home
- `GET /` → Página de inicio

### Cintas (CRUD)
- `GET /cintas` → Listar todas las cintas
- `GET /cintas/nuevo` → Formulario para nueva cinta
- `GET /cintas/editar/{id}` → Formulario para editar cinta
- `POST /cintas/guardar` → Guardar o actualizar cinta
- `GET /cintas/eliminar/{id}` → Eliminar cinta

---

## Estructura de Datos

### Entidad Cinta

```json
{
  "codigoCinta": 1,
  "titulo": "Matrix",
  "director": "Lana Wachowski",
  "año": 1999,
  "genero": "Ciencia Ficción",
  "estanteria": "A",
  "estante": "1",
  "fila": "1"
}
```

---

## Ejemplos de Uso

### 1. Listar todas las cintas

**Ruta**: `GET /cintas`

**Respuesta**: HTML con tabla de cintas

```html
<table>
  <tr th:each="c : ${cintas}">
    <td th:text="${c.titulo}"></td>
  </tr>
</table>
```

---

### 2. Agregar nueva cinta

**Ruta**: `GET /cintas/nuevo`

**Formulario HTML**:

```html
<form th:action="@{/cintas/guardar}" th:object="${cinta}" method="post">
    <input type="number" th:field="*{codigoCinta}" required>
    <input type="text" th:field="*{titulo}" required>
    <input type="text" th:field="*{director}" required>
    <input type="number" th:field="*{año}" required>
    <input type="text" th:field="*{genero}" required>
    <input type="text" th:field="*{estanteria}" required>
    <input type="text" th:field="*{estante}" required>
    <input type="text" th:field="*{fila}" required>
    <button type="submit">Guardar</button>
</form>
```

**POST**: `/cintas/guardar`

---

### 3. Editar cinta

**Ruta**: `GET /cintas/editar/1`

El formulario se pre-carga con los datos existentes

**POST**: `/cintas/guardar`

---

### 4. Eliminar cinta

**Ruta**: `GET /cintas/eliminar/1`

---

## Atributos de Thymeleaf Utilizados

| Atributo | Función | Ejemplo |
|---|---|---|
| `th:text` | Mostrar texto | `<td th:text="${cinta.titulo}"></td>` |
| `th:each` | Iterar lista | `<tr th:each="c : ${cintas}">` |
| `th:href` | Enlace dinámico | `<a th:href="@{/cintas/editar/{id}(id=${c.id})}">` |
| `th:action` | Acción de formulario | `<form th:action="@{/cintas/guardar}">` |
| `th:object` | Objeto del formulario | `<form th:object="${cinta}">` |
| `th:field` | Campo enlazado | `<input th:field="*{titulo}">` |
| `th:if` | Condicional | `<div th:if="${cintas.isEmpty()}">` |

---

## Flujo de Operaciones

### Crear Cinta

```
Usuario clica "Nueva Cinta"
    ↓
GET /cintas/nuevo
    ↓
Controller: formularioNuevo() retorna formulario.html
    ↓
Usuario completa formulario
    ↓
POST /cintas/guardar
    ↓
Controller: guardar(Cinta) → Service → Repository → BD
    ↓
Redirección a GET /cintas
    ↓
Lista actualizada
```

### Editar Cinta

```
Usuario clica "Editar"
    ↓
GET /cintas/editar/{id}
    ↓
Controller: formularioEditar(id) retorna formulario.html con datos
    ↓
Usuario modifica datos
    ↓
POST /cintas/guardar
    ↓
Service: actualizar(id, cinta) → Repository → BD
    ↓
Redirección a GET /cintas
```

### Eliminar Cinta

```
Usuario clica "Eliminar"
    ↓
GET /cintas/eliminar/{id}
    ↓
Controller: eliminar(id) → Service → Repository → BD
    ↓
Redirección a GET /cintas
```

---

## Variables del Modelo

### En lista.html

```java
model.addAttribute("cintas", List<Cinta>);
```

```html
<tr th:each="c : ${cintas}">
    <td th:text="${c.codigoCinta}"></td>
    <td th:text="${c.titulo}"></td>
</tr>
```

### En formulario.html

```java
model.addAttribute("cinta", Cinta);
```

```html
<input type="text" th:field="*{titulo}">
<!-- Equivalente a: -->
<input type="text" th:value="${cinta.titulo}" name="titulo">
```

---

## Tips y Mejores Prácticas

### 1. Validación en Entity

```java
public class Cinta {
    @NotBlank(message = "El título es requerido")
    private String titulo;
    
    @Min(1800)
    @Max(2099)
    private Integer año;
}
```

### 2. DTO (Data Transfer Object)

```java
@Data
public class CintaDTO {
    private Integer codigoCinta;
    private String titulo;
}
```

### 3. Mapeo de Excepciones

```java
@ExceptionHandler(EntityNotFoundException.class)
public String handleNotFound(Model model) {
    model.addAttribute("error", "Cinta no encontrada");
    return "error";
}
```

### 4. Logging

```java
@Service
public class CintaService {
    private static final Logger log = LoggerFactory.getLogger(CintaService.class);
    
    public List<Cinta> listar() {
        log.info("Listando todas las cintas");
        return repository.findAll();
    }
}
```

---

## Debugging

### Habilitar SQL en logs

En `application.properties`:

```
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
logging.level.org.hibernate.SQL=DEBUG
```

### Ver requests/responses en Thymeleaf

```html
<!-- DEBUG: Mostrar variable en HTML -->
<div th:text="${#vars.getVariable('cintas')}"></div>
```

---

**Última actualización**: Marzo 2026
