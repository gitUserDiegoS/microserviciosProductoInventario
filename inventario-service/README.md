# microservicio inventario service
Contiene artefactos inventario-service

## Table of Contents

- [Installation](#installation)
- [Usage](#usage)
- [Endpoints](#endpoints)
- [Contributing](#contributing)
- [License](#license)

## Installation

1. Para cada microservicio se debe realizar un mvn clean package -DskipTests
2. Ejecutar el archivo docker-compose.yml con el comando: docker compose up --build


Url base de microservicio inventario-service: `http://localhost:8082`


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