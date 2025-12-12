package controller.delete;

import controller.SistemTiket;
import util.Helper;

public class HapusPenerbangan {
    private SistemTiket sistem;
    public HapusPenerbangan(SistemTiket sistem) {
        this.sistem = sistem;
    }

    public void run() {
        System.out.println();
        System.out.println("╔═════════════════════════════════════════════════════════╗");
        System.out.println("║                 🗑️ HAPUS PENERBANGAN                    ║");
        System.out.println("╚═════════════════════════════════════════════════════════╝");
        sistem.view.showDaftarPenerbangan(sistem.daftarPenerbangan, sistem.jumlahPenerbangan);

        if (sistem.jumlahPenerbangan == 0) {
            System.out.println("\n⚠️ Belum ada penerbangan yang bisa dihapus.");
            return;
        }

        // Cari penerbangan berdasarkan ID
        System.out.println();
        int idx = -1;
        while (idx == -1) {
            int id = Helper.inputId(sistem.input, "Masukkan ID penerbangan yang akan dihapus: ");
            for (int i = 0; i < sistem.jumlahPenerbangan; i++) {
                if (sistem.daftarPenerbangan[i].id == id) {
                    idx = i;
                    break;
                }
            }

            if (idx == -1) {
                System.out.println("❌ ID Penerbangan tidak ditemukan. Silakan coba lagi!");
            }
        }

        // Data penerbangan yang akan dihapus
        System.out.println("┌──────────────────────────────────────────────────────────┐");
        System.out.println("│          🗑️ DATA PENERBANGAN YANG AKAN DIHAPUS           │");
        System.out.println("└──────────────────────────────────────────────────────────┘");
        System.out.println(sistem.daftarPenerbangan[idx]);
        System.out.println();

        // Hitung jumlah pemesanan tiket yang terhubung ke penerbangan ini
        int jumlahPemesananTerkait = 0;
        for (int i = 0; i < sistem.jumlahPemesanan; i++) {
            if (sistem.daftarPemesanan[i].idPenerbangan == sistem.daftarPenerbangan[idx].id) {
                jumlahPemesananTerkait++;
            }
        }

        // Peringatan jika ada pemesanan tiket yang terhubung ke penerbangan yang akan dihapus
        if (jumlahPemesananTerkait > 0) {
            System.out.println("⚠️ PERINGATAN: Ada " + jumlahPemesananTerkait + " pemesanan tiket di penerbangan ini!");
            System.out.println("Data pemesanan tiket akan ikut terhapus!");
            System.out.println();
        }

        // Konfirmasi hapus
        boolean konfirmasi = Helper.inputYesNo(sistem.input, "⚠️  Yakin ingin menghapus penerbangan ini? (y/n): ");
        if (konfirmasi) {
            int idPenerbanganYangDihapus = sistem.daftarPenerbangan[idx].id;

            // Hapus semua pemesanan tiket yang terhubung ke penerbangan ini
            for (int i = sistem.jumlahPemesanan - 1; i >= 0; i--) {
                if (sistem.daftarPemesanan[i].idPenerbangan == idPenerbanganYangDihapus) {
                    for (int j = i; j < sistem.jumlahPemesanan - 1; j++) {
                        sistem.daftarPemesanan[j] = sistem.daftarPemesanan[j + 1];
                    }
                    sistem.jumlahPemesanan--;
                }
            }

            // Hapus penerbangan
            for (int i = idx; i < sistem.jumlahPenerbangan - 1; i++) {
                sistem.daftarPenerbangan[i] = sistem.daftarPenerbangan[i + 1];
            }

            sistem.jumlahPenerbangan--;
            if (jumlahPemesananTerkait > 0) {
                System.out.println("✅ Penerbangan dan " + jumlahPemesananTerkait + " pemesanan tiket di penerbangan ini berhasil dihapus!");
            } else {
                System.out.println("✅ Penerbangan berhasil dihapus!");
            }
        } else {
            System.out.println("❌ Penghapusan dibatalkan.");
        }
    }
}