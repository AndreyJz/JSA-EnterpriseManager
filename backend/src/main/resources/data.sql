-- CREACIÓN DE MODULOS
INSERT INTO modules (name, base_path) VALUES ('COUNTRY', '/api/Countries');
INSERT INTO modules (name, base_path) VALUES ('REGION', '/api/Regions');
INSERT INTO modules (name, base_path) VALUES ('CITY', '/api/Cities');
INSERT INTO modules (name, base_path) VALUES ('COMPANY_TYPE', '/api/Company Type');
INSERT INTO modules (name, base_path) VALUES ('COMPANY', '/api/Companies');
INSERT INTO modules (name, base_path) VALUES ('BRANCH', '/api/Branches');
INSERT INTO modules (name, base_path) VALUES ('SERVICE', '/api/Services');
INSERT INTO modules (name, base_path) VALUES ('SERVICE_BRANCH', '/api/Service Branches');
INSERT INTO modules (name, base_path) VALUES ('SERVICE_APPROVAL', '/api/Service Approval');
INSERT INTO modules (name, base_path) VALUES ('SUPPLY', '/api/Supply');
INSERT INTO modules (name, base_path) VALUES ('SUPPLY_SERVICE', '/api/Supply Service');
INSERT INTO modules (name, base_path) VALUES ('PERSON', '/api/Person');
INSERT INTO modules (name, base_path) VALUES ('PERSON_TYPE', '/api/Person Type');
INSERT INTO modules (name, base_path) VALUES ('PHONE', '/api/Phone');
INSERT INTO modules (name, base_path) VALUES ('PHONE_TYPE', '/api/Phone Type');
INSERT INTO modules (name, base_path) VALUES ('EMAIL', '/api/Email');
INSERT INTO modules (name, base_path) VALUES ('EMAIL_TYPE', '/api/Email Type');
INSERT INTO modules (name, base_path) VALUES ('PERSON_SUPPLY', '/api/Person Supply');
INSERT INTO modules (name, base_path) VALUES ('ORDER_DETAIL', '/api/Order Details');
INSERT INTO modules (name, base_path) VALUES ('ORDER_STATUS', '/api/Order Status');
INSERT INTO modules (name, base_path) VALUES ('SERVICE_ORDER', '/api/Service Order');
INSERT INTO modules (name, base_path) VALUES ('WORK_ORDER', '/api/Work Orders');
INSERT INTO modules (name, base_path) VALUES ('WORK_ORDER_DETAIL', '/api/Work Order Detail');
INSERT INTO modules (name, base_path) VALUES ('WORK_ORDER_DETAIL_STATUS', '/api/Work Detail Status');
INSERT INTO modules (name, base_path) VALUES ('APPROVAL_STATUS', '/api/Approval Status');
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

INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('REGISTER_ONE','/customer', 'POST', true, 12);

INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('AUTHENTICATE','/authenticate', 'POST', true, 26);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('VALIDATE-TOKEN','/validate-token', 'GET', true, 26);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('READ_MY_PROFILE','/profile','GET', true, 26);

-- CREACIÓN DE ROLES
INSERT INTO roles (name) VALUES ('CUSTOMER'); 
INSERT INTO roles (name) VALUES ('ADMINISTRATOR');
INSERT INTO roles (name) VALUES ('BOSS'); 
INSERT INTO roles (name) VALUES ('EMPLOYEE'); 
INSERT INTO roles (name) VALUES ('SERVICE TRACKING');
INSERT INTO roles (name) VALUES ('MARKETING');
INSERT INTO roles (name) VALUES ('BUYS DEPARTMENT');
INSERT INTO roles (name) VALUES ('HUMAN RESOURCES');
INSERT INTO roles (name) VALUES ('WAREHOUSE´S ADMIN');
INSERT INTO roles (name) VALUES ('STORAGE´S ADMIN');
INSERT INTO roles (name) VALUES ('SUPPLIERS');

-- CREACIÓN DE PERMISOS
INSERT INTO granted_permissions (role_id, operation_id) VALUES (1, 59);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (1, 69);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (1, 79);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (1, 91);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (1, 92);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (1, 93);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (1, 94);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (1, 95);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (1, 101);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (1, 102);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (1, 103);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (1, 104);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (1, 105);

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

-- INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, (SELECT id FROM operations;));

INSERT INTO granted_permissions (role_id, operation_id) VALUES (3, 106);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (3, 107);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (3, 108);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (3, 109);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (3, 110);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (3, 111);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (3, 112);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (3, 113);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (3, 114);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (3, 115);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (3, 116);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (3, 117);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (3, 118);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (3, 119);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (3, 120);



