

--muestra en que area de cultivo esta el plantin, busca por nombre
create procedure sp_verUbicacionPlantin(
	@nombrePlantin varchar(50))
as begin
	select 
		p.nombre,  
		pac.stock, 
		pac.tamaño, 
		ac.nombre as areaCultivo 
	from PlantinAreaCultivo pac
	inner join Plantin p on pac.plantin_id = p.id
	inner join AreaCultivo ac on pac.area_cultivo_id = ac.id
	where p.nombre like '%' + @nombrePlantin + '%'
end

go

--muestra que plantines estan en un area de cultivo
create procedure sp_verPlantinesEnAreaCultivo(
	@idAreaCultivo int)
as begin
	select 
		p.nombre,  
		pac.stock, 
		pac.tamaño, 
		ac.nombre as areaCultivo 
	from PlantinAreaCultivo pac
	inner join Plantin p on pac.plantin_id = p.id
	inner join AreaCultivo ac on pac.area_cultivo_id = ac.id
	where ac.id = @idAreaCultivo;
end

exec sp_verPlantinesEnAreaCultivo @idAreaCultivo = 3;

exec sp_verUbicacionPlantin @nombreplantin = 'Palta';

INSERT INTO PlantinAreaCultivo (plantin_id, area_cultivo_id, stock, tamaño) 
VALUES 
    (1, 2, 5, 'pequeño'), 
    (2, 3, 8, 'Mediano'),
	(1, 4, 5, 'Grande'), 
    (4, 5, 8, 'Mediano'),
    (3, 1, 10, 'Pequeño');


	select * from Plantin
	select * from AreaCultivo


	SELECT name 
FROM sys.key_constraints 
WHERE type = 'UQ' AND parent_object_id = OBJECT_ID('Plantin_Area_Cultivo');
