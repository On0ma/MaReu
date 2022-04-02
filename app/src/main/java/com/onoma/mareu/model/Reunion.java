package com.onoma.mareu.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Reunion {

    private long id;

    private String time;

    public enum Room {
        MARIO("Mario"),
        PEACH("Peach"),
        LUIGI("Luigi");

        private String value;

        Room(String value) {
            this.value = value ;
        }

        public String getValue() {
            return  this.value ;
        }

        public static Room findByValue(String value) {
            for (Room r : values()) {
                if ( r.getValue().equals(value) ) {
                    return r;
                }
            }
            return null;
        }
    }

    private Room roomSelection;

    private String subject;

    private String attendeeMail;

    public Reunion(long id, String time, Room room, String subject, String attendeeMail) {
        this.id = id;
        this.time = time;
        roomSelection = room;
        this.subject = subject;
        this.attendeeMail = attendeeMail;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Room getRoom() {
        return roomSelection;
    }

    public void setRoom(Room room) {
        roomSelection = room;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getAttendeeMail() {
        return attendeeMail;
    }

    public void setAttendeeMail(String attendeeMail) {
        this.attendeeMail = attendeeMail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reunion reunion = (Reunion) o;
        return id == reunion.id && time.equals(reunion.time) && roomSelection.equals(reunion.roomSelection) && subject.equals(reunion.subject) && attendeeMail.equals(reunion.attendeeMail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, time, roomSelection, subject, attendeeMail);
    }

    public String concatenateInfos() {
        return this.subject + " - " + this.time + " - " + this.roomSelection.getValue();
    }

    public static List<String> listRoomValues() {
        List<String> roomValues = new ArrayList<>();
        for (Room roomItem : Room.values()) {
            roomValues.add(roomItem.getValue());
        }
        return roomValues;
    }
}
