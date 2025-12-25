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

        String pesawat = Helper.inputStringWajib(sistemTiket.input, "Nama pesawat: ");
        String asal = Helper.inputStringWajib(sistemTiket.input, "Asal: ");
        String tujuan = Helper.inputStringWajib(sistemTiket.input, "Tujuan: ");
        double harga = Helper.inputHarga(sistemTiket.input, "Harga: ");

        int[] waktu = Helper.inputTanggal(sistemTiket.input, "Masukkan waktu (contoh: 28 Februari 2024): ");
        int jam = Helper.inputInteger(sistemTiket.input, "Masukkan jam keberangkatan (0-23): ", 0, 23);
        int menit = Helper.inputInteger(sistemTiket.input, "Masukkan menit keberangkatan (0-59): ", 0, 59);
        int kursi = Helper.inputInteger(sistemTiket.input, "Jumlah kursi: ", 1, Integer.MAX_VALUE);

        sistemTiket.daftarPenerbangan[sistemTiket.jumlahPenerbangan++] = new Penerbangan(sistemTiket.nextIdPenerbangan++, pesawat, asal, tujuan, harga, waktu[0], waktu[1], waktu[2], jam, menit, kursi);
        System.out.println("✅ Penerbangan berhasil ditambahkan!");
    }
}