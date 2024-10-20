package com.enterprisemanager.backend.infrastructure.utils.enums;

import java.util.Arrays;
import java.util.List;

import static com.enterprisemanager.backend.infrastructure.utils.enums.RolePermission.*;

public enum Role {
    ROLE_ADMINISTRATOR(Arrays.asList(
            READ_ALL_SUPPLIES,
            READ_ONE_SUPPLY,
            CREATE_ONE_SUPPLY,
            UPDATE_ONE_SUPPLY,
            DISABLE_ONE_SUPPLY,

            READ_ALL_SERVICES,
            READ_ONE_SERVICE,
            CREATE_ONE_SERVICE,
            UPDATE_ONE_SERVICE,
            DISABLE_ONE_SERVICE,

            READ_ALL_SERVICE_APPROVALS,
            READ_ONE_SERVICE_APPROVAL,
            CREATE_ONE_SERVICE_APPROVAL,
            UPDATE_ONE_SERVICE_APPROVAL,
            DISABLE_ONE_SERVICE_APPROVAL,

            READ_ALL_SERVICE_BRANCHES,
            READ_ONE_SERVICE_BRANCH,
            CREATE_ONE_SERVICE_BRANCH,
            UPDATE_ONE_SERVICE_BRANCH,
            DISABLE_ONE_SERVICE_BRANCH,

            READ_ALL_SUPPLY_SERVICES,
            READ_ONE_SUPPLY_SERVICE,
            CREATE_ONE_SUPPLY_SERVICE,
            UPDATE_ONE_SUPPLY_SERVICE,
            DISABLE_ONE_SUPPLY_SERVICE,

            READ_ALL_COMPANIES,
            READ_ONE_COMPANY,
            CREATE_ONE_COMPANY,
            UPDATE_ONE_COMPANY,
            DISABLE_ONE_COMPANY,

            READ_ALL_BRANCHES,
            READ_ONE_BRANCH,
            CREATE_ONE_BRANCH,
            UPDATE_ONE_BRANCH,
            DISABLE_ONE_BRANCH,

            READ_ALL_EMAILS,
            READ_ONE_EMAIL,
            CREATE_ONE_EMAIL,
            UPDATE_ONE_EMAIL,
            DISABLE_ONE_EMAIL,

            READ_ALL_EMAIL_TYPES,
            READ_ONE_EMAIL_TYPE,
            CREATE_ONE_EMAIL_TYPE,
            UPDATE_ONE_EMAIL_TYPE,
            DISABLE_ONE_EMAIL_TYPE,

            READ_ALL_PEOPLE,
            READ_ONE_PERSON,
            CREATE_ONE_PERSON,
            UPDATE_ONE_PERSON,
            DISABLE_ONE_PERSON,

            READ_ALL_PERSON_SUPPLIES,
            READ_ONE_PERSON_SUPPLY,
            CREATE_ONE_PERSON_SUPPLY,
            UPDATE_ONE_PERSON_SUPPLY,
            DISABLE_ONE_PERSON_SUPPLY,

            READ_ALL_PERSON_TYPES,
            READ_ONE_PERSON_TYPE,
            CREATE_ONE_PERSON_TYPE,
            UPDATE_ONE_PERSON_TYPE,
            DISABLE_ONE_PERSON_TYPE,

            READ_ALL_PHONES,
            READ_ONE_PHONE,
            CREATE_ONE_PHONE,
            UPDATE_ONE_PHONE,
            DISABLE_ONE_PHONE,

            READ_ALL_PHONE_TYPES,
            READ_ONE_PHONE_TYPE,
            CREATE_ONE_PHONE_TYPE,
            UPDATE_ONE_PHONE_TYPE,
            DISABLE_ONE_PHONE_TYPE,

            READ_ALL_ORDERS,
            READ_ONE_ORDER,
            CREATE_ONE_ORDER,
            UPDATE_ONE_ORDER,
            DISABLE_ONE_ORDER,

            READ_ALL_ORDER_STATUSES,
            READ_ONE_ORDER_STATUS,
            CREATE_ONE_ORDER_STATUS,
            UPDATE_ONE_ORDER_STATUS,
            DISABLE_ONE_ORDER_STATUS,

            READ_ALL_SERVICE_ORDERS,
            READ_ONE_SERVICE_ORDER,
            CREATE_ONE_SERVICE_ORDER,
            UPDATE_ONE_SERVICE_ORDER,
            DISABLE_ONE_SERVICE_ORDER,

            READ_ALL_WORK_ORDERS,
            READ_ONE_WORK_ORDER,
            CREATE_ONE_WORK_ORDER,
            UPDATE_ONE_WORK_ORDER,
            DISABLE_ONE_WORK_ORDER,

            READ_ALL_WORK_ORDER_DETAILS,
            READ_ONE_WORK_ORDER_DETAIL,
            CREATE_ONE_WORK_ORDER_DETAIL,
            UPDATE_ONE_WORK_ORDER_DETAIL,
            DISABLE_ONE_WORK_ORDER_DETAIL,

            READ_ALL_APPROVAL_STATUSES,
            READ_ONE_APPROVAL_STATUS,
            CREATE_ONE_APPROVAL_STATUS,
            UPDATE_ONE_APPROVAL_STATUS,
            DISABLE_ONE_APPROVAL_STATUS,

            READ_ALL_WORK_ORDER_DETAIL_STATUSES,
            READ_ONE_WORK_ORDER_DETAIL_STATUS,
            CREATE_ONE_WORK_ORDER_DETAIL_STATUS,
            UPDATE_ONE_WORK_ORDER_DETAIL_STATUS,
            DISABLE_ONE_WORK_ORDER_DETAIL_STATUS,

            READ_ALL_CITIES,
            READ_ONE_CITY,
            CREATE_ONE_CITY,
            UPDATE_ONE_CITY,
            DISABLE_ONE_CITY,

            READ_ALL_COMPANY_TYPES,
            READ_ONE_COMPANY_TYPE,
            CREATE_ONE_COMPANY_TYPE,
            UPDATE_ONE_COMPANY_TYPE,
            DISABLE_ONE_COMPANY_TYPE,

            READ_ALL_COUNTRIES,
            READ_ONE_COUNTRY,
            CREATE_ONE_COUNTRY,
            UPDATE_ONE_COUNTRY,
            DISABLE_ONE_COUNTRY,

            READ_ALL_REGIONS,
            READ_ONE_REGION,
            CREATE_ONE_REGION,
            UPDATE_ONE_REGION,
            DISABLE_ONE_REGION,

            READ_MY_PROFILE

    )),
    ROLE_ASSISTANT_ADMINISTRATOR(Arrays.asList(
            READ_ALL_SUPPLIES,
            READ_ONE_SUPPLY,
            UPDATE_ONE_SUPPLY,

            READ_ALL_SERVICES,
            READ_ONE_SERVICE,
            UPDATE_ONE_SERVICE,

            READ_MY_PROFILE
    )),
    ROLE_CUSTOMER(Arrays.asList(
            READ_ALL_SERVICES,
            READ_ONE_SERVICE,

            READ_ALL_BRANCHES,
            READ_ONE_BRANCH,

            READ_ALL_COMPANIES,
            READ_ONE_COMPANY,

            CREATE_ONE_EMAIL,
            UPDATE_ONE_EMAIL,
            DISABLE_ONE_EMAIL,

            READ_ALL_EMAIL_TYPES,
            READ_ONE_EMAIL_TYPE,

            CREATE_ONE_PHONE,
            UPDATE_ONE_PHONE,
            DISABLE_ONE_PHONE,

            READ_ALL_PHONE_TYPES,
            READ_ONE_PHONE_TYPE,

            READ_ALL_PERSON_TYPES,
            READ_ONE_PERSON_TYPE,

            READ_ALL_COUNTRIES,
            READ_ALL_REGIONS,
            READ_ALL_CITIES,

            READ_MY_PROFILE
    ));
    private List<RolePermission> permissions;

    Role(List<RolePermission> permissions) {
        this.permissions = permissions;
    }

    public List<RolePermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<RolePermission> permissions) {
        this.permissions = permissions;
    }
}