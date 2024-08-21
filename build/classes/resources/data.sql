-- data.sql


INSERT INTO Cardapio (nome, descricao, preco) VALUES
('Espaguete à Bolonhesa', 'Espaguete com molho de carne e tomate.', 29.90),
('Pizza Margherita', 'Pizza com molho de tomate, mozzarella e manjericão.', 35.00);

INSERT INTO Reserva (nome, cpf, email, data_reserva) VALUES
('João da Silva', '123.456.789-00', 'joao.silva@email.com', '2024-09-10'),
('Maria Oliveira', '987.654.321-00', 'maria.oliveira@email.com', '2024-09-15');
