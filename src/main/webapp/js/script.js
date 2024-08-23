document.getElementById('data_reserva').addEventListener('change', function() {
            const dataReserva = this.value;

            fetch(`/restauranteDoZe/reserva/check?data_reserva=${dataReserva}`)
                .then(response => response.json())
                .then(data => {
                    const disponibilidadeElement = document.getElementById('disponibilidade');
                    if (data.disponivel) {
                        disponibilidadeElement.textContent = 'Data disponível!';
                        disponibilidadeElement.style.color = 'green';
                    } else {
                        disponibilidadeElement.textContent = 'Data indisponível!';
                        disponibilidadeElement.style.color = 'red';
                    }
                })
                .catch(error => {
                    console.error('Erro ao verificar disponibilidade:', error);
                });
        });