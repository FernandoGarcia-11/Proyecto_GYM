-- ======================================================
-- TABLA: MEMBRESIAS
-- ======================================================
DROP TABLE IF EXISTS pagos, entrenamientos, formas_de_pago, clientes, membresias;

CREATE TABLE IF NOT EXISTS membresias (
    membresia_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre_plan VARCHAR(20) NOT NULL,
    precio_plan DECIMAL(10,2) NOT NULL,
    duracion_de_meses_plan INT NOT NULL,
    descripcion VARCHAR(255)
    );

-- ======================================================
-- TABLA: CLIENTES
-- ======================================================
CREATE TABLE IF NOT EXISTS clientes (
    cliente_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    dpi BIGINT NOT NULL,
    nombre_cliente VARCHAR(100) NOT NULL,
    apellido_cliente VARCHAR(100) NOT NULL,
    correo_cliente VARCHAR(150),
    telefono_cliente VARCHAR(15),
    fecha_registro_cliente DATE DEFAULT (CURDATE()),
    fecha_nacimiento_cliente DATE,
    estado_de_membresia BOOLEAN DEFAULT TRUE,
    membresia_id BIGINT,
    CONSTRAINT fk_membresia_cliente FOREIGN KEY (membresia_id)
    REFERENCES membresias(membresia_id)
    );

-- ======================================================
-- TABLA: FORMAS_DE_PAGO
-- ======================================================
CREATE TABLE IF NOT EXISTS formas_de_pago (
    forma_pago_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    tipo_tarjeta VARCHAR(30) NOT NULL,
    numero_tarjeta CHAR(16) NOT NULL,
    vencimiento DATE NOT NULL,
    nit VARCHAR(20),
    cliente_id BIGINT,
    CONSTRAINT fk_cliente_forma_pago FOREIGN KEY (cliente_id)
    REFERENCES clientes(cliente_id)
    ON DELETE CASCADE
    );

-- ======================================================
-- TABLA: PAGOS
-- ======================================================
CREATE TABLE IF NOT EXISTS pagos (
    pago_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    fecha_pago DATE,
    monto DECIMAL(10,2) CHECK (monto >= 0),
    referencia VARCHAR(50),
    estado VARCHAR(30),
    cliente_id BIGINT,
    membresia_id BIGINT,
    forma_pago_id BIGINT,
    CONSTRAINT fk_cliente_pago FOREIGN KEY (cliente_id)
    REFERENCES clientes(cliente_id),
    CONSTRAINT fk_membresia_pago FOREIGN KEY (membresia_id)
    REFERENCES membresias(membresia_id),
    CONSTRAINT fk_forma_pago_pago FOREIGN KEY (forma_pago_id)
    REFERENCES formas_de_pago(forma_pago_id)
    );

-- ======================================================
-- TABLA: ENTRENAMIENTOS
-- ======================================================
DROP TABLE IF EXISTS entrenamientos;

CREATE TABLE entrenamientos (
entrenamiento_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                nombre_clase VARCHAR(100) NOT NULL,
                                horario VARCHAR(100) NOT NULL,
                                entrenador VARCHAR(100),
                                fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                cliente_id BIGINT NOT NULL,
                                CONSTRAINT fk_entrenamiento_cliente FOREIGN KEY (cliente_id)
                                    REFERENCES clientes(cliente_id)
                                    ON DELETE CASCADE
);




