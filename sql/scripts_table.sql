--- 1. Insertar Tipos de Residuos (WasteType)
--- Siguiendo los estándares de reciclaje comunes
INSERT INTO waste_types (name) VALUES 
('Cartón'),
('Vidrio'),
('Plástico'),
('Banal');

--- 2. Insertar Residuos específicos (Residue)
--- Basado en objetos típicos de colegio
INSERT INTO residues (name, id_waste_type) VALUES 
('Hoja de papel libreta', 1), -- Cartón/Papel
('Caja de cartón de zumo', 1),
('Botella de agua vacía', 3),  -- Plástico
('Envoltorio de bocadillo (film)', 3),
('Piel de plátano', 4),        -- Banal/Orgánico
('Corazón de manzana', 4),
('Yogur de plástico', 3),
('Lápiz gastado', 4);

--- 3. Insertar Contenedores (Container)
--- Nota: El estado (state) se guarda como String por tu @Enumerated(EnumType.STRING)
--- Supongo estados como 'FULL', 'EMPTY', 'AVAILABLE'
INSERT INTO containers (state, id_waste_type, max_capacity, current_volume) VALUES 
('EMPTY', 1, 1000, 150), -- Contenedor Papel
('EMPTY', 3, 1000, 400), -- Contenedor Plástico
('EMPTY', 4, 1000, 800), -- Contenedor Banal
('FULL', 1, 1000, 1000);     -- Contenedor Papel lleno

--- 4. Insertar Registros de Desperdicio (DiscardedWaste)
--- Asumiendo que existen IDs de Classroom (del 1 al 3)
INSERT INTO discarded_waste (id_classroom, id_residue, id_container, date, is_correct, points_earned) VALUES 
(1, 1, 1, NOW(), true, 10),
(1, 3, 2, NOW(), true, 10),
(2, 5, 3, NOW(), true, 10),
(3, 1, 2, NOW(), false, 0); -- Error: Tiró papel en contenedor de plástico

--- 5. Insertar Notificaciones (Notification)
INSERT INTO notifications (message, date, is_read, id_classroom) VALUES 
('El contenedor de papel está lleno', NOW(), false, 1),
('¡Gran trabajo reciclando esta semana!', NOW(), true, 2);

--- 1. Insertar Usuarios (Profesores y Alumnos)
--- Dejamos id_classroom como NULL momentáneamente para evitar errores de FK
INSERT INTO "users" (role, name, email, password, id_classroom) VALUES 
('TEACHER', 'Marta García', 'marta.profe@colegio.es', 'pass123', NULL),
('TEACHER', 'Jorge Sanz', 'jorge.profe@colegio.es', 'pass123', NULL),
('USER', 'Lucía Pérez', 'lucia.alum@colegio.es', 'pass123', NULL),
('USER', 'Pablo Ruiz', 'pablo.alum@colegio.es', 'pass123', NULL),
('USER', 'Elena Soler', 'elena.alum@colegio.es', 'pass123', NULL);

--- 2. Insertar Aulas (Classroom)
--- Asignamos los IDs de los profesores insertados arriba (1 y 2)
INSERT INTO classrooms (id_teacher, course, points) VALUES 
(1, '1º A', 150),
(2, '2º B', 80);

--- 3. Vincular los usuarios con sus aulas (Update id_classroom)
--- Ahora que las aulas existen, actualizamos los registros
UPDATE "users" SET id_classroom = 1 WHERE id IN (1, 3, 4); -- Marta, Lucía y Pablo a 1º ESO A
UPDATE "users" SET id_classroom = 2 WHERE id IN (2, 5);    -- Jorge y Elena a 2º ESO B

--- 4. Insertar los Desperdicios (Ahora con IDs de Classroom reales)
--- (Este bloque complementa al anterior para que los IDs coincidan)
INSERT INTO discarded_waste (id_classroom, id_residue, id_container, date, is_correct, points_earned) VALUES 
(1, 1, 1, NOW(), true, 10), -- Papel en contenedor de papel
(2, 5, 3, NOW(), true, 10); -- Piel de fruta en banal



----



--- 1. LIMPIEZA (Opcional, por si quieres empezar de cero)
 DELETE FROM discarded_waste;
 DELETE FROM residues;
 DELETE FROM waste_types;

--- 1. Insertar Tipos de Residuos
INSERT INTO waste_types (name) VALUES 
('Cartón'), 
('Vidrio'),  
('Plástico'),
('Banal');

--- 2. Insertar Residuos con la columna SIZE
--- He puesto valores de ejemplo (pueden representar gramos o volumen)
INSERT INTO residues (name, size, id_waste_type) VALUES 
('Hoja de papel libreta', 5, 1), 
('Caja de zumo', 20, 1),
('Botella de agua', 15, 3),  
('Envoltorio de plástico', 2, 3),
('Piel de plátano', 50, 4),        
('Corazón de manzana', 40, 4),
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







INSERT INTO waste_types (name) VALUES 
('Cartón'), 
('Vidrio'),  
('Plástico'),
('Banal');

--- 2. Insertar Residuos con la columna SIZE
--- He puesto valores de ejemplo (pueden representar gramos o volumen)
INSERT INTO residues (name, size, id_waste_type) VALUES 
('Hoja de papel libreta', 5, 1), 
('Caja de zumo', 20, 1),
('Botella de agua', 15, 3),  
('Envoltorio de plástico', 2, 3),
('Piel de plátano', 50, 4),        
('Corazón de manzana', 40, 4),
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
('EMPTY', 1, 1000, 150), -- Contenedor Papel
('EMPTY', 3, 1000, 400), -- Contenedor Plástico
('EMPTY', 4, 1000, 800), -- Contenedor Banal
('FULL', 1, 1000, 1000);     -- Contenedor Papel lleno

INSERT INTO discarded_waste (id_classroom, id_residue, id_container, date, is_correct, points_earned) VALUES 
(1, 1, 1, NOW(), true, 10),
(1, 3, 2, NOW(), true, 10),
(2, 5, 3, NOW(), true, 10),
(2, 1, 2, NOW(), false, 0);