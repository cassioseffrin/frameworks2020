Criar o projeto: https://start.spring.io/ 

Adicionar as seguintes dependências:

Spring Data JPA
Spring Web Starter
Mysql Driver
//Lombok
https://github.com/cassioseffrin/frameworks2020

#iniciando uma base de dados no mysql zerada:

#/path/bin/mysql -p -u root mysql> 
create database aulamvc ; 

mysql> 
create user 'cassio'@'%' identified by '123'; 

mysql> 
grant all on aulamvc.* to 'cassio'@'%


@Transactional

//resolve problema de referencia circular
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class)
