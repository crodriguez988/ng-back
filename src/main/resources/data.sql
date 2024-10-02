INSERT INTO franchise (nom)
VALUES 
	('The legend of Zelda');

INSERT INTO genre (libelle) 
VALUES 
	('Action'), ('RPG'), ('Beat\'em All'), ('Aventure'), ('Survival- Horror'), ('Simulation'), 
	('Infiltration'), ('FPS'), ('TPS'), ('MMORPG'), ('Survie'), ('Plateformes');

INSERT INTO developpeur (nom)
VALUES 
	('Capcom'), ('Larian Studios'), ('Nintendo');

INSERT INTO editeur (nom) 
VALUES 
	('Capcom'), ('Bandai Namco'), ('Larian Studios'), ('Take-Two Interactive'), ('CD Projekt'), ('Studio Wildcard'),
    ('Nintendo'), ('The Pokémon Company'), ('Blizzard Entertainment');
  
INSERT INTO plateforme (nom, abreviation) 
VALUES 
	('PC', 'PC'),('Sony PlayStation 5', 'PS5'), ('Xbox Series X/S', 'Xbox Series X/S'), ('Nintendo Switch', 'Switch'), ('Sony PlayStation 4', 'PS4'),
	('Microsoft Xbox One', 'Xbox One'), ('Nintendo Wii U', 'Wii U'), ('Nintendo 3DS', '3DS'), ('Sony PlayStation Vita', 'PS Vita'),
	('Nintendo Wii', 'Wii'), ('Sony PlayStation 3', 'PS3'), ('Nintendo DS Lite', 'DS Lite'), ('Nintendo DSi', 'DSi'),
	('Nintendo 3DS XL', '3DS XL'), ('Nintendo 2DS', '2DS'), ('Microsoft Xbox 360', 'Xbox 360'), ('Sony PlayStation Portable', 'PSP'),
	('Nintendo DS', 'DS'), ('Nintendo GameCube', 'GameCube'), ('Sony PlayStation 2', 'PS2'), ('Nintendo Game Boy Advance', 'GBA'),
	('Sega Dreamcast', 'Dreamcast'), ('Nintendo 64', 'N64'), ('Sony PlayStation', 'PS1'), ('Sega Saturn', 'Saturn'),
	('Atari Jaguar', 'Jaguar'), ('Sega CD', 'Sega CD'), ('Super Nintendo Entertainment System', 'SNES'), ('Sega Genesis', 'Genesis'),
	('Nintendo Game Boy', 'Game Boy'), ('Sega Game Gear', 'Game Gear'), ('NEC TurboGrafx-16', 'TG-16'), ('Atari 7800', 'Atari 7800'),
	('Nintendo Entertainment System', 'NES'), ('Atari 5200', 'Atari 5200'), ('ColecoVision', 'ColecoVision'), ('Intellivision', 'Intellivision'),
	('Magnavox Odyssey', 'Odyssey'); 

INSERT INTO jeu (nom, date_sortie, synopsis, goty, solo, cooperatif, multijoueur, id_franchise)
VALUES 
	('Dragon''s Dogma 2', '2024-04-06', 'Jeu... bla bla bla bla', 0, 0, 0, 1, null),
	('Baldur''s Gate 3', '2023-08-10', 'Jeu... bla bla bla bla', 1, 1, 0, 1, null),
	('The Legend of Zelda: Tears of the Kingdom', '2023-05-12', 
	'The Legend of Zelda: Tears of the Kingdom suit Link dans une nouvelle aventure épique à travers le royaume d''Hyrule, désormais élargi aux cieux.', 0, 1, 0, 0, 1),
	('The Legend of Zelda: Breath of the Wild', '2017-03-03', 'The Legend of Zelda: ....', 0, 0, 0, 0, 1);
 
INSERT INTO jeu_genre (id_jeu, id_genre)
VALUES 
	(1, 2), (1, 4), (2, 1), (2, 2), (2, 4);
 
INSERT INTO jeu_editeur (id_jeu, id_editeur)
VALUES 
	(1, 1), (2, 3);

INSERT INTO jeu_plateforme (id_jeu, id_plateforme)
VALUES 
	(1, 1), (1, 2), (2, 1), (2, 2);


INSERT INTO jeu_developpeur (id_jeu, id_developpeur)
VALUES 
	(1, 1), (2, 2), (3, 3), (4, 3);
