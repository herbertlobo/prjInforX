-- Selecionar banco de dados
use dbinforx;

create table if not exists tbusuarios(
	iduser int primary key auto_increment,
	usuario varchar(50) not null,
	fone varchar(15) not null,
	login varchar(15) not null unique,
	senha varchar(32) not null,
	perfil varchar(20) not null
);

INSERT INTO dbinforx.tbusuarios
(usuario, fone, login, senha, perfil)
VALUES('Administrador', '(00) 00000-0000', 'admin', 'admin', 'admin'),
('Herbert A Lobo', '(82) 987659687', 'herbert', 'lobo', 'admin');

SELECT iduser, usuario, fone, login, senha, perfil
FROM dbinforx.tbusuarios;


UPDATE dbinforx.tbusuarios
SET perfil='vendedor'
WHERE iduser = 2;


-- criando tabela cliente

create table if not exists tbclientes(
	idcli int primary key auto_increment,
	nomecli varchar(50) not null,
	endcli varchar(100),
	fonecli varchar(15) not null,
	emailcli varchar(50)
);

-- construção da tabela OS

create table if not exists tbos(
	os int primary key auto_increment,
	data_os timestamp default current_timestamp,
	equipamneto varchar(100) not null,
	defeito varchar(100) not null,
	servico varchar(150),
	tecnico varchar(50),
	valor numeric(15,2),
	idcli int not null,
	foreign key (idcli) references dbinforx.tbclientes(idcli)
);

describe tbos;
