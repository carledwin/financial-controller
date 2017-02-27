	--create database controle_acesso;
	/*
	create table users(	username varchar(15)
								,password varchar(40)
								,authority varchar(15)); */
								
	--use controle_acesso;
	
	--show tables;
	
	--desc users;
	--desc empresa;
	
	select *from users;
	
	--insert into users values('carl', '123', 'ROLE_ADMIN');
	--insert into users values('visitante','visitante', 'ROLE_VISITANTE');
	
	
	
	
	
	/* login customizado **/
	
	/* create table empresa(codigo int auto_increment
									,descricao varchar(45)
									,regra varchar(45)
									,primary key(codigo)
									);
	*/
	
		--drop table usuario;
	
	
	/*
	create table usuario(codigo int auto_increment
								,login varchar(15)
								,senha varchar(40)
								,empresa_codigo int 
								,primary key(codigo)
								,foreign key( empresa_codigo) references empresa(codigo)
								); 
	*/							
								
