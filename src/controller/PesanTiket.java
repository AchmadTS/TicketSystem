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
        int id = 0;
        boolean idValid = false;
        while (!idValid) {
            System.out.print("Masukkan ID penerbangan: ");
            String inputId = sistem.input.nextLine().trim();

            if (inputId.isEmpty()) {
                System.out.println("❌ ID tidak boleh kosong!");
                continue;
            }

            boolean formatValid = true;
            for (int i = 0; i < inputId.length(); i++) {
                char c = inputId.charAt(i);
                if (c < '0' || c > '9') {
                    formatValid = false;
                    break;
                }
            }

            if (!formatValid) {
                System.out.println("❌ ID harus berupa angka!");
                continue;
            }

            id = Integer.parseInt(inputId);
            idValid = true;
        }
        Penerbangan p = sistem.cariById(id);

        if (p == null) {
            System.out.println("❌ Tidak ditemukan.");
            return;
        }

        String nama = "";
        while (nama.isEmpty()) {
            System.out.print("Nama pemesan: ");
            nama = sistem.input.nextLine().trim();
            if (nama.isEmpty()) {
                System.out.println("❌ Nama tidak boleh kosong!");
            }
        }

        int jumlah = 0;
        boolean jumlahValid = false;
        while (!jumlahValid) {
            System.out.print("Jumlah tiket: ");
            String inputJumlah = sistem.input.nextLine().trim();

            // Cek input kosong
            if (inputJumlah.isEmpty()) {
                System.out.println("❌ Jumlah tiket tidak boleh kosong!");
                continue;
            }

            boolean formatValid = true;
            for (int i = 0; i < inputJumlah.length(); i++) {
                char c = inputJumlah.charAt(i);
                if (c < '0' || c > '9') {
                    formatValid = false;
                    break;
                }
            }

            if (!formatValid) {
                System.out.println("❌ Jumlah tiket harus berupa angka!");
                continue;
            }

            jumlah = Integer.parseInt(inputJumlah);

            if (jumlah <= 0) {
                System.out.println("❌ Jumlah tiket harus lebih dari 0!");
                continue;
            }

            jumlahValid = true;
        }

        if (jumlah > p.jumlahKursi) {
            System.out.println("❌ Kursi tidak cukup! Tersedia: " + p.jumlahKursi + " kursi");
            return;
        }

        p.jumlahKursi -= jumlah;
        double total = jumlah * p.harga;
        sistem.daftarPemesanan[sistem.jumlahPemesanan++] = new Pemesanan(sistem.nextIdPemesanan++, p.id, nama, jumlah, total, LocalDateTime.now());

        System.out.println();
        System.out.println("╔════════════════════════════════════════════════╗");
        System.out.println("║         ✅ PEMESANAN BERHASIL! ✅              ║");
        System.out.println("╚════════════════════════════════════════════════╝");
        System.out.printf("Nama Pemesan  : %s\n", nama);
        System.out.printf("Jumlah Tiket  : %d\n", jumlah);
        System.out.printf("Total Biaya   : Rp%,.0f\n", total);
        System.out.printf("Sisa Kursi    : %d\n", p.jumlahKursi);
        System.out.println("────────────────────────────────────────────────");
    }
}
