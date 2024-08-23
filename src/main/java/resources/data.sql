-- data.sql


INSERT INTO Prato (nome, descricao, preco) VALUES
('Espaguete à Bolonhesa', 'Espaguete com molho de carne e tomate.', 29.90),
('Pizza Margherita', 'Pizza com molho de tomate, mozzarella e manjericão.', 35.00),
('Ravioli de Ricota e Espinafre ao Molho de Manteiga e Sálvia', 'Delicados raviolis recheados com uma mistura cremosa de ricota fresca e espinafre refogado, servidos com um molho suave de manteiga e folhas de sálvia crocantes.', 48.00),
('Risoto de Cogumelos com Trufa', 'Risoto cremoso preparado com uma seleção de cogumelos frescos, finalizado com azeite trufado, proporcionando uma explosão de sabores terrosos e sofisticados.', 54.00),
('Polenta Cremosa com Ragu de Carne', 'Polenta cremosa feita com fubá italiano, acompanhada de um ragu de carne lentamente cozido, temperado com ervas aromáticas e vinho tinto.', 52.00),
('Tiramisu Tradicional', 'Sobremesa clássica italiana, com camadas de biscoito embebido em café, creme de mascarpone e uma generosa polvilhada de cacau em pó.', 32.00);


INSERT INTO Reserva (nome, cpf, email, data_reserva) VALUES
('João da Silva', '123.456.789-00', 'joao.silva@email.com', '2024-09-10'),
('Maria Oliveira', '987.654.321-00', 'maria.oliveira@email.com', '2024-09-15');
