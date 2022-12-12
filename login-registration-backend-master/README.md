# API RESTful

Aplicação para cadastrar e fazer login de usuários.




- [x] Spring Boot
- [x] Spring Security
- [x] Java Mail
- [x] Verificação de email com expiração
- [x] Spring Boot

## Diagrama
![Screenshot 2021-01-13 at 23 38 08](https://user-images.githubusercontent.com/40702606/104789980-15581a00-578e-11eb-998d-30f2e6a9f461.png)

## Verificação de email com expiração
![Email](https://user-images.githubusercontent.com/111810585/206878406-6b727624-28a5-4a2c-aa00-1a22b53e9223.png)

## Instruções para funcionamento do Maildev

1 - Instale o maildev pelo prompt de comando(CMD)<br>
npm install -g maildev

2 - Digite maildev no prompt de comando. 

3 - O maildev irá rodar em http://localhost:1080

## Exemplo de requisições
### Postman
![Requisição](https://user-images.githubusercontent.com/111810585/206878651-c49570e7-dabe-48e1-a062-7fd4bb31c41e.png)

### CURL
```
curl --location --request POST 'localhost:8080/entrebecos/cadastro' \
--header 'Content-Type: application/json' \
--data-raw '{
    "firstName": "Lucas",
    "lastName": "Costa",
    "email": "lucas@gmail.com",
    "password": "lucas123",
    "telefone": 11943442322,
    "endereco": "Rua da Areia, 120",
    "cep": "03240-060",
    "data_nascimento": "2000-10-13"
}'
```
