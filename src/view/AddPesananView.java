package view;

import model.Penerbangan;
import util.Helper;
import java.text.DecimalFormat;

public class AddPesananView {
    public void showPemesanan(Penerbangan p, String nama, int jumlah, double total) {
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