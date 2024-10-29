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

  export interface ServiceStatus {
	id: number;
	orderDate: string;
  }
  
  export interface ServiceOrderDetail {
	service: {
	  name: string;
	  
	};
	branch: {
	  name: string;
	};
	serviceValue: number
  }
  
  export interface ServiceDetails {
	items: ServiceOrderDetail[];
  }
  export interface OrderDetail {
	id: number;
	serviceBranch: {
	  service: {
		name: string;
	  };
	  branch: {
		name: string;
	  };
	  serviceValue: number;
	};
	serviceOrder: {
	  orderDate: string;
	};
  }