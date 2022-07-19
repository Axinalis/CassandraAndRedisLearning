# Library project

A simple project for learning Cassandra and Redis databases.
It represents library where each user has list of read books.

---
### Starting containers

1. Start up Cassandra node using docker-compose
2. Add tables for clients and books
3. Start up container with Redis
4. Start up application when Cassandra node is working

**Alternative way - start script in bin folder**

---
### Endpoints

/library/user - CRUD operations over users

/library/book - CRUD operations over books

/library/values - Access to different values (for example, Wi-fi password)