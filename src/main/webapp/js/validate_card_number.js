let attemptsCounter = 0;

const luhnAlgorithm = (ccNumber) => {
    const length = ccNumber.length;
    let count = 0;

    if(length % 2 == 0)
    {
        for(let i = 0; i < length; i++)
        {
            let currentDigit = parseInt(ccNumber[i]);
            if (i % 2 == 0) 
            {
                if ((currentDigit *= 2) > 9)
                {
                    let trailingNumber = currentDigit % 10;
                    let firstNumber = parseInt(currentDigit / 10)
                    currentDigit = firstNumber + trailingNumber;
                }
            }
            
            count += currentDigit;
        }
    }
    else {

        for(let i = length - 1 ; i >= 0; i--)
        {
            let currentDigit = parseInt(ccNumber[i]);
            if ((i - 1) % 2 == 0) 
            {
                if ((currentDigit *= 2) > 9)
                {
                    let trailingNumber = currentDigit % 10;
                    let firstNumber = parseInt(currentDigit / 10);
                    currentDigit = firstNumber + trailingNumber;
                }
            }
            
            count += currentDigit;
        }
    }

    return (count % 10) === 0;
}

const checkCC = () => {
    const elCCNumber = document.getElementById('credit-card-number');
    const elCCValidation = document.getElementById('credit-card-validator');
    const elAttempts = document.getElementById('attempts');
    let message = "";

    // Calls the Luhn algorithm. Fails if the Luhn algorithm returns false.
    if( luhnAlgorithm(elCCNumber.value) )
        message = "Well, it looks like that worked! Your CC is valid!";
    else
        message = "CC verification failed :(";
    
    // Initialize the display textbox with content.
    elCCValidation.textContent = message;
    // Clear the credit card field.
    elCCNumber.value = null;

    elAttempts.textContent = ++attemptsCounter;
};

const validateBtn = document.getElementById("validate-btn");
validateBtn.addEventListener('click', (e) => {
	e.preventDefault()
	checkCC();
})



