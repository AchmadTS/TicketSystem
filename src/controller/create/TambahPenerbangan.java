package controller.create;

import controller.SistemTiket;
import util.Helper;
import model.Penerbangan;

public class TambahPenerbangan {
    private SistemTiket sistem;

    public TambahPenerbangan(SistemTiket sistem) {
        this.sistem = sistem;
    }

    public void run() {
        System.out.println();
        System.out.println("╔═════════════════════════════════════════════════════════╗");
        System.out.println("║                 ➕ TAMBAH PENERBANGAN                   ║");
        System.out.println("╚═════════════════════════════════════════════════════════╝");

        String pesawat = Helper.inputStringWajib(sistem.input, "Nama pesawat: ");
        String asal = Helper.inputStringWajib(sistem.input, "Asal: ");
        String tujuan = Helper.inputStringWajib(sistem.input, "Tujuan: ");
        double harga = Helper.inputHarga(sistem.input, "Harga: ");

        int[] tanggal = Helper.inputTanggal(sistem.input, "Masukkan waktu (contoh: 28 Februari 2024): ");
        int hari = tanggal[0];
        int bulan = tanggal[1];
        int tahun = tanggal[2];

        int jam = Helper.inputInteger(sistem.input, "Masukkan jam keberangkatan (0-23): ", 0, 23);
        int menit = Helper.inputInteger(sistem.input, "Masukkan menit keberangkatan (0-59): ", 0, 59);
        int kursi = Helper.inputInteger(sistem.input, "Jumlah kursi: ", 1, Integer.MAX_VALUE);

        sistem.daftarPenerbangan[sistem.jumlahPenerbangan++] = new Penerbangan(sistem.nextIdPenerbangan++, pesawat, asal, tujuan, harga, hari, bulan, tahun, jam, menit, kursi);
        System.out.println("✅ Penerbangan berhasil ditambahkan!");
    }
}