let processInstanceKey;
let success;

// Obsługa formularza rejestracji
document.getElementById('webinarForm').addEventListener('submit', function (event) {
  event.preventDefault();

  const formData = {
    firstName: document.getElementById('firstName').value,
    lastName: document.getElementById('lastName').value,
    email: document.getElementById('email').value,
    webinarId: Number(document.getElementById('webinarId').value)
  };

  disableWebinarReservationButton();

  fetch('/start', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(formData),
  })
    .then(response => response.json())
    .then(data => {
      document.getElementById('webinarForm').reset();

      processInstanceKey = data.processInstanceKey;

      // Nasłuchiwanie na odpowiedź z backendu
      const eventSource = new EventSource(`/subscribe?processInstanceKey=${processInstanceKey}`);

      eventSource.onmessage = function (event) {
        const data = JSON.parse(event.data);
        if (data.success === true) {
          success = true;
          console.log("Otrzymano pozytywną odpowiedź");

          document.getElementById('form').style.display = 'none';
          const successMessage = document.getElementById('success-message');
          successMessage.innerHTML = "Aplikacja zarejestrowana. Decyzja zostanie przekazana na podany adres email.";
          document.getElementById('success').style.display = 'block';
          document.getElementById('payButton').style.display = 'block';

          eventSource.close();
        } else if (data.success === false) {
          success = false;
          document.getElementById('form').style.display = 'none';
          const successMessage = document.getElementById('success-message');
          successMessage.innerHTML = "Brak wolnych miejsc, spróbuj inny dzień.";
          document.getElementById('success').style.display = 'block';
          document.getElementById('payButton').style.display = 'none';
          eventSource.close();
        }
      };

      eventSource.onerror = function (error) {
        console.error("Błąd połączenia SSE:", error);
        eventSource.close();
      };
    })
    .catch(error => {
      console.error("Błąd podczas rejestracji:", error);
      alert("Wystąpił błąd podczas rejestracji. Spróbuj ponownie.");
    });
});

// Obsługa przycisku "Zapłać"
document.getElementById('payButton').addEventListener('click', function () {
  if (success) {
    // wyśwetl formularz do płatności
    document.getElementById('success').style.display = 'none';
    document.getElementById('paymentForm').style.display = 'block';
  } else {
    // wyśwetl od nowa formularz czy coś
    document.getElementById('form').style.display = 'block';
    document.getElementById('success').style.display = 'none';
    document.getElementById('payButton').style.display = 'none';
  }
});

// Obsługa formularza płatności
document.getElementById('creditCardForm').addEventListener('submit', function (event) {
  event.preventDefault();

  const paymentData = {
    owner: document.getElementById('owner').value,
    cardNumber: document.getElementById('cardNumber').value,
    expiryDate: document.getElementById('expiryDate').value,
    code: document.getElementById('code').value,
    processInstanceKey: processInstanceKey
  };

  fetch('/payment', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(paymentData),
  })
    .then(response => {
      if (!response.ok) {
        throw new Error('Network response was not ok');
      }
      return response.json();
    })
    .then(data => {
      document.getElementById('creditCardForm').reset();
      document.getElementById('paymentForm').style.display = 'none';
      document.getElementById('thankYouMessage').style.display = 'block';
    })
    .catch(error => {
      console.error("Błąd podczas przetwarzania płatności:", error);
      alert("Wystąpił błąd podczas przetwarzania płatności. Spróbuj ponownie.");
    });
});

function disableWebinarReservationButton() {
  document.getElementById('webinarForm').querySelector('button').disabled = true;
  document.getElementById('webinarForm').querySelector('button').textContent = "Proszę czekać...";
}
