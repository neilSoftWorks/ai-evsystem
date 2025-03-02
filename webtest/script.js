function loadApplications() {
    fetch('http://localhost:8080/api/applications')
        .then(response => {
            console.log('Response status:', response.status);
            return response.json();
        })
        .then(data => {
            console.log('Data received:', data);
            const tableBody = document.getElementById('applicationsTable').getElementsByTagName('tbody')[0];
            tableBody.innerHTML = ''; // Clear existing rows

            data.forEach(application => {
                console.log('Processing application:', application);
                let row = tableBody.insertRow();
                let cell0 = row.insertCell(0);
                let cell1 = row.insertCell(1);
                let cell2 = row.insertCell(2);
                let cell3 = row.insertCell(3);
                let cell4 = row.insertCell(4);
                let cell5 = row.insertCell(5);
                let cell6 = row.insertCell(6);

                console.log('Inserting ID:', application.id);
                cell0.textContent = application.id;
                console.log('Inserting Business Name:', application.name);
                cell1.textContent = application.name;
                console.log('Inserting Contact Details:', application.contactDetails);
                cell2.textContent = application.contactDetails;
                console.log('Inserting Phone Number:', application.phoneNumber);
                cell3.textContent = application.phoneNumber;
                console.log('Inserting Email Address:', application.emailAddress);
                cell4.textContent = application.emailAddress;
                console.log('Inserting Address:', application.address);
                cell5.textContent = application.address;
                cell6.innerHTML = `<button onclick="editApplication(${application.id})">Edit</button>`;
            });
        })
        .catch(error => console.error('Error loading applications:', error));
}

function editApplication(id) {
    window.location.href = `edit.html?id=${id}`;
}

loadApplications();

const urlParams = new URLSearchParams(window.location.search);
const id = urlParams.get('id');

function fetchBusinessDetails() {
    if (id) {
        fetch(`http://localhost:8080/api/applications/${id}`)
            .then(response => {
                console.log('Fetch details response status:', response.status);
                return response.json();
            })
            .then(data => {
                console.log('Fetched details:', data);
                document.getElementById('businessName').value = data.name;
                document.getElementById('contactDetails').value = data.contactDetails;
                document.getElementById('phoneNumber').value = data.phoneNumber;
                document.getElementById('emailAddress').value = data.emailAddress;
                document.getElementById('address').value = data.address;
            })
            .catch(error => console.error('Error fetching business details:', error));
    }
}

function updateBusinessDetails() {
    const name = document.getElementById('businessName').value;
    const contactDetails = document.getElementById('contactDetails').value;
    const phoneNumber = document.getElementById('phoneNumber').value;
    const emailAddress = document.getElementById('emailAddress').value;
    const address = document.getElementById('address').value;

    fetch(`http://localhost:8080/api/applications/${id}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ name, contactDetails, phoneNumber, emailAddress, address })
    })
    .then(response => {
        console.log('Update response status:', response.status);
        if (response.ok) {
            alert('Business details updated successfully.');
            window.location.href = 'index.html';
        } else {
            alert('Failed to update business details.');
        }
    })
    .catch(error => console.error('Error updating business details:', error));
}

function submitBusinessDetails() {
    const name = document.getElementById('businessName').value;
    const contactDetails = document.getElementById('contactDetails').value;
    const phoneNumber = document.getElementById('phoneNumber').value;
    const emailAddress = document.getElementById('emailAddress').value;
    const address = document.getElementById('address').value;

    fetch('http://localhost:8080/api/applications', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ name, contactDetails, phoneNumber, emailAddress, address })
    })
    .then(response => {
        console.log('Submit response status:', response.status);
        if (response.ok) {
            alert('Business details submitted successfully.');
            window.location.href = 'index.html';
        } else {
            alert('Failed to submit business details.');
        }
    })
    .catch(error => console.error('Error submitting business details:', error));
}

function submitOrUpdateBusinessDetails() {
    if (id) {
        updateBusinessDetails();
    } else {
        submitBusinessDetails();
    }
}

function deleteBusinessDetails() {
    if (confirm("Are you sure you want to delete this business detail?")) {
        fetch(`http://localhost:8080/api/applications/${id}`, {
            method: 'DELETE'
        })
        .then(response => {
            console.log('Delete response status:', response.status);
            if (response.ok) {
                alert('Business details deleted successfully.');
                window.location.href = 'index.html';
            } else {
                alert('Failed to delete business details.');
            }
        })
        .catch(error => console.error('Error deleting business details:', error));
    }
}

fetchBusinessDetails();
