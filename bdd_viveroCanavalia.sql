CREATE DATABASE ViveroCanavalia;
GO

USE ViveroCanavalia;
GO

-- Tabla Plantin (Registra los tipos de plantines)
CREATE TABLE Plantin (
    id INT IDENTITY(1,1) PRIMARY KEY,
    nombre NVARCHAR(50) NOT NULL,
    descripcion NVARCHAR(255),
    stock_actual INT NOT NULL DEFAULT 0
);
GO

-- Tabla AreaCultivo (Áreas de cultivo para almacenar plantines)
CREATE TABLE AreaCultivo (
    id INT IDENTITY(1,1) PRIMARY KEY,
    nombre NVARCHAR(25) NOT NULL,
    medida NVARCHAR(25)
);
GO

-- Tabla TipoMovimiento (Para registrar el tipo de movimiento: Ingreso, Salida)
CREATE TABLE TipoMovimiento (
    id INT IDENTITY(1,1) PRIMARY KEY,
    nombre NVARCHAR(25) NOT NULL -- Ej: "Compra", "Donación", "Venta", "Trasplante"
);
GO

-- Tabla PlantinAreaCultivo (Relación entre plantines y áreas de cultivo)
CREATE TABLE PlantinAreaCultivo (
    id INT IDENTITY(1,1) PRIMARY KEY,
    plantin_id INT NOT NULL,
    area_cultivo_id INT NOT NULL,
    stock INT NOT NULL DEFAULT 0,
    tamaño NVARCHAR(50),
    FOREIGN KEY (plantin_id) REFERENCES Plantin(id),
    FOREIGN KEY (area_cultivo_id) REFERENCES AreaCultivo(id),
    UNIQUE (plantin_id, area_cultivo_id) -- Evita registros duplicados
);
GO

-- Tabla Ingreso (Registra la entrada de nuevos plantines)
CREATE TABLE Ingreso (
    id INT IDENTITY(1,1) PRIMARY KEY,
    plantin_area_id INT NOT NULL,
    cantidad INT NOT NULL CHECK (cantidad > 0),
    fecha_ingreso DATETIME NOT NULL DEFAULT GETDATE(),
    tipo_movimiento_id INT NOT NULL, 
    descripcion NVARCHAR(255),
    FOREIGN KEY (plantin_area_id) REFERENCES PlantinAreaCultivo(id),
    FOREIGN KEY (tipo_movimiento_id) REFERENCES TipoMovimiento(id)
);
GO

-- Tabla Salida (Registra la salida de plantines)
CREATE TABLE Salida (
    id INT IDENTITY(1,1) PRIMARY KEY,
    plantin_area_id INT NOT NULL,
    cantidad INT NOT NULL CHECK (cantidad > 0),
    fecha_salida DATETIME NOT NULL DEFAULT GETDATE(),
    tipo_movimiento_id INT NOT NULL, 
    descripcion NVARCHAR(255),
    FOREIGN KEY (plantin_area_id) REFERENCES PlantinAreaCultivo(id),
    FOREIGN KEY (tipo_movimiento_id) REFERENCES TipoMovimiento(id)
);
GO

-- Tabla HistorialMovimientos (Registra todos los ingresos y salidas)
CREATE TABLE HistorialMovimientos (
    id INT IDENTITY(1,1) PRIMARY KEY,
    plantin_id INT NOT NULL,
    tipo_movimiento NVARCHAR(10) CHECK (tipo_movimiento IN ('Ingreso', 'Salida')),
    cantidad INT NOT NULL CHECK (cantidad > 0),
    fecha_movimiento DATETIME NOT NULL DEFAULT GETDATE(),
    area_cultivo_id INT,
    descripcion NVARCHAR(255),
    FOREIGN KEY (plantin_id) REFERENCES Plantin(id),
    FOREIGN KEY (area_cultivo_id) REFERENCES AreaCultivo(id)
);
GO
