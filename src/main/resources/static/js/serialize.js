function createEmployeeJSON() {
    var form = document.getElementById('employeeForm');
    var formData = {};

    for (var i = 0; i < form.elements.length; i++) {
        var element = form.elements[i];
        if (element.name) {
            formData[element.name] = element.value;
        }
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
