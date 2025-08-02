import React from 'react';
import officeImage from './office.jpg'; // Add an image named 'office.jpg' in src folder

function OfficeSpace() {
    const office = {
        name: 'Corporate Hub',
        rent: 75000,
        address: 'MG Road, Bengaluru'
    };

    const offices = [
        { name: 'Workspace A', rent: 50000, address: 'Koramangala' },
        { name: 'Workspace B', rent: 85000, address: 'Whitefield' },
        { name: 'Workspace C', rent: 60000, address: 'Indiranagar' }
    ];

    const rentStyle = (rent) => ({
        color: rent < 60000 ? 'red' : 'green'
    });

    return (
        <div>
            <h1>Office Space Rental Portal</h1>
            <img src={officeImage} alt="Office Space" width="300" />

            <h2>Featured Office</h2>
            <p>Name: {office.name}</p>
            <p style={rentStyle(office.rent)}>Rent: {office.rent}</p>
            <p>Address: {office.address}</p>

            <h2>Available Office Spaces</h2>
            {offices.map((item, index) => (
                <div key={index}>
                    <p>Name: {item.name}</p>
                    <p style={rentStyle(item.rent)}>Rent: {item.rent}</p>
                    <p>Address: {item.address}</p>
                    <hr />
                </div>
            ))}
        </div>
    );
}

export default OfficeSpace;
