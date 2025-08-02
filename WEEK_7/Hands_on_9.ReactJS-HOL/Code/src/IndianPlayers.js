import React from 'react';

function IndianPlayers() {
    const players = ['Virat', 'Rohit', 'Gill', 'Rahul', 'Hardik', 'Jadeja'];

    // Destructuring Odd and Even players
    const oddTeam = players.filter((_, index) => index % 2 === 0);
    const evenTeam = players.filter((_, index) => index % 2 !== 0);

    // Merging T20 Players and Ranji Trophy Players using Spread Operator
    const T20Players = ['Kohli', 'Rohit', 'Surya'];
    const RanjiPlayers = ['Rahane', 'Pujara', 'Iyer'];

    const mergedPlayers = [...T20Players, ...RanjiPlayers];

    return (
        <div>
            <h2>Odd Team Players</h2>
            <ul>
                {oddTeam.map((player, index) => <li key={index}>{player}</li>)}
            </ul>

            <h2>Even Team Players</h2>
            <ul>
                {evenTeam.map((player, index) => <li key={index}>{player}</li>)}
            </ul>

            <h2>Merged Team Players (T20 + Ranji)</h2>
            <ul>
                {mergedPlayers.map((player, index) => <li key={index}>{player}</li>)}
            </ul>
        </div>
    );
}

export default IndianPlayers;
