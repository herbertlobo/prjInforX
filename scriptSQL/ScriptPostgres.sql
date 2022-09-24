-- dbinforx;

create table if not exists tbusuarios(
	iduser serial  primary key,
	usuario varchar(50) not null,
	fone varchar(15) not null,
	login varchar(15) not null unique,
	senha varchar(32) not null,
	perfil varchar(20) not null
);

INSERT INTO public.tbusuarios 
(usuario, fone, login, senha, perfil)
VALUES('Administrador', '(00) 00000-0000', 'admin', 'admin', 'Admin'),
('Herbert A Lobo', '(82) 987659687', 'herbert', 'lobo', 'Admin');

SELECT iduser, usuario, fone, login, senha, perfil
FROM public.tbusuarios;


UPDATE public.tbusuarios
SET perfil='User'
WHERE iduser = 2;


-- criando tabela cliente

create table if not exists tbclientes(
	idcli serial primary key,
	nomecli varchar(50) not null,
	endcli varchar(100),
	fonecli varchar(15) not null,
	emailcli varchar(50)
);

-- construção da tabela OS

create table if not exists tbos(
	os serial primary key,
	data_os timestamp default current_timestamp,
	equipamneto varchar(100) not null,
	defeito varchar(100) not null,
	servico varchar(150),
	tecnico varchar(50),
	valor numeric(15,2),
	idcli int not null,
	foreign key (idcli) references public.tbclientes(idcli)
);

SELECT os, data_os, equipamneto, defeito, servico, tecnico, valor, idcli
FROM public.tbos;

