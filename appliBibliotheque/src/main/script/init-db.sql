--org.hibernate.tool.schema.spi.CommandAcceptanceException: Error executing DDL "ALTER TABLE emprunt ADD CONSTRAINT FK_emprunt_livre FOREIGN KEY (id_livre) REFERENCES livre" via JDBC Statement
	


/*
drop sequence  hibernate_sequence;
drop table  domaine cascade constraints;
drop table emprunt cascade constraints;
drop table  incident cascade constraints;
drop table  livre cascade constraints;
drop table personne cascade constraints;
*/


CREATE sequence hibernate_sequence start with 1 increment by  1;

CREATE TABLE Domaine (
	id NUMBER(19) generated as identity, 
	description VARCHAR(255 ), 
	nom VARCHAR(255 ), 
	PRIMARY KEY (id)
);

CREATE TABLE Incident (
	id NUMBER(19) generated as identity, 
	motif VARCHAR(255 ), 
	PRIMARY KEY (id)
);

CREATE TABLE Personne (
	id NUMBER(19) not null,
	role VARCHAR(31 ) not null, 
	codepostal VARCHAR(255 ), 
	email VARCHAR(255 ), 
	nom VARCHAR(255 ), 
	numallee VARCHAR(255 ), 
	numtel VARCHAR(255 ), 
	pays VARCHAR(255 ), 
	prenom VARCHAR(255 ), 
	typevoie VARCHAR(255 ), 
	ville VARCHAR(255 ), 
	password VARCHAR(255 ), 
	username VARCHAR(255 ), 
	PRIMARY KEY (id)
);

CREATE TABLE Livre (
	id NUMBER(19) not null, 
	auteur VARCHAR(255 ), 
	dispo NUMBER(1,0), 
	etat VARCHAR(255 ), 
	titre VARCHAR(255 ), 
	domaine NUMBER(19), 
	PRIMARY KEY (id),
	CONSTRAINT FK_livre_domaine FOREIGN KEY (domaine) REFERENCES domaine (id)
);

CREATE TABLE Emprunt (
	id_lecteur NUMBER(19) not null, 
	id_livre NUMBER(19) not null, 
	date_debut date, 
	date_fin date, 
	etat VARCHAR(255 ), 
	incident NUMBER(19), 
	PRIMARY KEY (id_lecteur, id_livre),
	CONSTRAINT FK_emprunt_incident FOREIGN KEY (incident) REFERENCES incident (id),
	CONSTRAINT FK_emprunt_personne FOREIGN KEY (id_lecteur) REFERENCES personne (id),
	CONSTRAINT FK_emprunt_livre FOREIGN KEY (id_livre) REFERENCES livre (id)
);





