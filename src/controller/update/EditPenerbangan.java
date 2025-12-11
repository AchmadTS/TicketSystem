package controller.update;

import controller.SistemTiket;
import model.Penerbangan;
import util.Helper;
import java.text.DecimalFormat;

public class EditPenerbangan {
    private SistemTiket sistem;
    public EditPenerbangan(SistemTiket sistem) {
        this.sistem = sistem;
    }

    public void run() {
        System.out.println();
        System.out.println("╔═════════════════════════════════════════════════════════╗");
        System.out.println("║                 ✏️ EDIT PENERBANGAN                     ║");
        System.out.println("╚═════════════════════════════════════════════════════════╝");
        sistem.view.showDaftarPenerbangan(sistem.daftarPenerbangan, sistem.jumlahPenerbangan);

        if (sistem.jumlahPenerbangan == 0) {
            System.out.println("\n⚠️ Belum ada penerbangan yang bisa diedit");
            return;
        }

        System.out.println();
        Penerbangan p = null;
        while (p == null) {
            int id = Helper.inputId(sistem.input, "Masukkan ID penerbangan yang akan diedit: ");
            p = sistem.cariById(id);
            if (p == null) {
                System.out.println("❌ ID penerbangan tidak ada! Silakan coba lagi");
            }
        }

        int jumlahPemesananTerkait = 0;
        for (int i = 0; i < sistem.jumlahPemesanan; i++) {
            if (sistem.daftarPemesanan[i].idPenerbangan == p.id) {
                jumlahPemesananTerkait++;
            }
        }

        if (jumlahPemesananTerkait > 0) {
            System.out.println("\n⚠️ PERINGATAN: Ada " + jumlahPemesananTerkait + " pemesanan terkait penerbangan ini!");
            System.out.println("Mengubah data penerbangan akan mempengaruhi pemesanan yang sudah ada.");

            boolean lanjutkan = Helper.inputYesNo(sistem.input, "Lanjutkan edit? (y/n): ");
            if (!lanjutkan) {
                System.out.println("❌ Edit dibatalkan.");
                return;
            }
        }

        System.out.println("┌──────────────────────────────────────────────────────────┐");
        System.out.println("│                 📋 DATA SAAT INI:                        │");
        System.out.println("└──────────────────────────────────────────────────────────┘");
        System.out.println(p);
        System.out.println();
        System.out.println("--- Edit Data (tekan ENTER untuk skip) ---");
        System.out.println();

        System.out.print("Nama pesawat baru [" + p.pesawat + "]: ");
        String pesawat = sistem.input.nextLine().trim();
        if (!pesawat.isEmpty()) {
            p.pesawat = pesawat;
        }

        System.out.print("Asal baru [" + p.asal + "]: ");
        String asal = sistem.input.nextLine().trim();
        if (!asal.isEmpty()) {
            p.asal = asal;
        }

        System.out.print("Tujuan baru [" + p.tujuan + "]: ");
        String tujuan = sistem.input.nextLine().trim();
        if (!tujuan.isEmpty()) {
            p.tujuan = tujuan;
        }

        DecimalFormat df = new DecimalFormat("#,###");
        double hargaLama = p.harga;
        System.out.print("Harga baru [Rp" + df.format(p.harga) + "]: ");
        String inputHarga = sistem.input.nextLine().trim();

        if (!inputHarga.isEmpty()) {
            if (Helper.isHargaValid(inputHarga)) {
                double hargaBaru = Double.parseDouble(inputHarga);
                if (hargaBaru > 0) {
                    if (jumlahPemesananTerkait > 0) {
                        System.out.println("\n⚠️ PERHATIAN: Perubahan harga akan mempengaruhi " + jumlahPemesananTerkait + " pemesanan pada penerbangan ini!");
                        System.out.println("Harga lama: Rp" + df.format(hargaLama));
                        System.out.println("Harga baru: Rp" + df.format(hargaBaru));
                        System.out.println();
                        System.out.println("┌──────────────────────────────────────────────────────────┐");
                        System.out.println("│       📋 PREVIEW PERUBAHAN TOTAL HARGA PEMESANAN:        │");
                        System.out.println("└──────────────────────────────────────────────────────────┘");

                        for (int i = 0; i < sistem.jumlahPemesanan; i++) {
                            if (sistem.daftarPemesanan[i].idPenerbangan == p.id) {
                                double totalLama = sistem.daftarPemesanan[i].totalHarga;
                                double totalBaru = sistem.daftarPemesanan[i].jumlah * hargaBaru;
                                System.out.println("• Pemesanan #" + sistem.daftarPemesanan[i].idPemesanan + " (" + sistem.daftarPemesanan[i].namaPelanggan + ") | " + "Rp" + df.format(totalLama) + " → Rp" + df.format(totalBaru));
                            }
                        }

                        System.out.println();
                        boolean konfirmasiHarga = Helper.inputYesNo(sistem.input, "⚠️  Yakin ingin mengubah harga dan update semua pemesanan? (y/n): ");
                        if (konfirmasiHarga) {
                            p.harga = hargaBaru;
                            for (int i = 0; i < sistem.jumlahPemesanan; i++) {
                                if (sistem.daftarPemesanan[i].idPenerbangan == p.id) {
                                    sistem.daftarPemesanan[i].totalHarga = sistem.daftarPemesanan[i].jumlah * hargaBaru;
                                }
                            }
                            System.out.println("✅ Harga penerbangan dan total harga pemesanan berhasil diperbarui!");
                        } else {
                            System.out.println("❌ Perubahan harga dibatalkan.");
                        }
                    } else {
                        p.harga = hargaBaru;
                        System.out.println("✅ Harga penerbangan berhasil diperbarui!");
                    }
                } else {
                    System.out.println("⚠️ Harga harus > 0! Harga tidak diubah");
                }
            } else {
                System.out.println("⚠️ Format harga tidak valid! Harga tidak diubah");
            }
        }

        System.out.print("Jumlah kursi baru [" + p.jumlahKursi + "]: ");
        String inputKursi = sistem.input.nextLine().trim();
        if (!inputKursi.isEmpty()) {
            if (Helper.isAngka(inputKursi)) {
                int kursiBaru = Integer.parseInt(inputKursi);
                if (kursiBaru > 0) {
                    p.jumlahKursi = kursiBaru;
                } else {
                    System.out.println("⚠️ Jumlah kursi harus lebih dari 0! Kursi tidak diubah");
                }
            } else {
                System.out.println("⚠️ Format jumlah kursi tidak valid! Kursi tidak diubah");
            }
        }

        boolean editTanggal = Helper.inputYesNo(sistem.input, "Edit tanggal keberangkatan? (y/n): ");
        if (editTanggal) {
            int[] tanggal = Helper.inputTanggal(sistem.input, "Masukkan waktu baru (contoh: 28 Februari 2024): ");

            p.hari = tanggal[0];
            p.bulan = tanggal[1];
            p.tahun = tanggal[2];

            p.jam = Helper.inputInteger(sistem.input, "Masukkan jam keberangkatan baru (0-23): ", 0, 23);
            p.menit = Helper.inputInteger(sistem.input, "Masukkan menit keberangkatan baru (0-59): ", 0, 59);
        }

        System.out.println();
        System.out.println("✅ Data berhasil diperbarui!");
        System.out.println("\n📋 Data baru:");
        System.out.println(p);
    }
}