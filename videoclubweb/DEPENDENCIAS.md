#📦 Archivo de Dependencias del Proyecto

## Dependencias de Spring Boot

| Dependencia | Versión | Propósito |
|---|---|---|
| spring-boot-starter-web | 3.2.0 | Framework web (MVC, REST, etc.) |
| spring-boot-starter-data-jpa | 3.2.0 | ORM y persistencia con Hibernate |
| spring-boot-starter-thymeleaf | 3.2.0 | Motor de plantillas HTML |
| spring-boot-starter-validation | 3.2.0 | Validación de datos |
| spring-boot-devtools | 3.2.0 | Recarga automática durante desarrollo |

## Dependencias de Base de Datos

| Dependencia | Versión | Propósito |
|---|---|---|
| ojdbc11 | 23.3.0.23.09 | Driver JDBC para Oracle Database |

## Dependencias de Utilidades

| Dependencia | Propósito |
|---|---|
| lombok | Reducir código boilerplate (@Data, @NoArgsConstructor, etc.) |

## Dependencias de Testing (Test Scope)

| Dependencia | Propósito |
|---|---|
| spring-boot-starter-test | JUnit 5, Mockito, AssertJ, etc. |

## Versiones de Java Soportadas

- **Java 21** (recomendado)
- Java 17 (también compatible)

## Maven

El proyecto usa **Maven 3.6+** como gestor de dependencias.

### Comandos útiles:

```bash
# Instalar dependencias
mvn clean install

# Compilar sin tests
mvn clean compile

# Ejecutar tests
mvn test

# Ejecutar la aplicación
mvn spring-boot:run

# Empaquetado JAR
mvn clean package
```

## Verificar Dependencias Instaladas

```bash
mvn dependency:tree
```

## Actualizar Dependencias

```bash
mvn versions:display-dependency-updates
```

---

**Nota**: Todas las versiones se heredan de `spring-boot-starter-parent:3.2.0` excepto ojdbc11 que se especifica explícitamente.
