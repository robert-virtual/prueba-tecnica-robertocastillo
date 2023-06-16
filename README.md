# Prueba tecnica Roberto Castillo

## Instrucciones para ejecutar el proyecto

### Requerimientos de software

- Java 17
- Postgresql <= 15

### Pasos a seguir para  ejecutar el proyecto

1. Acceder el ultimo release en el repositorio proporcionado y descargar todos los arhivos .jar
2. Acceder a una instancia de postgresql local
3. Crear un usuario y contraseña en la instancia de postgresql

> Recomendacion: usuario=rober, contraseña=superhardpass12,
> con esas credenciales no sera neceario realizar cambios en las propiedades de conexion de los proyectos,
> si elige crear su propio usuario y contraseña tendra que indicar este usuario y contraseña en el archivo
> src/main/resources/application.{properties|yml}

3. Crear las siguientes bases de datos(las tablas se crean automaticamente, solo debe crear las bases de datos)

- orders_db
- details_db
- security_db

4. Ingresar a la carpeta donde guardo los archivos del realease de github y ejecutar cada uno de los archivos jar en una terminal independiente; con los siguientes
   comandos:

```bash
java -jar eureka-service.jar
```
```bash
java -jar apigateway-service.jar
```
```bash
java -jar security-service.jar
```

```bash
java -jar products-service.jar
```
```bash
java -jar orders-service.jar
```
```bash
java -jar details-service.jar
```
```bash
java -jar payments-service.jar
```
