CREATE TABLE endereco (
    id SERIAL PRIMARY KEY,
    rua VARCHAR(100),
    numero VARCHAR(10)
);

CREATE TABLE contato (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100),
    email VARCHAR(100),
    endereco_id INT,
    FOREIGN KEY (endereco_id) REFERENCES endereco(id)
);