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

-- CREACIÓN DE OPERACIONES
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_ALL_COUNTRIES','', 'GET', false, 1);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_ONE_COUNTRY','/[0-9]*', 'GET', false, 1);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('CREATE_ONE_COUNTRY','', 'POST', false, 1);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('UPDATE_ONE_COUNTRY','/[0-9]*', 'PUT', false, 1);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('DISABLE_ONE_COUNTRY','/[0-9]*', 'PUT', false, 1);

INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_ALL_REGIONS','', 'GET', false, 2);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_ONE_REGION','/[0-9]*', 'GET', false, 2);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('CREATE_ONE_REGION','', 'POST', false, 2);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('UPDATE_ONE_REGION','/[0-9]*', 'PUT', false, 2);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('DISABLE_ONE_REGION','/[0-9]*', 'PUT', false, 2);

INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_ALL_CITIES','', 'GET', false, 3);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_ONE_CITY','/[0-9]*', 'GET', false, 3);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('CREATE_ONE_CITY','', 'POST', false, 3);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('UPDATE_ONE_CITY','/[0-9]*', 'PUT', false, 3);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('DISABLE_ONE_CITY','/[0-9]*', 'PUT', false, 3);

INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_ALL_COMPANY_TYPES','', 'GET', false, 4);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_ONE_COMPANY_TYPE','/[0-9]*', 'GET', false, 4);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('CREATE_ONE_COMPANY_TYPE','', 'POST', false, 4);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('UPDATE_ONE_COMPANY_TYPE','/[0-9]*', 'PUT', false, 4);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('DISABLE_ONE_COMPANY_TYPE','/[0-9]*', 'PUT', false, 4);

INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_ALL_COMPANIES','', 'GET', true, 5);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_ONE_COMPANY','/[0-9]*', 'GET', false, 5);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('CREATE_ONE_COMPANY','', 'POST', false, 5);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('UPDATE_ONE_COMPANY','/[0-9]*', 'PUT', false, 5);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('DISABLE_ONE_COMPANY','/[0-9]*', 'PUT', false, 5);

INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_ALL_BRANCHES','', 'GET', true, 6);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_ONE_BRANCH','/[0-9]*', 'GET', false, 6);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('CREATE_ONE_BRANCH','', 'POST', false, 6);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('UPDATE_ONE_BRANCH','/[0-9]*', 'PUT', false, 6);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('DISABLE_ONE_BRANCH','/[0-9]*', 'PUT', false, 6);

INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_ALL_SERVICES','', 'GET', true, 7);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_ONE_SERVICE','/[0-9]*', 'GET', false, 7);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('CREATE_ONE_SERVICE','', 'POST', false, 7);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('UPDATE_ONE_SERVICE','/[0-9]*', 'PUT', false, 7);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('DISABLE_ONE_SERVICE','/[0-9]*', 'PUT', false, 7);

INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_ALL_SERVICE_BRANCHES','', 'GET', true, 8);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_ONE_SERVICE_BRANCH','/[0-9]*', 'GET', false, 8);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('CREATE_ONE_SERVICE_BRANCH','', 'POST', false, 8);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('UPDATE_ONE_SERVICE_BRANCH','/[0-9]*', 'PUT', false, 8);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('DISABLE_ONE_SERVICE_BRANCH','/[0-9]*', 'PUT', false, 8);

INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_ALL_SERVICE_APPROVALS','', 'GET', false, 9);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_ONE_SERVICE_APPROVAL','/[0-9]*', 'GET', false, 9);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('CREATE_ONE_SERVICE_APPROVAL','', 'POST', false, 9);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('UPDATE_ONE_SERVICE_APPROVAL','/[0-9]*', 'PUT', false, 9);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('DISABLE_ONE_SERVICE_APPROVAL','/[0-9]*', 'PUT', false, 9);

INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_ALL_SUPPLIES','', 'GET', true, 10);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_ONE_SUPPLY','/[0-9]*', 'GET', true, 10);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('CREATE_ONE_SUPPLY','', 'POST', false, 10);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('UPDATE_ONE_SUPPLY','/[0-9]*', 'PUT', false, 10);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('DISABLE_ONE_SUPPLY','/[0-9]*', 'PUT', false, 10);

INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_ALL_SUPPLY_SERVICES','', 'GET', true, 11);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_ONE_SUPPLY_SERVICE','/[0-9]*', 'GET', false, 11);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('CREATE_ONE_SUPPLY_SERVICE','', 'POST', false, 11);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('UPDATE_ONE_SUPPLY_SERVICE','/[0-9]*', 'PUT', false, 11);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('DISABLE_ONE_SUPPLY_SERVICE','/[0-9]*', 'PUT', false, 11);

INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_ALL_PEOPLE','', 'GET', false, 12);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_ONE_PERSON','/[0-9]*', 'GET', false, 12);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('CREATE_ONE_PERSON','', 'POST', true, 12);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('UPDATE_ONE_PERSON','/[0-9]*', 'PUT', false, 12);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('DISABLE_ONE_PERSON','/[0-9]*', 'PUT', false, 12);

INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_ALL_PERSON_TYPES','', 'GET', true, 13);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_ONE_PERSON_TYPE','/[0-9]*', 'GET', false, 13);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('CREATE_ONE_PERSON_TYPE','', 'POST', false, 13);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('UPDATE_ONE_PERSON_TYPE','/[0-9]*', 'PUT', false, 13);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('DISABLE_ONE_PERSON_TYPE','/[0-9]*', 'PUT', false, 13);

INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_ALL_PHONES','', 'GET', false, 14);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_ONE_PHONE','/[0-9]*', 'GET', false, 14);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('CREATE_ONE_PHONE','', 'POST', true, 14);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('UPDATE_ONE_PHONE','/[0-9]*', 'PUT', false, 14);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('DISABLE_ONE_PHONE','/[0-9]*', 'PUT', false, 14);

INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_ALL_PHONE_TYPES','', 'GET', true, 15);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_ONE_PHONE_TYPE','/[0-9]*', 'GET', false, 15);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('CREATE_ONE_PHONE_TYPE','', 'POST', false, 15);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('UPDATE_ONE_PHONE_TYPE','/[0-9]*', 'PUT', false, 15);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('DISABLE_ONE_PHONE_TYPE','/[0-9]*', 'PUT', false, 15);

INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_ALL_EMAILS','', 'GET', false, 16);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_ONE_EMAIL','/[0-9]*', 'GET', false, 16);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('CREATE_ONE_EMAIL','', 'POST', true, 16);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('UPDATE_ONE_EMAIL','/[0-9]*', 'PUT', false, 16);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('DISABLE_ONE_EMAIL','/[0-9]*', 'PUT', false, 16);

INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_ALL_EMAIL_TYPES','', 'GET', true, 17);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_ONE_EMAIL_TYPE','/[0-9]*', 'GET', false, 17);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('CREATE_ONE_EMAIL_TYPE','', 'POST', false, 17);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('UPDATE_ONE_EMAIL_TYPE','/[0-9]*', 'PUT', false, 17);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('DISABLE_ONE_EMAIL_TYPE','/[0-9]*', 'PUT', false, 17);

INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_ALL_PERSON_SUPPLIES','', 'GET', false, 18);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_ONE_PERSON_SUPPLY','/[0-9]*', 'GET', false, 18);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('CREATE_ONE_PERSON_SUPPLY','', 'POST', false, 18);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('UPDATE_ONE_PERSON_SUPPLY','/[0-9]*', 'PUT', false, 18);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('DISABLE_ONE_PERSON_SUPPLY','/[0-9]*', 'PUT', false, 18);

INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_ALL_ORDER_DETAILS','', 'GET', false, 19);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_ONE_ORDER_DETAIL','/[0-9]*', 'GET', false, 19);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('CREATE_ONE_ORDER_DETAIL','', 'POST', false, 19);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('UPDATE_ONE_ORDER_DETAIL','/[0-9]*', 'PUT', false, 19);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('DISABLE_ONE_ORDER_DETAIL','/[0-9]*', 'PUT', false, 19);

INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_ALL_ORDER_STATUSES','', 'GET', false, 20);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_ONE_ORDER_STATUS','/[0-9]*', 'GET', false, 20);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('CREATE_ONE_ORDER_STATUS','', 'POST', false, 20);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('UPDATE_ONE_ORDER_STATUS','/[0-9]*', 'PUT', false, 20);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('DISABLE_ONE_ORDER_STATUS','/[0-9]*', 'PUT', false, 20);

INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_ALL_SERVICE_ORDERS','', 'GET', false, 21);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_ONE_SERVICE_ORDER','/[0-9]*', 'GET', false, 21);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('CREATE_ONE_SERVICE_ORDER','', 'POST', false, 21);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('UPDATE_ONE_SERVICE_ORDER','/[0-9]*', 'PUT', false, 21);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('DISABLE_ONE_SERVICE_ORDER','/[0-9]*', 'PUT', false, 21);

INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_ALL_WORK_ORDERS','', 'GET', false, 22);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_ONE_WORK_ORDER','/[0-9]*', 'GET', false, 22);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('CREATE_ONE_WORK_ORDER','', 'POST', false, 22);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('UPDATE_ONE_WORK_ORDER','/[0-9]*', 'PUT', false, 22);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('DISABLE_ONE_WORK_ORDER','/[0-9]*', 'PUT', false, 22);

INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_ALL_WORK_ORDER_DETAILS','', 'GET', false, 23);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_ONE_WORK_ORDER_DETAIL','/[0-9]*', 'GET', false, 23);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('CREATE_ONE_WORK_ORDER_DETAIL','', 'POST', false, 23);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('UPDATE_ONE_WORK_ORDER_DETAIL','/[0-9]*', 'PUT', false, 23);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('DISABLE_ONE_WORK_ORDER_DETAIL','/[0-9]*', 'PUT', false, 23);

INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_ALL_WORK_ORDER_DETAIL_STATUSES','', 'GET', false, 24);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_ONE_WORK_ORDER_DETAIL_STATUS','/[0-9]*', 'GET', false, 24);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('CREATE_ONE_WORK_ORDER_DETAIL_STATUS','', 'POST', false, 24);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('UPDATE_ONE_WORK_ORDER_DETAIL_STATUS','/[0-9]*', 'PUT', false, 24);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('DISABLE_ONE_WORK_ORDER_DETAIL_STATUS','/[0-9]*', 'PUT', false, 24);

INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_ALL_APPROVAL_STATUSES','', 'GET', false, 25);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_ONE_APPROVAL_STATUS','/[0-9]*', 'GET', false, 25);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('CREATE_ONE_APPROVAL_STATUS','', 'POST', false, 25);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('UPDATE_ONE_APPROVAL_STATUS','/[0-9]*', 'PUT', false, 25);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('DISABLE_ONE_APPROVAL_STATUS','/[0-9]*', 'PUT', false, 25);

INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('REGISTER_ONE_CUSTOMER','/customer', 'POST', true, 12);

INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('AUTHENTICATE','/login', 'POST', true, 26);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('VALIDATE-TOKEN','/validate-token', 'GET', true, 26);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_MY_PROFILE','/profile','GET', true, 26);

-- CREACIÓN DE ROLES
INSERT INTO roles (name) VALUES ('CUSTOMER');
INSERT INTO roles (name) VALUES ('ADMINISTRATOR');
INSERT INTO roles (name) VALUES ('EMPLOYEE');
INSERT INTO roles (name) VALUES ('MARKETING');
INSERT INTO roles (name) VALUES ('SUPPLIERS');
INSERT INTO roles (name) VALUES ('SERVICE TRACKING');
INSERT INTO roles (name) VALUES ('HUMAN RESOURCES');
INSERT INTO roles (name) VALUES ('STORAGE´S ADMIN');

