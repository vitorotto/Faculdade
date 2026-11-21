CREATE TABLE tb_conta (
    conta_id INT AUTO_INCREMENT PRIMARY KEY,
    numero VARCHAR(20) NOT NULL UNIQUE,
    titular VARCHAR(100) NOT NULL,
    saldo DECIMAL(12,2) NOT NULL DEFAULT 0
);

CREATE TABLE tb_conta_corrente (
    conta_id INT PRIMARY KEY,
    limite DECIMAL(12,2) NOT NULL,

    FOREIGN KEY (conta_id)
        REFERENCES tb_conta(conta_id)
        ON DELETE CASCADE
);

CREATE TABLE tb_operacoes (
    operacoes_id INT PRIMARY KEY,
    operacao VARCHAR(100) NOT NULL,
    valor DECIMAL(6,4) NOT NULL,
    conta_id INT,

    FOREIGN KEY (conta_id)
        REFERENCES tb_conta(conta_id)
        ON DELETE CASCADE
);
