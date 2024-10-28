export type UserInfo = {
	"id": string,
	"name": string,
	"lastname": string,
	"username": string,
	"password": string,
	"repeatedPassword": string,
	"personType": {
		"id": number
	},
	"branch": {
		"id": number
	}
}
export type UserUpdate = {
	"id": string,
	"name": string,
	"lastname": string,
	"username": string,
	"date":string
	"password": string,
	"repeatedPassword": string,
	"role": {
		"id": number
	},
	"personType": {
		"id": number
	},
	"branch": {
		"id": number
	}
}

export type ContactInfo = {
	
		"id": number,
		"number": string,
		"phoneType": {
			"id": number,
			"name": string
		},
		"person": {
			"id": string,
	}
}
export type EmailInfo = {
		"id": number,
		"mail": string,
		"emailType": {
			"id": number,
			"name": string
		},
		"person": {
			"id": string,
		}
}

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

export type OptionType = {
	"id" : number,
	"name":string
}

export type FormDataType=  {
	id: string;
	name: string;
	lastname: string;
	username: string;
	emails: { email: string; emailType: string }[];
	password: string;
	repeatedPassword: string;
	personType: string;
	phones: { phone: string; phoneType: string }[];
  }