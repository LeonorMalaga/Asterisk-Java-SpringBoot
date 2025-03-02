document.getElementById('listExtensions').addEventListener('click', function () {
    // Example API (you can replace this with the API you want to use)
    const apiUrl = 'http://localhost:8080/api/ExtensionsStateList';
    const requestBody = {
        ip: "192.168.1.143",
        port: 5038,
        user: "leonor",
        pass: "leonor2021"
    };
    fetch(apiUrl,
        {
        method: 'POST', // Change method to POST
        headers: {
            'Content-Type': 'application/json' // Ensure JSON format
        },
        body: JSON.stringify(requestBody) // Convert JS object to JSON string
       })
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
