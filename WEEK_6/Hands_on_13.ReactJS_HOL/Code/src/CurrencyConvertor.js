import React, { useState } from 'react';

function CurrencyConvertor() {
    const [rupees, setRupees] = useState('');
    const [euros, setEuros] = useState('');

    const handleSubmit = () => {
        const euroValue = parseFloat(rupees) / 90; // Assuming 1 Euro = ₹90
        setEuros(euroValue.toFixed(2));
    };

    return (
        <div>
            <h2>Currency Convertor (INR to EURO)</h2>
            <input
                type="text"
                value={rupees}
                onChange={(e) => setRupees(e.target.value)}
                placeholder="Enter amount in ₹"
            />
            <button onClick={handleSubmit}>Convert</button>
            <p>{rupees && `€${euros}`}</p>
        </div>
    );
}

export default CurrencyConvertor;
