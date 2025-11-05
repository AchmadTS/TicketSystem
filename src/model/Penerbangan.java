package model;

import java.text.DecimalFormat;

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

    public Penerbangan(int id, String pesawat, String asal, String tujuan, double harga, int bulan, int hari, int tahun, int jumlahKursi) {
        this.id = id;
        this.pesawat = pesawat;
        this.asal = asal;
        this.tujuan = tujuan;
        this.harga = harga;
        this.bulan = bulan;
        this.hari = hari;
        this.tahun = tahun;
        this.jumlahKursi = jumlahKursi;
    }

    private String namaBulan(int bulan) {
        switch (bulan) {
            case 1: return "Januari";
            case 2: return "Februari";
            case 3: return "Maret";
            case 4: return "April";
            case 5: return "Mei";
            case 6: return "Juni";
            case 7: return "Juli";
            case 8: return "Agustus";
            case 9: return "September";
            case 10: return "Oktober";
            case 11: return "November";
            case 12: return "Desember";
            default: return "Bulan tidak valid";
        }
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
