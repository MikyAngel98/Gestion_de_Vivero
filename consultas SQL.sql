

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
end;
go

--aumenta el stock global del plantin cada vez que se realiza un ingreso
create procedure sp_sumarStockGlobalPlantin(
	@plantinId int,
	@cantidadPlantin int)
as begin
	set nocount on;

	update Plantin
	set stock_actual = stock_actual + @cantidadPlantin
	where id = @plantinId;

end;
go

--disminuye el stock global del plantin cada vez que se realiza una salida
create procedure sp_restarStockGlobalPlantin(
	@plantinId int,
	@cantidadPlantin int
)
as begin
	set nocount on;

	update Plantin
	set stock_actual = stock_actual + @cantidadPlantin
	where id = @plantinId;
end;
go

--muestra una lista de las areas de cultivo, plantines y detalles
create procedure sp_mostrasAreaDeCultivosPlantines
as begin
	
	select pac.id as id,
		p.nombre as Plantin,
		pac.tamaño as tamañoPlantin,
		pac.stock as stock,
		ac.nombre as AreaCultivo
	

	from PlantinAreaCultivo pac
	inner join Plantin p on p.id = pac.plantin_id
	inner join AreaCultivo ac on ac.id = pac.area_cultivo_id 
	order by Plantin
end;

exec sp_mostrasAreaDeCultivosPlantines