/**
 * Author:  Pablo Rangel <pablo.rangel@gmail.com>
 * Created: 2 de jul de 2021
 */
insert into Usuario (login,senha, papel, id) values
('admin','$2a$10$K6PG.YUsSpMT/LOyPpeB5eUVdPImfDfSH.N0xLHAC1NbgbIBhraHe','ADMIN',1);

INSERT INTO cliente(
    id,
    empresa,
    cnpj,
    cep,
    cidade,
    estado,
    bairro,
    complemento,
    usuario_id
) values (1, 'ababa', '00360305000104', '20756121', 'rj', 'rj', 'ababa', 'ababa', 1);

SELECT * FROM usuario;