CREATE DATABASE IF NOT EXISTS overwatch_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE overwatch_db;

INSERT INTO herois (nome, funcao, vida_base, habilidade_suprema) VALUES
('Ramattra', 'Tanque', 450, 'Aniquilação'),
('Sojourn', 'Dano', 250, 'Overclock'),
('Kiriko', 'Suporte', 250, 'Investida da Raposa');

INSERT INTO jogadores (battle_tag, nome_real, funcao_principal, elo) VALUES
('KaiMoralez#1998', 'Kaique', 'Tanque', 'Diamante'),
('Strike#4412', 'Lucas', 'Dano', 'Mestre');

INSERT INTO equipes (nome, sigla, regiao, treinador) VALUES
('Overwatch Brasil', 'OWBR', 'América do Sul', 'Coach Silva'),
('Tokyo Watch', 'TKW', 'Ásia', 'Coach Sato');

INSERT INTO mapas (nome, modo_jogo, pais) VALUES
('King''s Row', 'Híbrido', 'Reino Unido'),
('Ilios', 'Controle', 'Grécia');

INSERT INTO partidas (equipe_a, equipe_b, mapa_jogado, placar, data_partida) VALUES
('Overwatch Brasil', 'Tokyo Watch', 'King''s Row', '3 x 2', '2026-11-23');
