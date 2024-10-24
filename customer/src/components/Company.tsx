

import React from 'react';
import { CompanyData } from '../types';

interface CompanyProps {
    companyData: CompanyData
}
function Company({companyData}: CompanyProps) {
    return (
        <div key={companyData.id} className="w-60 h-32 bg-white p-6 rounded-lg shadow-md text-center flex-col justify-center items-center transition-transform duration-300 hover:bg-blue-600 hover:text-white hover:scale-105 ">
            <h1 className="text-2xl font-bold">{companyData.name}</h1>
            <h3 className="text-xl font-semibold">{companyData.companyType.description}</h3>
        </div>          
    );
}

export default Company;