-- CREACIÓN DE PERMISOS
INSERT INTO granted_permission (role_id, operations_id) VALUES (1, 59);
INSERT INTO granted_permission (role_id, operations_id) VALUES (1, 69);
INSERT INTO granted_permission (role_id, operations_id) VALUES (1, 79);
INSERT INTO granted_permission (role_id, operations_id) VALUES (1, 91);
INSERT INTO granted_permission (role_id, operations_id) VALUES (1, 92);
INSERT INTO granted_permission (role_id, operations_id) VALUES (1, 93);
INSERT INTO granted_permission (role_id, operations_id) VALUES (1, 94);
INSERT INTO granted_permission (role_id, operations_id) VALUES (1, 95);
INSERT INTO granted_permission (role_id, operations_id) VALUES (1, 101);
INSERT INTO granted_permission (role_id, operations_id) VALUES (1, 102);
INSERT INTO granted_permission (role_id, operations_id) VALUES (1, 103);
INSERT INTO granted_permission (role_id, operations_id) VALUES (1, 104);
INSERT INTO granted_permission (role_id, operations_id) VALUES (1, 105);

INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 1);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 2);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 3);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 4);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 5);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 6);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 7);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 8);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 9);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 10);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 11);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 12);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 13);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 14);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 15);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 16);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 17);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 18);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 19);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 20);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 21);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 22);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 23);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 24);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 25);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 26);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 27);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 28);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 29);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 30);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 31);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 32);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 33);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 34);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 35);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 36);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 37);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 38);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 39);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 40);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 41);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 42);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 43);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 44);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 45);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 46);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 47);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 48);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 49);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 50);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 51);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 52);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 53);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 54);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 55);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 56);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 57);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 58);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 59);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 60);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 61);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 62);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 63);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 64);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 65);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 66);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 67);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 68);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 69);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 70);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 71);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 72);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 73);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 74);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 75);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 76);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 77);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 78);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 79);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 80);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 81);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 82);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 83);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 84);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 85);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 86);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 87);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 88);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 89);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 90);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 91);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 92);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 93);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 94);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 95);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 96);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 97);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 98);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 99);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 100);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 101);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 102);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 103);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 104);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 105);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 106);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 107);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 108);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 109);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 110);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 111);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 112);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 113);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 114);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 115);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 116);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 117);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 118);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 119);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 120);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 121);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 122);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 123);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 124);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 125);

-- INSERT INTO granted_permission (role_id, operation_id) VALUES (2, (SELECT id FROM operations;));

INSERT INTO granted_permission (role_id, operation_id) VALUES (3, 106);
INSERT INTO granted_permission (role_id, operation_id) VALUES (3, 107);
INSERT INTO granted_permission (role_id, operation_id) VALUES (3, 108);
INSERT INTO granted_permission (role_id, operation_id) VALUES (3, 109);
INSERT INTO granted_permission (role_id, operation_id) VALUES (3, 110);
INSERT INTO granted_permission (role_id, operation_id) VALUES (3, 111);
INSERT INTO granted_permission (role_id, operation_id) VALUES (3, 112);
INSERT INTO granted_permission (role_id, operation_id) VALUES (3, 113);
INSERT INTO granted_permission (role_id, operation_id) VALUES (3, 114);
INSERT INTO granted_permission (role_id, operation_id) VALUES (3, 115);
INSERT INTO granted_permission (role_id, operation_id) VALUES (3, 116);
INSERT INTO granted_permission (role_id, operation_id) VALUES (3, 117);
INSERT INTO granted_permission (role_id, operation_id) VALUES (3, 118);
INSERT INTO granted_permission (role_id, operation_id) VALUES (3, 119);
INSERT INTO granted_permission (role_id, operation_id) VALUES (3, 120);



INSERT INTO granted_permission (role_id, operation_id) VALUES (4, 111);
INSERT INTO granted_permission (role_id, operation_id) VALUES (4, 113);