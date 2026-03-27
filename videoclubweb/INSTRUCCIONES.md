# Instrucciones de Instalación y Ejecución

## Paso 1: Configurar Oracle Autonomous Database

1. Accede a https://cloud.oracle.com
2. Crea una Autonomous Database (tipo: Transaction Processing)
3. Usuario: ADMIN
4. Descarga el Wallet
5. Extrae en: `C:/wallet/videoclub`

## Paso 2: Configurar application.properties

Abre `src/main/resources/application.properties` y ajusta:
- `spring.datasource.password`: Tu contraseña de ADMIN
- `spring.datasource.url`: Nombre de tu BD

## Paso 3: Instalar y Ejecutar

```bash
# En PowerShell (Windows)
cd videoclubweb

# Instalar dependencias
mvn clean install

# Ejecutar
mvn spring-boot:run
```

## Accesso a la aplicación

- **URL**: http://localhost:8080
- **Menú**: Inicio → Cintas → Agregar/Editar/Eliminar

## Troubleshooting

### La BD no conecta
- Verificar ruta del Wallet
- Revisar TNS_ADMIN en application.properties
- Confirmar credenciales

### Puerto ocupado
- Cambiar en application.properties: `server.port=9090`

### Maven no reconocido
- Instalar Maven o usar `./mvnw` en lugar de `mvn`

