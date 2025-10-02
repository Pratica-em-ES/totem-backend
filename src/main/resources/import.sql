-------------------------------------------------------
--
-- Prédios e Coordenadas
--
-------------------------------------------------------
INSERT INTO coordinate (x, y) VALUES (0, 0); -- id 1
INSERT INTO building (name, model_path, coordinate_id) VALUES ('tecnopuc', '/models/tecnopuc.glb', 1);

INSERT INTO coordinate (x, y) VALUES (0, 0); -- id 2
INSERT INTO building (name, model_path, coordinate_id) VALUES ('99 A', '/models/99A.glb', 2);

INSERT INTO coordinate (x, y) VALUES (0, 0); -- id 3
INSERT INTO building (name, model_path, coordinate_id) VALUES ('95 A', '/models/95A.glb', 3);

INSERT INTO coordinate (x, y) VALUES (0, 0); -- id 4
INSERT INTO building (name, model_path, coordinate_id) VALUES ('97', '/models/97.glb', 4);

INSERT INTO coordinate (x, y) VALUES (0, 0); -- id 5
INSERT INTO building (name, model_path, coordinate_id) VALUES ('95 C', '/models/95c.glb', 5);

INSERT INTO coordinate (x, y) VALUES (0, 0); -- id 6
INSERT INTO building (name, model_path, coordinate_id) VALUES ('96 A', '/models/96A.glb', 6);

INSERT INTO coordinate (x, y) VALUES (0, 0); -- id 7
INSERT INTO building (name, model_path, coordinate_id) VALUES ('96 B/C/D/F', '/models/96BCDF.glb', 7);

INSERT INTO coordinate (x, y) VALUES (0, 0); -- id 8
INSERT INTO building (name, model_path, coordinate_id) VALUES ('96 J', '/models/96j.glb', 8);

INSERT INTO coordinate (x, y) VALUES (0, 0); -- id 9
INSERT INTO building (name, model_path, coordinate_id) VALUES ('96 E/H/I/G', '/models/96.glb', 9);

INSERT INTO coordinate (x, y) VALUES (0, 0); -- id 10
INSERT INTO building (name, model_path, coordinate_id) VALUES ('91 B', '/models/91B.glb', 10);

INSERT INTO coordinate (x, y) VALUES (0, 0); -- id 11
INSERT INTO building (name, model_path, coordinate_id) VALUES ('94', '/models/94.glb', 11);

INSERT INTO coordinate (x, y) VALUES (0, 0); -- id 12
INSERT INTO building (name, model_path, coordinate_id) VALUES ('93', '/models/93.glb', 12);

INSERT INTO coordinate (x, y) VALUES (0, 0); -- id 13
INSERT INTO building (name, model_path, coordinate_id) VALUES ('92 A', '/models/92A.glb', 13);

INSERT INTO coordinate (x, y) VALUES (0, 0); -- id 14
INSERT INTO building (name, model_path, coordinate_id) VALUES ('91 A', '/models/91A.glb', 14);

INSERT INTO coordinate (x, y) VALUES (-13, 28); -- id 15
INSERT INTO coordinate (x, y) VALUES (-13, -26); -- id 16
INSERT INTO street (width, coordinate_a_id, coordinate_b_id) VALUES (3, 15, 16);

INSERT INTO coordinate (x, y) VALUES (31.45, 8.56); -- id 17
INSERT INTO coordinate (x, y) VALUES (19, 18.29); -- id 18
INSERT INTO street (width, coordinate_a_id, coordinate_b_id) VALUES (3, 17, 18);

-------------------------------------------------------
--
-- Empresas
--
-------------------------------------------------------
INSERT INTO company (name, category, description, building) VALUES ('Microsoft', 'Tecnologia', 'Empresa de software e tecnologia', 'TecnoPUC');
INSERT INTO company (name, category, description, building) VALUES ('Google Brasil', 'Tecnologia', 'Empresa de pesquisa e publicidade online', '96 A');
INSERT INTO company (name, category, description, building) VALUES ('PUCRS Incubadora', 'Educação', 'Incubadora de empresas da universidade', '95 A');
INSERT INTO company (name, category, description, building) VALUES ('Dell Technologies', 'Hardware', 'Empresa de equipamentos de informática', '97');
INSERT INTO company (name, category, description, building) VALUES ('SAP Labs', 'Software', 'Laboratório de desenvolvimento de software', '95 C');

-------------------------------------------------------
--
-- Prédios x Empresas
--
-------------------------------------------------------
INSERT INTO building_company (building_id, company_id, floors) VALUES (1, 1, '3º andar');
INSERT INTO building_company (building_id, company_id, floors) VALUES (6, 2, '1º e 2º andar');
INSERT INTO building_company (building_id, company_id, floors) VALUES (3, 3, '4º andar');
INSERT INTO building_company (building_id, company_id, floors) VALUES (4, 4, '2º andar');
INSERT INTO building_company (building_id, company_id, floors) VALUES (5, 5, '1º andar');
INSERT INTO building_company (building_id, company_id, floors) VALUES (6, 1, '5º andar');
INSERT INTO building_company (building_id, company_id, floors) VALUES (1, 3, '6º andar');