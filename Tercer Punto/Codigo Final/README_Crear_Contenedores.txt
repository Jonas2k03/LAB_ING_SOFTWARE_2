Comandos para crear los contenedores de Docker:

docker run -d --restart always --name rabbitmq --network mired -p 5672:5672 -p 15672:15672 rabbitmq:3.12-management

docker run --name prueba --network mired -e POSTGRES_PASSWORD=1234 -p 6969:5432 -d postgres

docker run -p 8080:8080 --name api-container --network mired --link rabbitmq --link prueba api-image

Nota: Crear previamente la imagen de la api (IngSoftIIParcial2) con el paso a paso espeficado en Taller Clase Asincrónica de Hoy 4 de Diciembre (Docker)