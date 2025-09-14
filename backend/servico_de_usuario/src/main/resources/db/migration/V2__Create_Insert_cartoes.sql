-- Inserindo alguns cartões de exemplo (sem passar numero_cartao, o banco gera sozinho)
INSERT INTO service.cartao (cartao_id, nome, tipo_cartao, status_cartao, usuario_id) VALUES
(1, 'Cartão Mateus', 'COMUM', TRUE, 2),
(2, 'Cartão Mateus Estudante', 'ESTUDANTE', TRUE, 3),
(3, 'Cartão João', 'TRABALHADOR', TRUE, 4),
(4, 'Cartão Maria', 'COMUM', FALSE, 5),
(5, 'Cartão Ana', 'ESTUDANTE', TRUE, 6);
