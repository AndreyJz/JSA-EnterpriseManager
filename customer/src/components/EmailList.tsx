import { useState } from 'react';
import { Mail, Plus, Trash2 } from 'lucide-react';
import { ContactInfo } from '../types';

interface EmailListProps {
  userId: number;
  emails: ContactInfo[];
  onEmailsUpdate: (emails: ContactInfo[]) => void;
}

export function EmailList({ userId, emails, onEmailsUpdate }: EmailListProps) {
  const [newEmail, setNewEmail] = useState('');

  const handleAddEmail = async (e: React.FormEvent) => {
    e.preventDefault();
    if (!newEmail) return;

    try {
      // Test URL - Replace with your actual API endpoint
      const response = await fetch(`http://localhost:8081/api/Email/${userId}`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ value: newEmail }),
      });
      const addedEmail = await response.json();
      onEmailsUpdate([...emails, addedEmail]);
      setNewEmail('');
    } catch (error) {
      console.error('Error adding email:', error);
    }
  };

  const handleRemoveEmail = async (emailId: number) => {
    try {
      // Test URL - Replace with your actual API endpoint
      await fetch(`https://jsonplaceholder.typicode.com/users/${userId}/emails/${emailId}`, {
        method: 'DELETE',
      });
      onEmailsUpdate(emails.filter(email => email.id !== emailId));
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
              <span className="ml-2">{email.value}</span>
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