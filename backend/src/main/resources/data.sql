-- CREACIÓN DE MODULOS
INSERT INTO modules (name, base_path) VALUES ('COUNTRY', '/api/Countries');
INSERT INTO modules (name, base_path) VALUES ('REGION', '/api/Regions');
INSERT INTO modules (name, base_path) VALUES ('CITY', '/api/Cities');
INSERT INTO modules (name, base_path) VALUES ('COMPANY_TYPE', '/api/Company_Type');
INSERT INTO modules (name, base_path) VALUES ('COMPANY', '/api/Companies');
INSERT INTO modules (name, base_path) VALUES ('BRANCH', '/api/Branches');
INSERT INTO modules (name, base_path) VALUES ('SERVICE', '/api/Services');
INSERT INTO modules (name, base_path) VALUES ('SERVICE_BRANCH', '/api/Service_Branches');
INSERT INTO modules (name, base_path) VALUES ('SERVICE_APPROVAL', '/api/Service_Approval');
INSERT INTO modules (name, base_path) VALUES ('SUPPLY', '/api/Supply');
INSERT INTO modules (name, base_path) VALUES ('SUPPLY_SERVICE', '/api/Supply_Service');
INSERT INTO modules (name, base_path) VALUES ('PERSON', '/api/Person');
INSERT INTO modules (name, base_path) VALUES ('PERSON_TYPE', '/api/Person_Type');
INSERT INTO modules (name, base_path) VALUES ('PHONE', '/api/Phone');
INSERT INTO modules (name, base_path) VALUES ('PHONE_TYPE', '/api/Phone_Type');
INSERT INTO modules (name, base_path) VALUES ('EMAIL', '/api/Email');
INSERT INTO modules (name, base_path) VALUES ('EMAIL_TYPE', '/api/Email_Type');
INSERT INTO modules (name, base_path) VALUES ('PERSON_SUPPLY', '/api/Person_Supply');
INSERT INTO modules (name, base_path) VALUES ('ORDER_DETAIL', '/api/Order_Details');
INSERT INTO modules (name, base_path) VALUES ('ORDER_STATUS', '/api/Order_Status');
INSERT INTO modules (name, base_path) VALUES ('SERVICE_ORDER', '/api/Service_Order');
INSERT INTO modules (name, base_path) VALUES ('WORK_ORDER', '/api/Work_Orders');
INSERT INTO modules (name, base_path) VALUES ('WORK_ORDER_DETAIL', '/api/Work Order_Detail');
INSERT INTO modules (name, base_path) VALUES ('WORK_ORDER_DETAIL_STATUS', '/api/Work_Detail_Status');
INSERT INTO modules (name, base_path) VALUES ('APPROVAL_STATUS', '/api/Approval_Status');
INSERT INTO modules (name, base_path) VALUES ('AUTH', '/auth');
INSERT INTO modules (name, base_path) VALUES ('ROLE', '/api/Roles');
INSERT INTO modules (name, base_path) VALUES ('PAYMENT', '/api/Payment');

-- CREACIÓN DE OPERACIONES
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_ALL_COUNTRIES','', 'GET', false, 1);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_ONE_COUNTRY','/[0-9]*', 'GET', false, 1);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('CREATE_ONE_COUNTRY','', 'POST', false, 1);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('UPDATE_ONE_COUNTRY','/[0-9]*', 'PUT', false, 1);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('DISABLE_ONE_COUNTRY','/[0-9]*', 'DELETE', false, 1);

INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_ALL_REGIONS','', 'GET', false, 2);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_ONE_REGION','/[0-9]*', 'GET', false, 2);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('CREATE_ONE_REGION','', 'POST', false, 2);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('UPDATE_ONE_REGION','/[0-9]*', 'PUT', false, 2);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('DISABLE_ONE_REGION','/[0-9]*', 'DELETE', false, 2);

INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_ALL_CITIES','', 'GET', false, 3);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_ONE_CITY','/[0-9]*', 'GET', false, 3);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('CREATE_ONE_CITY','', 'POST', false, 3);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('UPDATE_ONE_CITY','/[0-9]*', 'PUT', false, 3);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('DISABLE_ONE_CITY','/[0-9]*', 'DELETE', false, 3);

INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_ALL_COMPANY_TYPES','', 'GET', false, 4);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_ONE_COMPANY_TYPE','/[0-9]*', 'GET', false, 4);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('CREATE_ONE_COMPANY_TYPE','', 'POST', false, 4);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('UPDATE_ONE_COMPANY_TYPE','/[0-9]*', 'PUT', false, 4);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('DISABLE_ONE_COMPANY_TYPE','/[0-9]*', 'DELETE', false, 4);

INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_ALL_COMPANIES','', 'GET', true, 5);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_ONE_COMPANY','/[0-9]*', 'GET', false, 5);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('CREATE_ONE_COMPANY','', 'POST', false, 5);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('UPDATE_ONE_COMPANY','/[0-9]*', 'PUT', false, 5);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('DISABLE_ONE_COMPANY','/[0-9]*', 'DELETE', false, 5);

INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_ALL_BRANCHES','', 'GET', true, 6);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_ONE_BRANCH','/[0-9]*', 'GET', false, 6);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('CREATE_ONE_BRANCH','', 'POST', false, 6);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('UPDATE_ONE_BRANCH','/[0-9]*', 'PUT', false, 6);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('DISABLE_ONE_BRANCH','/[0-9]*', 'DELETE', false, 6);

INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_ALL_SERVICES','', 'GET', true, 7);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_ONE_SERVICE','/[0-9]*', 'GET', false, 7);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('CREATE_ONE_SERVICE','', 'POST', false, 7);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('UPDATE_ONE_SERVICE','/[0-9]*', 'PUT', false, 7);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('DISABLE_ONE_SERVICE','/[0-9]*', 'DELETE', false, 7);

INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_ALL_SERVICE_BRANCHES','', 'GET', true, 8);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_ONE_SERVICE_BRANCH','/[0-9]*', 'GET', false, 8);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('CREATE_ONE_SERVICE_BRANCH','', 'POST', false, 8);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('UPDATE_ONE_SERVICE_BRANCH','/[0-9]*', 'PUT', false, 8);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('DISABLE_ONE_SERVICE_BRANCH','/[0-9]*', 'DELETE', false, 8);

INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_ALL_SERVICE_APPROVALS','', 'GET', false, 9);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_ONE_SERVICE_APPROVAL','/[0-9]*', 'GET', false, 9);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('CREATE_ONE_SERVICE_APPROVAL','', 'POST', false, 9);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('UPDATE_ONE_SERVICE_APPROVAL','/[0-9]*', 'PUT', false, 9);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('DISABLE_ONE_SERVICE_APPROVAL','/[0-9]*', 'DELETE', false, 9);

INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_ALL_SUPPLIES','', 'GET', true, 10);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_ONE_SUPPLY','/[0-9]*', 'GET', true, 10);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('CREATE_ONE_SUPPLY','', 'POST', false, 10);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('UPDATE_ONE_SUPPLY','/[0-9]*', 'PUT', false, 10);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('DISABLE_ONE_SUPPLY','/[0-9]*', 'DELETE', false, 10);

INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_ALL_SUPPLY_SERVICES','', 'GET', true, 11);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_ONE_SUPPLY_SERVICE','/[0-9]*', 'GET', false, 11);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('CREATE_ONE_SUPPLY_SERVICE','', 'POST', false, 11);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('UPDATE_ONE_SUPPLY_SERVICE','/[0-9]*', 'PUT', false, 11);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('DISABLE_ONE_SUPPLY_SERVICE','/[0-9]*', 'DELETE', false, 11);

INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_ALL_PEOPLE','', 'GET', false, 12);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_ONE_PERSON','/.*', 'GET', false, 12);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('CREATE_ONE_PERSON','', 'POST', true, 12);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('UPDATE_ONE_PERSON','/.*', 'PUT', false, 12);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('DISABLE_ONE_PERSON','/.*', 'DELETE', false, 12);

INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_ALL_PERSON_TYPES','', 'GET', true, 13);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_ONE_PERSON_TYPE','/[0-9]*', 'GET', false, 13);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('CREATE_ONE_PERSON_TYPE','', 'POST', false, 13);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('UPDATE_ONE_PERSON_TYPE','/[0-9]*', 'PUT', false, 13);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('DISABLE_ONE_PERSON_TYPE','/[0-9]*', 'DELETE', false, 13);

INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_ALL_PHONES','', 'GET', false, 14);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_ONE_PHONE','/[0-9]*', 'GET', false, 14);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('CREATE_ONE_PHONE','', 'POST', true, 14);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('UPDATE_ONE_PHONE','/[0-9]*', 'PUT', false, 14);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('DISABLE_ONE_PHONE','/[0-9]*', 'DELETE', false, 14);

INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_ALL_PHONE_TYPES','', 'GET', true, 15);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_ONE_PHONE_TYPE','/[0-9]*', 'GET', false, 15);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('CREATE_ONE_PHONE_TYPE','', 'POST', false, 15);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('UPDATE_ONE_PHONE_TYPE','/[0-9]*', 'PUT', false, 15);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('DISABLE_ONE_PHONE_TYPE','/[0-9]*', 'DELETE', false, 15);

INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_ALL_EMAILS','', 'GET', false, 16);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_ONE_EMAIL','/[0-9]*', 'GET', false, 16);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('CREATE_ONE_EMAIL','', 'POST', true, 16);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('UPDATE_ONE_EMAIL','/[0-9]*', 'PUT', false, 16);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('DISABLE_ONE_EMAIL','/[0-9]*', 'DELETE', false, 16);

INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_ALL_EMAIL_TYPES','', 'GET', true, 17);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_ONE_EMAIL_TYPE','/[0-9]*', 'GET', false, 17);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('CREATE_ONE_EMAIL_TYPE','', 'POST', false, 17);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('UPDATE_ONE_EMAIL_TYPE','/[0-9]*', 'PUT', false, 17);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('DISABLE_ONE_EMAIL_TYPE','/[0-9]*', 'DELETE', false, 17);

INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_ALL_PERSON_SUPPLIES','', 'GET', false, 18);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_ONE_PERSON_SUPPLY','/[0-9]*', 'GET', false, 18);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('CREATE_ONE_PERSON_SUPPLY','', 'POST', false, 18);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('UPDATE_ONE_PERSON_SUPPLY','/[0-9]*', 'PUT', false, 18);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('DISABLE_ONE_PERSON_SUPPLY','/[0-9]*', 'DELETE', false, 18);

INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_ALL_ORDER_DETAILS','', 'GET', false, 19);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_ONE_ORDER_DETAIL','/[0-9]*', 'GET', false, 19);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('CREATE_ONE_ORDER_DETAIL','', 'POST', false, 19);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('UPDATE_ONE_ORDER_DETAIL','/[0-9]*', 'PUT', false, 19);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('DISABLE_ONE_ORDER_DETAIL','/[0-9]*', 'DELETE', false, 19);

INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_ALL_ORDER_STATUSES','', 'GET', false, 20);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_ONE_ORDER_STATUS','/[0-9]*', 'GET', false, 20);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('CREATE_ONE_ORDER_STATUS','', 'POST', false, 20);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('UPDATE_ONE_ORDER_STATUS','/[0-9]*', 'PUT', false, 20);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('DISABLE_ONE_ORDER_STATUS','/[0-9]*', 'DELETE', false, 20);

INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_ALL_SERVICE_ORDERS','', 'GET', false, 21);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_ONE_SERVICE_ORDER','/[0-9]*', 'GET', false, 21);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('CREATE_ONE_SERVICE_ORDER','', 'POST', false, 21);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('UPDATE_ONE_SERVICE_ORDER','/[0-9]*', 'PUT', false, 21);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('DISABLE_ONE_SERVICE_ORDER','/[0-9]*', 'DELETE', false, 21);

INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_ALL_WORK_ORDERS','', 'GET', false, 22);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_ONE_WORK_ORDER','/[0-9]*', 'GET', false, 22);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('CREATE_ONE_WORK_ORDER','', 'POST', false, 22);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('UPDATE_ONE_WORK_ORDER','/[0-9]*', 'PUT', false, 22);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('DISABLE_ONE_WORK_ORDER','/[0-9]*', 'DELETE', false, 22);

INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_ALL_WORK_ORDER_DETAILS','', 'GET', false, 23);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_ONE_WORK_ORDER_DETAIL','/[0-9]*', 'GET', false, 23);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('CREATE_ONE_WORK_ORDER_DETAIL','', 'POST', false, 23);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('UPDATE_ONE_WORK_ORDER_DETAIL','/[0-9]*', 'PUT', false, 23);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('DISABLE_ONE_WORK_ORDER_DETAIL','/[0-9]*', 'DELETE', false, 23);

INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_ALL_WORK_ORDER_DETAIL_STATUSES','', 'GET', false, 24);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_ONE_WORK_ORDER_DETAIL_STATUS','/[0-9]*', 'GET', false, 24);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('CREATE_ONE_WORK_ORDER_DETAIL_STATUS','', 'POST', false, 24);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('UPDATE_ONE_WORK_ORDER_DETAIL_STATUS','/[0-9]*', 'PUT', false, 24);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('DISABLE_ONE_WORK_ORDER_DETAIL_STATUS','/[0-9]*', 'DELETE', false, 24);

INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_ALL_APPROVAL_STATUSES','', 'GET', false, 25);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_ONE_APPROVAL_STATUS','/[0-9]*', 'GET', false, 25);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('CREATE_ONE_APPROVAL_STATUS','', 'POST', false, 25);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('UPDATE_ONE_APPROVAL_STATUS','/[0-9]*', 'PUT', false, 25);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('DISABLE_ONE_APPROVAL_STATUS','/[0-9]*', 'DELETE', false, 25);

INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('REGISTER_ONE_CUSTOMER','/customer', 'POST', true, 12);

INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('AUTHENTICATE','/login', 'POST', true, 26);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('VALIDATE-TOKEN','/validate-token', 'GET', true, 26);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_MY_PROFILE','/profile','GET', true, 26);

INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_ALL_SUPPLIERS', '/Suppliers', 'GET', false, 12);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_ALL_CUSTOMERS', '/Customers', 'GET', false, 12);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_ALL_EMPLOYEES', '/Employees', 'GET', false, 12);

INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_WORK_ORDER_DETAILS_BY_EMPLOYEE', '/Employee_[0-9]*', 'GET', false, 23);

INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_SERVICE_ORDERS_BY_EMPLOYEE','/Employee_[0-9]*', 'GET', false, 21);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_SERVICE_ORDERS_BY_CUSTOMER','/Customer_[0-9]*', 'GET', false, 21);

INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_SERVICES_WITHOUT_BRANCH','/Without_Branch', 'GET', false, 7);

INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_PERSON_PHONES','/person/.*', 'GET', false, 14);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_PERSON_EMAILS','/person/.*', 'GET', false, 16);

INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('UPDATE_STATUS','/Status_[0-9]*', 'PUT', false, 9);

INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_ORDER_DETAILS_BY_SERVICE_ORDER', '/ServiceOrder_[0-9]*', 'GET', false, 19);

INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('AUTHENTICATE','/logout', 'POST', true, 26);

INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_ROLES','', 'GET', true, 27);

INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('CREATE_PAYMENT_INTENT','/create-payment-intent', 'POST', false, 28);

-- CREACIÓN DE ROLES
INSERT INTO roles (name) VALUES ('CUSTOMER');
INSERT INTO roles (name) VALUES ('ADMINISTRATOR');
INSERT INTO roles (name) VALUES ('EMPLOYEE');
INSERT INTO roles (name) VALUES ('MARKETING');
INSERT INTO roles (name) VALUES ('SUPPLIER');
INSERT INTO roles (name) VALUES ('SERVICE TRACKING');
INSERT INTO roles (name) VALUES ('HUMAN RESOURCES');
INSERT INTO roles (name) VALUES ('STORAGE´S ADMIN');

-- CREACIÓN DE PERMISOS
INSERT INTO granted_permissions (role_id, operation_id) VALUES (1, 59); -- UPDATE Person
INSERT INTO granted_permissions (role_id, operation_id) VALUES (1, 138); -- UPDATE Email
INSERT INTO granted_permissions (role_id, operation_id) VALUES (1, 70); -- UPDATE Email
INSERT INTO granted_permissions (role_id, operation_id) VALUES (1, 68); -- CREATE Email
INSERT INTO granted_permissions (role_id, operation_id) VALUES (1, 137); -- UPDATE Phone
INSERT INTO granted_permissions (role_id, operation_id) VALUES (1, 80); -- UPDATE Phone
INSERT INTO granted_permissions (role_id, operation_id) VALUES (1, 78); -- CREATE Phone
INSERT INTO granted_permissions (role_id, operation_id) VALUES (1, 135); -- Service ORDER person where id
INSERT INTO granted_permissions (role_id, operation_id) VALUES (1, 140); -- ORDER DETAIL BY SERVICE ORDER
INSERT INTO granted_permissions (role_id, operation_id) VALUES (1, 139); -- update APPROVAL STATUS
INSERT INTO granted_permissions (role_id, operation_id) VALUES (1, 103); -- Service Order
INSERT INTO granted_permissions (role_id, operation_id) VALUES (1, 121); -- Approval status
INSERT INTO granted_permissions (role_id, operation_id) VALUES (1, 143); -- Purchase Intent

INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 1);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 2);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 3);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 4);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 5);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 6);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 7);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 8);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 9);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 10);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 11);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 12);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 13);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 14);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 15);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 16);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 17);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 18);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 19);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 20);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 21);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 22);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 23);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 24);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 25);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 26);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 27);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 28);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 29);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 30);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 31);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 32);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 33);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 34);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 35);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 36);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 37);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 38);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 39);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 40);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 41);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 42);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 43);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 44);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 45);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 46);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 47);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 48);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 49);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 50);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 51);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 52);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 53);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 54);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 55);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 56);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 57);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 58);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 59);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 60);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 61);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 62);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 63);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 64);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 65);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 66);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 67);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 68);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 69);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 70);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 71);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 72);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 73);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 74);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 75);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 76);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 77);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 78);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 79);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 80);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 81);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 82);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 83);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 84);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 85);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 86);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 87);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 88);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 89);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 90);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 91);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 92);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 93);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 94);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 95);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 96);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 97);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 98);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 99);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 100);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 101);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 102);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 103);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 104);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 105);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 106);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 107);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 108);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 109);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 110);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 111);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 112);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 113);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 114);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 115);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 116);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 117);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 118);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 119);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 120);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 121);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 122);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 123);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 124);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 125);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 126);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 127);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 128);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 129);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 130);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 131);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 132);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 133);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 134);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 135);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 136);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 137);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 138);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 139);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 140);

INSERT INTO granted_permissions (role_id, operation_id) VALUES (3, 134); -- ORDER SERVICE employee where id
INSERT INTO granted_permissions (role_id, operation_id) VALUES (3, 140); -- ORDER DETAILS BY SERVICE ORDER
INSERT INTO granted_permissions (role_id, operation_id) VALUES (1, 139); -- update APPROVAL STATUS
-- INSERT INTO granted_permissions (role_id, operation_id) VALUES (3, 101); -- Service Order
INSERT INTO granted_permissions (role_id, operation_id) VALUES (3, 103); -- Create Service Order
INSERT INTO granted_permissions (role_id, operation_id) VALUES (3, 121); -- Approval status
INSERT INTO granted_permissions (role_id, operation_id) VALUES (3, 133); -- WorkOrderDetail employee where id
INSERT INTO granted_permissions (role_id, operation_id) VALUES (3, 112);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (3, 114);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (3, 116);

INSERT INTO granted_permissions (role_id, operation_id) VALUES (4, 131); -- ALL P
INSERT INTO granted_permissions (role_id, operation_id) VALUES (4, 57); -- ONE P

INSERT INTO granted_permissions (role_id, operation_id) VALUES (5, 86); -- ALL P_S
INSERT INTO granted_permissions (role_id, operation_id) VALUES (5, 87); -- ONE P_S
-- Hipotético Person-Supply-Status

INSERT INTO granted_permissions (role_id, operation_id) VALUES (6, 136); -- Services without branch
INSERT INTO granted_permissions (role_id, operation_id) VALUES (6, 32);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (6, 34);

INSERT INTO granted_permissions (role_id, operation_id) VALUES (7, 132); -- ALL Of Employees
INSERT INTO granted_permissions (role_id, operation_id) VALUES (7, 57);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (7, 58);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (7, 59);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (7, 60);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (7, 101); -- Service Order
INSERT INTO granted_permissions (role_id, operation_id) VALUES (7, 102);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (7, 104);

