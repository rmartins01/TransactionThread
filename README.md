Questões:

1 - A execução da classe Main resultará na geração de alugmas linhas na tela onde fica
claro que o método doInTransaction da classe RunnableThread na verdade não é 
executado dentro de uma transação apesar de estar anotado com @Transactional.

O desafio aqui é fazer alterações para que o problema seja resolvido.

É desejável também que sejam adicionados comentários que expliquem como a solução
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

2 - Ao executar o projeto como aplicação web (por exemplo, usando "mvn tomcat7:run"),
e abrir a página index.html, ao se fazer o upload de um arquivo é retornada uma
mensagem com o número de bytes no arquivo. No arquivo index.html tem um comentário
sobre o header contentType. O desafio é explicar porque esse header é necessário
e como é que removendo esse header o servlet passa a retornar que o arquivo tem
zero bytes. Note que o problema não acontece se o filtro for removido.

  Resposta:
```  
  O contentType serve como diretiva para prover instruções ao servidor de qual o tipo de MIME e 
  esquema de encoding para a resposta.

  O contentType no Header é necessário nesse caso para que o sevidor possa identificar o tipo de dado. 
  Não é possível saber o que os bytes representam, tipo de valores e etc. Em suma, é preciso obter mais 
  informações sobre os dados que está sendo baixado a fim	de saber como processá-los.

  Ao se usar o tipo "application/octet-stream", significa que não se sabe do que se tratam o real tipo 
  dos dados, ou seja, o que tem nesse arquivo binário que representa um stream de (8-bits).

  Repare que ao não se informar um contentType no Head do post um WARNING é gerado: "WARNING: Character 
  decoding failed. Parameter [ÐÏà¡± ..."
  Esse WARNING é gerado ao interceptar a request, na qual informa que o servidor não consegue indentificar 
  sozinho o tipo de dado recebido, com isso a mensagem interceptada não chega apropriadamente no 
  FileUploadServlet, resultando nos zero bytes.
  Remover o filter faz com que o WARNING não ocorra e a mensagem chegue de qualquer forma ao FileUploadServlet.
  A solução para esse caso é como muito bem apresentado no exemplo, informando um 
  contentType="application/octet-stream".
```