# Usa la imagen base de Amazon Corretto JDK 17
FROM amazoncorretto:17

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /opt/app

# Copia los archivos del proyecto al contenedor
COPY . .

# Da permisos de ejecución al wrapper de Gradle
RUN chmod +x ./gradlew

# Comando por defecto (puede sobreescribirse en Jenkins)
CMD ["./gradlew", "--version"]
