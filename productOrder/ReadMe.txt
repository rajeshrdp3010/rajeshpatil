This is product order application.The CRUD operation can be performed on Product and Orders.

There are two users defined and two roles defined USER & ADMIN.
The USER role has access to all GET methods and ADMIN role has access to all POST,PUT,DELETE methods.

The below user has USER and ADMIN roles.
 	userName - “user”
	Password - “user123”

The below user has USER role.
 	userName - “test”
	Password - “test123”

CRUD Operations with Protocol and URL

	GET "/orders/getAll"
	GET "/orders/get/{orderId}"
	POST "/orders/add"
     PUT "/orders/update"
     DELETE "/orders/delete/{orderId}"
     GET "/products/getAll"
     GET "/products/get/{productId}"
     POST "/products/add"
     PUT "/products/update"
     DELETE "/products/delete/{productId}"

