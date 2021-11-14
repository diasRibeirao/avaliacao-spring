SPRING  - 41SCJ

Projeto elaborado para a avaliação final de SPRING ministrada pelo Prof. FABIO TADASHI MIYASATO

# Grupo 
**Emerson - 341060  
Marco Antonio - 341785**  



# Opção Escolhida
a. Java com Spring 
b. banco de dados h2 
c. JUnit 5 para rodar os testes
d. Heroku pra deploy 
# Stack Tecnológica

Para esse projeto escolhemos utilizar java 11 por ser a versão que temos. mais facilidade e ja trabalharmos utilizando ela.  
Para o spring foi escolhida a versão 2.5.6 devido a familiaridade dos integrantes do grupo com os recursos providos e ser a ultima versao. 
Foi utilizado h2 durante o desenvolvimento, evitando assim o trabalho de instalar e subir um banco em tempo de desenvolvimento e para facilitar uma possível subida em docker. Como essa build foi gerada para fins de avaliação foi escolhido deixar com o h2, numa build para produção seria utilizado o MySQL. 
O design pattern escolhido para a transferencia de dados foi o DTO para tentar minimizar ao maximo o trafego de rede e a comunicacao do cliente com o banco de dados.
Utilizamos tambem os recursos padrao da JPA e suas implementacoes providas pelo spring. 
Utilizamos em trechos do código o Lombok para diminuir a quantidade de codigo boiler plate na aplicacao. 
Utilizamos nesse projeto o swagger para a criaçao da documentacao, o resultado pode ser conferido através da URL
# Deploy
Para esse projeto utilizamos o heroku como ferramenta para deploy, para acessar abrir a URL abaixo