Quest�es:

1 - A execu��o da classe Main resultar� na gera��o de alugmas linhas na tela onde fica
claro que o m�todo doInTransaction da classe RunnableThread na verdade n�o � 
executado dentro de uma transa��o apesar de estar anotado com @Transactional.

O desafio aqui � fazer altera��es para que o problema seja resolvido.

� desej�vel tamb�m que sejam adicionados coment�rios que expliquem como a solu��o
foi implementada.

2 - Ao executar o projeto como aplica��o web (por exemplo, usando "mvn tomcat7:run"),
e abrir a p�gina index.html, ao se fazer o upload de um arquivo � retornada uma
mensagem com o n�mero de bytes no arquivo. No arquivo index.html tem um coment�rio
sobre o header contentType. O desafio � explicar porque esse header � necess�rio
e como � que removendo esse header o servlet passa a retornar que o arquivo tem
zero bytes. Note que o problema n�o acontece se o filtro for removido.
