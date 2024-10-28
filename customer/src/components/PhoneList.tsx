    import { useState, useEffect } from 'react';
    import { Phone, Plus, Trash2 } from 'lucide-react';
    import { ContactInfo, OptionType } from '../types';
    
    interface PhoneListProps {
        userId: string | undefined;
        phones: ContactInfo[];
        onPhonesUpdate: (phones: ContactInfo[]) => void;
    }

    export function PhoneList({ userId, phones, onPhonesUpdate }: PhoneListProps) {
    const [newPhone, setNewPhone] = useState('');
    const [selectedPhoneType, setSelectedPhoneType] = useState<number | undefined>(undefined);
    const [phoneTypes, setPhoneTypes] = useState<OptionType[]>([]);

    useEffect(() => {
    const fetchPhoneTypes = async () => {
        try {
        const response = await fetch('http://localhost:8081/api/Phone_Type');
        if (response.ok) {
            const data = await response.json();
            setPhoneTypes(data);
        } else {
            console.error('Error fetching phone types:', response.statusText);
        }
        } catch (error) {
        console.error('Error fetching phone types:', error);
        }
    };
    fetchPhoneTypes();
    }, []);

    const handleAddPhone = async (e: React.FormEvent) => {
    e.preventDefault();
    if (!newPhone || selectedPhoneType === undefined) return;

    const token = localStorage.getItem('token');
    try {
        const response = await fetch('http://localhost:8081/api/Phone', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`,
        },
        body: JSON.stringify({
            number: newPhone,
            phoneType: { id: selectedPhoneType },
            person: { id: userId },
        }),
        });

        if (response.ok) {
        const addedPhone = await response.json();
        onPhonesUpdate([...phones, addedPhone]);
        setNewPhone('');
        setSelectedPhoneType(undefined);
        } else {
        console.error('Error adding phone:', response.statusText);
        }
    } catch (error) {
        console.error('Error adding phone:', error);
    }
    };

    const handleRemovePhone = async (phoneId: number) => {
    const token = localStorage.getItem('token');
    try {
        const response = await fetch(`http://localhost:8081/api/Phone/${phoneId}`, {
        method: 'DELETE',
        headers: {
            'Authorization': `Bearer ${token}`,
        },
        });

        if (response.ok) {
        onPhonesUpdate(phones.filter(phone => phone.id !== phoneId));
        } else {
        console.error('Error removing phone:', response.statusText);
        }
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
                <span className="ml-2">{phone.number} ({phone.phoneType.name})</span>
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
        <form onSubmit={handleAddPhone} className="flex flex-row space-y-2 gap-5 ">
        <input
            type="tel"
            value={newPhone}
            onChange={(e) => setNewPhone(e.target.value)}
            className="border p-2 rounded w-3/4"
            placeholder="Add new phone number"
        />
        <select
            value={selectedPhoneType}
            onChange={(e) => setSelectedPhoneType(Number(e.target.value))}
            className="border p-2 rounded w-2/10"
        >
            <option value="">Select phone type</option>
            {phoneTypes.map(type => (
            <option key={type.id} value={type.id}>
                {type.name}
            </option>
            ))}
        </select>
        <button
            type="submit"
            className="bg-black text-white px-4 py-2 rounded mt-2 w-1/10 hover:bg-blue-600 transition-colors"
        >
            <Plus size={18} />
        </button>
        </form>
    </div>
    );
    }
