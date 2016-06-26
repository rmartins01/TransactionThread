Questões:

1 - A execução da classe Main resultará na geração de alugmas linhas na tela onde fica
claro que o método doInTransaction da classe RunnableThread na verdade não é 
executado dentro de uma transação apesar de estar anotado com @Transactional.

O desafio aqui é fazer alterações para que o problema seja resolvido.

É desejável também que sejam adicionados comentários que expliquem como a solução
foi implementada.

2 - Ao executar o projeto como aplicação web (por exemplo, usando "mvn tomcat7:run"),
e abrir a página index.html, ao se fazer o upload de um arquivo é retornada uma
mensagem com o número de bytes no arquivo. No arquivo index.html tem um comentário
sobre o header contentType. O desafio é explicar porque esse header é necessário
e como é que removendo esse header o servlet passa a retornar que o arquivo tem
zero bytes. Note que o problema não acontece se o filtro for removido.
