package main;

import controller.SistemTiket;

public class Main {
    public static void main(String[] args) {
        SistemTiket app = new SistemTiket();
        app.isiContohPenerbangan();
        app.mulai();
    }
}
