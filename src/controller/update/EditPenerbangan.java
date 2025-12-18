package controller.update;

import controller.SistemTiket;
import model.Penerbangan;
import util.Helper;

import java.text.DecimalFormat;

public class EditPenerbangan {
    public static void run(SistemTiket sistemTiket) {
        System.out.println();
        System.out.println("╔═════════════════════════════════════════════════════════╗");
        System.out.println("║                 ✏️ EDIT PENERBANGAN                     ║");
        System.out.println("╚═════════════════════════════════════════════════════════╝");
        sistemTiket.view.showDaftarPenerbangan(sistemTiket.daftarPenerbangan, sistemTiket.jumlahPenerbangan);

        if (sistemTiket.jumlahPenerbangan == 0) {
            System.out.println("\n⚠️ Belum ada penerbangan yang bisa diedit");
            return;
        }

        /** Cari penerbangan berdasarkan ID */
        System.out.println();
        Penerbangan p = null;
        while (p == null) {
            int id = Helper.inputId(sistemTiket.input, "Masukkan ID penerbangan yang akan diedit: ");
            p = sistemTiket.cariById(id);
            if (p == null) {
                System.out.println("❌ ID penerbangan tidak ada! Silakan coba lagi");
            }
        }

        /** Cek ada/tidak pemesanan tiket ke penerbangan ini */
        int jumlahPemesananTerkait = 0;
        for (int i = 0; i < sistemTiket.jumlahPemesanan; i++) {
            if (sistemTiket.daftarPemesanan[i].idPenerbangan == p.id) {
                jumlahPemesananTerkait++;
            }
        }

        /** Tampilkan peringatan kalau ada pemesanan tiket ke penerbangan ini */
        if (jumlahPemesananTerkait > 0) {
            System.out.println("\n⚠️ PERINGATAN: Ada " + jumlahPemesananTerkait + " pemesanan terkait penerbangan ini!");
            System.out.println("Mengubah data penerbangan akan mempengaruhi pemesanan yang sudah ada.");

            boolean lanjut = Helper.inputYesNo(sistemTiket.input, "Lanjutkan edit? (y/n): ");
            if (!lanjut) {
                System.out.println("❌ Edit dibatalkan.");
                return;
            }
        }

        /** Tampilkan data penerbangan sebelum di edit */
        System.out.println("┌──────────────────────────────────────────────────────────┐");
        System.out.println("│                 📋 DATA SAAT INI:                        │");
        System.out.println("└──────────────────────────────────────────────────────────┘");
        System.out.println(p);
        System.out.println();
        System.out.println("--- Edit Data (tekan ENTER untuk skip) ---");
        System.out.println();

        System.out.print("Nama pesawat baru [" + p.pesawat + "]: ");
        String pesawat = sistemTiket.input.nextLine().trim();
        if (!pesawat.isEmpty()) {
            p.pesawat = pesawat;
        }

        System.out.print("Asal baru [" + p.asal + "]: ");
        String asal = sistemTiket.input.nextLine().trim();
        if (!asal.isEmpty()) {
            p.asal = asal;
        }

        System.out.print("Tujuan baru [" + p.tujuan + "]: ");
        String tujuan = sistemTiket.input.nextLine().trim();
        if (!tujuan.isEmpty()) {
            p.tujuan = tujuan;
        }

        /** Edit harga (harus konfirmasi kalau ada pemesanan tiket ke penerbangan ini) */
        DecimalFormat df = new DecimalFormat("#,###");
        double hargaLama = p.harga;
        System.out.print("Harga baru [Rp" + df.format(p.harga) + "]: ");
        String inputHarga = sistemTiket.input.nextLine().trim();

        if (!inputHarga.isEmpty()) {
            if (Helper.isHargaValid(inputHarga)) {
                double hargaBaru = Double.parseDouble(inputHarga);
                if (hargaBaru > 0) {
                    if (jumlahPemesananTerkait > 0) {
                        /** Preview perubahan total harga pemesanan tiket */
                        System.out.println("\n⚠️ PERHATIAN: Perubahan harga akan mempengaruhi " + jumlahPemesananTerkait + " pemesanan pada penerbangan ini!");
                        System.out.println("Harga lama: Rp" + df.format(hargaLama));
                        System.out.println("Harga baru: Rp" + df.format(hargaBaru));
                        System.out.println();
                        System.out.println("┌──────────────────────────────────────────────────────────┐");
                        System.out.println("│       📋 PREVIEW PERUBAHAN TOTAL HARGA PEMESANAN:        │");
                        System.out.println("└──────────────────────────────────────────────────────────┘");

                        for (int i = 0; i < sistemTiket.jumlahPemesanan; i++) {
                            if (sistemTiket.daftarPemesanan[i].idPenerbangan == p.id) {
                                double totalLama = sistemTiket.daftarPemesanan[i].totalHarga;
                                double totalBaru = sistemTiket.daftarPemesanan[i].jumlah * hargaBaru;
                                System.out.println("• Pemesanan #" + sistemTiket.daftarPemesanan[i].idPemesanan + " (" + sistemTiket.daftarPemesanan[i].namaPelanggan + ") | " + "Rp" + df.format(totalLama) + " → Rp" + df.format(totalBaru));
                            }
                        }

                        /** Konfirmasi perubahan harga tiket yang sudah dipesan */
                        System.out.println();
                        boolean konfirmasiHarga = Helper.inputYesNo(sistemTiket.input, "⚠️  Yakin ingin mengubah harga dan update semua pemesanan? (y/n): ");
                        if (konfirmasiHarga) {
                            p.harga = hargaBaru;
                            /** Update total harga semua pemesanan tiket di penerbangan ini */
                            for (int i = 0; i < sistemTiket.jumlahPemesanan; i++) {
                                if (sistemTiket.daftarPemesanan[i].idPenerbangan == p.id) {
                                    sistemTiket.daftarPemesanan[i].totalHarga = sistemTiket.daftarPemesanan[i].jumlah * hargaBaru;
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

        /** Edit jumlah kursi */
        System.out.print("Jumlah kursi baru [" + p.jumlahKursi + "]: ");
        String inputKursi = sistemTiket.input.nextLine().trim();
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

        /** Edit tanggal keberangkatan (opsional) */
        boolean editTanggal = Helper.inputYesNo(sistemTiket.input, "Edit tanggal keberangkatan? (y/n): ");
        if (editTanggal) {
            int[] tanggal = Helper.inputTanggal(sistemTiket.input, "Masukkan waktu baru (contoh: 28 Februari 2024): ");

            p.hari = tanggal[0];
            p.bulan = tanggal[1];
            p.tahun = tanggal[2];

            p.jam = Helper.inputInteger(sistemTiket.input, "Masukkan jam keberangkatan baru (0-23): ", 0, 23);
            p.menit = Helper.inputInteger(sistemTiket.input, "Masukkan menit keberangkatan baru (0-59): ", 0, 59);
        }

        /** Data yang sudah diperbarui */
        System.out.println();
        System.out.println("✅ Data berhasil diperbarui!");
        System.out.println("\n📋 Data baru:");
        System.out.println(p);
    }
}