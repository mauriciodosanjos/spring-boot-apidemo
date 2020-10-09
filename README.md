# API DEMO

Uma API Spring Boot para estudos ** [Referencia](https://medium.com/@mari_azevedo/construindo-uma-api-restful-com-java-e-spring-framework-46b74371d107) **


Url base: http://localhost:8080/financial/v1/transactions


## Como a API deve funcionar?

*GET/transactions:* retorna todas as transações criadas.

*POST/transaction*: cria uma transação. 

*PUT/transaction/{id}*: atualiza uma transação.

*DELETE/transactions*: remove todas as transações.

*GET/statistics*: retorna estatísticas básicas sobre as transações criadas.

 
 
**Body:**

```
{
  "id": 1,
  "nsu": "220788",
  "autorizationNumber": "010203",
  "amount": "22.88",
  "transactionDate": "2019-09-11T09:59:51.312Z",
  "type": "CARD"
}
```


**Where:**

*id*: número único da transação;

*nsu*: número de identificação de uma transação de cartão de crédito. Pode ser nula se a transação for paga em dinheiro.

*autorizationNumber*: é código único das transações online.

*amount*: valor da transação; deve ser uma String de tamanho arbitrário que pode ser parseada como um BigDecimal;

*transactionDate*: data da transação no formato ISO 8601 YYYY-MM-DDThh:mm:ss.sssZ no timezone local.

*type*: se a transação é em cartão (CARD) ou em dinheiro (MONEY).


Deve retornar com body vazio com um dos códigos a seguir:

* 201: em caso de sucesso.
* 400: caso o JSON seja inválido.
* 422: se qualquer um dos campos não for parseável ou a data da transação está no futuro.
