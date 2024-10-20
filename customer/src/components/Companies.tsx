import React from 'react';

const companiesData = [
  { name: "TechCorp", logo: "https://source.unsplash.com/random/200x200?tech" },
  { name: "DataSystems", logo: "https://source.unsplash.com/random/200x200?data" },
  { name: "SecureNet", logo: "https://source.unsplash.com/random/200x200?security" },
  { name: "CloudWave", logo: "https://source.unsplash.com/random/200x200?cloud" },
  { name: "InnovateTech", logo: "https://source.unsplash.com/random/200x200?innovation" },
  { name: "GlobalIT", logo: "https://source.unsplash.com/random/200x200?global" },
];

function Companies() {
  return (
    <div className="bg-gray-100 py-16">
      <div className="container mx-auto px-4">
        <h1 className="text-4xl font-bold text-center mb-12">Our Partner Companies</h1>
        <div className="grid grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-8">
          {companiesData.map((company, index) => (
            <div key={index} className="bg-white p-6 rounded-lg shadow-md text-center">
              <img src={company.logo} alt={company.name} className="w-32 h-32 mx-auto mb-4 rounded-full" />
              <h3 className="text-xl font-semibold">{company.name}</h3>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
}

export default Companies;