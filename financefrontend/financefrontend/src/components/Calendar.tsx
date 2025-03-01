// src/components/CalendarComponent.tsx
import React, { useEffect, useState } from 'react';
import ReactCalendar from 'react-calendar';
import 'react-calendar/dist/Calendar.css';
import '../App.css';

interface CalendarProps {
    selectedDate: Date;
    onDateChange: (date: Date) => void;
}

interface Transaction {
    id: number;
    amount: number;
    description: string;
    date: string;
}

const CalendarComponent: React.FC<CalendarProps> = ({ selectedDate, onDateChange }) => {
    const [transactions, setTransactions] = useState<Transaction[]>([]);

    useEffect(() => {
        // Format selected date as YYYY-MM-DD
        const formattedDate = selectedDate.toISOString().split('T')[0];
        // Ensure your fetch URL points to your backend (adjust port if needed)
        fetch(`http://localhost:8081/api/transactions?date=${formattedDate}`)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Error fetching transactions');
                }
                return response.json();
            })
            .then((data: Transaction[]) => setTransactions(data))
            .catch(error => console.error('Error:', error));
    }, [selectedDate]);

    return (
        <div className="calendar-container">
            <ReactCalendar
                onChange={(date: Date) => onDateChange(date)}
                value={selectedDate}
            />
            <div className="transactions-list">
                <h3>Transactions on {selectedDate.toDateString()}</h3>
                {transactions.length > 0 ? (
                    <ul>
                        {transactions.map(tx => (
                            <li key={tx.id}>
                                {tx.description}: ${tx.amount}
                            </li>
                        ))}
                    </ul>
                ) : (
                    <p>No transactions for this date.</p>
                )}
            </div>
        </div>
    );
};

export default CalendarComponent;
