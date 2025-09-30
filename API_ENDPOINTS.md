# Totem Backend API Endpoints

This document describes all the CRUD endpoints created for the Totem project to test and validate database information.

## Architecture

The API uses a simplified architecture pattern:
- **Main Services**: Each entity has its own service (BuildingService, CompanyService, StreetService, CoordinateService)
- **Relationship Management**: All relationship operations are handled within the main services (no separate services for BuildingCompany or BuildingStreet)
- **Repositories**: One repository per entity, including relationship entities

## Base Entities Endpoints

### 1. Companies (`/companies`)
- **GET** `/companies` - List all companies
- **GET** `/companies/{id}` - Get company by ID
- **POST** `/companies` - Create new company
- **PUT** `/companies/{id}` - Update company
- **DELETE** `/companies/{id}` - Delete company

### 2. Buildings (`/buildings`)
- **GET** `/buildings` - List all buildings
- **GET** `/buildings/{id}` - Get building by ID
- **POST** `/buildings` - Create new building
- **PUT** `/buildings/{id}` - Update building
- **DELETE** `/buildings/{id}` - Delete building

### 3. Streets (`/streets`)
- **GET** `/streets` - List all streets
- **GET** `/streets/{id}` - Get street by ID
- **POST** `/streets` - Create new street
- **PUT** `/streets/{id}` - Update street
- **DELETE** `/streets/{id}` - Delete street

### 4. Coordinates (`/coordinates`)
- **GET** `/coordinates` - List all coordinates
- **GET** `/coordinates/{id}` - Get coordinate by ID
- **POST** `/coordinates` - Create new coordinate
- **PUT** `/coordinates/{id}` - Update coordinate
- **DELETE** `/coordinates/{id}` - Delete coordinate

## Relationship Endpoints

### Building-Street Relationships
- **GET** `/buildings/{id}/streets` - Get all street connections for a building
- **POST** `/buildings/{id}/streets` - Add street connection to a building

### Building-Company Relationships
- **GET** `/buildings/{id}/companies` - Get all company connections for a building
- **POST** `/buildings/{id}/companies` - Add company connection to a building

### Company-Building Relationships
- **GET** `/companies/{id}/buildings` - Get all building connections for a company
- **POST** `/companies/{id}/buildings` - Add building connection to a company

### Street-Building Relationships
- **GET** `/streets/{id}/buildings` - Get all building connections for a street
- **POST** `/streets/{id}/buildings` - Add building connection to a street

## Sample Data Available

The database is initialized with sample data including:
- 14 buildings (tecnopuc, 99A, 95A, 97, 95C, 96A, 96B/C/D/F, 96J, 96E/H/I/G, 91B, 94, 93, 92A, 91A)
- 18 coordinates (associated with buildings and streets)
- 2 streets with coordinate connections

## Testing the Endpoints

To test the endpoints:

1. Start the application: `./mvnw spring-boot:run`
2. Access Swagger UI at: `http://localhost:8080/docs`
3. Use the Swagger interface to test all endpoints
4. Access H2 Console at: `http://localhost:8080/h2-console` for database inspection

### Example API Calls

#### Get all buildings:
```bash
GET http://localhost:8080/buildings
```

#### Get all coordinates:
```bash
GET http://localhost:8080/coordinates
```

#### Get all streets:
```bash
GET http://localhost:8080/streets
```

#### Create a new company:
```bash
POST http://localhost:8080/companies
Content-Type: application/json

{
    "name": "Test Company",
    "category": "Technology",
    "description": "A test company",
    "building": "tecnopuc"
}
```

#### Get building-street relationships:
```bash
GET http://localhost:8080/buildings/1/streets
```

All endpoints follow RESTful conventions and include proper HTTP status codes:
- 200 OK for successful GET operations
- 201 Created for successful POST operations
- 204 No Content for successful DELETE operations
- 404 Not Found when resource doesn't exist

The API includes proper CORS configuration and Swagger documentation for easy testing and integration.