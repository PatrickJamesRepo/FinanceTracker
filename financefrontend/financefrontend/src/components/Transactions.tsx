import React, { useState, useEffect } from 'react';
import axios from 'axios';

export type Transaction = {
    id: number;
    amount: number;
    date: string;
    description: string;
    category: string;
    tags: string[];
    isRecurring: boolean;
};

const Transactions: React.FC = () => {
    const [transactions, setTransactions] = useState<Transaction[]>([]);
    const [form, setForm] = useState({
        amount: '',
        date: '',
        description: '',
        category: 'Income',
        tags: '',
        isRecurring: false,
    });
    const [error, setError] = useState<string>('');

    useEffect(() => {
        fetchTransactions();
    }, []);

    const fetchTransactions = () => {
        axios
            .get('/api/transactions')
            .then((response) => {
                console.log('Response data:', response.data);
                const data = response.data;
                // Check if data is an array, otherwise try to extract the array from a property
                if (Array.isArray(data)) {
                    setTransactions(data);
                } else if (data.transactions && Array.isArray(data.transactions)) {
                    setTransactions(data.transactions);
                } else {
                    console.error('Unexpected response format:', data);
                    setTransactions([]);
                }
            })
            .catch((err) => console.error('Error fetching transactions:', err));
    };

    const handleSubmit = (e: React.FormEvent) => {
        e.preventDefault();
        setError('');
        const payload = {
            amount: parseFloat(form.amount),
            date: form.date,
            description: form.description,
            category: form.category,
            tags: form.tags.split(',').map((tag) => tag.trim()).filter((tag) => tag !== ''),
            isRecurring: form.isRecurring,
        };
        axios
            .post('/api/transactions/', payload)
            .then((response) => {
                setTransactions([...transactions, response.data]);
                setForm({
                    amount: '',
                    date: '',
                    description: '',
                    category: 'Income',
                    tags: '',
                    isRecurring: false,
                });
            })
            .catch((err) => {
                console.error('Error creating transaction:', err);
                setError('Failed to add transaction');
            });
    };

    return (
        <div className="card">
            <h2>Record Income & Expenses</h2>
            {error && <p style={{ color: 'red' }}>{error}</p>}
            <form onSubmit={handleSubmit}>
                {/* form groups here */}
            </form>
            <h3>Transactions List</h3>
            {transactions.length === 0 ? (
                <p>No transactions recorded yet.</p>
            ) : (
                <ul>
                    {transactions.map((t) => (
                        <li key={t.id}>
                            <strong>{t.date}</strong>: {t.description} - ${t.amount.toFixed(2)} ({t.category})
                            {t.isRecurring && ' [Recurring]'}
                            {t.tags.length > 0 && ` Tags: ${t.tags.join(', ')}`}
                        </li>
                    ))}
                </ul>
            )}
        </div>
    );
};

export default Transactions;
