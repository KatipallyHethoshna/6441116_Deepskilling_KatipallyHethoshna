import React, { useState } from 'react';
import './App.css';
import CurrencyConvertor from './CurrencyConvertor';

function App() {
  const [count, setCount] = useState(0);

  const increment = () => {
    setCount(count + 1);
    sayHello();
  };

  const decrement = () => {
    setCount(count - 1);
  };

  const sayHello = () => {
    console.log('Hello! This is a static message.');
  };

  const sayWelcome = (message) => {
    alert(message);
  };

  const handlePress = () => {
    alert('I was clicked');
  };

  return (
    <div className="App">
      <h1>React Events Lab</h1>

      <h2>Counter: {count}</h2>
      <button onClick={increment}>Increment</button>
      <button onClick={decrement}>Decrement</button>

      <br /><br />

      <button onClick={() => sayWelcome('Welcome!')}>Say Welcome</button>
      <br /><br />

      <button onClick={handlePress}>OnPress (Synthetic Event)</button>

      <hr />
      <CurrencyConvertor />
    </div>
  );
}

export default App;
