# Utilizar una imagen base de Maven para compilar tu proyecto
FROM maven:3.8.4-openjdk-17-slim AS build

# Establecer el directorio de trabajo
WORKDIR /app

# Copiar el archivo pom.xml para descargar las dependencias
COPY ./ProyectoTrivial/pom.xml .

# Descargar las dependencias del proyecto
RUN mvn -B dependency:go-offline

# Copiar todo el código fuente
COPY ./ProyectoTrivial/src ./src

# Compilar el proyecto especificando la versión de Java compatible (en este caso, Java 11)
RUN mvn -B -Dmaven.compiler.release=17 package -DskipTests

# Utilizar una imagen base de Java para ejecutar la aplicación compilada
FROM openjdk:11-jre-slim

# Establecer el directorio de trabajo
WORKDIR /app

# Comando para ejecutar la aplicación
CMD ["java", "-jar", "ProyectoTrivial.jar"]