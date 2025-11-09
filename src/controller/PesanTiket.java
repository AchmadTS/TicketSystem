package controller;

import java.time.LocalDateTime;
import model.Penerbangan;
import model.Pemesanan;

public class PesanTiket {
    private SistemTiket sistem;

    public PesanTiket(SistemTiket sistem) {
        this.sistem = sistem;
    }

    public void run() {
        sistem.view.showDaftarPenerbangan(sistem.daftarPenerbangan, sistem.jumlahPenerbangan);
        System.out.print("Masukkan ID penerbangan: ");
        int id = Integer.parseInt(sistem.input.nextLine());
        Penerbangan p = sistem.cariById(id);

        if (p == null) {
            System.out.println("❌ Tidak ditemukan.");
            return;
        }

        System.out.print("Nama pemesan: ");
        String nama = sistem.input.nextLine();
        System.out.print("Jumlah tiket: ");
        int jumlah = Integer.parseInt(sistem.input.nextLine());

        if (jumlah > p.jumlahKursi) {
            System.out.println("❌ Kursi tidak cukup!");
            return;
        }

        p.jumlahKursi -= jumlah;
        double total = jumlah * p.harga;
        sistem.daftarPemesanan[sistem.jumlahPemesanan++] = new Pemesanan(sistem.nextIdPemesanan++, p.id, nama, jumlah, total, LocalDateTime.now());

        System.out.printf("✅ Pemesanan berhasil! Total: Rp%,.0f", total);
        System.out.println();
    }
}
