INSERT INTO jeu (nom, date_sortie, synopsis, goty, solo, cooperatif, multijoueur)
VALUES ('Dragon\'s Dogma 2', '2024-04-06', 'Jeu... bla bla bla bla', 0, 0, 0, 1), ( 'Baldur\'s Gate 3 ', '2023-08-10' , 'Jeu... bla bla bla bla', 1, 1, 0, 1);

INSERT INTO genre (libelle) 
VALUES ('Action'), ('RPG'), ('Beat\'em All'), ('Aventure'), ('Survival- Horror'), ('Simulation'), ('Infiltration'), ('FPS'), ('TPS'), 
('MMORPG'), ('Survie'), ('Plateformes');
 
INSERT INTO studio_dev (nom)
VALUES ('Capcom'), ('Larian Studios');

INSERT INTO editeur (nom) 
VALUES ('Capcom'), ('Bandai Namco'), ('Larian Studios'), ('Take-Two Interactive'), ('CD Projekt'), ('Studio Wildcard')
  , ('Nintendo'), ('The Pokémon Company'), ('Blizzard Entertainment');
 
INSERT INTO plateforme (nom)
VALUES ('PC'), ('PS5');
 
INSERT INTO jeu_genre (id_jeu, id_genre)
VALUES (1, 2), (1, 4), (2, 1), (2, 2), (2, 4);
 
INSERT INTO jeu_studio_dev (id_jeu, id_studio)
VALUES (1, 1), (2, 2);
 
INSERT INTO jeu_editeur (id_jeu, id_editeur)
VALUES (1, 1), (2, 3);

INSERT INTO jeu_plateforme (id_jeu, id_plateforme)
VALUES (1, 1), (1, 2), (2, 1), (2, 2);