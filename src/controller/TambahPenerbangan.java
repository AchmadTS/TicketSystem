package controller;

public class TambahPenerbangan {
    private SistemTiket sistem;

    public TambahPenerbangan(SistemTiket sistem) {
        this.sistem = sistem;
    }

    public void run() {
        System.out.print("Nama pesawat: ");
        String pesawat = sistem.input.nextLine();

        System.out.print("Asal: ");
        String asal = sistem.input.nextLine();

        System.out.print("Tujuan: ");
        String tujuan = sistem.input.nextLine();

        System.out.print("Harga: ");
        double harga = Double.parseDouble(sistem.input.nextLine());

        int hari = 0, bulan = 0, tahun = 0;
        boolean valid = false;

        do {
            System.out.print("Masukkan waktu (tanggal bulan tahun): ");
            String[] waktu = sistem.input.nextLine().split(" ");

            if (waktu.length != 3) {
                System.out.println("❌ Format harus: tanggal bulan tahun (contoh: 29 februari 2024)");
                continue;
            }

            String strHari = waktu[0];
            String namaBulan = waktu[1].toLowerCase();
            String strTahun = waktu[2];

            boolean hariAngka = strHari.matches("\\d+");
            boolean tahunAngka = strTahun.matches("\\d+");

            if (!hariAngka || !tahunAngka) {
                System.out.println("❌ Tanggal dan tahun harus berupa angka!");
                continue;
            }

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
                    continue;
            }

            int maxHari;
            switch (bulan) {
                case 2:
                    boolean kabisat = (tahun % 4 == 0 && tahun % 100 != 0) || (tahun % 400 == 0);
                    maxHari = kabisat ? 29 : 28;
                    break;
                case 4:
                case 6:
                case 9:
                case 11:
                    maxHari = 30;
                    break;
                default:
                    maxHari = 31;
            }

            if (hari < 1 || hari > maxHari) {
                System.out.println("❌ Tanggal tidak valid untuk bulan yang dipilih!");
            } else {
                valid = true;
            }

        } while (!valid);

        System.out.print("Jumlah kursi: ");
        int kursi = Integer.parseInt(sistem.input.nextLine());

        sistem.daftarPenerbangan[sistem.jumlahPenerbangan++] =
                new model.Penerbangan(sistem.nextIdPenerbangan++, pesawat, asal, tujuan, harga, hari, bulan, tahun, kursi);
        System.out.println("✅ Penerbangan ditambahkan.");
    }
}