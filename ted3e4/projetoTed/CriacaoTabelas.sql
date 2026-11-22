CREATE DATABASE IF NOT EXISTS banco_db;
USE banco_db;

CREATE TABLE IF NOT EXISTS tb_conta (
    conta_id INT AUTO_INCREMENT PRIMARY KEY,
    numero VARCHAR(20) NOT NULL UNIQUE,
    titular VARCHAR(100) NOT NULL,
    saldo DECIMAL(12,2) NOT NULL DEFAULT 0,
    tipo VARCHAR(20) NOT NULL
);

CREATE TABLE tb_conta_corrente (
    conta_id INT PRIMARY KEY,
    limite DECIMAL(12,2) NOT NULL,
    FOREIGN KEY (conta_id)
        REFERENCES tb_conta(conta_id)
        ON DELETE CASCADE
);

CREATE TABLE tb_conta_poupanca (
    conta_id INT PRIMARY KEY,
    rendimento DECIMAL(12,4) NOT NULL DEFAULT 0,
    FOREIGN KEY (conta_id)
        REFERENCES tb_conta(conta_id)
        ON DELETE CASCADE
);

CREATE TABLE tb_operacoes (
    operacoes_id INT AUTO_INCREMENT PRIMARY KEY,
    conta_id INT NOT NULL,
    operacao VARCHAR(20) NOT NULL,
    valor DECIMAL(12,2) NOT NULL,
    data_operacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (conta_id)
        REFERENCES tb_conta(conta_id)
        ON DELETE CASCADE
);