INSERT INTO granted_permissions (role_id, operation_id) VALUES (8, 86); -- Person Supply
INSERT INTO granted_permissions (role_id, operation_id) VALUES (8, 87); -- Person Supply
INSERT INTO granted_permissions (role_id, operation_id) VALUES (8, 88); -- Person Supply
INSERT INTO granted_permissions (role_id, operation_id) VALUES (8, 89); -- Person Supply
INSERT INTO granted_permissions (role_id, operation_id) VALUES (8, 90); -- Person Supply
INSERT INTO granted_permissions (role_id, operation_id) VALUES (8, 130); -- ALL Of role supplier
INSERT INTO granted_permissions (role_id, operation_id) VALUES (8, 57);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (8, 101); -- Service Order
INSERT INTO granted_permissions (role_id, operation_id) VALUES (8, 102); -- Service Order
INSERT INTO granted_permissions (role_id, operation_id) VALUES (8, 104); -- Service Order

-- Tabla company_types
INSERT INTO company_types (description) VALUES
                                            ('Healthcare'),
                                            ('Finance'),
                                            ('Education');

-- Tabla approval_status
INSERT INTO approval_status (name) VALUES
                                       ('Pending'),
                                       ('Approved'),
                                       ('Rejected');

-- Tabla person_type
INSERT INTO person_type (name) VALUES
                                   ('Natural'),
                                   ('Juridical'),
                                   ('Other');

-- Tabla countries
INSERT INTO countries (name) VALUES
                                 ('Colombia'),
                                 ('Brazil'),
                                 ('España');

-- Tabla email_type
INSERT INTO email_type (name) VALUES
                                  ('Personal'),
                                  ('Work'),
                                  ('Support');

-- Tabla order_status
INSERT INTO order_status (name) VALUES
                                    ('Processing'),
                                    ('Shipped'),
                                    ('Delivered');

-- Tabla phone_type
INSERT INTO phone_type (name) VALUES
                                  ('Mobile'),
                                  ('Landline'),
                                  ('Fax');

-- Tabla work_order_detail_status
INSERT INTO work_order_detail_status (name) VALUES
                                                ('In Progress'),
                                                ('Completed'),
                                                ('Cancelled');


-- Insertando regiones (depende de countries)
INSERT INTO regions (country_id, name) VALUES
                                           (1, 'Santander'),
                                           (2, 'Rio de Janeiro'),
                                           (3, 'Cataluña');

-- Insertando ciudades (depende de regions)
INSERT INTO cities (region_id, name) VALUES
                                         (1, 'Bucaramanga'),
                                         (2, 'Rio de Janeiro'),
                                         (3, 'Barcelona');

-- Insertando compañías (depende de company_types)
INSERT INTO companies (company_type_id, name) VALUES
                                                  (1, 'TechCorp'),
                                                  (2, 'BuildCo'),
                                                  (1, 'DevSolutions'),
                                                  (2, 'JSA');

-- Insertando sucursales (depende de cities y companies)
INSERT INTO branches (city_id, company_id, creation_date, name, nit) VALUES
                                                                         (1, 1, NOW(), 'Cañaveral', '1234567890'),
                                                                         (2, 2, NOW(), 'Ipanema', '0987654321'),
                                                                         (3, 1, NOW(), 'Barcelona´s Branch', '1122334455'),
                                                                         (1, 4, NOW(), 'Parque San Pio', '6677889900');

-- Insertando personas (depende de branches y person_type)
INSERT INTO person (branch_id, person_type_id, lastname, name, password, date, id, role_id, username) VALUES
                                                                                                          (1, 1, 'Doe', 'John', 'hashed_password_1', NOW(), 'P001', 1, 'john_doe'),
                                                                                                          (4, 2, 'Pardo', 'Johlver', '$2a$10$pYR/g.p6TJgreH.HLv4JyeNjFLBcydyUQFjRa24S9T2xZr5GVgPba', NOW(), 'ADMIN01', 2, 'the_killer'),
                                                                                                          (3, 3, 'Brown', 'Charlie', 'hashed_password_3', NOW(), 'P003', 1, 'charlie_brown');

-- Insertando correos (depende de person y email_type)
INSERT INTO email (email_type_id, mail, person_id) VALUES
                                                       (1, 'john.doe@example.com', 'P001'),
                                                       (2, 'jjpardo@gmail.com', 'ADMIN01'),
                                                       (3, 'charlie.brown@support.com', 'P003');

-- Insertando teléfonos (depende de person y phone_type)
INSERT INTO phone (phone_type_id, number, person_id) VALUES
                                                         (1, '123-456-7890', 'P001'),
                                                         (2, '098-765-4321', 'ADMIN01'),
                                                         (1, '555-555-5555', 'P003');

