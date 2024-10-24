    import { useState, useEffect } from 'react';
    import { CustomerData } from './CustomerData';
    import { PhoneList } from './PhoneList';
    import { EmailList } from './EmailList';
    import { ServiceList } from './ServiceList';
    import { UserInfo, ContactInfo, ServiceStatus } from '../types';

    export default function Customer() {
    const [user, setUser] = useState<UserInfo | null>(null);
    const [phones, setPhones] = useState<ContactInfo[]>([]);
    const [emails, setEmails] = useState<ContactInfo[]>([]);
    const [services, setServices] = useState<ServiceStatus[]>([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState<string | null>(null);

    const userId = 1; // In a real app, this would come from authentication

    useEffect(() => {
        const fetchData = async () => {
        try {
            // Test URLs - Replace with your actual API endpoints
            const [userData, phonesData, emailsData, servicesData] = await Promise.all([
            fetch(`https://jsonplaceholder.typicode.com/users/${userId}`).then(res => res.json()),
            fetch(`https://jsonplaceholder.typicode.com/users/${userId}/phones`).then(res => res.json()),
            fetch(`https://jsonplaceholder.typicode.com/users/${userId}/emails`).then(res => res.json()),
            fetch(`https://jsonplaceholder.typicode.com/users/${userId}/services`).then(res => res.json())
            ]);

            setUser(userData);
            setPhones(phonesData || []); // Fallback for test API
            setEmails(emailsData || []); // Fallback for test API
            setServices(servicesData || []); // Fallback for test API
        } catch (err) {
            setError('Failed to load user data');
            console.error('Error fetching data:', err);
        } finally {
            setLoading(false);
        }
        };

        fetchData();
    }, []);

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
        <div className="min-h-screen bg-white p-8">
        <h1 className="text-3xl font-bold mb-8">User Dashboard</h1>
        
        <CustomerData 
            user={user} 
            onUserUpdate={setUser}
        />

        <PhoneList 
            userId={userId}
            phones={phones} 
            onPhonesUpdate={setPhones}
        />

        <EmailList 
            userId={userId}
            emails={emails} 
            onEmailsUpdate={setEmails}
        />

        <ServiceList 
            userId={userId}
            services={services}
        />
        </div>
    );
    }