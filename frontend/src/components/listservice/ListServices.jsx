    import React from 'react';
    import Service from '../service/Service';
    import './ListServices.css';


    const serviceData = {
    title: "Enterprise Server Solution",
    price: "4999",
    description: "High-performance server solution for large enterprises."
    };

    function ListService() {
    return (
        <div class = 'listServices'>
            <Service serviceData={serviceData} class = "cart"/>
            <Service serviceData={serviceData} class = "cart"/>
            <Service serviceData={serviceData} class = "cart"/>
            <Service serviceData={serviceData} class = "cart"/>
            <Service serviceData={serviceData} class = "cart"/>  
            <Service serviceData={serviceData} class = "cart"/>
        </div>
    );
    }

    export default ListService;
