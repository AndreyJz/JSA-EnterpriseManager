import { useState } from 'react';
import { Phone, Plus, Trash2 } from 'lucide-react';
import { ContactInfo } from '../types';

interface PhoneListProps {
userId: string;
phones: ContactInfo[];
onPhonesUpdate: (phones: ContactInfo[]) => void;
}

export function PhoneList({ userId, phones, onPhonesUpdate }: PhoneListProps) {
const [newPhone, setNewPhone] = useState('');

const handleAddPhone = async (e: React.FormEvent) => {
    e.preventDefault();
    if (!newPhone) return;

    try {
    // Test URL - Replace with your actual API endpoint
    const response = await fetch(`http://localhost:8081/api/Phone`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ value: newPhone }),
    });
    const addedPhone = await response.json();
    onPhonesUpdate([...phones, addedPhone]);
    setNewPhone('');
    } catch (error) {
    console.error('Error adding phone:', error);
    }
};

const handleRemovePhone = async (phoneId: number) => {
    try {
    // Test URL - Replace with your actual API endpoint
    await fetch(`http://localhost:8081/api/Phone/${phoneId}`, {
        method: 'DELETE',
    });
    onPhonesUpdate(phones.filter(phone => phone.id !== phoneId));
    } catch (error) {
    console.error('Error removing phone:', error);
    }
};

return (
    <div className="bg-gray-100 p-6 rounded-lg mb-8">
    <h2 className="text-2xl font-semibold mb-4">Phone Numbers</h2>
    <ul className="mb-4">
        {phones.map(phone => (
        <li key={phone.id} className="flex items-center justify-between mb-2">
            <span className="flex items-center">
            <Phone size={18} />
            <span className="ml-2">{phone.value}</span>
            </span>
            <button
            onClick={() => handleRemovePhone(phone.id)}
            className="bg-red-500 text-white p-1 rounded hover:bg-red-600 transition-colors"
            >
            <Trash2 size={18} />
            </button>
        </li>
        ))}
    </ul>
    <form onSubmit={handleAddPhone} className="flex">
        <input
        type="tel"
        value={newPhone}
        onChange={(e) => setNewPhone(e.target.value)}
        className="border p-2 rounded flex-grow"
        placeholder="Add new phone number"
        />
        <button
        type="submit"
        className="bg-black text-white px-4 py-2 rounded ml-2 hover:bg-blue-600 transition-colors"
        >
        <Plus size={18} />
        </button>
    </form>
    </div>
);
}