INSERT INTO granted_permissions (role_id, operation_id) VALUES (4, 111);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (4, 113);

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
('Customer'), 
('Employee'), 
('Admin');

-- Tabla countries
INSERT INTO countries (name) VALUES 
('Mexico'), 
('Brazil'), 
('Peru');

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
(1, 'Yucatan'),  -- Mexico
(2, 'Rio de Janeiro'),  -- Brazil
(3, 'Lima');  -- Peru

-- Insertando ciudades (depende de regions)
INSERT INTO cities (region_id, name) VALUES 
(1, 'San Francisco'), 
(2, 'Dallas'), 
(3, 'Medellín');

-- Insertando compañías (depende de company_types)
INSERT INTO companies (company_type_id, name) VALUES 
(1, 'TechCorp'), 
(2, 'BuildCo'), 
(1, 'DevSolutions');

-- Insertando sucursales (depende de cities y companies)
INSERT INTO branches (city_id, company_id, creation_date, name, nit) VALUES 
(1, 1, '2024-01-01 10:00:00', 'Branch 1', '1234567890'),  -- San Francisco, TechCorp
(2, 2, '2024-02-01 11:00:00', 'Branch 2', '0987654321'),  -- Dallas, BuildCo
(3, 1, '2024-03-01 12:00:00', 'Branch 3', '1122334455');  -- Medellín, TechCorp

-- Insertando personas (depende de branches y person_type)
INSERT INTO person (branch_id, person_type_id, lastname, name, password, date, id, role_id, username) VALUES 
(1, 1, 'Doe', 'John', 'hashed_password_1', '2024-04-01 13:00:00', 'P001', 1, 'john_doe'),  -- Branch 1
(2, 2, 'Smith', 'Jane', 'hashed_password_2', '2024-05-01 14:00:00', 'P002', 2, 'jane_smith'),  -- Branch 2
(3, 3, 'Brown', 'Charlie', 'hashed_password_3', '2024-06-01 15:00:00', 'P003', 1, 'charlie_brown');  -- Branch 3

-- Insertando correos (depende de person y email_type)
INSERT INTO email (email_type_id, mail, person_id) VALUES 
(1, 'john.doe@example.com', 'P001'), 
(2, 'jane.smith@work.com', 'P002'), 
(3, 'charlie.brown@support.com', 'P003');

-- Insertando teléfonos (depende de person y phone_type)
INSERT INTO phone (phone_type_id, number, person_id) VALUES 
(1, '123-456-7890', 'P001'), 
(2, '098-765-4321', 'P002'), 
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
(1, 'P001', 'P002', '2024-07-01 16:00:00'),  -- Orden 1: Cliente P001, Empleado P002
(2, 'P002', 'P003', '2024-07-02 17:00:00'),  -- Orden 2: Cliente P002, Empleado P003
(3, 'P003', 'P001', '2024-07-03 18:00:00');  -- Orden 3: Cliente P003, Empleado P001

-- Insertando órdenes de trabajo (depende de service_order)
INSERT INTO work_orders (service_order_id, work_order_num, assign_date) VALUES 
(1, 'WO001', '2024-08-01 19:30:00'), 
(2, 'WO002', '2024-08-02 20:30:00'), 
(3, 'WO003', '2024-08-03 21:30:00');

-- Insertando detalles de órdenes de trabajo (depende de work_orders, service_branches, work_order_detail_status, person)
INSERT INTO work_order_detail (service_branch_branch_id, service_branch_service_id, work_order_detail_status_id, work_order_id, date, employee_id) VALUES 
(1, 1, 1, 1, '2024-08-01 19:00:00', 'P001'), 
(2, 2, 2, 2, '2024-08-02 20:00:00', 'P002'), 
(3, 3, 3, 3, '2024-08-03 21:00:00', 'P003');

-- Insertando aprobaciones de servicio (depende de work_order_detail y approval_status)
INSERT INTO service_approval (approval_status_id, service_branch_branch_id, service_branch_service_id, work_order_id, report, solution) VALUES 
(1, 1, 1, 1, 'Initial inspection completed', 'Replace defective parts'), 
(2, 2, 2, 2, 'System upgrade completed', 'Optimize software configuration'), 
(3, 3, 3, 3, 'Issue resolved', 'Replaced motherboard');

-- Tabla person_supply
INSERT INTO person_supply (quantity, supply_id, person_id) VALUES 
(10, 1, 'P001'), 
(5, 2, 'P002'), 
(20, 3, 'P003');

-- Tabla order_details
INSERT INTO order_details (service_value, id, service_branch_branch_id, service_branch_service_id, service_order_id) VALUES 
(50.00, 1, 1, 1, 1), 
(75.00, 2, 2, 2, 2), 
(100.00, 3, 3, 3, 3);