document.getElementById('apiButton').addEventListener('click', function () {
    // Example API (you can replace this with the API you want to use)
    const apiUrl = 'https://jsonplaceholder.typicode.com/todos/1';

    fetch(apiUrl)
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
