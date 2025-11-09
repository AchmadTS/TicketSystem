package model;

import controller.SistemTiket;

import java.text.DecimalFormat;
import java.time.LocalDateTime;

public class Penerbangan {
    public int id;
    public String pesawat;
    public String asal;
    public String tujuan;
    public double harga;
    public int bulan;
    public int hari;
    public int tahun;
    public int jumlahKursi;

    public Penerbangan(int id, String pesawat, String asal, String tujuan, double harga, int hari, int bulan, int tahun, int jumlahKursi) {
        this.id = id;
        this.pesawat = pesawat;
        this.asal = asal;
        this.tujuan = tujuan;
        this.harga = harga;
        this.hari = hari;
        this.bulan = bulan;
        this.tahun = tahun;
        this.jumlahKursi = jumlahKursi;
    }

    public class PesanTiket {
        private SistemTiket sistem;

        public PesanTiket(SistemTiket sistem) {
            this.sistem = sistem;
        }

        public void run() {
            sistem.view.showDaftarPenerbangan(sistem.daftarPenerbangan, sistem.jumlahPenerbangan);
            System.out.print("\nMasukkan ID penerbangan: ");
            int id = Integer.parseInt(sistem.input.nextLine());
            Penerbangan p = sistem.cariById(id);

            if (p == null) {
                System.out.println("❌ Tidak ditemukan.");
                return;
            }

            System.out.print("Nama pemesan: ");
            String nama = sistem.input.nextLine();
            System.out.print("Jumlah tiket: ");
            int jumlah = Integer.parseInt(sistem.input.nextLine());

            if (jumlah > p.jumlahKursi) {
                System.out.println("❌ Kursi tidak cukup!");
                return;
            }

            p.jumlahKursi -= jumlah;
            double total = jumlah * p.harga;

            sistem.daftarPemesanan[sistem.jumlahPemesanan++] =
                    new Pemesanan(sistem.nextIdPemesanan++, p.id, nama, jumlah, total, LocalDateTime.now());

            System.out.printf("✅ Pemesanan berhasil! Total: Rp%,.0f\n", total);
        }
    }

    private String namaBulan(int bulan) {
        String namaBln;
        switch (bulan) {
            case 1:
                namaBln = "Januari";
                break;
            case 2:
                namaBln = "Februari";
                break;
            case 3:
                namaBln = "Maret";
                break;
            case 4:
                namaBln = "April";
                break;
            case 5:
                namaBln = "Mei";
                break;
            case 6:
                namaBln = "Juni";
                break;
            case 7:
                namaBln = "Juli";
                break;
            case 8:
                namaBln = "Agustus";
                break;
            case 9:
                namaBln = "September";
                break;
            case 10:
                namaBln = "Oktober";
                break;
            case 11:
                namaBln = "November";
                break;
            case 12:
                namaBln = "Desember";
                break;
            default:
                namaBln = "Bulan tidak valid";
                break;
        }
        return namaBln;
    }

    @Override
    public String toString() {
        String namaBulan = namaBulan(this.bulan);
        DecimalFormat df = new DecimalFormat("#,###");

        return "ID:" + id +
                " | " + pesawat +
                " | " + asal + " -> " + tujuan +
                " | " + (hari < 10 ? "0" + hari : hari) + " " + namaBulan + " " + tahun +
                " | Harga: Rp" + df.format(harga) +
                " | Kursi: " + jumlahKursi;
    }
}
