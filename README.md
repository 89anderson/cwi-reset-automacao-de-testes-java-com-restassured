# cwi-reset-automacao-de-testes-java-com-restassured
Cenario de testes automatizados com Java e Rest Assured

O desafio deste módulo é composto de duas partes:
Reportar inconsistências e melhorias mapeadas nos endpoints/serviços. Pode ser descrito em arquivo de qualquer extensão (PDF, TXT, PPT, ....);
Desenvolver mais cenários no projeto de automação afim de garantir uma maior cobertura de testes em cada endpoint/serviço.Abaixo os cenários e suas respectivas suítes. Obs.: Fique à vontade em adicionar outros cenários!

Cenários cobertos pelo teste e respectivos comentários>

📈Suite Healthcheck:

/GET
Verificar se API está online 
* Embora a documentação peça um http status 200, a API está retornando um 201.

✅Suite SchemaTest:

/POST
Criar uma nova reserva
/GET
Garantir o schema do retorno da lista de reservas
Garantir o schema do retorno de uma reserva específica

☠️Suite AcceptanceCriticalTest:

/DELETE
Excluir um reserva com sucesso 
* Ao deletar uma reserva, a documentação espera um httpstatus 200, porém está retornando um 201 CREATED

/GET 

* Todas as chamadas get booking podem ser efetuadas sem nenhuma autenticação. Isso é altamente perigoso pois deixa todos os dados de reservas desprotegidos.

Listar IDs das reservas
Listar uma reserva específica
Listar IDs de reservas utilizando o filtro firstname
Listar IDs de reservas utilizando o filtro lastname
Listar IDs de reservas utilizando o filtro checkin
Listar IDs de reservas utilizando o filtro checkout
Listar IDs de reservas utilizando o filtro checkout and checkout
Listar IDs de reservas utilizando o filtro name, checkin and checkout date

/POST
Criar uma nova reserva 

* ao criar uma reserva temos um httpstatus 200 como retorno. Seria mais adequado um 201.

/PUT
Alterar uma reserva usando o token
Alterar uma reserva usando o Basic auth

🧐Suite AcceptanceExceptionTest:

/DELETE
Tentar excluir um reserva que não existe - http status 405

/GET
Fazer consulta de reservas enviando filtro mal formatado

/POST
Fazer criação de reserva com payload inválido
Validar a criação de mais de uma reserva em sequência
Criar uma reserva enviando mais parâmetros no payload

/PUT
Alterar uma reserva que não existe

👩🏻‍✈️Suite SecurityTest:

/PUT
Alterar uma reserva quando o token não for enviado - http status 403
Alterar uma reserva quando o token enviado for inválido - http status 403

/DELETE
Excluir uma reserva sem autorização - http status 403





