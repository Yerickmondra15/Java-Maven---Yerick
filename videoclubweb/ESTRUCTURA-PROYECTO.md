# 📂 Estructura Completa del Proyecto Videoclub

```
videoclubweb/
│
├── 📄 pom.xml                          # Configuración Maven
├── 📄 .gitignore                       # Archivos a ignorar en Git
├── 📄 README.md                        # Documentación principal
├── 📄 INSTRUCCIONES.md                 # Pasos de instalación
├── 📄 DEPENDENCIAS.md                  # Lista de dependencias
├── 📄 GUIA-ENDPOINTS.md                # Guía de endpoints y uso
├── 📄 SQL-SETUP.sql                    # Script para crear tablas en Oracle
│
├── 📁 src/main/java/com/videoclub/
│   │
│   ├── 📁 controller/
│   │   ├── HomeController.java         # Controlador de página inicio
│   │   └── CintaController.java        # Controlador CRUD de cintas
│   │
│   ├── 📁 service/
│   │   └── CintaService.java           # Lógica de negocio
│   │
│   ├── 📁 repository/
│   │   └── CintaRepository.java        # JpaRepository para BD
│   │
│   ├── 📁 entity/
│   │   └── Cinta.java                  # Entidad mapeada a tabla
│   │
│   └── VideoclubwebApplication.java    # Clase principal Spring Boot
│
├── 📁 src/main/resources/
│   │
│   ├── 🎨 application.properties       # Configuración de BD y Spring
│   │
│   ├── 📁 templates/
│   │   ├── index.html                  # Página inicio
│   │   └── 📁 cintas/
│   │       ├── lista.html              # Tabla de cintas
│   │       └── formulario.html         # Formulario agregar/editar
│   │
│   └── 📁 static/
│       ├── 📁 css/
│       │   └── style.css               # Estilos CSS
│       └── 📁 js/
│           └── app.js                  # JavaScript de validación
│
└── 📁 src/test/java/com/videoclub/
    └── 📁 service/
        └── CintaServiceTest.java       # Tests unitarios
```

---

## 🔄 Flujo Completo de la Aplicación

```
┌─────────────────────────────────────────────────────────────────┐
│                    NAVEGADOR DEL USUARIO                        │
└─────────────────────────────────────────────────────────────────┘
                              ↓
                    (HTTP Request en /cintas)
                              ↓
┌─────────────────────────────────────────────────────────────────┐
│                     SPRING BOOT SERVER                          │
│ ┌──────────────────────────────────────────────────────────┐   │
│ │  CONTROLADOR (CintaController)                           │   │
│ │  - Recibe petición HTTP                                  │   │
│ │  - Llama a Service                                       │   │
│ │  - Agrega datos a Model                                  │   │
│ └──────────────────────────────────────────────────────────┘   │
│                              ↓                                   │
│ ┌──────────────────────────────────────────────────────────┐   │
│ │  SERVICIO (CintaService)                                 │   │
│ │  - Lógica de negocio                                     │   │
│ │  - Valida datos                                          │   │
│ │  - Llama a Repository                                    │   │
│ └──────────────────────────────────────────────────────────┘   │
│                              ↓                                   │
│ ┌──────────────────────────────────────────────────────────┐   │
│ │  REPOSITORIO (CintaRepository)                           │   │
│ │  - Acceso a datos                                        │   │
│ │  - Generado automáticamente por JPA                      │   │
│ │  - Ejecuta queries en BD                                 │   │
│ └──────────────────────────────────────────────────────────┘   │
│                              ↓                                   │
└─────────────────────────────────────────────────────────────────┘
                              ↓
┌─────────────────────────────────────────────────────────────────┐
│                  ORACLE AUTONOMOUS DATABASE                     │
│  Tabla CINTAS: {"CODIGO_CINTA", "TITULO", "DIRECTOR", ...}    │
└─────────────────────────────────────────────────────────────────┘
                              ↓
                    (Respuesta: List<Cinta>)
                              ↓
┌─────────────────────────────────────────────────────────────────┐
│                     THYMELEAF RENDERER                          │
│  - Template: lista.html                                         │
│  - Genera HTML dinámico con th:each, th:text, etc              │
│ ┌──────────────────────────────────────────────────────────┐   │
│ │  <table>                                                  │   │
│ │    <tr th:each="c : ${cintas}">                          │   │
│ │      <td th:text="${c.titulo}"></td>                     │   │
│ │    </tr>                                                  │   │
│ │  </table>                                                 │   │
│ └──────────────────────────────────────────────────────────┘   │
└─────────────────────────────────────────────────────────────────┘
                              ↓
                    (HTML Response)
                              ↓
┌─────────────────────────────────────────────────────────────────┐
│                    NAVEGADOR DEL USUARIO                        │
│          (Muestra tabla con las cintas registradas)             │
└─────────────────────────────────────────────────────────────────┘
```

---

## 🎯 Operaciones CRUD

### CREATE (Agregar Cinta)
```
Usuario → GET /cintas/nuevo → FormularioHTML → Usuario completa
         ↓
       POST /cintas/guardar → Service.guardar() → BD
         ↓
       Redirección: GET /cintas → Lista actualizada
```

### READ (Listar Cintas)
```
Usuario → GET /cintas → Controller.listar()
         ↓
       Service.listar() → Repository.findAll() → BD
         ↓
       Thymeleaf genera HTML con table
         ↓
       HTML → Navegador
```

### UPDATE (Editar Cinta)
```
Usuario → GET /cintas/editar/{id} → FormularioHTML pre-cargado
         ↓
       Usuario modifica
         ↓
       POST /cintas/guardar → Service.actualizar() → BD
         ↓
       Redirección: GET /cintas → Lista actualizada
```

### DELETE (Eliminar Cinta)
```
Usuario → GET /cintas/eliminar/{id} → Service.eliminar()
         ↓
       Repository.deleteById(id) → BD
         ↓
       Redirección: GET /cintas → Lista actualizada
```

---

## 📊 Dependencias Visuales

```
VideoclubwebApplication (Main)
    ↓
HomeController (GET /)
    ↓
CintaController (GET/POST /cintas/...)
    ↓
CintaService (Lógica)
    ↓
CintaRepository (JpaRepository)
    ↓
Cinta (Entity)
    ↓
CINTAS Table (Oracle)
    ↓
Templates HTML (Thymeleaf)
    ↓
CSS + JavaScript (Static)
```

---

## ✅ Checklist de Archivos Creados

- ✅ Estructura de carpetas Spring Boot
- ✅ pom.xml con todas las dependencias
- ✅ application.properties configurado
- ✅ Entity (Cinta.java)
- ✅ Repository (CintaRepository.java)
- ✅ Service (CintaService.java)
- ✅ Controllers (HomeController.java, CintaController.java)
- ✅ Templates HTML (index.html, lista.html, formulario.html)
- ✅ Estilos CSS (style.css)
- ✅ JavaScript (app.js)
- ✅ SQL Script (SQL-SETUP.sql)
- ✅ Documentación (README.md, INSTRUCCIONES.md, etc.)
- ✅ Testing (CintaServiceTest.java)
- ✅ .gitignore configurado

---

## 🚀 Próximos Pasos

1. **Configurar wallet Oracle** en `C:/wallet/videoclub`
2. **Editar application.properties** con credenciales
3. **Ejecutar SQL-SETUP.sql** para crear tabla
4. **Ejecutar `mvn clean install`** para instalar dependencias
5. **Ejecutar `mvn spring-boot:run`** para iniciar servidor
6. **Abrir http://localhost:8080** en navegador

---

**¡Proyecto completamente generado! 🎉**

