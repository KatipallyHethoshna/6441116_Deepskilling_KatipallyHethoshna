import React from 'react';
import ListofPlayers from './ListofPlayers';
import IndianPlayers from './IndianPlayers';

function App() {
  const flag = true; // Change this to false to switch component

  return (
    <div>
      {flag ? <ListofPlayers /> : <IndianPlayers />}
    </div>
  );
}

export default App;