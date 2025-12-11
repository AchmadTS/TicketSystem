package controller.updade;

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
            int id = 0;
            boolean idValid = false;
            while (!idValid) {
                System.out.print("Masukkan ID penerbangan yang akan diedit: ");
                String inputId = sistem.input.nextLine().trim();

                if (inputId.isEmpty()) {
                    System.out.println("❌ ID tidak boleh kosong!");
                    continue;
                }

                boolean formatValid = true;
                for (int i = 0; i < inputId.length(); i++) {
                    char c = inputId.charAt(i);
                    if (c < '0' || c > '9') {
                        formatValid = false;
                        break;
                    }
                }

                if (!formatValid) {
                    System.out.println("❌ ID harus berupa angka!");
                    continue;
                }

                id = Integer.parseInt(inputId);
                idValid = true;
            }

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

            boolean konfirmasiValid = false;
            while (!konfirmasiValid) {
                System.out.print("Lanjutkan edit? (y/n): ");
                String konfirmasi = sistem.input.nextLine().trim().toLowerCase();

                if (konfirmasi.equals("y")) {
                    konfirmasiValid = true;
                } else if (konfirmasi.equals("n")) {
                    System.out.println("❌ Edit dibatalkan.");
                    return;
                } else {
                    System.out.println("❌ Input tidak valid! Masukkan 'y' atau 'n'.");
                }
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
        boolean hargaBerubah = false;
        if (!inputHarga.isEmpty()) {
            boolean hargaValid = true;
            int jumlahTitik = 0;

            for (int i = 0; i < inputHarga.length(); i++) {
                char c = inputHarga.charAt(i);
                if (c == '.') {
                    jumlahTitik++;
                    if (jumlahTitik > 1) {
                        hargaValid = false;
                        break;
                    }
                } else if (c < '0' || c > '9') {
                    hargaValid = false;
                    break;
                }
            }

            if (hargaValid) {
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
                        boolean konfirmasiHargaValid = false;
                        while (!konfirmasiHargaValid) {
                            System.out.print("⚠️  Yakin ingin mengubah harga dan update semua pemesanan? (y/n): ");
                            String konfirmasiHarga = sistem.input.nextLine().trim().toLowerCase();
                            if (konfirmasiHarga.equals("y")) {
                                konfirmasiHargaValid = true;
                                p.harga = hargaBaru;
                                hargaBerubah = true;
                                for (int i = 0; i < sistem.jumlahPemesanan; i++) {
                                    if (sistem.daftarPemesanan[i].idPenerbangan == p.id) {
                                        sistem.daftarPemesanan[i].totalHarga = sistem.daftarPemesanan[i].jumlah * hargaBaru;
                                    }
                                }
                                System.out.println("✅ Harga penerbangan dan total harga pemesanan berhasil diperbarui!");
                            } else if (konfirmasiHarga.equals("n")) {
                                System.out.println("❌ Perubahan harga dibatalkan.");
                                konfirmasiHargaValid = true;
                            } else {
                                System.out.println("❌ Tidak valid! Masukkan 'y' atau 'n'");
                            }
                        }
                    } else {
                        p.harga = hargaBaru;
                        hargaBerubah = true;
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
            boolean kursiValid = true;

            for (int i = 0; i < inputKursi.length(); i++) {
                char c = inputKursi.charAt(i);
                if (c < '0' || c > '9') {
                    kursiValid = false;
                    break;
                }
            }

            if (kursiValid) {
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

        boolean editTanggalValid = false;
        String editTanggal = "";
        while (!editTanggalValid) {
            System.out.print("Edit tanggal keberangkatan? (y/n): ");
            editTanggal = sistem.input.nextLine().trim().toLowerCase();

            if (editTanggal.equals("y") || editTanggal.equals("n")) {
                editTanggalValid = true;
            } else {
                System.out.println("❌ Tidak valid! Masukkan 'y' atau 'n'");
            }
        }

        if (editTanggal.equals("y")) {
            int hari = 0, bulan = 0, tahun = 0;
            boolean valid = false;

            while (!valid) {
                System.out.print("Masukkan waktu baru (contoh: 28 februari 2024): ");
                String[] waktu = sistem.input.nextLine().split(" ");

                boolean inputBenar = true;
                if (waktu.length != 3) {
                    System.out.println("❌ Format salah! Harus: tanggal bulan tahun");
                    inputBenar = false;
                } else {
                    String strHari = waktu[0];
                    String namaBulanAsli = waktu[1];
                    String namaBulan = namaBulanAsli.toLowerCase();
                    String strTahun = waktu[2];

                    for (char c : strHari.toCharArray()) {
                        if (c < '0' || c > '9') {
                            System.out.println("❌ Tanggal harus angka!");
                            inputBenar = false;
                            break;
                        }
                    }

                    for (char c : strTahun.toCharArray()) {
                        if (c < '0' || c > '9') {
                            System.out.println("❌ Tahun harus angka!");
                            inputBenar = false;
                            break;
                        }
                    }

                    if (inputBenar) {
                        hari = Integer.parseInt(strHari);
                        tahun = Integer.parseInt(strTahun);
                        bulan = Helper.getBulanDariNama(namaBulan);

                        if (bulan == -1) {
                            System.out.println("❌ Nama bulan tidak valid!");
                            inputBenar = false;
                        } else {
                            int maxHari = 31;
                            if (bulan == 2) {
                                boolean kabisat = (tahun % 4 == 0 && tahun % 100 != 0) || (tahun % 400 == 0);
                                maxHari = kabisat ? 29 : 28;
                            } else if (bulan == 4 || bulan == 6 || bulan == 9 || bulan == 11) {
                                maxHari = 30;
                            }

                            if (hari < 1 || hari > maxHari) {
                                System.out.println("❌ Tanggal tidak valid untuk bulan " + namaBulanAsli + "!");
                                inputBenar = false;
                            }
                        }
                    }
                }
                valid = inputBenar;
            }
            p.hari = hari;
            p.bulan = bulan;
            p.tahun = tahun;

            int jam = 0;
            boolean jamValid = false;
            while (!jamValid) {
                System.out.print("Masukkan jam keberangkatan baru (0-23): ");
                String inputJam = sistem.input.nextLine().trim();

                if (inputJam.isEmpty()) {
                    System.out.println("❌ Jam tidak boleh kosong!");
                    continue;
                }

                boolean formatValid = true;
                for (int i = 0; i < inputJam.length(); i++) {
                    char c = inputJam.charAt(i);
                    if (c < '0' || c > '9') {
                        formatValid = false;
                        break;
                    }
                }

                if (!formatValid) {
                    System.out.println("❌ Jam harus berupa angka!");
                    continue;
                }

                jam = Integer.parseInt(inputJam);
                if (jam < 0 || jam > 23) {
                    System.out.println("❌ Jam harus antara 0-23!");
                    continue;
                }
                jamValid = true;
            }

            int menit = 0;
            boolean menitValid = false;
            while (!menitValid) {
                System.out.print("Masukkan menit keberangkatan baru (0-59): ");
                String inputMenit = sistem.input.nextLine().trim();

                if (inputMenit.isEmpty()) {
                    System.out.println("❌ Menit tidak boleh kosong!");
                    continue;
                }

                boolean formatValid = true;
                for (int i = 0; i < inputMenit.length(); i++) {
                    char c = inputMenit.charAt(i);
                    if (c < '0' || c > '9') {
                        formatValid = false;
                        break;
                    }
                }

                if (!formatValid) {
                    System.out.println("❌ Menit harus berupa angka!");
                    continue;
                }

                menit = Integer.parseInt(inputMenit);

                if (menit < 0 || menit > 59) {
                    System.out.println("❌ Menit harus antara 0-59!");
                    continue;
                }

                menitValid = true;
            }
            p.jam = jam;
            p.menit = menit;
        }

        System.out.println();
        System.out.println("✅ Data berhasil diperbarui!");
        System.out.println("\n📋 Data baru:");
        System.out.println(p);
    }
}