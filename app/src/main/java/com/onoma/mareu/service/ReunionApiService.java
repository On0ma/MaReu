package com.onoma.mareu.service;

import com.onoma.mareu.model.Reunion;

import java.util.List;

public interface ReunionApiService {

    List<Reunion> getReunions();

    List<Reunion> filterReunions(String charString);

    void createReunion(Reunion reunion);

    void deleteReunion(Reunion reunion);
}
