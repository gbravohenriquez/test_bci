# Test Bci

_Autor: Guillermo Bravo._


### Diagramas y extras

_En carpeta raiz /docs podrás encontrar los diagramas y una colección de postman para hacer uso de los servicios creados_

### Pre-requisitos

_Para desplegar el servicio en maquina local:_

```
Java 11.
Gradle.
Puerto 8081 disponible.
Postman (deseable).
Intellij Idea (deseable).
```

### Ejecucion

_Para arrancar el servicio en el puerto 8081:_

```
gradlew bootRun
```

_Ahora puedes consumir el servicio_

```
POST → http://localhost:8081/create -> Para la creación de usuario y obtención de un token valido
{
    "name": Guillermo,
    "email": "Guillermo@gmail.com",
    "password": Password123,
    "phones": [
        {
            "number": "3",
            "cityCode": "1",
            "contryCode": "2"
        }
    ]
}

```

_Para los siguientes endpoints es necesario incluir el token en los headers_
```
GET → http://localhost:8081/all         → Obtención de todos los usuarios
PUT → http://localhost:8081/update      → Actualización de un usuario
DEL → http://localhost:8081/delete      → Eliminación de un usuario
```
