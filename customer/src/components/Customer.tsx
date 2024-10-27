    import { useState, useEffect } from 'react';
    import { CustomerData } from './CustomerData';
    import { PhoneList } from './PhoneList';
    import { EmailList } from './EmailList';
    import { ServiceList } from './ServiceList';
    import { UserInfo, ContactInfo, ServiceStatus } from '../types';
import { Link } from 'react-router-dom';
import { User } from 'lucide-react';

    export default function Customer() {
    const [user, setUser] = useState<UserInfo | null>(null);
    const [phones, setPhones] = useState<ContactInfo[]>([]);
    const [emails, setEmails] = useState<ContactInfo[]>([]);
    const [services, setServices] = useState<ServiceStatus[]>([]);
    const [loading, setLoading] = useState(true);
    const [userId, setuserid] = useState();
    const [error, setError] = useState<string | null>(null);

    const LogOut = async () => {
        const token = localStorage.getItem('token');
        
        try {
        const response = await fetch('http://localhost:8081/logout', {
            method: 'POST',
            headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
            }
        });
    
        if (response.ok) {
            const data = await response.json(); // Procesar la respuesta del backend
            console.log(data.message); // Mostramos el mensaje de cierre exitoso en consola o actualizar el estado
            localStorage.removeItem('token'); // Eliminar el token del almacenamiento si el logout es exitoso
            <Link to="/login" className="hover:text-gray-300">
            <User className="h-6 w-6" />
            </Link>
        } else {
            console.error('Error in logout:', response.statusText);
        }
        } catch (error) {
        console.error('Error during logout:', error);
        }
    };
    
    useEffect(() => {

        const fetchUserProfile = async () => {
            const token = localStorage.getItem('token'); // Obtener el token del localStorage
            
            try {
            const response = await fetch('http://localhost:8081/auth/profile', {
                method: 'GET',
                headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${token}` // Enviar el token en el encabezado de autorización
                }
            });
            if (response.ok) {
                const userProfile = await response.json(); // Procesar la respuesta
                console.log('User Profile:', userProfile);
                setuserid(userProfile.id); 
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
        try {
            // Test URLs - Replace with your actual API endpoints
            const [phonesData, emailsData, servicesData] = await Promise.all([
            fetch(`http://localhost:8081/api/Phone/person/${userId}`).then(res => res.json()),
            fetch(`http://localhost:8081/api/Email/person${userId}`).then(res => res.json()),
            fetch(`http://localhost:8081/api/Service_Approval/${userId}`).then(res => res.json())
            ]);

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

        <button onClick={LogOut} className="text-blue-600 hover:underline"> Log Out</button>
        </div>
    );
    }