To compile and run:
```
mvn clean package -DskipTests=true && java -jar target/warehouse-1.0.jar
```

Below is a list of CRUD api for inventory and warehouse. Implemented the following:
1. CRUD api for inventory
2. CRUD api for warehouse
3. Assigning inventory to warehouse

CURLs for the apis:
```
{
	"info": {
		"_postman_id": "bcc7327c-618f-49c2-be7a-14d625298b54",
		"name": "shopify-warehouse",
		"schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json"
	},
	"item": [
		{
			"name": "inventory",
			"item": [
				{
					"name": "get all inventory",
					"request": {
						"method": "GET",
						"header": [],
						"url": {
							"raw": "localhost:8082/inventory",
							"host": [
								"localhost"
							],
							"port": "8082",
							"path": [
								"inventory"
							]
						}
					},
					"response": []
				},
				{
					"name": "update inventory",
					"request": {
						"method": "PUT",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "{\n  \"id\": 1,\n  \"name\": \"book 1\",\n  \"cost\": 5.0,\n  \"description\": \"Book No. 1\"\n}",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": {
							"raw": "localhost:8082/inventory/update",
							"host": [
								"localhost"
							],
							"port": "8082",
							"path": [
								"inventory",
								"update"
							]
						}
					},
					"response": []
				},
				{
					"name": "get inventory",
					"request": {
						"method": "GET",
						"header": [],
						"url": {
							"raw": "localhost:8082/inventory/1",
							"host": [
								"localhost"
							],
							"port": "8082",
							"path": [
								"inventory",
								"1"
							]
						}
					},
					"response": []
				},
				{
					"name": "create inventory",
					"request": {
						"method": "POST",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "{\n  \"name\": \"book 1\",\n  \"cost\": 5.0,\n  \"description\": \"Book No. 1\"\n}",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": {
							"raw": "localhost:8082/inventory/create",
							"host": [
								"localhost"
							],
							"port": "8082",
							"path": [
								"inventory",
								"create"
							]
						}
					},
					"response": []
				},
				{
					"name": "enable inventory",
					"request": {
						"method": "POST",
						"header": [],
						"url": {
							"raw": "localhost:8082/inventory/enable/1",
							"host": [
								"localhost"
							],
							"port": "8082",
							"path": [
								"inventory",
								"enable",
								"1"
							]
						}
					},
					"response": []
				},
				{
					"name": "delete inventory",
					"request": {
						"method": "DELETE",
						"header": [],
						"url": {
							"raw": "localhost:8082/inventory/delete/1",
							"host": [
								"localhost"
							],
							"port": "8082",
							"path": [
								"inventory",
								"delete",
								"1"
							]
						}
					},
					"response": []
				},
				{
					"name": "get inventory at all warehouses",
					"request": {
						"method": "GET",
						"header": [],
						"url": {
							"raw": "localhost:8082/inventory/locations/1",
							"host": [
								"localhost"
							],
							"port": "8082",
							"path": [
								"inventory",
								"locations",
								"1"
							]
						}
					},
					"response": []
				}
			]
		},
		{
			"name": "warehouse",
			"item": [
				{
					"name": "create warehouse",
					"request": {
						"method": "POST",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "{\n  \"name\": \"warehouse 1\",\n  \"location\": \"gallifrey\",\n  \"description\": \"Book No. 1\"\n}",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": {
							"raw": "localhost:8082/warehouse/create",
							"host": [
								"localhost"
							],
							"port": "8082",
							"path": [
								"warehouse",
								"create"
							]
						}
					},
					"response": []
				},
				{
					"name": "get warehouse",
					"request": {
						"method": "GET",
						"header": [],
						"url": {
							"raw": "localhost:8082/warehouse/1",
							"host": [
								"localhost"
							],
							"port": "8082",
							"path": [
								"warehouse",
								"1"
							]
						}
					},
					"response": []
				},
				{
					"name": "addInventoryToWarehouse",
					"request": {
						"method": "POST",
						"header": [],
						"body": {
							"mode": "formdata",
							"formdata": [
								{
									"key": "warehouse_id",
									"value": "1",
									"type": "text"
								},
								{
									"key": "inventory_id",
									"value": "1",
									"type": "text"
								},
								{
									"key": "inventory_count",
									"value": "10",
									"type": "text"
								}
							]
						},
						"url": {
							"raw": "localhost:8082/warehouse/addInventoryToWarehouse",
							"host": [
								"localhost"
							],
							"port": "8082",
							"path": [
								"warehouse",
								"addInventoryToWarehouse"
							]
						}
					},
					"response": []
				},
				{
					"name": "get all inventory at a warehouse",
					"request": {
						"method": "GET",
						"header": [],
						"url": {
							"raw": "localhost:8082/warehouse/1/inventory",
							"host": [
								"localhost"
							],
							"port": "8082",
							"path": [
								"warehouse",
								"1",
								"inventory"
							]
						}
					},
					"response": []
				}
			]
		}
	]
}
```