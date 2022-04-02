package com.onoma.mareu;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.hamcrest.MatcherAssert;
import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.onoma.mareu.di.DI;
import com.onoma.mareu.model.Reunion;
import com.onoma.mareu.service.DummyReunionGenerator;
import com.onoma.mareu.service.ReunionApiService;

import java.util.ArrayList;
import java.util.List;

@RunWith(JUnit4.class)
public class ReunionUnitTest {

    private ReunionApiService service;

    @Before
    public void setup() { service = DI.getNewInstanceApiService(); }

    @Test
    public void getReunionWithSuccess() {
        List<Reunion> reunions = service.getReunions();
        List<Reunion> expectedReunions = DummyReunionGenerator.DUMMY_REUNIONS;
        MatcherAssert.assertThat(reunions, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedReunions.toArray()));
    }

    @Test
    public void deleteReunionWithSuccess() {
        Reunion reunionToDelete = service.getReunions().get(0);
        service.deleteReunion(reunionToDelete);
        assertFalse(service.getReunions().contains(reunionToDelete));
    }

    @Test
    public void createReunionWithSuccess() {
        List<Reunion> reunions = service.getReunions();
        Reunion reunion = new Reunion(
                20,
                "15h00",
                Reunion.Room.PEACH,
                "Reunion Z",
                "alex@lamzone.com"
        );
        service.createReunion(reunion);
        assertTrue(reunions.contains(reunion));
    }

    @Test
    public void filterReunionWithSuccess() {
        String searchString = "Peach";
        List<Reunion> filteredList = service.filterReunions(searchString);
        List<Reunion> correctList = new ArrayList<>();
        for (Reunion row : service.getReunions()) {
            if (row.getRoom().getValue().equals(searchString)) {
                correctList.add(row);
            }
        }
        assertTrue(filteredList.equals(correctList));
    }
}