CREATE TRIGGER RegistrarHistorialIngreso
ON Ingreso
AFTER INSERT
AS
BEGIN
    DECLARE @PlantinId INT, @Cantidad INT, @AreaCultivoId INT, @TipoMovimiento NVARCHAR(10);

    -- Obtén los datos del ingreso
    SELECT 
        @PlantinId = plantin_id,
        @Cantidad = cantidad,
        @AreaCultivoId = area_cultivo_id,
        @TipoMovimiento = 'Ingreso' -- Movimiento tipo ingreso
    FROM inserted;

    -- Registra el movimiento en HistorialMovimientos
    INSERT INTO HistorialMovimientos (plantin_id, tipo_movimiento, cantidad, fecha_movimiento, area_cultivo_id)
    VALUES (@PlantinId, @TipoMovimiento, @Cantidad, GETDATE(), @AreaCultivoId);
END;
GO


CREATE TRIGGER RegistrarHistorialSalida
ON Salida
AFTER INSERT
AS
BEGIN
    DECLARE @PlantinId INT, @Cantidad INT, @AreaCultivoId INT, @TipoMovimiento NVARCHAR(10);

    -- Obtén los datos de la salida
    SELECT 
        @PlantinId = plantin_id,
        @Cantidad = cantidad,
        @AreaCultivoId = area_cultivo_id,
        @TipoMovimiento = 'Salida' -- Movimiento tipo salida
    FROM inserted;

    -- Registra el movimiento en HistorialMovimientos
    INSERT INTO HistorialMovimientos (plantin_id, tipo_movimiento, cantidad, fecha_movimiento, area_cultivo_id)
    VALUES (@PlantinId, @TipoMovimiento, @Cantidad, GETDATE(), @AreaCultivoId);
END;
GO
