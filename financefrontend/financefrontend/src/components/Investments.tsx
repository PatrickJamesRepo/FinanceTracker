import React, { useState, useEffect } from 'react';
import axios from 'axios';

export type Investment = {
    id: number;
    name: string;
    amount: number;
    type: string;
};

const Investments: React.FC = () => {
    const [investments, setInvestments] = useState<Investment[]>([]);
    const [form, setForm] = useState({
        name: '',
        amount: '',
        type: 'Stock',
    });
    const [error, setError] = useState<string>('');

    // Fetch investments when component mounts
    useEffect(() => {
        fetchInvestments();
    }, []);

    const fetchInvestments = () => {
        axios
            .get('/api/investments/')
            .then((response) => {
                console.log('Investments response:', response.data);
                const data = response.data;
                // Ensure that data is an array
                if (Array.isArray(data)) {
                    setInvestments(data);
                } else if (data.investments && Array.isArray(data.investments)) {
                    setInvestments(data.investments);
                } else {
                    console.error('Unexpected investments response format:', data);
                    setInvestments([]);
                }
            })
            .catch((err) => console.error('Error fetching investments:', err));
    };

    const handleSubmit = (e: React.FormEvent) => {
        e.preventDefault();
        setError('');
        const payload = {
            name: form.name,
            amount: parseFloat(form.amount),
            type: form.type,
        };
        axios
            .post('/api/investments', payload)
            .then((response) => {
                // Assuming the response returns the created investment
                setInvestments([...investments, response.data]);
                setForm({ name: '', amount: '', type: 'Stock' });
            })
            .catch((err) => {
                console.error('Error creating investment:', err);
                setError('Failed to add investment');
            });
    };

    return (
        <div className="card">
            <h2>Investment Tracker</h2>
            {error && <p style={{ color: 'red' }}>{error}</p>}
            <form onSubmit={handleSubmit}>
                <div className="form-group">
                    <label>Name:</label>
                    <input
                        type="text"
                        value={form.name}
                        onChange={(e) => setForm({ ...form, name: e.target.value })}
                        required
                    />
                </div>
                <div className="form-group">
                    <label>Amount:</label>
                    <input
                        type="number"
                        step="0.01"
                        value={form.amount}
                        onChange={(e) => setForm({ ...form, amount: e.target.value })}
                        required
                    />
                </div>
                <div className="form-group">
                    <label>Type:</label>
                    <select
                        value={form.type}
                        onChange={(e) => setForm({ ...form, type: e.target.value })}
                    >
                        <option value="Stock">Stock</option>
                        <option value="Bond">Bond</option>
                        <option value="Crypto">Crypto</option>
                        <option value="Real Estate">Real Estate</option>
                    </select>
                </div>
                <button className="submit-btn" type="submit">
                    Add Investment
                </button>
            </form>
            <h3>Investments List</h3>
            {investments.length === 0 ? (
                <p>No investments tracked yet.</p>
            ) : (
                <ul>
                    {investments.map((inv) => (
                        <li key={inv.id}>
                            {inv.name} ({inv.type}) - ${inv.amount.toFixed(2)}
                        </li>
                    ))}
                </ul>
            )}
        </div>
    );
};

export default Investments;
