import React, { Component } from 'react';

class CurrencyConvertor extends Component {
    constructor(props) {
        super(props);
        this.state = {
            rupees: '',
            euro: ''
        };
    }

    handleChange = (event) => {
        this.setState({ rupees: event.target.value });
    }

    handleSubmit = (event) => {
        event.preventDefault(); // Prevent page reload
        const euroValue = (this.state.rupees / 80).toFixed(2); // Assuming 1 Euro = ₹80
        this.setState({ euro: euroValue });
    }

    render() {
        return (
            <div>
                <h2>Currency Convertor</h2>
                <form onSubmit={this.handleSubmit}>
                    <label>Enter amount in Rupees: </label>
                    <input type="number" value={this.state.rupees} onChange={this.handleChange} />
                    <button type="submit">Convert</button>
                </form>
                {this.state.euro && (
                    <h3>{this.state.rupees} Rupees = {this.state.euro} Euros</h3>
                )}
            </div>
        );
    }
}

export default CurrencyConvertor;
