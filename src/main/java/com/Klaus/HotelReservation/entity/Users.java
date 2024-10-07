package com.Klaus.HotelReservation.entity;

import com.Klaus.HotelReservation.enums.UserRole;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/*UserDetails es una interfaz que representan detalles
del usuario que va a ser autenticado
*/

/*Se debe realizar metodos relacionado con JWT con que estara relacionado
* con el paquete utils con la clase JWTUTIL*/

@Entity
@Data
public class Users implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String name;
    private String password;
    private UserRole userRole;


//metodos sobrescritos(Override)

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(userRole.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

/*Indica si la cuenta del usuario ha expirado o no, si
* la cuenta esta expirado, el usuario no podra auntenticarse
* si devuelve un true significa que el sitemas no esta
* implementado expiracion de cuentas
* */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }


/*Indica que la cuenta esta bloqueada o no*/
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }



/*Indica si las credenciales (como la contraseña) han expirado o no.
Si las credenciales están expiradas, el usuario no podrá autenticarse.
En este caso, siempre devuelve true, lo que significa que no se está
manejando la expiración de credenciales.*/
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }



 /*Indica si la cuenta del usuario está habilitada o no. Si está
deshabilitada, el usuario no podrá autenticarse. Devuelves true,
lo que significa que todas las cuentas están habilitadas por defecto.*/
    @Override
    public boolean isEnabled() {
        return true;
    }
}
