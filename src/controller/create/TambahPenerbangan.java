package controller.create;

import controller.SistemTiket;
import util.Helper;
import model.Penerbangan;

public class TambahPenerbangan {
    public static void run(SistemTiket sistemTiket) {
        System.out.println();
        System.out.println("╔═════════════════════════════════════════════════════════╗");
        System.out.println("║                 ➕ TAMBAH PENERBANGAN                   ║");
        System.out.println("╚═════════════════════════════════════════════════════════╝");

        /** Input data penerbangan */
        String pesawat = Helper.inputStringWajib(sistemTiket.input, "Nama pesawat: ");
        String asal = Helper.inputStringWajib(sistemTiket.input, "Asal: ");
        String tujuan = Helper.inputStringWajib(sistemTiket.input, "Tujuan: ");
        double harga = Helper.inputHarga(sistemTiket.input, "Harga: ");

        /** Input tanggal keberangkatan */
        int[] tanggal = Helper.inputTanggal(sistemTiket.input, "Masukkan waktu (contoh: 28 Februari 2024): ");
        int hari = tanggal[0];
        int bulan = tanggal[1];
        int tahun = tanggal[2];

        /** Input waktu keberangkatan */
        int jam = Helper.inputInteger(sistemTiket.input, "Masukkan jam keberangkatan (0-23): ", 0, 23);
        int menit = Helper.inputInteger(sistemTiket.input, "Masukkan menit keberangkatan (0-59): ", 0, 59);
        int kursi = Helper.inputInteger(sistemTiket.input, "Jumlah kursi: ", 1, Integer.MAX_VALUE);

        /** Save */
        sistemTiket.daftarPenerbangan[sistemTiket.jumlahPenerbangan++] = new Penerbangan(sistemTiket.nextIdPenerbangan++, pesawat, asal, tujuan, harga, hari, bulan, tahun, jam, menit, kursi);
        System.out.println("✅ Penerbangan berhasil ditambahkan!");
    }
}