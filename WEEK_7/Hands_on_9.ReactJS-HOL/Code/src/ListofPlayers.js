import React from 'react';

function ListofPlayers() {
    const players = [
        { name: 'Virat Kohli', score: 90 },
        { name: 'Rohit Sharma', score: 85 },
        { name: 'KL Rahul', score: 65 },
        { name: 'Shreyas Iyer', score: 50 },
        { name: 'Hardik Pandya', score: 75 },
        { name: 'Ravindra Jadeja', score: 40 },
        { name: 'Jasprit Bumrah', score: 55 },
        { name: 'Shubman Gill', score: 95 },
        { name: 'Mohammed Shami', score: 60 },
        { name: 'Yuzvendra Chahal', score: 70 },
        { name: 'Suryakumar Yadav', score: 88 }
    ];

    // Filter players with scores below 70 using arrow function
    const filteredPlayers = players.filter(player => player.score < 70);

    return (
        <div>
            <h2>Players with Score Below 70</h2>
            <ul>
                {filteredPlayers.map((player, index) => (
                    <li key={index}>{player.name} - {player.score}</li>
                ))}
            </ul>
        </div>
    );
}

export default ListofPlayers;
