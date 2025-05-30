# microserviciosProductoInventario
Contiene artefactos Producto-service, inventario-service

## Table of Contents

- [Installation](#installation)
- [Usage](#usage)
- [Endpoints](#endpoints)
- [Contributing](#contributing)
- [License](#license)

## Installation

1. Para cada microservicio se debe realizar un mvn clean package -DskipTests
2. Ejecutar el archivo docker-compose.yml con el comando: docker compose up --build

Url base de microservicio producto-service: `http://localhost:8081`
Url base de microservicio inventario-service: `http://localhost:8082`

## Endpoints producto-service

### 1. POST - Crear producto

`http://localhost:8081/api/productos`

**Request:**

```json
{
  "nombre": "Test Product",  
  "precio": 49.99
}
```

**Response (HTTP 200):**

```json
{
    "id": 2,
    "nombre": "TestDos Product",
    "precio": 49.99
}
```

### 2. Get - Consultar productos

`http://localhost:8081/api/productos`



**Response (HTTP 200):**

```json
{
    "content": [
        {
            "id": 1,
            "nombre": "Test Product",
            "precio": 49.99
        }
    ],
    "pageable": {
        "pageNumber": 0,
        "pageSize": 10,
        "sort": {
            "sorted": true,
            "empty": false,
            "unsorted": false
        },
        "offset": 0,
        "paged": true,
        "unpaged": false
    },
    "totalPages": 1,
    "totalElements": 1,
    "last": true,
    "size": 10,
    "number": 0,
    "sort": {
        "sorted": true,
        "empty": false,
        "unsorted": false
    },
    "numberOfElements": 1,
    "first": true,
    "empty": false
}
```

### 3. GET - Consultar un solo producto

`http://localhost:8081/api/productos/1`

**Response:**

```json
{
    "id": 1,
    "nombre": "Test Product",
    "precio": 49.99
}
```


## Endpoints inventario-service


### 1. GET - consultar inventarios con informacion de producto

`http://localhost:8082/api/inventarios/1`

**Response:**

```json
{
    "productoDto": {
        "id": 1,
        "nombre": "Test Product",
        "precio": 49.99
    },
    "cantidad": 20.0
}
```

Consultar collecccion postman adjuntada