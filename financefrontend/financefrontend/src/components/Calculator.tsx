import React, { useState } from 'react';
import '../Caclulator.css';

const Calculator: React.FC = () => {
    const [display, setDisplay] = useState<string>('');

    const handleClick = (value: string) => {
        setDisplay(prev => prev + value);
    };

    const calculate = () => {
        try {
            // Warning: Using eval() is not recommended for untrusted input.
            // In production, consider using a proper math expression parser.
            const result = eval(display);
            setDisplay(result.toString());
        } catch (error) {
            setDisplay('Error');
        }
    };

    const clearDisplay = () => {
        setDisplay('');
    };

    return (
        <div className="calculator">
            <input
                type="text"
                value={display}
                readOnly
                className="calculator-display"
            />
            <div className="calculator-buttons">
                <button onClick={() => handleClick('7')}>7</button>
                <button onClick={() => handleClick('8')}>8</button>
                <button onClick={() => handleClick('9')}>9</button>
                <button onClick={() => handleClick('/')}>/</button>
                <button onClick={() => handleClick('4')}>4</button>
                <button onClick={() => handleClick('5')}>5</button>
                <button onClick={() => handleClick('6')}>6</button>
                <button onClick={() => handleClick('*')}>*</button>
                <button onClick={() => handleClick('1')}>1</button>
                <button onClick={() => handleClick('2')}>2</button>
                <button onClick={() => handleClick('3')}>3</button>
                <button onClick={() => handleClick('-')}>-</button>
                <button onClick={() => handleClick('0')}>0</button>
                <button onClick={() => handleClick('.')}>.</button>
                <button onClick={calculate}>=</button>
                <button onClick={() => handleClick('+')}>+</button>
                <button onClick={clearDisplay} className="calculator-clear">C</button>
            </div>
        </div>
    );
};

export default Calculator;
