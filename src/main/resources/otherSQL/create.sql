CREATE TABLE developpeur (
id_developpeur INT NOT NULL AUTO_INCREMENT, 
nom VARCHAR(200) NOT NULL,
PRIMARY KEY (id_developpeur));

CREATE TABLE editeur (
id_editeur INT NOT NULL AUTO_INCREMENT, 
nom VARCHAR (200) NOT NULL,
PRIMARY KEY (id_editeur));

CREATE TABLE genre (
id_genre INT NOT NULL AUTO_INCREMENT, 
libelle VARCHAR (200), 
PRIMARY KEY (id_genre));

CREATE TABLE plateforme (
id_plateforme INT NOT NULL AUTO_INCREMENT, 
nom VARCHAR (100),
PRIMARY KEY (id_plateforme));

CREATE TABLE jeu (
id_jeu BIGINT AUTO_INCREMENT NOT NULL, 
nom VARCHAR (250) NOT NULL, 
date_sortie DATE,
synopsis LONGTEXT, 
goty BOOLEAN,
solo BOOLEAN,
cooperatif BOOLEAN,
multijoueur BOOLEAN,
PRIMARY KEY (id_jeu));

CREATE TABLE jeu_developpeur (
id_jeu BIGINT NOT NULL,
id_developpeur INT NOT NULL,
PRIMARY KEY (id_jeu, id_developpeur),
FOREIGN KEY (id_jeu) REFERENCES jeu (id_jeu), 
FOREIGN KEY (id_developpeur) REFERENCES developpeur(id_developpeur));

CREATE TABLE jeu_editeur (
id_jeu BIGINT NOT NULL,
id_editeur INT NOT NULL,
PRIMARY KEY (id_jeu, id_editeur),
FOREIGN KEY (id_jeu) REFERENCES jeu (id_jeu),
FOREIGN KEY (id_editeur) REFERENCES editeur (id_editeur));

CREATE TABLE jeu_genre (
id_jeu BIGINT NOT NULL,
id_genre INT NOT NULL,
PRIMARY KEY (id_jeu, id_genre),
FOREIGN KEY (id_jeu) REFERENCES jeu (id_jeu),
FOREIGN KEY (id_genre) REFERENCES genre (id_genre));

CREATE TABLE jeu_plateforme (
id_jeu BIGINT NOT NULL,
id_plateforme INT NOT NULL,
PRIMARY KEY (id_jeu, id_plateforme),
FOREIGN KEY (id_jeu) REFERENCES jeu (id_jeu),
FOREIGN KEY (id_plateforme) REFERENCES plateforme (id_plateforme));