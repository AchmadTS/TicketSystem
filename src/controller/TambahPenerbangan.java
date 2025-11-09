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

        System.out.print("Masukkan waktu (tanggal bulan tahun): ");
        String[] waktu = sistem.input.nextLine().split(" ");

        int hari = Integer.parseInt(waktu[0]);
        String namaBulan = waktu[1].toLowerCase();
        int tahun = Integer.parseInt(waktu[2]);

        int bulan = 0;
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
                System.out.println("❌ Bulan tidak valid!");
                return;
        }

        System.out.print("Jumlah kursi: ");
        int kursi = Integer.parseInt(sistem.input.nextLine());

        sistem.daftarPenerbangan[sistem.jumlahPenerbangan++] =
                new model.Penerbangan(sistem.nextIdPenerbangan++, pesawat, asal, tujuan, harga, hari, bulan, tahun, kursi);
        System.out.println("✅ Penerbangan ditambahkan.");
    }
}