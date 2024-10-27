import { useState } from 'react';
import { Edit } from 'lucide-react';
import { UserInfo } from '../types';

interface CustomerDataProps {
  user: UserInfo;
  onUserUpdate: (user: UserInfo) => void;
}

export function CustomerData({ user, onUserUpdate }: CustomerDataProps) {
  const [isEditing, setIsEditing] = useState(false);

  const UserView = () => (
    <div className="bg-gray-100 p-6 rounded-lg mb-8">
      <div className="flex justify-between items-center mb-4">
        <h2 className="text-2xl font-semibold">Personal Information</h2>
        <button
          onClick={() => setIsEditing(true)}
          className="flex items-center bg-black text-white px-4 py-2 rounded hover:bg-blue-600 transition-colors"
        >
          <Edit className="mr-2" size={18} />
          Edit
        </button>
      </div>
      <div className="grid grid-cols-2 gap-4">
        <p><strong>id:</strong> {user.id}</p>
        <p><strong>Name:</strong> {user.name}</p>
        <p><strong>Last Name:</strong> {user.lastname}</p>
        <p><strong>Username:</strong> {user.username}</p>
      </div>
    </div>
  );

  const UserEdit = () => {
    const [editedUser, setEditedUser] = useState<Pick<UserInfo, 'name' | 'lastname'>>({
      name: user.name,
      lastname: user.lastname,
    });

    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
      setEditedUser({ ...editedUser, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e: React.FormEvent) => {
      e.preventDefault();
      try {
        // Asegúrate de usar la URL correcta para tu API
        const response = await fetch(`http://localhost:8081/api/Person/${user.id}`, {
          method: 'PUT',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify(editedUser),
        });

        if (!response.ok) {
          throw new Error('Failed to update user');
        }

        const updatedUser = await response.json();
        onUserUpdate(updatedUser);
        setIsEditing(false);
      } catch (error) {
        console.error('Error updating user:', error);
      }
    };

    return (
      <form onSubmit={handleSubmit} className="bg-gray-100 p-6 rounded-lg mb-8">
        <div className="flex justify-between items-center mb-4">
          <h2 className="text-2xl font-semibold">Edit Personal Information</h2>
          <button
            type="button"
            onClick={() => setIsEditing(false)}
            className="flex items-center bg-gray-300 text-black px-4 py-2 rounded hover:bg-gray-400 transition-colors"
          >
            Cancel
          </button>
        </div>
        <div className="grid grid-cols-2 gap-4">
          <input
            type="text"
            name="name"
            value={editedUser.name}
            onChange={handleChange}
            className="border p-2 rounded"
            placeholder="Name"
            required
          />
          <input
            type="text"
            name="lastname"
            value={editedUser.lastname}
            onChange={handleChange}
            className="border p-2 rounded"
            placeholder="Last Name"
            required
          />
        </div>
        <button
          type="submit"
          className="mt-4 bg-black text-white px-4 py-2 rounded hover:bg-blue-600 transition-colors"
        >
          Save Changes
        </button>
      </form>
    );
  };

  return isEditing ? <UserEdit /> : <UserView />;
}
