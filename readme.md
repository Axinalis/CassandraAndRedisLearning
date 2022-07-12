# Library project

A simple project for learning Cassandra and Redis databases.
It represents library where each user has list of read books.

---
### Starting containers

1. Start up Cassandra node using docker-compose (or using script from bin folder and skip second step)
2. Add tables for clients and books
3. Start up application when node is working

---
### Endpoints

/library/user - CRUD operations over users

/library/book - CRUD operations over books
