   function postData(){
    const data = {
    nazwa: document.getElementById('nazwa').value,
    wiadomosc: document.getElementById('wiadomosc').value,
    kontakt: document.getElementById('kontakt').value
    };

    fetch('/dane/stworz', {
    method: 'POST',
    headers: {
    'Content-Type': 'application/json'
    },
    body: JSON.stringify(data)
    })
    .then(response => {
    if (!response.ok) {
    throw new Error('Wystąpił błąd podczas wysyłania danych.');
    }
    // Możesz dodać kod obsługujący poprawną odpowiedź, np. wyświetlenie komunikatu o sukcesie.
    console.log('Dane zostały pomyślnie wysłane.');
    })
    .catch(error => {
    console.error('Wystąpił błąd', error);
    });

    console.log('wykonano');
    }

