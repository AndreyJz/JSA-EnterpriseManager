import { useState, useEffect } from 'react';
import { Mail, Plus, Trash2 } from 'lucide-react';
import { EmailInfo, OptionType } from '../types';

interface EmailListProps {
  userId: string | undefined;
  emails: EmailInfo[];
  onEmailsUpdate: (emails: EmailInfo[]) => void;
}

export function EmailList({userId, emails, onEmailsUpdate }: EmailListProps) {
  const [newEmail, setNewEmail] = useState('');
  const [selectedEmailType, setSelectedEmailType] = useState<number | undefined>(undefined);
  const [emailTypes, setEmailTypes] = useState<OptionType[]>([]);

  useEffect(() => {
    const fetchEmailTypes = async () => {
      try {
        const response = await fetch('http://localhost:8081/api/Email_Type');
        if (response.ok) {
          const data = await response.json();
          setEmailTypes(data);
        } else {
          console.error('Error fetching email types:', response.statusText);
        }
      } catch (error) {
        console.error('Error fetching email types:', error);
      }
    };
    fetchEmailTypes();
  }, []);

  const handleAddEmail = async (e: React.FormEvent) => {
    e.preventDefault();
    if (!newEmail || selectedEmailType === undefined) return;

    try {
      const response = await fetch(`http://localhost:8081/api/Email`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${localStorage.getItem('token')}`,
        },
        body: JSON.stringify({
          mail: newEmail,
          emailType: { id: selectedEmailType },
          person: { id: userId },
        }),
      });

      if (response.ok) {
        const addedEmail = await response.json();
        onEmailsUpdate([...emails, addedEmail]);
        setNewEmail('');
        setSelectedEmailType(undefined);
      } else {
        console.error('Error adding email:', response.statusText);
      }
    } catch (error) {
      console.error('Error adding email:', error);
    }
  };

  const handleRemoveEmail = async (emailId: number) => {
    try {
      const response = await fetch(`http://localhost:8081/api/Email/${emailId}`, {
        method: 'DELETE',
        headers: {
          'Authorization': `Bearer ${localStorage.getItem('token')}`,
        },
      });

      if (response.ok) {
        onEmailsUpdate(emails.filter(email => email.id !== emailId));
      } else {
        console.error('Error removing email:', response.statusText);
      }
    } catch (error) {
      console.error('Error removing email:', error);
    }
  };

  return (
    <div className="bg-gray-100 p-6 rounded-lg mb-8">
      <h2 className="text-2xl font-semibold mb-4">Email Addresses</h2>
      <ul className="mb-4">
        {emails.map(email => (
          <li key={email.id} className="flex items-center justify-between mb-2">
            <span className="flex items-center">
              <Mail size={18} />
              <span className="ml-2">{email.mail}</span>
            </span>
            <button
              onClick={() => handleRemoveEmail(email.id)}
              className="bg-red-500 text-white p-1 rounded hover:bg-red-600 transition-colors"
            >
              <Trash2 size={18} />
            </button>
          </li>
        ))}
      </ul>
      <form onSubmit={handleAddEmail} className="flex">
        <input
          type="email"
          value={newEmail}
          onChange={(e) => setNewEmail(e.target.value)}
          className="border p-2 rounded flex-grow"
          placeholder="Add new email address"
        />
        <select
          value={selectedEmailType}
          onChange={(e) => setSelectedEmailType(Number(e.target.value))}
          className="border p-2 rounded ml-2"
        >
          <option value="" disabled>Select email type</option>
          {emailTypes.map(type => (
            <option key={type.id} value={type.id}>
              {type.name}
            </option>
          ))}
        </select>
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