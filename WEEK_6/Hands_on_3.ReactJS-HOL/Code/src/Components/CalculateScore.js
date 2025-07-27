import React from 'react';
import '../Stylesheets/mystyle.css';

function CalculateScore(props) {
    const averageScore = (props.total / props.goal).toFixed(2);

    return (
        <div className="score-container">
            <h2>Student Score Calculator</h2>
            <p>Name: {props.name}</p>
            <p>School: {props.school}</p>
            <p>Total Score: {props.total}</p>
            <p>Goal: {props.goal}</p>
            <p>Average Score: {averageScore}</p>
        </div>
    );
}

export default CalculateScore;
