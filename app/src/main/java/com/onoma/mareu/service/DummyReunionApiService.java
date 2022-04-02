package com.onoma.mareu.service;

import com.onoma.mareu.model.Reunion;

import java.util.ArrayList;
import java.util.List;

public class DummyReunionApiService implements ReunionApiService {

    private List<Reunion> reunions = DummyReunionGenerator.generateReunions();

    @Override
    public List<Reunion> getReunions() { return reunions; }

    @Override
    public List<Reunion> filterReunions(String charString) {
        List<Reunion> listFiltered = new ArrayList<>();
        if (charString.isEmpty()) {
            listFiltered.addAll(reunions);
        } else {
            for (Reunion row : reunions) {
                if (row.getTime().toLowerCase().contains(charString.toLowerCase()) || row.getRoom().getValue().toLowerCase().contains(charString.toLowerCase())) {
                    listFiltered.add(row);
                }
            }
        }
        return listFiltered;
    }

    @Override
    public void createReunion(Reunion reunion) { reunions.add(reunion); }

    @Override
    public void deleteReunion(Reunion reunion) { reunions.remove(reunion); }
}
