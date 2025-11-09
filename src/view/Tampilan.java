package view;

import model.Penerbangan;
import model.Pemesanan;

public class Tampilan {

    public void menuUtama() {
        System.out.println();
        System.out.println("===== SISTEM PEMESANAN TIKET TRANSPORTASI =====");
        System.out.println("1. Lihat semua penerbangan (urutan berdasarkan tanggal, bulan dan tahun keberangkatan)");
        System.out.println("2. Cari penerbangan");
        System.out.println("3. Tambah penerbangan");
        System.out.println("4. Edit penerbangan");
        System.out.println("5. Hapus penerbangan");
        System.out.println("6. Pesan tiket");
        System.out.println("7. Lihat riwayat pemesanan");
        System.out.println("0. Keluar");
        System.out.print("Pilih menu: ");
    }

    public void showDaftarPenerbangan(Penerbangan[] daftar, int jumlah) {
        if (jumlah == 0) {
            System.out.println("Belum ada penerbangan.");
            return;
        }
        System.out.println();
        System.out.println("--- Daftar Penerbangan ---");
        for (int i = 0; i < jumlah; i++) {
            System.out.println(daftar[i]);
        }
    }

    public void showDaftarPemesanan(Pemesanan[] daftar, Penerbangan[] penerbangan, int jumlahPesan, int jumlahTerbang) {
        if (jumlahPesan == 0) {
            System.out.println("Belum ada pemesanan.");
            return;
        }
        System.out.println();
        System.out.println("--- Riwayat Pemesanan ---");
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
