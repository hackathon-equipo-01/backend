


INSERT INTO waste_types (name) VALUES 
('Carton'), 
('Organico'),  
('Plastico'),
('Banal');

--- 2. Insertar Residuos con la columna SIZE
--- He puesto valores de ejemplo (pueden representar gramos o volumen)
INSERT INTO residues (name, size, id_waste_type) VALUES 
('Hoja de papel libreta', 5, 1), 
('Brick de zumo', 20, 3),
('Botella de agua', 15, 3),  
('Envoltorio de plástico', 2, 3),
('Piel de plátano', 50, 2),        
('Corazón de manzana', 40, 2),
('Yogur vacío', 10, 3),
('Lápiz gastado', 5, 4);

--- 3. Insertar Usuarios
INSERT INTO "users" (role, name, email, password, id_classroom) VALUES 
('TEACHER', 'Marta García', 'marta@colegio.es', 'pass123', NULL),
('TEACHER', 'Jorge Sanz', 'jorge@colegio.es', 'pass123', NULL),
('USER', 'Lucía Pérez', 'lucia@colegio.es', 'pass123', NULL);

--- 4. Insertar Aulas
INSERT INTO classrooms (id_teacher, course, points) VALUES 
(1, '1º A', 0),
(2, '2º B', 0);

--- 5. Vincular Usuarios y Aulas
UPDATE "users" SET id_classroom = 1 WHERE id IN (1, 3);
UPDATE "users" SET id_classroom = 2 WHERE id IN (2);

INSERT INTO containers (state, id_waste_type, max_capacity, current_volume) VALUES 
INSERT INTO containers (state, id_waste_type, max_capacity, current_volume) VALUES 
('EMPTY', 1, 1000, 0), -- ID 1: Cartón
('EMPTY', 2, 1000, 0), -- ID 2: Orgánico  <-- ESTE TE FALTABA
('EMPTY', 3, 1000, 0), -- ID 3: Plástico
('EMPTY', 4, 1000, 0); -- ID 4: Banal

INSERT INTO discarded_waste (id_classroom, id_residue, id_container, date, is_correct, points_earned) VALUES 
(1, 1, 1, NOW(), true, 10),
(1, 3, 2, NOW(), true, 10),
(2, 5, 3, NOW(), true, 10),
(2, 1, 2, NOW(), false, 0);