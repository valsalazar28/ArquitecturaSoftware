CREATE DATABASE bd_arquitectura_corte1;

\connect  bd_arquitectura_corte1;

CREATE TABLE Tipo_usuario (
                              ID SERIAL PRIMARY KEY,
                              Tipo VARCHAR(15)
);

CREATE TABLE Usuario (
                         ID SERIAL PRIMARY KEY,
                         Nombre VARCHAR(255) UNIQUE,
                         Correo VARCHAR(255) UNIQUE,
                         Contrasena VARCHAR(255),
                         Tipo_usuario_ID INTEGER,
                         CONSTRAINT FK_Tipo_usuario_ID FOREIGN KEY (Tipo_usuario_ID) REFERENCES Tipo_usuario(ID)
);

CREATE TABLE Proyecto (
                          ID SERIAL PRIMARY KEY,
                          Nombre VARCHAR(30),
                          Descripcion VARCHAR(2000),
                          FechaInicio DATE,
                          Gerente_ID INTEGER,
                          CONSTRAINT FK_Gerente_ID FOREIGN KEY (Gerente_ID) REFERENCES Usuario(ID)
);

CREATE TABLE Usuario_proyecto (
                                  ID SERIAL PRIMARY KEY,
                                  FechaInicio DATE,
                                  Usuario_ID INTEGER,
                                  Proyecto_ID INTEGER,
                                  CONSTRAINT FK_Usuario_proyecto_Usuario_ID FOREIGN KEY (Usuario_ID) REFERENCES Usuario(ID),
                                  CONSTRAINT FK_Usuario_proyecto_Proyecto_ID FOREIGN KEY (Proyecto_ID) REFERENCES Proyecto(ID)
);

CREATE TABLE Estado (
                        ID SERIAL PRIMARY KEY,
                        Estado VARCHAR(25)
);

CREATE TABLE Historia_usuario (
                                  ID SERIAL PRIMARY KEY,
                                  Detalles VARCHAR(2000),
                                  Criterios_aceptacion VARCHAR(2000),
                                  Usuario_historia_ID INTEGER,
                                  Estado_ID INTEGER,
                                  Proyecto_ID INTEGER,
                                  CONSTRAINT FK_Historia_usuario_Usuario_ID FOREIGN KEY (Usuario_historia_ID) REFERENCES Usuario_proyecto(ID),
                                  CONSTRAINT FK_Historia_usuario_Estado_ID FOREIGN KEY (Estado_ID) REFERENCES Estado(ID),
                                  CONSTRAINT FK_Historia_usuario_Proyecto_ID FOREIGN KEY (Proyecto_ID) REFERENCES Proyecto(ID)
);

CREATE TABLE Tarea (
                       ID SERIAL PRIMARY KEY,
                       Descripcion VARCHAR(2000),
                       Usuario_tarea_ID INTEGER,
                       Estado_tarea_ID INTEGER,
                       Historia_usuario_ID INTEGER,
                       CONSTRAINT FK_Tarea_Usuario_tarea_ID FOREIGN KEY (Usuario_tarea_ID) REFERENCES Usuario_proyecto(ID),
                       CONSTRAINT FK_Tarea_Estado_tarea_ID FOREIGN KEY (Estado_tarea_ID) REFERENCES Estado(ID),
                       CONSTRAINT FK_Tarea_Historia_usuario_ID FOREIGN KEY (Historia_usuario_ID) REFERENCES Historia_usuario(ID)
);

CREATE TABLE Cambio_estado (
                               ID SERIAL PRIMARY KEY,
                               Fecha_cambio DATE,
                               Estado_cambio_ID INTEGER,
                               Usuario_ID INTEGER,
                               Status_type VARCHAR(40),
                               CONSTRAINT FK_Cambio_estado_Estado_cambio_ID FOREIGN KEY (Estado_cambio_ID) REFERENCES Estado(ID)
);

INSERT INTO Tipo_usuario (Tipo) VALUES
                                    ('Gerente'),
                                    ('Desarrollador');