-- Insertando servicios (no depende de otras tablas)
INSERT INTO services (requires_supply, name) VALUES
                                                 (0, 'Web Development'),
                                                 (1, 'IT Support'),
                                                 (1, 'Cloud Hosting');

-- Insertando suministros (no depende de otras tablas)
INSERT INTO supply (price, stock, stock_max, stock_min, barcode, name) VALUES
                                                                           (100.50, 10, 20, 5, 'ABC123', 'Laptop'),
                                                                           (50.75, 30, 50, 10, 'DEF456', 'Keyboard'),
                                                                           (150.00, 15, 25, 8, 'GHI789', 'Monitor');

-- Insertando servicios en sucursales (depende de branches y services)
INSERT INTO service_branches (service_value, branch_id, service_id) VALUES
                                                                        (100.00, 1, 1),  -- Web Development en Branch 1
                                                                        (150.00, 2, 2),  -- IT Support en Branch 2
                                                                        (200.00, 3, 3);  -- Cloud Hosting en Branch 3

-- Insertando suministros para servicios (depende de service_branches y supply)
INSERT INTO supply_service (quantity, service_branches_branch_id, service_branches_service_id, supply_id) VALUES
                                                                                                              (3, 1, 1, 1),  -- 3 Laptops para Web Development en Branch 1
                                                                                                              (2, 2, 2, 2),  -- 2 Keyboards para IT Support en Branch 2
                                                                                                              (5, 3, 3, 3);  -- 5 Monitors para Cloud Hosting en Branch 3

-- Insertando órdenes de servicio (depende de order_status, person)
INSERT INTO service_order (order_status_id, customer_id, employee_id, order_date) VALUES
                                                                                      (1, 'P001', 'P003', '2024-06-01 16:00:00'),  -- Orden 1: Cliente P001, Empleado P002
                                                                                      (2, 'P001', 'P003', '2024-07-02 17:00:00'),  -- Orden 2: Cliente P002, Empleado P003
                                                                                      (3, 'P003', 'P001', '2024-07-03 18:00:00');  -- Orden 3: Cliente P003, Empleado P001

-- Insertando órdenes de trabajo (depende de service_order)
INSERT INTO work_orders (service_order_id, work_order_num, assign_date) VALUES
                                                                            (1, 'WO001', '2024-08-01 19:30:00'),
                                                                            (2, 'WO002', '2024-08-02 20:30:00'),
                                                                            (3, 'WO003', '2024-08-03 21:30:00');

-- Insertando detalles de órdenes de trabajo (depende de work_orders, service_branches, work_order_detail_status, person)
INSERT INTO work_order_detail (service_branch_branch_id, service_branch_service_id, work_order_detail_status_id, work_order_id, date, employee_id) VALUES
                                                                                                                                                       (1, 1, 1, 1, '2024-08-01 19:00:00', 'P001'),
                                                                                                                                                       (2, 2, 2, 2, '2024-08-02 20:00:00', 'P001'),
                                                                                                                                                       (3, 3, 3, 3, '2024-08-03 21:00:00', 'P003');

-- Insertando aprobaciones de servicio (depende de work_order_detail y approval_status)
INSERT INTO service_approval (approval_status_id, service_branch_branch_id, service_branch_service_id, work_order_id, report, solution) VALUES
                                                                                                                                            (1, 1, 1, 1, 'Initial inspection completed', 'Replace defective parts'),
                                                                                                                                            (2, 2, 2, 2, 'System upgrade completed', 'Optimize software configuration'),
                                                                                                                                            (3, 3, 3, 3, 'Issue resolved', 'Replaced motherboard');

-- Tabla person_supply
INSERT INTO person_supply (quantity, supply_id, person_id) VALUES
                                                               (10, 1, 'P001'),
                                                               (5, 2, 'P003'),
                                                               (20, 3, 'P003');

-- Tabla order_details
INSERT INTO order_details (service_value, id, service_branch_branch_id, service_branch_service_id, service_order_id) VALUES
                                                                                                                         (50.00, 1, 1, 1, 1),
                                                                                                                         (75.00, 2, 2, 2, 2),
                                                                                                                         (100.00, 3, 3, 3, 3);

