import { useState, useEffect } from 'react';
import { CustomerData } from './CustomerData';
import { PhoneList } from './PhoneList';
import { EmailList } from './EmailList';
import { ServiceList } from './ServiceList';
import { UserUpdate, ContactInfo, ServiceStatus, EmailInfo } from '../types';
import { Link, useNavigate } from 'react-router-dom';

export default function Customer() {
const [user, setUser] = useState<UserUpdate | null>(null);
const [phones, setPhones] = useState<ContactInfo[]>([]);
const [emails, setEmails] = useState<EmailInfo[]>([]);
const [services, setServices] = useState<ServiceStatus[]>([]);
const [loading, setLoading] = useState(true);
const [userId, setUserId] = useState<string | undefined>();
const [error, setError] = useState<string | null>(null);
const navigate = useNavigate();

const LogOut = async () => {
    const token = localStorage.getItem('token');
    try {
    const response = await fetch('http://localhost:8081/auth/logout', {
        method: 'POST',
        headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${token}`,
        },
    });
    if (response.ok) {
        localStorage.removeItem('token');
        navigate('/login', { replace: true });
    } else {
        console.error('Error in logout:', response.statusText);
    }
    } catch (error) {
    console.error('Error during logout:', error);
    }
};

useEffect(() => {
    const fetchUserProfile = async () => {
    const token = localStorage.getItem('token');
    try {
        const response = await fetch('http://localhost:8081/auth/profile', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`,
        },
        });
        if (response.ok) {
        const userProfile = await response.json();
        setUserId(userProfile.id);
        setUser(userProfile);
        } else {
        console.error('Error fetching profile:', response.statusText);
        }
    } catch (error) {
        console.error('Error during fetch:', error);
    }
    };

    fetchUserProfile();
}, []);

useEffect(() => {
    const fetchData = async () => {
    const token = localStorage.getItem('token');
    if (!userId) return;

    try {
        const [phonesData, emailsData, servicesData] = await Promise.all([
        fetch(`http://localhost:8081/api/Phone/person/${userId}`, {
            headers: { 
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${token}` },
        }).then(res => res.json()),
        fetch(`http://localhost:8081/api/Email/person/${userId}`, {
            headers: { 
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${token}` },
        }).then(res => res.json()),
        fetch(`http://localhost:8081/api/Service_Order/Customer_${userId}`, {
            headers: { 'Content-Type': 'application/json',
                        'Authorization': `Bearer ${token}` },
        }).then(res => res.json())
        ]);

        setPhones(phonesData || []);
        setEmails(emailsData || []);
        setServices(servicesData || []);
    } catch (err) {
        console.error('Error fetching data:', err);
        setError('Failed to fetch user data.');
    } finally {
        setLoading(false);
    }
    };

    fetchData();
}, [userId]);

if (loading) {
    return (
    <div className="flex justify-center items-center h-screen">
        <div className="animate-spin rounded-full h-12 w-12 border-b-2 border-black"></div>
    </div>
    );
}

if (error || !user) {
    return (
    <div className="flex justify-center items-center h-screen text-red-600">
        {error || 'Failed to load user data'}
    </div>
    );
}

return (
    <div className="min-h-screen bg-white p-8 w-8/10">
    <h1 className="text-3xl font-bold mb-8">User Dashboard</h1>

    <CustomerData user={user} onUserUpdate={setUser} />

    <PhoneList userId={userId} phones={phones} onPhonesUpdate={setPhones} />

    <EmailList userId={userId} emails={emails} onEmailsUpdate={setEmails} />

    <ServiceList  services={services} />

    <button onClick={LogOut} className="text-blue-600 hover:underline">Log Out</button>
    </div>
);
}
