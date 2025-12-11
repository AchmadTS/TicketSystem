package controller.create;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import controller.SistemTiket;
import model.Penerbangan;
import model.Pemesanan;
import util.Helper;

public class PesanTiket {
    private SistemTiket sistem;

    public PesanTiket(SistemTiket sistem) {
        this.sistem = sistem;
    }

    public void run() {
        sistem.view.showDaftarPenerbangan(sistem.daftarPenerbangan, sistem.jumlahPenerbangan);
        Penerbangan p = null;
        while (p == null) {
            int id = Helper.inputId(sistem.input, "Masukkan ID penerbangan: ");
            p = sistem.cariById(id);
            if (p == null) {
                System.out.println("❌ ID Tidak ada");
            }
        }

        String nama = Helper.inputStringWajib(sistem.input, "Nama pemesan: ");
        int jumlah = 0;
        boolean jumlahValid = false;
        while (!jumlahValid) {
            jumlah = Helper.inputInteger(sistem.input, "Jumlah tiket: ", 1, Integer.MAX_VALUE);
            if (jumlah > p.jumlahKursi) {
                System.out.println("❌ Kursi tidak cukup! Tersedia: " + p.jumlahKursi + " kursi");
            } else {
                jumlahValid = true;
            }
        }

        p.jumlahKursi -= jumlah;
        double total = jumlah * p.harga;
        sistem.daftarPemesanan[sistem.jumlahPemesanan++] = new Pemesanan(sistem.nextIdPemesanan++, p.id, nama, jumlah, total, LocalDateTime.now());

        DecimalFormat df = new DecimalFormat("#,###");
        String namaBulan = Helper.getNamaBulan(p.bulan);
        String tanggal = Helper.formatDuaDigit(p.hari) + " " + namaBulan + " " + p.tahun;
        String waktu = Helper.formatDuaDigit(p.jam) + ":" + Helper.formatDuaDigit(p.menit) + ":00";

        System.out.println();
        System.out.println("╔═════════════════════════════════════════════════════════╗");
        System.out.println("║                PEMESANAN BERHASIL! ✅                   ║");
        System.out.println("╚═════════════════════════════════════════════════════════╝");
        System.out.println();
        System.out.println("─────────────────── DETAIL PENERBANGAN ───────────────────");
        System.out.printf("Pesawat       : %s\n", p.pesawat);
        System.out.printf("Rute          : %s → %s\n", p.asal, p.tujuan);
        System.out.printf("Tanggal       : %s\n", tanggal);
        System.out.printf("Waktu         : %s WIB\n", waktu);
        System.out.printf("Harga/Tiket   : Rp%s\n", df.format(p.harga));
        System.out.println();
        System.out.println("──────────────────── DETAIL PEMESANAN ────────────────────");
        System.out.printf("Nama Pemesan  : %s\n", nama);
        System.out.printf("Jumlah Tiket  : %d\n", jumlah);
        System.out.printf("Total Biaya   : Rp%,.0f\n", total);
        System.out.printf("Sisa Kursi    : %d\n", p.jumlahKursi);
        System.out.println("═══════════════════════════════════════════════════════════");
        System.out.println("          Terima kasih atas pemesanan Anda! 🙏");
        System.out.println("═══════════════════════════════════════════════════════════");
    }
}