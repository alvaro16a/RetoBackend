Get con paginacion

http://localhost:8080/product/pagina/numeroDeProductos
http://localhost:8080/product/0/4

Crear producto

Post -> http://localhost:8080/product
Body ->
{
    "name": "Monitor HD HUAWEI",
    "inInventory": 15,
    "enabled": true,
    "min": 1,
    "max": 3
}
Eliminar producto por id

DELETE -> http://localhost:8080/product/id

Editar producto

PUT->http://localhost:8080/product
{
    "idProduct": 5,
    "name": "Teclado Mecanico",
    "inInventory": 40,
    "enabled": true,
    "min": 3,
    "max": 5
}
Get por id
GET -> http://localhost:8080/product/id



Guardar compra

POST->http://localhost:8080/purchase
BODY->
{
    "date": "2022-11-25T10:20:00",
    "idType": "Cedula",
    "clientName": "Javier Mena",
    "products": [
        {
            "productId": 10,
            "quantity": 2
        },
        {
            "productId": 4,
            "quantity": 2
        },
        {
            "productId": 11,
            "quantity": 7
        },
        {
            "productId": 8,
            "quantity": 3
        }
    ] 
}

Obrtener todas las Compra

http://localhost:8080/purchase
