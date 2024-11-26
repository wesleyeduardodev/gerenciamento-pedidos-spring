package br.com.aceleraprogramador.gerenciamento_pedidos.adapter;
import br.com.aceleraprogramador.gerenciamento_pedidos.enuns.Role;
import br.com.aceleraprogramador.gerenciamento_pedidos.model.ModelRole;
import br.com.aceleraprogramador.gerenciamento_pedidos.model.Usuario;
import lombok.experimental.UtilityClass;
import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class UsuarioAdapter {

    public static Usuario criarPerfilDeUsuario(String nome, String senha, String email, Role role) {
        return Usuario
                .builder()
                .nome(nome)
                .senha(senha)
                .email(email)
                .roles(List.of(ModelRole.
                        builder()
                        .name(role)
                        .build()))
                .build();
    }

    public static Usuario criarPerfilDeUsuario(String nome, String senha, String email, List<Role> roles) {
        return Usuario
                .builder()
                .nome(nome)
                .senha(senha)
                .email(email)
                .roles(criarModelRoles(roles))
                .build();
    }

    private List<ModelRole> criarModelRoles(List<Role> roles) {
        List<ModelRole> modelRoles = new ArrayList<>();
        roles.forEach(role -> modelRoles.add(ModelRole
                .builder()
                .name(role)
                .build()));
        return modelRoles;
    }
}
