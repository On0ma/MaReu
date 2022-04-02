package com.onoma.mareu.service;

import com.onoma.mareu.model.Reunion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class DummyReunionGenerator {

    public static List<Reunion> DUMMY_REUNIONS = Arrays.asList(
            new Reunion(
                    0,
                    "10h00",
                    Reunion.Room.LUIGI,
                    "Reunion A",
                    "paul@lamzone.com, caroline@lamzone.com, viviane@lamzone.com"
            ),
            new Reunion(
                    1,
                    "11h00",
                    Reunion.Room.PEACH,
                    "Reunion B",
                    "paul@lamzone.com, caroline@lamzone.com, viviane@lamzone.com"
            ),
            new Reunion(
                    2,
                    "14h00",
                    Reunion.Room.MARIO,
                    "Reunion C",
                    "paul@lamzone.com, caroline@lamzone.com, viviane@lamzone.com"
            ),
            new Reunion(
                    3,
                    "15h00",
                    Reunion.Room.LUIGI,
                    "Reunion D",
                    "paul@lamzone.com, caroline@lamzone.com, viviane@lamzone.com"
            ),
            new Reunion(
                    4,
                    "16h00",
                    Reunion.Room.MARIO,
                    "Reunion E",
                    "paul@lamzone.com, caroline@lamzone.com, viviane@lamzone.com"
            ),
            new Reunion(
                    5,
                    "16h00",
                    Reunion.Room.PEACH,
                    "Reunion F",
                    "paul@lamzone.com, caroline@lamzone.com, viviane@lamzone.com"
            ),
            new Reunion(
                    6,
                    "17h00",
                    Reunion.Room.MARIO,
                    "Reunion G",
                    "paul@lamzone.com, caroline@lamzone.com, viviane@lamzone.com"
            ),
            new Reunion(
                    7,
                    "17h00",
                    Reunion.Room.LUIGI,
                    "Reunion H",
                    "paul@lamzone.com, caroline@lamzone.com, viviane@lamzone.com"
            ),
            new Reunion(
                    8,
                    "18h00",
                    Reunion.Room.MARIO,
                    "Reunion I",
                    "paul@lamzone.com, caroline@lamzone.com, viviane@lamzone.com"
            ),
            new Reunion(
                    9,
                    "15h00",
                    Reunion.Room.PEACH,
                    "Reunion J",
                    "paul@lamzone.com, caroline@lamzone.com, viviane@lamzone.com"
            )
    );

    static List<Reunion> generateReunions() { return new ArrayList<>(DUMMY_REUNIONS); }
}
