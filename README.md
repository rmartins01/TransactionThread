Quest�es:

1 - A execu��o da classe Main resultar� na gera��o de alugmas linhas na tela onde fica
claro que o m�todo doInTransaction da classe RunnableThread na verdade n�o � 
executado dentro de uma transa��o apesar de estar anotado com @Transactional.

O desafio aqui � fazer altera��es para que o problema seja resolvido.

� desej�vel tamb�m que sejam adicionados coment�rios que expliquem como a solu��o
foi implementada.

  Robson's answer 
```  
  That old code expects a transaction to be started when was called doInTransaction
  method but this woundn't happen because Spring uses AOP to start and stop transactions.
  
  It means that if you get an instance of a transactional Spring bean
  from the bean factory, or through dependency injection, what you get
  is in fact a proxy around the bean. This proxy starts a transaction
  before calling the actual method, and commits or rollbacks the
  transaction once the method has completed.
  
  Solution
  - Spring's TaskExecutor witch executes a task represented by a Java Runnable implementation 
  and let Spring to create new Threads
  
  Implementation 
  - Configured applicationContext.xml to use context:component-scan and TaskExecutor
  - Changed main method to use ThreadPoolTaskExecutor - ( TaskExecutor implementation providing 
  the ability to configure ThreadPoolExecutor via bean properties and expose it as a Spring TaskExecutor)
  and to stop services. 

  Now getTransaction is called before RunnableThread.doInTransaction 
	getTransaction in thread taskExecutor-1 - transaction started
	RunnableThread.doInTransaction
	
  A rollback test was used to test the new implementation
	rollback
	Exception in thread "taskExecutor-1" java.lang.RuntimeException: Rollback Test  
```

2 - Ao executar o projeto como aplica��o web (por exemplo, usando "mvn tomcat7:run"),
e abrir a p�gina index.html, ao se fazer o upload de um arquivo � retornada uma
mensagem com o n�mero de bytes no arquivo. No arquivo index.html tem um coment�rio
sobre o header contentType. O desafio � explicar porque esse header � necess�rio
e como � que removendo esse header o servlet passa a retornar que o arquivo tem
zero bytes. Note que o problema n�o acontece se o filtro for removido.

  Resposta:
```  
  O contentType serve como diretiva para prover instru��es ao servidor de qual o tipo de MIME e 
  esquema de encoding para a resposta.

  O contentType no Header � necess�rio nesse caso para que o sevidor possa identificar o tipo de dado. 
  N�o � poss�vel saber o que os bytes representam, tipo de valores e etc. Em suma, � preciso obter mais 
  informa��es sobre os dados que est� sendo baixado a fim	de saber como process�-los.

  Ao se usar o tipo "application/octet-stream", significa que n�o se sabe do que se tratam o real tipo 
  dos dados, ou seja, o que tem nesse arquivo bin�rio que representa um stream de (8-bits).

  Repare que ao n�o se informar um contentType no Head do post um WARNING � gerado: "WARNING: Character 
  decoding failed. Parameter [��ࡱ ..."
  Esse WARNING � gerado ao interceptar a request, na qual informa que o servidor n�o consegue indentificar 
  sozinho o tipo de dado recebido, com isso a mensagem interceptada n�o chega apropriadamente no 
  FileUploadServlet, resultando nos zero bytes.
  Remover o filter faz com que o WARNING n�o ocorra e a mensagem chegue de qualquer forma ao FileUploadServlet.
  A solu��o para esse caso � como muito bem apresentado no exemplo, informando um 
  contentType="application/octet-stream".
```