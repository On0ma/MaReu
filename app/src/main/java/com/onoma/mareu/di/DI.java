package com.onoma.mareu.di;

import com.onoma.mareu.service.DummyReunionApiService;
import com.onoma.mareu.service.ReunionApiService;

public class DI {

    private static ReunionApiService service = new DummyReunionApiService();

    public static ReunionApiService getReunionApiService() { return service; }

    public static ReunionApiService getNewInstanceApiService() {
        return new DummyReunionApiService();
    }
}
