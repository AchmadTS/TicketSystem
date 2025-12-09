package controller;

import model.Penerbangan;

import java.text.DecimalFormat;

public class EditPenerbangan {
    private SistemTiket sistem;

    public EditPenerbangan(SistemTiket sistem) {
        this.sistem = sistem;
    }

    public void run() {
        System.out.println("\n=== EDIT PENERBANGAN ===\n");
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
        }

        System.out.println("\n📋 Data saat ini:");
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
        System.out.print("Harga baru [Rp" + df.format(p.harga) + "]: ");
        String inputHarga = sistem.input.nextLine().trim();
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
                    p.harga = hargaBaru;
                } else {
                    System.out.println("⚠️ Harga harus lebih dari 0! Harga idak diubah");
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

                        switch (namaBulan) {
                            case "januari":
                                bulan = 1;
                                break;
                            case "februari":
                                bulan = 2;
                                break;
                            case "maret":
                                bulan = 3;
                                break;
                            case "april":
                                bulan = 4;
                                break;
                            case "mei":
                                bulan = 5;
                                break;
                            case "juni":
                                bulan = 6;
                                break;
                            case "juli":
                                bulan = 7;
                                break;
                            case "agustus":
                                bulan = 8;
                                break;
                            case "september":
                                bulan = 9;
                                break;
                            case "oktober":
                                bulan = 10;
                                break;
                            case "november":
                                bulan = 11;
                                break;
                            case "desember":
                                bulan = 12;
                                break;
                            default:
                                System.out.println("❌ Nama bulan tidak valid!");
                                inputBenar = false;
                        }

                        if (inputBenar) {
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
        }

        System.out.println();
        System.out.println("✅ Data berhasil diperbarui!");
        System.out.println("\n📋 Data baru:");
        System.out.println(p);
    }
}