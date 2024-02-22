function addNewEmployee() {
    var form = document.getElementById('employeeForm');
    var formData = {};

    for(let element of form.elements){
        if(element.name){
        formData[element.name] = element.value;}
    }

    var jsonData = JSON.stringify(formData);

    fetch('http://localhost:8080/api/v1/new/employee', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: jsonData
    })
       .then(response => response.json())
       .then(response => console.log(JSON.stringify(response)))
}

function updateEmployee() {
    var form = document.getElementById('employeeForm');
    var formData = {};
    var employeeId = parseInt(form.elements['employeeId'].value);
    var url = 'http://localhost:8080/api/v1/update/employee/' + employeeId;

    for(let element of form.elements){
        if(element.name && !element.readOnly){
        formData[element.name] = element.value;}
    }

    var jsonData = JSON.stringify(formData);

    fetch(url, {
            method: 'PATCH',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: jsonData
    })
    .then(response => response.json())
    .then(response => window.location.reload())
}
