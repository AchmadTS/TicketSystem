package controller.create;

import controller.SistemTiket;
import util.Helper;

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

        String pesawat = Helper.inputStringWajib(sistem.input, "Nama pesawat: ");
        String asal = Helper.inputStringWajib(sistem.input, "Asal: ");
        String tujuan = Helper.inputStringWajib(sistem.input, "Tujuan: ");
        double harga = Helper.inputHarga(sistem.input, "Harga: ");
        int jam = Helper.inputInteger(sistem.input, "Masukkan jam keberangkatan (0-23): ", 0, 23);
        int menit = Helper.inputInteger(sistem.input, "Masukkan menit keberangkatan (0-59): ", 0, 59);
        int kursi = Helper.inputInteger(sistem.input, "Jumlah kursi: ", 1, Integer.MAX_VALUE);

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
                    bulan = Helper.getBulanDariNama(namaBulanAsli);

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

        sistem.daftarPenerbangan[sistem.jumlahPenerbangan++] = new model.Penerbangan(sistem.nextIdPenerbangan++, pesawat, asal, tujuan, harga, hari, bulan, tahun, jam, menit, kursi);
        System.out.println("✅ Penerbangan berhasil ditambahkan!");
    }
}