FROM mysql:5.7.17

# Establecer las configuraciones necesarias
RUN echo "SET TIME_ZONE='+00:00';" > /docker-entrypoint-initdb.d/init.sql
RUN echo "SET SQL_MODE='NO_AUTO_VALUE_ON_ZERO';" >> /docker-entrypoint-initdb.d/init.sql
RUN echo "SET NAMES utf8mb4;" >> /docker-entrypoint-initdb.d/init.sql
RUN echo "SET CHARACTER_SET_CLIENT=utf8mb4;" >> /docker-entrypoint-initdb.d/init.sql
RUN echo "SET CHARACTER_SET_RESULTS=utf8mb4;" >> /docker-entrypoint-initdb.d/init.sql
RUN echo "SET COLLATION_CONNECTION=utf8mb4_unicode_ci;" >> /docker-entrypoint-initdb.d/init.sql

ENV MYSQL_ROOT_PASSWORD=root
ENV MYSQL_DATABASE=trivial
ENV MYSQL_PASSWORD=PracticaRoot

COPY trivial.sql /docker-entrypoint-initdb.d/

EXPOSE 3307