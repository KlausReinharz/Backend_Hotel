Desarrollo del backend de una reservacion de hotel
se utiliza el framework spring boot y como base de datos PostgreSQL
Se aplica autenticación JWT en donde la version mas estable de esta es jjwt-apli, impl y json son la version 0.11.5
Observacion conficto con una dependencia <!-- https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt -->
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt</artifactId>
    <version>0.9.1</version>
</dependency>
ya que cierta llamada de metodos no funcionaban correctamente por lo tanto debe no debe ser incluida en el archivo pom
