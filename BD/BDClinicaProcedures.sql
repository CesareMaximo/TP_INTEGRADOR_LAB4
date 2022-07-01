use bdclinica;
DELIMITER // 
CREATE DEFINER=`root`@`localhost` PROCEDURE `registrarPaciente`(
IN dni varchar(11), 
IN nombre varchar(50),
IN apellido varchar(50), 
IN sexo enum('M','F','O'), 
IN idnacionalidad int,
IN fechanacimiento date,
IN direccion varchar(50),
IN idlocalidad int, 
IN email varchar(50),
IN estado tinyint,
IN telefono1 varchar(50),
IN telefono2 varchar(50))
BEGIN
	INSERT INTO Persona (DNI, Nombre, Apellido, Sexo, idNacionalidad, FechaNacimiento, Direccion, idLocalidad, Email, Estado, Telefono1, Telefono2) VALUES (dni,nombre,apellido,sexo,idnacionalidad,fechanacimiento,direccion,idlocalidad,email,estado, telefono1, telefono2);
	INSERT INTO paciente(DNI) VALUES (dni);
END;

DELIMITER // 
CREATE DEFINER=`root`@`localhost` PROCEDURE `registrarUsuMedico`(
IN nombreUsuario varchar(25), 
IN clave varchar(100),
IN tipo enum('Admin','Medico'),
IN dni varchar(11),
IN nombre varchar(50),
IN apellido varchar(50),
IN sexo enum('M','F','O'),
IN idNacionalidad int, 
IN fechaNacimiento date,
IN direccion varchar(50),
IN idLocalidad int,
IN email varchar(50),
IN estado tinyint,
IN telefono1 varchar(50),
IN telefono2 varchar(50))
BEGIN
	INSERT INTO Usuarios(NombreUsuario, Clave, Tipo, Estado) VALUES(nombreUsuario, clave, tipo, estado);
    INSERT INTO Persona (DNI, Nombre, Apellido, Sexo, idNacionalidad, FechaNacimiento, Direccion, idLocalidad, Email, Estado, Telefono1, Telefono2) VALUES (dni,nombre,apellido,sexo,idnacionalidad,fechanacimiento,direccion,idlocalidad,email,estado, telefono1, telefono2);
END;

