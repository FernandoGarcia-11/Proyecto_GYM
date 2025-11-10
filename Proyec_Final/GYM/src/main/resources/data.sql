INSERT INTO membresias (nombre_plan, precio_plan, duracion_de_meses_plan, descripcion) VALUES
    ('BASICO', 120.00, 1, 'Plan básico'),
    ('BLACK', 200.00, 3, 'Plan black'),
    ('GOLD', 250.00, 6, 'Plan gold');

INSERT INTO clientes (dpi, nombre_cliente, apellido_cliente, correo_cliente, telefono_cliente, fecha_registro_cliente, fecha_nacimiento_cliente, estado_de_membresia, membresia_id)
VALUES (1234567890123, 'Juan', 'Perez', 'juan@example.com', '55555555', NOW(), '1990-01-01', true, 1);
