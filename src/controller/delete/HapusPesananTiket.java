package controller.delete;

import controller.SistemTiket;
import model.Pemesanan;
import model.Penerbangan;
import util.Helper;

public class HapusPesananTiket {
    private SistemTiket sistemTiket;

    public HapusPesananTiket(SistemTiket sistemTiket) {
        this.sistemTiket = sistemTiket;
    }

    public void run() {
        System.out.println();
        System.out.println("╔═════════════════════════════════════════════════════════╗");
        System.out.println("║              🗑️  HAPUS PEMESANAN TIKET                  ║");
        System.out.println("╚═════════════════════════════════════════════════════════╝");

        if (sistemTiket.jumlahPemesanan == 0) {
            System.out.println("\n⚠️ Belum ada pemesanan yang bisa dihapus");
            return;
        }

        sistemTiket.view.showDaftarPemesanan(sistemTiket.daftarPemesanan, sistemTiket.daftarPenerbangan, sistemTiket.jumlahPemesanan, sistemTiket.jumlahPenerbangan);
        System.out.println();

        // Cari pemesanan tiket berdasarkan ID
        Pemesanan pemesananDihapus = null;
        int indexPemesanan = -1;
        while (pemesananDihapus == null) {
            int idPemesanan = Helper.inputId(sistemTiket.input, "Masukkan ID pemesanan yang akan dihapus: ");
            for (int i = 0; i < sistemTiket.jumlahPemesanan; i++) {
                if (sistemTiket.daftarPemesanan[i].idPemesanan == idPemesanan) {
                    pemesananDihapus = sistemTiket.daftarPemesanan[i];
                    indexPemesanan = i;
                    break;
                }
            }

            if (pemesananDihapus == null) {
                System.out.println("❌ ID pemesanan tidak ditemukan! Silakan coba lagi");
            }
        }

        // Detail pemesanan tiket yang mau dihapus
        Penerbangan penerbanganTerkait = sistemTiket.cariById(pemesananDihapus.idPenerbangan);
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
        boolean konfirmasi = Helper.inputYesNo(sistemTiket.input, "⚠️  Yakin ingin menghapus pemesanan ini? (y/n): ");

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
        for (int i = indexPemesanan; i < sistemTiket.jumlahPemesanan - 1; i++) {
            sistemTiket.daftarPemesanan[i] = sistemTiket.daftarPemesanan[i + 1];
        }

        sistemTiket.daftarPemesanan[sistemTiket.jumlahPemesanan - 1] = null;
        sistemTiket.jumlahPemesanan--;
        System.out.println();
        System.out.println("╔═════════════════════════════════════════════════════════╗");
        System.out.println("║          ✅ PEMESANAN BERHASIL DIHAPUS!                 ║");
        System.out.println("╚═════════════════════════════════════════════════════════╝");
    }
}