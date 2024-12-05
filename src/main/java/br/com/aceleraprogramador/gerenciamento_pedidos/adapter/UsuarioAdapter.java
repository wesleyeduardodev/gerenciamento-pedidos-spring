package br.com.aceleraprogramador.gerenciamento_pedidos.adapter;
import br.com.aceleraprogramador.gerenciamento_pedidos.dto.request.UsuarioRequest;
import br.com.aceleraprogramador.gerenciamento_pedidos.dto.response.UsuarioResponse;
import br.com.aceleraprogramador.gerenciamento_pedidos.enuns.RoleType;
import br.com.aceleraprogramador.gerenciamento_pedidos.model.Role;
import br.com.aceleraprogramador.gerenciamento_pedidos.model.Usuario;
import lombok.experimental.UtilityClass;
import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class UsuarioAdapter {

    public static Usuario toEntity(UsuarioRequest request, String senha) {
        return Usuario
                .builder()
                .nome(request.getNome())
                .senha(senha)
                .email(request.getEmail())
                .roles(toResponseListFromRequest(request.getRoles()))
                .build();
    }

    public static Usuario toEntity(String nome, String senha, String email, List<RoleType> roleTypes) {
        return Usuario
                .builder()
                .nome(nome)
                .senha(senha)
                .email(email)
                .roles(criarModelRoles(roleTypes))
                .build();
    }

    public static UsuarioResponse toResponse(Usuario usuario) {
        return UsuarioResponse
                .builder()
                .id(usuario.getId())
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .roles(toResponseListFromEnity(usuario.getRoles()))
                .build();
    }

    public static List<String> toResponseListFromEnity(List<Role> roles) {
        List<String> rolesResponse = new ArrayList<>();
        for (Role role : roles) {
            rolesResponse.add(role.getRoleType().name());
        }
        return rolesResponse;
    }

    public static List<Role> toResponseListFromRequest(List<String> requests) {
        List<Role> roles = new ArrayList<>();
        for (String role : requests) {
            roles.add(Role.builder()
                    .roleType(RoleType.valueOf(role))
                    .build());
        }
        return roles;
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
