package com.narturebelle.providers.model;

/** Same values as NB-Ecom-API's enum, because both services read public.providers.status. */
public enum ProviderStatus {
    INVITED,
    DOCUMENTS_PENDING,
    UNDER_REVIEW,
    VERIFIED,
    ACTIVE,
    PAUSED,
    SUSPENDED,
    DEACTIVATED
}
