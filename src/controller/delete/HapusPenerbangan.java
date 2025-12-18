package controller.delete;

import controller.SistemTiket;
import util.Helper;

public class HapusPenerbangan {
    public static void run(SistemTiket sistemTiket) {
        System.out.println();
        System.out.println("╔═════════════════════════════════════════════════════════╗");
        System.out.println("║                 🗑️ HAPUS PENERBANGAN                    ║");
        System.out.println("╚═════════════════════════════════════════════════════════╝");
        sistemTiket.view.showDaftarPenerbangan(sistemTiket.daftarPenerbangan, sistemTiket.jumlahPenerbangan);

        if (sistemTiket.jumlahPenerbangan == 0) {
            System.out.println("\n⚠️ Belum ada penerbangan yang bisa dihapus");
            return;
        }

        /** Cari penerbangan berdasarkan ID */
        System.out.println();
        int idx = -1;
        while (idx == -1) {
            int id = Helper.inputId(sistemTiket.input, "Masukkan ID penerbangan yang akan dihapus: ");
            for (int i = 0; i < sistemTiket.jumlahPenerbangan; i++) {
                if (sistemTiket.daftarPenerbangan[i].id == id) {
                    idx = i;
                    break;
                }
            }

            if (idx == -1) {
                System.out.println("❌ ID Penerbangan tidak ditemukan. Silakan coba lagi!");
            }
        }

        /** Data penerbangan yang akan dihapus */
        System.out.println("┌──────────────────────────────────────────────────────────┐");
        System.out.println("│          🗑️ DATA PENERBANGAN YANG AKAN DIHAPUS           │");
        System.out.println("└──────────────────────────────────────────────────────────┘");
        System.out.println(sistemTiket.daftarPenerbangan[idx]);
        System.out.println();

        /** Hitung jumlah pemesanan tiket yang terhubung ke penerbangan ini */
        int jumlahPemesananTerkait = 0;
        for (int i = 0; i < sistemTiket.jumlahPemesanan; i++) {
            if (sistemTiket.daftarPemesanan[i].idPenerbangan == sistemTiket.daftarPenerbangan[idx].id) {
                jumlahPemesananTerkait++;
            }
        }

        /** Peringatan jika ada pemesanan tiket yang terhubung ke penerbangan yang akan dihapus */
        if (jumlahPemesananTerkait > 0) {
            System.out.println("⚠️ PERINGATAN: Ada " + jumlahPemesananTerkait + " pemesanan tiket di penerbangan ini!");
            System.out.println("Data pemesanan tiket akan ikut terhapus!");
            System.out.println();
        }

        /** Konfirmasi hapus */
        boolean konfirmasi = Helper.inputYesNo(sistemTiket.input, "⚠️  Yakin ingin menghapus penerbangan ini? (y/n): ");
        if (konfirmasi) {
            int idPenerbanganYangDihapus = sistemTiket.daftarPenerbangan[idx].id;

            /** Hapus semua pemesanan tiket yang terhubung ke penerbangan ini  */
            for (int i = sistemTiket.jumlahPemesanan - 1; i >= 0; i--) {
                if (sistemTiket.daftarPemesanan[i].idPenerbangan == idPenerbanganYangDihapus) {
                    for (int j = i; j < sistemTiket.jumlahPemesanan - 1; j++) {
                        sistemTiket.daftarPemesanan[j] = sistemTiket.daftarPemesanan[j + 1];
                    }
                    sistemTiket.jumlahPemesanan--;
                }
            }

            /** Hapus penerbangan */
            for (int i = idx; i < sistemTiket.jumlahPenerbangan - 1; i++) {
                sistemTiket.daftarPenerbangan[i] = sistemTiket.daftarPenerbangan[i + 1];
            }

            sistemTiket.jumlahPenerbangan--;
            if (jumlahPemesananTerkait > 0) {
                System.out.println("✅ Penerbangan dan " + jumlahPemesananTerkait + " pemesanan tiket di penerbangan ini berhasil dihapus!");
            } else {
                System.out.println("✅ Penerbangan berhasil dihapus!");
            }
        } else {
            System.out.println("❌ Penghapusan dibatalkan");
        }
    }
}