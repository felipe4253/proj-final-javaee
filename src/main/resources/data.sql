-- Empresas
insert into empresa (id, nome, email)
values(10001,'Empresa A LTDA', 'contato@empresaa.com');
insert into empresa (id, nome, email)
values(10002,'Empresa B LTDA', 'contato@empresab.com');
insert into empresa (id, nome, email)
values(10003,'Empresa C LTDA', 'contato@empresac.com');
insert into empresa (id, nome, email)
values(10004,'Empresa D LTDA', 'contato@empresad.com');


-- Investidores
insert into investidor (id, nome, email)
values(10001,'Investidor A', 'contato@investidorA.com');

insert into investidor (id, nome, email)
values(10002,'Investidor B', 'contato@investidorB.com');


-- Acoes
insert into acao (id, empresa_id, investidor_atual_id, valor_inicial, valor_atual)
values (10000, 10001, null, 1000, 1000);

insert into acao (id, empresa_id, investidor_atual_id, valor_inicial, valor_atual)
values (10001, 10001, null, 1000, 1000);

insert into acao (id, empresa_id, investidor_atual_id, valor_inicial, valor_atual)
values (10002, 10001, null, 1000, 1000);

insert into acao (id, empresa_id, investidor_atual_id, valor_inicial, valor_atual)
values (10003, 10001, null, 1000, 1000);