create database voluntariosapp;
use voluntariosapp;

create table tags (
	id bigint not null primary key auto_increment,
	nome varchar(45) not null
);

create table pessoa(
	id bigint not null primary key auto_increment,
	nome varchar(50) not null,
	sobrenome varchar(50) not null,
	telefone varchar(50) not null,
	cpf char(11) not null,
	ativo bit(1) not null default b'1',
	caminho_imagem varchar(100),
	login varchar(100) unique key not null,
	senha varchar(50) not null
);

create table tag_interesse_pessoa(
	id bigint not null primary key auto_increment,
	id_pessoa bigint not null,
	id_tag bigint not null,
	foreign key (id_pessoa) references pessoa(id),
	foreign key (id_tag) references tags(id)
);

create table organizacao(
	id bigint not null primary key auto_increment,
	nome varchar(250) not null,
	id_supervisor bigint not null,
	descricao varchar(500),
	caminho_imagem varchar(100),
	ativo bit(1) not null default b'1',
	cnpj varchar(14),
	foreign key (id_supervisor) references pessoa(id)
);

create table evento(
	id bigint not null primary key auto_increment,
	nome varchar(250) not null,
	data datetime not null,
	id_organizadora bigint not null,
	foreign key (id_organizadora) references organizacao(id)
);

create table tags_evento(
	id bigint not null primary key auto_increment,
	id_evento bigint not null,
	id_tag bigint not null,
	foreign key (id_evento) references evento(id),
	foreign key (id_tag) references tags(id)
);

create table pessoas_evento(
	id bigint not null primary key auto_increment,
	id_pessoa bigint not null,
	id_evento bigint not null,
	presenca  bit(1) not null default b'1',
	foreign key (id_evento) references evento(id),
	foreign key (id_pessoa) references pessoa(id)
);

create table patrocinador(
	id bigint not null primary key auto_increment,
	nome varchar(100) not null,
	id_representante bigint not null,
	caminho_imagem varchar(100),
	foreign key (id_representante) references pessoa(id)
);

create table patrocinadores_evento(
	id bigint not null primary key auto_increment,
	id_patrocinador bigint not null,
	id_evento bigint not null,
	foreign key (id_patrocinador) references patrocinador(id),
	foreign key (id_evento) references evento(id)
);

create table contribuicao(
	id bigint not null primary key auto_increment,
	valor decimal(9,2) not null,
	data datetime not null,
	id_organizacao bigint not null,
	id_pessoa bigint not null,
	foreign key(id_organizacao) references organizacao(id),
	foreign key(id_pessoa) references pessoa(id)
);