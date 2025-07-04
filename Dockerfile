# Usa la imagen base de Amazon Corretto JDK 17
FROM amazoncorretto:17

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /opt/app

# Copia los archivos esenciales primero para aprovechar la cache de Docker
COPY build.gradle gradlew gradlew.bat /opt/app/
COPY gradle /opt/app/gradle

# Copia el resto del código fuente
COPY . .

# Da permisos de ejecución al wrapper de Gradle
RUN chmod +x ./gradlew

# Comando por defecto (puede ser sobrescrito en Jenkins)
CMD ["./gradlew", "--version"]
