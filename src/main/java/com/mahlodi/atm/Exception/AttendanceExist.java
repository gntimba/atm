package com.mahlodi.atm.Exception;

public class AttendanceExist extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public AttendanceExist() {
        super();
    }

    public AttendanceExist(String message) {
        super(message);
    }
}
