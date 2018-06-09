# Assignment4

getProductByID(@PathParam("id") int id)
  Method Type: @GET
  Request URL: http://localhost:8080/Assignment_4/api/products/{id}
  Sample Response: 
    {
      "description1": "100% Polyester",
      "description2": "Imported",
      "description3": "Hand Wash",
      "description4": "Multi-Colored Propeller Hat",
      "description5": "I forgot what it was",
      "img_url1": "images\/propeller.png",
      "img_url2": "images\/propeller2.png",
      "img_url3": "images\/propeller3.jpg",
      "name": "Propeller Hat",
      "price": 12,
      "product_id": 1
    }

getAllProducts()
  Method Type: @GET
  Request URL: http://localhost:8080/Assignment_4/api/products/getAll
  Sample Response: 
    [
      {
        "color": "Multi-Colored",
        "description": "Propeller Hat",
        "image_url": "images\/propeller.png",
        "material": "Polyester",
        "price": 12,
        "product_id": 1
      },
      {
        "color": "Black",
        "description": "Pirate Hat",
        "image_url": "images\/pirate.png",
        "material": "Cloth",
        "price": 25,
        "product_id": 2
      }
    ]
    
getAllProducts(Cart cart)
  Method Type: @POST
  Request URL: http://localhost:8080/Assignment_4/api/products/post
  Sample Response: InboundJaxrsResponse{context=ClientResponse{method=POST, uri=http://localhost:8080/Assignment_4/api/products/post, status=204, reason=No Content}}
  Sample Request: 
    {
      "pid": 1
      "description": "Propeller Hat"
      "price": 12
      "quantity": 1
    }
