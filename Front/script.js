document.getElementById('apiButton').addEventListener('click', function () {
    // Example API (you can replace this with the API you want to use)
    const apiUrl = 'http://localhost:8080/task/';

    fetch(apiUrl,
        {method:"GET", 
          headers: {
            "Content-Type": 'application/x-www-form-urlencoded'
          }})
        .then(response => response.json())
        .then(data => {
            // Handle the response and display it
            document.getElementById('response').innerHTML = `
                <h3>API Response:</h3>
                <pre>${JSON.stringify(data, null, 2)}</pre>
            `;
        })
        .catch(error => {
            document.getElementById('response').innerHTML = `
                <p style="color: red;">Error: ${error}</p>
            `;
        });
});
