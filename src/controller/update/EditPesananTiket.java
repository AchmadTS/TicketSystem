package controller.update;

import controller.SistemTiket;
import model.*;
import util.Helper;
import java.text.DecimalFormat;

public class EditPesananTiket {
    private SistemTiket sistem;

    public EditPesananTiket(SistemTiket sistem) {
        this.sistem = sistem;
    }

    public void run() {
        System.out.println();
        System.out.println("╔═════════════════════════════════════════════════════════╗");
        System.out.println("║              ✏️  EDIT PEMESANAN TIKET                   ║");
        System.out.println("╚═════════════════════════════════════════════════════════╝");

        if (sistem.jumlahPemesanan == 0) {
            System.out.println("\n⚠️ Belum ada pemesanan yang bisa diedit");
            return;
        }

        sistem.view.showDaftarPemesanan(sistem.daftarPemesanan, sistem.daftarPenerbangan, sistem.jumlahPemesanan, sistem.jumlahPenerbangan);
        System.out.println();
        Pemesanan pemesanan = null;
        while (pemesanan == null) {
            int idPemesanan = Helper.inputId(sistem.input, "Masukkan ID pemesanan yang akan diedit: ");

            for (int i = 0; i < sistem.jumlahPemesanan; i++) {
                if (sistem.daftarPemesanan[i].idPemesanan == idPemesanan) {
                    pemesanan = sistem.daftarPemesanan[i];
                    break;
                }
            }

            if (pemesanan == null) {
                System.out.println("❌ ID pemesanan tidak ditemukan! Silakan coba lagi");
            }
        }

        Penerbangan penerbanganLama = sistem.cariById(pemesanan.idPenerbangan);
        if (penerbanganLama == null) {
            System.out.println("❌ Data penerbangan tidak ditemukan!");
            return;
        }

        DecimalFormat df = new DecimalFormat("#,###");
        System.out.println();
        System.out.println("┌──────────────────────────────────────────────────────────┐");
        System.out.println("│                 📋 DATA PEMESANAN SAAT INI               │");
        System.out.println("└──────────────────────────────────────────────────────────┘");
        System.out.println("ID Pemesanan  : " + pemesanan.idPemesanan);
        System.out.println("Nama Pemesan  : " + pemesanan.namaPelanggan);
        System.out.println("Jumlah Tiket  : " + pemesanan.jumlah);
        System.out.println("Harga/Tiket   : Rp" + df.format(penerbanganLama.harga));
        System.out.println("Total Harga   : Rp" + df.format(pemesanan.totalHarga));
        System.out.println("ID Penerbangan  : " + penerbanganLama.id);
        System.out.println("Penerbangan   : " + penerbanganLama.pesawat + " (" + penerbanganLama.asal + " → " + penerbanganLama.tujuan + ")");
        System.out.println();
        System.out.println("--- Edit Data ---");
        System.out.println();

        System.out.print("Nama pemesan baru [" + pemesanan.namaPelanggan + "]: ");
        String namaBaru = sistem.input.nextLine().trim();
        if (!namaBaru.isEmpty()) {
            pemesanan.namaPelanggan = namaBaru;
        }

        boolean gantiPenerbangan = Helper.inputYesNo(sistem.input, "Ganti penerbangan? (y/n): ");
        Penerbangan penerbanganBaru = penerbanganLama; // Default tetap penerbangan lama
        if (gantiPenerbangan) {
            System.out.println();
            System.out.println("📋 Daftar Penerbangan Tersedia:");
            sistem.view.showDaftarPenerbangan(sistem.daftarPenerbangan, sistem.jumlahPenerbangan);
            System.out.println();

            Penerbangan p = null;
            while (p == null) {
                int idPenerbanganBaru = Helper.inputId(sistem.input, "Masukkan ID penerbangan baru: ");
                p = sistem.cariById(idPenerbanganBaru);
                if (p == null) {
                    System.out.println("❌ ID penerbangan tidak ditemukan! Silakan coba lagi");
                }
            }

            if (pemesanan.jumlah > p.jumlahKursi) {
                System.out.println("❌ Kursi tidak cukup di penerbangan baru! Tersedia: " + p.jumlahKursi + " kursi");
                System.out.println("⚠️ Penerbangan tidak diubah.");
            } else {
                penerbanganLama.jumlahKursi += pemesanan.jumlah;
                System.out.println("✅ Kursi dikembalikan ke penerbangan lama: +" + pemesanan.jumlah + " kursi");
                p.jumlahKursi -= pemesanan.jumlah;
                System.out.println("✅ Kursi dipesan dari penerbangan baru: -" + pemesanan.jumlah + " kursi");
                pemesanan.idPenerbangan = p.id;
                pemesanan.totalHarga = pemesanan.jumlah * p.harga;
                penerbanganBaru = p;
                System.out.println("✅ Penerbangan berhasil diganti!");
            }
        }

        boolean editJumlah = Helper.inputYesNo(sistem.input, "Edit jumlah tiket? (y/n): ");
        if (editJumlah) {
            boolean jumlahValid = false;
            while (!jumlahValid) {
                int jumlahBaru = Helper.inputInteger(sistem.input, "Masukkan jumlah tiket baru: ", 1, Integer.MAX_VALUE);
                int selisih = jumlahBaru - pemesanan.jumlah;
                if (selisih > 0) {
                    if (selisih > penerbanganBaru.jumlahKursi) {
                        System.out.println("❌ Kursi tidak cukup! Tersedia: " + penerbanganBaru.jumlahKursi + " kursi");
                        System.out.println("   Silakan masukkan jumlah yang lebih sedikit.");
                    } else {
                        penerbanganBaru.jumlahKursi -= selisih;
                        pemesanan.jumlah = jumlahBaru;
                        pemesanan.totalHarga = pemesanan.jumlah * penerbanganBaru.harga;
                        System.out.println("✅ Jumlah tiket berhasil diperbarui!");
                        System.out.println("   Kursi penerbangan berkurang: " + selisih + " kursi");
                        jumlahValid = true;
                    }
                } else if (selisih < 0) {
                    penerbanganBaru.jumlahKursi += Math.abs(selisih);
                    pemesanan.jumlah = jumlahBaru;
                    pemesanan.totalHarga = pemesanan.jumlah * penerbanganBaru.harga;
                    System.out.println("✅ Jumlah tiket berhasil diperbarui!");
                    System.out.println("   Kursi penerbangan bertambah: " + Math.abs(selisih) + " kursi");
                    jumlahValid = true;
                } else {
                    System.out.println("ℹ️  Jumlah tiket tidak berubah.");
                    jumlahValid = true;
                }
            }
        }

        System.out.println();
        System.out.println("╔═════════════════════════════════════════════════════════╗");
        System.out.println("║          ✅ PEMESANAN BERHASIL DIPERBARUI!              ║");
        System.out.println("╚═════════════════════════════════════════════════════════╝");
        System.out.println();
        System.out.println("┌──────────────────────────────────────────────────────────┐");
        System.out.println("│                   📋 DATA PEMESANAN BARU                 │");
        System.out.println("└──────────────────────────────────────────────────────────┘");
        System.out.println("ID Pemesanan  : " + pemesanan.idPemesanan);
        System.out.println("Nama Pemesan  : " + pemesanan.namaPelanggan);
        System.out.println("Jumlah Tiket  : " + pemesanan.jumlah);
        System.out.println("Harga/Tiket   : Rp" + df.format(penerbanganBaru.harga));
        System.out.println("Total Harga   : Rp" + df.format(pemesanan.totalHarga));
        System.out.println("Penerbangan   : " + penerbanganBaru.pesawat + " (" + penerbanganBaru.asal + " → " + penerbanganBaru.tujuan + ")");
        System.out.println("Kursi Tersedia: " + penerbanganBaru.jumlahKursi);
        System.out.println("══════════════════════════════════════════════════════════");
    }
}