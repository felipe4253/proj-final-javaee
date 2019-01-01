A aplicação é um sring-boot web convencional
Existem exemplos de chamada dos metodos rest disponíveis em /src/main/resources/postman-test-collection

Pilha de tecnologia:
 - Java (Spring web, JPA)
 - Banco H2 (em memória)

Metodos da API:
- Buscar acoes disponiveis
		(por empresa) http://localhost:8080/v1/empresa/10001/acao [GET]
		(todas disponíveis) http://localhost:8080/v1/acao [GET]
		
- Emitir acoes para empresa
		[POST] http://localhost:8080/v1/acao
    
- Efetuar operações (compra e venda) nas acoes
	 [PUT] http://localhost:8080/v1/acao
   
	 Para verificar os dados no banco: http://localhost:8080/h2-console
