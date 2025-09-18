-- Coordinates + Predios
INSERT INTO Coordenada (coordenada_x, coordenada_y) VALUES (0, 15); -- id=1
INSERT INTO Predio (nome, id_coordenada, model_path) VALUES ('tecnopuc', 1, '/models/tecnopuc.glb');

INSERT INTO Coordenada (coordenada_x, coordenada_y) VALUES (0, 0); -- id=2
INSERT INTO Predio (nome, id_coordenada, model_path) VALUES ('99 A', 2, '/models/99A.glb');

INSERT INTO Coordenada (coordenada_x, coordenada_y) VALUES (0, 0); -- id=3
INSERT INTO Predio (nome, id_coordenada, model_path) VALUES ('95 A', 3, '/models/95A.glb');

INSERT INTO Coordenada (coordenada_x, coordenada_y) VALUES (0, 0); -- id=4
INSERT INTO Predio (nome, id_coordenada, model_path) VALUES ('97', 4, '/models/97.glb');

INSERT INTO Coordenada (coordenada_x, coordenada_y) VALUES (0, 0); -- id=5
INSERT INTO Predio (nome, id_coordenada, model_path) VALUES ('95 C', 5, '/models/95c.glb');

INSERT INTO Coordenada (coordenada_x, coordenada_y) VALUES (0, 0); -- id=6
INSERT INTO Predio (nome, id_coordenada, model_path) VALUES ('96 A', 6, '/models/96A.glb');

INSERT INTO Coordenada (coordenada_x, coordenada_y) VALUES (0, 0); -- id=7
INSERT INTO Predio (nome, id_coordenada, model_path) VALUES ('96 B/C/D/F', 7, '/models/96BCDF.glb');

INSERT INTO Coordenada (coordenada_x, coordenada_y) VALUES (0, 0); -- id=8
INSERT INTO Predio (nome, id_coordenada, model_path) VALUES ('96 J', 8, '/models/96j.glb');

INSERT INTO Coordenada (coordenada_x, coordenada_y) VALUES (0, 0); -- id=9
INSERT INTO Predio (nome, id_coordenada, model_path) VALUES ('96 E/H/I/G', 9, '/models/96.glb');

INSERT INTO Coordenada (coordenada_x, coordenada_y) VALUES (0, 0); -- id=10
INSERT INTO Predio (nome, id_coordenada, model_path) VALUES ('91 B', 10, '/models/91B.glb');

INSERT INTO Coordenada (coordenada_x, coordenada_y) VALUES (0, 0); -- id=11
INSERT INTO Predio (nome, id_coordenada, model_path) VALUES ('94', 11, '/models/94.glb');

INSERT INTO Coordenada (coordenada_x, coordenada_y) VALUES (0, 0); -- id=12
INSERT INTO Predio (nome, id_coordenada, model_path) VALUES ('93', 12, '/models/93.glb');

INSERT INTO Coordenada (coordenada_x, coordenada_y) VALUES (0, 0); -- id=13
INSERT INTO Predio (nome, id_coordenada, model_path) VALUES ('92 A', 13, '/models/92A.glb');

INSERT INTO Coordenada (coordenada_x, coordenada_y) VALUES (0, 0); -- id=14
INSERT INTO Predio (nome, id_coordenada, model_path) VALUES ('91 A', 14, '/models/91A.glb');


-- Inserts for ruas (need to be linked to predios later)

-- Rua 1 (norte-sul longo)
INSERT INTO Coordenada (coordenada_x, coordenada_y) VALUES (-13, 28);  -- id=15
INSERT INTO Coordenada (coordenada_x, coordenada_y) VALUES (-13, -26); -- id=16
INSERT INTO Rua (largura, id_coordenada_a, id_coordenada_b) VALUES (3, 15, 16);

-- Rua 2 (diagonal curta)
INSERT INTO Coordenada (coordenada_x, coordenada_y) VALUES (31.45, 8.56); -- id=17
INSERT INTO Coordenada (coordenada_x, coordenada_y) VALUES (19, 18.29);   -- id=18
INSERT INTO Rua (largura, id_coordenada_a, id_coordenada_b) VALUES (3, 17, 18);

-- Rua 3 (diagonal longa)
INSERT INTO Coordenada (coordenada_x, coordenada_y) VALUES (30, 29);   -- id=19
INSERT INTO Coordenada (coordenada_x, coordenada_y) VALUES (13.5, 12.9); -- id=20
INSERT INTO Rua (largura, id_coordenada_a, id_coordenada_b) VALUES (3, 19, 20);

-- Rua 4 (leste-oeste z≈13)
INSERT INTO Coordenada (coordenada_x, coordenada_y) VALUES (13, 13);   -- id=21
INSERT INTO Coordenada (coordenada_x, coordenada_y) VALUES (-13, 13);  -- id=22
INSERT INTO Rua (largura, id_coordenada_a, id_coordenada_b) VALUES (3, 21, 22);

-- Rua 5 (leste-oeste z≈-22)
INSERT INTO Coordenada (coordenada_x, coordenada_y) VALUES (-13, -22); -- id=23
INSERT INTO Coordenada (coordenada_x, coordenada_y) VALUES (17.73, -22); -- id=24
INSERT INTO Rua (largura, id_coordenada_a, id_coordenada_b) VALUES (3, 23, 24);
