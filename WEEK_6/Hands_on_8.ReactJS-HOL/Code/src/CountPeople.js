import React from 'react';

class CountPeople extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            entryCount: 0,
            exitCount: 0
        };
    }

    updateEntry = () => {
        this.setState({ entryCount: this.state.entryCount + 1 });
    };

    updateExit = () => {
        this.setState({ exitCount: this.state.exitCount + 1 });
    };

    render() {
        return (
            <div>
                <h2>Mall Entry/Exit Counter</h2>
                <p>People Entered: {this.state.entryCount}</p>
                <p>People Exited: {this.state.exitCount}</p>
                <button onClick={this.updateEntry}>Login</button>
                <button onClick={this.updateExit}>Exit</button>
            </div>
        );
    }
}

export default CountPeople;
