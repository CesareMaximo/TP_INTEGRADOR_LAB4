DROP SCHEMA BDClinica;
CREATE SCHEMA BDClinica;
use BDClinica;

CREATE TABLE Nacionalidad (
  idNacionalidad int not null primary key auto_increment,
  Descripcion varchar(50) not null
);

CREATE TABLE Provincia (
  idProvincia int not null primary key auto_increment,
  Descripcion varchar(20) not null
);

CREATE TABLE Localidad (
  idLocalidad int not null primary key auto_increment,
  idProvincia int not null,
  Descripcion varchar(50) not null,  
  FOREIGN KEY (idProvincia) REFERENCES Provincia(idProvincia)
);

CREATE TABLE Persona(
  DNI varchar(11) not null primary key,
  Nombre varchar(50) not null,
  Apellido varchar(50) not null,
  Sexo enum('M', 'F', 'O') not null,
  idNacionalidad int not null,
  FechaNacimiento date not null,
  Direccion varchar(50),
  idLocalidad int not null,
  Email varchar(50) not null,
  Estado Bool,
  Telefono1 varchar(50) not null,
  Telefono2 varchar(50),
  FOREIGN KEY (idNacionalidad) REFERENCES Nacionalidad(idNacionalidad),
  FOREIGN KEY (idLocalidad) REFERENCES Localidad(idLocalidad)
);

CREATE TABLE Paciente(
  idPaciente int not null primary key auto_increment,
  DNI varchar(11) not null unique,
  FOREIGN KEY (DNI) REFERENCES Persona(DNI)
);



CREATE TABLE Usuarios(
  idUsuario int not null primary key auto_increment,
  NombreUsuario varchar(25) not null unique,
  Clave varchar(100) not null,
  Tipo enum('Admin', 'Medico') not null,
  Estado bool
);

CREATE TABLE Especialidad(
  idEspecialidad  int not null primary key auto_increment,
  Descripcion varchar(50) not null
);

CREATE TABLE Medico (
  DNI varchar(11) not null unique,
  idMedico int not null primary key,
  idEspecialidad int not null,
  FOREIGN KEY (idEspecialidad) REFERENCES Especialidad(idEspecialidad),
  FOREIGN KEY (idMedico) REFERENCES Usuarios(idUsuario)
);

CREATE TABLE Dia(
	idDia int not null primary key,
    Descripcion varchar(15) not null,
    
    CONSTRAINT idDia CHECK (idDia>=1 AND idDia<=7)
);

CREATE TABLE Dia_x_Medico(
  idDia int not null,
  idMedico int not null,
  HorarioIngreso time not null,
  HorarioEgreso time not null,
  Estado boolean not null,
  FOREIGN KEY (idDia) REFERENCES Dia(idDia),
  FOREIGN KEY (idMedico) REFERENCES Medico(idMedico),
  primary key (idDia, idMedico)
);

CREATE TABLE Estados(
  idEstado int not null primary key auto_increment,
  NombreEstado varchar(20) not null
);


CREATE TABLE Turno (
  idTurno int not null primary key auto_increment,
  idMedico int not null,
  Fecha date not null,
  idPaciente int,
  idEstado int not null,
  Hora time not null,
  Observaci??n varchar(150),
  
  FOREIGN KEY (idMedico) REFERENCES Medico(idMedico),
  FOREIGN KEY (idPaciente) REFERENCES Paciente(idPaciente),
  FOREIGN KEY (idEstado) REFERENCES Estados(idEstado)
);








