package controller;

public class TambahPenerbangan {
    private SistemTiket sistem;

    public TambahPenerbangan(SistemTiket sistem) {
        this.sistem = sistem;
    }

    public void run() {
        System.out.println();
        System.out.println("╔═════════════════════════════════════════════════════════╗");
        System.out.println("║                 ➕ TAMBAH PENERBANGAN                   ║");
        System.out.println("╚═════════════════════════════════════════════════════════╝");
        String pesawat = "";
        while (pesawat.isEmpty()) {
            System.out.print("Nama pesawat: ");
            pesawat = sistem.input.nextLine().trim();
            if (pesawat.isEmpty()) {
                System.out.println("❌ Nama pesawat tidak boleh kosong!");
            }
        }

        String asal = "";
        while (asal.isEmpty()) {
            System.out.print("Asal: ");
            asal = sistem.input.nextLine().trim();
            if (asal.isEmpty()) {
                System.out.println("❌ Kota asal tidak boleh kosong!");
            }
        }

        String tujuan = "";
        while (tujuan.isEmpty()) {
            System.out.print("Tujuan: ");
            tujuan = sistem.input.nextLine().trim();
            if (tujuan.isEmpty()) {
                System.out.println("❌ Kota tujuan tidak boleh kosong!");
            }
        }

        double harga = 0;
        boolean hargaValid = false;
        while (!hargaValid) {
            System.out.print("Harga: ");
            String inputHarga = sistem.input.nextLine().trim();

            if (inputHarga.isEmpty()) {
                System.out.println("❌ Harga tidak boleh kosong!");
                continue;
            }

            boolean formatValid = true;
            int jumlahTitik = 0;

            for (int i = 0; i < inputHarga.length(); i++) {
                char c = inputHarga.charAt(i);
                if (c == '.') {
                    jumlahTitik++;
                    if (jumlahTitik > 1) {
                        formatValid = false;
                        break;
                    }
                } else if (c < '0' || c > '9') {
                    formatValid = false;
                    break;
                }
            }
            if (!formatValid) {
                System.out.println("❌ Harga harus berupa angka!");
                continue;
            }

            harga = Double.parseDouble(inputHarga);
            if (harga <= 0) {
                System.out.println("❌ Harga harus lebih dari 0!");
                continue;
            }

            hargaValid = true;
        }

        int hari = 0, bulan = 0, tahun = 0;
        boolean valid = false;
        while (!valid) {
            System.out.print("Masukkan waktu (contoh: 28 februari 2024): ");
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

        int kursi = 0;
        boolean kursiValid = false;
        while (!kursiValid) {
            System.out.print("Jumlah kursi: ");
            String inputKursi = sistem.input.nextLine().trim();

            if (inputKursi.isEmpty()) {
                System.out.println("❌ Jumlah kursi tidak boleh kosong!");
                continue;
            }
            boolean formatValid = true;
            for (int i = 0; i < inputKursi.length(); i++) {
                char c = inputKursi.charAt(i);
                if (c < '0' || c > '9') {
                    formatValid = false;
                    break;
                }
            }

            if (!formatValid) {
                System.out.println("❌ Jumlah kursi harus berupa angka bulat!");
                continue;
            }

            kursi = Integer.parseInt(inputKursi);
            if (kursi <= 0) {
                System.out.println("❌ Jumlah kursi harus lebih dari 0!");
                continue;
            }
            kursiValid = true;
        }

        sistem.daftarPenerbangan[sistem.jumlahPenerbangan++] =
                new model.Penerbangan(sistem.nextIdPenerbangan++, pesawat, asal, tujuan, harga, hari, bulan, tahun, kursi);
        System.out.println("✅ Penerbangan berhasil ditambahkan!");
    }
}