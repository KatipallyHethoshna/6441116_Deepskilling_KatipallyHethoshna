import React from 'react';
import CohortDetails from './CohortDetails';

const App = () => {
  const cohorts = [
    {
      name: 'Cohort A',
      status: 'ongoing',
      startDate: '22-Feb-2022',
      endDate: '30-Jun-2022',
    },
    {
      name: 'Cohort B',
      status: 'completed',
      startDate: '10-Sep-2021',
      endDate: '15-Dec-2021',
    },
    {
      name: 'Cohort C',
      status: 'completed',
      startDate: '24-Dec-2021',
      endDate: '28-Apr-2022',
    },
  ];

  return (
    <div>
      <h1>Cohorts Details</h1>
      {cohorts.map((cohort, index) => (
        <CohortDetails key={index} cohort={cohort} />
      ))}
    </div>
  );
};

export default App;
