import React, { Component } from 'react';

class EventExamples extends Component {
    constructor(props) {
        super(props);
        this.state = {
            count: 0
        };
    }

    increment = () => {
        this.setState({ count: this.state.count + 1 });
        this.sayHello();
    }

    decrement = () => {
        this.setState({ count: this.state.count - 1 });
    }

    sayHello = () => {
        alert("Hello! Welcome to Event Handling in React.");
    }

    sayWelcome = (message) => {
        alert(`Welcome ${message}`);
    }

    handleClick = (event) => {
        alert("I was clicked! This is a Synthetic Event.");
        console.log(event);
    }

    render() {
        return (
            <div>
                <h2>Counter: {this.state.count}</h2>
                <button onClick={this.increment}>Increment</button>
                <button onClick={this.decrement}>Decrement</button>
                <br /><br />
                <button onClick={() => this.sayWelcome("User")}>Say Welcome</button>
                <br /><br />
                <button onClick={this.handleClick}>OnPress Synthetic Event</button>
            </div>
        );
    }
}

export default EventExamples;
