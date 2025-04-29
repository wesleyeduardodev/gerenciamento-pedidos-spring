-- Criação do usuário caso não exista
DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM usuario WHERE id = 1) THEN
        INSERT INTO usuario (id, email, nome, senha)
        VALUES (1, 'wesley@gmail.com', 'Wesley Eduardo', '$2a$10$4wwRH6NMIEHLc7D7Inf11ub1sC4/cMTwdhHEsbcCq6kIwUcE1F7dK');
END IF;
END
$$;

-- Criação das roles caso não existam
DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM role WHERE id = 1) THEN
        INSERT INTO role (id, role_type) VALUES (1, 'ROLE_USUARIO');
END IF;
    IF NOT EXISTS (SELECT 1 FROM role WHERE id = 2) THEN
        INSERT INTO role (id, role_type) VALUES (2, 'ROLE_ADMINISTRADOR');
END IF;
    IF NOT EXISTS (SELECT 1 FROM role WHERE id = 3) THEN
        INSERT INTO role (id, role_type) VALUES (3, 'ROLE_GERENTE');
END IF;
END
$$;

-- Associação entre usuário e roles, se ainda não existir
DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM roles_usuario WHERE usuario_id = 1 AND role_id = 1) THEN
        INSERT INTO roles_usuario (usuario_id, role_id) VALUES (1, 1);
END IF;
    IF NOT EXISTS (SELECT 1 FROM roles_usuario WHERE usuario_id = 1 AND role_id = 2) THEN
        INSERT INTO roles_usuario (usuario_id, role_id) VALUES (1, 2);
END IF;
    IF NOT EXISTS (SELECT 1 FROM roles_usuario WHERE usuario_id = 1 AND role_id = 3) THEN
        INSERT INTO roles_usuario (usuario_id, role_id) VALUES (1, 3);
END IF;
END
$$;
