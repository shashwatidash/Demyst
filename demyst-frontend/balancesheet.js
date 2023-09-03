    
        const formDataObject = {};
        
        // Function to populate the formDataObject
        function populateFormDataObject() {
            const form = document.getElementById("loanForm");
            const formData = new FormData(form);

            // Add form data to formDataObject
            formData.forEach((value, key) => {
                formDataObject[key] = value;
            });
        }
        
        // Initialize the index variable outside the function
        let newIndex = 0;
        // Function to add more balance sheet items dynamically
        function addBalanceSheetItem() {
            const balanceSheetItemsContainer = document.getElementById("balanceSheetItemsContainer");
            const newBalanceSheetItemDiv = document.createElement("div");
            newBalanceSheetItemDiv.classList.add("balanceSheetItem");
            newBalanceSheetItemDiv.innerHTML = 
            `
                <input type="number" name="balanceSheetItems[${newIndex}].year" placeholder="Year">
                <input type="number" name="balanceSheetItems[${newIndex}].assetValue" placeholder="Asset Value">
                <input type="number" name="balanceSheetItems[${newIndex}].liabilitiesSum" placeholder="Liabilities Sum">
            `;
            
    
            balanceSheetItemsContainer.appendChild(newBalanceSheetItemDiv);
            newIndex++;
            console.log(`New Year Input (ID): year${newIndex}`);
            console.log(`New Asset Value Input (ID): assetValue${newIndex}`);
        }
        
        function postData() {
            // Create an empty payload object
            const payload = {};

            // Populate the payload object with user input values
            payload.name = document.getElementById("name").value;
            payload.email = document.getElementById("email").value;
            payload.accountingProvider = document.getElementById("dropdown1").value;
            payload.address = ""; // You can set this to an empty string or get it from user input
            payload.organisation = document.getElementById("dropdown3").value;
            payload.loanReason = document.getElementById("dropdown2").value;
            payload.loanAmount = document.getElementById("money").value;
            payload.yearOfEstablishment = document.getElementById("yearOfEstablishment").value;
            payload.totalCurrentAssets = document.getElementById("totalCurrentAssets").value;
            payload.shareholderEquity = document.getElementById("shareholderEquity").value;
            payload.annualIncome = document.getElementById("annualIncome").value;

            // Create an array for balanceSheetItems
            payload.balanceSheetItems = [];

            // Loop through dynamically added balanceSheetItem controls
            const balanceSheetItemsContainer = document.getElementsByClassName("balanceSheetItem");
            for (let i = 0; i < balanceSheetItemsContainer.length; i++) {

                const yearInput = balanceSheetItemsContainer[i].querySelector('input[name^="balanceSheetItems[' + i + '].year"]');
                const assetValueInput = balanceSheetItemsContainer[i].querySelector('input[name^="balanceSheetItems[' + i + '].assetValue"]');
                const liabilitiesSumInput = balanceSheetItemsContainer[i].querySelector('input[name^="balanceSheetItems[' + i + '].liabilitiesSum"]');
                
                // logging

                if (yearInput) {
                    const yearValue = yearInput.value;
                    console.log("Name input value:", yearValue);
                } else {
                    console.log("Name input element not found");
                }
                ///logging over


                // Create an object for each balance sheet item and add it to the array
                const balanceSheetItem = {
                    year: yearInput.value,
                    assetsValue: assetValueInput.value,
                    liabilitiesSum: liabilitiesSumInput.value, // You can set this to an empty string or get it from user input
                };

                payload.balanceSheetItems.push(balanceSheetItem);
            }

            // Convert the payload to JSON
            const payloadJSON = JSON.stringify(payload);

            fetch('http://localhost:8080/api/v1/saveBusinessDetails', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: payloadJSON, // Send the created payload as JSON
            })
            .then(response => {
                if (response.status === 200) {
                    return response.json(); // Assuming the response is JSON
                } else {
                    throw new Error('Server error');
                }
            })
            .then(data => {
                // Assuming the response is an integer
                document.getElementById('response').innerText = `Business ID: ${data}`;
            })
            .catch(error => {
                console.error('Error:', error);
            });
        }
        
        
        
        function createBalanceSheetTable(data) {
            const balanceSheetItems = data.balanceSheetItems; // Access the array of balance sheet items

            const tableContainer = document.getElementById("balanceSheetTableContainer");
            
                
            const table = document.createElement("table");
            table.classList.add("balanceSheetTable");
            // Add a CSS class to the table for styling (optional)
            table.classList.add("table-bordered");

            // Create table header
            const headerRow = table.insertRow(0);
            for (const key in balanceSheetItems[0]) {
                const headerCell = document.createElement("th");
                headerCell.textContent = key;
                headerRow.appendChild(headerCell);
            }

            // Create table rows and populate data
            balanceSheetItems.forEach((rowData, index) => {
                const row = table.insertRow(index + 1); // +1 to account for the header row
                for (const key in rowData) {
                    const cell = row.insertCell();
                    cell.textContent = rowData[key];
                }
            });

            // Append the table to the container
            tableContainer.innerHTML = ''; 
            tableContainer.appendChild(table);

        }

        
        // Function to get the balance sheet
        function getBalanceSheet() {
            const responseDiv = document.getElementById('response');
            const receivedInteger = parseInt(responseDiv.textContent.split(':')[1].trim());

            if (isNaN(receivedInteger)) {
                responseDiv.innerText = 'Please submit the form first to receive an integer.';
                return;
            }

            fetch(`http://localhost:8080/api/v1/${receivedInteger}/balanceSheet`)
                .then(response => {
                    if (response.status === 200) {
                        return response.json(); // Assuming the response is JSON
                    } else {
                        throw new Error('Server error');
                    }
                })
                .then(data => {
                    // Assuming the response is a balance sheet JSON object
                    //responseDiv.innerText = 'Received Balance Sheet: ' + JSON.stringify(data);
                    // Call the function to create and populate the table with the received data
                    createBalanceSheetTable(data);
                    
                    // Add "Get Assessment" button
                    const getAssessmentButton = document.createElement("button");
                    getAssessmentButton.textContent = "Get Assessment";
                    getAssessmentButton.addEventListener('click', function () {
                        event.preventDefault();
                        // Perform a GET request to get the assessment
                        fetch(`http://localhost:8080/api/v1/${receivedInteger}/assessment`)
                            .then(response => {
                                if (response.status === 200) {
                                    return response.json();
                                } else {
                                    throw new Error('Server error');
                                }
                            })
                            .then(assessmentData => {
                                // Display the assessment data or handle it as needed
                                const assessmentScoreElement = document.getElementById('assessmentScore');
                                assessmentScoreElement.textContent = `Assessment Score: ${assessmentData}`;
                                console.log('Assessment Value:', assessmentData);
                             
                                const loanApprovedButton = document.createElement("button");
                                loanApprovedButton.textContent = "Get Approved Loan";
                                loanApprovedButton.addEventListener('click', function () {
                                    
                                    event.preventDefault();
                                    // Perform a GET request to get the assessment
                                    fetch(`http://localhost:8080/api/v1/${receivedInteger}/result?preAssessment=${assessmentData}`)
                                        .then(response => {
                                            if (response.status === 200) {
                                                return response.json();
                                            } else {
                                                throw new Error('Server error');
                                            }
                                        })
                                        .then(approvedLoanData => {
                                            // Display the assessment data or handle it as needed
                                            const approvedLoanElement = document.getElementById('approvedLoan');
                                            approvedLoanElement.textContent = `Approved Loan: ${approvedLoanData}`;
                                            //console.log('Approved Loan:', approvedLoanData);
                                        })
                                        .catch(error => {
                                            console.error('Error:', error);
                                        });
                                    });
                                    // Append the "Get Assessment" button to the appropriate container
                                    const buttonContainer2 = document.getElementById('buttonContainer2');
                                    buttonContainer2.appendChild(loanApprovedButton);
                                    
                            })
                            .catch(error => {
                                console.error('Error:', error);
                            });
                    });
                    
                    // Append the "Get Assessment" button to the appropriate container
                    const buttonContainer = document.getElementById('buttonContainer');
                    buttonContainer.appendChild(getAssessmentButton);
                })
                .catch(error => {
                    console.error('Error:', error);
                });
        }
        
