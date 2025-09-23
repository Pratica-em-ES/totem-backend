-- 1. Coordenada must come first
CREATE TABLE Coordenada (
    id SERIAL PRIMARY KEY,
    coordenada_x DOUBLE PRECISION NOT NULL,
    coordenada_y DOUBLE PRECISION NOT NULL
);

-- 2. Predio depends on Coordenada
CREATE TABLE Predio (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    id_coordenada INT REFERENCES Coordenada(id) ON DELETE SET NULL,
    model_path VARCHAR(500)
);

-- 3. Empresa depends on Predio
CREATE TABLE Empresa (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    categoria VARCHAR(255),
    descricao TEXT,
    predio INT REFERENCES Predio(id) ON DELETE SET NULL
);

-- 4. Predio_Empresa depends on Predio and Empresa
CREATE TABLE Predio_Empresa (
    id_predio INT REFERENCES Predio(id) ON DELETE CASCADE,
    id_empresa INT REFERENCES Empresa(id) ON DELETE CASCADE,
    andares INT,
    PRIMARY KEY (id_predio, id_empresa)
);

-- 5. Rua depends on Coordenada
CREATE TABLE Rua (
    id SERIAL PRIMARY KEY,
    largura DOUBLE PRECISION,
    id_coordenada_a INT REFERENCES Coordenada(id) ON DELETE CASCADE,
    id_coordenada_b INT REFERENCES Coordenada(id) ON DELETE CASCADE
);

-- 6. Predio_Rua depends on Predio, Rua and Coordenada
CREATE TABLE Predio_Rua (
    id_predio INT REFERENCES Predio(id) ON DELETE CASCADE,
    id_rua INT REFERENCES Rua(id) ON DELETE CASCADE,
    id_coordenada INT REFERENCES Coordenada(id) ON DELETE CASCADE,
    PRIMARY KEY (id_predio, id_rua, id_coordenada)
);

-- 1. Coordenada (base table)
CREATE TABLE Coordenada (
    id SERIAL PRIMARY KEY,
    coordenada_x DOUBLE PRECISION NOT NULL,
    coordenada_y DOUBLE PRECISION NOT NULL
);

-- 2. Predio
CREATE TABLE Predio (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    id_coordenada INT,
    model_path VARCHAR(500),
    CONSTRAINT fk_predio_coordenada FOREIGN KEY (id_coordenada)
        REFERENCES Coordenada(id)
        ON DELETE SET NULL
        ON UPDATE CASCADE
);

-- 3. Empresa
CREATE TABLE Empresa (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    categoria VARCHAR(255),
    descricao TEXT,
    predio INT,
    CONSTRAINT fk_empresa_predio FOREIGN KEY (predio)
        REFERENCES Predio(id)
        ON DELETE SET NULL
        ON UPDATE CASCADE
);

-- 4. Predio_Empresa (many-to-many)
CREATE TABLE Predio_Empresa (
    id_predio INT NOT NULL,
    id_empresa INT NOT NULL,
    andares INT,
    PRIMARY KEY (id_predio, id_empresa),
    CONSTRAINT fk_pe_predio FOREIGN KEY (id_predio)
        REFERENCES Predio(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT fk_pe_empresa FOREIGN KEY (id_empresa)
        REFERENCES Empresa(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

-- 5. Rua
CREATE TABLE Rua (
    id SERIAL PRIMARY KEY,
    largura DOUBLE PRECISION,
    id_coordenada_a INT NOT NULL,
    id_coordenada_b INT NOT NULL,
    CONSTRAINT fk_rua_coordenada_a FOREIGN KEY (id_coordenada_a)
        REFERENCES Coordenada(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT fk_rua_coordenada_b FOREIGN KEY (id_coordenada_b)
        REFERENCES Coordenada(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

-- 6. Predio_Rua (junction table)
CREATE TABLE Predio_Rua (
    id_predio INT NOT NULL,
    id_rua INT NOT NULL,
    id_coordenada INT NOT NULL,
    PRIMARY KEY (id_predio, id_rua, id_coordenada),
    CONSTRAINT fk_pr_predio FOREIGN KEY (id_predio)
        REFERENCES Predio(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT fk_pr_rua FOREIGN KEY (id_rua)
        REFERENCES Rua(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT fk_pr_coordenada FOREIGN KEY (id_coordenada)
        REFERENCES Coordenada(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

-- 7. Indexes to speed up joins
CREATE INDEX idx_predio_coordenada ON Predio(id_coordenada);
CREATE INDEX idx_empresa_predio ON Empresa(predio);
CREATE INDEX idx_pe_predio ON Predio_Empresa(id_predio);
CREATE INDEX idx_pe_empresa ON Predio_Empresa(id_empresa);
CREATE INDEX idx_rua_coord_a ON Rua(id_coordenada_a);
CREATE INDEX idx_rua_coord_b ON Rua(id_coordenada_b);
CREATE INDEX idx_pr_predio ON Predio_Rua(id_predio);
CREATE INDEX idx_pr_rua ON Predio_Rua(id_rua);
CREATE INDEX idx_pr_coord ON Predio_Rua(id_coordenada);

