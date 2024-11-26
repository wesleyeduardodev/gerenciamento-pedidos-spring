package br.com.aceleraprogramador.gerenciamento_pedidos.adapter;
import br.com.aceleraprogramador.gerenciamento_pedidos.enuns.RoleType;
import br.com.aceleraprogramador.gerenciamento_pedidos.model.Role;
import br.com.aceleraprogramador.gerenciamento_pedidos.model.Usuario;
import lombok.experimental.UtilityClass;
import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class UsuarioAdapter {

    public static Usuario criarPerfilDeUsuario(String nome, String senha, String email, RoleType roleType) {
        return Usuario
                .builder()
                .nome(nome)
                .senha(senha)
                .email(email)
                .roles(List.of(Role.
                        builder()
                        .roleType(roleType)
                        .build()))
                .build();
    }

    public static Usuario criarPerfilDeUsuario(String nome, String senha, String email, List<RoleType> roleTypes) {
        return Usuario
                .builder()
                .nome(nome)
                .senha(senha)
                .email(email)
                .roles(criarModelRoles(roleTypes))
                .build();
    }

    private List<Role> criarModelRoles(List<RoleType> roleTypes) {
        List<Role> roles = new ArrayList<>();
        roleTypes.forEach(roleType -> roles.add(Role
                .builder()
                .roleType(roleType)
                .build()));
        return roles;
    }
}
