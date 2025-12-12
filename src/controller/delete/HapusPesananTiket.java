package controller.delete;

import controller.SistemTiket;
import model.Pemesanan;
import model.Penerbangan;
import util.Helper;

public class HapusPesananTiket {
    private SistemTiket sistem;

    public HapusPesananTiket(SistemTiket sistem) {
        this.sistem = sistem;
    }

    public void run() {
        System.out.println();
        System.out.println("╔═════════════════════════════════════════════════════════╗");
        System.out.println("║              🗑️  HAPUS PEMESANAN TIKET                  ║");
        System.out.println("╚═════════════════════════════════════════════════════════╝");

        if (sistem.jumlahPemesanan == 0) {
            System.out.println("\n⚠️ Belum ada pemesanan yang bisa dihapus");
            return;
        }

        sistem.view.showDaftarPemesanan(sistem.daftarPemesanan, sistem.daftarPenerbangan, sistem.jumlahPemesanan, sistem.jumlahPenerbangan);
        System.out.println();

        // Cari pemesanan tiket berdasarkan ID
        Pemesanan pemesananDihapus = null;
        int indexPemesanan = -1;
        while (pemesananDihapus == null) {
            int idPemesanan = Helper.inputId(sistem.input, "Masukkan ID pemesanan yang akan dihapus: ");
            for (int i = 0; i < sistem.jumlahPemesanan; i++) {
                if (sistem.daftarPemesanan[i].idPemesanan == idPemesanan) {
                    pemesananDihapus = sistem.daftarPemesanan[i];
                    indexPemesanan = i;
                    break;
                }
            }

            if (pemesananDihapus == null) {
                System.out.println("❌ ID pemesanan tidak ditemukan! Silakan coba lagi");
            }
        }

        // Detail pemesanan tiket yang mau dihapus
        Penerbangan penerbanganTerkait = sistem.cariById(pemesananDihapus.idPenerbangan);
        System.out.println();
        System.out.println("┌──────────────────────────────────────────────────────────┐");
        System.out.println("│           📋 DETAIL PEMESANAN YANG AKAN DIHAPUS          │");
        System.out.println("└──────────────────────────────────────────────────────────┘");
        System.out.println("ID Pemesanan  : " + pemesananDihapus.idPemesanan);
        System.out.println("Nama Pemesan  : " + pemesananDihapus.namaPelanggan);
        System.out.println("Jumlah Tiket  : " + pemesananDihapus.jumlah);
        System.out.println("Total Harga   : Rp" + String.format("%,.0f", pemesananDihapus.totalHarga));

        if (penerbanganTerkait != null) {
            System.out.println("Penerbangan   : " + penerbanganTerkait.pesawat + " (" + penerbanganTerkait.asal + " → " + penerbanganTerkait.tujuan + ")");
            System.out.println();
            System.out.println("ℹ️  Info: " + pemesananDihapus.jumlah + " kursi akan dikembalikan ke penerbangan ini.");
        }

        // Konfirmasi hapus
        System.out.println();
        boolean konfirmasi = Helper.inputYesNo(sistem.input, "⚠️  Yakin ingin menghapus pemesanan ini? (y/n): ");

        if (!konfirmasi) {
            System.out.println("❌ Penghapusan dibatalkan.");
            return;
        }

        // Tambahkan kursi ke penerbangan
        if (penerbanganTerkait != null) {
            int kursiSebelum = penerbanganTerkait.jumlahKursi;
            penerbanganTerkait.jumlahKursi += pemesananDihapus.jumlah;
            System.out.println("✅ Kursi dikembalikan: " + kursiSebelum + " → " + penerbanganTerkait.jumlahKursi);
        }

        // Hapus pemesanan tiket
        for (int i = indexPemesanan; i < sistem.jumlahPemesanan - 1; i++) {
            sistem.daftarPemesanan[i] = sistem.daftarPemesanan[i + 1];
        }

        sistem.daftarPemesanan[sistem.jumlahPemesanan - 1] = null;
        sistem.jumlahPemesanan--;
        System.out.println();
        System.out.println("╔═════════════════════════════════════════════════════════╗");
        System.out.println("║          ✅ PEMESANAN BERHASIL DIHAPUS!                 ║");
        System.out.println("╚═════════════════════════════════════════════════════════╝");
    }
}