INSERT INTO Estado (Estado) VALUES
                                ('Nueva'),
                                ('En desarrollo'),
                                ('Finalizada');

INSERT INTO Usuario (Nombre, Correo, Contrasena, Tipo_usuario_ID)
VALUES
    ('Gerente1', 'gerente1@example.com', 'contrasena1', 1),
    ('Gerente2', 'gerente2@example.com', 'contrasena2', 1),
    ('Desarrollador1', 'desarrollador1@example.com', 'contrasena3', 2),
    ('Desarrollador2', 'desarrollador2@example.com', 'contrasena4', 2),
    ('Desarrollador3', 'desarrollador3@example.com', 'contrasena5', 2),
    ('Desarrollador4', 'desarrollador4@example.com', 'contrasena6', 2),
    ('Desarrollador5', 'desarrollador5@example.com', 'contrasena7', 2),
    ('Desarrollador6', 'desarrollador6@example.com', 'contrasena8', 2),
    ('Desarrollador7', 'desarrollador7@example.com', 'contrasena9', 2),
    ('Desarrollador8', 'desarrollador8@example.com', 'contrasena10', 2);

-- Crear 2 proyectos diferentes
INSERT INTO Proyecto (Nombre, Descripcion, FechaInicio, Gerente_ID)
VALUES
    ('Proyecto_X', 'Descripción del Proyecto_X', CURRENT_DATE, 1),
    ('Proyecto_Y', 'Descripción del Proyecto_Y', CURRENT_DATE, 2);

-- Asociar 4 desarrolladores a cada proyecto
INSERT INTO Usuario_proyecto (FechaInicio, Usuario_ID, Proyecto_ID)
VALUES
    (CURRENT_DATE, 3, 3),
    (CURRENT_DATE, 4, 3),
    (CURRENT_DATE, 5, 3),
    (CURRENT_DATE, 6, 3),
    (CURRENT_DATE, 7, 4),
    (CURRENT_DATE, 8, 4),
    (CURRENT_DATE, 9, 4),
    (CURRENT_DATE, 10, 4);

-- Crear 5 historias de usuario para cada proyecto
INSERT INTO Historia_usuario (Detalles, Criterios_aceptacion, Usuario_historia_ID, Estado_ID, Proyecto_ID)
VALUES
    ('Implementar funcionalidad de notificaciones', 'Criterios HU1', 3, 1, 3),
    ('Diseñar interfaz de usuario responsiva', 'Criterios HU2', 4, 1, 3),
    ('Optimizar rendimiento del sistema', 'Criterios HU3', 5, 1, 3),
    ('Agregar funcionalidad de búsqueda avanzada', 'Criterios HU4', 6, 1, 3),
    ('Corregir errores de seguridad', 'Criterios HU5', 7, 1, 3),
    ('Implementar sistema de comentarios', 'Criterios HU1', 8, 1, 4),
    ('Actualizar diseño de la página principal', 'Criterios HU2', 9, 1, 4),
    ('Agregar soporte para múltiples idiomas', 'Criterios HU3', 10, 1, 4),
    ('Optimizar base de datos', 'Criterios HU4', 3, 1, 4),
    ('Crear panel de administración avanzado', 'Criterios HU5', 4, 1, 4);

-- Crear 3 tareas para cada historia
INSERT INTO Tarea (Descripcion, Usuario_tarea_ID, Estado_tarea_ID, Historia_usuario_ID)
VALUES
    ('Configurar notificaciones por correo', 3, 1, 11),
    ('Diseñar interfaz móvil', 4, 1, 12),
    ('Realizar pruebas de carga', 5, 1, 13),
    ('Implementar motor de búsqueda', 6, 1, 14),
    ('Realizar análisis de seguridad', 7, 1, 15),
    ('Codificar sistema de comentarios', 8, 1, 16),
    ('Actualizar estilos CSS', 9, 1, 17),
    ('Implementar soporte multilingüe', 10, 1, 18),
    ('Optimizar consultas SQL', 3, 1, 19),
    ('Desarrollar módulo de administración', 4, 1, 20);