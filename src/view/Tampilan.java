package view;

import model.Penerbangan;
import model.Pemesanan;

public class Tampilan {
    public void menuUtama() {
        System.out.println();
        System.out.println("╔═════════════════════════════════════════════════╗");
        System.out.println("║           ✈️ SISTEM PEMESANAN TIKET             ║");
        System.out.println("║              TRANSPORTASI UDARA ✈️              ║");
        System.out.println("╚═════════════════════════════════════════════════╝");
        System.out.println();
        System.out.println("╔═════════════════════════════════════════════════╗");
        System.out.println("║  1. 📋 Lihat Semua Penerbangan                  ║");
        System.out.println("║  2. 🔍 Cari Penerbangan                         ║");
        System.out.println("║  3. ➕ Tambah Penerbangan                       ║");
        System.out.println("║  4. ✏️ Edit Penerbangan                         ║");
        System.out.println("║  5. 🗑️ Hapus Penerbangan                        ║");
        System.out.println("║  6. 🎫 Pesan Tiket                              ║");
        System.out.println("║  7. 📜 Lihat Riwayat Pemesanan                  ║");
        System.out.println("║  0. 🚪 Keluar                                   ║");
        System.out.println("╚═════════════════════════════════════════════════╝");
        System.out.print("➤ Pilih menu: ");
    }

    public void showDaftarPenerbangan(Penerbangan[] daftar, int jumlah) {
        if (jumlah == 0) {
            System.out.println("\n⚠️ Belum ada penerbangan");
            return;
        }
        System.out.println();
        System.out.println("╔═════════════════════════════════════════════════════════╗");
        System.out.println("║                 📋 DAFTAR PENERBANGAN                   ║");
        System.out.println("╚═════════════════════════════════════════════════════════╝");
        for (int i = 0; i < jumlah; i++) {
            System.out.println(daftar[i]);
        }
    }

    public void showDaftarPemesanan(Pemesanan[] daftar, Penerbangan[] penerbangan, int jumlahPesan, int jumlahTerbang) {
        if (jumlahPesan == 0) {
            System.out.println("\n⚠️ Belum ada pemesanan");
            return;
        }
        System.out.println();
        System.out.println("╔═════════════════════════════════════════════════════════╗");
        System.out.println("║                 📜 RIWAYAT PEMESANAN                    ║");
        System.out.println("╚═════════════════════════════════════════════════════════╝");
        for (int i = 0; i < jumlahPesan; i++) {
            Pemesanan pem = daftar[i];
            Penerbangan p = null;
            for (int j = 0; j < jumlahTerbang; j++) {
                if (penerbangan[j].id == pem.idPenerbangan) {
                    p = penerbangan[j];
                    break;
                }
            }
            System.out.println(pem.ringkasan(p));
        }
    }
}
