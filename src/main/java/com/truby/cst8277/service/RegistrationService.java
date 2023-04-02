package com.truby.cst8277.service;

public class RegistrationService {

    private final String registrationId;
    private final String uri;
    private final String clientName;

    public RegistrationService(String registrationId, String uri, String clientName) {
        this.registrationId = registrationId;
        this.uri = uri;
        this.clientName = clientName;
    }

    public String getClientName() {
        return clientName;
    }

    public String getRegistrationId() {
        return registrationId;
    }

    public String getUri() {
        return uri;
    }
}
