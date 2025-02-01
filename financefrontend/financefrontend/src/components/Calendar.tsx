// src/components/CalendarComponent.tsx
import React from 'react';
import ReactCalendar from 'react-calendar'; // Aliased the import to ReactCalendar
import 'react-calendar/dist/Calendar.css';
import '../App.css'; // Assuming your App.css contains styles like .calendar-container

const CalendarComponent: React.FC = () => {
    return (
        <div className="calendar-container">
            <ReactCalendar />
        </div>
    );
};

export default CalendarComponent;
