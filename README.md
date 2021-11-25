# transactions-in-Spring
Demonstration of problems when working with transactions and their configuration

To check the assignment use the postman collection.
1. The Product class demonstrates dirty reading:
using the "addListProductsForDirtyRead" request, we save the products to the database and simultaneously get all the products from another browser.
2. The User class demonstrates the problem of saving data without configuring rollback in a transaction.
Add a user using the "addUser" method -> The "DeleteUser" method throws an exception and is not executed completely, but deletion from the database occurs.
3. The role class demonstrates the transaction setup (rollback and propagation).
 Add a role using the "addRole" method -> By calling the "deleteRole" method, we will get an exception, but there will be no changes in the database.
    The "findRoleByIdafterDeleteCheckCGLIBProxy" method demonstrates the CGLIB Proxy problem because when calling a transactional method from a non-transactional method. The transaction does not work !!!
