export type UserInfo = {
    id: number;
    name: string;
    lastname: string;
    username: string;
    password: string;
	personType: number;
  };
  
  export type ContactInfo = {
    id: number;
    value: string;
  };
  
  export type ServiceStatus = {
    id: number;
    title: string;
    status: 'active' | 'pending' | 'inactive';
  };
  
  export type ServiceDetails = {
    id: number;
    description: string;
    startDate: string;
    endDate: string;
  };
  export type ServiceData ={ 
    "id": {
			"branchId": number,
			"serviceId": number
		},
		"service": {
			"id": number,
			"name": string,
			"requiresSupply": boolean
		},
		"branch": {
			"id": number,
			"name": string,
			"nit": string,
			"creationDate": string,
			"city": {
				"id": number,
				"name": string,
				"region": {
					"id": number,
					"name": string,
					"country": {
						"id": number,
						"name": string
					}
				}
			},
			"company": {
				"id": number,
				"name": string,
				"companyType": {
					"id": number,
					"description": string
				}
			}
		},
		"serviceValue":number
  }

export type CompanyData =  {
        "id": number,
        "name": string,
        "companyType": {
            "id": number,
            "description": string
}}
