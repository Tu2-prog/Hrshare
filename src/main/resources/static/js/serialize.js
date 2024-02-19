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
    console.log(jsonData); // You can do anything with jsonData, e.g., send it to the server via AJAX
}
